package by.kalinklish.project.command.user.order;

import by.kalinklish.project.constant.JspPageConstants;
import by.kalinklish.project.constant.ParameterConstants;
import by.kalinklish.project.entity.Order;
import by.kalinklish.project.exception.CommandException;
import by.kalinklish.project.exception.LogicException;
import by.kalinklish.project.logic.OrderLogic;
import by.kalinklish.project.command.Command;
import by.kalinklish.project.controller.Router;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DeleteOrderCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(DeleteOrderCommand.class);
    private OrderLogic orderLogic;

    public DeleteOrderCommand(OrderLogic orderLogic) {
        this.orderLogic = orderLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        String page = null;
        List<Order> listOrders;

        try {
            int idOrder = Integer.parseInt(request.getParameter(ParameterConstants.PARAM_ID_ORDER));
            int id_user = Integer.parseInt(request.getParameter(ParameterConstants.PARAM_ID_READER));

            orderLogic.deleteOrderById(idOrder);
            listOrders = orderLogic.getPersonalOrders(id_user);

            request.setAttribute(ParameterConstants.PARAM_ORDERS, listOrders);
            page = JspPageConstants.USER_ORDERS_CART_PAGE;

        } catch (LogicException e) {
            LOGGER.error(e);
        }

        router.setPagePath(page);
        router.setRoute(Router.RouteType.FORWARD);
        return router;
    }
}
