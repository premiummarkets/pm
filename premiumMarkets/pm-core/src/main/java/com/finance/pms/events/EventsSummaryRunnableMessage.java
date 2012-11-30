package com.finance.pms.events;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.MessageCreator;

import com.finance.pms.SpringContext;
import com.finance.pms.admin.config.Config;
import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.calculation.MessageProperties;
import com.finance.pms.portfolio.PortfolioMgr;
import com.finance.pms.portfolio.PortfolioShare;
import com.finance.pms.portfolio.UserPortfolio;
import com.finance.pms.queue.AbstractAnalysisClientRunnableMessage;
import com.finance.pms.queue.SingleEventMessage;

public class EventsSummaryRunnableMessage extends AbstractAnalysisClientRunnableMessage {

	private static final long serialVersionUID = -4342498814307053600L;
	private Date stratDate;
	private Date endDate;
	private Boolean isEventsPersisted;

	public EventsSummaryRunnableMessage(Date startDate, Date endDate, String signalProcessingName, Boolean isEventsPersisted) {
		super(2000, SpringContext.getSingleton(), signalProcessingName);
		this.stratDate = startDate;
		this.endDate = endDate;
		this.isEventsPersisted = isEventsPersisted;
		
	}
	
	public void runEventsSummary() throws InterruptedException {
		
		this.sendRunnableStartProcessingEvent(getAnalysisName(), this);
		
		synchronized (syncObject) {
			syncObject.wait();
		}
	}

	@Override
	public void run() {
		
		try {
			
			List<UserPortfolio> userPortfolios = PortfolioMgr.getInstance().getUserPortfolios();
			
			SortedMap<Stock,StockCurrent> userStocks = new TreeMap<Stock,StockCurrent>();
			for (UserPortfolio userPortfolio : userPortfolios) {
				for (Stock stock : userPortfolio.getListShares().keySet()) {
					StockCurrent stockPortfolioShares = userStocks.get(stock);
					if (stockPortfolioShares == null) {
						stockPortfolioShares = new StockCurrent();
						userStocks.put(stock, stockPortfolioShares);
					}
					stockPortfolioShares.add(userPortfolio.getListShares().get(stock));
				}
			}

			EventSignalConfig eventSignalConfig = (EventSignalConfig) configs.get(Config.EVENT_SIGNAL_NAME);
			List<EventDefinition> indepIndicators = eventSignalConfig.getIndepIndicators();
			for (EventDefinition indepEventDefinition : indepIndicators) {
				List<SymbolEvents> listEventsForPeriodAndAnalysis = EventsResources.getInstance().getListEventsFor(this.stratDate, this.endDate, this.isEventsPersisted, this.analysisName+indepEventDefinition.name()); ;
				
				for (SymbolEvents symbolEvents : listEventsForPeriodAndAnalysis) {
					
					ArrayList<EventValue> stockSortedDataResultList = symbolEvents.getSortedDataResultList();
					
					StockCurrent stockCurrent = userStocks.get(symbolEvents.getStock());
					if (stockCurrent != null) {
						
						List<EventValue> filteredEventsForStockAndDef = stockCurrent.events.get(indepEventDefinition);
						if (filteredEventsForStockAndDef == null) {
							filteredEventsForStockAndDef = new ArrayList<EventValue>();
							stockCurrent.events.put(indepEventDefinition, filteredEventsForStockAndDef);
						}
						
						for (EventValue eventValue : stockSortedDataResultList) {
							if (eventValue.getEventDef().equals(indepEventDefinition)) {
								filteredEventsForStockAndDef.add(eventValue);
							}
						}
					}
				}
			}
			
			String eventDefSummaryMsgs = "";
			for (EventDefinition indepEventDefinition : indepIndicators) {
				
				Map<String, Double> ratios = new HashMap<String, Double>();
				ratios.put(EventType.BULLISH.name(), 0.0);
				ratios.put(EventType.BEARISH.name(), 0.0);
				ratios.put("Reversal to "+EventType.BULLISH, 0.0);
				ratios.put("Reversal to "+EventType.BEARISH, 0.0);
				
				Map<String, String> trendStrs = new HashMap<String, String>();
				trendStrs.put(EventType.BULLISH.name(), "");
				trendStrs.put(EventType.BEARISH.name(), "");
				trendStrs.put("Reversal to "+EventType.BULLISH, "");
				trendStrs.put("Reversal to "+EventType.BEARISH, "");
				
				eventDefSummaryMsgs = eventDefSummaryMsgs + " -----------------"+indepEventDefinition.toString()+"------------------- : \n";
				for (Stock stock : userStocks.keySet()) {
					StockCurrent stockCurrent = userStocks.get(stock);
					List<EventValue> eventsForEvtDef = stockCurrent.events.get(indepEventDefinition);
					if (eventsForEvtDef != null && eventsForEvtDef.size() > 0) {
						
						String eventsSumForDefAndStock = (stock.getName()+ "                               " ).substring(0, 30) + " : ";
						
						String eventsSumForDefAndStockString = "";
						for (EventValue eventValue : eventsForEvtDef) {
							eventsSumForDefAndStockString = eventsSumForDefAndStockString + eventValue.eventType + ",";
						}
						int evtStrLength = eventsSumForDefAndStockString.length();
						if (evtStrLength > 60) {
							eventsSumForDefAndStockString = eventsSumForDefAndStockString.substring(0,30)+ "..." +eventsSumForDefAndStockString.substring(evtStrLength-30, evtStrLength);
						}

						EventValue first = eventsForEvtDef.get(0);
						EventValue last = eventsForEvtDef.get(eventsForEvtDef.size()-1);
						if (first.getEventType().equals(last.getEventType())) {
							eventsSumForDefAndStock =  eventsSumForDefAndStock + last.getEventType() + " => " + eventsSumForDefAndStockString;
							ratios.put(last.getEventType().name(), ratios.get(last.getEventType().name())+ 1);
							trendStrs.put(last.getEventType().name(), trendStrs.get(last.getEventType().name())+eventsSumForDefAndStock + "\n");
						} else {
							eventsSumForDefAndStock = eventsSumForDefAndStock + "Reversal from " + first.getEventType() + " to "+ last.getEventType() + " => " + eventsSumForDefAndStockString ;
							ratios.put("Reversal to "+last.getEventType(), ratios.get("Reversal to "+last.getEventType())+ 1);
							trendStrs.put("Reversal to "+last.getEventType(), trendStrs.get("Reversal to "+last.getEventType())+eventsSumForDefAndStock + "\n");
						}
					} 
				}
				
				for (String trendStr : trendStrs.keySet()) {
					if (trendStrs.get(trendStr) != null && !trendStrs.get(trendStr).isEmpty())
					eventDefSummaryMsgs = eventDefSummaryMsgs + trendStr + " : \n" + trendStrs.get(trendStr) +"\n";
				}
				
				eventDefSummaryMsgs = eventDefSummaryMsgs  + "Ratios for "+indepEventDefinition + " are : ";
				NumberFormat numberFormat = NumberFormat.getPercentInstance();
				for (String ratio : ratios.keySet()) {
					eventDefSummaryMsgs = eventDefSummaryMsgs + ratio + " is "+ numberFormat.format(ratios.get(ratio)/userStocks.size()) + ", ";
				}
				
				eventDefSummaryMsgs = eventDefSummaryMsgs + "\n\n";
			}
			
			this.sendPeriodEventsSummary(eventDefSummaryMsgs);
			
		} finally {
			
			synchronized (syncObject) {
				syncObject.notify();
			}
		}
	
	}
	
