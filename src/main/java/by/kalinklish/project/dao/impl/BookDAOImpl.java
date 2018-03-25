package by.kalinklish.project.dao.impl;

import by.kalinklish.project.dao.BookDAO;
import by.kalinklish.project.dao.pool.ConnectionPool;
import by.kalinklish.project.dao.pool.ProxyConnection;
import by.kalinklish.project.entity.Author;
import by.kalinklish.project.entity.Book;
import by.kalinklish.project.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookDAOImpl implements BookDAO {
    private final static Logger LOGGER = LogManager.getLogger(BookDAOImpl.class);

    private final static String INSERT_BOOK = "INSERT INTO books(isbn, tittle, date_edition, place_edition, publisher, number_copies, image_book) VALUES(?,?,?,?,?,?,?)";
    private final static String SELECT_BOOKS_AND_AUTHORS = "SELECT books.id_book, isbn, tittle, authors.id_author, surname, name, middle_name, country, name_genre, date_edition, place_edition, publisher, number_copies, image_book " +
            "FROM library.books " +
            "JOIN library.book_author ON books.id_book = book_author.id_book " +
            "JOIN library.authors ON book_author.id_author = authors.id_author " +
            "JOIN library.book_genre ON book_genre.id_book = books.id_book " +
            "JOIN library.genres ON genres.id_genre = book_genre.id_genre;";
    private final static String SELECT_BOOK_BY_ID = "SELECT books.id_book, isbn, tittle, authors.id_author, surname, name, middle_name, country, date_edition, place_edition, publisher, number_copies, image_book " +
            "FROM library.books " +
            "JOIN library.book_author ON books.id_book = book_author.id_book " +
            "JOIN library.authors ON book_author.id_author = authors.id_author " +
            "WHERE books.id_book = ?;";
    private final static String SELECT_BOOKS_BY_GENRE = "SELECT books.id_book, authors.id_author," +
            " isbn, tittle, surname, name, middle_name, country, date_edition, place_edition, publisher, number_copies, image_book " +
            "FROM library.books JOIN library.book_author ON book_author.id_book = books.id_book " +
            "JOIN library.authors ON authors.id_author = book_author.id_author " +
            "JOIN library.book_genre ON book_genre.id_book = books.id_book " +
            "JOIN library.genres ON genres.id_genre = book_genre.id_genre " +
            "WHERE name_genre = ?";
    private final static String SELECT_FIND_BOOKS_BY_TITTLE = "SELECT books.id_book, authors.id_author, isbn, tittle, surname, name, middle_name, country, name_genre, date_edition, place_edition, publisher, number_copies, image_book " +
            "FROM library.books JOIN library.book_genre ON book_genre.id_book = books.id_book " +
            "JOIN library.genres ON book_genre.id_genre = genres.id_genre " +
            "JOIN library.book_author ON books.id_book = book_author.id_book " +
            "JOIN library.authors ON authors.id_author = book_author.id_author " +
            "WHERE tittle = ?;";
    private final static String SELECT_FIND_BOOKS_BY_AUTHOR = "SELECT books.id_book, authors.id_author, isbn, tittle, surname, name, middle_name, country, name_genre, date_edition, place_edition, publisher, number_copies, image_book " +
            "FROM library.books JOIN library.book_genre ON book_genre.id_book = books.id_book " +
            "JOIN library.genres ON book_genre.id_genre = genres.id_genre " +
            "JOIN library.book_author ON books.id_book = book_author.id_book " +
            "JOIN library.authors ON authors.id_author = book_author.id_author " +
            "WHERE surname = ?;";
    private final static String SELECT_ALL_GENRES = "SELECT id_genre, name_genre FROM library.genres;";
    private final static String DELETE_BOOK = "DELETE FROM library.books WHERE id_book = ?";
    private final static String UPDATE_BOOK_BY_ID = "UPDATE library.books " +
            "JOIN library.book_author ON books.id_book = book_author.id_book " +
            "JOIN library.authors ON book_author.id_author = authors.id_author " +
            "JOIN library.book_genre ON books.id_book = book_genre.id_book " +
            "JOIN library.genres ON book_genre.id_genre = genres.id_genre " +
            "SET isbn = ?, tittle = ?, surname = ?, name = ?, middle_name = ?, date_edition = ?, place_edition = ?, publisher = ?, number_copies = ?, image_book = ? " +
            "WHERE books.id_book = ?;";
    private final static String UPDATE_BOOK_AND_GENRE = "UPDATE library.book_genre SET id_genre = ? WHERE id_book = ?;";
    private final static String GET_LAST_ID = "(SELECT id_book FROM library.books ORDER BY id_book desc limit 1) " +
            "UNION (SELECT id_author FROM library.authors ORDER BY id_author desc limit 1)";
    private final static String INSERT_BOOK_AND_AUTHOR = "INSERT INTO library.book_author(id_book, id_author) VALUES(?,?);";
    private final static String INSERT_BOOK_AND_GENRE = "INSERT INTO library.book_genre(id_book, id_genre) VALUES(?,?);";
    private final static String GET_ID_GENRE = "SELECT id_genre FROM library.genres WHERE name_genre = ?";
    private final static String GET_GENRE_BY_ID_BOOK = "SELECT name_genre FROM library.books " +
            "JOIN library.book_genre ON books.id_book = book_genre.id_book " +
            "JOIN library.genres ON book_genre.id_genre = genres.id_genre WHERE books.id_book = ?;";
    private final static String COUNT_BOOKS = "SELECT COUNT(*) FROM library.books;";

    @Override
    public List<Book> getBooksAndAuthors() throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        Statement statement = null;
        ResultSet resultSet;
        Book book;
        List<Book> listBooks = new ArrayList<>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_BOOKS_AND_AUTHORS);
            while (resultSet.next()) {
                book = getBooksFromResultSet(resultSet);
                listBooks.add(book);
            }
            return listBooks;
        } catch (SQLException e) {
            throw new DAOException("Error get all book" + e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    LOGGER.error("Error closing statement", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error("Error closing connection", e);
                }
            }
        }
    }

    @Override
    public Book getBookById(int id) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet;
        Book book = new Book();
        try {
            statement = connection.prepareStatement(SELECT_BOOK_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                book = getBooksFromResultSet(resultSet);
            }
            return book;
        } catch (SQLException e) {
            throw new DAOException("Error get found book by id" + e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    LOGGER.error("Error closing statement", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error("Error closing connection", e);
                }
            }
        }
    }

    @Override
    public Map<Integer, String> getAllGenres() throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        Statement statement = null;
        ResultSet resultSet;
        Map<Integer, String> mapGenres = new HashMap<>();
        int idGenre;
        String nameGenre;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_ALL_GENRES);
            while (resultSet.next()) {
                idGenre = resultSet.getInt("id_genre");
                nameGenre = resultSet.getString("name_genre");
                mapGenres.put(idGenre, nameGenre);
            }
            return mapGenres;
        } catch (SQLException e) {
            throw new DAOException("Error get all genres" + e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    LOGGER.error("Error closing statement", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error("Error closing connection", e);
                }
            }
        }
    }

    @Override
    public List<Book> getFoundBooksByGenre(String genre) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet;
        Book book;
        List<Book> listBooks = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SELECT_BOOKS_BY_GENRE);
            statement.setString(1, genre);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                book = getBooksFromResultSet(resultSet);
                listBooks.add(book);
            }
            return listBooks;
        } catch (SQLException e) {
            throw new DAOException("Error found book by genre" + e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    LOGGER.error("Error closing statement", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error("Error closing connection", e);
                }
            }
        }
    }

    @Override
    public boolean addBook(Book book) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(INSERT_BOOK);
            statement.setString(1, book.getIsbn());
            statement.setString(2, book.getTittle());
            statement.setDate(3, book.getDateEdition());
            statement.setString(4, book.getPlaceEdition());
            statement.setString(5, book.getPublisher());
            statement.setInt(6, book.getNumberCopies());
            statement.setString(7, book.getImage());
            statement.executeUpdate();

            statement = connection.prepareStatement(GET_LAST_ID);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int idBook = resultSet.getInt(1);
                resultSet.next();
                int idAuthor = resultSet.getInt(1);

                statement = connection.prepareStatement(INSERT_BOOK_AND_AUTHOR);
                statement.setInt(1, idBook);
                statement.setInt(2, idAuthor);
                statement.executeUpdate();
            }
            return true;
        } catch (SQLException e) {
            throw new DAOException("Error add book " + e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    LOGGER.error("Error closing statement", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error("Error closing connection", e);
                }
            }
        }
    }

    @Override
    public void addBookAndGenre(String genre) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        int idBook = 0;
        int idGenre = 0;
        try {
            statement = connection.prepareStatement(GET_ID_GENRE);
            statement.setString(1, genre);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                idGenre = resultSet.getInt(1);
            }

            statement = connection.prepareStatement(GET_LAST_ID);
            ResultSet resultSet1 = statement.executeQuery();
            if (resultSet1.next()) {
                idBook = resultSet1.getInt(1);
            }

            statement = connection.prepareStatement(INSERT_BOOK_AND_GENRE);
            statement.setInt(1, idBook);
            statement.setInt(2, idGenre);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error add book " + e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    LOGGER.error("Error closing statement", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error("Error closing connection", e);
                }
            }
        }
    }

    @Override
    public void updateBookAndGenre(String genre, int idBook) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        int idGenre = 0;
        try {

            statement = connection.prepareStatement(GET_ID_GENRE);
            statement.setString(1, genre);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                idGenre = resultSet.getInt(1);
            }

            statement = connection.prepareStatement(UPDATE_BOOK_AND_GENRE);
            statement.setInt(1, idGenre);
            statement.setInt(2, idBook);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error update genre" + e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    LOGGER.error("Error closing statement", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error("Error closing connection", e);
                }
            }
        }
    }


    @Override
    public List<Book> getFoundBooksByTittle(String tittle) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet;
        Book book;
        List<Book> listBooks = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SELECT_FIND_BOOKS_BY_TITTLE);
            statement.setString(1, tittle);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                book = getBooksFromResultSet(resultSet);
                listBooks.add(book);
            }
            return listBooks;
        } catch (SQLException e) {
            throw new DAOException("Error found book by tittle" + e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    LOGGER.error("Error closing statement", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error("Error closing connection", e);
                }
            }
        }
    }

    @Override
    public List<Book> getFoundBooksByAuthor(String author) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet;
        Book book;
        List<Book> listBooks = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SELECT_FIND_BOOKS_BY_AUTHOR);
            statement.setString(1, author);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                book = getBooksFromResultSet(resultSet);
                listBooks.add(book);
            }
            return listBooks;
        } catch (SQLException e) {
            throw new DAOException("Error found book by author" + e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    LOGGER.error("Error closing statement", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error("Error closing connection", e);
                }
            }
        }
    }

    @Override
    public int countBooks() throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        Statement statement = null;
        ResultSet resultSet;
        int countBooks = 0;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(COUNT_BOOKS);
            if (resultSet.next()) {
                countBooks = resultSet.getInt(1);
            }
            return countBooks;
        } catch (SQLException e) {
            throw new DAOException("Error to count books" + e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    LOGGER.error("Error closing statement", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error("Error closing connection", e);
                }
            }
        }
    }

    @Override
    public boolean deleteBook(int id) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETE_BOOK);
            statement.setInt(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DAOException("Error delete the book" + e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    LOGGER.error("Error closing statement", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error("Error closing connection", e);
                }
            }
        }
    }

    @Override
    public boolean updateBook(Book book) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(UPDATE_BOOK_BY_ID);
            statement.setString(1, book.getIsbn());
            statement.setString(2, book.getTittle());
            statement.setString(3, book.getAuthor().getSurname());
            statement.setString(4, book.getAuthor().getName());
            statement.setString(5, book.getAuthor().getMiddleName());
            statement.setDate(6, book.getDateEdition());
            statement.setString(7, book.getPlaceEdition());
            statement.setString(8, book.getPublisher());
            statement.setInt(9, book.getNumberCopies());
            statement.setString(10, book.getImage());
            statement.setInt(11, book.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DAOException("Error update book" + e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    LOGGER.error("Error closing statement", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error("Error closing connection", e);
                }
            }
        }
    }

    private Book getBooksFromResultSet(ResultSet resultSet) throws SQLException, DAOException {
        Book book = new Book();
        Author author = new Author();
        List<String> listGenres;
        book.setId(resultSet.getInt("id_book"));
        book.setIsbn(resultSet.getString("isbn"));
        book.setTittle(resultSet.getString("tittle"));
        listGenres = getGenres(book.getId());
        book.setGenres(listGenres);
        author.setId(resultSet.getInt("id_author"));
        author.setSurname(resultSet.getString("surname"));
        author.setName(resultSet.getString("name"));
        author.setMiddleName(resultSet.getString("middle_name"));
        author.setCountryBirth(resultSet.getString("country"));
        book.setAuthor(author);
        book.setDateEdition(resultSet.getDate("date_edition"));
        book.setPlaceEdition(resultSet.getString("place_edition"));
        book.setPublisher(resultSet.getString("publisher"));
        book.setNumberCopies(resultSet.getInt("number_copies"));
        book.setImage(resultSet.getString("image_book"));
        return book;
    }

    private List<String> getGenres(int idBook) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<String> listGenres = new ArrayList<>();
        String genre;
        try {
            statement = connection.prepareStatement(GET_GENRE_BY_ID_BOOK);
            statement.setInt(1, idBook);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                genre = resultSet.getString("name_genre");
                listGenres.add(genre);
            }
            return listGenres;
        } catch (SQLException e) {
            throw new DAOException("Error get genres by id book" + e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    LOGGER.error("Error closing statement", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error("Error closing connection", e);
                }
            }
        }
    }
}
