package by.kalinklish.project.command.admin.author;

import by.kalinklish.project.command.Command;
import by.kalinklish.project.constant.JspPageConstants;
import by.kalinklish.project.constant.ParameterConstants;
import by.kalinklish.project.controller.Router;
import by.kalinklish.project.entity.Author;
import by.kalinklish.project.exception.CommandException;
import by.kalinklish.project.exception.LogicException;
import by.kalinklish.project.logic.AuthorLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ShowAuthorsCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(ShowAuthorsCommand.class);
    private AuthorLogic authorLogic;

    public ShowAuthorsCommand(AuthorLogic authorLogic) {
        this.authorLogic = authorLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        HttpSession session = request.getSession();
        String page = null;
        List<Author> listAuthors;

        try {
            listAuthors = authorLogic.getAuthors();
            session.setAttribute(ParameterConstants.PARAM_AUTHORS, listAuthors);
            page = JspPageConstants.ADMIN_AUTHORS_PAGE;
        } catch (LogicException e) {
            LOGGER.error(e);
        }

        router.setPagePath(page);
        router.setRoute(Router.RouteType.FORWARD);
        return router;
    }
}
