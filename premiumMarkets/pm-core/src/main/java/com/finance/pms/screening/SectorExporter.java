/**
 * Premium Markets is an automated financial technical analysis system. 
 * It implements a graphical environment for monitoring financial technical analysis
 * major indicators and for portfolio management.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pickup the best market shares, 
 * Forecast of share prices trend changes on the basis of financial technical analysis,
 * (with a rate of around 70% of forecasts being successful observed while back testing 
 * over DJI, FTSE, DAX and SBF),
 * Back testing and Email sending on buy and sell alerts triggered while scanning markets
 * and user defined portfolios.
 * Please refer to Premium Markets PRICE TREND FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com/ for a preview of more advanced features. 
 * 
 * Copyright (C) 2008-2012 Guillaume Thoreton
 * 
 * This file is part of Premium Markets.
 * 
 * Premium Markets is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
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
		
		NavigableSet<TrendSupplementedStock> listOfShares = sector.getListOfShares();
		Integer listOfSharesSize = listOfShares.size();
		String allShares = listOfSharesSize.toString().concat(SEPARATOR);
		
		allShares.concat(BLANK);
		for (TrendSupplementedStock share : listOfShares) {
			allShares = allShares.concat(share.getName().replace("&amp;", "&")).concat(" (").concat(share.getSectorHint().replace("&amp;", "&")).concat(") ").concat(BLANK);
		}
		return allShares;
	}

	@Override
	public void buildAndSendScreeningEvents(NavigableSet<Sector> screened,String analyseName) {
		throw new NotImplementedException();
	}

}
