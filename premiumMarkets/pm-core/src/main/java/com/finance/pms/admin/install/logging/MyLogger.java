/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * Around 80% of predicted trades more profitable than buy and hold, leading to 4 times 
 * more profit, while back testing over NYSE, NASDAQ, EURONEXT and LSE, Back testing, 
 * Automated buy sell email notifications on trend change signals calculated over markets 
 * and user defined portfolios. See Premium Markets FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com for documentation and a free workable demo.
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
package com.finance.pms.admin.install.logging;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Observable;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.TreeSet;
import java.util.concurrent.Semaphore;
import java.util.prefs.BackingStoreException;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JFrame;

import org.apache.log4j.Appender;
import org.apache.log4j.Category;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.apache.log4j.spi.LoggerRepository;
import org.apache.log4j.spi.LoggingEvent;

import com.finance.pms.CustomDialog;
import com.finance.pms.MainPMScmd;
import com.finance.pms.SpringContext;


// TODO: Auto-generated Javadoc
/**
 * The Class MyLogger.
 * 
 * @author Guillaume Thoreton
 */
public class MyLogger {
	
	/**
	 * Gets the logger.
	 * 
	 * @param clazz the clazz
	 * 
	 * @return the logger
	 */
	public static MyLogger getLogger(Class<? extends Object> clazz) {
		return new MyLogger(Logger.getLogger(clazz));
	}
	
	private static long msgDelay = new Long(MainPMScmd.getPrefs().get("mail.log.delay", "4000"));

	private static String mailUserName = null;
	private static String mailPassword = null; 
	private static String mailHost = null; 
	private static String mailTo = "trashpms@gmail.com";
	private static String mailFrom = null;
	
	
	private static TreeSet<Integer> hashesSet = new TreeSet<Integer>();
	private static File hashCodesFile = new File("hashes.txt");
	
	private static Session session;
	private static Semaphore semaphore;
	
	private static String mailActivationType;
	
	//public static String lastMessage = ""; 
	public static GuiLoggerObservable lastMsg = new GuiLoggerObservable();
	
	public static String version = "None";
	
	
	
