package by.kalinklish.project.command.common;

import by.kalinklish.project.constant.*;
import by.kalinklish.project.entity.User;
import by.kalinklish.project.exception.CommandException;
import by.kalinklish.project.exception.LogicException;
import by.kalinklish.project.logic.ReaderLogic;
import by.kalinklish.project.manager.MessageManager;
import by.kalinklish.project.command.Command;
import by.kalinklish.project.constant.*;
import by.kalinklish.project.controller.Router;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(LoginCommand.class);
    private ReaderLogic readerLogic;

    public LoginCommand(ReaderLogic readerLogic) {
        this.readerLogic = readerLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        String locale = request.getSession().getAttribute(ParameterConstants.PARAM_LOCALE) == null ? ParameterConstants.DEFAULT_LOCALE : request.getSession().getAttribute(ParameterConstants.PARAM_LOCALE).toString();
        String page = null;
        String roleType;
        User user;

        try {
            String loginValue = request.getParameter(ParameterConstants.PARAM_LOGIN);
            String passwordValue = request.getParameter(ParameterConstants.PARAM_PASSWORD);

            roleType = readerLogic.checkLoginPassword(loginValue, passwordValue);
            if (roleType != null) {
                user = readerLogic.getUserByLoginPassword(loginValue, passwordValue);
                router.setRoute(Router.RouteType.REDIRECT);
                page = setRole(request, roleType, user);
            } else {
                request.setAttribute(MessageConstants.MESSAGE_ERROR_LOGIN, MessageManager.getLocale(locale).getMessage(PropertyKeys.LOGIN_ERROR_MESSAGE));
                router.setRoute(Router.RouteType.FORWARD);
                page = JspPageConstants.LOGIN_PAGE;
            }
        } catch (LogicException e) {
            LOGGER.error(e);
        }

        router.setPagePath(page);
        return router;
    }

    private String setRole(HttpServletRequest request, String roleType, User user) {
        HttpSession session = request.getSession();
        String page = null;
        switch (roleType) {
            case RoleType.ADMIN:
                session.setAttribute(ParameterConstants.PARAM_ROLE, RoleType.ROLE_ADMIN);
                session.setAttribute(ParameterConstants.PARAM_ADMIN_LOGIN, user.getLogin());
                page = JspPageConstants.ADMIN_MAIN_PAGE;
                break;
            case RoleType.LIBRARIAN:
                session.setAttribute(ParameterConstants.PARAM_ROLE, RoleType.ROLE_LIBRARIAN);
                session.setAttribute(ParameterConstants.PARAM_LIBRARIAN_LOGIN, user.getLogin());
                page = JspPageConstants.LIBRARIAN_MAIN_PAGE;
                break;
            case RoleType.READER:
                session.setAttribute(ParameterConstants.PARAM_ROLE, RoleType.ROLE_READER);
                session.setAttribute(RoleType.ROLE_READER, user);
                page = JspPageConstants.USER_MAIN_PAGE;
                break;
        }

        return page;
    }
}
