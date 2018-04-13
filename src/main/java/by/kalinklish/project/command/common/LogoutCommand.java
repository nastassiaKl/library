package by.kalinklish.project.command.common;

import by.kalinklish.project.exception.CommandException;
import by.kalinklish.project.command.Command;
import by.kalinklish.project.constant.JspPageConstants;
import by.kalinklish.project.controller.Router;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        String page;

        HttpSession session = request.getSession(false);
        session.invalidate();

        page = JspPageConstants.INDEX_PAGE;
        router.setPagePath(page);
        router.setRoute(Router.RouteType.REDIRECT);
        return router;

    }
}
