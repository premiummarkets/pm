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
package com.finance.pms.events;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import com.finance.pms.MainPMScmd;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.alerts.AlertOnThresholdType;
import com.finance.pms.datasources.shares.Market;
import com.finance.pms.datasources.shares.MarketValuation;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.web.ScraperMetrics;
import com.finance.pms.events.calculation.MessageProperties;
import com.finance.pms.portfolio.PortfolioMgr;
import com.finance.pms.queue.AbstractAnalysisClientRunnableMessage;
import com.finance.pms.queue.EmailMessage;
import com.finance.pms.queue.ExportAutoPortfolioMessage;
import com.finance.pms.queue.IdentifiedObjecMessage;
import com.finance.pms.queue.InnerQueue;
import com.finance.pms.queue.SingleEventMessage;
import com.finance.pms.queue.SymbolEventsMessage;
import com.finance.pms.threads.ConfigThreadLocal;


/**
 * The Class AnalysisClient.
 * 
 * @author Guillaume Thoreton
 */
public class AnalysisClient  implements MessageListener, ApplicationContextAware {

	protected static MyLogger LOGGER = MyLogger.getLogger(AnalysisClient.class);

	public static Stock ANY_STOCK = new Stock("ANYSTOCK","ANYSTOCK");
	static {
		ANY_STOCK.setName("ANY STOCK");
		ANY_STOCK.setMarketValuation(new MarketValuation(Market.EURONEXT));
	}
	private static EnumSet<EmailFilterEventSource> EMAILMSGQEUEINGFILTER = EmailFilterEventSource.webSet();

	private static EmailSendingFilter EMAILSENDINGLFILTER;

	ApplicationContext applicationContext;

	private MailSender mailSender;
	private SimpleMailMessage templateMessage;
	private InnerQueue eventQueue;
	private ThreadPoolExecutor analysisExecutor;

	@Autowired
	private ScraperMetrics scrapperMetrics;


