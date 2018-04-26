package by.kalinklish.project.command.admin.librarian;

import by.kalinklish.project.command.Command;
import by.kalinklish.project.constant.JspPageConstants;
import by.kalinklish.project.constant.ParameterConstants;
import by.kalinklish.project.entity.User;
import by.kalinklish.project.exception.CommandException;
import by.kalinklish.project.exception.LogicException;
import by.kalinklish.project.logic.LibrarianLogic;
import by.kalinklish.project.controller.Router;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class EditLibrarianCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(EditLibrarianCommand.class);
    private LibrarianLogic librarianLogic;

    public EditLibrarianCommand(LibrarianLogic librarianLogic) {
        this.librarianLogic = librarianLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        String page = null;
        User librarian;

        try {
            int idLibrarian = Integer.parseInt(request.getParameter(ParameterConstants.PARAM_ID_LIBRARIAN));
            librarian = librarianLogic.getLibrarianById(idLibrarian);

            request.setAttribute(ParameterConstants.PARAM_LIBRARIAN, librarian);
            page = JspPageConstants.ADMIN_EDIT_LIBRARIAN_PAGE;

        } catch (LogicException e) {
            LOGGER.error(e);
        }

        router.setPagePath(page);
        router.setRoute(Router.RouteType.FORWARD);
        return router;
    }
}
