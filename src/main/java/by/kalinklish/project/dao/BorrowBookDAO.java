package by.kalinklish.project.dao;

import by.kalinklish.project.entity.Order;
import by.kalinklish.project.exception.DAOException;


import java.util.List;

public interface BorrowBookDAO {
    void addOrder(Order order) throws DAOException;
    List<Order> getApprovedOrders(int idUser) throws DAOException;
}