	public AnalysisClient() {
		super();
		this.analysisExecutor = new ThreadPoolExecutor(0, 250, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
		this.analysisExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

	}

	public void onMessage(Message message) {

		try {

			if (message instanceof IdentifiedObjecMessage) {
				ConfigThreadLocal.setAll(((IdentifiedObjecMessage) message).getPassedThroughConfigs());
			} else {
				throw new IllegalArgumentException("The message is not an IdentifiedObjecMessage: " + message.getClass().getSimpleName());
			}
			if (message instanceof ExportAutoPortfolioMessage) { //Not used??  //Well, Export. This could be seen as a particular case of AbstractAnalysisClientRunnableMessage

				ExportAutoPortfolioMessage m = (ExportAutoPortfolioMessage) message;
				LOGGER.info("New export message received: " + m.getAnalyseName());
				runAsyncTask(m.getAnalyseName(), new ExportAutoPortfolioRunnable(m));

			} else if (message instanceof AbstractAnalysisClientRunnableMessage) {//Runnable Messages : screener, indicator (gwt), all stocks summaries and alerts on threshold

				AbstractAnalysisClientRunnableMessage runnableMessage = (AbstractAnalysisClientRunnableMessage) message;
				LOGGER.info("New runnable message received: " + runnableMessage.getAnalysisName() + " for " + runnableMessage.getClass().getName());
				runAsyncTask(runnableMessage.getAnalysisName(), runnableMessage);

			} else if (message instanceof EmailMessage) { //SymbolEvent : send email

				SymbolEvents symbolEventMessage = extractSymbolEventsObject(message);
				if (symbolEventMessage != null) {
					
					EventType eType = EventType.valueOf((String) message.getObjectProperty(MessageProperties.TREND.getKey()));
					EmailFilterEventSource source = (EmailFilterEventSource) message.getObjectProperty(MessageProperties.ANALYSE_SOURCE.getKey());
					String eventInfoRef = (String) message.getObjectProperty(MessageProperties.EVENT_INFO.getKey());
					if (eventInfoRef == null) eventInfoRef = "";

					//Email
					Boolean sendEmail = (Boolean) message.getObjectProperty(MessageProperties.SEND_EMAIL.getKey());

					if (sendEmail) {
						String eventListName = extractEventListName(message);
						String portfolioNames = extractPortfolioNames(message);
						sendEmail(symbolEventMessage, eType, source, eventListName, portfolioNames, eventInfoRef);
					}
				} else {
					LOGGER.warn("Ignored message: " + message);
				}

			} else {
				throw new IllegalArgumentException("Unrecognised message type: " + message.getClass().getSimpleName());
			}

		} catch (JMSException ex) {
			throw new RuntimeException(ex);
		}
	}

	private void sendEmail(final SymbolEvents symbolEvents, EventType eventType, EmailFilterEventSource source, String eventListName, String portfolioNames, String eventInfoRef) {

		Boolean sendMailEnabled = Boolean.valueOf(MainPMScmd.getMyPrefs().get("mail.infoalert.activated","false"));

		Boolean isFiltered = 
				(
						!source.equals(EmailFilterEventSource.PMTAEvents) ||
						(
								EMAILSENDINGLFILTER != null && symbolEvents.getEventDefList().size() == 1 && 
								EMAILSENDINGLFILTER.filtered().contains(symbolEvents.getEventDefList().iterator().next()) && 
								symbolEvents.getLastDate().getTime() >= EMAILSENDINGLFILTER.filterDate()
								)
						);

		Boolean isValidEventSource = symbolEvents.getStock().equals(ANY_STOCK) || PortfolioMgr.getInstance().isMonitoredFor(symbolEvents, eventListName);
		if 	( sendMailEnabled && isValidEventSource && isFiltered ) {
			this.sendMailEvent(symbolEvents, eventType, source, eventListName, portfolioNames, eventInfoRef);
		} 
	}

	private void runAsyncTask(String analysisName, Runnable runnable) {

		if (LOGGER.isDebugEnabled()) LOGGER.debug("Executing executor: " + runnable.getClass().getName() + " for " + analysisName);
		analysisExecutor.execute(runnable);

	}

	private SymbolEvents extractSymbolEventsObject(Message message) throws JMSException {

		try {
			if (message instanceof SingleEventMessage) {
				EventMessageObject eventMessageObject = (EventMessageObject)((ObjectMessage) message).getObject();
				return  new SymbolEvents(eventMessageObject);
			} else if (message instanceof SymbolEventsMessage) {
				return (SymbolEvents) ((ObjectMessage) message).getObject();
			}
		} catch (NoSuchFieldException e) {
			LOGGER.warn("Unrecognised message : " + message + ".\nEvent definition not found in this configuration (db.property, user calculator) " + e);
			return null;
		}

		throw new JMSException("Unrecognised message: " + message);
	}


	private String extractEventListName(Message message) throws JMSException {
		if (message instanceof SingleEventMessage) {
			EventMessageObject eventMessageObject = (EventMessageObject)((ObjectMessage) message).getObject();
			return eventMessageObject.getEventListName();
		} if (message instanceof SymbolEventsMessage) {
			return ((SymbolEventsMessage)message).getEventListName();
		}
		return null;
	}
	
	private String extractPortfolioNames(Message message) throws JMSException {
		if (message instanceof SingleEventMessage) {
			EventMessageObject eventMessageObject = (EventMessageObject)((ObjectMessage) message).getObject();
			return eventMessageObject.getPortfolios();
		}
		return null;
	}

	private void sendMailEvent(SymbolEvents event, EventType eventType, EmailFilterEventSource source, String eventListName, String portfolioNames, String eventInfoRef) {

		if (this.templateMessage.getTo() == null || this.templateMessage.getTo().length == 0 || this.templateMessage.getTo()[0] == null || this.templateMessage.getTo()[0].equals("")) {
			LOGGER.warn("No recipient set for this message. Event sending aborted!");
			return;
		}

		SimpleMailMessage mail = new SimpleMailMessage(this.templateMessage);
		String stockName = event.getSymbolName() +" (" + event.getSymbol() + ")";
		String eMailTxt = event.toEMail();
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String notaBene = "Message and calculation made on the " + df.format(new Date());
		String subject = "NONE";
		if (!eventInfoRef.isEmpty()) eventInfoRef = ", " + eventInfoRef;

		switch (eventType) {
		case BEARISH:
			String sellTriggeringEvents = " ";
			if (eventInfoRef.isEmpty()) {
				String sellTrigPat = "Sell Events\t: ";
				if (eMailTxt.contains(sellTrigPat)) {
					int sstartIdx = eMailTxt.indexOf(sellTrigPat) + sellTrigPat.length();
					sellTriggeringEvents = " with trigger " + eMailTxt.substring(sstartIdx, eMailTxt.indexOf("\n", sstartIdx));
				}
			}
			subject = source + ": " + stockName + eventInfoRef + " " + eventType.name() + sellTriggeringEvents + " in " + eventListName + " " + portfolioNames;
			break;
		case BULLISH:
			String buyTriggeringEvents = " ";
			if (eventInfoRef.isEmpty()) {
				String buyTrigPat = "Buy Events\t: ";
				if (eMailTxt.contains(buyTrigPat)) {
					int bstartIdx = eMailTxt.indexOf(buyTrigPat) + buyTrigPat.length();
					buyTriggeringEvents = " with trigger " + eMailTxt.substring(bstartIdx, eMailTxt.indexOf("\n", bstartIdx));
				}
			}
			subject = source + ": " + stockName + eventInfoRef + " " + eventType.name() + buyTriggeringEvents + " in " + eventListName + " " + portfolioNames;
			break;
		default:
			String addEventType = inferAdditionalEventTypeForOtherNInfoEventTypes(source, eMailTxt);
			subject = source + ": " + stockName + eventInfoRef + " " + addEventType + " in " + eventListName + " " + portfolioNames;
		}

		mail.setSubject(subject);
		mail.setText(eMailTxt + "\n\n\n" + notaBene);
		mail.setSentDate(event.getLastDate());

		LOGGER.info("Email/Popup message preview: " + mail);

		try {
			this.mailSender.send(mail);
		}
		catch(MailException ex) {
			LOGGER.error("Can't send email: " + subject + ";" + eMailTxt + "\n\n\n" + notaBene, ex);         
		}
	}

	private String inferAdditionalEventTypeForOtherNInfoEventTypes(EmailFilterEventSource eventSource, String eMailTxt) {

		String evenTypeAdd = EventType.INFO.name();

		switch(eventSource) {
			case PMUserScreening:
				if (eMailTxt.contains("rank is Down")) {
					evenTypeAdd = "BEARISH";
				} else if (eMailTxt.contains("rank is Up")) {
					evenTypeAdd= "BULLISH";
				} else if (eMailTxt.contains("Screener Alert")) {
					evenTypeAdd = "SCREENING";
				}
				break;
			case PMUserAlert:
				if (eMailTxt.contains(AlertOnThresholdType.ABOVE_TAKE_PROFIT_LIMIT.getText()) || eMailTxt.contains(AlertOnThresholdType.BELOW_MAX_LOSS_LIMIT.getText())) {
					evenTypeAdd = "SELL";
				} else if ((eMailTxt.contains(AlertOnThresholdType.BELOW_PRICE_CHANNEL.getText()))) {
					evenTypeAdd = "BEARISH";
				} else if (eMailTxt.contains(AlertOnThresholdType.ABOVE_PRICE_CHANNEL.getText())) {
					evenTypeAdd = "BULLISH";
				}
				break;
			case PMUserBuySell:
				if (eMailTxt.contains("movement : sell")) {
					evenTypeAdd = "SELL";
				} else if (eMailTxt.contains("movement : buy")) {
					evenTypeAdd = "BUY";
				}
				break;
			default :
		}

		return evenTypeAdd;
	}

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void setTemplateMessage(SimpleMailMessage templateMessage) {
		this.templateMessage = templateMessage;
	}

	public void close() {

		try {
			LOGGER.info("Waiting for queue to be empty and processed.");
			int cpt = 0;
			while (!eventQueue.isEmptyAndProcessed() && cpt++ < 60) {
				LOGGER.info("Number of task left: " + eventQueue.messagesInProcess());
				LOGGER.info("Task left: " + eventQueue.toString());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					LOGGER.error("Error while waiting for event queue starvation.", e);
				}
			}
			LOGGER.info("Queue is now empty.");

			LOGGER.info("Waiting for all runnable messages to stop.");
			analysisExecutor.shutdown();
			boolean awaitTermination = analysisExecutor.awaitTermination(100, TimeUnit.DAYS);
			if (!awaitTermination) {
				List<Runnable> shutdownNow = analysisExecutor.shutdownNow();
				LOGGER.error(shutdownNow,new Exception());
			}
			LOGGER.info("All runnable messages are stopped.");

			Boolean hibOnClose = Boolean.valueOf(MainPMScmd.getMyPrefs().get("portofolio.hibernateonclose", "true"));
			LOGGER.info("Saving portfolios on shutdown: " + hibOnClose);
			if (hibOnClose) {
				LOGGER.info("Saving auto portfolios.");
				try {
					PortfolioMgr.getInstance().hibStorePortfolio();
				} catch (Exception e) {
					LOGGER.error("Error while closing AnalysisClient.", e);
				}
				LOGGER.info("Auto portfolios are saved.");
			}

			LOGGER.info("Sending metrics.");
			String metrics = scrapperMetrics.getMetrics(false);
			try {
				if (!metrics.isEmpty()) {
					SimpleMailMessage mail = new SimpleMailMessage(this.templateMessage);
					mail.setSubject("Scraper metrics");
					mail.setText(metrics);
					this.mailSender.send(mail);
					LOGGER.info("Metrics sent.");
				} else {
					LOGGER.info("Metrics are empty.");
				}
			} catch (Exception e) {
				LOGGER.warn("Can't send metrics. Please set up your email account in Settings ...? Here they are: " + metrics);
			}

			LOGGER.info("AnalysisClient is closed.");

		} catch (Exception e) {
			System.out.println("Error closing AnalysisClient: " + e);
			e.printStackTrace();
		}
	}

	public static void setEmailSendingFilter(EmailSendingFilter emailSendingFilter) {
		EMAILSENDINGLFILTER = emailSendingFilter;
	}

	public static void clearEmailSendingFilter() {
		EMAILSENDINGLFILTER = null;
	}

	public static EnumSet<EmailFilterEventSource> getEmailMsgQeueingFilter() {
		return AnalysisClient.EMAILMSGQEUEINGFILTER;
	}

	public static void addEmailMsgQeueingFilter(EmailFilterEventSource element) {
		AnalysisClient.EMAILMSGQEUEINGFILTER.add(element);
	}

	public static void removeEmailMsgQeueingFilter(EmailFilterEventSource element) {
		AnalysisClient.EMAILMSGQEUEINGFILTER.remove(element);
	}

	public static void setEmailMsgQeueingFilter(EnumSet<EmailFilterEventSource> emailMsgQeueingFilter) {
		AnalysisClient.EMAILMSGQEUEINGFILTER = emailMsgQeueingFilter;
	}

	public void setEventQueue(InnerQueue eventQueue) {
		this.eventQueue = eventQueue;
	}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext= applicationContext;
	}

}
