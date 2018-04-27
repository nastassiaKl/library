package by.kalinklish.project.command.user.book;

import by.kalinklish.project.constant.ParameterConstants;
import by.kalinklish.project.entity.Book;
import by.kalinklish.project.exception.CommandException;
import by.kalinklish.project.exception.LogicException;
import by.kalinklish.project.logic.BookLogic;
import by.kalinklish.project.command.Command;
import by.kalinklish.project.controller.Router;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static by.kalinklish.project.constant.JspPageConstants.USER_PERSONAL_BOOK_PAGE;

public class GetPersonalBookCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(GetPersonalBookCommand.class);
    private BookLogic bookLogic;

    public GetPersonalBookCommand(BookLogic bookLogic) {
        this.bookLogic = bookLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        String page = null;
        Book book;

        try {
            int idBook = Integer.parseInt(request.getParameter(ParameterConstants.PARAM_ID_BOOK));
            book = bookLogic.getBookById(idBook);
            request.setAttribute(ParameterConstants.PARAM_PERSONAL_BOOK, book);
            page = USER_PERSONAL_BOOK_PAGE;

        } catch (LogicException e) {
            LOGGER.error(e);
        }

        router.setPagePath(page);
        router.setRoute(Router.RouteType.FORWARD);
        return router;
    }
}
