// $ANTLR 3.5 /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g 2014-01-23 02:08:26
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
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:143:6: ( ( '-' )? ( '0' .. '9' )+ ( '.' ( '0' .. '9' )+ )? )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:143:8: ( '-' )? ( '0' .. '9' )+ ( '.' ( '0' .. '9' )+ )?
			{
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:143:8: ( '-' )?
			int alt5=2;
			int LA5_0 = input.LA(1);
			if ( (LA5_0=='-') ) {
				alt5=1;
			}
			switch (alt5) {
				case 1 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:143:9: '-'
					{
					match('-'); 
					}
					break;

			}

			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:143:15: ( '0' .. '9' )+
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

			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:143:27: ( '.' ( '0' .. '9' )+ )?
			int alt8=2;
			int LA8_0 = input.LA(1);
			if ( (LA8_0=='.') ) {
				alt8=1;
			}
			switch (alt8) {
				case 1 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:143:28: '.' ( '0' .. '9' )+
					{
					match('.'); 
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:143:32: ( '0' .. '9' )+
					int cnt7=0;
					loop7:
					while (true) {
						int alt7=2;
						int LA7_0 = input.LA(1);
						if ( ((LA7_0 >= '0' && LA7_0 <= '9')) ) {
							alt7=1;
						}

						switch (alt7) {
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
							if ( cnt7 >= 1 ) break loop7;
							EarlyExitException eee = new EarlyExitException(7, input);
							throw eee;
						}
						cnt7++;
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
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:146:6: ( '\"' ( 'a' .. 'z' | 'A' .. 'Z' | '.' | '_' )+ '\"' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:146:8: '\"' ( 'a' .. 'z' | 'A' .. 'Z' | '.' | '_' )+ '\"'
			{
			match('\"'); 
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:146:12: ( 'a' .. 'z' | 'A' .. 'Z' | '.' | '_' )+
			int cnt9=0;
			loop9:
			while (true) {
				int alt9=2;
				int LA9_0 = input.LA(1);
				if ( (LA9_0=='.'||(LA9_0 >= 'A' && LA9_0 <= 'Z')||LA9_0=='_'||(LA9_0 >= 'a' && LA9_0 <= 'z')) ) {
					alt9=1;
				}

				switch (alt9) {
				case 1 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:
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
					if ( cnt9 >= 1 ) break loop9;
					EarlyExitException eee = new EarlyExitException(9, input);
					throw eee;
				}
				cnt9++;
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
			int cnt10=0;
			loop10:
			while (true) {
				int alt10=2;
				int LA10_0 = input.LA(1);
				if ( ((LA10_0 >= 'A' && LA10_0 <= 'Z')||(LA10_0 >= 'a' && LA10_0 <= 'z')) ) {
					alt10=1;
				}

				switch (alt10) {
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
					if ( cnt10 >= 1 ) break loop10;
					EarlyExitException eee = new EarlyExitException(10, input);
					throw eee;
				}
				cnt10++;
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
			loop11:
			while (true) {
				int alt11=2;
				int LA11_0 = input.LA(1);
				if ( (LA11_0=='*') ) {
					int LA11_1 = input.LA(2);
					if ( (LA11_1=='/') ) {
						alt11=2;
					}
					else if ( ((LA11_1 >= '\u0000' && LA11_1 <= '.')||(LA11_1 >= '0' && LA11_1 <= '\uFFFF')) ) {
						alt11=1;
					}

				}
				else if ( ((LA11_0 >= '\u0000' && LA11_0 <= ')')||(LA11_0 >= '+' && LA11_0 <= '\uFFFF')) ) {
					alt11=1;
				}

				switch (alt11) {
				case 1 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:157:12: .
					{
					matchAny(); 
					}
					break;

				default :
					break loop11;
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
			loop12:
			while (true) {
				int alt12=2;
				int LA12_0 = input.LA(1);
				if ( ((LA12_0 >= '\u0000' && LA12_0 <= '\t')||(LA12_0 >= '\u000B' && LA12_0 <= '\f')||(LA12_0 >= '\u000E' && LA12_0 <= '\uFFFF')) ) {
					alt12=1;
				}

				switch (alt12) {
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
					break loop12;
				}
			}

			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:160:26: ( '\\r' )?
			int alt13=2;
			int LA13_0 = input.LA(1);
			if ( (LA13_0=='\r') ) {
				alt13=1;
			}
			switch (alt13) {
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
		int alt14=13;
		alt14 = dfa14.predict(input);
		switch (alt14) {
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


	protected DFA14 dfa14 = new DFA14(this);
	static final String DFA14_eotS =
		"\26\uffff\14\50\1\63\2\50\2\uffff\1\50\1\uffff\2\50\1\73\1\50\3\63\3\50"+
		"\1\uffff\3\50\2\uffff\2\73\1\uffff\1\50\2\63\1\50\1\uffff\2\63\1\73\1"+
		"\uffff\1\50\1\63\1\73";
	static final String DFA14_eofS =
		"\110\uffff";
	static final String DFA14_minS =
		"\1\11\3\uffff\15\60\4\uffff\1\52\17\60\2\uffff\1\60\1\0\12\60\1\0\3\60"+
		"\2\uffff\2\60\1\0\4\60\1\uffff\3\60\1\uffff\3\60";
	static final String DFA14_maxS =
		"\1\172\3\uffff\15\172\4\uffff\1\57\17\172\2\uffff\1\172\1\0\12\172\1\0"+
		"\3\172\2\uffff\2\172\1\0\4\172\1\uffff\3\172\1\uffff\3\172";
	static final String DFA14_acceptS =
		"\1\uffff\1\1\1\2\1\3\15\uffff\1\10\1\11\1\12\1\13\20\uffff\1\14\1\15\20"+
		"\uffff\1\6\1\7\7\uffff\1\5\3\uffff\1\4\3\uffff";
	static final String DFA14_specialS =
		"\1\30\3\uffff\1\32\1\27\1\23\1\22\1\21\1\11\1\6\1\5\1\2\1\57\1\71\1\65"+
		"\1\64\5\uffff\1\3\1\37\1\4\1\12\1\13\1\17\1\24\1\26\1\31\1\34\1\35\1\41"+
		"\1\44\1\50\1\46\2\uffff\1\0\1\14\1\7\1\10\1\56\1\16\1\60\1\61\1\67\1\33"+
		"\1\43\1\42\1\62\1\51\1\55\1\1\2\uffff\1\53\1\54\1\63\1\15\1\70\1\66\1"+
		"\36\1\uffff\1\52\1\40\1\47\1\uffff\1\20\1\45\1\25}>";
	static final String[] DFA14_transitionS = {
			"\2\24\1\uffff\2\24\22\uffff\1\24\1\uffff\1\22\5\uffff\1\1\1\2\2\uffff"+
			"\1\3\1\21\1\uffff\1\25\12\21\1\23\6\uffff\3\20\1\14\1\12\5\20\1\16\1"+
			"\20\1\17\5\20\1\11\1\15\2\20\1\13\3\20\4\uffff\1\20\1\uffff\2\20\1\4"+
			"\4\20\1\6\3\20\1\7\2\20\1\5\6\20\1\10\4\20",
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

	static final short[] DFA14_eot = DFA.unpackEncodedString(DFA14_eotS);
	static final short[] DFA14_eof = DFA.unpackEncodedString(DFA14_eofS);
	static final char[] DFA14_min = DFA.unpackEncodedStringToUnsignedChars(DFA14_minS);
	static final char[] DFA14_max = DFA.unpackEncodedStringToUnsignedChars(DFA14_maxS);
	static final short[] DFA14_accept = DFA.unpackEncodedString(DFA14_acceptS);
	static final short[] DFA14_special = DFA.unpackEncodedString(DFA14_specialS);
	static final short[][] DFA14_transition;

	static {
		int numStates = DFA14_transitionS.length;
		DFA14_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA14_transition[i] = DFA.unpackEncodedString(DFA14_transitionS[i]);
		}
	}

	protected class DFA14 extends DFA {

		public DFA14(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 14;
			this.eot = DFA14_eot;
			this.eof = DFA14_eof;
			this.min = DFA14_min;
			this.max = DFA14_max;
			this.accept = DFA14_accept;
			this.special = DFA14_special;
			this.transition = DFA14_transition;
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
						int LA14_39 = input.LA(1);
						 
						int index14_39 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_39=='s') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 54;}
						else if ( ((LA14_39 >= '0' && LA14_39 <= '9')||(LA14_39 >= 'A' && LA14_39 <= 'Z')||LA14_39=='_'||(LA14_39 >= 'a' && LA14_39 <= 'r')||(LA14_39 >= 't' && LA14_39 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 40;
						 
						input.seek(index14_39);
						if ( s>=0 ) return s;
						break;

					case 1 : 
						int LA14_54 = input.LA(1);
						 
						int index14_54 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_54=='e') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 67;}
						else if ( ((LA14_54 >= '0' && LA14_54 <= '9')||(LA14_54 >= 'A' && LA14_54 <= 'Z')||LA14_54=='_'||(LA14_54 >= 'a' && LA14_54 <= 'd')||(LA14_54 >= 'f' && LA14_54 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 40;
						 
						input.seek(index14_54);
						if ( s>=0 ) return s;
						break;

					case 2 : 
						int LA14_12 = input.LA(1);
						 
						int index14_12 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_12=='e') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 31;}
						else if ( ((LA14_12 >= '0' && LA14_12 <= '9')||(LA14_12 >= 'A' && LA14_12 <= 'Z')||LA14_12=='_'||(LA14_12 >= 'a' && LA14_12 <= 'd')||(LA14_12 >= 'f' && LA14_12 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						 
						input.seek(index14_12);
						if ( s>=0 ) return s;
						break;

					case 3 : 
						int LA14_22 = input.LA(1);
						 
						int index14_22 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_22=='o') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 39;}
						else if ( ((LA14_22 >= '0' && LA14_22 <= '9')||(LA14_22 >= 'A' && LA14_22 <= 'Z')||LA14_22=='_'||(LA14_22 >= 'a' && LA14_22 <= 'n')||(LA14_22 >= 'p' && LA14_22 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 40;
						 
						input.seek(index14_22);
						if ( s>=0 ) return s;
						break;

					case 4 : 
						int LA14_24 = input.LA(1);
						 
						int index14_24 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_24=='e') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 41;}
						else if ( ((LA14_24 >= '0' && LA14_24 <= '9')||(LA14_24 >= 'A' && LA14_24 <= 'Z')||LA14_24=='_'||(LA14_24 >= 'a' && LA14_24 <= 'd')||(LA14_24 >= 'f' && LA14_24 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 40;
						 
						input.seek(index14_24);
						if ( s>=0 ) return s;
						break;

					case 5 : 
						int LA14_11 = input.LA(1);
						 
						int index14_11 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_11=='m') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 30;}
						else if ( ((LA14_11 >= '0' && LA14_11 <= '9')||(LA14_11 >= 'A' && LA14_11 <= 'Z')||LA14_11=='_'||(LA14_11 >= 'a' && LA14_11 <= 'l')||(LA14_11 >= 'n' && LA14_11 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						 
						input.seek(index14_11);
						if ( s>=0 ) return s;
						break;

					case 6 : 
						int LA14_10 = input.LA(1);
						 
						int index14_10 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_10=='m') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 29;}
						else if ( ((LA14_10 >= '0' && LA14_10 <= '9')||(LA14_10 >= 'A' && LA14_10 <= 'Z')||LA14_10=='_'||(LA14_10 >= 'a' && LA14_10 <= 'l')||(LA14_10 >= 'n' && LA14_10 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						 
						input.seek(index14_10);
						if ( s>=0 ) return s;
						break;

					case 7 : 
						int LA14_41 = input.LA(1);
						 
						int index14_41 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_41=='n') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 57;}
						else if ( ((LA14_41 >= '0' && LA14_41 <= '9')||(LA14_41 >= 'A' && LA14_41 <= 'Z')||LA14_41=='_'||(LA14_41 >= 'a' && LA14_41 <= 'm')||(LA14_41 >= 'o' && LA14_41 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 40;
						 
						input.seek(index14_41);
						if ( s>=0 ) return s;
						break;

					case 8 : 
						int LA14_42 = input.LA(1);
						 
						int index14_42 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_42=='h') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 58;}
						else if ( ((LA14_42 >= '0' && LA14_42 <= '9')||(LA14_42 >= 'A' && LA14_42 <= 'Z')||LA14_42=='_'||(LA14_42 >= 'a' && LA14_42 <= 'g')||(LA14_42 >= 'i' && LA14_42 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 40;
						 
						input.seek(index14_42);
						if ( s>=0 ) return s;
						break;

					case 9 : 
						int LA14_9 = input.LA(1);
						 
						int index14_9 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_9=='m') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 28;}
						else if ( ((LA14_9 >= '0' && LA14_9 <= '9')||(LA14_9 >= 'A' && LA14_9 <= 'Z')||LA14_9=='_'||(LA14_9 >= 'a' && LA14_9 <= 'l')||(LA14_9 >= 'n' && LA14_9 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						 
						input.seek(index14_9);
						if ( s>=0 ) return s;
						break;

					case 10 : 
						int LA14_25 = input.LA(1);
						 
						int index14_25 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_25=='g') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 42;}
						else if ( ((LA14_25 >= '0' && LA14_25 <= '9')||(LA14_25 >= 'A' && LA14_25 <= 'Z')||LA14_25=='_'||(LA14_25 >= 'a' && LA14_25 <= 'f')||(LA14_25 >= 'h' && LA14_25 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 40;
						 
						input.seek(index14_25);
						if ( s>=0 ) return s;
						break;

					case 11 : 
						int LA14_26 = input.LA(1);
						 
						int index14_26 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_26=='w') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 43;}
						else if ( ((LA14_26 >= '0' && LA14_26 <= '9')||(LA14_26 >= 'A' && LA14_26 <= 'Z')||LA14_26=='_'||(LA14_26 >= 'a' && LA14_26 <= 'v')||(LA14_26 >= 'x' && LA14_26 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 40;
						 
						input.seek(index14_26);
						if ( s>=0 ) return s;
						break;

					case 12 : 
						int LA14_40 = input.LA(1);
						 
						int index14_40 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeNativeOpAhead())) ) {s = 55;}
						else if ( ((runtimeUserOpAhead())) ) {s = 56;}
						 
						input.seek(index14_40);
						if ( s>=0 ) return s;
						break;

					case 13 : 
						int LA14_60 = input.LA(1);
						 
						int index14_60 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_60=='m') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 69;}
						else if ( ((LA14_60 >= '0' && LA14_60 <= '9')||(LA14_60 >= 'A' && LA14_60 <= 'Z')||LA14_60=='_'||(LA14_60 >= 'a' && LA14_60 <= 'l')||(LA14_60 >= 'n' && LA14_60 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 40;
						 
						input.seek(index14_60);
						if ( s>=0 ) return s;
						break;

					case 14 : 
						int LA14_44 = input.LA(1);
						 
						int index14_44 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_44=='u') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 60;}
						else if ( ((LA14_44 >= '0' && LA14_44 <= '9')||(LA14_44 >= 'A' && LA14_44 <= 'Z')||LA14_44=='_'||(LA14_44 >= 'a' && LA14_44 <= 't')||(LA14_44 >= 'v' && LA14_44 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 40;
						 
						input.seek(index14_44);
						if ( s>=0 ) return s;
						break;

					case 15 : 
						int LA14_27 = input.LA(1);
						 
						int index14_27 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_27=='l') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 44;}
						else if ( ((LA14_27 >= '0' && LA14_27 <= '9')||(LA14_27 >= 'A' && LA14_27 <= 'Z')||LA14_27=='_'||(LA14_27 >= 'a' && LA14_27 <= 'k')||(LA14_27 >= 'm' && LA14_27 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 40;
						 
						input.seek(index14_27);
						if ( s>=0 ) return s;
						break;

					case 16 : 
						int LA14_69 = input.LA(1);
						 
						int index14_69 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_69=='e') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 71;}
						else if ( ((LA14_69 >= '0' && LA14_69 <= '9')||(LA14_69 >= 'A' && LA14_69 <= 'Z')||LA14_69=='_'||(LA14_69 >= 'a' && LA14_69 <= 'd')||(LA14_69 >= 'f' && LA14_69 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 40;
						 
						input.seek(index14_69);
						if ( s>=0 ) return s;
						break;

					case 17 : 
						int LA14_8 = input.LA(1);
						 
						int index14_8 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_8=='o') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 27;}
						else if ( ((LA14_8 >= '0' && LA14_8 <= '9')||(LA14_8 >= 'A' && LA14_8 <= 'Z')||LA14_8=='_'||(LA14_8 >= 'a' && LA14_8 <= 'n')||(LA14_8 >= 'p' && LA14_8 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						 
						input.seek(index14_8);
						if ( s>=0 ) return s;
						break;

					case 18 : 
						int LA14_7 = input.LA(1);
						 
						int index14_7 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_7=='o') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 26;}
						else if ( ((LA14_7 >= '0' && LA14_7 <= '9')||(LA14_7 >= 'A' && LA14_7 <= 'Z')||LA14_7=='_'||(LA14_7 >= 'a' && LA14_7 <= 'n')||(LA14_7 >= 'p' && LA14_7 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						 
						input.seek(index14_7);
						if ( s>=0 ) return s;
						break;

					case 19 : 
						int LA14_6 = input.LA(1);
						 
						int index14_6 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_6=='i') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 25;}
						else if ( ((LA14_6 >= '0' && LA14_6 <= '9')||(LA14_6 >= 'A' && LA14_6 <= 'Z')||LA14_6=='_'||(LA14_6 >= 'a' && LA14_6 <= 'h')||(LA14_6 >= 'j' && LA14_6 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						 
						input.seek(index14_6);
						if ( s>=0 ) return s;
						break;

					case 20 : 
						int LA14_28 = input.LA(1);
						 
						int index14_28 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_28=='a') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 45;}
						else if ( ((LA14_28 >= '0' && LA14_28 <= '9')||(LA14_28 >= 'A' && LA14_28 <= 'Z')||LA14_28=='_'||(LA14_28 >= 'b' && LA14_28 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 40;
						 
						input.seek(index14_28);
						if ( s>=0 ) return s;
						break;

					case 21 : 
						int LA14_71 = input.LA(1);
						 
						int index14_71 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA14_71 >= '0' && LA14_71 <= '9')||(LA14_71 >= 'A' && LA14_71 <= 'Z')||LA14_71=='_'||(LA14_71 >= 'a' && LA14_71 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 59;
						 
						input.seek(index14_71);
						if ( s>=0 ) return s;
						break;

					case 22 : 
						int LA14_29 = input.LA(1);
						 
						int index14_29 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_29=='a') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 46;}
						else if ( ((LA14_29 >= '0' && LA14_29 <= '9')||(LA14_29 >= 'A' && LA14_29 <= 'Z')||LA14_29=='_'||(LA14_29 >= 'b' && LA14_29 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 40;
						 
						input.seek(index14_29);
						if ( s>=0 ) return s;
						break;

					case 23 : 
						int LA14_5 = input.LA(1);
						 
						int index14_5 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_5=='p') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 24;}
						else if ( ((LA14_5 >= '0' && LA14_5 <= '9')||(LA14_5 >= 'A' && LA14_5 <= 'Z')||LA14_5=='_'||(LA14_5 >= 'a' && LA14_5 <= 'o')||(LA14_5 >= 'q' && LA14_5 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						 
						input.seek(index14_5);
						if ( s>=0 ) return s;
						break;

					case 24 : 
						int LA14_0 = input.LA(1);
						 
						int index14_0 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_0=='(') ) {s = 1;}
						else if ( (LA14_0==')') ) {s = 2;}
						else if ( (LA14_0==',') ) {s = 3;}
						else if ( (LA14_0=='c') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 4;}
						else if ( (LA14_0=='o') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 5;}
						else if ( (LA14_0=='h') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 6;}
						else if ( (LA14_0=='l') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 7;}
						else if ( (LA14_0=='v') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 8;}
						else if ( (LA14_0=='S') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 9;}
						else if ( (LA14_0=='E') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 10;}
						else if ( (LA14_0=='W') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 11;}
						else if ( (LA14_0=='D') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 12;}
						else if ( (LA14_0=='T') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 13;}
						else if ( (LA14_0=='K') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 14;}
						else if ( (LA14_0=='M') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 15;}
						else if ( ((LA14_0 >= 'A' && LA14_0 <= 'C')||(LA14_0 >= 'F' && LA14_0 <= 'J')||LA14_0=='L'||(LA14_0 >= 'N' && LA14_0 <= 'R')||(LA14_0 >= 'U' && LA14_0 <= 'V')||(LA14_0 >= 'X' && LA14_0 <= 'Z')||LA14_0=='_'||(LA14_0 >= 'a' && LA14_0 <= 'b')||(LA14_0 >= 'd' && LA14_0 <= 'g')||(LA14_0 >= 'i' && LA14_0 <= 'k')||(LA14_0 >= 'm' && LA14_0 <= 'n')||(LA14_0 >= 'p' && LA14_0 <= 'u')||(LA14_0 >= 'w' && LA14_0 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 16;}
						else if ( (LA14_0=='-'||(LA14_0 >= '0' && LA14_0 <= '9')) ) {s = 17;}
						else if ( (LA14_0=='\"') ) {s = 18;}
						else if ( (LA14_0==':') ) {s = 19;}
						else if ( ((LA14_0 >= '\t' && LA14_0 <= '\n')||(LA14_0 >= '\f' && LA14_0 <= '\r')||LA14_0==' ') ) {s = 20;}
						else if ( (LA14_0=='/') ) {s = 21;}
						 
						input.seek(index14_0);
						if ( s>=0 ) return s;
						break;

					case 25 : 
						int LA14_30 = input.LA(1);
						 
						int index14_30 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_30=='a') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 47;}
						else if ( ((LA14_30 >= '0' && LA14_30 <= '9')||(LA14_30 >= 'A' && LA14_30 <= 'Z')||LA14_30=='_'||(LA14_30 >= 'b' && LA14_30 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 40;
						 
						input.seek(index14_30);
						if ( s>=0 ) return s;
						break;

					case 26 : 
						int LA14_4 = input.LA(1);
						 
						int index14_4 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_4=='l') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 22;}
						else if ( ((LA14_4 >= '0' && LA14_4 <= '9')||(LA14_4 >= 'A' && LA14_4 <= 'Z')||LA14_4=='_'||(LA14_4 >= 'a' && LA14_4 <= 'k')||(LA14_4 >= 'm' && LA14_4 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						 
						input.seek(index14_4);
						if ( s>=0 ) return s;
						break;

					case 27 : 
						int LA14_48 = input.LA(1);
						 
						int index14_48 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_48=='a') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 61;}
						else if ( ((LA14_48 >= '0' && LA14_48 <= '9')||(LA14_48 >= 'A' && LA14_48 <= 'Z')||LA14_48=='_'||(LA14_48 >= 'b' && LA14_48 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 40;
						 
						input.seek(index14_48);
						if ( s>=0 ) return s;
						break;

					case 28 : 
						int LA14_31 = input.LA(1);
						 
						int index14_31 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_31=='m') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 48;}
						else if ( ((LA14_31 >= '0' && LA14_31 <= '9')||(LA14_31 >= 'A' && LA14_31 <= 'Z')||LA14_31=='_'||(LA14_31 >= 'a' && LA14_31 <= 'l')||(LA14_31 >= 'n' && LA14_31 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 40;
						 
						input.seek(index14_31);
						if ( s>=0 ) return s;
						break;

					case 29 : 
						int LA14_32 = input.LA(1);
						 
						int index14_32 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_32=='m') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 49;}
						else if ( ((LA14_32 >= '0' && LA14_32 <= '9')||(LA14_32 >= 'A' && LA14_32 <= 'Z')||LA14_32=='_'||(LA14_32 >= 'a' && LA14_32 <= 'l')||(LA14_32 >= 'n' && LA14_32 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 40;
						 
						input.seek(index14_32);
						if ( s>=0 ) return s;
						break;

					case 30 : 
						int LA14_63 = input.LA(1);
						 
						int index14_63 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_63=='a') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 70;}
						else if ( ((LA14_63 >= '0' && LA14_63 <= '9')||(LA14_63 >= 'A' && LA14_63 <= 'Z')||LA14_63=='_'||(LA14_63 >= 'b' && LA14_63 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 40;
						 
						input.seek(index14_63);
						if ( s>=0 ) return s;
						break;

					case 31 : 
						int LA14_23 = input.LA(1);
						 
						int index14_23 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA14_23 >= '0' && LA14_23 <= '9')||(LA14_23 >= 'A' && LA14_23 <= 'Z')||LA14_23=='_'||(LA14_23 >= 'a' && LA14_23 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 40;
						 
						input.seek(index14_23);
						if ( s>=0 ) return s;
						break;

					case 32 : 
						int LA14_66 = input.LA(1);
						 
						int index14_66 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA14_66 >= '0' && LA14_66 <= '9')||(LA14_66 >= 'A' && LA14_66 <= 'Z')||LA14_66=='_'||(LA14_66 >= 'a' && LA14_66 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 51;
						 
						input.seek(index14_66);
						if ( s>=0 ) return s;
						break;

					case 33 : 
						int LA14_33 = input.LA(1);
						 
						int index14_33 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_33=='i') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 50;}
						else if ( ((LA14_33 >= '0' && LA14_33 <= '9')||(LA14_33 >= 'A' && LA14_33 <= 'Z')||LA14_33=='_'||(LA14_33 >= 'a' && LA14_33 <= 'h')||(LA14_33 >= 'j' && LA14_33 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 40;
						 
						input.seek(index14_33);
						if ( s>=0 ) return s;
						break;

					case 34 : 
						int LA14_50 = input.LA(1);
						 
						int index14_50 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_50=='m') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 63;}
						else if ( ((LA14_50 >= '0' && LA14_50 <= '9')||(LA14_50 >= 'A' && LA14_50 <= 'Z')||LA14_50=='_'||(LA14_50 >= 'a' && LA14_50 <= 'l')||(LA14_50 >= 'n' && LA14_50 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 40;
						 
						input.seek(index14_50);
						if ( s>=0 ) return s;
						break;

					case 35 : 
						int LA14_49 = input.LA(1);
						 
						int index14_49 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_49=='a') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 62;}
						else if ( ((LA14_49 >= '0' && LA14_49 <= '9')||(LA14_49 >= 'A' && LA14_49 <= 'Z')||LA14_49=='_'||(LA14_49 >= 'b' && LA14_49 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 40;
						 
						input.seek(index14_49);
						if ( s>=0 ) return s;
						break;

					case 36 : 
						int LA14_34 = input.LA(1);
						 
						int index14_34 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA14_34 >= '0' && LA14_34 <= '9')||(LA14_34 >= 'A' && LA14_34 <= 'Z')||LA14_34=='_'||(LA14_34 >= 'a' && LA14_34 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 51;
						 
						input.seek(index14_34);
						if ( s>=0 ) return s;
						break;

					case 37 : 
						int LA14_70 = input.LA(1);
						 
						int index14_70 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA14_70 >= '0' && LA14_70 <= '9')||(LA14_70 >= 'A' && LA14_70 <= 'Z')||LA14_70=='_'||(LA14_70 >= 'a' && LA14_70 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 51;
						 
						input.seek(index14_70);
						if ( s>=0 ) return s;
						break;

					case 38 : 
						int LA14_36 = input.LA(1);
						 
						int index14_36 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_36=='m') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 53;}
						else if ( ((LA14_36 >= '0' && LA14_36 <= '9')||(LA14_36 >= 'A' && LA14_36 <= 'Z')||LA14_36=='_'||(LA14_36 >= 'a' && LA14_36 <= 'l')||(LA14_36 >= 'n' && LA14_36 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 40;
						 
						input.seek(index14_36);
						if ( s>=0 ) return s;
						break;

					case 39 : 
						int LA14_67 = input.LA(1);
						 
						int index14_67 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA14_67 >= '0' && LA14_67 <= '9')||(LA14_67 >= 'A' && LA14_67 <= 'Z')||LA14_67=='_'||(LA14_67 >= 'a' && LA14_67 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 59;
						 
						input.seek(index14_67);
						if ( s>=0 ) return s;
						break;

					case 40 : 
						int LA14_35 = input.LA(1);
						 
						int index14_35 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_35=='m') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 52;}
						else if ( ((LA14_35 >= '0' && LA14_35 <= '9')||(LA14_35 >= 'A' && LA14_35 <= 'Z')||LA14_35=='_'||(LA14_35 >= 'a' && LA14_35 <= 'l')||(LA14_35 >= 'n' && LA14_35 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 40;
						 
						input.seek(index14_35);
						if ( s>=0 ) return s;
						break;

					case 41 : 
						int LA14_52 = input.LA(1);
						 
						int index14_52 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_52=='a') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 65;}
						else if ( ((LA14_52 >= '0' && LA14_52 <= '9')||(LA14_52 >= 'A' && LA14_52 <= 'Z')||LA14_52=='_'||(LA14_52 >= 'b' && LA14_52 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 40;
						 
						input.seek(index14_52);
						if ( s>=0 ) return s;
						break;

					case 42 : 
						int LA14_65 = input.LA(1);
						 
						int index14_65 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA14_65 >= '0' && LA14_65 <= '9')||(LA14_65 >= 'A' && LA14_65 <= 'Z')||LA14_65=='_'||(LA14_65 >= 'a' && LA14_65 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 51;
						 
						input.seek(index14_65);
						if ( s>=0 ) return s;
						break;

					case 43 : 
						int LA14_57 = input.LA(1);
						 
						int index14_57 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA14_57 >= '0' && LA14_57 <= '9')||(LA14_57 >= 'A' && LA14_57 <= 'Z')||LA14_57=='_'||(LA14_57 >= 'a' && LA14_57 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 59;
						 
						input.seek(index14_57);
						if ( s>=0 ) return s;
						break;

					case 44 : 
						int LA14_58 = input.LA(1);
						 
						int index14_58 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA14_58 >= '0' && LA14_58 <= '9')||(LA14_58 >= 'A' && LA14_58 <= 'Z')||LA14_58=='_'||(LA14_58 >= 'a' && LA14_58 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 59;
						 
						input.seek(index14_58);
						if ( s>=0 ) return s;
						break;

					case 45 : 
						int LA14_53 = input.LA(1);
						 
						int index14_53 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_53=='a') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 66;}
						else if ( ((LA14_53 >= '0' && LA14_53 <= '9')||(LA14_53 >= 'A' && LA14_53 <= 'Z')||LA14_53=='_'||(LA14_53 >= 'b' && LA14_53 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 40;
						 
						input.seek(index14_53);
						if ( s>=0 ) return s;
						break;

					case 46 : 
						int LA14_43 = input.LA(1);
						 
						int index14_43 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA14_43 >= '0' && LA14_43 <= '9')||(LA14_43 >= 'A' && LA14_43 <= 'Z')||LA14_43=='_'||(LA14_43 >= 'a' && LA14_43 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 59;
						 
						input.seek(index14_43);
						if ( s>=0 ) return s;
						break;

					case 47 : 
						int LA14_13 = input.LA(1);
						 
						int index14_13 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_13=='e') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 32;}
						else if ( (LA14_13=='r') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 33;}
						else if ( (LA14_13=='3') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 34;}
						else if ( ((LA14_13 >= '0' && LA14_13 <= '2')||(LA14_13 >= '4' && LA14_13 <= '9')||(LA14_13 >= 'A' && LA14_13 <= 'Z')||LA14_13=='_'||(LA14_13 >= 'a' && LA14_13 <= 'd')||(LA14_13 >= 'f' && LA14_13 <= 'q')||(LA14_13 >= 's' && LA14_13 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						 
						input.seek(index14_13);
						if ( s>=0 ) return s;
						break;

					case 48 : 
						int LA14_45 = input.LA(1);
						 
						int index14_45 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA14_45 >= '0' && LA14_45 <= '9')||(LA14_45 >= 'A' && LA14_45 <= 'Z')||LA14_45=='_'||(LA14_45 >= 'a' && LA14_45 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 51;
						 
						input.seek(index14_45);
						if ( s>=0 ) return s;
						break;

					case 49 : 
						int LA14_46 = input.LA(1);
						 
						int index14_46 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA14_46 >= '0' && LA14_46 <= '9')||(LA14_46 >= 'A' && LA14_46 <= 'Z')||LA14_46=='_'||(LA14_46 >= 'a' && LA14_46 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 51;
						 
						input.seek(index14_46);
						if ( s>=0 ) return s;
						break;

					case 50 : 
						int LA14_51 = input.LA(1);
						 
						int index14_51 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeMATypeOpAhead())) ) {s = 64;}
						else if ( ((runtimeNativeOpAhead())) ) {s = 55;}
						else if ( ((runtimeUserOpAhead())) ) {s = 56;}
						 
						input.seek(index14_51);
						if ( s>=0 ) return s;
						break;

					case 51 : 
						int LA14_59 = input.LA(1);
						 
						int index14_59 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeHistoryOpAhead())) ) {s = 68;}
						else if ( ((runtimeNativeOpAhead())) ) {s = 55;}
						else if ( ((runtimeUserOpAhead())) ) {s = 56;}
						 
						input.seek(index14_59);
						if ( s>=0 ) return s;
						break;

					case 52 : 
						int LA14_16 = input.LA(1);
						 
						int index14_16 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA14_16 >= '0' && LA14_16 <= '9')||(LA14_16 >= 'A' && LA14_16 <= 'Z')||LA14_16=='_'||(LA14_16 >= 'a' && LA14_16 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						 
						input.seek(index14_16);
						if ( s>=0 ) return s;
						break;

					case 53 : 
						int LA14_15 = input.LA(1);
						 
						int index14_15 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_15=='a') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 36;}
						else if ( ((LA14_15 >= '0' && LA14_15 <= '9')||(LA14_15 >= 'A' && LA14_15 <= 'Z')||LA14_15=='_'||(LA14_15 >= 'b' && LA14_15 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						 
						input.seek(index14_15);
						if ( s>=0 ) return s;
						break;

					case 54 : 
						int LA14_62 = input.LA(1);
						 
						int index14_62 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA14_62 >= '0' && LA14_62 <= '9')||(LA14_62 >= 'A' && LA14_62 <= 'Z')||LA14_62=='_'||(LA14_62 >= 'a' && LA14_62 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 51;
						 
						input.seek(index14_62);
						if ( s>=0 ) return s;
						break;

					case 55 : 
						int LA14_47 = input.LA(1);
						 
						int index14_47 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA14_47 >= '0' && LA14_47 <= '9')||(LA14_47 >= 'A' && LA14_47 <= 'Z')||LA14_47=='_'||(LA14_47 >= 'a' && LA14_47 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 51;
						 
						input.seek(index14_47);
						if ( s>=0 ) return s;
						break;

					case 56 : 
						int LA14_61 = input.LA(1);
						 
						int index14_61 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA14_61 >= '0' && LA14_61 <= '9')||(LA14_61 >= 'A' && LA14_61 <= 'Z')||LA14_61=='_'||(LA14_61 >= 'a' && LA14_61 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						else s = 51;
						 
						input.seek(index14_61);
						if ( s>=0 ) return s;
						break;

					case 57 : 
						int LA14_14 = input.LA(1);
						 
						int index14_14 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_14=='a') && (((runtimeNativeOpAhead())||(runtimeMATypeOpAhead())||(runtimeUserOpAhead())))) {s = 35;}
						else if ( ((LA14_14 >= '0' && LA14_14 <= '9')||(LA14_14 >= 'A' && LA14_14 <= 'Z')||LA14_14=='_'||(LA14_14 >= 'b' && LA14_14 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 23;}
						 
						input.seek(index14_14);
						if ( s>=0 ) return s;
						break;
			}
			NoViableAltException nvae =
				new NoViableAltException(getDescription(), 14, _s, input);
			error(nvae);
			throw nvae;
		}
	}

}
