package by.kalinklish.project.validation;

import by.kalinklish.project.constant.RegularExpressions;
import org.junit.Assert;
import org.junit.Test;

public class InputParamValidatorTest {

    @Test
    public void testParamSurname() {
        String correctSurname = "Иванов";
        String incorrectSurname = "иванов";
        Assert.assertTrue(InputParamValidator.isCheckStringData(correctSurname, RegularExpressions.NAME_PATTERN));
        Assert.assertFalse(InputParamValidator.isCheckStringData(incorrectSurname, RegularExpressions.NAME_PATTERN));
    }

    @Test
    public void testParamName() {
        String correctName = "Екатерина-Виктория";
        String incorrectName = "екатерина";
        Assert.assertTrue(InputParamValidator.isCheckStringData(correctName, RegularExpressions.NAME_PATTERN));
        Assert.assertFalse(InputParamValidator.isCheckStringData(incorrectName, RegularExpressions.NAME_PATTERN));
    }

    @Test
    public void testParamMiddleName() {
        String correctMiddleName = "Иванова";
        String incorrectMiddleName = "иванова";
        Assert.assertTrue(InputParamValidator.isCheckStringData(correctMiddleName, RegularExpressions.MIDDLE_NAME_PATTERN));
        Assert.assertFalse(InputParamValidator.isCheckStringData(incorrectMiddleName, RegularExpressions.MIDDLE_NAME_PATTERN));
    }

    @Test
    public void testParamPhoneNumber() {
        String correctPhoneNumber = "+375-44-123-45-96";
        String incorrectPhoneNumber = "37511-441-1235-45-96";
        Assert.assertTrue(InputParamValidator.isCheckStringData(correctPhoneNumber, RegularExpressions.PHONE_NUMBER_PATTERN));
        Assert.assertFalse(InputParamValidator.isCheckStringData(incorrectPhoneNumber, RegularExpressions.PHONE_NUMBER_PATTERN));
    }

    @Test
    public void testParamPlace() {
        String correctPlace = "Республика Беларусь";
        String incorrectPlace = "россия";
        Assert.assertTrue(InputParamValidator.isCheckStringData(correctPlace, RegularExpressions.PLACE_PATTERN));
        Assert.assertFalse(InputParamValidator.isCheckStringData(incorrectPlace, RegularExpressions.PLACE_PATTERN));
    }

    @Test
    public void testParamMail() {
        String correctMail = "diana@mail.ru";
        String incorrectMail = "pavel.com";
        Assert.assertTrue(InputParamValidator.isCheckStringData(correctMail, RegularExpressions.MAIL_PATTERN));
        Assert.assertFalse(InputParamValidator.isCheckStringData(incorrectMail, RegularExpressions.MAIL_PATTERN));
    }

    @Test
    public void testParamLogin() {
        String correctLogin = "masha5";
        Assert.assertTrue(InputParamValidator.isCheckStringData(correctLogin, RegularExpressions.LOGIN_PATTERN));
    }

    @Test
    public void testParamPassword() {
        String correctPassword = "19mMasha";
        Assert.assertTrue(InputParamValidator.isCheckStringData(correctPassword, RegularExpressions.PASSWORD_PATTERN));
    }

    @Test
    public void testParamTittleBook() {
        String correctTittle = "Белоснежка и 7 гномов";
        String incorrectTittle = "1852Год";
        Assert.assertTrue(InputParamValidator.isCheckStringData(correctTittle, RegularExpressions.TITTLE_PATTERN));
        Assert.assertFalse(InputParamValidator.isCheckStringData(incorrectTittle, RegularExpressions.TITTLE_PATTERN));
    }

    @Test
    public void testParamDate() {
        String correctDate = "2018-02-11";
        String incorrectDate = "35-15-198";
        Assert.assertTrue(InputParamValidator.isCheckStringData(correctDate, RegularExpressions.DATE_PATTERN));
        Assert.assertFalse(InputParamValidator.isCheckStringData(incorrectDate, RegularExpressions.DATE_PATTERN));
    }

}
