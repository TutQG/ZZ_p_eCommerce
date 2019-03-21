package fr.adaming.entities;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

class Mail {

	public static void send(String from, String password, String to, String sub, String msg) {
		// Ici on configure les param�tres de ta boite mail
		// Ils sont r�gl�s pour gmail, donc utilise gmail si tu peux, en cr�ant
		// un compte de merde.
		// De plus si tu utilises un compte gmail, il faut cocher ceci sur gmail
		// : "https://myaccount.google.com/lesssecureapps"
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		// get Session
		javax.mail.Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, password);
			}
		});
		// Ici, on cr�e ce qu'il y aura dans le message, pas besoin de modifier
		try {
			MimeMessage message = new MimeMessage(session);
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(sub);
			message.setText(msg);
			// send message
			Transport.send(message);
			// D�commenter pour v�rifier que le message est envoy�
			System.out.println("message sent successfully");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}
}
