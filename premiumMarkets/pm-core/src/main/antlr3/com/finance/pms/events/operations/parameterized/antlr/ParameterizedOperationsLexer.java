// $ANTLR 3.5.2 com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g 2021-11-19 09:24:08
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
	public static final int T__21=21;
	public static final int T__22=22;
	public static final int T__23=23;
	public static final int T__24=24;
	public static final int T__25=25;
	public static final int T__26=26;
	public static final int COMMENT=4;
	public static final int HistoricalData=5;
	public static final int LINE_COMMENT=6;
	public static final int ListOperation=7;
	public static final int MAType=8;
	public static final int MATypeToken=9;
	public static final int NaNNumber=10;
	public static final int Nativeop=11;
	public static final int Number=12;
	public static final int NumberToken=13;
	public static final int OperationOutput=14;
	public static final int OutputSelector=15;
	public static final int StockOperation=16;
	public static final int String=17;
	public static final int StringToken=18;
	public static final int Userop=19;
	public static final int WS=20;


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
	  public boolean runtimeNaNAhead() {
	     return lexerDelegate.runtimeNaNAhead();
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
	@Override public String getGrammarFileName() { return "com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g"; }

	// $ANTLR start "T__21"
	public final void mT__21() throws RecognitionException {
		try {
			int _type = T__21;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:58:7: ( '(' )
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:58:9: '('
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
	// $ANTLR end "T__21"

	// $ANTLR start "T__22"
	public final void mT__22() throws RecognitionException {
		try {
			int _type = T__22;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:59:7: ( ')' )
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:59:9: ')'
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
	// $ANTLR end "T__22"

	// $ANTLR start "T__23"
	public final void mT__23() throws RecognitionException {
		try {
			int _type = T__23;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:60:7: ( ',' )
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:60:9: ','
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
	// $ANTLR end "T__23"

	// $ANTLR start "T__24"
	public final void mT__24() throws RecognitionException {
		try {
			int _type = T__24;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:61:7: ( '[' )
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:61:9: '['
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
	// $ANTLR end "T__24"

	// $ANTLR start "T__25"
	public final void mT__25() throws RecognitionException {
		try {
			int _type = T__25;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:62:7: ( '[]' )
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:62:9: '[]'
			{
			match("[]"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__25"

	// $ANTLR start "T__26"
	public final void mT__26() throws RecognitionException {
		try {
			int _type = T__26;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:63:7: ( ']' )
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:63:9: ']'
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
	// $ANTLR end "T__26"

	// $ANTLR start "HistoricalData"
	public final void mHistoricalData() throws RecognitionException {
		try {
			int _type = HistoricalData;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:133:6: ({...}? => ( 'close' | 'open' | 'high' | 'low' | 'volume' ) )
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:133:8: {...}? => ( 'close' | 'open' | 'high' | 'low' | 'volume' )
			{
			if ( !((runtimeHistoryOpAhead())) ) {
				throw new FailedPredicateException(input, "HistoricalData", "runtimeHistoryOpAhead()");
			}
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:133:38: ( 'close' | 'open' | 'high' | 'low' | 'volume' )
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
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:133:39: 'close'
					{
					match("close"); 

					}
					break;
				case 2 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:133:49: 'open'
					{
					match("open"); 

					}
					break;
				case 3 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:133:58: 'high'
					{
					match("high"); 

					}
					break;
				case 4 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:133:67: 'low'
					{
					match("low"); 

					}
					break;
				case 5 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:133:75: 'volume'
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
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:136:6: ({...}? => ( 'Sma' | 'Ema' | 'Wma' | 'Dema' | 'Tema' | 'Trima' | 'Kama' | 'Mama' | 'T3' ) )
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:136:8: {...}? => ( 'Sma' | 'Ema' | 'Wma' | 'Dema' | 'Tema' | 'Trima' | 'Kama' | 'Mama' | 'T3' )
			{
			if ( !((runtimeMATypeOpAhead())) ) {
				throw new FailedPredicateException(input, "MATypeToken", "runtimeMATypeOpAhead()");
			}
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:136:37: ( 'Sma' | 'Ema' | 'Wma' | 'Dema' | 'Tema' | 'Trima' | 'Kama' | 'Mama' | 'T3' )
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
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:136:38: 'Sma'
					{
					match("Sma"); 

					}
					break;
				case 2 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:136:44: 'Ema'
					{
					match("Ema"); 

					}
					break;
				case 3 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:136:50: 'Wma'
					{
					match("Wma"); 

					}
					break;
				case 4 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:136:56: 'Dema'
					{
					match("Dema"); 

					}
					break;
				case 5 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:136:63: 'Tema'
					{
					match("Tema"); 

					}
					break;
				case 6 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:136:71: 'Trima'
					{
					match("Trima"); 

					}
					break;
				case 7 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:136:80: 'Kama'
					{
					match("Kama"); 

					}
					break;
				case 8 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:136:88: 'Mama'
					{
					match("Mama"); 

					}
					break;
				case 9 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:136:96: 'T3'
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

	// $ANTLR start "NaNNumber"
	public final void mNaNNumber() throws RecognitionException {
		try {
			int _type = NaNNumber;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:139:6: ({...}? => ( 'NaN' ) )
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:139:8: {...}? => ( 'NaN' )
			{
			if ( !((runtimeNaNAhead())) ) {
				throw new FailedPredicateException(input, "NaNNumber", "runtimeNaNAhead()");
			}
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:139:32: ( 'NaN' )
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:139:33: 'NaN'
			{
			match("NaN"); 

			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NaNNumber"

	// $ANTLR start "Nativeop"
	public final void mNativeop() throws RecognitionException {
		try {
			int _type = Nativeop;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:142:6: ({...}? => ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )+ )
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:142:8: {...}? => ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )+
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
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:142:65: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )+
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
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:
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
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:145:6: ({...}? => ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '.' | '-' | '0' .. '9' )+ )
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:145:8: {...}? => ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '.' | '-' | '0' .. '9' )+
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
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:145:63: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '.' | '-' | '0' .. '9' )+
			int cnt4=0;
			loop4:
			while (true) {
				int alt4=2;
				int LA4_0 = input.LA(1);
				if ( ((LA4_0 >= '-' && LA4_0 <= '.')||(LA4_0 >= '0' && LA4_0 <= '9')||(LA4_0 >= 'A' && LA4_0 <= 'Z')||LA4_0=='_'||(LA4_0 >= 'a' && LA4_0 <= 'z')) ) {
					alt4=1;
				}

				switch (alt4) {
				case 1 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:
					{
					if ( (input.LA(1) >= '-' && input.LA(1) <= '.')||(input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
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
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:148:6: ( ( '-' )? ( '0' .. '9' )+ ( '.' ( '0' .. '9' )+ )? )
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:148:8: ( '-' )? ( '0' .. '9' )+ ( '.' ( '0' .. '9' )+ )?
			{
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:148:8: ( '-' )?
			int alt5=2;
			int LA5_0 = input.LA(1);
			if ( (LA5_0=='-') ) {
				alt5=1;
			}
			switch (alt5) {
				case 1 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:148:9: '-'
					{
					match('-'); 
					}
					break;

			}

			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:148:15: ( '0' .. '9' )+
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
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:
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

			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:148:27: ( '.' ( '0' .. '9' )+ )?
			int alt8=2;
			int LA8_0 = input.LA(1);
			if ( (LA8_0=='.') ) {
				alt8=1;
			}
			switch (alt8) {
				case 1 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:148:28: '.' ( '0' .. '9' )+
					{
					match('.'); 
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:148:32: ( '0' .. '9' )+
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
							// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:
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
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:151:6: ( '\"' ( 'a' .. 'z' | 'A' .. 'Z' | '.' | '_' | '/' | ',' | '=' | ( '0' .. '9' ) | '?' | ':' | '-' | '>' | '*' | '[' | ']' | '{' | '}' | '(' | ')' | '\\'' | '+' )+ '\"' )
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:151:8: '\"' ( 'a' .. 'z' | 'A' .. 'Z' | '.' | '_' | '/' | ',' | '=' | ( '0' .. '9' ) | '?' | ':' | '-' | '>' | '*' | '[' | ']' | '{' | '}' | '(' | ')' | '\\'' | '+' )+ '\"'
			{
			match('\"'); 
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:151:12: ( 'a' .. 'z' | 'A' .. 'Z' | '.' | '_' | '/' | ',' | '=' | ( '0' .. '9' ) | '?' | ':' | '-' | '>' | '*' | '[' | ']' | '{' | '}' | '(' | ')' | '\\'' | '+' )+
			int cnt9=0;
			loop9:
			while (true) {
				int alt9=2;
				int LA9_0 = input.LA(1);
				if ( ((LA9_0 >= '\'' && LA9_0 <= ':')||(LA9_0 >= '=' && LA9_0 <= '?')||(LA9_0 >= 'A' && LA9_0 <= '[')||LA9_0==']'||LA9_0=='_'||(LA9_0 >= 'a' && LA9_0 <= '{')||LA9_0=='}') ) {
					alt9=1;
				}

				switch (alt9) {
				case 1 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:
					{
					if ( (input.LA(1) >= '\'' && input.LA(1) <= ':')||(input.LA(1) >= '=' && input.LA(1) <= '?')||(input.LA(1) >= 'A' && input.LA(1) <= '[')||input.LA(1)==']'||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= '{')||input.LA(1)=='}' ) {
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
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:154:6: ( ':' ( 'a' .. 'z' | 'A' .. 'Z' )+ )
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:154:8: ':' ( 'a' .. 'z' | 'A' .. 'Z' )+
			{
			match(':'); 
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:154:12: ( 'a' .. 'z' | 'A' .. 'Z' )+
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
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:
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
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:159:5: ( ( ' ' | '\\r' | '\\t' | '\\u000C' | '\\n' ) )
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:159:7: ( ' ' | '\\r' | '\\t' | '\\u000C' | '\\n' )
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
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:162:5: ( '/*' ( . )* '*/' )
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:162:7: '/*' ( . )* '*/'
			{
			match("/*"); 

			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:162:12: ( . )*
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
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:162:12: .
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
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:165:5: ( '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n' )
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:165:7: '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n'
			{
			match("//"); 

			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:165:12: (~ ( '\\n' | '\\r' ) )*
			loop12:
			while (true) {
				int alt12=2;
				int LA12_0 = input.LA(1);
				if ( ((LA12_0 >= '\u0000' && LA12_0 <= '\t')||(LA12_0 >= '\u000B' && LA12_0 <= '\f')||(LA12_0 >= '\u000E' && LA12_0 <= '\uFFFF')) ) {
					alt12=1;
				}

				switch (alt12) {
				case 1 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:
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

			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:165:26: ( '\\r' )?
			int alt13=2;
			int LA13_0 = input.LA(1);
			if ( (LA13_0=='\r') ) {
				alt13=1;
			}
			switch (alt13) {
				case 1 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:165:26: '\\r'
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
		// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:8: ( T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | HistoricalData | MATypeToken | NaNNumber | Nativeop | Userop | NumberToken | StringToken | OutputSelector | WS | COMMENT | LINE_COMMENT )
		int alt14=17;
		alt14 = dfa14.predict(input);
		switch (alt14) {
			case 1 :
				// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:10: T__21
				{
				mT__21(); 

				}
				break;
			case 2 :
				// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:16: T__22
				{
				mT__22(); 

				}
				break;
			case 3 :
				// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:22: T__23
				{
				mT__23(); 

				}
				break;
			case 4 :
				// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:28: T__24
				{
				mT__24(); 

				}
				break;
			case 5 :
				// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:34: T__25
				{
				mT__25(); 

				}
				break;
			case 6 :
				// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:40: T__26
				{
				mT__26(); 

				}
				break;
			case 7 :
				// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:46: HistoricalData
				{
				mHistoricalData(); 

				}
				break;
			case 8 :
				// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:61: MATypeToken
				{
				mMATypeToken(); 

				}
				break;
			case 9 :
				// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:73: NaNNumber
				{
				mNaNNumber(); 

				}
				break;
			case 10 :
				// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:83: Nativeop
				{
				mNativeop(); 

				}
				break;
			case 11 :
				// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:92: Userop
				{
				mUserop(); 

				}
				break;
			case 12 :
				// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:99: NumberToken
				{
				mNumberToken(); 

				}
				break;
			case 13 :
				// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:111: StringToken
				{
				mStringToken(); 

				}
				break;
			case 14 :
				// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:123: OutputSelector
				{
				mOutputSelector(); 

				}
				break;
			case 15 :
				// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:138: WS
				{
				mWS(); 

				}
				break;
			case 16 :
				// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:141: COMMENT
				{
				mCOMMENT(); 

				}
				break;
			case 17 :
				// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:149: LINE_COMMENT
				{
				mLINE_COMMENT(); 

				}
				break;

		}
	}


	protected DFA14 dfa14 = new DFA14(this);
	static final String DFA14_eotS =
		"\4\uffff\1\32\26\uffff\2\57\1\uffff\12\57\1\72\3\57\2\uffff\1\57\1\uffff"+
		"\2\57\1\102\1\57\3\72\3\57\1\uffff\2\57\1\112\1\57\1\uffff\2\102\1\uffff"+
		"\1\57\2\72\1\57\1\uffff\2\72\1\uffff\1\102\1\uffff\1\57\1\72\1\uffff\1"+
		"\102";
	static final String DFA14_eofS =
		"\121\uffff";
	static final String DFA14_minS =
		"\1\11\3\uffff\1\135\1\uffff\16\55\4\uffff\1\52\2\uffff\2\55\1\uffff\16"+
		"\55\2\uffff\1\55\1\0\12\55\1\0\4\55\1\uffff\2\55\1\0\4\55\1\uffff\2\55"+
		"\1\0\1\55\1\uffff\2\55\1\uffff\1\55";
	static final String DFA14_maxS =
		"\1\172\3\uffff\1\135\1\uffff\16\172\4\uffff\1\57\2\uffff\2\172\1\uffff"+
		"\16\172\2\uffff\1\172\1\0\12\172\1\0\4\172\1\uffff\2\172\1\0\4\172\1\uffff"+
		"\2\172\1\0\1\172\1\uffff\2\172\1\uffff\1\172";
	static final String DFA14_acceptS =
		"\1\uffff\1\1\1\2\1\3\1\uffff\1\6\16\uffff\1\14\1\15\1\16\1\17\1\uffff"+
		"\1\5\1\4\2\uffff\1\13\16\uffff\1\20\1\21\21\uffff\1\12\7\uffff\1\10\4"+
		"\uffff\1\7\2\uffff\1\11\1\uffff";
	static final String DFA14_specialS =
		"\1\41\5\uffff\1\46\1\47\1\50\1\51\1\55\1\67\1\73\1\74\1\75\1\3\1\5\1\11"+
		"\1\31\1\4\7\uffff\1\52\1\21\1\uffff\1\56\1\61\1\65\1\66\1\0\1\1\1\2\1"+
		"\6\1\12\1\15\1\36\1\23\1\26\1\37\2\uffff\1\53\1\22\1\57\1\63\1\43\1\70"+
		"\1\10\1\14\1\17\1\7\1\13\1\16\1\62\1\24\1\27\1\44\1\54\1\uffff\1\40\1"+
		"\42\1\60\1\71\1\25\1\30\1\20\1\uffff\1\33\1\34\1\64\1\35\1\uffff\1\72"+
		"\1\32\1\uffff\1\45}>";
	static final String[] DFA14_transitionS = {
			"\2\27\1\uffff\2\27\22\uffff\1\27\1\uffff\1\25\5\uffff\1\1\1\2\2\uffff"+
			"\1\3\1\24\1\uffff\1\30\12\24\1\26\6\uffff\3\23\1\16\1\14\5\23\1\20\1"+
			"\23\1\21\1\22\4\23\1\13\1\17\2\23\1\15\3\23\1\4\1\uffff\1\5\1\uffff\1"+
			"\23\1\uffff\2\23\1\6\4\23\1\10\3\23\1\11\2\23\1\7\6\23\1\12\4\23",
			"",
			"",
			"",
			"\1\31",
			"",
			"\2\35\1\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\13\34\1\33\16"+
			"\34",
			"\2\35\1\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\17\34\1\36\12"+
			"\34",
			"\2\35\1\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\10\34\1\37\21"+
			"\34",
			"\2\35\1\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\16\34\1\40\13"+
			"\34",
			"\2\35\1\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\16\34\1\41\13"+
			"\34",
			"\2\35\1\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\14\34\1\42\15"+
			"\34",
			"\2\35\1\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\14\34\1\43\15"+
			"\34",
			"\2\35\1\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\14\34\1\44\15"+
			"\34",
			"\2\35\1\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\4\34\1\45\25"+
			"\34",
			"\2\35\1\uffff\3\34\1\50\6\34\7\uffff\32\34\4\uffff\1\34\1\uffff\4\34"+
			"\1\46\14\34\1\47\10\34",
			"\2\35\1\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\1\51\31\34",
			"\2\35\1\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\1\52\31\34",
			"\2\35\1\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\1\53\31\34",
			"\2\35\1\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
			"",
			"",
			"",
			"",
			"\1\54\4\uffff\1\55",
			"",
			"",
			"\2\35\1\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\16\34\1\56\13"+
			"\34",
			"\2\35\1\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
			"",
			"\2\35\1\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\4\34\1\60\25"+
			"\34",
			"\2\35\1\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\6\34\1\61\23"+
			"\34",
			"\2\35\1\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\26\34\1\62\3"+
			"\34",
			"\2\35\1\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\13\34\1\63\16"+
			"\34",
			"\2\35\1\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\1\64\31\34",
			"\2\35\1\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\1\65\31\34",
			"\2\35\1\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\1\66\31\34",
			"\2\35\1\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\14\34\1\67\15"+
			"\34",
			"\2\35\1\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\14\34\1\70\15"+
			"\34",
			"\2\35\1\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\10\34\1\71\21"+
			"\34",
			"\2\35\1\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
			"\2\35\1\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\14\34\1\73\15"+
			"\34",
			"\2\35\1\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\14\34\1\74\15"+
			"\34",
			"\2\35\1\uffff\12\34\7\uffff\15\34\1\75\14\34\4\uffff\1\34\1\uffff\32"+
			"\34",
			"",
			"",
			"\2\35\1\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\22\34\1\76\7"+
			"\34",
			"\1\uffff",
			"\2\35\1\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\15\34\1\100\14"+
			"\34",
			"\2\35\1\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\7\34\1\101\22"+
			"\34",
			"\2\35\1\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
			"\2\35\1\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\24\34\1\103\5"+
			"\34",
			"\2\35\1\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
			"\2\35\1\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
			"\2\35\1\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
			"\2\35\1\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\1\104\31\34",
			"\2\35\1\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\1\105\31\34",
			"\2\35\1\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\14\34\1\106\15"+
			"\34",
			"\1\uffff",
			"\2\35\1\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\1\110\31\34",
			"\2\35\1\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\1\111\31\34",
			"\2\35\1\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
			"\2\35\1\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\4\34\1\113\25"+
			"\34",
			"",
			"\2\35\1\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
			"\2\35\1\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
			"\1\uffff",
			"\2\35\1\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\14\34\1\115\15"+
			"\34",
			"\2\35\1\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
			"\2\35\1\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
			"\2\35\1\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\1\116\31\34",
			"",
			"\2\35\1\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
			"\2\35\1\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
			"\1\uffff",
			"\2\35\1\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
			"",
			"\2\35\1\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\4\34\1\120\25"+
			"\34",
			"\2\35\1\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
			"",
			"\2\35\1\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34"
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
			return "1:1: Tokens : ( T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | HistoricalData | MATypeToken | NaNNumber | Nativeop | Userop | NumberToken | StringToken | OutputSelector | WS | COMMENT | LINE_COMMENT );";
		}
		@Override
		public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
			IntStream input = _input;
			int _s = s;
			switch ( s ) {
					case 0 : 
						int LA14_34 = input.LA(1);
						 
						int index14_34 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_34=='a') && (((runtimeMATypeOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 52;}
						else if ( ((LA14_34 >= '0' && LA14_34 <= '9')||(LA14_34 >= 'A' && LA14_34 <= 'Z')||LA14_34=='_'||(LA14_34 >= 'b' && LA14_34 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 28;}
						else if ( ((LA14_34 >= '-' && LA14_34 <= '.')) && ((runtimeUserOpAhead()))) {s = 29;}
						else s = 47;
						 
						input.seek(index14_34);
						if ( s>=0 ) return s;
						break;

					case 1 : 
						int LA14_35 = input.LA(1);
						 
						int index14_35 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_35=='a') && (((runtimeMATypeOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 53;}
						else if ( ((LA14_35 >= '0' && LA14_35 <= '9')||(LA14_35 >= 'A' && LA14_35 <= 'Z')||LA14_35=='_'||(LA14_35 >= 'b' && LA14_35 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 28;}
						else if ( ((LA14_35 >= '-' && LA14_35 <= '.')) && ((runtimeUserOpAhead()))) {s = 29;}
						else s = 47;
						 
						input.seek(index14_35);
						if ( s>=0 ) return s;
						break;

					case 2 : 
						int LA14_36 = input.LA(1);
						 
						int index14_36 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_36=='a') && (((runtimeMATypeOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 54;}
						else if ( ((LA14_36 >= '0' && LA14_36 <= '9')||(LA14_36 >= 'A' && LA14_36 <= 'Z')||LA14_36=='_'||(LA14_36 >= 'b' && LA14_36 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 28;}
						else if ( ((LA14_36 >= '-' && LA14_36 <= '.')) && ((runtimeUserOpAhead()))) {s = 29;}
						else s = 47;
						 
						input.seek(index14_36);
						if ( s>=0 ) return s;
						break;

					case 3 : 
						int LA14_15 = input.LA(1);
						 
						int index14_15 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_15=='e') && (((runtimeMATypeOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 38;}
						else if ( (LA14_15=='r') && (((runtimeMATypeOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 39;}
						else if ( (LA14_15=='3') && (((runtimeMATypeOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 40;}
						else if ( ((LA14_15 >= '0' && LA14_15 <= '2')||(LA14_15 >= '4' && LA14_15 <= '9')||(LA14_15 >= 'A' && LA14_15 <= 'Z')||LA14_15=='_'||(LA14_15 >= 'a' && LA14_15 <= 'd')||(LA14_15 >= 'f' && LA14_15 <= 'q')||(LA14_15 >= 's' && LA14_15 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 28;}
						else if ( ((LA14_15 >= '-' && LA14_15 <= '.')) && ((runtimeUserOpAhead()))) {s = 29;}
						 
						input.seek(index14_15);
						if ( s>=0 ) return s;
						break;

					case 4 : 
						int LA14_19 = input.LA(1);
						 
						int index14_19 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA14_19 >= '0' && LA14_19 <= '9')||(LA14_19 >= 'A' && LA14_19 <= 'Z')||LA14_19=='_'||(LA14_19 >= 'a' && LA14_19 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 28;}
						else if ( ((LA14_19 >= '-' && LA14_19 <= '.')) && ((runtimeUserOpAhead()))) {s = 29;}
						 
						input.seek(index14_19);
						if ( s>=0 ) return s;
						break;

					case 5 : 
						int LA14_16 = input.LA(1);
						 
						int index14_16 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_16=='a') && (((runtimeMATypeOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 41;}
						else if ( ((LA14_16 >= '0' && LA14_16 <= '9')||(LA14_16 >= 'A' && LA14_16 <= 'Z')||LA14_16=='_'||(LA14_16 >= 'b' && LA14_16 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 28;}
						else if ( ((LA14_16 >= '-' && LA14_16 <= '.')) && ((runtimeUserOpAhead()))) {s = 29;}
						 
						input.seek(index14_16);
						if ( s>=0 ) return s;
						break;

					case 6 : 
						int LA14_37 = input.LA(1);
						 
						int index14_37 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_37=='m') && (((runtimeMATypeOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 55;}
						else if ( ((LA14_37 >= '0' && LA14_37 <= '9')||(LA14_37 >= 'A' && LA14_37 <= 'Z')||LA14_37=='_'||(LA14_37 >= 'a' && LA14_37 <= 'l')||(LA14_37 >= 'n' && LA14_37 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 28;}
						else if ( ((LA14_37 >= '-' && LA14_37 <= '.')) && ((runtimeUserOpAhead()))) {s = 29;}
						else s = 47;
						 
						input.seek(index14_37);
						if ( s>=0 ) return s;
						break;

					case 7 : 
						int LA14_55 = input.LA(1);
						 
						int index14_55 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_55=='a') && (((runtimeMATypeOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 68;}
						else if ( ((LA14_55 >= '0' && LA14_55 <= '9')||(LA14_55 >= 'A' && LA14_55 <= 'Z')||LA14_55=='_'||(LA14_55 >= 'b' && LA14_55 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 28;}
						else if ( ((LA14_55 >= '-' && LA14_55 <= '.')) && ((runtimeUserOpAhead()))) {s = 29;}
						else s = 47;
						 
						input.seek(index14_55);
						if ( s>=0 ) return s;
						break;

					case 8 : 
						int LA14_52 = input.LA(1);
						 
						int index14_52 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA14_52 >= '0' && LA14_52 <= '9')||(LA14_52 >= 'A' && LA14_52 <= 'Z')||LA14_52=='_'||(LA14_52 >= 'a' && LA14_52 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 28;}
						else if ( ((LA14_52 >= '-' && LA14_52 <= '.')) && ((runtimeUserOpAhead()))) {s = 29;}
						else s = 58;
						 
						input.seek(index14_52);
						if ( s>=0 ) return s;
						break;

					case 9 : 
						int LA14_17 = input.LA(1);
						 
						int index14_17 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_17=='a') && (((runtimeMATypeOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 42;}
						else if ( ((LA14_17 >= '0' && LA14_17 <= '9')||(LA14_17 >= 'A' && LA14_17 <= 'Z')||LA14_17=='_'||(LA14_17 >= 'b' && LA14_17 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 28;}
						else if ( ((LA14_17 >= '-' && LA14_17 <= '.')) && ((runtimeUserOpAhead()))) {s = 29;}
						 
						input.seek(index14_17);
						if ( s>=0 ) return s;
						break;

					case 10 : 
						int LA14_38 = input.LA(1);
						 
						int index14_38 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_38=='m') && (((runtimeMATypeOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 56;}
						else if ( ((LA14_38 >= '0' && LA14_38 <= '9')||(LA14_38 >= 'A' && LA14_38 <= 'Z')||LA14_38=='_'||(LA14_38 >= 'a' && LA14_38 <= 'l')||(LA14_38 >= 'n' && LA14_38 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 28;}
						else if ( ((LA14_38 >= '-' && LA14_38 <= '.')) && ((runtimeUserOpAhead()))) {s = 29;}
						else s = 47;
						 
						input.seek(index14_38);
						if ( s>=0 ) return s;
						break;

					case 11 : 
						int LA14_56 = input.LA(1);
						 
						int index14_56 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_56=='a') && (((runtimeMATypeOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 69;}
						else if ( ((LA14_56 >= '0' && LA14_56 <= '9')||(LA14_56 >= 'A' && LA14_56 <= 'Z')||LA14_56=='_'||(LA14_56 >= 'b' && LA14_56 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 28;}
						else if ( ((LA14_56 >= '-' && LA14_56 <= '.')) && ((runtimeUserOpAhead()))) {s = 29;}
						else s = 47;
						 
						input.seek(index14_56);
						if ( s>=0 ) return s;
						break;

					case 12 : 
						int LA14_53 = input.LA(1);
						 
						int index14_53 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA14_53 >= '0' && LA14_53 <= '9')||(LA14_53 >= 'A' && LA14_53 <= 'Z')||LA14_53=='_'||(LA14_53 >= 'a' && LA14_53 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 28;}
						else if ( ((LA14_53 >= '-' && LA14_53 <= '.')) && ((runtimeUserOpAhead()))) {s = 29;}
						else s = 58;
						 
						input.seek(index14_53);
						if ( s>=0 ) return s;
						break;

					case 13 : 
						int LA14_39 = input.LA(1);
						 
						int index14_39 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_39=='i') && (((runtimeMATypeOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 57;}
						else if ( ((LA14_39 >= '0' && LA14_39 <= '9')||(LA14_39 >= 'A' && LA14_39 <= 'Z')||LA14_39=='_'||(LA14_39 >= 'a' && LA14_39 <= 'h')||(LA14_39 >= 'j' && LA14_39 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 28;}
						else if ( ((LA14_39 >= '-' && LA14_39 <= '.')) && ((runtimeUserOpAhead()))) {s = 29;}
						else s = 47;
						 
						input.seek(index14_39);
						if ( s>=0 ) return s;
						break;

					case 14 : 
						int LA14_57 = input.LA(1);
						 
						int index14_57 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_57=='m') && (((runtimeMATypeOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 70;}
						else if ( ((LA14_57 >= '0' && LA14_57 <= '9')||(LA14_57 >= 'A' && LA14_57 <= 'Z')||LA14_57=='_'||(LA14_57 >= 'a' && LA14_57 <= 'l')||(LA14_57 >= 'n' && LA14_57 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 28;}
						else if ( ((LA14_57 >= '-' && LA14_57 <= '.')) && ((runtimeUserOpAhead()))) {s = 29;}
						else s = 47;
						 
						input.seek(index14_57);
						if ( s>=0 ) return s;
						break;

					case 15 : 
						int LA14_54 = input.LA(1);
						 
						int index14_54 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA14_54 >= '0' && LA14_54 <= '9')||(LA14_54 >= 'A' && LA14_54 <= 'Z')||LA14_54=='_'||(LA14_54 >= 'a' && LA14_54 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 28;}
						else if ( ((LA14_54 >= '-' && LA14_54 <= '.')) && ((runtimeUserOpAhead()))) {s = 29;}
						else s = 58;
						 
						input.seek(index14_54);
						if ( s>=0 ) return s;
						break;

					case 16 : 
						int LA14_70 = input.LA(1);
						 
						int index14_70 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_70=='a') && (((runtimeMATypeOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 78;}
						else if ( ((LA14_70 >= '0' && LA14_70 <= '9')||(LA14_70 >= 'A' && LA14_70 <= 'Z')||LA14_70=='_'||(LA14_70 >= 'b' && LA14_70 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 28;}
						else if ( ((LA14_70 >= '-' && LA14_70 <= '.')) && ((runtimeUserOpAhead()))) {s = 29;}
						else s = 47;
						 
						input.seek(index14_70);
						if ( s>=0 ) return s;
						break;

					case 17 : 
						int LA14_28 = input.LA(1);
						 
						int index14_28 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA14_28 >= '0' && LA14_28 <= '9')||(LA14_28 >= 'A' && LA14_28 <= 'Z')||LA14_28=='_'||(LA14_28 >= 'a' && LA14_28 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 28;}
						else if ( ((LA14_28 >= '-' && LA14_28 <= '.')) && ((runtimeUserOpAhead()))) {s = 29;}
						else s = 47;
						 
						input.seek(index14_28);
						if ( s>=0 ) return s;
						break;

					case 18 : 
						int LA14_47 = input.LA(1);
						 
						int index14_47 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeNativeOpAhead())) ) {s = 63;}
						else if ( ((runtimeUserOpAhead())) ) {s = 29;}
						 
						input.seek(index14_47);
						if ( s>=0 ) return s;
						break;

					case 19 : 
						int LA14_41 = input.LA(1);
						 
						int index14_41 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_41=='m') && (((runtimeMATypeOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 59;}
						else if ( ((LA14_41 >= '0' && LA14_41 <= '9')||(LA14_41 >= 'A' && LA14_41 <= 'Z')||LA14_41=='_'||(LA14_41 >= 'a' && LA14_41 <= 'l')||(LA14_41 >= 'n' && LA14_41 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 28;}
						else if ( ((LA14_41 >= '-' && LA14_41 <= '.')) && ((runtimeUserOpAhead()))) {s = 29;}
						else s = 47;
						 
						input.seek(index14_41);
						if ( s>=0 ) return s;
						break;

					case 20 : 
						int LA14_59 = input.LA(1);
						 
						int index14_59 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_59=='a') && (((runtimeMATypeOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 72;}
						else if ( ((LA14_59 >= '0' && LA14_59 <= '9')||(LA14_59 >= 'A' && LA14_59 <= 'Z')||LA14_59=='_'||(LA14_59 >= 'b' && LA14_59 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 28;}
						else if ( ((LA14_59 >= '-' && LA14_59 <= '.')) && ((runtimeUserOpAhead()))) {s = 29;}
						else s = 47;
						 
						input.seek(index14_59);
						if ( s>=0 ) return s;
						break;

					case 21 : 
						int LA14_68 = input.LA(1);
						 
						int index14_68 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA14_68 >= '0' && LA14_68 <= '9')||(LA14_68 >= 'A' && LA14_68 <= 'Z')||LA14_68=='_'||(LA14_68 >= 'a' && LA14_68 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 28;}
						else if ( ((LA14_68 >= '-' && LA14_68 <= '.')) && ((runtimeUserOpAhead()))) {s = 29;}
						else s = 58;
						 
						input.seek(index14_68);
						if ( s>=0 ) return s;
						break;

					case 22 : 
						int LA14_42 = input.LA(1);
						 
						int index14_42 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_42=='m') && (((runtimeMATypeOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 60;}
						else if ( ((LA14_42 >= '0' && LA14_42 <= '9')||(LA14_42 >= 'A' && LA14_42 <= 'Z')||LA14_42=='_'||(LA14_42 >= 'a' && LA14_42 <= 'l')||(LA14_42 >= 'n' && LA14_42 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 28;}
						else if ( ((LA14_42 >= '-' && LA14_42 <= '.')) && ((runtimeUserOpAhead()))) {s = 29;}
						else s = 47;
						 
						input.seek(index14_42);
						if ( s>=0 ) return s;
						break;

					case 23 : 
						int LA14_60 = input.LA(1);
						 
						int index14_60 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_60=='a') && (((runtimeMATypeOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 73;}
						else if ( ((LA14_60 >= '0' && LA14_60 <= '9')||(LA14_60 >= 'A' && LA14_60 <= 'Z')||LA14_60=='_'||(LA14_60 >= 'b' && LA14_60 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 28;}
						else if ( ((LA14_60 >= '-' && LA14_60 <= '.')) && ((runtimeUserOpAhead()))) {s = 29;}
						else s = 47;
						 
						input.seek(index14_60);
						if ( s>=0 ) return s;
						break;

					case 24 : 
						int LA14_69 = input.LA(1);
						 
						int index14_69 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA14_69 >= '0' && LA14_69 <= '9')||(LA14_69 >= 'A' && LA14_69 <= 'Z')||LA14_69=='_'||(LA14_69 >= 'a' && LA14_69 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 28;}
						else if ( ((LA14_69 >= '-' && LA14_69 <= '.')) && ((runtimeUserOpAhead()))) {s = 29;}
						else s = 58;
						 
						input.seek(index14_69);
						if ( s>=0 ) return s;
						break;

					case 25 : 
						int LA14_18 = input.LA(1);
						 
						int index14_18 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_18=='a') && (((runtimeNaNAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 43;}
						else if ( ((LA14_18 >= '0' && LA14_18 <= '9')||(LA14_18 >= 'A' && LA14_18 <= 'Z')||LA14_18=='_'||(LA14_18 >= 'b' && LA14_18 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 28;}
						else if ( ((LA14_18 >= '-' && LA14_18 <= '.')) && ((runtimeUserOpAhead()))) {s = 29;}
						 
						input.seek(index14_18);
						if ( s>=0 ) return s;
						break;

					case 26 : 
						int LA14_78 = input.LA(1);
						 
						int index14_78 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA14_78 >= '0' && LA14_78 <= '9')||(LA14_78 >= 'A' && LA14_78 <= 'Z')||LA14_78=='_'||(LA14_78 >= 'a' && LA14_78 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 28;}
						else if ( ((LA14_78 >= '-' && LA14_78 <= '.')) && ((runtimeUserOpAhead()))) {s = 29;}
						else s = 58;
						 
						input.seek(index14_78);
						if ( s>=0 ) return s;
						break;

					case 27 : 
						int LA14_72 = input.LA(1);
						 
						int index14_72 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA14_72 >= '0' && LA14_72 <= '9')||(LA14_72 >= 'A' && LA14_72 <= 'Z')||LA14_72=='_'||(LA14_72 >= 'a' && LA14_72 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 28;}
						else if ( ((LA14_72 >= '-' && LA14_72 <= '.')) && ((runtimeUserOpAhead()))) {s = 29;}
						else s = 58;
						 
						input.seek(index14_72);
						if ( s>=0 ) return s;
						break;

					case 28 : 
						int LA14_73 = input.LA(1);
						 
						int index14_73 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA14_73 >= '0' && LA14_73 <= '9')||(LA14_73 >= 'A' && LA14_73 <= 'Z')||LA14_73=='_'||(LA14_73 >= 'a' && LA14_73 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 28;}
						else if ( ((LA14_73 >= '-' && LA14_73 <= '.')) && ((runtimeUserOpAhead()))) {s = 29;}
						else s = 58;
						 
						input.seek(index14_73);
						if ( s>=0 ) return s;
						break;

					case 29 : 
						int LA14_75 = input.LA(1);
						 
						int index14_75 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA14_75 >= '0' && LA14_75 <= '9')||(LA14_75 >= 'A' && LA14_75 <= 'Z')||LA14_75=='_'||(LA14_75 >= 'a' && LA14_75 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 28;}
						else if ( ((LA14_75 >= '-' && LA14_75 <= '.')) && ((runtimeUserOpAhead()))) {s = 29;}
						else s = 66;
						 
						input.seek(index14_75);
						if ( s>=0 ) return s;
						break;

					case 30 : 
						int LA14_40 = input.LA(1);
						 
						int index14_40 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA14_40 >= '0' && LA14_40 <= '9')||(LA14_40 >= 'A' && LA14_40 <= 'Z')||LA14_40=='_'||(LA14_40 >= 'a' && LA14_40 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 28;}
						else if ( ((LA14_40 >= '-' && LA14_40 <= '.')) && ((runtimeUserOpAhead()))) {s = 29;}
						else s = 58;
						 
						input.seek(index14_40);
						if ( s>=0 ) return s;
						break;

					case 31 : 
						int LA14_43 = input.LA(1);
						 
						int index14_43 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_43=='N') && (((runtimeNaNAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 61;}
						else if ( ((LA14_43 >= '0' && LA14_43 <= '9')||(LA14_43 >= 'A' && LA14_43 <= 'M')||(LA14_43 >= 'O' && LA14_43 <= 'Z')||LA14_43=='_'||(LA14_43 >= 'a' && LA14_43 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 28;}
						else if ( ((LA14_43 >= '-' && LA14_43 <= '.')) && ((runtimeUserOpAhead()))) {s = 29;}
						else s = 47;
						 
						input.seek(index14_43);
						if ( s>=0 ) return s;
						break;

					case 32 : 
						int LA14_64 = input.LA(1);
						 
						int index14_64 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA14_64 >= '0' && LA14_64 <= '9')||(LA14_64 >= 'A' && LA14_64 <= 'Z')||LA14_64=='_'||(LA14_64 >= 'a' && LA14_64 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 28;}
						else if ( ((LA14_64 >= '-' && LA14_64 <= '.')) && ((runtimeUserOpAhead()))) {s = 29;}
						else s = 66;
						 
						input.seek(index14_64);
						if ( s>=0 ) return s;
						break;

					case 33 : 
						int LA14_0 = input.LA(1);
						 
						int index14_0 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_0=='(') ) {s = 1;}
						else if ( (LA14_0==')') ) {s = 2;}
						else if ( (LA14_0==',') ) {s = 3;}
						else if ( (LA14_0=='[') ) {s = 4;}
						else if ( (LA14_0==']') ) {s = 5;}
						else if ( (LA14_0=='c') && (((runtimeHistoryOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 6;}
						else if ( (LA14_0=='o') && (((runtimeHistoryOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 7;}
						else if ( (LA14_0=='h') && (((runtimeHistoryOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 8;}
						else if ( (LA14_0=='l') && (((runtimeHistoryOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 9;}
						else if ( (LA14_0=='v') && (((runtimeHistoryOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 10;}
						else if ( (LA14_0=='S') && (((runtimeMATypeOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 11;}
						else if ( (LA14_0=='E') && (((runtimeMATypeOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 12;}
						else if ( (LA14_0=='W') && (((runtimeMATypeOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 13;}
						else if ( (LA14_0=='D') && (((runtimeMATypeOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 14;}
						else if ( (LA14_0=='T') && (((runtimeMATypeOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 15;}
						else if ( (LA14_0=='K') && (((runtimeMATypeOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 16;}
						else if ( (LA14_0=='M') && (((runtimeMATypeOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 17;}
						else if ( (LA14_0=='N') && (((runtimeNaNAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 18;}
						else if ( ((LA14_0 >= 'A' && LA14_0 <= 'C')||(LA14_0 >= 'F' && LA14_0 <= 'J')||LA14_0=='L'||(LA14_0 >= 'O' && LA14_0 <= 'R')||(LA14_0 >= 'U' && LA14_0 <= 'V')||(LA14_0 >= 'X' && LA14_0 <= 'Z')||LA14_0=='_'||(LA14_0 >= 'a' && LA14_0 <= 'b')||(LA14_0 >= 'd' && LA14_0 <= 'g')||(LA14_0 >= 'i' && LA14_0 <= 'k')||(LA14_0 >= 'm' && LA14_0 <= 'n')||(LA14_0 >= 'p' && LA14_0 <= 'u')||(LA14_0 >= 'w' && LA14_0 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 19;}
						else if ( (LA14_0=='-'||(LA14_0 >= '0' && LA14_0 <= '9')) ) {s = 20;}
						else if ( (LA14_0=='\"') ) {s = 21;}
						else if ( (LA14_0==':') ) {s = 22;}
						else if ( ((LA14_0 >= '\t' && LA14_0 <= '\n')||(LA14_0 >= '\f' && LA14_0 <= '\r')||LA14_0==' ') ) {s = 23;}
						else if ( (LA14_0=='/') ) {s = 24;}
						 
						input.seek(index14_0);
						if ( s>=0 ) return s;
						break;

					case 34 : 
						int LA14_65 = input.LA(1);
						 
						int index14_65 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA14_65 >= '0' && LA14_65 <= '9')||(LA14_65 >= 'A' && LA14_65 <= 'Z')||LA14_65=='_'||(LA14_65 >= 'a' && LA14_65 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 28;}
						else if ( ((LA14_65 >= '-' && LA14_65 <= '.')) && ((runtimeUserOpAhead()))) {s = 29;}
						else s = 66;
						 
						input.seek(index14_65);
						if ( s>=0 ) return s;
						break;

					case 35 : 
						int LA14_50 = input.LA(1);
						 
						int index14_50 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA14_50 >= '0' && LA14_50 <= '9')||(LA14_50 >= 'A' && LA14_50 <= 'Z')||LA14_50=='_'||(LA14_50 >= 'a' && LA14_50 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 28;}
						else if ( ((LA14_50 >= '-' && LA14_50 <= '.')) && ((runtimeUserOpAhead()))) {s = 29;}
						else s = 66;
						 
						input.seek(index14_50);
						if ( s>=0 ) return s;
						break;

					case 36 : 
						int LA14_61 = input.LA(1);
						 
						int index14_61 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA14_61 >= '0' && LA14_61 <= '9')||(LA14_61 >= 'A' && LA14_61 <= 'Z')||LA14_61=='_'||(LA14_61 >= 'a' && LA14_61 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 28;}
						else if ( ((LA14_61 >= '-' && LA14_61 <= '.')) && ((runtimeUserOpAhead()))) {s = 29;}
						else s = 74;
						 
						input.seek(index14_61);
						if ( s>=0 ) return s;
						break;

					case 37 : 
						int LA14_80 = input.LA(1);
						 
						int index14_80 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA14_80 >= '0' && LA14_80 <= '9')||(LA14_80 >= 'A' && LA14_80 <= 'Z')||LA14_80=='_'||(LA14_80 >= 'a' && LA14_80 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 28;}
						else if ( ((LA14_80 >= '-' && LA14_80 <= '.')) && ((runtimeUserOpAhead()))) {s = 29;}
						else s = 66;
						 
						input.seek(index14_80);
						if ( s>=0 ) return s;
						break;

					case 38 : 
						int LA14_6 = input.LA(1);
						 
						int index14_6 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_6=='l') && (((runtimeHistoryOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 27;}
						else if ( ((LA14_6 >= '0' && LA14_6 <= '9')||(LA14_6 >= 'A' && LA14_6 <= 'Z')||LA14_6=='_'||(LA14_6 >= 'a' && LA14_6 <= 'k')||(LA14_6 >= 'm' && LA14_6 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 28;}
						else if ( ((LA14_6 >= '-' && LA14_6 <= '.')) && ((runtimeUserOpAhead()))) {s = 29;}
						 
						input.seek(index14_6);
						if ( s>=0 ) return s;
						break;

					case 39 : 
						int LA14_7 = input.LA(1);
						 
						int index14_7 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_7=='p') && (((runtimeHistoryOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 30;}
						else if ( ((LA14_7 >= '0' && LA14_7 <= '9')||(LA14_7 >= 'A' && LA14_7 <= 'Z')||LA14_7=='_'||(LA14_7 >= 'a' && LA14_7 <= 'o')||(LA14_7 >= 'q' && LA14_7 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 28;}
						else if ( ((LA14_7 >= '-' && LA14_7 <= '.')) && ((runtimeUserOpAhead()))) {s = 29;}
						 
						input.seek(index14_7);
						if ( s>=0 ) return s;
						break;

					case 40 : 
						int LA14_8 = input.LA(1);
						 
						int index14_8 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_8=='i') && (((runtimeHistoryOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 31;}
						else if ( ((LA14_8 >= '0' && LA14_8 <= '9')||(LA14_8 >= 'A' && LA14_8 <= 'Z')||LA14_8=='_'||(LA14_8 >= 'a' && LA14_8 <= 'h')||(LA14_8 >= 'j' && LA14_8 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 28;}
						else if ( ((LA14_8 >= '-' && LA14_8 <= '.')) && ((runtimeUserOpAhead()))) {s = 29;}
						 
						input.seek(index14_8);
						if ( s>=0 ) return s;
						break;

					case 41 : 
						int LA14_9 = input.LA(1);
						 
						int index14_9 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_9=='o') && (((runtimeHistoryOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 32;}
						else if ( ((LA14_9 >= '0' && LA14_9 <= '9')||(LA14_9 >= 'A' && LA14_9 <= 'Z')||LA14_9=='_'||(LA14_9 >= 'a' && LA14_9 <= 'n')||(LA14_9 >= 'p' && LA14_9 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 28;}
						else if ( ((LA14_9 >= '-' && LA14_9 <= '.')) && ((runtimeUserOpAhead()))) {s = 29;}
						 
						input.seek(index14_9);
						if ( s>=0 ) return s;
						break;

					case 42 : 
						int LA14_27 = input.LA(1);
						 
						int index14_27 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_27=='o') && (((runtimeHistoryOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 46;}
						else if ( ((LA14_27 >= '0' && LA14_27 <= '9')||(LA14_27 >= 'A' && LA14_27 <= 'Z')||LA14_27=='_'||(LA14_27 >= 'a' && LA14_27 <= 'n')||(LA14_27 >= 'p' && LA14_27 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 28;}
						else if ( ((LA14_27 >= '-' && LA14_27 <= '.')) && ((runtimeUserOpAhead()))) {s = 29;}
						else s = 47;
						 
						input.seek(index14_27);
						if ( s>=0 ) return s;
						break;

					case 43 : 
						int LA14_46 = input.LA(1);
						 
						int index14_46 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_46=='s') && (((runtimeHistoryOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 62;}
						else if ( ((LA14_46 >= '0' && LA14_46 <= '9')||(LA14_46 >= 'A' && LA14_46 <= 'Z')||LA14_46=='_'||(LA14_46 >= 'a' && LA14_46 <= 'r')||(LA14_46 >= 't' && LA14_46 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 28;}
						else if ( ((LA14_46 >= '-' && LA14_46 <= '.')) && ((runtimeUserOpAhead()))) {s = 29;}
						else s = 47;
						 
						input.seek(index14_46);
						if ( s>=0 ) return s;
						break;

					case 44 : 
						int LA14_62 = input.LA(1);
						 
						int index14_62 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_62=='e') && (((runtimeHistoryOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 75;}
						else if ( ((LA14_62 >= '0' && LA14_62 <= '9')||(LA14_62 >= 'A' && LA14_62 <= 'Z')||LA14_62=='_'||(LA14_62 >= 'a' && LA14_62 <= 'd')||(LA14_62 >= 'f' && LA14_62 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 28;}
						else if ( ((LA14_62 >= '-' && LA14_62 <= '.')) && ((runtimeUserOpAhead()))) {s = 29;}
						else s = 47;
						 
						input.seek(index14_62);
						if ( s>=0 ) return s;
						break;

					case 45 : 
						int LA14_10 = input.LA(1);
						 
						int index14_10 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_10=='o') && (((runtimeHistoryOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 33;}
						else if ( ((LA14_10 >= '0' && LA14_10 <= '9')||(LA14_10 >= 'A' && LA14_10 <= 'Z')||LA14_10=='_'||(LA14_10 >= 'a' && LA14_10 <= 'n')||(LA14_10 >= 'p' && LA14_10 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 28;}
						else if ( ((LA14_10 >= '-' && LA14_10 <= '.')) && ((runtimeUserOpAhead()))) {s = 29;}
						 
						input.seek(index14_10);
						if ( s>=0 ) return s;
						break;

					case 46 : 
						int LA14_30 = input.LA(1);
						 
						int index14_30 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_30=='e') && (((runtimeHistoryOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 48;}
						else if ( ((LA14_30 >= '0' && LA14_30 <= '9')||(LA14_30 >= 'A' && LA14_30 <= 'Z')||LA14_30=='_'||(LA14_30 >= 'a' && LA14_30 <= 'd')||(LA14_30 >= 'f' && LA14_30 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 28;}
						else if ( ((LA14_30 >= '-' && LA14_30 <= '.')) && ((runtimeUserOpAhead()))) {s = 29;}
						else s = 47;
						 
						input.seek(index14_30);
						if ( s>=0 ) return s;
						break;

					case 47 : 
						int LA14_48 = input.LA(1);
						 
						int index14_48 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_48=='n') && (((runtimeHistoryOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 64;}
						else if ( ((LA14_48 >= '0' && LA14_48 <= '9')||(LA14_48 >= 'A' && LA14_48 <= 'Z')||LA14_48=='_'||(LA14_48 >= 'a' && LA14_48 <= 'm')||(LA14_48 >= 'o' && LA14_48 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 28;}
						else if ( ((LA14_48 >= '-' && LA14_48 <= '.')) && ((runtimeUserOpAhead()))) {s = 29;}
						else s = 47;
						 
						input.seek(index14_48);
						if ( s>=0 ) return s;
						break;

					case 48 : 
						int LA14_66 = input.LA(1);
						 
						int index14_66 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeHistoryOpAhead())) ) {s = 76;}
						else if ( ((runtimeNativeOpAhead())) ) {s = 63;}
						else if ( ((runtimeUserOpAhead())) ) {s = 29;}
						 
						input.seek(index14_66);
						if ( s>=0 ) return s;
						break;

					case 49 : 
						int LA14_31 = input.LA(1);
						 
						int index14_31 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_31=='g') && (((runtimeHistoryOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 49;}
						else if ( ((LA14_31 >= '0' && LA14_31 <= '9')||(LA14_31 >= 'A' && LA14_31 <= 'Z')||LA14_31=='_'||(LA14_31 >= 'a' && LA14_31 <= 'f')||(LA14_31 >= 'h' && LA14_31 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 28;}
						else if ( ((LA14_31 >= '-' && LA14_31 <= '.')) && ((runtimeUserOpAhead()))) {s = 29;}
						else s = 47;
						 
						input.seek(index14_31);
						if ( s>=0 ) return s;
						break;

					case 50 : 
						int LA14_58 = input.LA(1);
						 
						int index14_58 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeMATypeOpAhead())) ) {s = 71;}
						else if ( ((runtimeNativeOpAhead())) ) {s = 63;}
						else if ( ((runtimeUserOpAhead())) ) {s = 29;}
						 
						input.seek(index14_58);
						if ( s>=0 ) return s;
						break;

					case 51 : 
						int LA14_49 = input.LA(1);
						 
						int index14_49 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_49=='h') && (((runtimeHistoryOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 65;}
						else if ( ((LA14_49 >= '0' && LA14_49 <= '9')||(LA14_49 >= 'A' && LA14_49 <= 'Z')||LA14_49=='_'||(LA14_49 >= 'a' && LA14_49 <= 'g')||(LA14_49 >= 'i' && LA14_49 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 28;}
						else if ( ((LA14_49 >= '-' && LA14_49 <= '.')) && ((runtimeUserOpAhead()))) {s = 29;}
						else s = 47;
						 
						input.seek(index14_49);
						if ( s>=0 ) return s;
						break;

					case 52 : 
						int LA14_74 = input.LA(1);
						 
						int index14_74 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeNaNAhead())) ) {s = 79;}
						else if ( ((runtimeNativeOpAhead())) ) {s = 63;}
						else if ( ((runtimeUserOpAhead())) ) {s = 29;}
						 
						input.seek(index14_74);
						if ( s>=0 ) return s;
						break;

					case 53 : 
						int LA14_32 = input.LA(1);
						 
						int index14_32 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_32=='w') && (((runtimeHistoryOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 50;}
						else if ( ((LA14_32 >= '0' && LA14_32 <= '9')||(LA14_32 >= 'A' && LA14_32 <= 'Z')||LA14_32=='_'||(LA14_32 >= 'a' && LA14_32 <= 'v')||(LA14_32 >= 'x' && LA14_32 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 28;}
						else if ( ((LA14_32 >= '-' && LA14_32 <= '.')) && ((runtimeUserOpAhead()))) {s = 29;}
						else s = 47;
						 
						input.seek(index14_32);
						if ( s>=0 ) return s;
						break;

					case 54 : 
						int LA14_33 = input.LA(1);
						 
						int index14_33 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_33=='l') && (((runtimeHistoryOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 51;}
						else if ( ((LA14_33 >= '0' && LA14_33 <= '9')||(LA14_33 >= 'A' && LA14_33 <= 'Z')||LA14_33=='_'||(LA14_33 >= 'a' && LA14_33 <= 'k')||(LA14_33 >= 'm' && LA14_33 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 28;}
						else if ( ((LA14_33 >= '-' && LA14_33 <= '.')) && ((runtimeUserOpAhead()))) {s = 29;}
						else s = 47;
						 
						input.seek(index14_33);
						if ( s>=0 ) return s;
						break;

					case 55 : 
						int LA14_11 = input.LA(1);
						 
						int index14_11 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_11=='m') && (((runtimeMATypeOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 34;}
						else if ( ((LA14_11 >= '0' && LA14_11 <= '9')||(LA14_11 >= 'A' && LA14_11 <= 'Z')||LA14_11=='_'||(LA14_11 >= 'a' && LA14_11 <= 'l')||(LA14_11 >= 'n' && LA14_11 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 28;}
						else if ( ((LA14_11 >= '-' && LA14_11 <= '.')) && ((runtimeUserOpAhead()))) {s = 29;}
						 
						input.seek(index14_11);
						if ( s>=0 ) return s;
						break;

					case 56 : 
						int LA14_51 = input.LA(1);
						 
						int index14_51 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_51=='u') && (((runtimeHistoryOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 67;}
						else if ( ((LA14_51 >= '0' && LA14_51 <= '9')||(LA14_51 >= 'A' && LA14_51 <= 'Z')||LA14_51=='_'||(LA14_51 >= 'a' && LA14_51 <= 't')||(LA14_51 >= 'v' && LA14_51 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 28;}
						else if ( ((LA14_51 >= '-' && LA14_51 <= '.')) && ((runtimeUserOpAhead()))) {s = 29;}
						else s = 47;
						 
						input.seek(index14_51);
						if ( s>=0 ) return s;
						break;

					case 57 : 
						int LA14_67 = input.LA(1);
						 
						int index14_67 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_67=='m') && (((runtimeHistoryOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 77;}
						else if ( ((LA14_67 >= '0' && LA14_67 <= '9')||(LA14_67 >= 'A' && LA14_67 <= 'Z')||LA14_67=='_'||(LA14_67 >= 'a' && LA14_67 <= 'l')||(LA14_67 >= 'n' && LA14_67 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 28;}
						else if ( ((LA14_67 >= '-' && LA14_67 <= '.')) && ((runtimeUserOpAhead()))) {s = 29;}
						else s = 47;
						 
						input.seek(index14_67);
						if ( s>=0 ) return s;
						break;

					case 58 : 
						int LA14_77 = input.LA(1);
						 
						int index14_77 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_77=='e') && (((runtimeHistoryOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 80;}
						else if ( ((LA14_77 >= '0' && LA14_77 <= '9')||(LA14_77 >= 'A' && LA14_77 <= 'Z')||LA14_77=='_'||(LA14_77 >= 'a' && LA14_77 <= 'd')||(LA14_77 >= 'f' && LA14_77 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 28;}
						else if ( ((LA14_77 >= '-' && LA14_77 <= '.')) && ((runtimeUserOpAhead()))) {s = 29;}
						else s = 47;
						 
						input.seek(index14_77);
						if ( s>=0 ) return s;
						break;

					case 59 : 
						int LA14_12 = input.LA(1);
						 
						int index14_12 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_12=='m') && (((runtimeMATypeOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 35;}
						else if ( ((LA14_12 >= '0' && LA14_12 <= '9')||(LA14_12 >= 'A' && LA14_12 <= 'Z')||LA14_12=='_'||(LA14_12 >= 'a' && LA14_12 <= 'l')||(LA14_12 >= 'n' && LA14_12 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 28;}
						else if ( ((LA14_12 >= '-' && LA14_12 <= '.')) && ((runtimeUserOpAhead()))) {s = 29;}
						 
						input.seek(index14_12);
						if ( s>=0 ) return s;
						break;

					case 60 : 
						int LA14_13 = input.LA(1);
						 
						int index14_13 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_13=='m') && (((runtimeMATypeOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 36;}
						else if ( ((LA14_13 >= '0' && LA14_13 <= '9')||(LA14_13 >= 'A' && LA14_13 <= 'Z')||LA14_13=='_'||(LA14_13 >= 'a' && LA14_13 <= 'l')||(LA14_13 >= 'n' && LA14_13 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 28;}
						else if ( ((LA14_13 >= '-' && LA14_13 <= '.')) && ((runtimeUserOpAhead()))) {s = 29;}
						 
						input.seek(index14_13);
						if ( s>=0 ) return s;
						break;

					case 61 : 
						int LA14_14 = input.LA(1);
						 
						int index14_14 = input.index();
						input.rewind();
						s = -1;
						if ( (LA14_14=='e') && (((runtimeMATypeOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 37;}
						else if ( ((LA14_14 >= '0' && LA14_14 <= '9')||(LA14_14 >= 'A' && LA14_14 <= 'Z')||LA14_14=='_'||(LA14_14 >= 'a' && LA14_14 <= 'd')||(LA14_14 >= 'f' && LA14_14 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 28;}
						else if ( ((LA14_14 >= '-' && LA14_14 <= '.')) && ((runtimeUserOpAhead()))) {s = 29;}
						 
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
