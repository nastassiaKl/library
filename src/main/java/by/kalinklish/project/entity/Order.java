package by.kalinklish.project.entity;


import java.sql.Date;

public class Order extends Entity {
    private User user;
    private Book book;
    private Author author;
    private Date dateBorrow;
    private Date dateReturn;
    private String methodBorrow;

    public Order() {}

    public Order(int id, User user, Book book, Author author, Date dateBorrow, Date dateReturn, String methodBorrow) {
        super(id);
        this.user = user;
        this.book = book;
        this.author = author;
        this.dateBorrow = dateBorrow;
        this.dateReturn = dateReturn;
        this.methodBorrow = methodBorrow;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Date getDateBorrow() {
        return dateBorrow;
    }

    public void setDateBorrow(Date dateBorrow) {
        this.dateBorrow = dateBorrow;
    }

    public Date getDateReturn() {
        return dateReturn;
    }

    public void setDateReturn(Date dateReturn) {
        this.dateReturn = dateReturn;
    }

    public String getMethodBorrow() {
        return methodBorrow;
    }

    public void setMethodBorrow(String methodBorrow) {
        this.methodBorrow = methodBorrow;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Order order = (Order) o;

        if (!user.equals(order.user)) return false;
        if (!book.equals(order.book)) return false;
        if (!author.equals(order.author)) return false;
        if (!dateBorrow.equals(order.dateBorrow)) return false;
        if (!dateReturn.equals(order.dateReturn)) return false;
        return methodBorrow.equals(order.methodBorrow);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + user.hashCode();
        result = 31 * result + book.hashCode();
        result = 31 * result + author.hashCode();
        result = 31 * result + dateBorrow.hashCode();
        result = 31 * result + dateReturn.hashCode();
        result = 31 * result + methodBorrow.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + getId() +
                "user=" + user +
                ", book=" + book +
                ", author=" + author +
                ", dateBorrow=" + dateBorrow +
                ", dateReturn=" + dateReturn +
                ", methodBorrow='" + methodBorrow + '\'' +
                '}';
    }
}
