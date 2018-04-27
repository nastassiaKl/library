package by.kalinklish.project.command.user.order;

import by.kalinklish.project.command.Command;
import by.kalinklish.project.constant.MessageConstants;
import by.kalinklish.project.constant.ParameterConstants;
import by.kalinklish.project.constant.PropertyKeys;
import by.kalinklish.project.controller.Router;
import by.kalinklish.project.entity.Order;
import by.kalinklish.project.exception.CommandException;
import by.kalinklish.project.exception.LogicException;
import by.kalinklish.project.logic.OrderLogic;
import by.kalinklish.project.manager.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.kalinklish.project.constant.JspPageConstants.USER_APPROVED_ORDERS;
import static by.kalinklish.project.constant.JspPageConstants.USER_ORDERS_CART_PAGE;

public class GetApprovedOrdersCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(GetApprovedOrdersCommand.class);
    private OrderLogic orderLogic;

    public GetApprovedOrdersCommand(OrderLogic orderLogic) {
        this.orderLogic = orderLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        String locale = request.getSession().getAttribute(ParameterConstants.PARAM_LOCALE) == null ? ParameterConstants.DEFAULT_LOCALE : request.getSession().getAttribute(ParameterConstants.PARAM_LOCALE).toString();
        String page = null;
        List<Order> listOrders;

        try {
            int idUser = Integer.parseInt(request.getParameter(ParameterConstants.PARAM_ID_READER));
            listOrders = orderLogic.getApprovedOrders(idUser);

            if (listOrders.isEmpty()) {
                request.setAttribute(MessageConstants.MESSAGE_EMTPY_APPROVED_ORDERS, MessageManager.getLocale(locale).getMessage(PropertyKeys.EMPTY_APPROVED_ORDERS_MESSAGE));
                page = USER_ORDERS_CART_PAGE;
            } else {
                request.setAttribute(ParameterConstants.PARAM_APPROVED_ORDERS, listOrders);
                page = USER_APPROVED_ORDERS;
            }

        } catch (LogicException e) {
            LOGGER.error(e);
        }

        router.setPagePath(page);
        router.setRoute(Router.RouteType.FORWARD);
        return router;
    }
}
