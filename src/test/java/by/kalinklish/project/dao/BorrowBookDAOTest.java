package by.kalinklish.project.dao;

import by.kalinklish.project.dao.factory.DAOFactory;
import by.kalinklish.project.entity.Order;
import by.kalinklish.project.exception.DAOException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class BorrowBookDAOTest {
    private static BorrowBookDAO borrowBookDAO;

    @BeforeClass
    public static void init() {
        borrowBookDAO = DAOFactory.getInstance().getBorrowBookDAO();
    }

    @Test
    public void testGetApprovedOrders() throws DAOException {
        List<Order> orders = borrowBookDAO.getApprovedOrders(1);
        Assert.assertFalse(orders.isEmpty());
    }
}