	private void sendPeriodEventsSummary(final String eventDefSummaryMsgs) {
		jmsTemplate.send(eventQueue, new MessageCreator() {
			
			public Message createMessage(Session session) throws JMSException {
				
				EventValue eventValue = new EventValue(endDate, EventDefinition.UNKNOWN99, EventType.INFO, eventDefSummaryMsgs, analysisName);
				SingleEventMessage infoMessage = new SingleEventMessage(analysisName, endDate, AnalysisClient.ANY_STOCK, eventValue);
				infoMessage.setObjectProperty(MessageProperties.ANALYSE_SOURCE.getKey(), EventSource.PMUserBuySell); //Source (event calculator)
				infoMessage.setObjectProperty(MessageProperties.TREND.getKey(), eventValue.getEventType().name()); //Bearish Bullish Other Info
				infoMessage.setObjectProperty(MessageProperties.SEND_EMAIL.getKey(), Boolean.TRUE);
				
				return infoMessage;
			}
		});
	}
	
	class StockCurrent {
		
		SortedSet<PortfolioShare> portfolioShares;
		SortedMap<EventDefinition,List<EventValue>> events;
		
		public StockCurrent() {
			super();
			this.portfolioShares = new TreeSet<PortfolioShare>();
			this.events = new TreeMap<EventDefinition, List<EventValue>>();
		}

		public void add(PortfolioShare portfolioShare) {
			this.portfolioShares.add(portfolioShare);
		}
	}

}
