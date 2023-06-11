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
import java.util.Calendar;
import java.util.Date;
import java.util.NavigableSet;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.finance.pms.MainPMScmd;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.AnalysisClient;
import com.finance.pms.events.EmailFilterEventSource;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventState;
import com.finance.pms.events.EventType;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.SymbolEvents;
import com.finance.pms.events.calculation.MessageProperties;
import com.finance.pms.portfolio.IgnoredEventDateException;
import com.finance.pms.queue.SymbolEventsMessage;
import com.finance.pms.talib.dataresults.AlertEventValue;
import com.finance.pms.talib.dataresults.StandardEventKey;
import com.finance.pms.threads.ConfigThreadLocal;

public abstract class ScreeningSupplementStockExporter extends Exporter<NavigableSet<ScreeningSupplementedStock>> {

	private static MyLogger LOGGER = MyLogger.getLogger(ScreeningSupplementStockExporter.class);


	protected ScreeningSupplementStockExporter(String fileExtention, Queue eventQueue, JmsTemplate jmsTemplate) {
		super("perfsAndYield_" +fileExtention,
				"rank;id;sector;close;ttmClose;" +
				"div;yield;payout;Ryie;Rpout;poutRat;PrcCh;PrcRat;PastRat;" +
				"Yrec;Brec;Ypot;Bpot;RecRat;" +
				"Yeps;YestEps;Ype;YepsGrowth;Ypeg;YPegRat;" +
				"Beps;BestEps;Bpe;BepsGrowth;Bpeg;BPegRat;" +
				"Reps;RestEps;Rpe;RepsGrowth;Rpeg;RPegRat;" +
				"PegRat;" +
				"EstRat;NoDivRat;FulRat",
				eventQueue, jmsTemplate);
	}


