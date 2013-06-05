grammar ParameterizedIndicators;

options {
	language = Java ;
	output = AST ; 
	ASTLabelType=CommonTree;
	//k=2; backtrack=true; memoize=true;
}
tokens {
  Double ;
  StockOperation ;
  OperationOutput ;
  Tcheat;
  
  OrDoubleMapCondition ;
  AndDoubleMapCondition ;
  NotDoubleMapCondition;
  
  SupDoubleMapCondition ;
  InfDoubleMapCondition ;
  EqualDoubleMapCondition ;
  CrossDownDoubleMapCondition ;
  CrossUpDoubleMapCondition ;
 
  SupConstantCondition ;
  InfConstantCondition ;
  EqualConstantCondition ;
  CrossDownConstantCondition ;
  CrossUpConstantCondition ;

  UpRatioCondition ;
  DownRatioCondition ;
  ReverseCondition ;
  HigherHighCondition ;
  HigherLowCondition ;
  LowerHighCondition ;
  LowerLowCondition ;
  
  EventConditionHolder ;
  StringOperation ;
  
  OR ='or';
  AND ='and';
  NOT='not';
  COMMA=';';
  OPENPARENTEHSIS='(';
  CLOSEPARENTEHSIS=')';
  PERCENT='%';
  DAYS='days';

}
@header { //parser
     package com.finance.pms.events.calculation.parametrizedindicators.antlr;
    import com.finance.pms.events.calculation.antlr.MyErrorReporter;
    import com.finance.pms.events.calculation.antlr.IndsParserDelegate;
    import com.finance.pms.events.calculation.antlr.MissingOutputSelectorException;
    import com.finance.pms.events.calculation.antlr.UnfinishedNestedCondition;
    import com.finance.pms.events.calculation.antlr.InvalidOperationException;
}

