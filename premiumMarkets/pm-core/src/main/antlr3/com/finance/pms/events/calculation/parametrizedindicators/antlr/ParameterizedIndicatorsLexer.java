// $ANTLR 3.5.2 com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g 2018-08-30 22:18:51
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:58:7: ( ',' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:58:9: ','
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
	// $ANTLR end "T__50"

	// $ANTLR start "T__51"
	public final void mT__51() throws RecognitionException {
		try {
			int _type = T__51;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:59:7: ( 'NaN' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:59:9: 'NaN'
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
	// $ANTLR end "T__51"

	// $ANTLR start "T__52"
	public final void mT__52() throws RecognitionException {
		try {
			int _type = T__52;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:60:7: ( '[' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:60:9: '['
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
	// $ANTLR end "T__52"

	// $ANTLR start "T__53"
	public final void mT__53() throws RecognitionException {
		try {
			int _type = T__53;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:61:7: ( ']' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:61:9: ']'
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
	// $ANTLR end "T__53"

	// $ANTLR start "T__54"
	public final void mT__54() throws RecognitionException {
		try {
			int _type = T__54;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:62:7: ( 'also display' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:62:9: 'also display'
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
	// $ANTLR end "T__54"

	// $ANTLR start "T__55"
	public final void mT__55() throws RecognitionException {
		try {
			int _type = T__55;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:63:7: ( 'bearish' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:63:9: 'bearish'
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
	// $ANTLR end "T__55"

	// $ANTLR start "T__56"
	public final void mT__56() throws RecognitionException {
		try {
			int _type = T__56;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:64:7: ( 'bullish' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:64:9: 'bullish'
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
	// $ANTLR end "T__56"

	// $ANTLR start "T__57"
	public final void mT__57() throws RecognitionException {
		try {
			int _type = T__57;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:65:7: ( 'crosses down historical' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:65:9: 'crosses down historical'
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
	// $ANTLR end "T__57"

	// $ANTLR start "T__58"
	public final void mT__58() throws RecognitionException {
		try {
			int _type = T__58;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:66:7: ( 'crosses down threshold' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:66:9: 'crosses down threshold'
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
	// $ANTLR end "T__58"

	// $ANTLR start "T__59"
	public final void mT__59() throws RecognitionException {
		try {
			int _type = T__59;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:67:7: ( 'crosses up historical' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:67:9: 'crosses up historical'
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
	// $ANTLR end "T__59"

	// $ANTLR start "T__60"
	public final void mT__60() throws RecognitionException {
		try {
			int _type = T__60;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:68:7: ( 'crosses up threshold' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:68:9: 'crosses up threshold'
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
	// $ANTLR end "T__60"

	// $ANTLR start "T__61"
	public final void mT__61() throws RecognitionException {
		try {
			int _type = T__61;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:69:7: ( 'ending within' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:69:9: 'ending within'
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
	// $ANTLR end "T__61"

	// $ANTLR start "T__62"
	public final void mT__62() throws RecognitionException {
		try {
			int _type = T__62;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:70:7: ( 'equals historical' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:70:9: 'equals historical'
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
	// $ANTLR end "T__62"

	// $ANTLR start "T__63"
	public final void mT__63() throws RecognitionException {
		try {
			int _type = T__63;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:71:7: ( 'equals threshold' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:71:9: 'equals threshold'
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
	// $ANTLR end "T__63"

	// $ANTLR start "T__64"
	public final void mT__64() throws RecognitionException {
		try {
			int _type = T__64;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:72:7: ( 'equals trend' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:72:9: 'equals trend'
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
	// $ANTLR end "T__64"

	// $ANTLR start "T__65"
	public final void mT__65() throws RecognitionException {
		try {
			int _type = T__65;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:73:7: ( 'for' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:73:9: 'for'
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
	// $ANTLR end "T__65"

	// $ANTLR start "T__66"
	public final void mT__66() throws RecognitionException {
		try {
			int _type = T__66;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:74:7: ( 'goes down more than' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:74:9: 'goes down more than'
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
	// $ANTLR end "T__66"

	// $ANTLR start "T__67"
	public final void mT__67() throws RecognitionException {
		try {
			int _type = T__67;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:75:7: ( 'goes up more than' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:75:9: 'goes up more than'
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
	// $ANTLR end "T__67"

	// $ANTLR start "T__68"
	public final void mT__68() throws RecognitionException {
		try {
			int _type = T__68;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:76:7: ( 'is above historical' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:76:9: 'is above historical'
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
	// $ANTLR end "T__68"

	// $ANTLR start "T__69"
	public final void mT__69() throws RecognitionException {
		try {
			int _type = T__69;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:77:7: ( 'is above threshold' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:77:9: 'is above threshold'
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
	// $ANTLR end "T__69"

	// $ANTLR start "T__70"
	public final void mT__70() throws RecognitionException {
		try {
			int _type = T__70;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:78:7: ( 'is bearish if not bullish' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:78:9: 'is bearish if not bullish'
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
	// $ANTLR end "T__70"

	// $ANTLR start "T__71"
	public final void mT__71() throws RecognitionException {
		try {
			int _type = T__71;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:79:7: ( 'is bearish when' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:79:9: 'is bearish when'
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
	// $ANTLR end "T__71"

	// $ANTLR start "T__72"
	public final void mT__72() throws RecognitionException {
		try {
			int _type = T__72;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:80:7: ( 'is below historical' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:80:9: 'is below historical'
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
	// $ANTLR end "T__72"

	// $ANTLR start "T__73"
	public final void mT__73() throws RecognitionException {
		try {
			int _type = T__73;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:81:7: ( 'is below threshold' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:81:9: 'is below threshold'
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
	// $ANTLR end "T__73"

	// $ANTLR start "T__74"
	public final void mT__74() throws RecognitionException {
		try {
			int _type = T__74;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:82:7: ( 'is bullish when' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:82:9: 'is bullish when'
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
	// $ANTLR end "T__74"

	// $ANTLR start "T__75"
	public final void mT__75() throws RecognitionException {
		try {
			int _type = T__75;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:83:7: ( 'makes a higher high over' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:83:9: 'makes a higher high over'
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
	// $ANTLR end "T__75"

	// $ANTLR start "T__76"
	public final void mT__76() throws RecognitionException {
		try {
			int _type = T__76;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:84:7: ( 'makes a higher low over' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:84:9: 'makes a higher low over'
			{
			match("makes a higher low over"); 

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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:85:7: ( 'makes a lower high over' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:85:9: 'makes a lower high over'
			{
			match("makes a lower high over"); 

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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:86:7: ( 'makes a lower low over' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:86:9: 'makes a lower low over'
			{
			match("makes a lower low over"); 

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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:87:7: ( 'more than' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:87:9: 'more than'
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
	// $ANTLR end "T__79"

	// $ANTLR start "T__80"
	public final void mT__80() throws RecognitionException {
		try {
			int _type = T__80;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:88:7: ( 'over' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:88:9: 'over'
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
	// $ANTLR end "T__80"

	// $ANTLR start "T__81"
	public final void mT__81() throws RecognitionException {
		try {
			int _type = T__81;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:89:7: ( 'override start shift with' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:89:9: 'override start shift with'
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
	// $ANTLR end "T__81"

	// $ANTLR start "T__82"
	public final void mT__82() throws RecognitionException {
		try {
			int _type = T__82;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:90:7: ( 'reverses down' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:90:9: 'reverses down'
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
	// $ANTLR end "T__82"

	// $ANTLR start "T__83"
	public final void mT__83() throws RecognitionException {
		try {
			int _type = T__83;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:91:7: ( 'reverses up' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:91:9: 'reverses up'
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
	// $ANTLR end "T__83"

	// $ANTLR start "T__84"
	public final void mT__84() throws RecognitionException {
		try {
			int _type = T__84;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:92:7: ( 'smoothed' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:92:9: 'smoothed'
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
	// $ANTLR end "T__84"

	// $ANTLR start "T__85"
	public final void mT__85() throws RecognitionException {
		try {
			int _type = T__85;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:93:7: ( 'spanning' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:93:9: 'spanning'
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
	// $ANTLR end "T__85"

	// $ANTLR start "T__86"
	public final void mT__86() throws RecognitionException {
		try {
			int _type = T__86;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:94:7: ( 'starting within' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:94:9: 'starting within'
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
	// $ANTLR end "T__86"

	// $ANTLR start "Operation"
	public final void mOperation() throws RecognitionException {
		try {
			int _type = Operation;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:283:7: ({...}? => ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )+ )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:283:9: {...}? => ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )+
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:283:60: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )+
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:287:7: ( ( ( '-' )? ( '0' .. '9' )+ ( '.' ( '0' .. '9' )+ )? ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:287:9: ( ( '-' )? ( '0' .. '9' )+ ( '.' ( '0' .. '9' )+ )? )
			{
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:287:9: ( ( '-' )? ( '0' .. '9' )+ ( '.' ( '0' .. '9' )+ )? )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:287:11: ( '-' )? ( '0' .. '9' )+ ( '.' ( '0' .. '9' )+ )?
			{
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:287:11: ( '-' )?
			int alt2=2;
			int LA2_0 = input.LA(1);
			if ( (LA2_0=='-') ) {
				alt2=1;
			}
			switch (alt2) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:287:12: '-'
					{
					match('-'); 
					}
					break;

			}

			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:287:18: ( '0' .. '9' )+
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

			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:287:30: ( '.' ( '0' .. '9' )+ )?
			int alt5=2;
			int LA5_0 = input.LA(1);
			if ( (LA5_0=='.') ) {
				alt5=1;
			}
			switch (alt5) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:287:31: '.' ( '0' .. '9' )+
					{
					match('.'); 
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:287:35: ( '0' .. '9' )+
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:291:6: ( '\"' ( 'a' .. 'z' | 'A' .. 'Z' | '.' | '_' )* '\"' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:291:8: '\"' ( 'a' .. 'z' | 'A' .. 'Z' | '.' | '_' )* '\"'
			{
			match('\"'); 
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:291:12: ( 'a' .. 'z' | 'A' .. 'Z' | '.' | '_' )*
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:295:4: ({...}? => ( 'open' | 'close' | 'high' | 'low' | 'volume' ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:295:6: {...}? => ( 'open' | 'close' | 'high' | 'low' | 'volume' )
			{
			if ( !((runtimeHistoryOpAhead())) ) {
				throw new FailedPredicateException(input, "HistoricalData", "runtimeHistoryOpAhead()");
			}
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:295:36: ( 'open' | 'close' | 'high' | 'low' | 'volume' )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:295:38: 'open'
					{
					match("open"); 

					}
					break;
				case 2 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:295:47: 'close'
					{
					match("close"); 

					}
					break;
				case 3 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:295:57: 'high'
					{
					match("high"); 

					}
					break;
				case 4 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:295:66: 'low'
					{
					match("low"); 

					}
					break;
				case 5 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:295:75: 'volume'
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:299:7: ( ( ( ' ' )+ ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:299:9: ( ( ' ' )+ )
			{
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:299:9: ( ( ' ' )+ )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:299:10: ( ' ' )+
			{
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:299:10: ( ' ' )+
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:299:10: ' '
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:303:6: ( ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )+ )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:303:8: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )+
			{
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:303:8: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )+
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:308:5: ( ( '\\r' | '\\t' | '\\u000C' | '\\n' ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:308:7: ( '\\r' | '\\t' | '\\u000C' | '\\n' )
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:311:5: ( '/*' ( . )* '*/' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:311:7: '/*' ( . )* '*/'
			{
			match("/*"); 

			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:311:12: ( . )*
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:311:12: .
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:314:5: ( '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:314:7: '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n'
			{
			match("//"); 

			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:314:12: (~ ( '\\n' | '\\r' ) )*
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

			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:314:26: ( '\\r' )?
			int alt12=2;
			int LA12_0 = input.LA(1);
			if ( (LA12_0=='\r') ) {
				alt12=1;
			}
			switch (alt12) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:314:26: '\\r'
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
		// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:8: ( AND | CLOSEPARENTEHSIS | COMMA | DAYS | LENIENT | NOT | OPENPARENTEHSIS | OR | PERCENT | T__50 | T__51 | T__52 | T__53 | T__54 | T__55 | T__56 | T__57 | T__58 | T__59 | T__60 | T__61 | T__62 | T__63 | T__64 | T__65 | T__66 | T__67 | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | T__79 | T__80 | T__81 | T__82 | T__83 | T__84 | T__85 | T__86 | Operation | NumberToken | StringToken | HistoricalData | WhiteChar | Tcheat | WS | COMMENT | LINE_COMMENT )
		int alt13=55;
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
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:267: T__81
				{
				mT__81(); 

				}
				break;
			case 42 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:273: T__82
				{
				mT__82(); 

				}
				break;
			case 43 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:279: T__83
				{
				mT__83(); 

				}
				break;
			case 44 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:285: T__84
				{
				mT__84(); 

				}
				break;
			case 45 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:291: T__85
				{
				mT__85(); 

				}
				break;
			case 46 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:297: T__86
				{
				mT__86(); 

				}
				break;
			case 47 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:303: Operation
				{
				mOperation(); 

				}
				break;
			case 48 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:313: NumberToken
				{
				mNumberToken(); 

				}
				break;
			case 49 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:325: StringToken
				{
				mStringToken(); 

				}
				break;
			case 50 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:337: HistoricalData
				{
				mHistoricalData(); 

				}
				break;
			case 51 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:352: WhiteChar
				{
				mWhiteChar(); 

				}
				break;
			case 52 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:362: Tcheat
				{
				mTcheat(); 

				}
				break;
			case 53 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:369: WS
				{
				mWS(); 

				}
				break;
			case 54 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:372: COMMENT
				{
				mCOMMENT(); 

				}
				break;
			case 55 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:380: LINE_COMMENT
				{
				mLINE_COMMENT(); 

				}
				break;

		}
	}


	protected DFA13 dfa13 = new DFA13(this);
	static final String DFA13_eotS =
		"\1\uffff\1\44\2\uffff\3\44\1\uffff\1\44\2\uffff\1\44\2\uffff\12\44\1\uffff"+
		"\1\30\1\uffff\2\44\4\uffff\1\101\1\103\1\104\1\uffff\1\106\1\110\1\112"+
		"\1\114\1\115\1\117\1\121\1\123\1\125\1\127\1\131\1\133\1\135\1\137\1\141"+
		"\1\143\1\145\1\147\1\151\1\153\1\155\1\157\1\161\1\163\1\165\2\uffff\1"+
		"\166\1\uffff\1\170\2\uffff\1\172\1\uffff\1\174\1\uffff\1\175\1\uffff\1"+
		"\176\2\uffff\1\u0080\1\uffff\1\u0082\1\uffff\1\u0083\1\uffff\1\u0085\1"+
		"\uffff\1\u0087\1\uffff\1\u0089\1\uffff\1\u008b\1\uffff\1\u008d\1\uffff"+
		"\1\u008f\1\uffff\1\u0090\1\uffff\1\u0092\3\uffff\1\u0096\1\uffff\1\u0098"+
		"\1\uffff\1\u009a\1\uffff\1\u009c\1\uffff\1\u009e\1\uffff\1\u00a0\1\uffff"+
		"\1\u00a2\1\uffff\1\u00a4\2\uffff\1\u00a6\1\uffff\1\u00a7\1\uffff\1\u00a9"+
		"\3\uffff\1\u00ac\1\uffff\1\u00ad\2\uffff\1\u00af\1\uffff\1\u00b1\1\uffff"+
		"\1\u00b3\1\uffff\1\u00b5\1\uffff\1\u00b7\1\uffff\1\u00b9\2\uffff\1\u00bb"+
		"\3\uffff\1\u00c0\1\uffff\1\u00c2\1\uffff\1\u00c4\1\uffff\1\u00c6\1\uffff"+
		"\1\u00c8\1\uffff\1\u00ca\1\uffff\1\u00cb\1\uffff\1\u00cd\4\uffff\1\u00cf"+
		"\2\uffff\1\u00d1\2\uffff\1\u00d3\1\uffff\1\u00d5\1\uffff\1\u00d7\1\uffff"+
		"\1\u00d8\1\uffff\1\u00da\1\uffff\1\u00dc\6\uffff\1\u00e3\3\uffff\1\u00e5"+
		"\1\uffff\1\u00e7\1\uffff\1\u00e9\1\uffff\1\u00eb\2\uffff\1\u00ed\1\uffff"+
		"\1\u00ef\1\uffff\1\u00f1\1\uffff\1\u00f3\1\uffff\1\u00f5\1\uffff\1\u00f7"+
		"\2\uffff\1\u00f9\1\uffff\1\u00fb\10\uffff\1\u0101\1\uffff\1\u0103\1\uffff"+
		"\1\u0105\1\uffff\1\u0107\1\uffff\1\u0108\1\uffff\1\u0109\1\uffff\1\u010b"+
		"\1\uffff\1\u010c\1\uffff\1\u010d\1\uffff\1\u010f\11\uffff\1\u0117\1\uffff"+
		"\1\u0119\1\uffff\1\u011b\1\uffff\1\u011d\3\uffff\1\u011f\13\uffff\1\u012a"+
		"\1\uffff\1\u012b\1\uffff\1\u012c\1\uffff\1\u012e\65\uffff";
	static final String DFA13_eofS =
		"\u0152\uffff";
	static final String DFA13_minS =
		"\1\11\1\60\2\uffff\3\60\1\uffff\1\60\2\uffff\1\60\2\uffff\12\60\1\uffff"+
		"\1\60\1\uffff\2\60\3\uffff\1\52\3\60\1\uffff\20\60\1\40\10\60\2\uffff"+
		"\1\60\1\0\1\60\2\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\uffff\1\60\1"+
		"\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60"+
		"\1\0\1\60\1\0\1\60\1\0\1\141\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1"+
		"\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\uffff\1\40\1\0\1\60\1\0\1\60\2\0"+
		"\1\uffff\1\60\1\0\1\60\1\0\1\uffff\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0"+
		"\1\60\1\0\1\60\1\0\1\uffff\1\40\1\0\1\142\1\145\1\60\1\0\1\40\1\0\1\60"+
		"\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\uffff\1\0\1\uffff"+
		"\1\60\1\0\1\uffff\1\60\1\uffff\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0"+
		"\1\60\1\0\1\60\1\0\1\144\1\0\1\157\1\141\1\uffff\1\40\1\0\1\uffff\1\0"+
		"\1\60\1\0\1\60\1\0\1\60\1\0\1\60\2\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1"+
		"\0\1\60\1\0\1\60\2\0\1\40\1\0\1\40\1\0\2\uffff\1\166\1\162\1\157\1\141"+
		"\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1"+
		"\60\1\0\1\60\1\0\1\40\1\0\1\uffff\1\0\1\150\1\0\1\145\1\151\1\167\1\40"+
		"\1\60\1\0\1\60\1\0\1\60\1\0\1\60\2\0\1\uffff\1\40\1\0\2\uffff\1\144\1"+
		"\0\1\uffff\1\150\1\40\1\163\1\40\1\150\1\40\1\0\1\60\1\0\1\60\1\0\1\40"+
		"\1\0\1\uffff\1\0\1\157\1\160\2\uffff\3\150\1\151\1\157\1\144\1\0\3\uffff"+
		"\1\0\1\167\1\40\2\uffff\1\40\2\uffff\1\147\1\167\2\uffff\1\156\1\150\1"+
		"\151\1\150\1\145\1\40\4\uffff\1\145\1\162\1\150\1\162\1\40\2\uffff\1\40"+
		"\2\150\4\uffff";
	static final String DFA13_maxS =
		"\2\172\2\uffff\3\172\1\uffff\1\172\2\uffff\1\172\2\uffff\12\172\1\uffff"+
		"\1\172\1\uffff\2\172\3\uffff\1\57\3\172\1\uffff\31\172\2\uffff\1\172\1"+
		"\0\1\172\2\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\uffff\1\172\1\0"+
		"\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0"+
		"\1\172\1\0\1\172\1\0\1\172\1\0\1\142\1\0\1\172\1\0\1\172\1\0\1\172\1\0"+
		"\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\uffff\1\172\1\0\1"+
		"\172\1\0\1\172\2\0\1\uffff\1\172\1\0\1\172\1\0\1\uffff\1\172\1\0\1\172"+
		"\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\uffff\1\172\1\0\1\142\1"+
		"\165\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172"+
		"\1\0\1\172\1\0\1\uffff\1\0\1\uffff\1\172\1\0\1\uffff\1\172\1\uffff\1\0"+
		"\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\165\1\0"+
		"\1\157\1\154\1\uffff\1\172\1\0\1\uffff\1\0\1\172\1\0\1\172\1\0\1\172\1"+
		"\0\1\172\2\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\2"+
		"\0\1\172\1\0\1\172\1\0\2\uffff\1\166\1\162\1\157\1\141\1\0\1\172\1\0\1"+
		"\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1"+
		"\172\1\0\1\172\1\0\1\uffff\1\0\1\164\1\0\1\145\1\151\1\167\1\40\1\172"+
		"\1\0\1\172\1\0\1\172\1\0\1\172\2\0\1\uffff\1\172\1\0\2\uffff\1\165\1\0"+
		"\1\uffff\1\162\1\40\1\163\1\40\1\154\1\172\1\0\1\172\1\0\1\172\1\0\1\172"+
		"\1\0\1\uffff\1\0\1\157\1\160\2\uffff\1\164\1\150\1\164\1\151\1\157\1\165"+
		"\1\0\3\uffff\1\0\1\167\1\40\2\uffff\1\40\2\uffff\1\147\1\167\2\uffff\1"+
		"\156\1\164\1\167\1\150\1\145\1\40\4\uffff\1\145\1\162\1\164\1\162\1\40"+
		"\2\uffff\1\40\2\154\4\uffff";
	static final String DFA13_acceptS =
		"\2\uffff\1\2\1\3\3\uffff\1\7\1\uffff\1\11\1\12\1\uffff\1\14\1\15\12\uffff"+
		"\1\60\1\uffff\1\61\2\uffff\1\63\1\57\1\65\4\uffff\1\64\31\uffff\1\66\1"+
		"\67\15\uffff\1\10\50\uffff\1\1\7\uffff\1\6\4\uffff\1\13\14\uffff\1\31"+
		"\24\uffff\1\16\1\uffff\1\4\2\uffff\1\62\1\uffff\1\50\21\uffff\1\42\2\uffff"+
		"\1\47\33\uffff\1\32\1\33\31\uffff\1\25\20\uffff\1\5\2\uffff\1\17\1\20"+
		"\2\uffff\1\26\15\uffff\1\51\3\uffff\1\27\1\30\7\uffff\1\54\1\55\1\56\3"+
		"\uffff\1\34\1\35\1\uffff\1\40\1\41\2\uffff\1\52\1\53\6\uffff\1\23\1\24"+
		"\1\36\1\37\5\uffff\1\21\1\22\3\uffff\1\45\1\46\1\43\1\44";
	static final String DFA13_specialS =
		"\1\u00da\1\4\2\uffff\1\u00c0\1\111\1\u00c4\1\uffff\1\33\2\uffff\1\u00cf"+
		"\2\uffff\1\61\1\27\1\30\1\50\1\2\1\66\1\u00ba\1\76\1\u00e6\1\31\3\uffff"+
		"\1\32\1\115\4\uffff\1\u00d9\1\5\1\u00bb\1\uffff\1\u00db\1\u00de\1\104"+
		"\1\u00e5\1\u00e7\1\112\1\74\1\u00e8\1\10\1\22\1\u00bc\1\77\1\67\1\u00c5"+
		"\1\114\1\63\1\116\1\u00af\1\0\1\u00b3\1\34\1\42\1\52\1\102\1\105\2\uffff"+
		"\1\u00d7\1\121\1\6\1\120\1\122\1\u00dc\1\144\1\u00df\1\154\1\u00ce\1\155"+
		"\1\u00e3\1\171\1\uffff\1\113\1\117\1\75\1\u00a1\1\21\1\132\1\12\1\u00a2"+
		"\1\23\1\172\1\u00bd\1\133\1\100\1\u00ae\1\70\1\137\1\u00c6\1\u0092\1\u00c3"+
		"\1\145\1\64\1\u00a0\1\uffff\1\u0099\1\u00b0\1\146\1\1\1\143\1\u00b4\1"+
		"\u0093\1\35\1\167\1\43\1\u00a3\1\53\1\u0085\1\103\1\173\1\106\1\175\1"+
		"\uffff\1\7\1\156\1\u00d8\1\151\1\u00e0\1\u0084\1\u00d1\1\uffff\1\u00e4"+
		"\1\u0082\1\u00c7\1\176\1\uffff\1\14\1\u0080\1\24\1\125\1\u00be\1\160\1"+
		"\101\1\u0097\1\71\1\u009e\1\u00c8\1\u009f\1\uffff\1\65\1\u00a8\2\uffff"+
		"\1\u00b1\1\u00a4\1\3\1\135\1\u00b5\1\u009b\1\36\1\u00aa\1\44\1\u008d\1"+
		"\54\1\u009c\1\u00cc\1\131\1\107\1\u008e\1\uffff\1\u0098\1\uffff\1\u00e1"+
		"\1\147\1\uffff\1\11\1\uffff\1\u00d2\1\16\1\u0089\1\25\1\157\1\u00bf\1"+
		"\152\1\u00c9\1\u00ab\1\72\1\123\1\u00ca\1\u0094\1\uffff\1\141\3\uffff"+
		"\1\u00b2\1\u009a\1\uffff\1\127\1\u00b6\1\u0090\1\37\1\u0083\1\45\1\163"+
		"\1\55\1\u0095\1\u00d3\1\110\1\u00a5\1\u00e2\1\140\1\13\1\u008c\1\20\1"+
		"\150\1\26\1\134\1\u00c1\1\u008f\1\u00d4\1\73\1\u0087\1\u00cb\1\u00ad\6"+
		"\uffff\1\u0091\1\u00b7\1\u0096\1\40\1\u0088\1\46\1\170\1\56\1\166\1\u00d0"+
		"\1\u008a\1\u00dd\1\153\1\15\1\u0081\1\51\1\174\1\62\1\130\1\u00c2\1\u00a6"+
		"\1\uffff\1\136\1\uffff\1\124\4\uffff\1\u00b8\1\142\1\41\1\177\1\47\1\u00ac"+
		"\1\57\1\161\1\u00d5\1\uffff\1\17\1\u008b\3\uffff\1\u009d\6\uffff\1\u00b9"+
		"\1\u00a7\1\u00cd\1\164\1\u00d6\1\165\1\60\1\162\1\uffff\1\u00a9\12\uffff"+
		"\1\126\3\uffff\1\u0086\43\uffff}>";
	static final String[] DFA13_transitionS = {
			"\2\37\1\uffff\2\37\22\uffff\1\35\1\uffff\1\32\2\uffff\1\11\2\uffff\1"+
			"\7\1\2\2\uffff\1\12\1\30\1\uffff\1\40\12\31\1\uffff\1\3\5\uffff\15\34"+
			"\1\13\14\34\1\14\1\uffff\1\15\1\uffff\1\36\1\uffff\1\1\1\16\1\17\1\4"+
			"\1\20\1\21\1\22\1\27\1\23\2\34\1\5\1\24\1\6\1\10\2\34\1\25\1\26\2\34"+
			"\1\33\4\34",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\13\43\1\42\1\43\1\41\14\43",
			"",
			"",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\1\45\31\43",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\4\43\1\46\11\43\1\47\13\43",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\16\43\1\50\13\43",
			"",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\17\43\1\53\1\43\1\51\3\43"+
			"\1\52\4\43",
			"",
			"",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\1\54\31\43",
			"",
			"",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\4\43\1\55\17\43\1\56\5\43",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\13\43\1\60\5\43\1\57\10\43",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\15\43\1\61\2\43\1\62\11\43",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\16\43\1\63\13\43",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\16\43\1\64\13\43",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\22\43\1\65\7\43",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\1\66\15\43\1\67\13\43",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\4\43\1\70\25\43",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\14\43\1\71\2\43\1\72\3\43"+
			"\1\73\6\43",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\10\43\1\74\21\43",
			"",
			"\12\31\7\uffff\32\44\6\uffff\32\44",
			"",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\16\43\1\75\13\43",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\32\43",
			"",
			"",
			"",
			"\1\76\4\uffff\1\77",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\3\43\1\100\26\43",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\22\43\1\102\7\43",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\32\43",
			"",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\30\43\1\105\1\43",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\15\43\1\107\14\43",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\26\43\1\111\3\43",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\23\43\1\113\6\43",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\32\43",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\4\43\1\116\25\43",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\4\43\1\120\25\43",
			"\12\43\7\uffff\15\43\1\122\14\43\4\uffff\1\36\1\uffff\32\43",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\1\124\31\43",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\13\43\1\126\16\43",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\16\43\1\130\13\43",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\16\43\1\132\13\43",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\3\43\1\134\26\43",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\24\43\1\136\5\43",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\21\43\1\140\10\43",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\4\43\1\142\25\43",
			"\1\144\17\uffff\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\32\43",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\12\43\1\146\17\43",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\21\43\1\150\10\43",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\25\43\1\152\4\43",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\16\43\1\154\13\43",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\1\156\31\43",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\1\160\31\43",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\6\43\1\162\23\43",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\13\43\1\164\16\43",
			"",
			"",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\32\43",
			"\1\uffff",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\16\43\1\167\13\43",
			"\1\uffff",
			"\1\uffff",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\22\43\1\171\7\43",
			"\1\uffff",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\10\43\1\173\21\43",
			"\1\uffff",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\32\43",
			"\1\uffff",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\32\43",
			"\1\uffff",
			"",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\21\43\1\177\10\43",
			"\1\uffff",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\15\43\1\u0081\14\43",
			"\1\uffff",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\32\43",
			"\1\uffff",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\21\43\1\u0084\10\43",
			"\1\uffff",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\13\43\1\u0086\16\43",
			"\1\uffff",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\22\43\1\u0088\7\43",
			"\1\uffff",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\22\43\1\u008a\7\43",
			"\1\uffff",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\10\43\1\u008c\21\43",
			"\1\uffff",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\1\u008e\31\43",
			"\1\uffff",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\32\43",
			"\1\uffff",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\22\43\1\u0091\7\43",
			"\1\uffff",
			"\1\u0093\1\u0094",
			"\1\uffff",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\4\43\1\u0095\25\43",
			"\1\uffff",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\4\43\1\u0097\25\43",
			"\1\uffff",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\4\43\1\u0099\25\43",
			"\1\uffff",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\16\43\1\u009b\13\43",
			"\1\uffff",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\15\43\1\u009d\14\43",
			"\1\uffff",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\21\43\1\u009f\10\43",
			"\1\uffff",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\7\43\1\u00a1\22\43",
			"\1\uffff",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\24\43\1\u00a3\5\43",
			"\1\uffff",
			"",
			"\1\u00a5\17\uffff\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\32\43",
			"\1\uffff",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\32\43",
			"\1\uffff",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\4\43\1\u00a8\25\43",
			"\1\uffff",
			"\1\uffff",
			"",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\21\43\1\u00ab\10\43",
			"\1\uffff",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\32\43",
			"\1\uffff",
			"",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\10\43\1\u00ae\21\43",
			"\1\uffff",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\10\43\1\u00b0\21\43",
			"\1\uffff",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\22\43\1\u00b2\7\43",
			"\1\uffff",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\4\43\1\u00b4\25\43",
			"\1\uffff",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\15\43\1\u00b6\14\43",
			"\1\uffff",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\13\43\1\u00b8\16\43",
			"\1\uffff",
			"",
			"\1\u00ba\17\uffff\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\32\43",
			"\1\uffff",
			"\1\u00bc",
			"\1\u00bd\17\uffff\1\u00be",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\22\43\1\u00bf\7\43",
			"\1\uffff",
			"\1\u00c1\17\uffff\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\32\43",
			"\1\uffff",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\21\43\1\u00c3\10\43",
			"\1\uffff",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\23\43\1\u00c5\6\43",
			"\1\uffff",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\15\43\1\u00c7\14\43",
			"\1\uffff",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\23\43\1\u00c9\6\43",
			"\1\uffff",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\32\43",
			"\1\uffff",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\14\43\1\u00cc\15\43",
			"\1\uffff",
			"",
			"\1\uffff",
			"",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\15\43\1\u00ce\14\43",
			"\1\uffff",
			"",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\10\43\1\u00d0\21\43",
			"",
			"\1\uffff",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\22\43\1\u00d2\7\43",
			"\1\uffff",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\22\43\1\u00d4\7\43",
			"\1\uffff",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\4\43\1\u00d6\25\43",
			"\1\uffff",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\32\43",
			"\1\uffff",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\6\43\1\u00d9\23\43",
			"\1\uffff",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\22\43\1\u00db\7\43",
			"\1\uffff",
			"\1\u00dd\20\uffff\1\u00de",
			"\1\uffff",
			"\1\u00df",
			"\1\u00e0\12\uffff\1\u00e1",
			"",
			"\1\u00e2\17\uffff\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\32\43",
			"\1\uffff",
			"",
			"\1\uffff",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\22\43\1\u00e4\7\43",
			"\1\uffff",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\7\43\1\u00e6\22\43",
			"\1\uffff",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\10\43\1\u00e8\21\43",
			"\1\uffff",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\10\43\1\u00ea\21\43",
			"\1\uffff",
			"\1\uffff",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\4\43\1\u00ec\25\43",
			"\1\uffff",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\23\43\1\u00ee\6\43",
			"\1\uffff",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\3\43\1\u00f0\26\43",
			"\1\uffff",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\7\43\1\u00f2\22\43",
			"\1\uffff",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\7\43\1\u00f4\22\43",
			"\1\uffff",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\22\43\1\u00f6\7\43",
			"\1\uffff",
			"\1\uffff",
			"\1\u00f8\17\uffff\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\32\43",
			"\1\uffff",
			"\1\u00fa\17\uffff\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\32\43",
			"\1\uffff",
			"",
			"",
			"\1\u00fc",
			"\1\u00fd",
			"\1\u00fe",
			"\1\u00ff",
			"\1\uffff",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\4\43\1\u0100\25\43",
			"\1\uffff",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\4\43\1\u0102\25\43",
			"\1\uffff",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\15\43\1\u0104\14\43",
			"\1\uffff",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\15\43\1\u0106\14\43",
			"\1\uffff",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\32\43",
			"\1\uffff",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\32\43",
			"\1\uffff",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\4\43\1\u010a\25\43",
			"\1\uffff",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\32\43",
			"\1\uffff",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\32\43",
			"\1\uffff",
			"\1\u010e\17\uffff\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\32\43",
			"\1\uffff",
			"",
			"\1\uffff",
			"\1\u0110\13\uffff\1\u0111",
			"\1\uffff",
			"\1\u0112",
			"\1\u0113",
			"\1\u0114",
			"\1\u0115",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\22\43\1\u0116\7\43",
			"\1\uffff",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\3\43\1\u0118\26\43",
			"\1\uffff",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\6\43\1\u011a\23\43",
			"\1\uffff",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\6\43\1\u011c\23\43",
			"\1\uffff",
			"\1\uffff",
			"",
			"\1\u011e\17\uffff\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\32\43",
			"\1\uffff",
			"",
			"",
			"\1\u0120\20\uffff\1\u0121",
			"\1\uffff",
			"",
			"\1\u0122\11\uffff\1\u0123",
			"\1\u0124",
			"\1\u0125",
			"\1\u0126",
			"\1\u0127\3\uffff\1\u0128",
			"\1\u0129\17\uffff\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\32\43",
			"\1\uffff",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\32\43",
			"\1\uffff",
			"\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\32\43",
			"\1\uffff",
			"\1\u012d\17\uffff\12\43\7\uffff\32\43\4\uffff\1\36\1\uffff\32\43",
			"\1\uffff",
			"",
			"\1\uffff",
			"\1\u012f",
			"\1\u0130",
			"",
			"",
			"\1\u0131\13\uffff\1\u0132",
			"\1\u0133",
			"\1\u0134\13\uffff\1\u0135",
			"\1\u0136",
			"\1\u0137",
			"\1\u0138\20\uffff\1\u0139",
			"\1\uffff",
			"",
			"",
			"",
			"\1\uffff",
			"\1\u013a",
			"\1\u013b",
			"",
			"",
			"\1\u013c",
			"",
			"",
			"\1\u013d",
			"\1\u013e",
			"",
			"",
			"\1\u013f",
			"\1\u0140\13\uffff\1\u0141",
			"\1\u0142\15\uffff\1\u0143",
			"\1\u0144",
			"\1\u0145",
			"\1\u0146",
			"",
			"",
			"",
			"",
			"\1\u0147",
			"\1\u0148",
			"\1\u0149\13\uffff\1\u014a",
			"\1\u014b",
			"\1\u014c",
			"",
			"",
			"\1\u014d",
			"\1\u014e\3\uffff\1\u014f",
			"\1\u0150\3\uffff\1\u0151",
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
			return "1:1: Tokens : ( AND | CLOSEPARENTEHSIS | COMMA | DAYS | LENIENT | NOT | OPENPARENTEHSIS | OR | PERCENT | T__50 | T__51 | T__52 | T__53 | T__54 | T__55 | T__56 | T__57 | T__58 | T__59 | T__60 | T__61 | T__62 | T__63 | T__64 | T__65 | T__66 | T__67 | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | T__79 | T__80 | T__81 | T__82 | T__83 | T__84 | T__85 | T__86 | Operation | NumberToken | StringToken | HistoricalData | WhiteChar | Tcheat | WS | COMMENT | LINE_COMMENT );";
		}
		@Override
		public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
			IntStream input = _input;
			int _s = s;
			switch ( s ) {
					case 0 : 
						int LA13_55 = input.LA(1);
						 
						int index13_55 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_55=='r') ) {s = 104;}
						else if ( ((LA13_55 >= '0' && LA13_55 <= '9')||(LA13_55 >= 'A' && LA13_55 <= 'Z')||(LA13_55 >= 'a' && LA13_55 <= 'q')||(LA13_55 >= 's' && LA13_55 <= 'z')) ) {s = 35;}
						else if ( (LA13_55=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 105;
						 
						input.seek(index13_55);
						if ( s>=0 ) return s;
						break;

					case 1 : 
						int LA13_104 = input.LA(1);
						 
						int index13_104 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_104=='e') ) {s = 151;}
						else if ( ((LA13_104 >= '0' && LA13_104 <= '9')||(LA13_104 >= 'A' && LA13_104 <= 'Z')||(LA13_104 >= 'a' && LA13_104 <= 'd')||(LA13_104 >= 'f' && LA13_104 <= 'z')) ) {s = 35;}
						else if ( (LA13_104=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 152;
						 
						input.seek(index13_104);
						if ( s>=0 ) return s;
						break;

					case 2 : 
						int LA13_18 = input.LA(1);
						 
						int index13_18 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_18=='o') ) {s = 52;}
						else if ( ((LA13_18 >= '0' && LA13_18 <= '9')||(LA13_18 >= 'A' && LA13_18 <= 'Z')||(LA13_18 >= 'a' && LA13_18 <= 'n')||(LA13_18 >= 'p' && LA13_18 <= 'z')) ) {s = 35;}
						else if ( (LA13_18=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 36;
						 
						input.seek(index13_18);
						if ( s>=0 ) return s;
						break;

					case 3 : 
						int LA13_151 = input.LA(1);
						 
						int index13_151 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_151==' ') ) {s = 193;}
						else if ( ((LA13_151 >= '0' && LA13_151 <= '9')||(LA13_151 >= 'A' && LA13_151 <= 'Z')||(LA13_151 >= 'a' && LA13_151 <= 'z')) ) {s = 35;}
						else if ( (LA13_151=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 194;
						 
						input.seek(index13_151);
						if ( s>=0 ) return s;
						break;

					case 4 : 
						int LA13_1 = input.LA(1);
						 
						int index13_1 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_1=='n') ) {s = 33;}
						else if ( (LA13_1=='l') ) {s = 34;}
						else if ( ((LA13_1 >= '0' && LA13_1 <= '9')||(LA13_1 >= 'A' && LA13_1 <= 'Z')||(LA13_1 >= 'a' && LA13_1 <= 'k')||LA13_1=='m'||(LA13_1 >= 'o' && LA13_1 <= 'z')) ) {s = 35;}
						else if ( (LA13_1=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 36;
						 
						input.seek(index13_1);
						if ( s>=0 ) return s;
						break;

					case 5 : 
						int LA13_34 = input.LA(1);
						 
						int index13_34 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_34=='s') ) {s = 66;}
						else if ( ((LA13_34 >= '0' && LA13_34 <= '9')||(LA13_34 >= 'A' && LA13_34 <= 'Z')||(LA13_34 >= 'a' && LA13_34 <= 'r')||(LA13_34 >= 't' && LA13_34 <= 'z')) ) {s = 35;}
						else if ( (LA13_34=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 67;
						 
						input.seek(index13_34);
						if ( s>=0 ) return s;
						break;

					case 6 : 
						int LA13_66 = input.LA(1);
						 
						int index13_66 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_66=='o') ) {s = 119;}
						else if ( ((LA13_66 >= '0' && LA13_66 <= '9')||(LA13_66 >= 'A' && LA13_66 <= 'Z')||(LA13_66 >= 'a' && LA13_66 <= 'n')||(LA13_66 >= 'p' && LA13_66 <= 'z')) ) {s = 35;}
						else if ( (LA13_66=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 120;
						 
						input.seek(index13_66);
						if ( s>=0 ) return s;
						break;

					case 7 : 
						int LA13_119 = input.LA(1);
						 
						int index13_119 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_119==' ') ) {s = 165;}
						else if ( ((LA13_119 >= '0' && LA13_119 <= '9')||(LA13_119 >= 'A' && LA13_119 <= 'Z')||(LA13_119 >= 'a' && LA13_119 <= 'z')) ) {s = 35;}
						else if ( (LA13_119=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 166;
						 
						input.seek(index13_119);
						if ( s>=0 ) return s;
						break;

					case 8 : 
						int LA13_45 = input.LA(1);
						 
						int index13_45 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_45=='a') ) {s = 84;}
						else if ( ((LA13_45 >= '0' && LA13_45 <= '9')||(LA13_45 >= 'A' && LA13_45 <= 'Z')||(LA13_45 >= 'b' && LA13_45 <= 'z')) ) {s = 35;}
						else if ( (LA13_45=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 85;
						 
						input.seek(index13_45);
						if ( s>=0 ) return s;
						break;

					case 9 : 
						int LA13_171 = input.LA(1);
						 
						int index13_171 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_171=='i') ) {s = 208;}
						else if ( ((LA13_171 >= '0' && LA13_171 <= '9')||(LA13_171 >= 'A' && LA13_171 <= 'Z')||(LA13_171 >= 'a' && LA13_171 <= 'h')||(LA13_171 >= 'j' && LA13_171 <= 'z')) ) {s = 35;}
						else if ( (LA13_171=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 209;
						 
						input.seek(index13_171);
						if ( s>=0 ) return s;
						break;

					case 10 : 
						int LA13_84 = input.LA(1);
						 
						int index13_84 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_84=='r') ) {s = 132;}
						else if ( ((LA13_84 >= '0' && LA13_84 <= '9')||(LA13_84 >= 'A' && LA13_84 <= 'Z')||(LA13_84 >= 'a' && LA13_84 <= 'q')||(LA13_84 >= 's' && LA13_84 <= 'z')) ) {s = 35;}
						else if ( (LA13_84=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 133;
						 
						input.seek(index13_84);
						if ( s>=0 ) return s;
						break;

					case 11 : 
						int LA13_208 = input.LA(1);
						 
						int index13_208 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_208=='d') ) {s = 240;}
						else if ( ((LA13_208 >= '0' && LA13_208 <= '9')||(LA13_208 >= 'A' && LA13_208 <= 'Z')||(LA13_208 >= 'a' && LA13_208 <= 'c')||(LA13_208 >= 'e' && LA13_208 <= 'z')) ) {s = 35;}
						else if ( (LA13_208=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 241;
						 
						input.seek(index13_208);
						if ( s>=0 ) return s;
						break;

					case 12 : 
						int LA13_132 = input.LA(1);
						 
						int index13_132 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_132=='i') ) {s = 174;}
						else if ( ((LA13_132 >= '0' && LA13_132 <= '9')||(LA13_132 >= 'A' && LA13_132 <= 'Z')||(LA13_132 >= 'a' && LA13_132 <= 'h')||(LA13_132 >= 'j' && LA13_132 <= 'z')) ) {s = 35;}
						else if ( (LA13_132=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 175;
						 
						input.seek(index13_132);
						if ( s>=0 ) return s;
						break;

					case 13 : 
						int LA13_240 = input.LA(1);
						 
						int index13_240 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_240=='e') ) {s = 266;}
						else if ( ((LA13_240 >= '0' && LA13_240 <= '9')||(LA13_240 >= 'A' && LA13_240 <= 'Z')||(LA13_240 >= 'a' && LA13_240 <= 'd')||(LA13_240 >= 'f' && LA13_240 <= 'z')) ) {s = 35;}
						else if ( (LA13_240=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 267;
						 
						input.seek(index13_240);
						if ( s>=0 ) return s;
						break;

					case 14 : 
						int LA13_174 = input.LA(1);
						 
						int index13_174 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_174=='s') ) {s = 210;}
						else if ( ((LA13_174 >= '0' && LA13_174 <= '9')||(LA13_174 >= 'A' && LA13_174 <= 'Z')||(LA13_174 >= 'a' && LA13_174 <= 'r')||(LA13_174 >= 't' && LA13_174 <= 'z')) ) {s = 35;}
						else if ( (LA13_174=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 211;
						 
						input.seek(index13_174);
						if ( s>=0 ) return s;
						break;

					case 15 : 
						int LA13_266 = input.LA(1);
						 
						int index13_266 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_266==' ') ) {s = 286;}
						else if ( ((LA13_266 >= '0' && LA13_266 <= '9')||(LA13_266 >= 'A' && LA13_266 <= 'Z')||(LA13_266 >= 'a' && LA13_266 <= 'z')) ) {s = 35;}
						else if ( (LA13_266=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 287;
						 
						input.seek(index13_266);
						if ( s>=0 ) return s;
						break;

					case 16 : 
						int LA13_210 = input.LA(1);
						 
						int index13_210 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_210=='h') ) {s = 242;}
						else if ( ((LA13_210 >= '0' && LA13_210 <= '9')||(LA13_210 >= 'A' && LA13_210 <= 'Z')||(LA13_210 >= 'a' && LA13_210 <= 'g')||(LA13_210 >= 'i' && LA13_210 <= 'z')) ) {s = 35;}
						else if ( (LA13_210=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 243;
						 
						input.seek(index13_210);
						if ( s>=0 ) return s;
						break;

					case 17 : 
						int LA13_82 = input.LA(1);
						 
						int index13_82 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_82 >= '0' && LA13_82 <= '9')||(LA13_82 >= 'A' && LA13_82 <= 'Z')||(LA13_82 >= 'a' && LA13_82 <= 'z')) ) {s = 35;}
						else if ( (LA13_82=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 131;
						 
						input.seek(index13_82);
						if ( s>=0 ) return s;
						break;

					case 18 : 
						int LA13_46 = input.LA(1);
						 
						int index13_46 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_46=='l') ) {s = 86;}
						else if ( ((LA13_46 >= '0' && LA13_46 <= '9')||(LA13_46 >= 'A' && LA13_46 <= 'Z')||(LA13_46 >= 'a' && LA13_46 <= 'k')||(LA13_46 >= 'm' && LA13_46 <= 'z')) ) {s = 35;}
						else if ( (LA13_46=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 87;
						 
						input.seek(index13_46);
						if ( s>=0 ) return s;
						break;

					case 19 : 
						int LA13_86 = input.LA(1);
						 
						int index13_86 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_86=='l') ) {s = 134;}
						else if ( ((LA13_86 >= '0' && LA13_86 <= '9')||(LA13_86 >= 'A' && LA13_86 <= 'Z')||(LA13_86 >= 'a' && LA13_86 <= 'k')||(LA13_86 >= 'm' && LA13_86 <= 'z')) ) {s = 35;}
						else if ( (LA13_86=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 135;
						 
						input.seek(index13_86);
						if ( s>=0 ) return s;
						break;

					case 20 : 
						int LA13_134 = input.LA(1);
						 
						int index13_134 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_134=='i') ) {s = 176;}
						else if ( ((LA13_134 >= '0' && LA13_134 <= '9')||(LA13_134 >= 'A' && LA13_134 <= 'Z')||(LA13_134 >= 'a' && LA13_134 <= 'h')||(LA13_134 >= 'j' && LA13_134 <= 'z')) ) {s = 35;}
						else if ( (LA13_134=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 177;
						 
						input.seek(index13_134);
						if ( s>=0 ) return s;
						break;

					case 21 : 
						int LA13_176 = input.LA(1);
						 
						int index13_176 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_176=='s') ) {s = 212;}
						else if ( ((LA13_176 >= '0' && LA13_176 <= '9')||(LA13_176 >= 'A' && LA13_176 <= 'Z')||(LA13_176 >= 'a' && LA13_176 <= 'r')||(LA13_176 >= 't' && LA13_176 <= 'z')) ) {s = 35;}
						else if ( (LA13_176=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 213;
						 
						input.seek(index13_176);
						if ( s>=0 ) return s;
						break;

					case 22 : 
						int LA13_212 = input.LA(1);
						 
						int index13_212 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_212=='h') ) {s = 244;}
						else if ( ((LA13_212 >= '0' && LA13_212 <= '9')||(LA13_212 >= 'A' && LA13_212 <= 'Z')||(LA13_212 >= 'a' && LA13_212 <= 'g')||(LA13_212 >= 'i' && LA13_212 <= 'z')) ) {s = 35;}
						else if ( (LA13_212=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 245;
						 
						input.seek(index13_212);
						if ( s>=0 ) return s;
						break;

					case 23 : 
						int LA13_15 = input.LA(1);
						 
						int index13_15 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_15=='r') ) {s = 47;}
						else if ( (LA13_15=='l') ) {s = 48;}
						else if ( ((LA13_15 >= '0' && LA13_15 <= '9')||(LA13_15 >= 'A' && LA13_15 <= 'Z')||(LA13_15 >= 'a' && LA13_15 <= 'k')||(LA13_15 >= 'm' && LA13_15 <= 'q')||(LA13_15 >= 's' && LA13_15 <= 'z')) ) {s = 35;}
						else if ( (LA13_15=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 36;
						 
						input.seek(index13_15);
						if ( s>=0 ) return s;
						break;

					case 24 : 
						int LA13_16 = input.LA(1);
						 
						int index13_16 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_16=='n') ) {s = 49;}
						else if ( (LA13_16=='q') ) {s = 50;}
						else if ( ((LA13_16 >= '0' && LA13_16 <= '9')||(LA13_16 >= 'A' && LA13_16 <= 'Z')||(LA13_16 >= 'a' && LA13_16 <= 'm')||(LA13_16 >= 'o' && LA13_16 <= 'p')||(LA13_16 >= 'r' && LA13_16 <= 'z')) ) {s = 35;}
						else if ( (LA13_16=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 36;
						 
						input.seek(index13_16);
						if ( s>=0 ) return s;
						break;

					case 25 : 
						int LA13_23 = input.LA(1);
						 
						int index13_23 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_23=='i') ) {s = 60;}
						else if ( ((LA13_23 >= '0' && LA13_23 <= '9')||(LA13_23 >= 'A' && LA13_23 <= 'Z')||(LA13_23 >= 'a' && LA13_23 <= 'h')||(LA13_23 >= 'j' && LA13_23 <= 'z')) ) {s = 35;}
						else if ( (LA13_23=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 36;
						 
						input.seek(index13_23);
						if ( s>=0 ) return s;
						break;

					case 26 : 
						int LA13_27 = input.LA(1);
						 
						int index13_27 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_27=='o') ) {s = 61;}
						else if ( ((LA13_27 >= '0' && LA13_27 <= '9')||(LA13_27 >= 'A' && LA13_27 <= 'Z')||(LA13_27 >= 'a' && LA13_27 <= 'n')||(LA13_27 >= 'p' && LA13_27 <= 'z')) ) {s = 35;}
						else if ( (LA13_27=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 36;
						 
						input.seek(index13_27);
						if ( s>=0 ) return s;
						break;

					case 27 : 
						int LA13_8 = input.LA(1);
						 
						int index13_8 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_8=='r') ) {s = 41;}
						else if ( (LA13_8=='v') ) {s = 42;}
						else if ( (LA13_8=='p') ) {s = 43;}
						else if ( ((LA13_8 >= '0' && LA13_8 <= '9')||(LA13_8 >= 'A' && LA13_8 <= 'Z')||(LA13_8 >= 'a' && LA13_8 <= 'o')||LA13_8=='q'||(LA13_8 >= 's' && LA13_8 <= 'u')||(LA13_8 >= 'w' && LA13_8 <= 'z')) ) {s = 35;}
						else if ( (LA13_8=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 36;
						 
						input.seek(index13_8);
						if ( s>=0 ) return s;
						break;

					case 28 : 
						int LA13_57 = input.LA(1);
						 
						int index13_57 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_57=='o') ) {s = 108;}
						else if ( ((LA13_57 >= '0' && LA13_57 <= '9')||(LA13_57 >= 'A' && LA13_57 <= 'Z')||(LA13_57 >= 'a' && LA13_57 <= 'n')||(LA13_57 >= 'p' && LA13_57 <= 'z')) ) {s = 35;}
						else if ( (LA13_57=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 109;
						 
						input.seek(index13_57);
						if ( s>=0 ) return s;
						break;

					case 29 : 
						int LA13_108 = input.LA(1);
						 
						int index13_108 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_108=='o') ) {s = 155;}
						else if ( ((LA13_108 >= '0' && LA13_108 <= '9')||(LA13_108 >= 'A' && LA13_108 <= 'Z')||(LA13_108 >= 'a' && LA13_108 <= 'n')||(LA13_108 >= 'p' && LA13_108 <= 'z')) ) {s = 35;}
						else if ( (LA13_108=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 156;
						 
						input.seek(index13_108);
						if ( s>=0 ) return s;
						break;

					case 30 : 
						int LA13_155 = input.LA(1);
						 
						int index13_155 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_155=='t') ) {s = 197;}
						else if ( ((LA13_155 >= '0' && LA13_155 <= '9')||(LA13_155 >= 'A' && LA13_155 <= 'Z')||(LA13_155 >= 'a' && LA13_155 <= 's')||(LA13_155 >= 'u' && LA13_155 <= 'z')) ) {s = 35;}
						else if ( (LA13_155=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 198;
						 
						input.seek(index13_155);
						if ( s>=0 ) return s;
						break;

					case 31 : 
						int LA13_197 = input.LA(1);
						 
						int index13_197 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_197=='h') ) {s = 230;}
						else if ( ((LA13_197 >= '0' && LA13_197 <= '9')||(LA13_197 >= 'A' && LA13_197 <= 'Z')||(LA13_197 >= 'a' && LA13_197 <= 'g')||(LA13_197 >= 'i' && LA13_197 <= 'z')) ) {s = 35;}
						else if ( (LA13_197=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 231;
						 
						input.seek(index13_197);
						if ( s>=0 ) return s;
						break;

					case 32 : 
						int LA13_230 = input.LA(1);
						 
						int index13_230 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_230=='e') ) {s = 258;}
						else if ( ((LA13_230 >= '0' && LA13_230 <= '9')||(LA13_230 >= 'A' && LA13_230 <= 'Z')||(LA13_230 >= 'a' && LA13_230 <= 'd')||(LA13_230 >= 'f' && LA13_230 <= 'z')) ) {s = 35;}
						else if ( (LA13_230=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 259;
						 
						input.seek(index13_230);
						if ( s>=0 ) return s;
						break;

					case 33 : 
						int LA13_258 = input.LA(1);
						 
						int index13_258 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_258=='d') ) {s = 280;}
						else if ( ((LA13_258 >= '0' && LA13_258 <= '9')||(LA13_258 >= 'A' && LA13_258 <= 'Z')||(LA13_258 >= 'a' && LA13_258 <= 'c')||(LA13_258 >= 'e' && LA13_258 <= 'z')) ) {s = 35;}
						else if ( (LA13_258=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 281;
						 
						input.seek(index13_258);
						if ( s>=0 ) return s;
						break;

					case 34 : 
						int LA13_58 = input.LA(1);
						 
						int index13_58 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_58=='a') ) {s = 110;}
						else if ( ((LA13_58 >= '0' && LA13_58 <= '9')||(LA13_58 >= 'A' && LA13_58 <= 'Z')||(LA13_58 >= 'b' && LA13_58 <= 'z')) ) {s = 35;}
						else if ( (LA13_58=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 111;
						 
						input.seek(index13_58);
						if ( s>=0 ) return s;
						break;

					case 35 : 
						int LA13_110 = input.LA(1);
						 
						int index13_110 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_110=='n') ) {s = 157;}
						else if ( ((LA13_110 >= '0' && LA13_110 <= '9')||(LA13_110 >= 'A' && LA13_110 <= 'Z')||(LA13_110 >= 'a' && LA13_110 <= 'm')||(LA13_110 >= 'o' && LA13_110 <= 'z')) ) {s = 35;}
						else if ( (LA13_110=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 158;
						 
						input.seek(index13_110);
						if ( s>=0 ) return s;
						break;

					case 36 : 
						int LA13_157 = input.LA(1);
						 
						int index13_157 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_157=='n') ) {s = 199;}
						else if ( ((LA13_157 >= '0' && LA13_157 <= '9')||(LA13_157 >= 'A' && LA13_157 <= 'Z')||(LA13_157 >= 'a' && LA13_157 <= 'm')||(LA13_157 >= 'o' && LA13_157 <= 'z')) ) {s = 35;}
						else if ( (LA13_157=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 200;
						 
						input.seek(index13_157);
						if ( s>=0 ) return s;
						break;

					case 37 : 
						int LA13_199 = input.LA(1);
						 
						int index13_199 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_199=='i') ) {s = 232;}
						else if ( ((LA13_199 >= '0' && LA13_199 <= '9')||(LA13_199 >= 'A' && LA13_199 <= 'Z')||(LA13_199 >= 'a' && LA13_199 <= 'h')||(LA13_199 >= 'j' && LA13_199 <= 'z')) ) {s = 35;}
						else if ( (LA13_199=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 233;
						 
						input.seek(index13_199);
						if ( s>=0 ) return s;
						break;

					case 38 : 
						int LA13_232 = input.LA(1);
						 
						int index13_232 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_232=='n') ) {s = 260;}
						else if ( ((LA13_232 >= '0' && LA13_232 <= '9')||(LA13_232 >= 'A' && LA13_232 <= 'Z')||(LA13_232 >= 'a' && LA13_232 <= 'm')||(LA13_232 >= 'o' && LA13_232 <= 'z')) ) {s = 35;}
						else if ( (LA13_232=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 261;
						 
						input.seek(index13_232);
						if ( s>=0 ) return s;
						break;

					case 39 : 
						int LA13_260 = input.LA(1);
						 
						int index13_260 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_260=='g') ) {s = 282;}
						else if ( ((LA13_260 >= '0' && LA13_260 <= '9')||(LA13_260 >= 'A' && LA13_260 <= 'Z')||(LA13_260 >= 'a' && LA13_260 <= 'f')||(LA13_260 >= 'h' && LA13_260 <= 'z')) ) {s = 35;}
						else if ( (LA13_260=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 283;
						 
						input.seek(index13_260);
						if ( s>=0 ) return s;
						break;

					case 40 : 
						int LA13_17 = input.LA(1);
						 
						int index13_17 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_17=='o') ) {s = 51;}
						else if ( ((LA13_17 >= '0' && LA13_17 <= '9')||(LA13_17 >= 'A' && LA13_17 <= 'Z')||(LA13_17 >= 'a' && LA13_17 <= 'n')||(LA13_17 >= 'p' && LA13_17 <= 'z')) ) {s = 35;}
						else if ( (LA13_17=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 36;
						 
						input.seek(index13_17);
						if ( s>=0 ) return s;
						break;

					case 41 : 
						int LA13_242 = input.LA(1);
						 
						int index13_242 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_242 >= '0' && LA13_242 <= '9')||(LA13_242 >= 'A' && LA13_242 <= 'Z')||(LA13_242 >= 'a' && LA13_242 <= 'z')) ) {s = 35;}
						else if ( (LA13_242=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 268;
						 
						input.seek(index13_242);
						if ( s>=0 ) return s;
						break;

					case 42 : 
						int LA13_59 = input.LA(1);
						 
						int index13_59 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_59=='a') ) {s = 112;}
						else if ( ((LA13_59 >= '0' && LA13_59 <= '9')||(LA13_59 >= 'A' && LA13_59 <= 'Z')||(LA13_59 >= 'b' && LA13_59 <= 'z')) ) {s = 35;}
						else if ( (LA13_59=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 113;
						 
						input.seek(index13_59);
						if ( s>=0 ) return s;
						break;

					case 43 : 
						int LA13_112 = input.LA(1);
						 
						int index13_112 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_112=='r') ) {s = 159;}
						else if ( ((LA13_112 >= '0' && LA13_112 <= '9')||(LA13_112 >= 'A' && LA13_112 <= 'Z')||(LA13_112 >= 'a' && LA13_112 <= 'q')||(LA13_112 >= 's' && LA13_112 <= 'z')) ) {s = 35;}
						else if ( (LA13_112=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 160;
						 
						input.seek(index13_112);
						if ( s>=0 ) return s;
						break;

					case 44 : 
						int LA13_159 = input.LA(1);
						 
						int index13_159 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_159=='t') ) {s = 201;}
						else if ( ((LA13_159 >= '0' && LA13_159 <= '9')||(LA13_159 >= 'A' && LA13_159 <= 'Z')||(LA13_159 >= 'a' && LA13_159 <= 's')||(LA13_159 >= 'u' && LA13_159 <= 'z')) ) {s = 35;}
						else if ( (LA13_159=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 202;
						 
						input.seek(index13_159);
						if ( s>=0 ) return s;
						break;

					case 45 : 
						int LA13_201 = input.LA(1);
						 
						int index13_201 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_201=='i') ) {s = 234;}
						else if ( ((LA13_201 >= '0' && LA13_201 <= '9')||(LA13_201 >= 'A' && LA13_201 <= 'Z')||(LA13_201 >= 'a' && LA13_201 <= 'h')||(LA13_201 >= 'j' && LA13_201 <= 'z')) ) {s = 35;}
						else if ( (LA13_201=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 235;
						 
						input.seek(index13_201);
						if ( s>=0 ) return s;
						break;

					case 46 : 
						int LA13_234 = input.LA(1);
						 
						int index13_234 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_234=='n') ) {s = 262;}
						else if ( ((LA13_234 >= '0' && LA13_234 <= '9')||(LA13_234 >= 'A' && LA13_234 <= 'Z')||(LA13_234 >= 'a' && LA13_234 <= 'm')||(LA13_234 >= 'o' && LA13_234 <= 'z')) ) {s = 35;}
						else if ( (LA13_234=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 263;
						 
						input.seek(index13_234);
						if ( s>=0 ) return s;
						break;

					case 47 : 
						int LA13_262 = input.LA(1);
						 
						int index13_262 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_262=='g') ) {s = 284;}
						else if ( ((LA13_262 >= '0' && LA13_262 <= '9')||(LA13_262 >= 'A' && LA13_262 <= 'Z')||(LA13_262 >= 'a' && LA13_262 <= 'f')||(LA13_262 >= 'h' && LA13_262 <= 'z')) ) {s = 35;}
						else if ( (LA13_262=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 285;
						 
						input.seek(index13_262);
						if ( s>=0 ) return s;
						break;

					case 48 : 
						int LA13_284 = input.LA(1);
						 
						int index13_284 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_284==' ') ) {s = 301;}
						else if ( ((LA13_284 >= '0' && LA13_284 <= '9')||(LA13_284 >= 'A' && LA13_284 <= 'Z')||(LA13_284 >= 'a' && LA13_284 <= 'z')) ) {s = 35;}
						else if ( (LA13_284=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 302;
						 
						input.seek(index13_284);
						if ( s>=0 ) return s;
						break;

					case 49 : 
						int LA13_14 = input.LA(1);
						 
						int index13_14 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_14=='e') ) {s = 45;}
						else if ( (LA13_14=='u') ) {s = 46;}
						else if ( ((LA13_14 >= '0' && LA13_14 <= '9')||(LA13_14 >= 'A' && LA13_14 <= 'Z')||(LA13_14 >= 'a' && LA13_14 <= 'd')||(LA13_14 >= 'f' && LA13_14 <= 't')||(LA13_14 >= 'v' && LA13_14 <= 'z')) ) {s = 35;}
						else if ( (LA13_14=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 36;
						 
						input.seek(index13_14);
						if ( s>=0 ) return s;
						break;

					case 50 : 
						int LA13_244 = input.LA(1);
						 
						int index13_244 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_244 >= '0' && LA13_244 <= '9')||(LA13_244 >= 'A' && LA13_244 <= 'Z')||(LA13_244 >= 'a' && LA13_244 <= 'z')) ) {s = 35;}
						else if ( (LA13_244=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 269;
						 
						input.seek(index13_244);
						if ( s>=0 ) return s;
						break;

					case 51 : 
						int LA13_52 = input.LA(1);
						 
						int index13_52 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_52=='e') ) {s = 98;}
						else if ( ((LA13_52 >= '0' && LA13_52 <= '9')||(LA13_52 >= 'A' && LA13_52 <= 'Z')||(LA13_52 >= 'a' && LA13_52 <= 'd')||(LA13_52 >= 'f' && LA13_52 <= 'z')) ) {s = 35;}
						else if ( (LA13_52=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 99;
						 
						input.seek(index13_52);
						if ( s>=0 ) return s;
						break;

					case 52 : 
						int LA13_98 = input.LA(1);
						 
						int index13_98 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_98=='s') ) {s = 145;}
						else if ( ((LA13_98 >= '0' && LA13_98 <= '9')||(LA13_98 >= 'A' && LA13_98 <= 'Z')||(LA13_98 >= 'a' && LA13_98 <= 'r')||(LA13_98 >= 't' && LA13_98 <= 'z')) ) {s = 35;}
						else if ( (LA13_98=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 146;
						 
						input.seek(index13_98);
						if ( s>=0 ) return s;
						break;

					case 53 : 
						int LA13_145 = input.LA(1);
						 
						int index13_145 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_145==' ') ) {s = 186;}
						else if ( ((LA13_145 >= '0' && LA13_145 <= '9')||(LA13_145 >= 'A' && LA13_145 <= 'Z')||(LA13_145 >= 'a' && LA13_145 <= 'z')) ) {s = 35;}
						else if ( (LA13_145=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 187;
						 
						input.seek(index13_145);
						if ( s>=0 ) return s;
						break;

					case 54 : 
						int LA13_19 = input.LA(1);
						 
						int index13_19 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_19=='s') ) {s = 53;}
						else if ( ((LA13_19 >= '0' && LA13_19 <= '9')||(LA13_19 >= 'A' && LA13_19 <= 'Z')||(LA13_19 >= 'a' && LA13_19 <= 'r')||(LA13_19 >= 't' && LA13_19 <= 'z')) ) {s = 35;}
						else if ( (LA13_19=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 36;
						 
						input.seek(index13_19);
						if ( s>=0 ) return s;
						break;

					case 55 : 
						int LA13_49 = input.LA(1);
						 
						int index13_49 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_49=='d') ) {s = 92;}
						else if ( ((LA13_49 >= '0' && LA13_49 <= '9')||(LA13_49 >= 'A' && LA13_49 <= 'Z')||(LA13_49 >= 'a' && LA13_49 <= 'c')||(LA13_49 >= 'e' && LA13_49 <= 'z')) ) {s = 35;}
						else if ( (LA13_49=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 93;
						 
						input.seek(index13_49);
						if ( s>=0 ) return s;
						break;

					case 56 : 
						int LA13_92 = input.LA(1);
						 
						int index13_92 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_92=='i') ) {s = 140;}
						else if ( ((LA13_92 >= '0' && LA13_92 <= '9')||(LA13_92 >= 'A' && LA13_92 <= 'Z')||(LA13_92 >= 'a' && LA13_92 <= 'h')||(LA13_92 >= 'j' && LA13_92 <= 'z')) ) {s = 35;}
						else if ( (LA13_92=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 141;
						 
						input.seek(index13_92);
						if ( s>=0 ) return s;
						break;

					case 57 : 
						int LA13_140 = input.LA(1);
						 
						int index13_140 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_140=='n') ) {s = 182;}
						else if ( ((LA13_140 >= '0' && LA13_140 <= '9')||(LA13_140 >= 'A' && LA13_140 <= 'Z')||(LA13_140 >= 'a' && LA13_140 <= 'm')||(LA13_140 >= 'o' && LA13_140 <= 'z')) ) {s = 35;}
						else if ( (LA13_140=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 183;
						 
						input.seek(index13_140);
						if ( s>=0 ) return s;
						break;

					case 58 : 
						int LA13_182 = input.LA(1);
						 
						int index13_182 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_182=='g') ) {s = 217;}
						else if ( ((LA13_182 >= '0' && LA13_182 <= '9')||(LA13_182 >= 'A' && LA13_182 <= 'Z')||(LA13_182 >= 'a' && LA13_182 <= 'f')||(LA13_182 >= 'h' && LA13_182 <= 'z')) ) {s = 35;}
						else if ( (LA13_182=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 218;
						 
						input.seek(index13_182);
						if ( s>=0 ) return s;
						break;

					case 59 : 
						int LA13_217 = input.LA(1);
						 
						int index13_217 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_217==' ') ) {s = 248;}
						else if ( ((LA13_217 >= '0' && LA13_217 <= '9')||(LA13_217 >= 'A' && LA13_217 <= 'Z')||(LA13_217 >= 'a' && LA13_217 <= 'z')) ) {s = 35;}
						else if ( (LA13_217=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 249;
						 
						input.seek(index13_217);
						if ( s>=0 ) return s;
						break;

					case 60 : 
						int LA13_43 = input.LA(1);
						 
						int index13_43 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_43=='e') ) {s = 80;}
						else if ( ((LA13_43 >= '0' && LA13_43 <= '9')||(LA13_43 >= 'A' && LA13_43 <= 'Z')||(LA13_43 >= 'a' && LA13_43 <= 'd')||(LA13_43 >= 'f' && LA13_43 <= 'z')) ) {s = 35;}
						else if ( (LA13_43=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 81;
						 
						input.seek(index13_43);
						if ( s>=0 ) return s;
						break;

					case 61 : 
						int LA13_80 = input.LA(1);
						 
						int index13_80 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_80=='n') ) {s = 129;}
						else if ( ((LA13_80 >= '0' && LA13_80 <= '9')||(LA13_80 >= 'A' && LA13_80 <= 'Z')||(LA13_80 >= 'a' && LA13_80 <= 'm')||(LA13_80 >= 'o' && LA13_80 <= 'z')) ) {s = 35;}
						else if ( (LA13_80=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 130;
						 
						input.seek(index13_80);
						if ( s>=0 ) return s;
						break;

					case 62 : 
						int LA13_21 = input.LA(1);
						 
						int index13_21 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_21=='e') ) {s = 56;}
						else if ( ((LA13_21 >= '0' && LA13_21 <= '9')||(LA13_21 >= 'A' && LA13_21 <= 'Z')||(LA13_21 >= 'a' && LA13_21 <= 'd')||(LA13_21 >= 'f' && LA13_21 <= 'z')) ) {s = 35;}
						else if ( (LA13_21=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 36;
						 
						input.seek(index13_21);
						if ( s>=0 ) return s;
						break;

					case 63 : 
						int LA13_48 = input.LA(1);
						 
						int index13_48 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_48=='o') ) {s = 90;}
						else if ( ((LA13_48 >= '0' && LA13_48 <= '9')||(LA13_48 >= 'A' && LA13_48 <= 'Z')||(LA13_48 >= 'a' && LA13_48 <= 'n')||(LA13_48 >= 'p' && LA13_48 <= 'z')) ) {s = 35;}
						else if ( (LA13_48=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 91;
						 
						input.seek(index13_48);
						if ( s>=0 ) return s;
						break;

					case 64 : 
						int LA13_90 = input.LA(1);
						 
						int index13_90 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_90=='s') ) {s = 138;}
						else if ( ((LA13_90 >= '0' && LA13_90 <= '9')||(LA13_90 >= 'A' && LA13_90 <= 'Z')||(LA13_90 >= 'a' && LA13_90 <= 'r')||(LA13_90 >= 't' && LA13_90 <= 'z')) ) {s = 35;}
						else if ( (LA13_90=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 139;
						 
						input.seek(index13_90);
						if ( s>=0 ) return s;
						break;

					case 65 : 
						int LA13_138 = input.LA(1);
						 
						int index13_138 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_138=='e') ) {s = 180;}
						else if ( ((LA13_138 >= '0' && LA13_138 <= '9')||(LA13_138 >= 'A' && LA13_138 <= 'Z')||(LA13_138 >= 'a' && LA13_138 <= 'd')||(LA13_138 >= 'f' && LA13_138 <= 'z')) ) {s = 35;}
						else if ( (LA13_138=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 181;
						 
						input.seek(index13_138);
						if ( s>=0 ) return s;
						break;

					case 66 : 
						int LA13_60 = input.LA(1);
						 
						int index13_60 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_60=='g') ) {s = 114;}
						else if ( ((LA13_60 >= '0' && LA13_60 <= '9')||(LA13_60 >= 'A' && LA13_60 <= 'Z')||(LA13_60 >= 'a' && LA13_60 <= 'f')||(LA13_60 >= 'h' && LA13_60 <= 'z')) ) {s = 35;}
						else if ( (LA13_60=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 115;
						 
						input.seek(index13_60);
						if ( s>=0 ) return s;
						break;

					case 67 : 
						int LA13_114 = input.LA(1);
						 
						int index13_114 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_114=='h') ) {s = 161;}
						else if ( ((LA13_114 >= '0' && LA13_114 <= '9')||(LA13_114 >= 'A' && LA13_114 <= 'Z')||(LA13_114 >= 'a' && LA13_114 <= 'g')||(LA13_114 >= 'i' && LA13_114 <= 'z')) ) {s = 35;}
						else if ( (LA13_114=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 162;
						 
						input.seek(index13_114);
						if ( s>=0 ) return s;
						break;

					case 68 : 
						int LA13_39 = input.LA(1);
						 
						int index13_39 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_39=='w') ) {s = 73;}
						else if ( ((LA13_39 >= '0' && LA13_39 <= '9')||(LA13_39 >= 'A' && LA13_39 <= 'Z')||(LA13_39 >= 'a' && LA13_39 <= 'v')||(LA13_39 >= 'x' && LA13_39 <= 'z')) ) {s = 35;}
						else if ( (LA13_39=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 74;
						 
						input.seek(index13_39);
						if ( s>=0 ) return s;
						break;

					case 69 : 
						int LA13_61 = input.LA(1);
						 
						int index13_61 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_61=='l') ) {s = 116;}
						else if ( ((LA13_61 >= '0' && LA13_61 <= '9')||(LA13_61 >= 'A' && LA13_61 <= 'Z')||(LA13_61 >= 'a' && LA13_61 <= 'k')||(LA13_61 >= 'm' && LA13_61 <= 'z')) ) {s = 35;}
						else if ( (LA13_61=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 117;
						 
						input.seek(index13_61);
						if ( s>=0 ) return s;
						break;

					case 70 : 
						int LA13_116 = input.LA(1);
						 
						int index13_116 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_116=='u') ) {s = 163;}
						else if ( ((LA13_116 >= '0' && LA13_116 <= '9')||(LA13_116 >= 'A' && LA13_116 <= 'Z')||(LA13_116 >= 'a' && LA13_116 <= 't')||(LA13_116 >= 'v' && LA13_116 <= 'z')) ) {s = 35;}
						else if ( (LA13_116=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 164;
						 
						input.seek(index13_116);
						if ( s>=0 ) return s;
						break;

					case 71 : 
						int LA13_163 = input.LA(1);
						 
						int index13_163 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_163=='m') ) {s = 204;}
						else if ( ((LA13_163 >= '0' && LA13_163 <= '9')||(LA13_163 >= 'A' && LA13_163 <= 'Z')||(LA13_163 >= 'a' && LA13_163 <= 'l')||(LA13_163 >= 'n' && LA13_163 <= 'z')) ) {s = 35;}
						else if ( (LA13_163=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 205;
						 
						input.seek(index13_163);
						if ( s>=0 ) return s;
						break;

					case 72 : 
						int LA13_204 = input.LA(1);
						 
						int index13_204 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_204=='e') ) {s = 236;}
						else if ( ((LA13_204 >= '0' && LA13_204 <= '9')||(LA13_204 >= 'A' && LA13_204 <= 'Z')||(LA13_204 >= 'a' && LA13_204 <= 'd')||(LA13_204 >= 'f' && LA13_204 <= 'z')) ) {s = 35;}
						else if ( (LA13_204=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 237;
						 
						input.seek(index13_204);
						if ( s>=0 ) return s;
						break;

					case 73 : 
						int LA13_5 = input.LA(1);
						 
						int index13_5 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_5=='e') ) {s = 38;}
						else if ( (LA13_5=='o') ) {s = 39;}
						else if ( ((LA13_5 >= '0' && LA13_5 <= '9')||(LA13_5 >= 'A' && LA13_5 <= 'Z')||(LA13_5 >= 'a' && LA13_5 <= 'd')||(LA13_5 >= 'f' && LA13_5 <= 'n')||(LA13_5 >= 'p' && LA13_5 <= 'z')) ) {s = 35;}
						else if ( (LA13_5=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 36;
						 
						input.seek(index13_5);
						if ( s>=0 ) return s;
						break;

					case 74 : 
						int LA13_42 = input.LA(1);
						 
						int index13_42 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_42=='e') ) {s = 78;}
						else if ( ((LA13_42 >= '0' && LA13_42 <= '9')||(LA13_42 >= 'A' && LA13_42 <= 'Z')||(LA13_42 >= 'a' && LA13_42 <= 'd')||(LA13_42 >= 'f' && LA13_42 <= 'z')) ) {s = 35;}
						else if ( (LA13_42=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 79;
						 
						input.seek(index13_42);
						if ( s>=0 ) return s;
						break;

					case 75 : 
						int LA13_78 = input.LA(1);
						 
						int index13_78 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_78=='r') ) {s = 127;}
						else if ( ((LA13_78 >= '0' && LA13_78 <= '9')||(LA13_78 >= 'A' && LA13_78 <= 'Z')||(LA13_78 >= 'a' && LA13_78 <= 'q')||(LA13_78 >= 's' && LA13_78 <= 'z')) ) {s = 35;}
						else if ( (LA13_78=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 128;
						 
						input.seek(index13_78);
						if ( s>=0 ) return s;
						break;

					case 76 : 
						int LA13_51 = input.LA(1);
						 
						int index13_51 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_51=='r') ) {s = 96;}
						else if ( ((LA13_51 >= '0' && LA13_51 <= '9')||(LA13_51 >= 'A' && LA13_51 <= 'Z')||(LA13_51 >= 'a' && LA13_51 <= 'q')||(LA13_51 >= 's' && LA13_51 <= 'z')) ) {s = 35;}
						else if ( (LA13_51=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 97;
						 
						input.seek(index13_51);
						if ( s>=0 ) return s;
						break;

					case 77 : 
						int LA13_28 = input.LA(1);
						 
						int index13_28 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_28 >= '0' && LA13_28 <= '9')||(LA13_28 >= 'A' && LA13_28 <= 'Z')||(LA13_28 >= 'a' && LA13_28 <= 'z')) ) {s = 35;}
						else if ( (LA13_28=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 36;
						 
						input.seek(index13_28);
						if ( s>=0 ) return s;
						break;

					case 78 : 
						int LA13_53 = input.LA(1);
						 
						int index13_53 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_53==' ') ) {s = 100;}
						else if ( ((LA13_53 >= '0' && LA13_53 <= '9')||(LA13_53 >= 'A' && LA13_53 <= 'Z')||(LA13_53 >= 'a' && LA13_53 <= 'z')) ) {s = 35;}
						else if ( (LA13_53=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 101;
						 
						input.seek(index13_53);
						if ( s>=0 ) return s;
						break;

					case 79 : 
						int LA13_79 = input.LA(1);
						 
						int index13_79 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_79);
						if ( s>=0 ) return s;
						break;

					case 80 : 
						int LA13_67 = input.LA(1);
						 
						int index13_67 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_67);
						if ( s>=0 ) return s;
						break;

					case 81 : 
						int LA13_65 = input.LA(1);
						 
						int index13_65 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_65);
						if ( s>=0 ) return s;
						break;

					case 82 : 
						int LA13_68 = input.LA(1);
						 
						int index13_68 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_68);
						if ( s>=0 ) return s;
						break;

					case 83 : 
						int LA13_183 = input.LA(1);
						 
						int index13_183 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_183);
						if ( s>=0 ) return s;
						break;

					case 84 : 
						int LA13_251 = input.LA(1);
						 
						int index13_251 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_251);
						if ( s>=0 ) return s;
						break;

					case 85 : 
						int LA13_135 = input.LA(1);
						 
						int index13_135 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_135);
						if ( s>=0 ) return s;
						break;

					case 86 : 
						int LA13_298 = input.LA(1);
						 
						int index13_298 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_298);
						if ( s>=0 ) return s;
						break;

					case 87 : 
						int LA13_194 = input.LA(1);
						 
						int index13_194 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_194);
						if ( s>=0 ) return s;
						break;

					case 88 : 
						int LA13_245 = input.LA(1);
						 
						int index13_245 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_245);
						if ( s>=0 ) return s;
						break;

					case 89 : 
						int LA13_162 = input.LA(1);
						 
						int index13_162 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_162);
						if ( s>=0 ) return s;
						break;

					case 90 : 
						int LA13_83 = input.LA(1);
						 
						int index13_83 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_83);
						if ( s>=0 ) return s;
						break;

					case 91 : 
						int LA13_89 = input.LA(1);
						 
						int index13_89 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_89);
						if ( s>=0 ) return s;
						break;

					case 92 : 
						int LA13_213 = input.LA(1);
						 
						int index13_213 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_213);
						if ( s>=0 ) return s;
						break;

					case 93 : 
						int LA13_152 = input.LA(1);
						 
						int index13_152 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_152);
						if ( s>=0 ) return s;
						break;

					case 94 : 
						int LA13_249 = input.LA(1);
						 
						int index13_249 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_249);
						if ( s>=0 ) return s;
						break;

					case 95 : 
						int LA13_93 = input.LA(1);
						 
						int index13_93 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_93);
						if ( s>=0 ) return s;
						break;

					case 96 : 
						int LA13_207 = input.LA(1);
						 
						int index13_207 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_207);
						if ( s>=0 ) return s;
						break;

					case 97 : 
						int LA13_187 = input.LA(1);
						 
						int index13_187 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_187);
						if ( s>=0 ) return s;
						break;

					case 98 : 
						int LA13_257 = input.LA(1);
						 
						int index13_257 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_257);
						if ( s>=0 ) return s;
						break;

					case 99 : 
						int LA13_105 = input.LA(1);
						 
						int index13_105 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_105);
						if ( s>=0 ) return s;
						break;

					case 100 : 
						int LA13_70 = input.LA(1);
						 
						int index13_70 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_70);
						if ( s>=0 ) return s;
						break;

					case 101 : 
						int LA13_97 = input.LA(1);
						 
						int index13_97 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_97);
						if ( s>=0 ) return s;
						break;

					case 102 : 
						int LA13_103 = input.LA(1);
						 
						int index13_103 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_103);
						if ( s>=0 ) return s;
						break;

					case 103 : 
						int LA13_169 = input.LA(1);
						 
						int index13_169 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_169);
						if ( s>=0 ) return s;
						break;

					case 104 : 
						int LA13_211 = input.LA(1);
						 
						int index13_211 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_211);
						if ( s>=0 ) return s;
						break;

					case 105 : 
						int LA13_122 = input.LA(1);
						 
						int index13_122 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_122);
						if ( s>=0 ) return s;
						break;

					case 106 : 
						int LA13_179 = input.LA(1);
						 
						int index13_179 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_179);
						if ( s>=0 ) return s;
						break;

					case 107 : 
						int LA13_239 = input.LA(1);
						 
						int index13_239 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_239);
						if ( s>=0 ) return s;
						break;

					case 108 : 
						int LA13_72 = input.LA(1);
						 
						int index13_72 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_72);
						if ( s>=0 ) return s;
						break;

					case 109 : 
						int LA13_74 = input.LA(1);
						 
						int index13_74 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_74);
						if ( s>=0 ) return s;
						break;

					case 110 : 
						int LA13_120 = input.LA(1);
						 
						int index13_120 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_120);
						if ( s>=0 ) return s;
						break;

					case 111 : 
						int LA13_177 = input.LA(1);
						 
						int index13_177 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_177);
						if ( s>=0 ) return s;
						break;

					case 112 : 
						int LA13_137 = input.LA(1);
						 
						int index13_137 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_137);
						if ( s>=0 ) return s;
						break;

					case 113 : 
						int LA13_263 = input.LA(1);
						 
						int index13_263 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_263);
						if ( s>=0 ) return s;
						break;

					case 114 : 
						int LA13_285 = input.LA(1);
						 
						int index13_285 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_285);
						if ( s>=0 ) return s;
						break;

					case 115 : 
						int LA13_200 = input.LA(1);
						 
						int index13_200 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_200);
						if ( s>=0 ) return s;
						break;

					case 116 : 
						int LA13_281 = input.LA(1);
						 
						int index13_281 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_281);
						if ( s>=0 ) return s;
						break;

					case 117 : 
						int LA13_283 = input.LA(1);
						 
						int index13_283 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_283);
						if ( s>=0 ) return s;
						break;

					case 118 : 
						int LA13_235 = input.LA(1);
						 
						int index13_235 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_235);
						if ( s>=0 ) return s;
						break;

					case 119 : 
						int LA13_109 = input.LA(1);
						 
						int index13_109 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_109);
						if ( s>=0 ) return s;
						break;

					case 120 : 
						int LA13_233 = input.LA(1);
						 
						int index13_233 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_233);
						if ( s>=0 ) return s;
						break;

					case 121 : 
						int LA13_76 = input.LA(1);
						 
						int index13_76 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_76);
						if ( s>=0 ) return s;
						break;

					case 122 : 
						int LA13_87 = input.LA(1);
						 
						int index13_87 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_87);
						if ( s>=0 ) return s;
						break;

					case 123 : 
						int LA13_115 = input.LA(1);
						 
						int index13_115 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_115);
						if ( s>=0 ) return s;
						break;

					case 124 : 
						int LA13_243 = input.LA(1);
						 
						int index13_243 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_243);
						if ( s>=0 ) return s;
						break;

					case 125 : 
						int LA13_117 = input.LA(1);
						 
						int index13_117 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_117);
						if ( s>=0 ) return s;
						break;

					case 126 : 
						int LA13_130 = input.LA(1);
						 
						int index13_130 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_130);
						if ( s>=0 ) return s;
						break;

					case 127 : 
						int LA13_259 = input.LA(1);
						 
						int index13_259 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_259);
						if ( s>=0 ) return s;
						break;

					case 128 : 
						int LA13_133 = input.LA(1);
						 
						int index13_133 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_133);
						if ( s>=0 ) return s;
						break;

					case 129 : 
						int LA13_241 = input.LA(1);
						 
						int index13_241 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_241);
						if ( s>=0 ) return s;
						break;

					case 130 : 
						int LA13_128 = input.LA(1);
						 
						int index13_128 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_128);
						if ( s>=0 ) return s;
						break;

					case 131 : 
						int LA13_198 = input.LA(1);
						 
						int index13_198 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_198);
						if ( s>=0 ) return s;
						break;

					case 132 : 
						int LA13_124 = input.LA(1);
						 
						int index13_124 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_124);
						if ( s>=0 ) return s;
						break;

					case 133 : 
						int LA13_113 = input.LA(1);
						 
						int index13_113 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_113);
						if ( s>=0 ) return s;
						break;

					case 134 : 
						int LA13_302 = input.LA(1);
						 
						int index13_302 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_302);
						if ( s>=0 ) return s;
						break;

					case 135 : 
						int LA13_218 = input.LA(1);
						 
						int index13_218 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_218);
						if ( s>=0 ) return s;
						break;

					case 136 : 
						int LA13_231 = input.LA(1);
						 
						int index13_231 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_231);
						if ( s>=0 ) return s;
						break;

					case 137 : 
						int LA13_175 = input.LA(1);
						 
						int index13_175 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_175);
						if ( s>=0 ) return s;
						break;

					case 138 : 
						int LA13_237 = input.LA(1);
						 
						int index13_237 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_237);
						if ( s>=0 ) return s;
						break;

					case 139 : 
						int LA13_267 = input.LA(1);
						 
						int index13_267 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_267);
						if ( s>=0 ) return s;
						break;

					case 140 : 
						int LA13_209 = input.LA(1);
						 
						int index13_209 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_209);
						if ( s>=0 ) return s;
						break;

					case 141 : 
						int LA13_158 = input.LA(1);
						 
						int index13_158 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_158);
						if ( s>=0 ) return s;
						break;

					case 142 : 
						int LA13_164 = input.LA(1);
						 
						int index13_164 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_164);
						if ( s>=0 ) return s;
						break;

					case 143 : 
						int LA13_215 = input.LA(1);
						 
						int index13_215 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_215);
						if ( s>=0 ) return s;
						break;

					case 144 : 
						int LA13_196 = input.LA(1);
						 
						int index13_196 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_196);
						if ( s>=0 ) return s;
						break;

					case 145 : 
						int LA13_227 = input.LA(1);
						 
						int index13_227 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_227);
						if ( s>=0 ) return s;
						break;

					case 146 : 
						int LA13_95 = input.LA(1);
						 
						int index13_95 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_95);
						if ( s>=0 ) return s;
						break;

					case 147 : 
						int LA13_107 = input.LA(1);
						 
						int index13_107 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_107);
						if ( s>=0 ) return s;
						break;

					case 148 : 
						int LA13_185 = input.LA(1);
						 
						int index13_185 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_185);
						if ( s>=0 ) return s;
						break;

					case 149 : 
						int LA13_202 = input.LA(1);
						 
						int index13_202 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_202);
						if ( s>=0 ) return s;
						break;

					case 150 : 
						int LA13_229 = input.LA(1);
						 
						int index13_229 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_229);
						if ( s>=0 ) return s;
						break;

					case 151 : 
						int LA13_139 = input.LA(1);
						 
						int index13_139 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_139);
						if ( s>=0 ) return s;
						break;

					case 152 : 
						int LA13_166 = input.LA(1);
						 
						int index13_166 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_166);
						if ( s>=0 ) return s;
						break;

					case 153 : 
						int LA13_101 = input.LA(1);
						 
						int index13_101 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_101);
						if ( s>=0 ) return s;
						break;

					case 154 : 
						int LA13_192 = input.LA(1);
						 
						int index13_192 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_192);
						if ( s>=0 ) return s;
						break;

					case 155 : 
						int LA13_154 = input.LA(1);
						 
						int index13_154 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_154);
						if ( s>=0 ) return s;
						break;

					case 156 : 
						int LA13_160 = input.LA(1);
						 
						int index13_160 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_160);
						if ( s>=0 ) return s;
						break;

					case 157 : 
						int LA13_271 = input.LA(1);
						 
						int index13_271 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_271);
						if ( s>=0 ) return s;
						break;

					case 158 : 
						int LA13_141 = input.LA(1);
						 
						int index13_141 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_141);
						if ( s>=0 ) return s;
						break;

					case 159 : 
						int LA13_143 = input.LA(1);
						 
						int index13_143 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_143);
						if ( s>=0 ) return s;
						break;

					case 160 : 
						int LA13_99 = input.LA(1);
						 
						int index13_99 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_99);
						if ( s>=0 ) return s;
						break;

					case 161 : 
						int LA13_81 = input.LA(1);
						 
						int index13_81 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_81);
						if ( s>=0 ) return s;
						break;

					case 162 : 
						int LA13_85 = input.LA(1);
						 
						int index13_85 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_85);
						if ( s>=0 ) return s;
						break;

					case 163 : 
						int LA13_111 = input.LA(1);
						 
						int index13_111 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_111);
						if ( s>=0 ) return s;
						break;

					case 164 : 
						int LA13_150 = input.LA(1);
						 
						int index13_150 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_150);
						if ( s>=0 ) return s;
						break;

					case 165 : 
						int LA13_205 = input.LA(1);
						 
						int index13_205 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_205);
						if ( s>=0 ) return s;
						break;

					case 166 : 
						int LA13_247 = input.LA(1);
						 
						int index13_247 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_247);
						if ( s>=0 ) return s;
						break;

					case 167 : 
						int LA13_279 = input.LA(1);
						 
						int index13_279 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_279);
						if ( s>=0 ) return s;
						break;

					case 168 : 
						int LA13_146 = input.LA(1);
						 
						int index13_146 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_146);
						if ( s>=0 ) return s;
						break;

					case 169 : 
						int LA13_287 = input.LA(1);
						 
						int index13_287 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_287);
						if ( s>=0 ) return s;
						break;

					case 170 : 
						int LA13_156 = input.LA(1);
						 
						int index13_156 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_156);
						if ( s>=0 ) return s;
						break;

					case 171 : 
						int LA13_181 = input.LA(1);
						 
						int index13_181 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_181);
						if ( s>=0 ) return s;
						break;

					case 172 : 
						int LA13_261 = input.LA(1);
						 
						int index13_261 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_261);
						if ( s>=0 ) return s;
						break;

					case 173 : 
						int LA13_220 = input.LA(1);
						 
						int index13_220 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_220);
						if ( s>=0 ) return s;
						break;

					case 174 : 
						int LA13_91 = input.LA(1);
						 
						int index13_91 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_91);
						if ( s>=0 ) return s;
						break;

					case 175 : 
						int LA13_54 = input.LA(1);
						 
						int index13_54 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_54=='k') ) {s = 102;}
						else if ( ((LA13_54 >= '0' && LA13_54 <= '9')||(LA13_54 >= 'A' && LA13_54 <= 'Z')||(LA13_54 >= 'a' && LA13_54 <= 'j')||(LA13_54 >= 'l' && LA13_54 <= 'z')) ) {s = 35;}
						else if ( (LA13_54=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 103;
						 
						input.seek(index13_54);
						if ( s>=0 ) return s;
						break;

					case 176 : 
						int LA13_102 = input.LA(1);
						 
						int index13_102 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_102=='e') ) {s = 149;}
						else if ( ((LA13_102 >= '0' && LA13_102 <= '9')||(LA13_102 >= 'A' && LA13_102 <= 'Z')||(LA13_102 >= 'a' && LA13_102 <= 'd')||(LA13_102 >= 'f' && LA13_102 <= 'z')) ) {s = 35;}
						else if ( (LA13_102=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 150;
						 
						input.seek(index13_102);
						if ( s>=0 ) return s;
						break;

					case 177 : 
						int LA13_149 = input.LA(1);
						 
						int index13_149 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_149=='s') ) {s = 191;}
						else if ( ((LA13_149 >= '0' && LA13_149 <= '9')||(LA13_149 >= 'A' && LA13_149 <= 'Z')||(LA13_149 >= 'a' && LA13_149 <= 'r')||(LA13_149 >= 't' && LA13_149 <= 'z')) ) {s = 35;}
						else if ( (LA13_149=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 192;
						 
						input.seek(index13_149);
						if ( s>=0 ) return s;
						break;

					case 178 : 
						int LA13_191 = input.LA(1);
						 
						int index13_191 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_191==' ') ) {s = 226;}
						else if ( ((LA13_191 >= '0' && LA13_191 <= '9')||(LA13_191 >= 'A' && LA13_191 <= 'Z')||(LA13_191 >= 'a' && LA13_191 <= 'z')) ) {s = 35;}
						else if ( (LA13_191=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 227;
						 
						input.seek(index13_191);
						if ( s>=0 ) return s;
						break;

					case 179 : 
						int LA13_56 = input.LA(1);
						 
						int index13_56 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_56=='v') ) {s = 106;}
						else if ( ((LA13_56 >= '0' && LA13_56 <= '9')||(LA13_56 >= 'A' && LA13_56 <= 'Z')||(LA13_56 >= 'a' && LA13_56 <= 'u')||(LA13_56 >= 'w' && LA13_56 <= 'z')) ) {s = 35;}
						else if ( (LA13_56=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 107;
						 
						input.seek(index13_56);
						if ( s>=0 ) return s;
						break;

					case 180 : 
						int LA13_106 = input.LA(1);
						 
						int index13_106 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_106=='e') ) {s = 153;}
						else if ( ((LA13_106 >= '0' && LA13_106 <= '9')||(LA13_106 >= 'A' && LA13_106 <= 'Z')||(LA13_106 >= 'a' && LA13_106 <= 'd')||(LA13_106 >= 'f' && LA13_106 <= 'z')) ) {s = 35;}
						else if ( (LA13_106=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 154;
						 
						input.seek(index13_106);
						if ( s>=0 ) return s;
						break;

					case 181 : 
						int LA13_153 = input.LA(1);
						 
						int index13_153 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_153=='r') ) {s = 195;}
						else if ( ((LA13_153 >= '0' && LA13_153 <= '9')||(LA13_153 >= 'A' && LA13_153 <= 'Z')||(LA13_153 >= 'a' && LA13_153 <= 'q')||(LA13_153 >= 's' && LA13_153 <= 'z')) ) {s = 35;}
						else if ( (LA13_153=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 196;
						 
						input.seek(index13_153);
						if ( s>=0 ) return s;
						break;

					case 182 : 
						int LA13_195 = input.LA(1);
						 
						int index13_195 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_195=='s') ) {s = 228;}
						else if ( ((LA13_195 >= '0' && LA13_195 <= '9')||(LA13_195 >= 'A' && LA13_195 <= 'Z')||(LA13_195 >= 'a' && LA13_195 <= 'r')||(LA13_195 >= 't' && LA13_195 <= 'z')) ) {s = 35;}
						else if ( (LA13_195=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 229;
						 
						input.seek(index13_195);
						if ( s>=0 ) return s;
						break;

					case 183 : 
						int LA13_228 = input.LA(1);
						 
						int index13_228 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_228=='e') ) {s = 256;}
						else if ( ((LA13_228 >= '0' && LA13_228 <= '9')||(LA13_228 >= 'A' && LA13_228 <= 'Z')||(LA13_228 >= 'a' && LA13_228 <= 'd')||(LA13_228 >= 'f' && LA13_228 <= 'z')) ) {s = 35;}
						else if ( (LA13_228=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 257;
						 
						input.seek(index13_228);
						if ( s>=0 ) return s;
						break;

					case 184 : 
						int LA13_256 = input.LA(1);
						 
						int index13_256 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_256=='s') ) {s = 278;}
						else if ( ((LA13_256 >= '0' && LA13_256 <= '9')||(LA13_256 >= 'A' && LA13_256 <= 'Z')||(LA13_256 >= 'a' && LA13_256 <= 'r')||(LA13_256 >= 't' && LA13_256 <= 'z')) ) {s = 35;}
						else if ( (LA13_256=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 279;
						 
						input.seek(index13_256);
						if ( s>=0 ) return s;
						break;

					case 185 : 
						int LA13_278 = input.LA(1);
						 
						int index13_278 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_278==' ') ) {s = 297;}
						else if ( ((LA13_278 >= '0' && LA13_278 <= '9')||(LA13_278 >= 'A' && LA13_278 <= 'Z')||(LA13_278 >= 'a' && LA13_278 <= 'z')) ) {s = 35;}
						else if ( (LA13_278=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 298;
						 
						input.seek(index13_278);
						if ( s>=0 ) return s;
						break;

					case 186 : 
						int LA13_20 = input.LA(1);
						 
						int index13_20 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_20=='a') ) {s = 54;}
						else if ( (LA13_20=='o') ) {s = 55;}
						else if ( ((LA13_20 >= '0' && LA13_20 <= '9')||(LA13_20 >= 'A' && LA13_20 <= 'Z')||(LA13_20 >= 'b' && LA13_20 <= 'n')||(LA13_20 >= 'p' && LA13_20 <= 'z')) ) {s = 35;}
						else if ( (LA13_20=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 36;
						 
						input.seek(index13_20);
						if ( s>=0 ) return s;
						break;

					case 187 : 
						int LA13_35 = input.LA(1);
						 
						int index13_35 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_35 >= '0' && LA13_35 <= '9')||(LA13_35 >= 'A' && LA13_35 <= 'Z')||(LA13_35 >= 'a' && LA13_35 <= 'z')) ) {s = 35;}
						else if ( (LA13_35=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 68;
						 
						input.seek(index13_35);
						if ( s>=0 ) return s;
						break;

					case 188 : 
						int LA13_47 = input.LA(1);
						 
						int index13_47 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_47=='o') ) {s = 88;}
						else if ( ((LA13_47 >= '0' && LA13_47 <= '9')||(LA13_47 >= 'A' && LA13_47 <= 'Z')||(LA13_47 >= 'a' && LA13_47 <= 'n')||(LA13_47 >= 'p' && LA13_47 <= 'z')) ) {s = 35;}
						else if ( (LA13_47=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 89;
						 
						input.seek(index13_47);
						if ( s>=0 ) return s;
						break;

					case 189 : 
						int LA13_88 = input.LA(1);
						 
						int index13_88 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_88=='s') ) {s = 136;}
						else if ( ((LA13_88 >= '0' && LA13_88 <= '9')||(LA13_88 >= 'A' && LA13_88 <= 'Z')||(LA13_88 >= 'a' && LA13_88 <= 'r')||(LA13_88 >= 't' && LA13_88 <= 'z')) ) {s = 35;}
						else if ( (LA13_88=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 137;
						 
						input.seek(index13_88);
						if ( s>=0 ) return s;
						break;

					case 190 : 
						int LA13_136 = input.LA(1);
						 
						int index13_136 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_136=='s') ) {s = 178;}
						else if ( ((LA13_136 >= '0' && LA13_136 <= '9')||(LA13_136 >= 'A' && LA13_136 <= 'Z')||(LA13_136 >= 'a' && LA13_136 <= 'r')||(LA13_136 >= 't' && LA13_136 <= 'z')) ) {s = 35;}
						else if ( (LA13_136=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 179;
						 
						input.seek(index13_136);
						if ( s>=0 ) return s;
						break;

					case 191 : 
						int LA13_178 = input.LA(1);
						 
						int index13_178 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_178=='e') ) {s = 214;}
						else if ( ((LA13_178 >= '0' && LA13_178 <= '9')||(LA13_178 >= 'A' && LA13_178 <= 'Z')||(LA13_178 >= 'a' && LA13_178 <= 'd')||(LA13_178 >= 'f' && LA13_178 <= 'z')) ) {s = 35;}
						else if ( (LA13_178=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 215;
						 
						input.seek(index13_178);
						if ( s>=0 ) return s;
						break;

					case 192 : 
						int LA13_4 = input.LA(1);
						 
						int index13_4 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_4=='a') ) {s = 37;}
						else if ( ((LA13_4 >= '0' && LA13_4 <= '9')||(LA13_4 >= 'A' && LA13_4 <= 'Z')||(LA13_4 >= 'b' && LA13_4 <= 'z')) ) {s = 35;}
						else if ( (LA13_4=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 36;
						 
						input.seek(index13_4);
						if ( s>=0 ) return s;
						break;

					case 193 : 
						int LA13_214 = input.LA(1);
						 
						int index13_214 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_214=='s') ) {s = 246;}
						else if ( ((LA13_214 >= '0' && LA13_214 <= '9')||(LA13_214 >= 'A' && LA13_214 <= 'Z')||(LA13_214 >= 'a' && LA13_214 <= 'r')||(LA13_214 >= 't' && LA13_214 <= 'z')) ) {s = 35;}
						else if ( (LA13_214=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 247;
						 
						input.seek(index13_214);
						if ( s>=0 ) return s;
						break;

					case 194 : 
						int LA13_246 = input.LA(1);
						 
						int index13_246 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_246==' ') ) {s = 270;}
						else if ( ((LA13_246 >= '0' && LA13_246 <= '9')||(LA13_246 >= 'A' && LA13_246 <= 'Z')||(LA13_246 >= 'a' && LA13_246 <= 'z')) ) {s = 35;}
						else if ( (LA13_246=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 271;
						 
						input.seek(index13_246);
						if ( s>=0 ) return s;
						break;

					case 195 : 
						int LA13_96 = input.LA(1);
						 
						int index13_96 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_96 >= '0' && LA13_96 <= '9')||(LA13_96 >= 'A' && LA13_96 <= 'Z')||(LA13_96 >= 'a' && LA13_96 <= 'z')) ) {s = 35;}
						else if ( (LA13_96=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 144;
						 
						input.seek(index13_96);
						if ( s>=0 ) return s;
						break;

					case 196 : 
						int LA13_6 = input.LA(1);
						 
						int index13_6 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_6=='o') ) {s = 40;}
						else if ( ((LA13_6 >= '0' && LA13_6 <= '9')||(LA13_6 >= 'A' && LA13_6 <= 'Z')||(LA13_6 >= 'a' && LA13_6 <= 'n')||(LA13_6 >= 'p' && LA13_6 <= 'z')) ) {s = 35;}
						else if ( (LA13_6=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 36;
						 
						input.seek(index13_6);
						if ( s>=0 ) return s;
						break;

					case 197 : 
						int LA13_50 = input.LA(1);
						 
						int index13_50 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_50=='u') ) {s = 94;}
						else if ( ((LA13_50 >= '0' && LA13_50 <= '9')||(LA13_50 >= 'A' && LA13_50 <= 'Z')||(LA13_50 >= 'a' && LA13_50 <= 't')||(LA13_50 >= 'v' && LA13_50 <= 'z')) ) {s = 35;}
						else if ( (LA13_50=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 95;
						 
						input.seek(index13_50);
						if ( s>=0 ) return s;
						break;

					case 198 : 
						int LA13_94 = input.LA(1);
						 
						int index13_94 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_94=='a') ) {s = 142;}
						else if ( ((LA13_94 >= '0' && LA13_94 <= '9')||(LA13_94 >= 'A' && LA13_94 <= 'Z')||(LA13_94 >= 'b' && LA13_94 <= 'z')) ) {s = 35;}
						else if ( (LA13_94=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 143;
						 
						input.seek(index13_94);
						if ( s>=0 ) return s;
						break;

					case 199 : 
						int LA13_129 = input.LA(1);
						 
						int index13_129 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_129 >= '0' && LA13_129 <= '9')||(LA13_129 >= 'A' && LA13_129 <= 'Z')||(LA13_129 >= 'a' && LA13_129 <= 'z')) ) {s = 35;}
						else if ( (LA13_129=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 173;
						 
						input.seek(index13_129);
						if ( s>=0 ) return s;
						break;

					case 200 : 
						int LA13_142 = input.LA(1);
						 
						int index13_142 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_142=='l') ) {s = 184;}
						else if ( ((LA13_142 >= '0' && LA13_142 <= '9')||(LA13_142 >= 'A' && LA13_142 <= 'Z')||(LA13_142 >= 'a' && LA13_142 <= 'k')||(LA13_142 >= 'm' && LA13_142 <= 'z')) ) {s = 35;}
						else if ( (LA13_142=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 185;
						 
						input.seek(index13_142);
						if ( s>=0 ) return s;
						break;

					case 201 : 
						int LA13_180 = input.LA(1);
						 
						int index13_180 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_180 >= '0' && LA13_180 <= '9')||(LA13_180 >= 'A' && LA13_180 <= 'Z')||(LA13_180 >= 'a' && LA13_180 <= 'z')) ) {s = 35;}
						else if ( (LA13_180=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 216;
						 
						input.seek(index13_180);
						if ( s>=0 ) return s;
						break;

					case 202 : 
						int LA13_184 = input.LA(1);
						 
						int index13_184 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_184=='s') ) {s = 219;}
						else if ( ((LA13_184 >= '0' && LA13_184 <= '9')||(LA13_184 >= 'A' && LA13_184 <= 'Z')||(LA13_184 >= 'a' && LA13_184 <= 'r')||(LA13_184 >= 't' && LA13_184 <= 'z')) ) {s = 35;}
						else if ( (LA13_184=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 220;
						 
						input.seek(index13_184);
						if ( s>=0 ) return s;
						break;

					case 203 : 
						int LA13_219 = input.LA(1);
						 
						int index13_219 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_219==' ') ) {s = 250;}
						else if ( ((LA13_219 >= '0' && LA13_219 <= '9')||(LA13_219 >= 'A' && LA13_219 <= 'Z')||(LA13_219 >= 'a' && LA13_219 <= 'z')) ) {s = 35;}
						else if ( (LA13_219=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 251;
						 
						input.seek(index13_219);
						if ( s>=0 ) return s;
						break;

					case 204 : 
						int LA13_161 = input.LA(1);
						 
						int index13_161 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_161 >= '0' && LA13_161 <= '9')||(LA13_161 >= 'A' && LA13_161 <= 'Z')||(LA13_161 >= 'a' && LA13_161 <= 'z')) ) {s = 35;}
						else if ( (LA13_161=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 203;
						 
						input.seek(index13_161);
						if ( s>=0 ) return s;
						break;

					case 205 : 
						int LA13_280 = input.LA(1);
						 
						int index13_280 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_280 >= '0' && LA13_280 <= '9')||(LA13_280 >= 'A' && LA13_280 <= 'Z')||(LA13_280 >= 'a' && LA13_280 <= 'z')) ) {s = 35;}
						else if ( (LA13_280=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 299;
						 
						input.seek(index13_280);
						if ( s>=0 ) return s;
						break;

					case 206 : 
						int LA13_73 = input.LA(1);
						 
						int index13_73 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_73 >= '0' && LA13_73 <= '9')||(LA13_73 >= 'A' && LA13_73 <= 'Z')||(LA13_73 >= 'a' && LA13_73 <= 'z')) ) {s = 35;}
						else if ( (LA13_73=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 125;
						 
						input.seek(index13_73);
						if ( s>=0 ) return s;
						break;

					case 207 : 
						int LA13_11 = input.LA(1);
						 
						int index13_11 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_11=='a') ) {s = 44;}
						else if ( ((LA13_11 >= '0' && LA13_11 <= '9')||(LA13_11 >= 'A' && LA13_11 <= 'Z')||(LA13_11 >= 'b' && LA13_11 <= 'z')) ) {s = 35;}
						else if ( (LA13_11=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 36;
						 
						input.seek(index13_11);
						if ( s>=0 ) return s;
						break;

					case 208 : 
						int LA13_236 = input.LA(1);
						 
						int index13_236 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_236 >= '0' && LA13_236 <= '9')||(LA13_236 >= 'A' && LA13_236 <= 'Z')||(LA13_236 >= 'a' && LA13_236 <= 'z')) ) {s = 35;}
						else if ( (LA13_236=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 264;
						 
						input.seek(index13_236);
						if ( s>=0 ) return s;
						break;

					case 209 : 
						int LA13_125 = input.LA(1);
						 
						int index13_125 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( ((runtimeHistoryOpAhead())) ) {s = 170;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_125);
						if ( s>=0 ) return s;
						break;

					case 210 : 
						int LA13_173 = input.LA(1);
						 
						int index13_173 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( ((runtimeHistoryOpAhead())) ) {s = 170;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_173);
						if ( s>=0 ) return s;
						break;

					case 211 : 
						int LA13_203 = input.LA(1);
						 
						int index13_203 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( ((runtimeHistoryOpAhead())) ) {s = 170;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_203);
						if ( s>=0 ) return s;
						break;

					case 212 : 
						int LA13_216 = input.LA(1);
						 
						int index13_216 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( ((runtimeHistoryOpAhead())) ) {s = 170;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_216);
						if ( s>=0 ) return s;
						break;

					case 213 : 
						int LA13_264 = input.LA(1);
						 
						int index13_264 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 30;}
						else if ( ((runtimeHistoryOpAhead())) ) {s = 170;}
						else if ( (true) ) {s = 36;}
						 
						input.seek(index13_264);
						if ( s>=0 ) return s;
						break;

					case 214 : 
						int LA13_282 = input.LA(1);
						 
						int index13_282 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_282 >= '0' && LA13_282 <= '9')||(LA13_282 >= 'A' && LA13_282 <= 'Z')||(LA13_282 >= 'a' && LA13_282 <= 'z')) ) {s = 35;}
						else if ( (LA13_282=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 300;
						 
						input.seek(index13_282);
						if ( s>=0 ) return s;
						break;

					case 215 : 
						int LA13_64 = input.LA(1);
						 
						int index13_64 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_64 >= '0' && LA13_64 <= '9')||(LA13_64 >= 'A' && LA13_64 <= 'Z')||(LA13_64 >= 'a' && LA13_64 <= 'z')) ) {s = 35;}
						else if ( (LA13_64=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 118;
						 
						input.seek(index13_64);
						if ( s>=0 ) return s;
						break;

					case 216 : 
						int LA13_121 = input.LA(1);
						 
						int index13_121 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_121 >= '0' && LA13_121 <= '9')||(LA13_121 >= 'A' && LA13_121 <= 'Z')||(LA13_121 >= 'a' && LA13_121 <= 'z')) ) {s = 35;}
						else if ( (LA13_121=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 167;
						 
						input.seek(index13_121);
						if ( s>=0 ) return s;
						break;

					case 217 : 
						int LA13_33 = input.LA(1);
						 
						int index13_33 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_33=='d') ) {s = 64;}
						else if ( ((LA13_33 >= '0' && LA13_33 <= '9')||(LA13_33 >= 'A' && LA13_33 <= 'Z')||(LA13_33 >= 'a' && LA13_33 <= 'c')||(LA13_33 >= 'e' && LA13_33 <= 'z')) ) {s = 35;}
						else if ( (LA13_33=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 65;
						 
						input.seek(index13_33);
						if ( s>=0 ) return s;
						break;

					case 218 : 
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
						else if ( (LA13_0==',') ) {s = 10;}
						else if ( (LA13_0=='N') ) {s = 11;}
						else if ( (LA13_0=='[') ) {s = 12;}
						else if ( (LA13_0==']') ) {s = 13;}
						else if ( (LA13_0=='b') ) {s = 14;}
						else if ( (LA13_0=='c') ) {s = 15;}
						else if ( (LA13_0=='e') ) {s = 16;}
						else if ( (LA13_0=='f') ) {s = 17;}
						else if ( (LA13_0=='g') ) {s = 18;}
						else if ( (LA13_0=='i') ) {s = 19;}
						else if ( (LA13_0=='m') ) {s = 20;}
						else if ( (LA13_0=='r') ) {s = 21;}
						else if ( (LA13_0=='s') ) {s = 22;}
						else if ( (LA13_0=='h') ) {s = 23;}
						else if ( (LA13_0=='-') ) {s = 24;}
						else if ( ((LA13_0 >= '0' && LA13_0 <= '9')) ) {s = 25;}
						else if ( (LA13_0=='\"') ) {s = 26;}
						else if ( (LA13_0=='v') ) {s = 27;}
						else if ( ((LA13_0 >= 'A' && LA13_0 <= 'M')||(LA13_0 >= 'O' && LA13_0 <= 'Z')||(LA13_0 >= 'j' && LA13_0 <= 'k')||(LA13_0 >= 'p' && LA13_0 <= 'q')||(LA13_0 >= 't' && LA13_0 <= 'u')||(LA13_0 >= 'w' && LA13_0 <= 'z')) ) {s = 28;}
						else if ( (LA13_0==' ') ) {s = 29;}
						else if ( (LA13_0=='_') && ((runtimeOpAhead()))) {s = 30;}
						else if ( ((LA13_0 >= '\t' && LA13_0 <= '\n')||(LA13_0 >= '\f' && LA13_0 <= '\r')) ) {s = 31;}
						else if ( (LA13_0=='/') ) {s = 32;}
						 
						input.seek(index13_0);
						if ( s>=0 ) return s;
						break;

					case 219 : 
						int LA13_37 = input.LA(1);
						 
						int index13_37 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_37=='y') ) {s = 69;}
						else if ( ((LA13_37 >= '0' && LA13_37 <= '9')||(LA13_37 >= 'A' && LA13_37 <= 'Z')||(LA13_37 >= 'a' && LA13_37 <= 'x')||LA13_37=='z') ) {s = 35;}
						else if ( (LA13_37=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 70;
						 
						input.seek(index13_37);
						if ( s>=0 ) return s;
						break;

					case 220 : 
						int LA13_69 = input.LA(1);
						 
						int index13_69 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_69=='s') ) {s = 121;}
						else if ( ((LA13_69 >= '0' && LA13_69 <= '9')||(LA13_69 >= 'A' && LA13_69 <= 'Z')||(LA13_69 >= 'a' && LA13_69 <= 'r')||(LA13_69 >= 't' && LA13_69 <= 'z')) ) {s = 35;}
						else if ( (LA13_69=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 122;
						 
						input.seek(index13_69);
						if ( s>=0 ) return s;
						break;

					case 221 : 
						int LA13_238 = input.LA(1);
						 
						int index13_238 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_238 >= '0' && LA13_238 <= '9')||(LA13_238 >= 'A' && LA13_238 <= 'Z')||(LA13_238 >= 'a' && LA13_238 <= 'z')) ) {s = 35;}
						else if ( (LA13_238=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 265;
						 
						input.seek(index13_238);
						if ( s>=0 ) return s;
						break;

					case 222 : 
						int LA13_38 = input.LA(1);
						 
						int index13_38 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_38=='n') ) {s = 71;}
						else if ( ((LA13_38 >= '0' && LA13_38 <= '9')||(LA13_38 >= 'A' && LA13_38 <= 'Z')||(LA13_38 >= 'a' && LA13_38 <= 'm')||(LA13_38 >= 'o' && LA13_38 <= 'z')) ) {s = 35;}
						else if ( (LA13_38=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 72;
						 
						input.seek(index13_38);
						if ( s>=0 ) return s;
						break;

					case 223 : 
						int LA13_71 = input.LA(1);
						 
						int index13_71 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_71=='i') ) {s = 123;}
						else if ( ((LA13_71 >= '0' && LA13_71 <= '9')||(LA13_71 >= 'A' && LA13_71 <= 'Z')||(LA13_71 >= 'a' && LA13_71 <= 'h')||(LA13_71 >= 'j' && LA13_71 <= 'z')) ) {s = 35;}
						else if ( (LA13_71=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 124;
						 
						input.seek(index13_71);
						if ( s>=0 ) return s;
						break;

					case 224 : 
						int LA13_123 = input.LA(1);
						 
						int index13_123 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_123=='e') ) {s = 168;}
						else if ( ((LA13_123 >= '0' && LA13_123 <= '9')||(LA13_123 >= 'A' && LA13_123 <= 'Z')||(LA13_123 >= 'a' && LA13_123 <= 'd')||(LA13_123 >= 'f' && LA13_123 <= 'z')) ) {s = 35;}
						else if ( (LA13_123=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 169;
						 
						input.seek(index13_123);
						if ( s>=0 ) return s;
						break;

					case 225 : 
						int LA13_168 = input.LA(1);
						 
						int index13_168 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_168=='n') ) {s = 206;}
						else if ( ((LA13_168 >= '0' && LA13_168 <= '9')||(LA13_168 >= 'A' && LA13_168 <= 'Z')||(LA13_168 >= 'a' && LA13_168 <= 'm')||(LA13_168 >= 'o' && LA13_168 <= 'z')) ) {s = 35;}
						else if ( (LA13_168=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 207;
						 
						input.seek(index13_168);
						if ( s>=0 ) return s;
						break;

					case 226 : 
						int LA13_206 = input.LA(1);
						 
						int index13_206 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_206=='t') ) {s = 238;}
						else if ( ((LA13_206 >= '0' && LA13_206 <= '9')||(LA13_206 >= 'A' && LA13_206 <= 'Z')||(LA13_206 >= 'a' && LA13_206 <= 's')||(LA13_206 >= 'u' && LA13_206 <= 'z')) ) {s = 35;}
						else if ( (LA13_206=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 239;
						 
						input.seek(index13_206);
						if ( s>=0 ) return s;
						break;

					case 227 : 
						int LA13_75 = input.LA(1);
						 
						int index13_75 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_75 >= '0' && LA13_75 <= '9')||(LA13_75 >= 'A' && LA13_75 <= 'Z')||(LA13_75 >= 'a' && LA13_75 <= 'z')) ) {s = 35;}
						else if ( (LA13_75=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 126;
						 
						input.seek(index13_75);
						if ( s>=0 ) return s;
						break;

					case 228 : 
						int LA13_127 = input.LA(1);
						 
						int index13_127 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_127=='r') ) {s = 171;}
						else if ( ((LA13_127 >= '0' && LA13_127 <= '9')||(LA13_127 >= 'A' && LA13_127 <= 'Z')||(LA13_127 >= 'a' && LA13_127 <= 'q')||(LA13_127 >= 's' && LA13_127 <= 'z')) ) {s = 35;}
						else if ( (LA13_127=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 172;
						 
						input.seek(index13_127);
						if ( s>=0 ) return s;
						break;

					case 229 : 
						int LA13_40 = input.LA(1);
						 
						int index13_40 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_40=='t') ) {s = 75;}
						else if ( ((LA13_40 >= '0' && LA13_40 <= '9')||(LA13_40 >= 'A' && LA13_40 <= 'Z')||(LA13_40 >= 'a' && LA13_40 <= 's')||(LA13_40 >= 'u' && LA13_40 <= 'z')) ) {s = 35;}
						else if ( (LA13_40=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 76;
						 
						input.seek(index13_40);
						if ( s>=0 ) return s;
						break;

					case 230 : 
						int LA13_22 = input.LA(1);
						 
						int index13_22 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_22=='m') ) {s = 57;}
						else if ( (LA13_22=='p') ) {s = 58;}
						else if ( (LA13_22=='t') ) {s = 59;}
						else if ( ((LA13_22 >= '0' && LA13_22 <= '9')||(LA13_22 >= 'A' && LA13_22 <= 'Z')||(LA13_22 >= 'a' && LA13_22 <= 'l')||(LA13_22 >= 'n' && LA13_22 <= 'o')||(LA13_22 >= 'q' && LA13_22 <= 's')||(LA13_22 >= 'u' && LA13_22 <= 'z')) ) {s = 35;}
						else if ( (LA13_22=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 36;
						 
						input.seek(index13_22);
						if ( s>=0 ) return s;
						break;

					case 231 : 
						int LA13_41 = input.LA(1);
						 
						int index13_41 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_41 >= '0' && LA13_41 <= '9')||(LA13_41 >= 'A' && LA13_41 <= 'Z')||(LA13_41 >= 'a' && LA13_41 <= 'z')) ) {s = 35;}
						else if ( (LA13_41=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 77;
						 
						input.seek(index13_41);
						if ( s>=0 ) return s;
						break;

					case 232 : 
						int LA13_44 = input.LA(1);
						 
						int index13_44 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_44=='N') ) {s = 82;}
						else if ( ((LA13_44 >= '0' && LA13_44 <= '9')||(LA13_44 >= 'A' && LA13_44 <= 'M')||(LA13_44 >= 'O' && LA13_44 <= 'Z')||(LA13_44 >= 'a' && LA13_44 <= 'z')) ) {s = 35;}
						else if ( (LA13_44=='_') && ((runtimeOpAhead()))) {s = 30;}
						else s = 83;
						 
						input.seek(index13_44);
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
