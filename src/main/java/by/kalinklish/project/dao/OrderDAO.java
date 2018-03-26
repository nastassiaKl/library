package by.kalinklish.project.dao;

import by.kalinklish.project.entity.Order;
import by.kalinklish.project.exception.DAOException;


import java.util.List;

public interface OrderDAO {
    List<Order> getAllOrders() throws DAOException;
    boolean checkPersonalOrders(int idUser) throws DAOException;
    List<Order> getPersonalOrders(int idUser) throws DAOException;
    boolean makeOrder(int idUser, int idBook, int idAuthor) throws DAOException;
    void deleteOrderById(int id) throws DAOException;
}
