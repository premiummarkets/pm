package com.finance.pms.events.calculation.antlr;

import java.util.Set;

import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.Token;

public class EditorIndsParserDelegate extends EditorParserDelegate implements IndsParserDelegate {
	
	private RecognizerSharedState state;

	public EditorIndsParserDelegate(CommonTokenStream tokens, RecognizerSharedState state, Set<EditorOpDescr> allOps) {
		super(tokens, allOps);
		this.state = state;
	}

	@Override
	public RecognizerSharedState getState() {
		return state;
	}

	@Override
	public void checkParenthesis(String type) throws UnfinishedNestedCondition {
		
		System.out.println("check parenthesis : "+type);
		
		String parsed = "";
		int i = 0;
			
		if (input.LA(i+1) == Token.EOF) {
			throw new UnfinishedNestedCondition(input, type, parsed, false, true);
		}
		
		//Some tokens after check point => this needs to be 'or', 'and' or ')' or this is wrong
		Boolean wrongToken = true;
		Boolean unfinishToken = false;
		while (input.LA(i+1) != Token.EOF) {
			
			Token lt = input.LT(i+1);
			if (lt != null) {
				parsed = lt.getText();
			}
			
			if (lt == null || !lt.getText().trim().isEmpty()) {
				
				if (type.isEmpty()) {
					wrongToken = !"and".startsWith(lt.getText()) && !"or".startsWith(lt.getText());
					unfinishToken = !wrongToken && !"and".equals(lt.getText()) && !"or".equals(lt.getText());
				}
				else if (type.equals("and")) {
					wrongToken = !"and".startsWith(lt.getText());
					unfinishToken = !wrongToken && !"and".equals(lt.getText());
				}
				else if (type.equals("or")) {
					wrongToken = !"or".startsWith(lt.getText());
					unfinishToken = !wrongToken && !"or".equals(lt.getText());
				}
				wrongToken = wrongToken && !lt.getText().equals(")");
				//unfinishToken = unfinishToken || lt.getText().equals(")");
				
				if (lt == null || wrongToken || unfinishToken) {
					throw new UnfinishedNestedCondition(input, type, parsed, wrongToken, unfinishToken);
				} else {
					break;
				}
			}
			i++;
		}
		//Only white spaces met to the end
		if (wrongToken) {
			throw new UnfinishedNestedCondition(input, type, parsed, false, true);
		}
	}

	@Override
	public void checkOperationValidity(Token opName) throws InvalidOperationException {

		EditorOpDescr currentOp = grabOpForToken(opName);
		if (currentOp == null) throw new InvalidOperationException(input, opName, null);

	}

}
