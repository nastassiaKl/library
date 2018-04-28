package by.kalinklish.project.dao;

import by.kalinklish.project.dao.factory.DAOFactory;
import by.kalinklish.project.entity.Author;
import by.kalinklish.project.exception.DAOException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class AuthorDAOTest {
    private static AuthorDAO authorDAO;

    @BeforeClass
    public static void init() {
        authorDAO = DAOFactory.getInstance().getAuthorDAO();
    }

    @Test
    public void testGetAllAuthors() throws DAOException {
        List<Author> authors = authorDAO.getAllAuthors();
        Assert.assertFalse(authors.isEmpty());
    }
}
