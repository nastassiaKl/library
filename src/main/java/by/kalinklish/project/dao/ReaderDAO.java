package by.kalinklish.project.dao;

import by.kalinklish.project.entity.User;
import by.kalinklish.project.exception.DAOException;
import by.radomskaya.project.entity.User;
import by.radomskaya.project.exception.DAOException;

import java.util.List;

public interface ReaderDAO {
    List<User> getAllReaders() throws DAOException;
    User getUserByTicket(int numberTicket) throws DAOException;
    User getUserByLoginPassword(String login, String password) throws DAOException;
    int getIdUser(String login, String password) throws DAOException;
    int getNumberTicket(String login, String password) throws DAOException;
    String getPassword(int numberTicket) throws DAOException;
    boolean checkLogin(String login) throws DAOException;
    boolean checkMailAndTicket(String mail, int numberTicket) throws DAOException;
    String checkLoginPassword(String login, String password) throws DAOException;
    void addReader(User user) throws DAOException;
    boolean deleteReader(int idUser) throws DAOException;
    boolean updateUser(User user) throws DAOException;
    boolean changePassword(int numberTicket, String password) throws DAOException;
    void forgotPassword(int numberTicket, String password) throws DAOException;
}
