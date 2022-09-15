package com.finance.pms.admin.install.logging;

import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.junit.Test;

public class SendMailTest {

	@Test
	public void test() throws MessagingException {

		Properties mailSessionProps = new Properties();
		mailSessionProps.put("mail.transport.protocol", "smtp");
//		mailSessionProps.put("mail.smtp.user", "root");
		mailSessionProps.put("mail.smtp.host", "localhost");
		mailSessionProps.put("mail.smtp.starttls.enable", "false");
		mailSessionProps.put("mail.debug", "true");
		mailSessionProps.put("mail.smtp.timeout", "1000");
		//mailSessionProps.put("mail.smtp.auth", "true");

		Session session = Session.getInstance(mailSessionProps);

		session.setDebug(true);
		System.out.println("Url name : "+session.getTransport().getURLName());

		Transport transport = session.getTransport("smtp");
		//transport.connect("localhost","", "");
		transport.connect();

		MimeMessage msg = new MimeMessage(session);
		InternetAddress senderAddress;
		try {
			senderAddress = new InternetAddress("pm@thebeast.local");
		} catch (Exception e) {
			try {
				e.printStackTrace();
				senderAddress = new InternetAddress("");
			} catch (Exception e1) {
				e.printStackTrace();
				senderAddress = new InternetAddress();
			}
		}

		StringBuffer msgBody = new StringBuffer("Essay");
		String msgSubject = "Essay from user "+senderAddress;

		msgBody.insert(0, "This is an info test message : \n");
		msgSubject = msgSubject.replaceFirst("Error detected", "Warning detected");

		msg.setFrom(senderAddress);
		msg.setSender(senderAddress);
		Address[] rTo = {senderAddress};
		msg.setReplyTo(rTo);
		msg.setRecipients(Message.RecipientType.TO, "guil@localhost.localdomain");
		msg.setSubject(msgSubject);
		msg.setSentDate(new Date());
		msg.setText(msgBody.toString());

		msg.saveChanges();// don't forget this

		System.out.println("Sending error mail : senderAddress=" + senderAddress.getAddress() + ", recipient=" + Arrays.toString(msg.getAllRecipients()) + ", transport=" + transport.getURLName().toString());
		transport.sendMessage(msg, msg.getRecipients(Message.RecipientType.TO));

		transport.close();
	}
	
	//@Test
	public void gmailTest() throws MessagingException {
		
		String mailHost = "smtp.gmail.com";
		String mailUserName = "piggymarketsqueak@googlemail.com";
		String mailPassword = "LmTqr270";
		String mailFrom = "piggymarketsqueak@gmail.com";
		
		
		Properties mailSessionProps = new Properties();
		mailSessionProps.put("mail.transport.protocol", "smtp");
		mailSessionProps.put("mail.smtp.host", mailHost);
		mailSessionProps.put("mail.smtp.timeout", "5000");
		
		//mailSessionProps.put("mail.smtp.localhost", MainPMScmd.getMyPrefs().get("site.url", "none.com"));
		mailSessionProps.put("mail.smtp.user", mailUserName);
		mailSessionProps.put("mail.smtp.password", mailPassword);

		mailSessionProps.put("mail.smtp.auth", "true");
		mailSessionProps.put("mail.smtp.starttls.enable", "true");
		//mailSessionProps.put("mail.smtp.port", "587");
		mailSessionProps.put("mail.smtp.port", "465");
        mailSessionProps.put("mail.smtp.ssl.enable", "true");
        
        InternetAddress senderAddress;
        try {
			System.out.println("Testing Auth TLS connection with " + mailFrom);
			senderAddress = new InternetAddress(mailFrom);
		} catch (Exception e) {
			System.out.println("Testing Auth TLS connection with adding " + mailUserName);
			senderAddress = new InternetAddress(mailUserName);
		}
		
		System.out.println("Testing Auth TLS session params: " + mailSessionProps);
		Session session = MyLogger.buildAuthSession(mailSessionProps);
		Transport transport = session.getTransport("smtp");
		MimeMessage testMsg = MyLogger.buildTestMessage(senderAddress);
		try {
			transport.connect(mailUserName, mailPassword);
			transport.sendMessage(testMsg, testMsg.getAllRecipients());
			System.out.println("Auth TLS connection is valid");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
