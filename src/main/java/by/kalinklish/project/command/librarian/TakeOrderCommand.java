package by.kalinklish.project.command.librarian;

import by.kalinklish.project.constant.ParameterConstants;
import by.kalinklish.project.entity.Book;
import by.kalinklish.project.entity.Order;
import by.kalinklish.project.entity.User;
import by.kalinklish.project.exception.CommandException;
import by.kalinklish.project.logic.OrderLogic;
import by.kalinklish.project.mail.sender.MailSender;
import by.kalinklish.project.command.Command;
import by.kalinklish.project.constant.JspPageConstants;
import by.kalinklish.project.controller.Router;
import by.kalinklish.project.exception.LogicException;
import by.kalinklish.project.validation.InputParamValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;

public class TakeOrderCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(TakeOrderCommand.class);
    private OrderLogic orderLogic;

    public TakeOrderCommand(OrderLogic orderLogic) {
        this.orderLogic = orderLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        HttpSession session = request.getSession();
        String page = null;
        Order order;
        List<Order> listOrders;

        try {
            order = setOrderFromRequest(request);
            int idOrder = Integer.parseInt(request.getParameter(ParameterConstants.PARAM_ID_ORDER));
            String mail = request.getParameter(ParameterConstants.PARAM_MAIL);
            String tittle = request.getParameter(ParameterConstants.PARAM_TITTLE);

            orderLogic.takeOrder(order);
            orderLogic.deleteOrderById(idOrder);

            MailSender.sendMail(ParameterConstants.PARAM_TITTLE_MAIL, ParameterConstants.PARAM_BOOK_MAIL + tittle, mail);

            listOrders = orderLogic.getAllOrders();

            session.setAttribute(ParameterConstants.PARAM_ORDERS, listOrders);
            page = JspPageConstants.LIBRARIAN_ORDERS_PAGE;
        } catch (LogicException e) {
            LOGGER.error(e);
        }

        router.setPagePath(page);
        router.setRoute(Router.RouteType.REDIRECT);
        return router;
    }

    private Order setOrderFromRequest(HttpServletRequest request) {
        Order order = new Order();
        User user = new User();
        Book book = new Book();
        user.setId(Integer.parseInt(request.getParameter(ParameterConstants.PARAM_ID_READER)));
        book.setId(Integer.parseInt(request.getParameter(ParameterConstants.PARAM_ID_BOOK)));
        order.setUser(user);
        order.setBook(book);
        Date dateBorrow = Date.valueOf(request.getParameter(ParameterConstants.PARAM_DATE_BORROW));
        Date dateReturn = Date.valueOf(request.getParameter(ParameterConstants.PARAM_DATE_RETURN));
        String methodBorrow = request.getParameter(ParameterConstants.PARAM_METHOD_BORROW);

        if (InputParamValidator.isValidateOrderData(dateBorrow, dateReturn, methodBorrow)) {
            order.setDateBorrow(dateBorrow);
            order.setDateReturn(dateReturn);
            order.setMethodBorrow(methodBorrow);
        }
        return order;
    }
}
