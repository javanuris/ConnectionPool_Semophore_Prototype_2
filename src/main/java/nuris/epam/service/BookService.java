package nuris.epam.service;

import nuris.epam.dao.BookDao;
import nuris.epam.dao.exception.DaoException;
import nuris.epam.dao.manager.DaoFactory;
import nuris.epam.dao.manager.TypeDao;

import nuris.epam.entity.Book;
import nuris.epam.entity.Genre;
import nuris.epam.service.exception.ServiceException;

import java.util.List;

/**
 * Created by User on 20.03.2017.
 */
public class BookService {

    private AuthorService authorService = new AuthorService();
    private PublisherService publisherService = new PublisherService();
    private GenreService genreService = new GenreService();
    private GeneralService generalService = new GeneralService(TypeDao.getInstance().getBookDao());

    public Book findById(int id) throws ServiceException {
        Book book;
        book = (Book) generalService.findById(id);
        fillBook(book);
        return book;
    }

    public Book insert(Book book) throws ServiceException {
        book = (Book) generalService.insert(book);
        return book;
    }

    public void update(Book book) throws ServiceException {
        generalService.update(book);
    }

    public void delete(Book book) throws ServiceException {
        generalService.delete(book);
    }

    public int getBookCount() throws ServiceException {
        DaoFactory daoFactory = new DaoFactory();
        try {
            BookDao bookDao = (BookDao) daoFactory.getDao(daoFactory.typeDao().getBookDao());
            int count = bookDao.getBookCount();
            return count;
        } catch (DaoException e) {
            throw new ServiceException("Cannot getCount", e);
        } finally {
            daoFactory.returnConnect();
        }
    }

    public List<Book> getLimitBookByGenre(Genre genre, int start, int count) throws ServiceException {
        List<Book> list;
        DaoFactory daoFactory = new DaoFactory();
        try {
            BookDao bookDao = (BookDao) daoFactory.getDao(daoFactory.typeDao().getBookDao());
            list = bookDao.getLimitBookByGenre(genre, start, count);
            for (Book book : list) {
                fillBook(book);
            }
            return list;
        } catch (DaoException e) {
            throw new ServiceException("Cannot getLimitBook", e);
        } finally {
            daoFactory.returnConnect();
        }

    }

    public List<Book> getLimitBook(int start, int count) throws ServiceException {
        List<Book> list;
        DaoFactory daoFactory = new DaoFactory();
        try {
            BookDao bookDao = (BookDao) daoFactory.getDao(daoFactory.typeDao().getBookDao());
            list = bookDao.getLimitBook(start, count);
            for (Book book : list) {
                fillBook(book);
            }
            return list;
        } catch (DaoException e) {
            throw new ServiceException("Cannot getLimitBook", e);
        } finally {
            daoFactory.returnConnect();
        }
    }

    private void fillBook(Book book) throws ServiceException {
        if (book != null) {
            authorService.findByAuthor(book);
            publisherService.findByPublisher(book);
            genreService.findByGenre(book);
        }
    }
}
