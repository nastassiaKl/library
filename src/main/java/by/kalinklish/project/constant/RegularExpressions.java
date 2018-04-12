package by.kalinklish.project.constant;

public class RegularExpressions {
    public final static String NAME_PATTERN = "[A-ZА-Я][a-zа-я]+('[a-zа-я]+|-[A-ZА-Я][a-zа-я]+)?";
    public final static String MIDDLE_NAME_PATTERN = "[A-ZА-Я][a-zа-я]+";
    public final static String INTEGER_PATTERN = "[\\d]+";
    public final static String PHONE_NUMBER_PATTERN = "(\\s*)?(\\+)?([- _():=+]?\\d[- _():=+]?){10,14}(\\s*)?";
    public final static String MAIL_PATTERN = "[\\w-\\.]+@[\\w-]+\\.[a-z]{2,3}";
    public final static String LOGIN_PATTERN = "[A-Za-z][A-Za-z0-9_]+";
    public final static String PASSWORD_PATTERN = "(?=.*[a-z])(?=.*[A-Z]).{4,}";
    public final static String PLACE_PATTERN = "[A-ZА-Я][А-ЯA-Za-zа-я]+('[a-zа-я]+|-[A-ZА-Я][a-zа-я]+)?";
    public final static String TITTLE_PATTERN = "[A-ZА-Я\\d\\W][a-zа-я\\W\\d\\s]+";
    public final static String DATE_PATTERN = "(\\d{4})\\-(0\\d|1[012])\\-([0-2]\\d|3[01])";

}
