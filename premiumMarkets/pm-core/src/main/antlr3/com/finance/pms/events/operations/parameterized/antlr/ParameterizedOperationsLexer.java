// $ANTLR 3.5 /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g 2013-06-23 15:36:56
 //lexer
    package com.finance.pms.events.operations.parameterized.antlr;
    import com.finance.pms.events.calculation.antlr.IErrorReporter;
    import com.finance.pms.events.calculation.antlr.MyErrorReporter;
    import com.finance.pms.events.calculation.antlr.OpsLexerDelegate;
    
 

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class ParameterizedOperationsLexer extends Lexer {
	public static final int EOF=-1;
	public static final int T__19=19;
	public static final int T__20=20;
	public static final int T__21=21;
	public static final int COMMENT=4;
	public static final int HistoricalData=5;
	public static final int LINE_COMMENT=6;
	public static final int MAType=7;
	public static final int MATypeToken=8;
	public static final int Nativeop=9;
	public static final int Number=10;
	public static final int NumberToken=11;
	public static final int OperationOutput=12;
	public static final int OutputSelector=13;
	public static final int StockOperation=14;
	public static final int String=15;
	public static final int StringToken=16;
	public static final int Userop=17;
	public static final int WS=18;


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

	// $ANTLR start "T__19"
	public final void mT__19() throws RecognitionException {
		try {
			int _type = T__19;
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
	// $ANTLR end "T__19"

	// $ANTLR start "T__20"
	public final void mT__20() throws RecognitionException {
		try {
			int _type = T__20;
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
	// $ANTLR end "T__20"

	// $ANTLR start "T__21"
	public final void mT__21() throws RecognitionException {
		try {
			int _type = T__21;
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
	// $ANTLR end "T__21"

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

	// $ANTLR start "NumberToken"
	public final void mNumberToken() throws RecognitionException {
		try {
			int _type = NumberToken;
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
	// $ANTLR end "NumberToken"

	// $ANTLR start "StringToken"
	public final void mStringToken() throws RecognitionException {
		try {
			int _type = StringToken;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:146:6: ( '\"' ( 'a' .. 'z' | 'A' .. 'Z' | '.' )+ '\"' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:146:8: '\"' ( 'a' .. 'z' | 'A' .. 'Z' | '.' )+ '\"'
			{
			match('\"'); 
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:146:12: ( 'a' .. 'z' | 'A' .. 'Z' | '.' )+
			int cnt8=0;
			loop8:
			while (true) {
				int alt8=2;
				int LA8_0 = input.LA(1);
				if ( (LA8_0=='.'||(LA8_0 >= 'A' && LA8_0 <= 'Z')||(LA8_0 >= 'a' && LA8_0 <= 'z')) ) {
					alt8=1;
				}

				switch (alt8) {
				case 1 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:
					{
					if ( input.LA(1)=='.'||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
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

	// $ANTLR start "OutputSelector"
	public final void mOutputSelector() throws RecognitionException {
		try {
			int _type = OutputSelector;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:149:6: ( ':' ( 'a' .. 'z' | 'A' .. 'Z' )+ )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:149:8: ':' ( 'a' .. 'z' | 'A' .. 'Z' )+
			{
			match(':'); 
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:149:12: ( 'a' .. 'z' | 'A' .. 'Z' )+
			int cnt9=0;
			loop9:
			while (true) {
				int alt9=2;
				int LA9_0 = input.LA(1);
				if ( ((LA9_0 >= 'A' && LA9_0 <= 'Z')||(LA9_0 >= 'a' && LA9_0 <= 'z')) ) {
					alt9=1;
				}

				switch (alt9) {
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
	// $ANTLR end "OutputSelector"

	// $ANTLR start "WS"
	public final void mWS() throws RecognitionException {
		try {
			int _type = WS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:154:5: ( ( ' ' | '\\r' | '\\t' | '\\u000C' | '\\n' ) )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:154:7: ( ' ' | '\\r' | '\\t' | '\\u000C' | '\\n' )
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
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:157:5: ( '/*' ( . )* '*/' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:157:7: '/*' ( . )* '*/'
			{
			match("/*"); 

			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:157:12: ( . )*
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
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:157:12: .
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
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:160:5: ( '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:160:7: '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n'
			{
			match("//"); 

			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:160:12: (~ ( '\\n' | '\\r' ) )*
			loop11:
			while (true) {
				int alt11=2;
				int LA11_0 = input.LA(1);
				if ( ((LA11_0 >= '\u0000' && LA11_0 <= '\t')||(LA11_0 >= '\u000B' && LA11_0 <= '\f')||(LA11_0 >= '\u000E' && LA11_0 <= '\uFFFF')) ) {
					alt11=1;
				}

				switch (alt11) {
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
					break loop11;
				}
			}

			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:160:26: ( '\\r' )?
			int alt12=2;
			int LA12_0 = input.LA(1);
			if ( (LA12_0=='\r') ) {
				alt12=1;
			}
			switch (alt12) {
				case 1 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:160:26: '\\r'
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
		// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:8: ( T__19 | T__20 | T__21 | HistoricalData | MATypeToken | Nativeop | Userop | NumberToken | StringToken | OutputSelector | WS | COMMENT | LINE_COMMENT )
		int alt13=13;
		alt13 = dfa13.predict(input);
		switch (alt13) {
			case 1 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:10: T__19
				{
				mT__19(); 

				}
				break;
			case 2 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:16: T__20
				{
				mT__20(); 

				}
				break;
			case 3 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:22: T__21
				{
				mT__21(); 

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
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:71: NumberToken
				{
				mNumberToken(); 

				}
				break;
			case 9 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:83: StringToken
				{
				mStringToken(); 

				}
				break;
			case 10 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:95: OutputSelector
				{
				mOutputSelector(); 

				}
				break;
			case 11 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:110: WS
				{
				mWS(); 

				}
				break;
			case 12 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:113: COMMENT
				{
				mCOMMENT(); 

				}
				break;
			case 13 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:121: LINE_COMMENT
				{
				mLINE_COMMENT(); 

				}
				break;

		}
	}


	protected DFA13 dfa13 = new DFA13(this);
	static final String DFA13_eotS =
		"\26\uffff\14\50\1\63\2\50\2\uffff\1\50\1\uffff\2\50\1\73\1\50\3\63\3\50"+
		"\1\uffff\3\50\2\uffff\2\73\1\uffff\1\50\2\63\1\50\1\uffff\2\63\1\73\1"+
		"\uffff\1\50\1\63\1\73";
	static final String DFA13_eofS =
		"\110\uffff";
	static final String DFA13_minS =
		"\1\11\3\uffff\15\60\4\uffff\1\52\17\60\2\uffff\1\60\1\0\12\60\1\0\3\60"+
		"\2\uffff\2\60\1\0\4\60\1\uffff\3\60\1\uffff\3\60";
	static final String DFA13_maxS =
		"\1\172\3\uffff\15\172\4\uffff\1\57\17\172\2\uffff\1\172\1\0\12\172\1\0"+
		"\3\172\2\uffff\2\172\1\0\4\172\1\uffff\3\172\1\uffff\3\172";
	static final String DFA13_acceptS =
		"\1\uffff\1\1\1\2\1\3\15\uffff\1\10\1\11\1\12\1\13\20\uffff\1\14\1\15\20"+
		"\uffff\1\6\1\7\7\uffff\1\5\3\uffff\1\4\3\uffff";
	static final String DFA13_specialS =
		"\1\27\3\uffff\1\42\1\37\1\33\1\30\1\25\1\17\1\14\1\12\1\7\1\47\1\71\1"+
		"\62\1\57\5\uffff\1\60\1\46\1\52\1\0\1\4\1\10\1\20\1\21\1\23\1\24\1\31"+
		"\1\35\1\70\1\41\1\43\2\uffff\1\55\1\16\1\50\1\2\1\66\1\5\1\3\1\1\1\22"+
		"\1\26\1\32\1\34\1\67\1\40\1\44\1\56\2\uffff\1\63\1\64\1\65\1\6\1\15\1"+
		"\13\1\36\1\uffff\1\51\1\53\1\61\1\uffff\1\11\1\54\1\45}>";
	static final String[] DFA13_transitionS = {
			"\2\24\1\uffff\2\24\22\uffff\1\24\1\uffff\1\22\5\uffff\1\1\1\2\2\uffff"+
			"\1\3\2\uffff\1\25\12\21\1\23\6\uffff\3\20\1\14\1\12\5\20\1\16\1\20\1"+
			"\17\5\20\1\11\1\15\2\20\1\13\3\20\4\uffff\1\20\1\uffff\2\20\1\4\4\20"+
			"\1\6\3\20\1\7\2\20\1\5\6\20\1\10\4\20",
			"",
			"",
			"",
			"\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\13\27\1\26\16\27",
			"\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\17\27\1\30\12\27",
			"\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\10\27\1\31\21\27",
			"\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\16\27\1\32\13\27",
			"\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\16\27\1\33\13\27",
			"\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\14\27\1\34\15\27",
			"\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\14\27\1\35\15\27",
			"\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\14\27\1\36\15\27",
			"\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\4\27\1\37\25\27",
			"\3\27\1\42\6\27\7\uffff\32\27\4\uffff\1\27\1\uffff\4\27\1\40\14\27\1"+
			"\41\10\27",
			"\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\1\43\31\27",
			"\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\1\44\31\27",
			"\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
			"",
			"",
			"",
			"",
			"\1\45\4\uffff\1\46",
			"\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\16\27\1\47\13\27",
			"\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
			"\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\4\27\1\51\25\27",
			"\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\6\27\1\52\23\27",
			"\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\26\27\1\53\3\27",
			"\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\13\27\1\54\16\27",
			"\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\1\55\31\27",
			"\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\1\56\31\27",
			"\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\1\57\31\27",
			"\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\14\27\1\60\15\27",
			"\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\14\27\1\61\15\27",
			"\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\10\27\1\62\21\27",
			"\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
			"\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\14\27\1\64\15\27",
			"\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\14\27\1\65\15\27",
			"",
			"",
			"\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\22\27\1\66\7\27",
			"\1\uffff",
			"\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\15\27\1\71\14\27",
			"\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\7\27\1\72\22\27",
			"\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
			"\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\24\27\1\74\5\27",
			"\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
			"\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
			"\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
			"\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\1\75\31\27",
			"\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\1\76\31\27",
			"\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\14\27\1\77\15\27",
			"\1\uffff",
			"\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\1\101\31\27",
			"\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\1\102\31\27",
			"\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\4\27\1\103\25\27",
			"",
			"",
			"\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
			"\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
			"\1\uffff",
			"\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\14\27\1\105\15\27",
			"\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
			"\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
			"\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\1\106\31\27",
			"",
			"\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
			"\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
			"\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
			"",
			"\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\4\27\1\107\25\27",
			"\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
			"\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27"
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
			return "1:1: Tokens : ( T__19 | T__20 | T__21 | HistoricalData | MATypeToken | Nativeop | Userop | NumberToken | StringToken | OutputSelector | WS | COMMENT | LINE_COMMENT );";
		}
		@Override
		public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
			IntStream input = _input;
			int _s = s;
			switch ( s ) {
					case 0 : 
						int LA13_25 = input.LA(1);
						 
						int index13_25 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_25=='g') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 42;}
						else if ( ((LA13_25 >= '0' && LA13_25 <= '9')||(LA13_25 >= 'A' && LA13_25 <= 'Z')||LA13_25=='_'||(LA13_25 >= 'a' && LA13_25 <= 'f')||(LA13_25 >= 'h' && LA13_25 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 40;
						 
						input.seek(index13_25);
						if ( s>=0 ) return s;
						break;

					case 1 : 
						int LA13_46 = input.LA(1);
						 
						int index13_46 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_46 >= '0' && LA13_46 <= '9')||(LA13_46 >= 'A' && LA13_46 <= 'Z')||LA13_46=='_'||(LA13_46 >= 'a' && LA13_46 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 51;
						 
						input.seek(index13_46);
						if ( s>=0 ) return s;
						break;

					case 2 : 
						int LA13_42 = input.LA(1);
						 
						int index13_42 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_42=='h') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 58;}
						else if ( ((LA13_42 >= '0' && LA13_42 <= '9')||(LA13_42 >= 'A' && LA13_42 <= 'Z')||LA13_42=='_'||(LA13_42 >= 'a' && LA13_42 <= 'g')||(LA13_42 >= 'i' && LA13_42 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 40;
						 
						input.seek(index13_42);
						if ( s>=0 ) return s;
						break;

					case 3 : 
						int LA13_45 = input.LA(1);
						 
						int index13_45 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_45 >= '0' && LA13_45 <= '9')||(LA13_45 >= 'A' && LA13_45 <= 'Z')||LA13_45=='_'||(LA13_45 >= 'a' && LA13_45 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 51;
						 
						input.seek(index13_45);
						if ( s>=0 ) return s;
						break;

					case 4 : 
						int LA13_26 = input.LA(1);
						 
						int index13_26 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_26=='w') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 43;}
						else if ( ((LA13_26 >= '0' && LA13_26 <= '9')||(LA13_26 >= 'A' && LA13_26 <= 'Z')||LA13_26=='_'||(LA13_26 >= 'a' && LA13_26 <= 'v')||(LA13_26 >= 'x' && LA13_26 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 40;
						 
						input.seek(index13_26);
						if ( s>=0 ) return s;
						break;

					case 5 : 
						int LA13_44 = input.LA(1);
						 
						int index13_44 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_44=='u') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 60;}
						else if ( ((LA13_44 >= '0' && LA13_44 <= '9')||(LA13_44 >= 'A' && LA13_44 <= 'Z')||LA13_44=='_'||(LA13_44 >= 'a' && LA13_44 <= 't')||(LA13_44 >= 'v' && LA13_44 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 40;
						 
						input.seek(index13_44);
						if ( s>=0 ) return s;
						break;

					case 6 : 
						int LA13_60 = input.LA(1);
						 
						int index13_60 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_60=='m') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 69;}
						else if ( ((LA13_60 >= '0' && LA13_60 <= '9')||(LA13_60 >= 'A' && LA13_60 <= 'Z')||LA13_60=='_'||(LA13_60 >= 'a' && LA13_60 <= 'l')||(LA13_60 >= 'n' && LA13_60 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 40;
						 
						input.seek(index13_60);
						if ( s>=0 ) return s;
						break;

					case 7 : 
						int LA13_12 = input.LA(1);
						 
						int index13_12 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_12=='e') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 31;}
						else if ( ((LA13_12 >= '0' && LA13_12 <= '9')||(LA13_12 >= 'A' && LA13_12 <= 'Z')||LA13_12=='_'||(LA13_12 >= 'a' && LA13_12 <= 'd')||(LA13_12 >= 'f' && LA13_12 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						 
						input.seek(index13_12);
						if ( s>=0 ) return s;
						break;

					case 8 : 
						int LA13_27 = input.LA(1);
						 
						int index13_27 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_27=='l') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 44;}
						else if ( ((LA13_27 >= '0' && LA13_27 <= '9')||(LA13_27 >= 'A' && LA13_27 <= 'Z')||LA13_27=='_'||(LA13_27 >= 'a' && LA13_27 <= 'k')||(LA13_27 >= 'm' && LA13_27 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 40;
						 
						input.seek(index13_27);
						if ( s>=0 ) return s;
						break;

					case 9 : 
						int LA13_69 = input.LA(1);
						 
						int index13_69 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_69=='e') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 71;}
						else if ( ((LA13_69 >= '0' && LA13_69 <= '9')||(LA13_69 >= 'A' && LA13_69 <= 'Z')||LA13_69=='_'||(LA13_69 >= 'a' && LA13_69 <= 'd')||(LA13_69 >= 'f' && LA13_69 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 40;
						 
						input.seek(index13_69);
						if ( s>=0 ) return s;
						break;

					case 10 : 
						int LA13_11 = input.LA(1);
						 
						int index13_11 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_11=='m') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 30;}
						else if ( ((LA13_11 >= '0' && LA13_11 <= '9')||(LA13_11 >= 'A' && LA13_11 <= 'Z')||LA13_11=='_'||(LA13_11 >= 'a' && LA13_11 <= 'l')||(LA13_11 >= 'n' && LA13_11 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						 
						input.seek(index13_11);
						if ( s>=0 ) return s;
						break;

					case 11 : 
						int LA13_62 = input.LA(1);
						 
						int index13_62 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_62 >= '0' && LA13_62 <= '9')||(LA13_62 >= 'A' && LA13_62 <= 'Z')||LA13_62=='_'||(LA13_62 >= 'a' && LA13_62 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 51;
						 
						input.seek(index13_62);
						if ( s>=0 ) return s;
						break;

					case 12 : 
						int LA13_10 = input.LA(1);
						 
						int index13_10 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_10=='m') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 29;}
						else if ( ((LA13_10 >= '0' && LA13_10 <= '9')||(LA13_10 >= 'A' && LA13_10 <= 'Z')||LA13_10=='_'||(LA13_10 >= 'a' && LA13_10 <= 'l')||(LA13_10 >= 'n' && LA13_10 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						 
						input.seek(index13_10);
						if ( s>=0 ) return s;
						break;

					case 13 : 
						int LA13_61 = input.LA(1);
						 
						int index13_61 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_61 >= '0' && LA13_61 <= '9')||(LA13_61 >= 'A' && LA13_61 <= 'Z')||LA13_61=='_'||(LA13_61 >= 'a' && LA13_61 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 51;
						 
						input.seek(index13_61);
						if ( s>=0 ) return s;
						break;

					case 14 : 
						int LA13_40 = input.LA(1);
						 
						int index13_40 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeNativeOpAhead())) ) {s = 55;}
						else if ( ((runtimeUserOpAhead())) ) {s = 56;}
						 
						input.seek(index13_40);
						if ( s>=0 ) return s;
						break;

					case 15 : 
						int LA13_9 = input.LA(1);
						 
						int index13_9 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_9=='m') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 28;}
						else if ( ((LA13_9 >= '0' && LA13_9 <= '9')||(LA13_9 >= 'A' && LA13_9 <= 'Z')||LA13_9=='_'||(LA13_9 >= 'a' && LA13_9 <= 'l')||(LA13_9 >= 'n' && LA13_9 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						 
						input.seek(index13_9);
						if ( s>=0 ) return s;
						break;

					case 16 : 
						int LA13_28 = input.LA(1);
						 
						int index13_28 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_28=='a') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 45;}
						else if ( ((LA13_28 >= '0' && LA13_28 <= '9')||(LA13_28 >= 'A' && LA13_28 <= 'Z')||LA13_28=='_'||(LA13_28 >= 'b' && LA13_28 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 40;
						 
						input.seek(index13_28);
						if ( s>=0 ) return s;
						break;

					case 17 : 
						int LA13_29 = input.LA(1);
						 
						int index13_29 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_29=='a') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 46;}
						else if ( ((LA13_29 >= '0' && LA13_29 <= '9')||(LA13_29 >= 'A' && LA13_29 <= 'Z')||LA13_29=='_'||(LA13_29 >= 'b' && LA13_29 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 40;
						 
						input.seek(index13_29);
						if ( s>=0 ) return s;
						break;

					case 18 : 
						int LA13_47 = input.LA(1);
						 
						int index13_47 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_47 >= '0' && LA13_47 <= '9')||(LA13_47 >= 'A' && LA13_47 <= 'Z')||LA13_47=='_'||(LA13_47 >= 'a' && LA13_47 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 51;
						 
						input.seek(index13_47);
						if ( s>=0 ) return s;
						break;

					case 19 : 
						int LA13_30 = input.LA(1);
						 
						int index13_30 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_30=='a') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 47;}
						else if ( ((LA13_30 >= '0' && LA13_30 <= '9')||(LA13_30 >= 'A' && LA13_30 <= 'Z')||LA13_30=='_'||(LA13_30 >= 'b' && LA13_30 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 40;
						 
						input.seek(index13_30);
						if ( s>=0 ) return s;
						break;

					case 20 : 
						int LA13_31 = input.LA(1);
						 
						int index13_31 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_31=='m') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 48;}
						else if ( ((LA13_31 >= '0' && LA13_31 <= '9')||(LA13_31 >= 'A' && LA13_31 <= 'Z')||LA13_31=='_'||(LA13_31 >= 'a' && LA13_31 <= 'l')||(LA13_31 >= 'n' && LA13_31 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 40;
						 
						input.seek(index13_31);
						if ( s>=0 ) return s;
						break;

					case 21 : 
						int LA13_8 = input.LA(1);
						 
						int index13_8 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_8=='o') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 27;}
						else if ( ((LA13_8 >= '0' && LA13_8 <= '9')||(LA13_8 >= 'A' && LA13_8 <= 'Z')||LA13_8=='_'||(LA13_8 >= 'a' && LA13_8 <= 'n')||(LA13_8 >= 'p' && LA13_8 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						 
						input.seek(index13_8);
						if ( s>=0 ) return s;
						break;

					case 22 : 
						int LA13_48 = input.LA(1);
						 
						int index13_48 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_48=='a') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 61;}
						else if ( ((LA13_48 >= '0' && LA13_48 <= '9')||(LA13_48 >= 'A' && LA13_48 <= 'Z')||LA13_48=='_'||(LA13_48 >= 'b' && LA13_48 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 40;
						 
						input.seek(index13_48);
						if ( s>=0 ) return s;
						break;

					case 23 : 
						int LA13_0 = input.LA(1);
						 
						int index13_0 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_0=='(') ) {s = 1;}
						else if ( (LA13_0==')') ) {s = 2;}
						else if ( (LA13_0==',') ) {s = 3;}
						else if ( (LA13_0=='c') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 4;}
						else if ( (LA13_0=='o') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 5;}
						else if ( (LA13_0=='h') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 6;}
						else if ( (LA13_0=='l') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 7;}
						else if ( (LA13_0=='v') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 8;}
						else if ( (LA13_0=='S') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 9;}
						else if ( (LA13_0=='E') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 10;}
						else if ( (LA13_0=='W') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 11;}
						else if ( (LA13_0=='D') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 12;}
						else if ( (LA13_0=='T') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 13;}
						else if ( (LA13_0=='K') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 14;}
						else if ( (LA13_0=='M') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 15;}
						else if ( ((LA13_0 >= 'A' && LA13_0 <= 'C')||(LA13_0 >= 'F' && LA13_0 <= 'J')||LA13_0=='L'||(LA13_0 >= 'N' && LA13_0 <= 'R')||(LA13_0 >= 'U' && LA13_0 <= 'V')||(LA13_0 >= 'X' && LA13_0 <= 'Z')||LA13_0=='_'||(LA13_0 >= 'a' && LA13_0 <= 'b')||(LA13_0 >= 'd' && LA13_0 <= 'g')||(LA13_0 >= 'i' && LA13_0 <= 'k')||(LA13_0 >= 'm' && LA13_0 <= 'n')||(LA13_0 >= 'p' && LA13_0 <= 'u')||(LA13_0 >= 'w' && LA13_0 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 16;}
						else if ( ((LA13_0 >= '0' && LA13_0 <= '9')) ) {s = 17;}
						else if ( (LA13_0=='\"') ) {s = 18;}
						else if ( (LA13_0==':') ) {s = 19;}
						else if ( ((LA13_0 >= '\t' && LA13_0 <= '\n')||(LA13_0 >= '\f' && LA13_0 <= '\r')||LA13_0==' ') ) {s = 20;}
						else if ( (LA13_0=='/') ) {s = 21;}
						 
						input.seek(index13_0);
						if ( s>=0 ) return s;
						break;

					case 24 : 
						int LA13_7 = input.LA(1);
						 
						int index13_7 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_7=='o') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 26;}
						else if ( ((LA13_7 >= '0' && LA13_7 <= '9')||(LA13_7 >= 'A' && LA13_7 <= 'Z')||LA13_7=='_'||(LA13_7 >= 'a' && LA13_7 <= 'n')||(LA13_7 >= 'p' && LA13_7 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						 
						input.seek(index13_7);
						if ( s>=0 ) return s;
						break;

					case 25 : 
						int LA13_32 = input.LA(1);
						 
						int index13_32 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_32=='m') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 49;}
						else if ( ((LA13_32 >= '0' && LA13_32 <= '9')||(LA13_32 >= 'A' && LA13_32 <= 'Z')||LA13_32=='_'||(LA13_32 >= 'a' && LA13_32 <= 'l')||(LA13_32 >= 'n' && LA13_32 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 40;
						 
						input.seek(index13_32);
						if ( s>=0 ) return s;
						break;

					case 26 : 
						int LA13_49 = input.LA(1);
						 
						int index13_49 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_49=='a') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 62;}
						else if ( ((LA13_49 >= '0' && LA13_49 <= '9')||(LA13_49 >= 'A' && LA13_49 <= 'Z')||LA13_49=='_'||(LA13_49 >= 'b' && LA13_49 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 40;
						 
						input.seek(index13_49);
						if ( s>=0 ) return s;
						break;

					case 27 : 
						int LA13_6 = input.LA(1);
						 
						int index13_6 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_6=='i') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 25;}
						else if ( ((LA13_6 >= '0' && LA13_6 <= '9')||(LA13_6 >= 'A' && LA13_6 <= 'Z')||LA13_6=='_'||(LA13_6 >= 'a' && LA13_6 <= 'h')||(LA13_6 >= 'j' && LA13_6 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						 
						input.seek(index13_6);
						if ( s>=0 ) return s;
						break;

					case 28 : 
						int LA13_50 = input.LA(1);
						 
						int index13_50 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_50=='m') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 63;}
						else if ( ((LA13_50 >= '0' && LA13_50 <= '9')||(LA13_50 >= 'A' && LA13_50 <= 'Z')||LA13_50=='_'||(LA13_50 >= 'a' && LA13_50 <= 'l')||(LA13_50 >= 'n' && LA13_50 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 40;
						 
						input.seek(index13_50);
						if ( s>=0 ) return s;
						break;

					case 29 : 
						int LA13_33 = input.LA(1);
						 
						int index13_33 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_33=='i') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 50;}
						else if ( ((LA13_33 >= '0' && LA13_33 <= '9')||(LA13_33 >= 'A' && LA13_33 <= 'Z')||LA13_33=='_'||(LA13_33 >= 'a' && LA13_33 <= 'h')||(LA13_33 >= 'j' && LA13_33 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 40;
						 
						input.seek(index13_33);
						if ( s>=0 ) return s;
						break;

					case 30 : 
						int LA13_63 = input.LA(1);
						 
						int index13_63 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_63=='a') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 70;}
						else if ( ((LA13_63 >= '0' && LA13_63 <= '9')||(LA13_63 >= 'A' && LA13_63 <= 'Z')||LA13_63=='_'||(LA13_63 >= 'b' && LA13_63 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 40;
						 
						input.seek(index13_63);
						if ( s>=0 ) return s;
						break;

					case 31 : 
						int LA13_5 = input.LA(1);
						 
						int index13_5 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_5=='p') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 24;}
						else if ( ((LA13_5 >= '0' && LA13_5 <= '9')||(LA13_5 >= 'A' && LA13_5 <= 'Z')||LA13_5=='_'||(LA13_5 >= 'a' && LA13_5 <= 'o')||(LA13_5 >= 'q' && LA13_5 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						 
						input.seek(index13_5);
						if ( s>=0 ) return s;
						break;

					case 32 : 
						int LA13_52 = input.LA(1);
						 
						int index13_52 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_52=='a') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 65;}
						else if ( ((LA13_52 >= '0' && LA13_52 <= '9')||(LA13_52 >= 'A' && LA13_52 <= 'Z')||LA13_52=='_'||(LA13_52 >= 'b' && LA13_52 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 40;
						 
						input.seek(index13_52);
						if ( s>=0 ) return s;
						break;

					case 33 : 
						int LA13_35 = input.LA(1);
						 
						int index13_35 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_35=='m') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 52;}
						else if ( ((LA13_35 >= '0' && LA13_35 <= '9')||(LA13_35 >= 'A' && LA13_35 <= 'Z')||LA13_35=='_'||(LA13_35 >= 'a' && LA13_35 <= 'l')||(LA13_35 >= 'n' && LA13_35 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 40;
						 
						input.seek(index13_35);
						if ( s>=0 ) return s;
						break;

					case 34 : 
						int LA13_4 = input.LA(1);
						 
						int index13_4 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_4=='l') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 22;}
						else if ( ((LA13_4 >= '0' && LA13_4 <= '9')||(LA13_4 >= 'A' && LA13_4 <= 'Z')||LA13_4=='_'||(LA13_4 >= 'a' && LA13_4 <= 'k')||(LA13_4 >= 'm' && LA13_4 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						 
						input.seek(index13_4);
						if ( s>=0 ) return s;
						break;

					case 35 : 
						int LA13_36 = input.LA(1);
						 
						int index13_36 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_36=='m') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 53;}
						else if ( ((LA13_36 >= '0' && LA13_36 <= '9')||(LA13_36 >= 'A' && LA13_36 <= 'Z')||LA13_36=='_'||(LA13_36 >= 'a' && LA13_36 <= 'l')||(LA13_36 >= 'n' && LA13_36 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 40;
						 
						input.seek(index13_36);
						if ( s>=0 ) return s;
						break;

					case 36 : 
						int LA13_53 = input.LA(1);
						 
						int index13_53 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_53=='a') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 66;}
						else if ( ((LA13_53 >= '0' && LA13_53 <= '9')||(LA13_53 >= 'A' && LA13_53 <= 'Z')||LA13_53=='_'||(LA13_53 >= 'b' && LA13_53 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 40;
						 
						input.seek(index13_53);
						if ( s>=0 ) return s;
						break;

					case 37 : 
						int LA13_71 = input.LA(1);
						 
						int index13_71 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_71 >= '0' && LA13_71 <= '9')||(LA13_71 >= 'A' && LA13_71 <= 'Z')||LA13_71=='_'||(LA13_71 >= 'a' && LA13_71 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 59;
						 
						input.seek(index13_71);
						if ( s>=0 ) return s;
						break;

					case 38 : 
						int LA13_23 = input.LA(1);
						 
						int index13_23 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_23 >= '0' && LA13_23 <= '9')||(LA13_23 >= 'A' && LA13_23 <= 'Z')||LA13_23=='_'||(LA13_23 >= 'a' && LA13_23 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 40;
						 
						input.seek(index13_23);
						if ( s>=0 ) return s;
						break;

					case 39 : 
						int LA13_13 = input.LA(1);
						 
						int index13_13 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_13=='e') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 32;}
						else if ( (LA13_13=='r') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 33;}
						else if ( (LA13_13=='3') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 34;}
						else if ( ((LA13_13 >= '0' && LA13_13 <= '2')||(LA13_13 >= '4' && LA13_13 <= '9')||(LA13_13 >= 'A' && LA13_13 <= 'Z')||LA13_13=='_'||(LA13_13 >= 'a' && LA13_13 <= 'd')||(LA13_13 >= 'f' && LA13_13 <= 'q')||(LA13_13 >= 's' && LA13_13 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						 
						input.seek(index13_13);
						if ( s>=0 ) return s;
						break;

					case 40 : 
						int LA13_41 = input.LA(1);
						 
						int index13_41 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_41=='n') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 57;}
						else if ( ((LA13_41 >= '0' && LA13_41 <= '9')||(LA13_41 >= 'A' && LA13_41 <= 'Z')||LA13_41=='_'||(LA13_41 >= 'a' && LA13_41 <= 'm')||(LA13_41 >= 'o' && LA13_41 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 40;
						 
						input.seek(index13_41);
						if ( s>=0 ) return s;
						break;

					case 41 : 
						int LA13_65 = input.LA(1);
						 
						int index13_65 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_65 >= '0' && LA13_65 <= '9')||(LA13_65 >= 'A' && LA13_65 <= 'Z')||LA13_65=='_'||(LA13_65 >= 'a' && LA13_65 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 51;
						 
						input.seek(index13_65);
						if ( s>=0 ) return s;
						break;

					case 42 : 
						int LA13_24 = input.LA(1);
						 
						int index13_24 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_24=='e') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 41;}
						else if ( ((LA13_24 >= '0' && LA13_24 <= '9')||(LA13_24 >= 'A' && LA13_24 <= 'Z')||LA13_24=='_'||(LA13_24 >= 'a' && LA13_24 <= 'd')||(LA13_24 >= 'f' && LA13_24 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 40;
						 
						input.seek(index13_24);
						if ( s>=0 ) return s;
						break;

					case 43 : 
						int LA13_66 = input.LA(1);
						 
						int index13_66 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_66 >= '0' && LA13_66 <= '9')||(LA13_66 >= 'A' && LA13_66 <= 'Z')||LA13_66=='_'||(LA13_66 >= 'a' && LA13_66 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 51;
						 
						input.seek(index13_66);
						if ( s>=0 ) return s;
						break;

					case 44 : 
						int LA13_70 = input.LA(1);
						 
						int index13_70 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_70 >= '0' && LA13_70 <= '9')||(LA13_70 >= 'A' && LA13_70 <= 'Z')||LA13_70=='_'||(LA13_70 >= 'a' && LA13_70 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 51;
						 
						input.seek(index13_70);
						if ( s>=0 ) return s;
						break;

					case 45 : 
						int LA13_39 = input.LA(1);
						 
						int index13_39 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_39=='s') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 54;}
						else if ( ((LA13_39 >= '0' && LA13_39 <= '9')||(LA13_39 >= 'A' && LA13_39 <= 'Z')||LA13_39=='_'||(LA13_39 >= 'a' && LA13_39 <= 'r')||(LA13_39 >= 't' && LA13_39 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 40;
						 
						input.seek(index13_39);
						if ( s>=0 ) return s;
						break;

					case 46 : 
						int LA13_54 = input.LA(1);
						 
						int index13_54 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_54=='e') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 67;}
						else if ( ((LA13_54 >= '0' && LA13_54 <= '9')||(LA13_54 >= 'A' && LA13_54 <= 'Z')||LA13_54=='_'||(LA13_54 >= 'a' && LA13_54 <= 'd')||(LA13_54 >= 'f' && LA13_54 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 40;
						 
						input.seek(index13_54);
						if ( s>=0 ) return s;
						break;

					case 47 : 
						int LA13_16 = input.LA(1);
						 
						int index13_16 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_16 >= '0' && LA13_16 <= '9')||(LA13_16 >= 'A' && LA13_16 <= 'Z')||LA13_16=='_'||(LA13_16 >= 'a' && LA13_16 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						 
						input.seek(index13_16);
						if ( s>=0 ) return s;
						break;

					case 48 : 
						int LA13_22 = input.LA(1);
						 
						int index13_22 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_22=='o') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 39;}
						else if ( ((LA13_22 >= '0' && LA13_22 <= '9')||(LA13_22 >= 'A' && LA13_22 <= 'Z')||LA13_22=='_'||(LA13_22 >= 'a' && LA13_22 <= 'n')||(LA13_22 >= 'p' && LA13_22 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 40;
						 
						input.seek(index13_22);
						if ( s>=0 ) return s;
						break;

					case 49 : 
						int LA13_67 = input.LA(1);
						 
						int index13_67 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_67 >= '0' && LA13_67 <= '9')||(LA13_67 >= 'A' && LA13_67 <= 'Z')||LA13_67=='_'||(LA13_67 >= 'a' && LA13_67 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 59;
						 
						input.seek(index13_67);
						if ( s>=0 ) return s;
						break;

					case 50 : 
						int LA13_15 = input.LA(1);
						 
						int index13_15 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_15=='a') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 36;}
						else if ( ((LA13_15 >= '0' && LA13_15 <= '9')||(LA13_15 >= 'A' && LA13_15 <= 'Z')||LA13_15=='_'||(LA13_15 >= 'b' && LA13_15 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						 
						input.seek(index13_15);
						if ( s>=0 ) return s;
						break;

					case 51 : 
						int LA13_57 = input.LA(1);
						 
						int index13_57 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_57 >= '0' && LA13_57 <= '9')||(LA13_57 >= 'A' && LA13_57 <= 'Z')||LA13_57=='_'||(LA13_57 >= 'a' && LA13_57 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 59;
						 
						input.seek(index13_57);
						if ( s>=0 ) return s;
						break;

					case 52 : 
						int LA13_58 = input.LA(1);
						 
						int index13_58 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_58 >= '0' && LA13_58 <= '9')||(LA13_58 >= 'A' && LA13_58 <= 'Z')||LA13_58=='_'||(LA13_58 >= 'a' && LA13_58 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 59;
						 
						input.seek(index13_58);
						if ( s>=0 ) return s;
						break;

					case 53 : 
						int LA13_59 = input.LA(1);
						 
						int index13_59 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeHistoryOpAhead())) ) {s = 68;}
						else if ( ((runtimeNativeOpAhead())) ) {s = 55;}
						else if ( ((runtimeUserOpAhead())) ) {s = 56;}
						 
						input.seek(index13_59);
						if ( s>=0 ) return s;
						break;

					case 54 : 
						int LA13_43 = input.LA(1);
						 
						int index13_43 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_43 >= '0' && LA13_43 <= '9')||(LA13_43 >= 'A' && LA13_43 <= 'Z')||LA13_43=='_'||(LA13_43 >= 'a' && LA13_43 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 59;
						 
						input.seek(index13_43);
						if ( s>=0 ) return s;
						break;

					case 55 : 
						int LA13_51 = input.LA(1);
						 
						int index13_51 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeMATypeOpAhead())) ) {s = 64;}
						else if ( ((runtimeNativeOpAhead())) ) {s = 55;}
						else if ( ((runtimeUserOpAhead())) ) {s = 56;}
						 
						input.seek(index13_51);
						if ( s>=0 ) return s;
						break;

					case 56 : 
						int LA13_34 = input.LA(1);
						 
						int index13_34 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_34 >= '0' && LA13_34 <= '9')||(LA13_34 >= 'A' && LA13_34 <= 'Z')||LA13_34=='_'||(LA13_34 >= 'a' && LA13_34 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 51;
						 
						input.seek(index13_34);
						if ( s>=0 ) return s;
						break;

					case 57 : 
						int LA13_14 = input.LA(1);
						 
						int index13_14 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_14=='a') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 35;}
						else if ( ((LA13_14 >= '0' && LA13_14 <= '9')||(LA13_14 >= 'A' && LA13_14 <= 'Z')||LA13_14=='_'||(LA13_14 >= 'b' && LA13_14 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						 
						input.seek(index13_14);
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
