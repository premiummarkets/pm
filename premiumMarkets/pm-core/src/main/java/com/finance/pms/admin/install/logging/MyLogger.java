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
package com.finance.pms.admin.install.logging;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Observable;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.TreeSet;
import java.util.concurrent.Semaphore;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
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

/**
 * The Class MyLogger.
 * 
 * @author Guillaume Thoreton
 */
public class MyLogger {
	
	public static MyLogger getLogger(Class<? extends Object> clazz) {
		return new MyLogger(Logger.getLogger(clazz));
	}
	
	private static long msgDelay = new Long(MainPMScmd.getMyPrefs().get("mail.log.delay", "4000"));

	private static String mailUserName = null;
	private static String mailPassword = null; 
	private static String mailHost = null; 
	private static String mailTo = "trashpms@gmail.com";
	private static String mailFrom = null;
	
	
	private static TreeSet<Integer> hashesSet = new TreeSet<Integer>();

	private static Session session;
	private static Semaphore semaphore;
	
	private static String mailActivationType;
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
				MainPMScmd.getMyPrefs().put("mail.log.activated", props.getProperty("mail.log.activated"));
				MyLogger.mailActivationType = props.getProperty("mail.log.activated");
				System.out.println("Logger activation status (in accordance with 'mail.log.activated' prop in db.properties) : "+MyLogger.mailActivationType);
			} else {
				MyLogger.mailActivationType =  MainPMScmd.getMyPrefs().get("mail.log.activated", "false");
				System.out.println("Logger activation status (no 'mail.log.activated' prop in db.properties) : "+MyLogger.mailActivationType);
			}
			
			if (props.containsKey("mail.log.local")) {
				MainPMScmd.getMyPrefs().put("mail.log.local", props.getProperty("mail.log.local"));
			}
			if (props.containsKey("mail.username")) {
				MainPMScmd.getMyPrefs().put("mail.username", props.getProperty("mail.username"));
			}
			if (props.containsKey("mail.password")) {
				MainPMScmd.getMyPrefs().put("mail.password", props.getProperty("mail.password"));
			}
			if (props.containsKey("mail.host")) {
				MainPMScmd.getMyPrefs().put("mail.host", props.getProperty("mail.host"));
			}
			if (props.containsKey("mail.from")) {
				MainPMScmd.getMyPrefs().put("mail.from", props.getProperty("mail.from"));
			}

			MainPMScmd.getMyPrefs().flushy();

			
		} catch (Throwable e3) {
			e3.printStackTrace();
			MainPMScmd.getMyPrefs().put("mail.log.activated", "false");
			MainPMScmd.getMyPrefs().flushy();
		}

		try {

			//Reset smtp connection settings for log msgs if they have been set up by user
			String propsMailUserName = MainPMScmd.getMyPrefs().get("mail.username", "nouser");
			String propsMailPassword = MainPMScmd.getMyPrefs().get("mail.password","nopassword"); 
			String propsMailHost = MainPMScmd.getMyPrefs().get("mail.host", null);
			boolean allConnectionFieldsAreValid = propsMailHost != null && !propsMailHost.isEmpty();
			
			if ( allConnectionFieldsAreValid ) {
				
				MyLogger.mailHost = propsMailHost;
				MyLogger.mailUserName = propsMailUserName;
				MyLogger.mailPassword = propsMailPassword;

				//Recover from previous failure
				if (MyLogger.mailActivationType.equals("failed")) {
					MainPMScmd.getMyPrefs().put("mail.log.activated", "true");
					MainPMScmd.getMyPrefs().flushy();
				}

				MyLogger.mailFrom = MainPMScmd.getMyPrefs().get("mail.from", MyLogger.mailUserName);

				//Session props and email addresses
				Properties mailSessionProps = new Properties();
				mailSessionProps.put("mail.transport.protocol", "smtp");
				mailSessionProps.put("mail.smtp.host", MyLogger.mailHost);


				Boolean isLocal = new Boolean(MainPMScmd.getMyPrefs().get("mail.log.local", "false"));
				if (isLocal) {

					MyLogger.mailTo = MainPMScmd.getMyPrefs().get("mail.to", MyLogger.mailFrom);
					mailSessionProps.put("mail.smtp.localhost", "localhost");
					MyLogger.session = Session.getInstance(mailSessionProps, null);
					//MyLogger.session.setDebug(true);

				} else {

					//Test the smtp session
					mailSessionProps.put("mail.smtp.localhost", MainPMScmd.getMyPrefs().get("site.url", "none.com"));
					mailSessionProps.put("mail.smtp.user", MyLogger.mailUserName);
					mailSessionProps.put("mail.smtp.password", MyLogger.mailPassword);
					
					mailSessionProps.put("mail.smtp.auth", "true");
					mailSessionProps.put("mail.smtp.starttls.enable", "true");
					mailSessionProps.put("mail.smtp.port", "587");

					InternetAddress senderAddress;
					try {
						senderAddress = new InternetAddress(MyLogger.mailFrom);
					} catch (Exception e) {
						senderAddress = new InternetAddress(MyLogger.mailUserName);
					}

					buildSession(mailSessionProps);
					Transport transport = MyLogger.session.getTransport("smtp");
					MimeMessage testMsg = buildTestMessage(senderAddress);
					try {
						transport.connect(MyLogger.mailUserName, MyLogger.mailPassword);
						transport.sendMessage(testMsg, testMsg.getAllRecipients());
						System.out.println("Auth TLS connection is valid");
					} catch (Exception e) {
						System.out.println("Auth TLS connection is Invalid : "+ e.toString());
						mailSessionProps.put("mail.smtp.auth", "true");
						mailSessionProps.put("mail.smtp.starttls.enable", "false");
						mailSessionProps.put("mail.smtp.port", "465");
						mailSessionProps.put("mail.smtp.socketFactory.host", MyLogger.mailHost);
						mailSessionProps.put("mail.smtp.socketFactory.port", "465");
						mailSessionProps.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
						buildSession(mailSessionProps);
						transport = MyLogger.session.getTransport("smtp");
						testMsg = buildTestMessage(senderAddress);
						try {
							transport.connect(MyLogger.mailUserName, MyLogger.mailPassword);
							transport.sendMessage(testMsg, testMsg.getAllRecipients());
							System.out.println("Auth SSL connection is valid");
						} catch (Exception e1) {
							System.out.println("Auth SSL connection is Invalid : "+ e1.toString());
							mailSessionProps.put("mail.smtp.auth", "false");
							mailSessionProps.put("mail.smtp.starttls.enable", "false");
							mailSessionProps.put("mail.smtp.port", "25");
							buildSession(mailSessionProps);
							transport = MyLogger.session.getTransport("smtp");
							testMsg = buildTestMessage(senderAddress);
							try {
								transport.connect(MyLogger.mailUserName, MyLogger.mailPassword);
								transport.sendMessage(testMsg, testMsg.getAllRecipients());
								System.out.println("Non Auth SMTP connection is valid");
							} catch (Exception e2) {
								System.out.println("Non Auth SMTP connection is Invalid : "+ e2.toString());
								System.out.println("Could not set up error msg handling.\nThis feature will be disabled until you set up your smtp connection in Settings ..."); 
								if (MyLogger.mailActivationType.equals("true")) {
									MainPMScmd.getMyPrefs().put("mail.log.activated", "failed");
									MainPMScmd.getMyPrefs().flushy();
								}

							}
						}
					}
				}
				
			} else {
				
				System.out.println("SMTP connection params are Invalid : propsMailUserName="+propsMailUserName+", propsMailPassword=xxx, propsMailHost="+propsMailHost);
				if (MyLogger.mailActivationType.equals("true")) {
					MainPMScmd.getMyPrefs().put("mail.log.activated", "failed");
					MainPMScmd.getMyPrefs().flushy();
				};
				
			}

		} catch (Throwable t) {
			
			System.out.println("Log send failed, exception: " + t); 
			System.out.println("Could not set up error msg handling.\nThis feature will be disabled until you set up your smtp connection in Settings ..." + t); 
			if (MyLogger.mailActivationType.equals("true")) {
				MainPMScmd.getMyPrefs().put("mail.log.activated", "failed");
			}
			MainPMScmd.getMyPrefs().flushy();
			t.printStackTrace();
			
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

	protected static void buildSession(Properties mailSessionProps) {
		MyLogger.session = Session.getInstance(mailSessionProps, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(MyLogger.mailUserName, MyLogger.mailPassword);
			}
		  });
		//MyLogger.session.setDebug(true);
	}

	protected static MimeMessage buildTestMessage(InternetAddress senderAddress) throws MessagingException {
		MimeMessage testMsg = new MimeMessage(MyLogger.session);
		testMsg.setSubject("Smtp connection test msg");
		testMsg.setText("Smtp connection test msg");
		testMsg.setFrom(senderAddress);
		testMsg.setSender(senderAddress);
		Address[] rTo = {senderAddress};
		testMsg.setReplyTo(rTo);
		testMsg.setRecipients(Message.RecipientType.TO, mailTo);
		testMsg.saveChanges();
		return testMsg;
	}

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

	public MyLogger(Logger delagateLogger) {
		super();
		this.delegateLogger = delagateLogger;
	}

	public void addAppender(Appender newAppender) {
		delegateLogger.addAppender(newAppender);
	}

	public void assertLog(boolean assertion, String msg) {
		delegateLogger.assertLog(assertion, msg);
	}

	public void callAppenders(LoggingEvent event) {
		delegateLogger.callAppenders(event);
	}

	public void debug(Object message, Throwable t) {
		delegateLogger.debug(message, t);
	}

	public void debug(Object message) {
		delegateLogger.debug(message);
	}

	@Override
	public boolean equals(Object obj) {
		return delegateLogger.equals(obj);
	}

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

	public void error(Object message) {
		delegateLogger.error(message);
		this.sendMail(message, null, false);
	}

	public void fatal(Object message, Throwable t) {
		delegateLogger.fatal(message, t);
		this.sendMail(message, t, false);
	}

	public void fatal(Object message) {
		delegateLogger.fatal(message);
		this.sendMail(message, null, false);
	}

	public boolean getAdditivity() {
		return delegateLogger.getAdditivity();
	}

	public Enumeration<?> getAllAppenders() {
		return delegateLogger.getAllAppenders();
	}

	public Appender getAppender(String name) {
		return delegateLogger.getAppender(name);
	}

	@Deprecated
	public Priority getChainedPriority() {
		return delegateLogger.getChainedPriority();
	}

	public Level getEffectiveLevel() {
		return delegateLogger.getEffectiveLevel();
	}

	@Deprecated
	public LoggerRepository getHierarchy() {
		return delegateLogger.getHierarchy();
	}

	public final Level getLevel() {
		return delegateLogger.getLevel();
	}

	public LoggerRepository getLoggerRepository() {
		return delegateLogger.getLoggerRepository();
	}

	public final String getName() {
		return delegateLogger.getName();
	}

	public final Category getParent() {
		return delegateLogger.getParent();
	}

	@Deprecated
	public final Level getPriority() {
		return delegateLogger.getPriority();
	}

	public ResourceBundle getResourceBundle() {
		return delegateLogger.getResourceBundle();
	}

	@Override
	public int hashCode() {
		return delegateLogger.hashCode();
	}

	public void info(Object message, Throwable t) {
		delegateLogger.info(message, t);
	}

	public void info(Object message) {
		delegateLogger.info(message);
	}

	public void guiInfo(Object message) {
		delegateLogger.info(message);
		lastMsg.setLastMessage(message.toString());
	}

	public boolean isAttached(Appender appender) {
		return delegateLogger.isAttached(appender);
	}

	public boolean isDebugEnabled() {
		return delegateLogger.isDebugEnabled();
	}
	
	public boolean isEnabledFor(Priority level) {
		return delegateLogger.isEnabledFor(level);
	}
	
	public boolean isInfoEnabled() {
		return delegateLogger.isInfoEnabled();
	}

	public boolean isTraceEnabled() {
		return delegateLogger.isTraceEnabled();
	}

	public void l7dlog(Priority priority, String key, Object[] params, Throwable t) {
		delegateLogger.l7dlog(priority, key, params, t);
	}

	public void l7dlog(Priority priority, String key, Throwable t) {
		delegateLogger.l7dlog(priority, key, t);
	}

	public void log(Priority priority, Object message, Throwable t) {
		delegateLogger.log(priority, message, t);
	}

	public void log(Priority priority, Object message) {
		delegateLogger.log(priority, message);
	}

	public void log(String callerFQCN, Priority level, Object message, Throwable t) {
		delegateLogger.log(callerFQCN, level, message, t);
	}

	public void removeAllAppenders() {
		delegateLogger.removeAllAppenders();
	}

	public void removeAppender(Appender appender) {
		delegateLogger.removeAppender(appender);
	}

	public void removeAppender(String name) {
		delegateLogger.removeAppender(name);
	}

	public void setAdditivity(boolean additive) {
		delegateLogger.setAdditivity(additive);
	}

	public void setLevel(Level level) {
		delegateLogger.setLevel(level);
	}

	@Deprecated
	public void setPriority(Priority priority) {
		delegateLogger.setPriority(priority);
	}

	public void setResourceBundle(ResourceBundle bundle) {
		delegateLogger.setResourceBundle(bundle);
	}

	@Override
	public String toString() {
		return delegateLogger.toString();
	}

	public void trace(Object message, Throwable t) {
		delegateLogger.trace(message, t);
	}

	public void trace(Object message) {
		delegateLogger.trace(message);
	}

	public void warn(Object message, Throwable t) {
		delegateLogger.warn(message, t);
	}

	public void warn(Object message) {
		delegateLogger.warn(message);
	}

	//Email settings options :
	//false (no email no popup), 
	//true (send error email only - no test error email, no duplicates -, with popup), 
	//force (send error email and test error email - with duplicates -, no popup),
	//forceNoTest (send only errors, no test and no popup)
	private void sendMail(Object errorMsg, final Throwable error, final Boolean isTest) {
		
		//Is active?
		MyLogger.mailActivationType = MainPMScmd.getMyPrefs().get("mail.log.activated", "true");  
		String errorMailSetup = "Mail Settings : log activation type : " + MyLogger.mailActivationType;
		System.out.println(errorMailSetup);
		delegateLogger.info(errorMailSetup);
		
		//No sending mail
		if ("false".equals(MyLogger.mailActivationType) || SpringContext.getSingleton() == null || !SpringContext.getSingleton().isActive()) return;
		
		//Sending
		final String errorStr = (null == errorMsg)?"No message available":errorMsg.toString();
		Thread sendLogThread = new Thread() {
			@Override
			public void run() {
				
				JFrame frame = null;
				CustomDialog customDialog = null;
				
				try {
					
					MyLogger.mailActivationType = MainPMScmd.getMyPrefs().get("mail.log.activated", "true");  
					if ("false".equals(MyLogger.mailActivationType) || SpringContext.getSingleton() == null || !SpringContext.getSingleton().isActive()) return;
					
					Boolean isSendingErrorEmail = "true".equals(MyLogger.mailActivationType) || "force".equals(MyLogger.mailActivationType) || "forceNoTest".equals(MyLogger.mailActivationType);
					Boolean isSendingTestEmail = "force".equals(MyLogger.mailActivationType);
					Boolean isFailed = "failed".equals(MyLogger.mailActivationType);
					
					Boolean isSendingEmail = ((isTest && isSendingTestEmail) || (!isTest && isSendingErrorEmail)) && !isFailed;
					Boolean isPopup = ("true".equals(MyLogger.mailActivationType) || "failed".equals(MyLogger.mailActivationType)) && !isTest ;
					Boolean hasDuplicate = isTest;
					
					boolean errorMailHandlingsGrants = !isSendingEmail && !isPopup;
					if (errorMailHandlingsGrants) return;
					
					//Msg hash
					Integer bodyHashcode = createMsgBodyFirstLines(error, errorStr, 3).toString().hashCode();
					if (!hasDuplicate) {
						if (hashesSet.contains(bodyHashcode)) {
							return;
						} else {
							hashesSet.add(bodyHashcode);
						}
					}
					
					try {
						
						semaphore.acquire();
						delegateLogger.info("Sending mail on error; grants : "+!errorMailHandlingsGrants+ ". isSendingEmail="+isSendingEmail+", isPopup="+isPopup+", has duplicate "+hasDuplicate);	
						
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
									MainPMScmd.getMyPrefs().put("mail.log.activated", "false");
									MainPMScmd.getMyPrefs().flushy();
								}

							} catch (Throwable e) {
								delegateLogger.error("Can't open error Popup for acknowledgement.");
								e.printStackTrace();
							}
							
						} else if (isSendingEmail) {
							doSend(bodyHashcode, isTest);
						}
						
					} finally {
						semaphore.release();
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

				delegateLogger.info("Sending error mail : senderAddress="+senderAddress.getAddress()+ ", recipient="+MyLogger.mailTo+", transport="+transport.getURLName().toString());
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

//			private void writeHashesToFile(Integer bodyHashcode) throws IOException {
//				BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(hashCodesFile, true));
//				bufferedWriter.write(bodyHashcode.toString());
//				bufferedWriter.newLine();
//				bufferedWriter.flush();
//				bufferedWriter.close();
//			}
		};
		
		sendLogThread.setDaemon(true);
		sendLogThread.start();
	}
}
