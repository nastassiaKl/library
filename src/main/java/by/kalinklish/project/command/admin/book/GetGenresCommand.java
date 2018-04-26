package by.kalinklish.project.command.admin.book;

import by.kalinklish.project.logic.BookLogic;
import by.kalinklish.project.command.Command;
import by.kalinklish.project.constant.ParameterConstants;
import by.kalinklish.project.controller.Router;
import by.kalinklish.project.exception.CommandException;
import by.kalinklish.project.exception.LogicException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static by.kalinklish.project.constant.JspPageConstants.ADMIN_ADD_BOOKS_PAGE;

public class GetGenresCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(GetGenresCommand.class);
    private BookLogic bookLogic;

    public GetGenresCommand(BookLogic bookLogic) {
        this.bookLogic = bookLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        String page = null;
        Map<Integer, String> mapGenres;

        try {
            mapGenres = bookLogic.getAllGenres();
            request.setAttribute(ParameterConstants.PARAM_GENRES, mapGenres);
            page = ADMIN_ADD_BOOKS_PAGE;
        } catch (LogicException e) {
            LOGGER.error(e);
        }

        router.setPagePath(page);
        router.setRoute(Router.RouteType.FORWARD);
        return router;
    }
}
