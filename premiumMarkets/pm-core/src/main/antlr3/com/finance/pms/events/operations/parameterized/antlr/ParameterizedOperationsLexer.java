// $ANTLR 3.5.2 com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g 2023-11-17 17:30:44
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
	public static final int T__24=24;
	public static final int T__25=25;
	public static final int T__26=26;
	public static final int T__27=27;
	public static final int T__28=28;
	public static final int T__29=29;
	public static final int T__30=30;
	public static final int T__31=31;
	public static final int T__32=32;
	public static final int T__33=33;
	public static final int COMMENT=4;
	public static final int HistoricalData=5;
	public static final int LINE_COMMENT=6;
	public static final int ListOperation=7;
	public static final int MAType=8;
	public static final int MATypeToken=9;
	public static final int NaNNumber=10;
	public static final int NamedListOperation=11;
	public static final int Nativeop=12;
	public static final int Number=13;
	public static final int NumberToken=14;
	public static final int OperationOutput=15;
	public static final int OperationReference=16;
	public static final int OperationReferenceToken=17;
	public static final int OutputSelector=18;
	public static final int StockOperation=19;
	public static final int String=20;
	public static final int StringToken=21;
	public static final int Userop=22;
	public static final int WS=23;


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
	  public boolean runtimeOpRefOpAhead() {
	  	return lexerDelegate.runtimeOpRefOpAhead();
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

	// $ANTLR start "T__24"
	public final void mT__24() throws RecognitionException {
		try {
			int _type = T__24;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:61:7: ( '(' )
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:61:9: '('
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
	// $ANTLR end "T__24"

	// $ANTLR start "T__25"
	public final void mT__25() throws RecognitionException {
		try {
			int _type = T__25;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:62:7: ( ')' )
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:62:9: ')'
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
	// $ANTLR end "T__25"

	// $ANTLR start "T__26"
	public final void mT__26() throws RecognitionException {
		try {
			int _type = T__26;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:63:7: ( ',' )
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:63:9: ','
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
	// $ANTLR end "T__26"

	// $ANTLR start "T__27"
	public final void mT__27() throws RecognitionException {
		try {
			int _type = T__27;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:64:7: ( ':' )
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:64:9: ':'
			{
			match(':'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__27"

	// $ANTLR start "T__28"
	public final void mT__28() throws RecognitionException {
		try {
			int _type = T__28;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:65:7: ( '[' )
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:65:9: '['
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
	// $ANTLR end "T__28"

	// $ANTLR start "T__29"
	public final void mT__29() throws RecognitionException {
		try {
			int _type = T__29;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:66:7: ( '[]' )
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:66:9: '[]'
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
	// $ANTLR end "T__29"

	// $ANTLR start "T__30"
	public final void mT__30() throws RecognitionException {
		try {
			int _type = T__30;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:67:7: ( ']' )
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:67:9: ']'
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
	// $ANTLR end "T__30"

	// $ANTLR start "T__31"
	public final void mT__31() throws RecognitionException {
		try {
			int _type = T__31;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:68:7: ( '{' )
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:68:9: '{'
			{
			match('{'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__31"

	// $ANTLR start "T__32"
	public final void mT__32() throws RecognitionException {
		try {
			int _type = T__32;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:69:7: ( '{}' )
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:69:9: '{}'
			{
			match("{}"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__32"

	// $ANTLR start "T__33"
	public final void mT__33() throws RecognitionException {
		try {
			int _type = T__33;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:70:7: ( '}' )
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:70:9: '}'
			{
			match('}'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__33"

	// $ANTLR start "HistoricalData"
	public final void mHistoricalData() throws RecognitionException {
		try {
			int _type = HistoricalData;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:141:6: ({...}? => ( 'close' | 'open' | 'high' | 'low' | 'volume' ) )
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:141:8: {...}? => ( 'close' | 'open' | 'high' | 'low' | 'volume' )
			{
			if ( !((runtimeHistoryOpAhead())) ) {
				throw new FailedPredicateException(input, "HistoricalData", "runtimeHistoryOpAhead()");
			}
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:141:38: ( 'close' | 'open' | 'high' | 'low' | 'volume' )
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
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:141:39: 'close'
					{
					match("close"); 

					}
					break;
				case 2 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:141:49: 'open'
					{
					match("open"); 

					}
					break;
				case 3 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:141:58: 'high'
					{
					match("high"); 

					}
					break;
				case 4 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:141:67: 'low'
					{
					match("low"); 

					}
					break;
				case 5 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:141:75: 'volume'
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
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:144:6: ({...}? => ( 'Sma' | 'Ema' | 'Wma' | 'Dema' | 'Tema' | 'Trima' | 'Kama' | 'Mama' | 'T3' ) )
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:144:8: {...}? => ( 'Sma' | 'Ema' | 'Wma' | 'Dema' | 'Tema' | 'Trima' | 'Kama' | 'Mama' | 'T3' )
			{
			if ( !((runtimeMATypeOpAhead())) ) {
				throw new FailedPredicateException(input, "MATypeToken", "runtimeMATypeOpAhead()");
			}
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:144:37: ( 'Sma' | 'Ema' | 'Wma' | 'Dema' | 'Tema' | 'Trima' | 'Kama' | 'Mama' | 'T3' )
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
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:144:38: 'Sma'
					{
					match("Sma"); 

					}
					break;
				case 2 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:144:44: 'Ema'
					{
					match("Ema"); 

					}
					break;
				case 3 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:144:50: 'Wma'
					{
					match("Wma"); 

					}
					break;
				case 4 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:144:56: 'Dema'
					{
					match("Dema"); 

					}
					break;
				case 5 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:144:63: 'Tema'
					{
					match("Tema"); 

					}
					break;
				case 6 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:144:71: 'Trima'
					{
					match("Trima"); 

					}
					break;
				case 7 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:144:80: 'Kama'
					{
					match("Kama"); 

					}
					break;
				case 8 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:144:88: 'Mama'
					{
					match("Mama"); 

					}
					break;
				case 9 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:144:96: 'T3'
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
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:147:6: ({...}? => ( 'NaN' ) )
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:147:8: {...}? => ( 'NaN' )
			{
			if ( !((runtimeNaNAhead())) ) {
				throw new FailedPredicateException(input, "NaNNumber", "runtimeNaNAhead()");
			}
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:147:32: ( 'NaN' )
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:147:33: 'NaN'
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
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:150:6: ({...}? => ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )+ )
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:150:8: {...}? => ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )+
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
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:150:65: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )+
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
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:153:6: ({...}? => ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '.' | '-' | '0' .. '9' )+ )
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:153:8: {...}? => ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '.' | '-' | '0' .. '9' )+
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
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:153:63: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '.' | '-' | '0' .. '9' )+
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

	// $ANTLR start "OperationReferenceToken"
	public final void mOperationReferenceToken() throws RecognitionException {
		try {
			int _type = OperationReferenceToken;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:156:6: ({...}? => '$' ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '.' | '-' | '0' .. '9' )+ ( ':' ( 'a' .. 'z' | 'A' .. 'Z' )+ )? ) '$' )
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:156:8: {...}? => '$' ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '.' | '-' | '0' .. '9' )+ ( ':' ( 'a' .. 'z' | 'A' .. 'Z' )+ )? ) '$'
			{
			if ( !((runtimeOpRefOpAhead())) ) {
				throw new FailedPredicateException(input, "OperationReferenceToken", "runtimeOpRefOpAhead()");
			}
			match('$'); 
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:156:41: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '.' | '-' | '0' .. '9' )+ ( ':' ( 'a' .. 'z' | 'A' .. 'Z' )+ )? )
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:156:42: ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '.' | '-' | '0' .. '9' )+ ( ':' ( 'a' .. 'z' | 'A' .. 'Z' )+ )?
			{
			if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:156:70: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '.' | '-' | '0' .. '9' )+
			int cnt5=0;
			loop5:
			while (true) {
				int alt5=2;
				int LA5_0 = input.LA(1);
				if ( ((LA5_0 >= '-' && LA5_0 <= '.')||(LA5_0 >= '0' && LA5_0 <= '9')||(LA5_0 >= 'A' && LA5_0 <= 'Z')||LA5_0=='_'||(LA5_0 >= 'a' && LA5_0 <= 'z')) ) {
					alt5=1;
				}

				switch (alt5) {
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
					if ( cnt5 >= 1 ) break loop5;
					EarlyExitException eee = new EarlyExitException(5, input);
					throw eee;
				}
				cnt5++;
			}

			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:156:122: ( ':' ( 'a' .. 'z' | 'A' .. 'Z' )+ )?
			int alt7=2;
			int LA7_0 = input.LA(1);
			if ( (LA7_0==':') ) {
				alt7=1;
			}
			switch (alt7) {
				case 1 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:156:123: ':' ( 'a' .. 'z' | 'A' .. 'Z' )+
					{
					match(':'); 
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:156:127: ( 'a' .. 'z' | 'A' .. 'Z' )+
					int cnt6=0;
					loop6:
					while (true) {
						int alt6=2;
						int LA6_0 = input.LA(1);
						if ( ((LA6_0 >= 'A' && LA6_0 <= 'Z')||(LA6_0 >= 'a' && LA6_0 <= 'z')) ) {
							alt6=1;
						}

						switch (alt6) {
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

			match('$'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "OperationReferenceToken"

	// $ANTLR start "NumberToken"
	public final void mNumberToken() throws RecognitionException {
		try {
			int _type = NumberToken;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:159:6: ( ( '-' )? ( '0' .. '9' )+ ( '.' ( '0' .. '9' )+ )? )
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:159:8: ( '-' )? ( '0' .. '9' )+ ( '.' ( '0' .. '9' )+ )?
			{
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:159:8: ( '-' )?
			int alt8=2;
			int LA8_0 = input.LA(1);
			if ( (LA8_0=='-') ) {
				alt8=1;
			}
			switch (alt8) {
				case 1 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:159:9: '-'
					{
					match('-'); 
					}
					break;

			}

			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:159:15: ( '0' .. '9' )+
			int cnt9=0;
			loop9:
			while (true) {
				int alt9=2;
				int LA9_0 = input.LA(1);
				if ( ((LA9_0 >= '0' && LA9_0 <= '9')) ) {
					alt9=1;
				}

				switch (alt9) {
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
					if ( cnt9 >= 1 ) break loop9;
					EarlyExitException eee = new EarlyExitException(9, input);
					throw eee;
				}
				cnt9++;
			}

			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:159:27: ( '.' ( '0' .. '9' )+ )?
			int alt11=2;
			int LA11_0 = input.LA(1);
			if ( (LA11_0=='.') ) {
				alt11=1;
			}
			switch (alt11) {
				case 1 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:159:28: '.' ( '0' .. '9' )+
					{
					match('.'); 
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:159:32: ( '0' .. '9' )+
					int cnt10=0;
					loop10:
					while (true) {
						int alt10=2;
						int LA10_0 = input.LA(1);
						if ( ((LA10_0 >= '0' && LA10_0 <= '9')) ) {
							alt10=1;
						}

						switch (alt10) {
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
							if ( cnt10 >= 1 ) break loop10;
							EarlyExitException eee = new EarlyExitException(10, input);
							throw eee;
						}
						cnt10++;
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
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:162:6: ( '\"' ( 'a' .. 'z' | 'A' .. 'Z' | '.' | '_' | '/' | ',' | '=' | ( '0' .. '9' ) | '?' | ':' | '-' | '>' | '*' | '[' | ']' | '{' | '}' | '(' | ')' | '\\'' | '+' | '$' )+ '\"' )
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:162:8: '\"' ( 'a' .. 'z' | 'A' .. 'Z' | '.' | '_' | '/' | ',' | '=' | ( '0' .. '9' ) | '?' | ':' | '-' | '>' | '*' | '[' | ']' | '{' | '}' | '(' | ')' | '\\'' | '+' | '$' )+ '\"'
			{
			match('\"'); 
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:162:12: ( 'a' .. 'z' | 'A' .. 'Z' | '.' | '_' | '/' | ',' | '=' | ( '0' .. '9' ) | '?' | ':' | '-' | '>' | '*' | '[' | ']' | '{' | '}' | '(' | ')' | '\\'' | '+' | '$' )+
			int cnt12=0;
			loop12:
			while (true) {
				int alt12=2;
				int LA12_0 = input.LA(1);
				if ( (LA12_0=='$'||(LA12_0 >= '\'' && LA12_0 <= ':')||(LA12_0 >= '=' && LA12_0 <= '?')||(LA12_0 >= 'A' && LA12_0 <= '[')||LA12_0==']'||LA12_0=='_'||(LA12_0 >= 'a' && LA12_0 <= '{')||LA12_0=='}') ) {
					alt12=1;
				}

				switch (alt12) {
				case 1 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:
					{
					if ( input.LA(1)=='$'||(input.LA(1) >= '\'' && input.LA(1) <= ':')||(input.LA(1) >= '=' && input.LA(1) <= '?')||(input.LA(1) >= 'A' && input.LA(1) <= '[')||input.LA(1)==']'||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= '{')||input.LA(1)=='}' ) {
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
					if ( cnt12 >= 1 ) break loop12;
					EarlyExitException eee = new EarlyExitException(12, input);
					throw eee;
				}
				cnt12++;
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
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:165:6: ( ':' ( 'a' .. 'z' | 'A' .. 'Z' )+ )
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:165:8: ':' ( 'a' .. 'z' | 'A' .. 'Z' )+
			{
			match(':'); 
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:165:12: ( 'a' .. 'z' | 'A' .. 'Z' )+
			int cnt13=0;
			loop13:
			while (true) {
				int alt13=2;
				int LA13_0 = input.LA(1);
				if ( ((LA13_0 >= 'A' && LA13_0 <= 'Z')||(LA13_0 >= 'a' && LA13_0 <= 'z')) ) {
					alt13=1;
				}

				switch (alt13) {
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
					if ( cnt13 >= 1 ) break loop13;
					EarlyExitException eee = new EarlyExitException(13, input);
					throw eee;
				}
				cnt13++;
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
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:170:5: ( ( ' ' | '\\r' | '\\t' | '\\u000C' | '\\n' ) )
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:170:7: ( ' ' | '\\r' | '\\t' | '\\u000C' | '\\n' )
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
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:173:5: ( '/*' ( . )* '*/' )
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:173:7: '/*' ( . )* '*/'
			{
			match("/*"); 

			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:173:12: ( . )*
			loop14:
			while (true) {
				int alt14=2;
				int LA14_0 = input.LA(1);
				if ( (LA14_0=='*') ) {
					int LA14_1 = input.LA(2);
					if ( (LA14_1=='/') ) {
						alt14=2;
					}
					else if ( ((LA14_1 >= '\u0000' && LA14_1 <= '.')||(LA14_1 >= '0' && LA14_1 <= '\uFFFF')) ) {
						alt14=1;
					}

				}
				else if ( ((LA14_0 >= '\u0000' && LA14_0 <= ')')||(LA14_0 >= '+' && LA14_0 <= '\uFFFF')) ) {
					alt14=1;
				}

				switch (alt14) {
				case 1 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:173:12: .
					{
					matchAny(); 
					}
					break;

				default :
					break loop14;
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
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:176:5: ( '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n' )
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:176:7: '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n'
			{
			match("//"); 

			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:176:12: (~ ( '\\n' | '\\r' ) )*
			loop15:
			while (true) {
				int alt15=2;
				int LA15_0 = input.LA(1);
				if ( ((LA15_0 >= '\u0000' && LA15_0 <= '\t')||(LA15_0 >= '\u000B' && LA15_0 <= '\f')||(LA15_0 >= '\u000E' && LA15_0 <= '\uFFFF')) ) {
					alt15=1;
				}

				switch (alt15) {
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
					break loop15;
				}
			}

			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:176:26: ( '\\r' )?
			int alt16=2;
			int LA16_0 = input.LA(1);
			if ( (LA16_0=='\r') ) {
				alt16=1;
			}
			switch (alt16) {
				case 1 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:176:26: '\\r'
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
		// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:8: ( T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | HistoricalData | MATypeToken | NaNNumber | Nativeop | Userop | OperationReferenceToken | NumberToken | StringToken | OutputSelector | WS | COMMENT | LINE_COMMENT )
		int alt17=22;
		alt17 = dfa17.predict(input);
		switch (alt17) {
			case 1 :
				// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:10: T__24
				{
				mT__24(); 

				}
				break;
			case 2 :
				// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:16: T__25
				{
				mT__25(); 

				}
				break;
			case 3 :
				// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:22: T__26
				{
				mT__26(); 

				}
				break;
			case 4 :
				// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:28: T__27
				{
				mT__27(); 

				}
				break;
			case 5 :
				// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:34: T__28
				{
				mT__28(); 

				}
				break;
			case 6 :
				// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:40: T__29
				{
				mT__29(); 

				}
				break;
			case 7 :
				// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:46: T__30
				{
				mT__30(); 

				}
				break;
			case 8 :
				// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:52: T__31
				{
				mT__31(); 

				}
				break;
			case 9 :
				// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:58: T__32
				{
				mT__32(); 

				}
				break;
			case 10 :
				// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:64: T__33
				{
				mT__33(); 

				}
				break;
			case 11 :
				// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:70: HistoricalData
				{
				mHistoricalData(); 

				}
				break;
			case 12 :
				// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:85: MATypeToken
				{
				mMATypeToken(); 

				}
				break;
			case 13 :
				// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:97: NaNNumber
				{
				mNaNNumber(); 

				}
				break;
			case 14 :
				// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:107: Nativeop
				{
				mNativeop(); 

				}
				break;
			case 15 :
				// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:116: Userop
				{
				mUserop(); 

				}
				break;
			case 16 :
				// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:123: OperationReferenceToken
				{
				mOperationReferenceToken(); 

				}
				break;
			case 17 :
				// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:147: NumberToken
				{
				mNumberToken(); 

				}
				break;
			case 18 :
				// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:159: StringToken
				{
				mStringToken(); 

				}
				break;
			case 19 :
				// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:171: OutputSelector
				{
				mOutputSelector(); 

				}
				break;
			case 20 :
				// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:186: WS
				{
				mWS(); 

				}
				break;
			case 21 :
				// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:189: COMMENT
				{
				mCOMMENT(); 

				}
				break;
			case 22 :
				// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:197: LINE_COMMENT
				{
				mLINE_COMMENT(); 

				}
				break;

		}
	}


	protected DFA17 dfa17 = new DFA17(this);
	static final String DFA17_eotS =
		"\4\uffff\1\34\1\37\1\uffff\1\41\32\uffff\2\66\1\uffff\12\66\1\101\3\66"+
		"\2\uffff\1\66\1\uffff\2\66\1\111\1\66\3\101\3\66\1\uffff\2\66\1\121\1"+
		"\66\1\uffff\2\111\1\uffff\1\66\2\101\1\66\1\uffff\2\101\1\uffff\1\111"+
		"\1\uffff\1\66\1\101\1\uffff\1\111";
	static final String DFA17_eofS =
		"\130\uffff";
	static final String DFA17_minS =
		"\1\11\3\uffff\1\101\1\135\1\uffff\1\175\1\uffff\16\55\4\uffff\1\52\6\uffff"+
		"\2\55\1\uffff\16\55\2\uffff\1\55\1\0\12\55\1\0\4\55\1\uffff\2\55\1\0\4"+
		"\55\1\uffff\2\55\1\0\1\55\1\uffff\2\55\1\uffff\1\55";
	static final String DFA17_maxS =
		"\1\175\3\uffff\1\172\1\135\1\uffff\1\175\1\uffff\16\172\4\uffff\1\57\6"+
		"\uffff\2\172\1\uffff\16\172\2\uffff\1\172\1\0\12\172\1\0\4\172\1\uffff"+
		"\2\172\1\0\4\172\1\uffff\2\172\1\0\1\172\1\uffff\2\172\1\uffff\1\172";
	static final String DFA17_acceptS =
		"\1\uffff\1\1\1\2\1\3\2\uffff\1\7\1\uffff\1\12\16\uffff\1\20\1\21\1\22"+
		"\1\24\1\uffff\1\4\1\23\1\6\1\5\1\11\1\10\2\uffff\1\17\16\uffff\1\25\1"+
		"\26\21\uffff\1\16\7\uffff\1\14\4\uffff\1\13\2\uffff\1\15\1\uffff";
	static final String DFA17_specialS =
		"\1\52\10\uffff\1\7\1\15\1\17\1\20\1\23\1\33\1\35\1\41\1\44\1\72\1\53\1"+
		"\54\1\61\1\31\13\uffff\1\66\1\21\1\uffff\1\73\1\2\1\5\1\10\1\22\1\24\1"+
		"\25\1\26\1\32\1\36\1\1\1\42\1\45\1\55\2\uffff\1\67\1\30\1\0\1\4\1\11\1"+
		"\12\1\56\1\57\1\60\1\27\1\34\1\37\1\50\1\43\1\46\1\64\1\71\1\uffff\1\3"+
		"\1\6\1\47\1\13\1\62\1\63\1\40\1\uffff\1\70\1\74\1\51\1\75\1\uffff\1\14"+
		"\1\65\1\uffff\1\16}>";
	static final String[] DFA17_transitionS = {
			"\2\32\1\uffff\2\32\22\uffff\1\32\1\uffff\1\31\1\uffff\1\27\3\uffff\1"+
			"\1\1\2\2\uffff\1\3\1\30\1\uffff\1\33\12\30\1\4\6\uffff\3\26\1\21\1\17"+
			"\5\26\1\23\1\26\1\24\1\25\4\26\1\16\1\22\2\26\1\20\3\26\1\5\1\uffff\1"+
			"\6\1\uffff\1\26\1\uffff\2\26\1\11\4\26\1\13\3\26\1\14\2\26\1\12\6\26"+
			"\1\15\4\26\1\7\1\uffff\1\10",
			"",
			"",
			"",
			"\32\35\6\uffff\32\35",
			"\1\36",
			"",
			"\1\40",
			"",
			"\2\44\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\13\43\1\42\16"+
			"\43",
			"\2\44\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\17\43\1\45\12"+
			"\43",
			"\2\44\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\10\43\1\46\21"+
			"\43",
			"\2\44\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\16\43\1\47\13"+
			"\43",
			"\2\44\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\16\43\1\50\13"+
			"\43",
			"\2\44\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\14\43\1\51\15"+
			"\43",
			"\2\44\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\14\43\1\52\15"+
			"\43",
			"\2\44\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\14\43\1\53\15"+
			"\43",
			"\2\44\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\4\43\1\54\25"+
			"\43",
			"\2\44\1\uffff\3\43\1\57\6\43\7\uffff\32\43\4\uffff\1\43\1\uffff\4\43"+
			"\1\55\14\43\1\56\10\43",
			"\2\44\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\1\60\31\43",
			"\2\44\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\1\61\31\43",
			"\2\44\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\1\62\31\43",
			"\2\44\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
			"",
			"",
			"",
			"",
			"\1\63\4\uffff\1\64",
			"",
			"",
			"",
			"",
			"",
			"",
			"\2\44\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\16\43\1\65\13"+
			"\43",
			"\2\44\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
			"",
			"\2\44\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\4\43\1\67\25"+
			"\43",
			"\2\44\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\6\43\1\70\23"+
			"\43",
			"\2\44\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\26\43\1\71\3"+
			"\43",
			"\2\44\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\13\43\1\72\16"+
			"\43",
			"\2\44\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\1\73\31\43",
			"\2\44\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\1\74\31\43",
			"\2\44\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\1\75\31\43",
			"\2\44\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\14\43\1\76\15"+
			"\43",
			"\2\44\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\14\43\1\77\15"+
			"\43",
			"\2\44\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\10\43\1\100\21"+
			"\43",
			"\2\44\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
			"\2\44\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\14\43\1\102\15"+
			"\43",
			"\2\44\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\14\43\1\103\15"+
			"\43",
			"\2\44\1\uffff\12\43\7\uffff\15\43\1\104\14\43\4\uffff\1\43\1\uffff\32"+
			"\43",
			"",
			"",
			"\2\44\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\22\43\1\105\7"+
			"\43",
			"\1\uffff",
			"\2\44\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\15\43\1\107\14"+
			"\43",
			"\2\44\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\7\43\1\110\22"+
			"\43",
			"\2\44\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
			"\2\44\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\24\43\1\112\5"+
			"\43",
			"\2\44\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
			"\2\44\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
			"\2\44\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
			"\2\44\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\1\113\31\43",
			"\2\44\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\1\114\31\43",
			"\2\44\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\14\43\1\115\15"+
			"\43",
			"\1\uffff",
			"\2\44\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\1\117\31\43",
			"\2\44\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\1\120\31\43",
			"\2\44\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
			"\2\44\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\4\43\1\122\25"+
			"\43",
			"",
			"\2\44\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
			"\2\44\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
			"\1\uffff",
			"\2\44\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\14\43\1\124\15"+
			"\43",
			"\2\44\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
			"\2\44\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
			"\2\44\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\1\125\31\43",
			"",
			"\2\44\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
			"\2\44\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
			"\1\uffff",
			"\2\44\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
			"",
			"\2\44\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\4\43\1\127\25"+
			"\43",
			"\2\44\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
			"",
			"\2\44\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43"
	};

	static final short[] DFA17_eot = DFA.unpackEncodedString(DFA17_eotS);
	static final short[] DFA17_eof = DFA.unpackEncodedString(DFA17_eofS);
	static final char[] DFA17_min = DFA.unpackEncodedStringToUnsignedChars(DFA17_minS);
	static final char[] DFA17_max = DFA.unpackEncodedStringToUnsignedChars(DFA17_maxS);
	static final short[] DFA17_accept = DFA.unpackEncodedString(DFA17_acceptS);
	static final short[] DFA17_special = DFA.unpackEncodedString(DFA17_specialS);
	static final short[][] DFA17_transition;

	static {
		int numStates = DFA17_transitionS.length;
		DFA17_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA17_transition[i] = DFA.unpackEncodedString(DFA17_transitionS[i]);
		}
	}

	protected class DFA17 extends DFA {

		public DFA17(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 17;
			this.eot = DFA17_eot;
			this.eof = DFA17_eof;
			this.min = DFA17_min;
			this.max = DFA17_max;
			this.accept = DFA17_accept;
			this.special = DFA17_special;
			this.transition = DFA17_transition;
		}
		@Override
		public String getDescription() {
			return "1:1: Tokens : ( T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | HistoricalData | MATypeToken | NaNNumber | Nativeop | Userop | OperationReferenceToken | NumberToken | StringToken | OutputSelector | WS | COMMENT | LINE_COMMENT );";
		}
		@Override
		public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
			IntStream input = _input;
			int _s = s;
			switch ( s ) {
					case 0 : 
						int LA17_55 = input.LA(1);
						 
						int index17_55 = input.index();
						input.rewind();
						s = -1;
						if ( (LA17_55=='n') && (((runtimeHistoryOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 71;}
						else if ( ((LA17_55 >= '0' && LA17_55 <= '9')||(LA17_55 >= 'A' && LA17_55 <= 'Z')||LA17_55=='_'||(LA17_55 >= 'a' && LA17_55 <= 'm')||(LA17_55 >= 'o' && LA17_55 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 35;}
						else if ( ((LA17_55 >= '-' && LA17_55 <= '.')) && ((runtimeUserOpAhead()))) {s = 36;}
						else s = 54;
						 
						input.seek(index17_55);
						if ( s>=0 ) return s;
						break;

					case 1 : 
						int LA17_47 = input.LA(1);
						 
						int index17_47 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA17_47 >= '0' && LA17_47 <= '9')||(LA17_47 >= 'A' && LA17_47 <= 'Z')||LA17_47=='_'||(LA17_47 >= 'a' && LA17_47 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 35;}
						else if ( ((LA17_47 >= '-' && LA17_47 <= '.')) && ((runtimeUserOpAhead()))) {s = 36;}
						else s = 65;
						 
						input.seek(index17_47);
						if ( s>=0 ) return s;
						break;

					case 2 : 
						int LA17_38 = input.LA(1);
						 
						int index17_38 = input.index();
						input.rewind();
						s = -1;
						if ( (LA17_38=='g') && (((runtimeHistoryOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 56;}
						else if ( ((LA17_38 >= '0' && LA17_38 <= '9')||(LA17_38 >= 'A' && LA17_38 <= 'Z')||LA17_38=='_'||(LA17_38 >= 'a' && LA17_38 <= 'f')||(LA17_38 >= 'h' && LA17_38 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 35;}
						else if ( ((LA17_38 >= '-' && LA17_38 <= '.')) && ((runtimeUserOpAhead()))) {s = 36;}
						else s = 54;
						 
						input.seek(index17_38);
						if ( s>=0 ) return s;
						break;

					case 3 : 
						int LA17_71 = input.LA(1);
						 
						int index17_71 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA17_71 >= '0' && LA17_71 <= '9')||(LA17_71 >= 'A' && LA17_71 <= 'Z')||LA17_71=='_'||(LA17_71 >= 'a' && LA17_71 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 35;}
						else if ( ((LA17_71 >= '-' && LA17_71 <= '.')) && ((runtimeUserOpAhead()))) {s = 36;}
						else s = 73;
						 
						input.seek(index17_71);
						if ( s>=0 ) return s;
						break;

					case 4 : 
						int LA17_56 = input.LA(1);
						 
						int index17_56 = input.index();
						input.rewind();
						s = -1;
						if ( (LA17_56=='h') && (((runtimeHistoryOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 72;}
						else if ( ((LA17_56 >= '0' && LA17_56 <= '9')||(LA17_56 >= 'A' && LA17_56 <= 'Z')||LA17_56=='_'||(LA17_56 >= 'a' && LA17_56 <= 'g')||(LA17_56 >= 'i' && LA17_56 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 35;}
						else if ( ((LA17_56 >= '-' && LA17_56 <= '.')) && ((runtimeUserOpAhead()))) {s = 36;}
						else s = 54;
						 
						input.seek(index17_56);
						if ( s>=0 ) return s;
						break;

					case 5 : 
						int LA17_39 = input.LA(1);
						 
						int index17_39 = input.index();
						input.rewind();
						s = -1;
						if ( (LA17_39=='w') && (((runtimeHistoryOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 57;}
						else if ( ((LA17_39 >= '0' && LA17_39 <= '9')||(LA17_39 >= 'A' && LA17_39 <= 'Z')||LA17_39=='_'||(LA17_39 >= 'a' && LA17_39 <= 'v')||(LA17_39 >= 'x' && LA17_39 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 35;}
						else if ( ((LA17_39 >= '-' && LA17_39 <= '.')) && ((runtimeUserOpAhead()))) {s = 36;}
						else s = 54;
						 
						input.seek(index17_39);
						if ( s>=0 ) return s;
						break;

					case 6 : 
						int LA17_72 = input.LA(1);
						 
						int index17_72 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA17_72 >= '0' && LA17_72 <= '9')||(LA17_72 >= 'A' && LA17_72 <= 'Z')||LA17_72=='_'||(LA17_72 >= 'a' && LA17_72 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 35;}
						else if ( ((LA17_72 >= '-' && LA17_72 <= '.')) && ((runtimeUserOpAhead()))) {s = 36;}
						else s = 73;
						 
						input.seek(index17_72);
						if ( s>=0 ) return s;
						break;

					case 7 : 
						int LA17_9 = input.LA(1);
						 
						int index17_9 = input.index();
						input.rewind();
						s = -1;
						if ( (LA17_9=='l') && (((runtimeHistoryOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 34;}
						else if ( ((LA17_9 >= '0' && LA17_9 <= '9')||(LA17_9 >= 'A' && LA17_9 <= 'Z')||LA17_9=='_'||(LA17_9 >= 'a' && LA17_9 <= 'k')||(LA17_9 >= 'm' && LA17_9 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 35;}
						else if ( ((LA17_9 >= '-' && LA17_9 <= '.')) && ((runtimeUserOpAhead()))) {s = 36;}
						 
						input.seek(index17_9);
						if ( s>=0 ) return s;
						break;

					case 8 : 
						int LA17_40 = input.LA(1);
						 
						int index17_40 = input.index();
						input.rewind();
						s = -1;
						if ( (LA17_40=='l') && (((runtimeHistoryOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 58;}
						else if ( ((LA17_40 >= '0' && LA17_40 <= '9')||(LA17_40 >= 'A' && LA17_40 <= 'Z')||LA17_40=='_'||(LA17_40 >= 'a' && LA17_40 <= 'k')||(LA17_40 >= 'm' && LA17_40 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 35;}
						else if ( ((LA17_40 >= '-' && LA17_40 <= '.')) && ((runtimeUserOpAhead()))) {s = 36;}
						else s = 54;
						 
						input.seek(index17_40);
						if ( s>=0 ) return s;
						break;

					case 9 : 
						int LA17_57 = input.LA(1);
						 
						int index17_57 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA17_57 >= '0' && LA17_57 <= '9')||(LA17_57 >= 'A' && LA17_57 <= 'Z')||LA17_57=='_'||(LA17_57 >= 'a' && LA17_57 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 35;}
						else if ( ((LA17_57 >= '-' && LA17_57 <= '.')) && ((runtimeUserOpAhead()))) {s = 36;}
						else s = 73;
						 
						input.seek(index17_57);
						if ( s>=0 ) return s;
						break;

					case 10 : 
						int LA17_58 = input.LA(1);
						 
						int index17_58 = input.index();
						input.rewind();
						s = -1;
						if ( (LA17_58=='u') && (((runtimeHistoryOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 74;}
						else if ( ((LA17_58 >= '0' && LA17_58 <= '9')||(LA17_58 >= 'A' && LA17_58 <= 'Z')||LA17_58=='_'||(LA17_58 >= 'a' && LA17_58 <= 't')||(LA17_58 >= 'v' && LA17_58 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 35;}
						else if ( ((LA17_58 >= '-' && LA17_58 <= '.')) && ((runtimeUserOpAhead()))) {s = 36;}
						else s = 54;
						 
						input.seek(index17_58);
						if ( s>=0 ) return s;
						break;

					case 11 : 
						int LA17_74 = input.LA(1);
						 
						int index17_74 = input.index();
						input.rewind();
						s = -1;
						if ( (LA17_74=='m') && (((runtimeHistoryOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 84;}
						else if ( ((LA17_74 >= '0' && LA17_74 <= '9')||(LA17_74 >= 'A' && LA17_74 <= 'Z')||LA17_74=='_'||(LA17_74 >= 'a' && LA17_74 <= 'l')||(LA17_74 >= 'n' && LA17_74 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 35;}
						else if ( ((LA17_74 >= '-' && LA17_74 <= '.')) && ((runtimeUserOpAhead()))) {s = 36;}
						else s = 54;
						 
						input.seek(index17_74);
						if ( s>=0 ) return s;
						break;

					case 12 : 
						int LA17_84 = input.LA(1);
						 
						int index17_84 = input.index();
						input.rewind();
						s = -1;
						if ( (LA17_84=='e') && (((runtimeHistoryOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 87;}
						else if ( ((LA17_84 >= '0' && LA17_84 <= '9')||(LA17_84 >= 'A' && LA17_84 <= 'Z')||LA17_84=='_'||(LA17_84 >= 'a' && LA17_84 <= 'd')||(LA17_84 >= 'f' && LA17_84 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 35;}
						else if ( ((LA17_84 >= '-' && LA17_84 <= '.')) && ((runtimeUserOpAhead()))) {s = 36;}
						else s = 54;
						 
						input.seek(index17_84);
						if ( s>=0 ) return s;
						break;

					case 13 : 
						int LA17_10 = input.LA(1);
						 
						int index17_10 = input.index();
						input.rewind();
						s = -1;
						if ( (LA17_10=='p') && (((runtimeHistoryOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 37;}
						else if ( ((LA17_10 >= '0' && LA17_10 <= '9')||(LA17_10 >= 'A' && LA17_10 <= 'Z')||LA17_10=='_'||(LA17_10 >= 'a' && LA17_10 <= 'o')||(LA17_10 >= 'q' && LA17_10 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 35;}
						else if ( ((LA17_10 >= '-' && LA17_10 <= '.')) && ((runtimeUserOpAhead()))) {s = 36;}
						 
						input.seek(index17_10);
						if ( s>=0 ) return s;
						break;

					case 14 : 
						int LA17_87 = input.LA(1);
						 
						int index17_87 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA17_87 >= '0' && LA17_87 <= '9')||(LA17_87 >= 'A' && LA17_87 <= 'Z')||LA17_87=='_'||(LA17_87 >= 'a' && LA17_87 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 35;}
						else if ( ((LA17_87 >= '-' && LA17_87 <= '.')) && ((runtimeUserOpAhead()))) {s = 36;}
						else s = 73;
						 
						input.seek(index17_87);
						if ( s>=0 ) return s;
						break;

					case 15 : 
						int LA17_11 = input.LA(1);
						 
						int index17_11 = input.index();
						input.rewind();
						s = -1;
						if ( (LA17_11=='i') && (((runtimeHistoryOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 38;}
						else if ( ((LA17_11 >= '0' && LA17_11 <= '9')||(LA17_11 >= 'A' && LA17_11 <= 'Z')||LA17_11=='_'||(LA17_11 >= 'a' && LA17_11 <= 'h')||(LA17_11 >= 'j' && LA17_11 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 35;}
						else if ( ((LA17_11 >= '-' && LA17_11 <= '.')) && ((runtimeUserOpAhead()))) {s = 36;}
						 
						input.seek(index17_11);
						if ( s>=0 ) return s;
						break;

					case 16 : 
						int LA17_12 = input.LA(1);
						 
						int index17_12 = input.index();
						input.rewind();
						s = -1;
						if ( (LA17_12=='o') && (((runtimeHistoryOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 39;}
						else if ( ((LA17_12 >= '0' && LA17_12 <= '9')||(LA17_12 >= 'A' && LA17_12 <= 'Z')||LA17_12=='_'||(LA17_12 >= 'a' && LA17_12 <= 'n')||(LA17_12 >= 'p' && LA17_12 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 35;}
						else if ( ((LA17_12 >= '-' && LA17_12 <= '.')) && ((runtimeUserOpAhead()))) {s = 36;}
						 
						input.seek(index17_12);
						if ( s>=0 ) return s;
						break;

					case 17 : 
						int LA17_35 = input.LA(1);
						 
						int index17_35 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA17_35 >= '0' && LA17_35 <= '9')||(LA17_35 >= 'A' && LA17_35 <= 'Z')||LA17_35=='_'||(LA17_35 >= 'a' && LA17_35 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 35;}
						else if ( ((LA17_35 >= '-' && LA17_35 <= '.')) && ((runtimeUserOpAhead()))) {s = 36;}
						else s = 54;
						 
						input.seek(index17_35);
						if ( s>=0 ) return s;
						break;

					case 18 : 
						int LA17_41 = input.LA(1);
						 
						int index17_41 = input.index();
						input.rewind();
						s = -1;
						if ( (LA17_41=='a') && (((runtimeMATypeOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 59;}
						else if ( ((LA17_41 >= '0' && LA17_41 <= '9')||(LA17_41 >= 'A' && LA17_41 <= 'Z')||LA17_41=='_'||(LA17_41 >= 'b' && LA17_41 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 35;}
						else if ( ((LA17_41 >= '-' && LA17_41 <= '.')) && ((runtimeUserOpAhead()))) {s = 36;}
						else s = 54;
						 
						input.seek(index17_41);
						if ( s>=0 ) return s;
						break;

					case 19 : 
						int LA17_13 = input.LA(1);
						 
						int index17_13 = input.index();
						input.rewind();
						s = -1;
						if ( (LA17_13=='o') && (((runtimeHistoryOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 40;}
						else if ( ((LA17_13 >= '0' && LA17_13 <= '9')||(LA17_13 >= 'A' && LA17_13 <= 'Z')||LA17_13=='_'||(LA17_13 >= 'a' && LA17_13 <= 'n')||(LA17_13 >= 'p' && LA17_13 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 35;}
						else if ( ((LA17_13 >= '-' && LA17_13 <= '.')) && ((runtimeUserOpAhead()))) {s = 36;}
						 
						input.seek(index17_13);
						if ( s>=0 ) return s;
						break;

					case 20 : 
						int LA17_42 = input.LA(1);
						 
						int index17_42 = input.index();
						input.rewind();
						s = -1;
						if ( (LA17_42=='a') && (((runtimeMATypeOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 60;}
						else if ( ((LA17_42 >= '0' && LA17_42 <= '9')||(LA17_42 >= 'A' && LA17_42 <= 'Z')||LA17_42=='_'||(LA17_42 >= 'b' && LA17_42 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 35;}
						else if ( ((LA17_42 >= '-' && LA17_42 <= '.')) && ((runtimeUserOpAhead()))) {s = 36;}
						else s = 54;
						 
						input.seek(index17_42);
						if ( s>=0 ) return s;
						break;

					case 21 : 
						int LA17_43 = input.LA(1);
						 
						int index17_43 = input.index();
						input.rewind();
						s = -1;
						if ( (LA17_43=='a') && (((runtimeMATypeOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 61;}
						else if ( ((LA17_43 >= '0' && LA17_43 <= '9')||(LA17_43 >= 'A' && LA17_43 <= 'Z')||LA17_43=='_'||(LA17_43 >= 'b' && LA17_43 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 35;}
						else if ( ((LA17_43 >= '-' && LA17_43 <= '.')) && ((runtimeUserOpAhead()))) {s = 36;}
						else s = 54;
						 
						input.seek(index17_43);
						if ( s>=0 ) return s;
						break;

					case 22 : 
						int LA17_44 = input.LA(1);
						 
						int index17_44 = input.index();
						input.rewind();
						s = -1;
						if ( (LA17_44=='m') && (((runtimeMATypeOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 62;}
						else if ( ((LA17_44 >= '0' && LA17_44 <= '9')||(LA17_44 >= 'A' && LA17_44 <= 'Z')||LA17_44=='_'||(LA17_44 >= 'a' && LA17_44 <= 'l')||(LA17_44 >= 'n' && LA17_44 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 35;}
						else if ( ((LA17_44 >= '-' && LA17_44 <= '.')) && ((runtimeUserOpAhead()))) {s = 36;}
						else s = 54;
						 
						input.seek(index17_44);
						if ( s>=0 ) return s;
						break;

					case 23 : 
						int LA17_62 = input.LA(1);
						 
						int index17_62 = input.index();
						input.rewind();
						s = -1;
						if ( (LA17_62=='a') && (((runtimeMATypeOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 75;}
						else if ( ((LA17_62 >= '0' && LA17_62 <= '9')||(LA17_62 >= 'A' && LA17_62 <= 'Z')||LA17_62=='_'||(LA17_62 >= 'b' && LA17_62 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 35;}
						else if ( ((LA17_62 >= '-' && LA17_62 <= '.')) && ((runtimeUserOpAhead()))) {s = 36;}
						else s = 54;
						 
						input.seek(index17_62);
						if ( s>=0 ) return s;
						break;

					case 24 : 
						int LA17_54 = input.LA(1);
						 
						int index17_54 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeNativeOpAhead())) ) {s = 70;}
						else if ( ((runtimeUserOpAhead())) ) {s = 36;}
						 
						input.seek(index17_54);
						if ( s>=0 ) return s;
						break;

					case 25 : 
						int LA17_22 = input.LA(1);
						 
						int index17_22 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA17_22 >= '0' && LA17_22 <= '9')||(LA17_22 >= 'A' && LA17_22 <= 'Z')||LA17_22=='_'||(LA17_22 >= 'a' && LA17_22 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 35;}
						else if ( ((LA17_22 >= '-' && LA17_22 <= '.')) && ((runtimeUserOpAhead()))) {s = 36;}
						 
						input.seek(index17_22);
						if ( s>=0 ) return s;
						break;

					case 26 : 
						int LA17_45 = input.LA(1);
						 
						int index17_45 = input.index();
						input.rewind();
						s = -1;
						if ( (LA17_45=='m') && (((runtimeMATypeOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 63;}
						else if ( ((LA17_45 >= '0' && LA17_45 <= '9')||(LA17_45 >= 'A' && LA17_45 <= 'Z')||LA17_45=='_'||(LA17_45 >= 'a' && LA17_45 <= 'l')||(LA17_45 >= 'n' && LA17_45 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 35;}
						else if ( ((LA17_45 >= '-' && LA17_45 <= '.')) && ((runtimeUserOpAhead()))) {s = 36;}
						else s = 54;
						 
						input.seek(index17_45);
						if ( s>=0 ) return s;
						break;

					case 27 : 
						int LA17_14 = input.LA(1);
						 
						int index17_14 = input.index();
						input.rewind();
						s = -1;
						if ( (LA17_14=='m') && (((runtimeMATypeOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 41;}
						else if ( ((LA17_14 >= '0' && LA17_14 <= '9')||(LA17_14 >= 'A' && LA17_14 <= 'Z')||LA17_14=='_'||(LA17_14 >= 'a' && LA17_14 <= 'l')||(LA17_14 >= 'n' && LA17_14 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 35;}
						else if ( ((LA17_14 >= '-' && LA17_14 <= '.')) && ((runtimeUserOpAhead()))) {s = 36;}
						 
						input.seek(index17_14);
						if ( s>=0 ) return s;
						break;

					case 28 : 
						int LA17_63 = input.LA(1);
						 
						int index17_63 = input.index();
						input.rewind();
						s = -1;
						if ( (LA17_63=='a') && (((runtimeMATypeOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 76;}
						else if ( ((LA17_63 >= '0' && LA17_63 <= '9')||(LA17_63 >= 'A' && LA17_63 <= 'Z')||LA17_63=='_'||(LA17_63 >= 'b' && LA17_63 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 35;}
						else if ( ((LA17_63 >= '-' && LA17_63 <= '.')) && ((runtimeUserOpAhead()))) {s = 36;}
						else s = 54;
						 
						input.seek(index17_63);
						if ( s>=0 ) return s;
						break;

					case 29 : 
						int LA17_15 = input.LA(1);
						 
						int index17_15 = input.index();
						input.rewind();
						s = -1;
						if ( (LA17_15=='m') && (((runtimeMATypeOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 42;}
						else if ( ((LA17_15 >= '0' && LA17_15 <= '9')||(LA17_15 >= 'A' && LA17_15 <= 'Z')||LA17_15=='_'||(LA17_15 >= 'a' && LA17_15 <= 'l')||(LA17_15 >= 'n' && LA17_15 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 35;}
						else if ( ((LA17_15 >= '-' && LA17_15 <= '.')) && ((runtimeUserOpAhead()))) {s = 36;}
						 
						input.seek(index17_15);
						if ( s>=0 ) return s;
						break;

					case 30 : 
						int LA17_46 = input.LA(1);
						 
						int index17_46 = input.index();
						input.rewind();
						s = -1;
						if ( (LA17_46=='i') && (((runtimeMATypeOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 64;}
						else if ( ((LA17_46 >= '0' && LA17_46 <= '9')||(LA17_46 >= 'A' && LA17_46 <= 'Z')||LA17_46=='_'||(LA17_46 >= 'a' && LA17_46 <= 'h')||(LA17_46 >= 'j' && LA17_46 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 35;}
						else if ( ((LA17_46 >= '-' && LA17_46 <= '.')) && ((runtimeUserOpAhead()))) {s = 36;}
						else s = 54;
						 
						input.seek(index17_46);
						if ( s>=0 ) return s;
						break;

					case 31 : 
						int LA17_64 = input.LA(1);
						 
						int index17_64 = input.index();
						input.rewind();
						s = -1;
						if ( (LA17_64=='m') && (((runtimeMATypeOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 77;}
						else if ( ((LA17_64 >= '0' && LA17_64 <= '9')||(LA17_64 >= 'A' && LA17_64 <= 'Z')||LA17_64=='_'||(LA17_64 >= 'a' && LA17_64 <= 'l')||(LA17_64 >= 'n' && LA17_64 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 35;}
						else if ( ((LA17_64 >= '-' && LA17_64 <= '.')) && ((runtimeUserOpAhead()))) {s = 36;}
						else s = 54;
						 
						input.seek(index17_64);
						if ( s>=0 ) return s;
						break;

					case 32 : 
						int LA17_77 = input.LA(1);
						 
						int index17_77 = input.index();
						input.rewind();
						s = -1;
						if ( (LA17_77=='a') && (((runtimeMATypeOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 85;}
						else if ( ((LA17_77 >= '0' && LA17_77 <= '9')||(LA17_77 >= 'A' && LA17_77 <= 'Z')||LA17_77=='_'||(LA17_77 >= 'b' && LA17_77 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 35;}
						else if ( ((LA17_77 >= '-' && LA17_77 <= '.')) && ((runtimeUserOpAhead()))) {s = 36;}
						else s = 54;
						 
						input.seek(index17_77);
						if ( s>=0 ) return s;
						break;

					case 33 : 
						int LA17_16 = input.LA(1);
						 
						int index17_16 = input.index();
						input.rewind();
						s = -1;
						if ( (LA17_16=='m') && (((runtimeMATypeOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 43;}
						else if ( ((LA17_16 >= '0' && LA17_16 <= '9')||(LA17_16 >= 'A' && LA17_16 <= 'Z')||LA17_16=='_'||(LA17_16 >= 'a' && LA17_16 <= 'l')||(LA17_16 >= 'n' && LA17_16 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 35;}
						else if ( ((LA17_16 >= '-' && LA17_16 <= '.')) && ((runtimeUserOpAhead()))) {s = 36;}
						 
						input.seek(index17_16);
						if ( s>=0 ) return s;
						break;

					case 34 : 
						int LA17_48 = input.LA(1);
						 
						int index17_48 = input.index();
						input.rewind();
						s = -1;
						if ( (LA17_48=='m') && (((runtimeMATypeOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 66;}
						else if ( ((LA17_48 >= '0' && LA17_48 <= '9')||(LA17_48 >= 'A' && LA17_48 <= 'Z')||LA17_48=='_'||(LA17_48 >= 'a' && LA17_48 <= 'l')||(LA17_48 >= 'n' && LA17_48 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 35;}
						else if ( ((LA17_48 >= '-' && LA17_48 <= '.')) && ((runtimeUserOpAhead()))) {s = 36;}
						else s = 54;
						 
						input.seek(index17_48);
						if ( s>=0 ) return s;
						break;

					case 35 : 
						int LA17_66 = input.LA(1);
						 
						int index17_66 = input.index();
						input.rewind();
						s = -1;
						if ( (LA17_66=='a') && (((runtimeMATypeOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 79;}
						else if ( ((LA17_66 >= '0' && LA17_66 <= '9')||(LA17_66 >= 'A' && LA17_66 <= 'Z')||LA17_66=='_'||(LA17_66 >= 'b' && LA17_66 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 35;}
						else if ( ((LA17_66 >= '-' && LA17_66 <= '.')) && ((runtimeUserOpAhead()))) {s = 36;}
						else s = 54;
						 
						input.seek(index17_66);
						if ( s>=0 ) return s;
						break;

					case 36 : 
						int LA17_17 = input.LA(1);
						 
						int index17_17 = input.index();
						input.rewind();
						s = -1;
						if ( (LA17_17=='e') && (((runtimeMATypeOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 44;}
						else if ( ((LA17_17 >= '0' && LA17_17 <= '9')||(LA17_17 >= 'A' && LA17_17 <= 'Z')||LA17_17=='_'||(LA17_17 >= 'a' && LA17_17 <= 'd')||(LA17_17 >= 'f' && LA17_17 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 35;}
						else if ( ((LA17_17 >= '-' && LA17_17 <= '.')) && ((runtimeUserOpAhead()))) {s = 36;}
						 
						input.seek(index17_17);
						if ( s>=0 ) return s;
						break;

					case 37 : 
						int LA17_49 = input.LA(1);
						 
						int index17_49 = input.index();
						input.rewind();
						s = -1;
						if ( (LA17_49=='m') && (((runtimeMATypeOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 67;}
						else if ( ((LA17_49 >= '0' && LA17_49 <= '9')||(LA17_49 >= 'A' && LA17_49 <= 'Z')||LA17_49=='_'||(LA17_49 >= 'a' && LA17_49 <= 'l')||(LA17_49 >= 'n' && LA17_49 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 35;}
						else if ( ((LA17_49 >= '-' && LA17_49 <= '.')) && ((runtimeUserOpAhead()))) {s = 36;}
						else s = 54;
						 
						input.seek(index17_49);
						if ( s>=0 ) return s;
						break;

					case 38 : 
						int LA17_67 = input.LA(1);
						 
						int index17_67 = input.index();
						input.rewind();
						s = -1;
						if ( (LA17_67=='a') && (((runtimeMATypeOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 80;}
						else if ( ((LA17_67 >= '0' && LA17_67 <= '9')||(LA17_67 >= 'A' && LA17_67 <= 'Z')||LA17_67=='_'||(LA17_67 >= 'b' && LA17_67 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 35;}
						else if ( ((LA17_67 >= '-' && LA17_67 <= '.')) && ((runtimeUserOpAhead()))) {s = 36;}
						else s = 54;
						 
						input.seek(index17_67);
						if ( s>=0 ) return s;
						break;

					case 39 : 
						int LA17_73 = input.LA(1);
						 
						int index17_73 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeHistoryOpAhead())) ) {s = 83;}
						else if ( ((runtimeNativeOpAhead())) ) {s = 70;}
						else if ( ((runtimeUserOpAhead())) ) {s = 36;}
						 
						input.seek(index17_73);
						if ( s>=0 ) return s;
						break;

					case 40 : 
						int LA17_65 = input.LA(1);
						 
						int index17_65 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeMATypeOpAhead())) ) {s = 78;}
						else if ( ((runtimeNativeOpAhead())) ) {s = 70;}
						else if ( ((runtimeUserOpAhead())) ) {s = 36;}
						 
						input.seek(index17_65);
						if ( s>=0 ) return s;
						break;

					case 41 : 
						int LA17_81 = input.LA(1);
						 
						int index17_81 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeNaNAhead())) ) {s = 86;}
						else if ( ((runtimeNativeOpAhead())) ) {s = 70;}
						else if ( ((runtimeUserOpAhead())) ) {s = 36;}
						 
						input.seek(index17_81);
						if ( s>=0 ) return s;
						break;

					case 42 : 
						int LA17_0 = input.LA(1);
						 
						int index17_0 = input.index();
						input.rewind();
						s = -1;
						if ( (LA17_0=='(') ) {s = 1;}
						else if ( (LA17_0==')') ) {s = 2;}
						else if ( (LA17_0==',') ) {s = 3;}
						else if ( (LA17_0==':') ) {s = 4;}
						else if ( (LA17_0=='[') ) {s = 5;}
						else if ( (LA17_0==']') ) {s = 6;}
						else if ( (LA17_0=='{') ) {s = 7;}
						else if ( (LA17_0=='}') ) {s = 8;}
						else if ( (LA17_0=='c') && (((runtimeHistoryOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 9;}
						else if ( (LA17_0=='o') && (((runtimeHistoryOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 10;}
						else if ( (LA17_0=='h') && (((runtimeHistoryOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 11;}
						else if ( (LA17_0=='l') && (((runtimeHistoryOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 12;}
						else if ( (LA17_0=='v') && (((runtimeHistoryOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 13;}
						else if ( (LA17_0=='S') && (((runtimeMATypeOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 14;}
						else if ( (LA17_0=='E') && (((runtimeMATypeOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 15;}
						else if ( (LA17_0=='W') && (((runtimeMATypeOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 16;}
						else if ( (LA17_0=='D') && (((runtimeMATypeOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 17;}
						else if ( (LA17_0=='T') && (((runtimeMATypeOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 18;}
						else if ( (LA17_0=='K') && (((runtimeMATypeOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 19;}
						else if ( (LA17_0=='M') && (((runtimeMATypeOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 20;}
						else if ( (LA17_0=='N') && (((runtimeNaNAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 21;}
						else if ( ((LA17_0 >= 'A' && LA17_0 <= 'C')||(LA17_0 >= 'F' && LA17_0 <= 'J')||LA17_0=='L'||(LA17_0 >= 'O' && LA17_0 <= 'R')||(LA17_0 >= 'U' && LA17_0 <= 'V')||(LA17_0 >= 'X' && LA17_0 <= 'Z')||LA17_0=='_'||(LA17_0 >= 'a' && LA17_0 <= 'b')||(LA17_0 >= 'd' && LA17_0 <= 'g')||(LA17_0 >= 'i' && LA17_0 <= 'k')||(LA17_0 >= 'm' && LA17_0 <= 'n')||(LA17_0 >= 'p' && LA17_0 <= 'u')||(LA17_0 >= 'w' && LA17_0 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 22;}
						else if ( (LA17_0=='$') && ((runtimeOpRefOpAhead()))) {s = 23;}
						else if ( (LA17_0=='-'||(LA17_0 >= '0' && LA17_0 <= '9')) ) {s = 24;}
						else if ( (LA17_0=='\"') ) {s = 25;}
						else if ( ((LA17_0 >= '\t' && LA17_0 <= '\n')||(LA17_0 >= '\f' && LA17_0 <= '\r')||LA17_0==' ') ) {s = 26;}
						else if ( (LA17_0=='/') ) {s = 27;}
						 
						input.seek(index17_0);
						if ( s>=0 ) return s;
						break;

					case 43 : 
						int LA17_19 = input.LA(1);
						 
						int index17_19 = input.index();
						input.rewind();
						s = -1;
						if ( (LA17_19=='a') && (((runtimeMATypeOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 48;}
						else if ( ((LA17_19 >= '0' && LA17_19 <= '9')||(LA17_19 >= 'A' && LA17_19 <= 'Z')||LA17_19=='_'||(LA17_19 >= 'b' && LA17_19 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 35;}
						else if ( ((LA17_19 >= '-' && LA17_19 <= '.')) && ((runtimeUserOpAhead()))) {s = 36;}
						 
						input.seek(index17_19);
						if ( s>=0 ) return s;
						break;

					case 44 : 
						int LA17_20 = input.LA(1);
						 
						int index17_20 = input.index();
						input.rewind();
						s = -1;
						if ( (LA17_20=='a') && (((runtimeMATypeOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 49;}
						else if ( ((LA17_20 >= '0' && LA17_20 <= '9')||(LA17_20 >= 'A' && LA17_20 <= 'Z')||LA17_20=='_'||(LA17_20 >= 'b' && LA17_20 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 35;}
						else if ( ((LA17_20 >= '-' && LA17_20 <= '.')) && ((runtimeUserOpAhead()))) {s = 36;}
						 
						input.seek(index17_20);
						if ( s>=0 ) return s;
						break;

					case 45 : 
						int LA17_50 = input.LA(1);
						 
						int index17_50 = input.index();
						input.rewind();
						s = -1;
						if ( (LA17_50=='N') && (((runtimeNaNAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 68;}
						else if ( ((LA17_50 >= '0' && LA17_50 <= '9')||(LA17_50 >= 'A' && LA17_50 <= 'M')||(LA17_50 >= 'O' && LA17_50 <= 'Z')||LA17_50=='_'||(LA17_50 >= 'a' && LA17_50 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 35;}
						else if ( ((LA17_50 >= '-' && LA17_50 <= '.')) && ((runtimeUserOpAhead()))) {s = 36;}
						else s = 54;
						 
						input.seek(index17_50);
						if ( s>=0 ) return s;
						break;

					case 46 : 
						int LA17_59 = input.LA(1);
						 
						int index17_59 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA17_59 >= '0' && LA17_59 <= '9')||(LA17_59 >= 'A' && LA17_59 <= 'Z')||LA17_59=='_'||(LA17_59 >= 'a' && LA17_59 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 35;}
						else if ( ((LA17_59 >= '-' && LA17_59 <= '.')) && ((runtimeUserOpAhead()))) {s = 36;}
						else s = 65;
						 
						input.seek(index17_59);
						if ( s>=0 ) return s;
						break;

					case 47 : 
						int LA17_60 = input.LA(1);
						 
						int index17_60 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA17_60 >= '0' && LA17_60 <= '9')||(LA17_60 >= 'A' && LA17_60 <= 'Z')||LA17_60=='_'||(LA17_60 >= 'a' && LA17_60 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 35;}
						else if ( ((LA17_60 >= '-' && LA17_60 <= '.')) && ((runtimeUserOpAhead()))) {s = 36;}
						else s = 65;
						 
						input.seek(index17_60);
						if ( s>=0 ) return s;
						break;

					case 48 : 
						int LA17_61 = input.LA(1);
						 
						int index17_61 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA17_61 >= '0' && LA17_61 <= '9')||(LA17_61 >= 'A' && LA17_61 <= 'Z')||LA17_61=='_'||(LA17_61 >= 'a' && LA17_61 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 35;}
						else if ( ((LA17_61 >= '-' && LA17_61 <= '.')) && ((runtimeUserOpAhead()))) {s = 36;}
						else s = 65;
						 
						input.seek(index17_61);
						if ( s>=0 ) return s;
						break;

					case 49 : 
						int LA17_21 = input.LA(1);
						 
						int index17_21 = input.index();
						input.rewind();
						s = -1;
						if ( (LA17_21=='a') && (((runtimeNaNAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 50;}
						else if ( ((LA17_21 >= '0' && LA17_21 <= '9')||(LA17_21 >= 'A' && LA17_21 <= 'Z')||LA17_21=='_'||(LA17_21 >= 'b' && LA17_21 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 35;}
						else if ( ((LA17_21 >= '-' && LA17_21 <= '.')) && ((runtimeUserOpAhead()))) {s = 36;}
						 
						input.seek(index17_21);
						if ( s>=0 ) return s;
						break;

					case 50 : 
						int LA17_75 = input.LA(1);
						 
						int index17_75 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA17_75 >= '0' && LA17_75 <= '9')||(LA17_75 >= 'A' && LA17_75 <= 'Z')||LA17_75=='_'||(LA17_75 >= 'a' && LA17_75 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 35;}
						else if ( ((LA17_75 >= '-' && LA17_75 <= '.')) && ((runtimeUserOpAhead()))) {s = 36;}
						else s = 65;
						 
						input.seek(index17_75);
						if ( s>=0 ) return s;
						break;

					case 51 : 
						int LA17_76 = input.LA(1);
						 
						int index17_76 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA17_76 >= '0' && LA17_76 <= '9')||(LA17_76 >= 'A' && LA17_76 <= 'Z')||LA17_76=='_'||(LA17_76 >= 'a' && LA17_76 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 35;}
						else if ( ((LA17_76 >= '-' && LA17_76 <= '.')) && ((runtimeUserOpAhead()))) {s = 36;}
						else s = 65;
						 
						input.seek(index17_76);
						if ( s>=0 ) return s;
						break;

					case 52 : 
						int LA17_68 = input.LA(1);
						 
						int index17_68 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA17_68 >= '0' && LA17_68 <= '9')||(LA17_68 >= 'A' && LA17_68 <= 'Z')||LA17_68=='_'||(LA17_68 >= 'a' && LA17_68 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 35;}
						else if ( ((LA17_68 >= '-' && LA17_68 <= '.')) && ((runtimeUserOpAhead()))) {s = 36;}
						else s = 81;
						 
						input.seek(index17_68);
						if ( s>=0 ) return s;
						break;

					case 53 : 
						int LA17_85 = input.LA(1);
						 
						int index17_85 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA17_85 >= '0' && LA17_85 <= '9')||(LA17_85 >= 'A' && LA17_85 <= 'Z')||LA17_85=='_'||(LA17_85 >= 'a' && LA17_85 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 35;}
						else if ( ((LA17_85 >= '-' && LA17_85 <= '.')) && ((runtimeUserOpAhead()))) {s = 36;}
						else s = 65;
						 
						input.seek(index17_85);
						if ( s>=0 ) return s;
						break;

					case 54 : 
						int LA17_34 = input.LA(1);
						 
						int index17_34 = input.index();
						input.rewind();
						s = -1;
						if ( (LA17_34=='o') && (((runtimeHistoryOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 53;}
						else if ( ((LA17_34 >= '0' && LA17_34 <= '9')||(LA17_34 >= 'A' && LA17_34 <= 'Z')||LA17_34=='_'||(LA17_34 >= 'a' && LA17_34 <= 'n')||(LA17_34 >= 'p' && LA17_34 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 35;}
						else if ( ((LA17_34 >= '-' && LA17_34 <= '.')) && ((runtimeUserOpAhead()))) {s = 36;}
						else s = 54;
						 
						input.seek(index17_34);
						if ( s>=0 ) return s;
						break;

					case 55 : 
						int LA17_53 = input.LA(1);
						 
						int index17_53 = input.index();
						input.rewind();
						s = -1;
						if ( (LA17_53=='s') && (((runtimeHistoryOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 69;}
						else if ( ((LA17_53 >= '0' && LA17_53 <= '9')||(LA17_53 >= 'A' && LA17_53 <= 'Z')||LA17_53=='_'||(LA17_53 >= 'a' && LA17_53 <= 'r')||(LA17_53 >= 't' && LA17_53 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 35;}
						else if ( ((LA17_53 >= '-' && LA17_53 <= '.')) && ((runtimeUserOpAhead()))) {s = 36;}
						else s = 54;
						 
						input.seek(index17_53);
						if ( s>=0 ) return s;
						break;

					case 56 : 
						int LA17_79 = input.LA(1);
						 
						int index17_79 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA17_79 >= '0' && LA17_79 <= '9')||(LA17_79 >= 'A' && LA17_79 <= 'Z')||LA17_79=='_'||(LA17_79 >= 'a' && LA17_79 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 35;}
						else if ( ((LA17_79 >= '-' && LA17_79 <= '.')) && ((runtimeUserOpAhead()))) {s = 36;}
						else s = 65;
						 
						input.seek(index17_79);
						if ( s>=0 ) return s;
						break;

					case 57 : 
						int LA17_69 = input.LA(1);
						 
						int index17_69 = input.index();
						input.rewind();
						s = -1;
						if ( (LA17_69=='e') && (((runtimeHistoryOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 82;}
						else if ( ((LA17_69 >= '0' && LA17_69 <= '9')||(LA17_69 >= 'A' && LA17_69 <= 'Z')||LA17_69=='_'||(LA17_69 >= 'a' && LA17_69 <= 'd')||(LA17_69 >= 'f' && LA17_69 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 35;}
						else if ( ((LA17_69 >= '-' && LA17_69 <= '.')) && ((runtimeUserOpAhead()))) {s = 36;}
						else s = 54;
						 
						input.seek(index17_69);
						if ( s>=0 ) return s;
						break;

					case 58 : 
						int LA17_18 = input.LA(1);
						 
						int index17_18 = input.index();
						input.rewind();
						s = -1;
						if ( (LA17_18=='e') && (((runtimeMATypeOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 45;}
						else if ( (LA17_18=='r') && (((runtimeMATypeOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 46;}
						else if ( (LA17_18=='3') && (((runtimeMATypeOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 47;}
						else if ( ((LA17_18 >= '0' && LA17_18 <= '2')||(LA17_18 >= '4' && LA17_18 <= '9')||(LA17_18 >= 'A' && LA17_18 <= 'Z')||LA17_18=='_'||(LA17_18 >= 'a' && LA17_18 <= 'd')||(LA17_18 >= 'f' && LA17_18 <= 'q')||(LA17_18 >= 's' && LA17_18 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 35;}
						else if ( ((LA17_18 >= '-' && LA17_18 <= '.')) && ((runtimeUserOpAhead()))) {s = 36;}
						 
						input.seek(index17_18);
						if ( s>=0 ) return s;
						break;

					case 59 : 
						int LA17_37 = input.LA(1);
						 
						int index17_37 = input.index();
						input.rewind();
						s = -1;
						if ( (LA17_37=='e') && (((runtimeHistoryOpAhead())||(runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 55;}
						else if ( ((LA17_37 >= '0' && LA17_37 <= '9')||(LA17_37 >= 'A' && LA17_37 <= 'Z')||LA17_37=='_'||(LA17_37 >= 'a' && LA17_37 <= 'd')||(LA17_37 >= 'f' && LA17_37 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 35;}
						else if ( ((LA17_37 >= '-' && LA17_37 <= '.')) && ((runtimeUserOpAhead()))) {s = 36;}
						else s = 54;
						 
						input.seek(index17_37);
						if ( s>=0 ) return s;
						break;

					case 60 : 
						int LA17_80 = input.LA(1);
						 
						int index17_80 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA17_80 >= '0' && LA17_80 <= '9')||(LA17_80 >= 'A' && LA17_80 <= 'Z')||LA17_80=='_'||(LA17_80 >= 'a' && LA17_80 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 35;}
						else if ( ((LA17_80 >= '-' && LA17_80 <= '.')) && ((runtimeUserOpAhead()))) {s = 36;}
						else s = 65;
						 
						input.seek(index17_80);
						if ( s>=0 ) return s;
						break;

					case 61 : 
						int LA17_82 = input.LA(1);
						 
						int index17_82 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA17_82 >= '0' && LA17_82 <= '9')||(LA17_82 >= 'A' && LA17_82 <= 'Z')||LA17_82=='_'||(LA17_82 >= 'a' && LA17_82 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 35;}
						else if ( ((LA17_82 >= '-' && LA17_82 <= '.')) && ((runtimeUserOpAhead()))) {s = 36;}
						else s = 73;
						 
						input.seek(index17_82);
						if ( s>=0 ) return s;
						break;
			}
			NoViableAltException nvae =
				new NoViableAltException(getDescription(), 17, _s, input);
			error(nvae);
			throw nvae;
		}
	}

}
