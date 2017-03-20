package nuris.epam.service;

import nuris.epam.dao.BookDao;
import nuris.epam.dao.exception.DaoException;
import nuris.epam.dao.manager.DaoFactory;
import nuris.epam.entity.Book;
import nuris.epam.entity.Genre;
import nuris.epam.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 20.03.2017.
 */
public class BookService {

    private AuthorService authorService = new AuthorService();
    private PublisherService publisherService = new PublisherService();
    private GenreService genreService = new GenreService();

    public Book findById(int id) throws ServiceException {
        DaoFactory daoFactory = new DaoFactory();
        Book book;
        try {
            BookDao bookDao = (BookDao) daoFactory.getDao(daoFactory.typeDao().getBookDao());
            book = bookDao.findById(id);
            fillBook(book);
            return book;
        } catch (DaoException e) {
            throw new ServiceException("Cannot findByAuthor", e);
        } finally {
            daoFactory.returnConnect();
        }
    }

    public int insert(Book book) throws ServiceException {
        DaoFactory daoFactory = new DaoFactory();
        try {
            BookDao bookDao = (BookDao) daoFactory.getDao(daoFactory.typeDao().getBookDao());
            book = bookDao.insert(book);
            return book.getId();
        } catch (DaoException e) {
            throw new ServiceException("Cannot insert", e);
        } finally {
            daoFactory.returnConnect();
        }
    }

    public void update(Book book) throws ServiceException {
        DaoFactory daoFactory = new DaoFactory();
        try {
            BookDao bookDao = (BookDao) daoFactory.getDao(daoFactory.typeDao().getBookDao());
            bookDao.update(book);
        } catch (DaoException e) {
            throw new ServiceException("Cannot update", e);
        } finally {
            daoFactory.returnConnect();
        }
    }

    public void delete(Book book) throws ServiceException {
        DaoFactory daoFactory = new DaoFactory();
        try {
            BookDao bookDao = (BookDao) daoFactory.getDao(daoFactory.typeDao().getBookDao());
            bookDao.delete(book);
        } catch (DaoException e) {
            throw new ServiceException("Cannot delete", e);
        } finally {
            daoFactory.returnConnect();
        }
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
    public  List<Book> getLimitBookByGenre(Genre genre , int start , int count) throws ServiceException{
        List<Book> list;
        DaoFactory daoFactory = new DaoFactory();
        try {
            BookDao bookDao = (BookDao) daoFactory.getDao(daoFactory.typeDao().getBookDao());
            list = bookDao.getLimitBookByGenre(genre ,start , count);
            for(Book book : list) {
                fillBook(book);
            }
            return list;
        } catch (DaoException e) {
            throw new ServiceException("Cannot getLimitBook", e);
        } finally {
            daoFactory.returnConnect();
        }

    }

    public List<Book> getLimitBook(int start , int count) throws ServiceException{
        List<Book> list;
        DaoFactory daoFactory = new DaoFactory();
        try {
            BookDao bookDao = (BookDao) daoFactory.getDao(daoFactory.typeDao().getBookDao());
            list = bookDao.getLimitBook(start , count);
            for(Book book : list) {
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
