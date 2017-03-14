package nuris.epam.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 14.03.2017.
 */
public class Book extends BaseEntity {
    private List<Author> authors = new ArrayList<>();
    private List<Genre> genres = new ArrayList<>();
    private List<Publisher> publishers = new ArrayList<>();

    private int name;
    private int isbn;
    private Date date;

    public void setAuthor(Author author) {
        authors.add(author);
    }

    public void setGenre(Genre genre) {
        genres.add(genre);
    }

    public void setPublisher(Publisher publisher) {
        publishers.add(publisher);
    }

    public Author getAuthor(int i) {
        return authors.get(i);
    }

    public Genre getGenre(int i) {
        return genres.get(i);
    }

    public Publisher getPublisher(int i) {
        return publishers.get(i);
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
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
        return getId() + "/" + name + "/" + date + "/" + isbn;
    }
}
