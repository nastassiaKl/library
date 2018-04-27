package by.kalinklish.project.command.user.book;

import by.kalinklish.project.constant.ParameterConstants;
import by.kalinklish.project.entity.Book;
import by.kalinklish.project.exception.CommandException;
import by.kalinklish.project.exception.LogicException;
import by.kalinklish.project.logic.BookLogic;
import by.kalinklish.project.command.Command;
import by.kalinklish.project.constant.JspPageConstants;
import by.kalinklish.project.controller.Router;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class GetBooksCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(GetBooksCommand.class);
    private BookLogic bookLogic;

    public GetBooksCommand(BookLogic bookLogic) {
        this.bookLogic = bookLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        Router router = new Router();
        String page = null;
        List<Book> listBooks;
        int bookPage = 1;

        if (request.getParameter(ParameterConstants.PARAM_PAGE) != null) {
            bookPage = Integer.parseInt(request.getParameter(ParameterConstants.PARAM_PAGE));
        }

        try {
            listBooks = bookLogic.getBooksWithPages(bookPage);
            request.setAttribute(ParameterConstants.PARAM_NUMBER_OF_PAGES, bookLogic.getNoOfPages());
            session.setAttribute(ParameterConstants.PARAM_BOOKS, listBooks);
            session.setAttribute(ParameterConstants.PARAM_CURRENT_PAGE, bookPage);
            page = JspPageConstants.USER_BOOKS_PAGE;
        } catch (LogicException e) {
            LOGGER.error(e);
        }
        router.setPagePath(page);
        router.setRoute(Router.RouteType.FORWARD);
        return router;
    }
}
