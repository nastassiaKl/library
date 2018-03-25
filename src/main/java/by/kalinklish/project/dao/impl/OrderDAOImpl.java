package by.kalinklish.project.dao.impl;

import by.kalinklish.project.dao.pool.ConnectionPool;
import by.kalinklish.project.entity.Author;
import by.kalinklish.project.entity.Book;
import by.kalinklish.project.entity.Order;
import by.kalinklish.project.entity.User;
import by.kalinklish.project.exception.DAOException;
import by.kalinklish.project.dao.OrderDAO;
import by.kalinklish.project.dao.pool.ProxyConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    private final static Logger LOGGER = LogManager.getLogger(OrderDAOImpl.class);
    private final static String SELECT_ALL_ORDERS = "SELECT id_order, users.id_user, mail, number_ticket, books.id_book, tittle, authors.surname, authors.name, authors.middle_name, image_book " +
            "FROM library.orders " +
            "JOIN library.users ON users.id_user = orders.id_user " +
            "JOIN library.books ON books.id_book = orders.id_book " +
            "JOIN library.authors ON authors.id_author = orders.id_author;";
    private final static String SELECT_PERSONAL_ORDERS = "SELECT id_order, books.id_book, users.id_user, mail, number_ticket, tittle, authors.surname, authors.name, authors.middle_name, image_book " +
            "FROM library.orders " +
            "JOIN library.users ON users.id_user = orders.id_user " +
            "JOIN library.books ON orders.id_book = books.id_book " +
            "JOIN library.authors ON orders.id_author = authors.id_author " +
            "WHERE users.id_user = ?;";
    private final static String CHECK_PERSONAL_ORDERS = "SELECT id_user " +
            "FROM library.orders " +
            "JOIN library.books ON orders.id_book = books.id_book " +
            "JOIN library.authors ON orders.id_author = authors.id_author " +
            "WHERE id_user = ?;";
    private final static String INSERT_ORDER = "INSERT INTO library.orders(id_user, id_book, id_author) VALUES(?,?,?)";
    private final static String DELETE_ORDER_BY_ID = "DELETE FROM library.orders WHERE id_order = ?";

    @Override
    public List<Order> getAllOrders() throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        Statement statement = null;
        ResultSet resultSet;
        Order order;
        List<Order> listOrders = new ArrayList<>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_ALL_ORDERS);
            while (resultSet.next()) {
                order = getOrdersFromResultSet(resultSet);
                listOrders.add(order);
            }
            return listOrders;
        } catch (SQLException e) {
            throw new DAOException("Error get all orders" + e);
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
    public boolean checkPersonalOrders(int idUser) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet;
        try {
            statement = connection.prepareStatement(CHECK_PERSONAL_ORDERS);
            statement.setInt(1, idUser);
            resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new DAOException("Error find orders by number ticket" + e);
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
    public List<Order> getPersonalOrders(int idUser) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet;
        Order order;
        List<Order> listOrders = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SELECT_PERSONAL_ORDERS);
            statement.setInt(1, idUser);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                order = getOrdersFromResultSet(resultSet);
                listOrders.add(order);
            }
            return listOrders;
        } catch (SQLException e) {
            throw new DAOException("Error get personal orders" + e);
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
    public boolean makeOrder(int idUser, int idBook, int idAuthor) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(INSERT_ORDER);
            statement.setInt(1, idUser);
            statement.setInt(2, idBook);
            statement.setInt(3, idAuthor);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DAOException("Error add order" + e);
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
    public void deleteOrderById(int id) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETE_ORDER_BY_ID);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error delete an order by id" + e);
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

    private Order getOrdersFromResultSet(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        Book book = new Book();
        Author author = new Author();
        User user = new User();
        order.setId(resultSet.getInt("id_order"));
        user.setId(resultSet.getInt("id_user"));
        user.setNumberTicket(resultSet.getInt("number_ticket"));
        user.setMail(resultSet.getString("mail"));
        order.setUser(user);
        book.setId(resultSet.getInt("id_book"));
        book.setTittle(resultSet.getString("tittle"));
        book.setImage(resultSet.getString("image_book"));
        order.setBook(book);
        author.setSurname(resultSet.getString("surname"));
        author.setName(resultSet.getString("name"));
        author.setMiddleName(resultSet.getString("middle_name"));
        order.setAuthor(author);
        return order;
    }
}
