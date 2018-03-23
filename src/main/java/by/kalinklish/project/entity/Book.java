package by.kalinklish.project.entity;

import java.sql.Date;
import java.util.List;

public class Book extends Entity {
    private String isbn;
    private String tittle;
    private Author author;
    private List<String> genres;
    private Date dateEdition;
    private String placeEdition;
    private String publisher;
    private int numberCopies;
    private String image;

    public Book() {}

    public Book(int id) {
        super(id);
    }

    public Book(int id, String isbn, String tittle, Author author, List<String> genres, Date dateEdition, String placeEdition, String publisher, int numberCopies, String image) {
        super(id);
        this.isbn = isbn;
        this.tittle = tittle;
        this.author = author;
        this.genres = genres;
        this.dateEdition = dateEdition;
        this.placeEdition = placeEdition;
        this.publisher = publisher;
        this.numberCopies = numberCopies;
        this.image = image;
    }

    public Book(String isbn, String tittle, Author author, Date dateEdition, String placeEdition, String publisher, int numberCopies, String image) {
        this.isbn = isbn;
        this.tittle = tittle;
        this.author = author;
        this.dateEdition = dateEdition;
        this.placeEdition = placeEdition;
        this.publisher = publisher;
        this.numberCopies = numberCopies;
        this.image = image;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public Date getDateEdition() {
        return dateEdition;
    }

    public void setDateEdition(Date dateEdition) {
        this.dateEdition = dateEdition;
    }

    public String getPlaceEdition() {
        return placeEdition;
    }

    public void setPlaceEdition(String placeEdition) {
        this.placeEdition = placeEdition;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getNumberCopies() {
        return numberCopies;
    }

    public void setNumberCopies(int numberCopies) {
        this.numberCopies = numberCopies;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Book book = (Book) o;

        if (numberCopies != book.numberCopies) return false;
        if (!isbn.equals(book.isbn)) return false;
        if (!tittle.equals(book.tittle)) return false;
        if (!author.equals(book.author)) return false;
        if (!genres.equals(book.genres)) return false;
        if (!dateEdition.equals(book.dateEdition)) return false;
        if (!placeEdition.equals(book.placeEdition)) return false;
        if (!publisher.equals(book.publisher)) return false;
        return image.equals(book.image);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + isbn.hashCode();
        result = 31 * result + tittle.hashCode();
        result = 31 * result + author.hashCode();
        result = 31 * result + genres.hashCode();
        result = 31 * result + dateEdition.hashCode();
        result = 31 * result + placeEdition.hashCode();
        result = 31 * result + publisher.hashCode();
        result = 31 * result + numberCopies;
        result = 31 * result + image.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + getId() +
                ", isbn='" + isbn + '\'' +
                ", tittle='" + tittle + '\'' +
                ", author=" + author +
                ", genres=" + genres +
                ", dateEdition=" + dateEdition +
                ", placeEdition='" + placeEdition + '\'' +
                ", publisher='" + publisher + '\'' +
                ", numberCopies=" + numberCopies +
                ", image='" + image + '\'' +
                '}';
    }
}
