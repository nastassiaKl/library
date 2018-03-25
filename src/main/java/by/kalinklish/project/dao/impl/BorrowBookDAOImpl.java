package by.kalinklish.project.dao.impl;

import by.kalinklish.project.dao.BorrowBookDAO;
import by.kalinklish.project.entity.Book;
import by.kalinklish.project.entity.Order;
import by.kalinklish.project.exception.DAOException;
import by.kalinklish.project.dao.pool.ConnectionPool;
import by.kalinklish.project.dao.pool.ProxyConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BorrowBookDAOImpl implements BorrowBookDAO {
    private final static Logger LOGGER = LogManager.getLogger(BorrowBookDAOImpl.class);
    private final static String INSERT_BORROWED_BOOK = "INSERT INTO library.borrow_book(id_user, id_book, date_borrow, date_return, method_borrow) VALUES(?,?,?,?,?);";
    private final static String GET_APPROVED_ORDERS = "SELECT tittle, date_borrow, date_return, method_borrow FROM library.borrow_book JOIN library.books ON books.id_book = borrow_book.id_book WHERE id_user = ?;";

    @Override
    public void addOrder(Order order) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(INSERT_BORROWED_BOOK);
            statement.setInt(1, order.getUser().getId());
            statement.setInt(2, order.getBook().getId());
            statement.setDate(3, order.getDateBorrow());
            statement.setDate(4, order.getDateReturn());
            statement.setString(5, order.getMethodBorrow());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error add borrowed book" + e);
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
    public List<Order> getApprovedOrders(int idUser) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet;
        List<Order> listOrders = new ArrayList<>();
        try {
            statement = connection.prepareStatement(GET_APPROVED_ORDERS);
            statement.setInt(1, idUser);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                Book book = new Book();
                book.setTittle(resultSet.getString("tittle"));
                order.setBook(book);
                order.setDateBorrow(resultSet.getDate("date_borrow"));
                order.setDateReturn(resultSet.getDate("date_return"));
                order.setMethodBorrow(resultSet.getString("method_borrow"));
                listOrders.add(order);
            }
            return listOrders;
        } catch (SQLException e) {
            throw new DAOException("Error get approved orders" + e);
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
}
