// $ANTLR 3.5.2 com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g 2018-11-18 20:04:09
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
	public static final int AND=4;
	public static final int AndDoubleMapCondition=5;
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
	public static final int LinearFlatTrendsCondition=27;
	public static final int LinearOppositeTrendsCondition=28;
	public static final int LinearSimilarTrendsCondition=29;
	public static final int LowerHighCondition=30;
	public static final int LowerLowCondition=31;
	public static final int MATCHING=32;
	public static final int MatchingMapCondition=33;
	public static final int NOT=34;
	public static final int NotDoubleMapCondition=35;
	public static final int NullCondition=36;
	public static final int Number=37;
	public static final int NumberToken=38;
	public static final int OPENPARENTEHSIS=39;
	public static final int OPENSQRT=40;
	public static final int OR=41;
	public static final int Operation=42;
	public static final int OperationOutput=43;
	public static final int OrDoubleMapCondition=44;
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
	public static final int Tcheat=56;
	public static final int UpRatioCondition=57;
	public static final int WS=58;
	public static final int WhiteChar=59;

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

	// $ANTLR start "T__60"
	public final void mT__60() throws RecognitionException {
		try {
			int _type = T__60;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:62:7: ( 'NaN' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:62:9: 'NaN'
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
	// $ANTLR end "T__60"

	// $ANTLR start "T__61"
	public final void mT__61() throws RecognitionException {
		try {
			int _type = T__61;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:63:7: ( 'also display' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:63:9: 'also display'
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
	// $ANTLR end "T__61"

	// $ANTLR start "T__62"
	public final void mT__62() throws RecognitionException {
		try {
			int _type = T__62;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:64:7: ( 'bearish' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:64:9: 'bearish'
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
	// $ANTLR end "T__62"

	// $ANTLR start "T__63"
	public final void mT__63() throws RecognitionException {
		try {
			int _type = T__63;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:65:7: ( 'bullish' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:65:9: 'bullish'
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
	// $ANTLR end "T__63"

	// $ANTLR start "T__64"
	public final void mT__64() throws RecognitionException {
		try {
			int _type = T__64;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:66:7: ( 'crosses down historical' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:66:9: 'crosses down historical'
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
	// $ANTLR end "T__64"

	// $ANTLR start "T__65"
	public final void mT__65() throws RecognitionException {
		try {
			int _type = T__65;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:67:7: ( 'crosses down threshold' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:67:9: 'crosses down threshold'
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
	// $ANTLR end "T__65"

	// $ANTLR start "T__66"
	public final void mT__66() throws RecognitionException {
		try {
			int _type = T__66;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:68:7: ( 'crosses up historical' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:68:9: 'crosses up historical'
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
	// $ANTLR end "T__66"

	// $ANTLR start "T__67"
	public final void mT__67() throws RecognitionException {
		try {
			int _type = T__67;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:69:7: ( 'crosses up threshold' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:69:9: 'crosses up threshold'
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
	// $ANTLR end "T__67"

	// $ANTLR start "T__68"
	public final void mT__68() throws RecognitionException {
		try {
			int _type = T__68;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:70:7: ( 'direction' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:70:9: 'direction'
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
	// $ANTLR end "T__68"

	// $ANTLR start "T__69"
	public final void mT__69() throws RecognitionException {
		try {
			int _type = T__69;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:71:7: ( 'ending within' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:71:9: 'ending within'
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
	// $ANTLR end "T__69"

	// $ANTLR start "T__70"
	public final void mT__70() throws RecognitionException {
		try {
			int _type = T__70;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:72:7: ( 'epsilon' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:72:9: 'epsilon'
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
	// $ANTLR end "T__70"

	// $ANTLR start "T__71"
	public final void mT__71() throws RecognitionException {
		try {
			int _type = T__71;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:73:7: ( 'equals historical' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:73:9: 'equals historical'
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
	// $ANTLR end "T__71"

	// $ANTLR start "T__72"
	public final void mT__72() throws RecognitionException {
		try {
			int _type = T__72;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:74:7: ( 'equals threshold' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:74:9: 'equals threshold'
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
	// $ANTLR end "T__72"

	// $ANTLR start "T__73"
	public final void mT__73() throws RecognitionException {
		try {
			int _type = T__73;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:75:7: ( 'equals trend' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:75:9: 'equals trend'
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
	// $ANTLR end "T__73"

	// $ANTLR start "T__74"
	public final void mT__74() throws RecognitionException {
		try {
			int _type = T__74;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:76:7: ( 'for' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:76:9: 'for'
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
	// $ANTLR end "T__74"

	// $ANTLR start "T__75"
	public final void mT__75() throws RecognitionException {
		try {
			int _type = T__75;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:77:7: ( 'goes down more than' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:77:9: 'goes down more than'
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
	// $ANTLR end "T__75"

	// $ANTLR start "T__76"
	public final void mT__76() throws RecognitionException {
		try {
			int _type = T__76;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:78:7: ( 'goes up more than' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:78:9: 'goes up more than'
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
	// $ANTLR end "T__76"

	// $ANTLR start "T__77"
	public final void mT__77() throws RecognitionException {
		try {
			int _type = T__77;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:79:7: ( 'greed' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:79:9: 'greed'
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
	// $ANTLR end "T__77"

	// $ANTLR start "T__78"
	public final void mT__78() throws RecognitionException {
		try {
			int _type = T__78;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:80:7: ( 'is above historical' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:80:9: 'is above historical'
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
	// $ANTLR end "T__78"

	// $ANTLR start "T__79"
	public final void mT__79() throws RecognitionException {
		try {
			int _type = T__79;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:81:7: ( 'is above threshold' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:81:9: 'is above threshold'
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
	// $ANTLR end "T__79"

	// $ANTLR start "T__80"
	public final void mT__80() throws RecognitionException {
		try {
			int _type = T__80;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:82:7: ( 'is bearish if not bullish' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:82:9: 'is bearish if not bullish'
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
	// $ANTLR end "T__80"

	// $ANTLR start "T__81"
	public final void mT__81() throws RecognitionException {
		try {
			int _type = T__81;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:83:7: ( 'is bearish when' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:83:9: 'is bearish when'
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
	// $ANTLR end "T__81"

	// $ANTLR start "T__82"
	public final void mT__82() throws RecognitionException {
		try {
			int _type = T__82;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:84:7: ( 'is below historical' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:84:9: 'is below historical'
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
	// $ANTLR end "T__82"

	// $ANTLR start "T__83"
	public final void mT__83() throws RecognitionException {
		try {
			int _type = T__83;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:85:7: ( 'is below threshold' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:85:9: 'is below threshold'
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
	// $ANTLR end "T__83"

	// $ANTLR start "T__84"
	public final void mT__84() throws RecognitionException {
		try {
			int _type = T__84;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:86:7: ( 'is bullish when' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:86:9: 'is bullish when'
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
	// $ANTLR end "T__84"

	// $ANTLR start "T__85"
	public final void mT__85() throws RecognitionException {
		try {
			int _type = T__85;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:87:7: ( 'makes a higher high spanning' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:87:9: 'makes a higher high spanning'
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
	// $ANTLR end "T__85"

	// $ANTLR start "T__86"
	public final void mT__86() throws RecognitionException {
		try {
			int _type = T__86;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:88:7: ( 'makes a higher low spanning' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:88:9: 'makes a higher low spanning'
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
	// $ANTLR end "T__86"

	// $ANTLR start "T__87"
	public final void mT__87() throws RecognitionException {
		try {
			int _type = T__87;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:89:7: ( 'makes a lower high spanning' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:89:9: 'makes a lower high spanning'
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
	// $ANTLR end "T__87"

	// $ANTLR start "T__88"
	public final void mT__88() throws RecognitionException {
		try {
			int _type = T__88;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:90:7: ( 'makes a lower low spanning' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:90:9: 'makes a lower low spanning'
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
	// $ANTLR end "T__88"

	// $ANTLR start "T__89"
	public final void mT__89() throws RecognitionException {
		try {
			int _type = T__89;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:91:7: ( 'makes a support break down spanning' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:91:9: 'makes a support break down spanning'
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
	// $ANTLR end "T__89"

	// $ANTLR start "T__90"
	public final void mT__90() throws RecognitionException {
		try {
			int _type = T__90;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:92:7: ( 'makes a support break up spanning' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:92:9: 'makes a support break up spanning'
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
	// $ANTLR end "T__90"

	// $ANTLR start "T__91"
	public final void mT__91() throws RecognitionException {
		try {
			int _type = T__91;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:93:7: ( 'more than' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:93:9: 'more than'
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
	// $ANTLR end "T__91"

	// $ANTLR start "T__92"
	public final void mT__92() throws RecognitionException {
		try {
			int _type = T__92;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:94:7: ( 'over' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:94:9: 'over'
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
	// $ANTLR end "T__92"

	// $ANTLR start "T__93"
	public final void mT__93() throws RecognitionException {
		try {
			int _type = T__93;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:95:7: ( 'override start shift with' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:95:9: 'override start shift with'
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
	// $ANTLR end "T__93"

	// $ANTLR start "T__94"
	public final void mT__94() throws RecognitionException {
		try {
			int _type = T__94;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:96:7: ( 'reverses down' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:96:9: 'reverses down'
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
	// $ANTLR end "T__94"

	// $ANTLR start "T__95"
	public final void mT__95() throws RecognitionException {
		try {
			int _type = T__95;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:97:7: ( 'reverses up' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:97:9: 'reverses up'
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
	// $ANTLR end "T__95"

	// $ANTLR start "T__96"
	public final void mT__96() throws RecognitionException {
		try {
			int _type = T__96;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:98:7: ( 'slope within' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:98:9: 'slope within'
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
	// $ANTLR end "T__96"

	// $ANTLR start "T__97"
	public final void mT__97() throws RecognitionException {
		try {
			int _type = T__97;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:99:7: ( 'smoothed' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:99:9: 'smoothed'
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
	// $ANTLR end "T__97"

	// $ANTLR start "T__98"
	public final void mT__98() throws RecognitionException {
		try {
			int _type = T__98;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:100:7: ( 'spanning' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:100:9: 'spanning'
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
	// $ANTLR end "T__98"

	// $ANTLR start "T__99"
	public final void mT__99() throws RecognitionException {
		try {
			int _type = T__99;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:101:7: ( 'starting within' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:101:9: 'starting within'
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
	// $ANTLR end "T__99"

	// $ANTLR start "T__100"
	public final void mT__100() throws RecognitionException {
		try {
			int _type = T__100;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:102:8: ( 'tolerance' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:102:10: 'tolerance'
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
	// $ANTLR end "T__100"

	// $ANTLR start "T__101"
	public final void mT__101() throws RecognitionException {
		try {
			int _type = T__101;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:103:8: ( 'trends flat' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:103:10: 'trends flat'
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
	// $ANTLR end "T__101"

	// $ANTLR start "T__102"
	public final void mT__102() throws RecognitionException {
		try {
			int _type = T__102;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:104:8: ( 'trends like' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:104:10: 'trends like'
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
	// $ANTLR end "T__102"

	// $ANTLR start "T__103"
	public final void mT__103() throws RecognitionException {
		try {
			int _type = T__103;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:105:8: ( 'trends unlike' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:105:10: 'trends unlike'
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
	// $ANTLR end "T__103"

	// $ANTLR start "Operation"
	public final void mOperation() throws RecognitionException {
		try {
			int _type = Operation;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:354:7: ({...}? => ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )+ )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:354:9: {...}? => ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )+
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:354:60: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )+
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:358:7: ( ( ( '-' )? ( '0' .. '9' )+ ( '.' ( '0' .. '9' )+ )? ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:358:9: ( ( '-' )? ( '0' .. '9' )+ ( '.' ( '0' .. '9' )+ )? )
			{
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:358:9: ( ( '-' )? ( '0' .. '9' )+ ( '.' ( '0' .. '9' )+ )? )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:358:11: ( '-' )? ( '0' .. '9' )+ ( '.' ( '0' .. '9' )+ )?
			{
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:358:11: ( '-' )?
			int alt2=2;
			int LA2_0 = input.LA(1);
			if ( (LA2_0=='-') ) {
				alt2=1;
			}
			switch (alt2) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:358:12: '-'
					{
					match('-'); 
					}
					break;

			}

			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:358:18: ( '0' .. '9' )+
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

			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:358:30: ( '.' ( '0' .. '9' )+ )?
			int alt5=2;
			int LA5_0 = input.LA(1);
			if ( (LA5_0=='.') ) {
				alt5=1;
			}
			switch (alt5) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:358:31: '.' ( '0' .. '9' )+
					{
					match('.'); 
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:358:35: ( '0' .. '9' )+
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:362:6: ( '\"' ( 'a' .. 'z' | 'A' .. 'Z' | '.' | '_' )* '\"' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:362:8: '\"' ( 'a' .. 'z' | 'A' .. 'Z' | '.' | '_' )* '\"'
			{
			match('\"'); 
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:362:12: ( 'a' .. 'z' | 'A' .. 'Z' | '.' | '_' )*
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:366:4: ({...}? => ( 'open' | 'close' | 'high' | 'low' | 'volume' ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:366:6: {...}? => ( 'open' | 'close' | 'high' | 'low' | 'volume' )
			{
			if ( !((runtimeHistoryOpAhead())) ) {
				throw new FailedPredicateException(input, "HistoricalData", "runtimeHistoryOpAhead()");
			}
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:366:36: ( 'open' | 'close' | 'high' | 'low' | 'volume' )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:366:38: 'open'
					{
					match("open"); 

					}
					break;
				case 2 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:366:47: 'close'
					{
					match("close"); 

					}
					break;
				case 3 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:366:57: 'high'
					{
					match("high"); 

					}
					break;
				case 4 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:366:66: 'low'
					{
					match("low"); 

					}
					break;
				case 5 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:366:75: 'volume'
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:370:7: ( ( ( ' ' )+ ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:370:9: ( ( ' ' )+ )
			{
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:370:9: ( ( ' ' )+ )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:370:10: ( ' ' )+
			{
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:370:10: ( ' ' )+
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:370:10: ' '
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:374:6: ( ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )+ )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:374:8: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )+
			{
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:374:8: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )+
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:379:5: ( ( '\\r' | '\\t' | '\\u000C' | '\\n' ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:379:7: ( '\\r' | '\\t' | '\\u000C' | '\\n' )
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:382:5: ( '/*' ( . )* '*/' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:382:7: '/*' ( . )* '*/'
			{
			match("/*"); 

			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:382:12: ( . )*
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:382:12: .
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:385:5: ( '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:385:7: '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n'
			{
			match("//"); 

			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:385:12: (~ ( '\\n' | '\\r' ) )*
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

			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:385:26: ( '\\r' )?
			int alt12=2;
			int LA12_0 = input.LA(1);
			if ( (LA12_0=='\r') ) {
				alt12=1;
			}
			switch (alt12) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:385:26: '\\r'
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
		// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:8: ( AND | CLOSEPARENTEHSIS | CLOSESQRT | COMMA | DAYS | LENIENT | MATCHING | NOT | OPENPARENTEHSIS | OPENSQRT | OR | PERCENT | SEMICOLUMN | T__60 | T__61 | T__62 | T__63 | T__64 | T__65 | T__66 | T__67 | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | T__79 | T__80 | T__81 | T__82 | T__83 | T__84 | T__85 | T__86 | T__87 | T__88 | T__89 | T__90 | T__91 | T__92 | T__93 | T__94 | T__95 | T__96 | T__97 | T__98 | T__99 | T__100 | T__101 | T__102 | T__103 | Operation | NumberToken | StringToken | HistoricalData | WhiteChar | Tcheat | WS | COMMENT | LINE_COMMENT )
		int alt13=66;
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
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:120: T__60
				{
				mT__60(); 

				}
				break;
			case 15 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:126: T__61
				{
				mT__61(); 

				}
				break;
			case 16 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:132: T__62
				{
				mT__62(); 

				}
				break;
			case 17 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:138: T__63
				{
				mT__63(); 

				}
				break;
			case 18 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:144: T__64
				{
				mT__64(); 

				}
				break;
			case 19 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:150: T__65
				{
				mT__65(); 

				}
				break;
			case 20 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:156: T__66
				{
				mT__66(); 

				}
				break;
			case 21 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:162: T__67
				{
				mT__67(); 

				}
				break;
			case 22 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:168: T__68
				{
				mT__68(); 

				}
				break;
			case 23 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:174: T__69
				{
				mT__69(); 

				}
				break;
			case 24 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:180: T__70
				{
				mT__70(); 

				}
				break;
			case 25 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:186: T__71
				{
				mT__71(); 

				}
				break;
			case 26 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:192: T__72
				{
				mT__72(); 

				}
				break;
			case 27 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:198: T__73
				{
				mT__73(); 

				}
				break;
			case 28 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:204: T__74
				{
				mT__74(); 

				}
				break;
			case 29 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:210: T__75
				{
				mT__75(); 

				}
				break;
			case 30 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:216: T__76
				{
				mT__76(); 

				}
				break;
			case 31 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:222: T__77
				{
				mT__77(); 

				}
				break;
			case 32 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:228: T__78
				{
				mT__78(); 

				}
				break;
			case 33 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:234: T__79
				{
				mT__79(); 

				}
				break;
			case 34 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:240: T__80
				{
				mT__80(); 

				}
				break;
			case 35 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:246: T__81
				{
				mT__81(); 

				}
				break;
			case 36 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:252: T__82
				{
				mT__82(); 

				}
				break;
			case 37 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:258: T__83
				{
				mT__83(); 

				}
				break;
			case 38 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:264: T__84
				{
				mT__84(); 

				}
				break;
			case 39 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:270: T__85
				{
				mT__85(); 

				}
				break;
			case 40 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:276: T__86
				{
				mT__86(); 

				}
				break;
			case 41 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:282: T__87
				{
				mT__87(); 

				}
				break;
			case 42 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:288: T__88
				{
				mT__88(); 

				}
				break;
			case 43 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:294: T__89
				{
				mT__89(); 

				}
				break;
			case 44 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:300: T__90
				{
				mT__90(); 

				}
				break;
			case 45 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:306: T__91
				{
				mT__91(); 

				}
				break;
			case 46 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:312: T__92
				{
				mT__92(); 

				}
				break;
			case 47 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:318: T__93
				{
				mT__93(); 

				}
				break;
			case 48 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:324: T__94
				{
				mT__94(); 

				}
				break;
			case 49 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:330: T__95
				{
				mT__95(); 

				}
				break;
			case 50 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:336: T__96
				{
				mT__96(); 

				}
				break;
			case 51 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:342: T__97
				{
				mT__97(); 

				}
				break;
			case 52 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:348: T__98
				{
				mT__98(); 

				}
				break;
			case 53 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:354: T__99
				{
				mT__99(); 

				}
				break;
			case 54 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:360: T__100
				{
				mT__100(); 

				}
				break;
			case 55 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:367: T__101
				{
				mT__101(); 

				}
				break;
			case 56 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:374: T__102
				{
				mT__102(); 

				}
				break;
			case 57 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:381: T__103
				{
				mT__103(); 

				}
				break;
			case 58 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:388: Operation
				{
				mOperation(); 

				}
				break;
			case 59 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:398: NumberToken
				{
				mNumberToken(); 

				}
				break;
			case 60 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:410: StringToken
				{
				mStringToken(); 

				}
				break;
			case 61 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:422: HistoricalData
				{
				mHistoricalData(); 

				}
				break;
			case 62 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:437: WhiteChar
				{
				mWhiteChar(); 

				}
				break;
			case 63 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:447: Tcheat
				{
				mTcheat(); 

				}
				break;
			case 64 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:454: WS
				{
				mWS(); 

				}
				break;
			case 65 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:457: COMMENT
				{
				mCOMMENT(); 

				}
				break;
			case 66 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:465: LINE_COMMENT
				{
				mLINE_COMMENT(); 

				}
				break;

		}
	}


	protected DFA13 dfa13 = new DFA13(this);
	static final String DFA13_eotS =
		"\1\uffff\1\45\3\uffff\4\45\2\uffff\1\45\2\uffff\13\45\1\uffff\1\31\1\uffff"+
		"\2\45\4\uffff\1\110\1\112\1\113\1\uffff\1\115\1\117\1\121\1\123\1\126"+
		"\1\130\1\132\1\133\1\135\1\137\1\141\1\143\1\145\1\147\1\151\1\153\1\155"+
		"\1\157\1\161\1\163\1\165\1\167\1\171\1\173\1\175\1\177\1\u0081\1\u0083"+
		"\1\u0085\1\u0087\1\u0089\2\uffff\1\u008a\1\uffff\1\u008c\2\uffff\1\u008e"+
		"\1\uffff\1\u0090\1\uffff\1\u0092\1\uffff\1\u0093\1\uffff\1\u0095\1\u0097"+
		"\1\uffff\1\u0099\1\uffff\1\u009a\2\uffff\1\u009c\1\uffff\1\u009e\1\uffff"+
		"\1\u009f\1\uffff\1\u00a1\1\uffff\1\u00a3\1\uffff\1\u00a5\1\uffff\1\u00a7"+
		"\1\uffff\1\u00a9\1\uffff\1\u00ab\1\uffff\1\u00ad\1\uffff\1\u00ae\1\uffff"+
		"\1\u00b0\1\uffff\1\u00b2\3\uffff\1\u00b6\1\uffff\1\u00b8\1\uffff\1\u00ba"+
		"\1\uffff\1\u00bc\1\uffff\1\u00be\1\uffff\1\u00c0\1\uffff\1\u00c2\1\uffff"+
		"\1\u00c4\1\uffff\1\u00c6\2\uffff\1\u00c8\1\uffff\1\u00c9\1\uffff\1\u00cb"+
		"\1\uffff\1\u00cd\2\uffff\1\u00d0\1\uffff\1\u00d2\1\uffff\1\u00d4\2\uffff"+
		"\1\u00d6\1\uffff\1\u00d7\2\uffff\1\u00d9\1\uffff\1\u00db\1\uffff\1\u00dd"+
		"\1\uffff\1\u00df\1\uffff\1\u00e1\1\uffff\1\u00e3\1\uffff\1\u00e5\2\uffff"+
		"\1\u00e7\1\uffff\1\u00e9\3\uffff\1\u00ee\1\uffff\1\u00f0\1\uffff\1\u00f2"+
		"\1\uffff\1\u00f4\1\uffff\1\u00f6\1\uffff\1\u00f8\1\uffff\1\u00fa\1\uffff"+
		"\1\u00fb\1\uffff\1\u00fd\4\uffff\1\u00ff\1\uffff\1\u0101\2\uffff\1\u0103"+
		"\1\uffff\1\u0105\3\uffff\1\u0107\2\uffff\1\u0109\1\uffff\1\u010b\1\uffff"+
		"\1\u010d\1\uffff\1\u010e\1\uffff\1\u0110\1\uffff\1\u0112\1\uffff\1\u0114"+
		"\3\uffff\1\u0117\4\uffff\1\u011c\1\uffff\1\u011e\1\uffff\1\u0120\1\uffff"+
		"\1\u0122\1\uffff\1\u0124\1\uffff\1\u0126\1\uffff\1\u0128\2\uffff\1\u012a"+
		"\1\uffff\1\u012c\1\uffff\1\u012e\1\uffff\1\u0130\3\uffff\1\u0133\1\uffff"+
		"\1\u0135\1\uffff\1\u0137\1\uffff\1\u0139\2\uffff\1\u013b\1\uffff\1\u013d"+
		"\1\uffff\1\u013f\7\uffff\1\u0144\3\uffff\1\u0146\1\uffff\1\u0148\1\uffff"+
		"\1\u014a\1\uffff\1\u014c\1\uffff\1\u014e\1\uffff\1\u014f\1\uffff\1\u0151"+
		"\1\uffff\1\u0152\1\uffff\1\u0154\2\uffff\1\u0157\1\uffff\1\u0158\1\uffff"+
		"\1\u0159\1\uffff\1\u015b\3\uffff\1\u015c\6\uffff\1\u0163\1\uffff\1\u0165"+
		"\1\uffff\1\u0167\1\uffff\1\u0169\1\uffff\1\u016b\4\uffff\1\u0170\2\uffff"+
		"\1\u0171\2\uffff\1\u0176\13\uffff\1\u017f\1\uffff\1\u0180\1\uffff\1\u0181"+
		"\1\uffff\1\u0183\1\uffff\1\u0185\4\uffff\1\u0186\24\uffff\1\u0193\65\uffff";
	static final String DFA13_eofS =
		"\u01ba\uffff";
	static final String DFA13_minS =
		"\1\11\1\60\3\uffff\4\60\2\uffff\1\60\2\uffff\13\60\1\uffff\1\60\1\uffff"+
		"\2\60\3\uffff\1\52\3\60\1\uffff\25\60\1\40\11\60\2\uffff\1\60\1\0\1\60"+
		"\2\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\2\60\1\0\1\60\1\0\1\60\1\0\1"+
		"\uffff\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0"+
		"\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\141\1\0\1\60"+
		"\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1"+
		"\60\1\0\1\uffff\1\40\1\0\1\60\1\0\1\60\1\0\1\60\2\0\1\60\1\0\1\60\1\0"+
		"\1\40\1\0\1\uffff\1\60\1\0\1\60\1\0\1\uffff\1\60\1\0\1\60\1\0\1\60\1\0"+
		"\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\uffff\1\40\1\0\1\60\1\0\1\142\1"+
		"\145\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1"+
		"\60\1\0\1\60\1\0\1\uffff\1\0\1\uffff\1\60\1\0\1\60\1\0\1\uffff\1\60\1"+
		"\0\1\40\1\0\1\uffff\1\0\1\60\1\uffff\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1"+
		"\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\144\1\0\1\60\1\0\1\157\1\141\1\uffff"+
		"\1\60\1\0\1\40\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\2\0\1\60\1"+
		"\0\1\60\1\0\1\60\1\0\1\60\1\0\1\141\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60"+
		"\2\0\1\40\1\0\1\60\1\0\1\40\1\0\3\uffff\1\166\1\162\1\157\1\60\1\0\1\uffff"+
		"\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\40\1\0\1\60\1\0\1\60\1\0\1"+
		"\60\1\0\1\60\1\0\1\40\1\60\1\0\1\60\1\0\1\60\1\0\1\40\1\0\1\uffff\1\0"+
		"\1\60\1\0\1\150\1\0\1\145\1\151\1\167\1\60\1\0\1\60\1\0\1\60\1\0\1\60"+
		"\1\0\1\60\1\0\1\146\2\0\1\60\1\0\1\uffff\1\60\1\0\1\150\1\40\1\0\2\uffff"+
		"\1\144\1\0\2\uffff\1\150\1\40\1\163\2\40\1\0\1\60\1\0\1\60\1\0\1\40\1"+
		"\0\1\60\1\0\3\uffff\1\60\1\0\1\uffff\1\151\1\157\1\165\1\uffff\1\0\1\157"+
		"\1\160\2\uffff\3\150\1\144\1\0\3\uffff\1\0\1\60\1\0\1\uffff\1\147\1\167"+
		"\1\160\1\167\1\40\2\uffff\1\40\5\uffff\1\150\1\145\1\160\1\156\1\150\1"+
		"\151\1\145\1\162\1\157\1\40\4\uffff\1\162\1\40\1\162\1\150\1\40\1\150"+
		"\1\164\2\uffff\1\150\2\uffff\1\40\2\uffff\1\142\1\162\1\145\1\141\1\153"+
		"\1\40\1\144\2\uffff";
	static final String DFA13_maxS =
		"\2\172\3\uffff\4\172\2\uffff\1\172\2\uffff\13\172\1\uffff\1\172\1\uffff"+
		"\2\172\3\uffff\1\57\3\172\1\uffff\37\172\2\uffff\1\172\1\0\1\172\2\0\1"+
		"\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\2\172\1\0\1\172\1\0\1\172\1\0\1"+
		"\uffff\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172"+
		"\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\142"+
		"\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172"+
		"\1\0\1\172\1\0\1\172\1\0\1\uffff\1\172\1\0\1\172\1\0\1\172\1\0\1\172\2"+
		"\0\1\172\1\0\1\172\1\0\1\172\1\0\1\uffff\1\172\1\0\1\172\1\0\1\uffff\1"+
		"\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1"+
		"\uffff\1\172\1\0\1\172\1\0\1\142\1\165\1\172\1\0\1\172\1\0\1\172\1\0\1"+
		"\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\uffff\1\0"+
		"\1\uffff\1\172\1\0\1\172\1\0\1\uffff\1\172\1\0\1\172\1\0\1\uffff\1\0\1"+
		"\172\1\uffff\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172"+
		"\1\0\1\172\1\0\1\165\1\0\1\172\1\0\1\157\1\154\1\uffff\1\172\1\0\1\172"+
		"\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\2\0\1\172\1\0\1\172"+
		"\1\0\1\172\1\0\1\172\1\0\1\141\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172"+
		"\2\0\1\172\1\0\1\172\1\0\1\172\1\0\3\uffff\1\166\1\162\1\157\1\172\1\0"+
		"\1\uffff\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1"+
		"\0\1\172\1\0\1\172\1\0\1\172\1\0\1\40\1\172\1\0\1\172\1\0\1\172\1\0\1"+
		"\172\1\0\1\uffff\1\0\1\172\1\0\1\164\1\0\1\145\1\151\1\167\1\172\1\0\1"+
		"\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\165\2\0\1\172\1\0\1\uffff\1\172"+
		"\1\0\1\163\1\172\1\0\2\uffff\1\165\1\0\2\uffff\1\162\1\40\1\163\1\40\1"+
		"\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\3\uffff\1\172\1\0\1\uffff"+
		"\1\151\1\157\1\165\1\uffff\1\0\1\157\1\160\2\uffff\1\164\1\150\1\164\1"+
		"\165\1\0\3\uffff\1\0\1\172\1\0\1\uffff\1\147\1\167\1\160\1\167\1\40\2"+
		"\uffff\1\40\5\uffff\1\150\1\145\1\160\1\156\1\164\1\167\1\145\1\162\1"+
		"\157\1\40\4\uffff\1\162\1\40\1\162\1\164\1\40\1\154\1\164\2\uffff\1\154"+
		"\2\uffff\1\40\2\uffff\1\142\1\162\1\145\1\141\1\153\1\40\1\165\2\uffff";
	static final String DFA13_acceptS =
		"\2\uffff\1\2\1\3\1\4\4\uffff\1\11\1\12\1\uffff\1\14\1\15\13\uffff\1\73"+
		"\1\uffff\1\74\2\uffff\1\76\1\72\1\100\4\uffff\1\77\37\uffff\1\101\1\102"+
		"\24\uffff\1\13\56\uffff\1\1\17\uffff\1\10\4\uffff\1\16\16\uffff\1\34\30"+
		"\uffff\1\17\1\uffff\1\5\4\uffff\1\75\4\uffff\1\55\2\uffff\1\56\25\uffff"+
		"\1\46\50\uffff\1\35\1\36\1\37\5\uffff\1\62\34\uffff\1\27\27\uffff\1\6"+
		"\5\uffff\1\20\1\21\2\uffff\1\30\1\31\16\uffff\1\67\1\70\1\71\2\uffff\1"+
		"\7\3\uffff\1\57\3\uffff\1\32\1\33\5\uffff\1\63\1\64\1\65\3\uffff\1\26"+
		"\5\uffff\1\40\1\41\1\uffff\1\44\1\45\1\60\1\61\1\66\12\uffff\1\24\1\25"+
		"\1\42\1\43\7\uffff\1\22\1\23\1\uffff\1\51\1\52\1\uffff\1\47\1\50\7\uffff"+
		"\1\53\1\54";
	static final String DFA13_specialS =
		"\1\6\1\u0092\3\uffff\1\u009b\1\u0084\1\u0083\1\171\2\uffff\1\75\2\uffff"+
		"\1\173\1\u0099\1\172\1\73\1\u0098\1\u0096\1\57\1\5\1\u0132\1\60\1\141"+
		"\3\uffff\1\144\1\113\4\uffff\1\117\1\151\1\61\1\uffff\1\124\1\174\1\126"+
		"\1\106\1\u008f\1\u0134\1\140\1\u0125\1\u0123\1\76\1\150\1\154\1\161\1"+
		"\114\1\100\1\u0085\1\u008a\1\142\1\u0090\1\7\1\u0093\1\4\1\u0129\1\10"+
		"\1\16\1\24\1\35\1\47\1\31\1\104\1\107\2\uffff\1\u0097\1\u009d\1\152\1"+
		"\u009e\1\u009f\1\125\1\u00ab\1\175\1\u00d6\1\127\1\u00d7\1\72\1\u009c"+
		"\1\133\1\166\1\u00f6\1\u0135\1\u010f\1\u0122\1\u00c5\1\uffff\1\u0124\1"+
		"\u0104\1\77\1\u00ac\1\u0126\1\u00cb\1\155\1\u00ef\1\162\1\u00f5\1\116"+
		"\1\u00c2\1\101\1\u00e7\1\u0086\1\u00f3\1\u008b\1\u00c3\1\143\1\u00b7\1"+
		"\103\1\u00d5\1\11\1\u00f4\1\u0094\1\u00d8\1\uffff\1\u0116\1\u012a\1\u00a3"+
		"\1\12\1\u0105\1\17\1\u00c4\1\25\1\u00c7\1\37\1\u00ea\1\50\1\u00e0\1\34"+
		"\1\u00b8\1\105\1\u00a4\1\110\1\u00cd\1\uffff\1\153\1\u00f2\1\u009a\1\u00af"+
		"\1\176\1\u00a0\1\130\1\u0110\1\62\1\134\1\u00bb\1\167\1\u00a9\1\u0136"+
		"\1\u00e1\1\uffff\1\u0091\1\u00b0\1\67\1\u00c1\1\uffff\1\156\1\u00e8\1"+
		"\163\1\u00b2\1\120\1\u00fa\1\102\1\u011a\1\u0087\1\u0117\1\u008c\1\u00de"+
		"\1\145\1\u00aa\1\uffff\1\13\1\u010c\1\u0095\1\u00b9\2\uffff\1\u012b\1"+
		"\u00a2\1\14\1\u00d9\1\20\1\u00d4\1\26\1\u00c8\1\40\1\u00b3\1\51\1\u00c6"+
		"\1\36\1\u00bc\1\71\1\u00ba\1\111\1\u00ca\1\uffff\1\u00b5\1\uffff\1\177"+
		"\1\u0118\1\131\1\u0103\1\uffff\1\135\1\u00bd\1\170\1\u00bf\1\uffff\1\u0106"+
		"\1\0\1\uffff\1\63\1\157\1\u011c\1\164\1\u0101\1\121\1\u00b1\1\70\1\u0114"+
		"\1\u0088\1\u0108\1\u008d\1\u00d2\1\146\1\u0109\1\uffff\1\u00be\1\115\1"+
		"\u00fb\3\uffff\1\u012c\1\u00c9\1\15\1\u0111\1\21\1\u00b4\1\27\1\u00d3"+
		"\1\41\1\u00a1\1\52\1\u0100\1\42\1\u00ed\1\64\1\112\1\u0112\1\u0080\1\u011f"+
		"\1\132\1\u00ee\1\136\1\u00e4\1\uffff\1\u00a7\1\1\1\u00e6\1\160\1\u00f8"+
		"\1\165\1\u0102\1\122\1\u00dd\1\65\1\u0089\1\u00ec\1\u008e\1\u00c0\1\147"+
		"\1\u00fc\6\uffff\1\u012d\1\u00e2\1\uffff\1\u010a\1\22\1\u010b\1\30\1\u00b6"+
		"\1\43\1\u00da\1\53\1\u00eb\1\45\1\u00ad\1\74\1\u00a5\1\u0081\1\u00e9\1"+
		"\u0120\1\u00d0\1\137\1\u0107\1\uffff\1\2\1\u00cc\1\u0130\1\u00ce\1\u0131"+
		"\1\u0119\1\123\1\u00ae\1\uffff\1\u00df\1\56\1\u00fe\1\uffff\1\u00e5\3"+
		"\uffff\1\u012e\1\u00a8\1\23\1\u010d\1\32\1\u010e\1\44\1\u00f0\1\54\1\u00a6"+
		"\1\uffff\1\u00d1\1\66\1\u0082\1\u00e3\1\uffff\1\u0121\1\u0115\1\uffff"+
		"\1\3\1\u00cf\3\uffff\1\u0113\6\uffff\1\u012f\1\u00dc\1\u0127\1\u00db\1"+
		"\u0128\1\u00f9\1\46\1\u00f1\1\55\1\u011d\3\uffff\1\33\1\u00f7\5\uffff"+
		"\1\u00ff\10\uffff\1\u011e\3\uffff\1\u011b\1\u0133\1\u00fd\64\uffff}>";
	static final String[] DFA13_transitionS = {
			"\2\40\1\uffff\2\40\22\uffff\1\36\1\uffff\1\33\2\uffff\1\14\2\uffff\1"+
			"\11\1\2\2\uffff\1\4\1\31\1\uffff\1\41\12\32\1\uffff\1\15\5\uffff\15\35"+
			"\1\16\14\35\1\12\1\uffff\1\3\1\uffff\1\37\1\uffff\1\1\1\17\1\20\1\5\1"+
			"\21\1\22\1\23\1\30\1\24\2\35\1\6\1\7\1\10\1\13\2\35\1\25\1\26\1\27\1"+
			"\35\1\34\4\35",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\13\44\1\43\1\44\1\42\14\44",
			"",
			"",
			"",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\1\46\7\44\1\47\21\44",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\4\44\1\50\11\44\1\51\13\44",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\1\52\15\44\1\53\13\44",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\16\44\1\54\13\44",
			"",
			"",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\17\44\1\57\1\44\1\55\3\44"+
			"\1\56\4\44",
			"",
			"",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\1\60\31\44",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\4\44\1\61\17\44\1\62\5\44",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\13\44\1\64\5\44\1\63\10\44",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\15\44\1\65\1\44\1\66\1\67"+
			"\11\44",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\16\44\1\70\13\44",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\16\44\1\71\2\44\1\72\10\44",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\22\44\1\73\7\44",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\4\44\1\74\25\44",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\13\44\1\75\1\76\2\44\1\77"+
			"\3\44\1\100\6\44",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\16\44\1\101\2\44\1\102\10"+
			"\44",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\10\44\1\103\21\44",
			"",
			"\12\32\7\uffff\32\45\6\uffff\32\45",
			"",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\16\44\1\104\13\44",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\32\44",
			"",
			"",
			"",
			"\1\105\4\uffff\1\106",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\3\44\1\107\26\44",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\22\44\1\111\7\44",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\32\44",
			"",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\30\44\1\114\1\44",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\21\44\1\116\10\44",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\15\44\1\120\14\44",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\26\44\1\122\3\44",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\12\44\1\125\10\44\1\124\6"+
			"\44",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\21\44\1\127\10\44",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\23\44\1\131\6\44",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\32\44",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\4\44\1\134\25\44",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\4\44\1\136\25\44",
			"\12\44\7\uffff\15\44\1\140\14\44\4\uffff\1\37\1\uffff\32\44",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\1\142\31\44",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\13\44\1\144\16\44",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\16\44\1\146\13\44",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\16\44\1\150\13\44",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\3\44\1\152\26\44",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\22\44\1\154\7\44",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\24\44\1\156\5\44",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\21\44\1\160\10\44",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\4\44\1\162\25\44",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\4\44\1\164\25\44",
			"\1\166\17\uffff\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\32\44",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\25\44\1\170\4\44",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\16\44\1\172\13\44",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\16\44\1\174\13\44",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\1\176\31\44",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\1\u0080\31\44",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\13\44\1\u0082\16\44",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\4\44\1\u0084\25\44",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\6\44\1\u0086\23\44",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\13\44\1\u0088\16\44",
			"",
			"",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\32\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\16\44\1\u008b\13\44",
			"\1\uffff",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\22\44\1\u008d\7\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\4\44\1\u008f\25\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\10\44\1\u0091\21\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\32\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\2\44\1\u0094\27\44",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\4\44\1\u0096\25\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\4\44\1\u0098\25\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\32\44",
			"\1\uffff",
			"",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\21\44\1\u009b\10\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\15\44\1\u009d\14\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\32\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\21\44\1\u00a0\10\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\13\44\1\u00a2\16\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\22\44\1\u00a4\7\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\22\44\1\u00a6\7\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\10\44\1\u00a8\21\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\10\44\1\u00aa\21\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\1\u00ac\31\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\32\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\22\44\1\u00af\7\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\4\44\1\u00b1\25\44",
			"\1\uffff",
			"\1\u00b3\1\u00b4",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\4\44\1\u00b5\25\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\17\44\1\u00b7\12\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\16\44\1\u00b9\13\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\15\44\1\u00bb\14\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\21\44\1\u00bd\10\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\4\44\1\u00bf\25\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\15\44\1\u00c1\14\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\7\44\1\u00c3\22\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\24\44\1\u00c5\5\44",
			"\1\uffff",
			"",
			"\1\u00c7\17\uffff\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\32\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\32\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\2\44\1\u00ca\27\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\4\44\1\u00cc\25\44",
			"\1\uffff",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\7\44\1\u00cf\22\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\22\44\1\u00d1\7\44",
			"\1\uffff",
			"\1\u00d3\17\uffff\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\32\44",
			"\1\uffff",
			"",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\21\44\1\u00d5\10\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\32\44",
			"\1\uffff",
			"",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\10\44\1\u00d8\21\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\10\44\1\u00da\21\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\22\44\1\u00dc\7\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\4\44\1\u00de\25\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\15\44\1\u00e0\14\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\13\44\1\u00e2\16\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\13\44\1\u00e4\16\44",
			"\1\uffff",
			"",
			"\1\u00e6\17\uffff\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\32\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\3\44\1\u00e8\26\44",
			"\1\uffff",
			"\1\u00ea",
			"\1\u00eb\17\uffff\1\u00ec",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\21\44\1\u00ed\10\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\4\44\1\u00ef\25\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\23\44\1\u00f1\6\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\15\44\1\u00f3\14\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\23\44\1\u00f5\6\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\21\44\1\u00f7\10\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\3\44\1\u00f9\26\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\32\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\14\44\1\u00fc\15\44",
			"\1\uffff",
			"",
			"\1\uffff",
			"",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\23\44\1\u00fe\6\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\15\44\1\u0100\14\44",
			"\1\uffff",
			"",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\10\44\1\u0102\21\44",
			"\1\uffff",
			"\1\u0104\17\uffff\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\32\44",
			"\1\uffff",
			"",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\10\44\1\u0106\21\44",
			"",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\22\44\1\u0108\7\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\22\44\1\u010a\7\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\4\44\1\u010c\25\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\32\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\6\44\1\u010f\23\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\16\44\1\u0111\13\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\22\44\1\u0113\7\44",
			"\1\uffff",
			"\1\u0115\20\uffff\1\u0116",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\32\44",
			"\1\uffff",
			"\1\u0118",
			"\1\u0119\12\uffff\1\u011a",
			"",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\22\44\1\u011b\7\44",
			"\1\uffff",
			"\1\u011d\17\uffff\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\32\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\7\44\1\u011f\22\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\10\44\1\u0121\21\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\10\44\1\u0123\21\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\1\u0125\31\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\22\44\1\u0127\7\44",
			"\1\uffff",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\4\44\1\u0129\25\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\10\44\1\u012b\21\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\23\44\1\u012d\6\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\15\44\1\u012f\14\44",
			"\1\uffff",
			"\1\u0131",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\3\44\1\u0132\26\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\7\44\1\u0134\22\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\7\44\1\u0136\22\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\22\44\1\u0138\7\44",
			"\1\uffff",
			"\1\uffff",
			"\1\u013a\17\uffff\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\32\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\15\44\1\u013c\14\44",
			"\1\uffff",
			"\1\u013e\17\uffff\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\32\44",
			"\1\uffff",
			"",
			"",
			"",
			"\1\u0140",
			"\1\u0141",
			"\1\u0142",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\4\44\1\u0143\25\44",
			"\1\uffff",
			"",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\4\44\1\u0145\25\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\15\44\1\u0147\14\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\15\44\1\u0149\14\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\15\44\1\u014b\14\44",
			"\1\uffff",
			"\1\u014d\17\uffff\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\32\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\32\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\16\44\1\u0150\13\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\32\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\6\44\1\u0153\23\44",
			"\1\uffff",
			"\1\u0155",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\4\44\1\u0156\25\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\32\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\32\44",
			"\1\uffff",
			"\1\u015a\17\uffff\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\32\44",
			"\1\uffff",
			"",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\32\44",
			"\1\uffff",
			"\1\u015d\13\uffff\1\u015e",
			"\1\uffff",
			"\1\u015f",
			"\1\u0160",
			"\1\u0161",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\22\44\1\u0162\7\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\3\44\1\u0164\26\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\6\44\1\u0166\23\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\6\44\1\u0168\23\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\2\44\1\u016a\27\44",
			"\1\uffff",
			"\1\u016c\5\uffff\1\u016d\10\uffff\1\u016e",
			"\1\uffff",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\15\44\1\u016f\14\44",
			"\1\uffff",
			"",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\32\44",
			"\1\uffff",
			"\1\u0172\3\uffff\1\u0173\6\uffff\1\u0174",
			"\1\u0175\17\uffff\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\32\44",
			"\1\uffff",
			"",
			"",
			"\1\u0177\20\uffff\1\u0178",
			"\1\uffff",
			"",
			"",
			"\1\u0179\11\uffff\1\u017a",
			"\1\u017b",
			"\1\u017c",
			"\1\u017d",
			"\1\u017e\17\uffff\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\32\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\32\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\32\44",
			"\1\uffff",
			"\1\u0182\17\uffff\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\32\44",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\4\44\1\u0184\25\44",
			"\1\uffff",
			"",
			"",
			"",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\32\44",
			"\1\uffff",
			"",
			"\1\u0187",
			"\1\u0188",
			"\1\u0189",
			"",
			"\1\uffff",
			"\1\u018a",
			"\1\u018b",
			"",
			"",
			"\1\u018c\13\uffff\1\u018d",
			"\1\u018e",
			"\1\u018f\13\uffff\1\u0190",
			"\1\u0191\20\uffff\1\u0192",
			"\1\uffff",
			"",
			"",
			"",
			"\1\uffff",
			"\12\44\7\uffff\32\44\4\uffff\1\37\1\uffff\32\44",
			"\1\uffff",
			"",
			"\1\u0194",
			"\1\u0195",
			"\1\u0196",
			"\1\u0197",
			"\1\u0198",
			"",
			"",
			"\1\u0199",
			"",
			"",
			"",
			"",
			"",
			"\1\u019a",
			"\1\u019b",
			"\1\u019c",
			"\1\u019d",
			"\1\u019e\13\uffff\1\u019f",
			"\1\u01a0\15\uffff\1\u01a1",
			"\1\u01a2",
			"\1\u01a3",
			"\1\u01a4",
			"\1\u01a5",
			"",
			"",
			"",
			"",
			"\1\u01a6",
			"\1\u01a7",
			"\1\u01a8",
			"\1\u01a9\13\uffff\1\u01aa",
			"\1\u01ab",
			"\1\u01ac\3\uffff\1\u01ad",
			"\1\u01ae",
			"",
			"",
			"\1\u01af\3\uffff\1\u01b0",
			"",
			"",
			"\1\u01b1",
			"",
			"",
			"\1\u01b2",
			"\1\u01b3",
			"\1\u01b4",
			"\1\u01b5",
			"\1\u01b6",
			"\1\u01b7",
			"\1\u01b8\20\uffff\1\u01b9",
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
			return "1:1: Tokens : ( AND | CLOSEPARENTEHSIS | CLOSESQRT | COMMA | DAYS | LENIENT | MATCHING | NOT | OPENPARENTEHSIS | OPENSQRT | OR | PERCENT | SEMICOLUMN | T__60 | T__61 | T__62 | T__63 | T__64 | T__65 | T__66 | T__67 | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | T__79 | T__80 | T__81 | T__82 | T__83 | T__84 | T__85 | T__86 | T__87 | T__88 | T__89 | T__90 | T__91 | T__92 | T__93 | T__94 | T__95 | T__96 | T__97 | T__98 | T__99 | T__100 | T__101 | T__102 | T__103 | Operation | NumberToken | StringToken | HistoricalData | WhiteChar | Tcheat | WS | COMMENT | LINE_COMMENT );";
		}
		@Override
		public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
			IntStream input = _input;
			int _s = s;
			switch ( s ) {
					case 0 : 
						int LA13_213 = input.LA(1);
						 
						int index13_213 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_213=='i') ) {s = 262;}
						else if ( ((LA13_213 >= '0' && LA13_213 <= '9')||(LA13_213 >= 'A' && LA13_213 <= 'Z')||(LA13_213 >= 'a' && LA13_213 <= 'h')||(LA13_213 >= 'j' && LA13_213 <= 'z')) ) {s = 36;}
						else if ( (LA13_213=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 263;
						 
						input.seek(index13_213);
						if ( s>=0 ) return s;
						break;

					case 1 : 
						int LA13_262 = input.LA(1);
						 
						int index13_262 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_262=='d') ) {s = 306;}
						else if ( ((LA13_262 >= '0' && LA13_262 <= '9')||(LA13_262 >= 'A' && LA13_262 <= 'Z')||(LA13_262 >= 'a' && LA13_262 <= 'c')||(LA13_262 >= 'e' && LA13_262 <= 'z')) ) {s = 36;}
						else if ( (LA13_262=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 307;
						 
						input.seek(index13_262);
						if ( s>=0 ) return s;
						break;

					case 2 : 
						int LA13_306 = input.LA(1);
						 
						int index13_306 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_306=='e') ) {s = 342;}
						else if ( ((LA13_306 >= '0' && LA13_306 <= '9')||(LA13_306 >= 'A' && LA13_306 <= 'Z')||(LA13_306 >= 'a' && LA13_306 <= 'd')||(LA13_306 >= 'f' && LA13_306 <= 'z')) ) {s = 36;}
						else if ( (LA13_306=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 343;
						 
						input.seek(index13_306);
						if ( s>=0 ) return s;
						break;

					case 3 : 
						int LA13_342 = input.LA(1);
						 
						int index13_342 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_342==' ') ) {s = 373;}
						else if ( ((LA13_342 >= '0' && LA13_342 <= '9')||(LA13_342 >= 'A' && LA13_342 <= 'Z')||(LA13_342 >= 'a' && LA13_342 <= 'z')) ) {s = 36;}
						else if ( (LA13_342=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 374;
						 
						input.seek(index13_342);
						if ( s>=0 ) return s;
						break;

					case 4 : 
						int LA13_59 = input.LA(1);
						 
						int index13_59 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_59==' ') ) {s = 118;}
						else if ( ((LA13_59 >= '0' && LA13_59 <= '9')||(LA13_59 >= 'A' && LA13_59 <= 'Z')||(LA13_59 >= 'a' && LA13_59 <= 'z')) ) {s = 36;}
						else if ( (LA13_59=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 119;
						 
						input.seek(index13_59);
						if ( s>=0 ) return s;
						break;

					case 5 : 
						int LA13_21 = input.LA(1);
						 
						int index13_21 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_21=='e') ) {s = 60;}
						else if ( ((LA13_21 >= '0' && LA13_21 <= '9')||(LA13_21 >= 'A' && LA13_21 <= 'Z')||(LA13_21 >= 'a' && LA13_21 <= 'd')||(LA13_21 >= 'f' && LA13_21 <= 'z')) ) {s = 36;}
						else if ( (LA13_21=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 37;
						 
						input.seek(index13_21);
						if ( s>=0 ) return s;
						break;

					case 6 : 
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
						else if ( (LA13_0=='N') ) {s = 14;}
						else if ( (LA13_0=='b') ) {s = 15;}
						else if ( (LA13_0=='c') ) {s = 16;}
						else if ( (LA13_0=='e') ) {s = 17;}
						else if ( (LA13_0=='f') ) {s = 18;}
						else if ( (LA13_0=='g') ) {s = 19;}
						else if ( (LA13_0=='i') ) {s = 20;}
						else if ( (LA13_0=='r') ) {s = 21;}
						else if ( (LA13_0=='s') ) {s = 22;}
						else if ( (LA13_0=='t') ) {s = 23;}
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

					case 7 : 
						int LA13_57 = input.LA(1);
						 
						int index13_57 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_57=='e') ) {s = 114;}
						else if ( ((LA13_57 >= '0' && LA13_57 <= '9')||(LA13_57 >= 'A' && LA13_57 <= 'Z')||(LA13_57 >= 'a' && LA13_57 <= 'd')||(LA13_57 >= 'f' && LA13_57 <= 'z')) ) {s = 36;}
						else if ( (LA13_57=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 115;
						 
						input.seek(index13_57);
						if ( s>=0 ) return s;
						break;

					case 8 : 
						int LA13_61 = input.LA(1);
						 
						int index13_61 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_61=='o') ) {s = 122;}
						else if ( ((LA13_61 >= '0' && LA13_61 <= '9')||(LA13_61 >= 'A' && LA13_61 <= 'Z')||(LA13_61 >= 'a' && LA13_61 <= 'n')||(LA13_61 >= 'p' && LA13_61 <= 'z')) ) {s = 36;}
						else if ( (LA13_61=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 123;
						 
						input.seek(index13_61);
						if ( s>=0 ) return s;
						break;

					case 9 : 
						int LA13_114 = input.LA(1);
						 
						int index13_114 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_114=='s') ) {s = 175;}
						else if ( ((LA13_114 >= '0' && LA13_114 <= '9')||(LA13_114 >= 'A' && LA13_114 <= 'Z')||(LA13_114 >= 'a' && LA13_114 <= 'r')||(LA13_114 >= 't' && LA13_114 <= 'z')) ) {s = 36;}
						else if ( (LA13_114=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 176;
						 
						input.seek(index13_114);
						if ( s>=0 ) return s;
						break;

					case 10 : 
						int LA13_122 = input.LA(1);
						 
						int index13_122 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_122=='p') ) {s = 183;}
						else if ( ((LA13_122 >= '0' && LA13_122 <= '9')||(LA13_122 >= 'A' && LA13_122 <= 'Z')||(LA13_122 >= 'a' && LA13_122 <= 'o')||(LA13_122 >= 'q' && LA13_122 <= 'z')) ) {s = 36;}
						else if ( (LA13_122=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 184;
						 
						input.seek(index13_122);
						if ( s>=0 ) return s;
						break;

					case 11 : 
						int LA13_175 = input.LA(1);
						 
						int index13_175 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_175==' ') ) {s = 230;}
						else if ( ((LA13_175 >= '0' && LA13_175 <= '9')||(LA13_175 >= 'A' && LA13_175 <= 'Z')||(LA13_175 >= 'a' && LA13_175 <= 'z')) ) {s = 36;}
						else if ( (LA13_175=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 231;
						 
						input.seek(index13_175);
						if ( s>=0 ) return s;
						break;

					case 12 : 
						int LA13_183 = input.LA(1);
						 
						int index13_183 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_183=='e') ) {s = 239;}
						else if ( ((LA13_183 >= '0' && LA13_183 <= '9')||(LA13_183 >= 'A' && LA13_183 <= 'Z')||(LA13_183 >= 'a' && LA13_183 <= 'd')||(LA13_183 >= 'f' && LA13_183 <= 'z')) ) {s = 36;}
						else if ( (LA13_183=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 240;
						 
						input.seek(index13_183);
						if ( s>=0 ) return s;
						break;

					case 13 : 
						int LA13_239 = input.LA(1);
						 
						int index13_239 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_239==' ') ) {s = 285;}
						else if ( ((LA13_239 >= '0' && LA13_239 <= '9')||(LA13_239 >= 'A' && LA13_239 <= 'Z')||(LA13_239 >= 'a' && LA13_239 <= 'z')) ) {s = 36;}
						else if ( (LA13_239=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 286;
						 
						input.seek(index13_239);
						if ( s>=0 ) return s;
						break;

					case 14 : 
						int LA13_62 = input.LA(1);
						 
						int index13_62 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_62=='o') ) {s = 124;}
						else if ( ((LA13_62 >= '0' && LA13_62 <= '9')||(LA13_62 >= 'A' && LA13_62 <= 'Z')||(LA13_62 >= 'a' && LA13_62 <= 'n')||(LA13_62 >= 'p' && LA13_62 <= 'z')) ) {s = 36;}
						else if ( (LA13_62=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 125;
						 
						input.seek(index13_62);
						if ( s>=0 ) return s;
						break;

					case 15 : 
						int LA13_124 = input.LA(1);
						 
						int index13_124 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_124=='o') ) {s = 185;}
						else if ( ((LA13_124 >= '0' && LA13_124 <= '9')||(LA13_124 >= 'A' && LA13_124 <= 'Z')||(LA13_124 >= 'a' && LA13_124 <= 'n')||(LA13_124 >= 'p' && LA13_124 <= 'z')) ) {s = 36;}
						else if ( (LA13_124=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 186;
						 
						input.seek(index13_124);
						if ( s>=0 ) return s;
						break;

					case 16 : 
						int LA13_185 = input.LA(1);
						 
						int index13_185 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_185=='t') ) {s = 241;}
						else if ( ((LA13_185 >= '0' && LA13_185 <= '9')||(LA13_185 >= 'A' && LA13_185 <= 'Z')||(LA13_185 >= 'a' && LA13_185 <= 's')||(LA13_185 >= 'u' && LA13_185 <= 'z')) ) {s = 36;}
						else if ( (LA13_185=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 242;
						 
						input.seek(index13_185);
						if ( s>=0 ) return s;
						break;

					case 17 : 
						int LA13_241 = input.LA(1);
						 
						int index13_241 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_241=='h') ) {s = 287;}
						else if ( ((LA13_241 >= '0' && LA13_241 <= '9')||(LA13_241 >= 'A' && LA13_241 <= 'Z')||(LA13_241 >= 'a' && LA13_241 <= 'g')||(LA13_241 >= 'i' && LA13_241 <= 'z')) ) {s = 36;}
						else if ( (LA13_241=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 288;
						 
						input.seek(index13_241);
						if ( s>=0 ) return s;
						break;

					case 18 : 
						int LA13_287 = input.LA(1);
						 
						int index13_287 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_287=='e') ) {s = 325;}
						else if ( ((LA13_287 >= '0' && LA13_287 <= '9')||(LA13_287 >= 'A' && LA13_287 <= 'Z')||(LA13_287 >= 'a' && LA13_287 <= 'd')||(LA13_287 >= 'f' && LA13_287 <= 'z')) ) {s = 36;}
						else if ( (LA13_287=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 326;
						 
						input.seek(index13_287);
						if ( s>=0 ) return s;
						break;

					case 19 : 
						int LA13_325 = input.LA(1);
						 
						int index13_325 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_325=='d') ) {s = 356;}
						else if ( ((LA13_325 >= '0' && LA13_325 <= '9')||(LA13_325 >= 'A' && LA13_325 <= 'Z')||(LA13_325 >= 'a' && LA13_325 <= 'c')||(LA13_325 >= 'e' && LA13_325 <= 'z')) ) {s = 36;}
						else if ( (LA13_325=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 357;
						 
						input.seek(index13_325);
						if ( s>=0 ) return s;
						break;

					case 20 : 
						int LA13_63 = input.LA(1);
						 
						int index13_63 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_63=='a') ) {s = 126;}
						else if ( ((LA13_63 >= '0' && LA13_63 <= '9')||(LA13_63 >= 'A' && LA13_63 <= 'Z')||(LA13_63 >= 'b' && LA13_63 <= 'z')) ) {s = 36;}
						else if ( (LA13_63=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 127;
						 
						input.seek(index13_63);
						if ( s>=0 ) return s;
						break;

					case 21 : 
						int LA13_126 = input.LA(1);
						 
						int index13_126 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_126=='n') ) {s = 187;}
						else if ( ((LA13_126 >= '0' && LA13_126 <= '9')||(LA13_126 >= 'A' && LA13_126 <= 'Z')||(LA13_126 >= 'a' && LA13_126 <= 'm')||(LA13_126 >= 'o' && LA13_126 <= 'z')) ) {s = 36;}
						else if ( (LA13_126=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 188;
						 
						input.seek(index13_126);
						if ( s>=0 ) return s;
						break;

					case 22 : 
						int LA13_187 = input.LA(1);
						 
						int index13_187 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_187=='n') ) {s = 243;}
						else if ( ((LA13_187 >= '0' && LA13_187 <= '9')||(LA13_187 >= 'A' && LA13_187 <= 'Z')||(LA13_187 >= 'a' && LA13_187 <= 'm')||(LA13_187 >= 'o' && LA13_187 <= 'z')) ) {s = 36;}
						else if ( (LA13_187=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 244;
						 
						input.seek(index13_187);
						if ( s>=0 ) return s;
						break;

					case 23 : 
						int LA13_243 = input.LA(1);
						 
						int index13_243 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_243=='i') ) {s = 289;}
						else if ( ((LA13_243 >= '0' && LA13_243 <= '9')||(LA13_243 >= 'A' && LA13_243 <= 'Z')||(LA13_243 >= 'a' && LA13_243 <= 'h')||(LA13_243 >= 'j' && LA13_243 <= 'z')) ) {s = 36;}
						else if ( (LA13_243=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 290;
						 
						input.seek(index13_243);
						if ( s>=0 ) return s;
						break;

					case 24 : 
						int LA13_289 = input.LA(1);
						 
						int index13_289 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_289=='n') ) {s = 327;}
						else if ( ((LA13_289 >= '0' && LA13_289 <= '9')||(LA13_289 >= 'A' && LA13_289 <= 'Z')||(LA13_289 >= 'a' && LA13_289 <= 'm')||(LA13_289 >= 'o' && LA13_289 <= 'z')) ) {s = 36;}
						else if ( (LA13_289=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 328;
						 
						input.seek(index13_289);
						if ( s>=0 ) return s;
						break;

					case 25 : 
						int LA13_66 = input.LA(1);
						 
						int index13_66 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_66=='e') ) {s = 132;}
						else if ( ((LA13_66 >= '0' && LA13_66 <= '9')||(LA13_66 >= 'A' && LA13_66 <= 'Z')||(LA13_66 >= 'a' && LA13_66 <= 'd')||(LA13_66 >= 'f' && LA13_66 <= 'z')) ) {s = 36;}
						else if ( (LA13_66=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 133;
						 
						input.seek(index13_66);
						if ( s>=0 ) return s;
						break;

					case 26 : 
						int LA13_327 = input.LA(1);
						 
						int index13_327 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_327=='g') ) {s = 358;}
						else if ( ((LA13_327 >= '0' && LA13_327 <= '9')||(LA13_327 >= 'A' && LA13_327 <= 'Z')||(LA13_327 >= 'a' && LA13_327 <= 'f')||(LA13_327 >= 'h' && LA13_327 <= 'z')) ) {s = 36;}
						else if ( (LA13_327=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 359;
						 
						input.seek(index13_327);
						if ( s>=0 ) return s;
						break;

					case 27 : 
						int LA13_367 = input.LA(1);
						 
						int index13_367 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_367 >= '0' && LA13_367 <= '9')||(LA13_367 >= 'A' && LA13_367 <= 'Z')||(LA13_367 >= 'a' && LA13_367 <= 'z')) ) {s = 36;}
						else if ( (LA13_367=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 390;
						 
						input.seek(index13_367);
						if ( s>=0 ) return s;
						break;

					case 28 : 
						int LA13_132 = input.LA(1);
						 
						int index13_132 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_132=='n') ) {s = 193;}
						else if ( ((LA13_132 >= '0' && LA13_132 <= '9')||(LA13_132 >= 'A' && LA13_132 <= 'Z')||(LA13_132 >= 'a' && LA13_132 <= 'm')||(LA13_132 >= 'o' && LA13_132 <= 'z')) ) {s = 36;}
						else if ( (LA13_132=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 194;
						 
						input.seek(index13_132);
						if ( s>=0 ) return s;
						break;

					case 29 : 
						int LA13_64 = input.LA(1);
						 
						int index13_64 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_64=='a') ) {s = 128;}
						else if ( ((LA13_64 >= '0' && LA13_64 <= '9')||(LA13_64 >= 'A' && LA13_64 <= 'Z')||(LA13_64 >= 'b' && LA13_64 <= 'z')) ) {s = 36;}
						else if ( (LA13_64=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 129;
						 
						input.seek(index13_64);
						if ( s>=0 ) return s;
						break;

					case 30 : 
						int LA13_193 = input.LA(1);
						 
						int index13_193 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_193=='d') ) {s = 249;}
						else if ( ((LA13_193 >= '0' && LA13_193 <= '9')||(LA13_193 >= 'A' && LA13_193 <= 'Z')||(LA13_193 >= 'a' && LA13_193 <= 'c')||(LA13_193 >= 'e' && LA13_193 <= 'z')) ) {s = 36;}
						else if ( (LA13_193=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 250;
						 
						input.seek(index13_193);
						if ( s>=0 ) return s;
						break;

					case 31 : 
						int LA13_128 = input.LA(1);
						 
						int index13_128 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_128=='r') ) {s = 189;}
						else if ( ((LA13_128 >= '0' && LA13_128 <= '9')||(LA13_128 >= 'A' && LA13_128 <= 'Z')||(LA13_128 >= 'a' && LA13_128 <= 'q')||(LA13_128 >= 's' && LA13_128 <= 'z')) ) {s = 36;}
						else if ( (LA13_128=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 190;
						 
						input.seek(index13_128);
						if ( s>=0 ) return s;
						break;

					case 32 : 
						int LA13_189 = input.LA(1);
						 
						int index13_189 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_189=='t') ) {s = 245;}
						else if ( ((LA13_189 >= '0' && LA13_189 <= '9')||(LA13_189 >= 'A' && LA13_189 <= 'Z')||(LA13_189 >= 'a' && LA13_189 <= 's')||(LA13_189 >= 'u' && LA13_189 <= 'z')) ) {s = 36;}
						else if ( (LA13_189=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 246;
						 
						input.seek(index13_189);
						if ( s>=0 ) return s;
						break;

					case 33 : 
						int LA13_245 = input.LA(1);
						 
						int index13_245 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_245=='i') ) {s = 291;}
						else if ( ((LA13_245 >= '0' && LA13_245 <= '9')||(LA13_245 >= 'A' && LA13_245 <= 'Z')||(LA13_245 >= 'a' && LA13_245 <= 'h')||(LA13_245 >= 'j' && LA13_245 <= 'z')) ) {s = 36;}
						else if ( (LA13_245=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 292;
						 
						input.seek(index13_245);
						if ( s>=0 ) return s;
						break;

					case 34 : 
						int LA13_249 = input.LA(1);
						 
						int index13_249 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_249=='s') ) {s = 295;}
						else if ( ((LA13_249 >= '0' && LA13_249 <= '9')||(LA13_249 >= 'A' && LA13_249 <= 'Z')||(LA13_249 >= 'a' && LA13_249 <= 'r')||(LA13_249 >= 't' && LA13_249 <= 'z')) ) {s = 36;}
						else if ( (LA13_249=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 296;
						 
						input.seek(index13_249);
						if ( s>=0 ) return s;
						break;

					case 35 : 
						int LA13_291 = input.LA(1);
						 
						int index13_291 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_291=='n') ) {s = 329;}
						else if ( ((LA13_291 >= '0' && LA13_291 <= '9')||(LA13_291 >= 'A' && LA13_291 <= 'Z')||(LA13_291 >= 'a' && LA13_291 <= 'm')||(LA13_291 >= 'o' && LA13_291 <= 'z')) ) {s = 36;}
						else if ( (LA13_291=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 330;
						 
						input.seek(index13_291);
						if ( s>=0 ) return s;
						break;

					case 36 : 
						int LA13_329 = input.LA(1);
						 
						int index13_329 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_329=='g') ) {s = 360;}
						else if ( ((LA13_329 >= '0' && LA13_329 <= '9')||(LA13_329 >= 'A' && LA13_329 <= 'Z')||(LA13_329 >= 'a' && LA13_329 <= 'f')||(LA13_329 >= 'h' && LA13_329 <= 'z')) ) {s = 36;}
						else if ( (LA13_329=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 361;
						 
						input.seek(index13_329);
						if ( s>=0 ) return s;
						break;

					case 37 : 
						int LA13_295 = input.LA(1);
						 
						int index13_295 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_295==' ') ) {s = 333;}
						else if ( ((LA13_295 >= '0' && LA13_295 <= '9')||(LA13_295 >= 'A' && LA13_295 <= 'Z')||(LA13_295 >= 'a' && LA13_295 <= 'z')) ) {s = 36;}
						else if ( (LA13_295=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 334;
						 
						input.seek(index13_295);
						if ( s>=0 ) return s;
						break;

					case 38 : 
						int LA13_360 = input.LA(1);
						 
						int index13_360 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_360==' ') ) {s = 386;}
						else if ( ((LA13_360 >= '0' && LA13_360 <= '9')||(LA13_360 >= 'A' && LA13_360 <= 'Z')||(LA13_360 >= 'a' && LA13_360 <= 'z')) ) {s = 36;}
						else if ( (LA13_360=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 387;
						 
						input.seek(index13_360);
						if ( s>=0 ) return s;
						break;

					case 39 : 
						int LA13_65 = input.LA(1);
						 
						int index13_65 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_65=='l') ) {s = 130;}
						else if ( ((LA13_65 >= '0' && LA13_65 <= '9')||(LA13_65 >= 'A' && LA13_65 <= 'Z')||(LA13_65 >= 'a' && LA13_65 <= 'k')||(LA13_65 >= 'm' && LA13_65 <= 'z')) ) {s = 36;}
						else if ( (LA13_65=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 131;
						 
						input.seek(index13_65);
						if ( s>=0 ) return s;
						break;

					case 40 : 
						int LA13_130 = input.LA(1);
						 
						int index13_130 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_130=='e') ) {s = 191;}
						else if ( ((LA13_130 >= '0' && LA13_130 <= '9')||(LA13_130 >= 'A' && LA13_130 <= 'Z')||(LA13_130 >= 'a' && LA13_130 <= 'd')||(LA13_130 >= 'f' && LA13_130 <= 'z')) ) {s = 36;}
						else if ( (LA13_130=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 192;
						 
						input.seek(index13_130);
						if ( s>=0 ) return s;
						break;

					case 41 : 
						int LA13_191 = input.LA(1);
						 
						int index13_191 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_191=='r') ) {s = 247;}
						else if ( ((LA13_191 >= '0' && LA13_191 <= '9')||(LA13_191 >= 'A' && LA13_191 <= 'Z')||(LA13_191 >= 'a' && LA13_191 <= 'q')||(LA13_191 >= 's' && LA13_191 <= 'z')) ) {s = 36;}
						else if ( (LA13_191=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 248;
						 
						input.seek(index13_191);
						if ( s>=0 ) return s;
						break;

					case 42 : 
						int LA13_247 = input.LA(1);
						 
						int index13_247 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_247=='a') ) {s = 293;}
						else if ( ((LA13_247 >= '0' && LA13_247 <= '9')||(LA13_247 >= 'A' && LA13_247 <= 'Z')||(LA13_247 >= 'b' && LA13_247 <= 'z')) ) {s = 36;}
						else if ( (LA13_247=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 294;
						 
						input.seek(index13_247);
						if ( s>=0 ) return s;
						break;

					case 43 : 
						int LA13_293 = input.LA(1);
						 
						int index13_293 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_293=='n') ) {s = 331;}
						else if ( ((LA13_293 >= '0' && LA13_293 <= '9')||(LA13_293 >= 'A' && LA13_293 <= 'Z')||(LA13_293 >= 'a' && LA13_293 <= 'm')||(LA13_293 >= 'o' && LA13_293 <= 'z')) ) {s = 36;}
						else if ( (LA13_293=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 332;
						 
						input.seek(index13_293);
						if ( s>=0 ) return s;
						break;

					case 44 : 
						int LA13_331 = input.LA(1);
						 
						int index13_331 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_331=='c') ) {s = 362;}
						else if ( ((LA13_331 >= '0' && LA13_331 <= '9')||(LA13_331 >= 'A' && LA13_331 <= 'Z')||(LA13_331 >= 'a' && LA13_331 <= 'b')||(LA13_331 >= 'd' && LA13_331 <= 'z')) ) {s = 36;}
						else if ( (LA13_331=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 363;
						 
						input.seek(index13_331);
						if ( s>=0 ) return s;
						break;

					case 45 : 
						int LA13_362 = input.LA(1);
						 
						int index13_362 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_362=='e') ) {s = 388;}
						else if ( ((LA13_362 >= '0' && LA13_362 <= '9')||(LA13_362 >= 'A' && LA13_362 <= 'Z')||(LA13_362 >= 'a' && LA13_362 <= 'd')||(LA13_362 >= 'f' && LA13_362 <= 'z')) ) {s = 36;}
						else if ( (LA13_362=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 389;
						 
						input.seek(index13_362);
						if ( s>=0 ) return s;
						break;

					case 46 : 
						int LA13_316 = input.LA(1);
						 
						int index13_316 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_316 >= '0' && LA13_316 <= '9')||(LA13_316 >= 'A' && LA13_316 <= 'Z')||(LA13_316 >= 'a' && LA13_316 <= 'z')) ) {s = 36;}
						else if ( (LA13_316=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 348;
						 
						input.seek(index13_316);
						if ( s>=0 ) return s;
						break;

					case 47 : 
						int LA13_20 = input.LA(1);
						 
						int index13_20 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_20=='s') ) {s = 59;}
						else if ( ((LA13_20 >= '0' && LA13_20 <= '9')||(LA13_20 >= 'A' && LA13_20 <= 'Z')||(LA13_20 >= 'a' && LA13_20 <= 'r')||(LA13_20 >= 't' && LA13_20 <= 'z')) ) {s = 36;}
						else if ( (LA13_20=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 37;
						 
						input.seek(index13_20);
						if ( s>=0 ) return s;
						break;

					case 48 : 
						int LA13_23 = input.LA(1);
						 
						int index13_23 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_23=='o') ) {s = 65;}
						else if ( (LA13_23=='r') ) {s = 66;}
						else if ( ((LA13_23 >= '0' && LA13_23 <= '9')||(LA13_23 >= 'A' && LA13_23 <= 'Z')||(LA13_23 >= 'a' && LA13_23 <= 'n')||(LA13_23 >= 'p' && LA13_23 <= 'q')||(LA13_23 >= 's' && LA13_23 <= 'z')) ) {s = 36;}
						else if ( (LA13_23=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 37;
						 
						input.seek(index13_23);
						if ( s>=0 ) return s;
						break;

					case 49 : 
						int LA13_36 = input.LA(1);
						 
						int index13_36 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_36 >= '0' && LA13_36 <= '9')||(LA13_36 >= 'A' && LA13_36 <= 'Z')||(LA13_36 >= 'a' && LA13_36 <= 'z')) ) {s = 36;}
						else if ( (LA13_36=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 75;
						 
						input.seek(index13_36);
						if ( s>=0 ) return s;
						break;

					case 50 : 
						int LA13_147 = input.LA(1);
						 
						int index13_147 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( ((runtimeHistoryOpAhead())) ) {s = 206;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_147);
						if ( s>=0 ) return s;
						break;

					case 51 : 
						int LA13_215 = input.LA(1);
						 
						int index13_215 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( ((runtimeHistoryOpAhead())) ) {s = 206;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_215);
						if ( s>=0 ) return s;
						break;

					case 52 : 
						int LA13_251 = input.LA(1);
						 
						int index13_251 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( ((runtimeHistoryOpAhead())) ) {s = 206;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_251);
						if ( s>=0 ) return s;
						break;

					case 53 : 
						int LA13_270 = input.LA(1);
						 
						int index13_270 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( ((runtimeHistoryOpAhead())) ) {s = 206;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_270);
						if ( s>=0 ) return s;
						break;

					case 54 : 
						int LA13_335 = input.LA(1);
						 
						int index13_335 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( ((runtimeHistoryOpAhead())) ) {s = 206;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_335);
						if ( s>=0 ) return s;
						break;

					case 55 : 
						int LA13_157 = input.LA(1);
						 
						int index13_157 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_157 >= '0' && LA13_157 <= '9')||(LA13_157 >= 'A' && LA13_157 <= 'Z')||(LA13_157 >= 'a' && LA13_157 <= 'z')) ) {s = 36;}
						else if ( (LA13_157=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 215;
						 
						input.seek(index13_157);
						if ( s>=0 ) return s;
						break;

					case 56 : 
						int LA13_222 = input.LA(1);
						 
						int index13_222 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_222 >= '0' && LA13_222 <= '9')||(LA13_222 >= 'A' && LA13_222 <= 'Z')||(LA13_222 >= 'a' && LA13_222 <= 'z')) ) {s = 36;}
						else if ( (LA13_222=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 270;
						 
						input.seek(index13_222);
						if ( s>=0 ) return s;
						break;

					case 57 : 
						int LA13_195 = input.LA(1);
						 
						int index13_195 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_195 >= '0' && LA13_195 <= '9')||(LA13_195 >= 'A' && LA13_195 <= 'Z')||(LA13_195 >= 'a' && LA13_195 <= 'z')) ) {s = 36;}
						else if ( (LA13_195=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 251;
						 
						input.seek(index13_195);
						if ( s>=0 ) return s;
						break;

					case 58 : 
						int LA13_82 = input.LA(1);
						 
						int index13_82 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_82 >= '0' && LA13_82 <= '9')||(LA13_82 >= 'A' && LA13_82 <= 'Z')||(LA13_82 >= 'a' && LA13_82 <= 'z')) ) {s = 36;}
						else if ( (LA13_82=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 147;
						 
						input.seek(index13_82);
						if ( s>=0 ) return s;
						break;

					case 59 : 
						int LA13_17 = input.LA(1);
						 
						int index13_17 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_17=='n') ) {s = 53;}
						else if ( (LA13_17=='p') ) {s = 54;}
						else if ( (LA13_17=='q') ) {s = 55;}
						else if ( ((LA13_17 >= '0' && LA13_17 <= '9')||(LA13_17 >= 'A' && LA13_17 <= 'Z')||(LA13_17 >= 'a' && LA13_17 <= 'm')||LA13_17=='o'||(LA13_17 >= 'r' && LA13_17 <= 'z')) ) {s = 36;}
						else if ( (LA13_17=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 37;
						 
						input.seek(index13_17);
						if ( s>=0 ) return s;
						break;

					case 60 : 
						int LA13_297 = input.LA(1);
						 
						int index13_297 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_297 >= '0' && LA13_297 <= '9')||(LA13_297 >= 'A' && LA13_297 <= 'Z')||(LA13_297 >= 'a' && LA13_297 <= 'z')) ) {s = 36;}
						else if ( (LA13_297=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 335;
						 
						input.seek(index13_297);
						if ( s>=0 ) return s;
						break;

					case 61 : 
						int LA13_11 = input.LA(1);
						 
						int index13_11 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_11=='r') ) {s = 45;}
						else if ( (LA13_11=='v') ) {s = 46;}
						else if ( (LA13_11=='p') ) {s = 47;}
						else if ( ((LA13_11 >= '0' && LA13_11 <= '9')||(LA13_11 >= 'A' && LA13_11 <= 'Z')||(LA13_11 >= 'a' && LA13_11 <= 'o')||LA13_11=='q'||(LA13_11 >= 's' && LA13_11 <= 'u')||(LA13_11 >= 'w' && LA13_11 <= 'z')) ) {s = 36;}
						else if ( (LA13_11=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 37;
						 
						input.seek(index13_11);
						if ( s>=0 ) return s;
						break;

					case 62 : 
						int LA13_47 = input.LA(1);
						 
						int index13_47 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_47=='e') ) {s = 94;}
						else if ( ((LA13_47 >= '0' && LA13_47 <= '9')||(LA13_47 >= 'A' && LA13_47 <= 'Z')||(LA13_47 >= 'a' && LA13_47 <= 'd')||(LA13_47 >= 'f' && LA13_47 <= 'z')) ) {s = 36;}
						else if ( (LA13_47=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 95;
						 
						input.seek(index13_47);
						if ( s>=0 ) return s;
						break;

					case 63 : 
						int LA13_94 = input.LA(1);
						 
						int index13_94 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_94=='n') ) {s = 157;}
						else if ( ((LA13_94 >= '0' && LA13_94 <= '9')||(LA13_94 >= 'A' && LA13_94 <= 'Z')||(LA13_94 >= 'a' && LA13_94 <= 'm')||(LA13_94 >= 'o' && LA13_94 <= 'z')) ) {s = 36;}
						else if ( (LA13_94=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 158;
						 
						input.seek(index13_94);
						if ( s>=0 ) return s;
						break;

					case 64 : 
						int LA13_52 = input.LA(1);
						 
						int index13_52 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_52=='o') ) {s = 104;}
						else if ( ((LA13_52 >= '0' && LA13_52 <= '9')||(LA13_52 >= 'A' && LA13_52 <= 'Z')||(LA13_52 >= 'a' && LA13_52 <= 'n')||(LA13_52 >= 'p' && LA13_52 <= 'z')) ) {s = 36;}
						else if ( (LA13_52=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 105;
						 
						input.seek(index13_52);
						if ( s>=0 ) return s;
						break;

					case 65 : 
						int LA13_104 = input.LA(1);
						 
						int index13_104 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_104=='s') ) {s = 166;}
						else if ( ((LA13_104 >= '0' && LA13_104 <= '9')||(LA13_104 >= 'A' && LA13_104 <= 'Z')||(LA13_104 >= 'a' && LA13_104 <= 'r')||(LA13_104 >= 't' && LA13_104 <= 'z')) ) {s = 36;}
						else if ( (LA13_104=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 167;
						 
						input.seek(index13_104);
						if ( s>=0 ) return s;
						break;

					case 66 : 
						int LA13_166 = input.LA(1);
						 
						int index13_166 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_166=='e') ) {s = 222;}
						else if ( ((LA13_166 >= '0' && LA13_166 <= '9')||(LA13_166 >= 'A' && LA13_166 <= 'Z')||(LA13_166 >= 'a' && LA13_166 <= 'd')||(LA13_166 >= 'f' && LA13_166 <= 'z')) ) {s = 36;}
						else if ( (LA13_166=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 223;
						 
						input.seek(index13_166);
						if ( s>=0 ) return s;
						break;

					case 67 : 
						int LA13_112 = input.LA(1);
						 
						int index13_112 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_112 >= '0' && LA13_112 <= '9')||(LA13_112 >= 'A' && LA13_112 <= 'Z')||(LA13_112 >= 'a' && LA13_112 <= 'z')) ) {s = 36;}
						else if ( (LA13_112=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 174;
						 
						input.seek(index13_112);
						if ( s>=0 ) return s;
						break;

					case 68 : 
						int LA13_67 = input.LA(1);
						 
						int index13_67 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_67=='g') ) {s = 134;}
						else if ( ((LA13_67 >= '0' && LA13_67 <= '9')||(LA13_67 >= 'A' && LA13_67 <= 'Z')||(LA13_67 >= 'a' && LA13_67 <= 'f')||(LA13_67 >= 'h' && LA13_67 <= 'z')) ) {s = 36;}
						else if ( (LA13_67=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 135;
						 
						input.seek(index13_67);
						if ( s>=0 ) return s;
						break;

					case 69 : 
						int LA13_134 = input.LA(1);
						 
						int index13_134 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_134=='h') ) {s = 195;}
						else if ( ((LA13_134 >= '0' && LA13_134 <= '9')||(LA13_134 >= 'A' && LA13_134 <= 'Z')||(LA13_134 >= 'a' && LA13_134 <= 'g')||(LA13_134 >= 'i' && LA13_134 <= 'z')) ) {s = 36;}
						else if ( (LA13_134=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 196;
						 
						input.seek(index13_134);
						if ( s>=0 ) return s;
						break;

					case 70 : 
						int LA13_41 = input.LA(1);
						 
						int index13_41 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_41=='w') ) {s = 82;}
						else if ( ((LA13_41 >= '0' && LA13_41 <= '9')||(LA13_41 >= 'A' && LA13_41 <= 'Z')||(LA13_41 >= 'a' && LA13_41 <= 'v')||(LA13_41 >= 'x' && LA13_41 <= 'z')) ) {s = 36;}
						else if ( (LA13_41=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 83;
						 
						input.seek(index13_41);
						if ( s>=0 ) return s;
						break;

					case 71 : 
						int LA13_68 = input.LA(1);
						 
						int index13_68 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_68=='l') ) {s = 136;}
						else if ( ((LA13_68 >= '0' && LA13_68 <= '9')||(LA13_68 >= 'A' && LA13_68 <= 'Z')||(LA13_68 >= 'a' && LA13_68 <= 'k')||(LA13_68 >= 'm' && LA13_68 <= 'z')) ) {s = 36;}
						else if ( (LA13_68=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 137;
						 
						input.seek(index13_68);
						if ( s>=0 ) return s;
						break;

					case 72 : 
						int LA13_136 = input.LA(1);
						 
						int index13_136 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_136=='u') ) {s = 197;}
						else if ( ((LA13_136 >= '0' && LA13_136 <= '9')||(LA13_136 >= 'A' && LA13_136 <= 'Z')||(LA13_136 >= 'a' && LA13_136 <= 't')||(LA13_136 >= 'v' && LA13_136 <= 'z')) ) {s = 36;}
						else if ( (LA13_136=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 198;
						 
						input.seek(index13_136);
						if ( s>=0 ) return s;
						break;

					case 73 : 
						int LA13_197 = input.LA(1);
						 
						int index13_197 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_197=='m') ) {s = 252;}
						else if ( ((LA13_197 >= '0' && LA13_197 <= '9')||(LA13_197 >= 'A' && LA13_197 <= 'Z')||(LA13_197 >= 'a' && LA13_197 <= 'l')||(LA13_197 >= 'n' && LA13_197 <= 'z')) ) {s = 36;}
						else if ( (LA13_197=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 253;
						 
						input.seek(index13_197);
						if ( s>=0 ) return s;
						break;

					case 74 : 
						int LA13_252 = input.LA(1);
						 
						int index13_252 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_252=='e') ) {s = 297;}
						else if ( ((LA13_252 >= '0' && LA13_252 <= '9')||(LA13_252 >= 'A' && LA13_252 <= 'Z')||(LA13_252 >= 'a' && LA13_252 <= 'd')||(LA13_252 >= 'f' && LA13_252 <= 'z')) ) {s = 36;}
						else if ( (LA13_252=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 298;
						 
						input.seek(index13_252);
						if ( s>=0 ) return s;
						break;

					case 75 : 
						int LA13_29 = input.LA(1);
						 
						int index13_29 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_29 >= '0' && LA13_29 <= '9')||(LA13_29 >= 'A' && LA13_29 <= 'Z')||(LA13_29 >= 'a' && LA13_29 <= 'z')) ) {s = 36;}
						else if ( (LA13_29=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 37;
						 
						input.seek(index13_29);
						if ( s>=0 ) return s;
						break;

					case 76 : 
						int LA13_51 = input.LA(1);
						 
						int index13_51 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_51=='o') ) {s = 102;}
						else if ( ((LA13_51 >= '0' && LA13_51 <= '9')||(LA13_51 >= 'A' && LA13_51 <= 'Z')||(LA13_51 >= 'a' && LA13_51 <= 'n')||(LA13_51 >= 'p' && LA13_51 <= 'z')) ) {s = 36;}
						else if ( (LA13_51=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 103;
						 
						input.seek(index13_51);
						if ( s>=0 ) return s;
						break;

					case 77 : 
						int LA13_232 = input.LA(1);
						 
						int index13_232 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_232 >= '0' && LA13_232 <= '9')||(LA13_232 >= 'A' && LA13_232 <= 'Z')||(LA13_232 >= 'a' && LA13_232 <= 'z')) ) {s = 36;}
						else if ( (LA13_232=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 279;
						 
						input.seek(index13_232);
						if ( s>=0 ) return s;
						break;

					case 78 : 
						int LA13_102 = input.LA(1);
						 
						int index13_102 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_102=='s') ) {s = 164;}
						else if ( ((LA13_102 >= '0' && LA13_102 <= '9')||(LA13_102 >= 'A' && LA13_102 <= 'Z')||(LA13_102 >= 'a' && LA13_102 <= 'r')||(LA13_102 >= 't' && LA13_102 <= 'z')) ) {s = 36;}
						else if ( (LA13_102=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 165;
						 
						input.seek(index13_102);
						if ( s>=0 ) return s;
						break;

					case 79 : 
						int LA13_34 = input.LA(1);
						 
						int index13_34 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_34=='d') ) {s = 71;}
						else if ( ((LA13_34 >= '0' && LA13_34 <= '9')||(LA13_34 >= 'A' && LA13_34 <= 'Z')||(LA13_34 >= 'a' && LA13_34 <= 'c')||(LA13_34 >= 'e' && LA13_34 <= 'z')) ) {s = 36;}
						else if ( (LA13_34=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 72;
						 
						input.seek(index13_34);
						if ( s>=0 ) return s;
						break;

					case 80 : 
						int LA13_164 = input.LA(1);
						 
						int index13_164 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_164=='s') ) {s = 220;}
						else if ( ((LA13_164 >= '0' && LA13_164 <= '9')||(LA13_164 >= 'A' && LA13_164 <= 'Z')||(LA13_164 >= 'a' && LA13_164 <= 'r')||(LA13_164 >= 't' && LA13_164 <= 'z')) ) {s = 36;}
						else if ( (LA13_164=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 221;
						 
						input.seek(index13_164);
						if ( s>=0 ) return s;
						break;

					case 81 : 
						int LA13_220 = input.LA(1);
						 
						int index13_220 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_220=='e') ) {s = 268;}
						else if ( ((LA13_220 >= '0' && LA13_220 <= '9')||(LA13_220 >= 'A' && LA13_220 <= 'Z')||(LA13_220 >= 'a' && LA13_220 <= 'd')||(LA13_220 >= 'f' && LA13_220 <= 'z')) ) {s = 36;}
						else if ( (LA13_220=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 269;
						 
						input.seek(index13_220);
						if ( s>=0 ) return s;
						break;

					case 82 : 
						int LA13_268 = input.LA(1);
						 
						int index13_268 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_268=='s') ) {s = 312;}
						else if ( ((LA13_268 >= '0' && LA13_268 <= '9')||(LA13_268 >= 'A' && LA13_268 <= 'Z')||(LA13_268 >= 'a' && LA13_268 <= 'r')||(LA13_268 >= 't' && LA13_268 <= 'z')) ) {s = 36;}
						else if ( (LA13_268=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 313;
						 
						input.seek(index13_268);
						if ( s>=0 ) return s;
						break;

					case 83 : 
						int LA13_312 = input.LA(1);
						 
						int index13_312 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_312==' ') ) {s = 346;}
						else if ( ((LA13_312 >= '0' && LA13_312 <= '9')||(LA13_312 >= 'A' && LA13_312 <= 'Z')||(LA13_312 >= 'a' && LA13_312 <= 'z')) ) {s = 36;}
						else if ( (LA13_312=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 347;
						 
						input.seek(index13_312);
						if ( s>=0 ) return s;
						break;

					case 84 : 
						int LA13_38 = input.LA(1);
						 
						int index13_38 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_38=='y') ) {s = 76;}
						else if ( ((LA13_38 >= '0' && LA13_38 <= '9')||(LA13_38 >= 'A' && LA13_38 <= 'Z')||(LA13_38 >= 'a' && LA13_38 <= 'x')||LA13_38=='z') ) {s = 36;}
						else if ( (LA13_38=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 77;
						 
						input.seek(index13_38);
						if ( s>=0 ) return s;
						break;

					case 85 : 
						int LA13_76 = input.LA(1);
						 
						int index13_76 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_76=='s') ) {s = 141;}
						else if ( ((LA13_76 >= '0' && LA13_76 <= '9')||(LA13_76 >= 'A' && LA13_76 <= 'Z')||(LA13_76 >= 'a' && LA13_76 <= 'r')||(LA13_76 >= 't' && LA13_76 <= 'z')) ) {s = 36;}
						else if ( (LA13_76=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 142;
						 
						input.seek(index13_76);
						if ( s>=0 ) return s;
						break;

					case 86 : 
						int LA13_40 = input.LA(1);
						 
						int index13_40 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_40=='n') ) {s = 80;}
						else if ( ((LA13_40 >= '0' && LA13_40 <= '9')||(LA13_40 >= 'A' && LA13_40 <= 'Z')||(LA13_40 >= 'a' && LA13_40 <= 'm')||(LA13_40 >= 'o' && LA13_40 <= 'z')) ) {s = 36;}
						else if ( (LA13_40=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 81;
						 
						input.seek(index13_40);
						if ( s>=0 ) return s;
						break;

					case 87 : 
						int LA13_80 = input.LA(1);
						 
						int index13_80 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_80=='i') ) {s = 145;}
						else if ( ((LA13_80 >= '0' && LA13_80 <= '9')||(LA13_80 >= 'A' && LA13_80 <= 'Z')||(LA13_80 >= 'a' && LA13_80 <= 'h')||(LA13_80 >= 'j' && LA13_80 <= 'z')) ) {s = 36;}
						else if ( (LA13_80=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 146;
						 
						input.seek(index13_80);
						if ( s>=0 ) return s;
						break;

					case 88 : 
						int LA13_145 = input.LA(1);
						 
						int index13_145 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_145=='e') ) {s = 204;}
						else if ( ((LA13_145 >= '0' && LA13_145 <= '9')||(LA13_145 >= 'A' && LA13_145 <= 'Z')||(LA13_145 >= 'a' && LA13_145 <= 'd')||(LA13_145 >= 'f' && LA13_145 <= 'z')) ) {s = 36;}
						else if ( (LA13_145=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 205;
						 
						input.seek(index13_145);
						if ( s>=0 ) return s;
						break;

					case 89 : 
						int LA13_204 = input.LA(1);
						 
						int index13_204 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_204=='n') ) {s = 256;}
						else if ( ((LA13_204 >= '0' && LA13_204 <= '9')||(LA13_204 >= 'A' && LA13_204 <= 'Z')||(LA13_204 >= 'a' && LA13_204 <= 'm')||(LA13_204 >= 'o' && LA13_204 <= 'z')) ) {s = 36;}
						else if ( (LA13_204=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 257;
						 
						input.seek(index13_204);
						if ( s>=0 ) return s;
						break;

					case 90 : 
						int LA13_256 = input.LA(1);
						 
						int index13_256 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_256=='t') ) {s = 301;}
						else if ( ((LA13_256 >= '0' && LA13_256 <= '9')||(LA13_256 >= 'A' && LA13_256 <= 'Z')||(LA13_256 >= 'a' && LA13_256 <= 's')||(LA13_256 >= 'u' && LA13_256 <= 'z')) ) {s = 36;}
						else if ( (LA13_256=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 302;
						 
						input.seek(index13_256);
						if ( s>=0 ) return s;
						break;

					case 91 : 
						int LA13_84 = input.LA(1);
						 
						int index13_84 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_84=='c') ) {s = 148;}
						else if ( ((LA13_84 >= '0' && LA13_84 <= '9')||(LA13_84 >= 'A' && LA13_84 <= 'Z')||(LA13_84 >= 'a' && LA13_84 <= 'b')||(LA13_84 >= 'd' && LA13_84 <= 'z')) ) {s = 36;}
						else if ( (LA13_84=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 149;
						 
						input.seek(index13_84);
						if ( s>=0 ) return s;
						break;

					case 92 : 
						int LA13_148 = input.LA(1);
						 
						int index13_148 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_148=='h') ) {s = 207;}
						else if ( ((LA13_148 >= '0' && LA13_148 <= '9')||(LA13_148 >= 'A' && LA13_148 <= 'Z')||(LA13_148 >= 'a' && LA13_148 <= 'g')||(LA13_148 >= 'i' && LA13_148 <= 'z')) ) {s = 36;}
						else if ( (LA13_148=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 208;
						 
						input.seek(index13_148);
						if ( s>=0 ) return s;
						break;

					case 93 : 
						int LA13_207 = input.LA(1);
						 
						int index13_207 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_207=='i') ) {s = 258;}
						else if ( ((LA13_207 >= '0' && LA13_207 <= '9')||(LA13_207 >= 'A' && LA13_207 <= 'Z')||(LA13_207 >= 'a' && LA13_207 <= 'h')||(LA13_207 >= 'j' && LA13_207 <= 'z')) ) {s = 36;}
						else if ( (LA13_207=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 259;
						 
						input.seek(index13_207);
						if ( s>=0 ) return s;
						break;

					case 94 : 
						int LA13_258 = input.LA(1);
						 
						int index13_258 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_258=='n') ) {s = 303;}
						else if ( ((LA13_258 >= '0' && LA13_258 <= '9')||(LA13_258 >= 'A' && LA13_258 <= 'Z')||(LA13_258 >= 'a' && LA13_258 <= 'm')||(LA13_258 >= 'o' && LA13_258 <= 'z')) ) {s = 36;}
						else if ( (LA13_258=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 304;
						 
						input.seek(index13_258);
						if ( s>=0 ) return s;
						break;

					case 95 : 
						int LA13_303 = input.LA(1);
						 
						int index13_303 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_303=='g') ) {s = 339;}
						else if ( ((LA13_303 >= '0' && LA13_303 <= '9')||(LA13_303 >= 'A' && LA13_303 <= 'Z')||(LA13_303 >= 'a' && LA13_303 <= 'f')||(LA13_303 >= 'h' && LA13_303 <= 'z')) ) {s = 36;}
						else if ( (LA13_303=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 340;
						 
						input.seek(index13_303);
						if ( s>=0 ) return s;
						break;

					case 96 : 
						int LA13_44 = input.LA(1);
						 
						int index13_44 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_44=='t') ) {s = 89;}
						else if ( ((LA13_44 >= '0' && LA13_44 <= '9')||(LA13_44 >= 'A' && LA13_44 <= 'Z')||(LA13_44 >= 'a' && LA13_44 <= 's')||(LA13_44 >= 'u' && LA13_44 <= 'z')) ) {s = 36;}
						else if ( (LA13_44=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 90;
						 
						input.seek(index13_44);
						if ( s>=0 ) return s;
						break;

					case 97 : 
						int LA13_24 = input.LA(1);
						 
						int index13_24 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_24=='i') ) {s = 67;}
						else if ( ((LA13_24 >= '0' && LA13_24 <= '9')||(LA13_24 >= 'A' && LA13_24 <= 'Z')||(LA13_24 >= 'a' && LA13_24 <= 'h')||(LA13_24 >= 'j' && LA13_24 <= 'z')) ) {s = 36;}
						else if ( (LA13_24=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 37;
						 
						input.seek(index13_24);
						if ( s>=0 ) return s;
						break;

					case 98 : 
						int LA13_55 = input.LA(1);
						 
						int index13_55 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_55=='u') ) {s = 110;}
						else if ( ((LA13_55 >= '0' && LA13_55 <= '9')||(LA13_55 >= 'A' && LA13_55 <= 'Z')||(LA13_55 >= 'a' && LA13_55 <= 't')||(LA13_55 >= 'v' && LA13_55 <= 'z')) ) {s = 36;}
						else if ( (LA13_55=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 111;
						 
						input.seek(index13_55);
						if ( s>=0 ) return s;
						break;

					case 99 : 
						int LA13_110 = input.LA(1);
						 
						int index13_110 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_110=='a') ) {s = 172;}
						else if ( ((LA13_110 >= '0' && LA13_110 <= '9')||(LA13_110 >= 'A' && LA13_110 <= 'Z')||(LA13_110 >= 'b' && LA13_110 <= 'z')) ) {s = 36;}
						else if ( (LA13_110=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 173;
						 
						input.seek(index13_110);
						if ( s>=0 ) return s;
						break;

					case 100 : 
						int LA13_28 = input.LA(1);
						 
						int index13_28 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_28=='o') ) {s = 68;}
						else if ( ((LA13_28 >= '0' && LA13_28 <= '9')||(LA13_28 >= 'A' && LA13_28 <= 'Z')||(LA13_28 >= 'a' && LA13_28 <= 'n')||(LA13_28 >= 'p' && LA13_28 <= 'z')) ) {s = 36;}
						else if ( (LA13_28=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 37;
						 
						input.seek(index13_28);
						if ( s>=0 ) return s;
						break;

					case 101 : 
						int LA13_172 = input.LA(1);
						 
						int index13_172 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_172=='l') ) {s = 228;}
						else if ( ((LA13_172 >= '0' && LA13_172 <= '9')||(LA13_172 >= 'A' && LA13_172 <= 'Z')||(LA13_172 >= 'a' && LA13_172 <= 'k')||(LA13_172 >= 'm' && LA13_172 <= 'z')) ) {s = 36;}
						else if ( (LA13_172=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 229;
						 
						input.seek(index13_172);
						if ( s>=0 ) return s;
						break;

					case 102 : 
						int LA13_228 = input.LA(1);
						 
						int index13_228 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_228=='s') ) {s = 275;}
						else if ( ((LA13_228 >= '0' && LA13_228 <= '9')||(LA13_228 >= 'A' && LA13_228 <= 'Z')||(LA13_228 >= 'a' && LA13_228 <= 'r')||(LA13_228 >= 't' && LA13_228 <= 'z')) ) {s = 36;}
						else if ( (LA13_228=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 276;
						 
						input.seek(index13_228);
						if ( s>=0 ) return s;
						break;

					case 103 : 
						int LA13_275 = input.LA(1);
						 
						int index13_275 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_275==' ') ) {s = 318;}
						else if ( ((LA13_275 >= '0' && LA13_275 <= '9')||(LA13_275 >= 'A' && LA13_275 <= 'Z')||(LA13_275 >= 'a' && LA13_275 <= 'z')) ) {s = 36;}
						else if ( (LA13_275=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 319;
						 
						input.seek(index13_275);
						if ( s>=0 ) return s;
						break;

					case 104 : 
						int LA13_48 = input.LA(1);
						 
						int index13_48 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_48=='N') ) {s = 96;}
						else if ( ((LA13_48 >= '0' && LA13_48 <= '9')||(LA13_48 >= 'A' && LA13_48 <= 'M')||(LA13_48 >= 'O' && LA13_48 <= 'Z')||(LA13_48 >= 'a' && LA13_48 <= 'z')) ) {s = 36;}
						else if ( (LA13_48=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 97;
						 
						input.seek(index13_48);
						if ( s>=0 ) return s;
						break;

					case 105 : 
						int LA13_35 = input.LA(1);
						 
						int index13_35 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_35=='s') ) {s = 73;}
						else if ( ((LA13_35 >= '0' && LA13_35 <= '9')||(LA13_35 >= 'A' && LA13_35 <= 'Z')||(LA13_35 >= 'a' && LA13_35 <= 'r')||(LA13_35 >= 't' && LA13_35 <= 'z')) ) {s = 36;}
						else if ( (LA13_35=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 74;
						 
						input.seek(index13_35);
						if ( s>=0 ) return s;
						break;

					case 106 : 
						int LA13_73 = input.LA(1);
						 
						int index13_73 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_73=='o') ) {s = 139;}
						else if ( ((LA13_73 >= '0' && LA13_73 <= '9')||(LA13_73 >= 'A' && LA13_73 <= 'Z')||(LA13_73 >= 'a' && LA13_73 <= 'n')||(LA13_73 >= 'p' && LA13_73 <= 'z')) ) {s = 36;}
						else if ( (LA13_73=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 140;
						 
						input.seek(index13_73);
						if ( s>=0 ) return s;
						break;

					case 107 : 
						int LA13_139 = input.LA(1);
						 
						int index13_139 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_139==' ') ) {s = 199;}
						else if ( ((LA13_139 >= '0' && LA13_139 <= '9')||(LA13_139 >= 'A' && LA13_139 <= 'Z')||(LA13_139 >= 'a' && LA13_139 <= 'z')) ) {s = 36;}
						else if ( (LA13_139=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 200;
						 
						input.seek(index13_139);
						if ( s>=0 ) return s;
						break;

					case 108 : 
						int LA13_49 = input.LA(1);
						 
						int index13_49 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_49=='a') ) {s = 98;}
						else if ( ((LA13_49 >= '0' && LA13_49 <= '9')||(LA13_49 >= 'A' && LA13_49 <= 'Z')||(LA13_49 >= 'b' && LA13_49 <= 'z')) ) {s = 36;}
						else if ( (LA13_49=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 99;
						 
						input.seek(index13_49);
						if ( s>=0 ) return s;
						break;

					case 109 : 
						int LA13_98 = input.LA(1);
						 
						int index13_98 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_98=='r') ) {s = 160;}
						else if ( ((LA13_98 >= '0' && LA13_98 <= '9')||(LA13_98 >= 'A' && LA13_98 <= 'Z')||(LA13_98 >= 'a' && LA13_98 <= 'q')||(LA13_98 >= 's' && LA13_98 <= 'z')) ) {s = 36;}
						else if ( (LA13_98=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 161;
						 
						input.seek(index13_98);
						if ( s>=0 ) return s;
						break;

					case 110 : 
						int LA13_160 = input.LA(1);
						 
						int index13_160 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_160=='i') ) {s = 216;}
						else if ( ((LA13_160 >= '0' && LA13_160 <= '9')||(LA13_160 >= 'A' && LA13_160 <= 'Z')||(LA13_160 >= 'a' && LA13_160 <= 'h')||(LA13_160 >= 'j' && LA13_160 <= 'z')) ) {s = 36;}
						else if ( (LA13_160=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 217;
						 
						input.seek(index13_160);
						if ( s>=0 ) return s;
						break;

					case 111 : 
						int LA13_216 = input.LA(1);
						 
						int index13_216 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_216=='s') ) {s = 264;}
						else if ( ((LA13_216 >= '0' && LA13_216 <= '9')||(LA13_216 >= 'A' && LA13_216 <= 'Z')||(LA13_216 >= 'a' && LA13_216 <= 'r')||(LA13_216 >= 't' && LA13_216 <= 'z')) ) {s = 36;}
						else if ( (LA13_216=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 265;
						 
						input.seek(index13_216);
						if ( s>=0 ) return s;
						break;

					case 112 : 
						int LA13_264 = input.LA(1);
						 
						int index13_264 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_264=='h') ) {s = 308;}
						else if ( ((LA13_264 >= '0' && LA13_264 <= '9')||(LA13_264 >= 'A' && LA13_264 <= 'Z')||(LA13_264 >= 'a' && LA13_264 <= 'g')||(LA13_264 >= 'i' && LA13_264 <= 'z')) ) {s = 36;}
						else if ( (LA13_264=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 309;
						 
						input.seek(index13_264);
						if ( s>=0 ) return s;
						break;

					case 113 : 
						int LA13_50 = input.LA(1);
						 
						int index13_50 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_50=='l') ) {s = 100;}
						else if ( ((LA13_50 >= '0' && LA13_50 <= '9')||(LA13_50 >= 'A' && LA13_50 <= 'Z')||(LA13_50 >= 'a' && LA13_50 <= 'k')||(LA13_50 >= 'm' && LA13_50 <= 'z')) ) {s = 36;}
						else if ( (LA13_50=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 101;
						 
						input.seek(index13_50);
						if ( s>=0 ) return s;
						break;

					case 114 : 
						int LA13_100 = input.LA(1);
						 
						int index13_100 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_100=='l') ) {s = 162;}
						else if ( ((LA13_100 >= '0' && LA13_100 <= '9')||(LA13_100 >= 'A' && LA13_100 <= 'Z')||(LA13_100 >= 'a' && LA13_100 <= 'k')||(LA13_100 >= 'm' && LA13_100 <= 'z')) ) {s = 36;}
						else if ( (LA13_100=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 163;
						 
						input.seek(index13_100);
						if ( s>=0 ) return s;
						break;

					case 115 : 
						int LA13_162 = input.LA(1);
						 
						int index13_162 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_162=='i') ) {s = 218;}
						else if ( ((LA13_162 >= '0' && LA13_162 <= '9')||(LA13_162 >= 'A' && LA13_162 <= 'Z')||(LA13_162 >= 'a' && LA13_162 <= 'h')||(LA13_162 >= 'j' && LA13_162 <= 'z')) ) {s = 36;}
						else if ( (LA13_162=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 219;
						 
						input.seek(index13_162);
						if ( s>=0 ) return s;
						break;

					case 116 : 
						int LA13_218 = input.LA(1);
						 
						int index13_218 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_218=='s') ) {s = 266;}
						else if ( ((LA13_218 >= '0' && LA13_218 <= '9')||(LA13_218 >= 'A' && LA13_218 <= 'Z')||(LA13_218 >= 'a' && LA13_218 <= 'r')||(LA13_218 >= 't' && LA13_218 <= 'z')) ) {s = 36;}
						else if ( (LA13_218=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 267;
						 
						input.seek(index13_218);
						if ( s>=0 ) return s;
						break;

					case 117 : 
						int LA13_266 = input.LA(1);
						 
						int index13_266 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_266=='h') ) {s = 310;}
						else if ( ((LA13_266 >= '0' && LA13_266 <= '9')||(LA13_266 >= 'A' && LA13_266 <= 'Z')||(LA13_266 >= 'a' && LA13_266 <= 'g')||(LA13_266 >= 'i' && LA13_266 <= 'z')) ) {s = 36;}
						else if ( (LA13_266=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 311;
						 
						input.seek(index13_266);
						if ( s>=0 ) return s;
						break;

					case 118 : 
						int LA13_85 = input.LA(1);
						 
						int index13_85 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_85=='e') ) {s = 150;}
						else if ( ((LA13_85 >= '0' && LA13_85 <= '9')||(LA13_85 >= 'A' && LA13_85 <= 'Z')||(LA13_85 >= 'a' && LA13_85 <= 'd')||(LA13_85 >= 'f' && LA13_85 <= 'z')) ) {s = 36;}
						else if ( (LA13_85=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 151;
						 
						input.seek(index13_85);
						if ( s>=0 ) return s;
						break;

					case 119 : 
						int LA13_150 = input.LA(1);
						 
						int index13_150 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_150=='s') ) {s = 209;}
						else if ( ((LA13_150 >= '0' && LA13_150 <= '9')||(LA13_150 >= 'A' && LA13_150 <= 'Z')||(LA13_150 >= 'a' && LA13_150 <= 'r')||(LA13_150 >= 't' && LA13_150 <= 'z')) ) {s = 36;}
						else if ( (LA13_150=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 210;
						 
						input.seek(index13_150);
						if ( s>=0 ) return s;
						break;

					case 120 : 
						int LA13_209 = input.LA(1);
						 
						int index13_209 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_209==' ') ) {s = 260;}
						else if ( ((LA13_209 >= '0' && LA13_209 <= '9')||(LA13_209 >= 'A' && LA13_209 <= 'Z')||(LA13_209 >= 'a' && LA13_209 <= 'z')) ) {s = 36;}
						else if ( (LA13_209=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 261;
						 
						input.seek(index13_209);
						if ( s>=0 ) return s;
						break;

					case 121 : 
						int LA13_8 = input.LA(1);
						 
						int index13_8 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_8=='o') ) {s = 44;}
						else if ( ((LA13_8 >= '0' && LA13_8 <= '9')||(LA13_8 >= 'A' && LA13_8 <= 'Z')||(LA13_8 >= 'a' && LA13_8 <= 'n')||(LA13_8 >= 'p' && LA13_8 <= 'z')) ) {s = 36;}
						else if ( (LA13_8=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 37;
						 
						input.seek(index13_8);
						if ( s>=0 ) return s;
						break;

					case 122 : 
						int LA13_16 = input.LA(1);
						 
						int index13_16 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_16=='r') ) {s = 51;}
						else if ( (LA13_16=='l') ) {s = 52;}
						else if ( ((LA13_16 >= '0' && LA13_16 <= '9')||(LA13_16 >= 'A' && LA13_16 <= 'Z')||(LA13_16 >= 'a' && LA13_16 <= 'k')||(LA13_16 >= 'm' && LA13_16 <= 'q')||(LA13_16 >= 's' && LA13_16 <= 'z')) ) {s = 36;}
						else if ( (LA13_16=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 37;
						 
						input.seek(index13_16);
						if ( s>=0 ) return s;
						break;

					case 123 : 
						int LA13_14 = input.LA(1);
						 
						int index13_14 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_14=='a') ) {s = 48;}
						else if ( ((LA13_14 >= '0' && LA13_14 <= '9')||(LA13_14 >= 'A' && LA13_14 <= 'Z')||(LA13_14 >= 'b' && LA13_14 <= 'z')) ) {s = 36;}
						else if ( (LA13_14=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 37;
						 
						input.seek(index13_14);
						if ( s>=0 ) return s;
						break;

					case 124 : 
						int LA13_39 = input.LA(1);
						 
						int index13_39 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_39=='r') ) {s = 78;}
						else if ( ((LA13_39 >= '0' && LA13_39 <= '9')||(LA13_39 >= 'A' && LA13_39 <= 'Z')||(LA13_39 >= 'a' && LA13_39 <= 'q')||(LA13_39 >= 's' && LA13_39 <= 'z')) ) {s = 36;}
						else if ( (LA13_39=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 79;
						 
						input.seek(index13_39);
						if ( s>=0 ) return s;
						break;

					case 125 : 
						int LA13_78 = input.LA(1);
						 
						int index13_78 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_78=='e') ) {s = 143;}
						else if ( ((LA13_78 >= '0' && LA13_78 <= '9')||(LA13_78 >= 'A' && LA13_78 <= 'Z')||(LA13_78 >= 'a' && LA13_78 <= 'd')||(LA13_78 >= 'f' && LA13_78 <= 'z')) ) {s = 36;}
						else if ( (LA13_78=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 144;
						 
						input.seek(index13_78);
						if ( s>=0 ) return s;
						break;

					case 126 : 
						int LA13_143 = input.LA(1);
						 
						int index13_143 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_143=='c') ) {s = 202;}
						else if ( ((LA13_143 >= '0' && LA13_143 <= '9')||(LA13_143 >= 'A' && LA13_143 <= 'Z')||(LA13_143 >= 'a' && LA13_143 <= 'b')||(LA13_143 >= 'd' && LA13_143 <= 'z')) ) {s = 36;}
						else if ( (LA13_143=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 203;
						 
						input.seek(index13_143);
						if ( s>=0 ) return s;
						break;

					case 127 : 
						int LA13_202 = input.LA(1);
						 
						int index13_202 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_202=='t') ) {s = 254;}
						else if ( ((LA13_202 >= '0' && LA13_202 <= '9')||(LA13_202 >= 'A' && LA13_202 <= 'Z')||(LA13_202 >= 'a' && LA13_202 <= 's')||(LA13_202 >= 'u' && LA13_202 <= 'z')) ) {s = 36;}
						else if ( (LA13_202=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 255;
						 
						input.seek(index13_202);
						if ( s>=0 ) return s;
						break;

					case 128 : 
						int LA13_254 = input.LA(1);
						 
						int index13_254 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_254=='i') ) {s = 299;}
						else if ( ((LA13_254 >= '0' && LA13_254 <= '9')||(LA13_254 >= 'A' && LA13_254 <= 'Z')||(LA13_254 >= 'a' && LA13_254 <= 'h')||(LA13_254 >= 'j' && LA13_254 <= 'z')) ) {s = 36;}
						else if ( (LA13_254=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 300;
						 
						input.seek(index13_254);
						if ( s>=0 ) return s;
						break;

					case 129 : 
						int LA13_299 = input.LA(1);
						 
						int index13_299 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_299=='o') ) {s = 336;}
						else if ( ((LA13_299 >= '0' && LA13_299 <= '9')||(LA13_299 >= 'A' && LA13_299 <= 'Z')||(LA13_299 >= 'a' && LA13_299 <= 'n')||(LA13_299 >= 'p' && LA13_299 <= 'z')) ) {s = 36;}
						else if ( (LA13_299=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 337;
						 
						input.seek(index13_299);
						if ( s>=0 ) return s;
						break;

					case 130 : 
						int LA13_336 = input.LA(1);
						 
						int index13_336 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_336=='n') ) {s = 367;}
						else if ( ((LA13_336 >= '0' && LA13_336 <= '9')||(LA13_336 >= 'A' && LA13_336 <= 'Z')||(LA13_336 >= 'a' && LA13_336 <= 'm')||(LA13_336 >= 'o' && LA13_336 <= 'z')) ) {s = 36;}
						else if ( (LA13_336=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 368;
						 
						input.seek(index13_336);
						if ( s>=0 ) return s;
						break;

					case 131 : 
						int LA13_7 = input.LA(1);
						 
						int index13_7 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_7=='a') ) {s = 42;}
						else if ( (LA13_7=='o') ) {s = 43;}
						else if ( ((LA13_7 >= '0' && LA13_7 <= '9')||(LA13_7 >= 'A' && LA13_7 <= 'Z')||(LA13_7 >= 'b' && LA13_7 <= 'n')||(LA13_7 >= 'p' && LA13_7 <= 'z')) ) {s = 36;}
						else if ( (LA13_7=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 37;
						 
						input.seek(index13_7);
						if ( s>=0 ) return s;
						break;

					case 132 : 
						int LA13_6 = input.LA(1);
						 
						int index13_6 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_6=='e') ) {s = 40;}
						else if ( (LA13_6=='o') ) {s = 41;}
						else if ( ((LA13_6 >= '0' && LA13_6 <= '9')||(LA13_6 >= 'A' && LA13_6 <= 'Z')||(LA13_6 >= 'a' && LA13_6 <= 'd')||(LA13_6 >= 'f' && LA13_6 <= 'n')||(LA13_6 >= 'p' && LA13_6 <= 'z')) ) {s = 36;}
						else if ( (LA13_6=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 37;
						 
						input.seek(index13_6);
						if ( s>=0 ) return s;
						break;

					case 133 : 
						int LA13_53 = input.LA(1);
						 
						int index13_53 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_53=='d') ) {s = 106;}
						else if ( ((LA13_53 >= '0' && LA13_53 <= '9')||(LA13_53 >= 'A' && LA13_53 <= 'Z')||(LA13_53 >= 'a' && LA13_53 <= 'c')||(LA13_53 >= 'e' && LA13_53 <= 'z')) ) {s = 36;}
						else if ( (LA13_53=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 107;
						 
						input.seek(index13_53);
						if ( s>=0 ) return s;
						break;

					case 134 : 
						int LA13_106 = input.LA(1);
						 
						int index13_106 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_106=='i') ) {s = 168;}
						else if ( ((LA13_106 >= '0' && LA13_106 <= '9')||(LA13_106 >= 'A' && LA13_106 <= 'Z')||(LA13_106 >= 'a' && LA13_106 <= 'h')||(LA13_106 >= 'j' && LA13_106 <= 'z')) ) {s = 36;}
						else if ( (LA13_106=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 169;
						 
						input.seek(index13_106);
						if ( s>=0 ) return s;
						break;

					case 135 : 
						int LA13_168 = input.LA(1);
						 
						int index13_168 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_168=='n') ) {s = 224;}
						else if ( ((LA13_168 >= '0' && LA13_168 <= '9')||(LA13_168 >= 'A' && LA13_168 <= 'Z')||(LA13_168 >= 'a' && LA13_168 <= 'm')||(LA13_168 >= 'o' && LA13_168 <= 'z')) ) {s = 36;}
						else if ( (LA13_168=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 225;
						 
						input.seek(index13_168);
						if ( s>=0 ) return s;
						break;

					case 136 : 
						int LA13_224 = input.LA(1);
						 
						int index13_224 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_224=='g') ) {s = 271;}
						else if ( ((LA13_224 >= '0' && LA13_224 <= '9')||(LA13_224 >= 'A' && LA13_224 <= 'Z')||(LA13_224 >= 'a' && LA13_224 <= 'f')||(LA13_224 >= 'h' && LA13_224 <= 'z')) ) {s = 36;}
						else if ( (LA13_224=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 272;
						 
						input.seek(index13_224);
						if ( s>=0 ) return s;
						break;

					case 137 : 
						int LA13_271 = input.LA(1);
						 
						int index13_271 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_271==' ') ) {s = 314;}
						else if ( ((LA13_271 >= '0' && LA13_271 <= '9')||(LA13_271 >= 'A' && LA13_271 <= 'Z')||(LA13_271 >= 'a' && LA13_271 <= 'z')) ) {s = 36;}
						else if ( (LA13_271=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 315;
						 
						input.seek(index13_271);
						if ( s>=0 ) return s;
						break;

					case 138 : 
						int LA13_54 = input.LA(1);
						 
						int index13_54 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_54=='s') ) {s = 108;}
						else if ( ((LA13_54 >= '0' && LA13_54 <= '9')||(LA13_54 >= 'A' && LA13_54 <= 'Z')||(LA13_54 >= 'a' && LA13_54 <= 'r')||(LA13_54 >= 't' && LA13_54 <= 'z')) ) {s = 36;}
						else if ( (LA13_54=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 109;
						 
						input.seek(index13_54);
						if ( s>=0 ) return s;
						break;

					case 139 : 
						int LA13_108 = input.LA(1);
						 
						int index13_108 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_108=='i') ) {s = 170;}
						else if ( ((LA13_108 >= '0' && LA13_108 <= '9')||(LA13_108 >= 'A' && LA13_108 <= 'Z')||(LA13_108 >= 'a' && LA13_108 <= 'h')||(LA13_108 >= 'j' && LA13_108 <= 'z')) ) {s = 36;}
						else if ( (LA13_108=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 171;
						 
						input.seek(index13_108);
						if ( s>=0 ) return s;
						break;

					case 140 : 
						int LA13_170 = input.LA(1);
						 
						int index13_170 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_170=='l') ) {s = 226;}
						else if ( ((LA13_170 >= '0' && LA13_170 <= '9')||(LA13_170 >= 'A' && LA13_170 <= 'Z')||(LA13_170 >= 'a' && LA13_170 <= 'k')||(LA13_170 >= 'm' && LA13_170 <= 'z')) ) {s = 36;}
						else if ( (LA13_170=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 227;
						 
						input.seek(index13_170);
						if ( s>=0 ) return s;
						break;

					case 141 : 
						int LA13_226 = input.LA(1);
						 
						int index13_226 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_226=='o') ) {s = 273;}
						else if ( ((LA13_226 >= '0' && LA13_226 <= '9')||(LA13_226 >= 'A' && LA13_226 <= 'Z')||(LA13_226 >= 'a' && LA13_226 <= 'n')||(LA13_226 >= 'p' && LA13_226 <= 'z')) ) {s = 36;}
						else if ( (LA13_226=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 274;
						 
						input.seek(index13_226);
						if ( s>=0 ) return s;
						break;

					case 142 : 
						int LA13_273 = input.LA(1);
						 
						int index13_273 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_273=='n') ) {s = 316;}
						else if ( ((LA13_273 >= '0' && LA13_273 <= '9')||(LA13_273 >= 'A' && LA13_273 <= 'Z')||(LA13_273 >= 'a' && LA13_273 <= 'm')||(LA13_273 >= 'o' && LA13_273 <= 'z')) ) {s = 36;}
						else if ( (LA13_273=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 317;
						 
						input.seek(index13_273);
						if ( s>=0 ) return s;
						break;

					case 143 : 
						int LA13_42 = input.LA(1);
						 
						int index13_42 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_42=='t') ) {s = 84;}
						else if ( (LA13_42=='k') ) {s = 85;}
						else if ( ((LA13_42 >= '0' && LA13_42 <= '9')||(LA13_42 >= 'A' && LA13_42 <= 'Z')||(LA13_42 >= 'a' && LA13_42 <= 'j')||(LA13_42 >= 'l' && LA13_42 <= 's')||(LA13_42 >= 'u' && LA13_42 <= 'z')) ) {s = 36;}
						else if ( (LA13_42=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 86;
						 
						input.seek(index13_42);
						if ( s>=0 ) return s;
						break;

					case 144 : 
						int LA13_56 = input.LA(1);
						 
						int index13_56 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_56=='r') ) {s = 112;}
						else if ( ((LA13_56 >= '0' && LA13_56 <= '9')||(LA13_56 >= 'A' && LA13_56 <= 'Z')||(LA13_56 >= 'a' && LA13_56 <= 'q')||(LA13_56 >= 's' && LA13_56 <= 'z')) ) {s = 36;}
						else if ( (LA13_56=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 113;
						 
						input.seek(index13_56);
						if ( s>=0 ) return s;
						break;

					case 145 : 
						int LA13_155 = input.LA(1);
						 
						int index13_155 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_155=='r') ) {s = 213;}
						else if ( ((LA13_155 >= '0' && LA13_155 <= '9')||(LA13_155 >= 'A' && LA13_155 <= 'Z')||(LA13_155 >= 'a' && LA13_155 <= 'q')||(LA13_155 >= 's' && LA13_155 <= 'z')) ) {s = 36;}
						else if ( (LA13_155=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 214;
						 
						input.seek(index13_155);
						if ( s>=0 ) return s;
						break;

					case 146 : 
						int LA13_1 = input.LA(1);
						 
						int index13_1 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_1=='n') ) {s = 34;}
						else if ( (LA13_1=='l') ) {s = 35;}
						else if ( ((LA13_1 >= '0' && LA13_1 <= '9')||(LA13_1 >= 'A' && LA13_1 <= 'Z')||(LA13_1 >= 'a' && LA13_1 <= 'k')||LA13_1=='m'||(LA13_1 >= 'o' && LA13_1 <= 'z')) ) {s = 36;}
						else if ( (LA13_1=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 37;
						 
						input.seek(index13_1);
						if ( s>=0 ) return s;
						break;

					case 147 : 
						int LA13_58 = input.LA(1);
						 
						int index13_58 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_58=='e') ) {s = 116;}
						else if ( ((LA13_58 >= '0' && LA13_58 <= '9')||(LA13_58 >= 'A' && LA13_58 <= 'Z')||(LA13_58 >= 'a' && LA13_58 <= 'd')||(LA13_58 >= 'f' && LA13_58 <= 'z')) ) {s = 36;}
						else if ( (LA13_58=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 117;
						 
						input.seek(index13_58);
						if ( s>=0 ) return s;
						break;

					case 148 : 
						int LA13_116 = input.LA(1);
						 
						int index13_116 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_116=='e') ) {s = 177;}
						else if ( ((LA13_116 >= '0' && LA13_116 <= '9')||(LA13_116 >= 'A' && LA13_116 <= 'Z')||(LA13_116 >= 'a' && LA13_116 <= 'd')||(LA13_116 >= 'f' && LA13_116 <= 'z')) ) {s = 36;}
						else if ( (LA13_116=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 178;
						 
						input.seek(index13_116);
						if ( s>=0 ) return s;
						break;

					case 149 : 
						int LA13_177 = input.LA(1);
						 
						int index13_177 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_177=='d') ) {s = 232;}
						else if ( ((LA13_177 >= '0' && LA13_177 <= '9')||(LA13_177 >= 'A' && LA13_177 <= 'Z')||(LA13_177 >= 'a' && LA13_177 <= 'c')||(LA13_177 >= 'e' && LA13_177 <= 'z')) ) {s = 36;}
						else if ( (LA13_177=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 233;
						 
						input.seek(index13_177);
						if ( s>=0 ) return s;
						break;

					case 150 : 
						int LA13_19 = input.LA(1);
						 
						int index13_19 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_19=='o') ) {s = 57;}
						else if ( (LA13_19=='r') ) {s = 58;}
						else if ( ((LA13_19 >= '0' && LA13_19 <= '9')||(LA13_19 >= 'A' && LA13_19 <= 'Z')||(LA13_19 >= 'a' && LA13_19 <= 'n')||(LA13_19 >= 'p' && LA13_19 <= 'q')||(LA13_19 >= 's' && LA13_19 <= 'z')) ) {s = 36;}
						else if ( (LA13_19=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 37;
						 
						input.seek(index13_19);
						if ( s>=0 ) return s;
						break;

					case 151 : 
						int LA13_71 = input.LA(1);
						 
						int index13_71 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_71 >= '0' && LA13_71 <= '9')||(LA13_71 >= 'A' && LA13_71 <= 'Z')||(LA13_71 >= 'a' && LA13_71 <= 'z')) ) {s = 36;}
						else if ( (LA13_71=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 138;
						 
						input.seek(index13_71);
						if ( s>=0 ) return s;
						break;

					case 152 : 
						int LA13_18 = input.LA(1);
						 
						int index13_18 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_18=='o') ) {s = 56;}
						else if ( ((LA13_18 >= '0' && LA13_18 <= '9')||(LA13_18 >= 'A' && LA13_18 <= 'Z')||(LA13_18 >= 'a' && LA13_18 <= 'n')||(LA13_18 >= 'p' && LA13_18 <= 'z')) ) {s = 36;}
						else if ( (LA13_18=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 37;
						 
						input.seek(index13_18);
						if ( s>=0 ) return s;
						break;

					case 153 : 
						int LA13_15 = input.LA(1);
						 
						int index13_15 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_15=='e') ) {s = 49;}
						else if ( (LA13_15=='u') ) {s = 50;}
						else if ( ((LA13_15 >= '0' && LA13_15 <= '9')||(LA13_15 >= 'A' && LA13_15 <= 'Z')||(LA13_15 >= 'a' && LA13_15 <= 'd')||(LA13_15 >= 'f' && LA13_15 <= 't')||(LA13_15 >= 'v' && LA13_15 <= 'z')) ) {s = 36;}
						else if ( (LA13_15=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 37;
						 
						input.seek(index13_15);
						if ( s>=0 ) return s;
						break;

					case 154 : 
						int LA13_141 = input.LA(1);
						 
						int index13_141 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_141 >= '0' && LA13_141 <= '9')||(LA13_141 >= 'A' && LA13_141 <= 'Z')||(LA13_141 >= 'a' && LA13_141 <= 'z')) ) {s = 36;}
						else if ( (LA13_141=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 201;
						 
						input.seek(index13_141);
						if ( s>=0 ) return s;
						break;

					case 155 : 
						int LA13_5 = input.LA(1);
						 
						int index13_5 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_5=='a') ) {s = 38;}
						else if ( (LA13_5=='i') ) {s = 39;}
						else if ( ((LA13_5 >= '0' && LA13_5 <= '9')||(LA13_5 >= 'A' && LA13_5 <= 'Z')||(LA13_5 >= 'b' && LA13_5 <= 'h')||(LA13_5 >= 'j' && LA13_5 <= 'z')) ) {s = 36;}
						else if ( (LA13_5=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 37;
						 
						input.seek(index13_5);
						if ( s>=0 ) return s;
						break;

					case 156 : 
						int LA13_83 = input.LA(1);
						 
						int index13_83 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_83);
						if ( s>=0 ) return s;
						break;

					case 157 : 
						int LA13_72 = input.LA(1);
						 
						int index13_72 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_72);
						if ( s>=0 ) return s;
						break;

					case 158 : 
						int LA13_74 = input.LA(1);
						 
						int index13_74 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_74);
						if ( s>=0 ) return s;
						break;

					case 159 : 
						int LA13_75 = input.LA(1);
						 
						int index13_75 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_75);
						if ( s>=0 ) return s;
						break;

					case 160 : 
						int LA13_144 = input.LA(1);
						 
						int index13_144 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_144);
						if ( s>=0 ) return s;
						break;

					case 161 : 
						int LA13_246 = input.LA(1);
						 
						int index13_246 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_246);
						if ( s>=0 ) return s;
						break;

					case 162 : 
						int LA13_182 = input.LA(1);
						 
						int index13_182 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_182);
						if ( s>=0 ) return s;
						break;

					case 163 : 
						int LA13_121 = input.LA(1);
						 
						int index13_121 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_121);
						if ( s>=0 ) return s;
						break;

					case 164 : 
						int LA13_135 = input.LA(1);
						 
						int index13_135 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_135);
						if ( s>=0 ) return s;
						break;

					case 165 : 
						int LA13_298 = input.LA(1);
						 
						int index13_298 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_298);
						if ( s>=0 ) return s;
						break;

					case 166 : 
						int LA13_332 = input.LA(1);
						 
						int index13_332 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_332);
						if ( s>=0 ) return s;
						break;

					case 167 : 
						int LA13_261 = input.LA(1);
						 
						int index13_261 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_261);
						if ( s>=0 ) return s;
						break;

					case 168 : 
						int LA13_324 = input.LA(1);
						 
						int index13_324 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_324);
						if ( s>=0 ) return s;
						break;

					case 169 : 
						int LA13_151 = input.LA(1);
						 
						int index13_151 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_151);
						if ( s>=0 ) return s;
						break;

					case 170 : 
						int LA13_173 = input.LA(1);
						 
						int index13_173 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_173);
						if ( s>=0 ) return s;
						break;

					case 171 : 
						int LA13_77 = input.LA(1);
						 
						int index13_77 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_77);
						if ( s>=0 ) return s;
						break;

					case 172 : 
						int LA13_95 = input.LA(1);
						 
						int index13_95 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_95);
						if ( s>=0 ) return s;
						break;

					case 173 : 
						int LA13_296 = input.LA(1);
						 
						int index13_296 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_296);
						if ( s>=0 ) return s;
						break;

					case 174 : 
						int LA13_313 = input.LA(1);
						 
						int index13_313 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_313);
						if ( s>=0 ) return s;
						break;

					case 175 : 
						int LA13_142 = input.LA(1);
						 
						int index13_142 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_142);
						if ( s>=0 ) return s;
						break;

					case 176 : 
						int LA13_156 = input.LA(1);
						 
						int index13_156 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_156);
						if ( s>=0 ) return s;
						break;

					case 177 : 
						int LA13_221 = input.LA(1);
						 
						int index13_221 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_221);
						if ( s>=0 ) return s;
						break;

					case 178 : 
						int LA13_163 = input.LA(1);
						 
						int index13_163 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_163);
						if ( s>=0 ) return s;
						break;

					case 179 : 
						int LA13_190 = input.LA(1);
						 
						int index13_190 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_190);
						if ( s>=0 ) return s;
						break;

					case 180 : 
						int LA13_242 = input.LA(1);
						 
						int index13_242 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_242);
						if ( s>=0 ) return s;
						break;

					case 181 : 
						int LA13_200 = input.LA(1);
						 
						int index13_200 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_200);
						if ( s>=0 ) return s;
						break;

					case 182 : 
						int LA13_290 = input.LA(1);
						 
						int index13_290 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_290);
						if ( s>=0 ) return s;
						break;

					case 183 : 
						int LA13_111 = input.LA(1);
						 
						int index13_111 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_111);
						if ( s>=0 ) return s;
						break;

					case 184 : 
						int LA13_133 = input.LA(1);
						 
						int index13_133 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_133);
						if ( s>=0 ) return s;
						break;

					case 185 : 
						int LA13_178 = input.LA(1);
						 
						int index13_178 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_178);
						if ( s>=0 ) return s;
						break;

					case 186 : 
						int LA13_196 = input.LA(1);
						 
						int index13_196 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_196);
						if ( s>=0 ) return s;
						break;

					case 187 : 
						int LA13_149 = input.LA(1);
						 
						int index13_149 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_149);
						if ( s>=0 ) return s;
						break;

					case 188 : 
						int LA13_194 = input.LA(1);
						 
						int index13_194 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_194);
						if ( s>=0 ) return s;
						break;

					case 189 : 
						int LA13_208 = input.LA(1);
						 
						int index13_208 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_208);
						if ( s>=0 ) return s;
						break;

					case 190 : 
						int LA13_231 = input.LA(1);
						 
						int index13_231 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_231);
						if ( s>=0 ) return s;
						break;

					case 191 : 
						int LA13_210 = input.LA(1);
						 
						int index13_210 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_210);
						if ( s>=0 ) return s;
						break;

					case 192 : 
						int LA13_274 = input.LA(1);
						 
						int index13_274 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_274);
						if ( s>=0 ) return s;
						break;

					case 193 : 
						int LA13_158 = input.LA(1);
						 
						int index13_158 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_158);
						if ( s>=0 ) return s;
						break;

					case 194 : 
						int LA13_103 = input.LA(1);
						 
						int index13_103 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_103);
						if ( s>=0 ) return s;
						break;

					case 195 : 
						int LA13_109 = input.LA(1);
						 
						int index13_109 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_109);
						if ( s>=0 ) return s;
						break;

					case 196 : 
						int LA13_125 = input.LA(1);
						 
						int index13_125 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_125);
						if ( s>=0 ) return s;
						break;

					case 197 : 
						int LA13_90 = input.LA(1);
						 
						int index13_90 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_90);
						if ( s>=0 ) return s;
						break;

					case 198 : 
						int LA13_192 = input.LA(1);
						 
						int index13_192 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_192);
						if ( s>=0 ) return s;
						break;

					case 199 : 
						int LA13_127 = input.LA(1);
						 
						int index13_127 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_127);
						if ( s>=0 ) return s;
						break;

					case 200 : 
						int LA13_188 = input.LA(1);
						 
						int index13_188 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_188);
						if ( s>=0 ) return s;
						break;

					case 201 : 
						int LA13_238 = input.LA(1);
						 
						int index13_238 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_238);
						if ( s>=0 ) return s;
						break;

					case 202 : 
						int LA13_198 = input.LA(1);
						 
						int index13_198 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_198);
						if ( s>=0 ) return s;
						break;

					case 203 : 
						int LA13_97 = input.LA(1);
						 
						int index13_97 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_97);
						if ( s>=0 ) return s;
						break;

					case 204 : 
						int LA13_307 = input.LA(1);
						 
						int index13_307 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_307);
						if ( s>=0 ) return s;
						break;

					case 205 : 
						int LA13_137 = input.LA(1);
						 
						int index13_137 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_137);
						if ( s>=0 ) return s;
						break;

					case 206 : 
						int LA13_309 = input.LA(1);
						 
						int index13_309 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_309);
						if ( s>=0 ) return s;
						break;

					case 207 : 
						int LA13_343 = input.LA(1);
						 
						int index13_343 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_343);
						if ( s>=0 ) return s;
						break;

					case 208 : 
						int LA13_302 = input.LA(1);
						 
						int index13_302 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_302);
						if ( s>=0 ) return s;
						break;

					case 209 : 
						int LA13_334 = input.LA(1);
						 
						int index13_334 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_334);
						if ( s>=0 ) return s;
						break;

					case 210 : 
						int LA13_227 = input.LA(1);
						 
						int index13_227 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_227);
						if ( s>=0 ) return s;
						break;

					case 211 : 
						int LA13_244 = input.LA(1);
						 
						int index13_244 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_244);
						if ( s>=0 ) return s;
						break;

					case 212 : 
						int LA13_186 = input.LA(1);
						 
						int index13_186 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_186);
						if ( s>=0 ) return s;
						break;

					case 213 : 
						int LA13_113 = input.LA(1);
						 
						int index13_113 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_113);
						if ( s>=0 ) return s;
						break;

					case 214 : 
						int LA13_79 = input.LA(1);
						 
						int index13_79 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_79);
						if ( s>=0 ) return s;
						break;

					case 215 : 
						int LA13_81 = input.LA(1);
						 
						int index13_81 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_81);
						if ( s>=0 ) return s;
						break;

					case 216 : 
						int LA13_117 = input.LA(1);
						 
						int index13_117 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_117);
						if ( s>=0 ) return s;
						break;

					case 217 : 
						int LA13_184 = input.LA(1);
						 
						int index13_184 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_184);
						if ( s>=0 ) return s;
						break;

					case 218 : 
						int LA13_292 = input.LA(1);
						 
						int index13_292 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_292);
						if ( s>=0 ) return s;
						break;

					case 219 : 
						int LA13_357 = input.LA(1);
						 
						int index13_357 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_357);
						if ( s>=0 ) return s;
						break;

					case 220 : 
						int LA13_355 = input.LA(1);
						 
						int index13_355 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_355);
						if ( s>=0 ) return s;
						break;

					case 221 : 
						int LA13_269 = input.LA(1);
						 
						int index13_269 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_269);
						if ( s>=0 ) return s;
						break;

					case 222 : 
						int LA13_171 = input.LA(1);
						 
						int index13_171 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_171);
						if ( s>=0 ) return s;
						break;

					case 223 : 
						int LA13_315 = input.LA(1);
						 
						int index13_315 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_315);
						if ( s>=0 ) return s;
						break;

					case 224 : 
						int LA13_131 = input.LA(1);
						 
						int index13_131 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_131);
						if ( s>=0 ) return s;
						break;

					case 225 : 
						int LA13_153 = input.LA(1);
						 
						int index13_153 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_153);
						if ( s>=0 ) return s;
						break;

					case 226 : 
						int LA13_284 = input.LA(1);
						 
						int index13_284 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_284);
						if ( s>=0 ) return s;
						break;

					case 227 : 
						int LA13_337 = input.LA(1);
						 
						int index13_337 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_337);
						if ( s>=0 ) return s;
						break;

					case 228 : 
						int LA13_259 = input.LA(1);
						 
						int index13_259 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_259);
						if ( s>=0 ) return s;
						break;

					case 229 : 
						int LA13_319 = input.LA(1);
						 
						int index13_319 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_319);
						if ( s>=0 ) return s;
						break;

					case 230 : 
						int LA13_263 = input.LA(1);
						 
						int index13_263 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_263);
						if ( s>=0 ) return s;
						break;

					case 231 : 
						int LA13_105 = input.LA(1);
						 
						int index13_105 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_105);
						if ( s>=0 ) return s;
						break;

					case 232 : 
						int LA13_161 = input.LA(1);
						 
						int index13_161 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_161);
						if ( s>=0 ) return s;
						break;

					case 233 : 
						int LA13_300 = input.LA(1);
						 
						int index13_300 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_300);
						if ( s>=0 ) return s;
						break;

					case 234 : 
						int LA13_129 = input.LA(1);
						 
						int index13_129 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_129);
						if ( s>=0 ) return s;
						break;

					case 235 : 
						int LA13_294 = input.LA(1);
						 
						int index13_294 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_294);
						if ( s>=0 ) return s;
						break;

					case 236 : 
						int LA13_272 = input.LA(1);
						 
						int index13_272 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_272);
						if ( s>=0 ) return s;
						break;

					case 237 : 
						int LA13_250 = input.LA(1);
						 
						int index13_250 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_250);
						if ( s>=0 ) return s;
						break;

					case 238 : 
						int LA13_257 = input.LA(1);
						 
						int index13_257 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_257);
						if ( s>=0 ) return s;
						break;

					case 239 : 
						int LA13_99 = input.LA(1);
						 
						int index13_99 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_99);
						if ( s>=0 ) return s;
						break;

					case 240 : 
						int LA13_330 = input.LA(1);
						 
						int index13_330 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_330);
						if ( s>=0 ) return s;
						break;

					case 241 : 
						int LA13_361 = input.LA(1);
						 
						int index13_361 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_361);
						if ( s>=0 ) return s;
						break;

					case 242 : 
						int LA13_140 = input.LA(1);
						 
						int index13_140 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_140);
						if ( s>=0 ) return s;
						break;

					case 243 : 
						int LA13_107 = input.LA(1);
						 
						int index13_107 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_107);
						if ( s>=0 ) return s;
						break;

					case 244 : 
						int LA13_115 = input.LA(1);
						 
						int index13_115 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_115);
						if ( s>=0 ) return s;
						break;

					case 245 : 
						int LA13_101 = input.LA(1);
						 
						int index13_101 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_101);
						if ( s>=0 ) return s;
						break;

					case 246 : 
						int LA13_86 = input.LA(1);
						 
						int index13_86 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_86);
						if ( s>=0 ) return s;
						break;

					case 247 : 
						int LA13_368 = input.LA(1);
						 
						int index13_368 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_368);
						if ( s>=0 ) return s;
						break;

					case 248 : 
						int LA13_265 = input.LA(1);
						 
						int index13_265 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_265);
						if ( s>=0 ) return s;
						break;

					case 249 : 
						int LA13_359 = input.LA(1);
						 
						int index13_359 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_359);
						if ( s>=0 ) return s;
						break;

					case 250 : 
						int LA13_165 = input.LA(1);
						 
						int index13_165 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_165);
						if ( s>=0 ) return s;
						break;

					case 251 : 
						int LA13_233 = input.LA(1);
						 
						int index13_233 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_233);
						if ( s>=0 ) return s;
						break;

					case 252 : 
						int LA13_276 = input.LA(1);
						 
						int index13_276 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_276);
						if ( s>=0 ) return s;
						break;

					case 253 : 
						int LA13_389 = input.LA(1);
						 
						int index13_389 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_389);
						if ( s>=0 ) return s;
						break;

					case 254 : 
						int LA13_317 = input.LA(1);
						 
						int index13_317 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_317);
						if ( s>=0 ) return s;
						break;

					case 255 : 
						int LA13_374 = input.LA(1);
						 
						int index13_374 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_374);
						if ( s>=0 ) return s;
						break;

					case 256 : 
						int LA13_248 = input.LA(1);
						 
						int index13_248 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_248);
						if ( s>=0 ) return s;
						break;

					case 257 : 
						int LA13_219 = input.LA(1);
						 
						int index13_219 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_219);
						if ( s>=0 ) return s;
						break;

					case 258 : 
						int LA13_267 = input.LA(1);
						 
						int index13_267 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_267);
						if ( s>=0 ) return s;
						break;

					case 259 : 
						int LA13_205 = input.LA(1);
						 
						int index13_205 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_205);
						if ( s>=0 ) return s;
						break;

					case 260 : 
						int LA13_93 = input.LA(1);
						 
						int index13_93 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_93);
						if ( s>=0 ) return s;
						break;

					case 261 : 
						int LA13_123 = input.LA(1);
						 
						int index13_123 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_123);
						if ( s>=0 ) return s;
						break;

					case 262 : 
						int LA13_212 = input.LA(1);
						 
						int index13_212 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_212);
						if ( s>=0 ) return s;
						break;

					case 263 : 
						int LA13_304 = input.LA(1);
						 
						int index13_304 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_304);
						if ( s>=0 ) return s;
						break;

					case 264 : 
						int LA13_225 = input.LA(1);
						 
						int index13_225 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_225);
						if ( s>=0 ) return s;
						break;

					case 265 : 
						int LA13_229 = input.LA(1);
						 
						int index13_229 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_229);
						if ( s>=0 ) return s;
						break;

					case 266 : 
						int LA13_286 = input.LA(1);
						 
						int index13_286 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_286);
						if ( s>=0 ) return s;
						break;

					case 267 : 
						int LA13_288 = input.LA(1);
						 
						int index13_288 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_288);
						if ( s>=0 ) return s;
						break;

					case 268 : 
						int LA13_176 = input.LA(1);
						 
						int index13_176 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_176);
						if ( s>=0 ) return s;
						break;

					case 269 : 
						int LA13_326 = input.LA(1);
						 
						int index13_326 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_326);
						if ( s>=0 ) return s;
						break;

					case 270 : 
						int LA13_328 = input.LA(1);
						 
						int index13_328 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_328);
						if ( s>=0 ) return s;
						break;

					case 271 : 
						int LA13_88 = input.LA(1);
						 
						int index13_88 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_88);
						if ( s>=0 ) return s;
						break;

					case 272 : 
						int LA13_146 = input.LA(1);
						 
						int index13_146 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_146);
						if ( s>=0 ) return s;
						break;

					case 273 : 
						int LA13_240 = input.LA(1);
						 
						int index13_240 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_240);
						if ( s>=0 ) return s;
						break;

					case 274 : 
						int LA13_253 = input.LA(1);
						 
						int index13_253 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_253);
						if ( s>=0 ) return s;
						break;

					case 275 : 
						int LA13_347 = input.LA(1);
						 
						int index13_347 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_347);
						if ( s>=0 ) return s;
						break;

					case 276 : 
						int LA13_223 = input.LA(1);
						 
						int index13_223 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_223);
						if ( s>=0 ) return s;
						break;

					case 277 : 
						int LA13_340 = input.LA(1);
						 
						int index13_340 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_340);
						if ( s>=0 ) return s;
						break;

					case 278 : 
						int LA13_119 = input.LA(1);
						 
						int index13_119 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_119);
						if ( s>=0 ) return s;
						break;

					case 279 : 
						int LA13_169 = input.LA(1);
						 
						int index13_169 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_169);
						if ( s>=0 ) return s;
						break;

					case 280 : 
						int LA13_203 = input.LA(1);
						 
						int index13_203 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_203);
						if ( s>=0 ) return s;
						break;

					case 281 : 
						int LA13_311 = input.LA(1);
						 
						int index13_311 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_311);
						if ( s>=0 ) return s;
						break;

					case 282 : 
						int LA13_167 = input.LA(1);
						 
						int index13_167 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_167);
						if ( s>=0 ) return s;
						break;

					case 283 : 
						int LA13_387 = input.LA(1);
						 
						int index13_387 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_387);
						if ( s>=0 ) return s;
						break;

					case 284 : 
						int LA13_217 = input.LA(1);
						 
						int index13_217 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_217);
						if ( s>=0 ) return s;
						break;

					case 285 : 
						int LA13_363 = input.LA(1);
						 
						int index13_363 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_363);
						if ( s>=0 ) return s;
						break;

					case 286 : 
						int LA13_383 = input.LA(1);
						 
						int index13_383 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_383);
						if ( s>=0 ) return s;
						break;

					case 287 : 
						int LA13_255 = input.LA(1);
						 
						int index13_255 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 37;}
						 
						input.seek(index13_255);
						if ( s>=0 ) return s;
						break;

					case 288 : 
						int LA13_301 = input.LA(1);
						 
						int index13_301 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_301 >= '0' && LA13_301 <= '9')||(LA13_301 >= 'A' && LA13_301 <= 'Z')||(LA13_301 >= 'a' && LA13_301 <= 'z')) ) {s = 36;}
						else if ( (LA13_301=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 338;
						 
						input.seek(index13_301);
						if ( s>=0 ) return s;
						break;

					case 289 : 
						int LA13_339 = input.LA(1);
						 
						int index13_339 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_339 >= '0' && LA13_339 <= '9')||(LA13_339 >= 'A' && LA13_339 <= 'Z')||(LA13_339 >= 'a' && LA13_339 <= 'z')) ) {s = 36;}
						else if ( (LA13_339=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 369;
						 
						input.seek(index13_339);
						if ( s>=0 ) return s;
						break;

					case 290 : 
						int LA13_89 = input.LA(1);
						 
						int index13_89 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_89 >= '0' && LA13_89 <= '9')||(LA13_89 >= 'A' && LA13_89 <= 'Z')||(LA13_89 >= 'a' && LA13_89 <= 'z')) ) {s = 36;}
						else if ( (LA13_89=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 154;
						 
						input.seek(index13_89);
						if ( s>=0 ) return s;
						break;

					case 291 : 
						int LA13_46 = input.LA(1);
						 
						int index13_46 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_46=='e') ) {s = 92;}
						else if ( ((LA13_46 >= '0' && LA13_46 <= '9')||(LA13_46 >= 'A' && LA13_46 <= 'Z')||(LA13_46 >= 'a' && LA13_46 <= 'd')||(LA13_46 >= 'f' && LA13_46 <= 'z')) ) {s = 36;}
						else if ( (LA13_46=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 93;
						 
						input.seek(index13_46);
						if ( s>=0 ) return s;
						break;

					case 292 : 
						int LA13_92 = input.LA(1);
						 
						int index13_92 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_92=='r') ) {s = 155;}
						else if ( ((LA13_92 >= '0' && LA13_92 <= '9')||(LA13_92 >= 'A' && LA13_92 <= 'Z')||(LA13_92 >= 'a' && LA13_92 <= 'q')||(LA13_92 >= 's' && LA13_92 <= 'z')) ) {s = 36;}
						else if ( (LA13_92=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 156;
						 
						input.seek(index13_92);
						if ( s>=0 ) return s;
						break;

					case 293 : 
						int LA13_45 = input.LA(1);
						 
						int index13_45 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_45 >= '0' && LA13_45 <= '9')||(LA13_45 >= 'A' && LA13_45 <= 'Z')||(LA13_45 >= 'a' && LA13_45 <= 'z')) ) {s = 36;}
						else if ( (LA13_45=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 91;
						 
						input.seek(index13_45);
						if ( s>=0 ) return s;
						break;

					case 294 : 
						int LA13_96 = input.LA(1);
						 
						int index13_96 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_96 >= '0' && LA13_96 <= '9')||(LA13_96 >= 'A' && LA13_96 <= 'Z')||(LA13_96 >= 'a' && LA13_96 <= 'z')) ) {s = 36;}
						else if ( (LA13_96=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 159;
						 
						input.seek(index13_96);
						if ( s>=0 ) return s;
						break;

					case 295 : 
						int LA13_356 = input.LA(1);
						 
						int index13_356 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_356 >= '0' && LA13_356 <= '9')||(LA13_356 >= 'A' && LA13_356 <= 'Z')||(LA13_356 >= 'a' && LA13_356 <= 'z')) ) {s = 36;}
						else if ( (LA13_356=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 384;
						 
						input.seek(index13_356);
						if ( s>=0 ) return s;
						break;

					case 296 : 
						int LA13_358 = input.LA(1);
						 
						int index13_358 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_358 >= '0' && LA13_358 <= '9')||(LA13_358 >= 'A' && LA13_358 <= 'Z')||(LA13_358 >= 'a' && LA13_358 <= 'z')) ) {s = 36;}
						else if ( (LA13_358=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 385;
						 
						input.seek(index13_358);
						if ( s>=0 ) return s;
						break;

					case 297 : 
						int LA13_60 = input.LA(1);
						 
						int index13_60 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_60=='v') ) {s = 120;}
						else if ( ((LA13_60 >= '0' && LA13_60 <= '9')||(LA13_60 >= 'A' && LA13_60 <= 'Z')||(LA13_60 >= 'a' && LA13_60 <= 'u')||(LA13_60 >= 'w' && LA13_60 <= 'z')) ) {s = 36;}
						else if ( (LA13_60=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 121;
						 
						input.seek(index13_60);
						if ( s>=0 ) return s;
						break;

					case 298 : 
						int LA13_120 = input.LA(1);
						 
						int index13_120 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_120=='e') ) {s = 181;}
						else if ( ((LA13_120 >= '0' && LA13_120 <= '9')||(LA13_120 >= 'A' && LA13_120 <= 'Z')||(LA13_120 >= 'a' && LA13_120 <= 'd')||(LA13_120 >= 'f' && LA13_120 <= 'z')) ) {s = 36;}
						else if ( (LA13_120=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 182;
						 
						input.seek(index13_120);
						if ( s>=0 ) return s;
						break;

					case 299 : 
						int LA13_181 = input.LA(1);
						 
						int index13_181 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_181=='r') ) {s = 237;}
						else if ( ((LA13_181 >= '0' && LA13_181 <= '9')||(LA13_181 >= 'A' && LA13_181 <= 'Z')||(LA13_181 >= 'a' && LA13_181 <= 'q')||(LA13_181 >= 's' && LA13_181 <= 'z')) ) {s = 36;}
						else if ( (LA13_181=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 238;
						 
						input.seek(index13_181);
						if ( s>=0 ) return s;
						break;

					case 300 : 
						int LA13_237 = input.LA(1);
						 
						int index13_237 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_237=='s') ) {s = 283;}
						else if ( ((LA13_237 >= '0' && LA13_237 <= '9')||(LA13_237 >= 'A' && LA13_237 <= 'Z')||(LA13_237 >= 'a' && LA13_237 <= 'r')||(LA13_237 >= 't' && LA13_237 <= 'z')) ) {s = 36;}
						else if ( (LA13_237=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 284;
						 
						input.seek(index13_237);
						if ( s>=0 ) return s;
						break;

					case 301 : 
						int LA13_283 = input.LA(1);
						 
						int index13_283 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_283=='e') ) {s = 323;}
						else if ( ((LA13_283 >= '0' && LA13_283 <= '9')||(LA13_283 >= 'A' && LA13_283 <= 'Z')||(LA13_283 >= 'a' && LA13_283 <= 'd')||(LA13_283 >= 'f' && LA13_283 <= 'z')) ) {s = 36;}
						else if ( (LA13_283=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 324;
						 
						input.seek(index13_283);
						if ( s>=0 ) return s;
						break;

					case 302 : 
						int LA13_323 = input.LA(1);
						 
						int index13_323 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_323=='s') ) {s = 354;}
						else if ( ((LA13_323 >= '0' && LA13_323 <= '9')||(LA13_323 >= 'A' && LA13_323 <= 'Z')||(LA13_323 >= 'a' && LA13_323 <= 'r')||(LA13_323 >= 't' && LA13_323 <= 'z')) ) {s = 36;}
						else if ( (LA13_323=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 355;
						 
						input.seek(index13_323);
						if ( s>=0 ) return s;
						break;

					case 303 : 
						int LA13_354 = input.LA(1);
						 
						int index13_354 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_354==' ') ) {s = 382;}
						else if ( ((LA13_354 >= '0' && LA13_354 <= '9')||(LA13_354 >= 'A' && LA13_354 <= 'Z')||(LA13_354 >= 'a' && LA13_354 <= 'z')) ) {s = 36;}
						else if ( (LA13_354=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 383;
						 
						input.seek(index13_354);
						if ( s>=0 ) return s;
						break;

					case 304 : 
						int LA13_308 = input.LA(1);
						 
						int index13_308 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_308 >= '0' && LA13_308 <= '9')||(LA13_308 >= 'A' && LA13_308 <= 'Z')||(LA13_308 >= 'a' && LA13_308 <= 'z')) ) {s = 36;}
						else if ( (LA13_308=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 344;
						 
						input.seek(index13_308);
						if ( s>=0 ) return s;
						break;

					case 305 : 
						int LA13_310 = input.LA(1);
						 
						int index13_310 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_310 >= '0' && LA13_310 <= '9')||(LA13_310 >= 'A' && LA13_310 <= 'Z')||(LA13_310 >= 'a' && LA13_310 <= 'z')) ) {s = 36;}
						else if ( (LA13_310=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 345;
						 
						input.seek(index13_310);
						if ( s>=0 ) return s;
						break;

					case 306 : 
						int LA13_22 = input.LA(1);
						 
						int index13_22 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_22=='l') ) {s = 61;}
						else if ( (LA13_22=='m') ) {s = 62;}
						else if ( (LA13_22=='p') ) {s = 63;}
						else if ( (LA13_22=='t') ) {s = 64;}
						else if ( ((LA13_22 >= '0' && LA13_22 <= '9')||(LA13_22 >= 'A' && LA13_22 <= 'Z')||(LA13_22 >= 'a' && LA13_22 <= 'k')||(LA13_22 >= 'n' && LA13_22 <= 'o')||(LA13_22 >= 'q' && LA13_22 <= 's')||(LA13_22 >= 'u' && LA13_22 <= 'z')) ) {s = 36;}
						else if ( (LA13_22=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 37;
						 
						input.seek(index13_22);
						if ( s>=0 ) return s;
						break;

					case 307 : 
						int LA13_388 = input.LA(1);
						 
						int index13_388 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_388 >= '0' && LA13_388 <= '9')||(LA13_388 >= 'A' && LA13_388 <= 'Z')||(LA13_388 >= 'a' && LA13_388 <= 'z')) ) {s = 36;}
						else if ( (LA13_388=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 403;
						 
						input.seek(index13_388);
						if ( s>=0 ) return s;
						break;

					case 308 : 
						int LA13_43 = input.LA(1);
						 
						int index13_43 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_43=='r') ) {s = 87;}
						else if ( ((LA13_43 >= '0' && LA13_43 <= '9')||(LA13_43 >= 'A' && LA13_43 <= 'Z')||(LA13_43 >= 'a' && LA13_43 <= 'q')||(LA13_43 >= 's' && LA13_43 <= 'z')) ) {s = 36;}
						else if ( (LA13_43=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 88;
						 
						input.seek(index13_43);
						if ( s>=0 ) return s;
						break;

					case 309 : 
						int LA13_87 = input.LA(1);
						 
						int index13_87 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_87=='e') ) {s = 152;}
						else if ( ((LA13_87 >= '0' && LA13_87 <= '9')||(LA13_87 >= 'A' && LA13_87 <= 'Z')||(LA13_87 >= 'a' && LA13_87 <= 'd')||(LA13_87 >= 'f' && LA13_87 <= 'z')) ) {s = 36;}
						else if ( (LA13_87=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 153;
						 
						input.seek(index13_87);
						if ( s>=0 ) return s;
						break;

					case 310 : 
						int LA13_152 = input.LA(1);
						 
						int index13_152 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_152==' ') ) {s = 211;}
						else if ( ((LA13_152 >= '0' && LA13_152 <= '9')||(LA13_152 >= 'A' && LA13_152 <= 'Z')||(LA13_152 >= 'a' && LA13_152 <= 'z')) ) {s = 36;}
						else if ( (LA13_152=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 212;
						 
						input.seek(index13_152);
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
