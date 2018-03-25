package by.kalinklish.project.dao.impl;

import by.kalinklish.project.dao.AuthorDAO;
import by.kalinklish.project.entity.Author;
import by.kalinklish.project.exception.DAOException;
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

public class AuthorDAOImpl implements AuthorDAO {
    private final static Logger LOGGER = LogManager.getLogger(AuthorDAOImpl.class);
    private final static String SELECT_AUTHORS = "SELECT id_author, surname, name, middle_name, country FROM library.authors;";
    private final static String SELECT_AUTHOR_BY_ID = "SELECT id_author, surname, name, middle_name, country FROM library.authors WHERE id_author = ?;";
    private final static String INSERT_AUTHOR = "INSERT INTO authors(surname, name, middle_name, country) VALUES(?,?,?,?)";
    private final static String DELETE_AUTHOR = "DELETE FROM library.authors WHERE id_author = ?";
    private final static String UPDATE_AUTHOR = "UPDATE library.authors SET surname = ?, name = ?, middle_name = ?, country = ? " +
            "WHERE id_author = ?;";

    @Override
    public List<Author> getAllAuthors() throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        Statement statement = null;
        ResultSet resultSet;
        Author author;
        List<Author> listAuthors = new ArrayList<>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_AUTHORS);
            while (resultSet.next()) {
                author = getAuthorFromResultSet(resultSet);
                listAuthors.add(author);
            }
            return listAuthors;
        } catch (SQLException e) {
            throw new DAOException("Error get all authors" + e);
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
    public Author getAuthorById(int id) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet;
        Author author = new Author();
        try {
            statement = connection.prepareStatement(SELECT_AUTHOR_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                author = getAuthorFromResultSet(resultSet);
            }
            return author;
        } catch (SQLException e) {
            throw new DAOException("Error get an author by id" + e);
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
    public boolean addAuthor(Author author) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(INSERT_AUTHOR);
            statement.setString(1, author.getSurname());
            statement.setString(2, author.getName());
            statement.setString(3, author.getMiddleName());
            statement.setString(4, author.getCountryBirth());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DAOException("Error add author" + e);
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
    public boolean deleteAuthor(int id) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETE_AUTHOR);
            statement.setInt(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DAOException("Error delete an author" + e);
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
    public boolean updateAuthor(Author author) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(UPDATE_AUTHOR);
            statement.setString(1, author.getSurname());
            statement.setString(2, author.getName());
            statement.setString(3, author.getMiddleName());
            statement.setString(4, author.getCountryBirth());
            statement.setInt(5, author.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DAOException("Error update an author" + e);
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

    private Author getAuthorFromResultSet(ResultSet resultSet) throws SQLException {
        Author author = new Author();
        author.setId(resultSet.getInt("id_author"));
        author.setSurname(resultSet.getString("surname"));
        author.setName(resultSet.getString("name"));
        author.setMiddleName(resultSet.getString("middle_name"));
        author.setCountryBirth(resultSet.getString("country"));
        return author;
    }
}