@members {

    private MyErrorReporter errorReporter;
    private IndsParserDelegate parserDelegate;

    public void setParserDelegate(IndsParserDelegate parserDelegate) {
        this.parserDelegate = parserDelegate;
    }
    
    public IndsParserDelegate getParserDelegate() {
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
      
    private void  checkParenthesis(String type)  throws UnfinishedNestedCondition {
            parserDelegate.checkParenthesis(type);
    }
    
    private void checkOperationValidity(Token opName) throws InvalidOperationException {
             parserDelegate.checkOperationValidity(opName);
    }
}

@lexer::header { //lexer
    package com.finance.pms.events.calculation.parametrizedindicators.antlr;
    import com.finance.pms.events.calculation.antlr.IErrorReporter;
    import com.finance.pms.events.calculation.antlr.MyErrorReporter;
    import com.finance.pms.events.calculation.antlr.IndsLexerDelegate;
}

@lexer::members {
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
}

complete_expression :
   bcond=bullish_condition bearish_condition[$bcond.tree] -> ^(EventConditionHolder bullish_condition bearish_condition StringOperation) 
   ;

bullish_condition :
 'is bullish when' WhiteChar primary_expression WhiteChar* COMMA WhiteChar* -> primary_expression
 ;
bearish_condition[CommonTree bcond] :
 'is bearish when' WhiteChar  primary_expression WhiteChar* COMMA -> primary_expression |
 bearish_not_bullish[$bcond] WhiteChar* COMMA -> bearish_not_bullish
 ;
 
bearish_not_bullish[CommonTree bcond] :
 'is bearish if not bullish' 
  (
  WhiteChar AND WhiteChar primary_expression ->  ^(AndDoubleMapCondition ^(NotDoubleMapCondition {$bcond}) primary_expression)| 
  WhiteChar OR WhiteChar  primary_expression ->  ^(OrDoubleMapCondition ^(NotDoubleMapCondition {$bcond}) primary_expression)|
  -> ^(NotDoubleMapCondition {$bcond})
  )
 ;

primary_expression : 
 and_expression
 ;
 
and_expression :  or_expression (WhiteChar AND WhiteChar or_expression)*  ->  ^(AndDoubleMapCondition or_expression or_expression*);
or_expression :  atom (WhiteChar OR WhiteChar atom)*                      ->  ^(OrDoubleMapCondition atom atom*);
atom : booleanhistory | '(' WhiteChar* primary_expression WhiteChar* ')' ->  primary_expression | 'not' WhiteChar* '(' WhiteChar* primary_expression WhiteChar* ')' -> ^(NotDoubleMapCondition primary_expression);
 
booleanhistory : firstOp=operand WhiteChar ( presetcondition[$firstOp.tree]  ->  presetcondition | opcmpcondition[$firstOp.tree]   ->  opcmpcondition| constantcmp[$firstOp.tree]   ->  constantcmp );
operand : HistoricalData -> ^(StockOperation ^(OperationOutput HistoricalData)) | opName = Operation {checkOperationValidity($opName);} -> Operation;
constant :  Number -> ^(Double Number) ;

opcmpcondition [CommonTree firstOp] : 
  'is above historical' WhiteChar operand -> ^(SupDoubleMapCondition {$firstOp} operand) |
  'is below historical' WhiteChar operand -> ^(InfDoubleMapCondition {$firstOp} operand) |
  ('crosses down historical' WhiteChar operand -> ^(CrossDownDoubleMapCondition ^(Double Number["1.0"]) {$firstOp} operand))
        ( WhiteChar 'over' WhiteChar nbDays=constant WhiteChar DAYS -> ^(CrossDownDoubleMapCondition {$nbDays.tree} {$firstOp}) )? |
  ('crosses up historical' WhiteChar operand -> ^(CrossUpDoubleMapCondition ^(Double Number["1.0"]) {$firstOp} operand)) 
       ( WhiteChar 'over' WhiteChar nbDays=constant WhiteChar DAYS -> ^(CrossUpDoubleMapCondition {$nbDays.tree} {$firstOp}) )? |
  'equals historical' WhiteChar operand -> ^(EqualDoubleMapCondition {$firstOp} operand);

constantcmp [CommonTree firstOp] : 
  'equals threshold' WhiteChar constant -> ^(EqualConstantCondition constant {$firstOp}) | 
  'is above threshold' WhiteChar constant -> ^(SupConstantCondition constant {$firstOp})| 
  'is below threshold' WhiteChar constant -> ^(InfConstantCondition constant {$firstOp});
  
//TODO constant ops with several constants as operands (ex : is up more than X% for N days) <= needs hadoc tree rewriting?
presetcondition [CommonTree firstOp]  : 
  ('reverses down' -> ^(ReverseCondition ^(Double Number["-1"]) ^(Double Number["0.0"]) ^(Double Number["1.0"]) {$firstOp}))
      ( WhiteChar 'more than' WhiteChar percentdown=constant PERCENT WhiteChar 'over' WhiteChar nbDays=constant WhiteChar DAYS -> ^(ReverseCondition ^(Double Number["-1"]) {$percentdown.tree} {$nbDays.tree} {$firstOp}) )? | 
  ('reverses up' -> ^(ReverseCondition ^(Double Number["1"]) ^(Double Number["0.0"]) ^(Double Number["1.0"]) {$firstOp}))
      ( WhiteChar 'more than' WhiteChar percentup=constant PERCENT WhiteChar 'over' WhiteChar nbDays=constant WhiteChar DAYS -> ^(ReverseCondition ^(Double Number["1"]) {$percentup.tree} {$nbDays.tree} {$firstOp}) )? |
  ('goes down more than' WhiteChar percentdown=constant PERCENT -> ^(DownRatioCondition constant ^(Double Number["1.0"]) {$firstOp})) 
      ( WhiteChar 'over' WhiteChar nbDays=constant WhiteChar DAYS -> ^(DownRatioCondition {$percentdown.tree} {$nbDays.tree} {$firstOp}) )? |
  ('goes up more than' WhiteChar percentup=constant PERCENT -> ^(UpRatioCondition constant ^(Double Number["1.0"]) {$firstOp})) 
      ( WhiteChar 'over' WhiteChar nbDays=constant WhiteChar DAYS -> ^(UpRatioCondition {$percentup.tree} {$nbDays.tree} {$firstOp}) )? |
  ('crosses up threshold' WhiteChar cnst=constant -> ^(CrossUpConstantCondition constant ^(Double Number["1.0"]) {$firstOp}))
      ( WhiteChar 'over' WhiteChar nbDays=constant WhiteChar DAYS -> ^(CrossUpConstantCondition {$cnst.tree} {$nbDays.tree} {$firstOp}) )? |  
  ('crosses down threshold' WhiteChar cnst=constant -> ^(CrossDownConstantCondition constant ^(Double Number["1.0"]) {$firstOp}))
      ( WhiteChar 'over' WhiteChar nbDays=constant WhiteChar DAYS -> ^(CrossDownConstantCondition {$cnst.tree} {$nbDays.tree} {$firstOp}) )? |
  ('makes a higher high over' WhiteChar nbDays=constant WhiteChar DAYS -> ^(HigherHighCondition constant ^(Double Number["-1.0"]) {$firstOp}))
       ( WhiteChar 'smoothing threshold' WhiteChar lookBackSmthPeriod=constant -> ^(HigherHighCondition {$nbDays.tree} {$lookBackSmthPeriod.tree} {$firstOp}) )? |
  ('makes a higher low over' WhiteChar nbDays=constant WhiteChar DAYS -> ^(HigherLowCondition constant ^(Double Number["-1.0"]) {$firstOp}))
       ( WhiteChar 'smoothing threshold' WhiteChar lookBackSmthPeriod=constant -> ^(HigherLowCondition {$nbDays.tree} {$lookBackSmthPeriod.tree} {$firstOp}) )? |
  ('makes a lower high over' WhiteChar nbDays=constant WhiteChar DAYS -> ^(LowerHighCondition constant ^(Double Number["-1.0"]) {$firstOp}))
       ( WhiteChar 'smoothing threshold' WhiteChar lookBackSmthPeriod=constant -> ^(LowerHighCondition {$nbDays.tree} {$lookBackSmthPeriod.tree} {$firstOp}) )? |
  ('makes a lower low over' WhiteChar nbDays=constant WhiteChar DAYS -> ^(LowerLowCondition constant ^(Double Number["-1.0"]) {$firstOp}))
       ( WhiteChar 'smoothing threshold' WhiteChar lookBackSmthPeriod=constant -> ^(LowerLowCondition {$nbDays.tree} {$lookBackSmthPeriod.tree} {$firstOp}) )?;

Operation 
      : {runtimeOpAhead()}? => ('a'..'z' | 'A'..'Z' | '_') ('a'..'z' | 'A'..'Z' | '_' | '0'..'9')+
      ;

Number 
      :  ('-')? ('0'..'9')+ ('.' ('0'..'9')+)?
      ;
      
HistoricalData
			: {runtimeHistoryOpAhead()}? => ( 'open' | 'close' | 'high' | 'low'  | 'volume' )
			;
   
WhiteChar 
      : (' '+)
      ;
      
Tcheat
     : ('a'..'z' | 'A'..'Z')+
     ;
    
//additionnal lexical rules (hidden chars)
//WS  : (' '|'\r'|'\t'|'\u000C'|'\n') {$channel=HIDDEN;}
WS  : ('\r'|'\t'|'\u000C'|'\n') {$channel=HIDDEN;}
    ;
COMMENT
    : '/*' .* '*/' {$channel=HIDDEN;}
    ;
LINE_COMMENT
    : '//' ~('\n'|'\r')* '\r'? '\n' {$channel=HIDDEN;}
    ;
