package by.kalinklish.project.command.admin.book;

import by.kalinklish.project.logic.BookLogic;
import by.kalinklish.project.command.Command;
import by.kalinklish.project.constant.JspPageConstants;
import by.kalinklish.project.constant.ParameterConstants;
import by.kalinklish.project.controller.Router;
import by.kalinklish.project.entity.Author;
import by.kalinklish.project.entity.Book;
import by.kalinklish.project.exception.CommandException;
import by.kalinklish.project.exception.LogicException;
import by.kalinklish.project.logic.AuthorLogic;
import by.kalinklish.project.validation.InputParamValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.List;

public class AddBookCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(AddBookCommand.class);
    private BookLogic bookLogic;
    private AuthorLogic authorLogic;

    public AddBookCommand(BookLogic bookLogic, AuthorLogic authorLogic) {
        this.bookLogic = bookLogic;
        this.authorLogic = authorLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        HttpSession session = request.getSession();
        String page = null;
        Book book;
        Author author;
        List<Book> listBooks;
        int bookPage = 1;

        if (request.getParameter(ParameterConstants.PARAM_PAGE) != null) {
            bookPage = Integer.parseInt(request.getParameter(ParameterConstants.PARAM_PAGE));
        }

        try {
            book = setBookFromRequest(request);
            author = setAuthorFromRequest(request);
            String[] genres = request.getParameterValues(ParameterConstants.PARAM_GENRE);

            if (authorLogic.addAuthor(author) && bookLogic.addBook(book)) {
                bookLogic.addBookAndGenre(genres);
                listBooks = bookLogic.getBooksWithPages(bookPage);
                session.setAttribute(ParameterConstants.PARAM_NUMBER_OF_PAGES, bookLogic.getNoOfPages());
                session.setAttribute(ParameterConstants.PARAM_BOOKS, listBooks);
                router.setRoute(Router.RouteType.REDIRECT);
                page = JspPageConstants.ADMIN_BOOKS_PAGE;
            } else {
                router.setRoute(Router.RouteType.FORWARD);
                page = JspPageConstants.ADMIN_ADD_BOOKS_PAGE;
            }
        } catch (ServletException | LogicException | IOException e) {
            LOGGER.error(e);
        }

        router.setPagePath(page);
        return router;
    }

    private Book setBookFromRequest(HttpServletRequest request) throws IOException, ServletException {
        Book book = new Book();
        Author author;
        String isbn = request.getParameter(ParameterConstants.PARAM_ISBN);
        String tittle = request.getParameter(ParameterConstants.PARAM_TITTLE);
        author = setAuthorFromRequest(request);
        Date dateEdition = Date.valueOf(request.getParameter(ParameterConstants.PARAM_DATA_EDITION));
        String placeEdition = request.getParameter(ParameterConstants.PARAM_PLACE_EDITION);
        String publisher = request.getParameter(ParameterConstants.PARAM_PUBLISHER);
        int numberCopies = Integer.parseInt(request.getParameter(ParameterConstants.PARAM_NUMBER_COPIES));
        Part filePart = request.getPart(ParameterConstants.PARAM_IMAGE);
        String imageName = getImageName(filePart);
        if (imageName.equals(ParameterConstants.PARAM_EMPTY_IMAGE)) {
            book.setImage(ParameterConstants.PARAM_DEFAULT_IMAGE_BOOK);
        } else {
            book.setImage(imageName);
        }

        if (InputParamValidator.isValidateBookData(isbn, tittle, dateEdition, placeEdition, publisher, numberCopies)) {
            book.setIsbn(isbn);
            book.setTittle(tittle);
            book.setAuthor(author);
            book.setDateEdition(dateEdition);
            book.setPlaceEdition(placeEdition);
            book.setPublisher(publisher);
            book.setNumberCopies(numberCopies);
        }

        return book;
    }

    private Author setAuthorFromRequest(HttpServletRequest request) {
        Author author = new Author();
        String surname = request.getParameter(ParameterConstants.PARAM_AUTHOR_SURNAME);
        String name = request.getParameter(ParameterConstants.PARAM_AUTHOR_NAME);
        String middleName = request.getParameter(ParameterConstants.PARAM_AUTHOR_MIDDLE_NAME);
        String country = request.getParameter(ParameterConstants.PARAM_AUTHOR_COUNTRY);
        if (middleName.equals(ParameterConstants.PARAM_AUTHOR_EMPTY_MIDDLE_NAME)) {
            middleName = ParameterConstants.PARAM_AUTHOR_NO_MIDDLE_NAME;
            author.setMiddleName(middleName);
        } else {
            if (InputParamValidator.isValidateMiddleName(middleName)) {
                author.setMiddleName(middleName);
            }
        }

        if (InputParamValidator.isValidateAuthorData(surname, name, country)) {
            author.setSurname(surname);
            author.setName(name);
            author.setCountryBirth(country);
        }
        return author;
    }

    private String getImageName(Part filePart) {
        String name = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        return name;
    }
}