	static {
		
		MyLogger.semaphore = new Semaphore(1);
		
		try {
			//Init props
			Properties props = new Properties();
			String dbProperty = System.getProperty("dbproperties");
			if (dbProperty == null) dbProperty = "db.properties";
			props.load(new FileInputStream((new File(System.getProperty("installdir")+File.separator+dbProperty))));
			if (props.containsKey("mail.log.delay")) {
				msgDelay = new Long(props.getProperty("mail.log.delay"));
			} else {
				msgDelay = 4000;
			}
			if (props.containsKey("mail.log.activated")) {
				MainPMScmd.getPrefs().put("mail.log.activated", props.getProperty("mail.log.activated"));
				MyLogger.mailActivationType = props.getProperty("mail.log.activated");  
			} else {
				MyLogger.mailActivationType =  MainPMScmd.getPrefs().get("mail.log.activated", "false");
			}
			if (props.containsKey("mail.log.local")) {
				MainPMScmd.getPrefs().put("mail.log.local", props.getProperty("mail.log.local"));
			}

			if (props.containsKey("mail.username")) {
				MainPMScmd.getPrefs().put("mail.username", props.getProperty("mail.username"));
			}
			if (props.containsKey("mail.password")) {
				MainPMScmd.getPrefs().put("mail.password", props.getProperty("mail.password"));
			}
			if (props.containsKey("mail.host")) {
				MainPMScmd.getPrefs().put("mail.host", props.getProperty("mail.host"));
			}
			if (props.containsKey("mail.from")) {
				MainPMScmd.getPrefs().put("mail.from", props.getProperty("mail.from"));
			}
			MainPMScmd.getPrefs().flush();
			
		} catch (Throwable e3) {
			e3.printStackTrace();
			MainPMScmd.getPrefs().put("mail.log.activated", "false");
			try {
				MainPMScmd.getPrefs().flush();
			} catch (BackingStoreException e1) {
				e1.printStackTrace();
			}
		}

		try {

			//Reset smtp connection settings for log msgs if they have been set up by user
			String propsMailUserName = MainPMScmd.getPrefs().get("mail.username", "nouser");
			String propsMailPassword = MainPMScmd.getPrefs().get("mail.password","nopassword"); 
			String propsMailHost = MainPMScmd.getPrefs().get("mail.host", null);
			boolean allConnectionFieldsAreValid = propsMailHost != null && !propsMailHost.isEmpty();
			if ( allConnectionFieldsAreValid ) {
				MyLogger.mailHost = propsMailHost;
				MyLogger.mailUserName = propsMailUserName;
				MyLogger.mailPassword = propsMailPassword;

				//Recover from previous failure
				if (MyLogger.mailActivationType.equals("failed")) {
					MainPMScmd.getPrefs().put("mail.log.activated", "true");
					try {
						MainPMScmd.getPrefs().flush();
					} catch (BackingStoreException e1) {
						e1.printStackTrace();
					}
				}

				MyLogger.mailFrom = MainPMScmd.getPrefs().get("mail.from", MyLogger.mailUserName);

				//Session props and email addresses
				Properties mailSessionProps = new Properties();
				mailSessionProps.put("mail.transport.protocol", "smtp");
				mailSessionProps.put("mail.smtp.host", MyLogger.mailHost);


				Boolean isLocal = new Boolean(MainPMScmd.getPrefs().get("mail.log.local", "false"));
				if (isLocal) {

					MyLogger.mailTo = MainPMScmd.getPrefs().get("mail.to", MyLogger.mailFrom);
					MyLogger.session = Session.getInstance(mailSessionProps);

				} else {

					//Test the smtp session
					mailSessionProps.put("mail.smtp.starttls.enable", "true");
					mailSessionProps.put("mail.smtp.auth", "true");

					InternetAddress senderAddress;
					try {
						senderAddress = new InternetAddress(MyLogger.mailFrom);
					} catch (Exception e) {
						senderAddress = new InternetAddress(MyLogger.mailUserName);
					}

					MyLogger.session = Session.getInstance(mailSessionProps);
					Transport transport = MyLogger.session.getTransport();
					MimeMessage testMsg = new MimeMessage(MyLogger.session);
					testMsg.setSubject("Smtp connection test msg");
					testMsg.setText("Smtp connection test msg");
					testMsg.setFrom(senderAddress);
					testMsg.setSender(senderAddress);
					Address[] rTo = {senderAddress};
					testMsg.setReplyTo(rTo);
					testMsg.setRecipients(Message.RecipientType.TO, mailTo);
					testMsg.saveChanges();   
					try {
						transport.connect(MyLogger.mailUserName, MyLogger.mailPassword);
						transport.sendMessage(testMsg, testMsg.getAllRecipients());
						System.out.println("Valid auths connection");
					} catch (Exception e) {
						mailSessionProps.put("mail.smtp.starttls.enable", "false");
						MyLogger.session = Session.getInstance(mailSessionProps);
						try {
							transport.connect(MyLogger.mailUserName, MyLogger.mailPassword);
							transport.sendMessage(testMsg, testMsg.getAllRecipients());
							System.out.println("Valid auth connection");
						} catch (Exception e1) {
							mailSessionProps.put("mail.smtp.auth", "false");
							MyLogger.session = Session.getInstance(mailSessionProps);
							try {
								transport.connect(MyLogger.mailUserName, MyLogger.mailPassword);
								transport.sendMessage(testMsg, testMsg.getAllRecipients());
								System.out.println("Valid non auth smtp connection");
							} catch (Exception e2) {
								System.out.println("Could not set up error msg handling.\nThis feature will be disabled until you set up your smtp connection in Settings ..." + e); 
								if (MyLogger.mailActivationType.equals("true")) {
									MainPMScmd.getPrefs().put("mail.log.activated", "failed");
									MainPMScmd.getPrefs().flush();
								}

							}
						}
					}
				}
				
			} else {
				if (MyLogger.mailActivationType.equals("true")) {
					MainPMScmd.getPrefs().put("mail.log.activated", "failed");
					MainPMScmd.getPrefs().flush();
				};
				try {
					MainPMScmd.getPrefs().flush();
				} catch (BackingStoreException e1) {
					e1.printStackTrace();
				}
			}

		} catch (Throwable e) {
			
			System.out.println("log send failed, exception: " + e); 
			System.out.println("Could not set up error msg handling.\nThis feature will be disabled until you set up your smtp connection in Settings ..." + e); 
			if (MyLogger.mailActivationType.equals("true")) {
				MainPMScmd.getPrefs().put("mail.log.activated", "failed");
			}
			try {
				MainPMScmd.getPrefs().flush();
			} catch (BackingStoreException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			
		}
		
		//Init hashesSet
		try {
			hashCodesFile.delete();
			
		} catch (Throwable e) {
			System.out.println("log send failed, exception: " + e); 
			e.printStackTrace();
		}
		
		//Get version
		try {
			Properties pbuild = new Properties();
			pbuild.load(MyLogger.class.getClassLoader().getResourceAsStream("pmsbuild.properties"));
			version = pbuild.getProperty("application.buildtime");
		} catch (Throwable e1) {
			System.out.println("log send failed, exception: " + e1); 
			e1.printStackTrace();
		}
		
	}
	
	/** The delegate logger. */
	private Logger delegateLogger;
	
	
	public static class GuiLoggerObservable extends Observable {
		
		String lastMessage;

		public void setLastMessage(String lastMessage) {
			this.lastMessage = lastMessage;
			setChanged();
			notifyObservers(lastMessage);
		}

		public String getLastMessage() {
			return lastMessage;
		} 
		
	}

	/**
	 * Instantiates a new my logger.
	 * 
	 * @param delagateLogger the delegate logger
	 * 
	 * @author Guillaume Thoreton
	 */
	public MyLogger(Logger delagateLogger) {
		super();
		this.delegateLogger = delagateLogger;
	}

	/**
	 * Adds the appender.
	 * 
	 * @param newAppender the new appender
	 * 
	 * @author Guillaume Thoreton
	 */
	public void addAppender(Appender newAppender) {
		delegateLogger.addAppender(newAppender);
	}

	/**
	 * Assert log.
	 * 
	 * @param assertion the assertion
	 * @param msg the msg
	 * 
	 * @author Guillaume Thoreton
	 */
	public void assertLog(boolean assertion, String msg) {
		delegateLogger.assertLog(assertion, msg);
	}

	/**
	 * Call appenders.
	 * 
	 * @param event the event
	 * 
	 * @author Guillaume Thoreton
	 */
	public void callAppenders(LoggingEvent event) {
		delegateLogger.callAppenders(event);
	}

	/**
	 * Debug.
	 * 
	 * @param message the message
	 * @param t the t
	 * 
	 * @author Guillaume Thoreton
	 */
	public void debug(Object message, Throwable t) {
		delegateLogger.debug(message, t);
	}

	/**
	 * Debug.
	 * 
	 * @param message the message
	 * 
	 * @author Guillaume Thoreton
	 */
	public void debug(Object message) {
		delegateLogger.debug(message);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return delegateLogger.equals(obj);
	}

	/**
	 * Error.
	 * 
	 * @param message the message
	 * @param t the t
	 * 
	 * @author Guillaume Thoreton
	 */
	public void error(Object message, Throwable t) {
		delegateLogger.error(message, t);
		this.sendMail(message, t, false);
	}
	
	public void warn(Object message, Boolean isTest) {
		delegateLogger.warn(message);
		this.sendMail(message, null, isTest);
	}
	
	public void warn(Object message, Throwable t, Boolean isTest) {
		delegateLogger.warn(message, t);
		this.sendMail(message, t, isTest);
	}

	/**
	 * Error.
	 * 
	 * @param message the message
	 * 
	 * @author Guillaume Thoreton
	 */
	public void error(Object message) {
		delegateLogger.error(message);
		this.sendMail(message, null, false);
	}

	/**
	 * Fatal.
	 * 
	 * @param message the message
	 * @param t the t
	 * 
	 * @author Guillaume Thoreton
	 */
	public void fatal(Object message, Throwable t) {
		delegateLogger.fatal(message, t);
		this.sendMail(message, t, false);
	}

	/**
	 * Fatal.
	 * 
	 * @param message the message
	 * 
	 * @author Guillaume Thoreton
	 */
	public void fatal(Object message) {
		delegateLogger.fatal(message);
		this.sendMail(message, null, false);
	}

	/**
	 * Gets the additivity.
	 * 
	 * @return the additivity
	 */
	public boolean getAdditivity() {
		return delegateLogger.getAdditivity();
	}

	/**
	 * Gets the all appenders.
	 * 
	 * @return the all appenders
	 */
	public Enumeration<?> getAllAppenders() {
		return delegateLogger.getAllAppenders();
	}

	/**
	 * Gets the appender.
	 * 
	 * @param name the name
	 * 
	 * @return the appender
	 */
	public Appender getAppender(String name) {
		return delegateLogger.getAppender(name);
	}

	/**
	 * Gets the chained priority.
	 * 
	 * @return the chained priority
	 */
	@Deprecated
	public Priority getChainedPriority() {
		return delegateLogger.getChainedPriority();
	}

	/**
	 * Gets the effective level.
	 * 
	 * @return the effective level
	 */
	public Level getEffectiveLevel() {
		return delegateLogger.getEffectiveLevel();
	}

	/**
	 * Gets the hierarchy.
	 * 
	 * @return the hierarchy
	 */
	@Deprecated
	public LoggerRepository getHierarchy() {
		return delegateLogger.getHierarchy();
	}

	/**
	 * Gets the level.
	 * 
	 * @return the level
	 */
	public final Level getLevel() {
		return delegateLogger.getLevel();
	}

	/**
	 * Gets the logger repository.
	 * 
	 * @return the logger repository
	 */
	public LoggerRepository getLoggerRepository() {
		return delegateLogger.getLoggerRepository();
	}

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public final String getName() {
		return delegateLogger.getName();
	}

	/**
	 * Gets the parent.
	 * 
	 * @return the parent
	 */
	public final Category getParent() {
		return delegateLogger.getParent();
	}

	/**
	 * Gets the priority.
	 * 
	 * @return the priority
	 */
	@Deprecated
	public final Level getPriority() {
		return delegateLogger.getPriority();
	}

	/**
	 * Gets the resource bundle.
	 * 
	 * @return the resource bundle
	 */
	public ResourceBundle getResourceBundle() {
		return delegateLogger.getResourceBundle();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return delegateLogger.hashCode();
	}

	/**
	 * Info.
	 * 
	 * @param message the message
	 * @param t the t
	 * 
	 * @author Guillaume Thoreton
	 */
	public void info(Object message, Throwable t) {
		delegateLogger.info(message, t);
	}

	/**
	 * Info.
	 * 
	 * @param message the message
	 * 
	 * @author Guillaume Thoreton
	 */
	public void info(Object message) {
		delegateLogger.info(message);
	}
	
	/**
	 * Gui info.
	 * 
	 * @param message the message
	 * 
	 * @author Guillaume Thoreton
	 */
	public void guiInfo(Object message) {
		delegateLogger.info(message);
		//lastMessage = message.toString();
		lastMsg.setLastMessage(message.toString());
		
	}

	/**
	 * Checks if is attached.
	 * 
	 * @param appender the appender
	 * 
	 * @return true, if is attached
	 */
	public boolean isAttached(Appender appender) {
		return delegateLogger.isAttached(appender);
	}

	/**
	 * Checks if is debug enabled.
	 * 
	 * @return true, if is debug enabled
	 */
	public boolean isDebugEnabled() {
		return delegateLogger.isDebugEnabled();
	}

	/**
	 * Checks if is enabled for.
	 * 
	 * @param level the level
	 * 
	 * @return true, if is enabled for
	 */
	public boolean isEnabledFor(Priority level) {
		return delegateLogger.isEnabledFor(level);
	}

	/**
	 * Checks if is info enabled.
	 * 
	 * @return true, if is info enabled
	 */
	public boolean isInfoEnabled() {
		return delegateLogger.isInfoEnabled();
	}

	/**
	 * Checks if is trace enabled.
	 * 
	 * @return true, if is trace enabled
	 */
	public boolean isTraceEnabled() {
		return delegateLogger.isTraceEnabled();
	}

	/**
	 * L7dlog.
	 * 
	 * @param priority the priority
	 * @param key the key
	 * @param params the params
	 * @param t the t
	 * 
	 * @author Guillaume Thoreton
	 */
	public void l7dlog(Priority priority, String key, Object[] params, Throwable t) {
		delegateLogger.l7dlog(priority, key, params, t);
	}

	/**
	 * L7dlog.
	 * 
	 * @param priority the priority
	 * @param key the key
	 * @param t the t
	 * 
	 * @author Guillaume Thoreton
	 */
	public void l7dlog(Priority priority, String key, Throwable t) {
		delegateLogger.l7dlog(priority, key, t);
	}

	/**
	 * Log.
	 * 
	 * @param priority the priority
	 * @param message the message
	 * @param t the t
	 * 
	 * @author Guillaume Thoreton
	 */
	public void log(Priority priority, Object message, Throwable t) {
		delegateLogger.log(priority, message, t);
	}

	/**
	 * Log.
	 * 
	 * @param priority the priority
	 * @param message the message
	 * 
	 * @author Guillaume Thoreton
	 */
	public void log(Priority priority, Object message) {
		delegateLogger.log(priority, message);
	}

	/**
	 * Log.
	 * 
	 * @param callerFQCN the caller fqcn
	 * @param level the level
	 * @param message the message
	 * @param t the t
	 * 
	 * @author Guillaume Thoreton
	 */
	public void log(String callerFQCN, Priority level, Object message, Throwable t) {
		delegateLogger.log(callerFQCN, level, message, t);
	}

	/**
	 * Removes the all appenders.
	 * 
	 * @author Guillaume Thoreton
	 */
	public void removeAllAppenders() {
		delegateLogger.removeAllAppenders();
	}

	/**
	 * Removes the appender.
	 * 
	 * @param appender the appender
	 * 
	 * @author Guillaume Thoreton
	 */
	public void removeAppender(Appender appender) {
		delegateLogger.removeAppender(appender);
	}

	/**
	 * Removes the appender.
	 * 
	 * @param name the name
	 * 
	 * @author Guillaume Thoreton
	 */
	public void removeAppender(String name) {
		delegateLogger.removeAppender(name);
	}

	/**
	 * Sets the additivity.
	 * 
	 * @param additive the new additivity
	 */
	public void setAdditivity(boolean additive) {
		delegateLogger.setAdditivity(additive);
	}

	/**
	 * Sets the level.
	 * 
	 * @param level the new level
	 */
	public void setLevel(Level level) {
		delegateLogger.setLevel(level);
	}

	/**
	 * Sets the priority.
	 * 
	 * @param priority the new priority
	 */
	@Deprecated
	public void setPriority(Priority priority) {
		delegateLogger.setPriority(priority);
	}

	/**
	 * Sets the resource bundle.
	 * 
	 * @param bundle the new resource bundle
	 */
	public void setResourceBundle(ResourceBundle bundle) {
		delegateLogger.setResourceBundle(bundle);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return delegateLogger.toString();
	}

	/**
	 * Trace.
	 * 
	 * @param message the message
	 * @param t the t
	 * 
	 * @author Guillaume Thoreton
	 */
	public void trace(Object message, Throwable t) {
		delegateLogger.trace(message, t);
	}

	/**
	 * Trace.
	 * 
	 * @param message the message
	 * 
	 * @author Guillaume Thoreton
	 */
	public void trace(Object message) {
		delegateLogger.trace(message);
	}

	/**
	 * Warn.
	 * 
	 * @param message the message
	 * @param t the t
	 * 
	 * @author Guillaume Thoreton
	 */
	public void warn(Object message, Throwable t) {
		delegateLogger.warn(message, t);
	}

	/**
	 * Warn.
	 * 
	 * @param message the message
	 * 
	 * @author Guillaume Thoreton
	 */
	public void warn(Object message) {
		delegateLogger.warn(message);
	}

	/**
	 * Send mail.
	 * 
	 * @param errorMsg the error msg
	 * @param error the error
	 * 
	 * @author Guillaume Thoreton
	 */
	//Email settings options :
	//false (no email no popup), 
	//true (send error email only - no test error email, no duplicates -, with popup), 
	//force (send error email and test error email - with duplicates -, no popup),
	//forceNoTest (send only errors, no test and no popup)
	private void sendMail(Object errorMsg, final Throwable error, final Boolean isTest) {
		
		final String errorStr = (null == errorMsg)?"No message available":errorMsg.toString();
		
		//Is active?
		MyLogger.mailActivationType = MainPMScmd.getPrefs().get("mail.log.activated", "true");  
		System.out.println("Mail Settings : log activated : " + MyLogger.mailActivationType); 
		if ("false".equals(MyLogger.mailActivationType) || SpringContext.getSingleton() == null || !SpringContext.getSingleton().isActive()) return;
		
		Thread sendLogThread = new Thread() {
			@Override
			public void run() {
				
				JFrame frame = null;
				CustomDialog customDialog = null;
				
				try {
					semaphore.acquire();
					
					MyLogger.mailActivationType = MainPMScmd.getPrefs().get("mail.log.activated", "true");  
					if ("false".equals(MyLogger.mailActivationType) || SpringContext.getSingleton() == null || !SpringContext.getSingleton().isActive()) return;
					
					Boolean isSendingErrorEmail = "true".equals(MyLogger.mailActivationType) || "force".equals(MyLogger.mailActivationType) ||  "forceNoTest".equals(MyLogger.mailActivationType);
					Boolean isSendingTestEmail = "force".equals(MyLogger.mailActivationType);
					Boolean isFailed = "failed".equals(MyLogger.mailActivationType);
					
					Boolean isSendingEmail = ((isTest && isSendingTestEmail) || (!isTest && isSendingErrorEmail)) && !isFailed;
					Boolean isPopup = ("true".equals(MyLogger.mailActivationType) || "failed".equals(MyLogger.mailActivationType)) && !isTest ;
					Boolean hasDuplicate = isTest;
					
					
					if (!isSendingEmail && !isPopup) return;
					
					//Msg hash
					Integer bodyHashcode = createMsgBodyFirstLines(error, errorStr, 3).toString().hashCode();
					if (!hasDuplicate && hashesSet.contains(bodyHashcode)) {
						return;
					} 
					if (hasDuplicate && !hashesSet.contains(bodyHashcode)) {
						hashesSet.add(bodyHashcode);
						writeHashesToFile(bodyHashcode);
					}
					
					if (isPopup) {
						
						///Dialog
						try {

							frame = new JFrame();
							String report = "An error has occurred.\nThis error may be recoverable.\n";
							if (isFailed) {
								report = report + "Your smtp connection is not set. In order to proceed with solving this error, you will have to go into Settings and setup your SMTP connection.\n";
							} else {
								report = report + "By cliking OK on the button below this error will automatically be sent to the development team.\n";
							}
							report = report + "You can also disable these popups in the Settings menu by setting activate error logging to false and restart.\n";
									
							customDialog = new CustomDialog(frame, report, createMsgBodyFirstLines(error, errorStr, 20).toString(), "Error Report", true);
							customDialog.pack();
							customDialog.setVisible(true);
							customDialog.dispose();
							frame.dispose();

							if ("Ok".equals(customDialog.getOptionPane().getValue())) {
								if (isSendingEmail) doSend(bodyHashcode, isTest);
							} else {
								MainPMScmd.getPrefs().put("mail.log.activated", "false");
								MainPMScmd.getPrefs().flush();
							}

						} catch (Throwable e) {
							delegateLogger.error("Can't open error popup for acknowledment.");
							e.printStackTrace();
						}
						
					} else if (isSendingEmail) {
						doSend(bodyHashcode, isTest);
					}
					
					
				} catch (Throwable mex) {
					mex.printStackTrace();
					delegateLogger.error("Failed to process error \""+errorStr+"\", cause \""+mex+"\".\n" +
							"Errors can be forwarded to development team by email.\n" +
							"To enable this feature, you must setup your email parameters in the Settings menu and restart.\n" +
							"Thanks.\n\n");
					
				} finally {
					customDialog = null;
					frame = null;
					try {
						Thread.sleep(msgDelay);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					semaphore.release();
					
				}
				
			}

			private void doSend(Integer bodyHashcode, boolean isTestMail) throws IOException, MessagingException {

				//Email msg
				Transport transport = MyLogger.session.getTransport("smtp"); 
				transport.connect(MyLogger.mailUserName, MyLogger.mailPassword);

				MimeMessage msg = new MimeMessage(MyLogger.session);		
				InternetAddress senderAddress = fromAdressResolution();

				StringBuffer msgBody = createMsgBodyFirstLines(error, errorStr, 200);
				String msgSubject = "Error detected on Version build : "+version+ " from user "+senderAddress;
				
				if (isTestMail) {
					msgBody.insert(0, "This is an info test message : \n");
					msgSubject = msgSubject.replaceFirst("Error detected", "Warning detected");
				}

				msg.setFrom(senderAddress);
				msg.setSender(senderAddress);
				Address[] rTo = {senderAddress};
				msg.setReplyTo(rTo);
				msg.setRecipients(Message.RecipientType.TO, MyLogger.mailTo); 
				msg.setSubject(msgSubject); 
				msg.setSentDate(new Date());					
				msg.setText(msgBody.toString());

				msg.saveChanges();      // don't forget this

				transport.sendMessage(msg, msg.getRecipients(Message.RecipientType.TO));
				
				transport.close();
			}

			private InternetAddress fromAdressResolution() {

				InternetAddress senderAddress;
				try {
					senderAddress = new InternetAddress(MyLogger.mailFrom);
				} catch (Exception e) {
					try {
						senderAddress = new InternetAddress(MyLogger.mailUserName);
					} catch (Exception e1) {
						delegateLogger.error("Error from adress :"+e); 
						e.printStackTrace();
						senderAddress = new InternetAddress();
					}
				}
				return senderAddress;
			}

			/**
			 * @param error
			 * @param errorStr
			 * @return
			 */
			private StringBuffer createMsgBodyFirstLines(Throwable error, String errorStr, Integer sizeMax) {
				StringBuffer msgBoddy = new StringBuffer("Text : " + errorStr + "\n");  
				if (error != null) {
					msgBoddy.append("Error :" + error + "\n"); 
					msgBoddy.append("Message :" + error.getMessage() + "\n");  
					msgBoddy.append("Cause :" + error.getCause() + "\n");  
					StackTraceElement[] ste = error.getStackTrace();
					for (int i = 0; i < Math.min(ste.length,sizeMax); i++) {
						msgBoddy.append(ste[i] + "\n"); 
					}
				}
				return msgBoddy;
			}

			/**
			 * @param bodyHashcode
			 * @throws IOException
			 */
			private void writeHashesToFile(Integer bodyHashcode) throws IOException {
				BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(hashCodesFile,true));
				bufferedWriter.write(bodyHashcode.toString());
				bufferedWriter.newLine();
				bufferedWriter.flush();
				bufferedWriter.close();
			}
		};
		
		sendLogThread.setDaemon(true);
		sendLogThread.start();
	}
}
