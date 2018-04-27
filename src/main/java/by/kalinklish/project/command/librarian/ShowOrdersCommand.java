package by.kalinklish.project.command.librarian;

import by.kalinklish.project.command.Command;
import by.kalinklish.project.constant.JspPageConstants;
import by.kalinklish.project.constant.ParameterConstants;
import by.kalinklish.project.controller.Router;
import by.kalinklish.project.entity.Order;
import by.kalinklish.project.exception.CommandException;
import by.kalinklish.project.exception.LogicException;
import by.kalinklish.project.logic.OrderLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowOrdersCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(ShowOrdersCommand.class);
    private OrderLogic orderLogic;

    public ShowOrdersCommand(OrderLogic orderLogic) {
        this.orderLogic = orderLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        String page = null;
        List<Order> listOrders;

        try {
            listOrders = orderLogic.getAllOrders();
            request.setAttribute(ParameterConstants.PARAM_ORDERS, listOrders);
            page = JspPageConstants.LIBRARIAN_ORDERS_PAGE;
        } catch (LogicException e) {
            LOGGER.error(e);
        }

        router.setPagePath(page);
        router.setRoute(Router.RouteType.FORWARD);
        return router;
    }
}
