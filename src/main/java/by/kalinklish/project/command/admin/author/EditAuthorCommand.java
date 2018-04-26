package by.kalinklish.project.command.admin.author;

import by.kalinklish.project.constant.ParameterConstants;
import by.kalinklish.project.exception.CommandException;
import by.kalinklish.project.command.Command;
import by.kalinklish.project.constant.JspPageConstants;
import by.kalinklish.project.controller.Router;
import by.kalinklish.project.entity.Author;
import by.kalinklish.project.exception.LogicException;
import by.kalinklish.project.logic.AuthorLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class EditAuthorCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(EditAuthorCommand.class);
    private AuthorLogic authorLogic;

    public EditAuthorCommand(AuthorLogic authorLogic) {
        this.authorLogic = authorLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        String page = null;
        Author author;

        try {
            int idAuthor = Integer.parseInt(request.getParameter(ParameterConstants.PARAM_ID_AUTHOR));

            author = authorLogic.getAuthorById(idAuthor);
            request.setAttribute(ParameterConstants.PARAM_AUTHOR, author);
            page = JspPageConstants.ADMIN_EDIT_AUTHOR_PAGE;
        } catch (LogicException e) {
            LOGGER.error(e);
        }

        router.setPagePath(page);
        router.setRoute(Router.RouteType.FORWARD);
        return router;
    }
}
