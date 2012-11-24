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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.TreeSet;
import java.util.concurrent.Semaphore;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
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

	/** The name. */
	private static String mailRecipientName = Messages.getEncriptedString("mail.recipientname"); 
	private static String mailRecipientDomain = Messages.getEncriptedString("mail.recipientdomain"); 
	private static String mailSmtpUser = MyLogger.mailRecipientName + "@" + MyLogger.mailRecipientDomain;
	private static String mailSmtpPass = Messages.getEncriptedString("mail.smtppass"); 
	private static String mailSmtpHost = Messages.getEncriptedString("mail.smtphost"); 
	
	private static TreeSet<Integer> hashesSet = new TreeSet<Integer>();
	private static File hashCodesFile = new File("hashes.txt");
	
	/** The session. */
	private static Session session;
	
	private static Address senderAddress;
	private static String mailActivationType;
	
	/** The s. */
	private static Semaphore semaphore;
	
	/** The last message. */
	public static String lastMessage = ""; 
	
	public static String version = "None";
	
//	<property name="host" value="${mail.host}" />
//	<property name="username" value="${mail.username}" />
//	<property name="password" value="${mail.password}" />
//	<property name="javaMailProperties">
//		<props>
//			<prop key="mail.smtp.auth">true</prop>
//			<prop key="mail.smtp.starttls.enable">false</prop>
//		</props>
//	</property>	
	static {
		MyLogger.semaphore = new Semaphore(1);
		
		//MyLogger.senderUsername = MainPMScmd.getPrefs().get("mail.username", MyLogger.mailRecipientName + "@" + MyLogger.mailRecipientDomain);  //$NON-NLS-2$
		//MyLogger.senderPassword = MainPMScmd.getPrefs().get("mail.password", MyLogger.mailSmtpPass); 
		//MyLogger.mailSmtpHost = MainPMScmd.getPrefs().get("mail.host", MyLogger.mailSmtpHost); 
		
		String senderUserName = MainPMScmd.getPrefs().get("mail.username", null);
		String senderPassword = MainPMScmd.getPrefs().get("mail.password",null); 
		String senderHost = MainPMScmd.getPrefs().get("mail.host", null);
		
		boolean credentialsAreValid = senderUserName != null && !senderUserName.isEmpty() && senderPassword != null && !senderPassword.isEmpty();
		boolean allFieldsAreValid = credentialsAreValid && senderHost != null && !senderHost.isEmpty();
		if ( allFieldsAreValid ) {
			
			MyLogger.mailSmtpHost = senderHost;
			MyLogger.mailSmtpUser = senderUserName;
			MyLogger.mailSmtpPass = senderPassword;
		}
				
		Properties props = new Properties();
		//props.put("mail.transport.protocol", "smtps");
		//props.put("mail.smtps.host", MyLogger.mailSmtpHost);
		//props.put("mail.smtps.starttls.enable", "true");
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.host", MyLogger.mailSmtpHost);
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");

		MyLogger.session = Session.getInstance(props);
		
		//init hashesSet
		try {
			hashCodesFile.createNewFile();
			BufferedReader fileReader = new BufferedReader(new FileReader(hashCodesFile));
			String hline;
			while ((hline = fileReader.readLine()) != null) {
				hashesSet.add(new Integer(hline));
			}
			fileReader.close();
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//get version
		try {
			Properties pbuild = new Properties();
			pbuild.load(MyLogger.class.getClassLoader().getResourceAsStream("pmsbuild.properties"));
			version = pbuild.getProperty("application.buildtime");
		} catch (IOException e1) {
			System.out.println("log send failed, exception: " + e1); 
			e1.printStackTrace();
		}
		
	}
	
	/** The delegate logger. */
	Logger delegateLogger;

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
		this.sendMail(message, t);
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
		this.sendMail(message, null);
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
		this.sendMail(message, t);
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
		this.sendMail(message, null);
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
		lastMessage = message.toString();
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
	private void sendMail(Object errorMsg, final Throwable error) {
		
		final String errorStr = (null == errorMsg)?"No message available":errorMsg.toString();
		
		//is active?
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
					
					//Create msg
					StringBuffer msgBoddy = createMsgBodyFirstLines(error, errorStr, 200);
					Integer bodyHashcode = createMsgBodyFirstLines(error, errorStr, 3).toString().hashCode();

					if (hashesSet.contains(bodyHashcode)) {
						return;
					}
					
					//Email
					//Transport transport = MyLogger.session.getTransport("smtps"); 
					//transport.connect(MyLogger.senderUsername, MyLogger.senderPassword);
					Transport transport = MyLogger.session.getTransport("smtp"); 
					transport.connect(MyLogger.mailSmtpUser, MyLogger.mailSmtpPass);
					
					MimeMessage msg = new MimeMessage(MyLogger.session);		
					fromAdressResolution();

					msg.setFrom(MyLogger.senderAddress);
					msg.setSender(MyLogger.senderAddress);
					Address[] rTo = {MyLogger.senderAddress};
					msg.setReplyTo(rTo);
					msg.setRecipients(Message.RecipientType.TO, MyLogger.mailRecipientName + "@" + MyLogger.mailRecipientDomain); 
					msg.setSubject("Error detected on Version build : "+version+ " from user "+MyLogger.senderAddress); 
					msg.setSentDate(new Date());					
					msg.setText(msgBoddy.toString());

					msg.saveChanges();      // don't forget this
					
					if (!"force".equals(MyLogger.mailActivationType)) {
						
						///Dialog
						try {

							frame = new JFrame();
							String report = "An error has occured.\nThis error may be recoverable.\n"
									+ "By cliking OK on the button below this error will automatically be sent to the development team.\n";
							customDialog = new CustomDialog(frame, report, createMsgBodyFirstLines(error, errorStr, 20).toString(), "Error Report", false);
							customDialog.pack();
							customDialog.setVisible(true);
							customDialog.dispose();
							frame.dispose();

							if ("Ok".equals(customDialog.getOptionPane().getValue())) {
								doSend(bodyHashcode, transport, msg);
							} else {
								MainPMScmd.getPrefs().put("mail.log.activated", "false");
								MainPMScmd.getPrefs().flush();
							}

						} catch (Throwable e) {
							delegateLogger.error("Can't open error popup for acknowledment.");
							e.printStackTrace();
						}
						
					} else {
						doSend(bodyHashcode, transport, msg);
					}
					
					transport.close();
					
				} catch (Throwable mex) {
					delegateLogger.error("Failed to process error \""+errorStr+"\", cause \""+mex+"\".\n" +
							"Errors can be forwarded to development team by email.\n" +
							"To enable this feature, you must setup your email parameters in the Settings menu and restart.\n" +
							"Thanks.\n\n");
					
				} finally {
					semaphore.release();
					customDialog = null;
					frame = null;
				}
				
			}

			private void doSend(Integer bodyHashcode, Transport transport, MimeMessage msg) throws IOException, MessagingException {
				if (!hashesSet.contains(bodyHashcode)) {
					hashesSet.add(bodyHashcode);
					writeHashesToFile(bodyHashcode);
					transport.sendMessage(msg, msg.getRecipients(Message.RecipientType.TO));
				}
			}

			/**
			 * 
			 */
			private void fromAdressResolution() {
				try {
					String from = MainPMScmd.getPrefs().get("mail.from", MyLogger.mailSmtpUser);
					MyLogger.senderAddress = new InternetAddress(from);
				} catch (AddressException e) {
					delegateLogger.error("Error from adress :"+e); 
					e.printStackTrace();
					MyLogger.senderAddress = new InternetAddress();
				}
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
