package by.kalinklish.project.dao.impl;

import by.kalinklish.project.exception.DAOException;
import by.kalinklish.project.dao.ReaderDAO;
import by.kalinklish.project.entity.User;
import by.kalinklish.project.dao.pool.ConnectionPool;
import by.kalinklish.project.dao.pool.ProxyConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ReaderDAOImpl implements ReaderDAO {
    private final static Logger LOGGER = LogManager.getLogger(ReaderDAOImpl.class);
    private final static String SELECT_READERS = "SELECT id_user, number_ticket, surname, name, middle_name, age, phone_number, mail, login, password, image FROM library.users JOIN library.roles ON roles.id_role = users.id_role WHERE roles.name_role = 'Читатель'";
    private final static String SELECT_ID_USER = "SELECT id_user FROM library.users WHERE login = ? AND password = ?;";
    private final static String SELECT_USER_BY_LOGIN_PASSWORD = "SELECT id_user, number_ticket, surname, name, middle_name, age, phone_number, mail, login, password, image FROM library.users WHERE login = ? AND password = ?";
    private final static String SELECT_NUMBER_TICKET = "SELECT number_ticket FROM library.users WHERE login = ? AND password = ?;";
    private final static String GET_READER_BY_TICKET = "SELECT id_user, number_ticket, surname, name, middle_name, age, phone_number, mail, login, password, image FROM library.users WHERE number_ticket = ?";
    private final static String GET_PASSWORD_BY_TICKET = "SELECT password FROM library.users WHERE number_ticket = ?";
    private final static String CHECK_LOGIN_PASSWORD = "SELECT name_role FROM library.users JOIN library.roles ON roles.id_role = users.id_role WHERE login = ? AND password = ?";
    private final static String CHECK_LOGIN = "SELECT login FROM library.users WHERE login = ?";
    private final static String CHECK_MAIL_AND_TICKET = "SELECT mail, number_ticket FROM library.users WHERE mail = ? AND number_ticket = ?";
    private final static String INSERT_READER = "INSERT INTO users(number_ticket, surname, name, middle_name, age, phone_number, mail, image, login, password, id_role) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
    private final static String DELETE_READER = "DELETE FROM library.users WHERE id_user = ?";
    private final static String UPDATE_USER = "UPDATE library.users SET surname = ?, name = ?, middle_name = ?, age = ?, phone_number = ?, mail = ?, image = ?, login = ? WHERE number_ticket = ?";
    private final static String CHANGE_PASSWORD = "UPDATE library.users SET password = ? WHERE number_ticket = ?;";
    private final static String FORGOT_AND_CHANGE_PASSWORD = "UPDATE library.users SET password = ? WHERE number_ticket = ?";
    private final static int ID_ROLE_READER = 3;


    @Override
    public List<User> getAllReaders() throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        Statement statement = null;
        ResultSet resultSet;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_READERS);
            User user;
            List<User> listUsers = new ArrayList<>();
            while (resultSet.next()) {
                user = gerUserFromResultSet(resultSet);
                listUsers.add(user);
            }
            return listUsers;
        } catch (SQLException e) {
            throw new DAOException("Error get all readers" + e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    LOGGER.error("Error closing statement", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error("Error closing connection", e);
                }
            }
        }
    }

    @Override
    public User getUserByLoginPassword(String login, String password) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet;
        User user = new User();
        try {
            statement = connection.prepareStatement(SELECT_USER_BY_LOGIN_PASSWORD);
            statement.setString(1, login);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = gerUserFromResultSet(resultSet);
            }
            return user;
        } catch (SQLException e) {
            throw new DAOException("Error get user by login amd password" + e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    LOGGER.error("Error closing statement", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error("Error closing connection", e);
                }
            }
        }
    }

    @Override
    public int getIdUser(String login, String password) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet;
        int idUser = 0;
        try {
            statement = connection.prepareStatement(SELECT_ID_USER);
            statement.setString(1, login);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                idUser = resultSet.getInt("id_user");
            }
        } catch (SQLException e) {
            throw new DAOException("Error get user's id" + e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    LOGGER.error("Error closing statement", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error("Error closing connection", e);
                }
            }
        }
        return idUser;
    }

    @Override
    public int getNumberTicket(String login, String password) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet;
        int numberTicket = 0;
        try {
            statement = connection.prepareStatement(SELECT_NUMBER_TICKET);
            statement.setString(1, login);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                numberTicket = resultSet.getInt("number_ticket");
            }
        } catch (SQLException e) {
            throw new DAOException("Error get user's number ticket" + e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    LOGGER.error("Error closing statement", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error("Error closing connection", e);
                }
            }
        }
        return numberTicket;
    }

    @Override
    public void addReader(User user) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(INSERT_READER);
            statement.setInt(1, user.getNumberTicket());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getName());
            statement.setString(4, user.getMiddleName());
            statement.setInt(5, user.getAge());
            statement.setString(6, user.getPhoneNumber());
            statement.setString(7, user.getMail());
            statement.setString(8, user.getProfilePhoto());
            statement.setString(9, user.getLogin());
            statement.setString(10, user.getPassword());
            statement.setInt(11, ID_ROLE_READER);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error add user" + e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    LOGGER.error("Error closing statement", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error("Error closing connection", e);
                }
            }
        }
    }

    @Override
    public boolean deleteReader(int idUser) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETE_READER);
            statement.setInt(1, idUser);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DAOException("Error delete a reader" + e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    LOGGER.error("Error closing statement", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error("Error closing connection", e);
                }
            }
        }
    }

    @Override
    public String checkLoginPassword(String login, String password) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet;
        String roleType = null;
        try {
            statement = connection.prepareStatement(CHECK_LOGIN_PASSWORD);
            statement.setString(1, login);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                roleType = resultSet.getString("name_role");
            }
            return roleType;
        } catch (SQLException e) {
            throw new DAOException("Error check reader by login and password" + e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    LOGGER.error("Error closing statement", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error("Error closing connection", e);
                }
            }
        }
    }

    @Override
    public User getUserByTicket(int numberTicket) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet;
        User user = new User();
        try {
            statement = connection.prepareStatement(GET_READER_BY_TICKET);
            statement.setInt(1, numberTicket);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = gerUserFromResultSet(resultSet);
            }
            return user;
        } catch (SQLException e) {
            throw new DAOException("Error get an user by number ticket" + e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    LOGGER.error("Error closing statement", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error("Error closing connection", e);
                }
            }
        }
    }

    @Override
    public boolean changePassword(int numberTicket, String password) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(CHANGE_PASSWORD);
            statement.setString(1, password);
            statement.setInt(2, numberTicket);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DAOException("Error get an user by number ticket" + e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    LOGGER.error("Error closing statement", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error("Error closing connection", e);
                }
            }
        }
    }

    @Override
    public String getPassword(int numberTicket) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet;
        String password = null;
        try {
            statement = connection.prepareStatement(GET_PASSWORD_BY_TICKET);
            statement.setInt(1, numberTicket);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                password = resultSet.getString("password");
            }
            return password;
        } catch (SQLException e) {
            throw new DAOException("Error get an user by number ticket" + e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    LOGGER.error("Error closing statement", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error("Error closing connection", e);
                }
            }
        }
    }

    @Override
    public void forgotPassword(int numberTicket, String password) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(FORGOT_AND_CHANGE_PASSWORD);
            statement.setString(1, password);
            statement.setInt(2, numberTicket);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error get an user by number ticket" + e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    LOGGER.error("Error closing statement", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error("Error closing connection", e);
                }
            }
        }
    }

    @Override
    public boolean updateUser(User user) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(UPDATE_USER);
            statement.setString(1, user.getSurname());
            statement.setString(2, user.getName());
            statement.setString(3, user.getMiddleName());
            statement.setInt(4, user.getAge());
            statement.setString(5, user.getPhoneNumber());
            statement.setString(6, user.getMail());
            statement.setString(7, user.getProfilePhoto());
            statement.setString(8, user.getLogin());
            statement.setInt(9, user.getNumberTicket());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DAOException("Error update an user" + e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    LOGGER.error("Error closing statement", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error("Error closing connection", e);
                }
            }
        }
    }

    @Override
    public boolean checkLogin(String login) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet;
        try {
            statement = connection.prepareStatement(CHECK_LOGIN);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new DAOException("Error check reader by login" + e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    LOGGER.error("Error closing statement", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error("Error closing connection", e);
                }
            }
        }
    }

    @Override
    public boolean checkMailAndTicket(String mail, int numberTicket) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet;
        try {
            statement = connection.prepareStatement(CHECK_MAIL_AND_TICKET);
            statement.setString(1, mail);
            statement.setInt(2, numberTicket);
            resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new DAOException("Error check reader by mail and number ticket" + e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    LOGGER.error("Error closing statement", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error("Error closing connection", e);
                }
            }
        }
    }

    private User gerUserFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id_user"));
        user.setNumberTicket(resultSet.getInt("number_ticket"));
        user.setSurname(resultSet.getString("surname"));
        user.setName(resultSet.getString("name"));
        user.setMiddleName(resultSet.getString("middle_name"));
        user.setAge(resultSet.getInt("age"));
        user.setPhoneNumber(resultSet.getString("phone_number"));
        user.setMail(resultSet.getString("mail"));
        user.setLogin(resultSet.getString("login"));
        user.setProfilePhoto(resultSet.getString("image"));
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
        return user;
    }

}
