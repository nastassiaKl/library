package by.kalinklish.project.command.user.book;

import by.kalinklish.project.logic.BookLogic;
import by.kalinklish.project.manager.MessageManager;
import by.kalinklish.project.command.Command;
import by.kalinklish.project.constant.JspPageConstants;
import by.kalinklish.project.constant.MessageConstants;
import by.kalinklish.project.constant.ParameterConstants;
import by.kalinklish.project.constant.PropertyKeys;
import by.kalinklish.project.controller.Router;
import by.kalinklish.project.entity.Book;
import by.kalinklish.project.exception.CommandException;
import by.kalinklish.project.exception.LogicException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class FindBookByGenreCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(FindBookByGenreCommand.class);
    private BookLogic bookLogic;

    public FindBookByGenreCommand(BookLogic bookLogic) {
        this.bookLogic = bookLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        String locale = request.getSession().getAttribute(ParameterConstants.PARAM_LOCALE) == null ? ParameterConstants.DEFAULT_LOCALE : request.getSession().getAttribute(ParameterConstants.PARAM_LOCALE).toString();
        String page = null;
        List<Book> listFoundBooksByGenre;

        try {
            String genre = request.getParameter(ParameterConstants.PARAM_GENRE);
            listFoundBooksByGenre = bookLogic.findBooksByGenre(genre);

            if (listFoundBooksByGenre.isEmpty()) {
                request.setAttribute(MessageConstants.MESSAGE_FIND_BOOK, MessageManager.getLocale(locale).getMessage(PropertyKeys.FIND_BOOKS_MESSAGE));
                page = JspPageConstants.USER_FIND_BOOKS_PAGE;
            } else {
                request.setAttribute(ParameterConstants.PARAM_FOUND_BOOKS, listFoundBooksByGenre);
                page = JspPageConstants.USER_FIND_BOOKS_PAGE;
            }

        } catch (LogicException e) {
            LOGGER.error(e);
        }
        router.setPagePath(page);
        router.setRoute(Router.RouteType.FORWARD);
        return router;
    }
}
