package by.kalinklish.project.command.common;

import by.kalinklish.project.constant.ParameterConstants;
import by.kalinklish.project.exception.CommandException;
import by.kalinklish.project.command.Command;
import by.kalinklish.project.constant.JspPageConstants;
import by.kalinklish.project.controller.Router;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangeLocaleCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        HttpSession session = request.getSession();
        String page;

        String locale = request.getParameter(ParameterConstants.PARAM_LOCALE);
        session.setAttribute(ParameterConstants.PARAM_LOCALE, locale);
        page = JspPageConstants.START_PAGE;

        router.setPagePath(page);
        router.setRoute(Router.RouteType.FORWARD);
        return router;
    }
}
