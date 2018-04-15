package by.kalinklish.project.logic;

import by.kalinklish.project.dao.AuthorDAO;
import by.kalinklish.project.entity.Author;
import by.kalinklish.project.exception.DAOException;
import by.kalinklish.project.exception.LogicException;
import by.kalinklish.project.dao.factory.DAOFactory;

import java.util.List;

public class AuthorLogic {
    private AuthorDAO authorDAO = DAOFactory.getInstance().getAuthorDAO();

    public List<Author> getAuthors() throws LogicException {
        try {
            return authorDAO.getAllAuthors();
        } catch (DAOException e) {
            throw new LogicException(e);
        }
    }

    public Author getAuthorById(int id) throws LogicException {
        try {
            return authorDAO.getAuthorById(id);
        } catch (DAOException e) {
            throw new LogicException(e);
        }
    }

    public boolean addAuthor(Author author) throws LogicException {
        try {
            return authorDAO.addAuthor(author);
        } catch (DAOException e) {
            throw new LogicException(e);
        }
    }

    public boolean deleteAuthor(int id) throws LogicException {
        try {
            return authorDAO.deleteAuthor(id);
        } catch (DAOException e) {
            throw new LogicException(e);
        }
    }

    public boolean updateAuthor(Author author) throws LogicException {
        try {
            return authorDAO.updateAuthor(author);
        } catch (DAOException e) {
            throw new LogicException(e);
        }
    }
}
