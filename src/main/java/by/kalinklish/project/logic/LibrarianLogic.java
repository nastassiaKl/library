package by.kalinklish.project.logic;

import by.kalinklish.project.exception.DAOException;
import by.kalinklish.project.dao.LibrarianDAO;
import by.kalinklish.project.dao.factory.DAOFactory;
import by.kalinklish.project.entity.User;
import by.kalinklish.project.exception.LogicException;

import java.util.List;

public class LibrarianLogic {
    private LibrarianDAO librarianDAO = DAOFactory.getInstance().getLibrarianDAO();

    public List<User> getLibrarians() throws LogicException {
        try {
            return librarianDAO.getAllLibrarians();
        } catch (DAOException e) {
            throw new LogicException(e);
        }
    }

    public User getLibrarianById(int id) throws LogicException {
        try {
            return librarianDAO.getLibrarianById(id);
        } catch (DAOException e) {
            throw new LogicException(e);
        }
    }

    public void addLibrarian(User librarian) throws LogicException {
        try {
            librarianDAO.addLibrarian(librarian);
        } catch (DAOException e) {
            throw new LogicException(e);
        }
    }

    public boolean deleteLibrarian(int id) throws LogicException {
        try {
            return librarianDAO.deleteLibrarian(id);
        } catch (DAOException e) {
            throw new LogicException(e);
        }
    }

    public boolean updateLibrarian(User librarian) throws LogicException {
        try {
            return librarianDAO.updateLibrarian(librarian);
        } catch (DAOException e) {
            throw new LogicException(e);
        }
    }
}
