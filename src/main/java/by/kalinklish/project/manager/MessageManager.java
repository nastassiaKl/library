package by.kalinklish.project.manager;

import java.util.Locale;
import java.util.ResourceBundle;

public enum MessageManager {
    EN(ResourceBundle.getBundle("locale/locale", new Locale("en", "US"))),
    RU(ResourceBundle.getBundle("locale/locale", new Locale("ru", "RU"))),
    BE(ResourceBundle.getBundle("locale/locale", new Locale("be", "BY")));

    private ResourceBundle bundle;

    MessageManager(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    public String getMessage(String key) {
        return bundle.getString(key);
    }

    public static MessageManager getLocale(String locale){
        MessageManager messageManager = null;
        switch (locale){
            case "en":
                messageManager = EN;
                break;
            case "ru":
                messageManager = RU;
                break;
            case "be":
                messageManager = BE;
                break;
        }
        return messageManager;
    }
}
