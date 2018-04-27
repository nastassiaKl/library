package by.kalinklish.project.command.user.account;

import by.kalinklish.project.constant.JspPageConstants;
import by.kalinklish.project.constant.MessageConstants;
import by.kalinklish.project.constant.ParameterConstants;
import by.kalinklish.project.constant.PropertyKeys;
import by.kalinklish.project.exception.CommandException;
import by.kalinklish.project.exception.LogicException;
import by.kalinklish.project.logic.ReaderLogic;
import by.kalinklish.project.manager.MessageManager;
import by.kalinklish.project.command.Command;
import by.kalinklish.project.controller.Router;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ChangePasswordCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(ChangePasswordCommand.class);
    private ReaderLogic readerLogic;

    public ChangePasswordCommand(ReaderLogic readerLogic) {
        this.readerLogic = readerLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String locale = request.getSession().getAttribute(ParameterConstants.PARAM_LOCALE) == null ? ParameterConstants.DEFAULT_LOCALE : request.getSession().getAttribute(ParameterConstants.PARAM_LOCALE).toString();
        Router router = new Router();
        String page = null;
        String truePassword;

        int numberTicket = Integer.parseInt(request.getParameter(ParameterConstants.PARAM_NUMBER_TICKET));
        String oldPassword = request.getParameter(ParameterConstants.PARAM_OLD_PASSWORD);
        String newPassword = request.getParameter(ParameterConstants.PARAM_NEW_PASSWORD);
        String repeatNewPassword = request.getParameter(ParameterConstants.PARAM_REPEAT_NEW_PASSWORD);

        try {
            truePassword = readerLogic.getPassword(numberTicket);
            if (!oldPassword.equals(truePassword)) {
                request.setAttribute(MessageConstants.MESSAGE_WRONG_TRUE_PASSWORD, MessageManager.getLocale(locale).getMessage(PropertyKeys.WRONG_TRUE_PASSWORD_MESSAGE));
                router.setRoute(Router.RouteType.FORWARD);
                page = JspPageConstants.USER_CHANGE_PASSWORD_PAGE;
            } else if (!repeatNewPassword.equals(newPassword)) {
                request.setAttribute(MessageConstants.MESSAGE_WRONG_REPEAT_PASSWORD, MessageManager.getLocale(locale).getMessage(PropertyKeys.WRONG_REPEAT_PASSWORD_MESSAGE));
                router.setRoute(Router.RouteType.FORWARD);
                page = JspPageConstants.USER_CHANGE_PASSWORD_PAGE;
            } else if (readerLogic.changePassword(numberTicket, newPassword)) {
                router.setRoute(Router.RouteType.REDIRECT);
                page = JspPageConstants.USER_ACCOUNT_PAGE;
            }
        } catch (LogicException e) {
            LOGGER.error(e);
        }

        router.setPagePath(page);
        return router;
    }
}
