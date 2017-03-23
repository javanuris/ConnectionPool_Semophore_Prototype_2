package nuris.epam.service;


import nuris.epam.dao.AuthorDao;
import nuris.epam.dao.exception.DaoException;
import nuris.epam.dao.manager.DaoFactory;
import nuris.epam.entity.Author;
import nuris.epam.entity.Book;
import nuris.epam.service.exception.ServiceException;

/**
 * Created by User on 20.03.2017.
 */
public class AuthorService {

    public void update(Author author) throws ServiceException {
        try {
            try (DaoFactory daoFactory = new DaoFactory()) {
                AuthorDao authorDao = (AuthorDao) daoFactory.getDao(daoFactory.typeDao().getAuthorDao());
                authorDao.update(author);
            }
        } catch (DaoException e) {
            throw new ServiceException("Cannot update date", e);
        }
    }

    public void findByBook(Book book) throws ServiceException {
        try {
            try (DaoFactory daoFactory = new DaoFactory()) {
                AuthorDao authorDao = (AuthorDao) daoFactory.getDao(daoFactory.typeDao().getAuthorDao());
                book.setAuthor(authorDao.findByBook(book));
            }
        } catch (DaoException e) {
            throw new ServiceException("Cannot update date", e);
        }
    }


}
