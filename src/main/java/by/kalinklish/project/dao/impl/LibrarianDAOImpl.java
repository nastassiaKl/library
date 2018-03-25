package by.kalinklish.project.dao.impl;

import by.kalinklish.project.exception.DAOException;
import by.kalinklish.project.dao.LibrarianDAO;
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

public class LibrarianDAOImpl implements LibrarianDAO {
    private final static Logger LOGGER = LogManager.getLogger(LibrarianDAOImpl.class);
    private final static String INSERT_LIBRARIAN = "INSERT INTO users(surname, name, middle_name, login, password, id_role) VALUES(?,?,?,?,?,?);";
    private final static String SELECT_LIBRARIANS = "SELECT id_user, surname, name, middle_name, login FROM library.users " +
            "JOIN library.roles ON users.id_role = roles.id_role " +
            "WHERE roles.name_role = 'Библиотекарь';";
    private final static String SELECT_LIBRARIAN_BY_ID = "SELECT id_user, surname, name, middle_name, login FROM library.users " +
            "JOIN library.roles ON users.id_role = roles.id_role " +
            "WHERE roles.name_role = 'Библиотекарь' AND id_user = ?;";
    private final static String CHECK_LOGIN_PASSWORD = "SELECT login, password FROM library.users JOIN library.roles on users.id_role = roles.id_role WHERE name_role = 'Библиотекарь' AND login = ? AND password = ?";
    private final static String DELETE_LIBRARIAN = "DELETE FROM library.users WHERE id_user = ?";
    private final static String UPDATE_LIBRARIAN = "UPDATE library.users SET surname = ?, name = ?, middle_name = ?, login = ? WHERE id_user = ?;";
    private final static int ID_ROLE_LIBRARIAN = 2;


    @Override
    public List<User> getAllLibrarians() throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        Statement statement = null;
        ResultSet resultSet;
        User librarian;
        List<User> listLibrarian = new ArrayList<>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_LIBRARIANS);
            while (resultSet.next()) {
                librarian = getLibrarianFromResultSet(resultSet);
                listLibrarian.add(librarian);
            }
            return listLibrarian;
        } catch (SQLException e) {
            throw new DAOException("Error get all librarians" + e);
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
    public User getLibrarianById(int id) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet;
        User librarian = new User();
        try {
            statement = connection.prepareStatement(SELECT_LIBRARIAN_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                librarian = getLibrarianFromResultSet(resultSet);
            }
            return librarian;
        } catch (SQLException e) {
            throw new DAOException("Error get an librarian by id" + e);
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
    public void addLibrarian(User librarian) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(INSERT_LIBRARIAN);
            statement.setString(1, librarian.getSurname());
            statement.setString(2, librarian.getName());
            statement.setString(3, librarian.getMiddleName());
            statement.setString(4, librarian.getLogin());
            statement.setString(5, librarian.getPassword());
            statement.setInt(6, ID_ROLE_LIBRARIAN);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error add librarian" + e);
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
    public boolean deleteLibrarian(int id) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETE_LIBRARIAN);
            statement.setInt(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DAOException("Error delete the librarian" + e);
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
    public boolean updateLibrarian(User librarian) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(UPDATE_LIBRARIAN);
            statement.setString(1, librarian.getSurname());
            statement.setString(2, librarian.getName());
            statement.setString(3, librarian.getMiddleName());
            statement.setString(4, librarian.getLogin());
            statement.setInt(5, librarian.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DAOException("Error update a librarian" + e);
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
    public boolean checkLoginPassword(String login, String password) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(CHECK_LOGIN_PASSWORD);
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new DAOException("Error check librarian by login and password" + e);
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

    private User getLibrarianFromResultSet(ResultSet resultSet) throws SQLException {
        User librarian = new User();
        librarian.setId(resultSet.getInt("id_user"));
        librarian.setSurname(resultSet.getString("surname"));
        librarian.setName(resultSet.getString("name"));
        librarian.setMiddleName(resultSet.getString("middle_name"));
        librarian.setLogin(resultSet.getString("login"));
        return librarian;
    }
}
