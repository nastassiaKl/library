package by.kalinklish.project.command.common;

import by.kalinklish.project.constant.ParameterConstants;
import by.kalinklish.project.exception.CommandException;
import by.kalinklish.project.command.Command;
import by.kalinklish.project.controller.Router;

import javax.servlet.http.HttpServletRequest;

public class LoadPageCommand implements Command {

    public LoadPageCommand() { }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        router.setPagePath(request.getParameter(ParameterConstants.PARAM_PAGE));
        router.setRoute(Router.RouteType.FORWARD);
        return router;
    }
}
