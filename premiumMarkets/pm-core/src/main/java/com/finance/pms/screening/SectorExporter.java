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
package com.finance.pms.screening;

import java.io.IOException;
import java.util.NavigableSet;

import javax.jms.Queue;

import org.apache.commons.lang.NotImplementedException;
import org.springframework.jms.core.JmsTemplate;


public class SectorExporter extends Exporter<NavigableSet<Sector>> {


	public SectorExporter(String seName, Queue eventQueue, JmsTemplate jmsTemplate) {
		super("sectors_" + seName,"name;%yield;%change;Prat;potentiel;rec;FutRat;FulRat;Best Share;All Shares", eventQueue, jmsTemplate);
	}
	
	@Override
	public void exportToFile(NavigableSet<Sector> element) throws IOException {
		
		writeFileHeader(fileName, header);
		
		for (Sector sector : element) {
			String newLine = 
					sector.getSectorName().concat(SEPARATOR)
					.concat(sector.getYieldtoString()).concat(SEPARATOR)
					.concat(sector.getPerfs().toString()).concat(SEPARATOR)
					.concat(sector.pastRating().toString()).concat(SEPARATOR)
					.concat(sector.getPotentieltoString()).concat(SEPARATOR)
					.concat(sector.getConsensustoString()).concat(SEPARATOR)
					.concat(sector.futurRating().toString().concat(SEPARATOR))
					.concat(sector.calculateFullRating().toString().concat(SEPARATOR))
					.concat(sector.getBest().getName().replace("&amp;", "&").concat(SEPARATOR))
					.concat(getAllSharesFromSector(sector));
			bufferedWriter.write(newLine);
			bufferedWriter.newLine();
		}
		
		bufferedWriter.flush();
		
	}

	private String getAllSharesFromSector(Sector sector) {
		
		NavigableSet<ScreeningSupplementedStock> listOfShares = sector.getListOfShares();
		Integer listOfSharesSize = listOfShares.size();
		String allShares = listOfSharesSize.toString().concat(SEPARATOR);
		
		allShares.concat(BLANK);
		for (ScreeningSupplementedStock share : listOfShares) {
			allShares = allShares.concat(share.getName().replace("&amp;", "&")).concat(" (").concat(share.getSectorHint().replace("&amp;", "&")).concat(") ").concat(BLANK);
		}
		return allShares;
	}

	@Override
	public void buildAndSendScreeningEvents(NavigableSet<Sector> screened,String analyseName) {
		throw new NotImplementedException();
	}

}
