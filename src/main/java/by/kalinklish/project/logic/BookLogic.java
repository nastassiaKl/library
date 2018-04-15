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

    //]
    public List<Book> getBooksWithPages(int noPage) throws LogicException {
        List<Book> listBooks;

        try {
            listBooks = getBooks();
            int step = (noPage - 1) * QUANTITY_DATA_ON_PAGE;
            if (step + QUANTITY_DATA_ON_PAGE >= listBooks.size()) {
                listBooks = listBooks.subList(step, listBooks.size());
            } else {
                listBooks = listBooks.subList(step, step + QUANTITY_DATA_ON_PAGE);
            }

        } catch (LogicException e) {
            throw new LogicException(e);
        }

        return listBooks;
    }

    public int getNoOfPages() throws LogicException {
        int numberOfPages;
        try {
            numberOfPages = bookDAO.countBooks();
        } catch (DAOException e) {
            throw new LogicException(e);
        }
        return (int) Math.ceil(numberOfPages * 1.0 / QUANTITY_DATA_ON_PAGE);

    }

    public Book getBookById(int id) throws LogicException {
        try {
            return bookDAO.getBookById(id);
        } catch (DAOException e) {
            throw new LogicException(e);
        }
    }

    public List<Book> findBooksByGenre(String genre) throws LogicException {
        try {
            return bookDAO.getFoundBooksByGenre(genre);
        } catch (DAOException e) {
            throw new LogicException(e);
        }
    }


    public boolean addBook(Book book) throws LogicException {
        try {
            return bookDAO.addBook(book);
        } catch (DAOException e) {
            throw new LogicException(e);
        }
    }

     public boolean updateBook(Book book) throws LogicException {
        try {
            return bookDAO.updateBook(book);
        } catch (DAOException e) {
            throw new LogicException(e);
        }
    }

     public List<Book> getFoundBooksByTittle(String tittle) throws LogicException {
        try {
            return bookDAO.getFoundBooksByTittle(tittle);
        } catch (DAOException e) {
            throw new LogicException(e);
        }
    }

    public List<Book> getFoundBooksByAuthor(String author) throws LogicException {
        try {
            return bookDAO.getFoundBooksByAuthor(author);
        } catch (DAOException e) {
            throw new LogicException(e);
        }
    }

    public Map<Integer, String> getAllGenres() throws LogicException {
        try {
            return bookDAO.getAllGenres();
        } catch (DAOException e) {
            throw new LogicException(e);
        }
    }

    public void addBookAndGenre(String[] genres) throws LogicException {
        for (int i = 0; i < genres.length; i++) {
            try {
                bookDAO.addBookAndGenre(genres[i]);
            } catch (DAOException e) {
                throw new LogicException(e);
            }
        }
    }

    public void updateBookAndGenre(String[] genres, int idBook) throws LogicException {
        for (int i = 0; i < genres.length; i++) {
            try {
                bookDAO.updateBookAndGenre(genres[i], idBook);
            } catch (DAOException e) {
                throw new LogicException(e);
            }
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
