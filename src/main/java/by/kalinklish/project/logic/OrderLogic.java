package by.kalinklish.project.logic;

import by.kalinklish.project.entity.Order;
import by.kalinklish.project.exception.DAOException;
import by.kalinklish.project.dao.BorrowBookDAO;
import by.kalinklish.project.dao.OrderDAO;
import by.kalinklish.project.dao.factory.DAOFactory;
import by.kalinklish.project.exception.LogicException;

import java.util.List;

public class OrderLogic {
    private OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
    private BorrowBookDAO borrowBookDAO = DAOFactory.getInstance().getBorrowBookDAO();

    public List<Order> getAllOrders() throws LogicException {
        try {
            return orderDAO.getAllOrders();
        } catch (DAOException e) {
            throw new LogicException(e);
        }
    }

    public boolean addToCart(int idUser, int idBook, int idAuthor) throws LogicException {
        try {
            return orderDAO.makeOrder(idUser, idBook, idAuthor);
        } catch (DAOException e) {
            throw new LogicException(e);
        }
    }

    public void deleteOrderById(int id) throws LogicException {
        try {
            orderDAO.deleteOrderById(id);
        } catch (DAOException e) {
            throw new LogicException(e);
        }
    }

   public boolean checkPersonalOrders(int idUser) throws LogicException {
        try {
            return orderDAO.checkPersonalOrders(idUser);
        } catch (DAOException e) {
            throw new LogicException(e);
        }
    }

    public List<Order> getPersonalOrders(int idUser) throws LogicException {
        try {
            return orderDAO.getPersonalOrders(idUser);
        } catch (DAOException e) {
            throw new LogicException(e);
        }
    }

    public void takeOrder(Order order) throws LogicException {
        try {
            borrowBookDAO.addOrder(order);
        } catch (DAOException e) {
            throw new LogicException(e);
        }
    }

    public List<Order> getApprovedOrders(int idUser) throws LogicException {
        try {
            return borrowBookDAO.getApprovedOrders(idUser);
        } catch (DAOException e) {
            throw new LogicException(e);
        }
    }
}
