package by.kalinklish.project.mail.sender;

import by.kalinklish.project.constant.MailProperty;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;
import java.util.ResourceBundle;

public class MailSessionCreator {
    private final String USER_NAME;
    private final String USER_PASSWORD;
    private Properties properties;

    MailSessionCreator() {
        ResourceBundle resource = ResourceBundle.getBundle(MailProperty.MAIL_PROPERTY_FILE);

        USER_NAME = resource.getString(MailProperty.MAIL_USER_NAME);
        USER_PASSWORD = resource.getString(MailProperty.MAIL_USER_PASSWORD);

        properties = new Properties();
        properties.setProperty(MailProperty.MAIL_PROTOCOL, resource.getString(MailProperty.MAIL_PROTOCOL));
        properties.setProperty(MailProperty.MAIL_HOST, resource.getString(MailProperty.MAIL_HOST));
        properties.put(MailProperty.MAIL_SMTP_AUTH, resource.getString(MailProperty.MAIL_SMTP_AUTH));
        properties.put(MailProperty.MAIL_SMTP_PORT, resource.getString(MailProperty.MAIL_SMTP_PORT));
        properties.put(MailProperty.MAIL_SMTP_SF_PORT, resource.getString(MailProperty.MAIL_SMTP_SF_PORT));
        properties.put(MailProperty.MAIL_SMTP_SF_CLASS, resource.getString(MailProperty.MAIL_SMTP_SF_CLASS));
        properties.put(MailProperty.MAIL_SMTP_SF_FALLBACK, resource.getString(MailProperty.MAIL_SMTP_SF_FALLBACK));
    }

    Session createSession() {
        return Session.getDefaultInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(USER_NAME, USER_PASSWORD);
                    }
                });
    }
}