	@Override
	public void exportToFile(NavigableSet<ScreeningSupplementedStock> element) throws IOException {
		
		Boolean generateScreenerReports = Boolean.valueOf(MainPMScmd.getMyPrefs().get("screener.generatecsv", "false"));
		if (!generateScreenerReports) return;
		
		writeFileHeader(fileName, header);
		bufferedWriter.write(SEPARATOR+"Dividende : being overridden in this order :  from yahoo then reuters then bourso\n");
		bufferedWriter.write(SEPARATOR+"BNA == EPS (annual Earning Per Share)\n");
		bufferedWriter.write(SEPARATOR+"Est BNA/EPS : Estimated end of next this year BNA or estimated EPS\n");
		bufferedWriter.write(SEPARATOR+"Pay out ratio (reuters) == dividend per share / earning per share. The best is a high div and a low ratio as the earnings support the dividend.\n");
		bufferedWriter.write(SEPARATOR+"Ideal Payout ratio is 50%. It shouldn't be outside the 40%, 60% limits.\n");
		bufferedWriter.write(SEPARATOR+"EPS growth == Estimated EPS - Current EPS\n");
		bufferedWriter.write(SEPARATOR+"PEG ratio == P/E ratio / EPS growth rate. It is considered a form of normalisation because higher growth rates should cause higher P/E ratios.\n");
		bufferedWriter.write(SEPARATOR+"Ideal figures : P/E is below 15 (ie not over priced) and EPS growth is above 20% and PEG : below 0.75 (ie 15/20)\n");
		bufferedWriter.write(SEPARATOR+"The lower the result, the better.\n\n");
		bufferedWriter.write(header);
		bufferedWriter.newLine();
		
		
		Integer rank = 0;
		for (ScreeningSupplementedStock stockPerf : element) {
			rank++;
			String newLine = 
					rank.toString().concat(SEPARATOR)
					.concat(stockPerf.getName().replace(SEPARATOR,"_").concat(BLANK))
					.concat(stockPerf.getStock().getSymbol()).concat(BLANK)
					.concat(stockPerf.getStock().getIsin()).concat(SEPARATOR)
					.concat(stockPerf.getSectorHint()).concat(SEPARATOR)
					.concat(stockPerf.closeToString()).concat(SEPARATOR)
					.concat(stockPerf.ttmCloseToString()).concat(SEPARATOR)
					 //Past reating
					.concat(stockPerf.dividendToString()).concat(SEPARATOR)
					.concat(stockPerf.yield().toString()).concat(SEPARATOR)
					.concat(stockPerf.payoutRatio().toString()).concat(SEPARATOR)
					.concat(stockPerf.getReutersYield().toString()).concat(SEPARATOR)
					.concat(stockPerf.getReutersPayoutRatio().toString()).concat(SEPARATOR)
					.concat(stockPerf.payoutRating().toString()).concat(SEPARATOR)
					.concat(stockPerf.priceChangeTTM().toString()).concat(SEPARATOR)
					.concat(stockPerf.priceChangeRating().toString()).concat(SEPARATOR)
					.concat(stockPerf.pastRating().toString()).concat(SEPARATOR)
					//Reco
					.concat(stockPerf.getYahooMeanRecommendations().toString()).concat(SEPARATOR)
					.concat(stockPerf.getBoursoMeanRecommendations().toString()).concat(SEPARATOR)
					.concat(stockPerf.yahooPotentielPrice().toString()).concat(SEPARATOR)
					.concat(stockPerf.boursoPricePotentiel().toString()).concat(SEPARATOR)
					.concat(stockPerf.recRating().toString()).concat(SEPARATOR)
					
					//Actual PE EPSG PEG
					.concat(stockPerf.getYahooEPS().toString()).concat(SEPARATOR)
					.concat(stockPerf.getYahooEstEPS().toString()).concat(SEPARATOR)
					.concat(stockPerf.yahooPE().toString()).concat(SEPARATOR)
					.concat(stockPerf.yahooEPSG().toString()).concat(SEPARATOR)
					.concat(stockPerf.yahooPEG().toString()).concat(SEPARATOR)
					.concat(stockPerf.yahooPEGRating().toString()).concat(SEPARATOR)
					
					.concat(stockPerf.getBoursoBNA().toString()).concat(SEPARATOR)
					.concat(stockPerf.getBoursoEstBNA().toString()).concat(SEPARATOR)
					.concat(stockPerf.boursoPE().toString()).concat(SEPARATOR)
					.concat(stockPerf.boursoEPSG().toString()).concat(SEPARATOR)
					.concat(stockPerf.boursoPEG().toString()).concat(SEPARATOR)
					.concat(stockPerf.boursoPEGRating().toString()).concat(SEPARATOR)

					.concat(stockPerf.getReutersEPS().toString()).concat(SEPARATOR)
					.concat(stockPerf.getReutersEstEPS().toString()).concat(SEPARATOR)
					.concat(stockPerf.reutersPE().toString()).concat(SEPARATOR)
					.concat(stockPerf.reutersEPSG().toString()).concat(SEPARATOR)
					.concat(stockPerf.reutersPEG().toString()).concat(SEPARATOR)
					.concat(stockPerf.reutersPEGRating().toString()).concat(SEPARATOR)
					
					.concat(stockPerf.pegRatings().toString()).concat(SEPARATOR)
					//Totals
					.concat(stockPerf.estimationRating().toString()).concat(SEPARATOR)
					.concat(stockPerf.noPayoutFullRating().toString()).concat(SEPARATOR)
					.concat(stockPerf.fullRating().toString());
			
			bufferedWriter.write(newLine);
			bufferedWriter.newLine();
		}
		
		bufferedWriter.flush();
		
	}

	protected SymbolEvents constructEvent(
			String eventListName, ScreeningSupplementedStock screenedStock, EventDefinition eventDefinition, Integer rank, Integer previousRank, EventType eventType, String message, Date eventDate) {
		
		ConcurrentSkipListMap<EventKey, EventValue> eventMap = new ConcurrentSkipListMap<EventKey, EventValue>();
		
		EventKey key = new StandardEventKey(eventDate, eventDefinition, eventType);
		message = message + ". Rank is : --" + rank +"--";
		if (previousRank != null) message = message + " and was "+previousRank;
		EventValue value = new AlertEventValue(eventDate, eventType, eventDefinition, message, eventListName);
		eventMap.put(key, value);
		SymbolEvents symbolEvents = new SymbolEvents(screenedStock.getStock(), eventMap, EventDefinition.loadMaxPassPrefsEventInfo(), EventState.STATE_TERMINATED);
		return symbolEvents;
	}


