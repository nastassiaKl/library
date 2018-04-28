package by.kalinklish.project.dao;

import by.kalinklish.project.dao.factory.DAOFactory;
import by.kalinklish.project.entity.Author;
import by.kalinklish.project.entity.Book;
import by.kalinklish.project.exception.DAOException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class BookDAOTest {
    private static BookDAO bookDAO;
    private static AuthorDAO authorDAO;
    private static Author author;
    private static Book book;
    private static Book editBook;
    private static List<String> genres;
    private static List<Book> books;

    @BeforeClass
    public static void init() {
        bookDAO = DAOFactory.getInstance().getBookDAO();
        authorDAO = DAOFactory.getInstance().getAuthorDAO();
        author = new Author(50, "Виктор", "Гюго", "-", "Франция");
        genres = new ArrayList<>();
        genres.add("Роман");
        book = new Book(50, "1478523698521", "Человек, который смеется", author, genres, new Date(2011, 11, 21), "Франция", "АСТ", 5, "");
        editBook = new Book(27, "1478524562369", "Человек, который смеется", author, genres, new Date(2011, 11, 21), "Франция", "АСТ", 10, "");
    }

    @Test
    public void testGetAllBooks() throws DAOException {
        List<Book> books = bookDAO.getBooksAndAuthors();
        Assert.assertFalse(books.isEmpty());
    }

    @Test
    public void testGetBookById() throws DAOException {
        Assert.assertNotNull(bookDAO.getBookById(book.getId()));
    }

    @Test
    public void testAddBook() throws DAOException {
        Assert.assertTrue(authorDAO.addAuthor(author));
        Assert.assertTrue(bookDAO.addBook(book));
        bookDAO.addBookAndGenre(getGenre(genres));
    }

    @Test
    public void teasFoundBookByTittle() throws DAOException {
        books = bookDAO.getFoundBooksByTittle(book.getTittle());
        Assert.assertNotNull(books);
    }

    @Test
    public void teasFoundBookByGenre() throws DAOException {
        books = bookDAO.getFoundBooksByTittle(book.getGenres().toString());
        Assert.assertNotNull(books);
    }

    @Test
    public void teasFoundBookByAuthor() throws DAOException {
        books = bookDAO.getFoundBooksByTittle(book.getAuthor().getSurname());
        Assert.assertNotNull(books);
    }

    @Test
    public void testDeleteBook() throws DAOException {
        Assert.assertTrue(bookDAO.deleteBook(book.getId()));
        Assert.assertTrue(authorDAO.deleteAuthor(author.getId()));
    }

    @Test
    public void testEditBook() throws DAOException {
        Assert.assertTrue(bookDAO.updateBook(editBook));
    }

    private static String getGenre(List<String> genres) {
        String genre = new String();
        for (int i = 0; i < genres.size(); i++) {
            genre = genres.get(i);
        }
        return  genre;
    }
}
