package nuris.epam.service;

import nuris.epam.dao.AuthorDao;
import nuris.epam.dao.BookDao;
import nuris.epam.dao.GenreDao;
import nuris.epam.dao.exception.DaoException;
import nuris.epam.dao.manager.DaoFactory;
import nuris.epam.entity.Book;
import nuris.epam.service.exception.ServiceException;

/**
 * Created by User on 20.03.2017.
 */
public class BookService {

    public void registerBook(Book book) throws ServiceException {
        try (DaoFactory daoFactory = new DaoFactory()) {
            try {
                AuthorDao authorDao = (AuthorDao) daoFactory.getDao(daoFactory.typeDao().getAuthorDao());
                BookDao bookDao = (BookDao) daoFactory.getDao(daoFactory.typeDao().getBookDao());
                daoFactory.startTransaction();
                authorDao.insert(book.getAuthor());
                bookDao.insert(book);
                daoFactory.commitTransaction();
            } catch (DaoException e) {
                try {
                    daoFactory.rollbackTransaction();
                } catch (DaoException e1) {
                    throw new ServiceException("doesn't work rollback", e);
                }
                throw new ServiceException("cannot insert date to base", e);
            }
        }

    }

    public Book findById(int id) throws ServiceException {
        try (DaoFactory daoFactory = new DaoFactory()) {
            try {
                BookDao bookDao = (BookDao) daoFactory.getDao(daoFactory.typeDao().getBookDao());
                Book book = bookDao.findById(id);
                fillBook(book);
                return book;
            } catch (DaoException e) {
                try {
                    daoFactory.rollbackTransaction();
                } catch (DaoException e1) {
                    throw new ServiceException("doesn't work rollback", e);
                }
                throw new ServiceException("cannot insert date to base", e);
            }
        }
    }

    private void fillBook(Book book) throws ServiceException {
        try {
            if (book != null) {
                try (DaoFactory daoFactory = new DaoFactory()) {
                    AuthorDao authorDao = (AuthorDao) daoFactory.getDao(daoFactory.typeDao().getAuthorDao());
                    GenreDao genreDao = (GenreDao) daoFactory.getDao(daoFactory.typeDao().getGenreDao());
                    book.setAuthor(authorDao.findByBook(book));
                    book.setGenre(genreDao.findByBook(book));
                }
            }
        } catch (DaoException e) {
            throw new ServiceException("Can't fill book", e);
        }
    }

}
