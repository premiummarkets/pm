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
package com.finance.pms.events.operations.nativeops.ta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StackElement;
import com.finance.pms.events.operations.TargetStockInfo;

public abstract class PMDataFreeOperation extends PMIndicatorOperation {

    protected PMDataFreeOperation() {
        super();
    }

    public PMDataFreeOperation(String reference, String description, ArrayList<Operation> operands) {
        super(reference, description, operands);
    }

    public PMDataFreeOperation(String reference, String description, Operation ... operands) {
        this(reference, description,  new ArrayList<Operation>(Arrays.asList(operands)));
    }


    @Override
    public final int operandsRequiredStartShift(TargetStockInfo targetStock, List<StackElement> thisCallStack, int thisParentStartShift) {//Always 0 as the shift must be done ad'hoc and internally in the operation implementer
        return 0;
    }

    @Override
    public Boolean isIdemPotent(TargetStockInfo targetStock) {
        return false;
    }
    
    @Override
	public String toFormulaeShort(TargetStockInfo targetStock, List<StackElement> thisCallStack) {
    	String thisShort = getOperationReference().substring(0,1) + getOperationReference().chars()
				.filter(c -> Character.isUpperCase(c))
				.mapToObj(cu -> (char) cu)
				.reduce("", (r, e) -> r + e, (a, b) -> a + b);
				String opsFormulaeShort = super.toFormulaeShort(targetStock, thisCallStack, this.getOperands());
				return thisShort + ((opsFormulaeShort.isEmpty())?"":"_" + opsFormulaeShort);
	}


}
