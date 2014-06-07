// $ANTLR 3.5 /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g 2014-05-10 22:03:29
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
	public static final int T__71=71;
	public static final int T__72=72;
	public static final int T__73=73;
	public static final int T__74=74;
	public static final int T__75=75;
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
	public static final int LENIENT=23;
	public static final int LINE_COMMENT=24;
	public static final int LowerHighCondition=25;
	public static final int LowerLowCondition=26;
	public static final int NOT=27;
	public static final int NotDoubleMapCondition=28;
	public static final int NullCondition=29;
	public static final int Number=30;
	public static final int NumberToken=31;
	public static final int OPENPARENTEHSIS=32;
	public static final int OR=33;
	public static final int Operation=34;
	public static final int OperationOutput=35;
	public static final int OrDoubleMapCondition=36;
	public static final int PERCENT=37;
	public static final int ReverseCondition=38;
	public static final int StockOperation=39;
	public static final int String=40;
	public static final int StringOperation=41;
	public static final int StringToken=42;
	public static final int SupConstantCondition=43;
	public static final int SupDoubleMapCondition=44;
	public static final int Tcheat=45;
	public static final int UpRatioCondition=46;
	public static final int WS=47;
	public static final int WhiteChar=48;

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

	// $ANTLR start "LENIENT"
	public final void mLENIENT() throws RecognitionException {
		try {
			int _type = LENIENT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:53:9: ( 'lenient' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:53:11: 'lenient'
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
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:54:5: ( 'not' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:54:7: 'not'
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
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:55:17: ( '(' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:55:19: '('
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
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:56:4: ( 'or' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:56:6: 'or'
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
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:57:9: ( '%' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:57:11: '%'
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

	// $ANTLR start "T__49"
	public final void mT__49() throws RecognitionException {
		try {
			int _type = T__49;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:58:7: ( 'also display' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:58:9: 'also display'
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
	// $ANTLR end "T__49"

	// $ANTLR start "T__50"
	public final void mT__50() throws RecognitionException {
		try {
			int _type = T__50;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:59:7: ( 'crosses down historical' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:59:9: 'crosses down historical'
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
	// $ANTLR end "T__50"

	// $ANTLR start "T__51"
	public final void mT__51() throws RecognitionException {
		try {
			int _type = T__51;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:60:7: ( 'crosses down threshold' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:60:9: 'crosses down threshold'
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
	// $ANTLR end "T__51"

	// $ANTLR start "T__52"
	public final void mT__52() throws RecognitionException {
		try {
			int _type = T__52;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:61:7: ( 'crosses up historical' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:61:9: 'crosses up historical'
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
	// $ANTLR end "T__52"

	// $ANTLR start "T__53"
	public final void mT__53() throws RecognitionException {
		try {
			int _type = T__53;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:62:7: ( 'crosses up threshold' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:62:9: 'crosses up threshold'
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
	// $ANTLR end "T__53"

	// $ANTLR start "T__54"
	public final void mT__54() throws RecognitionException {
		try {
			int _type = T__54;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:63:7: ( 'equals historical' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:63:9: 'equals historical'
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
	// $ANTLR end "T__54"

	// $ANTLR start "T__55"
	public final void mT__55() throws RecognitionException {
		try {
			int _type = T__55;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:64:7: ( 'equals threshold' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:64:9: 'equals threshold'
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
	// $ANTLR end "T__55"

	// $ANTLR start "T__56"
	public final void mT__56() throws RecognitionException {
		try {
			int _type = T__56;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:65:7: ( 'for' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:65:9: 'for'
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
	// $ANTLR end "T__56"

	// $ANTLR start "T__57"
	public final void mT__57() throws RecognitionException {
		try {
			int _type = T__57;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:66:7: ( 'goes down more than' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:66:9: 'goes down more than'
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
	// $ANTLR end "T__57"

	// $ANTLR start "T__58"
	public final void mT__58() throws RecognitionException {
		try {
			int _type = T__58;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:67:7: ( 'goes up more than' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:67:9: 'goes up more than'
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
	// $ANTLR end "T__58"

	// $ANTLR start "T__59"
	public final void mT__59() throws RecognitionException {
		try {
			int _type = T__59;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:68:7: ( 'is above historical' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:68:9: 'is above historical'
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
	// $ANTLR end "T__59"

	// $ANTLR start "T__60"
	public final void mT__60() throws RecognitionException {
		try {
			int _type = T__60;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:69:7: ( 'is above threshold' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:69:9: 'is above threshold'
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
	// $ANTLR end "T__60"

	// $ANTLR start "T__61"
	public final void mT__61() throws RecognitionException {
		try {
			int _type = T__61;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:70:7: ( 'is bearish if not bullish' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:70:9: 'is bearish if not bullish'
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
	// $ANTLR end "T__61"

	// $ANTLR start "T__62"
	public final void mT__62() throws RecognitionException {
		try {
			int _type = T__62;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:71:7: ( 'is bearish when' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:71:9: 'is bearish when'
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
	// $ANTLR end "T__62"

	// $ANTLR start "T__63"
	public final void mT__63() throws RecognitionException {
		try {
			int _type = T__63;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:72:7: ( 'is below historical' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:72:9: 'is below historical'
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
	// $ANTLR end "T__63"

	// $ANTLR start "T__64"
	public final void mT__64() throws RecognitionException {
		try {
			int _type = T__64;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:73:7: ( 'is below threshold' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:73:9: 'is below threshold'
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
	// $ANTLR end "T__64"

	// $ANTLR start "T__65"
	public final void mT__65() throws RecognitionException {
		try {
			int _type = T__65;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:74:7: ( 'is bullish when' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:74:9: 'is bullish when'
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
	// $ANTLR end "T__65"

	// $ANTLR start "T__66"
	public final void mT__66() throws RecognitionException {
		try {
			int _type = T__66;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:75:7: ( 'makes a higher high spanning' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:75:9: 'makes a higher high spanning'
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
	// $ANTLR end "T__66"

	// $ANTLR start "T__67"
	public final void mT__67() throws RecognitionException {
		try {
			int _type = T__67;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:76:7: ( 'makes a higher low spanning' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:76:9: 'makes a higher low spanning'
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
	// $ANTLR end "T__67"

	// $ANTLR start "T__68"
	public final void mT__68() throws RecognitionException {
		try {
			int _type = T__68;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:77:7: ( 'makes a lower high spanning' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:77:9: 'makes a lower high spanning'
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
	// $ANTLR end "T__68"

	// $ANTLR start "T__69"
	public final void mT__69() throws RecognitionException {
		try {
			int _type = T__69;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:78:7: ( 'makes a lower low spanning' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:78:9: 'makes a lower low spanning'
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
	// $ANTLR end "T__69"

	// $ANTLR start "T__70"
	public final void mT__70() throws RecognitionException {
		try {
			int _type = T__70;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:79:7: ( 'more than' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:79:9: 'more than'
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
	// $ANTLR end "T__70"

	// $ANTLR start "T__71"
	public final void mT__71() throws RecognitionException {
		try {
			int _type = T__71;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:80:7: ( 'over' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:80:9: 'over'
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
	// $ANTLR end "T__71"

	// $ANTLR start "T__72"
	public final void mT__72() throws RecognitionException {
		try {
			int _type = T__72;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:81:7: ( 'override start shift with' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:81:9: 'override start shift with'
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
	// $ANTLR end "T__72"

	// $ANTLR start "T__73"
	public final void mT__73() throws RecognitionException {
		try {
			int _type = T__73;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:82:7: ( 'reverses down' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:82:9: 'reverses down'
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
	// $ANTLR end "T__73"

	// $ANTLR start "T__74"
	public final void mT__74() throws RecognitionException {
		try {
			int _type = T__74;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:83:7: ( 'reverses up' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:83:9: 'reverses up'
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
	// $ANTLR end "T__74"

	// $ANTLR start "T__75"
	public final void mT__75() throws RecognitionException {
		try {
			int _type = T__75;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:84:7: ( 'spanning' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:84:9: 'spanning'
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
	// $ANTLR end "T__75"

	// $ANTLR start "Operation"
	public final void mOperation() throws RecognitionException {
		try {
			int _type = Operation;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:261:7: ({...}? => ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )+ )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:261:9: {...}? => ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )+
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
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:261:60: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )+
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
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:265:7: ( ( '-' )? ( '0' .. '9' )+ ( '.' ( '0' .. '9' )+ )? )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:265:10: ( '-' )? ( '0' .. '9' )+ ( '.' ( '0' .. '9' )+ )?
			{
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:265:10: ( '-' )?
			int alt2=2;
			int LA2_0 = input.LA(1);
			if ( (LA2_0=='-') ) {
				alt2=1;
			}
			switch (alt2) {
				case 1 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:265:11: '-'
					{
					match('-'); 
					}
					break;

			}

			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:265:17: ( '0' .. '9' )+
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

			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:265:29: ( '.' ( '0' .. '9' )+ )?
			int alt5=2;
			int LA5_0 = input.LA(1);
			if ( (LA5_0=='.') ) {
				alt5=1;
			}
			switch (alt5) {
				case 1 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:265:30: '.' ( '0' .. '9' )+
					{
					match('.'); 
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:265:34: ( '0' .. '9' )+
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

	// $ANTLR start "StringToken"
	public final void mStringToken() throws RecognitionException {
		try {
			int _type = StringToken;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:268:6: ( '\"' ( 'a' .. 'z' | 'A' .. 'Z' | '.' | '_' )* '\"' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:268:8: '\"' ( 'a' .. 'z' | 'A' .. 'Z' | '.' | '_' )* '\"'
			{
			match('\"'); 
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:268:12: ( 'a' .. 'z' | 'A' .. 'Z' | '.' | '_' )*
			loop6:
			while (true) {
				int alt6=2;
				int LA6_0 = input.LA(1);
				if ( (LA6_0=='.'||(LA6_0 >= 'A' && LA6_0 <= 'Z')||LA6_0=='_'||(LA6_0 >= 'a' && LA6_0 <= 'z')) ) {
					alt6=1;
				}

				switch (alt6) {
				case 1 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:
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
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:272:4: ({...}? => ( 'open' | 'close' | 'high' | 'low' | 'volume' ) )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:272:6: {...}? => ( 'open' | 'close' | 'high' | 'low' | 'volume' )
			{
			if ( !((runtimeHistoryOpAhead())) ) {
				throw new FailedPredicateException(input, "HistoricalData", "runtimeHistoryOpAhead()");
			}
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:272:36: ( 'open' | 'close' | 'high' | 'low' | 'volume' )
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
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:272:38: 'open'
					{
					match("open"); 

					}
					break;
				case 2 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:272:47: 'close'
					{
					match("close"); 

					}
					break;
				case 3 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:272:57: 'high'
					{
					match("high"); 

					}
					break;
				case 4 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:272:66: 'low'
					{
					match("low"); 

					}
					break;
				case 5 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:272:75: 'volume'
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
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:276:7: ( ( ( ' ' )+ ) )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:276:9: ( ( ' ' )+ )
			{
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:276:9: ( ( ' ' )+ )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:276:10: ( ' ' )+
			{
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:276:10: ( ' ' )+
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
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:276:10: ' '
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
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:280:6: ( ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )+ )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:280:8: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )+
			{
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:280:8: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )+
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
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:
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
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:285:5: ( ( '\\r' | '\\t' | '\\u000C' | '\\n' ) )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:285:7: ( '\\r' | '\\t' | '\\u000C' | '\\n' )
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
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:288:5: ( '/*' ( . )* '*/' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:288:7: '/*' ( . )* '*/'
			{
			match("/*"); 

			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:288:12: ( . )*
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
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:288:12: .
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
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:291:5: ( '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:291:7: '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n'
			{
			match("//"); 

			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:291:12: (~ ( '\\n' | '\\r' ) )*
			loop11:
			while (true) {
				int alt11=2;
				int LA11_0 = input.LA(1);
				if ( ((LA11_0 >= '\u0000' && LA11_0 <= '\t')||(LA11_0 >= '\u000B' && LA11_0 <= '\f')||(LA11_0 >= '\u000E' && LA11_0 <= '\uFFFF')) ) {
					alt11=1;
				}

				switch (alt11) {
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
					break loop11;
				}
			}

			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:291:26: ( '\\r' )?
			int alt12=2;
			int LA12_0 = input.LA(1);
			if ( (LA12_0=='\r') ) {
				alt12=1;
			}
			switch (alt12) {
				case 1 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:291:26: '\\r'
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
		// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:8: ( AND | CLOSEPARENTEHSIS | COMMA | DAYS | LENIENT | NOT | OPENPARENTEHSIS | OR | PERCENT | T__49 | T__50 | T__51 | T__52 | T__53 | T__54 | T__55 | T__56 | T__57 | T__58 | T__59 | T__60 | T__61 | T__62 | T__63 | T__64 | T__65 | T__66 | T__67 | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | Operation | NumberToken | StringToken | HistoricalData | WhiteChar | Tcheat | WS | COMMENT | LINE_COMMENT )
		int alt13=45;
		alt13 = dfa13.predict(input);
		switch (alt13) {
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
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:42: LENIENT
				{
				mLENIENT(); 

				}
				break;
			case 6 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:50: NOT
				{
				mNOT(); 

				}
				break;
			case 7 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:54: OPENPARENTEHSIS
				{
				mOPENPARENTEHSIS(); 

				}
				break;
			case 8 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:70: OR
				{
				mOR(); 

				}
				break;
			case 9 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:73: PERCENT
				{
				mPERCENT(); 

				}
				break;
			case 10 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:81: T__49
				{
				mT__49(); 

				}
				break;
			case 11 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:87: T__50
				{
				mT__50(); 

				}
				break;
			case 12 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:93: T__51
				{
				mT__51(); 

				}
				break;
			case 13 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:99: T__52
				{
				mT__52(); 

				}
				break;
			case 14 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:105: T__53
				{
				mT__53(); 

				}
				break;
			case 15 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:111: T__54
				{
				mT__54(); 

				}
				break;
			case 16 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:117: T__55
				{
				mT__55(); 

				}
				break;
			case 17 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:123: T__56
				{
				mT__56(); 

				}
				break;
			case 18 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:129: T__57
				{
				mT__57(); 

				}
				break;
			case 19 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:135: T__58
				{
				mT__58(); 

				}
				break;
			case 20 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:141: T__59
				{
				mT__59(); 

				}
				break;
			case 21 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:147: T__60
				{
				mT__60(); 

				}
				break;
			case 22 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:153: T__61
				{
				mT__61(); 

				}
				break;
			case 23 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:159: T__62
				{
				mT__62(); 

				}
				break;
			case 24 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:165: T__63
				{
				mT__63(); 

				}
				break;
			case 25 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:171: T__64
				{
				mT__64(); 

				}
				break;
			case 26 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:177: T__65
				{
				mT__65(); 

				}
				break;
			case 27 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:183: T__66
				{
				mT__66(); 

				}
				break;
			case 28 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:189: T__67
				{
				mT__67(); 

				}
				break;
			case 29 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:195: T__68
				{
				mT__68(); 

				}
				break;
			case 30 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:201: T__69
				{
				mT__69(); 

				}
				break;
			case 31 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:207: T__70
				{
				mT__70(); 

				}
				break;
			case 32 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:213: T__71
				{
				mT__71(); 

				}
				break;
			case 33 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:219: T__72
				{
				mT__72(); 

				}
				break;
			case 34 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:225: T__73
				{
				mT__73(); 

				}
				break;
			case 35 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:231: T__74
				{
				mT__74(); 

				}
				break;
			case 36 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:237: T__75
				{
				mT__75(); 

				}
				break;
			case 37 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:243: Operation
				{
				mOperation(); 

				}
				break;
			case 38 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:253: NumberToken
				{
				mNumberToken(); 

				}
				break;
			case 39 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:265: StringToken
				{
				mStringToken(); 

				}
				break;
			case 40 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:277: HistoricalData
				{
				mHistoricalData(); 

				}
				break;
			case 41 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:292: WhiteChar
				{
				mWhiteChar(); 

				}
				break;
			case 42 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:302: Tcheat
				{
				mTcheat(); 

				}
				break;
			case 43 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:309: WS
				{
				mWS(); 

				}
				break;
			case 44 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:312: COMMENT
				{
				mCOMMENT(); 

				}
				break;
			case 45 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:320: LINE_COMMENT
				{
				mLINE_COMMENT(); 

				}
				break;

		}
	}


	protected DFA13 dfa13 = new DFA13(this);
	static final String DFA13_eotS =
		"\1\uffff\1\37\2\uffff\3\37\1\uffff\1\37\1\uffff\11\37\1\uffff\1\23\1\uffff"+
		"\2\37\4\uffff\1\66\1\70\1\71\1\uffff\1\73\1\75\1\77\1\101\1\102\1\104"+
		"\1\106\1\110\1\112\1\114\1\116\1\120\1\122\1\124\1\126\1\130\1\132\1\134"+
		"\1\136\2\uffff\1\137\1\uffff\1\141\2\uffff\1\143\1\uffff\1\145\1\uffff"+
		"\1\146\1\uffff\1\147\2\uffff\1\151\1\uffff\1\153\1\uffff\1\155\1\uffff"+
		"\1\157\1\uffff\1\161\1\uffff\1\162\1\uffff\1\164\3\uffff\1\170\1\uffff"+
		"\1\172\1\uffff\1\174\1\uffff\1\176\1\uffff\1\u0080\1\uffff\1\u0082\2\uffff"+
		"\1\u0084\1\uffff\1\u0085\1\uffff\1\u0087\3\uffff\1\u008a\1\uffff\1\u008b"+
		"\1\uffff\1\u008d\1\uffff\1\u008f\1\uffff\1\u0091\2\uffff\1\u0093\3\uffff"+
		"\1\u0098\1\uffff\1\u009a\1\uffff\1\u009c\1\uffff\1\u009e\1\uffff\1\u009f"+
		"\1\uffff\1\u00a1\4\uffff\1\u00a3\2\uffff\1\u00a5\2\uffff\1\u00a7\1\uffff"+
		"\1\u00a8\1\uffff\1\u00aa\6\uffff\1\u00b1\3\uffff\1\u00b3\1\uffff\1\u00b5"+
		"\2\uffff\1\u00b7\1\uffff\1\u00b9\1\uffff\1\u00bb\1\uffff\1\u00bd\2\uffff"+
		"\1\u00bf\10\uffff\1\u00c5\1\uffff\1\u00c7\1\uffff\1\u00c8\1\uffff\1\u00c9"+
		"\1\uffff\1\u00cb\1\uffff\1\u00cd\7\uffff\1\u00d5\1\uffff\1\u00d7\3\uffff"+
		"\1\u00d9\11\uffff\1\u00e2\1\uffff\1\u00e3\60\uffff";
	static final String DFA13_eofS =
		"\u0107\uffff";
	static final String DFA13_minS =
		"\1\11\1\60\2\uffff\3\60\1\uffff\1\60\1\uffff\11\60\1\uffff\1\60\1\uffff"+
		"\2\60\3\uffff\1\52\3\60\1\uffff\14\60\1\40\6\60\2\uffff\1\60\1\0\1\60"+
		"\2\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\uffff\1\60\1\0\1\60\1\0\1\60"+
		"\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\141\1\0\1\60\1\0\1\60\1\0\1"+
		"\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\uffff\1\40\1\0\1\60\1\0\1\60\2\0"+
		"\1\uffff\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\uffff\1\40\1\0"+
		"\1\142\1\145\1\60\1\0\1\40\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\uffff"+
		"\1\0\1\uffff\1\60\1\0\1\uffff\1\60\1\uffff\1\0\1\60\1\0\1\60\1\0\1\60"+
		"\1\0\1\144\1\0\1\157\1\141\1\uffff\1\40\1\0\1\uffff\1\0\1\60\1\0\1\60"+
		"\2\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\2\0\1\40\1\0\2\uffff\1\166\1\162"+
		"\1\157\1\141\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\40\1\0"+
		"\1\150\1\0\1\145\1\151\1\167\1\40\1\60\1\0\1\60\2\0\1\uffff\1\40\1\0\1"+
		"\144\1\0\2\uffff\1\40\1\163\1\40\1\150\1\40\1\0\1\60\1\0\1\uffff\1\0\1"+
		"\157\1\160\3\150\1\151\1\157\1\144\1\0\1\uffff\1\167\1\40\2\uffff\1\40"+
		"\2\uffff\1\147\1\167\2\uffff\1\156\1\150\1\151\1\150\1\145\1\40\4\uffff"+
		"\1\145\1\162\1\150\1\162\1\40\2\uffff\1\40\2\150\4\uffff";
	static final String DFA13_maxS =
		"\2\172\2\uffff\3\172\1\uffff\1\172\1\uffff\11\172\1\uffff\1\172\1\uffff"+
		"\2\172\3\uffff\1\57\3\172\1\uffff\23\172\2\uffff\1\172\1\0\1\172\2\0\1"+
		"\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\uffff\1\172\1\0\1\172\1\0\1\172"+
		"\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\142\1\0\1\172\1\0\1\172"+
		"\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\uffff\1\172\1\0\1\172\1"+
		"\0\1\172\2\0\1\uffff\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0"+
		"\1\uffff\1\172\1\0\1\142\1\165\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0"+
		"\1\172\1\0\1\172\1\0\1\uffff\1\0\1\uffff\1\172\1\0\1\uffff\1\172\1\uffff"+
		"\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\165\1\0\1\157\1\154\1\uffff\1\172"+
		"\1\0\1\uffff\1\0\1\172\1\0\1\172\2\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172"+
		"\2\0\1\172\1\0\2\uffff\1\166\1\162\1\157\1\141\1\0\1\172\1\0\1\172\1\0"+
		"\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\164\1\0\1\145\1\151\1\167\1"+
		"\40\1\172\1\0\1\172\2\0\1\uffff\1\172\1\0\1\165\1\0\2\uffff\1\40\1\163"+
		"\1\40\1\154\1\172\1\0\1\172\1\0\1\uffff\1\0\1\157\1\160\1\164\1\150\1"+
		"\164\1\151\1\157\1\165\1\0\1\uffff\1\167\1\40\2\uffff\1\40\2\uffff\1\147"+
		"\1\167\2\uffff\1\156\1\164\1\167\1\150\1\145\1\40\4\uffff\1\145\1\162"+
		"\1\164\1\162\1\40\2\uffff\1\40\2\154\4\uffff";
	static final String DFA13_acceptS =
		"\2\uffff\1\2\1\3\3\uffff\1\7\1\uffff\1\11\11\uffff\1\46\1\uffff\1\47\2"+
		"\uffff\1\51\1\45\1\53\4\uffff\1\52\23\uffff\1\54\1\55\15\uffff\1\10\34"+
		"\uffff\1\1\7\uffff\1\6\12\uffff\1\21\20\uffff\1\12\1\uffff\1\4\2\uffff"+
		"\1\50\1\uffff\1\40\13\uffff\1\32\2\uffff\1\37\21\uffff\1\22\1\23\34\uffff"+
		"\1\5\4\uffff\1\17\1\20\10\uffff\1\41\12\uffff\1\44\2\uffff\1\24\1\25\1"+
		"\uffff\1\30\1\31\2\uffff\1\42\1\43\6\uffff\1\15\1\16\1\26\1\27\5\uffff"+
		"\1\13\1\14\3\uffff\1\35\1\36\1\33\1\34";
	static final String DFA13_specialS =
		"\1\105\1\11\2\uffff\1\52\1\1\1\53\1\uffff\1\127\1\uffff\1\41\1\114\1\u00a6"+
		"\1\51\1\63\1\165\1\36\1\26\1\2\3\uffff\1\6\1\107\4\uffff\1\60\1\50\1\u009e"+
		"\1\uffff\1\103\1\75\1\21\1\101\1\u00a4\1\12\1\31\1\10\1\27\1\112\1\13"+
		"\1\56\1\u00a2\1\123\1\71\1\34\1\40\1\22\1\17\2\uffff\1\u00a1\1\164\1\46"+
		"\1\163\1\162\1\104\1\161\1\74\1\160\1\72\1\157\1\u00a8\1\156\1\uffff\1"+
		"\14\1\155\1\30\1\154\1\5\1\153\1\24\1\152\1\113\1\151\1\115\1\150\1\57"+
		"\1\147\1\uffff\1\146\1\124\1\145\1\66\1\144\1\35\1\143\1\37\1\142\1\23"+
		"\1\141\1\20\1\140\1\uffff\1\47\1\166\1\u00a0\1\167\1\77\1\170\1\117\1"+
		"\uffff\1\u00a3\1\171\1\102\1\172\1\7\1\173\1\25\1\174\1\110\1\175\1\uffff"+
		"\1\54\1\176\2\uffff\1\125\1\177\1\67\1\u0080\1\32\1\u0081\1\43\1\u0082"+
		"\1\73\1\u0083\1\16\1\u0084\1\uffff\1\u0085\1\uffff\1\76\1\u0086\1\uffff"+
		"\1\62\1\uffff\1\120\1\3\1\u0087\1\70\1\u0088\1\111\1\u0089\1\uffff\1\u008a"+
		"\3\uffff\1\126\1\u008b\1\uffff\1\u008c\1\33\1\u008d\1\42\1\u008e\1\121"+
		"\1\15\1\u008f\1\100\1\u0090\1\65\1\u0091\1\4\1\u0092\1\122\1\106\1\u0093"+
		"\6\uffff\1\u0094\1\u009c\1\u0095\1\45\1\u0096\1\55\1\u0097\1\u00a7\1\u0098"+
		"\1\64\1\u0099\1\0\1\u009a\1\uffff\1\u009b\4\uffff\1\u009d\1\137\1\44\1"+
		"\136\1\116\1\uffff\1\61\1\135\1\uffff\1\134\6\uffff\1\u009f\1\133\1\u00a5"+
		"\1\132\1\uffff\1\131\10\uffff\1\130\44\uffff}>";
	static final String[] DFA13_transitionS = {
			"\2\32\1\uffff\2\32\22\uffff\1\30\1\uffff\1\25\2\uffff\1\11\2\uffff\1"+
			"\7\1\2\3\uffff\1\23\1\uffff\1\33\12\24\1\uffff\1\3\5\uffff\32\27\4\uffff"+
			"\1\31\1\uffff\1\1\1\27\1\12\1\4\1\13\1\14\1\15\1\22\1\16\2\27\1\5\1\17"+
			"\1\6\1\10\2\27\1\20\1\21\2\27\1\26\4\27",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\13\36\1\35\1\36\1\34\14\36",
			"",
			"",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\1\40\31\36",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\4\36\1\41\11\36\1\42\13\36",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\16\36\1\43\13\36",
			"",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\17\36\1\46\1\36\1\44\3\36"+
			"\1\45\4\36",
			"",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\13\36\1\50\5\36\1\47\10\36",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\20\36\1\51\11\36",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\16\36\1\52\13\36",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\16\36\1\53\13\36",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\22\36\1\54\7\36",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\1\55\15\36\1\56\13\36",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\4\36\1\57\25\36",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\17\36\1\60\12\36",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\10\36\1\61\21\36",
			"",
			"\12\24\7\uffff\32\37\6\uffff\32\37",
			"",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\16\36\1\62\13\36",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\32\36",
			"",
			"",
			"",
			"\1\63\4\uffff\1\64",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\3\36\1\65\26\36",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\22\36\1\67\7\36",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\32\36",
			"",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\30\36\1\72\1\36",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\15\36\1\74\14\36",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\26\36\1\76\3\36",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\23\36\1\100\6\36",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\32\36",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\4\36\1\103\25\36",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\4\36\1\105\25\36",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\16\36\1\107\13\36",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\16\36\1\111\13\36",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\24\36\1\113\5\36",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\21\36\1\115\10\36",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\4\36\1\117\25\36",
			"\1\121\17\uffff\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\32\36",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\12\36\1\123\17\36",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\21\36\1\125\10\36",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\25\36\1\127\4\36",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\1\131\31\36",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\6\36\1\133\23\36",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\13\36\1\135\16\36",
			"",
			"",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\32\36",
			"\1\uffff",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\16\36\1\140\13\36",
			"\1\uffff",
			"\1\uffff",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\22\36\1\142\7\36",
			"\1\uffff",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\10\36\1\144\21\36",
			"\1\uffff",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\32\36",
			"\1\uffff",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\32\36",
			"\1\uffff",
			"",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\21\36\1\150\10\36",
			"\1\uffff",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\15\36\1\152\14\36",
			"\1\uffff",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\22\36\1\154\7\36",
			"\1\uffff",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\22\36\1\156\7\36",
			"\1\uffff",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\1\160\31\36",
			"\1\uffff",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\32\36",
			"\1\uffff",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\22\36\1\163\7\36",
			"\1\uffff",
			"\1\165\1\166",
			"\1\uffff",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\4\36\1\167\25\36",
			"\1\uffff",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\4\36\1\171\25\36",
			"\1\uffff",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\4\36\1\173\25\36",
			"\1\uffff",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\15\36\1\175\14\36",
			"\1\uffff",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\7\36\1\177\22\36",
			"\1\uffff",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\24\36\1\u0081\5\36",
			"\1\uffff",
			"",
			"\1\u0083\17\uffff\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\32\36",
			"\1\uffff",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\32\36",
			"\1\uffff",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\4\36\1\u0086\25\36",
			"\1\uffff",
			"\1\uffff",
			"",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\21\36\1\u0089\10\36",
			"\1\uffff",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\32\36",
			"\1\uffff",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\22\36\1\u008c\7\36",
			"\1\uffff",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\4\36\1\u008e\25\36",
			"\1\uffff",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\13\36\1\u0090\16\36",
			"\1\uffff",
			"",
			"\1\u0092\17\uffff\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\32\36",
			"\1\uffff",
			"\1\u0094",
			"\1\u0095\17\uffff\1\u0096",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\22\36\1\u0097\7\36",
			"\1\uffff",
			"\1\u0099\17\uffff\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\32\36",
			"\1\uffff",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\21\36\1\u009b\10\36",
			"\1\uffff",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\15\36\1\u009d\14\36",
			"\1\uffff",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\32\36",
			"\1\uffff",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\14\36\1\u00a0\15\36",
			"\1\uffff",
			"",
			"\1\uffff",
			"",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\15\36\1\u00a2\14\36",
			"\1\uffff",
			"",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\10\36\1\u00a4\21\36",
			"",
			"\1\uffff",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\4\36\1\u00a6\25\36",
			"\1\uffff",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\32\36",
			"\1\uffff",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\22\36\1\u00a9\7\36",
			"\1\uffff",
			"\1\u00ab\20\uffff\1\u00ac",
			"\1\uffff",
			"\1\u00ad",
			"\1\u00ae\12\uffff\1\u00af",
			"",
			"\1\u00b0\17\uffff\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\32\36",
			"\1\uffff",
			"",
			"\1\uffff",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\22\36\1\u00b2\7\36",
			"\1\uffff",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\10\36\1\u00b4\21\36",
			"\1\uffff",
			"\1\uffff",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\4\36\1\u00b6\25\36",
			"\1\uffff",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\23\36\1\u00b8\6\36",
			"\1\uffff",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\3\36\1\u00ba\26\36",
			"\1\uffff",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\22\36\1\u00bc\7\36",
			"\1\uffff",
			"\1\uffff",
			"\1\u00be\17\uffff\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\32\36",
			"\1\uffff",
			"",
			"",
			"\1\u00c0",
			"\1\u00c1",
			"\1\u00c2",
			"\1\u00c3",
			"\1\uffff",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\4\36\1\u00c4\25\36",
			"\1\uffff",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\15\36\1\u00c6\14\36",
			"\1\uffff",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\32\36",
			"\1\uffff",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\32\36",
			"\1\uffff",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\4\36\1\u00ca\25\36",
			"\1\uffff",
			"\1\u00cc\17\uffff\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\32\36",
			"\1\uffff",
			"\1\u00ce\13\uffff\1\u00cf",
			"\1\uffff",
			"\1\u00d0",
			"\1\u00d1",
			"\1\u00d2",
			"\1\u00d3",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\22\36\1\u00d4\7\36",
			"\1\uffff",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\6\36\1\u00d6\23\36",
			"\1\uffff",
			"\1\uffff",
			"",
			"\1\u00d8\17\uffff\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\32\36",
			"\1\uffff",
			"\1\u00da\20\uffff\1\u00db",
			"\1\uffff",
			"",
			"",
			"\1\u00dc",
			"\1\u00dd",
			"\1\u00de",
			"\1\u00df\3\uffff\1\u00e0",
			"\1\u00e1\17\uffff\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\32\36",
			"\1\uffff",
			"\12\36\7\uffff\32\36\4\uffff\1\31\1\uffff\32\36",
			"\1\uffff",
			"",
			"\1\uffff",
			"\1\u00e4",
			"\1\u00e5",
			"\1\u00e6\13\uffff\1\u00e7",
			"\1\u00e8",
			"\1\u00e9\13\uffff\1\u00ea",
			"\1\u00eb",
			"\1\u00ec",
			"\1\u00ed\20\uffff\1\u00ee",
			"\1\uffff",
			"",
			"\1\u00ef",
			"\1\u00f0",
			"",
			"",
			"\1\u00f1",
			"",
			"",
			"\1\u00f2",
			"\1\u00f3",
			"",
			"",
			"\1\u00f4",
			"\1\u00f5\13\uffff\1\u00f6",
			"\1\u00f7\15\uffff\1\u00f8",
			"\1\u00f9",
			"\1\u00fa",
			"\1\u00fb",
			"",
			"",
			"",
			"",
			"\1\u00fc",
			"\1\u00fd",
			"\1\u00fe\13\uffff\1\u00ff",
			"\1\u0100",
			"\1\u0101",
			"",
			"",
			"\1\u0102",
			"\1\u0103\3\uffff\1\u0104",
			"\1\u0105\3\uffff\1\u0106",
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
			return "1:1: Tokens : ( AND | CLOSEPARENTEHSIS | COMMA | DAYS | LENIENT | NOT | OPENPARENTEHSIS | OR | PERCENT | T__49 | T__50 | T__51 | T__52 | T__53 | T__54 | T__55 | T__56 | T__57 | T__58 | T__59 | T__60 | T__61 | T__62 | T__63 | T__64 | T__65 | T__66 | T__67 | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | Operation | NumberToken | StringToken | HistoricalData | WhiteChar | Tcheat | WS | COMMENT | LINE_COMMENT );";
		}
		@Override
		public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
			IntStream input = _input;
			int _s = s;
			switch ( s ) {
					case 0 : 
						int LA13_188 = input.LA(1);
						 
						int index13_188 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_188==' ') ) {s = 204;}
						else if ( ((LA13_188 >= '0' && LA13_188 <= '9')||(LA13_188 >= 'A' && LA13_188 <= 'Z')||(LA13_188 >= 'a' && LA13_188 <= 'z')) ) {s = 30;}
						else if ( (LA13_188=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 205;
						 
						input.seek(index13_188);
						if ( s>=0 ) return s;
						break;

					case 1 : 
						int LA13_5 = input.LA(1);
						 
						int index13_5 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_5=='e') ) {s = 33;}
						else if ( (LA13_5=='o') ) {s = 34;}
						else if ( ((LA13_5 >= '0' && LA13_5 <= '9')||(LA13_5 >= 'A' && LA13_5 <= 'Z')||(LA13_5 >= 'a' && LA13_5 <= 'd')||(LA13_5 >= 'f' && LA13_5 <= 'n')||(LA13_5 >= 'p' && LA13_5 <= 'z')) ) {s = 30;}
						else if ( (LA13_5=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 31;
						 
						input.seek(index13_5);
						if ( s>=0 ) return s;
						break;

					case 2 : 
						int LA13_18 = input.LA(1);
						 
						int index13_18 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_18=='i') ) {s = 49;}
						else if ( ((LA13_18 >= '0' && LA13_18 <= '9')||(LA13_18 >= 'A' && LA13_18 <= 'Z')||(LA13_18 >= 'a' && LA13_18 <= 'h')||(LA13_18 >= 'j' && LA13_18 <= 'z')) ) {s = 30;}
						else if ( (LA13_18=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 31;
						 
						input.seek(index13_18);
						if ( s>=0 ) return s;
						break;

					case 3 : 
						int LA13_140 = input.LA(1);
						 
						int index13_140 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_140=='e') ) {s = 166;}
						else if ( ((LA13_140 >= '0' && LA13_140 <= '9')||(LA13_140 >= 'A' && LA13_140 <= 'Z')||(LA13_140 >= 'a' && LA13_140 <= 'd')||(LA13_140 >= 'f' && LA13_140 <= 'z')) ) {s = 30;}
						else if ( (LA13_140=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 167;
						 
						input.seek(index13_140);
						if ( s>=0 ) return s;
						break;

					case 4 : 
						int LA13_166 = input.LA(1);
						 
						int index13_166 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_166=='s') ) {s = 188;}
						else if ( ((LA13_166 >= '0' && LA13_166 <= '9')||(LA13_166 >= 'A' && LA13_166 <= 'Z')||(LA13_166 >= 'a' && LA13_166 <= 'r')||(LA13_166 >= 't' && LA13_166 <= 'z')) ) {s = 30;}
						else if ( (LA13_166=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 189;
						 
						input.seek(index13_166);
						if ( s>=0 ) return s;
						break;

					case 5 : 
						int LA13_71 = input.LA(1);
						 
						int index13_71 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_71=='s') ) {s = 108;}
						else if ( ((LA13_71 >= '0' && LA13_71 <= '9')||(LA13_71 >= 'A' && LA13_71 <= 'Z')||(LA13_71 >= 'a' && LA13_71 <= 'r')||(LA13_71 >= 't' && LA13_71 <= 'z')) ) {s = 30;}
						else if ( (LA13_71=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 109;
						 
						input.seek(index13_71);
						if ( s>=0 ) return s;
						break;

					case 6 : 
						int LA13_22 = input.LA(1);
						 
						int index13_22 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_22=='o') ) {s = 50;}
						else if ( ((LA13_22 >= '0' && LA13_22 <= '9')||(LA13_22 >= 'A' && LA13_22 <= 'Z')||(LA13_22 >= 'a' && LA13_22 <= 'n')||(LA13_22 >= 'p' && LA13_22 <= 'z')) ) {s = 30;}
						else if ( (LA13_22=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 31;
						 
						input.seek(index13_22);
						if ( s>=0 ) return s;
						break;

					case 7 : 
						int LA13_108 = input.LA(1);
						 
						int index13_108 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_108=='s') ) {s = 140;}
						else if ( ((LA13_108 >= '0' && LA13_108 <= '9')||(LA13_108 >= 'A' && LA13_108 <= 'Z')||(LA13_108 >= 'a' && LA13_108 <= 'r')||(LA13_108 >= 't' && LA13_108 <= 'z')) ) {s = 30;}
						else if ( (LA13_108=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 141;
						 
						input.seek(index13_108);
						if ( s>=0 ) return s;
						break;

					case 8 : 
						int LA13_39 = input.LA(1);
						 
						int index13_39 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_39=='o') ) {s = 71;}
						else if ( ((LA13_39 >= '0' && LA13_39 <= '9')||(LA13_39 >= 'A' && LA13_39 <= 'Z')||(LA13_39 >= 'a' && LA13_39 <= 'n')||(LA13_39 >= 'p' && LA13_39 <= 'z')) ) {s = 30;}
						else if ( (LA13_39=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 72;
						 
						input.seek(index13_39);
						if ( s>=0 ) return s;
						break;

					case 9 : 
						int LA13_1 = input.LA(1);
						 
						int index13_1 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_1=='n') ) {s = 28;}
						else if ( (LA13_1=='l') ) {s = 29;}
						else if ( ((LA13_1 >= '0' && LA13_1 <= '9')||(LA13_1 >= 'A' && LA13_1 <= 'Z')||(LA13_1 >= 'a' && LA13_1 <= 'k')||LA13_1=='m'||(LA13_1 >= 'o' && LA13_1 <= 'z')) ) {s = 30;}
						else if ( (LA13_1=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 31;
						 
						input.seek(index13_1);
						if ( s>=0 ) return s;
						break;

					case 10 : 
						int LA13_37 = input.LA(1);
						 
						int index13_37 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_37=='e') ) {s = 67;}
						else if ( ((LA13_37 >= '0' && LA13_37 <= '9')||(LA13_37 >= 'A' && LA13_37 <= 'Z')||(LA13_37 >= 'a' && LA13_37 <= 'd')||(LA13_37 >= 'f' && LA13_37 <= 'z')) ) {s = 30;}
						else if ( (LA13_37=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 68;
						 
						input.seek(index13_37);
						if ( s>=0 ) return s;
						break;

					case 11 : 
						int LA13_42 = input.LA(1);
						 
						int index13_42 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_42=='r') ) {s = 77;}
						else if ( ((LA13_42 >= '0' && LA13_42 <= '9')||(LA13_42 >= 'A' && LA13_42 <= 'Z')||(LA13_42 >= 'a' && LA13_42 <= 'q')||(LA13_42 >= 's' && LA13_42 <= 'z')) ) {s = 30;}
						else if ( (LA13_42=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 78;
						 
						input.seek(index13_42);
						if ( s>=0 ) return s;
						break;

					case 12 : 
						int LA13_67 = input.LA(1);
						 
						int index13_67 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_67=='r') ) {s = 104;}
						else if ( ((LA13_67 >= '0' && LA13_67 <= '9')||(LA13_67 >= 'A' && LA13_67 <= 'Z')||(LA13_67 >= 'a' && LA13_67 <= 'q')||(LA13_67 >= 's' && LA13_67 <= 'z')) ) {s = 30;}
						else if ( (LA13_67=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 105;
						 
						input.seek(index13_67);
						if ( s>=0 ) return s;
						break;

					case 13 : 
						int LA13_160 = input.LA(1);
						 
						int index13_160 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_160=='e') ) {s = 182;}
						else if ( ((LA13_160 >= '0' && LA13_160 <= '9')||(LA13_160 >= 'A' && LA13_160 <= 'Z')||(LA13_160 >= 'a' && LA13_160 <= 'd')||(LA13_160 >= 'f' && LA13_160 <= 'z')) ) {s = 30;}
						else if ( (LA13_160=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 183;
						 
						input.seek(index13_160);
						if ( s>=0 ) return s;
						break;

					case 14 : 
						int LA13_129 = input.LA(1);
						 
						int index13_129 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_129=='m') ) {s = 160;}
						else if ( ((LA13_129 >= '0' && LA13_129 <= '9')||(LA13_129 >= 'A' && LA13_129 <= 'Z')||(LA13_129 >= 'a' && LA13_129 <= 'l')||(LA13_129 >= 'n' && LA13_129 <= 'z')) ) {s = 30;}
						else if ( (LA13_129=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 161;
						 
						input.seek(index13_129);
						if ( s>=0 ) return s;
						break;

					case 15 : 
						int LA13_50 = input.LA(1);
						 
						int index13_50 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_50=='l') ) {s = 93;}
						else if ( ((LA13_50 >= '0' && LA13_50 <= '9')||(LA13_50 >= 'A' && LA13_50 <= 'Z')||(LA13_50 >= 'a' && LA13_50 <= 'k')||(LA13_50 >= 'm' && LA13_50 <= 'z')) ) {s = 30;}
						else if ( (LA13_50=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 94;
						 
						input.seek(index13_50);
						if ( s>=0 ) return s;
						break;

					case 16 : 
						int LA13_93 = input.LA(1);
						 
						int index13_93 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_93=='u') ) {s = 129;}
						else if ( ((LA13_93 >= '0' && LA13_93 <= '9')||(LA13_93 >= 'A' && LA13_93 <= 'Z')||(LA13_93 >= 'a' && LA13_93 <= 't')||(LA13_93 >= 'v' && LA13_93 <= 'z')) ) {s = 30;}
						else if ( (LA13_93=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 130;
						 
						input.seek(index13_93);
						if ( s>=0 ) return s;
						break;

					case 17 : 
						int LA13_34 = input.LA(1);
						 
						int index13_34 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_34=='w') ) {s = 62;}
						else if ( ((LA13_34 >= '0' && LA13_34 <= '9')||(LA13_34 >= 'A' && LA13_34 <= 'Z')||(LA13_34 >= 'a' && LA13_34 <= 'v')||(LA13_34 >= 'x' && LA13_34 <= 'z')) ) {s = 30;}
						else if ( (LA13_34=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 63;
						 
						input.seek(index13_34);
						if ( s>=0 ) return s;
						break;

					case 18 : 
						int LA13_49 = input.LA(1);
						 
						int index13_49 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_49=='g') ) {s = 91;}
						else if ( ((LA13_49 >= '0' && LA13_49 <= '9')||(LA13_49 >= 'A' && LA13_49 <= 'Z')||(LA13_49 >= 'a' && LA13_49 <= 'f')||(LA13_49 >= 'h' && LA13_49 <= 'z')) ) {s = 30;}
						else if ( (LA13_49=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 92;
						 
						input.seek(index13_49);
						if ( s>=0 ) return s;
						break;

					case 19 : 
						int LA13_91 = input.LA(1);
						 
						int index13_91 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_91=='h') ) {s = 127;}
						else if ( ((LA13_91 >= '0' && LA13_91 <= '9')||(LA13_91 >= 'A' && LA13_91 <= 'Z')||(LA13_91 >= 'a' && LA13_91 <= 'g')||(LA13_91 >= 'i' && LA13_91 <= 'z')) ) {s = 30;}
						else if ( (LA13_91=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 128;
						 
						input.seek(index13_91);
						if ( s>=0 ) return s;
						break;

					case 20 : 
						int LA13_73 = input.LA(1);
						 
						int index13_73 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_73=='s') ) {s = 110;}
						else if ( ((LA13_73 >= '0' && LA13_73 <= '9')||(LA13_73 >= 'A' && LA13_73 <= 'Z')||(LA13_73 >= 'a' && LA13_73 <= 'r')||(LA13_73 >= 't' && LA13_73 <= 'z')) ) {s = 30;}
						else if ( (LA13_73=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 111;
						 
						input.seek(index13_73);
						if ( s>=0 ) return s;
						break;

					case 21 : 
						int LA13_110 = input.LA(1);
						 
						int index13_110 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_110=='e') ) {s = 142;}
						else if ( ((LA13_110 >= '0' && LA13_110 <= '9')||(LA13_110 >= 'A' && LA13_110 <= 'Z')||(LA13_110 >= 'a' && LA13_110 <= 'd')||(LA13_110 >= 'f' && LA13_110 <= 'z')) ) {s = 30;}
						else if ( (LA13_110=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 143;
						 
						input.seek(index13_110);
						if ( s>=0 ) return s;
						break;

					case 22 : 
						int LA13_17 = input.LA(1);
						 
						int index13_17 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_17=='p') ) {s = 48;}
						else if ( ((LA13_17 >= '0' && LA13_17 <= '9')||(LA13_17 >= 'A' && LA13_17 <= 'Z')||(LA13_17 >= 'a' && LA13_17 <= 'o')||(LA13_17 >= 'q' && LA13_17 <= 'z')) ) {s = 30;}
						else if ( (LA13_17=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 31;
						 
						input.seek(index13_17);
						if ( s>=0 ) return s;
						break;

					case 23 : 
						int LA13_40 = input.LA(1);
						 
						int index13_40 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_40=='o') ) {s = 73;}
						else if ( ((LA13_40 >= '0' && LA13_40 <= '9')||(LA13_40 >= 'A' && LA13_40 <= 'Z')||(LA13_40 >= 'a' && LA13_40 <= 'n')||(LA13_40 >= 'p' && LA13_40 <= 'z')) ) {s = 30;}
						else if ( (LA13_40=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 74;
						 
						input.seek(index13_40);
						if ( s>=0 ) return s;
						break;

					case 24 : 
						int LA13_69 = input.LA(1);
						 
						int index13_69 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_69=='n') ) {s = 106;}
						else if ( ((LA13_69 >= '0' && LA13_69 <= '9')||(LA13_69 >= 'A' && LA13_69 <= 'Z')||(LA13_69 >= 'a' && LA13_69 <= 'm')||(LA13_69 >= 'o' && LA13_69 <= 'z')) ) {s = 30;}
						else if ( (LA13_69=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 107;
						 
						input.seek(index13_69);
						if ( s>=0 ) return s;
						break;

					case 25 : 
						int LA13_38 = input.LA(1);
						 
						int index13_38 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_38=='e') ) {s = 69;}
						else if ( ((LA13_38 >= '0' && LA13_38 <= '9')||(LA13_38 >= 'A' && LA13_38 <= 'Z')||(LA13_38 >= 'a' && LA13_38 <= 'd')||(LA13_38 >= 'f' && LA13_38 <= 'z')) ) {s = 30;}
						else if ( (LA13_38=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 70;
						 
						input.seek(index13_38);
						if ( s>=0 ) return s;
						break;

					case 26 : 
						int LA13_123 = input.LA(1);
						 
						int index13_123 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_123=='r') ) {s = 155;}
						else if ( ((LA13_123 >= '0' && LA13_123 <= '9')||(LA13_123 >= 'A' && LA13_123 <= 'Z')||(LA13_123 >= 'a' && LA13_123 <= 'q')||(LA13_123 >= 's' && LA13_123 <= 'z')) ) {s = 30;}
						else if ( (LA13_123=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 156;
						 
						input.seek(index13_123);
						if ( s>=0 ) return s;
						break;

					case 27 : 
						int LA13_155 = input.LA(1);
						 
						int index13_155 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_155=='s') ) {s = 178;}
						else if ( ((LA13_155 >= '0' && LA13_155 <= '9')||(LA13_155 >= 'A' && LA13_155 <= 'Z')||(LA13_155 >= 'a' && LA13_155 <= 'r')||(LA13_155 >= 't' && LA13_155 <= 'z')) ) {s = 30;}
						else if ( (LA13_155=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 179;
						 
						input.seek(index13_155);
						if ( s>=0 ) return s;
						break;

					case 28 : 
						int LA13_47 = input.LA(1);
						 
						int index13_47 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_47=='v') ) {s = 87;}
						else if ( ((LA13_47 >= '0' && LA13_47 <= '9')||(LA13_47 >= 'A' && LA13_47 <= 'Z')||(LA13_47 >= 'a' && LA13_47 <= 'u')||(LA13_47 >= 'w' && LA13_47 <= 'z')) ) {s = 30;}
						else if ( (LA13_47=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 88;
						 
						input.seek(index13_47);
						if ( s>=0 ) return s;
						break;

					case 29 : 
						int LA13_87 = input.LA(1);
						 
						int index13_87 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_87=='e') ) {s = 123;}
						else if ( ((LA13_87 >= '0' && LA13_87 <= '9')||(LA13_87 >= 'A' && LA13_87 <= 'Z')||(LA13_87 >= 'a' && LA13_87 <= 'd')||(LA13_87 >= 'f' && LA13_87 <= 'z')) ) {s = 30;}
						else if ( (LA13_87=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 124;
						 
						input.seek(index13_87);
						if ( s>=0 ) return s;
						break;

					case 30 : 
						int LA13_16 = input.LA(1);
						 
						int index13_16 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_16=='e') ) {s = 47;}
						else if ( ((LA13_16 >= '0' && LA13_16 <= '9')||(LA13_16 >= 'A' && LA13_16 <= 'Z')||(LA13_16 >= 'a' && LA13_16 <= 'd')||(LA13_16 >= 'f' && LA13_16 <= 'z')) ) {s = 30;}
						else if ( (LA13_16=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 31;
						 
						input.seek(index13_16);
						if ( s>=0 ) return s;
						break;

					case 31 : 
						int LA13_89 = input.LA(1);
						 
						int index13_89 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_89=='n') ) {s = 125;}
						else if ( ((LA13_89 >= '0' && LA13_89 <= '9')||(LA13_89 >= 'A' && LA13_89 <= 'Z')||(LA13_89 >= 'a' && LA13_89 <= 'm')||(LA13_89 >= 'o' && LA13_89 <= 'z')) ) {s = 30;}
						else if ( (LA13_89=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 126;
						 
						input.seek(index13_89);
						if ( s>=0 ) return s;
						break;

					case 32 : 
						int LA13_48 = input.LA(1);
						 
						int index13_48 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_48=='a') ) {s = 89;}
						else if ( ((LA13_48 >= '0' && LA13_48 <= '9')||(LA13_48 >= 'A' && LA13_48 <= 'Z')||(LA13_48 >= 'b' && LA13_48 <= 'z')) ) {s = 30;}
						else if ( (LA13_48=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 90;
						 
						input.seek(index13_48);
						if ( s>=0 ) return s;
						break;

					case 33 : 
						int LA13_10 = input.LA(1);
						 
						int index13_10 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_10=='r') ) {s = 39;}
						else if ( (LA13_10=='l') ) {s = 40;}
						else if ( ((LA13_10 >= '0' && LA13_10 <= '9')||(LA13_10 >= 'A' && LA13_10 <= 'Z')||(LA13_10 >= 'a' && LA13_10 <= 'k')||(LA13_10 >= 'm' && LA13_10 <= 'q')||(LA13_10 >= 's' && LA13_10 <= 'z')) ) {s = 30;}
						else if ( (LA13_10=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 31;
						 
						input.seek(index13_10);
						if ( s>=0 ) return s;
						break;

					case 34 : 
						int LA13_157 = input.LA(1);
						 
						int index13_157 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_157=='i') ) {s = 180;}
						else if ( ((LA13_157 >= '0' && LA13_157 <= '9')||(LA13_157 >= 'A' && LA13_157 <= 'Z')||(LA13_157 >= 'a' && LA13_157 <= 'h')||(LA13_157 >= 'j' && LA13_157 <= 'z')) ) {s = 30;}
						else if ( (LA13_157=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 181;
						 
						input.seek(index13_157);
						if ( s>=0 ) return s;
						break;

					case 35 : 
						int LA13_125 = input.LA(1);
						 
						int index13_125 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_125=='n') ) {s = 157;}
						else if ( ((LA13_125 >= '0' && LA13_125 <= '9')||(LA13_125 >= 'A' && LA13_125 <= 'Z')||(LA13_125 >= 'a' && LA13_125 <= 'm')||(LA13_125 >= 'o' && LA13_125 <= 'z')) ) {s = 30;}
						else if ( (LA13_125=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 158;
						 
						input.seek(index13_125);
						if ( s>=0 ) return s;
						break;

					case 36 : 
						int LA13_198 = input.LA(1);
						 
						int index13_198 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_198=='g') ) {s = 214;}
						else if ( ((LA13_198 >= '0' && LA13_198 <= '9')||(LA13_198 >= 'A' && LA13_198 <= 'Z')||(LA13_198 >= 'a' && LA13_198 <= 'f')||(LA13_198 >= 'h' && LA13_198 <= 'z')) ) {s = 30;}
						else if ( (LA13_198=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 215;
						 
						input.seek(index13_198);
						if ( s>=0 ) return s;
						break;

					case 37 : 
						int LA13_180 = input.LA(1);
						 
						int index13_180 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_180=='n') ) {s = 198;}
						else if ( ((LA13_180 >= '0' && LA13_180 <= '9')||(LA13_180 >= 'A' && LA13_180 <= 'Z')||(LA13_180 >= 'a' && LA13_180 <= 'm')||(LA13_180 >= 'o' && LA13_180 <= 'z')) ) {s = 30;}
						else if ( (LA13_180=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 199;
						 
						input.seek(index13_180);
						if ( s>=0 ) return s;
						break;

					case 38 : 
						int LA13_55 = input.LA(1);
						 
						int index13_55 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_55=='o') ) {s = 96;}
						else if ( ((LA13_55 >= '0' && LA13_55 <= '9')||(LA13_55 >= 'A' && LA13_55 <= 'Z')||(LA13_55 >= 'a' && LA13_55 <= 'n')||(LA13_55 >= 'p' && LA13_55 <= 'z')) ) {s = 30;}
						else if ( (LA13_55=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 97;
						 
						input.seek(index13_55);
						if ( s>=0 ) return s;
						break;

					case 39 : 
						int LA13_96 = input.LA(1);
						 
						int index13_96 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_96==' ') ) {s = 131;}
						else if ( ((LA13_96 >= '0' && LA13_96 <= '9')||(LA13_96 >= 'A' && LA13_96 <= 'Z')||(LA13_96 >= 'a' && LA13_96 <= 'z')) ) {s = 30;}
						else if ( (LA13_96=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 132;
						 
						input.seek(index13_96);
						if ( s>=0 ) return s;
						break;

					case 40 : 
						int LA13_29 = input.LA(1);
						 
						int index13_29 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_29=='s') ) {s = 55;}
						else if ( ((LA13_29 >= '0' && LA13_29 <= '9')||(LA13_29 >= 'A' && LA13_29 <= 'Z')||(LA13_29 >= 'a' && LA13_29 <= 'r')||(LA13_29 >= 't' && LA13_29 <= 'z')) ) {s = 30;}
						else if ( (LA13_29=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 56;
						 
						input.seek(index13_29);
						if ( s>=0 ) return s;
						break;

					case 41 : 
						int LA13_13 = input.LA(1);
						 
						int index13_13 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_13=='o') ) {s = 43;}
						else if ( ((LA13_13 >= '0' && LA13_13 <= '9')||(LA13_13 >= 'A' && LA13_13 <= 'Z')||(LA13_13 >= 'a' && LA13_13 <= 'n')||(LA13_13 >= 'p' && LA13_13 <= 'z')) ) {s = 30;}
						else if ( (LA13_13=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 31;
						 
						input.seek(index13_13);
						if ( s>=0 ) return s;
						break;

					case 42 : 
						int LA13_4 = input.LA(1);
						 
						int index13_4 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_4=='a') ) {s = 32;}
						else if ( ((LA13_4 >= '0' && LA13_4 <= '9')||(LA13_4 >= 'A' && LA13_4 <= 'Z')||(LA13_4 >= 'b' && LA13_4 <= 'z')) ) {s = 30;}
						else if ( (LA13_4=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 31;
						 
						input.seek(index13_4);
						if ( s>=0 ) return s;
						break;

					case 43 : 
						int LA13_6 = input.LA(1);
						 
						int index13_6 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_6=='o') ) {s = 35;}
						else if ( ((LA13_6 >= '0' && LA13_6 <= '9')||(LA13_6 >= 'A' && LA13_6 <= 'Z')||(LA13_6 >= 'a' && LA13_6 <= 'n')||(LA13_6 >= 'p' && LA13_6 <= 'z')) ) {s = 30;}
						else if ( (LA13_6=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 31;
						 
						input.seek(index13_6);
						if ( s>=0 ) return s;
						break;

					case 44 : 
						int LA13_115 = input.LA(1);
						 
						int index13_115 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_115==' ') ) {s = 146;}
						else if ( ((LA13_115 >= '0' && LA13_115 <= '9')||(LA13_115 >= 'A' && LA13_115 <= 'Z')||(LA13_115 >= 'a' && LA13_115 <= 'z')) ) {s = 30;}
						else if ( (LA13_115=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 147;
						 
						input.seek(index13_115);
						if ( s>=0 ) return s;
						break;

					case 45 : 
						int LA13_182 = input.LA(1);
						 
						int index13_182 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_182 >= '0' && LA13_182 <= '9')||(LA13_182 >= 'A' && LA13_182 <= 'Z')||(LA13_182 >= 'a' && LA13_182 <= 'z')) ) {s = 30;}
						else if ( (LA13_182=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 200;
						 
						input.seek(index13_182);
						if ( s>=0 ) return s;
						break;

					case 46 : 
						int LA13_43 = input.LA(1);
						 
						int index13_43 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_43=='e') ) {s = 79;}
						else if ( ((LA13_43 >= '0' && LA13_43 <= '9')||(LA13_43 >= 'A' && LA13_43 <= 'Z')||(LA13_43 >= 'a' && LA13_43 <= 'd')||(LA13_43 >= 'f' && LA13_43 <= 'z')) ) {s = 30;}
						else if ( (LA13_43=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 80;
						 
						input.seek(index13_43);
						if ( s>=0 ) return s;
						break;

					case 47 : 
						int LA13_79 = input.LA(1);
						 
						int index13_79 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_79=='s') ) {s = 115;}
						else if ( ((LA13_79 >= '0' && LA13_79 <= '9')||(LA13_79 >= 'A' && LA13_79 <= 'Z')||(LA13_79 >= 'a' && LA13_79 <= 'r')||(LA13_79 >= 't' && LA13_79 <= 'z')) ) {s = 30;}
						else if ( (LA13_79=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 116;
						 
						input.seek(index13_79);
						if ( s>=0 ) return s;
						break;

					case 48 : 
						int LA13_28 = input.LA(1);
						 
						int index13_28 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_28=='d') ) {s = 53;}
						else if ( ((LA13_28 >= '0' && LA13_28 <= '9')||(LA13_28 >= 'A' && LA13_28 <= 'Z')||(LA13_28 >= 'a' && LA13_28 <= 'c')||(LA13_28 >= 'e' && LA13_28 <= 'z')) ) {s = 30;}
						else if ( (LA13_28=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 54;
						 
						input.seek(index13_28);
						if ( s>=0 ) return s;
						break;

					case 49 : 
						int LA13_202 = input.LA(1);
						 
						int index13_202 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_202==' ') ) {s = 216;}
						else if ( ((LA13_202 >= '0' && LA13_202 <= '9')||(LA13_202 >= 'A' && LA13_202 <= 'Z')||(LA13_202 >= 'a' && LA13_202 <= 'z')) ) {s = 30;}
						else if ( (LA13_202=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 217;
						 
						input.seek(index13_202);
						if ( s>=0 ) return s;
						break;

					case 50 : 
						int LA13_137 = input.LA(1);
						 
						int index13_137 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_137=='i') ) {s = 164;}
						else if ( ((LA13_137 >= '0' && LA13_137 <= '9')||(LA13_137 >= 'A' && LA13_137 <= 'Z')||(LA13_137 >= 'a' && LA13_137 <= 'h')||(LA13_137 >= 'j' && LA13_137 <= 'z')) ) {s = 30;}
						else if ( (LA13_137=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 165;
						 
						input.seek(index13_137);
						if ( s>=0 ) return s;
						break;

					case 51 : 
						int LA13_14 = input.LA(1);
						 
						int index13_14 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_14=='s') ) {s = 44;}
						else if ( ((LA13_14 >= '0' && LA13_14 <= '9')||(LA13_14 >= 'A' && LA13_14 <= 'Z')||(LA13_14 >= 'a' && LA13_14 <= 'r')||(LA13_14 >= 't' && LA13_14 <= 'z')) ) {s = 30;}
						else if ( (LA13_14=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 31;
						 
						input.seek(index13_14);
						if ( s>=0 ) return s;
						break;

					case 52 : 
						int LA13_186 = input.LA(1);
						 
						int index13_186 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_186=='e') ) {s = 202;}
						else if ( ((LA13_186 >= '0' && LA13_186 <= '9')||(LA13_186 >= 'A' && LA13_186 <= 'Z')||(LA13_186 >= 'a' && LA13_186 <= 'd')||(LA13_186 >= 'f' && LA13_186 <= 'z')) ) {s = 30;}
						else if ( (LA13_186=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 203;
						 
						input.seek(index13_186);
						if ( s>=0 ) return s;
						break;

					case 53 : 
						int LA13_164 = input.LA(1);
						 
						int index13_164 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_164=='d') ) {s = 186;}
						else if ( ((LA13_164 >= '0' && LA13_164 <= '9')||(LA13_164 >= 'A' && LA13_164 <= 'Z')||(LA13_164 >= 'a' && LA13_164 <= 'c')||(LA13_164 >= 'e' && LA13_164 <= 'z')) ) {s = 30;}
						else if ( (LA13_164=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 187;
						 
						input.seek(index13_164);
						if ( s>=0 ) return s;
						break;

					case 54 : 
						int LA13_85 = input.LA(1);
						 
						int index13_85 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_85=='e') ) {s = 121;}
						else if ( ((LA13_85 >= '0' && LA13_85 <= '9')||(LA13_85 >= 'A' && LA13_85 <= 'Z')||(LA13_85 >= 'a' && LA13_85 <= 'd')||(LA13_85 >= 'f' && LA13_85 <= 'z')) ) {s = 30;}
						else if ( (LA13_85=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 122;
						 
						input.seek(index13_85);
						if ( s>=0 ) return s;
						break;

					case 55 : 
						int LA13_121 = input.LA(1);
						 
						int index13_121 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_121==' ') ) {s = 153;}
						else if ( ((LA13_121 >= '0' && LA13_121 <= '9')||(LA13_121 >= 'A' && LA13_121 <= 'Z')||(LA13_121 >= 'a' && LA13_121 <= 'z')) ) {s = 30;}
						else if ( (LA13_121=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 154;
						 
						input.seek(index13_121);
						if ( s>=0 ) return s;
						break;

					case 56 : 
						int LA13_142 = input.LA(1);
						 
						int index13_142 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_142 >= '0' && LA13_142 <= '9')||(LA13_142 >= 'A' && LA13_142 <= 'Z')||(LA13_142 >= 'a' && LA13_142 <= 'z')) ) {s = 30;}
						else if ( (LA13_142=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 168;
						 
						input.seek(index13_142);
						if ( s>=0 ) return s;
						break;

					case 57 : 
						int LA13_46 = input.LA(1);
						 
						int index13_46 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_46=='r') ) {s = 85;}
						else if ( ((LA13_46 >= '0' && LA13_46 <= '9')||(LA13_46 >= 'A' && LA13_46 <= 'Z')||(LA13_46 >= 'a' && LA13_46 <= 'q')||(LA13_46 >= 's' && LA13_46 <= 'z')) ) {s = 30;}
						else if ( (LA13_46=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 86;
						 
						input.seek(index13_46);
						if ( s>=0 ) return s;
						break;

					case 58 : 
						int LA13_62 = input.LA(1);
						 
						int index13_62 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_62 >= '0' && LA13_62 <= '9')||(LA13_62 >= 'A' && LA13_62 <= 'Z')||(LA13_62 >= 'a' && LA13_62 <= 'z')) ) {s = 30;}
						else if ( (LA13_62=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 102;
						 
						input.seek(index13_62);
						if ( s>=0 ) return s;
						break;

					case 59 : 
						int LA13_127 = input.LA(1);
						 
						int index13_127 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_127 >= '0' && LA13_127 <= '9')||(LA13_127 >= 'A' && LA13_127 <= 'Z')||(LA13_127 >= 'a' && LA13_127 <= 'z')) ) {s = 30;}
						else if ( (LA13_127=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 159;
						 
						input.seek(index13_127);
						if ( s>=0 ) return s;
						break;

					case 60 : 
						int LA13_60 = input.LA(1);
						 
						int index13_60 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_60=='i') ) {s = 100;}
						else if ( ((LA13_60 >= '0' && LA13_60 <= '9')||(LA13_60 >= 'A' && LA13_60 <= 'Z')||(LA13_60 >= 'a' && LA13_60 <= 'h')||(LA13_60 >= 'j' && LA13_60 <= 'z')) ) {s = 30;}
						else if ( (LA13_60=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 101;
						 
						input.seek(index13_60);
						if ( s>=0 ) return s;
						break;

					case 61 : 
						int LA13_33 = input.LA(1);
						 
						int index13_33 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_33=='n') ) {s = 60;}
						else if ( ((LA13_33 >= '0' && LA13_33 <= '9')||(LA13_33 >= 'A' && LA13_33 <= 'Z')||(LA13_33 >= 'a' && LA13_33 <= 'm')||(LA13_33 >= 'o' && LA13_33 <= 'z')) ) {s = 30;}
						else if ( (LA13_33=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 61;
						 
						input.seek(index13_33);
						if ( s>=0 ) return s;
						break;

					case 62 : 
						int LA13_134 = input.LA(1);
						 
						int index13_134 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_134=='n') ) {s = 162;}
						else if ( ((LA13_134 >= '0' && LA13_134 <= '9')||(LA13_134 >= 'A' && LA13_134 <= 'Z')||(LA13_134 >= 'a' && LA13_134 <= 'm')||(LA13_134 >= 'o' && LA13_134 <= 'z')) ) {s = 30;}
						else if ( (LA13_134=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 163;
						 
						input.seek(index13_134);
						if ( s>=0 ) return s;
						break;

					case 63 : 
						int LA13_100 = input.LA(1);
						 
						int index13_100 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_100=='e') ) {s = 134;}
						else if ( ((LA13_100 >= '0' && LA13_100 <= '9')||(LA13_100 >= 'A' && LA13_100 <= 'Z')||(LA13_100 >= 'a' && LA13_100 <= 'd')||(LA13_100 >= 'f' && LA13_100 <= 'z')) ) {s = 30;}
						else if ( (LA13_100=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 135;
						 
						input.seek(index13_100);
						if ( s>=0 ) return s;
						break;

					case 64 : 
						int LA13_162 = input.LA(1);
						 
						int index13_162 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_162=='t') ) {s = 184;}
						else if ( ((LA13_162 >= '0' && LA13_162 <= '9')||(LA13_162 >= 'A' && LA13_162 <= 'Z')||(LA13_162 >= 'a' && LA13_162 <= 's')||(LA13_162 >= 'u' && LA13_162 <= 'z')) ) {s = 30;}
						else if ( (LA13_162=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 185;
						 
						input.seek(index13_162);
						if ( s>=0 ) return s;
						break;

					case 65 : 
						int LA13_35 = input.LA(1);
						 
						int index13_35 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_35=='t') ) {s = 64;}
						else if ( ((LA13_35 >= '0' && LA13_35 <= '9')||(LA13_35 >= 'A' && LA13_35 <= 'Z')||(LA13_35 >= 'a' && LA13_35 <= 's')||(LA13_35 >= 'u' && LA13_35 <= 'z')) ) {s = 30;}
						else if ( (LA13_35=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 65;
						 
						input.seek(index13_35);
						if ( s>=0 ) return s;
						break;

					case 66 : 
						int LA13_106 = input.LA(1);
						 
						int index13_106 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_106 >= '0' && LA13_106 <= '9')||(LA13_106 >= 'A' && LA13_106 <= 'Z')||(LA13_106 >= 'a' && LA13_106 <= 'z')) ) {s = 30;}
						else if ( (LA13_106=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 139;
						 
						input.seek(index13_106);
						if ( s>=0 ) return s;
						break;

					case 67 : 
						int LA13_32 = input.LA(1);
						 
						int index13_32 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_32=='y') ) {s = 58;}
						else if ( ((LA13_32 >= '0' && LA13_32 <= '9')||(LA13_32 >= 'A' && LA13_32 <= 'Z')||(LA13_32 >= 'a' && LA13_32 <= 'x')||LA13_32=='z') ) {s = 30;}
						else if ( (LA13_32=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 59;
						 
						input.seek(index13_32);
						if ( s>=0 ) return s;
						break;

					case 68 : 
						int LA13_58 = input.LA(1);
						 
						int index13_58 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_58=='s') ) {s = 98;}
						else if ( ((LA13_58 >= '0' && LA13_58 <= '9')||(LA13_58 >= 'A' && LA13_58 <= 'Z')||(LA13_58 >= 'a' && LA13_58 <= 'r')||(LA13_58 >= 't' && LA13_58 <= 'z')) ) {s = 30;}
						else if ( (LA13_58=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 99;
						 
						input.seek(index13_58);
						if ( s>=0 ) return s;
						break;

					case 69 : 
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
						else if ( (LA13_0=='c') ) {s = 10;}
						else if ( (LA13_0=='e') ) {s = 11;}
						else if ( (LA13_0=='f') ) {s = 12;}
						else if ( (LA13_0=='g') ) {s = 13;}
						else if ( (LA13_0=='i') ) {s = 14;}
						else if ( (LA13_0=='m') ) {s = 15;}
						else if ( (LA13_0=='r') ) {s = 16;}
						else if ( (LA13_0=='s') ) {s = 17;}
						else if ( (LA13_0=='h') ) {s = 18;}
						else if ( (LA13_0=='-') ) {s = 19;}
						else if ( ((LA13_0 >= '0' && LA13_0 <= '9')) ) {s = 20;}
						else if ( (LA13_0=='\"') ) {s = 21;}
						else if ( (LA13_0=='v') ) {s = 22;}
						else if ( ((LA13_0 >= 'A' && LA13_0 <= 'Z')||LA13_0=='b'||(LA13_0 >= 'j' && LA13_0 <= 'k')||(LA13_0 >= 'p' && LA13_0 <= 'q')||(LA13_0 >= 't' && LA13_0 <= 'u')||(LA13_0 >= 'w' && LA13_0 <= 'z')) ) {s = 23;}
						else if ( (LA13_0==' ') ) {s = 24;}
						else if ( (LA13_0=='_') && ((runtimeOpAhead()))) {s = 25;}
						else if ( ((LA13_0 >= '\t' && LA13_0 <= '\n')||(LA13_0 >= '\f' && LA13_0 <= '\r')) ) {s = 26;}
						else if ( (LA13_0=='/') ) {s = 27;}
						 
						input.seek(index13_0);
						if ( s>=0 ) return s;
						break;

					case 70 : 
						int LA13_169 = input.LA(1);
						 
						int index13_169 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_169==' ') ) {s = 190;}
						else if ( ((LA13_169 >= '0' && LA13_169 <= '9')||(LA13_169 >= 'A' && LA13_169 <= 'Z')||(LA13_169 >= 'a' && LA13_169 <= 'z')) ) {s = 30;}
						else if ( (LA13_169=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 191;
						 
						input.seek(index13_169);
						if ( s>=0 ) return s;
						break;

					case 71 : 
						int LA13_23 = input.LA(1);
						 
						int index13_23 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_23 >= '0' && LA13_23 <= '9')||(LA13_23 >= 'A' && LA13_23 <= 'Z')||(LA13_23 >= 'a' && LA13_23 <= 'z')) ) {s = 30;}
						else if ( (LA13_23=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 31;
						 
						input.seek(index13_23);
						if ( s>=0 ) return s;
						break;

					case 72 : 
						int LA13_112 = input.LA(1);
						 
						int index13_112 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_112=='l') ) {s = 144;}
						else if ( ((LA13_112 >= '0' && LA13_112 <= '9')||(LA13_112 >= 'A' && LA13_112 <= 'Z')||(LA13_112 >= 'a' && LA13_112 <= 'k')||(LA13_112 >= 'm' && LA13_112 <= 'z')) ) {s = 30;}
						else if ( (LA13_112=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 145;
						 
						input.seek(index13_112);
						if ( s>=0 ) return s;
						break;

					case 73 : 
						int LA13_144 = input.LA(1);
						 
						int index13_144 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_144=='s') ) {s = 169;}
						else if ( ((LA13_144 >= '0' && LA13_144 <= '9')||(LA13_144 >= 'A' && LA13_144 <= 'Z')||(LA13_144 >= 'a' && LA13_144 <= 'r')||(LA13_144 >= 't' && LA13_144 <= 'z')) ) {s = 30;}
						else if ( (LA13_144=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 170;
						 
						input.seek(index13_144);
						if ( s>=0 ) return s;
						break;

					case 74 : 
						int LA13_41 = input.LA(1);
						 
						int index13_41 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_41=='u') ) {s = 75;}
						else if ( ((LA13_41 >= '0' && LA13_41 <= '9')||(LA13_41 >= 'A' && LA13_41 <= 'Z')||(LA13_41 >= 'a' && LA13_41 <= 't')||(LA13_41 >= 'v' && LA13_41 <= 'z')) ) {s = 30;}
						else if ( (LA13_41=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 76;
						 
						input.seek(index13_41);
						if ( s>=0 ) return s;
						break;

					case 75 : 
						int LA13_75 = input.LA(1);
						 
						int index13_75 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_75=='a') ) {s = 112;}
						else if ( ((LA13_75 >= '0' && LA13_75 <= '9')||(LA13_75 >= 'A' && LA13_75 <= 'Z')||(LA13_75 >= 'b' && LA13_75 <= 'z')) ) {s = 30;}
						else if ( (LA13_75=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 113;
						 
						input.seek(index13_75);
						if ( s>=0 ) return s;
						break;

					case 76 : 
						int LA13_11 = input.LA(1);
						 
						int index13_11 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_11=='q') ) {s = 41;}
						else if ( ((LA13_11 >= '0' && LA13_11 <= '9')||(LA13_11 >= 'A' && LA13_11 <= 'Z')||(LA13_11 >= 'a' && LA13_11 <= 'p')||(LA13_11 >= 'r' && LA13_11 <= 'z')) ) {s = 30;}
						else if ( (LA13_11=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 31;
						 
						input.seek(index13_11);
						if ( s>=0 ) return s;
						break;

					case 77 : 
						int LA13_77 = input.LA(1);
						 
						int index13_77 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_77 >= '0' && LA13_77 <= '9')||(LA13_77 >= 'A' && LA13_77 <= 'Z')||(LA13_77 >= 'a' && LA13_77 <= 'z')) ) {s = 30;}
						else if ( (LA13_77=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 114;
						 
						input.seek(index13_77);
						if ( s>=0 ) return s;
						break;

					case 78 : 
						int LA13_200 = input.LA(1);
						 
						int index13_200 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 25;}
						else if ( ((runtimeHistoryOpAhead())) ) {s = 136;}
						else if ( (true) ) {s = 31;}
						 
						input.seek(index13_200);
						if ( s>=0 ) return s;
						break;

					case 79 : 
						int LA13_102 = input.LA(1);
						 
						int index13_102 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 25;}
						else if ( ((runtimeHistoryOpAhead())) ) {s = 136;}
						else if ( (true) ) {s = 31;}
						 
						input.seek(index13_102);
						if ( s>=0 ) return s;
						break;

					case 80 : 
						int LA13_139 = input.LA(1);
						 
						int index13_139 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 25;}
						else if ( ((runtimeHistoryOpAhead())) ) {s = 136;}
						else if ( (true) ) {s = 31;}
						 
						input.seek(index13_139);
						if ( s>=0 ) return s;
						break;

					case 81 : 
						int LA13_159 = input.LA(1);
						 
						int index13_159 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 25;}
						else if ( ((runtimeHistoryOpAhead())) ) {s = 136;}
						else if ( (true) ) {s = 31;}
						 
						input.seek(index13_159);
						if ( s>=0 ) return s;
						break;

					case 82 : 
						int LA13_168 = input.LA(1);
						 
						int index13_168 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 25;}
						else if ( ((runtimeHistoryOpAhead())) ) {s = 136;}
						else if ( (true) ) {s = 31;}
						 
						input.seek(index13_168);
						if ( s>=0 ) return s;
						break;

					case 83 : 
						int LA13_45 = input.LA(1);
						 
						int index13_45 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_45=='k') ) {s = 83;}
						else if ( ((LA13_45 >= '0' && LA13_45 <= '9')||(LA13_45 >= 'A' && LA13_45 <= 'Z')||(LA13_45 >= 'a' && LA13_45 <= 'j')||(LA13_45 >= 'l' && LA13_45 <= 'z')) ) {s = 30;}
						else if ( (LA13_45=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 84;
						 
						input.seek(index13_45);
						if ( s>=0 ) return s;
						break;

					case 84 : 
						int LA13_83 = input.LA(1);
						 
						int index13_83 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_83=='e') ) {s = 119;}
						else if ( ((LA13_83 >= '0' && LA13_83 <= '9')||(LA13_83 >= 'A' && LA13_83 <= 'Z')||(LA13_83 >= 'a' && LA13_83 <= 'd')||(LA13_83 >= 'f' && LA13_83 <= 'z')) ) {s = 30;}
						else if ( (LA13_83=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 120;
						 
						input.seek(index13_83);
						if ( s>=0 ) return s;
						break;

					case 85 : 
						int LA13_119 = input.LA(1);
						 
						int index13_119 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_119=='s') ) {s = 151;}
						else if ( ((LA13_119 >= '0' && LA13_119 <= '9')||(LA13_119 >= 'A' && LA13_119 <= 'Z')||(LA13_119 >= 'a' && LA13_119 <= 'r')||(LA13_119 >= 't' && LA13_119 <= 'z')) ) {s = 30;}
						else if ( (LA13_119=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 152;
						 
						input.seek(index13_119);
						if ( s>=0 ) return s;
						break;

					case 86 : 
						int LA13_151 = input.LA(1);
						 
						int index13_151 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_151==' ') ) {s = 176;}
						else if ( ((LA13_151 >= '0' && LA13_151 <= '9')||(LA13_151 >= 'A' && LA13_151 <= 'Z')||(LA13_151 >= 'a' && LA13_151 <= 'z')) ) {s = 30;}
						else if ( (LA13_151=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 177;
						 
						input.seek(index13_151);
						if ( s>=0 ) return s;
						break;

					case 87 : 
						int LA13_8 = input.LA(1);
						 
						int index13_8 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_8=='r') ) {s = 36;}
						else if ( (LA13_8=='v') ) {s = 37;}
						else if ( (LA13_8=='p') ) {s = 38;}
						else if ( ((LA13_8 >= '0' && LA13_8 <= '9')||(LA13_8 >= 'A' && LA13_8 <= 'Z')||(LA13_8 >= 'a' && LA13_8 <= 'o')||LA13_8=='q'||(LA13_8 >= 's' && LA13_8 <= 'u')||(LA13_8 >= 'w' && LA13_8 <= 'z')) ) {s = 30;}
						else if ( (LA13_8=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 31;
						 
						input.seek(index13_8);
						if ( s>=0 ) return s;
						break;

					case 88 : 
						int LA13_226 = input.LA(1);
						 
						int index13_226 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 25;}
						else if ( (true) ) {s = 31;}
						 
						input.seek(index13_226);
						if ( s>=0 ) return s;
						break;

					case 89 : 
						int LA13_217 = input.LA(1);
						 
						int index13_217 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 25;}
						else if ( (true) ) {s = 31;}
						 
						input.seek(index13_217);
						if ( s>=0 ) return s;
						break;

					case 90 : 
						int LA13_215 = input.LA(1);
						 
						int index13_215 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 25;}
						else if ( (true) ) {s = 31;}
						 
						input.seek(index13_215);
						if ( s>=0 ) return s;
						break;

					case 91 : 
						int LA13_213 = input.LA(1);
						 
						int index13_213 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 25;}
						else if ( (true) ) {s = 31;}
						 
						input.seek(index13_213);
						if ( s>=0 ) return s;
						break;

					case 92 : 
						int LA13_205 = input.LA(1);
						 
						int index13_205 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 25;}
						else if ( (true) ) {s = 31;}
						 
						input.seek(index13_205);
						if ( s>=0 ) return s;
						break;

					case 93 : 
						int LA13_203 = input.LA(1);
						 
						int index13_203 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 25;}
						else if ( (true) ) {s = 31;}
						 
						input.seek(index13_203);
						if ( s>=0 ) return s;
						break;

					case 94 : 
						int LA13_199 = input.LA(1);
						 
						int index13_199 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 25;}
						else if ( (true) ) {s = 31;}
						 
						input.seek(index13_199);
						if ( s>=0 ) return s;
						break;

					case 95 : 
						int LA13_197 = input.LA(1);
						 
						int index13_197 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 25;}
						else if ( (true) ) {s = 31;}
						 
						input.seek(index13_197);
						if ( s>=0 ) return s;
						break;

					case 96 : 
						int LA13_94 = input.LA(1);
						 
						int index13_94 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 25;}
						else if ( (true) ) {s = 31;}
						 
						input.seek(index13_94);
						if ( s>=0 ) return s;
						break;

					case 97 : 
						int LA13_92 = input.LA(1);
						 
						int index13_92 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 25;}
						else if ( (true) ) {s = 31;}
						 
						input.seek(index13_92);
						if ( s>=0 ) return s;
						break;

					case 98 : 
						int LA13_90 = input.LA(1);
						 
						int index13_90 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 25;}
						else if ( (true) ) {s = 31;}
						 
						input.seek(index13_90);
						if ( s>=0 ) return s;
						break;

					case 99 : 
						int LA13_88 = input.LA(1);
						 
						int index13_88 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 25;}
						else if ( (true) ) {s = 31;}
						 
						input.seek(index13_88);
						if ( s>=0 ) return s;
						break;

					case 100 : 
						int LA13_86 = input.LA(1);
						 
						int index13_86 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 25;}
						else if ( (true) ) {s = 31;}
						 
						input.seek(index13_86);
						if ( s>=0 ) return s;
						break;

					case 101 : 
						int LA13_84 = input.LA(1);
						 
						int index13_84 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 25;}
						else if ( (true) ) {s = 31;}
						 
						input.seek(index13_84);
						if ( s>=0 ) return s;
						break;

					case 102 : 
						int LA13_82 = input.LA(1);
						 
						int index13_82 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 25;}
						else if ( (true) ) {s = 31;}
						 
						input.seek(index13_82);
						if ( s>=0 ) return s;
						break;

					case 103 : 
						int LA13_80 = input.LA(1);
						 
						int index13_80 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 25;}
						else if ( (true) ) {s = 31;}
						 
						input.seek(index13_80);
						if ( s>=0 ) return s;
						break;

					case 104 : 
						int LA13_78 = input.LA(1);
						 
						int index13_78 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 25;}
						else if ( (true) ) {s = 31;}
						 
						input.seek(index13_78);
						if ( s>=0 ) return s;
						break;

					case 105 : 
						int LA13_76 = input.LA(1);
						 
						int index13_76 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 25;}
						else if ( (true) ) {s = 31;}
						 
						input.seek(index13_76);
						if ( s>=0 ) return s;
						break;

					case 106 : 
						int LA13_74 = input.LA(1);
						 
						int index13_74 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 25;}
						else if ( (true) ) {s = 31;}
						 
						input.seek(index13_74);
						if ( s>=0 ) return s;
						break;

					case 107 : 
						int LA13_72 = input.LA(1);
						 
						int index13_72 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 25;}
						else if ( (true) ) {s = 31;}
						 
						input.seek(index13_72);
						if ( s>=0 ) return s;
						break;

					case 108 : 
						int LA13_70 = input.LA(1);
						 
						int index13_70 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 25;}
						else if ( (true) ) {s = 31;}
						 
						input.seek(index13_70);
						if ( s>=0 ) return s;
						break;

					case 109 : 
						int LA13_68 = input.LA(1);
						 
						int index13_68 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 25;}
						else if ( (true) ) {s = 31;}
						 
						input.seek(index13_68);
						if ( s>=0 ) return s;
						break;

					case 110 : 
						int LA13_65 = input.LA(1);
						 
						int index13_65 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 25;}
						else if ( (true) ) {s = 31;}
						 
						input.seek(index13_65);
						if ( s>=0 ) return s;
						break;

					case 111 : 
						int LA13_63 = input.LA(1);
						 
						int index13_63 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 25;}
						else if ( (true) ) {s = 31;}
						 
						input.seek(index13_63);
						if ( s>=0 ) return s;
						break;

					case 112 : 
						int LA13_61 = input.LA(1);
						 
						int index13_61 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 25;}
						else if ( (true) ) {s = 31;}
						 
						input.seek(index13_61);
						if ( s>=0 ) return s;
						break;

					case 113 : 
						int LA13_59 = input.LA(1);
						 
						int index13_59 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 25;}
						else if ( (true) ) {s = 31;}
						 
						input.seek(index13_59);
						if ( s>=0 ) return s;
						break;

					case 114 : 
						int LA13_57 = input.LA(1);
						 
						int index13_57 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 25;}
						else if ( (true) ) {s = 31;}
						 
						input.seek(index13_57);
						if ( s>=0 ) return s;
						break;

					case 115 : 
						int LA13_56 = input.LA(1);
						 
						int index13_56 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 25;}
						else if ( (true) ) {s = 31;}
						 
						input.seek(index13_56);
						if ( s>=0 ) return s;
						break;

					case 116 : 
						int LA13_54 = input.LA(1);
						 
						int index13_54 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 25;}
						else if ( (true) ) {s = 31;}
						 
						input.seek(index13_54);
						if ( s>=0 ) return s;
						break;

					case 117 : 
						int LA13_15 = input.LA(1);
						 
						int index13_15 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_15=='a') ) {s = 45;}
						else if ( (LA13_15=='o') ) {s = 46;}
						else if ( ((LA13_15 >= '0' && LA13_15 <= '9')||(LA13_15 >= 'A' && LA13_15 <= 'Z')||(LA13_15 >= 'b' && LA13_15 <= 'n')||(LA13_15 >= 'p' && LA13_15 <= 'z')) ) {s = 30;}
						else if ( (LA13_15=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 31;
						 
						input.seek(index13_15);
						if ( s>=0 ) return s;
						break;

					case 118 : 
						int LA13_97 = input.LA(1);
						 
						int index13_97 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 25;}
						else if ( (true) ) {s = 31;}
						 
						input.seek(index13_97);
						if ( s>=0 ) return s;
						break;

					case 119 : 
						int LA13_99 = input.LA(1);
						 
						int index13_99 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 25;}
						else if ( (true) ) {s = 31;}
						 
						input.seek(index13_99);
						if ( s>=0 ) return s;
						break;

					case 120 : 
						int LA13_101 = input.LA(1);
						 
						int index13_101 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 25;}
						else if ( (true) ) {s = 31;}
						 
						input.seek(index13_101);
						if ( s>=0 ) return s;
						break;

					case 121 : 
						int LA13_105 = input.LA(1);
						 
						int index13_105 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 25;}
						else if ( (true) ) {s = 31;}
						 
						input.seek(index13_105);
						if ( s>=0 ) return s;
						break;

					case 122 : 
						int LA13_107 = input.LA(1);
						 
						int index13_107 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 25;}
						else if ( (true) ) {s = 31;}
						 
						input.seek(index13_107);
						if ( s>=0 ) return s;
						break;

					case 123 : 
						int LA13_109 = input.LA(1);
						 
						int index13_109 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 25;}
						else if ( (true) ) {s = 31;}
						 
						input.seek(index13_109);
						if ( s>=0 ) return s;
						break;

					case 124 : 
						int LA13_111 = input.LA(1);
						 
						int index13_111 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 25;}
						else if ( (true) ) {s = 31;}
						 
						input.seek(index13_111);
						if ( s>=0 ) return s;
						break;

					case 125 : 
						int LA13_113 = input.LA(1);
						 
						int index13_113 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 25;}
						else if ( (true) ) {s = 31;}
						 
						input.seek(index13_113);
						if ( s>=0 ) return s;
						break;

					case 126 : 
						int LA13_116 = input.LA(1);
						 
						int index13_116 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 25;}
						else if ( (true) ) {s = 31;}
						 
						input.seek(index13_116);
						if ( s>=0 ) return s;
						break;

					case 127 : 
						int LA13_120 = input.LA(1);
						 
						int index13_120 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 25;}
						else if ( (true) ) {s = 31;}
						 
						input.seek(index13_120);
						if ( s>=0 ) return s;
						break;

					case 128 : 
						int LA13_122 = input.LA(1);
						 
						int index13_122 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 25;}
						else if ( (true) ) {s = 31;}
						 
						input.seek(index13_122);
						if ( s>=0 ) return s;
						break;

					case 129 : 
						int LA13_124 = input.LA(1);
						 
						int index13_124 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 25;}
						else if ( (true) ) {s = 31;}
						 
						input.seek(index13_124);
						if ( s>=0 ) return s;
						break;

					case 130 : 
						int LA13_126 = input.LA(1);
						 
						int index13_126 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 25;}
						else if ( (true) ) {s = 31;}
						 
						input.seek(index13_126);
						if ( s>=0 ) return s;
						break;

					case 131 : 
						int LA13_128 = input.LA(1);
						 
						int index13_128 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 25;}
						else if ( (true) ) {s = 31;}
						 
						input.seek(index13_128);
						if ( s>=0 ) return s;
						break;

					case 132 : 
						int LA13_130 = input.LA(1);
						 
						int index13_130 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 25;}
						else if ( (true) ) {s = 31;}
						 
						input.seek(index13_130);
						if ( s>=0 ) return s;
						break;

					case 133 : 
						int LA13_132 = input.LA(1);
						 
						int index13_132 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 25;}
						else if ( (true) ) {s = 31;}
						 
						input.seek(index13_132);
						if ( s>=0 ) return s;
						break;

					case 134 : 
						int LA13_135 = input.LA(1);
						 
						int index13_135 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 25;}
						else if ( (true) ) {s = 31;}
						 
						input.seek(index13_135);
						if ( s>=0 ) return s;
						break;

					case 135 : 
						int LA13_141 = input.LA(1);
						 
						int index13_141 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 25;}
						else if ( (true) ) {s = 31;}
						 
						input.seek(index13_141);
						if ( s>=0 ) return s;
						break;

					case 136 : 
						int LA13_143 = input.LA(1);
						 
						int index13_143 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 25;}
						else if ( (true) ) {s = 31;}
						 
						input.seek(index13_143);
						if ( s>=0 ) return s;
						break;

					case 137 : 
						int LA13_145 = input.LA(1);
						 
						int index13_145 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 25;}
						else if ( (true) ) {s = 31;}
						 
						input.seek(index13_145);
						if ( s>=0 ) return s;
						break;

					case 138 : 
						int LA13_147 = input.LA(1);
						 
						int index13_147 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 25;}
						else if ( (true) ) {s = 31;}
						 
						input.seek(index13_147);
						if ( s>=0 ) return s;
						break;

					case 139 : 
						int LA13_152 = input.LA(1);
						 
						int index13_152 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 25;}
						else if ( (true) ) {s = 31;}
						 
						input.seek(index13_152);
						if ( s>=0 ) return s;
						break;

					case 140 : 
						int LA13_154 = input.LA(1);
						 
						int index13_154 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 25;}
						else if ( (true) ) {s = 31;}
						 
						input.seek(index13_154);
						if ( s>=0 ) return s;
						break;

					case 141 : 
						int LA13_156 = input.LA(1);
						 
						int index13_156 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 25;}
						else if ( (true) ) {s = 31;}
						 
						input.seek(index13_156);
						if ( s>=0 ) return s;
						break;

					case 142 : 
						int LA13_158 = input.LA(1);
						 
						int index13_158 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 25;}
						else if ( (true) ) {s = 31;}
						 
						input.seek(index13_158);
						if ( s>=0 ) return s;
						break;

					case 143 : 
						int LA13_161 = input.LA(1);
						 
						int index13_161 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 25;}
						else if ( (true) ) {s = 31;}
						 
						input.seek(index13_161);
						if ( s>=0 ) return s;
						break;

					case 144 : 
						int LA13_163 = input.LA(1);
						 
						int index13_163 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 25;}
						else if ( (true) ) {s = 31;}
						 
						input.seek(index13_163);
						if ( s>=0 ) return s;
						break;

					case 145 : 
						int LA13_165 = input.LA(1);
						 
						int index13_165 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 25;}
						else if ( (true) ) {s = 31;}
						 
						input.seek(index13_165);
						if ( s>=0 ) return s;
						break;

					case 146 : 
						int LA13_167 = input.LA(1);
						 
						int index13_167 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 25;}
						else if ( (true) ) {s = 31;}
						 
						input.seek(index13_167);
						if ( s>=0 ) return s;
						break;

					case 147 : 
						int LA13_170 = input.LA(1);
						 
						int index13_170 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 25;}
						else if ( (true) ) {s = 31;}
						 
						input.seek(index13_170);
						if ( s>=0 ) return s;
						break;

					case 148 : 
						int LA13_177 = input.LA(1);
						 
						int index13_177 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 25;}
						else if ( (true) ) {s = 31;}
						 
						input.seek(index13_177);
						if ( s>=0 ) return s;
						break;

					case 149 : 
						int LA13_179 = input.LA(1);
						 
						int index13_179 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 25;}
						else if ( (true) ) {s = 31;}
						 
						input.seek(index13_179);
						if ( s>=0 ) return s;
						break;

					case 150 : 
						int LA13_181 = input.LA(1);
						 
						int index13_181 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 25;}
						else if ( (true) ) {s = 31;}
						 
						input.seek(index13_181);
						if ( s>=0 ) return s;
						break;

					case 151 : 
						int LA13_183 = input.LA(1);
						 
						int index13_183 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 25;}
						else if ( (true) ) {s = 31;}
						 
						input.seek(index13_183);
						if ( s>=0 ) return s;
						break;

					case 152 : 
						int LA13_185 = input.LA(1);
						 
						int index13_185 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 25;}
						else if ( (true) ) {s = 31;}
						 
						input.seek(index13_185);
						if ( s>=0 ) return s;
						break;

					case 153 : 
						int LA13_187 = input.LA(1);
						 
						int index13_187 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 25;}
						else if ( (true) ) {s = 31;}
						 
						input.seek(index13_187);
						if ( s>=0 ) return s;
						break;

					case 154 : 
						int LA13_189 = input.LA(1);
						 
						int index13_189 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 25;}
						else if ( (true) ) {s = 31;}
						 
						input.seek(index13_189);
						if ( s>=0 ) return s;
						break;

					case 155 : 
						int LA13_191 = input.LA(1);
						 
						int index13_191 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 25;}
						else if ( (true) ) {s = 31;}
						 
						input.seek(index13_191);
						if ( s>=0 ) return s;
						break;

					case 156 : 
						int LA13_178 = input.LA(1);
						 
						int index13_178 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_178=='e') ) {s = 196;}
						else if ( ((LA13_178 >= '0' && LA13_178 <= '9')||(LA13_178 >= 'A' && LA13_178 <= 'Z')||(LA13_178 >= 'a' && LA13_178 <= 'd')||(LA13_178 >= 'f' && LA13_178 <= 'z')) ) {s = 30;}
						else if ( (LA13_178=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 197;
						 
						input.seek(index13_178);
						if ( s>=0 ) return s;
						break;

					case 157 : 
						int LA13_196 = input.LA(1);
						 
						int index13_196 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_196=='s') ) {s = 212;}
						else if ( ((LA13_196 >= '0' && LA13_196 <= '9')||(LA13_196 >= 'A' && LA13_196 <= 'Z')||(LA13_196 >= 'a' && LA13_196 <= 'r')||(LA13_196 >= 't' && LA13_196 <= 'z')) ) {s = 30;}
						else if ( (LA13_196=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 213;
						 
						input.seek(index13_196);
						if ( s>=0 ) return s;
						break;

					case 158 : 
						int LA13_30 = input.LA(1);
						 
						int index13_30 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_30 >= '0' && LA13_30 <= '9')||(LA13_30 >= 'A' && LA13_30 <= 'Z')||(LA13_30 >= 'a' && LA13_30 <= 'z')) ) {s = 30;}
						else if ( (LA13_30=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 57;
						 
						input.seek(index13_30);
						if ( s>=0 ) return s;
						break;

					case 159 : 
						int LA13_212 = input.LA(1);
						 
						int index13_212 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_212==' ') ) {s = 225;}
						else if ( ((LA13_212 >= '0' && LA13_212 <= '9')||(LA13_212 >= 'A' && LA13_212 <= 'Z')||(LA13_212 >= 'a' && LA13_212 <= 'z')) ) {s = 30;}
						else if ( (LA13_212=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 226;
						 
						input.seek(index13_212);
						if ( s>=0 ) return s;
						break;

					case 160 : 
						int LA13_98 = input.LA(1);
						 
						int index13_98 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_98 >= '0' && LA13_98 <= '9')||(LA13_98 >= 'A' && LA13_98 <= 'Z')||(LA13_98 >= 'a' && LA13_98 <= 'z')) ) {s = 30;}
						else if ( (LA13_98=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 133;
						 
						input.seek(index13_98);
						if ( s>=0 ) return s;
						break;

					case 161 : 
						int LA13_53 = input.LA(1);
						 
						int index13_53 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_53 >= '0' && LA13_53 <= '9')||(LA13_53 >= 'A' && LA13_53 <= 'Z')||(LA13_53 >= 'a' && LA13_53 <= 'z')) ) {s = 30;}
						else if ( (LA13_53=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 95;
						 
						input.seek(index13_53);
						if ( s>=0 ) return s;
						break;

					case 162 : 
						int LA13_44 = input.LA(1);
						 
						int index13_44 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_44==' ') ) {s = 81;}
						else if ( ((LA13_44 >= '0' && LA13_44 <= '9')||(LA13_44 >= 'A' && LA13_44 <= 'Z')||(LA13_44 >= 'a' && LA13_44 <= 'z')) ) {s = 30;}
						else if ( (LA13_44=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 82;
						 
						input.seek(index13_44);
						if ( s>=0 ) return s;
						break;

					case 163 : 
						int LA13_104 = input.LA(1);
						 
						int index13_104 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_104=='r') ) {s = 137;}
						else if ( ((LA13_104 >= '0' && LA13_104 <= '9')||(LA13_104 >= 'A' && LA13_104 <= 'Z')||(LA13_104 >= 'a' && LA13_104 <= 'q')||(LA13_104 >= 's' && LA13_104 <= 'z')) ) {s = 30;}
						else if ( (LA13_104=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 138;
						 
						input.seek(index13_104);
						if ( s>=0 ) return s;
						break;

					case 164 : 
						int LA13_36 = input.LA(1);
						 
						int index13_36 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_36 >= '0' && LA13_36 <= '9')||(LA13_36 >= 'A' && LA13_36 <= 'Z')||(LA13_36 >= 'a' && LA13_36 <= 'z')) ) {s = 30;}
						else if ( (LA13_36=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 66;
						 
						input.seek(index13_36);
						if ( s>=0 ) return s;
						break;

					case 165 : 
						int LA13_214 = input.LA(1);
						 
						int index13_214 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_214 >= '0' && LA13_214 <= '9')||(LA13_214 >= 'A' && LA13_214 <= 'Z')||(LA13_214 >= 'a' && LA13_214 <= 'z')) ) {s = 30;}
						else if ( (LA13_214=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 227;
						 
						input.seek(index13_214);
						if ( s>=0 ) return s;
						break;

					case 166 : 
						int LA13_12 = input.LA(1);
						 
						int index13_12 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_12=='o') ) {s = 42;}
						else if ( ((LA13_12 >= '0' && LA13_12 <= '9')||(LA13_12 >= 'A' && LA13_12 <= 'Z')||(LA13_12 >= 'a' && LA13_12 <= 'n')||(LA13_12 >= 'p' && LA13_12 <= 'z')) ) {s = 30;}
						else if ( (LA13_12=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 31;
						 
						input.seek(index13_12);
						if ( s>=0 ) return s;
						break;

					case 167 : 
						int LA13_184 = input.LA(1);
						 
						int index13_184 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_184 >= '0' && LA13_184 <= '9')||(LA13_184 >= 'A' && LA13_184 <= 'Z')||(LA13_184 >= 'a' && LA13_184 <= 'z')) ) {s = 30;}
						else if ( (LA13_184=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 201;
						 
						input.seek(index13_184);
						if ( s>=0 ) return s;
						break;

					case 168 : 
						int LA13_64 = input.LA(1);
						 
						int index13_64 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_64 >= '0' && LA13_64 <= '9')||(LA13_64 >= 'A' && LA13_64 <= 'Z')||(LA13_64 >= 'a' && LA13_64 <= 'z')) ) {s = 30;}
						else if ( (LA13_64=='_') && ((runtimeOpAhead()))) {s = 25;}
						else s = 103;
						 
						input.seek(index13_64);
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
