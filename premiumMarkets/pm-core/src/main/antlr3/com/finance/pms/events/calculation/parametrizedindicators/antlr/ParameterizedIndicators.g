grammar ParameterizedIndicators;

options {
	language = Java ;
	output = AST ; 
	ASTLabelType=CommonTree;
	//k=2; backtrack=true; memoize=true;
}
tokens {
  Number ;
  StockOperation ;
  OperationOutput ;
  Tcheat;
  String;

  NullCondition;
  
  PreAndSignalCondition ;
  TruthOfCondition ;
  OrBooleanMapCondition ;
  AndBooleanMapCondition ;
  NotBooleanMapCondition ;

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
  SupportBreakDown ;
  SupportBreakUp ;

  LinearSimilarTrendsCondition ;
  LinearOppositeTrendsCondition ;
  LinearDirectedTrendsCondition ;

  MatchingBooleanMapCondition ;

  EqualStringConstantCondition ;

  EventInfoOpsCompoOperation ;
  StringOperation ;

  OR ='or';
  AND ='and';
  WITH ='with';
  MATCHING = 'matching';
  LENIENT = 'lenient';
  NOT = 'not';
  TRUTHOF = 'truth of';
  SEMICOLUMN = ';';
  OPENPARENTEHSIS = '(';
  CLOSEPARENTEHSIS = ')';
  PERCENT = '%';
  DAYS = 'days';
  OPENSQRT = '[';
  CLOSESQRT = ']';
  COMMA = ',';


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
   //TODO : common optional statement condition for also_display and fixed_start_shift
   bcond=bullish_condition bearish_condition[$bcond.tree] also_display fixed_start_shift na_event_list_name -> ^(EventInfoOpsCompoOperation bullish_condition bearish_condition also_display fixed_start_shift na_event_list_name)
   ;

bullish_condition :
 'is bullish when' WhiteChar primary_expression WhiteChar* SEMICOLUMN WhiteChar* -> primary_expression
 ;
bearish_condition[CommonTree bcond] :
 'is bearish when' WhiteChar primary_expression WhiteChar* SEMICOLUMN WhiteChar* -> primary_expression |
 bearish_not_bullish[$bcond] WhiteChar* SEMICOLUMN WhiteChar* -> bearish_not_bullish
 ;
 also_display :
  'also display' WhiteChar primary_expression WhiteChar* SEMICOLUMN -> ^(AndBooleanMapCondition ^(String StringToken["\"TRUE\""]) primary_expression) |
  -> NullCondition
 ;
 fixed_start_shift :
  'override start shift with' WhiteChar fixedStartShift=constant WhiteChar DAYS SEMICOLUMN -> {$fixedStartShift.tree} |
  -> ^(Number NumberToken["-1"])
 ;
 na_event_list_name :
  'override event list name with' WhiteChar eventListName=stringconstant SEMICOLUMN -> {$eventListName.tree} |
  -> ^(String StringToken["FROM PARENT"])
 ;

bearish_not_bullish[CommonTree bcond] :
 'is bearish if not bullish'
  (
  WhiteChar AND WhiteChar primary_expression -> ^(AndBooleanMapCondition ^(String StringToken["\"FALSE\""]) ^(NotBooleanMapCondition ^(String StringToken["\"FALSE\""]) {$bcond}) primary_expression)|
  WhiteChar OR WhiteChar primary_expression -> ^(OrBooleanMapCondition ^(String StringToken["\"TRUE\""]) ^(NotBooleanMapCondition ^(String StringToken["\"FALSE\""]) {$bcond}) primary_expression)|
  -> ^(NotBooleanMapCondition ^(String StringToken["\"FALSE\""]) {$bcond})
  )
 ;

primary_expression :
  and_expression
  ;
and_expression :
  or_expression lenientParam=lenient (WhiteChar AND WhiteChar or_expression)* -> ^(AndBooleanMapCondition {$lenientParam.tree} or_expression or_expression*)
  ;
or_expression :
  precondition_expression (WhiteChar OR WhiteChar precondition_expression)* -> ^(OrBooleanMapCondition ^(String StringToken["\"TRUE\""]) precondition_expression precondition_expression*)
  ;
precondition_expression :
  matches_expression (WhiteChar WITH WhiteChar matches_expression)* -> ^(PreAndSignalCondition matches_expression matches_expression*)
  ;
matches_expression :
  atom (WhiteChar MATCHING WhiteChar '[' constant (',' constant)* ']' WhiteChar atom)? -> ^(MatchingBooleanMapCondition constant* atom atom?)
  ;
atom :
  booleanhistory |
  '(' WhiteChar* primary_expression WhiteChar* ')' -> primary_expression |
  NOT WhiteChar* '(' WhiteChar* primary_expression WhiteChar* ')' -> ^(NotBooleanMapCondition ^(String StringToken["\"FALSE\""]) primary_expression) |
  conjunctiontruthof
  ;
conjunctiontruthof :
  TRUTHOF WhiteChar primary_expression (COMMA WhiteChar primary_expression)* WhiteChar 'is within' WhiteChar '[' min=constant ',' max=constant ']' 
  	-> ^(TruthOfCondition ^(String StringToken["\"TRUE\""]) {$min.tree} {$max.tree} primary_expression primary_expression*)
  ;
booleanhistory :
	firstOp=operand WhiteChar (
		presetcondition[$firstOp.tree] -> presetcondition |
		opcmpcondition[$firstOp.tree] -> opcmpcondition |
		constantcmp[$firstOp.tree] -> constantcmp
	);

operand : HistoricalData -> ^(StockOperation ^(OperationOutput HistoricalData) ^(String StringToken["\"THIS\""])) | opName = Operation {checkOperationValidity($opName);} -> Operation;

constant :  NumberToken -> ^(Number NumberToken) | 'NaN' -> ^(Number NumberToken["NaN"]);
stringconstant : StringToken -> ^(String StringToken);
trendconstant : 'bullish' -> ^(String StringToken["\"bullish\""]) | 'bearish' -> ^(String StringToken["\"bearish\""]);
lenient : (WhiteChar LENIENT -> ^(String StringToken["\"TRUE\""]) | -> ^(String StringToken["\"FALSE\""])); //If lenient is specified, the String Token is true. It is false otherwise.

opcmpcondition [CommonTree firstOp] :

//Double maps comparisons
  ('is above historical' WhiteChar secondOp=operand -> ^(SupDoubleMapCondition ^(Number NumberToken["0"]) ^(Number NumberToken["0.0"]) {$firstOp} operand))
   (WhiteChar 'for' WhiteChar forNbDays=constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon=constant PERCENT 
    -> ^(SupDoubleMapCondition {$forNbDays.tree} {$epsilon.tree} {$firstOp} {$secondOp.tree})
   )? |
  ('is below historical' WhiteChar secondOp=operand -> ^(InfDoubleMapCondition ^(Number NumberToken["0"]) ^(Number NumberToken["0.0"]) {$firstOp} operand))
   (WhiteChar 'for' WhiteChar forNbDays=constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon=constant PERCENT
    -> ^(InfDoubleMapCondition {$forNbDays.tree} {$epsilon.tree} {$firstOp} {$secondOp.tree})
   )? |
  ('equals historical' WhiteChar secondOp=operand -> ^(EqualDoubleMapCondition ^(Number NumberToken["0"]) ^(Number NumberToken["0.0"]) {$firstOp} operand))
   (WhiteChar 'for' WhiteChar forNbDays=constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon=constant PERCENT
    -> ^(EqualDoubleMapCondition {$forNbDays.tree} {$epsilon.tree} {$firstOp} {$secondOp.tree})
   )? |

//Double maps crossing
  ('crosses down historical' WhiteChar secondOp=operand 
   -> ^(CrossDownDoubleMapCondition ^(Number NumberToken["1.0"]) ^(Number NumberToken["0.0"]) ^(Number NumberToken["0.0"]) ^(Number NumberToken["0.0"]) NullCondition {$firstOp} {$secondOp.tree})
  )
  ((WhiteChar 'spanning' WhiteChar spanningNbDays=constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays=constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon=constant PERCENT
    -> ^(CrossDownDoubleMapCondition {$spanningNbDays.tree} {$overNbDays.tree} {$epsilon.tree} ^(Number NumberToken["0.0"]) NullCondition {$firstOp} {$secondOp.tree})
   )
   (WhiteChar 'adaptiveRate' WhiteChar adaptiveRate=constant PERCENT WhiteChar adaptive=atom 
    -> ^(CrossDownDoubleMapCondition {$spanningNbDays.tree} {$overNbDays.tree} {$epsilon.tree} {$adaptiveRate.tree} {$adaptive.tree} {$firstOp} {$secondOp.tree})
   )?
  )? |
  ('crosses up historical' WhiteChar secondOp=operand 
   -> ^(CrossUpDoubleMapCondition ^(Number NumberToken["1.0"]) ^(Number NumberToken["0.0"]) ^(Number NumberToken["0.0"]) ^(Number NumberToken["0.0"]) NullCondition {$firstOp} {$secondOp.tree})
  )
  ((WhiteChar 'spanning' WhiteChar spanningNbDays=constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays=constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon=constant PERCENT
    -> ^(CrossUpDoubleMapCondition {$spanningNbDays.tree} {$overNbDays.tree} {$epsilon.tree} ^(Number NumberToken["0.0"]) NullCondition {$firstOp} {$secondOp.tree})
   )
   (WhiteChar 'adaptiveRate' WhiteChar adaptiveRate=constant PERCENT WhiteChar adaptive=atom 
    -> ^(CrossUpDoubleMapCondition {$spanningNbDays.tree} {$overNbDays.tree} {$epsilon.tree} {$adaptiveRate.tree} {$adaptive.tree} {$firstOp} {$secondOp.tree})
   )?
  )? |

//Double maps trends comparisons
  ('trends like' WhiteChar secondOp=operand
      WhiteChar 'over' WhiteChar overNbDays=constant WhiteChar DAYS
      WhiteChar 'for' WhiteChar forNbDays=constant WhiteChar DAYS
      WhiteChar 'direction' WhiteChar direction=stringconstant
      WhiteChar 'epsilon' WhiteChar epsilon=constant
      -> ^(LinearSimilarTrendsCondition {$overNbDays.tree} {$forNbDays.tree} {$direction.tree} {$epsilon.tree} {$firstOp} {$secondOp.tree})) |
  ('trends unlike' WhiteChar secondOp=operand
      WhiteChar 'over' WhiteChar overNbDays=constant WhiteChar DAYS
      WhiteChar 'for' WhiteChar forNbDays=constant WhiteChar DAYS
      WhiteChar 'direction' WhiteChar direction=stringconstant
      -> ^(LinearOppositeTrendsCondition {$overNbDays.tree} {$forNbDays.tree} {$direction.tree} {$firstOp} {$secondOp.tree}));

constantcmp [CommonTree firstOp] :

  ('equals trend' WhiteChar trendSignal=trendconstant -> ^(EqualStringConstantCondition trendconstant ^(Number NumberToken["0"]) ^(Number NumberToken["0"]) {$firstOp}) )
    ( WhiteChar 'over' WhiteChar overNbDays=constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays=constant WhiteChar DAYS -> ^(EqualStringConstantCondition {$trendSignal.tree} {$overNbDays.tree} {$forNbDays.tree} {$firstOp}) )?|

  ('equals threshold' WhiteChar threshold=constant -> ^(EqualConstantCondition constant ^(Number NumberToken["0"]) ^(Number NumberToken["0"]) ^(Number NumberToken["0"]) {$firstOp}) )
    ( WhiteChar 'over' WhiteChar overNbDays=constant WhiteChar DAYS
    WhiteChar 'for' WhiteChar forNbDays=constant WhiteChar DAYS
    WhiteChar 'epsilon' WhiteChar epsilon=constant PERCENT
    -> ^(EqualConstantCondition {$threshold.tree} {$overNbDays.tree} {$forNbDays.tree} {$epsilon.tree} {$firstOp}) )? |
  ('is above threshold' WhiteChar threshold=constant -> ^(SupConstantCondition constant ^(Number NumberToken["0"]) ^(Number NumberToken["0"]) ^(Number NumberToken["0"]) {$firstOp}) )
    ( WhiteChar 'over' WhiteChar overNbDays=constant WhiteChar DAYS
    WhiteChar 'for' WhiteChar forNbDays=constant WhiteChar DAYS
    WhiteChar 'epsilon' WhiteChar epsilon=constant PERCENT
    -> ^(SupConstantCondition {$threshold.tree} {$overNbDays.tree} {$forNbDays.tree} {$epsilon.tree} {$firstOp}) )? |
  ('is below threshold' WhiteChar threshold=constant -> ^(InfConstantCondition constant ^(Number NumberToken["0"]) ^(Number NumberToken["0"]) ^(Number NumberToken["0"]) {$firstOp}) )
    ( WhiteChar 'over' WhiteChar overNbDays=constant WhiteChar DAYS
    WhiteChar 'for' WhiteChar forNbDays=constant WhiteChar DAYS
    WhiteChar 'epsilon' WhiteChar epsilon=constant PERCENT
    -> ^(InfConstantCondition {$threshold.tree} {$overNbDays.tree} {$forNbDays.tree} {$epsilon.tree} {$firstOp}) )?;

presetcondition [CommonTree firstOp] :

  ('reverses down' -> ^(ReverseCondition ^(Number NumberToken["-1"]) ^(Number NumberToken["0.0"]) ^(Number NumberToken["1.0"]) {$firstOp}))
      ( WhiteChar 'more than' WhiteChar percentdown=constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays=constant WhiteChar DAYS 
      -> ^(ReverseCondition ^(Number NumberToken["-1"]) {$percentdown.tree} {$spanningNbDays.tree} {$firstOp}) )? |
  ('reverses up' -> ^(ReverseCondition ^(Number NumberToken["1"]) ^(Number NumberToken["0.0"]) ^(Number NumberToken["1.0"]) {$firstOp}))
      ( WhiteChar 'more than' WhiteChar percentup=constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays=constant WhiteChar DAYS 
      -> ^(ReverseCondition ^(Number NumberToken["1"]) {$percentup.tree} {$spanningNbDays.tree} {$firstOp}) )? |

  ('goes down more than' WhiteChar percentdown=constant PERCENT -> ^(DownRatioCondition constant ^(Number NumberToken["1.0"]) ^(Number NumberToken["0.0"]) ^(Number NumberToken["0.0"]) ^(Number NumberToken["0.0"]) {$firstOp}))
      ( WhiteChar 'spanning' WhiteChar spanningNbDays=constant WhiteChar DAYS 
        WhiteChar 'for' WhiteChar forNbDays=constant WhiteChar DAYS
      -> ^(DownRatioCondition {$percentdown.tree} {$spanningNbDays.tree} ^(Number NumberToken["0.0"]) {$forNbDays.tree} ^(Number NumberToken["0.0"]) {$firstOp}) )? |
  ('goes up more than' WhiteChar percentup=constant PERCENT -> ^(UpRatioCondition constant ^(Number NumberToken["1.0"]) ^(Number NumberToken["0.0"]) ^(Number NumberToken["0.0"]) ^(Number NumberToken["0.0"]) {$firstOp}))
      ( WhiteChar 'spanning' WhiteChar spanningNbDays=constant WhiteChar DAYS 
        WhiteChar 'for' WhiteChar forNbDays=constant WhiteChar DAYS
      -> ^(UpRatioCondition {$percentup.tree} {$spanningNbDays.tree} ^(Number NumberToken["0.0"]) {$forNbDays.tree} ^(Number NumberToken["0.0"]) {$firstOp}) )? |

  ('crosses up threshold' WhiteChar threshold=constant -> ^(CrossUpConstantCondition constant ^(Number NumberToken["1.0"]) ^(Number NumberToken["0.0"]) ^(Number NumberToken["0.0"]) ^(Number NumberToken["0"]) {$firstOp}))
      ( WhiteChar 'spanning' WhiteChar spanningNbDays=constant WhiteChar DAYS 
        WhiteChar 'over' WhiteChar overNbDays=constant WhiteChar DAYS
        WhiteChar 'epsilon' WhiteChar epsilon=constant PERCENT
      -> ^(CrossUpConstantCondition {$threshold.tree} {$spanningNbDays.tree} {$overNbDays.tree} ^(Number NumberToken["0.0"]) {$epsilon.tree} {$firstOp}) )? |
  ('crosses down threshold' WhiteChar threshold=constant -> ^(CrossDownConstantCondition constant ^(Number NumberToken["1.0"]) ^(Number NumberToken["0.0"]) ^(Number NumberToken["0.0"]) ^(Number NumberToken["0"]) {$firstOp}))
      ( WhiteChar 'spanning' WhiteChar spanningNbDays=constant WhiteChar DAYS 
        WhiteChar 'over' WhiteChar overNbDays=constant WhiteChar DAYS
        WhiteChar 'epsilon' WhiteChar epsilon=constant PERCENT
      -> ^(CrossDownConstantCondition {$threshold.tree} {$spanningNbDays.tree} {$overNbDays.tree} ^(Number NumberToken["0.0"]) {$epsilon.tree} {$firstOp}) )? |

  ('makes a higher high spanning' WhiteChar lookBack=constant WhiteChar DAYS 
  		WhiteChar 'over' WhiteChar remanencePeriod=constant WhiteChar DAYS 
  		WhiteChar 'for' WhiteChar extremesSpan=constant WhiteChar DAYS 
  		WhiteChar 'smoothed' WhiteChar smoothP=constant WhiteChar DAYS 
  		WhiteChar 'greed' WhiteChar greed=stringconstant
  		WhiteChar 'type' WhiteChar type=stringconstant
  		WhiteChar 'starting within' WhiteChar '[' lowestStart=constant ',' highestStart=constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd=constant ',' highestEnd=constant ']'
  		WhiteChar 'slope within' WhiteChar '[' minSlope=constant ',' maxSlope=constant ']'
  	-> ^(HigherHighCondition {$lookBack.tree} {$remanencePeriod.tree} {$extremesSpan.tree} {$smoothP.tree} {$greed.tree} {$type.tree} {$lowestStart.tree} {$highestStart.tree} {$lowestEnd.tree} {$highestEnd.tree} {$minSlope.tree} {$maxSlope.tree} ^(Number NumberToken["NaN"]) {$firstOp}) ) |
  ('makes a higher low spanning' WhiteChar lookBack=constant WhiteChar DAYS 
  		WhiteChar 'over' WhiteChar remanencePeriod=constant WhiteChar DAYS 
  		WhiteChar 'for' WhiteChar extremesSpan=constant WhiteChar DAYS 
  		WhiteChar 'smoothed' WhiteChar smoothP=constant WhiteChar DAYS 
  		WhiteChar 'greed' WhiteChar greed=stringconstant
  		WhiteChar 'type' WhiteChar type=stringconstant
  		WhiteChar 'starting within' WhiteChar '[' lowestStart=constant ',' highestStart=constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd=constant ',' highestEnd=constant ']'
  		WhiteChar 'slope within' WhiteChar '[' minSlope=constant ',' maxSlope=constant ']'
  	-> ^(HigherLowCondition {$lookBack.tree} {$remanencePeriod.tree} {$extremesSpan.tree} {$smoothP.tree} {$greed.tree} {$type.tree} {$lowestStart.tree} {$highestStart.tree} {$lowestEnd.tree} {$highestEnd.tree} {$minSlope.tree} {$maxSlope.tree} ^(Number NumberToken["NaN"]) {$firstOp}) ) |
  ('makes a lower high spanning' WhiteChar lookBack=constant WhiteChar DAYS 
  		WhiteChar 'over' WhiteChar remanencePeriod=constant WhiteChar DAYS 
  		WhiteChar 'for' WhiteChar extremesSpan=constant WhiteChar DAYS 
  		WhiteChar 'smoothed' WhiteChar smoothP=constant WhiteChar DAYS 
  		WhiteChar 'greed' WhiteChar greed=stringconstant
  		WhiteChar 'type' WhiteChar type=stringconstant
  		WhiteChar 'starting within' WhiteChar '[' lowestStart=constant ',' highestStart=constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd=constant ',' highestEnd=constant ']'
  		WhiteChar 'slope within' WhiteChar '[' minSlope=constant ',' maxSlope=constant ']'
  	-> ^(LowerHighCondition {$lookBack.tree} {$remanencePeriod.tree} {$extremesSpan.tree} {$smoothP.tree} {$greed.tree} {$type.tree} {$lowestStart.tree} {$highestStart.tree} {$lowestEnd.tree} {$highestEnd.tree} {$minSlope.tree} {$maxSlope.tree} ^(Number NumberToken["NaN"]) {$firstOp}) ) |
  ('makes a lower low spanning' WhiteChar lookBack=constant WhiteChar DAYS 
  		WhiteChar 'over' WhiteChar remanencePeriod=constant WhiteChar DAYS 
  		WhiteChar 'for' WhiteChar extremesSpan=constant WhiteChar DAYS 
  		WhiteChar 'smoothed' WhiteChar smoothP=constant WhiteChar DAYS 
  		WhiteChar 'greed' WhiteChar greed=stringconstant
  		WhiteChar 'type' WhiteChar type=stringconstant
  		WhiteChar 'starting within' WhiteChar '[' lowestStart=constant ',' highestStart=constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd=constant ',' highestEnd=constant ']'
  		WhiteChar 'slope within' WhiteChar '[' minSlope=constant ',' maxSlope=constant ']'
  	-> ^(LowerLowCondition {$lookBack.tree} {$remanencePeriod.tree} {$extremesSpan.tree} {$smoothP.tree} {$greed.tree} {$type.tree} {$lowestStart.tree} {$highestStart.tree} {$lowestEnd.tree} {$highestEnd.tree} {$minSlope.tree} {$maxSlope.tree} ^(Number NumberToken["NaN"]) {$firstOp}) ) |

  	('makes a support break down spanning' WhiteChar lookBack=constant WhiteChar DAYS 
  		WhiteChar 'over' WhiteChar remanencePeriod=constant WhiteChar DAYS 
  		WhiteChar 'for' WhiteChar extremesSpan=constant WhiteChar DAYS 
  		WhiteChar 'smoothed' WhiteChar smoothP=constant WhiteChar DAYS 
  		WhiteChar 'starting within' WhiteChar '[' lowestStart=constant ',' highestStart=constant ']'
  		WhiteChar 'tolerance' WhiteChar tolerance=constant
  	-> ^(SupportBreakDown {$lookBack.tree} {$remanencePeriod.tree} {$extremesSpan.tree} {$smoothP.tree} ^(String StringToken["\"greedy\""]) ^(String StringToken["\"smooth\""]) {$lowestStart.tree} {$highestStart.tree} ^(Number NumberToken["NaN"]) ^(Number NumberToken["NaN"]) ^(Number NumberToken["NaN"]) ^(Number NumberToken["NaN"]) {$tolerance.tree} {$firstOp}) ) |
  	('makes a support break up spanning' WhiteChar lookBack=constant WhiteChar DAYS
      		WhiteChar 'over' WhiteChar remanencePeriod=constant WhiteChar DAYS
      		WhiteChar 'for' WhiteChar extremesSpan=constant WhiteChar DAYS
      		WhiteChar 'smoothed' WhiteChar smoothP=constant WhiteChar DAYS
      		WhiteChar 'starting within' WhiteChar '[' lowestStart=constant ',' highestStart=constant ']'
      		WhiteChar 'tolerance' WhiteChar tolerance=constant
     -> ^(SupportBreakUp {$lookBack.tree} {$remanencePeriod.tree} {$extremesSpan.tree} {$smoothP.tree} ^(String StringToken["\"greedy\""]) ^(String StringToken["\"smooth\""]) {$lowestStart.tree} {$highestStart.tree} ^(Number NumberToken["NaN"]) ^(Number NumberToken["NaN"]) ^(Number NumberToken["NaN"]) ^(Number NumberToken["NaN"]) {$tolerance.tree} {$firstOp}) ) |

  ('trends flat'
      WhiteChar 'over' WhiteChar overNbDays=constant WhiteChar DAYS
      WhiteChar 'for' WhiteChar forNbDays=constant WhiteChar DAYS
      WhiteChar 'epsilon' WhiteChar epsilon=constant
      -> ^(LinearDirectedTrendsCondition {$overNbDays.tree} {$forNbDays.tree} ^(String StringToken["\"flat\""]) {$epsilon.tree} {$firstOp})) |
  ('trends up'
      WhiteChar 'over' WhiteChar overNbDays=constant WhiteChar DAYS
      WhiteChar 'for' WhiteChar forNbDays=constant WhiteChar DAYS
      WhiteChar 'epsilon' WhiteChar epsilon=constant
      -> ^(LinearDirectedTrendsCondition {$overNbDays.tree} {$forNbDays.tree} ^(String StringToken["\"up\""]) {$epsilon.tree} {$firstOp})) |
  ('trends down'
      WhiteChar 'over' WhiteChar overNbDays=constant WhiteChar DAYS
      WhiteChar 'for' WhiteChar forNbDays=constant WhiteChar DAYS
      WhiteChar 'epsilon' WhiteChar epsilon=constant
      -> ^(LinearDirectedTrendsCondition {$overNbDays.tree} {$forNbDays.tree} ^(String StringToken["\"down\""]) {$epsilon.tree} {$firstOp}));

Operation
      : {runtimeOpAhead()}? => ('a'..'z' | 'A'..'Z' | '_') ('a'..'z' | 'A'..'Z' | '_' | '.' | '-' | '0'..'9')+
      ;

NumberToken
      : ( ('-')? ('0'..'9')+ ('.' ('0'..'9')+)? )
      ;

StringToken
     : '"' ('a'..'z' | 'A'..'Z' | '.' | '_' | '/' | ',' | '=' | ('0'..'9') | '?' | ':' | '-' | '>')+ '"'
     ;

HistoricalData
      : {runtimeHistoryOpAhead()}? => ( 'open' | 'close' | 'high' | 'low'  | 'volume' )
      ;

WhiteChar 
      : (' '+)
      ;

Tcheat
     : ('a'..'z' | 'A'..'Z' | '0'..'9')+
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
