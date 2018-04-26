package by.kalinklish.project.command.admin.librarian;

import by.kalinklish.project.manager.MessageManager;
import by.kalinklish.project.command.Command;
import by.kalinklish.project.constant.JspPageConstants;
import by.kalinklish.project.constant.MessageConstants;
import by.kalinklish.project.constant.ParameterConstants;
import by.kalinklish.project.constant.PropertyKeys;
import by.kalinklish.project.controller.Router;
import by.kalinklish.project.entity.User;
import by.kalinklish.project.exception.CommandException;
import by.kalinklish.project.exception.LogicException;
import by.kalinklish.project.logic.LibrarianLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DeleteLibrarianCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(DeleteLibrarianCommand.class);
    private LibrarianLogic librarianLogic;

    public DeleteLibrarianCommand(LibrarianLogic librarianLogic) {
        this.librarianLogic = librarianLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        String locale = request.getSession().getAttribute(ParameterConstants.PARAM_LOCALE) == null ? ParameterConstants.DEFAULT_LOCALE : request.getSession().getAttribute(ParameterConstants.PARAM_LOCALE).toString();
        String page = null;
        List<User> listLibrarians;

        try {
            int id = Integer.parseInt(request.getParameter(ParameterConstants.PARAM_ID_LIBRARIAN));

            if (librarianLogic.deleteLibrarian(id)) {
                listLibrarians = librarianLogic.getLibrarians();
                request.setAttribute(ParameterConstants.PARAM_LIBRARIANS, listLibrarians);
                request.setAttribute(MessageConstants.MESSAGE_DELETE_LIBRARIAN, MessageManager.getLocale(locale).getMessage(PropertyKeys.DELETE_LIBRARIAN_MESSAGE));
                page = JspPageConstants.ADMIN_LIBRARIANS_PAGE;
            } else {
                page = JspPageConstants.ADMIN_LIBRARIANS_PAGE;
            }
        } catch (LogicException e) {
            LOGGER.error(e);
        }

        router.setPagePath(page);
        router.setRoute(Router.RouteType.FORWARD);
        return router;
    }
}
