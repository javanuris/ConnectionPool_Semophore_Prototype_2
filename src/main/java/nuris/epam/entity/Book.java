package nuris.epam.entity;

import sun.java2d.windows.GDIWindowSurfaceData;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 14.03.2017.
 */
public class Book extends BaseEntity {
    private Author author;
    private Genre genre;
    private Publisher publisher;
    private String name;
    private String isbn;
    private Date date;

    public Book(){
        author = new Author();
        genre = new Genre();
        publisher = new Publisher();
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Author getAuthor() {
        return author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return getId() + "/" + name + "/" + date  + "/" + isbn +"/"+ genre+"/"+author+"/"+publisher;
    }
}
