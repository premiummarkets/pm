// $ANTLR 3.5 /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g 2013-06-09 22:47:57
 //lexer
    package com.finance.pms.events.operations.parameterized.antlr;
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

import com.finance.pms.events.calculation.antlr.MyErrorReporter;
import com.finance.pms.events.calculation.antlr.OpsLexerDelegate;

@SuppressWarnings("all")
public class ParameterizedOperationsLexer extends Lexer {
	public static final int EOF=-1;
	public static final int T__17=17;
	public static final int T__18=18;
	public static final int T__19=19;
	public static final int COMMENT=4;
	public static final int Double=5;
	public static final int HistoricalData=6;
	public static final int LINE_COMMENT=7;
	public static final int MAType=8;
	public static final int MATypeToken=9;
	public static final int Nativeop=10;
	public static final int Number=11;
	public static final int OperationOutput=12;
	public static final int OutputSelector=13;
	public static final int StockOperation=14;
	public static final int Userop=15;
	public static final int WS=16;


	  private MyErrorReporter errorReporter;
	  private OpsLexerDelegate lexerDelegate;

	  public void setLexerDelegate(OpsLexerDelegate lexerDelegate) {
	      this.lexerDelegate = lexerDelegate;
	  }
	  
	  public OpsLexerDelegate getLexerDelegate() {
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

	  
	  private boolean runtimeNativeOpAhead() {
	     return lexerDelegate.runtimeNativeOpAhead();
	  }
	  private boolean runtimeUserOpAhead() {
	    return lexerDelegate.runtimeUserOpAhead();
	  }
	  
	  private boolean runtimeHistoryOpAhead() {
	    return lexerDelegate.runtimeHistoryOpAhead();
	  }
	  public boolean runtimeMATypeOpAhead() {
	     return lexerDelegate.runtimeMATypeOpAhead();
	  }

	  


	// delegates
	// delegators
	public Lexer[] getDelegates() {
		return new Lexer[] {};
	}

	public ParameterizedOperationsLexer() {} 
	public ParameterizedOperationsLexer(CharStream input) {
		this(input, new RecognizerSharedState());
	}
	public ParameterizedOperationsLexer(CharStream input, RecognizerSharedState state) {
		super(input,state);
	}
	@Override public String getGrammarFileName() { return "/home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g"; }

	// $ANTLR start "T__17"
	public final void mT__17() throws RecognitionException {
		try {
			int _type = T__17;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:58:7: ( '(' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:58:9: '('
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
	// $ANTLR end "T__17"

	// $ANTLR start "T__18"
	public final void mT__18() throws RecognitionException {
		try {
			int _type = T__18;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:59:7: ( ')' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:59:9: ')'
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
	// $ANTLR end "T__18"

	// $ANTLR start "T__19"
	public final void mT__19() throws RecognitionException {
		try {
			int _type = T__19;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:60:7: ( ',' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:60:9: ','
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
	// $ANTLR end "T__19"

	// $ANTLR start "HistoricalData"
	public final void mHistoricalData() throws RecognitionException {
		try {
			int _type = HistoricalData;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:131:6: ({...}? => ( 'close' | 'open' | 'high' | 'low' | 'volume' ) )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:131:8: {...}? => ( 'close' | 'open' | 'high' | 'low' | 'volume' )
			{
			if ( !((runtimeHistoryOpAhead())) ) {
				throw new FailedPredicateException(input, "HistoricalData", "runtimeHistoryOpAhead()");
			}
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:131:38: ( 'close' | 'open' | 'high' | 'low' | 'volume' )
			int alt1=5;
			switch ( input.LA(1) ) {
			case 'c':
				{
				alt1=1;
				}
				break;
			case 'o':
				{
				alt1=2;
				}
				break;
			case 'h':
				{
				alt1=3;
				}
				break;
			case 'l':
				{
				alt1=4;
				}
				break;
			case 'v':
				{
				alt1=5;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 1, 0, input);
				throw nvae;
			}
			switch (alt1) {
				case 1 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:131:39: 'close'
					{
					match("close"); 

					}
					break;
				case 2 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:131:49: 'open'
					{
					match("open"); 

					}
					break;
				case 3 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:131:58: 'high'
					{
					match("high"); 

					}
					break;
				case 4 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:131:67: 'low'
					{
					match("low"); 

					}
					break;
				case 5 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:131:76: 'volume'
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

	// $ANTLR start "MATypeToken"
	public final void mMATypeToken() throws RecognitionException {
		try {
			int _type = MATypeToken;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:134:6: ({...}? => ( 'Sma' | 'Ema' | 'Wma' | 'Dema' | 'Tema' | 'Trima' | 'Kama' | 'Mama' | 'T3' ) )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:134:8: {...}? => ( 'Sma' | 'Ema' | 'Wma' | 'Dema' | 'Tema' | 'Trima' | 'Kama' | 'Mama' | 'T3' )
			{
			if ( !((runtimeMATypeOpAhead())) ) {
				throw new FailedPredicateException(input, "MATypeToken", "runtimeMATypeOpAhead()");
			}
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:134:37: ( 'Sma' | 'Ema' | 'Wma' | 'Dema' | 'Tema' | 'Trima' | 'Kama' | 'Mama' | 'T3' )
			int alt2=9;
			switch ( input.LA(1) ) {
			case 'S':
				{
				alt2=1;
				}
				break;
			case 'E':
				{
				alt2=2;
				}
				break;
			case 'W':
				{
				alt2=3;
				}
				break;
			case 'D':
				{
				alt2=4;
				}
				break;
			case 'T':
				{
				switch ( input.LA(2) ) {
				case 'e':
					{
					alt2=5;
					}
					break;
				case 'r':
					{
					alt2=6;
					}
					break;
				case '3':
					{
					alt2=9;
					}
					break;
				default:
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 2, 5, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}
				}
				break;
			case 'K':
				{
				alt2=7;
				}
				break;
			case 'M':
				{
				alt2=8;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 2, 0, input);
				throw nvae;
			}
			switch (alt2) {
				case 1 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:134:38: 'Sma'
					{
					match("Sma"); 

					}
					break;
				case 2 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:134:44: 'Ema'
					{
					match("Ema"); 

					}
					break;
				case 3 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:134:50: 'Wma'
					{
					match("Wma"); 

					}
					break;
				case 4 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:134:56: 'Dema'
					{
					match("Dema"); 

					}
					break;
				case 5 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:134:63: 'Tema'
					{
					match("Tema"); 

					}
					break;
				case 6 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:134:71: 'Trima'
					{
					match("Trima"); 

					}
					break;
				case 7 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:134:80: 'Kama'
					{
					match("Kama"); 

					}
					break;
				case 8 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:134:88: 'Mama'
					{
					match("Mama"); 

					}
					break;
				case 9 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:134:96: 'T3'
					{
					match("T3"); 

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
	// $ANTLR end "MATypeToken"

	// $ANTLR start "Nativeop"
	public final void mNativeop() throws RecognitionException {
		try {
			int _type = Nativeop;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:137:6: ({...}? => ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )+ )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:137:8: {...}? => ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )+
			{
			if ( !((runtimeNativeOpAhead())) ) {
				throw new FailedPredicateException(input, "Nativeop", "runtimeNativeOpAhead()");
			}
			if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:137:65: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )+
			int cnt3=0;
			loop3:
			while (true) {
				int alt3=2;
				int LA3_0 = input.LA(1);
				if ( ((LA3_0 >= '0' && LA3_0 <= '9')||(LA3_0 >= 'A' && LA3_0 <= 'Z')||LA3_0=='_'||(LA3_0 >= 'a' && LA3_0 <= 'z')) ) {
					alt3=1;
				}

				switch (alt3) {
				case 1 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:
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
					if ( cnt3 >= 1 ) break loop3;
					EarlyExitException eee = new EarlyExitException(3, input);
					throw eee;
				}
				cnt3++;
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "Nativeop"

	// $ANTLR start "Userop"
	public final void mUserop() throws RecognitionException {
		try {
			int _type = Userop;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:140:6: ({...}? => ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )+ )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:140:8: {...}? => ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )+
			{
			if ( !((runtimeUserOpAhead())) ) {
				throw new FailedPredicateException(input, "Userop", "runtimeUserOpAhead()");
			}
			if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:140:63: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )+
			int cnt4=0;
			loop4:
			while (true) {
				int alt4=2;
				int LA4_0 = input.LA(1);
				if ( ((LA4_0 >= '0' && LA4_0 <= '9')||(LA4_0 >= 'A' && LA4_0 <= 'Z')||LA4_0=='_'||(LA4_0 >= 'a' && LA4_0 <= 'z')) ) {
					alt4=1;
				}

				switch (alt4) {
				case 1 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:
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
					if ( cnt4 >= 1 ) break loop4;
					EarlyExitException eee = new EarlyExitException(4, input);
					throw eee;
				}
				cnt4++;
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "Userop"

	// $ANTLR start "Number"
	public final void mNumber() throws RecognitionException {
		try {
			int _type = Number;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:143:6: ( ( '0' .. '9' )+ ( '.' ( '0' .. '9' )+ )? )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:143:8: ( '0' .. '9' )+ ( '.' ( '0' .. '9' )+ )?
			{
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:143:8: ( '0' .. '9' )+
			int cnt5=0;
			loop5:
			while (true) {
				int alt5=2;
				int LA5_0 = input.LA(1);
				if ( ((LA5_0 >= '0' && LA5_0 <= '9')) ) {
					alt5=1;
				}

				switch (alt5) {
				case 1 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:
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
					if ( cnt5 >= 1 ) break loop5;
					EarlyExitException eee = new EarlyExitException(5, input);
					throw eee;
				}
				cnt5++;
			}

			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:143:20: ( '.' ( '0' .. '9' )+ )?
			int alt7=2;
			int LA7_0 = input.LA(1);
			if ( (LA7_0=='.') ) {
				alt7=1;
			}
			switch (alt7) {
				case 1 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:143:21: '.' ( '0' .. '9' )+
					{
					match('.'); 
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:143:25: ( '0' .. '9' )+
					int cnt6=0;
					loop6:
					while (true) {
						int alt6=2;
						int LA6_0 = input.LA(1);
						if ( ((LA6_0 >= '0' && LA6_0 <= '9')) ) {
							alt6=1;
						}

						switch (alt6) {
						case 1 :
							// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:
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
							if ( cnt6 >= 1 ) break loop6;
							EarlyExitException eee = new EarlyExitException(6, input);
							throw eee;
						}
						cnt6++;
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

	// $ANTLR start "OutputSelector"
	public final void mOutputSelector() throws RecognitionException {
		try {
			int _type = OutputSelector;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:146:6: ( ':' ( 'a' .. 'z' | 'A' .. 'Z' )+ )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:146:8: ':' ( 'a' .. 'z' | 'A' .. 'Z' )+
			{
			match(':'); 
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:146:12: ( 'a' .. 'z' | 'A' .. 'Z' )+
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
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:
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
	// $ANTLR end "OutputSelector"

	// $ANTLR start "WS"
	public final void mWS() throws RecognitionException {
		try {
			int _type = WS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:151:5: ( ( ' ' | '\\r' | '\\t' | '\\u000C' | '\\n' ) )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:151:7: ( ' ' | '\\r' | '\\t' | '\\u000C' | '\\n' )
			{
			if ( (input.LA(1) >= '\t' && input.LA(1) <= '\n')||(input.LA(1) >= '\f' && input.LA(1) <= '\r')||input.LA(1)==' ' ) {
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
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:154:5: ( '/*' ( . )* '*/' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:154:7: '/*' ( . )* '*/'
			{
			match("/*"); 

			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:154:12: ( . )*
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
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:154:12: .
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
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:157:5: ( '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:157:7: '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n'
			{
			match("//"); 

			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:157:12: (~ ( '\\n' | '\\r' ) )*
			loop10:
			while (true) {
				int alt10=2;
				int LA10_0 = input.LA(1);
				if ( ((LA10_0 >= '\u0000' && LA10_0 <= '\t')||(LA10_0 >= '\u000B' && LA10_0 <= '\f')||(LA10_0 >= '\u000E' && LA10_0 <= '\uFFFF')) ) {
					alt10=1;
				}

				switch (alt10) {
				case 1 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:
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

			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:157:26: ( '\\r' )?
			int alt11=2;
			int LA11_0 = input.LA(1);
			if ( (LA11_0=='\r') ) {
				alt11=1;
			}
			switch (alt11) {
				case 1 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:157:26: '\\r'
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
		// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:8: ( T__17 | T__18 | T__19 | HistoricalData | MATypeToken | Nativeop | Userop | Number | OutputSelector | WS | COMMENT | LINE_COMMENT )
		int alt12=12;
		alt12 = dfa12.predict(input);
		switch (alt12) {
			case 1 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:10: T__17
				{
				mT__17(); 

				}
				break;
			case 2 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:16: T__18
				{
				mT__18(); 

				}
				break;
			case 3 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:22: T__19
				{
				mT__19(); 

				}
				break;
			case 4 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:28: HistoricalData
				{
				mHistoricalData(); 

				}
				break;
			case 5 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:43: MATypeToken
				{
				mMATypeToken(); 

				}
				break;
			case 6 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:55: Nativeop
				{
				mNativeop(); 

				}
				break;
			case 7 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:64: Userop
				{
				mUserop(); 

				}
				break;
			case 8 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:71: Number
				{
				mNumber(); 

				}
				break;
			case 9 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:78: OutputSelector
				{
				mOutputSelector(); 

				}
				break;
			case 10 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:93: WS
				{
				mWS(); 

				}
				break;
			case 11 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:96: COMMENT
				{
				mCOMMENT(); 

				}
				break;
			case 12 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:104: LINE_COMMENT
				{
				mLINE_COMMENT(); 

				}
				break;

		}
	}


	protected DFA12 dfa12 = new DFA12(this);
	static final String DFA12_eotS =
		"\25\uffff\14\47\1\62\2\47\2\uffff\1\47\1\uffff\2\47\1\72\1\47\3\62\3\47"+
		"\1\uffff\3\47\2\uffff\2\72\1\uffff\1\47\2\62\1\47\1\uffff\2\62\1\72\1"+
		"\uffff\1\47\1\62\1\72";
	static final String DFA12_eofS =
		"\107\uffff";
	static final String DFA12_minS =
		"\1\11\3\uffff\15\60\3\uffff\1\52\17\60\2\uffff\1\60\1\0\12\60\1\0\3\60"+
		"\2\uffff\2\60\1\0\4\60\1\uffff\3\60\1\uffff\3\60";
	static final String DFA12_maxS =
		"\1\172\3\uffff\15\172\3\uffff\1\57\17\172\2\uffff\1\172\1\0\12\172\1\0"+
		"\3\172\2\uffff\2\172\1\0\4\172\1\uffff\3\172\1\uffff\3\172";
	static final String DFA12_acceptS =
		"\1\uffff\1\1\1\2\1\3\15\uffff\1\10\1\11\1\12\20\uffff\1\13\1\14\20\uffff"+
		"\1\6\1\7\7\uffff\1\5\3\uffff\1\4\3\uffff";
	static final String DFA12_specialS =
		"\1\32\3\uffff\1\36\1\42\1\25\1\24\1\20\1\7\1\5\1\4\1\2\1\35\1\1\1\0\1"+
		"\71\4\uffff\1\17\1\3\1\12\1\14\1\6\1\10\1\43\1\37\1\33\1\30\1\70\1\66"+
		"\1\40\1\61\1\56\2\uffff\1\23\1\22\1\11\1\16\1\41\1\47\1\52\1\53\1\54\1"+
		"\31\1\67\1\65\1\13\1\57\1\55\1\21\2\uffff\1\44\1\45\1\15\1\46\1\60\1\62"+
		"\1\63\1\uffff\1\27\1\34\1\50\1\uffff\1\51\1\64\1\26}>";
	static final String[] DFA12_transitionS = {
			"\2\23\1\uffff\2\23\22\uffff\1\23\7\uffff\1\1\1\2\2\uffff\1\3\2\uffff"+
			"\1\24\12\21\1\22\6\uffff\3\20\1\14\1\12\5\20\1\16\1\20\1\17\5\20\1\11"+
			"\1\15\2\20\1\13\3\20\4\uffff\1\20\1\uffff\2\20\1\4\4\20\1\6\3\20\1\7"+
			"\2\20\1\5\6\20\1\10\4\20",
			"",
			"",
			"",
			"\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\13\26\1\25\16\26",
			"\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\17\26\1\27\12\26",
			"\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\10\26\1\30\21\26",
			"\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\16\26\1\31\13\26",
			"\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\16\26\1\32\13\26",
			"\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\14\26\1\33\15\26",
			"\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\14\26\1\34\15\26",
			"\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\14\26\1\35\15\26",
			"\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\4\26\1\36\25\26",
			"\3\26\1\41\6\26\7\uffff\32\26\4\uffff\1\26\1\uffff\4\26\1\37\14\26\1"+
			"\40\10\26",
			"\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\1\42\31\26",
			"\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\1\43\31\26",
			"\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26",
			"",
			"",
			"",
			"\1\44\4\uffff\1\45",
			"\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\16\26\1\46\13\26",
			"\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26",
			"\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\4\26\1\50\25\26",
			"\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\6\26\1\51\23\26",
			"\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\26\26\1\52\3\26",
			"\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\13\26\1\53\16\26",
			"\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\1\54\31\26",
			"\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\1\55\31\26",
			"\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\1\56\31\26",
			"\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\14\26\1\57\15\26",
			"\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\14\26\1\60\15\26",
			"\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\10\26\1\61\21\26",
			"\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26",
			"\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\14\26\1\63\15\26",
			"\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\14\26\1\64\15\26",
			"",
			"",
			"\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\22\26\1\65\7\26",
			"\1\uffff",
			"\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\15\26\1\70\14\26",
			"\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\7\26\1\71\22\26",
			"\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26",
			"\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\24\26\1\73\5\26",
			"\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26",
			"\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26",
			"\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26",
			"\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\1\74\31\26",
			"\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\1\75\31\26",
			"\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\14\26\1\76\15\26",
			"\1\uffff",
			"\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\1\100\31\26",
			"\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\1\101\31\26",
			"\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\4\26\1\102\25\26",
			"",
			"",
			"\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26",
			"\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26",
			"\1\uffff",
			"\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\14\26\1\104\15\26",
			"\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26",
			"\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26",
			"\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\1\105\31\26",
			"",
			"\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26",
			"\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26",
			"\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26",
			"",
			"\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\4\26\1\106\25\26",
			"\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26",
			"\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26"
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
			return "1:1: Tokens : ( T__17 | T__18 | T__19 | HistoricalData | MATypeToken | Nativeop | Userop | Number | OutputSelector | WS | COMMENT | LINE_COMMENT );";
		}
		@Override
		public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
			IntStream input = _input;
			int _s = s;
			switch ( s ) {
					case 0 : 
						int LA12_15 = input.LA(1);
						 
						int index12_15 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_15=='a') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 35;}
						else if ( ((LA12_15 >= '0' && LA12_15 <= '9')||(LA12_15 >= 'A' && LA12_15 <= 'Z')||LA12_15=='_'||(LA12_15 >= 'b' && LA12_15 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 22;}
						 
						input.seek(index12_15);
						if ( s>=0 ) return s;
						break;

					case 1 : 
						int LA12_14 = input.LA(1);
						 
						int index12_14 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_14=='a') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 34;}
						else if ( ((LA12_14 >= '0' && LA12_14 <= '9')||(LA12_14 >= 'A' && LA12_14 <= 'Z')||LA12_14=='_'||(LA12_14 >= 'b' && LA12_14 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 22;}
						 
						input.seek(index12_14);
						if ( s>=0 ) return s;
						break;

					case 2 : 
						int LA12_12 = input.LA(1);
						 
						int index12_12 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_12=='e') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 30;}
						else if ( ((LA12_12 >= '0' && LA12_12 <= '9')||(LA12_12 >= 'A' && LA12_12 <= 'Z')||LA12_12=='_'||(LA12_12 >= 'a' && LA12_12 <= 'd')||(LA12_12 >= 'f' && LA12_12 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 22;}
						 
						input.seek(index12_12);
						if ( s>=0 ) return s;
						break;

					case 3 : 
						int LA12_22 = input.LA(1);
						 
						int index12_22 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA12_22 >= '0' && LA12_22 <= '9')||(LA12_22 >= 'A' && LA12_22 <= 'Z')||LA12_22=='_'||(LA12_22 >= 'a' && LA12_22 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 22;}
						else s = 39;
						 
						input.seek(index12_22);
						if ( s>=0 ) return s;
						break;

					case 4 : 
						int LA12_11 = input.LA(1);
						 
						int index12_11 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_11=='m') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 29;}
						else if ( ((LA12_11 >= '0' && LA12_11 <= '9')||(LA12_11 >= 'A' && LA12_11 <= 'Z')||LA12_11=='_'||(LA12_11 >= 'a' && LA12_11 <= 'l')||(LA12_11 >= 'n' && LA12_11 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 22;}
						 
						input.seek(index12_11);
						if ( s>=0 ) return s;
						break;

					case 5 : 
						int LA12_10 = input.LA(1);
						 
						int index12_10 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_10=='m') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 28;}
						else if ( ((LA12_10 >= '0' && LA12_10 <= '9')||(LA12_10 >= 'A' && LA12_10 <= 'Z')||LA12_10=='_'||(LA12_10 >= 'a' && LA12_10 <= 'l')||(LA12_10 >= 'n' && LA12_10 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 22;}
						 
						input.seek(index12_10);
						if ( s>=0 ) return s;
						break;

					case 6 : 
						int LA12_25 = input.LA(1);
						 
						int index12_25 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_25=='w') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 42;}
						else if ( ((LA12_25 >= '0' && LA12_25 <= '9')||(LA12_25 >= 'A' && LA12_25 <= 'Z')||LA12_25=='_'||(LA12_25 >= 'a' && LA12_25 <= 'v')||(LA12_25 >= 'x' && LA12_25 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 22;}
						else s = 39;
						 
						input.seek(index12_25);
						if ( s>=0 ) return s;
						break;

					case 7 : 
						int LA12_9 = input.LA(1);
						 
						int index12_9 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_9=='m') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 27;}
						else if ( ((LA12_9 >= '0' && LA12_9 <= '9')||(LA12_9 >= 'A' && LA12_9 <= 'Z')||LA12_9=='_'||(LA12_9 >= 'a' && LA12_9 <= 'l')||(LA12_9 >= 'n' && LA12_9 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 22;}
						 
						input.seek(index12_9);
						if ( s>=0 ) return s;
						break;

					case 8 : 
						int LA12_26 = input.LA(1);
						 
						int index12_26 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_26=='l') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 43;}
						else if ( ((LA12_26 >= '0' && LA12_26 <= '9')||(LA12_26 >= 'A' && LA12_26 <= 'Z')||LA12_26=='_'||(LA12_26 >= 'a' && LA12_26 <= 'k')||(LA12_26 >= 'm' && LA12_26 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 22;}
						else s = 39;
						 
						input.seek(index12_26);
						if ( s>=0 ) return s;
						break;

					case 9 : 
						int LA12_40 = input.LA(1);
						 
						int index12_40 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_40=='n') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 56;}
						else if ( ((LA12_40 >= '0' && LA12_40 <= '9')||(LA12_40 >= 'A' && LA12_40 <= 'Z')||LA12_40=='_'||(LA12_40 >= 'a' && LA12_40 <= 'm')||(LA12_40 >= 'o' && LA12_40 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 22;}
						else s = 39;
						 
						input.seek(index12_40);
						if ( s>=0 ) return s;
						break;

					case 10 : 
						int LA12_23 = input.LA(1);
						 
						int index12_23 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_23=='e') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 40;}
						else if ( ((LA12_23 >= '0' && LA12_23 <= '9')||(LA12_23 >= 'A' && LA12_23 <= 'Z')||LA12_23=='_'||(LA12_23 >= 'a' && LA12_23 <= 'd')||(LA12_23 >= 'f' && LA12_23 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 22;}
						else s = 39;
						 
						input.seek(index12_23);
						if ( s>=0 ) return s;
						break;

					case 11 : 
						int LA12_50 = input.LA(1);
						 
						int index12_50 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeMATypeOpAhead())) ) {s = 63;}
						else if ( ((runtimeNativeOpAhead())) ) {s = 54;}
						else if ( ((runtimeUserOpAhead())) ) {s = 55;}
						 
						input.seek(index12_50);
						if ( s>=0 ) return s;
						break;

					case 12 : 
						int LA12_24 = input.LA(1);
						 
						int index12_24 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_24=='g') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 41;}
						else if ( ((LA12_24 >= '0' && LA12_24 <= '9')||(LA12_24 >= 'A' && LA12_24 <= 'Z')||LA12_24=='_'||(LA12_24 >= 'a' && LA12_24 <= 'f')||(LA12_24 >= 'h' && LA12_24 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 22;}
						else s = 39;
						 
						input.seek(index12_24);
						if ( s>=0 ) return s;
						break;

					case 13 : 
						int LA12_58 = input.LA(1);
						 
						int index12_58 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeHistoryOpAhead())) ) {s = 67;}
						else if ( ((runtimeNativeOpAhead())) ) {s = 54;}
						else if ( ((runtimeUserOpAhead())) ) {s = 55;}
						 
						input.seek(index12_58);
						if ( s>=0 ) return s;
						break;

					case 14 : 
						int LA12_41 = input.LA(1);
						 
						int index12_41 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_41=='h') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 57;}
						else if ( ((LA12_41 >= '0' && LA12_41 <= '9')||(LA12_41 >= 'A' && LA12_41 <= 'Z')||LA12_41=='_'||(LA12_41 >= 'a' && LA12_41 <= 'g')||(LA12_41 >= 'i' && LA12_41 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 22;}
						else s = 39;
						 
						input.seek(index12_41);
						if ( s>=0 ) return s;
						break;

					case 15 : 
						int LA12_21 = input.LA(1);
						 
						int index12_21 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_21=='o') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 38;}
						else if ( ((LA12_21 >= '0' && LA12_21 <= '9')||(LA12_21 >= 'A' && LA12_21 <= 'Z')||LA12_21=='_'||(LA12_21 >= 'a' && LA12_21 <= 'n')||(LA12_21 >= 'p' && LA12_21 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 22;}
						else s = 39;
						 
						input.seek(index12_21);
						if ( s>=0 ) return s;
						break;

					case 16 : 
						int LA12_8 = input.LA(1);
						 
						int index12_8 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_8=='o') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 26;}
						else if ( ((LA12_8 >= '0' && LA12_8 <= '9')||(LA12_8 >= 'A' && LA12_8 <= 'Z')||LA12_8=='_'||(LA12_8 >= 'a' && LA12_8 <= 'n')||(LA12_8 >= 'p' && LA12_8 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 22;}
						 
						input.seek(index12_8);
						if ( s>=0 ) return s;
						break;

					case 17 : 
						int LA12_53 = input.LA(1);
						 
						int index12_53 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_53=='e') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 66;}
						else if ( ((LA12_53 >= '0' && LA12_53 <= '9')||(LA12_53 >= 'A' && LA12_53 <= 'Z')||LA12_53=='_'||(LA12_53 >= 'a' && LA12_53 <= 'd')||(LA12_53 >= 'f' && LA12_53 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 22;}
						else s = 39;
						 
						input.seek(index12_53);
						if ( s>=0 ) return s;
						break;

					case 18 : 
						int LA12_39 = input.LA(1);
						 
						int index12_39 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeNativeOpAhead())) ) {s = 54;}
						else if ( ((runtimeUserOpAhead())) ) {s = 55;}
						 
						input.seek(index12_39);
						if ( s>=0 ) return s;
						break;

					case 19 : 
						int LA12_38 = input.LA(1);
						 
						int index12_38 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_38=='s') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 53;}
						else if ( ((LA12_38 >= '0' && LA12_38 <= '9')||(LA12_38 >= 'A' && LA12_38 <= 'Z')||LA12_38=='_'||(LA12_38 >= 'a' && LA12_38 <= 'r')||(LA12_38 >= 't' && LA12_38 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 22;}
						else s = 39;
						 
						input.seek(index12_38);
						if ( s>=0 ) return s;
						break;

					case 20 : 
						int LA12_7 = input.LA(1);
						 
						int index12_7 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_7=='o') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 25;}
						else if ( ((LA12_7 >= '0' && LA12_7 <= '9')||(LA12_7 >= 'A' && LA12_7 <= 'Z')||LA12_7=='_'||(LA12_7 >= 'a' && LA12_7 <= 'n')||(LA12_7 >= 'p' && LA12_7 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 22;}
						 
						input.seek(index12_7);
						if ( s>=0 ) return s;
						break;

					case 21 : 
						int LA12_6 = input.LA(1);
						 
						int index12_6 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_6=='i') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 24;}
						else if ( ((LA12_6 >= '0' && LA12_6 <= '9')||(LA12_6 >= 'A' && LA12_6 <= 'Z')||LA12_6=='_'||(LA12_6 >= 'a' && LA12_6 <= 'h')||(LA12_6 >= 'j' && LA12_6 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 22;}
						 
						input.seek(index12_6);
						if ( s>=0 ) return s;
						break;

					case 22 : 
						int LA12_70 = input.LA(1);
						 
						int index12_70 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA12_70 >= '0' && LA12_70 <= '9')||(LA12_70 >= 'A' && LA12_70 <= 'Z')||LA12_70=='_'||(LA12_70 >= 'a' && LA12_70 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 22;}
						else s = 58;
						 
						input.seek(index12_70);
						if ( s>=0 ) return s;
						break;

					case 23 : 
						int LA12_64 = input.LA(1);
						 
						int index12_64 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA12_64 >= '0' && LA12_64 <= '9')||(LA12_64 >= 'A' && LA12_64 <= 'Z')||LA12_64=='_'||(LA12_64 >= 'a' && LA12_64 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 22;}
						else s = 50;
						 
						input.seek(index12_64);
						if ( s>=0 ) return s;
						break;

					case 24 : 
						int LA12_30 = input.LA(1);
						 
						int index12_30 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_30=='m') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 47;}
						else if ( ((LA12_30 >= '0' && LA12_30 <= '9')||(LA12_30 >= 'A' && LA12_30 <= 'Z')||LA12_30=='_'||(LA12_30 >= 'a' && LA12_30 <= 'l')||(LA12_30 >= 'n' && LA12_30 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 22;}
						else s = 39;
						 
						input.seek(index12_30);
						if ( s>=0 ) return s;
						break;

					case 25 : 
						int LA12_47 = input.LA(1);
						 
						int index12_47 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_47=='a') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 60;}
						else if ( ((LA12_47 >= '0' && LA12_47 <= '9')||(LA12_47 >= 'A' && LA12_47 <= 'Z')||LA12_47=='_'||(LA12_47 >= 'b' && LA12_47 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 22;}
						else s = 39;
						 
						input.seek(index12_47);
						if ( s>=0 ) return s;
						break;

					case 26 : 
						int LA12_0 = input.LA(1);
						 
						int index12_0 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_0=='(') ) {s = 1;}
						else if ( (LA12_0==')') ) {s = 2;}
						else if ( (LA12_0==',') ) {s = 3;}
						else if ( (LA12_0=='c') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 4;}
						else if ( (LA12_0=='o') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 5;}
						else if ( (LA12_0=='h') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 6;}
						else if ( (LA12_0=='l') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 7;}
						else if ( (LA12_0=='v') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 8;}
						else if ( (LA12_0=='S') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 9;}
						else if ( (LA12_0=='E') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 10;}
						else if ( (LA12_0=='W') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 11;}
						else if ( (LA12_0=='D') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 12;}
						else if ( (LA12_0=='T') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 13;}
						else if ( (LA12_0=='K') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 14;}
						else if ( (LA12_0=='M') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 15;}
						else if ( ((LA12_0 >= 'A' && LA12_0 <= 'C')||(LA12_0 >= 'F' && LA12_0 <= 'J')||LA12_0=='L'||(LA12_0 >= 'N' && LA12_0 <= 'R')||(LA12_0 >= 'U' && LA12_0 <= 'V')||(LA12_0 >= 'X' && LA12_0 <= 'Z')||LA12_0=='_'||(LA12_0 >= 'a' && LA12_0 <= 'b')||(LA12_0 >= 'd' && LA12_0 <= 'g')||(LA12_0 >= 'i' && LA12_0 <= 'k')||(LA12_0 >= 'm' && LA12_0 <= 'n')||(LA12_0 >= 'p' && LA12_0 <= 'u')||(LA12_0 >= 'w' && LA12_0 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 16;}
						else if ( ((LA12_0 >= '0' && LA12_0 <= '9')) ) {s = 17;}
						else if ( (LA12_0==':') ) {s = 18;}
						else if ( ((LA12_0 >= '\t' && LA12_0 <= '\n')||(LA12_0 >= '\f' && LA12_0 <= '\r')||LA12_0==' ') ) {s = 19;}
						else if ( (LA12_0=='/') ) {s = 20;}
						 
						input.seek(index12_0);
						if ( s>=0 ) return s;
						break;

					case 27 : 
						int LA12_29 = input.LA(1);
						 
						int index12_29 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_29=='a') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 46;}
						else if ( ((LA12_29 >= '0' && LA12_29 <= '9')||(LA12_29 >= 'A' && LA12_29 <= 'Z')||LA12_29=='_'||(LA12_29 >= 'b' && LA12_29 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 22;}
						else s = 39;
						 
						input.seek(index12_29);
						if ( s>=0 ) return s;
						break;

					case 28 : 
						int LA12_65 = input.LA(1);
						 
						int index12_65 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA12_65 >= '0' && LA12_65 <= '9')||(LA12_65 >= 'A' && LA12_65 <= 'Z')||LA12_65=='_'||(LA12_65 >= 'a' && LA12_65 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 22;}
						else s = 50;
						 
						input.seek(index12_65);
						if ( s>=0 ) return s;
						break;

					case 29 : 
						int LA12_13 = input.LA(1);
						 
						int index12_13 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_13=='e') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 31;}
						else if ( (LA12_13=='r') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 32;}
						else if ( (LA12_13=='3') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 33;}
						else if ( ((LA12_13 >= '0' && LA12_13 <= '2')||(LA12_13 >= '4' && LA12_13 <= '9')||(LA12_13 >= 'A' && LA12_13 <= 'Z')||LA12_13=='_'||(LA12_13 >= 'a' && LA12_13 <= 'd')||(LA12_13 >= 'f' && LA12_13 <= 'q')||(LA12_13 >= 's' && LA12_13 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 22;}
						 
						input.seek(index12_13);
						if ( s>=0 ) return s;
						break;

					case 30 : 
						int LA12_4 = input.LA(1);
						 
						int index12_4 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_4=='l') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 21;}
						else if ( ((LA12_4 >= '0' && LA12_4 <= '9')||(LA12_4 >= 'A' && LA12_4 <= 'Z')||LA12_4=='_'||(LA12_4 >= 'a' && LA12_4 <= 'k')||(LA12_4 >= 'm' && LA12_4 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 22;}
						 
						input.seek(index12_4);
						if ( s>=0 ) return s;
						break;

					case 31 : 
						int LA12_28 = input.LA(1);
						 
						int index12_28 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_28=='a') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 45;}
						else if ( ((LA12_28 >= '0' && LA12_28 <= '9')||(LA12_28 >= 'A' && LA12_28 <= 'Z')||LA12_28=='_'||(LA12_28 >= 'b' && LA12_28 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 22;}
						else s = 39;
						 
						input.seek(index12_28);
						if ( s>=0 ) return s;
						break;

					case 32 : 
						int LA12_33 = input.LA(1);
						 
						int index12_33 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA12_33 >= '0' && LA12_33 <= '9')||(LA12_33 >= 'A' && LA12_33 <= 'Z')||LA12_33=='_'||(LA12_33 >= 'a' && LA12_33 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 22;}
						else s = 50;
						 
						input.seek(index12_33);
						if ( s>=0 ) return s;
						break;

					case 33 : 
						int LA12_42 = input.LA(1);
						 
						int index12_42 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA12_42 >= '0' && LA12_42 <= '9')||(LA12_42 >= 'A' && LA12_42 <= 'Z')||LA12_42=='_'||(LA12_42 >= 'a' && LA12_42 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 22;}
						else s = 58;
						 
						input.seek(index12_42);
						if ( s>=0 ) return s;
						break;

					case 34 : 
						int LA12_5 = input.LA(1);
						 
						int index12_5 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_5=='p') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 23;}
						else if ( ((LA12_5 >= '0' && LA12_5 <= '9')||(LA12_5 >= 'A' && LA12_5 <= 'Z')||LA12_5=='_'||(LA12_5 >= 'a' && LA12_5 <= 'o')||(LA12_5 >= 'q' && LA12_5 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 22;}
						 
						input.seek(index12_5);
						if ( s>=0 ) return s;
						break;

					case 35 : 
						int LA12_27 = input.LA(1);
						 
						int index12_27 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_27=='a') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 44;}
						else if ( ((LA12_27 >= '0' && LA12_27 <= '9')||(LA12_27 >= 'A' && LA12_27 <= 'Z')||LA12_27=='_'||(LA12_27 >= 'b' && LA12_27 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 22;}
						else s = 39;
						 
						input.seek(index12_27);
						if ( s>=0 ) return s;
						break;

					case 36 : 
						int LA12_56 = input.LA(1);
						 
						int index12_56 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA12_56 >= '0' && LA12_56 <= '9')||(LA12_56 >= 'A' && LA12_56 <= 'Z')||LA12_56=='_'||(LA12_56 >= 'a' && LA12_56 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 22;}
						else s = 58;
						 
						input.seek(index12_56);
						if ( s>=0 ) return s;
						break;

					case 37 : 
						int LA12_57 = input.LA(1);
						 
						int index12_57 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA12_57 >= '0' && LA12_57 <= '9')||(LA12_57 >= 'A' && LA12_57 <= 'Z')||LA12_57=='_'||(LA12_57 >= 'a' && LA12_57 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 22;}
						else s = 58;
						 
						input.seek(index12_57);
						if ( s>=0 ) return s;
						break;

					case 38 : 
						int LA12_59 = input.LA(1);
						 
						int index12_59 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_59=='m') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 68;}
						else if ( ((LA12_59 >= '0' && LA12_59 <= '9')||(LA12_59 >= 'A' && LA12_59 <= 'Z')||LA12_59=='_'||(LA12_59 >= 'a' && LA12_59 <= 'l')||(LA12_59 >= 'n' && LA12_59 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 22;}
						else s = 39;
						 
						input.seek(index12_59);
						if ( s>=0 ) return s;
						break;

					case 39 : 
						int LA12_43 = input.LA(1);
						 
						int index12_43 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_43=='u') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 59;}
						else if ( ((LA12_43 >= '0' && LA12_43 <= '9')||(LA12_43 >= 'A' && LA12_43 <= 'Z')||LA12_43=='_'||(LA12_43 >= 'a' && LA12_43 <= 't')||(LA12_43 >= 'v' && LA12_43 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 22;}
						else s = 39;
						 
						input.seek(index12_43);
						if ( s>=0 ) return s;
						break;

					case 40 : 
						int LA12_66 = input.LA(1);
						 
						int index12_66 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA12_66 >= '0' && LA12_66 <= '9')||(LA12_66 >= 'A' && LA12_66 <= 'Z')||LA12_66=='_'||(LA12_66 >= 'a' && LA12_66 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 22;}
						else s = 58;
						 
						input.seek(index12_66);
						if ( s>=0 ) return s;
						break;

					case 41 : 
						int LA12_68 = input.LA(1);
						 
						int index12_68 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_68=='e') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 70;}
						else if ( ((LA12_68 >= '0' && LA12_68 <= '9')||(LA12_68 >= 'A' && LA12_68 <= 'Z')||LA12_68=='_'||(LA12_68 >= 'a' && LA12_68 <= 'd')||(LA12_68 >= 'f' && LA12_68 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 22;}
						else s = 39;
						 
						input.seek(index12_68);
						if ( s>=0 ) return s;
						break;

					case 42 : 
						int LA12_44 = input.LA(1);
						 
						int index12_44 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA12_44 >= '0' && LA12_44 <= '9')||(LA12_44 >= 'A' && LA12_44 <= 'Z')||LA12_44=='_'||(LA12_44 >= 'a' && LA12_44 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 22;}
						else s = 50;
						 
						input.seek(index12_44);
						if ( s>=0 ) return s;
						break;

					case 43 : 
						int LA12_45 = input.LA(1);
						 
						int index12_45 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA12_45 >= '0' && LA12_45 <= '9')||(LA12_45 >= 'A' && LA12_45 <= 'Z')||LA12_45=='_'||(LA12_45 >= 'a' && LA12_45 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 22;}
						else s = 50;
						 
						input.seek(index12_45);
						if ( s>=0 ) return s;
						break;

					case 44 : 
						int LA12_46 = input.LA(1);
						 
						int index12_46 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA12_46 >= '0' && LA12_46 <= '9')||(LA12_46 >= 'A' && LA12_46 <= 'Z')||LA12_46=='_'||(LA12_46 >= 'a' && LA12_46 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 22;}
						else s = 50;
						 
						input.seek(index12_46);
						if ( s>=0 ) return s;
						break;

					case 45 : 
						int LA12_52 = input.LA(1);
						 
						int index12_52 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_52=='a') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 65;}
						else if ( ((LA12_52 >= '0' && LA12_52 <= '9')||(LA12_52 >= 'A' && LA12_52 <= 'Z')||LA12_52=='_'||(LA12_52 >= 'b' && LA12_52 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 22;}
						else s = 39;
						 
						input.seek(index12_52);
						if ( s>=0 ) return s;
						break;

					case 46 : 
						int LA12_35 = input.LA(1);
						 
						int index12_35 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_35=='m') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 52;}
						else if ( ((LA12_35 >= '0' && LA12_35 <= '9')||(LA12_35 >= 'A' && LA12_35 <= 'Z')||LA12_35=='_'||(LA12_35 >= 'a' && LA12_35 <= 'l')||(LA12_35 >= 'n' && LA12_35 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 22;}
						else s = 39;
						 
						input.seek(index12_35);
						if ( s>=0 ) return s;
						break;

					case 47 : 
						int LA12_51 = input.LA(1);
						 
						int index12_51 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_51=='a') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 64;}
						else if ( ((LA12_51 >= '0' && LA12_51 <= '9')||(LA12_51 >= 'A' && LA12_51 <= 'Z')||LA12_51=='_'||(LA12_51 >= 'b' && LA12_51 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 22;}
						else s = 39;
						 
						input.seek(index12_51);
						if ( s>=0 ) return s;
						break;

					case 48 : 
						int LA12_60 = input.LA(1);
						 
						int index12_60 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA12_60 >= '0' && LA12_60 <= '9')||(LA12_60 >= 'A' && LA12_60 <= 'Z')||LA12_60=='_'||(LA12_60 >= 'a' && LA12_60 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 22;}
						else s = 50;
						 
						input.seek(index12_60);
						if ( s>=0 ) return s;
						break;

					case 49 : 
						int LA12_34 = input.LA(1);
						 
						int index12_34 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_34=='m') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 51;}
						else if ( ((LA12_34 >= '0' && LA12_34 <= '9')||(LA12_34 >= 'A' && LA12_34 <= 'Z')||LA12_34=='_'||(LA12_34 >= 'a' && LA12_34 <= 'l')||(LA12_34 >= 'n' && LA12_34 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 22;}
						else s = 39;
						 
						input.seek(index12_34);
						if ( s>=0 ) return s;
						break;

					case 50 : 
						int LA12_61 = input.LA(1);
						 
						int index12_61 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA12_61 >= '0' && LA12_61 <= '9')||(LA12_61 >= 'A' && LA12_61 <= 'Z')||LA12_61=='_'||(LA12_61 >= 'a' && LA12_61 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 22;}
						else s = 50;
						 
						input.seek(index12_61);
						if ( s>=0 ) return s;
						break;

					case 51 : 
						int LA12_62 = input.LA(1);
						 
						int index12_62 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_62=='a') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 69;}
						else if ( ((LA12_62 >= '0' && LA12_62 <= '9')||(LA12_62 >= 'A' && LA12_62 <= 'Z')||LA12_62=='_'||(LA12_62 >= 'b' && LA12_62 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 22;}
						else s = 39;
						 
						input.seek(index12_62);
						if ( s>=0 ) return s;
						break;

					case 52 : 
						int LA12_69 = input.LA(1);
						 
						int index12_69 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA12_69 >= '0' && LA12_69 <= '9')||(LA12_69 >= 'A' && LA12_69 <= 'Z')||LA12_69=='_'||(LA12_69 >= 'a' && LA12_69 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 22;}
						else s = 50;
						 
						input.seek(index12_69);
						if ( s>=0 ) return s;
						break;

					case 53 : 
						int LA12_49 = input.LA(1);
						 
						int index12_49 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_49=='m') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 62;}
						else if ( ((LA12_49 >= '0' && LA12_49 <= '9')||(LA12_49 >= 'A' && LA12_49 <= 'Z')||LA12_49=='_'||(LA12_49 >= 'a' && LA12_49 <= 'l')||(LA12_49 >= 'n' && LA12_49 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 22;}
						else s = 39;
						 
						input.seek(index12_49);
						if ( s>=0 ) return s;
						break;

					case 54 : 
						int LA12_32 = input.LA(1);
						 
						int index12_32 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_32=='i') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 49;}
						else if ( ((LA12_32 >= '0' && LA12_32 <= '9')||(LA12_32 >= 'A' && LA12_32 <= 'Z')||LA12_32=='_'||(LA12_32 >= 'a' && LA12_32 <= 'h')||(LA12_32 >= 'j' && LA12_32 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 22;}
						else s = 39;
						 
						input.seek(index12_32);
						if ( s>=0 ) return s;
						break;

					case 55 : 
						int LA12_48 = input.LA(1);
						 
						int index12_48 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_48=='a') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 61;}
						else if ( ((LA12_48 >= '0' && LA12_48 <= '9')||(LA12_48 >= 'A' && LA12_48 <= 'Z')||LA12_48=='_'||(LA12_48 >= 'b' && LA12_48 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 22;}
						else s = 39;
						 
						input.seek(index12_48);
						if ( s>=0 ) return s;
						break;

					case 56 : 
						int LA12_31 = input.LA(1);
						 
						int index12_31 = input.index();
						input.rewind();
						s = -1;
						if ( (LA12_31=='m') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 48;}
						else if ( ((LA12_31 >= '0' && LA12_31 <= '9')||(LA12_31 >= 'A' && LA12_31 <= 'Z')||LA12_31=='_'||(LA12_31 >= 'a' && LA12_31 <= 'l')||(LA12_31 >= 'n' && LA12_31 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 22;}
						else s = 39;
						 
						input.seek(index12_31);
						if ( s>=0 ) return s;
						break;

					case 57 : 
						int LA12_16 = input.LA(1);
						 
						int index12_16 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA12_16 >= '0' && LA12_16 <= '9')||(LA12_16 >= 'A' && LA12_16 <= 'Z')||LA12_16=='_'||(LA12_16 >= 'a' && LA12_16 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 22;}
						 
						input.seek(index12_16);
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
