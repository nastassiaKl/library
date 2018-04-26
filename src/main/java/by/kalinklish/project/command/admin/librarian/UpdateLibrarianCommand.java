package by.kalinklish.project.command.admin.librarian;

import by.kalinklish.project.command.Command;
import by.kalinklish.project.constant.JspPageConstants;
import by.kalinklish.project.constant.ParameterConstants;
import by.kalinklish.project.controller.Router;
import by.kalinklish.project.entity.User;
import by.kalinklish.project.exception.CommandException;
import by.kalinklish.project.exception.LogicException;
import by.kalinklish.project.logic.LibrarianLogic;
import by.kalinklish.project.validation.InputParamValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class UpdateLibrarianCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(UpdateLibrarianCommand.class);
    private LibrarianLogic librarianLogic;

    public UpdateLibrarianCommand(LibrarianLogic librarianLogic) {
        this.librarianLogic = librarianLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        HttpSession session = request.getSession();
        String page = null;
        User librarian;
        List<User> listLibrarians;

        try {
            librarian = setLibrarianFromRequest(request);

            if (librarianLogic.updateLibrarian(librarian)) {
                listLibrarians = librarianLogic.getLibrarians();
                session.setAttribute(ParameterConstants.PARAM_LIBRARIANS, listLibrarians);
                router.setRoute(Router.RouteType.REDIRECT);
                page = JspPageConstants.ADMIN_LIBRARIANS_PAGE;
            } else {
                router.setRoute(Router.RouteType.FORWARD);
                page = JspPageConstants.ADMIN_EDIT_LIBRARIAN_PAGE;
            }

        } catch (LogicException e) {
            LOGGER.error(e);
        }

        router.setPagePath(page);
        return router;
    }

    private User setLibrarianFromRequest(HttpServletRequest request) {
        User librarian = new User();
        int id = Integer.parseInt(request.getParameter(ParameterConstants.PARAM_ID_LIBRARIAN));
        String surname = request.getParameter(ParameterConstants.PARAM_SURNAME);
        String name = request.getParameter(ParameterConstants.PARAM_NAME);
        String middleName = request.getParameter(ParameterConstants.PARAM_MIDDLE_NAME);
        String login = request.getParameter(ParameterConstants.PARAM_LOGIN);

        if (InputParamValidator.isValidateLibrarianData(surname, name, middleName, login)) {
            librarian = new User(id, surname, name, middleName, login);
        }

        return librarian;
    }
}
