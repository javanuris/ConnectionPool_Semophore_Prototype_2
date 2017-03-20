package nuris.epam.service;

import nuris.epam.dao.BookDao;
import nuris.epam.dao.exception.DaoException;
import nuris.epam.dao.manager.DaoFactory;
import nuris.epam.entity.Book;
import nuris.epam.service.exception.ServiceException;

/**
 * Created by User on 20.03.2017.
 */
public class BookService {

    AuthorService authorService = new AuthorService();
    PublisherService publisherService = new PublisherService();
    GenreService genreService = new GenreService();

    public Book findById(int id) throws ServiceException{
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

    private void fillBook(Book book) throws ServiceException {
        if(book!=null){
            authorService.findByAuthor(book);
            publisherService.findByPublisher(book);
            genreService.findByGenre(book);
        }
    }
}
