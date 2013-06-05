// $ANTLR 3.5 /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g 2013-06-04 00:11:44
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
	public static final int T__45=45;
	public static final int T__46=46;
	public static final int T__47=47;
	public static final int T__48=48;
	public static final int T__49=49;
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
	public static final int Double=14;
	public static final int DownRatioCondition=15;
	public static final int EqualConstantCondition=16;
	public static final int EqualDoubleMapCondition=17;
	public static final int EventConditionHolder=18;
	public static final int HigherHighCondition=19;
	public static final int HigherLowCondition=20;
	public static final int HistoricalData=21;
	public static final int InfConstantCondition=22;
	public static final int InfDoubleMapCondition=23;
	public static final int LINE_COMMENT=24;
	public static final int LowerHighCondition=25;
	public static final int LowerLowCondition=26;
	public static final int NOT=27;
	public static final int NotDoubleMapCondition=28;
	public static final int Number=29;
	public static final int OPENPARENTEHSIS=30;
	public static final int OR=31;
	public static final int Operation=32;
	public static final int OperationOutput=33;
	public static final int OrDoubleMapCondition=34;
	public static final int PERCENT=35;
	public static final int ReverseCondition=36;
	public static final int StockOperation=37;
	public static final int StringOperation=38;
	public static final int SupConstantCondition=39;
	public static final int SupDoubleMapCondition=40;
	public static final int Tcheat=41;
	public static final int UpRatioCondition=42;
	public static final int WS=43;
	public static final int WhiteChar=44;

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
	@Override public String getGrammarFileName() { return "/home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g"; }

	// $ANTLR start "AND"
	public final void mAND() throws RecognitionException {
		try {
			int _type = AND;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:49:5: ( 'and' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:49:7: 'and'
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
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:50:18: ( ')' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:50:20: ')'
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
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:51:7: ( ';' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:51:9: ';'
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
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:52:6: ( 'days' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:52:8: 'days'
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

	// $ANTLR start "NOT"
	public final void mNOT() throws RecognitionException {
		try {
			int _type = NOT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:53:5: ( 'not' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:53:7: 'not'
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
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:54:17: ( '(' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:54:19: '('
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
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:55:4: ( 'or' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:55:6: 'or'
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
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:56:9: ( '%' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:56:11: '%'
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

	// $ANTLR start "T__45"
	public final void mT__45() throws RecognitionException {
		try {
			int _type = T__45;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:57:7: ( 'crosses down historical' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:57:9: 'crosses down historical'
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
	// $ANTLR end "T__45"

	// $ANTLR start "T__46"
	public final void mT__46() throws RecognitionException {
		try {
			int _type = T__46;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:58:7: ( 'crosses down threshold' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:58:9: 'crosses down threshold'
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
	// $ANTLR end "T__46"

	// $ANTLR start "T__47"
	public final void mT__47() throws RecognitionException {
		try {
			int _type = T__47;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:59:7: ( 'crosses up historical' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:59:9: 'crosses up historical'
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
	// $ANTLR end "T__47"

	// $ANTLR start "T__48"
	public final void mT__48() throws RecognitionException {
		try {
			int _type = T__48;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:60:7: ( 'crosses up threshold' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:60:9: 'crosses up threshold'
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
	// $ANTLR end "T__48"

	// $ANTLR start "T__49"
	public final void mT__49() throws RecognitionException {
		try {
			int _type = T__49;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:61:7: ( 'equals historical' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:61:9: 'equals historical'
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
	// $ANTLR end "T__49"

	// $ANTLR start "T__50"
	public final void mT__50() throws RecognitionException {
		try {
			int _type = T__50;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:62:7: ( 'equals threshold' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:62:9: 'equals threshold'
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
	// $ANTLR end "T__50"

	// $ANTLR start "T__51"
	public final void mT__51() throws RecognitionException {
		try {
			int _type = T__51;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:63:7: ( 'goes down more than' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:63:9: 'goes down more than'
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
	// $ANTLR end "T__51"

	// $ANTLR start "T__52"
	public final void mT__52() throws RecognitionException {
		try {
			int _type = T__52;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:64:7: ( 'goes up more than' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:64:9: 'goes up more than'
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
	// $ANTLR end "T__52"

	// $ANTLR start "T__53"
	public final void mT__53() throws RecognitionException {
		try {
			int _type = T__53;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:65:7: ( 'is above historical' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:65:9: 'is above historical'
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
	// $ANTLR end "T__53"

	// $ANTLR start "T__54"
	public final void mT__54() throws RecognitionException {
		try {
			int _type = T__54;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:66:7: ( 'is above threshold' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:66:9: 'is above threshold'
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
	// $ANTLR end "T__54"

	// $ANTLR start "T__55"
	public final void mT__55() throws RecognitionException {
		try {
			int _type = T__55;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:67:7: ( 'is bearish if not bullish' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:67:9: 'is bearish if not bullish'
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
	// $ANTLR end "T__55"

	// $ANTLR start "T__56"
	public final void mT__56() throws RecognitionException {
		try {
			int _type = T__56;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:68:7: ( 'is bearish when' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:68:9: 'is bearish when'
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
	// $ANTLR end "T__56"

	// $ANTLR start "T__57"
	public final void mT__57() throws RecognitionException {
		try {
			int _type = T__57;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:69:7: ( 'is below historical' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:69:9: 'is below historical'
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
	// $ANTLR end "T__57"

	// $ANTLR start "T__58"
	public final void mT__58() throws RecognitionException {
		try {
			int _type = T__58;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:70:7: ( 'is below threshold' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:70:9: 'is below threshold'
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
	// $ANTLR end "T__58"

	// $ANTLR start "T__59"
	public final void mT__59() throws RecognitionException {
		try {
			int _type = T__59;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:71:7: ( 'is bullish when' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:71:9: 'is bullish when'
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
	// $ANTLR end "T__59"

	// $ANTLR start "T__60"
	public final void mT__60() throws RecognitionException {
		try {
			int _type = T__60;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:72:7: ( 'makes a higher high over' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:72:9: 'makes a higher high over'
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
	// $ANTLR end "T__60"

	// $ANTLR start "T__61"
	public final void mT__61() throws RecognitionException {
		try {
			int _type = T__61;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:73:7: ( 'makes a higher low over' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:73:9: 'makes a higher low over'
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
	// $ANTLR end "T__61"

	// $ANTLR start "T__62"
	public final void mT__62() throws RecognitionException {
		try {
			int _type = T__62;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:74:7: ( 'makes a lower high over' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:74:9: 'makes a lower high over'
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
	// $ANTLR end "T__62"

	// $ANTLR start "T__63"
	public final void mT__63() throws RecognitionException {
		try {
			int _type = T__63;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:75:7: ( 'makes a lower low over' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:75:9: 'makes a lower low over'
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
	// $ANTLR end "T__63"

	// $ANTLR start "T__64"
	public final void mT__64() throws RecognitionException {
		try {
			int _type = T__64;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:76:7: ( 'more than' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:76:9: 'more than'
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
	// $ANTLR end "T__64"

	// $ANTLR start "T__65"
	public final void mT__65() throws RecognitionException {
		try {
			int _type = T__65;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:77:7: ( 'over' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:77:9: 'over'
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
	// $ANTLR end "T__65"

	// $ANTLR start "T__66"
	public final void mT__66() throws RecognitionException {
		try {
			int _type = T__66;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:78:7: ( 'reverses down' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:78:9: 'reverses down'
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
	// $ANTLR end "T__66"

	// $ANTLR start "T__67"
	public final void mT__67() throws RecognitionException {
		try {
			int _type = T__67;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:79:7: ( 'reverses up' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:79:9: 'reverses up'
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
	// $ANTLR end "T__67"

	// $ANTLR start "T__68"
	public final void mT__68() throws RecognitionException {
		try {
			int _type = T__68;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:80:7: ( 'smoothing threshold' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:80:9: 'smoothing threshold'
			{
			match("smoothing threshold"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__68"

	// $ANTLR start "Operation"
	public final void mOperation() throws RecognitionException {
		try {
			int _type = Operation;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:210:7: ({...}? => ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )+ )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:210:9: {...}? => ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )+
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
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:210:60: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )+
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
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:
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

	// $ANTLR start "Number"
	public final void mNumber() throws RecognitionException {
		try {
			int _type = Number;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:214:7: ( ( '-' )? ( '0' .. '9' )+ ( '.' ( '0' .. '9' )+ )? )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:214:10: ( '-' )? ( '0' .. '9' )+ ( '.' ( '0' .. '9' )+ )?
			{
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:214:10: ( '-' )?
			int alt2=2;
			int LA2_0 = input.LA(1);
			if ( (LA2_0=='-') ) {
				alt2=1;
			}
			switch (alt2) {
				case 1 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:214:11: '-'
					{
					match('-'); 
					}
					break;

			}

			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:214:17: ( '0' .. '9' )+
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
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:
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

			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:214:29: ( '.' ( '0' .. '9' )+ )?
			int alt5=2;
			int LA5_0 = input.LA(1);
			if ( (LA5_0=='.') ) {
				alt5=1;
			}
			switch (alt5) {
				case 1 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:214:30: '.' ( '0' .. '9' )+
					{
					match('.'); 
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:214:34: ( '0' .. '9' )+
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
							// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:
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
	// $ANTLR end "Number"

	// $ANTLR start "HistoricalData"
	public final void mHistoricalData() throws RecognitionException {
		try {
			int _type = HistoricalData;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:218:4: ({...}? => ( 'open' | 'close' | 'high' | 'low' | 'volume' ) )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:218:6: {...}? => ( 'open' | 'close' | 'high' | 'low' | 'volume' )
			{
			if ( !((runtimeHistoryOpAhead())) ) {
				throw new FailedPredicateException(input, "HistoricalData", "runtimeHistoryOpAhead()");
			}
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:218:36: ( 'open' | 'close' | 'high' | 'low' | 'volume' )
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
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:218:38: 'open'
					{
					match("open"); 

					}
					break;
				case 2 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:218:47: 'close'
					{
					match("close"); 

					}
					break;
				case 3 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:218:57: 'high'
					{
					match("high"); 

					}
					break;
				case 4 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:218:66: 'low'
					{
					match("low"); 

					}
					break;
				case 5 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:218:75: 'volume'
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
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:222:7: ( ( ( ' ' )+ ) )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:222:9: ( ( ' ' )+ )
			{
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:222:9: ( ( ' ' )+ )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:222:10: ( ' ' )+
			{
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:222:10: ( ' ' )+
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
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:222:10: ' '
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
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:226:6: ( ( 'a' .. 'z' | 'A' .. 'Z' )+ )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:226:8: ( 'a' .. 'z' | 'A' .. 'Z' )+
			{
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:226:8: ( 'a' .. 'z' | 'A' .. 'Z' )+
			int cnt8=0;
			loop8:
			while (true) {
				int alt8=2;
				int LA8_0 = input.LA(1);
				if ( ((LA8_0 >= 'A' && LA8_0 <= 'Z')||(LA8_0 >= 'a' && LA8_0 <= 'z')) ) {
					alt8=1;
				}

				switch (alt8) {
				case 1 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:
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
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:231:5: ( ( '\\r' | '\\t' | '\\u000C' | '\\n' ) )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:231:7: ( '\\r' | '\\t' | '\\u000C' | '\\n' )
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
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:234:5: ( '/*' ( . )* '*/' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:234:7: '/*' ( . )* '*/'
			{
			match("/*"); 

			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:234:12: ( . )*
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
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:234:12: .
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
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:237:5: ( '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:237:7: '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n'
			{
			match("//"); 

			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:237:12: (~ ( '\\n' | '\\r' ) )*
			loop10:
			while (true) {
				int alt10=2;
				int LA10_0 = input.LA(1);
				if ( ((LA10_0 >= '\u0000' && LA10_0 <= '\t')||(LA10_0 >= '\u000B' && LA10_0 <= '\f')||(LA10_0 >= '\u000E' && LA10_0 <= '\uFFFF')) ) {
					alt10=1;
				}

				switch (alt10) {
				case 1 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:
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

			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:237:26: ( '\\r' )?
			int alt11=2;
			int LA11_0 = input.LA(1);
			if ( (LA11_0=='\r') ) {
				alt11=1;
			}
			switch (alt11) {
				case 1 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:237:26: '\\r'
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
		// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:8: ( AND | CLOSEPARENTEHSIS | COMMA | DAYS | NOT | OPENPARENTEHSIS | OR | PERCENT | T__45 | T__46 | T__47 | T__48 | T__49 | T__50 | T__51 | T__52 | T__53 | T__54 | T__55 | T__56 | T__57 | T__58 | T__59 | T__60 | T__61 | T__62 | T__63 | T__64 | T__65 | T__66 | T__67 | T__68 | Operation | Number | HistoricalData | WhiteChar | Tcheat | WS | COMMENT | LINE_COMMENT )
		int alt12=40;
		alt12 = dfa12.predict(input);
		switch (alt12) {
			case 1 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:10: AND
				{
				mAND(); 

				}
				break;
			case 2 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:14: CLOSEPARENTEHSIS
				{
				mCLOSEPARENTEHSIS(); 

				}
				break;
			case 3 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:31: COMMA
				{
				mCOMMA(); 

				}
				break;
			case 4 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:37: DAYS
				{
				mDAYS(); 

				}
				break;
			case 5 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:42: NOT
				{
				mNOT(); 

				}
				break;
			case 6 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:46: OPENPARENTEHSIS
				{
				mOPENPARENTEHSIS(); 

				}
				break;
			case 7 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:62: OR
				{
				mOR(); 

				}
				break;
			case 8 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:65: PERCENT
				{
				mPERCENT(); 

				}
				break;
			case 9 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:73: T__45
				{
				mT__45(); 

				}
				break;
			case 10 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:79: T__46
				{
				mT__46(); 

				}
				break;
			case 11 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:85: T__47
				{
				mT__47(); 

				}
				break;
			case 12 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:91: T__48
				{
				mT__48(); 

				}
				break;
			case 13 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:97: T__49
				{
				mT__49(); 

				}
				break;
			case 14 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:103: T__50
				{
				mT__50(); 

				}
				break;
			case 15 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:109: T__51
				{
				mT__51(); 

				}
				break;
			case 16 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:115: T__52
				{
				mT__52(); 

				}
				break;
			case 17 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:121: T__53
				{
				mT__53(); 

				}
				break;
			case 18 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:127: T__54
				{
				mT__54(); 

				}
				break;
			case 19 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:133: T__55
				{
				mT__55(); 

				}
				break;
			case 20 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:139: T__56
				{
				mT__56(); 

				}
				break;
			case 21 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:145: T__57
				{
				mT__57(); 

				}
				break;
			case 22 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:151: T__58
				{
				mT__58(); 

				}
				break;
			case 23 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:157: T__59
				{
				mT__59(); 

				}
				break;
			case 24 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:163: T__60
				{
				mT__60(); 

				}
				break;
			case 25 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:169: T__61
				{
				mT__61(); 

				}
				break;
			case 26 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:175: T__62
				{
				mT__62(); 

				}
				break;
			case 27 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:181: T__63
				{
				mT__63(); 

				}
				break;
			case 28 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:187: T__64
				{
				mT__64(); 

				}
				break;
			case 29 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:193: T__65
				{
				mT__65(); 

				}
				break;
			case 30 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:199: T__66
				{
				mT__66(); 

				}
				break;
			case 31 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:205: T__67
				{
				mT__67(); 

				}
				break;
			case 32 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:211: T__68
				{
				mT__68(); 

				}
				break;
			case 33 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:217: Operation
				{
				mOperation(); 

				}
				break;
			case 34 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:227: Number
				{
				mNumber(); 

				}
				break;
			case 35 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:234: HistoricalData
				{
				mHistoricalData(); 

				}
				break;
			case 36 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:249: WhiteChar
				{
				mWhiteChar(); 

				}
				break;
			case 37 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:259: Tcheat
				{
				mTcheat(); 

				}
				break;
			case 38 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:266: WS
				{
				mWS(); 

				}
				break;
			case 39 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:269: COMMENT
				{
				mCOMMENT(); 

				}
				break;
			case 40 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:277: LINE_COMMENT
				{
				mLINE_COMMENT(); 

				}
				break;

		}
	}


	protected DFA12 dfa12 = new DFA12(this);
	static final String DFA12_eotS =
		"\1\uffff\1\33\2\uffff\2\33\1\uffff\1\33\1\uffff\10\33\1\uffff\3\33\4\uffff"+
		"\1\60\1\61\1\uffff\1\63\1\65\1\66\1\70\1\72\1\74\1\76\1\100\1\102\1\104"+
		"\1\106\1\110\1\112\1\114\1\116\1\120\1\122\2\uffff\1\123\2\uffff\1\125"+
		"\1\uffff\1\126\2\uffff\1\130\1\uffff\1\132\1\uffff\1\134\1\uffff\1\136"+
		"\1\uffff\1\140\1\uffff\1\142\3\uffff\1\146\1\uffff\1\150\1\uffff\1\152"+
		"\1\uffff\1\154\1\uffff\1\156\1\uffff\1\157\1\uffff\1\161\2\uffff\1\162"+
		"\2\uffff\1\163\1\uffff\1\164\1\uffff\1\166\1\uffff\1\170\1\uffff\1\172"+
		"\1\uffff\1\174\3\uffff\1\u0081\1\uffff\1\u0083\1\uffff\1\u0085\1\uffff"+
		"\1\u0087\1\uffff\1\u0088\2\uffff\1\u008b\4\uffff\1\u008d\1\uffff\1\u008e"+
		"\1\uffff\1\u0090\6\uffff\1\u0097\3\uffff\1\u0099\1\uffff\1\u009b\3\uffff"+
		"\1\u009d\1\uffff\1\u009f\2\uffff\1\u00a1\10\uffff\1\u00a7\1\uffff\1\u00a9"+
		"\1\uffff\1\u00aa\1\uffff\1\u00ac\7\uffff\1\u00b4\1\uffff\1\u00b6\12\uffff"+
		"\1\u00bf\1\uffff\1\u00c1\12\uffff\1\u00ce\46\uffff";
	static final String DFA12_eofS =
		"\u00e7\uffff";
	static final String DFA12_minS =
		"\1\11\1\60\2\uffff\2\60\1\uffff\1\60\1\uffff\10\60\1\uffff\3\60\3\uffff"+
		"\1\52\2\60\1\uffff\11\60\1\40\7\60\2\uffff\1\60\2\0\1\60\1\0\1\60\1\0"+
		"\1\uffff\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\141\1"+
		"\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\uffff"+
		"\1\60\1\0\1\uffff\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\40\1"+
		"\0\1\142\1\145\1\60\1\0\1\40\1\0\1\60\1\0\1\60\1\0\1\60\2\0\1\60\1\0\2"+
		"\uffff\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\144\1\0\1\157\1\141\1\uffff\1"+
		"\40\1\0\1\uffff\1\0\1\60\1\0\1\60\2\0\1\uffff\1\60\1\0\1\60\2\0\1\40\1"+
		"\0\2\uffff\1\166\1\162\1\157\1\141\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\40"+
		"\1\0\1\150\1\0\1\145\1\151\1\167\1\40\1\60\1\0\1\60\2\0\1\144\1\0\2\uffff"+
		"\1\40\1\163\1\40\1\150\1\40\1\0\1\60\1\0\1\157\1\160\3\150\1\151\1\157"+
		"\1\144\1\0\1\40\1\0\1\167\1\40\2\uffff\1\40\2\uffff\1\147\1\167\3\uffff"+
		"\1\0\1\156\1\150\1\151\1\150\1\145\1\40\4\uffff\1\145\1\162\1\150\1\162"+
		"\1\40\2\uffff\1\40\2\150\4\uffff";
	static final String DFA12_maxS =
		"\2\172\2\uffff\2\172\1\uffff\1\172\1\uffff\10\172\1\uffff\3\172\3\uffff"+
		"\1\57\2\172\1\uffff\21\172\2\uffff\1\172\2\0\1\172\1\0\1\172\1\0\1\uffff"+
		"\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\142\1\0"+
		"\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0"+
		"\1\uffff\1\172\1\0\1\uffff\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172"+
		"\1\0\1\172\1\0\1\142\1\165\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172"+
		"\2\0\1\172\1\0\2\uffff\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\165\1\0\1\157"+
		"\1\154\1\uffff\1\172\1\0\1\uffff\1\0\1\172\1\0\1\172\2\0\1\uffff\1\172"+
		"\1\0\1\172\2\0\1\172\1\0\2\uffff\1\166\1\162\1\157\1\141\1\0\1\172\1\0"+
		"\1\172\1\0\1\172\1\0\1\172\1\0\1\164\1\0\1\145\1\151\1\167\1\40\1\172"+
		"\1\0\1\172\2\0\1\165\1\0\2\uffff\1\40\1\163\1\40\1\154\1\172\1\0\1\172"+
		"\1\0\1\157\1\160\1\164\1\150\1\164\1\151\1\157\1\165\1\0\1\172\1\0\1\167"+
		"\1\40\2\uffff\1\40\2\uffff\1\147\1\167\3\uffff\1\0\1\156\1\164\1\167\1"+
		"\150\1\145\1\40\4\uffff\1\145\1\162\1\164\1\162\1\40\2\uffff\1\40\2\154"+
		"\4\uffff";
	static final String DFA12_acceptS =
		"\2\uffff\1\2\1\3\2\uffff\1\6\1\uffff\1\10\10\uffff\1\42\3\uffff\1\44\1"+
		"\41\1\46\3\uffff\1\45\21\uffff\1\47\1\50\7\uffff\1\7\34\uffff\1\1\2\uffff"+
		"\1\5\33\uffff\1\4\1\35\13\uffff\1\27\2\uffff\1\34\6\uffff\1\43\7\uffff"+
		"\1\17\1\20\32\uffff\1\15\1\16\25\uffff\1\21\1\22\1\uffff\1\25\1\26\2\uffff"+
		"\1\36\1\37\1\40\7\uffff\1\13\1\14\1\23\1\24\5\uffff\1\11\1\12\3\uffff"+
		"\1\32\1\33\1\30\1\31";
	static final String DFA12_specialS =
		"\1\47\1\175\2\uffff\1\171\1\164\1\uffff\1\41\1\uffff\1\50\1\12\1\31\1"+
		"\161\1\u0081\1\154\1\u0083\1\160\1\uffff\1\157\1\155\1\30\4\uffff\1\170"+
		"\1\u0087\1\uffff\1\162\1\156\1\33\1\u0084\1\7\1\27\1\4\1\55\1\176\1\51"+
		"\1\u0085\1\166\1\36\1\u0086\1\2\1\24\1\21\2\uffff\1\42\1\113\1\112\1\163"+
		"\1\111\1\34\1\110\1\uffff\1\u0082\1\107\1\11\1\106\1\26\1\105\1\5\1\104"+
		"\1\53\1\103\1\u0080\1\102\1\uffff\1\101\1\165\1\100\1\167\1\77\1\40\1"+
		"\76\1\u008e\1\75\1\0\1\74\1\60\1\73\1\20\1\72\1\uffff\1\35\1\71\1\uffff"+
		"\1\32\1\70\1\52\1\67\1\25\1\66\1\6\1\65\1\54\1\64\1\177\1\114\2\uffff"+
		"\1\172\1\115\1\173\1\116\1\37\1\117\1\u008d\1\120\1\57\1\121\1\13\1\23"+
		"\1\122\2\uffff\1\14\1\10\1\123\1\56\1\124\1\153\1\125\1\uffff\1\126\3"+
		"\uffff\1\174\1\127\1\uffff\1\130\1\43\1\131\1\u008c\1\132\1\15\1\uffff"+
		"\1\22\1\133\1\3\1\134\1\16\1\152\1\135\6\uffff\1\136\1\44\1\137\1\u008b"+
		"\1\140\1\61\1\141\1\1\1\142\1\uffff\1\143\4\uffff\1\45\1\144\1\u008a\1"+
		"\145\1\17\1\uffff\1\146\6\uffff\1\46\1\147\1\u0089\1\150\10\uffff\1\151"+
		"\1\u0088\1\63\14\uffff\1\62\30\uffff}>";
	static final String[] DFA12_transitionS = {
			"\2\27\1\uffff\2\27\22\uffff\1\25\4\uffff\1\10\2\uffff\1\6\1\2\3\uffff"+
			"\1\21\1\uffff\1\30\12\21\1\uffff\1\3\5\uffff\32\24\4\uffff\1\26\1\uffff"+
			"\1\1\1\24\1\11\1\4\1\12\1\24\1\13\1\20\1\14\2\24\1\22\1\15\1\5\1\7\2"+
			"\24\1\16\1\17\2\24\1\23\4\24",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\15\32\1\31\14\32",
			"",
			"",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\1\34\31\32",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\16\32\1\35\13\32",
			"",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\17\32\1\40\1\32\1\36\3\32"+
			"\1\37\4\32",
			"",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\13\32\1\42\5\32\1\41\10\32",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\20\32\1\43\11\32",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\16\32\1\44\13\32",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\22\32\1\45\7\32",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\1\46\15\32\1\47\13\32",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\4\32\1\50\25\32",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\14\32\1\51\15\32",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\10\32\1\52\21\32",
			"",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\16\32\1\53\13\32",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\16\32\1\54\13\32",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\32\32",
			"",
			"",
			"",
			"\1\55\4\uffff\1\56",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\3\32\1\57\26\32",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\32\32",
			"",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\30\32\1\62\1\32",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\23\32\1\64\6\32",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\32\32",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\4\32\1\67\25\32",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\4\32\1\71\25\32",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\16\32\1\73\13\32",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\16\32\1\75\13\32",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\24\32\1\77\5\32",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\4\32\1\101\25\32",
			"\1\103\17\uffff\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\32\32",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\12\32\1\105\17\32",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\21\32\1\107\10\32",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\25\32\1\111\4\32",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\16\32\1\113\13\32",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\6\32\1\115\23\32",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\26\32\1\117\3\32",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\13\32\1\121\16\32",
			"",
			"",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\32\32",
			"\1\uffff",
			"\1\uffff",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\22\32\1\124\7\32",
			"\1\uffff",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\32\32",
			"\1\uffff",
			"",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\21\32\1\127\10\32",
			"\1\uffff",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\15\32\1\131\14\32",
			"\1\uffff",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\22\32\1\133\7\32",
			"\1\uffff",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\22\32\1\135\7\32",
			"\1\uffff",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\1\137\31\32",
			"\1\uffff",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\22\32\1\141\7\32",
			"\1\uffff",
			"\1\143\1\144",
			"\1\uffff",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\4\32\1\145\25\32",
			"\1\uffff",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\4\32\1\147\25\32",
			"\1\uffff",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\4\32\1\151\25\32",
			"\1\uffff",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\16\32\1\153\13\32",
			"\1\uffff",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\7\32\1\155\22\32",
			"\1\uffff",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\32\32",
			"\1\uffff",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\24\32\1\160\5\32",
			"\1\uffff",
			"",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\32\32",
			"\1\uffff",
			"",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\32\32",
			"\1\uffff",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\32\32",
			"\1\uffff",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\22\32\1\165\7\32",
			"\1\uffff",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\4\32\1\167\25\32",
			"\1\uffff",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\13\32\1\171\16\32",
			"\1\uffff",
			"\1\173\17\uffff\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\32\32",
			"\1\uffff",
			"\1\175",
			"\1\176\17\uffff\1\177",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\22\32\1\u0080\7\32",
			"\1\uffff",
			"\1\u0082\17\uffff\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\32\32",
			"\1\uffff",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\21\32\1\u0084\10\32",
			"\1\uffff",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\23\32\1\u0086\6\32",
			"\1\uffff",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\32\32",
			"\1\uffff",
			"\1\uffff",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\14\32\1\u008a\15\32",
			"\1\uffff",
			"",
			"",
			"\1\uffff",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\4\32\1\u008c\25\32",
			"\1\uffff",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\32\32",
			"\1\uffff",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\22\32\1\u008f\7\32",
			"\1\uffff",
			"\1\u0091\20\uffff\1\u0092",
			"\1\uffff",
			"\1\u0093",
			"\1\u0094\12\uffff\1\u0095",
			"",
			"\1\u0096\17\uffff\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\32\32",
			"\1\uffff",
			"",
			"\1\uffff",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\22\32\1\u0098\7\32",
			"\1\uffff",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\7\32\1\u009a\22\32",
			"\1\uffff",
			"\1\uffff",
			"",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\4\32\1\u009c\25\32",
			"\1\uffff",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\22\32\1\u009e\7\32",
			"\1\uffff",
			"\1\uffff",
			"\1\u00a0\17\uffff\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\32\32",
			"\1\uffff",
			"",
			"",
			"\1\u00a2",
			"\1\u00a3",
			"\1\u00a4",
			"\1\u00a5",
			"\1\uffff",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\4\32\1\u00a6\25\32",
			"\1\uffff",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\10\32\1\u00a8\21\32",
			"\1\uffff",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\32\32",
			"\1\uffff",
			"\1\u00ab\17\uffff\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\32\32",
			"\1\uffff",
			"\1\u00ad\13\uffff\1\u00ae",
			"\1\uffff",
			"\1\u00af",
			"\1\u00b0",
			"\1\u00b1",
			"\1\u00b2",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\22\32\1\u00b3\7\32",
			"\1\uffff",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\15\32\1\u00b5\14\32",
			"\1\uffff",
			"\1\uffff",
			"\1\u00b7\20\uffff\1\u00b8",
			"\1\uffff",
			"",
			"",
			"\1\u00b9",
			"\1\u00ba",
			"\1\u00bb",
			"\1\u00bc\3\uffff\1\u00bd",
			"\1\u00be\17\uffff\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\32\32",
			"\1\uffff",
			"\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\6\32\1\u00c0\23\32",
			"\1\uffff",
			"\1\u00c2",
			"\1\u00c3",
			"\1\u00c4\13\uffff\1\u00c5",
			"\1\u00c6",
			"\1\u00c7\13\uffff\1\u00c8",
			"\1\u00c9",
			"\1\u00ca",
			"\1\u00cb\20\uffff\1\u00cc",
			"\1\uffff",
			"\1\u00cd\17\uffff\12\26\7\uffff\32\32\4\uffff\1\26\1\uffff\32\32",
			"\1\uffff",
			"\1\u00cf",
			"\1\u00d0",
			"",
			"",
			"\1\u00d1",
			"",
			"",
			"\1\u00d2",
			"\1\u00d3",
			"",
			"",
			"",
			"\1\uffff",
			"\1\u00d4",
			"\1\u00d5\13\uffff\1\u00d6",
			"\1\u00d7\15\uffff\1\u00d8",
			"\1\u00d9",
			"\1\u00da",
			"\1\u00db",
			"",
			"",
			"",
			"",
			"\1\u00dc",
			"\1\u00dd",
			"\1\u00de\13\uffff\1\u00df",
			"\1\u00e0",
			"\1\u00e1",
			"",
			"",
			"\1\u00e2",
			"\1\u00e3\3\uffff\1\u00e4",
			"\1\u00e5\3\uffff\1\u00e6",
			"",
			"",
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
			return "1:1: Tokens : ( AND | CLOSEPARENTEHSIS | COMMA | DAYS | NOT | OPENPARENTEHSIS | OR | PERCENT | T__45 | T__46 | T__47 | T__48 | T__49 | T__50 | T__51 | T__52 | T__53 | T__54 | T__55 | T__56 | T__57 | T__58 | T__59 | T__60 | T__61 | T__62 | T__63 | T__64 | T__65 | T__66 | T__67 | T__68 | Operation | Number | HistoricalData | WhiteChar | Tcheat | WS | COMMENT | LINE_COMMENT );";
		}
		@Override
		public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
			IntStream input = _input;
			int _s = s;
			switch ( s ) {
					case 0 : 
						int LA12_77 = input.LA(1);
						 
						int index12_77 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_77=='h') ) {s = 109;}
						else if ( ((LA12_77 >= 'A' && LA12_77 <= 'Z')||(LA12_77 >= 'a' && LA12_77 <= 'g')||(LA12_77 >= 'i' && LA12_77 <= 'z')) ) {s = 26;}
						else if ( ((LA12_77 >= '0' && LA12_77 <= '9')||LA12_77=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 110;
						 
						input.seek(index12_77);
						if ( s>=0 ) return s;
						break;

					case 1 : 
						int LA12_158 = input.LA(1);
						 
						int index12_158 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_158==' ') ) {s = 171;}
						else if ( ((LA12_158 >= 'A' && LA12_158 <= 'Z')||(LA12_158 >= 'a' && LA12_158 <= 'z')) ) {s = 26;}
						else if ( ((LA12_158 >= '0' && LA12_158 <= '9')||LA12_158=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 172;
						 
						input.seek(index12_158);
						if ( s>=0 ) return s;
						break;

					case 2 : 
						int LA12_42 = input.LA(1);
						 
						int index12_42 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_42=='g') ) {s = 77;}
						else if ( ((LA12_42 >= 'A' && LA12_42 <= 'Z')||(LA12_42 >= 'a' && LA12_42 <= 'f')||(LA12_42 >= 'h' && LA12_42 <= 'z')) ) {s = 26;}
						else if ( ((LA12_42 >= '0' && LA12_42 <= '9')||LA12_42=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 78;
						 
						input.seek(index12_42);
						if ( s>=0 ) return s;
						break;

					case 3 : 
						int LA12_140 = input.LA(1);
						 
						int index12_140 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_140=='s') ) {s = 158;}
						else if ( ((LA12_140 >= 'A' && LA12_140 <= 'Z')||(LA12_140 >= 'a' && LA12_140 <= 'r')||(LA12_140 >= 't' && LA12_140 <= 'z')) ) {s = 26;}
						else if ( ((LA12_140 >= '0' && LA12_140 <= '9')||LA12_140=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 159;
						 
						input.seek(index12_140);
						if ( s>=0 ) return s;
						break;

					case 4 : 
						int LA12_34 = input.LA(1);
						 
						int index12_34 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_34=='o') ) {s = 61;}
						else if ( ((LA12_34 >= 'A' && LA12_34 <= 'Z')||(LA12_34 >= 'a' && LA12_34 <= 'n')||(LA12_34 >= 'p' && LA12_34 <= 'z')) ) {s = 26;}
						else if ( ((LA12_34 >= '0' && LA12_34 <= '9')||LA12_34=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 62;
						 
						input.seek(index12_34);
						if ( s>=0 ) return s;
						break;

					case 5 : 
						int LA12_61 = input.LA(1);
						 
						int index12_61 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_61=='s') ) {s = 93;}
						else if ( ((LA12_61 >= 'A' && LA12_61 <= 'Z')||(LA12_61 >= 'a' && LA12_61 <= 'r')||(LA12_61 >= 't' && LA12_61 <= 'z')) ) {s = 26;}
						else if ( ((LA12_61 >= '0' && LA12_61 <= '9')||LA12_61=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 94;
						 
						input.seek(index12_61);
						if ( s>=0 ) return s;
						break;

					case 6 : 
						int LA12_93 = input.LA(1);
						 
						int index12_93 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_93=='e') ) {s = 119;}
						else if ( ((LA12_93 >= 'A' && LA12_93 <= 'Z')||(LA12_93 >= 'a' && LA12_93 <= 'd')||(LA12_93 >= 'f' && LA12_93 <= 'z')) ) {s = 26;}
						else if ( ((LA12_93 >= '0' && LA12_93 <= '9')||LA12_93=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 120;
						 
						input.seek(index12_93);
						if ( s>=0 ) return s;
						break;

					case 7 : 
						int LA12_32 = input.LA(1);
						 
						int index12_32 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_32=='e') ) {s = 57;}
						else if ( ((LA12_32 >= 'A' && LA12_32 <= 'Z')||(LA12_32 >= 'a' && LA12_32 <= 'd')||(LA12_32 >= 'f' && LA12_32 <= 'z')) ) {s = 26;}
						else if ( ((LA12_32 >= '0' && LA12_32 <= '9')||LA12_32=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 58;
						 
						input.seek(index12_32);
						if ( s>=0 ) return s;
						break;

					case 8 : 
						int LA12_117 = input.LA(1);
						 
						int index12_117 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_117=='e') ) {s = 140;}
						else if ( ((LA12_117 >= 'A' && LA12_117 <= 'Z')||(LA12_117 >= 'a' && LA12_117 <= 'd')||(LA12_117 >= 'f' && LA12_117 <= 'z')) ) {s = 26;}
						else if ( ((LA12_117 >= '0' && LA12_117 <= '9')||LA12_117=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 141;
						 
						input.seek(index12_117);
						if ( s>=0 ) return s;
						break;

					case 9 : 
						int LA12_57 = input.LA(1);
						 
						int index12_57 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_57=='n') ) {s = 89;}
						else if ( ((LA12_57 >= 'A' && LA12_57 <= 'Z')||(LA12_57 >= 'a' && LA12_57 <= 'm')||(LA12_57 >= 'o' && LA12_57 <= 'z')) ) {s = 26;}
						else if ( ((LA12_57 >= '0' && LA12_57 <= '9')||LA12_57=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 90;
						 
						input.seek(index12_57);
						if ( s>=0 ) return s;
						break;

					case 10 : 
						int LA12_10 = input.LA(1);
						 
						int index12_10 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_10=='q') ) {s = 35;}
						else if ( ((LA12_10 >= 'A' && LA12_10 <= 'Z')||(LA12_10 >= 'a' && LA12_10 <= 'p')||(LA12_10 >= 'r' && LA12_10 <= 'z')) ) {s = 26;}
						else if ( ((LA12_10 >= '0' && LA12_10 <= '9')||LA12_10=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 27;
						 
						input.seek(index12_10);
						if ( s>=0 ) return s;
						break;

					case 11 : 
						int LA12_111 = input.LA(1);
						 
						int index12_111 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 22;}
						else if ( ((runtimeHistoryOpAhead())) ) {s = 137;}
						else if ( (true) ) {s = 27;}
						 
						input.seek(index12_111);
						if ( s>=0 ) return s;
						break;

					case 12 : 
						int LA12_116 = input.LA(1);
						 
						int index12_116 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 22;}
						else if ( ((runtimeHistoryOpAhead())) ) {s = 137;}
						else if ( (true) ) {s = 27;}
						 
						input.seek(index12_116);
						if ( s>=0 ) return s;
						break;

					case 13 : 
						int LA12_136 = input.LA(1);
						 
						int index12_136 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 22;}
						else if ( ((runtimeHistoryOpAhead())) ) {s = 137;}
						else if ( (true) ) {s = 27;}
						 
						input.seek(index12_136);
						if ( s>=0 ) return s;
						break;

					case 14 : 
						int LA12_142 = input.LA(1);
						 
						int index12_142 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 22;}
						else if ( ((runtimeHistoryOpAhead())) ) {s = 137;}
						else if ( (true) ) {s = 27;}
						 
						input.seek(index12_142);
						if ( s>=0 ) return s;
						break;

					case 15 : 
						int LA12_170 = input.LA(1);
						 
						int index12_170 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 22;}
						else if ( ((runtimeHistoryOpAhead())) ) {s = 137;}
						else if ( (true) ) {s = 27;}
						 
						input.seek(index12_170);
						if ( s>=0 ) return s;
						break;

					case 16 : 
						int LA12_81 = input.LA(1);
						 
						int index12_81 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_81=='u') ) {s = 112;}
						else if ( ((LA12_81 >= 'A' && LA12_81 <= 'Z')||(LA12_81 >= 'a' && LA12_81 <= 't')||(LA12_81 >= 'v' && LA12_81 <= 'z')) ) {s = 26;}
						else if ( ((LA12_81 >= '0' && LA12_81 <= '9')||LA12_81=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 113;
						 
						input.seek(index12_81);
						if ( s>=0 ) return s;
						break;

					case 17 : 
						int LA12_44 = input.LA(1);
						 
						int index12_44 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_44=='l') ) {s = 81;}
						else if ( ((LA12_44 >= 'A' && LA12_44 <= 'Z')||(LA12_44 >= 'a' && LA12_44 <= 'k')||(LA12_44 >= 'm' && LA12_44 <= 'z')) ) {s = 26;}
						else if ( ((LA12_44 >= '0' && LA12_44 <= '9')||LA12_44=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 82;
						 
						input.seek(index12_44);
						if ( s>=0 ) return s;
						break;

					case 18 : 
						int LA12_138 = input.LA(1);
						 
						int index12_138 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_138=='e') ) {s = 156;}
						else if ( ((LA12_138 >= 'A' && LA12_138 <= 'Z')||(LA12_138 >= 'a' && LA12_138 <= 'd')||(LA12_138 >= 'f' && LA12_138 <= 'z')) ) {s = 26;}
						else if ( ((LA12_138 >= '0' && LA12_138 <= '9')||LA12_138=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 157;
						 
						input.seek(index12_138);
						if ( s>=0 ) return s;
						break;

					case 19 : 
						int LA12_112 = input.LA(1);
						 
						int index12_112 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_112=='m') ) {s = 138;}
						else if ( ((LA12_112 >= 'A' && LA12_112 <= 'Z')||(LA12_112 >= 'a' && LA12_112 <= 'l')||(LA12_112 >= 'n' && LA12_112 <= 'z')) ) {s = 26;}
						else if ( ((LA12_112 >= '0' && LA12_112 <= '9')||LA12_112=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 139;
						 
						input.seek(index12_112);
						if ( s>=0 ) return s;
						break;

					case 20 : 
						int LA12_43 = input.LA(1);
						 
						int index12_43 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_43=='w') ) {s = 79;}
						else if ( ((LA12_43 >= 'A' && LA12_43 <= 'Z')||(LA12_43 >= 'a' && LA12_43 <= 'v')||(LA12_43 >= 'x' && LA12_43 <= 'z')) ) {s = 26;}
						else if ( ((LA12_43 >= '0' && LA12_43 <= '9')||LA12_43=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 80;
						 
						input.seek(index12_43);
						if ( s>=0 ) return s;
						break;

					case 21 : 
						int LA12_91 = input.LA(1);
						 
						int index12_91 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_91=='s') ) {s = 117;}
						else if ( ((LA12_91 >= 'A' && LA12_91 <= 'Z')||(LA12_91 >= 'a' && LA12_91 <= 'r')||(LA12_91 >= 't' && LA12_91 <= 'z')) ) {s = 26;}
						else if ( ((LA12_91 >= '0' && LA12_91 <= '9')||LA12_91=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 118;
						 
						input.seek(index12_91);
						if ( s>=0 ) return s;
						break;

					case 22 : 
						int LA12_59 = input.LA(1);
						 
						int index12_59 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_59=='s') ) {s = 91;}
						else if ( ((LA12_59 >= 'A' && LA12_59 <= 'Z')||(LA12_59 >= 'a' && LA12_59 <= 'r')||(LA12_59 >= 't' && LA12_59 <= 'z')) ) {s = 26;}
						else if ( ((LA12_59 >= '0' && LA12_59 <= '9')||LA12_59=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 92;
						 
						input.seek(index12_59);
						if ( s>=0 ) return s;
						break;

					case 23 : 
						int LA12_33 = input.LA(1);
						 
						int index12_33 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_33=='o') ) {s = 59;}
						else if ( ((LA12_33 >= 'A' && LA12_33 <= 'Z')||(LA12_33 >= 'a' && LA12_33 <= 'n')||(LA12_33 >= 'p' && LA12_33 <= 'z')) ) {s = 26;}
						else if ( ((LA12_33 >= '0' && LA12_33 <= '9')||LA12_33=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 60;
						 
						input.seek(index12_33);
						if ( s>=0 ) return s;
						break;

					case 24 : 
						int LA12_20 = input.LA(1);
						 
						int index12_20 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA12_20 >= 'A' && LA12_20 <= 'Z')||(LA12_20 >= 'a' && LA12_20 <= 'z')) ) {s = 26;}
						else if ( ((LA12_20 >= '0' && LA12_20 <= '9')||LA12_20=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 27;
						 
						input.seek(index12_20);
						if ( s>=0 ) return s;
						break;

					case 25 : 
						int LA12_11 = input.LA(1);
						 
						int index12_11 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_11=='o') ) {s = 36;}
						else if ( ((LA12_11 >= 'A' && LA12_11 <= 'Z')||(LA12_11 >= 'a' && LA12_11 <= 'n')||(LA12_11 >= 'p' && LA12_11 <= 'z')) ) {s = 26;}
						else if ( ((LA12_11 >= '0' && LA12_11 <= '9')||LA12_11=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 27;
						 
						input.seek(index12_11);
						if ( s>=0 ) return s;
						break;

					case 26 : 
						int LA12_87 = input.LA(1);
						 
						int index12_87 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA12_87 >= 'A' && LA12_87 <= 'Z')||(LA12_87 >= 'a' && LA12_87 <= 'z')) ) {s = 26;}
						else if ( ((LA12_87 >= '0' && LA12_87 <= '9')||LA12_87=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 115;
						 
						input.seek(index12_87);
						if ( s>=0 ) return s;
						break;

					case 27 : 
						int LA12_30 = input.LA(1);
						 
						int index12_30 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA12_30 >= 'A' && LA12_30 <= 'Z')||(LA12_30 >= 'a' && LA12_30 <= 'z')) ) {s = 26;}
						else if ( ((LA12_30 >= '0' && LA12_30 <= '9')||LA12_30=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 54;
						 
						input.seek(index12_30);
						if ( s>=0 ) return s;
						break;

					case 28 : 
						int LA12_52 = input.LA(1);
						 
						int index12_52 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA12_52 >= 'A' && LA12_52 <= 'Z')||(LA12_52 >= 'a' && LA12_52 <= 'z')) ) {s = 26;}
						else if ( ((LA12_52 >= '0' && LA12_52 <= '9')||LA12_52=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 86;
						 
						input.seek(index12_52);
						if ( s>=0 ) return s;
						break;

					case 29 : 
						int LA12_84 = input.LA(1);
						 
						int index12_84 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA12_84 >= 'A' && LA12_84 <= 'Z')||(LA12_84 >= 'a' && LA12_84 <= 'z')) ) {s = 26;}
						else if ( ((LA12_84 >= '0' && LA12_84 <= '9')||LA12_84=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 114;
						 
						input.seek(index12_84);
						if ( s>=0 ) return s;
						break;

					case 30 : 
						int LA12_40 = input.LA(1);
						 
						int index12_40 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_40=='v') ) {s = 73;}
						else if ( ((LA12_40 >= 'A' && LA12_40 <= 'Z')||(LA12_40 >= 'a' && LA12_40 <= 'u')||(LA12_40 >= 'w' && LA12_40 <= 'z')) ) {s = 26;}
						else if ( ((LA12_40 >= '0' && LA12_40 <= '9')||LA12_40=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 74;
						 
						input.seek(index12_40);
						if ( s>=0 ) return s;
						break;

					case 31 : 
						int LA12_105 = input.LA(1);
						 
						int index12_105 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_105=='r') ) {s = 132;}
						else if ( ((LA12_105 >= 'A' && LA12_105 <= 'Z')||(LA12_105 >= 'a' && LA12_105 <= 'q')||(LA12_105 >= 's' && LA12_105 <= 'z')) ) {s = 26;}
						else if ( ((LA12_105 >= '0' && LA12_105 <= '9')||LA12_105=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 133;
						 
						input.seek(index12_105);
						if ( s>=0 ) return s;
						break;

					case 32 : 
						int LA12_73 = input.LA(1);
						 
						int index12_73 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_73=='e') ) {s = 105;}
						else if ( ((LA12_73 >= 'A' && LA12_73 <= 'Z')||(LA12_73 >= 'a' && LA12_73 <= 'd')||(LA12_73 >= 'f' && LA12_73 <= 'z')) ) {s = 26;}
						else if ( ((LA12_73 >= '0' && LA12_73 <= '9')||LA12_73=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 106;
						 
						input.seek(index12_73);
						if ( s>=0 ) return s;
						break;

					case 33 : 
						int LA12_7 = input.LA(1);
						 
						int index12_7 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_7=='r') ) {s = 30;}
						else if ( (LA12_7=='v') ) {s = 31;}
						else if ( (LA12_7=='p') ) {s = 32;}
						else if ( ((LA12_7 >= 'A' && LA12_7 <= 'Z')||(LA12_7 >= 'a' && LA12_7 <= 'o')||LA12_7=='q'||(LA12_7 >= 's' && LA12_7 <= 'u')||(LA12_7 >= 'w' && LA12_7 <= 'z')) ) {s = 26;}
						else if ( ((LA12_7 >= '0' && LA12_7 <= '9')||LA12_7=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 27;
						 
						input.seek(index12_7);
						if ( s>=0 ) return s;
						break;

					case 34 : 
						int LA12_47 = input.LA(1);
						 
						int index12_47 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA12_47 >= 'A' && LA12_47 <= 'Z')||(LA12_47 >= 'a' && LA12_47 <= 'z')) ) {s = 26;}
						else if ( ((LA12_47 >= '0' && LA12_47 <= '9')||LA12_47=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 83;
						 
						input.seek(index12_47);
						if ( s>=0 ) return s;
						break;

					case 35 : 
						int LA12_132 = input.LA(1);
						 
						int index12_132 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_132=='s') ) {s = 152;}
						else if ( ((LA12_132 >= 'A' && LA12_132 <= 'Z')||(LA12_132 >= 'a' && LA12_132 <= 'r')||(LA12_132 >= 't' && LA12_132 <= 'z')) ) {s = 26;}
						else if ( ((LA12_132 >= '0' && LA12_132 <= '9')||LA12_132=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 153;
						 
						input.seek(index12_132);
						if ( s>=0 ) return s;
						break;

					case 36 : 
						int LA12_152 = input.LA(1);
						 
						int index12_152 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_152=='e') ) {s = 166;}
						else if ( ((LA12_152 >= 'A' && LA12_152 <= 'Z')||(LA12_152 >= 'a' && LA12_152 <= 'd')||(LA12_152 >= 'f' && LA12_152 <= 'z')) ) {s = 26;}
						else if ( ((LA12_152 >= '0' && LA12_152 <= '9')||LA12_152=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 167;
						 
						input.seek(index12_152);
						if ( s>=0 ) return s;
						break;

					case 37 : 
						int LA12_166 = input.LA(1);
						 
						int index12_166 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_166=='s') ) {s = 179;}
						else if ( ((LA12_166 >= 'A' && LA12_166 <= 'Z')||(LA12_166 >= 'a' && LA12_166 <= 'r')||(LA12_166 >= 't' && LA12_166 <= 'z')) ) {s = 26;}
						else if ( ((LA12_166 >= '0' && LA12_166 <= '9')||LA12_166=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 180;
						 
						input.seek(index12_166);
						if ( s>=0 ) return s;
						break;

					case 38 : 
						int LA12_179 = input.LA(1);
						 
						int index12_179 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_179==' ') ) {s = 190;}
						else if ( ((LA12_179 >= 'A' && LA12_179 <= 'Z')||(LA12_179 >= 'a' && LA12_179 <= 'z')) ) {s = 26;}
						else if ( ((LA12_179 >= '0' && LA12_179 <= '9')||LA12_179=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 191;
						 
						input.seek(index12_179);
						if ( s>=0 ) return s;
						break;

					case 39 : 
						int LA12_0 = input.LA(1);
						 
						int index12_0 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_0=='a') ) {s = 1;}
						else if ( (LA12_0==')') ) {s = 2;}
						else if ( (LA12_0==';') ) {s = 3;}
						else if ( (LA12_0=='d') ) {s = 4;}
						else if ( (LA12_0=='n') ) {s = 5;}
						else if ( (LA12_0=='(') ) {s = 6;}
						else if ( (LA12_0=='o') ) {s = 7;}
						else if ( (LA12_0=='%') ) {s = 8;}
						else if ( (LA12_0=='c') ) {s = 9;}
						else if ( (LA12_0=='e') ) {s = 10;}
						else if ( (LA12_0=='g') ) {s = 11;}
						else if ( (LA12_0=='i') ) {s = 12;}
						else if ( (LA12_0=='m') ) {s = 13;}
						else if ( (LA12_0=='r') ) {s = 14;}
						else if ( (LA12_0=='s') ) {s = 15;}
						else if ( (LA12_0=='h') ) {s = 16;}
						else if ( (LA12_0=='-'||(LA12_0 >= '0' && LA12_0 <= '9')) ) {s = 17;}
						else if ( (LA12_0=='l') ) {s = 18;}
						else if ( (LA12_0=='v') ) {s = 19;}
						else if ( ((LA12_0 >= 'A' && LA12_0 <= 'Z')||LA12_0=='b'||LA12_0=='f'||(LA12_0 >= 'j' && LA12_0 <= 'k')||(LA12_0 >= 'p' && LA12_0 <= 'q')||(LA12_0 >= 't' && LA12_0 <= 'u')||(LA12_0 >= 'w' && LA12_0 <= 'z')) ) {s = 20;}
						else if ( (LA12_0==' ') ) {s = 21;}
						else if ( (LA12_0=='_') && ((runtimeOpAhead()))) {s = 22;}
						else if ( ((LA12_0 >= '\t' && LA12_0 <= '\n')||(LA12_0 >= '\f' && LA12_0 <= '\r')) ) {s = 23;}
						else if ( (LA12_0=='/') ) {s = 24;}
						 
						input.seek(index12_0);
						if ( s>=0 ) return s;
						break;

					case 40 : 
						int LA12_9 = input.LA(1);
						 
						int index12_9 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_9=='r') ) {s = 33;}
						else if ( (LA12_9=='l') ) {s = 34;}
						else if ( ((LA12_9 >= 'A' && LA12_9 <= 'Z')||(LA12_9 >= 'a' && LA12_9 <= 'k')||(LA12_9 >= 'm' && LA12_9 <= 'q')||(LA12_9 >= 's' && LA12_9 <= 'z')) ) {s = 26;}
						else if ( ((LA12_9 >= '0' && LA12_9 <= '9')||LA12_9=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 27;
						 
						input.seek(index12_9);
						if ( s>=0 ) return s;
						break;

					case 41 : 
						int LA12_37 = input.LA(1);
						 
						int index12_37 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_37==' ') ) {s = 67;}
						else if ( ((LA12_37 >= 'A' && LA12_37 <= 'Z')||(LA12_37 >= 'a' && LA12_37 <= 'z')) ) {s = 26;}
						else if ( ((LA12_37 >= '0' && LA12_37 <= '9')||LA12_37=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 68;
						 
						input.seek(index12_37);
						if ( s>=0 ) return s;
						break;

					case 42 : 
						int LA12_89 = input.LA(1);
						 
						int index12_89 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA12_89 >= 'A' && LA12_89 <= 'Z')||(LA12_89 >= 'a' && LA12_89 <= 'z')) ) {s = 26;}
						else if ( ((LA12_89 >= '0' && LA12_89 <= '9')||LA12_89=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 116;
						 
						input.seek(index12_89);
						if ( s>=0 ) return s;
						break;

					case 43 : 
						int LA12_63 = input.LA(1);
						 
						int index12_63 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_63=='a') ) {s = 95;}
						else if ( ((LA12_63 >= 'A' && LA12_63 <= 'Z')||(LA12_63 >= 'b' && LA12_63 <= 'z')) ) {s = 26;}
						else if ( ((LA12_63 >= '0' && LA12_63 <= '9')||LA12_63=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 96;
						 
						input.seek(index12_63);
						if ( s>=0 ) return s;
						break;

					case 44 : 
						int LA12_95 = input.LA(1);
						 
						int index12_95 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_95=='l') ) {s = 121;}
						else if ( ((LA12_95 >= 'A' && LA12_95 <= 'Z')||(LA12_95 >= 'a' && LA12_95 <= 'k')||(LA12_95 >= 'm' && LA12_95 <= 'z')) ) {s = 26;}
						else if ( ((LA12_95 >= '0' && LA12_95 <= '9')||LA12_95=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 122;
						 
						input.seek(index12_95);
						if ( s>=0 ) return s;
						break;

					case 45 : 
						int LA12_35 = input.LA(1);
						 
						int index12_35 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_35=='u') ) {s = 63;}
						else if ( ((LA12_35 >= 'A' && LA12_35 <= 'Z')||(LA12_35 >= 'a' && LA12_35 <= 't')||(LA12_35 >= 'v' && LA12_35 <= 'z')) ) {s = 26;}
						else if ( ((LA12_35 >= '0' && LA12_35 <= '9')||LA12_35=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 64;
						 
						input.seek(index12_35);
						if ( s>=0 ) return s;
						break;

					case 46 : 
						int LA12_119 = input.LA(1);
						 
						int index12_119 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA12_119 >= 'A' && LA12_119 <= 'Z')||(LA12_119 >= 'a' && LA12_119 <= 'z')) ) {s = 26;}
						else if ( ((LA12_119 >= '0' && LA12_119 <= '9')||LA12_119=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 142;
						 
						input.seek(index12_119);
						if ( s>=0 ) return s;
						break;

					case 47 : 
						int LA12_109 = input.LA(1);
						 
						int index12_109 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA12_109 >= 'A' && LA12_109 <= 'Z')||(LA12_109 >= 'a' && LA12_109 <= 'z')) ) {s = 26;}
						else if ( ((LA12_109 >= '0' && LA12_109 <= '9')||LA12_109=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 136;
						 
						input.seek(index12_109);
						if ( s>=0 ) return s;
						break;

					case 48 : 
						int LA12_79 = input.LA(1);
						 
						int index12_79 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA12_79 >= 'A' && LA12_79 <= 'Z')||(LA12_79 >= 'a' && LA12_79 <= 'z')) ) {s = 26;}
						else if ( ((LA12_79 >= '0' && LA12_79 <= '9')||LA12_79=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 111;
						 
						input.seek(index12_79);
						if ( s>=0 ) return s;
						break;

					case 49 : 
						int LA12_156 = input.LA(1);
						 
						int index12_156 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA12_156 >= 'A' && LA12_156 <= 'Z')||(LA12_156 >= 'a' && LA12_156 <= 'z')) ) {s = 26;}
						else if ( ((LA12_156 >= '0' && LA12_156 <= '9')||LA12_156=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 170;
						 
						input.seek(index12_156);
						if ( s>=0 ) return s;
						break;

					case 50 : 
						int LA12_206 = input.LA(1);
						 
						int index12_206 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 22;}
						else if ( (true) ) {s = 27;}
						 
						input.seek(index12_206);
						if ( s>=0 ) return s;
						break;

					case 51 : 
						int LA12_193 = input.LA(1);
						 
						int index12_193 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 22;}
						else if ( (true) ) {s = 27;}
						 
						input.seek(index12_193);
						if ( s>=0 ) return s;
						break;

					case 52 : 
						int LA12_96 = input.LA(1);
						 
						int index12_96 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 22;}
						else if ( (true) ) {s = 27;}
						 
						input.seek(index12_96);
						if ( s>=0 ) return s;
						break;

					case 53 : 
						int LA12_94 = input.LA(1);
						 
						int index12_94 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 22;}
						else if ( (true) ) {s = 27;}
						 
						input.seek(index12_94);
						if ( s>=0 ) return s;
						break;

					case 54 : 
						int LA12_92 = input.LA(1);
						 
						int index12_92 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 22;}
						else if ( (true) ) {s = 27;}
						 
						input.seek(index12_92);
						if ( s>=0 ) return s;
						break;

					case 55 : 
						int LA12_90 = input.LA(1);
						 
						int index12_90 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 22;}
						else if ( (true) ) {s = 27;}
						 
						input.seek(index12_90);
						if ( s>=0 ) return s;
						break;

					case 56 : 
						int LA12_88 = input.LA(1);
						 
						int index12_88 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 22;}
						else if ( (true) ) {s = 27;}
						 
						input.seek(index12_88);
						if ( s>=0 ) return s;
						break;

					case 57 : 
						int LA12_85 = input.LA(1);
						 
						int index12_85 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 22;}
						else if ( (true) ) {s = 27;}
						 
						input.seek(index12_85);
						if ( s>=0 ) return s;
						break;

					case 58 : 
						int LA12_82 = input.LA(1);
						 
						int index12_82 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 22;}
						else if ( (true) ) {s = 27;}
						 
						input.seek(index12_82);
						if ( s>=0 ) return s;
						break;

					case 59 : 
						int LA12_80 = input.LA(1);
						 
						int index12_80 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 22;}
						else if ( (true) ) {s = 27;}
						 
						input.seek(index12_80);
						if ( s>=0 ) return s;
						break;

					case 60 : 
						int LA12_78 = input.LA(1);
						 
						int index12_78 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 22;}
						else if ( (true) ) {s = 27;}
						 
						input.seek(index12_78);
						if ( s>=0 ) return s;
						break;

					case 61 : 
						int LA12_76 = input.LA(1);
						 
						int index12_76 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 22;}
						else if ( (true) ) {s = 27;}
						 
						input.seek(index12_76);
						if ( s>=0 ) return s;
						break;

					case 62 : 
						int LA12_74 = input.LA(1);
						 
						int index12_74 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 22;}
						else if ( (true) ) {s = 27;}
						 
						input.seek(index12_74);
						if ( s>=0 ) return s;
						break;

					case 63 : 
						int LA12_72 = input.LA(1);
						 
						int index12_72 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 22;}
						else if ( (true) ) {s = 27;}
						 
						input.seek(index12_72);
						if ( s>=0 ) return s;
						break;

					case 64 : 
						int LA12_70 = input.LA(1);
						 
						int index12_70 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 22;}
						else if ( (true) ) {s = 27;}
						 
						input.seek(index12_70);
						if ( s>=0 ) return s;
						break;

					case 65 : 
						int LA12_68 = input.LA(1);
						 
						int index12_68 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 22;}
						else if ( (true) ) {s = 27;}
						 
						input.seek(index12_68);
						if ( s>=0 ) return s;
						break;

					case 66 : 
						int LA12_66 = input.LA(1);
						 
						int index12_66 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 22;}
						else if ( (true) ) {s = 27;}
						 
						input.seek(index12_66);
						if ( s>=0 ) return s;
						break;

					case 67 : 
						int LA12_64 = input.LA(1);
						 
						int index12_64 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 22;}
						else if ( (true) ) {s = 27;}
						 
						input.seek(index12_64);
						if ( s>=0 ) return s;
						break;

					case 68 : 
						int LA12_62 = input.LA(1);
						 
						int index12_62 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 22;}
						else if ( (true) ) {s = 27;}
						 
						input.seek(index12_62);
						if ( s>=0 ) return s;
						break;

					case 69 : 
						int LA12_60 = input.LA(1);
						 
						int index12_60 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 22;}
						else if ( (true) ) {s = 27;}
						 
						input.seek(index12_60);
						if ( s>=0 ) return s;
						break;

					case 70 : 
						int LA12_58 = input.LA(1);
						 
						int index12_58 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 22;}
						else if ( (true) ) {s = 27;}
						 
						input.seek(index12_58);
						if ( s>=0 ) return s;
						break;

					case 71 : 
						int LA12_56 = input.LA(1);
						 
						int index12_56 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 22;}
						else if ( (true) ) {s = 27;}
						 
						input.seek(index12_56);
						if ( s>=0 ) return s;
						break;

					case 72 : 
						int LA12_53 = input.LA(1);
						 
						int index12_53 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 22;}
						else if ( (true) ) {s = 27;}
						 
						input.seek(index12_53);
						if ( s>=0 ) return s;
						break;

					case 73 : 
						int LA12_51 = input.LA(1);
						 
						int index12_51 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 22;}
						else if ( (true) ) {s = 27;}
						 
						input.seek(index12_51);
						if ( s>=0 ) return s;
						break;

					case 74 : 
						int LA12_49 = input.LA(1);
						 
						int index12_49 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 22;}
						else if ( (true) ) {s = 27;}
						 
						input.seek(index12_49);
						if ( s>=0 ) return s;
						break;

					case 75 : 
						int LA12_48 = input.LA(1);
						 
						int index12_48 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 22;}
						else if ( (true) ) {s = 27;}
						 
						input.seek(index12_48);
						if ( s>=0 ) return s;
						break;

					case 76 : 
						int LA12_98 = input.LA(1);
						 
						int index12_98 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 22;}
						else if ( (true) ) {s = 27;}
						 
						input.seek(index12_98);
						if ( s>=0 ) return s;
						break;

					case 77 : 
						int LA12_102 = input.LA(1);
						 
						int index12_102 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 22;}
						else if ( (true) ) {s = 27;}
						 
						input.seek(index12_102);
						if ( s>=0 ) return s;
						break;

					case 78 : 
						int LA12_104 = input.LA(1);
						 
						int index12_104 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 22;}
						else if ( (true) ) {s = 27;}
						 
						input.seek(index12_104);
						if ( s>=0 ) return s;
						break;

					case 79 : 
						int LA12_106 = input.LA(1);
						 
						int index12_106 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 22;}
						else if ( (true) ) {s = 27;}
						 
						input.seek(index12_106);
						if ( s>=0 ) return s;
						break;

					case 80 : 
						int LA12_108 = input.LA(1);
						 
						int index12_108 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 22;}
						else if ( (true) ) {s = 27;}
						 
						input.seek(index12_108);
						if ( s>=0 ) return s;
						break;

					case 81 : 
						int LA12_110 = input.LA(1);
						 
						int index12_110 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 22;}
						else if ( (true) ) {s = 27;}
						 
						input.seek(index12_110);
						if ( s>=0 ) return s;
						break;

					case 82 : 
						int LA12_113 = input.LA(1);
						 
						int index12_113 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 22;}
						else if ( (true) ) {s = 27;}
						 
						input.seek(index12_113);
						if ( s>=0 ) return s;
						break;

					case 83 : 
						int LA12_118 = input.LA(1);
						 
						int index12_118 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 22;}
						else if ( (true) ) {s = 27;}
						 
						input.seek(index12_118);
						if ( s>=0 ) return s;
						break;

					case 84 : 
						int LA12_120 = input.LA(1);
						 
						int index12_120 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 22;}
						else if ( (true) ) {s = 27;}
						 
						input.seek(index12_120);
						if ( s>=0 ) return s;
						break;

					case 85 : 
						int LA12_122 = input.LA(1);
						 
						int index12_122 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 22;}
						else if ( (true) ) {s = 27;}
						 
						input.seek(index12_122);
						if ( s>=0 ) return s;
						break;

					case 86 : 
						int LA12_124 = input.LA(1);
						 
						int index12_124 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 22;}
						else if ( (true) ) {s = 27;}
						 
						input.seek(index12_124);
						if ( s>=0 ) return s;
						break;

					case 87 : 
						int LA12_129 = input.LA(1);
						 
						int index12_129 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 22;}
						else if ( (true) ) {s = 27;}
						 
						input.seek(index12_129);
						if ( s>=0 ) return s;
						break;

					case 88 : 
						int LA12_131 = input.LA(1);
						 
						int index12_131 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 22;}
						else if ( (true) ) {s = 27;}
						 
						input.seek(index12_131);
						if ( s>=0 ) return s;
						break;

					case 89 : 
						int LA12_133 = input.LA(1);
						 
						int index12_133 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 22;}
						else if ( (true) ) {s = 27;}
						 
						input.seek(index12_133);
						if ( s>=0 ) return s;
						break;

					case 90 : 
						int LA12_135 = input.LA(1);
						 
						int index12_135 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 22;}
						else if ( (true) ) {s = 27;}
						 
						input.seek(index12_135);
						if ( s>=0 ) return s;
						break;

					case 91 : 
						int LA12_139 = input.LA(1);
						 
						int index12_139 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 22;}
						else if ( (true) ) {s = 27;}
						 
						input.seek(index12_139);
						if ( s>=0 ) return s;
						break;

					case 92 : 
						int LA12_141 = input.LA(1);
						 
						int index12_141 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 22;}
						else if ( (true) ) {s = 27;}
						 
						input.seek(index12_141);
						if ( s>=0 ) return s;
						break;

					case 93 : 
						int LA12_144 = input.LA(1);
						 
						int index12_144 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 22;}
						else if ( (true) ) {s = 27;}
						 
						input.seek(index12_144);
						if ( s>=0 ) return s;
						break;

					case 94 : 
						int LA12_151 = input.LA(1);
						 
						int index12_151 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 22;}
						else if ( (true) ) {s = 27;}
						 
						input.seek(index12_151);
						if ( s>=0 ) return s;
						break;

					case 95 : 
						int LA12_153 = input.LA(1);
						 
						int index12_153 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 22;}
						else if ( (true) ) {s = 27;}
						 
						input.seek(index12_153);
						if ( s>=0 ) return s;
						break;

					case 96 : 
						int LA12_155 = input.LA(1);
						 
						int index12_155 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 22;}
						else if ( (true) ) {s = 27;}
						 
						input.seek(index12_155);
						if ( s>=0 ) return s;
						break;

					case 97 : 
						int LA12_157 = input.LA(1);
						 
						int index12_157 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 22;}
						else if ( (true) ) {s = 27;}
						 
						input.seek(index12_157);
						if ( s>=0 ) return s;
						break;

					case 98 : 
						int LA12_159 = input.LA(1);
						 
						int index12_159 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 22;}
						else if ( (true) ) {s = 27;}
						 
						input.seek(index12_159);
						if ( s>=0 ) return s;
						break;

					case 99 : 
						int LA12_161 = input.LA(1);
						 
						int index12_161 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 22;}
						else if ( (true) ) {s = 27;}
						 
						input.seek(index12_161);
						if ( s>=0 ) return s;
						break;

					case 100 : 
						int LA12_167 = input.LA(1);
						 
						int index12_167 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 22;}
						else if ( (true) ) {s = 27;}
						 
						input.seek(index12_167);
						if ( s>=0 ) return s;
						break;

					case 101 : 
						int LA12_169 = input.LA(1);
						 
						int index12_169 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 22;}
						else if ( (true) ) {s = 27;}
						 
						input.seek(index12_169);
						if ( s>=0 ) return s;
						break;

					case 102 : 
						int LA12_172 = input.LA(1);
						 
						int index12_172 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 22;}
						else if ( (true) ) {s = 27;}
						 
						input.seek(index12_172);
						if ( s>=0 ) return s;
						break;

					case 103 : 
						int LA12_180 = input.LA(1);
						 
						int index12_180 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 22;}
						else if ( (true) ) {s = 27;}
						 
						input.seek(index12_180);
						if ( s>=0 ) return s;
						break;

					case 104 : 
						int LA12_182 = input.LA(1);
						 
						int index12_182 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 22;}
						else if ( (true) ) {s = 27;}
						 
						input.seek(index12_182);
						if ( s>=0 ) return s;
						break;

					case 105 : 
						int LA12_191 = input.LA(1);
						 
						int index12_191 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 22;}
						else if ( (true) ) {s = 27;}
						 
						input.seek(index12_191);
						if ( s>=0 ) return s;
						break;

					case 106 : 
						int LA12_143 = input.LA(1);
						 
						int index12_143 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_143==' ') ) {s = 160;}
						else if ( ((LA12_143 >= 'A' && LA12_143 <= 'Z')||(LA12_143 >= 'a' && LA12_143 <= 'z')) ) {s = 26;}
						else if ( ((LA12_143 >= '0' && LA12_143 <= '9')||LA12_143=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 161;
						 
						input.seek(index12_143);
						if ( s>=0 ) return s;
						break;

					case 107 : 
						int LA12_121 = input.LA(1);
						 
						int index12_121 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_121=='s') ) {s = 143;}
						else if ( ((LA12_121 >= 'A' && LA12_121 <= 'Z')||(LA12_121 >= 'a' && LA12_121 <= 'r')||(LA12_121 >= 't' && LA12_121 <= 'z')) ) {s = 26;}
						else if ( ((LA12_121 >= '0' && LA12_121 <= '9')||LA12_121=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 144;
						 
						input.seek(index12_121);
						if ( s>=0 ) return s;
						break;

					case 108 : 
						int LA12_14 = input.LA(1);
						 
						int index12_14 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_14=='e') ) {s = 40;}
						else if ( ((LA12_14 >= 'A' && LA12_14 <= 'Z')||(LA12_14 >= 'a' && LA12_14 <= 'd')||(LA12_14 >= 'f' && LA12_14 <= 'z')) ) {s = 26;}
						else if ( ((LA12_14 >= '0' && LA12_14 <= '9')||LA12_14=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 27;
						 
						input.seek(index12_14);
						if ( s>=0 ) return s;
						break;

					case 109 : 
						int LA12_19 = input.LA(1);
						 
						int index12_19 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_19=='o') ) {s = 44;}
						else if ( ((LA12_19 >= 'A' && LA12_19 <= 'Z')||(LA12_19 >= 'a' && LA12_19 <= 'n')||(LA12_19 >= 'p' && LA12_19 <= 'z')) ) {s = 26;}
						else if ( ((LA12_19 >= '0' && LA12_19 <= '9')||LA12_19=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 27;
						 
						input.seek(index12_19);
						if ( s>=0 ) return s;
						break;

					case 110 : 
						int LA12_29 = input.LA(1);
						 
						int index12_29 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_29=='t') ) {s = 52;}
						else if ( ((LA12_29 >= 'A' && LA12_29 <= 'Z')||(LA12_29 >= 'a' && LA12_29 <= 's')||(LA12_29 >= 'u' && LA12_29 <= 'z')) ) {s = 26;}
						else if ( ((LA12_29 >= '0' && LA12_29 <= '9')||LA12_29=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 53;
						 
						input.seek(index12_29);
						if ( s>=0 ) return s;
						break;

					case 111 : 
						int LA12_18 = input.LA(1);
						 
						int index12_18 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_18=='o') ) {s = 43;}
						else if ( ((LA12_18 >= 'A' && LA12_18 <= 'Z')||(LA12_18 >= 'a' && LA12_18 <= 'n')||(LA12_18 >= 'p' && LA12_18 <= 'z')) ) {s = 26;}
						else if ( ((LA12_18 >= '0' && LA12_18 <= '9')||LA12_18=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 27;
						 
						input.seek(index12_18);
						if ( s>=0 ) return s;
						break;

					case 112 : 
						int LA12_16 = input.LA(1);
						 
						int index12_16 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_16=='i') ) {s = 42;}
						else if ( ((LA12_16 >= 'A' && LA12_16 <= 'Z')||(LA12_16 >= 'a' && LA12_16 <= 'h')||(LA12_16 >= 'j' && LA12_16 <= 'z')) ) {s = 26;}
						else if ( ((LA12_16 >= '0' && LA12_16 <= '9')||LA12_16=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 27;
						 
						input.seek(index12_16);
						if ( s>=0 ) return s;
						break;

					case 113 : 
						int LA12_12 = input.LA(1);
						 
						int index12_12 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_12=='s') ) {s = 37;}
						else if ( ((LA12_12 >= 'A' && LA12_12 <= 'Z')||(LA12_12 >= 'a' && LA12_12 <= 'r')||(LA12_12 >= 't' && LA12_12 <= 'z')) ) {s = 26;}
						else if ( ((LA12_12 >= '0' && LA12_12 <= '9')||LA12_12=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 27;
						 
						input.seek(index12_12);
						if ( s>=0 ) return s;
						break;

					case 114 : 
						int LA12_28 = input.LA(1);
						 
						int index12_28 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_28=='y') ) {s = 50;}
						else if ( ((LA12_28 >= 'A' && LA12_28 <= 'Z')||(LA12_28 >= 'a' && LA12_28 <= 'x')||LA12_28=='z') ) {s = 26;}
						else if ( ((LA12_28 >= '0' && LA12_28 <= '9')||LA12_28=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 51;
						 
						input.seek(index12_28);
						if ( s>=0 ) return s;
						break;

					case 115 : 
						int LA12_50 = input.LA(1);
						 
						int index12_50 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_50=='s') ) {s = 84;}
						else if ( ((LA12_50 >= 'A' && LA12_50 <= 'Z')||(LA12_50 >= 'a' && LA12_50 <= 'r')||(LA12_50 >= 't' && LA12_50 <= 'z')) ) {s = 26;}
						else if ( ((LA12_50 >= '0' && LA12_50 <= '9')||LA12_50=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 85;
						 
						input.seek(index12_50);
						if ( s>=0 ) return s;
						break;

					case 116 : 
						int LA12_5 = input.LA(1);
						 
						int index12_5 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_5=='o') ) {s = 29;}
						else if ( ((LA12_5 >= 'A' && LA12_5 <= 'Z')||(LA12_5 >= 'a' && LA12_5 <= 'n')||(LA12_5 >= 'p' && LA12_5 <= 'z')) ) {s = 26;}
						else if ( ((LA12_5 >= '0' && LA12_5 <= '9')||LA12_5=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 27;
						 
						input.seek(index12_5);
						if ( s>=0 ) return s;
						break;

					case 117 : 
						int LA12_69 = input.LA(1);
						 
						int index12_69 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_69=='e') ) {s = 101;}
						else if ( ((LA12_69 >= 'A' && LA12_69 <= 'Z')||(LA12_69 >= 'a' && LA12_69 <= 'd')||(LA12_69 >= 'f' && LA12_69 <= 'z')) ) {s = 26;}
						else if ( ((LA12_69 >= '0' && LA12_69 <= '9')||LA12_69=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 102;
						 
						input.seek(index12_69);
						if ( s>=0 ) return s;
						break;

					case 118 : 
						int LA12_39 = input.LA(1);
						 
						int index12_39 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_39=='r') ) {s = 71;}
						else if ( ((LA12_39 >= 'A' && LA12_39 <= 'Z')||(LA12_39 >= 'a' && LA12_39 <= 'q')||(LA12_39 >= 's' && LA12_39 <= 'z')) ) {s = 26;}
						else if ( ((LA12_39 >= '0' && LA12_39 <= '9')||LA12_39=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 72;
						 
						input.seek(index12_39);
						if ( s>=0 ) return s;
						break;

					case 119 : 
						int LA12_71 = input.LA(1);
						 
						int index12_71 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_71=='e') ) {s = 103;}
						else if ( ((LA12_71 >= 'A' && LA12_71 <= 'Z')||(LA12_71 >= 'a' && LA12_71 <= 'd')||(LA12_71 >= 'f' && LA12_71 <= 'z')) ) {s = 26;}
						else if ( ((LA12_71 >= '0' && LA12_71 <= '9')||LA12_71=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 104;
						 
						input.seek(index12_71);
						if ( s>=0 ) return s;
						break;

					case 120 : 
						int LA12_25 = input.LA(1);
						 
						int index12_25 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_25=='d') ) {s = 47;}
						else if ( ((LA12_25 >= 'A' && LA12_25 <= 'Z')||(LA12_25 >= 'a' && LA12_25 <= 'c')||(LA12_25 >= 'e' && LA12_25 <= 'z')) ) {s = 26;}
						else if ( ((LA12_25 >= '0' && LA12_25 <= '9')||LA12_25=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 48;
						 
						input.seek(index12_25);
						if ( s>=0 ) return s;
						break;

					case 121 : 
						int LA12_4 = input.LA(1);
						 
						int index12_4 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_4=='a') ) {s = 28;}
						else if ( ((LA12_4 >= 'A' && LA12_4 <= 'Z')||(LA12_4 >= 'b' && LA12_4 <= 'z')) ) {s = 26;}
						else if ( ((LA12_4 >= '0' && LA12_4 <= '9')||LA12_4=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 27;
						 
						input.seek(index12_4);
						if ( s>=0 ) return s;
						break;

					case 122 : 
						int LA12_101 = input.LA(1);
						 
						int index12_101 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_101=='s') ) {s = 128;}
						else if ( ((LA12_101 >= 'A' && LA12_101 <= 'Z')||(LA12_101 >= 'a' && LA12_101 <= 'r')||(LA12_101 >= 't' && LA12_101 <= 'z')) ) {s = 26;}
						else if ( ((LA12_101 >= '0' && LA12_101 <= '9')||LA12_101=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 129;
						 
						input.seek(index12_101);
						if ( s>=0 ) return s;
						break;

					case 123 : 
						int LA12_103 = input.LA(1);
						 
						int index12_103 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_103==' ') ) {s = 130;}
						else if ( ((LA12_103 >= 'A' && LA12_103 <= 'Z')||(LA12_103 >= 'a' && LA12_103 <= 'z')) ) {s = 26;}
						else if ( ((LA12_103 >= '0' && LA12_103 <= '9')||LA12_103=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 131;
						 
						input.seek(index12_103);
						if ( s>=0 ) return s;
						break;

					case 124 : 
						int LA12_128 = input.LA(1);
						 
						int index12_128 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_128==' ') ) {s = 150;}
						else if ( ((LA12_128 >= 'A' && LA12_128 <= 'Z')||(LA12_128 >= 'a' && LA12_128 <= 'z')) ) {s = 26;}
						else if ( ((LA12_128 >= '0' && LA12_128 <= '9')||LA12_128=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 151;
						 
						input.seek(index12_128);
						if ( s>=0 ) return s;
						break;

					case 125 : 
						int LA12_1 = input.LA(1);
						 
						int index12_1 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_1=='n') ) {s = 25;}
						else if ( ((LA12_1 >= 'A' && LA12_1 <= 'Z')||(LA12_1 >= 'a' && LA12_1 <= 'm')||(LA12_1 >= 'o' && LA12_1 <= 'z')) ) {s = 26;}
						else if ( ((LA12_1 >= '0' && LA12_1 <= '9')||LA12_1=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 27;
						 
						input.seek(index12_1);
						if ( s>=0 ) return s;
						break;

					case 126 : 
						int LA12_36 = input.LA(1);
						 
						int index12_36 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_36=='e') ) {s = 65;}
						else if ( ((LA12_36 >= 'A' && LA12_36 <= 'Z')||(LA12_36 >= 'a' && LA12_36 <= 'd')||(LA12_36 >= 'f' && LA12_36 <= 'z')) ) {s = 26;}
						else if ( ((LA12_36 >= '0' && LA12_36 <= '9')||LA12_36=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 66;
						 
						input.seek(index12_36);
						if ( s>=0 ) return s;
						break;

					case 127 : 
						int LA12_97 = input.LA(1);
						 
						int index12_97 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_97==' ') ) {s = 123;}
						else if ( ((LA12_97 >= 'A' && LA12_97 <= 'Z')||(LA12_97 >= 'a' && LA12_97 <= 'z')) ) {s = 26;}
						else if ( ((LA12_97 >= '0' && LA12_97 <= '9')||LA12_97=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 124;
						 
						input.seek(index12_97);
						if ( s>=0 ) return s;
						break;

					case 128 : 
						int LA12_65 = input.LA(1);
						 
						int index12_65 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_65=='s') ) {s = 97;}
						else if ( ((LA12_65 >= 'A' && LA12_65 <= 'Z')||(LA12_65 >= 'a' && LA12_65 <= 'r')||(LA12_65 >= 't' && LA12_65 <= 'z')) ) {s = 26;}
						else if ( ((LA12_65 >= '0' && LA12_65 <= '9')||LA12_65=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 98;
						 
						input.seek(index12_65);
						if ( s>=0 ) return s;
						break;

					case 129 : 
						int LA12_13 = input.LA(1);
						 
						int index12_13 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_13=='a') ) {s = 38;}
						else if ( (LA12_13=='o') ) {s = 39;}
						else if ( ((LA12_13 >= 'A' && LA12_13 <= 'Z')||(LA12_13 >= 'b' && LA12_13 <= 'n')||(LA12_13 >= 'p' && LA12_13 <= 'z')) ) {s = 26;}
						else if ( ((LA12_13 >= '0' && LA12_13 <= '9')||LA12_13=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 27;
						 
						input.seek(index12_13);
						if ( s>=0 ) return s;
						break;

					case 130 : 
						int LA12_55 = input.LA(1);
						 
						int index12_55 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_55=='r') ) {s = 87;}
						else if ( ((LA12_55 >= 'A' && LA12_55 <= 'Z')||(LA12_55 >= 'a' && LA12_55 <= 'q')||(LA12_55 >= 's' && LA12_55 <= 'z')) ) {s = 26;}
						else if ( ((LA12_55 >= '0' && LA12_55 <= '9')||LA12_55=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 88;
						 
						input.seek(index12_55);
						if ( s>=0 ) return s;
						break;

					case 131 : 
						int LA12_15 = input.LA(1);
						 
						int index12_15 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_15=='m') ) {s = 41;}
						else if ( ((LA12_15 >= 'A' && LA12_15 <= 'Z')||(LA12_15 >= 'a' && LA12_15 <= 'l')||(LA12_15 >= 'n' && LA12_15 <= 'z')) ) {s = 26;}
						else if ( ((LA12_15 >= '0' && LA12_15 <= '9')||LA12_15=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 27;
						 
						input.seek(index12_15);
						if ( s>=0 ) return s;
						break;

					case 132 : 
						int LA12_31 = input.LA(1);
						 
						int index12_31 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_31=='e') ) {s = 55;}
						else if ( ((LA12_31 >= 'A' && LA12_31 <= 'Z')||(LA12_31 >= 'a' && LA12_31 <= 'd')||(LA12_31 >= 'f' && LA12_31 <= 'z')) ) {s = 26;}
						else if ( ((LA12_31 >= '0' && LA12_31 <= '9')||LA12_31=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 56;
						 
						input.seek(index12_31);
						if ( s>=0 ) return s;
						break;

					case 133 : 
						int LA12_38 = input.LA(1);
						 
						int index12_38 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_38=='k') ) {s = 69;}
						else if ( ((LA12_38 >= 'A' && LA12_38 <= 'Z')||(LA12_38 >= 'a' && LA12_38 <= 'j')||(LA12_38 >= 'l' && LA12_38 <= 'z')) ) {s = 26;}
						else if ( ((LA12_38 >= '0' && LA12_38 <= '9')||LA12_38=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 70;
						 
						input.seek(index12_38);
						if ( s>=0 ) return s;
						break;

					case 134 : 
						int LA12_41 = input.LA(1);
						 
						int index12_41 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_41=='o') ) {s = 75;}
						else if ( ((LA12_41 >= 'A' && LA12_41 <= 'Z')||(LA12_41 >= 'a' && LA12_41 <= 'n')||(LA12_41 >= 'p' && LA12_41 <= 'z')) ) {s = 26;}
						else if ( ((LA12_41 >= '0' && LA12_41 <= '9')||LA12_41=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 76;
						 
						input.seek(index12_41);
						if ( s>=0 ) return s;
						break;

					case 135 : 
						int LA12_26 = input.LA(1);
						 
						int index12_26 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA12_26 >= 'A' && LA12_26 <= 'Z')||(LA12_26 >= 'a' && LA12_26 <= 'z')) ) {s = 26;}
						else if ( ((LA12_26 >= '0' && LA12_26 <= '9')||LA12_26=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 49;
						 
						input.seek(index12_26);
						if ( s>=0 ) return s;
						break;

					case 136 : 
						int LA12_192 = input.LA(1);
						 
						int index12_192 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_192==' ') ) {s = 205;}
						else if ( ((LA12_192 >= 'A' && LA12_192 <= 'Z')||(LA12_192 >= 'a' && LA12_192 <= 'z')) ) {s = 26;}
						else if ( ((LA12_192 >= '0' && LA12_192 <= '9')||LA12_192=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 206;
						 
						input.seek(index12_192);
						if ( s>=0 ) return s;
						break;

					case 137 : 
						int LA12_181 = input.LA(1);
						 
						int index12_181 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_181=='g') ) {s = 192;}
						else if ( ((LA12_181 >= 'A' && LA12_181 <= 'Z')||(LA12_181 >= 'a' && LA12_181 <= 'f')||(LA12_181 >= 'h' && LA12_181 <= 'z')) ) {s = 26;}
						else if ( ((LA12_181 >= '0' && LA12_181 <= '9')||LA12_181=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 193;
						 
						input.seek(index12_181);
						if ( s>=0 ) return s;
						break;

					case 138 : 
						int LA12_168 = input.LA(1);
						 
						int index12_168 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_168=='n') ) {s = 181;}
						else if ( ((LA12_168 >= 'A' && LA12_168 <= 'Z')||(LA12_168 >= 'a' && LA12_168 <= 'm')||(LA12_168 >= 'o' && LA12_168 <= 'z')) ) {s = 26;}
						else if ( ((LA12_168 >= '0' && LA12_168 <= '9')||LA12_168=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 182;
						 
						input.seek(index12_168);
						if ( s>=0 ) return s;
						break;

					case 139 : 
						int LA12_154 = input.LA(1);
						 
						int index12_154 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_154=='i') ) {s = 168;}
						else if ( ((LA12_154 >= 'A' && LA12_154 <= 'Z')||(LA12_154 >= 'a' && LA12_154 <= 'h')||(LA12_154 >= 'j' && LA12_154 <= 'z')) ) {s = 26;}
						else if ( ((LA12_154 >= '0' && LA12_154 <= '9')||LA12_154=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 169;
						 
						input.seek(index12_154);
						if ( s>=0 ) return s;
						break;

					case 140 : 
						int LA12_134 = input.LA(1);
						 
						int index12_134 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_134=='h') ) {s = 154;}
						else if ( ((LA12_134 >= 'A' && LA12_134 <= 'Z')||(LA12_134 >= 'a' && LA12_134 <= 'g')||(LA12_134 >= 'i' && LA12_134 <= 'z')) ) {s = 26;}
						else if ( ((LA12_134 >= '0' && LA12_134 <= '9')||LA12_134=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 155;
						 
						input.seek(index12_134);
						if ( s>=0 ) return s;
						break;

					case 141 : 
						int LA12_107 = input.LA(1);
						 
						int index12_107 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_107=='t') ) {s = 134;}
						else if ( ((LA12_107 >= 'A' && LA12_107 <= 'Z')||(LA12_107 >= 'a' && LA12_107 <= 's')||(LA12_107 >= 'u' && LA12_107 <= 'z')) ) {s = 26;}
						else if ( ((LA12_107 >= '0' && LA12_107 <= '9')||LA12_107=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 135;
						 
						input.seek(index12_107);
						if ( s>=0 ) return s;
						break;

					case 142 : 
						int LA12_75 = input.LA(1);
						 
						int index12_75 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_75=='o') ) {s = 107;}
						else if ( ((LA12_75 >= 'A' && LA12_75 <= 'Z')||(LA12_75 >= 'a' && LA12_75 <= 'n')||(LA12_75 >= 'p' && LA12_75 <= 'z')) ) {s = 26;}
						else if ( ((LA12_75 >= '0' && LA12_75 <= '9')||LA12_75=='_') && ((runtimeOpAhead()))) {s = 22;}
						else s = 108;
						 
						input.seek(index12_75);
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
