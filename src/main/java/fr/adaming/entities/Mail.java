package fr.adaming.entities;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

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
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
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
			
			// Multipart
			Multipart multipart = new MimeMultipart();

			// Corps du message
			BodyPart partieMessage = new MimeBodyPart();

			// Ajouter du texte au message
			partieMessage.setText(msg);
			multipart.addBodyPart(partieMessage);
			
			// Pi�ces jointes
			partieMessage = new MimeBodyPart();
			DataSource source = new FileDataSource("C:\\Users\\IN-BR-007\\FicheProduit.pdf");
			partieMessage.setDataHandler(new DataHandler(source));
			partieMessage.setFileName("Fiche Produit \n \n Test envoie PDF en PJ de mail. Guillaume-Arthur");
			multipart.addBodyPart(partieMessage);
			message.setContent(multipart);
			
			// send message
			Transport.send(message);
			// D�commenter pour v�rifier que le message est envoy�
			System.out.println("Message envoy�");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}
}
