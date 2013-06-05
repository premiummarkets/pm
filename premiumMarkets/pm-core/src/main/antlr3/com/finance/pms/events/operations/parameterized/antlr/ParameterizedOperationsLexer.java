// $ANTLR 3.5 /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g 2013-05-27 16:09:23
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
	public static final int T__15=15;
	public static final int T__16=16;
	public static final int T__17=17;
	public static final int COMMENT=4;
	public static final int Double=5;
	public static final int HistoricalData=6;
	public static final int LINE_COMMENT=7;
	public static final int Nativeop=8;
	public static final int Number=9;
	public static final int OperationOutput=10;
	public static final int OutputSelector=11;
	public static final int StockOperation=12;
	public static final int Userop=13;
	public static final int WS=14;


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

	// $ANTLR start "T__15"
	public final void mT__15() throws RecognitionException {
		try {
			int _type = T__15;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:55:7: ( '(' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:55:9: '('
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
	// $ANTLR end "T__15"

	// $ANTLR start "T__16"
	public final void mT__16() throws RecognitionException {
		try {
			int _type = T__16;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:56:7: ( ')' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:56:9: ')'
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
	// $ANTLR end "T__16"

	// $ANTLR start "T__17"
	public final void mT__17() throws RecognitionException {
		try {
			int _type = T__17;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:57:7: ( ',' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:57:9: ','
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
	// $ANTLR end "T__17"

	// $ANTLR start "HistoricalData"
	public final void mHistoricalData() throws RecognitionException {
		try {
			int _type = HistoricalData;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:127:6: ({...}? => ( 'close' | 'open' | 'high' | 'low' | 'volume' ) )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:127:8: {...}? => ( 'close' | 'open' | 'high' | 'low' | 'volume' )
			{
			if ( !((runtimeHistoryOpAhead())) ) {
				throw new FailedPredicateException(input, "HistoricalData", "runtimeHistoryOpAhead()");
			}
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:127:38: ( 'close' | 'open' | 'high' | 'low' | 'volume' )
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
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:127:39: 'close'
					{
					match("close"); 

					}
					break;
				case 2 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:127:49: 'open'
					{
					match("open"); 

					}
					break;
				case 3 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:127:58: 'high'
					{
					match("high"); 

					}
					break;
				case 4 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:127:67: 'low'
					{
					match("low"); 

					}
					break;
				case 5 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:127:76: 'volume'
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

	// $ANTLR start "Nativeop"
	public final void mNativeop() throws RecognitionException {
		try {
			int _type = Nativeop;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:130:6: ({...}? => ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )+ )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:130:8: {...}? => ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )+
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
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:130:65: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )+
			int cnt2=0;
			loop2:
			while (true) {
				int alt2=2;
				int LA2_0 = input.LA(1);
				if ( ((LA2_0 >= '0' && LA2_0 <= '9')||(LA2_0 >= 'A' && LA2_0 <= 'Z')||LA2_0=='_'||(LA2_0 >= 'a' && LA2_0 <= 'z')) ) {
					alt2=1;
				}

				switch (alt2) {
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
					if ( cnt2 >= 1 ) break loop2;
					EarlyExitException eee = new EarlyExitException(2, input);
					throw eee;
				}
				cnt2++;
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
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:133:6: ({...}? => ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )+ )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:133:8: {...}? => ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )+
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
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:133:63: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )+
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
	// $ANTLR end "Userop"

	// $ANTLR start "Number"
	public final void mNumber() throws RecognitionException {
		try {
			int _type = Number;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:136:6: ( ( '0' .. '9' )+ ( '.' ( '0' .. '9' )+ )? )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:136:8: ( '0' .. '9' )+ ( '.' ( '0' .. '9' )+ )?
			{
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:136:8: ( '0' .. '9' )+
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
					if ( cnt4 >= 1 ) break loop4;
					EarlyExitException eee = new EarlyExitException(4, input);
					throw eee;
				}
				cnt4++;
			}

			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:136:20: ( '.' ( '0' .. '9' )+ )?
			int alt6=2;
			int LA6_0 = input.LA(1);
			if ( (LA6_0=='.') ) {
				alt6=1;
			}
			switch (alt6) {
				case 1 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:136:21: '.' ( '0' .. '9' )+
					{
					match('.'); 
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:136:25: ( '0' .. '9' )+
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
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:139:6: ( ':' ( 'a' .. 'z' | 'A' .. 'Z' )+ )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:139:8: ':' ( 'a' .. 'z' | 'A' .. 'Z' )+
			{
			match(':'); 
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:139:12: ( 'a' .. 'z' | 'A' .. 'Z' )+
			int cnt7=0;
			loop7:
			while (true) {
				int alt7=2;
				int LA7_0 = input.LA(1);
				if ( ((LA7_0 >= 'A' && LA7_0 <= 'Z')||(LA7_0 >= 'a' && LA7_0 <= 'z')) ) {
					alt7=1;
				}

				switch (alt7) {
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
					if ( cnt7 >= 1 ) break loop7;
					EarlyExitException eee = new EarlyExitException(7, input);
					throw eee;
				}
				cnt7++;
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
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:144:5: ( ( ' ' | '\\r' | '\\t' | '\\u000C' | '\\n' ) )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:144:7: ( ' ' | '\\r' | '\\t' | '\\u000C' | '\\n' )
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
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:147:5: ( '/*' ( . )* '*/' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:147:7: '/*' ( . )* '*/'
			{
			match("/*"); 

			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:147:12: ( . )*
			loop8:
			while (true) {
				int alt8=2;
				int LA8_0 = input.LA(1);
				if ( (LA8_0=='*') ) {
					int LA8_1 = input.LA(2);
					if ( (LA8_1=='/') ) {
						alt8=2;
					}
					else if ( ((LA8_1 >= '\u0000' && LA8_1 <= '.')||(LA8_1 >= '0' && LA8_1 <= '\uFFFF')) ) {
						alt8=1;
					}

				}
				else if ( ((LA8_0 >= '\u0000' && LA8_0 <= ')')||(LA8_0 >= '+' && LA8_0 <= '\uFFFF')) ) {
					alt8=1;
				}

				switch (alt8) {
				case 1 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:147:12: .
					{
					matchAny(); 
					}
					break;

				default :
					break loop8;
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
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:150:5: ( '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n' )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:150:7: '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n'
			{
			match("//"); 

			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:150:12: (~ ( '\\n' | '\\r' ) )*
			loop9:
			while (true) {
				int alt9=2;
				int LA9_0 = input.LA(1);
				if ( ((LA9_0 >= '\u0000' && LA9_0 <= '\t')||(LA9_0 >= '\u000B' && LA9_0 <= '\f')||(LA9_0 >= '\u000E' && LA9_0 <= '\uFFFF')) ) {
					alt9=1;
				}

				switch (alt9) {
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
					break loop9;
				}
			}

			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:150:26: ( '\\r' )?
			int alt10=2;
			int LA10_0 = input.LA(1);
			if ( (LA10_0=='\r') ) {
				alt10=1;
			}
			switch (alt10) {
				case 1 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:150:26: '\\r'
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
		// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:8: ( T__15 | T__16 | T__17 | HistoricalData | Nativeop | Userop | Number | OutputSelector | WS | COMMENT | LINE_COMMENT )
		int alt11=11;
		alt11 = dfa11.predict(input);
		switch (alt11) {
			case 1 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:10: T__15
				{
				mT__15(); 

				}
				break;
			case 2 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:16: T__16
				{
				mT__16(); 

				}
				break;
			case 3 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:22: T__17
				{
				mT__17(); 

				}
				break;
			case 4 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:28: HistoricalData
				{
				mHistoricalData(); 

				}
				break;
			case 5 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:43: Nativeop
				{
				mNativeop(); 

				}
				break;
			case 6 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:52: Userop
				{
				mUserop(); 

				}
				break;
			case 7 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:59: Number
				{
				mNumber(); 

				}
				break;
			case 8 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:66: OutputSelector
				{
				mOutputSelector(); 

				}
				break;
			case 9 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:81: WS
				{
				mWS(); 

				}
				break;
			case 10 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:84: COMMENT
				{
				mCOMMENT(); 

				}
				break;
			case 11 :
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:1:92: LINE_COMMENT
				{
				mLINE_COMMENT(); 

				}
				break;

		}
	}


	protected DFA11 dfa11 = new DFA11(this);
	static final String DFA11_eotS =
		"\16\uffff\6\27\2\uffff\1\27\1\uffff\2\27\1\41\2\27\2\uffff\2\41\1\uffff"+
		"\1\27\1\41\1\uffff\1\27\1\41";
	static final String DFA11_eofS =
		"\47\uffff";
	static final String DFA11_minS =
		"\1\11\3\uffff\6\60\3\uffff\1\52\6\60\2\uffff\1\60\1\0\5\60\2\uffff\2\60"+
		"\1\0\2\60\1\uffff\2\60";
	static final String DFA11_maxS =
		"\1\172\3\uffff\6\172\3\uffff\1\57\6\172\2\uffff\1\172\1\0\5\172\2\uffff"+
		"\2\172\1\0\2\172\1\uffff\2\172";
	static final String DFA11_acceptS =
		"\1\uffff\1\1\1\2\1\3\6\uffff\1\7\1\10\1\11\7\uffff\1\12\1\13\7\uffff\1"+
		"\5\1\6\5\uffff\1\4\2\uffff";
	static final String DFA11_specialS =
		"\1\20\3\uffff\1\26\1\1\1\6\1\5\1\10\1\22\4\uffff\1\30\1\11\1\27\1\13\1"+
		"\24\1\16\2\uffff\1\32\1\2\1\15\1\14\1\7\1\17\1\31\2\uffff\1\4\1\0\1\3"+
		"\1\21\1\12\1\uffff\1\23\1\25}>";
	static final String[] DFA11_transitionS = {
			"\2\14\1\uffff\2\14\22\uffff\1\14\7\uffff\1\1\1\2\2\uffff\1\3\2\uffff"+
			"\1\15\12\12\1\13\6\uffff\32\11\4\uffff\1\11\1\uffff\2\11\1\4\4\11\1\6"+
			"\3\11\1\7\2\11\1\5\6\11\1\10\4\11",
			"",
			"",
			"",
			"\12\17\7\uffff\32\17\4\uffff\1\17\1\uffff\13\17\1\16\16\17",
			"\12\17\7\uffff\32\17\4\uffff\1\17\1\uffff\17\17\1\20\12\17",
			"\12\17\7\uffff\32\17\4\uffff\1\17\1\uffff\10\17\1\21\21\17",
			"\12\17\7\uffff\32\17\4\uffff\1\17\1\uffff\16\17\1\22\13\17",
			"\12\17\7\uffff\32\17\4\uffff\1\17\1\uffff\16\17\1\23\13\17",
			"\12\17\7\uffff\32\17\4\uffff\1\17\1\uffff\32\17",
			"",
			"",
			"",
			"\1\24\4\uffff\1\25",
			"\12\17\7\uffff\32\17\4\uffff\1\17\1\uffff\16\17\1\26\13\17",
			"\12\17\7\uffff\32\17\4\uffff\1\17\1\uffff\32\17",
			"\12\17\7\uffff\32\17\4\uffff\1\17\1\uffff\4\17\1\30\25\17",
			"\12\17\7\uffff\32\17\4\uffff\1\17\1\uffff\6\17\1\31\23\17",
			"\12\17\7\uffff\32\17\4\uffff\1\17\1\uffff\26\17\1\32\3\17",
			"\12\17\7\uffff\32\17\4\uffff\1\17\1\uffff\13\17\1\33\16\17",
			"",
			"",
			"\12\17\7\uffff\32\17\4\uffff\1\17\1\uffff\22\17\1\34\7\17",
			"\1\uffff",
			"\12\17\7\uffff\32\17\4\uffff\1\17\1\uffff\15\17\1\37\14\17",
			"\12\17\7\uffff\32\17\4\uffff\1\17\1\uffff\7\17\1\40\22\17",
			"\12\17\7\uffff\32\17\4\uffff\1\17\1\uffff\32\17",
			"\12\17\7\uffff\32\17\4\uffff\1\17\1\uffff\24\17\1\42\5\17",
			"\12\17\7\uffff\32\17\4\uffff\1\17\1\uffff\4\17\1\43\25\17",
			"",
			"",
			"\12\17\7\uffff\32\17\4\uffff\1\17\1\uffff\32\17",
			"\12\17\7\uffff\32\17\4\uffff\1\17\1\uffff\32\17",
			"\1\uffff",
			"\12\17\7\uffff\32\17\4\uffff\1\17\1\uffff\14\17\1\45\15\17",
			"\12\17\7\uffff\32\17\4\uffff\1\17\1\uffff\32\17",
			"",
			"\12\17\7\uffff\32\17\4\uffff\1\17\1\uffff\4\17\1\46\25\17",
			"\12\17\7\uffff\32\17\4\uffff\1\17\1\uffff\32\17"
	};

	static final short[] DFA11_eot = DFA.unpackEncodedString(DFA11_eotS);
	static final short[] DFA11_eof = DFA.unpackEncodedString(DFA11_eofS);
	static final char[] DFA11_min = DFA.unpackEncodedStringToUnsignedChars(DFA11_minS);
	static final char[] DFA11_max = DFA.unpackEncodedStringToUnsignedChars(DFA11_maxS);
	static final short[] DFA11_accept = DFA.unpackEncodedString(DFA11_acceptS);
	static final short[] DFA11_special = DFA.unpackEncodedString(DFA11_specialS);
	static final short[][] DFA11_transition;

	static {
		int numStates = DFA11_transitionS.length;
		DFA11_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA11_transition[i] = DFA.unpackEncodedString(DFA11_transitionS[i]);
		}
	}

	protected class DFA11 extends DFA {

		public DFA11(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 11;
			this.eot = DFA11_eot;
			this.eof = DFA11_eof;
			this.min = DFA11_min;
			this.max = DFA11_max;
			this.accept = DFA11_accept;
			this.special = DFA11_special;
			this.transition = DFA11_transition;
		}
		@Override
		public String getDescription() {
			return "1:1: Tokens : ( T__15 | T__16 | T__17 | HistoricalData | Nativeop | Userop | Number | OutputSelector | WS | COMMENT | LINE_COMMENT );";
		}
		@Override
		public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
			IntStream input = _input;
			int _s = s;
			switch ( s ) {
					case 0 : 
						int LA11_32 = input.LA(1);
						 
						int index11_32 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA11_32 >= '0' && LA11_32 <= '9')||(LA11_32 >= 'A' && LA11_32 <= 'Z')||LA11_32=='_'||(LA11_32 >= 'a' && LA11_32 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 15;}
						else s = 33;
						 
						input.seek(index11_32);
						if ( s>=0 ) return s;
						break;

					case 1 : 
						int LA11_5 = input.LA(1);
						 
						int index11_5 = input.index();
						input.rewind();
						s = -1;
						if ( (LA11_5=='p') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 16;}
						else if ( ((LA11_5 >= '0' && LA11_5 <= '9')||(LA11_5 >= 'A' && LA11_5 <= 'Z')||LA11_5=='_'||(LA11_5 >= 'a' && LA11_5 <= 'o')||(LA11_5 >= 'q' && LA11_5 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 15;}
						 
						input.seek(index11_5);
						if ( s>=0 ) return s;
						break;

					case 2 : 
						int LA11_23 = input.LA(1);
						 
						int index11_23 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeNativeOpAhead())) ) {s = 29;}
						else if ( ((runtimeUserOpAhead())) ) {s = 30;}
						 
						input.seek(index11_23);
						if ( s>=0 ) return s;
						break;

					case 3 : 
						int LA11_33 = input.LA(1);
						 
						int index11_33 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeHistoryOpAhead())) ) {s = 36;}
						else if ( ((runtimeNativeOpAhead())) ) {s = 29;}
						else if ( ((runtimeUserOpAhead())) ) {s = 30;}
						 
						input.seek(index11_33);
						if ( s>=0 ) return s;
						break;

					case 4 : 
						int LA11_31 = input.LA(1);
						 
						int index11_31 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA11_31 >= '0' && LA11_31 <= '9')||(LA11_31 >= 'A' && LA11_31 <= 'Z')||LA11_31=='_'||(LA11_31 >= 'a' && LA11_31 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 15;}
						else s = 33;
						 
						input.seek(index11_31);
						if ( s>=0 ) return s;
						break;

					case 5 : 
						int LA11_7 = input.LA(1);
						 
						int index11_7 = input.index();
						input.rewind();
						s = -1;
						if ( (LA11_7=='o') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 18;}
						else if ( ((LA11_7 >= '0' && LA11_7 <= '9')||(LA11_7 >= 'A' && LA11_7 <= 'Z')||LA11_7=='_'||(LA11_7 >= 'a' && LA11_7 <= 'n')||(LA11_7 >= 'p' && LA11_7 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 15;}
						 
						input.seek(index11_7);
						if ( s>=0 ) return s;
						break;

					case 6 : 
						int LA11_6 = input.LA(1);
						 
						int index11_6 = input.index();
						input.rewind();
						s = -1;
						if ( (LA11_6=='i') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 17;}
						else if ( ((LA11_6 >= '0' && LA11_6 <= '9')||(LA11_6 >= 'A' && LA11_6 <= 'Z')||LA11_6=='_'||(LA11_6 >= 'a' && LA11_6 <= 'h')||(LA11_6 >= 'j' && LA11_6 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 15;}
						 
						input.seek(index11_6);
						if ( s>=0 ) return s;
						break;

					case 7 : 
						int LA11_26 = input.LA(1);
						 
						int index11_26 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA11_26 >= '0' && LA11_26 <= '9')||(LA11_26 >= 'A' && LA11_26 <= 'Z')||LA11_26=='_'||(LA11_26 >= 'a' && LA11_26 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 15;}
						else s = 33;
						 
						input.seek(index11_26);
						if ( s>=0 ) return s;
						break;

					case 8 : 
						int LA11_8 = input.LA(1);
						 
						int index11_8 = input.index();
						input.rewind();
						s = -1;
						if ( (LA11_8=='o') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 19;}
						else if ( ((LA11_8 >= '0' && LA11_8 <= '9')||(LA11_8 >= 'A' && LA11_8 <= 'Z')||LA11_8=='_'||(LA11_8 >= 'a' && LA11_8 <= 'n')||(LA11_8 >= 'p' && LA11_8 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 15;}
						 
						input.seek(index11_8);
						if ( s>=0 ) return s;
						break;

					case 9 : 
						int LA11_15 = input.LA(1);
						 
						int index11_15 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA11_15 >= '0' && LA11_15 <= '9')||(LA11_15 >= 'A' && LA11_15 <= 'Z')||LA11_15=='_'||(LA11_15 >= 'a' && LA11_15 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 15;}
						else s = 23;
						 
						input.seek(index11_15);
						if ( s>=0 ) return s;
						break;

					case 10 : 
						int LA11_35 = input.LA(1);
						 
						int index11_35 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA11_35 >= '0' && LA11_35 <= '9')||(LA11_35 >= 'A' && LA11_35 <= 'Z')||LA11_35=='_'||(LA11_35 >= 'a' && LA11_35 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 15;}
						else s = 33;
						 
						input.seek(index11_35);
						if ( s>=0 ) return s;
						break;

					case 11 : 
						int LA11_17 = input.LA(1);
						 
						int index11_17 = input.index();
						input.rewind();
						s = -1;
						if ( (LA11_17=='g') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 25;}
						else if ( ((LA11_17 >= '0' && LA11_17 <= '9')||(LA11_17 >= 'A' && LA11_17 <= 'Z')||LA11_17=='_'||(LA11_17 >= 'a' && LA11_17 <= 'f')||(LA11_17 >= 'h' && LA11_17 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 15;}
						else s = 23;
						 
						input.seek(index11_17);
						if ( s>=0 ) return s;
						break;

					case 12 : 
						int LA11_25 = input.LA(1);
						 
						int index11_25 = input.index();
						input.rewind();
						s = -1;
						if ( (LA11_25=='h') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 32;}
						else if ( ((LA11_25 >= '0' && LA11_25 <= '9')||(LA11_25 >= 'A' && LA11_25 <= 'Z')||LA11_25=='_'||(LA11_25 >= 'a' && LA11_25 <= 'g')||(LA11_25 >= 'i' && LA11_25 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 15;}
						else s = 23;
						 
						input.seek(index11_25);
						if ( s>=0 ) return s;
						break;

					case 13 : 
						int LA11_24 = input.LA(1);
						 
						int index11_24 = input.index();
						input.rewind();
						s = -1;
						if ( (LA11_24=='n') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 31;}
						else if ( ((LA11_24 >= '0' && LA11_24 <= '9')||(LA11_24 >= 'A' && LA11_24 <= 'Z')||LA11_24=='_'||(LA11_24 >= 'a' && LA11_24 <= 'm')||(LA11_24 >= 'o' && LA11_24 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 15;}
						else s = 23;
						 
						input.seek(index11_24);
						if ( s>=0 ) return s;
						break;

					case 14 : 
						int LA11_19 = input.LA(1);
						 
						int index11_19 = input.index();
						input.rewind();
						s = -1;
						if ( (LA11_19=='l') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 27;}
						else if ( ((LA11_19 >= '0' && LA11_19 <= '9')||(LA11_19 >= 'A' && LA11_19 <= 'Z')||LA11_19=='_'||(LA11_19 >= 'a' && LA11_19 <= 'k')||(LA11_19 >= 'm' && LA11_19 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 15;}
						else s = 23;
						 
						input.seek(index11_19);
						if ( s>=0 ) return s;
						break;

					case 15 : 
						int LA11_27 = input.LA(1);
						 
						int index11_27 = input.index();
						input.rewind();
						s = -1;
						if ( (LA11_27=='u') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 34;}
						else if ( ((LA11_27 >= '0' && LA11_27 <= '9')||(LA11_27 >= 'A' && LA11_27 <= 'Z')||LA11_27=='_'||(LA11_27 >= 'a' && LA11_27 <= 't')||(LA11_27 >= 'v' && LA11_27 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 15;}
						else s = 23;
						 
						input.seek(index11_27);
						if ( s>=0 ) return s;
						break;

					case 16 : 
						int LA11_0 = input.LA(1);
						 
						int index11_0 = input.index();
						input.rewind();
						s = -1;
						if ( (LA11_0=='(') ) {s = 1;}
						else if ( (LA11_0==')') ) {s = 2;}
						else if ( (LA11_0==',') ) {s = 3;}
						else if ( (LA11_0=='c') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 4;}
						else if ( (LA11_0=='o') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 5;}
						else if ( (LA11_0=='h') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 6;}
						else if ( (LA11_0=='l') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 7;}
						else if ( (LA11_0=='v') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 8;}
						else if ( ((LA11_0 >= 'A' && LA11_0 <= 'Z')||LA11_0=='_'||(LA11_0 >= 'a' && LA11_0 <= 'b')||(LA11_0 >= 'd' && LA11_0 <= 'g')||(LA11_0 >= 'i' && LA11_0 <= 'k')||(LA11_0 >= 'm' && LA11_0 <= 'n')||(LA11_0 >= 'p' && LA11_0 <= 'u')||(LA11_0 >= 'w' && LA11_0 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 9;}
						else if ( ((LA11_0 >= '0' && LA11_0 <= '9')) ) {s = 10;}
						else if ( (LA11_0==':') ) {s = 11;}
						else if ( ((LA11_0 >= '\t' && LA11_0 <= '\n')||(LA11_0 >= '\f' && LA11_0 <= '\r')||LA11_0==' ') ) {s = 12;}
						else if ( (LA11_0=='/') ) {s = 13;}
						 
						input.seek(index11_0);
						if ( s>=0 ) return s;
						break;

					case 17 : 
						int LA11_34 = input.LA(1);
						 
						int index11_34 = input.index();
						input.rewind();
						s = -1;
						if ( (LA11_34=='m') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 37;}
						else if ( ((LA11_34 >= '0' && LA11_34 <= '9')||(LA11_34 >= 'A' && LA11_34 <= 'Z')||LA11_34=='_'||(LA11_34 >= 'a' && LA11_34 <= 'l')||(LA11_34 >= 'n' && LA11_34 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 15;}
						else s = 23;
						 
						input.seek(index11_34);
						if ( s>=0 ) return s;
						break;

					case 18 : 
						int LA11_9 = input.LA(1);
						 
						int index11_9 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA11_9 >= '0' && LA11_9 <= '9')||(LA11_9 >= 'A' && LA11_9 <= 'Z')||LA11_9=='_'||(LA11_9 >= 'a' && LA11_9 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 15;}
						 
						input.seek(index11_9);
						if ( s>=0 ) return s;
						break;

					case 19 : 
						int LA11_37 = input.LA(1);
						 
						int index11_37 = input.index();
						input.rewind();
						s = -1;
						if ( (LA11_37=='e') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 38;}
						else if ( ((LA11_37 >= '0' && LA11_37 <= '9')||(LA11_37 >= 'A' && LA11_37 <= 'Z')||LA11_37=='_'||(LA11_37 >= 'a' && LA11_37 <= 'd')||(LA11_37 >= 'f' && LA11_37 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 15;}
						else s = 23;
						 
						input.seek(index11_37);
						if ( s>=0 ) return s;
						break;

					case 20 : 
						int LA11_18 = input.LA(1);
						 
						int index11_18 = input.index();
						input.rewind();
						s = -1;
						if ( (LA11_18=='w') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 26;}
						else if ( ((LA11_18 >= '0' && LA11_18 <= '9')||(LA11_18 >= 'A' && LA11_18 <= 'Z')||LA11_18=='_'||(LA11_18 >= 'a' && LA11_18 <= 'v')||(LA11_18 >= 'x' && LA11_18 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 15;}
						else s = 23;
						 
						input.seek(index11_18);
						if ( s>=0 ) return s;
						break;

					case 21 : 
						int LA11_38 = input.LA(1);
						 
						int index11_38 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA11_38 >= '0' && LA11_38 <= '9')||(LA11_38 >= 'A' && LA11_38 <= 'Z')||LA11_38=='_'||(LA11_38 >= 'a' && LA11_38 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 15;}
						else s = 33;
						 
						input.seek(index11_38);
						if ( s>=0 ) return s;
						break;

					case 22 : 
						int LA11_4 = input.LA(1);
						 
						int index11_4 = input.index();
						input.rewind();
						s = -1;
						if ( (LA11_4=='l') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 14;}
						else if ( ((LA11_4 >= '0' && LA11_4 <= '9')||(LA11_4 >= 'A' && LA11_4 <= 'Z')||LA11_4=='_'||(LA11_4 >= 'a' && LA11_4 <= 'k')||(LA11_4 >= 'm' && LA11_4 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 15;}
						 
						input.seek(index11_4);
						if ( s>=0 ) return s;
						break;

					case 23 : 
						int LA11_16 = input.LA(1);
						 
						int index11_16 = input.index();
						input.rewind();
						s = -1;
						if ( (LA11_16=='e') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 24;}
						else if ( ((LA11_16 >= '0' && LA11_16 <= '9')||(LA11_16 >= 'A' && LA11_16 <= 'Z')||LA11_16=='_'||(LA11_16 >= 'a' && LA11_16 <= 'd')||(LA11_16 >= 'f' && LA11_16 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 15;}
						else s = 23;
						 
						input.seek(index11_16);
						if ( s>=0 ) return s;
						break;

					case 24 : 
						int LA11_14 = input.LA(1);
						 
						int index11_14 = input.index();
						input.rewind();
						s = -1;
						if ( (LA11_14=='o') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 22;}
						else if ( ((LA11_14 >= '0' && LA11_14 <= '9')||(LA11_14 >= 'A' && LA11_14 <= 'Z')||LA11_14=='_'||(LA11_14 >= 'a' && LA11_14 <= 'n')||(LA11_14 >= 'p' && LA11_14 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 15;}
						else s = 23;
						 
						input.seek(index11_14);
						if ( s>=0 ) return s;
						break;

					case 25 : 
						int LA11_28 = input.LA(1);
						 
						int index11_28 = input.index();
						input.rewind();
						s = -1;
						if ( (LA11_28=='e') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 35;}
						else if ( ((LA11_28 >= '0' && LA11_28 <= '9')||(LA11_28 >= 'A' && LA11_28 <= 'Z')||LA11_28=='_'||(LA11_28 >= 'a' && LA11_28 <= 'd')||(LA11_28 >= 'f' && LA11_28 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 15;}
						else s = 23;
						 
						input.seek(index11_28);
						if ( s>=0 ) return s;
						break;

					case 26 : 
						int LA11_22 = input.LA(1);
						 
						int index11_22 = input.index();
						input.rewind();
						s = -1;
						if ( (LA11_22=='s') && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())||(runtimeHistoryOpAhead())))) {s = 28;}
						else if ( ((LA11_22 >= '0' && LA11_22 <= '9')||(LA11_22 >= 'A' && LA11_22 <= 'Z')||LA11_22=='_'||(LA11_22 >= 'a' && LA11_22 <= 'r')||(LA11_22 >= 't' && LA11_22 <= 'z')) && (((runtimeNativeOpAhead())||(runtimeUserOpAhead())))) {s = 15;}
						else s = 23;
						 
						input.seek(index11_22);
						if ( s>=0 ) return s;
						break;
			}
			NoViableAltException nvae =
				new NoViableAltException(getDescription(), 11, _s, input);
			error(nvae);
			throw nvae;
		}
	}

}
