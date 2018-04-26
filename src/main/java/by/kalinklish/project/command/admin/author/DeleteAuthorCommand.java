package by.kalinklish.project.command.admin.author;

import by.kalinklish.project.constant.PropertyKeys;
import by.kalinklish.project.exception.CommandException;
import by.kalinklish.project.manager.MessageManager;
import by.kalinklish.project.command.Command;
import by.kalinklish.project.constant.JspPageConstants;
import by.kalinklish.project.constant.MessageConstants;
import by.kalinklish.project.constant.ParameterConstants;
import by.kalinklish.project.controller.Router;
import by.kalinklish.project.entity.Author;
import by.kalinklish.project.exception.LogicException;
import by.kalinklish.project.logic.AuthorLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DeleteAuthorCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(DeleteAuthorCommand.class);
    private AuthorLogic authorLogic;

    public DeleteAuthorCommand(AuthorLogic authorLogic) {
        this.authorLogic = authorLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        String locale = request.getSession().getAttribute(ParameterConstants.PARAM_LOCALE) == null ? ParameterConstants.DEFAULT_LOCALE : request.getSession().getAttribute(ParameterConstants.PARAM_LOCALE).toString();
        String page = null;
        List<Author> listAuthors;

        try {
            int idAuthor = Integer.parseInt(request.getParameter(ParameterConstants.PARAM_ID_AUTHOR));

            if (authorLogic.deleteAuthor(idAuthor)) {
                listAuthors = authorLogic.getAuthors();
                request.setAttribute(ParameterConstants.PARAM_AUTHORS, listAuthors);
                request.setAttribute(MessageConstants.MESSAGE_DELETE_AUTHOR, MessageManager.getLocale(locale).getMessage(PropertyKeys.DELETE_AUTHOR_MESSAGE));
                page = JspPageConstants.ADMIN_AUTHORS_PAGE;
            } else {
                page = JspPageConstants.ADMIN_AUTHORS_PAGE;
            }

        } catch (LogicException e) {
            LOGGER.error(e);
        }

        router.setPagePath(page);
        router.setRoute(Router.RouteType.FORWARD);
        return router;
    }
}
