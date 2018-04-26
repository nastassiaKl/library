package by.kalinklish.project.mail.sender;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {
    private final static Logger LOGGER = LogManager.getLogger(MailSender.class);

    public MailSender() {
    }

    public static void sendMail(String subject, String text, String toEmail) {
        Session session = (new MailSessionCreator()).createSession();
        session.setDebug(true);

        try {
            Message message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(text);
            Transport.send(message);
        } catch (MessagingException e) {
            LOGGER.log(Level.ERROR, "Cannot send an email: " + e, e);
        }
    }

}