	protected Integer extractPreviousRank(Stock stock, String analysisName, Date endDate, EventType... eventTypes) throws IgnoredEventDateException {
		EventValue previousEventValue = DataSource.getInstance().getLastTrendEventFor(stock, analysisName, endDate, eventTypes);
		
		if (previousEventValue == null) return null;
		Calendar currentDateCal = Calendar.getInstance();
		currentDateCal.setTime(endDate);
		currentDateCal.set(Calendar.HOUR_OF_DAY, 0);
		currentDateCal.set(Calendar.MINUTE, 0);
		currentDateCal.set(Calendar.SECOND, 0);
		currentDateCal.set(Calendar.MILLISECOND,0);
		if (previousEventValue.getDate().compareTo(currentDateCal.getTime()) == 0) throw new IgnoredEventDateException(previousEventValue);
		
		Integer previousRank = extractPreviousRankFromMessage(previousEventValue);
		return previousRank;
	}

	protected Integer extractPreviousRankFromMessage(EventValue previousEventValue) {
		
		Pattern pattern = Pattern.compile("Rank is : --([0-9]+)--");
		Matcher matcher = pattern.matcher(previousEventValue.getMessage());
		if (matcher.find()) {
			return Integer.valueOf(matcher.group(1));
		} else {
			LOGGER.error("Invalid trend event : "+previousEventValue, new Throwable());
			return 0;
		}
	}


	public void sendValidityMetricsMsg(final FullRatingOrdinator ordinator, final String eventListName) {
		
		if (AnalysisClient.getEmailMsgQeueingFilter().contains(EmailFilterEventSource.Metrics)) {
			jmsTemplate.send(eventQueue, new MessageCreator() {

				public Message createMessage(Session session) throws JMSException {

					SymbolEvents symbolEvents = new SymbolEvents(AnalysisClient.ANY_STOCK);
					StandardEventKey eventKey = new StandardEventKey(new Date(), EventDefinition.SCREENER, EventType.INFO);
					String messageTxt = String.format(
							"Percentage of unfound trends %.2f %%.\n" +
									"Percentage of not credible trends %.2f %%.\n" +
									"Percentage of staled trends %.2f %%.\n" +
									"Percentage of under performing %.2f %%.\n" +
									"Percentage of invalid trends %.2f %%.\n" +
									"Unfound trends :\n %s \nNot credible trends :\n %s \nStaled trends :\n %s \nUnder perf :\n %s \nInvalid trends :\n %s\n",
									ordinator.getNotToBefoundPerCentage()*100,ordinator.getNotCrediblePerCentage()*100,ordinator.getStaledPerCentage()*100,ordinator.getIgnoredPerCentage()*100,ordinator.getInvalidPerCentage()*100,
									ordinator.getNotToBeFoundTrends(),ordinator.getNotCredibleTrends(),ordinator.getStaledTrends(),ordinator.getIgnoredTrends(),ordinator.getInvalidTrends());
					symbolEvents.addEventResultElement(eventKey, new AlertEventValue(eventKey, messageTxt, eventListName), EventDefinition.SCREENER.getEventDefinitionRef());

					SymbolEventsMessage message = new SymbolEventsMessage(eventListName, symbolEvents, ConfigThreadLocal.getAll());
					message.setObjectProperty(MessageProperties.ANALYSE_SOURCE.getKey(), EmailFilterEventSource.Metrics);
					message.setObjectProperty(MessageProperties.SEND_EMAIL.getKey(), Boolean.TRUE);
					message.setObjectProperty(MessageProperties.TREND.getKey(), EventType.INFO.name());

					return message;
				}
			});
		}
	}
		
	
}
