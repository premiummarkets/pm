// $ANTLR 3.5.2 com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g 2018-08-17 22:18:40
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:78:7: ( 'makes a higher high over' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:78:9: 'makes a higher high over'
			{
			match("makes a higher high over"); 

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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:87:7: ( 'smoothed' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:87:9: 'smoothed'
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
	// $ANTLR end "T__79"

	// $ANTLR start "T__80"
	public final void mT__80() throws RecognitionException {
		try {
			int _type = T__80;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:88:7: ( 'spanning' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:88:9: 'spanning'
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
	// $ANTLR end "T__80"

	// $ANTLR start "Operation"
	public final void mOperation() throws RecognitionException {
		try {
			int _type = Operation;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:270:7: ({...}? => ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )+ )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:270:9: {...}? => ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )+
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:270:60: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )+
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:274:7: ( ( '-' )? ( '0' .. '9' )+ ( '.' ( '0' .. '9' )+ )? )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:274:10: ( '-' )? ( '0' .. '9' )+ ( '.' ( '0' .. '9' )+ )?
			{
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:274:10: ( '-' )?
			int alt2=2;
			int LA2_0 = input.LA(1);
			if ( (LA2_0=='-') ) {
				alt2=1;
			}
			switch (alt2) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:274:11: '-'
					{
					match('-'); 
					}
					break;

			}

			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:274:17: ( '0' .. '9' )+
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

			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:274:29: ( '.' ( '0' .. '9' )+ )?
			int alt5=2;
			int LA5_0 = input.LA(1);
			if ( (LA5_0=='.') ) {
				alt5=1;
			}
			switch (alt5) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:274:30: '.' ( '0' .. '9' )+
					{
					match('.'); 
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:274:34: ( '0' .. '9' )+
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:278:6: ( '\"' ( 'a' .. 'z' | 'A' .. 'Z' | '.' | '_' )* '\"' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:278:8: '\"' ( 'a' .. 'z' | 'A' .. 'Z' | '.' | '_' )* '\"'
			{
			match('\"'); 
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:278:12: ( 'a' .. 'z' | 'A' .. 'Z' | '.' | '_' )*
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:282:4: ({...}? => ( 'open' | 'close' | 'high' | 'low' | 'volume' ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:282:6: {...}? => ( 'open' | 'close' | 'high' | 'low' | 'volume' )
			{
			if ( !((runtimeHistoryOpAhead())) ) {
				throw new FailedPredicateException(input, "HistoricalData", "runtimeHistoryOpAhead()");
			}
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:282:36: ( 'open' | 'close' | 'high' | 'low' | 'volume' )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:282:38: 'open'
					{
					match("open"); 

					}
					break;
				case 2 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:282:47: 'close'
					{
					match("close"); 

					}
					break;
				case 3 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:282:57: 'high'
					{
					match("high"); 

					}
					break;
				case 4 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:282:66: 'low'
					{
					match("low"); 

					}
					break;
				case 5 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:282:75: 'volume'
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:286:7: ( ( ( ' ' )+ ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:286:9: ( ( ' ' )+ )
			{
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:286:9: ( ( ' ' )+ )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:286:10: ( ' ' )+
			{
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:286:10: ( ' ' )+
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:286:10: ' '
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:290:6: ( ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )+ )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:290:8: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )+
			{
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:290:8: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )+
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:295:5: ( ( '\\r' | '\\t' | '\\u000C' | '\\n' ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:295:7: ( '\\r' | '\\t' | '\\u000C' | '\\n' )
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:298:5: ( '/*' ( . )* '*/' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:298:7: '/*' ( . )* '*/'
			{
			match("/*"); 

			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:298:12: ( . )*
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:298:12: .
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:301:5: ( '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:301:7: '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n'
			{
			match("//"); 

			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:301:12: (~ ( '\\n' | '\\r' ) )*
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

			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:301:26: ( '\\r' )?
			int alt12=2;
			int LA12_0 = input.LA(1);
			if ( (LA12_0=='\r') ) {
				alt12=1;
			}
			switch (alt12) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:301:26: '\\r'
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
		// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:8: ( AND | CLOSEPARENTEHSIS | COMMA | DAYS | LENIENT | NOT | OPENPARENTEHSIS | OR | PERCENT | T__50 | T__51 | T__52 | T__53 | T__54 | T__55 | T__56 | T__57 | T__58 | T__59 | T__60 | T__61 | T__62 | T__63 | T__64 | T__65 | T__66 | T__67 | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | T__79 | T__80 | Operation | NumberToken | StringToken | HistoricalData | WhiteChar | Tcheat | WS | COMMENT | LINE_COMMENT )
		int alt13=49;
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
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:261: T__80
				{
				mT__80(); 

				}
				break;
			case 41 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:267: Operation
				{
				mOperation(); 

				}
				break;
			case 42 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:277: NumberToken
				{
				mNumberToken(); 

				}
				break;
			case 43 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:289: StringToken
				{
				mStringToken(); 

				}
				break;
			case 44 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:301: HistoricalData
				{
				mHistoricalData(); 

				}
				break;
			case 45 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:316: WhiteChar
				{
				mWhiteChar(); 

				}
				break;
			case 46 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:326: Tcheat
				{
				mTcheat(); 

				}
				break;
			case 47 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:333: WS
				{
				mWS(); 

				}
				break;
			case 48 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:336: COMMENT
				{
				mCOMMENT(); 

				}
				break;
			case 49 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:344: LINE_COMMENT
				{
				mLINE_COMMENT(); 

				}
				break;

		}
	}


	protected DFA13 dfa13 = new DFA13(this);
	static final String DFA13_eotS =
		"\1\uffff\1\40\2\uffff\3\40\1\uffff\1\40\1\uffff\12\40\1\uffff\1\24\1\uffff"+
		"\2\40\4\uffff\1\72\1\74\1\75\1\uffff\1\77\1\101\1\103\1\105\1\106\1\110"+
		"\1\112\1\114\1\116\1\120\1\122\1\124\1\126\1\130\1\132\1\134\1\136\1\140"+
		"\1\142\1\144\1\146\1\150\2\uffff\1\151\1\uffff\1\153\2\uffff\1\155\1\uffff"+
		"\1\157\1\uffff\1\160\1\uffff\1\161\2\uffff\1\163\1\uffff\1\165\1\uffff"+
		"\1\167\1\uffff\1\171\1\uffff\1\173\1\uffff\1\175\1\uffff\1\177\1\uffff"+
		"\1\u0080\1\uffff\1\u0082\3\uffff\1\u0086\1\uffff\1\u0088\1\uffff\1\u008a"+
		"\1\uffff\1\u008c\1\uffff\1\u008e\1\uffff\1\u0090\1\uffff\1\u0092\2\uffff"+
		"\1\u0094\1\uffff\1\u0095\1\uffff\1\u0097\3\uffff\1\u009a\1\uffff\1\u009b"+
		"\1\uffff\1\u009d\1\uffff\1\u009f\1\uffff\1\u00a1\1\uffff\1\u00a3\1\uffff"+
		"\1\u00a5\2\uffff\1\u00a7\3\uffff\1\u00ac\1\uffff\1\u00ae\1\uffff\1\u00b0"+
		"\1\uffff\1\u00b2\1\uffff\1\u00b4\1\uffff\1\u00b5\1\uffff\1\u00b7\4\uffff"+
		"\1\u00b9\2\uffff\1\u00bb\2\uffff\1\u00bd\1\uffff\1\u00bf\1\uffff\1\u00c1"+
		"\1\uffff\1\u00c2\1\uffff\1\u00c4\6\uffff\1\u00cb\3\uffff\1\u00cd\1\uffff"+
		"\1\u00cf\1\uffff\1\u00d1\2\uffff\1\u00d3\1\uffff\1\u00d5\1\uffff\1\u00d7"+
		"\1\uffff\1\u00d9\1\uffff\1\u00db\1\uffff\1\u00dd\2\uffff\1\u00df\10\uffff"+
		"\1\u00e5\1\uffff\1\u00e7\1\uffff\1\u00e9\1\uffff\1\u00ea\1\uffff\1\u00eb"+
		"\1\uffff\1\u00ed\1\uffff\1\u00ee\1\uffff\1\u00ef\1\uffff\1\u00f1\7\uffff"+
		"\1\u00f9\1\uffff\1\u00fb\1\uffff\1\u00fd\3\uffff\1\u00ff\13\uffff\1\u010a"+
		"\1\uffff\1\u010b\1\uffff\1\u010c\63\uffff";
	static final String DFA13_eofS =
		"\u0130\uffff";
	static final String DFA13_minS =
		"\1\11\1\60\2\uffff\3\60\1\uffff\1\60\1\uffff\12\60\1\uffff\1\60\1\uffff"+
		"\2\60\3\uffff\1\52\3\60\1\uffff\16\60\1\40\7\60\2\uffff\1\60\1\0\1\60"+
		"\2\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\uffff\1\60\1\0\1\60\1\0\1\60"+
		"\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\141\1\0\1"+
		"\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\uffff"+
		"\1\40\1\0\1\60\1\0\1\60\2\0\1\uffff\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1"+
		"\0\1\60\1\0\1\60\1\0\1\60\1\0\1\uffff\1\40\1\0\1\142\1\145\1\60\1\0\1"+
		"\40\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\uffff\1\0\1\uffff"+
		"\1\60\1\0\1\uffff\1\60\1\uffff\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0"+
		"\1\60\1\0\1\144\1\0\1\157\1\141\1\uffff\1\40\1\0\1\uffff\1\0\1\60\1\0"+
		"\1\60\1\0\1\60\2\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\2"+
		"\0\1\40\1\0\2\uffff\1\166\1\162\1\157\1\141\1\0\1\60\1\0\1\60\1\0\1\60"+
		"\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\40\1\0\1\150\1\0\1"+
		"\145\1\151\1\167\1\40\1\60\1\0\1\60\1\0\1\60\2\0\1\uffff\1\40\1\0\2\uffff"+
		"\1\144\1\0\1\uffff\1\150\1\40\1\163\1\40\1\150\1\40\1\0\1\60\1\0\1\60"+
		"\1\0\1\uffff\1\0\1\157\1\160\2\uffff\3\150\1\151\1\157\1\144\1\0\2\uffff"+
		"\1\167\1\40\2\uffff\1\40\2\uffff\1\147\1\167\2\uffff\1\156\1\150\1\151"+
		"\1\150\1\145\1\40\4\uffff\1\145\1\162\1\150\1\162\1\40\2\uffff\1\40\2"+
		"\150\4\uffff";
	static final String DFA13_maxS =
		"\2\172\2\uffff\3\172\1\uffff\1\172\1\uffff\12\172\1\uffff\1\172\1\uffff"+
		"\2\172\3\uffff\1\57\3\172\1\uffff\26\172\2\uffff\1\172\1\0\1\172\2\0\1"+
		"\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\uffff\1\172\1\0\1\172\1\0\1\172"+
		"\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\142"+
		"\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172"+
		"\1\0\1\uffff\1\172\1\0\1\172\1\0\1\172\2\0\1\uffff\1\172\1\0\1\172\1\0"+
		"\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\uffff\1\172\1\0\1"+
		"\142\1\165\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0"+
		"\1\172\1\0\1\uffff\1\0\1\uffff\1\172\1\0\1\uffff\1\172\1\uffff\1\0\1\172"+
		"\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\165\1\0\1\157\1\154\1\uffff"+
		"\1\172\1\0\1\uffff\1\0\1\172\1\0\1\172\1\0\1\172\2\0\1\172\1\0\1\172\1"+
		"\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\2\0\1\172\1\0\2\uffff\1\166\1\162"+
		"\1\157\1\141\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172"+
		"\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\164\1\0\1\145\1\151\1\167\1\40\1"+
		"\172\1\0\1\172\1\0\1\172\2\0\1\uffff\1\172\1\0\2\uffff\1\165\1\0\1\uffff"+
		"\1\162\1\40\1\163\1\40\1\154\1\172\1\0\1\172\1\0\1\172\1\0\1\uffff\1\0"+
		"\1\157\1\160\2\uffff\1\164\1\150\1\164\1\151\1\157\1\165\1\0\2\uffff\1"+
		"\167\1\40\2\uffff\1\40\2\uffff\1\147\1\167\2\uffff\1\156\1\164\1\167\1"+
		"\150\1\145\1\40\4\uffff\1\145\1\162\1\164\1\162\1\40\2\uffff\1\40\2\154"+
		"\4\uffff";
	static final String DFA13_acceptS =
		"\2\uffff\1\2\1\3\3\uffff\1\7\1\uffff\1\11\12\uffff\1\52\1\uffff\1\53\2"+
		"\uffff\1\55\1\51\1\57\4\uffff\1\56\26\uffff\1\60\1\61\15\uffff\1\10\42"+
		"\uffff\1\1\7\uffff\1\6\16\uffff\1\24\22\uffff\1\12\1\uffff\1\4\2\uffff"+
		"\1\54\1\uffff\1\43\17\uffff\1\35\2\uffff\1\42\27\uffff\1\25\1\26\44\uffff"+
		"\1\5\2\uffff\1\13\1\14\2\uffff\1\21\13\uffff\1\44\3\uffff\1\22\1\23\7"+
		"\uffff\1\47\1\50\2\uffff\1\27\1\30\1\uffff\1\33\1\34\2\uffff\1\45\1\46"+
		"\6\uffff\1\17\1\20\1\31\1\32\5\uffff\1\15\1\16\3\uffff\1\40\1\41\1\36"+
		"\1\37";
	static final String DFA13_specialS =
		"\1\174\1\u00b9\2\uffff\1\u0090\1\141\1\u009c\1\uffff\1\u00b7\1\uffff\1"+
		"\5\1\u009d\1\11\1\4\1\u00ae\1\u008e\1\14\1\10\1\142\1\u00c3\3\uffff\1"+
		"\u00cb\1\153\4\uffff\1\152\1\171\1\12\1\uffff\1\156\1\160\1\u00af\1\165"+
		"\1\143\1\u00b4\1\u00a5\1\u0081\1\u0086\1\u00bc\1\u00a8\1\u00c7\1\u00b8"+
		"\1\u008b\1\155\1\u009f\1\166\1\u00bd\1\u008f\1\u0096\1\u00ac\1\u00b0\2"+
		"\uffff\1\6\1\17\1\172\1\20\1\16\1\157\1\46\1\161\1\66\1\u00a6\1\67\1\15"+
		"\1\76\1\uffff\1\u00b5\1\106\1\u00a7\1\113\1\u0082\1\114\1\u0087\1\70\1"+
		"\u00be\1\100\1\u00a9\1\124\1\u00c9\1\42\1\u00b6\1\45\1\u008c\1\33\1\uffff"+
		"\1\43\1\u00a0\1\140\1\167\1\50\1\u00bf\1\133\1\u0091\1\121\1\u0097\1\72"+
		"\1\u00ad\1\47\1\u00b1\1\134\1\uffff\1\173\1\57\1\7\1\41\1\162\1\126\1"+
		"\144\1\uffff\1\3\1\32\1\u009e\1\21\1\u0083\1\123\1\u0088\1\36\1\u00c1"+
		"\1\101\1\u00aa\1\51\1\u00cc\1\103\1\uffff\1\u008d\1\111\2\uffff\1\u00a2"+
		"\1\77\1\170\1\55\1\u00c0\1\107\1\u0092\1\75\1\u0098\1\116\1\u00a3\1\104"+
		"\1\u00b2\1\136\1\uffff\1\115\1\uffff\1\163\1\62\1\uffff\1\175\1\uffff"+
		"\1\145\1\u0084\1\127\1\u0089\1\30\1\u00c5\1\60\1\u00a1\1\31\1\1\1\65\1"+
		"\uffff\1\131\3\uffff\1\u00a4\1\74\1\uffff\1\44\1\u00c2\1\61\1\u0093\1"+
		"\120\1\u0099\1\34\1\146\1\u00b3\1\135\1\164\1\122\1\176\1\63\1\u0085\1"+
		"\56\1\u008a\1\132\1\u00ca\1\37\1\147\1\2\1\54\6\uffff\1\110\1\u00c4\1"+
		"\24\1\u0094\1\137\1\u009a\1\25\1\u00ab\1\52\1\13\1\35\1\177\1\64\1\151"+
		"\1\71\1\154\1\53\1\0\1\112\1\uffff\1\102\4\uffff\1\u00c6\1\23\1\u0095"+
		"\1\40\1\u009b\1\105\1\150\1\uffff\1\u0080\1\73\3\uffff\1\130\6\uffff\1"+
		"\u00c8\1\117\1\u00ba\1\26\1\u00bb\1\125\1\uffff\1\22\12\uffff\1\27\45"+
		"\uffff}>";
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
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\14\37\1\63\2\37\1\64\12\37",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\10\37\1\65\21\37",
			"",
			"\12\25\7\uffff\32\40\6\uffff\32\40",
			"",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\16\37\1\66\13\37",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\32\37",
			"",
			"",
			"",
			"\1\67\4\uffff\1\70",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\3\37\1\71\26\37",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\22\37\1\73\7\37",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\32\37",
			"",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\30\37\1\76\1\37",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\15\37\1\100\14\37",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\26\37\1\102\3\37",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\23\37\1\104\6\37",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\32\37",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\4\37\1\107\25\37",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\4\37\1\111\25\37",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\1\113\31\37",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\13\37\1\115\16\37",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\16\37\1\117\13\37",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\16\37\1\121\13\37",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\24\37\1\123\5\37",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\21\37\1\125\10\37",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\4\37\1\127\25\37",
			"\1\131\17\uffff\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\32\37",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\12\37\1\133\17\37",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\21\37\1\135\10\37",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\25\37\1\137\4\37",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\16\37\1\141\13\37",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\1\143\31\37",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\6\37\1\145\23\37",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\13\37\1\147\16\37",
			"",
			"",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\32\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\16\37\1\152\13\37",
			"\1\uffff",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\22\37\1\154\7\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\10\37\1\156\21\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\32\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\32\37",
			"\1\uffff",
			"",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\21\37\1\162\10\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\15\37\1\164\14\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\21\37\1\166\10\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\13\37\1\170\16\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\22\37\1\172\7\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\22\37\1\174\7\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\1\176\31\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\32\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\22\37\1\u0081\7\37",
			"\1\uffff",
			"\1\u0083\1\u0084",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\4\37\1\u0085\25\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\4\37\1\u0087\25\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\4\37\1\u0089\25\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\16\37\1\u008b\13\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\15\37\1\u008d\14\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\7\37\1\u008f\22\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\24\37\1\u0091\5\37",
			"\1\uffff",
			"",
			"\1\u0093\17\uffff\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\32\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\32\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\4\37\1\u0096\25\37",
			"\1\uffff",
			"\1\uffff",
			"",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\21\37\1\u0099\10\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\32\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\10\37\1\u009c\21\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\10\37\1\u009e\21\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\22\37\1\u00a0\7\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\4\37\1\u00a2\25\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\13\37\1\u00a4\16\37",
			"\1\uffff",
			"",
			"\1\u00a6\17\uffff\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\32\37",
			"\1\uffff",
			"\1\u00a8",
			"\1\u00a9\17\uffff\1\u00aa",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\22\37\1\u00ab\7\37",
			"\1\uffff",
			"\1\u00ad\17\uffff\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\32\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\21\37\1\u00af\10\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\23\37\1\u00b1\6\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\15\37\1\u00b3\14\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\32\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\14\37\1\u00b6\15\37",
			"\1\uffff",
			"",
			"\1\uffff",
			"",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\15\37\1\u00b8\14\37",
			"\1\uffff",
			"",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\10\37\1\u00ba\21\37",
			"",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\22\37\1\u00bc\7\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\22\37\1\u00be\7\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\4\37\1\u00c0\25\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\32\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\22\37\1\u00c3\7\37",
			"\1\uffff",
			"\1\u00c5\20\uffff\1\u00c6",
			"\1\uffff",
			"\1\u00c7",
			"\1\u00c8\12\uffff\1\u00c9",
			"",
			"\1\u00ca\17\uffff\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\32\37",
			"\1\uffff",
			"",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\22\37\1\u00cc\7\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\7\37\1\u00ce\22\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\10\37\1\u00d0\21\37",
			"\1\uffff",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\4\37\1\u00d2\25\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\23\37\1\u00d4\6\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\3\37\1\u00d6\26\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\7\37\1\u00d8\22\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\7\37\1\u00da\22\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\22\37\1\u00dc\7\37",
			"\1\uffff",
			"\1\uffff",
			"\1\u00de\17\uffff\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\32\37",
			"\1\uffff",
			"",
			"",
			"\1\u00e0",
			"\1\u00e1",
			"\1\u00e2",
			"\1\u00e3",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\4\37\1\u00e4\25\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\4\37\1\u00e6\25\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\15\37\1\u00e8\14\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\32\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\32\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\4\37\1\u00ec\25\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\32\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\32\37",
			"\1\uffff",
			"\1\u00f0\17\uffff\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\32\37",
			"\1\uffff",
			"\1\u00f2\13\uffff\1\u00f3",
			"\1\uffff",
			"\1\u00f4",
			"\1\u00f5",
			"\1\u00f6",
			"\1\u00f7",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\22\37\1\u00f8\7\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\3\37\1\u00fa\26\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\6\37\1\u00fc\23\37",
			"\1\uffff",
			"\1\uffff",
			"",
			"\1\u00fe\17\uffff\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\32\37",
			"\1\uffff",
			"",
			"",
			"\1\u0100\20\uffff\1\u0101",
			"\1\uffff",
			"",
			"\1\u0102\11\uffff\1\u0103",
			"\1\u0104",
			"\1\u0105",
			"\1\u0106",
			"\1\u0107\3\uffff\1\u0108",
			"\1\u0109\17\uffff\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\32\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\32\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\32\37",
			"\1\uffff",
			"",
			"\1\uffff",
			"\1\u010d",
			"\1\u010e",
			"",
			"",
			"\1\u010f\13\uffff\1\u0110",
			"\1\u0111",
			"\1\u0112\13\uffff\1\u0113",
			"\1\u0114",
			"\1\u0115",
			"\1\u0116\20\uffff\1\u0117",
			"\1\uffff",
			"",
			"",
			"\1\u0118",
			"\1\u0119",
			"",
			"",
			"\1\u011a",
			"",
			"",
			"\1\u011b",
			"\1\u011c",
			"",
			"",
			"\1\u011d",
			"\1\u011e\13\uffff\1\u011f",
			"\1\u0120\15\uffff\1\u0121",
			"\1\u0122",
			"\1\u0123",
			"\1\u0124",
			"",
			"",
			"",
			"",
			"\1\u0125",
			"\1\u0126",
			"\1\u0127\13\uffff\1\u0128",
			"\1\u0129",
			"\1\u012a",
			"",
			"",
			"\1\u012b",
			"\1\u012c\3\uffff\1\u012d",
			"\1\u012e\3\uffff\1\u012f",
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
			return "1:1: Tokens : ( AND | CLOSEPARENTEHSIS | COMMA | DAYS | LENIENT | NOT | OPENPARENTEHSIS | OR | PERCENT | T__50 | T__51 | T__52 | T__53 | T__54 | T__55 | T__56 | T__57 | T__58 | T__59 | T__60 | T__61 | T__62 | T__63 | T__64 | T__65 | T__66 | T__67 | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | T__79 | T__80 | Operation | NumberToken | StringToken | HistoricalData | WhiteChar | Tcheat | WS | COMMENT | LINE_COMMENT );";
		}
		@Override
		public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
			IntStream input = _input;
			int _s = s;
			switch ( s ) {
					case 0 : 
						int LA13_220 = input.LA(1);
						 
						int index13_220 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_220==' ') ) {s = 240;}
						else if ( ((LA13_220 >= '0' && LA13_220 <= '9')||(LA13_220 >= 'A' && LA13_220 <= 'Z')||(LA13_220 >= 'a' && LA13_220 <= 'z')) ) {s = 31;}
						else if ( (LA13_220=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 241;
						 
						input.seek(index13_220);
						if ( s>=0 ) return s;
						break;

					case 1 : 
						int LA13_164 = input.LA(1);
						 
						int index13_164 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_164=='s') ) {s = 195;}
						else if ( ((LA13_164 >= '0' && LA13_164 <= '9')||(LA13_164 >= 'A' && LA13_164 <= 'Z')||(LA13_164 >= 'a' && LA13_164 <= 'r')||(LA13_164 >= 't' && LA13_164 <= 'z')) ) {s = 31;}
						else if ( (LA13_164=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 196;
						 
						input.seek(index13_164);
						if ( s>=0 ) return s;
						break;

					case 2 : 
						int LA13_195 = input.LA(1);
						 
						int index13_195 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_195==' ') ) {s = 222;}
						else if ( ((LA13_195 >= '0' && LA13_195 <= '9')||(LA13_195 >= 'A' && LA13_195 <= 'Z')||(LA13_195 >= 'a' && LA13_195 <= 'z')) ) {s = 31;}
						else if ( (LA13_195=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 223;
						 
						input.seek(index13_195);
						if ( s>=0 ) return s;
						break;

					case 3 : 
						int LA13_114 = input.LA(1);
						 
						int index13_114 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_114=='r') ) {s = 153;}
						else if ( ((LA13_114 >= '0' && LA13_114 <= '9')||(LA13_114 >= 'A' && LA13_114 <= 'Z')||(LA13_114 >= 'a' && LA13_114 <= 'q')||(LA13_114 >= 's' && LA13_114 <= 'z')) ) {s = 31;}
						else if ( (LA13_114=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 154;
						 
						input.seek(index13_114);
						if ( s>=0 ) return s;
						break;

					case 4 : 
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

					case 5 : 
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

					case 6 : 
						int LA13_57 = input.LA(1);
						 
						int index13_57 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_57 >= '0' && LA13_57 <= '9')||(LA13_57 >= 'A' && LA13_57 <= 'Z')||(LA13_57 >= 'a' && LA13_57 <= 'z')) ) {s = 31;}
						else if ( (LA13_57=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 105;
						 
						input.seek(index13_57);
						if ( s>=0 ) return s;
						break;

					case 7 : 
						int LA13_108 = input.LA(1);
						 
						int index13_108 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_108 >= '0' && LA13_108 <= '9')||(LA13_108 >= 'A' && LA13_108 <= 'Z')||(LA13_108 >= 'a' && LA13_108 <= 'z')) ) {s = 31;}
						else if ( (LA13_108=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 149;
						 
						input.seek(index13_108);
						if ( s>=0 ) return s;
						break;

					case 8 : 
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

					case 9 : 
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

					case 10 : 
						int LA13_31 = input.LA(1);
						 
						int index13_31 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_31 >= '0' && LA13_31 <= '9')||(LA13_31 >= 'A' && LA13_31 <= 'Z')||(LA13_31 >= 'a' && LA13_31 <= 'z')) ) {s = 31;}
						else if ( (LA13_31=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 61;
						 
						input.seek(index13_31);
						if ( s>=0 ) return s;
						break;

					case 11 : 
						int LA13_212 = input.LA(1);
						 
						int index13_212 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_212 >= '0' && LA13_212 <= '9')||(LA13_212 >= 'A' && LA13_212 <= 'Z')||(LA13_212 >= 'a' && LA13_212 <= 'z')) ) {s = 31;}
						else if ( (LA13_212=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 235;
						 
						input.seek(index13_212);
						if ( s>=0 ) return s;
						break;

					case 12 : 
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

					case 13 : 
						int LA13_68 = input.LA(1);
						 
						int index13_68 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_68 >= '0' && LA13_68 <= '9')||(LA13_68 >= 'A' && LA13_68 <= 'Z')||(LA13_68 >= 'a' && LA13_68 <= 'z')) ) {s = 31;}
						else if ( (LA13_68=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 113;
						 
						input.seek(index13_68);
						if ( s>=0 ) return s;
						break;

					case 14 : 
						int LA13_61 = input.LA(1);
						 
						int index13_61 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_61);
						if ( s>=0 ) return s;
						break;

					case 15 : 
						int LA13_58 = input.LA(1);
						 
						int index13_58 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_58);
						if ( s>=0 ) return s;
						break;

					case 16 : 
						int LA13_60 = input.LA(1);
						 
						int index13_60 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_60);
						if ( s>=0 ) return s;
						break;

					case 17 : 
						int LA13_117 = input.LA(1);
						 
						int index13_117 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_117);
						if ( s>=0 ) return s;
						break;

					case 18 : 
						int LA13_255 = input.LA(1);
						 
						int index13_255 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_255);
						if ( s>=0 ) return s;
						break;

					case 19 : 
						int LA13_229 = input.LA(1);
						 
						int index13_229 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_229);
						if ( s>=0 ) return s;
						break;

					case 20 : 
						int LA13_205 = input.LA(1);
						 
						int index13_205 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_205);
						if ( s>=0 ) return s;
						break;

					case 21 : 
						int LA13_209 = input.LA(1);
						 
						int index13_209 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_209);
						if ( s>=0 ) return s;
						break;

					case 22 : 
						int LA13_251 = input.LA(1);
						 
						int index13_251 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_251);
						if ( s>=0 ) return s;
						break;

					case 23 : 
						int LA13_266 = input.LA(1);
						 
						int index13_266 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_266);
						if ( s>=0 ) return s;
						break;

					case 24 : 
						int LA13_159 = input.LA(1);
						 
						int index13_159 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_159);
						if ( s>=0 ) return s;
						break;

					case 25 : 
						int LA13_163 = input.LA(1);
						 
						int index13_163 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_163);
						if ( s>=0 ) return s;
						break;

					case 26 : 
						int LA13_115 = input.LA(1);
						 
						int index13_115 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_115);
						if ( s>=0 ) return s;
						break;

					case 27 : 
						int LA13_88 = input.LA(1);
						 
						int index13_88 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_88);
						if ( s>=0 ) return s;
						break;

					case 28 : 
						int LA13_180 = input.LA(1);
						 
						int index13_180 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_180);
						if ( s>=0 ) return s;
						break;

					case 29 : 
						int LA13_213 = input.LA(1);
						 
						int index13_213 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_213);
						if ( s>=0 ) return s;
						break;

					case 30 : 
						int LA13_121 = input.LA(1);
						 
						int index13_121 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_121);
						if ( s>=0 ) return s;
						break;

					case 31 : 
						int LA13_193 = input.LA(1);
						 
						int index13_193 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_193);
						if ( s>=0 ) return s;
						break;

					case 32 : 
						int LA13_231 = input.LA(1);
						 
						int index13_231 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_231);
						if ( s>=0 ) return s;
						break;

					case 33 : 
						int LA13_109 = input.LA(1);
						 
						int index13_109 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_109);
						if ( s>=0 ) return s;
						break;

					case 34 : 
						int LA13_84 = input.LA(1);
						 
						int index13_84 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_84);
						if ( s>=0 ) return s;
						break;

					case 35 : 
						int LA13_90 = input.LA(1);
						 
						int index13_90 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_90);
						if ( s>=0 ) return s;
						break;

					case 36 : 
						int LA13_174 = input.LA(1);
						 
						int index13_174 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_174);
						if ( s>=0 ) return s;
						break;

					case 37 : 
						int LA13_86 = input.LA(1);
						 
						int index13_86 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_86);
						if ( s>=0 ) return s;
						break;

					case 38 : 
						int LA13_63 = input.LA(1);
						 
						int index13_63 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_63);
						if ( s>=0 ) return s;
						break;

					case 39 : 
						int LA13_102 = input.LA(1);
						 
						int index13_102 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_102);
						if ( s>=0 ) return s;
						break;

					case 40 : 
						int LA13_94 = input.LA(1);
						 
						int index13_94 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_94);
						if ( s>=0 ) return s;
						break;

					case 41 : 
						int LA13_125 = input.LA(1);
						 
						int index13_125 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_125);
						if ( s>=0 ) return s;
						break;

					case 42 : 
						int LA13_211 = input.LA(1);
						 
						int index13_211 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_211);
						if ( s>=0 ) return s;
						break;

					case 43 : 
						int LA13_219 = input.LA(1);
						 
						int index13_219 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_219);
						if ( s>=0 ) return s;
						break;

					case 44 : 
						int LA13_196 = input.LA(1);
						 
						int index13_196 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_196);
						if ( s>=0 ) return s;
						break;

					case 45 : 
						int LA13_136 = input.LA(1);
						 
						int index13_136 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_136);
						if ( s>=0 ) return s;
						break;

					case 46 : 
						int LA13_189 = input.LA(1);
						 
						int index13_189 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_189);
						if ( s>=0 ) return s;
						break;

					case 47 : 
						int LA13_107 = input.LA(1);
						 
						int index13_107 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_107);
						if ( s>=0 ) return s;
						break;

					case 48 : 
						int LA13_161 = input.LA(1);
						 
						int index13_161 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_161);
						if ( s>=0 ) return s;
						break;

					case 49 : 
						int LA13_176 = input.LA(1);
						 
						int index13_176 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_176);
						if ( s>=0 ) return s;
						break;

					case 50 : 
						int LA13_151 = input.LA(1);
						 
						int index13_151 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_151);
						if ( s>=0 ) return s;
						break;

					case 51 : 
						int LA13_187 = input.LA(1);
						 
						int index13_187 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_187);
						if ( s>=0 ) return s;
						break;

					case 52 : 
						int LA13_215 = input.LA(1);
						 
						int index13_215 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_215);
						if ( s>=0 ) return s;
						break;

					case 53 : 
						int LA13_165 = input.LA(1);
						 
						int index13_165 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_165);
						if ( s>=0 ) return s;
						break;

					case 54 : 
						int LA13_65 = input.LA(1);
						 
						int index13_65 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_65);
						if ( s>=0 ) return s;
						break;

					case 55 : 
						int LA13_67 = input.LA(1);
						 
						int index13_67 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_67);
						if ( s>=0 ) return s;
						break;

					case 56 : 
						int LA13_78 = input.LA(1);
						 
						int index13_78 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_78);
						if ( s>=0 ) return s;
						break;

					case 57 : 
						int LA13_217 = input.LA(1);
						 
						int index13_217 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_217);
						if ( s>=0 ) return s;
						break;

					case 58 : 
						int LA13_100 = input.LA(1);
						 
						int index13_100 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_100);
						if ( s>=0 ) return s;
						break;

					case 59 : 
						int LA13_237 = input.LA(1);
						 
						int index13_237 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_237);
						if ( s>=0 ) return s;
						break;

					case 60 : 
						int LA13_172 = input.LA(1);
						 
						int index13_172 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_172);
						if ( s>=0 ) return s;
						break;

					case 61 : 
						int LA13_140 = input.LA(1);
						 
						int index13_140 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_140);
						if ( s>=0 ) return s;
						break;

					case 62 : 
						int LA13_69 = input.LA(1);
						 
						int index13_69 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_69);
						if ( s>=0 ) return s;
						break;

					case 63 : 
						int LA13_134 = input.LA(1);
						 
						int index13_134 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_134);
						if ( s>=0 ) return s;
						break;

					case 64 : 
						int LA13_80 = input.LA(1);
						 
						int index13_80 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_80);
						if ( s>=0 ) return s;
						break;

					case 65 : 
						int LA13_123 = input.LA(1);
						 
						int index13_123 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_123);
						if ( s>=0 ) return s;
						break;

					case 66 : 
						int LA13_223 = input.LA(1);
						 
						int index13_223 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_223);
						if ( s>=0 ) return s;
						break;

					case 67 : 
						int LA13_127 = input.LA(1);
						 
						int index13_127 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_127);
						if ( s>=0 ) return s;
						break;

					case 68 : 
						int LA13_144 = input.LA(1);
						 
						int index13_144 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_144);
						if ( s>=0 ) return s;
						break;

					case 69 : 
						int LA13_233 = input.LA(1);
						 
						int index13_233 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_233);
						if ( s>=0 ) return s;
						break;

					case 70 : 
						int LA13_72 = input.LA(1);
						 
						int index13_72 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_72);
						if ( s>=0 ) return s;
						break;

					case 71 : 
						int LA13_138 = input.LA(1);
						 
						int index13_138 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_138);
						if ( s>=0 ) return s;
						break;

					case 72 : 
						int LA13_203 = input.LA(1);
						 
						int index13_203 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_203);
						if ( s>=0 ) return s;
						break;

					case 73 : 
						int LA13_130 = input.LA(1);
						 
						int index13_130 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_130);
						if ( s>=0 ) return s;
						break;

					case 74 : 
						int LA13_221 = input.LA(1);
						 
						int index13_221 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_221);
						if ( s>=0 ) return s;
						break;

					case 75 : 
						int LA13_74 = input.LA(1);
						 
						int index13_74 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_74);
						if ( s>=0 ) return s;
						break;

					case 76 : 
						int LA13_76 = input.LA(1);
						 
						int index13_76 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_76);
						if ( s>=0 ) return s;
						break;

					case 77 : 
						int LA13_148 = input.LA(1);
						 
						int index13_148 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_148);
						if ( s>=0 ) return s;
						break;

					case 78 : 
						int LA13_142 = input.LA(1);
						 
						int index13_142 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_142);
						if ( s>=0 ) return s;
						break;

					case 79 : 
						int LA13_249 = input.LA(1);
						 
						int index13_249 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_249);
						if ( s>=0 ) return s;
						break;

					case 80 : 
						int LA13_178 = input.LA(1);
						 
						int index13_178 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_178);
						if ( s>=0 ) return s;
						break;

					case 81 : 
						int LA13_98 = input.LA(1);
						 
						int index13_98 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_98);
						if ( s>=0 ) return s;
						break;

					case 82 : 
						int LA13_185 = input.LA(1);
						 
						int index13_185 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_185);
						if ( s>=0 ) return s;
						break;

					case 83 : 
						int LA13_119 = input.LA(1);
						 
						int index13_119 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_119);
						if ( s>=0 ) return s;
						break;

					case 84 : 
						int LA13_82 = input.LA(1);
						 
						int index13_82 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_82);
						if ( s>=0 ) return s;
						break;

					case 85 : 
						int LA13_253 = input.LA(1);
						 
						int index13_253 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_253);
						if ( s>=0 ) return s;
						break;

					case 86 : 
						int LA13_111 = input.LA(1);
						 
						int index13_111 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_111);
						if ( s>=0 ) return s;
						break;

					case 87 : 
						int LA13_157 = input.LA(1);
						 
						int index13_157 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_157);
						if ( s>=0 ) return s;
						break;

					case 88 : 
						int LA13_241 = input.LA(1);
						 
						int index13_241 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_241);
						if ( s>=0 ) return s;
						break;

					case 89 : 
						int LA13_167 = input.LA(1);
						 
						int index13_167 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_167);
						if ( s>=0 ) return s;
						break;

					case 90 : 
						int LA13_191 = input.LA(1);
						 
						int index13_191 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_191);
						if ( s>=0 ) return s;
						break;

					case 91 : 
						int LA13_96 = input.LA(1);
						 
						int index13_96 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_96);
						if ( s>=0 ) return s;
						break;

					case 92 : 
						int LA13_104 = input.LA(1);
						 
						int index13_104 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_104);
						if ( s>=0 ) return s;
						break;

					case 93 : 
						int LA13_183 = input.LA(1);
						 
						int index13_183 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_183);
						if ( s>=0 ) return s;
						break;

					case 94 : 
						int LA13_146 = input.LA(1);
						 
						int index13_146 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_146);
						if ( s>=0 ) return s;
						break;

					case 95 : 
						int LA13_207 = input.LA(1);
						 
						int index13_207 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_207);
						if ( s>=0 ) return s;
						break;

					case 96 : 
						int LA13_92 = input.LA(1);
						 
						int index13_92 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_92);
						if ( s>=0 ) return s;
						break;

					case 97 : 
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

					case 98 : 
						int LA13_18 = input.LA(1);
						 
						int index13_18 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_18=='m') ) {s = 51;}
						else if ( (LA13_18=='p') ) {s = 52;}
						else if ( ((LA13_18 >= '0' && LA13_18 <= '9')||(LA13_18 >= 'A' && LA13_18 <= 'Z')||(LA13_18 >= 'a' && LA13_18 <= 'l')||(LA13_18 >= 'n' && LA13_18 <= 'o')||(LA13_18 >= 'q' && LA13_18 <= 'z')) ) {s = 31;}
						else if ( (LA13_18=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 32;
						 
						input.seek(index13_18);
						if ( s>=0 ) return s;
						break;

					case 99 : 
						int LA13_37 = input.LA(1);
						 
						int index13_37 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_37 >= '0' && LA13_37 <= '9')||(LA13_37 >= 'A' && LA13_37 <= 'Z')||(LA13_37 >= 'a' && LA13_37 <= 'z')) ) {s = 31;}
						else if ( (LA13_37=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 70;
						 
						input.seek(index13_37);
						if ( s>=0 ) return s;
						break;

					case 100 : 
						int LA13_112 = input.LA(1);
						 
						int index13_112 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( ((runtimeHistoryOpAhead())) ) {s = 152;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_112);
						if ( s>=0 ) return s;
						break;

					case 101 : 
						int LA13_155 = input.LA(1);
						 
						int index13_155 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( ((runtimeHistoryOpAhead())) ) {s = 152;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_155);
						if ( s>=0 ) return s;
						break;

					case 102 : 
						int LA13_181 = input.LA(1);
						 
						int index13_181 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( ((runtimeHistoryOpAhead())) ) {s = 152;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_181);
						if ( s>=0 ) return s;
						break;

					case 103 : 
						int LA13_194 = input.LA(1);
						 
						int index13_194 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( ((runtimeHistoryOpAhead())) ) {s = 152;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_194);
						if ( s>=0 ) return s;
						break;

					case 104 : 
						int LA13_234 = input.LA(1);
						 
						int index13_234 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( ((runtimeHistoryOpAhead())) ) {s = 152;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_234);
						if ( s>=0 ) return s;
						break;

					case 105 : 
						int LA13_216 = input.LA(1);
						 
						int index13_216 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_216 >= '0' && LA13_216 <= '9')||(LA13_216 >= 'A' && LA13_216 <= 'Z')||(LA13_216 >= 'a' && LA13_216 <= 'z')) ) {s = 31;}
						else if ( (LA13_216=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 238;
						 
						input.seek(index13_216);
						if ( s>=0 ) return s;
						break;

					case 106 : 
						int LA13_29 = input.LA(1);
						 
						int index13_29 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_29=='d') ) {s = 57;}
						else if ( ((LA13_29 >= '0' && LA13_29 <= '9')||(LA13_29 >= 'A' && LA13_29 <= 'Z')||(LA13_29 >= 'a' && LA13_29 <= 'c')||(LA13_29 >= 'e' && LA13_29 <= 'z')) ) {s = 31;}
						else if ( (LA13_29=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 58;
						 
						input.seek(index13_29);
						if ( s>=0 ) return s;
						break;

					case 107 : 
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

					case 108 : 
						int LA13_218 = input.LA(1);
						 
						int index13_218 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_218 >= '0' && LA13_218 <= '9')||(LA13_218 >= 'A' && LA13_218 <= 'Z')||(LA13_218 >= 'a' && LA13_218 <= 'z')) ) {s = 31;}
						else if ( (LA13_218=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 239;
						 
						input.seek(index13_218);
						if ( s>=0 ) return s;
						break;

					case 109 : 
						int LA13_47 = input.LA(1);
						 
						int index13_47 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_47==' ') ) {s = 89;}
						else if ( ((LA13_47 >= '0' && LA13_47 <= '9')||(LA13_47 >= 'A' && LA13_47 <= 'Z')||(LA13_47 >= 'a' && LA13_47 <= 'z')) ) {s = 31;}
						else if ( (LA13_47=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 90;
						 
						input.seek(index13_47);
						if ( s>=0 ) return s;
						break;

					case 110 : 
						int LA13_33 = input.LA(1);
						 
						int index13_33 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_33=='y') ) {s = 62;}
						else if ( ((LA13_33 >= '0' && LA13_33 <= '9')||(LA13_33 >= 'A' && LA13_33 <= 'Z')||(LA13_33 >= 'a' && LA13_33 <= 'x')||LA13_33=='z') ) {s = 31;}
						else if ( (LA13_33=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 63;
						 
						input.seek(index13_33);
						if ( s>=0 ) return s;
						break;

					case 111 : 
						int LA13_62 = input.LA(1);
						 
						int index13_62 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_62=='s') ) {s = 108;}
						else if ( ((LA13_62 >= '0' && LA13_62 <= '9')||(LA13_62 >= 'A' && LA13_62 <= 'Z')||(LA13_62 >= 'a' && LA13_62 <= 'r')||(LA13_62 >= 't' && LA13_62 <= 'z')) ) {s = 31;}
						else if ( (LA13_62=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 109;
						 
						input.seek(index13_62);
						if ( s>=0 ) return s;
						break;

					case 112 : 
						int LA13_34 = input.LA(1);
						 
						int index13_34 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_34=='n') ) {s = 64;}
						else if ( ((LA13_34 >= '0' && LA13_34 <= '9')||(LA13_34 >= 'A' && LA13_34 <= 'Z')||(LA13_34 >= 'a' && LA13_34 <= 'm')||(LA13_34 >= 'o' && LA13_34 <= 'z')) ) {s = 31;}
						else if ( (LA13_34=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 65;
						 
						input.seek(index13_34);
						if ( s>=0 ) return s;
						break;

					case 113 : 
						int LA13_64 = input.LA(1);
						 
						int index13_64 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_64=='i') ) {s = 110;}
						else if ( ((LA13_64 >= '0' && LA13_64 <= '9')||(LA13_64 >= 'A' && LA13_64 <= 'Z')||(LA13_64 >= 'a' && LA13_64 <= 'h')||(LA13_64 >= 'j' && LA13_64 <= 'z')) ) {s = 31;}
						else if ( (LA13_64=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 111;
						 
						input.seek(index13_64);
						if ( s>=0 ) return s;
						break;

					case 114 : 
						int LA13_110 = input.LA(1);
						 
						int index13_110 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_110=='e') ) {s = 150;}
						else if ( ((LA13_110 >= '0' && LA13_110 <= '9')||(LA13_110 >= 'A' && LA13_110 <= 'Z')||(LA13_110 >= 'a' && LA13_110 <= 'd')||(LA13_110 >= 'f' && LA13_110 <= 'z')) ) {s = 31;}
						else if ( (LA13_110=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 151;
						 
						input.seek(index13_110);
						if ( s>=0 ) return s;
						break;

					case 115 : 
						int LA13_150 = input.LA(1);
						 
						int index13_150 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_150=='n') ) {s = 184;}
						else if ( ((LA13_150 >= '0' && LA13_150 <= '9')||(LA13_150 >= 'A' && LA13_150 <= 'Z')||(LA13_150 >= 'a' && LA13_150 <= 'm')||(LA13_150 >= 'o' && LA13_150 <= 'z')) ) {s = 31;}
						else if ( (LA13_150=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 185;
						 
						input.seek(index13_150);
						if ( s>=0 ) return s;
						break;

					case 116 : 
						int LA13_184 = input.LA(1);
						 
						int index13_184 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_184=='t') ) {s = 212;}
						else if ( ((LA13_184 >= '0' && LA13_184 <= '9')||(LA13_184 >= 'A' && LA13_184 <= 'Z')||(LA13_184 >= 'a' && LA13_184 <= 's')||(LA13_184 >= 'u' && LA13_184 <= 'z')) ) {s = 31;}
						else if ( (LA13_184=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 213;
						 
						input.seek(index13_184);
						if ( s>=0 ) return s;
						break;

					case 117 : 
						int LA13_36 = input.LA(1);
						 
						int index13_36 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_36=='t') ) {s = 68;}
						else if ( ((LA13_36 >= '0' && LA13_36 <= '9')||(LA13_36 >= 'A' && LA13_36 <= 'Z')||(LA13_36 >= 'a' && LA13_36 <= 's')||(LA13_36 >= 'u' && LA13_36 <= 'z')) ) {s = 31;}
						else if ( (LA13_36=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 69;
						 
						input.seek(index13_36);
						if ( s>=0 ) return s;
						break;

					case 118 : 
						int LA13_49 = input.LA(1);
						 
						int index13_49 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_49=='r') ) {s = 93;}
						else if ( ((LA13_49 >= '0' && LA13_49 <= '9')||(LA13_49 >= 'A' && LA13_49 <= 'Z')||(LA13_49 >= 'a' && LA13_49 <= 'q')||(LA13_49 >= 's' && LA13_49 <= 'z')) ) {s = 31;}
						else if ( (LA13_49=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 94;
						 
						input.seek(index13_49);
						if ( s>=0 ) return s;
						break;

					case 119 : 
						int LA13_93 = input.LA(1);
						 
						int index13_93 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_93=='e') ) {s = 135;}
						else if ( ((LA13_93 >= '0' && LA13_93 <= '9')||(LA13_93 >= 'A' && LA13_93 <= 'Z')||(LA13_93 >= 'a' && LA13_93 <= 'd')||(LA13_93 >= 'f' && LA13_93 <= 'z')) ) {s = 31;}
						else if ( (LA13_93=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 136;
						 
						input.seek(index13_93);
						if ( s>=0 ) return s;
						break;

					case 120 : 
						int LA13_135 = input.LA(1);
						 
						int index13_135 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_135==' ') ) {s = 173;}
						else if ( ((LA13_135 >= '0' && LA13_135 <= '9')||(LA13_135 >= 'A' && LA13_135 <= 'Z')||(LA13_135 >= 'a' && LA13_135 <= 'z')) ) {s = 31;}
						else if ( (LA13_135=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 174;
						 
						input.seek(index13_135);
						if ( s>=0 ) return s;
						break;

					case 121 : 
						int LA13_30 = input.LA(1);
						 
						int index13_30 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_30=='s') ) {s = 59;}
						else if ( ((LA13_30 >= '0' && LA13_30 <= '9')||(LA13_30 >= 'A' && LA13_30 <= 'Z')||(LA13_30 >= 'a' && LA13_30 <= 'r')||(LA13_30 >= 't' && LA13_30 <= 'z')) ) {s = 31;}
						else if ( (LA13_30=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 60;
						 
						input.seek(index13_30);
						if ( s>=0 ) return s;
						break;

					case 122 : 
						int LA13_59 = input.LA(1);
						 
						int index13_59 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_59=='o') ) {s = 106;}
						else if ( ((LA13_59 >= '0' && LA13_59 <= '9')||(LA13_59 >= 'A' && LA13_59 <= 'Z')||(LA13_59 >= 'a' && LA13_59 <= 'n')||(LA13_59 >= 'p' && LA13_59 <= 'z')) ) {s = 31;}
						else if ( (LA13_59=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 107;
						 
						input.seek(index13_59);
						if ( s>=0 ) return s;
						break;

					case 123 : 
						int LA13_106 = input.LA(1);
						 
						int index13_106 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_106==' ') ) {s = 147;}
						else if ( ((LA13_106 >= '0' && LA13_106 <= '9')||(LA13_106 >= 'A' && LA13_106 <= 'Z')||(LA13_106 >= 'a' && LA13_106 <= 'z')) ) {s = 31;}
						else if ( (LA13_106=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 148;
						 
						input.seek(index13_106);
						if ( s>=0 ) return s;
						break;

					case 124 : 
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

					case 125 : 
						int LA13_153 = input.LA(1);
						 
						int index13_153 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_153=='i') ) {s = 186;}
						else if ( ((LA13_153 >= '0' && LA13_153 <= '9')||(LA13_153 >= 'A' && LA13_153 <= 'Z')||(LA13_153 >= 'a' && LA13_153 <= 'h')||(LA13_153 >= 'j' && LA13_153 <= 'z')) ) {s = 31;}
						else if ( (LA13_153=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 187;
						 
						input.seek(index13_153);
						if ( s>=0 ) return s;
						break;

					case 126 : 
						int LA13_186 = input.LA(1);
						 
						int index13_186 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_186=='d') ) {s = 214;}
						else if ( ((LA13_186 >= '0' && LA13_186 <= '9')||(LA13_186 >= 'A' && LA13_186 <= 'Z')||(LA13_186 >= 'a' && LA13_186 <= 'c')||(LA13_186 >= 'e' && LA13_186 <= 'z')) ) {s = 31;}
						else if ( (LA13_186=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 215;
						 
						input.seek(index13_186);
						if ( s>=0 ) return s;
						break;

					case 127 : 
						int LA13_214 = input.LA(1);
						 
						int index13_214 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_214=='e') ) {s = 236;}
						else if ( ((LA13_214 >= '0' && LA13_214 <= '9')||(LA13_214 >= 'A' && LA13_214 <= 'Z')||(LA13_214 >= 'a' && LA13_214 <= 'd')||(LA13_214 >= 'f' && LA13_214 <= 'z')) ) {s = 31;}
						else if ( (LA13_214=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 237;
						 
						input.seek(index13_214);
						if ( s>=0 ) return s;
						break;

					case 128 : 
						int LA13_236 = input.LA(1);
						 
						int index13_236 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_236==' ') ) {s = 254;}
						else if ( ((LA13_236 >= '0' && LA13_236 <= '9')||(LA13_236 >= 'A' && LA13_236 <= 'Z')||(LA13_236 >= 'a' && LA13_236 <= 'z')) ) {s = 31;}
						else if ( (LA13_236=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 255;
						 
						input.seek(index13_236);
						if ( s>=0 ) return s;
						break;

					case 129 : 
						int LA13_40 = input.LA(1);
						 
						int index13_40 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_40=='a') ) {s = 75;}
						else if ( ((LA13_40 >= '0' && LA13_40 <= '9')||(LA13_40 >= 'A' && LA13_40 <= 'Z')||(LA13_40 >= 'b' && LA13_40 <= 'z')) ) {s = 31;}
						else if ( (LA13_40=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 76;
						 
						input.seek(index13_40);
						if ( s>=0 ) return s;
						break;

					case 130 : 
						int LA13_75 = input.LA(1);
						 
						int index13_75 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_75=='r') ) {s = 118;}
						else if ( ((LA13_75 >= '0' && LA13_75 <= '9')||(LA13_75 >= 'A' && LA13_75 <= 'Z')||(LA13_75 >= 'a' && LA13_75 <= 'q')||(LA13_75 >= 's' && LA13_75 <= 'z')) ) {s = 31;}
						else if ( (LA13_75=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 119;
						 
						input.seek(index13_75);
						if ( s>=0 ) return s;
						break;

					case 131 : 
						int LA13_118 = input.LA(1);
						 
						int index13_118 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_118=='i') ) {s = 156;}
						else if ( ((LA13_118 >= '0' && LA13_118 <= '9')||(LA13_118 >= 'A' && LA13_118 <= 'Z')||(LA13_118 >= 'a' && LA13_118 <= 'h')||(LA13_118 >= 'j' && LA13_118 <= 'z')) ) {s = 31;}
						else if ( (LA13_118=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 157;
						 
						input.seek(index13_118);
						if ( s>=0 ) return s;
						break;

					case 132 : 
						int LA13_156 = input.LA(1);
						 
						int index13_156 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_156=='s') ) {s = 188;}
						else if ( ((LA13_156 >= '0' && LA13_156 <= '9')||(LA13_156 >= 'A' && LA13_156 <= 'Z')||(LA13_156 >= 'a' && LA13_156 <= 'r')||(LA13_156 >= 't' && LA13_156 <= 'z')) ) {s = 31;}
						else if ( (LA13_156=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 189;
						 
						input.seek(index13_156);
						if ( s>=0 ) return s;
						break;

					case 133 : 
						int LA13_188 = input.LA(1);
						 
						int index13_188 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_188=='h') ) {s = 216;}
						else if ( ((LA13_188 >= '0' && LA13_188 <= '9')||(LA13_188 >= 'A' && LA13_188 <= 'Z')||(LA13_188 >= 'a' && LA13_188 <= 'g')||(LA13_188 >= 'i' && LA13_188 <= 'z')) ) {s = 31;}
						else if ( (LA13_188=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 217;
						 
						input.seek(index13_188);
						if ( s>=0 ) return s;
						break;

					case 134 : 
						int LA13_41 = input.LA(1);
						 
						int index13_41 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_41=='l') ) {s = 77;}
						else if ( ((LA13_41 >= '0' && LA13_41 <= '9')||(LA13_41 >= 'A' && LA13_41 <= 'Z')||(LA13_41 >= 'a' && LA13_41 <= 'k')||(LA13_41 >= 'm' && LA13_41 <= 'z')) ) {s = 31;}
						else if ( (LA13_41=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 78;
						 
						input.seek(index13_41);
						if ( s>=0 ) return s;
						break;

					case 135 : 
						int LA13_77 = input.LA(1);
						 
						int index13_77 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_77=='l') ) {s = 120;}
						else if ( ((LA13_77 >= '0' && LA13_77 <= '9')||(LA13_77 >= 'A' && LA13_77 <= 'Z')||(LA13_77 >= 'a' && LA13_77 <= 'k')||(LA13_77 >= 'm' && LA13_77 <= 'z')) ) {s = 31;}
						else if ( (LA13_77=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 121;
						 
						input.seek(index13_77);
						if ( s>=0 ) return s;
						break;

					case 136 : 
						int LA13_120 = input.LA(1);
						 
						int index13_120 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_120=='i') ) {s = 158;}
						else if ( ((LA13_120 >= '0' && LA13_120 <= '9')||(LA13_120 >= 'A' && LA13_120 <= 'Z')||(LA13_120 >= 'a' && LA13_120 <= 'h')||(LA13_120 >= 'j' && LA13_120 <= 'z')) ) {s = 31;}
						else if ( (LA13_120=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 159;
						 
						input.seek(index13_120);
						if ( s>=0 ) return s;
						break;

					case 137 : 
						int LA13_158 = input.LA(1);
						 
						int index13_158 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_158=='s') ) {s = 190;}
						else if ( ((LA13_158 >= '0' && LA13_158 <= '9')||(LA13_158 >= 'A' && LA13_158 <= 'Z')||(LA13_158 >= 'a' && LA13_158 <= 'r')||(LA13_158 >= 't' && LA13_158 <= 'z')) ) {s = 31;}
						else if ( (LA13_158=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 191;
						 
						input.seek(index13_158);
						if ( s>=0 ) return s;
						break;

					case 138 : 
						int LA13_190 = input.LA(1);
						 
						int index13_190 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_190=='h') ) {s = 218;}
						else if ( ((LA13_190 >= '0' && LA13_190 <= '9')||(LA13_190 >= 'A' && LA13_190 <= 'Z')||(LA13_190 >= 'a' && LA13_190 <= 'g')||(LA13_190 >= 'i' && LA13_190 <= 'z')) ) {s = 31;}
						else if ( (LA13_190=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 219;
						 
						input.seek(index13_190);
						if ( s>=0 ) return s;
						break;

					case 139 : 
						int LA13_46 = input.LA(1);
						 
						int index13_46 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_46=='e') ) {s = 87;}
						else if ( ((LA13_46 >= '0' && LA13_46 <= '9')||(LA13_46 >= 'A' && LA13_46 <= 'Z')||(LA13_46 >= 'a' && LA13_46 <= 'd')||(LA13_46 >= 'f' && LA13_46 <= 'z')) ) {s = 31;}
						else if ( (LA13_46=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 88;
						 
						input.seek(index13_46);
						if ( s>=0 ) return s;
						break;

					case 140 : 
						int LA13_87 = input.LA(1);
						 
						int index13_87 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_87=='s') ) {s = 129;}
						else if ( ((LA13_87 >= '0' && LA13_87 <= '9')||(LA13_87 >= 'A' && LA13_87 <= 'Z')||(LA13_87 >= 'a' && LA13_87 <= 'r')||(LA13_87 >= 't' && LA13_87 <= 'z')) ) {s = 31;}
						else if ( (LA13_87=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 130;
						 
						input.seek(index13_87);
						if ( s>=0 ) return s;
						break;

					case 141 : 
						int LA13_129 = input.LA(1);
						 
						int index13_129 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_129==' ') ) {s = 166;}
						else if ( ((LA13_129 >= '0' && LA13_129 <= '9')||(LA13_129 >= 'A' && LA13_129 <= 'Z')||(LA13_129 >= 'a' && LA13_129 <= 'z')) ) {s = 31;}
						else if ( (LA13_129=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 167;
						 
						input.seek(index13_129);
						if ( s>=0 ) return s;
						break;

					case 142 : 
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

					case 143 : 
						int LA13_51 = input.LA(1);
						 
						int index13_51 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_51=='o') ) {s = 97;}
						else if ( ((LA13_51 >= '0' && LA13_51 <= '9')||(LA13_51 >= 'A' && LA13_51 <= 'Z')||(LA13_51 >= 'a' && LA13_51 <= 'n')||(LA13_51 >= 'p' && LA13_51 <= 'z')) ) {s = 31;}
						else if ( (LA13_51=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 98;
						 
						input.seek(index13_51);
						if ( s>=0 ) return s;
						break;

					case 144 : 
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

					case 145 : 
						int LA13_97 = input.LA(1);
						 
						int index13_97 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_97=='o') ) {s = 139;}
						else if ( ((LA13_97 >= '0' && LA13_97 <= '9')||(LA13_97 >= 'A' && LA13_97 <= 'Z')||(LA13_97 >= 'a' && LA13_97 <= 'n')||(LA13_97 >= 'p' && LA13_97 <= 'z')) ) {s = 31;}
						else if ( (LA13_97=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 140;
						 
						input.seek(index13_97);
						if ( s>=0 ) return s;
						break;

					case 146 : 
						int LA13_139 = input.LA(1);
						 
						int index13_139 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_139=='t') ) {s = 177;}
						else if ( ((LA13_139 >= '0' && LA13_139 <= '9')||(LA13_139 >= 'A' && LA13_139 <= 'Z')||(LA13_139 >= 'a' && LA13_139 <= 's')||(LA13_139 >= 'u' && LA13_139 <= 'z')) ) {s = 31;}
						else if ( (LA13_139=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 178;
						 
						input.seek(index13_139);
						if ( s>=0 ) return s;
						break;

					case 147 : 
						int LA13_177 = input.LA(1);
						 
						int index13_177 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_177=='h') ) {s = 206;}
						else if ( ((LA13_177 >= '0' && LA13_177 <= '9')||(LA13_177 >= 'A' && LA13_177 <= 'Z')||(LA13_177 >= 'a' && LA13_177 <= 'g')||(LA13_177 >= 'i' && LA13_177 <= 'z')) ) {s = 31;}
						else if ( (LA13_177=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 207;
						 
						input.seek(index13_177);
						if ( s>=0 ) return s;
						break;

					case 148 : 
						int LA13_206 = input.LA(1);
						 
						int index13_206 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_206=='e') ) {s = 230;}
						else if ( ((LA13_206 >= '0' && LA13_206 <= '9')||(LA13_206 >= 'A' && LA13_206 <= 'Z')||(LA13_206 >= 'a' && LA13_206 <= 'd')||(LA13_206 >= 'f' && LA13_206 <= 'z')) ) {s = 31;}
						else if ( (LA13_206=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 231;
						 
						input.seek(index13_206);
						if ( s>=0 ) return s;
						break;

					case 149 : 
						int LA13_230 = input.LA(1);
						 
						int index13_230 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_230=='d') ) {s = 250;}
						else if ( ((LA13_230 >= '0' && LA13_230 <= '9')||(LA13_230 >= 'A' && LA13_230 <= 'Z')||(LA13_230 >= 'a' && LA13_230 <= 'c')||(LA13_230 >= 'e' && LA13_230 <= 'z')) ) {s = 31;}
						else if ( (LA13_230=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 251;
						 
						input.seek(index13_230);
						if ( s>=0 ) return s;
						break;

					case 150 : 
						int LA13_52 = input.LA(1);
						 
						int index13_52 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_52=='a') ) {s = 99;}
						else if ( ((LA13_52 >= '0' && LA13_52 <= '9')||(LA13_52 >= 'A' && LA13_52 <= 'Z')||(LA13_52 >= 'b' && LA13_52 <= 'z')) ) {s = 31;}
						else if ( (LA13_52=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 100;
						 
						input.seek(index13_52);
						if ( s>=0 ) return s;
						break;

					case 151 : 
						int LA13_99 = input.LA(1);
						 
						int index13_99 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_99=='n') ) {s = 141;}
						else if ( ((LA13_99 >= '0' && LA13_99 <= '9')||(LA13_99 >= 'A' && LA13_99 <= 'Z')||(LA13_99 >= 'a' && LA13_99 <= 'm')||(LA13_99 >= 'o' && LA13_99 <= 'z')) ) {s = 31;}
						else if ( (LA13_99=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 142;
						 
						input.seek(index13_99);
						if ( s>=0 ) return s;
						break;

					case 152 : 
						int LA13_141 = input.LA(1);
						 
						int index13_141 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_141=='n') ) {s = 179;}
						else if ( ((LA13_141 >= '0' && LA13_141 <= '9')||(LA13_141 >= 'A' && LA13_141 <= 'Z')||(LA13_141 >= 'a' && LA13_141 <= 'm')||(LA13_141 >= 'o' && LA13_141 <= 'z')) ) {s = 31;}
						else if ( (LA13_141=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 180;
						 
						input.seek(index13_141);
						if ( s>=0 ) return s;
						break;

					case 153 : 
						int LA13_179 = input.LA(1);
						 
						int index13_179 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_179=='i') ) {s = 208;}
						else if ( ((LA13_179 >= '0' && LA13_179 <= '9')||(LA13_179 >= 'A' && LA13_179 <= 'Z')||(LA13_179 >= 'a' && LA13_179 <= 'h')||(LA13_179 >= 'j' && LA13_179 <= 'z')) ) {s = 31;}
						else if ( (LA13_179=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 209;
						 
						input.seek(index13_179);
						if ( s>=0 ) return s;
						break;

					case 154 : 
						int LA13_208 = input.LA(1);
						 
						int index13_208 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_208=='n') ) {s = 232;}
						else if ( ((LA13_208 >= '0' && LA13_208 <= '9')||(LA13_208 >= 'A' && LA13_208 <= 'Z')||(LA13_208 >= 'a' && LA13_208 <= 'm')||(LA13_208 >= 'o' && LA13_208 <= 'z')) ) {s = 31;}
						else if ( (LA13_208=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 233;
						 
						input.seek(index13_208);
						if ( s>=0 ) return s;
						break;

					case 155 : 
						int LA13_232 = input.LA(1);
						 
						int index13_232 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_232=='g') ) {s = 252;}
						else if ( ((LA13_232 >= '0' && LA13_232 <= '9')||(LA13_232 >= 'A' && LA13_232 <= 'Z')||(LA13_232 >= 'a' && LA13_232 <= 'f')||(LA13_232 >= 'h' && LA13_232 <= 'z')) ) {s = 31;}
						else if ( (LA13_232=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 253;
						 
						input.seek(index13_232);
						if ( s>=0 ) return s;
						break;

					case 156 : 
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

					case 157 : 
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

					case 158 : 
						int LA13_116 = input.LA(1);
						 
						int index13_116 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_116 >= '0' && LA13_116 <= '9')||(LA13_116 >= 'A' && LA13_116 <= 'Z')||(LA13_116 >= 'a' && LA13_116 <= 'z')) ) {s = 31;}
						else if ( (LA13_116=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 155;
						 
						input.seek(index13_116);
						if ( s>=0 ) return s;
						break;

					case 159 : 
						int LA13_48 = input.LA(1);
						 
						int index13_48 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_48=='k') ) {s = 91;}
						else if ( ((LA13_48 >= '0' && LA13_48 <= '9')||(LA13_48 >= 'A' && LA13_48 <= 'Z')||(LA13_48 >= 'a' && LA13_48 <= 'j')||(LA13_48 >= 'l' && LA13_48 <= 'z')) ) {s = 31;}
						else if ( (LA13_48=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 92;
						 
						input.seek(index13_48);
						if ( s>=0 ) return s;
						break;

					case 160 : 
						int LA13_91 = input.LA(1);
						 
						int index13_91 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_91=='e') ) {s = 133;}
						else if ( ((LA13_91 >= '0' && LA13_91 <= '9')||(LA13_91 >= 'A' && LA13_91 <= 'Z')||(LA13_91 >= 'a' && LA13_91 <= 'd')||(LA13_91 >= 'f' && LA13_91 <= 'z')) ) {s = 31;}
						else if ( (LA13_91=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 134;
						 
						input.seek(index13_91);
						if ( s>=0 ) return s;
						break;

					case 161 : 
						int LA13_162 = input.LA(1);
						 
						int index13_162 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_162 >= '0' && LA13_162 <= '9')||(LA13_162 >= 'A' && LA13_162 <= 'Z')||(LA13_162 >= 'a' && LA13_162 <= 'z')) ) {s = 31;}
						else if ( (LA13_162=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 194;
						 
						input.seek(index13_162);
						if ( s>=0 ) return s;
						break;

					case 162 : 
						int LA13_133 = input.LA(1);
						 
						int index13_133 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_133=='s') ) {s = 171;}
						else if ( ((LA13_133 >= '0' && LA13_133 <= '9')||(LA13_133 >= 'A' && LA13_133 <= 'Z')||(LA13_133 >= 'a' && LA13_133 <= 'r')||(LA13_133 >= 't' && LA13_133 <= 'z')) ) {s = 31;}
						else if ( (LA13_133=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 172;
						 
						input.seek(index13_133);
						if ( s>=0 ) return s;
						break;

					case 163 : 
						int LA13_143 = input.LA(1);
						 
						int index13_143 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_143 >= '0' && LA13_143 <= '9')||(LA13_143 >= 'A' && LA13_143 <= 'Z')||(LA13_143 >= 'a' && LA13_143 <= 'z')) ) {s = 31;}
						else if ( (LA13_143=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 181;
						 
						input.seek(index13_143);
						if ( s>=0 ) return s;
						break;

					case 164 : 
						int LA13_171 = input.LA(1);
						 
						int index13_171 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_171==' ') ) {s = 202;}
						else if ( ((LA13_171 >= '0' && LA13_171 <= '9')||(LA13_171 >= 'A' && LA13_171 <= 'Z')||(LA13_171 >= 'a' && LA13_171 <= 'z')) ) {s = 31;}
						else if ( (LA13_171=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 203;
						 
						input.seek(index13_171);
						if ( s>=0 ) return s;
						break;

					case 165 : 
						int LA13_39 = input.LA(1);
						 
						int index13_39 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_39=='e') ) {s = 73;}
						else if ( ((LA13_39 >= '0' && LA13_39 <= '9')||(LA13_39 >= 'A' && LA13_39 <= 'Z')||(LA13_39 >= 'a' && LA13_39 <= 'd')||(LA13_39 >= 'f' && LA13_39 <= 'z')) ) {s = 31;}
						else if ( (LA13_39=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 74;
						 
						input.seek(index13_39);
						if ( s>=0 ) return s;
						break;

					case 166 : 
						int LA13_66 = input.LA(1);
						 
						int index13_66 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_66 >= '0' && LA13_66 <= '9')||(LA13_66 >= 'A' && LA13_66 <= 'Z')||(LA13_66 >= 'a' && LA13_66 <= 'z')) ) {s = 31;}
						else if ( (LA13_66=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 112;
						 
						input.seek(index13_66);
						if ( s>=0 ) return s;
						break;

					case 167 : 
						int LA13_73 = input.LA(1);
						 
						int index13_73 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_73=='n') ) {s = 116;}
						else if ( ((LA13_73 >= '0' && LA13_73 <= '9')||(LA13_73 >= 'A' && LA13_73 <= 'Z')||(LA13_73 >= 'a' && LA13_73 <= 'm')||(LA13_73 >= 'o' && LA13_73 <= 'z')) ) {s = 31;}
						else if ( (LA13_73=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 117;
						 
						input.seek(index13_73);
						if ( s>=0 ) return s;
						break;

					case 168 : 
						int LA13_43 = input.LA(1);
						 
						int index13_43 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_43=='o') ) {s = 81;}
						else if ( ((LA13_43 >= '0' && LA13_43 <= '9')||(LA13_43 >= 'A' && LA13_43 <= 'Z')||(LA13_43 >= 'a' && LA13_43 <= 'n')||(LA13_43 >= 'p' && LA13_43 <= 'z')) ) {s = 31;}
						else if ( (LA13_43=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 82;
						 
						input.seek(index13_43);
						if ( s>=0 ) return s;
						break;

					case 169 : 
						int LA13_81 = input.LA(1);
						 
						int index13_81 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_81=='s') ) {s = 124;}
						else if ( ((LA13_81 >= '0' && LA13_81 <= '9')||(LA13_81 >= 'A' && LA13_81 <= 'Z')||(LA13_81 >= 'a' && LA13_81 <= 'r')||(LA13_81 >= 't' && LA13_81 <= 'z')) ) {s = 31;}
						else if ( (LA13_81=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 125;
						 
						input.seek(index13_81);
						if ( s>=0 ) return s;
						break;

					case 170 : 
						int LA13_124 = input.LA(1);
						 
						int index13_124 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_124=='e') ) {s = 162;}
						else if ( ((LA13_124 >= '0' && LA13_124 <= '9')||(LA13_124 >= 'A' && LA13_124 <= 'Z')||(LA13_124 >= 'a' && LA13_124 <= 'd')||(LA13_124 >= 'f' && LA13_124 <= 'z')) ) {s = 31;}
						else if ( (LA13_124=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 163;
						 
						input.seek(index13_124);
						if ( s>=0 ) return s;
						break;

					case 171 : 
						int LA13_210 = input.LA(1);
						 
						int index13_210 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_210 >= '0' && LA13_210 <= '9')||(LA13_210 >= 'A' && LA13_210 <= 'Z')||(LA13_210 >= 'a' && LA13_210 <= 'z')) ) {s = 31;}
						else if ( (LA13_210=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 234;
						 
						input.seek(index13_210);
						if ( s>=0 ) return s;
						break;

					case 172 : 
						int LA13_53 = input.LA(1);
						 
						int index13_53 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_53=='g') ) {s = 101;}
						else if ( ((LA13_53 >= '0' && LA13_53 <= '9')||(LA13_53 >= 'A' && LA13_53 <= 'Z')||(LA13_53 >= 'a' && LA13_53 <= 'f')||(LA13_53 >= 'h' && LA13_53 <= 'z')) ) {s = 31;}
						else if ( (LA13_53=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 102;
						 
						input.seek(index13_53);
						if ( s>=0 ) return s;
						break;

					case 173 : 
						int LA13_101 = input.LA(1);
						 
						int index13_101 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_101=='h') ) {s = 143;}
						else if ( ((LA13_101 >= '0' && LA13_101 <= '9')||(LA13_101 >= 'A' && LA13_101 <= 'Z')||(LA13_101 >= 'a' && LA13_101 <= 'g')||(LA13_101 >= 'i' && LA13_101 <= 'z')) ) {s = 31;}
						else if ( (LA13_101=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 144;
						 
						input.seek(index13_101);
						if ( s>=0 ) return s;
						break;

					case 174 : 
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

					case 175 : 
						int LA13_35 = input.LA(1);
						 
						int index13_35 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_35=='w') ) {s = 66;}
						else if ( ((LA13_35 >= '0' && LA13_35 <= '9')||(LA13_35 >= 'A' && LA13_35 <= 'Z')||(LA13_35 >= 'a' && LA13_35 <= 'v')||(LA13_35 >= 'x' && LA13_35 <= 'z')) ) {s = 31;}
						else if ( (LA13_35=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 67;
						 
						input.seek(index13_35);
						if ( s>=0 ) return s;
						break;

					case 176 : 
						int LA13_54 = input.LA(1);
						 
						int index13_54 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_54=='l') ) {s = 103;}
						else if ( ((LA13_54 >= '0' && LA13_54 <= '9')||(LA13_54 >= 'A' && LA13_54 <= 'Z')||(LA13_54 >= 'a' && LA13_54 <= 'k')||(LA13_54 >= 'm' && LA13_54 <= 'z')) ) {s = 31;}
						else if ( (LA13_54=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 104;
						 
						input.seek(index13_54);
						if ( s>=0 ) return s;
						break;

					case 177 : 
						int LA13_103 = input.LA(1);
						 
						int index13_103 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_103=='u') ) {s = 145;}
						else if ( ((LA13_103 >= '0' && LA13_103 <= '9')||(LA13_103 >= 'A' && LA13_103 <= 'Z')||(LA13_103 >= 'a' && LA13_103 <= 't')||(LA13_103 >= 'v' && LA13_103 <= 'z')) ) {s = 31;}
						else if ( (LA13_103=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 146;
						 
						input.seek(index13_103);
						if ( s>=0 ) return s;
						break;

					case 178 : 
						int LA13_145 = input.LA(1);
						 
						int index13_145 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_145=='m') ) {s = 182;}
						else if ( ((LA13_145 >= '0' && LA13_145 <= '9')||(LA13_145 >= 'A' && LA13_145 <= 'Z')||(LA13_145 >= 'a' && LA13_145 <= 'l')||(LA13_145 >= 'n' && LA13_145 <= 'z')) ) {s = 31;}
						else if ( (LA13_145=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 183;
						 
						input.seek(index13_145);
						if ( s>=0 ) return s;
						break;

					case 179 : 
						int LA13_182 = input.LA(1);
						 
						int index13_182 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_182=='e') ) {s = 210;}
						else if ( ((LA13_182 >= '0' && LA13_182 <= '9')||(LA13_182 >= 'A' && LA13_182 <= 'Z')||(LA13_182 >= 'a' && LA13_182 <= 'd')||(LA13_182 >= 'f' && LA13_182 <= 'z')) ) {s = 31;}
						else if ( (LA13_182=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 211;
						 
						input.seek(index13_182);
						if ( s>=0 ) return s;
						break;

					case 180 : 
						int LA13_38 = input.LA(1);
						 
						int index13_38 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_38=='e') ) {s = 71;}
						else if ( ((LA13_38 >= '0' && LA13_38 <= '9')||(LA13_38 >= 'A' && LA13_38 <= 'Z')||(LA13_38 >= 'a' && LA13_38 <= 'd')||(LA13_38 >= 'f' && LA13_38 <= 'z')) ) {s = 31;}
						else if ( (LA13_38=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 72;
						 
						input.seek(index13_38);
						if ( s>=0 ) return s;
						break;

					case 181 : 
						int LA13_71 = input.LA(1);
						 
						int index13_71 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_71=='r') ) {s = 114;}
						else if ( ((LA13_71 >= '0' && LA13_71 <= '9')||(LA13_71 >= 'A' && LA13_71 <= 'Z')||(LA13_71 >= 'a' && LA13_71 <= 'q')||(LA13_71 >= 's' && LA13_71 <= 'z')) ) {s = 31;}
						else if ( (LA13_71=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 115;
						 
						input.seek(index13_71);
						if ( s>=0 ) return s;
						break;

					case 182 : 
						int LA13_85 = input.LA(1);
						 
						int index13_85 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_85 >= '0' && LA13_85 <= '9')||(LA13_85 >= 'A' && LA13_85 <= 'Z')||(LA13_85 >= 'a' && LA13_85 <= 'z')) ) {s = 31;}
						else if ( (LA13_85=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 128;
						 
						input.seek(index13_85);
						if ( s>=0 ) return s;
						break;

					case 183 : 
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

					case 184 : 
						int LA13_45 = input.LA(1);
						 
						int index13_45 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_45=='r') ) {s = 85;}
						else if ( ((LA13_45 >= '0' && LA13_45 <= '9')||(LA13_45 >= 'A' && LA13_45 <= 'Z')||(LA13_45 >= 'a' && LA13_45 <= 'q')||(LA13_45 >= 's' && LA13_45 <= 'z')) ) {s = 31;}
						else if ( (LA13_45=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 86;
						 
						input.seek(index13_45);
						if ( s>=0 ) return s;
						break;

					case 185 : 
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

					case 186 : 
						int LA13_250 = input.LA(1);
						 
						int index13_250 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_250 >= '0' && LA13_250 <= '9')||(LA13_250 >= 'A' && LA13_250 <= 'Z')||(LA13_250 >= 'a' && LA13_250 <= 'z')) ) {s = 31;}
						else if ( (LA13_250=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 267;
						 
						input.seek(index13_250);
						if ( s>=0 ) return s;
						break;

					case 187 : 
						int LA13_252 = input.LA(1);
						 
						int index13_252 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_252 >= '0' && LA13_252 <= '9')||(LA13_252 >= 'A' && LA13_252 <= 'Z')||(LA13_252 >= 'a' && LA13_252 <= 'z')) ) {s = 31;}
						else if ( (LA13_252=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 268;
						 
						input.seek(index13_252);
						if ( s>=0 ) return s;
						break;

					case 188 : 
						int LA13_42 = input.LA(1);
						 
						int index13_42 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_42=='o') ) {s = 79;}
						else if ( ((LA13_42 >= '0' && LA13_42 <= '9')||(LA13_42 >= 'A' && LA13_42 <= 'Z')||(LA13_42 >= 'a' && LA13_42 <= 'n')||(LA13_42 >= 'p' && LA13_42 <= 'z')) ) {s = 31;}
						else if ( (LA13_42=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 80;
						 
						input.seek(index13_42);
						if ( s>=0 ) return s;
						break;

					case 189 : 
						int LA13_50 = input.LA(1);
						 
						int index13_50 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_50=='v') ) {s = 95;}
						else if ( ((LA13_50 >= '0' && LA13_50 <= '9')||(LA13_50 >= 'A' && LA13_50 <= 'Z')||(LA13_50 >= 'a' && LA13_50 <= 'u')||(LA13_50 >= 'w' && LA13_50 <= 'z')) ) {s = 31;}
						else if ( (LA13_50=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 96;
						 
						input.seek(index13_50);
						if ( s>=0 ) return s;
						break;

					case 190 : 
						int LA13_79 = input.LA(1);
						 
						int index13_79 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_79=='s') ) {s = 122;}
						else if ( ((LA13_79 >= '0' && LA13_79 <= '9')||(LA13_79 >= 'A' && LA13_79 <= 'Z')||(LA13_79 >= 'a' && LA13_79 <= 'r')||(LA13_79 >= 't' && LA13_79 <= 'z')) ) {s = 31;}
						else if ( (LA13_79=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 123;
						 
						input.seek(index13_79);
						if ( s>=0 ) return s;
						break;

					case 191 : 
						int LA13_95 = input.LA(1);
						 
						int index13_95 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_95=='e') ) {s = 137;}
						else if ( ((LA13_95 >= '0' && LA13_95 <= '9')||(LA13_95 >= 'A' && LA13_95 <= 'Z')||(LA13_95 >= 'a' && LA13_95 <= 'd')||(LA13_95 >= 'f' && LA13_95 <= 'z')) ) {s = 31;}
						else if ( (LA13_95=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 138;
						 
						input.seek(index13_95);
						if ( s>=0 ) return s;
						break;

					case 192 : 
						int LA13_137 = input.LA(1);
						 
						int index13_137 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_137=='r') ) {s = 175;}
						else if ( ((LA13_137 >= '0' && LA13_137 <= '9')||(LA13_137 >= 'A' && LA13_137 <= 'Z')||(LA13_137 >= 'a' && LA13_137 <= 'q')||(LA13_137 >= 's' && LA13_137 <= 'z')) ) {s = 31;}
						else if ( (LA13_137=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 176;
						 
						input.seek(index13_137);
						if ( s>=0 ) return s;
						break;

					case 193 : 
						int LA13_122 = input.LA(1);
						 
						int index13_122 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_122=='s') ) {s = 160;}
						else if ( ((LA13_122 >= '0' && LA13_122 <= '9')||(LA13_122 >= 'A' && LA13_122 <= 'Z')||(LA13_122 >= 'a' && LA13_122 <= 'r')||(LA13_122 >= 't' && LA13_122 <= 'z')) ) {s = 31;}
						else if ( (LA13_122=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 161;
						 
						input.seek(index13_122);
						if ( s>=0 ) return s;
						break;

					case 194 : 
						int LA13_175 = input.LA(1);
						 
						int index13_175 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_175=='s') ) {s = 204;}
						else if ( ((LA13_175 >= '0' && LA13_175 <= '9')||(LA13_175 >= 'A' && LA13_175 <= 'Z')||(LA13_175 >= 'a' && LA13_175 <= 'r')||(LA13_175 >= 't' && LA13_175 <= 'z')) ) {s = 31;}
						else if ( (LA13_175=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 205;
						 
						input.seek(index13_175);
						if ( s>=0 ) return s;
						break;

					case 195 : 
						int LA13_19 = input.LA(1);
						 
						int index13_19 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_19=='i') ) {s = 53;}
						else if ( ((LA13_19 >= '0' && LA13_19 <= '9')||(LA13_19 >= 'A' && LA13_19 <= 'Z')||(LA13_19 >= 'a' && LA13_19 <= 'h')||(LA13_19 >= 'j' && LA13_19 <= 'z')) ) {s = 31;}
						else if ( (LA13_19=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 32;
						 
						input.seek(index13_19);
						if ( s>=0 ) return s;
						break;

					case 196 : 
						int LA13_204 = input.LA(1);
						 
						int index13_204 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_204=='e') ) {s = 228;}
						else if ( ((LA13_204 >= '0' && LA13_204 <= '9')||(LA13_204 >= 'A' && LA13_204 <= 'Z')||(LA13_204 >= 'a' && LA13_204 <= 'd')||(LA13_204 >= 'f' && LA13_204 <= 'z')) ) {s = 31;}
						else if ( (LA13_204=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 229;
						 
						input.seek(index13_204);
						if ( s>=0 ) return s;
						break;

					case 197 : 
						int LA13_160 = input.LA(1);
						 
						int index13_160 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_160=='e') ) {s = 192;}
						else if ( ((LA13_160 >= '0' && LA13_160 <= '9')||(LA13_160 >= 'A' && LA13_160 <= 'Z')||(LA13_160 >= 'a' && LA13_160 <= 'd')||(LA13_160 >= 'f' && LA13_160 <= 'z')) ) {s = 31;}
						else if ( (LA13_160=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 193;
						 
						input.seek(index13_160);
						if ( s>=0 ) return s;
						break;

					case 198 : 
						int LA13_228 = input.LA(1);
						 
						int index13_228 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_228=='s') ) {s = 248;}
						else if ( ((LA13_228 >= '0' && LA13_228 <= '9')||(LA13_228 >= 'A' && LA13_228 <= 'Z')||(LA13_228 >= 'a' && LA13_228 <= 'r')||(LA13_228 >= 't' && LA13_228 <= 'z')) ) {s = 31;}
						else if ( (LA13_228=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 249;
						 
						input.seek(index13_228);
						if ( s>=0 ) return s;
						break;

					case 199 : 
						int LA13_44 = input.LA(1);
						 
						int index13_44 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_44=='u') ) {s = 83;}
						else if ( ((LA13_44 >= '0' && LA13_44 <= '9')||(LA13_44 >= 'A' && LA13_44 <= 'Z')||(LA13_44 >= 'a' && LA13_44 <= 't')||(LA13_44 >= 'v' && LA13_44 <= 'z')) ) {s = 31;}
						else if ( (LA13_44=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 84;
						 
						input.seek(index13_44);
						if ( s>=0 ) return s;
						break;

					case 200 : 
						int LA13_248 = input.LA(1);
						 
						int index13_248 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_248==' ') ) {s = 265;}
						else if ( ((LA13_248 >= '0' && LA13_248 <= '9')||(LA13_248 >= 'A' && LA13_248 <= 'Z')||(LA13_248 >= 'a' && LA13_248 <= 'z')) ) {s = 31;}
						else if ( (LA13_248=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 266;
						 
						input.seek(index13_248);
						if ( s>=0 ) return s;
						break;

					case 201 : 
						int LA13_83 = input.LA(1);
						 
						int index13_83 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_83=='a') ) {s = 126;}
						else if ( ((LA13_83 >= '0' && LA13_83 <= '9')||(LA13_83 >= 'A' && LA13_83 <= 'Z')||(LA13_83 >= 'b' && LA13_83 <= 'z')) ) {s = 31;}
						else if ( (LA13_83=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 127;
						 
						input.seek(index13_83);
						if ( s>=0 ) return s;
						break;

					case 202 : 
						int LA13_192 = input.LA(1);
						 
						int index13_192 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_192=='s') ) {s = 220;}
						else if ( ((LA13_192 >= '0' && LA13_192 <= '9')||(LA13_192 >= 'A' && LA13_192 <= 'Z')||(LA13_192 >= 'a' && LA13_192 <= 'r')||(LA13_192 >= 't' && LA13_192 <= 'z')) ) {s = 31;}
						else if ( (LA13_192=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 221;
						 
						input.seek(index13_192);
						if ( s>=0 ) return s;
						break;

					case 203 : 
						int LA13_23 = input.LA(1);
						 
						int index13_23 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_23=='o') ) {s = 54;}
						else if ( ((LA13_23 >= '0' && LA13_23 <= '9')||(LA13_23 >= 'A' && LA13_23 <= 'Z')||(LA13_23 >= 'a' && LA13_23 <= 'n')||(LA13_23 >= 'p' && LA13_23 <= 'z')) ) {s = 31;}
						else if ( (LA13_23=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 32;
						 
						input.seek(index13_23);
						if ( s>=0 ) return s;
						break;

					case 204 : 
						int LA13_126 = input.LA(1);
						 
						int index13_126 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_126=='l') ) {s = 164;}
						else if ( ((LA13_126 >= '0' && LA13_126 <= '9')||(LA13_126 >= 'A' && LA13_126 <= 'Z')||(LA13_126 >= 'a' && LA13_126 <= 'k')||(LA13_126 >= 'm' && LA13_126 <= 'z')) ) {s = 31;}
						else if ( (LA13_126=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 165;
						 
						input.seek(index13_126);
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
