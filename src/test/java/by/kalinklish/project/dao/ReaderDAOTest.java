package by.kalinklish.project.dao;

import by.kalinklish.project.exception.DAOException;
import by.kalinklish.project.dao.factory.DAOFactory;
import by.kalinklish.project.entity.User;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class ReaderDAOTest {
    private static ReaderDAO readerDAO;
    private static User reader;
    private static User editReader;

    @BeforeClass
    public static void init() {
        readerDAO = DAOFactory.getInstance().getReaderDAO();
        reader = new User(4, 1717, "Иванов", "Иван", "Иванович", 20, "+375296356985", "ivan@mail.ru", "ivan20", "Iivan20", "");
        editReader = new User(4, 1717, "Иванов", "Иван", "Иванович", 22, "+375296356985", "ivan@mail.ru", "ivan22", "Iivan20", "");
    }

    @Test
    public void testGetAllReaders() throws DAOException {
        List<User> readers = readerDAO.getAllReaders();
        Assert.assertFalse(readers.isEmpty());
    }

    @Test
    public void testGetUserByTicket() throws DAOException {
        User findReader = readerDAO.getUserByTicket(reader.getNumberTicket());
        Assert.assertNotNull(findReader.getSurname());
}

    @Test
    public void testGetUserByLoginPassword() throws DAOException {
        User findReader = readerDAO.getUserByLoginPassword(reader.getLogin(), reader.getPassword());
        Assert.assertNotNull(findReader.getSurname());
    }

    @Test
    public void testGetIdUser() throws DAOException {
        int id = readerDAO.getIdUser(reader.getLogin(), reader.getPassword());
        Assert.assertTrue(reader.getId() == id);
    }

    @Test
    public void testGetNumberTicket() throws DAOException {
        int numberTicket = readerDAO.getNumberTicket(reader.getLogin(), reader.getPassword());
        Assert.assertTrue(reader.getNumberTicket() == numberTicket);
    }

    @Test
    public void testGetPassword() throws DAOException {
        String expectedPassword = reader.getPassword();
        String actualPassword = readerDAO.getPassword(reader.getNumberTicket());
        Assert.assertEquals(expectedPassword, actualPassword);
    }

    @Test
    public void testCheckLogin() throws DAOException {
        String login = reader.getLogin();
        Assert.assertTrue(readerDAO.checkLogin(login));
    }

    @Test
    public void testCheckLoginAndTicket() throws DAOException {
        String mail = reader.getMail();
        int numberTicket = reader.getNumberTicket();
        Assert.assertTrue(readerDAO.checkMailAndTicket(mail, numberTicket));
    }

    @Test
    public void testCheckRoleType() throws DAOException {
        String expectedRole = "Читатель";
        String actualRole = readerDAO.checkLoginPassword(reader.getLogin(), reader.getPassword());
        Assert.assertEquals(expectedRole, actualRole);
    }

    @Test
    public void testAddReader() throws DAOException {
        readerDAO.addReader(reader);
    }

    @Test
    public void testDeleteReader() throws DAOException {
        Assert.assertTrue(readerDAO.deleteReader(reader.getId()));
    }

    @Test
    public void testUpdateReader() throws DAOException {
        Assert.assertTrue(readerDAO.updateUser(editReader));
    }

    @Test
    public void testChangePassword() throws DAOException {
        int numberTicket = reader.getNumberTicket();
        String newPassword = "20Iivan";
        Assert.assertTrue(readerDAO.changePassword(numberTicket, newPassword));
    }
}
