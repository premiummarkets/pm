// $ANTLR 3.5 /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g 2013-06-29 21:14:02
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
	public static final int T__69=69;
	public static final int T__70=70;
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
	public static final int EventConditionHolder=17;
	public static final int HigherHighCondition=18;
	public static final int HigherLowCondition=19;
	public static final int HistoricalData=20;
	public static final int InfConstantCondition=21;
	public static final int InfDoubleMapCondition=22;
	public static final int LINE_COMMENT=23;
	public static final int LowerHighCondition=24;
	public static final int LowerLowCondition=25;
	public static final int NOT=26;
	public static final int NotDoubleMapCondition=27;
	public static final int Number=28;
	public static final int NumberToken=29;
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
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:63:7: ( 'for' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:63:9: 'for'
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
	// $ANTLR end "T__51"

	// $ANTLR start "T__52"
	public final void mT__52() throws RecognitionException {
		try {
			int _type = T__52;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:64:7: ( 'goes down more than' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:64:9: 'goes down more than'
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
	// $ANTLR end "T__52"

	// $ANTLR start "T__53"
	public final void mT__53() throws RecognitionException {
		try {
			int _type = T__53;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:65:7: ( 'goes up more than' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:65:9: 'goes up more than'
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
	// $ANTLR end "T__53"

	// $ANTLR start "T__54"
	public final void mT__54() throws RecognitionException {
		try {
			int _type = T__54;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:66:7: ( 'is above historical' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:66:9: 'is above historical'
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
	// $ANTLR end "T__54"

	// $ANTLR start "T__55"
	public final void mT__55() throws RecognitionException {
		try {
			int _type = T__55;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:67:7: ( 'is above threshold' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:67:9: 'is above threshold'
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
	// $ANTLR end "T__55"

	// $ANTLR start "T__56"
	public final void mT__56() throws RecognitionException {
		try {
			int _type = T__56;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:68:7: ( 'is bearish if not bullish' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:68:9: 'is bearish if not bullish'
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
	// $ANTLR end "T__56"

	// $ANTLR start "T__57"
	public final void mT__57() throws RecognitionException {
		try {
			int _type = T__57;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:69:7: ( 'is bearish when' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:69:9: 'is bearish when'
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
	// $ANTLR end "T__57"

	// $ANTLR start "T__58"
	public final void mT__58() throws RecognitionException {
		try {
			int _type = T__58;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:70:7: ( 'is below historical' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:70:9: 'is below historical'
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
	// $ANTLR end "T__58"

	// $ANTLR start "T__59"
	public final void mT__59() throws RecognitionException {
		try {
			int _type = T__59;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:71:7: ( 'is below threshold' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:71:9: 'is below threshold'
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
	// $ANTLR end "T__59"

	// $ANTLR start "T__60"
	public final void mT__60() throws RecognitionException {
		try {
			int _type = T__60;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:72:7: ( 'is bullish when' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:72:9: 'is bullish when'
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
	// $ANTLR end "T__60"

	// $ANTLR start "T__61"
	public final void mT__61() throws RecognitionException {
		try {
			int _type = T__61;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:73:7: ( 'makes a higher high spanning' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:73:9: 'makes a higher high spanning'
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
	// $ANTLR end "T__61"

	// $ANTLR start "T__62"
	public final void mT__62() throws RecognitionException {
		try {
			int _type = T__62;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:74:7: ( 'makes a higher low spanning' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:74:9: 'makes a higher low spanning'
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
	// $ANTLR end "T__62"

	// $ANTLR start "T__63"
	public final void mT__63() throws RecognitionException {
		try {
			int _type = T__63;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:75:7: ( 'makes a lower high spanning' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:75:9: 'makes a lower high spanning'
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
	// $ANTLR end "T__63"

	// $ANTLR start "T__64"
	public final void mT__64() throws RecognitionException {
		try {
			int _type = T__64;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:76:7: ( 'makes a lower low spanning' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:76:9: 'makes a lower low spanning'
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
	// $ANTLR end "T__64"

	// $ANTLR start "T__65"
	public final void mT__65() throws RecognitionException {
		try {
			int _type = T__65;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:77:7: ( 'more than' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:77:9: 'more than'
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
	// $ANTLR end "T__65"

	// $ANTLR start "T__66"
	public final void mT__66() throws RecognitionException {
		try {
			int _type = T__66;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:78:7: ( 'over' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:78:9: 'over'
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
	// $ANTLR end "T__66"

	// $ANTLR start "T__67"
	public final void mT__67() throws RecognitionException {
		try {
			int _type = T__67;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:79:7: ( 'reverses down' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:79:9: 'reverses down'
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
	// $ANTLR end "T__67"

	// $ANTLR start "T__68"
	public final void mT__68() throws RecognitionException {
		try {
			int _type = T__68;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:80:7: ( 'reverses up' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:80:9: 'reverses up'
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
	// $ANTLR end "T__68"

	// $ANTLR start "T__69"
	public final void mT__69() throws RecognitionException {
		try {
			int _type = T__69;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:81:7: ( 'smoothing threshold' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:81:9: 'smoothing threshold'
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
	// $ANTLR end "T__69"

	// $ANTLR start "T__70"
	public final void mT__70() throws RecognitionException {
		try {
			int _type = T__70;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:82:7: ( 'spanning' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:82:9: 'spanning'
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
	// $ANTLR end "T__70"

	// $ANTLR start "Operation"
	public final void mOperation() throws RecognitionException {
		try {
			int _type = Operation;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:239:7: ({...}? => ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )+ )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:239:9: {...}? => ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )+
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
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:239:60: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )+
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

	// $ANTLR start "NumberToken"
	public final void mNumberToken() throws RecognitionException {
		try {
			int _type = NumberToken;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:243:7: ( ( '-' )? ( '0' .. '9' )+ ( '.' ( '0' .. '9' )+ )? )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:243:10: ( '-' )? ( '0' .. '9' )+ ( '.' ( '0' .. '9' )+ )?
			{
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:243:10: ( '-' )?
			int alt2=2;
			int LA2_0 = input.LA(1);
			if ( (LA2_0=='-') ) {
				alt2=1;
			}
			switch (alt2) {
				case 1 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:243:11: '-'
					{
					match('-'); 
					}
					break;

			}

			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:243:17: ( '0' .. '9' )+
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

			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:243:29: ( '.' ( '0' .. '9' )+ )?
			int alt5=2;
			int LA5_0 = input.LA(1);
			if ( (LA5_0=='.') ) {
				alt5=1;
			}
			switch (alt5) {
				case 1 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:243:30: '.' ( '0' .. '9' )+
					{
					match('.'); 
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:243:34: ( '0' .. '9' )+
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
	// $ANTLR end "NumberToken"

	// $ANTLR start "HistoricalData"
	public final void mHistoricalData() throws RecognitionException {
		try {
			int _type = HistoricalData;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:247:4: ({...}? => ( 'open' | 'close' | 'high' | 'low' | 'volume' ) )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:247:6: {...}? => ( 'open' | 'close' | 'high' | 'low' | 'volume' )
			{
			if ( !((runtimeHistoryOpAhead())) ) {
				throw new FailedPredicateException(input, "HistoricalData", "runtimeHistoryOpAhead()");
			}
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:247:36: ( 'open' | 'close' | 'high' | 'low' | 'volume' )
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
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:247:38: 'open'
					{
					match("open"); 

					}
					break;
				case 2 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:247:47: 'close'
					{
					match("close"); 

					}
					break;
				case 3 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:247:57: 'high'
					{
					match("high"); 

					}
					break;
				case 4 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:247:66: 'low'
					{
					match("low"); 

					}
					break;
				case 5 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:247:75: 'volume'
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
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:251:7: ( ( ( ' ' )+ ) )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:251:9: ( ( ' ' )+ )
			{
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:251:9: ( ( ' ' )+ )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:251:10: ( ' ' )+
			{
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:251:10: ( ' ' )+
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
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:251:10: ' '
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
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:255:6: ( ( 'a' .. 'z' | 'A' .. 'Z' )+ )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:255:8: ( 'a' .. 'z' | 'A' .. 'Z' )+
			{
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:255:8: ( 'a' .. 'z' | 'A' .. 'Z' )+
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
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:260:5: ( ( '\\r' | '\\t' | '\\u000C' | '\\n' ) )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:260:7: ( '\\r' | '\\t' | '\\u000C' | '\\n' )
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
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:263:5: ( '/*' ( . )* '*/' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:263:7: '/*' ( . )* '*/'
			{
			match("/*"); 

			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:263:12: ( . )*
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
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:263:12: .
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
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:266:5: ( '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:266:7: '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n'
			{
			match("//"); 

			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:266:12: (~ ( '\\n' | '\\r' ) )*
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

			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:266:26: ( '\\r' )?
			int alt11=2;
			int LA11_0 = input.LA(1);
			if ( (LA11_0=='\r') ) {
				alt11=1;
			}
			switch (alt11) {
				case 1 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:266:26: '\\r'
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
		// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:8: ( AND | CLOSEPARENTEHSIS | COMMA | DAYS | NOT | OPENPARENTEHSIS | OR | PERCENT | T__45 | T__46 | T__47 | T__48 | T__49 | T__50 | T__51 | T__52 | T__53 | T__54 | T__55 | T__56 | T__57 | T__58 | T__59 | T__60 | T__61 | T__62 | T__63 | T__64 | T__65 | T__66 | T__67 | T__68 | T__69 | T__70 | Operation | NumberToken | HistoricalData | WhiteChar | Tcheat | WS | COMMENT | LINE_COMMENT )
		int alt12=42;
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
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:217: T__69
				{
				mT__69(); 

				}
				break;
			case 34 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:223: T__70
				{
				mT__70(); 

				}
				break;
			case 35 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:229: Operation
				{
				mOperation(); 

				}
				break;
			case 36 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:239: NumberToken
				{
				mNumberToken(); 

				}
				break;
			case 37 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:251: HistoricalData
				{
				mHistoricalData(); 

				}
				break;
			case 38 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:266: WhiteChar
				{
				mWhiteChar(); 

				}
				break;
			case 39 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:276: Tcheat
				{
				mTcheat(); 

				}
				break;
			case 40 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:283: WS
				{
				mWS(); 

				}
				break;
			case 41 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:286: COMMENT
				{
				mCOMMENT(); 

				}
				break;
			case 42 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:294: LINE_COMMENT
				{
				mLINE_COMMENT(); 

				}
				break;

		}
	}


	protected DFA12 dfa12 = new DFA12(this);
	static final String DFA12_eotS =
		"\1\uffff\1\34\2\uffff\2\34\1\uffff\1\34\1\uffff\11\34\1\uffff\3\34\4\uffff"+
		"\1\63\1\64\1\uffff\1\66\1\70\1\71\1\73\1\75\1\77\1\101\1\103\1\105\1\107"+
		"\1\111\1\113\1\115\1\117\1\121\1\123\1\125\1\127\1\131\2\uffff\1\132\2"+
		"\uffff\1\134\1\uffff\1\135\2\uffff\1\137\1\uffff\1\141\1\uffff\1\143\1"+
		"\uffff\1\145\1\uffff\1\147\1\uffff\1\150\1\uffff\1\152\3\uffff\1\156\1"+
		"\uffff\1\160\1\uffff\1\162\1\uffff\1\164\1\uffff\1\166\1\uffff\1\170\1"+
		"\uffff\1\171\1\uffff\1\173\2\uffff\1\174\2\uffff\1\175\1\uffff\1\176\1"+
		"\uffff\1\u0080\1\uffff\1\u0082\1\uffff\1\u0084\2\uffff\1\u0086\3\uffff"+
		"\1\u008b\1\uffff\1\u008d\1\uffff\1\u008f\1\uffff\1\u0091\1\uffff\1\u0093"+
		"\1\uffff\1\u0094\2\uffff\1\u0097\4\uffff\1\u0099\1\uffff\1\u009a\1\uffff"+
		"\1\u009c\6\uffff\1\u00a3\3\uffff\1\u00a5\1\uffff\1\u00a7\1\uffff\1\u00a9"+
		"\3\uffff\1\u00ab\1\uffff\1\u00ad\2\uffff\1\u00af\10\uffff\1\u00b5\1\uffff"+
		"\1\u00b7\1\uffff\1\u00b9\1\uffff\1\u00ba\1\uffff\1\u00bc\7\uffff\1\u00c4"+
		"\1\uffff\1\u00c6\1\uffff\1\u00c8\12\uffff\1\u00d1\1\uffff\1\u00d3\1\uffff"+
		"\1\u00d4\12\uffff\1\u00e1\47\uffff";
	static final String DFA12_eofS =
		"\u00fa\uffff";
	static final String DFA12_minS =
		"\1\11\1\60\2\uffff\2\60\1\uffff\1\60\1\uffff\11\60\1\uffff\3\60\3\uffff"+
		"\1\52\2\60\1\uffff\12\60\1\40\10\60\2\uffff\1\60\2\0\1\60\1\0\1\60\1\0"+
		"\1\uffff\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1"+
		"\0\1\141\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60"+
		"\1\0\1\60\1\0\1\uffff\1\60\1\0\1\uffff\1\60\1\0\1\60\1\0\1\60\1\0\1\60"+
		"\1\0\1\60\1\0\1\uffff\1\40\1\0\1\142\1\145\1\60\1\0\1\40\1\0\1\60\1\0"+
		"\1\60\1\0\1\60\1\0\1\60\2\0\1\60\1\0\2\uffff\1\0\1\60\1\0\1\60\1\0\1\60"+
		"\1\0\1\144\1\0\1\157\1\141\1\uffff\1\40\1\0\1\uffff\1\0\1\60\1\0\1\60"+
		"\1\0\1\60\2\0\1\uffff\1\60\1\0\1\60\2\0\1\40\1\0\2\uffff\1\166\1\162\1"+
		"\157\1\141\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\40\1\0\1\150\1\0"+
		"\1\145\1\151\1\167\1\40\1\60\1\0\1\60\1\0\1\60\2\0\1\144\1\0\2\uffff\1"+
		"\40\1\163\1\40\1\150\1\40\1\0\1\60\1\0\1\60\1\0\1\157\1\160\3\150\1\151"+
		"\1\157\1\144\1\0\1\40\1\0\1\uffff\1\167\1\40\2\uffff\1\40\2\uffff\1\147"+
		"\1\167\3\uffff\1\0\1\156\1\150\1\151\1\150\1\145\1\40\4\uffff\1\145\1"+
		"\162\1\150\1\162\1\40\2\uffff\1\40\2\150\4\uffff";
	static final String DFA12_maxS =
		"\2\172\2\uffff\2\172\1\uffff\1\172\1\uffff\11\172\1\uffff\3\172\3\uffff"+
		"\1\57\2\172\1\uffff\23\172\2\uffff\1\172\2\0\1\172\1\0\1\172\1\0\1\uffff"+
		"\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0"+
		"\1\142\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0"+
		"\1\172\1\0\1\172\1\0\1\uffff\1\172\1\0\1\uffff\1\172\1\0\1\172\1\0\1\172"+
		"\1\0\1\172\1\0\1\172\1\0\1\uffff\1\172\1\0\1\142\1\165\1\172\1\0\1\172"+
		"\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\2\0\1\172\1\0\2\uffff\1\0\1\172"+
		"\1\0\1\172\1\0\1\172\1\0\1\165\1\0\1\157\1\154\1\uffff\1\172\1\0\1\uffff"+
		"\1\0\1\172\1\0\1\172\1\0\1\172\2\0\1\uffff\1\172\1\0\1\172\2\0\1\172\1"+
		"\0\2\uffff\1\166\1\162\1\157\1\141\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1"+
		"\172\1\0\1\172\1\0\1\164\1\0\1\145\1\151\1\167\1\40\1\172\1\0\1\172\1"+
		"\0\1\172\2\0\1\165\1\0\2\uffff\1\40\1\163\1\40\1\154\1\172\1\0\1\172\1"+
		"\0\1\172\1\0\1\157\1\160\1\164\1\150\1\164\1\151\1\157\1\165\1\0\1\172"+
		"\1\0\1\uffff\1\167\1\40\2\uffff\1\40\2\uffff\1\147\1\167\3\uffff\1\0\1"+
		"\156\1\164\1\167\1\150\1\145\1\40\4\uffff\1\145\1\162\1\164\1\162\1\40"+
		"\2\uffff\1\40\2\154\4\uffff";
	static final String DFA12_acceptS =
		"\2\uffff\1\2\1\3\2\uffff\1\6\1\uffff\1\10\11\uffff\1\44\3\uffff\1\46\1"+
		"\43\1\50\3\uffff\1\47\23\uffff\1\51\1\52\7\uffff\1\7\40\uffff\1\1\2\uffff"+
		"\1\5\12\uffff\1\17\23\uffff\1\4\1\36\13\uffff\1\30\2\uffff\1\35\10\uffff"+
		"\1\45\7\uffff\1\20\1\21\36\uffff\1\15\1\16\25\uffff\1\42\2\uffff\1\22"+
		"\1\23\1\uffff\1\26\1\27\2\uffff\1\37\1\40\1\41\7\uffff\1\13\1\14\1\24"+
		"\1\25\5\uffff\1\11\1\12\3\uffff\1\33\1\34\1\31\1\32";
	static final String DFA12_specialS =
		"\1\153\1\0\2\uffff\1\6\1\3\1\uffff\1\51\1\uffff\1\17\1\43\1\u0084\1\13"+
		"\1\166\1\163\1\10\1\12\1\2\1\uffff\1\7\1\5\1\15\4\uffff\1\u008c\1\156"+
		"\1\uffff\1\u0085\1\u008a\1\1\1\u009b\1\47\1\34\1\52\1\164\1\11\1\u0081"+
		"\1\161\1\u0099\1\167\1\22\1\u008f\1\u0086\1\41\1\42\1\44\2\uffff\1\30"+
		"\1\107\1\106\1\u0083\1\105\1\4\1\104\1\uffff\1\u009a\1\103\1\50\1\102"+
		"\1\35\1\101\1\53\1\100\1\155\1\77\1\u009e\1\76\1\u0080\1\75\1\uffff\1"+
		"\74\1\u0098\1\73\1\170\1\72\1\16\1\71\1\u008e\1\70\1\u0089\1\67\1\37\1"+
		"\66\1\u009c\1\65\1\46\1\64\1\uffff\1\40\1\63\1\uffff\1\u0082\1\62\1\u0095"+
		"\1\110\1\20\1\111\1\36\1\112\1\157\1\113\1\uffff\1\172\1\114\2\uffff\1"+
		"\u0097\1\115\1\165\1\116\1\21\1\117\1\u008d\1\120\1\u008b\1\121\1\u009d"+
		"\1\122\1\173\1\45\1\123\2\uffff\1\174\1\23\1\124\1\u009f\1\125\1\160\1"+
		"\126\1\uffff\1\127\3\uffff\1\u0096\1\130\1\uffff\1\131\1\27\1\132\1\u0093"+
		"\1\133\1\u0087\1\134\1\175\1\uffff\1\14\1\135\1\26\1\136\1\176\1\162\1"+
		"\137\6\uffff\1\140\1\31\1\141\1\u0092\1\142\1\u0088\1\143\1\171\1\144"+
		"\1\32\1\145\1\uffff\1\146\4\uffff\1\24\1\147\1\u0091\1\150\1\33\1\151"+
		"\1\177\1\uffff\1\152\6\uffff\1\25\1\61\1\u0090\1\60\1\154\1\57\10\uffff"+
		"\1\56\1\u0094\1\55\15\uffff\1\54\30\uffff}>";
	static final String[] DFA12_transitionS = {
			"\2\30\1\uffff\2\30\22\uffff\1\26\4\uffff\1\10\2\uffff\1\6\1\2\3\uffff"+
			"\1\22\1\uffff\1\31\12\22\1\uffff\1\3\5\uffff\32\25\4\uffff\1\27\1\uffff"+
			"\1\1\1\25\1\11\1\4\1\12\1\13\1\14\1\21\1\15\2\25\1\23\1\16\1\5\1\7\2"+
			"\25\1\17\1\20\2\25\1\24\4\25",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\15\33\1\32\14\33",
			"",
			"",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\1\35\31\33",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\16\33\1\36\13\33",
			"",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\17\33\1\41\1\33\1\37\3\33"+
			"\1\40\4\33",
			"",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\13\33\1\43\5\33\1\42\10\33",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\20\33\1\44\11\33",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\16\33\1\45\13\33",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\16\33\1\46\13\33",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\22\33\1\47\7\33",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\1\50\15\33\1\51\13\33",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\4\33\1\52\25\33",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\14\33\1\53\2\33\1\54\12\33",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\10\33\1\55\21\33",
			"",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\16\33\1\56\13\33",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\16\33\1\57\13\33",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\32\33",
			"",
			"",
			"",
			"\1\60\4\uffff\1\61",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\3\33\1\62\26\33",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\32\33",
			"",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\30\33\1\65\1\33",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\23\33\1\67\6\33",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\32\33",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\4\33\1\72\25\33",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\4\33\1\74\25\33",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\16\33\1\76\13\33",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\16\33\1\100\13\33",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\24\33\1\102\5\33",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\21\33\1\104\10\33",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\4\33\1\106\25\33",
			"\1\110\17\uffff\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\32\33",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\12\33\1\112\17\33",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\21\33\1\114\10\33",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\25\33\1\116\4\33",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\16\33\1\120\13\33",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\1\122\31\33",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\6\33\1\124\23\33",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\26\33\1\126\3\33",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\13\33\1\130\16\33",
			"",
			"",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\32\33",
			"\1\uffff",
			"\1\uffff",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\22\33\1\133\7\33",
			"\1\uffff",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\32\33",
			"\1\uffff",
			"",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\21\33\1\136\10\33",
			"\1\uffff",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\15\33\1\140\14\33",
			"\1\uffff",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\22\33\1\142\7\33",
			"\1\uffff",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\22\33\1\144\7\33",
			"\1\uffff",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\1\146\31\33",
			"\1\uffff",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\32\33",
			"\1\uffff",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\22\33\1\151\7\33",
			"\1\uffff",
			"\1\153\1\154",
			"\1\uffff",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\4\33\1\155\25\33",
			"\1\uffff",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\4\33\1\157\25\33",
			"\1\uffff",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\4\33\1\161\25\33",
			"\1\uffff",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\16\33\1\163\13\33",
			"\1\uffff",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\15\33\1\165\14\33",
			"\1\uffff",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\7\33\1\167\22\33",
			"\1\uffff",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\32\33",
			"\1\uffff",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\24\33\1\172\5\33",
			"\1\uffff",
			"",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\32\33",
			"\1\uffff",
			"",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\32\33",
			"\1\uffff",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\32\33",
			"\1\uffff",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\22\33\1\177\7\33",
			"\1\uffff",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\4\33\1\u0081\25\33",
			"\1\uffff",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\13\33\1\u0083\16\33",
			"\1\uffff",
			"",
			"\1\u0085\17\uffff\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\32\33",
			"\1\uffff",
			"\1\u0087",
			"\1\u0088\17\uffff\1\u0089",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\22\33\1\u008a\7\33",
			"\1\uffff",
			"\1\u008c\17\uffff\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\32\33",
			"\1\uffff",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\21\33\1\u008e\10\33",
			"\1\uffff",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\23\33\1\u0090\6\33",
			"\1\uffff",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\15\33\1\u0092\14\33",
			"\1\uffff",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\32\33",
			"\1\uffff",
			"\1\uffff",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\14\33\1\u0096\15\33",
			"\1\uffff",
			"",
			"",
			"\1\uffff",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\4\33\1\u0098\25\33",
			"\1\uffff",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\32\33",
			"\1\uffff",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\22\33\1\u009b\7\33",
			"\1\uffff",
			"\1\u009d\20\uffff\1\u009e",
			"\1\uffff",
			"\1\u009f",
			"\1\u00a0\12\uffff\1\u00a1",
			"",
			"\1\u00a2\17\uffff\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\32\33",
			"\1\uffff",
			"",
			"\1\uffff",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\22\33\1\u00a4\7\33",
			"\1\uffff",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\7\33\1\u00a6\22\33",
			"\1\uffff",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\10\33\1\u00a8\21\33",
			"\1\uffff",
			"\1\uffff",
			"",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\4\33\1\u00aa\25\33",
			"\1\uffff",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\22\33\1\u00ac\7\33",
			"\1\uffff",
			"\1\uffff",
			"\1\u00ae\17\uffff\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\32\33",
			"\1\uffff",
			"",
			"",
			"\1\u00b0",
			"\1\u00b1",
			"\1\u00b2",
			"\1\u00b3",
			"\1\uffff",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\4\33\1\u00b4\25\33",
			"\1\uffff",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\10\33\1\u00b6\21\33",
			"\1\uffff",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\15\33\1\u00b8\14\33",
			"\1\uffff",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\32\33",
			"\1\uffff",
			"\1\u00bb\17\uffff\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\32\33",
			"\1\uffff",
			"\1\u00bd\13\uffff\1\u00be",
			"\1\uffff",
			"\1\u00bf",
			"\1\u00c0",
			"\1\u00c1",
			"\1\u00c2",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\22\33\1\u00c3\7\33",
			"\1\uffff",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\15\33\1\u00c5\14\33",
			"\1\uffff",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\6\33\1\u00c7\23\33",
			"\1\uffff",
			"\1\uffff",
			"\1\u00c9\20\uffff\1\u00ca",
			"\1\uffff",
			"",
			"",
			"\1\u00cb",
			"\1\u00cc",
			"\1\u00cd",
			"\1\u00ce\3\uffff\1\u00cf",
			"\1\u00d0\17\uffff\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\32\33",
			"\1\uffff",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\6\33\1\u00d2\23\33",
			"\1\uffff",
			"\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\32\33",
			"\1\uffff",
			"\1\u00d5",
			"\1\u00d6",
			"\1\u00d7\13\uffff\1\u00d8",
			"\1\u00d9",
			"\1\u00da\13\uffff\1\u00db",
			"\1\u00dc",
			"\1\u00dd",
			"\1\u00de\20\uffff\1\u00df",
			"\1\uffff",
			"\1\u00e0\17\uffff\12\27\7\uffff\32\33\4\uffff\1\27\1\uffff\32\33",
			"\1\uffff",
			"",
			"\1\u00e2",
			"\1\u00e3",
			"",
			"",
			"\1\u00e4",
			"",
			"",
			"\1\u00e5",
			"\1\u00e6",
			"",
			"",
			"",
			"\1\uffff",
			"\1\u00e7",
			"\1\u00e8\13\uffff\1\u00e9",
			"\1\u00ea\15\uffff\1\u00eb",
			"\1\u00ec",
			"\1\u00ed",
			"\1\u00ee",
			"",
			"",
			"",
			"",
			"\1\u00ef",
			"\1\u00f0",
			"\1\u00f1\13\uffff\1\u00f2",
			"\1\u00f3",
			"\1\u00f4",
			"",
			"",
			"\1\u00f5",
			"\1\u00f6\3\uffff\1\u00f7",
			"\1\u00f8\3\uffff\1\u00f9",
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
			return "1:1: Tokens : ( AND | CLOSEPARENTEHSIS | COMMA | DAYS | NOT | OPENPARENTEHSIS | OR | PERCENT | T__45 | T__46 | T__47 | T__48 | T__49 | T__50 | T__51 | T__52 | T__53 | T__54 | T__55 | T__56 | T__57 | T__58 | T__59 | T__60 | T__61 | T__62 | T__63 | T__64 | T__65 | T__66 | T__67 | T__68 | T__69 | T__70 | Operation | NumberToken | HistoricalData | WhiteChar | Tcheat | WS | COMMENT | LINE_COMMENT );";
		}
		@Override
		public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
			IntStream input = _input;
			int _s = s;
			switch ( s ) {
					case 0 : 
						int LA12_1 = input.LA(1);
						 
						int index12_1 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_1=='n') ) {s = 26;}
						else if ( ((LA12_1 >= 'A' && LA12_1 <= 'Z')||(LA12_1 >= 'a' && LA12_1 <= 'm')||(LA12_1 >= 'o' && LA12_1 <= 'z')) ) {s = 27;}
						else if ( ((LA12_1 >= '0' && LA12_1 <= '9')||LA12_1=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 28;
						 
						input.seek(index12_1);
						if ( s>=0 ) return s;
						break;

					case 1 : 
						int LA12_31 = input.LA(1);
						 
						int index12_31 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA12_31 >= 'A' && LA12_31 <= 'Z')||(LA12_31 >= 'a' && LA12_31 <= 'z')) ) {s = 27;}
						else if ( ((LA12_31 >= '0' && LA12_31 <= '9')||LA12_31=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 57;
						 
						input.seek(index12_31);
						if ( s>=0 ) return s;
						break;

					case 2 : 
						int LA12_17 = input.LA(1);
						 
						int index12_17 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_17=='i') ) {s = 45;}
						else if ( ((LA12_17 >= 'A' && LA12_17 <= 'Z')||(LA12_17 >= 'a' && LA12_17 <= 'h')||(LA12_17 >= 'j' && LA12_17 <= 'z')) ) {s = 27;}
						else if ( ((LA12_17 >= '0' && LA12_17 <= '9')||LA12_17=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 28;
						 
						input.seek(index12_17);
						if ( s>=0 ) return s;
						break;

					case 3 : 
						int LA12_5 = input.LA(1);
						 
						int index12_5 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_5=='o') ) {s = 30;}
						else if ( ((LA12_5 >= 'A' && LA12_5 <= 'Z')||(LA12_5 >= 'a' && LA12_5 <= 'n')||(LA12_5 >= 'p' && LA12_5 <= 'z')) ) {s = 27;}
						else if ( ((LA12_5 >= '0' && LA12_5 <= '9')||LA12_5=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 28;
						 
						input.seek(index12_5);
						if ( s>=0 ) return s;
						break;

					case 4 : 
						int LA12_55 = input.LA(1);
						 
						int index12_55 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA12_55 >= 'A' && LA12_55 <= 'Z')||(LA12_55 >= 'a' && LA12_55 <= 'z')) ) {s = 27;}
						else if ( ((LA12_55 >= '0' && LA12_55 <= '9')||LA12_55=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 93;
						 
						input.seek(index12_55);
						if ( s>=0 ) return s;
						break;

					case 5 : 
						int LA12_20 = input.LA(1);
						 
						int index12_20 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_20=='o') ) {s = 47;}
						else if ( ((LA12_20 >= 'A' && LA12_20 <= 'Z')||(LA12_20 >= 'a' && LA12_20 <= 'n')||(LA12_20 >= 'p' && LA12_20 <= 'z')) ) {s = 27;}
						else if ( ((LA12_20 >= '0' && LA12_20 <= '9')||LA12_20=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 28;
						 
						input.seek(index12_20);
						if ( s>=0 ) return s;
						break;

					case 6 : 
						int LA12_4 = input.LA(1);
						 
						int index12_4 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_4=='a') ) {s = 29;}
						else if ( ((LA12_4 >= 'A' && LA12_4 <= 'Z')||(LA12_4 >= 'b' && LA12_4 <= 'z')) ) {s = 27;}
						else if ( ((LA12_4 >= '0' && LA12_4 <= '9')||LA12_4=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 28;
						 
						input.seek(index12_4);
						if ( s>=0 ) return s;
						break;

					case 7 : 
						int LA12_19 = input.LA(1);
						 
						int index12_19 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_19=='o') ) {s = 46;}
						else if ( ((LA12_19 >= 'A' && LA12_19 <= 'Z')||(LA12_19 >= 'a' && LA12_19 <= 'n')||(LA12_19 >= 'p' && LA12_19 <= 'z')) ) {s = 27;}
						else if ( ((LA12_19 >= '0' && LA12_19 <= '9')||LA12_19=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 28;
						 
						input.seek(index12_19);
						if ( s>=0 ) return s;
						break;

					case 8 : 
						int LA12_15 = input.LA(1);
						 
						int index12_15 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_15=='e') ) {s = 42;}
						else if ( ((LA12_15 >= 'A' && LA12_15 <= 'Z')||(LA12_15 >= 'a' && LA12_15 <= 'd')||(LA12_15 >= 'f' && LA12_15 <= 'z')) ) {s = 27;}
						else if ( ((LA12_15 >= '0' && LA12_15 <= '9')||LA12_15=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 28;
						 
						input.seek(index12_15);
						if ( s>=0 ) return s;
						break;

					case 9 : 
						int LA12_37 = input.LA(1);
						 
						int index12_37 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_37=='r') ) {s = 68;}
						else if ( ((LA12_37 >= 'A' && LA12_37 <= 'Z')||(LA12_37 >= 'a' && LA12_37 <= 'q')||(LA12_37 >= 's' && LA12_37 <= 'z')) ) {s = 27;}
						else if ( ((LA12_37 >= '0' && LA12_37 <= '9')||LA12_37=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 69;
						 
						input.seek(index12_37);
						if ( s>=0 ) return s;
						break;

					case 10 : 
						int LA12_16 = input.LA(1);
						 
						int index12_16 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_16=='m') ) {s = 43;}
						else if ( (LA12_16=='p') ) {s = 44;}
						else if ( ((LA12_16 >= 'A' && LA12_16 <= 'Z')||(LA12_16 >= 'a' && LA12_16 <= 'l')||(LA12_16 >= 'n' && LA12_16 <= 'o')||(LA12_16 >= 'q' && LA12_16 <= 'z')) ) {s = 27;}
						else if ( ((LA12_16 >= '0' && LA12_16 <= '9')||LA12_16=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 28;
						 
						input.seek(index12_16);
						if ( s>=0 ) return s;
						break;

					case 11 : 
						int LA12_12 = input.LA(1);
						 
						int index12_12 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_12=='o') ) {s = 38;}
						else if ( ((LA12_12 >= 'A' && LA12_12 <= 'Z')||(LA12_12 >= 'a' && LA12_12 <= 'n')||(LA12_12 >= 'p' && LA12_12 <= 'z')) ) {s = 27;}
						else if ( ((LA12_12 >= '0' && LA12_12 <= '9')||LA12_12=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 28;
						 
						input.seek(index12_12);
						if ( s>=0 ) return s;
						break;

					case 12 : 
						int LA12_150 = input.LA(1);
						 
						int index12_150 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_150=='e') ) {s = 170;}
						else if ( ((LA12_150 >= 'A' && LA12_150 <= 'Z')||(LA12_150 >= 'a' && LA12_150 <= 'd')||(LA12_150 >= 'f' && LA12_150 <= 'z')) ) {s = 27;}
						else if ( ((LA12_150 >= '0' && LA12_150 <= '9')||LA12_150=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 171;
						 
						input.seek(index12_150);
						if ( s>=0 ) return s;
						break;

					case 13 : 
						int LA12_21 = input.LA(1);
						 
						int index12_21 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA12_21 >= 'A' && LA12_21 <= 'Z')||(LA12_21 >= 'a' && LA12_21 <= 'z')) ) {s = 27;}
						else if ( ((LA12_21 >= '0' && LA12_21 <= '9')||LA12_21=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 28;
						 
						input.seek(index12_21);
						if ( s>=0 ) return s;
						break;

					case 14 : 
						int LA12_78 = input.LA(1);
						 
						int index12_78 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_78=='e') ) {s = 113;}
						else if ( ((LA12_78 >= 'A' && LA12_78 <= 'Z')||(LA12_78 >= 'a' && LA12_78 <= 'd')||(LA12_78 >= 'f' && LA12_78 <= 'z')) ) {s = 27;}
						else if ( ((LA12_78 >= '0' && LA12_78 <= '9')||LA12_78=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 114;
						 
						input.seek(index12_78);
						if ( s>=0 ) return s;
						break;

					case 15 : 
						int LA12_9 = input.LA(1);
						 
						int index12_9 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_9=='r') ) {s = 34;}
						else if ( (LA12_9=='l') ) {s = 35;}
						else if ( ((LA12_9 >= 'A' && LA12_9 <= 'Z')||(LA12_9 >= 'a' && LA12_9 <= 'k')||(LA12_9 >= 'm' && LA12_9 <= 'q')||(LA12_9 >= 's' && LA12_9 <= 'z')) ) {s = 27;}
						else if ( ((LA12_9 >= '0' && LA12_9 <= '9')||LA12_9=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 28;
						 
						input.seek(index12_9);
						if ( s>=0 ) return s;
						break;

					case 16 : 
						int LA12_98 = input.LA(1);
						 
						int index12_98 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_98=='s') ) {s = 127;}
						else if ( ((LA12_98 >= 'A' && LA12_98 <= 'Z')||(LA12_98 >= 'a' && LA12_98 <= 'r')||(LA12_98 >= 't' && LA12_98 <= 'z')) ) {s = 27;}
						else if ( ((LA12_98 >= '0' && LA12_98 <= '9')||LA12_98=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 128;
						 
						input.seek(index12_98);
						if ( s>=0 ) return s;
						break;

					case 17 : 
						int LA12_113 = input.LA(1);
						 
						int index12_113 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_113=='r') ) {s = 142;}
						else if ( ((LA12_113 >= 'A' && LA12_113 <= 'Z')||(LA12_113 >= 'a' && LA12_113 <= 'q')||(LA12_113 >= 's' && LA12_113 <= 'z')) ) {s = 27;}
						else if ( ((LA12_113 >= '0' && LA12_113 <= '9')||LA12_113=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 143;
						 
						input.seek(index12_113);
						if ( s>=0 ) return s;
						break;

					case 18 : 
						int LA12_42 = input.LA(1);
						 
						int index12_42 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_42=='v') ) {s = 78;}
						else if ( ((LA12_42 >= 'A' && LA12_42 <= 'Z')||(LA12_42 >= 'a' && LA12_42 <= 'u')||(LA12_42 >= 'w' && LA12_42 <= 'z')) ) {s = 27;}
						else if ( ((LA12_42 >= '0' && LA12_42 <= '9')||LA12_42=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 79;
						 
						input.seek(index12_42);
						if ( s>=0 ) return s;
						break;

					case 19 : 
						int LA12_127 = input.LA(1);
						 
						int index12_127 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_127=='e') ) {s = 152;}
						else if ( ((LA12_127 >= 'A' && LA12_127 <= 'Z')||(LA12_127 >= 'a' && LA12_127 <= 'd')||(LA12_127 >= 'f' && LA12_127 <= 'z')) ) {s = 27;}
						else if ( ((LA12_127 >= '0' && LA12_127 <= '9')||LA12_127=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 153;
						 
						input.seek(index12_127);
						if ( s>=0 ) return s;
						break;

					case 20 : 
						int LA12_180 = input.LA(1);
						 
						int index12_180 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_180=='s') ) {s = 195;}
						else if ( ((LA12_180 >= 'A' && LA12_180 <= 'Z')||(LA12_180 >= 'a' && LA12_180 <= 'r')||(LA12_180 >= 't' && LA12_180 <= 'z')) ) {s = 27;}
						else if ( ((LA12_180 >= '0' && LA12_180 <= '9')||LA12_180=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 196;
						 
						input.seek(index12_180);
						if ( s>=0 ) return s;
						break;

					case 21 : 
						int LA12_195 = input.LA(1);
						 
						int index12_195 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_195==' ') ) {s = 208;}
						else if ( ((LA12_195 >= 'A' && LA12_195 <= 'Z')||(LA12_195 >= 'a' && LA12_195 <= 'z')) ) {s = 27;}
						else if ( ((LA12_195 >= '0' && LA12_195 <= '9')||LA12_195=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 209;
						 
						input.seek(index12_195);
						if ( s>=0 ) return s;
						break;

					case 22 : 
						int LA12_152 = input.LA(1);
						 
						int index12_152 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_152=='s') ) {s = 172;}
						else if ( ((LA12_152 >= 'A' && LA12_152 <= 'Z')||(LA12_152 >= 'a' && LA12_152 <= 'r')||(LA12_152 >= 't' && LA12_152 <= 'z')) ) {s = 27;}
						else if ( ((LA12_152 >= '0' && LA12_152 <= '9')||LA12_152=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 173;
						 
						input.seek(index12_152);
						if ( s>=0 ) return s;
						break;

					case 23 : 
						int LA12_142 = input.LA(1);
						 
						int index12_142 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_142=='s') ) {s = 164;}
						else if ( ((LA12_142 >= 'A' && LA12_142 <= 'Z')||(LA12_142 >= 'a' && LA12_142 <= 'r')||(LA12_142 >= 't' && LA12_142 <= 'z')) ) {s = 27;}
						else if ( ((LA12_142 >= '0' && LA12_142 <= '9')||LA12_142=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 165;
						 
						input.seek(index12_142);
						if ( s>=0 ) return s;
						break;

					case 24 : 
						int LA12_50 = input.LA(1);
						 
						int index12_50 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA12_50 >= 'A' && LA12_50 <= 'Z')||(LA12_50 >= 'a' && LA12_50 <= 'z')) ) {s = 27;}
						else if ( ((LA12_50 >= '0' && LA12_50 <= '9')||LA12_50=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 90;
						 
						input.seek(index12_50);
						if ( s>=0 ) return s;
						break;

					case 25 : 
						int LA12_164 = input.LA(1);
						 
						int index12_164 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_164=='e') ) {s = 180;}
						else if ( ((LA12_164 >= 'A' && LA12_164 <= 'Z')||(LA12_164 >= 'a' && LA12_164 <= 'd')||(LA12_164 >= 'f' && LA12_164 <= 'z')) ) {s = 27;}
						else if ( ((LA12_164 >= '0' && LA12_164 <= '9')||LA12_164=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 181;
						 
						input.seek(index12_164);
						if ( s>=0 ) return s;
						break;

					case 26 : 
						int LA12_172 = input.LA(1);
						 
						int index12_172 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_172==' ') ) {s = 187;}
						else if ( ((LA12_172 >= 'A' && LA12_172 <= 'Z')||(LA12_172 >= 'a' && LA12_172 <= 'z')) ) {s = 27;}
						else if ( ((LA12_172 >= '0' && LA12_172 <= '9')||LA12_172=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 188;
						 
						input.seek(index12_172);
						if ( s>=0 ) return s;
						break;

					case 27 : 
						int LA12_184 = input.LA(1);
						 
						int index12_184 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_184=='g') ) {s = 199;}
						else if ( ((LA12_184 >= 'A' && LA12_184 <= 'Z')||(LA12_184 >= 'a' && LA12_184 <= 'f')||(LA12_184 >= 'h' && LA12_184 <= 'z')) ) {s = 27;}
						else if ( ((LA12_184 >= '0' && LA12_184 <= '9')||LA12_184=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 200;
						 
						input.seek(index12_184);
						if ( s>=0 ) return s;
						break;

					case 28 : 
						int LA12_34 = input.LA(1);
						 
						int index12_34 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_34=='o') ) {s = 62;}
						else if ( ((LA12_34 >= 'A' && LA12_34 <= 'Z')||(LA12_34 >= 'a' && LA12_34 <= 'n')||(LA12_34 >= 'p' && LA12_34 <= 'z')) ) {s = 27;}
						else if ( ((LA12_34 >= '0' && LA12_34 <= '9')||LA12_34=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 63;
						 
						input.seek(index12_34);
						if ( s>=0 ) return s;
						break;

					case 29 : 
						int LA12_62 = input.LA(1);
						 
						int index12_62 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_62=='s') ) {s = 98;}
						else if ( ((LA12_62 >= 'A' && LA12_62 <= 'Z')||(LA12_62 >= 'a' && LA12_62 <= 'r')||(LA12_62 >= 't' && LA12_62 <= 'z')) ) {s = 27;}
						else if ( ((LA12_62 >= '0' && LA12_62 <= '9')||LA12_62=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 99;
						 
						input.seek(index12_62);
						if ( s>=0 ) return s;
						break;

					case 30 : 
						int LA12_100 = input.LA(1);
						 
						int index12_100 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_100=='e') ) {s = 129;}
						else if ( ((LA12_100 >= 'A' && LA12_100 <= 'Z')||(LA12_100 >= 'a' && LA12_100 <= 'd')||(LA12_100 >= 'f' && LA12_100 <= 'z')) ) {s = 27;}
						else if ( ((LA12_100 >= '0' && LA12_100 <= '9')||LA12_100=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 130;
						 
						input.seek(index12_100);
						if ( s>=0 ) return s;
						break;

					case 31 : 
						int LA12_84 = input.LA(1);
						 
						int index12_84 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_84=='h') ) {s = 119;}
						else if ( ((LA12_84 >= 'A' && LA12_84 <= 'Z')||(LA12_84 >= 'a' && LA12_84 <= 'g')||(LA12_84 >= 'i' && LA12_84 <= 'z')) ) {s = 27;}
						else if ( ((LA12_84 >= '0' && LA12_84 <= '9')||LA12_84=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 120;
						 
						input.seek(index12_84);
						if ( s>=0 ) return s;
						break;

					case 32 : 
						int LA12_91 = input.LA(1);
						 
						int index12_91 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA12_91 >= 'A' && LA12_91 <= 'Z')||(LA12_91 >= 'a' && LA12_91 <= 'z')) ) {s = 27;}
						else if ( ((LA12_91 >= '0' && LA12_91 <= '9')||LA12_91=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 124;
						 
						input.seek(index12_91);
						if ( s>=0 ) return s;
						break;

					case 33 : 
						int LA12_45 = input.LA(1);
						 
						int index12_45 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_45=='g') ) {s = 84;}
						else if ( ((LA12_45 >= 'A' && LA12_45 <= 'Z')||(LA12_45 >= 'a' && LA12_45 <= 'f')||(LA12_45 >= 'h' && LA12_45 <= 'z')) ) {s = 27;}
						else if ( ((LA12_45 >= '0' && LA12_45 <= '9')||LA12_45=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 85;
						 
						input.seek(index12_45);
						if ( s>=0 ) return s;
						break;

					case 34 : 
						int LA12_46 = input.LA(1);
						 
						int index12_46 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_46=='w') ) {s = 86;}
						else if ( ((LA12_46 >= 'A' && LA12_46 <= 'Z')||(LA12_46 >= 'a' && LA12_46 <= 'v')||(LA12_46 >= 'x' && LA12_46 <= 'z')) ) {s = 27;}
						else if ( ((LA12_46 >= '0' && LA12_46 <= '9')||LA12_46=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 87;
						 
						input.seek(index12_46);
						if ( s>=0 ) return s;
						break;

					case 35 : 
						int LA12_10 = input.LA(1);
						 
						int index12_10 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_10=='q') ) {s = 36;}
						else if ( ((LA12_10 >= 'A' && LA12_10 <= 'Z')||(LA12_10 >= 'a' && LA12_10 <= 'p')||(LA12_10 >= 'r' && LA12_10 <= 'z')) ) {s = 27;}
						else if ( ((LA12_10 >= '0' && LA12_10 <= '9')||LA12_10=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 28;
						 
						input.seek(index12_10);
						if ( s>=0 ) return s;
						break;

					case 36 : 
						int LA12_47 = input.LA(1);
						 
						int index12_47 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_47=='l') ) {s = 88;}
						else if ( ((LA12_47 >= 'A' && LA12_47 <= 'Z')||(LA12_47 >= 'a' && LA12_47 <= 'k')||(LA12_47 >= 'm' && LA12_47 <= 'z')) ) {s = 27;}
						else if ( ((LA12_47 >= '0' && LA12_47 <= '9')||LA12_47=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 89;
						 
						input.seek(index12_47);
						if ( s>=0 ) return s;
						break;

					case 37 : 
						int LA12_122 = input.LA(1);
						 
						int index12_122 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_122=='m') ) {s = 150;}
						else if ( ((LA12_122 >= 'A' && LA12_122 <= 'Z')||(LA12_122 >= 'a' && LA12_122 <= 'l')||(LA12_122 >= 'n' && LA12_122 <= 'z')) ) {s = 27;}
						else if ( ((LA12_122 >= '0' && LA12_122 <= '9')||LA12_122=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 151;
						 
						input.seek(index12_122);
						if ( s>=0 ) return s;
						break;

					case 38 : 
						int LA12_88 = input.LA(1);
						 
						int index12_88 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_88=='u') ) {s = 122;}
						else if ( ((LA12_88 >= 'A' && LA12_88 <= 'Z')||(LA12_88 >= 'a' && LA12_88 <= 't')||(LA12_88 >= 'v' && LA12_88 <= 'z')) ) {s = 27;}
						else if ( ((LA12_88 >= '0' && LA12_88 <= '9')||LA12_88=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 123;
						 
						input.seek(index12_88);
						if ( s>=0 ) return s;
						break;

					case 39 : 
						int LA12_33 = input.LA(1);
						 
						int index12_33 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_33=='e') ) {s = 60;}
						else if ( ((LA12_33 >= 'A' && LA12_33 <= 'Z')||(LA12_33 >= 'a' && LA12_33 <= 'd')||(LA12_33 >= 'f' && LA12_33 <= 'z')) ) {s = 27;}
						else if ( ((LA12_33 >= '0' && LA12_33 <= '9')||LA12_33=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 61;
						 
						input.seek(index12_33);
						if ( s>=0 ) return s;
						break;

					case 40 : 
						int LA12_60 = input.LA(1);
						 
						int index12_60 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_60=='n') ) {s = 96;}
						else if ( ((LA12_60 >= 'A' && LA12_60 <= 'Z')||(LA12_60 >= 'a' && LA12_60 <= 'm')||(LA12_60 >= 'o' && LA12_60 <= 'z')) ) {s = 27;}
						else if ( ((LA12_60 >= '0' && LA12_60 <= '9')||LA12_60=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 97;
						 
						input.seek(index12_60);
						if ( s>=0 ) return s;
						break;

					case 41 : 
						int LA12_7 = input.LA(1);
						 
						int index12_7 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_7=='r') ) {s = 31;}
						else if ( (LA12_7=='v') ) {s = 32;}
						else if ( (LA12_7=='p') ) {s = 33;}
						else if ( ((LA12_7 >= 'A' && LA12_7 <= 'Z')||(LA12_7 >= 'a' && LA12_7 <= 'o')||LA12_7=='q'||(LA12_7 >= 's' && LA12_7 <= 'u')||(LA12_7 >= 'w' && LA12_7 <= 'z')) ) {s = 27;}
						else if ( ((LA12_7 >= '0' && LA12_7 <= '9')||LA12_7=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 28;
						 
						input.seek(index12_7);
						if ( s>=0 ) return s;
						break;

					case 42 : 
						int LA12_35 = input.LA(1);
						 
						int index12_35 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_35=='o') ) {s = 64;}
						else if ( ((LA12_35 >= 'A' && LA12_35 <= 'Z')||(LA12_35 >= 'a' && LA12_35 <= 'n')||(LA12_35 >= 'p' && LA12_35 <= 'z')) ) {s = 27;}
						else if ( ((LA12_35 >= '0' && LA12_35 <= '9')||LA12_35=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 65;
						 
						input.seek(index12_35);
						if ( s>=0 ) return s;
						break;

					case 43 : 
						int LA12_64 = input.LA(1);
						 
						int index12_64 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_64=='s') ) {s = 100;}
						else if ( ((LA12_64 >= 'A' && LA12_64 <= 'Z')||(LA12_64 >= 'a' && LA12_64 <= 'r')||(LA12_64 >= 't' && LA12_64 <= 'z')) ) {s = 27;}
						else if ( ((LA12_64 >= '0' && LA12_64 <= '9')||LA12_64=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 101;
						 
						input.seek(index12_64);
						if ( s>=0 ) return s;
						break;

					case 44 : 
						int LA12_225 = input.LA(1);
						 
						int index12_225 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 23;}
						else if ( (true) ) {s = 28;}
						 
						input.seek(index12_225);
						if ( s>=0 ) return s;
						break;

					case 45 : 
						int LA12_211 = input.LA(1);
						 
						int index12_211 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 23;}
						else if ( (true) ) {s = 28;}
						 
						input.seek(index12_211);
						if ( s>=0 ) return s;
						break;

					case 46 : 
						int LA12_209 = input.LA(1);
						 
						int index12_209 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 23;}
						else if ( (true) ) {s = 28;}
						 
						input.seek(index12_209);
						if ( s>=0 ) return s;
						break;

					case 47 : 
						int LA12_200 = input.LA(1);
						 
						int index12_200 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 23;}
						else if ( (true) ) {s = 28;}
						 
						input.seek(index12_200);
						if ( s>=0 ) return s;
						break;

					case 48 : 
						int LA12_198 = input.LA(1);
						 
						int index12_198 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 23;}
						else if ( (true) ) {s = 28;}
						 
						input.seek(index12_198);
						if ( s>=0 ) return s;
						break;

					case 49 : 
						int LA12_196 = input.LA(1);
						 
						int index12_196 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 23;}
						else if ( (true) ) {s = 28;}
						 
						input.seek(index12_196);
						if ( s>=0 ) return s;
						break;

					case 50 : 
						int LA12_95 = input.LA(1);
						 
						int index12_95 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 23;}
						else if ( (true) ) {s = 28;}
						 
						input.seek(index12_95);
						if ( s>=0 ) return s;
						break;

					case 51 : 
						int LA12_92 = input.LA(1);
						 
						int index12_92 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 23;}
						else if ( (true) ) {s = 28;}
						 
						input.seek(index12_92);
						if ( s>=0 ) return s;
						break;

					case 52 : 
						int LA12_89 = input.LA(1);
						 
						int index12_89 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 23;}
						else if ( (true) ) {s = 28;}
						 
						input.seek(index12_89);
						if ( s>=0 ) return s;
						break;

					case 53 : 
						int LA12_87 = input.LA(1);
						 
						int index12_87 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 23;}
						else if ( (true) ) {s = 28;}
						 
						input.seek(index12_87);
						if ( s>=0 ) return s;
						break;

					case 54 : 
						int LA12_85 = input.LA(1);
						 
						int index12_85 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 23;}
						else if ( (true) ) {s = 28;}
						 
						input.seek(index12_85);
						if ( s>=0 ) return s;
						break;

					case 55 : 
						int LA12_83 = input.LA(1);
						 
						int index12_83 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 23;}
						else if ( (true) ) {s = 28;}
						 
						input.seek(index12_83);
						if ( s>=0 ) return s;
						break;

					case 56 : 
						int LA12_81 = input.LA(1);
						 
						int index12_81 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 23;}
						else if ( (true) ) {s = 28;}
						 
						input.seek(index12_81);
						if ( s>=0 ) return s;
						break;

					case 57 : 
						int LA12_79 = input.LA(1);
						 
						int index12_79 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 23;}
						else if ( (true) ) {s = 28;}
						 
						input.seek(index12_79);
						if ( s>=0 ) return s;
						break;

					case 58 : 
						int LA12_77 = input.LA(1);
						 
						int index12_77 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 23;}
						else if ( (true) ) {s = 28;}
						 
						input.seek(index12_77);
						if ( s>=0 ) return s;
						break;

					case 59 : 
						int LA12_75 = input.LA(1);
						 
						int index12_75 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 23;}
						else if ( (true) ) {s = 28;}
						 
						input.seek(index12_75);
						if ( s>=0 ) return s;
						break;

					case 60 : 
						int LA12_73 = input.LA(1);
						 
						int index12_73 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 23;}
						else if ( (true) ) {s = 28;}
						 
						input.seek(index12_73);
						if ( s>=0 ) return s;
						break;

					case 61 : 
						int LA12_71 = input.LA(1);
						 
						int index12_71 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 23;}
						else if ( (true) ) {s = 28;}
						 
						input.seek(index12_71);
						if ( s>=0 ) return s;
						break;

					case 62 : 
						int LA12_69 = input.LA(1);
						 
						int index12_69 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 23;}
						else if ( (true) ) {s = 28;}
						 
						input.seek(index12_69);
						if ( s>=0 ) return s;
						break;

					case 63 : 
						int LA12_67 = input.LA(1);
						 
						int index12_67 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 23;}
						else if ( (true) ) {s = 28;}
						 
						input.seek(index12_67);
						if ( s>=0 ) return s;
						break;

					case 64 : 
						int LA12_65 = input.LA(1);
						 
						int index12_65 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 23;}
						else if ( (true) ) {s = 28;}
						 
						input.seek(index12_65);
						if ( s>=0 ) return s;
						break;

					case 65 : 
						int LA12_63 = input.LA(1);
						 
						int index12_63 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 23;}
						else if ( (true) ) {s = 28;}
						 
						input.seek(index12_63);
						if ( s>=0 ) return s;
						break;

					case 66 : 
						int LA12_61 = input.LA(1);
						 
						int index12_61 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 23;}
						else if ( (true) ) {s = 28;}
						 
						input.seek(index12_61);
						if ( s>=0 ) return s;
						break;

					case 67 : 
						int LA12_59 = input.LA(1);
						 
						int index12_59 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 23;}
						else if ( (true) ) {s = 28;}
						 
						input.seek(index12_59);
						if ( s>=0 ) return s;
						break;

					case 68 : 
						int LA12_56 = input.LA(1);
						 
						int index12_56 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 23;}
						else if ( (true) ) {s = 28;}
						 
						input.seek(index12_56);
						if ( s>=0 ) return s;
						break;

					case 69 : 
						int LA12_54 = input.LA(1);
						 
						int index12_54 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 23;}
						else if ( (true) ) {s = 28;}
						 
						input.seek(index12_54);
						if ( s>=0 ) return s;
						break;

					case 70 : 
						int LA12_52 = input.LA(1);
						 
						int index12_52 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 23;}
						else if ( (true) ) {s = 28;}
						 
						input.seek(index12_52);
						if ( s>=0 ) return s;
						break;

					case 71 : 
						int LA12_51 = input.LA(1);
						 
						int index12_51 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 23;}
						else if ( (true) ) {s = 28;}
						 
						input.seek(index12_51);
						if ( s>=0 ) return s;
						break;

					case 72 : 
						int LA12_97 = input.LA(1);
						 
						int index12_97 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 23;}
						else if ( (true) ) {s = 28;}
						 
						input.seek(index12_97);
						if ( s>=0 ) return s;
						break;

					case 73 : 
						int LA12_99 = input.LA(1);
						 
						int index12_99 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 23;}
						else if ( (true) ) {s = 28;}
						 
						input.seek(index12_99);
						if ( s>=0 ) return s;
						break;

					case 74 : 
						int LA12_101 = input.LA(1);
						 
						int index12_101 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 23;}
						else if ( (true) ) {s = 28;}
						 
						input.seek(index12_101);
						if ( s>=0 ) return s;
						break;

					case 75 : 
						int LA12_103 = input.LA(1);
						 
						int index12_103 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 23;}
						else if ( (true) ) {s = 28;}
						 
						input.seek(index12_103);
						if ( s>=0 ) return s;
						break;

					case 76 : 
						int LA12_106 = input.LA(1);
						 
						int index12_106 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 23;}
						else if ( (true) ) {s = 28;}
						 
						input.seek(index12_106);
						if ( s>=0 ) return s;
						break;

					case 77 : 
						int LA12_110 = input.LA(1);
						 
						int index12_110 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 23;}
						else if ( (true) ) {s = 28;}
						 
						input.seek(index12_110);
						if ( s>=0 ) return s;
						break;

					case 78 : 
						int LA12_112 = input.LA(1);
						 
						int index12_112 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 23;}
						else if ( (true) ) {s = 28;}
						 
						input.seek(index12_112);
						if ( s>=0 ) return s;
						break;

					case 79 : 
						int LA12_114 = input.LA(1);
						 
						int index12_114 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 23;}
						else if ( (true) ) {s = 28;}
						 
						input.seek(index12_114);
						if ( s>=0 ) return s;
						break;

					case 80 : 
						int LA12_116 = input.LA(1);
						 
						int index12_116 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 23;}
						else if ( (true) ) {s = 28;}
						 
						input.seek(index12_116);
						if ( s>=0 ) return s;
						break;

					case 81 : 
						int LA12_118 = input.LA(1);
						 
						int index12_118 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 23;}
						else if ( (true) ) {s = 28;}
						 
						input.seek(index12_118);
						if ( s>=0 ) return s;
						break;

					case 82 : 
						int LA12_120 = input.LA(1);
						 
						int index12_120 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 23;}
						else if ( (true) ) {s = 28;}
						 
						input.seek(index12_120);
						if ( s>=0 ) return s;
						break;

					case 83 : 
						int LA12_123 = input.LA(1);
						 
						int index12_123 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 23;}
						else if ( (true) ) {s = 28;}
						 
						input.seek(index12_123);
						if ( s>=0 ) return s;
						break;

					case 84 : 
						int LA12_128 = input.LA(1);
						 
						int index12_128 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 23;}
						else if ( (true) ) {s = 28;}
						 
						input.seek(index12_128);
						if ( s>=0 ) return s;
						break;

					case 85 : 
						int LA12_130 = input.LA(1);
						 
						int index12_130 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 23;}
						else if ( (true) ) {s = 28;}
						 
						input.seek(index12_130);
						if ( s>=0 ) return s;
						break;

					case 86 : 
						int LA12_132 = input.LA(1);
						 
						int index12_132 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 23;}
						else if ( (true) ) {s = 28;}
						 
						input.seek(index12_132);
						if ( s>=0 ) return s;
						break;

					case 87 : 
						int LA12_134 = input.LA(1);
						 
						int index12_134 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 23;}
						else if ( (true) ) {s = 28;}
						 
						input.seek(index12_134);
						if ( s>=0 ) return s;
						break;

					case 88 : 
						int LA12_139 = input.LA(1);
						 
						int index12_139 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 23;}
						else if ( (true) ) {s = 28;}
						 
						input.seek(index12_139);
						if ( s>=0 ) return s;
						break;

					case 89 : 
						int LA12_141 = input.LA(1);
						 
						int index12_141 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 23;}
						else if ( (true) ) {s = 28;}
						 
						input.seek(index12_141);
						if ( s>=0 ) return s;
						break;

					case 90 : 
						int LA12_143 = input.LA(1);
						 
						int index12_143 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 23;}
						else if ( (true) ) {s = 28;}
						 
						input.seek(index12_143);
						if ( s>=0 ) return s;
						break;

					case 91 : 
						int LA12_145 = input.LA(1);
						 
						int index12_145 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 23;}
						else if ( (true) ) {s = 28;}
						 
						input.seek(index12_145);
						if ( s>=0 ) return s;
						break;

					case 92 : 
						int LA12_147 = input.LA(1);
						 
						int index12_147 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 23;}
						else if ( (true) ) {s = 28;}
						 
						input.seek(index12_147);
						if ( s>=0 ) return s;
						break;

					case 93 : 
						int LA12_151 = input.LA(1);
						 
						int index12_151 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 23;}
						else if ( (true) ) {s = 28;}
						 
						input.seek(index12_151);
						if ( s>=0 ) return s;
						break;

					case 94 : 
						int LA12_153 = input.LA(1);
						 
						int index12_153 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 23;}
						else if ( (true) ) {s = 28;}
						 
						input.seek(index12_153);
						if ( s>=0 ) return s;
						break;

					case 95 : 
						int LA12_156 = input.LA(1);
						 
						int index12_156 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 23;}
						else if ( (true) ) {s = 28;}
						 
						input.seek(index12_156);
						if ( s>=0 ) return s;
						break;

					case 96 : 
						int LA12_163 = input.LA(1);
						 
						int index12_163 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 23;}
						else if ( (true) ) {s = 28;}
						 
						input.seek(index12_163);
						if ( s>=0 ) return s;
						break;

					case 97 : 
						int LA12_165 = input.LA(1);
						 
						int index12_165 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 23;}
						else if ( (true) ) {s = 28;}
						 
						input.seek(index12_165);
						if ( s>=0 ) return s;
						break;

					case 98 : 
						int LA12_167 = input.LA(1);
						 
						int index12_167 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 23;}
						else if ( (true) ) {s = 28;}
						 
						input.seek(index12_167);
						if ( s>=0 ) return s;
						break;

					case 99 : 
						int LA12_169 = input.LA(1);
						 
						int index12_169 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 23;}
						else if ( (true) ) {s = 28;}
						 
						input.seek(index12_169);
						if ( s>=0 ) return s;
						break;

					case 100 : 
						int LA12_171 = input.LA(1);
						 
						int index12_171 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 23;}
						else if ( (true) ) {s = 28;}
						 
						input.seek(index12_171);
						if ( s>=0 ) return s;
						break;

					case 101 : 
						int LA12_173 = input.LA(1);
						 
						int index12_173 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 23;}
						else if ( (true) ) {s = 28;}
						 
						input.seek(index12_173);
						if ( s>=0 ) return s;
						break;

					case 102 : 
						int LA12_175 = input.LA(1);
						 
						int index12_175 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 23;}
						else if ( (true) ) {s = 28;}
						 
						input.seek(index12_175);
						if ( s>=0 ) return s;
						break;

					case 103 : 
						int LA12_181 = input.LA(1);
						 
						int index12_181 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 23;}
						else if ( (true) ) {s = 28;}
						 
						input.seek(index12_181);
						if ( s>=0 ) return s;
						break;

					case 104 : 
						int LA12_183 = input.LA(1);
						 
						int index12_183 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 23;}
						else if ( (true) ) {s = 28;}
						 
						input.seek(index12_183);
						if ( s>=0 ) return s;
						break;

					case 105 : 
						int LA12_185 = input.LA(1);
						 
						int index12_185 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 23;}
						else if ( (true) ) {s = 28;}
						 
						input.seek(index12_185);
						if ( s>=0 ) return s;
						break;

					case 106 : 
						int LA12_188 = input.LA(1);
						 
						int index12_188 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 23;}
						else if ( (true) ) {s = 28;}
						 
						input.seek(index12_188);
						if ( s>=0 ) return s;
						break;

					case 107 : 
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
						else if ( (LA12_0=='f') ) {s = 11;}
						else if ( (LA12_0=='g') ) {s = 12;}
						else if ( (LA12_0=='i') ) {s = 13;}
						else if ( (LA12_0=='m') ) {s = 14;}
						else if ( (LA12_0=='r') ) {s = 15;}
						else if ( (LA12_0=='s') ) {s = 16;}
						else if ( (LA12_0=='h') ) {s = 17;}
						else if ( (LA12_0=='-'||(LA12_0 >= '0' && LA12_0 <= '9')) ) {s = 18;}
						else if ( (LA12_0=='l') ) {s = 19;}
						else if ( (LA12_0=='v') ) {s = 20;}
						else if ( ((LA12_0 >= 'A' && LA12_0 <= 'Z')||LA12_0=='b'||(LA12_0 >= 'j' && LA12_0 <= 'k')||(LA12_0 >= 'p' && LA12_0 <= 'q')||(LA12_0 >= 't' && LA12_0 <= 'u')||(LA12_0 >= 'w' && LA12_0 <= 'z')) ) {s = 21;}
						else if ( (LA12_0==' ') ) {s = 22;}
						else if ( (LA12_0=='_') && ((runtimeOpAhead()))) {s = 23;}
						else if ( ((LA12_0 >= '\t' && LA12_0 <= '\n')||(LA12_0 >= '\f' && LA12_0 <= '\r')) ) {s = 24;}
						else if ( (LA12_0=='/') ) {s = 25;}
						 
						input.seek(index12_0);
						if ( s>=0 ) return s;
						break;

					case 108 : 
						int LA12_199 = input.LA(1);
						 
						int index12_199 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA12_199 >= 'A' && LA12_199 <= 'Z')||(LA12_199 >= 'a' && LA12_199 <= 'z')) ) {s = 27;}
						else if ( ((LA12_199 >= '0' && LA12_199 <= '9')||LA12_199=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 212;
						 
						input.seek(index12_199);
						if ( s>=0 ) return s;
						break;

					case 109 : 
						int LA12_66 = input.LA(1);
						 
						int index12_66 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_66=='a') ) {s = 102;}
						else if ( ((LA12_66 >= 'A' && LA12_66 <= 'Z')||(LA12_66 >= 'b' && LA12_66 <= 'z')) ) {s = 27;}
						else if ( ((LA12_66 >= '0' && LA12_66 <= '9')||LA12_66=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 103;
						 
						input.seek(index12_66);
						if ( s>=0 ) return s;
						break;

					case 110 : 
						int LA12_27 = input.LA(1);
						 
						int index12_27 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA12_27 >= 'A' && LA12_27 <= 'Z')||(LA12_27 >= 'a' && LA12_27 <= 'z')) ) {s = 27;}
						else if ( ((LA12_27 >= '0' && LA12_27 <= '9')||LA12_27=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 52;
						 
						input.seek(index12_27);
						if ( s>=0 ) return s;
						break;

					case 111 : 
						int LA12_102 = input.LA(1);
						 
						int index12_102 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_102=='l') ) {s = 131;}
						else if ( ((LA12_102 >= 'A' && LA12_102 <= 'Z')||(LA12_102 >= 'a' && LA12_102 <= 'k')||(LA12_102 >= 'm' && LA12_102 <= 'z')) ) {s = 27;}
						else if ( ((LA12_102 >= '0' && LA12_102 <= '9')||LA12_102=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 132;
						 
						input.seek(index12_102);
						if ( s>=0 ) return s;
						break;

					case 112 : 
						int LA12_131 = input.LA(1);
						 
						int index12_131 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_131=='s') ) {s = 155;}
						else if ( ((LA12_131 >= 'A' && LA12_131 <= 'Z')||(LA12_131 >= 'a' && LA12_131 <= 'r')||(LA12_131 >= 't' && LA12_131 <= 'z')) ) {s = 27;}
						else if ( ((LA12_131 >= '0' && LA12_131 <= '9')||LA12_131=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 156;
						 
						input.seek(index12_131);
						if ( s>=0 ) return s;
						break;

					case 113 : 
						int LA12_39 = input.LA(1);
						 
						int index12_39 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_39==' ') ) {s = 72;}
						else if ( ((LA12_39 >= 'A' && LA12_39 <= 'Z')||(LA12_39 >= 'a' && LA12_39 <= 'z')) ) {s = 27;}
						else if ( ((LA12_39 >= '0' && LA12_39 <= '9')||LA12_39=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 73;
						 
						input.seek(index12_39);
						if ( s>=0 ) return s;
						break;

					case 114 : 
						int LA12_155 = input.LA(1);
						 
						int index12_155 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_155==' ') ) {s = 174;}
						else if ( ((LA12_155 >= 'A' && LA12_155 <= 'Z')||(LA12_155 >= 'a' && LA12_155 <= 'z')) ) {s = 27;}
						else if ( ((LA12_155 >= '0' && LA12_155 <= '9')||LA12_155=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 175;
						 
						input.seek(index12_155);
						if ( s>=0 ) return s;
						break;

					case 115 : 
						int LA12_14 = input.LA(1);
						 
						int index12_14 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_14=='a') ) {s = 40;}
						else if ( (LA12_14=='o') ) {s = 41;}
						else if ( ((LA12_14 >= 'A' && LA12_14 <= 'Z')||(LA12_14 >= 'b' && LA12_14 <= 'n')||(LA12_14 >= 'p' && LA12_14 <= 'z')) ) {s = 27;}
						else if ( ((LA12_14 >= '0' && LA12_14 <= '9')||LA12_14=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 28;
						 
						input.seek(index12_14);
						if ( s>=0 ) return s;
						break;

					case 116 : 
						int LA12_36 = input.LA(1);
						 
						int index12_36 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_36=='u') ) {s = 66;}
						else if ( ((LA12_36 >= 'A' && LA12_36 <= 'Z')||(LA12_36 >= 'a' && LA12_36 <= 't')||(LA12_36 >= 'v' && LA12_36 <= 'z')) ) {s = 27;}
						else if ( ((LA12_36 >= '0' && LA12_36 <= '9')||LA12_36=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 67;
						 
						input.seek(index12_36);
						if ( s>=0 ) return s;
						break;

					case 117 : 
						int LA12_111 = input.LA(1);
						 
						int index12_111 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_111==' ') ) {s = 140;}
						else if ( ((LA12_111 >= 'A' && LA12_111 <= 'Z')||(LA12_111 >= 'a' && LA12_111 <= 'z')) ) {s = 27;}
						else if ( ((LA12_111 >= '0' && LA12_111 <= '9')||LA12_111=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 141;
						 
						input.seek(index12_111);
						if ( s>=0 ) return s;
						break;

					case 118 : 
						int LA12_13 = input.LA(1);
						 
						int index12_13 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_13=='s') ) {s = 39;}
						else if ( ((LA12_13 >= 'A' && LA12_13 <= 'Z')||(LA12_13 >= 'a' && LA12_13 <= 'r')||(LA12_13 >= 't' && LA12_13 <= 'z')) ) {s = 27;}
						else if ( ((LA12_13 >= '0' && LA12_13 <= '9')||LA12_13=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 28;
						 
						input.seek(index12_13);
						if ( s>=0 ) return s;
						break;

					case 119 : 
						int LA12_41 = input.LA(1);
						 
						int index12_41 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_41=='r') ) {s = 76;}
						else if ( ((LA12_41 >= 'A' && LA12_41 <= 'Z')||(LA12_41 >= 'a' && LA12_41 <= 'q')||(LA12_41 >= 's' && LA12_41 <= 'z')) ) {s = 27;}
						else if ( ((LA12_41 >= '0' && LA12_41 <= '9')||LA12_41=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 77;
						 
						input.seek(index12_41);
						if ( s>=0 ) return s;
						break;

					case 120 : 
						int LA12_76 = input.LA(1);
						 
						int index12_76 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_76=='e') ) {s = 111;}
						else if ( ((LA12_76 >= 'A' && LA12_76 <= 'Z')||(LA12_76 >= 'a' && LA12_76 <= 'd')||(LA12_76 >= 'f' && LA12_76 <= 'z')) ) {s = 27;}
						else if ( ((LA12_76 >= '0' && LA12_76 <= '9')||LA12_76=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 112;
						 
						input.seek(index12_76);
						if ( s>=0 ) return s;
						break;

					case 121 : 
						int LA12_170 = input.LA(1);
						 
						int index12_170 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA12_170 >= 'A' && LA12_170 <= 'Z')||(LA12_170 >= 'a' && LA12_170 <= 'z')) ) {s = 27;}
						else if ( ((LA12_170 >= '0' && LA12_170 <= '9')||LA12_170=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 186;
						 
						input.seek(index12_170);
						if ( s>=0 ) return s;
						break;

					case 122 : 
						int LA12_105 = input.LA(1);
						 
						int index12_105 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_105==' ') ) {s = 133;}
						else if ( ((LA12_105 >= 'A' && LA12_105 <= 'Z')||(LA12_105 >= 'a' && LA12_105 <= 'z')) ) {s = 27;}
						else if ( ((LA12_105 >= '0' && LA12_105 <= '9')||LA12_105=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 134;
						 
						input.seek(index12_105);
						if ( s>=0 ) return s;
						break;

					case 123 : 
						int LA12_121 = input.LA(1);
						 
						int index12_121 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 23;}
						else if ( ((runtimeHistoryOpAhead())) ) {s = 149;}
						else if ( (true) ) {s = 28;}
						 
						input.seek(index12_121);
						if ( s>=0 ) return s;
						break;

					case 124 : 
						int LA12_126 = input.LA(1);
						 
						int index12_126 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 23;}
						else if ( ((runtimeHistoryOpAhead())) ) {s = 149;}
						else if ( (true) ) {s = 28;}
						 
						input.seek(index12_126);
						if ( s>=0 ) return s;
						break;

					case 125 : 
						int LA12_148 = input.LA(1);
						 
						int index12_148 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 23;}
						else if ( ((runtimeHistoryOpAhead())) ) {s = 149;}
						else if ( (true) ) {s = 28;}
						 
						input.seek(index12_148);
						if ( s>=0 ) return s;
						break;

					case 126 : 
						int LA12_154 = input.LA(1);
						 
						int index12_154 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 23;}
						else if ( ((runtimeHistoryOpAhead())) ) {s = 149;}
						else if ( (true) ) {s = 28;}
						 
						input.seek(index12_154);
						if ( s>=0 ) return s;
						break;

					case 127 : 
						int LA12_186 = input.LA(1);
						 
						int index12_186 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 23;}
						else if ( ((runtimeHistoryOpAhead())) ) {s = 149;}
						else if ( (true) ) {s = 28;}
						 
						input.seek(index12_186);
						if ( s>=0 ) return s;
						break;

					case 128 : 
						int LA12_70 = input.LA(1);
						 
						int index12_70 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_70=='s') ) {s = 105;}
						else if ( ((LA12_70 >= 'A' && LA12_70 <= 'Z')||(LA12_70 >= 'a' && LA12_70 <= 'r')||(LA12_70 >= 't' && LA12_70 <= 'z')) ) {s = 27;}
						else if ( ((LA12_70 >= '0' && LA12_70 <= '9')||LA12_70=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 106;
						 
						input.seek(index12_70);
						if ( s>=0 ) return s;
						break;

					case 129 : 
						int LA12_38 = input.LA(1);
						 
						int index12_38 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_38=='e') ) {s = 70;}
						else if ( ((LA12_38 >= 'A' && LA12_38 <= 'Z')||(LA12_38 >= 'a' && LA12_38 <= 'd')||(LA12_38 >= 'f' && LA12_38 <= 'z')) ) {s = 27;}
						else if ( ((LA12_38 >= '0' && LA12_38 <= '9')||LA12_38=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 71;
						 
						input.seek(index12_38);
						if ( s>=0 ) return s;
						break;

					case 130 : 
						int LA12_94 = input.LA(1);
						 
						int index12_94 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA12_94 >= 'A' && LA12_94 <= 'Z')||(LA12_94 >= 'a' && LA12_94 <= 'z')) ) {s = 27;}
						else if ( ((LA12_94 >= '0' && LA12_94 <= '9')||LA12_94=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 125;
						 
						input.seek(index12_94);
						if ( s>=0 ) return s;
						break;

					case 131 : 
						int LA12_53 = input.LA(1);
						 
						int index12_53 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_53=='s') ) {s = 91;}
						else if ( ((LA12_53 >= 'A' && LA12_53 <= 'Z')||(LA12_53 >= 'a' && LA12_53 <= 'r')||(LA12_53 >= 't' && LA12_53 <= 'z')) ) {s = 27;}
						else if ( ((LA12_53 >= '0' && LA12_53 <= '9')||LA12_53=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 92;
						 
						input.seek(index12_53);
						if ( s>=0 ) return s;
						break;

					case 132 : 
						int LA12_11 = input.LA(1);
						 
						int index12_11 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_11=='o') ) {s = 37;}
						else if ( ((LA12_11 >= 'A' && LA12_11 <= 'Z')||(LA12_11 >= 'a' && LA12_11 <= 'n')||(LA12_11 >= 'p' && LA12_11 <= 'z')) ) {s = 27;}
						else if ( ((LA12_11 >= '0' && LA12_11 <= '9')||LA12_11=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 28;
						 
						input.seek(index12_11);
						if ( s>=0 ) return s;
						break;

					case 133 : 
						int LA12_29 = input.LA(1);
						 
						int index12_29 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_29=='y') ) {s = 53;}
						else if ( ((LA12_29 >= 'A' && LA12_29 <= 'Z')||(LA12_29 >= 'a' && LA12_29 <= 'x')||LA12_29=='z') ) {s = 27;}
						else if ( ((LA12_29 >= '0' && LA12_29 <= '9')||LA12_29=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 54;
						 
						input.seek(index12_29);
						if ( s>=0 ) return s;
						break;

					case 134 : 
						int LA12_44 = input.LA(1);
						 
						int index12_44 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_44=='a') ) {s = 82;}
						else if ( ((LA12_44 >= 'A' && LA12_44 <= 'Z')||(LA12_44 >= 'b' && LA12_44 <= 'z')) ) {s = 27;}
						else if ( ((LA12_44 >= '0' && LA12_44 <= '9')||LA12_44=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 83;
						 
						input.seek(index12_44);
						if ( s>=0 ) return s;
						break;

					case 135 : 
						int LA12_146 = input.LA(1);
						 
						int index12_146 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_146=='i') ) {s = 168;}
						else if ( ((LA12_146 >= 'A' && LA12_146 <= 'Z')||(LA12_146 >= 'a' && LA12_146 <= 'h')||(LA12_146 >= 'j' && LA12_146 <= 'z')) ) {s = 27;}
						else if ( ((LA12_146 >= '0' && LA12_146 <= '9')||LA12_146=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 169;
						 
						input.seek(index12_146);
						if ( s>=0 ) return s;
						break;

					case 136 : 
						int LA12_168 = input.LA(1);
						 
						int index12_168 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_168=='n') ) {s = 184;}
						else if ( ((LA12_168 >= 'A' && LA12_168 <= 'Z')||(LA12_168 >= 'a' && LA12_168 <= 'm')||(LA12_168 >= 'o' && LA12_168 <= 'z')) ) {s = 27;}
						else if ( ((LA12_168 >= '0' && LA12_168 <= '9')||LA12_168=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 185;
						 
						input.seek(index12_168);
						if ( s>=0 ) return s;
						break;

					case 137 : 
						int LA12_82 = input.LA(1);
						 
						int index12_82 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_82=='n') ) {s = 117;}
						else if ( ((LA12_82 >= 'A' && LA12_82 <= 'Z')||(LA12_82 >= 'a' && LA12_82 <= 'm')||(LA12_82 >= 'o' && LA12_82 <= 'z')) ) {s = 27;}
						else if ( ((LA12_82 >= '0' && LA12_82 <= '9')||LA12_82=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 118;
						 
						input.seek(index12_82);
						if ( s>=0 ) return s;
						break;

					case 138 : 
						int LA12_30 = input.LA(1);
						 
						int index12_30 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_30=='t') ) {s = 55;}
						else if ( ((LA12_30 >= 'A' && LA12_30 <= 'Z')||(LA12_30 >= 'a' && LA12_30 <= 's')||(LA12_30 >= 'u' && LA12_30 <= 'z')) ) {s = 27;}
						else if ( ((LA12_30 >= '0' && LA12_30 <= '9')||LA12_30=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 56;
						 
						input.seek(index12_30);
						if ( s>=0 ) return s;
						break;

					case 139 : 
						int LA12_117 = input.LA(1);
						 
						int index12_117 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_117=='n') ) {s = 146;}
						else if ( ((LA12_117 >= 'A' && LA12_117 <= 'Z')||(LA12_117 >= 'a' && LA12_117 <= 'm')||(LA12_117 >= 'o' && LA12_117 <= 'z')) ) {s = 27;}
						else if ( ((LA12_117 >= '0' && LA12_117 <= '9')||LA12_117=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 147;
						 
						input.seek(index12_117);
						if ( s>=0 ) return s;
						break;

					case 140 : 
						int LA12_26 = input.LA(1);
						 
						int index12_26 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_26=='d') ) {s = 50;}
						else if ( ((LA12_26 >= 'A' && LA12_26 <= 'Z')||(LA12_26 >= 'a' && LA12_26 <= 'c')||(LA12_26 >= 'e' && LA12_26 <= 'z')) ) {s = 27;}
						else if ( ((LA12_26 >= '0' && LA12_26 <= '9')||LA12_26=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 51;
						 
						input.seek(index12_26);
						if ( s>=0 ) return s;
						break;

					case 141 : 
						int LA12_115 = input.LA(1);
						 
						int index12_115 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_115=='t') ) {s = 144;}
						else if ( ((LA12_115 >= 'A' && LA12_115 <= 'Z')||(LA12_115 >= 'a' && LA12_115 <= 's')||(LA12_115 >= 'u' && LA12_115 <= 'z')) ) {s = 27;}
						else if ( ((LA12_115 >= '0' && LA12_115 <= '9')||LA12_115=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 145;
						 
						input.seek(index12_115);
						if ( s>=0 ) return s;
						break;

					case 142 : 
						int LA12_80 = input.LA(1);
						 
						int index12_80 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_80=='o') ) {s = 115;}
						else if ( ((LA12_80 >= 'A' && LA12_80 <= 'Z')||(LA12_80 >= 'a' && LA12_80 <= 'n')||(LA12_80 >= 'p' && LA12_80 <= 'z')) ) {s = 27;}
						else if ( ((LA12_80 >= '0' && LA12_80 <= '9')||LA12_80=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 116;
						 
						input.seek(index12_80);
						if ( s>=0 ) return s;
						break;

					case 143 : 
						int LA12_43 = input.LA(1);
						 
						int index12_43 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_43=='o') ) {s = 80;}
						else if ( ((LA12_43 >= 'A' && LA12_43 <= 'Z')||(LA12_43 >= 'a' && LA12_43 <= 'n')||(LA12_43 >= 'p' && LA12_43 <= 'z')) ) {s = 27;}
						else if ( ((LA12_43 >= '0' && LA12_43 <= '9')||LA12_43=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 81;
						 
						input.seek(index12_43);
						if ( s>=0 ) return s;
						break;

					case 144 : 
						int LA12_197 = input.LA(1);
						 
						int index12_197 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_197=='g') ) {s = 210;}
						else if ( ((LA12_197 >= 'A' && LA12_197 <= 'Z')||(LA12_197 >= 'a' && LA12_197 <= 'f')||(LA12_197 >= 'h' && LA12_197 <= 'z')) ) {s = 27;}
						else if ( ((LA12_197 >= '0' && LA12_197 <= '9')||LA12_197=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 211;
						 
						input.seek(index12_197);
						if ( s>=0 ) return s;
						break;

					case 145 : 
						int LA12_182 = input.LA(1);
						 
						int index12_182 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_182=='n') ) {s = 197;}
						else if ( ((LA12_182 >= 'A' && LA12_182 <= 'Z')||(LA12_182 >= 'a' && LA12_182 <= 'm')||(LA12_182 >= 'o' && LA12_182 <= 'z')) ) {s = 27;}
						else if ( ((LA12_182 >= '0' && LA12_182 <= '9')||LA12_182=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 198;
						 
						input.seek(index12_182);
						if ( s>=0 ) return s;
						break;

					case 146 : 
						int LA12_166 = input.LA(1);
						 
						int index12_166 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_166=='i') ) {s = 182;}
						else if ( ((LA12_166 >= 'A' && LA12_166 <= 'Z')||(LA12_166 >= 'a' && LA12_166 <= 'h')||(LA12_166 >= 'j' && LA12_166 <= 'z')) ) {s = 27;}
						else if ( ((LA12_166 >= '0' && LA12_166 <= '9')||LA12_166=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 183;
						 
						input.seek(index12_166);
						if ( s>=0 ) return s;
						break;

					case 147 : 
						int LA12_144 = input.LA(1);
						 
						int index12_144 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_144=='h') ) {s = 166;}
						else if ( ((LA12_144 >= 'A' && LA12_144 <= 'Z')||(LA12_144 >= 'a' && LA12_144 <= 'g')||(LA12_144 >= 'i' && LA12_144 <= 'z')) ) {s = 27;}
						else if ( ((LA12_144 >= '0' && LA12_144 <= '9')||LA12_144=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 167;
						 
						input.seek(index12_144);
						if ( s>=0 ) return s;
						break;

					case 148 : 
						int LA12_210 = input.LA(1);
						 
						int index12_210 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_210==' ') ) {s = 224;}
						else if ( ((LA12_210 >= 'A' && LA12_210 <= 'Z')||(LA12_210 >= 'a' && LA12_210 <= 'z')) ) {s = 27;}
						else if ( ((LA12_210 >= '0' && LA12_210 <= '9')||LA12_210=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 225;
						 
						input.seek(index12_210);
						if ( s>=0 ) return s;
						break;

					case 149 : 
						int LA12_96 = input.LA(1);
						 
						int index12_96 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA12_96 >= 'A' && LA12_96 <= 'Z')||(LA12_96 >= 'a' && LA12_96 <= 'z')) ) {s = 27;}
						else if ( ((LA12_96 >= '0' && LA12_96 <= '9')||LA12_96=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 126;
						 
						input.seek(index12_96);
						if ( s>=0 ) return s;
						break;

					case 150 : 
						int LA12_138 = input.LA(1);
						 
						int index12_138 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_138==' ') ) {s = 162;}
						else if ( ((LA12_138 >= 'A' && LA12_138 <= 'Z')||(LA12_138 >= 'a' && LA12_138 <= 'z')) ) {s = 27;}
						else if ( ((LA12_138 >= '0' && LA12_138 <= '9')||LA12_138=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 163;
						 
						input.seek(index12_138);
						if ( s>=0 ) return s;
						break;

					case 151 : 
						int LA12_109 = input.LA(1);
						 
						int index12_109 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_109=='s') ) {s = 138;}
						else if ( ((LA12_109 >= 'A' && LA12_109 <= 'Z')||(LA12_109 >= 'a' && LA12_109 <= 'r')||(LA12_109 >= 't' && LA12_109 <= 'z')) ) {s = 27;}
						else if ( ((LA12_109 >= '0' && LA12_109 <= '9')||LA12_109=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 139;
						 
						input.seek(index12_109);
						if ( s>=0 ) return s;
						break;

					case 152 : 
						int LA12_74 = input.LA(1);
						 
						int index12_74 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_74=='e') ) {s = 109;}
						else if ( ((LA12_74 >= 'A' && LA12_74 <= 'Z')||(LA12_74 >= 'a' && LA12_74 <= 'd')||(LA12_74 >= 'f' && LA12_74 <= 'z')) ) {s = 27;}
						else if ( ((LA12_74 >= '0' && LA12_74 <= '9')||LA12_74=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 110;
						 
						input.seek(index12_74);
						if ( s>=0 ) return s;
						break;

					case 153 : 
						int LA12_40 = input.LA(1);
						 
						int index12_40 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_40=='k') ) {s = 74;}
						else if ( ((LA12_40 >= 'A' && LA12_40 <= 'Z')||(LA12_40 >= 'a' && LA12_40 <= 'j')||(LA12_40 >= 'l' && LA12_40 <= 'z')) ) {s = 27;}
						else if ( ((LA12_40 >= '0' && LA12_40 <= '9')||LA12_40=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 75;
						 
						input.seek(index12_40);
						if ( s>=0 ) return s;
						break;

					case 154 : 
						int LA12_58 = input.LA(1);
						 
						int index12_58 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_58=='r') ) {s = 94;}
						else if ( ((LA12_58 >= 'A' && LA12_58 <= 'Z')||(LA12_58 >= 'a' && LA12_58 <= 'q')||(LA12_58 >= 's' && LA12_58 <= 'z')) ) {s = 27;}
						else if ( ((LA12_58 >= '0' && LA12_58 <= '9')||LA12_58=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 95;
						 
						input.seek(index12_58);
						if ( s>=0 ) return s;
						break;

					case 155 : 
						int LA12_32 = input.LA(1);
						 
						int index12_32 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_32=='e') ) {s = 58;}
						else if ( ((LA12_32 >= 'A' && LA12_32 <= 'Z')||(LA12_32 >= 'a' && LA12_32 <= 'd')||(LA12_32 >= 'f' && LA12_32 <= 'z')) ) {s = 27;}
						else if ( ((LA12_32 >= '0' && LA12_32 <= '9')||LA12_32=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 59;
						 
						input.seek(index12_32);
						if ( s>=0 ) return s;
						break;

					case 156 : 
						int LA12_86 = input.LA(1);
						 
						int index12_86 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA12_86 >= 'A' && LA12_86 <= 'Z')||(LA12_86 >= 'a' && LA12_86 <= 'z')) ) {s = 27;}
						else if ( ((LA12_86 >= '0' && LA12_86 <= '9')||LA12_86=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 121;
						 
						input.seek(index12_86);
						if ( s>=0 ) return s;
						break;

					case 157 : 
						int LA12_119 = input.LA(1);
						 
						int index12_119 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA12_119 >= 'A' && LA12_119 <= 'Z')||(LA12_119 >= 'a' && LA12_119 <= 'z')) ) {s = 27;}
						else if ( ((LA12_119 >= '0' && LA12_119 <= '9')||LA12_119=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 148;
						 
						input.seek(index12_119);
						if ( s>=0 ) return s;
						break;

					case 158 : 
						int LA12_68 = input.LA(1);
						 
						int index12_68 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA12_68 >= 'A' && LA12_68 <= 'Z')||(LA12_68 >= 'a' && LA12_68 <= 'z')) ) {s = 27;}
						else if ( ((LA12_68 >= '0' && LA12_68 <= '9')||LA12_68=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 104;
						 
						input.seek(index12_68);
						if ( s>=0 ) return s;
						break;

					case 159 : 
						int LA12_129 = input.LA(1);
						 
						int index12_129 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA12_129 >= 'A' && LA12_129 <= 'Z')||(LA12_129 >= 'a' && LA12_129 <= 'z')) ) {s = 27;}
						else if ( ((LA12_129 >= '0' && LA12_129 <= '9')||LA12_129=='_') && ((runtimeOpAhead()))) {s = 23;}
						else s = 154;
						 
						input.seek(index12_129);
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
