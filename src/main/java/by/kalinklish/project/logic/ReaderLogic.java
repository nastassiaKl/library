package by.kalinklish.project.logic;

import by.kalinklish.project.entity.User;
import by.kalinklish.project.exception.DAOException;
import by.kalinklish.project.exception.LogicException;
import by.kalinklish.project.dao.ReaderDAO;
import by.kalinklish.project.dao.factory.DAOFactory;

import java.util.List;

public class ReaderLogic {
    private ReaderDAO readerDAO = DAOFactory.getInstance().getReaderDAO();

    public void registrationReader(User user) throws LogicException {
        try {
            readerDAO.addReader(user);
        } catch (DAOException e) {
            throw new LogicException(e);
        }
    }

    public String checkLoginPassword(String login, String password) throws LogicException {
        try {
            return readerDAO.checkLoginPassword(login, password);
        } catch (DAOException e) {
            throw new LogicException(e);
        }
    }

    public User getUserByLoginPassword(String login, String password) throws LogicException {
        try {
            return readerDAO.getUserByLoginPassword(login, password);
        } catch (DAOException e) {
            throw new LogicException(e);
        }
    }

    public List<User> getReaders() throws LogicException {
        try {
            return readerDAO.getAllReaders();
        } catch (DAOException e) {
            throw new LogicException(e);
        }
    }

    public boolean deleteReader(int idUser) throws LogicException {
        try {
            return readerDAO.deleteReader(idUser);
        } catch (DAOException e) {
            throw new LogicException(e);
        }
    }

    public User getReaderByTicket(int numberTicket) throws LogicException {
        try {
            return readerDAO.getUserByTicket(numberTicket);
        } catch (DAOException e) {
            throw new LogicException(e);
        }
    }

    public boolean changePassword(int numberTicket, String password) throws LogicException {
        try {
            return readerDAO.changePassword(numberTicket, password);
        } catch (DAOException e) {
            throw new LogicException(e);
        }
    }

    public String getPassword(int numberTicket) throws LogicException {
        try {
            return readerDAO.getPassword(numberTicket);
        } catch (DAOException e) {
            throw new LogicException(e);
        }
    }

    public void forgotPassword(int numberTicket, String password) throws LogicException {
        try {
            readerDAO.forgotPassword(numberTicket, password);
        } catch (DAOException e) {
            throw new LogicException(e);
        }
    }

    public boolean updateUser(User user) throws LogicException {
        try {
            return readerDAO.updateUser(user);
        } catch (DAOException e) {
            throw new LogicException(e);
        }
    }

    public boolean checkLogin(String login) throws LogicException {
        try {
            return readerDAO.checkLogin(login);
        } catch (DAOException e) {
            throw new LogicException(e);
        }
    }

    public boolean checkMailAndTicket(String mail, int numberTicket) throws LogicException {
        try {
            return readerDAO.checkMailAndTicket(mail, numberTicket);
        } catch (DAOException e) {
            throw new LogicException(e);
        }
    }
}
