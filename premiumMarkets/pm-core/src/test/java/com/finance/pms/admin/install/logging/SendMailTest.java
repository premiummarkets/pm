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
		mailSessionProps.put("mail.smtp.starttls.enable", "true");
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
			senderAddress = new InternetAddress("guil@localhost.localdomain");
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

}
