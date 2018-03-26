package by.kalinklish.project.dao;

import by.kalinklish.project.entity.Author;
import by.kalinklish.project.exception.DAOException;
import by.radomskaya.project.entity.Author;
import by.radomskaya.project.exception.DAOException;

import java.util.List;

public interface AuthorDAO {
    List<Author> getAllAuthors() throws DAOException;
    Author getAuthorById(int id) throws DAOException;
    boolean addAuthor(Author author) throws DAOException;
    boolean deleteAuthor(int id) throws  DAOException;
    boolean updateAuthor(Author author) throws DAOException;
}
