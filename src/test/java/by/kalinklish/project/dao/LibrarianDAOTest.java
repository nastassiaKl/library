package by.kalinklish.project.dao;

import by.kalinklish.project.dao.factory.DAOFactory;
import by.kalinklish.project.entity.User;
import by.kalinklish.project.exception.DAOException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class LibrarianDAOTest {
    private static LibrarianDAO librarianDAO;
    private static User librarian;
    private static User editLibrarian;

    @BeforeClass
    public static void init() {
        librarianDAO = DAOFactory.getInstance().getLibrarianDAO();
        librarian = new User(6, "Книгова", "Анна", "Николаевна", "librar", "Llibrar19");
        editLibrarian = new User(6, "Книгова", "Аня", "Николаевна", "librar", "Llibrar19");
    }

    @Test
    public void testGetAllLibrarians() throws DAOException {
        List<User> librarians = librarianDAO.getAllLibrarians();
        Assert.assertFalse(librarians.isEmpty());
    }

    @Test
    public void testAddLibrarian() throws DAOException {
        librarianDAO.addLibrarian(librarian);
    }

    @Test
    public void testDeleteLibrarian() throws DAOException {
        Assert.assertTrue(librarianDAO.deleteLibrarian(librarian.getId()));
    }

    @Test
    public void testUpdateLibrarian() throws DAOException {
        Assert.assertTrue(librarianDAO.updateLibrarian(editLibrarian));
    }

    @Test
    public void testCheckLoginPassword() throws DAOException {
        Assert.assertTrue(librarianDAO.checkLoginPassword(librarian.getLogin(), librarian.getPassword()));
    }

}
