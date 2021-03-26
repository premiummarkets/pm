grammar ParameterizedOperations;

options {
	language = Java ;
	output = AST ; 
	ASTLabelType=CommonTree;
}
tokens {
  Number;
  MAType;
  String;
  StockOperation;
  OperationOutput;
}

@header { //parser
    package com.finance.pms.events.operations.parameterized.antlr;
    import com.finance.pms.events.calculation.antlr.IErrorReporter;
    import com.finance.pms.events.calculation.antlr.MyErrorReporter;
    import com.finance.pms.events.calculation.antlr.OpsParserDelegate;
    import com.finance.pms.events.calculation.antlr.ParamsCountException;
    import com.finance.pms.events.calculation.antlr.UnfinishedParameterException;
    import com.finance.pms.events.calculation.antlr.InvalidOperationException;
    import com.finance.pms.events.calculation.antlr.EditorOpDescr;
    import com.finance.pms.events.calculation.antlr.MissingOutputSelectorException;
    import com.finance.pms.events.calculation.antlr.InvalidOperationException;
}

@members { //parser

    private MyErrorReporter errorReporter;
    private OpsParserDelegate parserDelegate;

	  public void setParserDelegate(OpsParserDelegate parserDelegate) {
	      this.parserDelegate = parserDelegate;
	  }
	  
	  public OpsParserDelegate getParserDelegate() {
	     return parserDelegate;
	  }

    public void setMyErrorReporter(MyErrorReporter errorReporter) {
        this.errorReporter = errorReporter;
    }

	@Override
    public void reportError(RecognitionException e) {
        if (this.errorReporter !=null) {
          this.errorReporter.report(e);
        } else {
          super.reportError(e);
        }
    }
    
    public Boolean checkParamExhaust(Token opName, List<Object> params) throws ParamsCountException, UnfinishedParameterException, InvalidOperationException {
       return  parserDelegate.checkParamExhaust(opName, params);
     }
     
    private void  outputSelectorHint(Token opToken, Token outputSelector) throws MissingOutputSelectorException, InvalidOperationException, NoViableAltException {
        parserDelegate.outputSelectorHint(opToken, outputSelector);
    }

}

@lexer::header { //lexer
    package com.finance.pms.events.operations.parameterized.antlr;
    import com.finance.pms.events.calculation.antlr.IErrorReporter;
    import com.finance.pms.events.calculation.antlr.MyErrorReporter;
    import com.finance.pms.events.calculation.antlr.OpsLexerDelegate;
    
 }

@lexer::members {

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

}

indicatorexpr : expression -> expression ;
expression : nativeop | userop;

nativeop :
 opName=Nativeop ( outSelect=OutputSelector )? {outputSelectorHint($opName, $outSelect);} '(' (pars+=params)? {checkParamExhaust($opName, $pars);} ')' -> ^(Nativeop ^(OperationOutput OutputSelector)? params?);
userop :
 opName=Userop '(' (pars+=params)? {checkParamExhaust($opName, $pars);} ')'  -> ^(Userop params?) ;

params : param (',' param)* -> param+ ;
param : NumberToken ->  ^(Number NumberToken) | NaNNumber -> ^(Number NumberToken["NaN"]) | MATypeToken -> ^(MAType MATypeToken) | StringToken -> ^(String StringToken) | operand;
operand : stockhistory -> stockhistory | expression ;
stockhistory : HistoricalData -> ^(StockOperation ^(OperationOutput HistoricalData) ^(String StringToken["\"THIS\""]));

HistoricalData
     : {runtimeHistoryOpAhead()}? => ('close' | 'open' | 'high' | 'low' | 'volume')
     ;
MATypeToken
     : {runtimeMATypeOpAhead()}? => ('Sma'|'Ema'|'Wma'|'Dema'|'Tema'| 'Trima'| 'Kama'| 'Mama'| 'T3')
     ;
NaNNumber
     : {runtimeNaNAhead()}? => ('NaN')
     ;
Nativeop 
     : {runtimeNativeOpAhead()}? => ('a'..'z' | 'A'..'Z' | '_') ('a'..'z' | 'A'..'Z' | '_' | '0'..'9')+
     ;
Userop 
     : {runtimeUserOpAhead()}? => ('a'..'z' | 'A'..'Z' | '_') ('a'..'z' | 'A'..'Z' | '_' | '.' | '-' | '0'..'9')+
     ;
NumberToken 
     : ('-')? ('0'..'9')+ ('.' ('0'..'9')+)?
     ;
StringToken
     : '"' ('a'..'z' | 'A'..'Z' | '.' | '_' | '/' | ',' | '=' | ('0'..'9') | '?' | ':' | '-' | '>')+ '"'
     ;
OutputSelector
     :	':' ('a'..'z' | 'A'..'Z')+
     ;

//additional lexical rules (hidden chars)
WS  
    : (' '|'\r'|'\t'|'\u000C'|'\n') {$channel=HIDDEN;}
    ;
COMMENT
    : '/*' .* '*/' {$channel=HIDDEN;}
    ;
LINE_COMMENT
    : '//' ~('\n'|'\r')* '\r'? '\n' {$channel=HIDDEN;}
    ;
