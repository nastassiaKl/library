package by.kalinklish.project.validation;

import by.kalinklish.project.constant.RegularExpressions;

import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputParamValidator {

    public static boolean isCheckStringData(String data, String expression) {
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(data);
        return matcher.matches();
    }

    public static boolean isValidateUserData(String surname, String name, String middleName, int age, String phone, String mail, String login, String password) {
        return isCheckStringData(surname, RegularExpressions.NAME_PATTERN) && surname != null
                && isCheckStringData(name, RegularExpressions.NAME_PATTERN) && name != null
                && isCheckStringData(middleName, RegularExpressions.MIDDLE_NAME_PATTERN) && middleName != null
                && isCheckStringData(phone, RegularExpressions.PHONE_NUMBER_PATTERN) && phone != null
                && isCheckStringData(mail, RegularExpressions.MAIL_PATTERN) && mail != null
                && isCheckStringData(login, RegularExpressions.LOGIN_PATTERN) && login != null && login.length() >= 5
                && isCheckStringData(password, RegularExpressions.PASSWORD_PATTERN) && password.length() >= 6
                && age != 0;
    }

    public static boolean isValidateAccountData(String surname, String name, String middleName, int age, String phone, String mail, String login) {
        return isCheckStringData(surname, RegularExpressions.NAME_PATTERN) && surname != null
                && isCheckStringData(name, RegularExpressions.NAME_PATTERN) && name != null
                && isCheckStringData(middleName, RegularExpressions.MIDDLE_NAME_PATTERN) && middleName != null
                && isCheckStringData(phone, RegularExpressions.PHONE_NUMBER_PATTERN) && phone != null
                && isCheckStringData(mail, RegularExpressions.MAIL_PATTERN) && mail != null
                && isCheckStringData(login, RegularExpressions.LOGIN_PATTERN) && login != null && login.length() >= 5
                && age != 0;
    }

    public static boolean isValidateBookData(String isbn, String tittle, Date dateEdition, String placeEdition, String publisher, int numberCopies) {
        return isCheckStringData(isbn, RegularExpressions.INTEGER_PATTERN) && isbn != null
                && isCheckStringData(tittle, RegularExpressions.TITTLE_PATTERN) && tittle != null
                && isCheckStringData(String.valueOf(dateEdition), RegularExpressions.DATE_PATTERN) && String.valueOf(dateEdition) != null
                && isCheckStringData(placeEdition, RegularExpressions.PLACE_PATTERN) && placeEdition!= null
                && isCheckStringData(publisher, RegularExpressions.PLACE_PATTERN) && publisher!= null
                && numberCopies != 0;
    }

    public static boolean isValidateAuthorData(String surname, String name, String country) {
        return isCheckStringData(surname, RegularExpressions.NAME_PATTERN) && surname != null
                && isCheckStringData(name, RegularExpressions.NAME_PATTERN) && name != null
                && isCheckStringData(country, RegularExpressions.PLACE_PATTERN) && country != null;
    }

    public static boolean isValidateMiddleName(String middleName) {
        return isCheckStringData(middleName, RegularExpressions.MIDDLE_NAME_PATTERN);
    }

    public static boolean isValidateLibrarianData(String surname, String name, String middleName, String login) {
        return isCheckStringData(surname, RegularExpressions.NAME_PATTERN) && surname != null
                && isCheckStringData(name, RegularExpressions.NAME_PATTERN) && name != null
                && isCheckStringData(middleName, RegularExpressions.MIDDLE_NAME_PATTERN) && middleName != null
                && isCheckStringData(login, RegularExpressions.LOGIN_PATTERN) && login != null && login.length() >= 5;
    }

    public static boolean isValidateOrderData(Date dateBorrow, Date dateReturn, String methodBorrow) {
        return isCheckStringData(String.valueOf(dateBorrow), RegularExpressions.DATE_PATTERN) && String.valueOf(dateBorrow) != null
                && isCheckStringData(String.valueOf(dateReturn), RegularExpressions.DATE_PATTERN) && String.valueOf(dateReturn) != null
                && methodBorrow != null;
    }

}
