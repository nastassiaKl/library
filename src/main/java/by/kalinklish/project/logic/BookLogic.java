package by.kalinklish.project.logic;

import by.kalinklish.project.dao.BookDAO;
import by.kalinklish.project.dao.factory.DAOFactory;
import by.kalinklish.project.entity.Book;
import by.kalinklish.project.exception.DAOException;
import by.kalinklish.project.exception.LogicException;

import java.util.List;
import java.util.Map;

public class BookLogic {
    private final static int QUANTITY_DATA_ON_PAGE = 5;
    private BookDAO bookDAO = DAOFactory.getInstance().getBookDAO();

    public List<Book> getBooks() throws LogicException {
        try {
            return bookDAO.getBooksAndAuthors();
        } catch (DAOException e) {
            throw new LogicException(e);
        }
    }

    ]

    public boolean addBook(Book book) throws LogicException {
        try {
            return bookDAO.addBook(book);
        } catch (DAOException e) {
            throw new LogicException(e);
        }
    }

    
    public boolean deleteBook(int id) throws LogicException {
        try {
            return bookDAO.deleteBook(id);
        } catch (DAOException e) {
            throw new LogicException(e);
        }
    }   

}
