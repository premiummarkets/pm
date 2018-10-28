/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock markets technical analysis
 * major indicators, for portfolio management and historical data charting.
 * In its advanced packaging -not provided under this license- it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock markets technical analysis and indices rotation,
 * Back testing, Automated buy sell email notifications on trend signals calculated over
 * markets and user defined portfolios. 
 * With in mind beating the buy and hold strategy.
 * Type 'Premium Markets FORECAST' in your favourite search engine for a free workable demo.
 * 
 * Copyright (C) 2008-2014 Guillaume Thoreton
 * 
 * This file is part of Premium Markets.
 * 
 * Premium Markets is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along
 * with this program. If not, see <http://www.gnu.org/licenses/>.
 */
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
					wrongToken = !"and".startsWith(lt.getText()) && !"or".startsWith(lt.getText()) && !"matches".startsWith(lt.getText());
					unfinishToken = !wrongToken && !"and".equals(lt.getText()) && !"or".equals(lt.getText()) && !"matches".equals(lt.getText());
				}
				else if (type.equals("and")) {
					wrongToken = !"and".startsWith(lt.getText());
					unfinishToken = !wrongToken && !"and".equals(lt.getText());
				}
				else if (type.equals("or")) {
					wrongToken = !"or".startsWith(lt.getText());
					unfinishToken = !wrongToken && !"or".equals(lt.getText());
				}
				else if (type.equals("matches")) {
					wrongToken = !"matches".startsWith(lt.getText());
					unfinishToken = !wrongToken && !"matches".equals(lt.getText());
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
