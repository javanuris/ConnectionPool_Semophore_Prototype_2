package nuris.epam.service;

import nuris.epam.dao.AuthorDao;
import nuris.epam.dao.GenreDao;
import nuris.epam.dao.exception.DaoException;
import nuris.epam.dao.manager.DaoFactory;
import nuris.epam.entity.Author;
import nuris.epam.service.exception.ServiceException;

import java.util.List;

/**
 * Created by User on 20.03.2017.
 */
public class AuthorService {

    public Author findByAuthor(int id) throws ServiceException {
        DaoFactory daoFactory = new DaoFactory();
        Author author;
        AuthorDao authorDao = null;
        try {
            authorDao = (AuthorDao) daoFactory.getDao(daoFactory.typeDao().getAuthorDao());
            author = authorDao.findById(id);
            return author;
        } catch (DaoException e) {
            throw new ServiceException("Cannot findByAuthor", e);
        } finally {
            daoFactory.returnConnect();
        }
    }

    public Author insert(Author author) throws ServiceException {
        DaoFactory daoFactory = new DaoFactory();
        try {
            AuthorDao authorDao = (AuthorDao) daoFactory.getDao(daoFactory.typeDao().getAuthorDao());
            author = authorDao.insert(author);
            return author;
        } catch (DaoException e) {
            throw new ServiceException("Cannot insert", e);
        } finally {
            daoFactory.returnConnect();
        }
    }

    public void update(Author author) throws ServiceException {
        DaoFactory daoFactory = new DaoFactory();
        try {
            AuthorDao authorDao = (AuthorDao) daoFactory.getDao(daoFactory.typeDao().getAuthorDao());
            authorDao.update(author);
        } catch (DaoException e) {
            throw new ServiceException("Cannot update", e);
        } finally {
            daoFactory.returnConnect();
        }
    }
    public void delete(Author author) throws ServiceException {
        DaoFactory daoFactory = new DaoFactory();
        try {
            AuthorDao authorDao = (AuthorDao) daoFactory.getDao(daoFactory.typeDao().getAuthorDao());
            authorDao.delete(author);
        } catch (DaoException e) {
            throw new ServiceException("Cannot delete", e);
        } finally {
            daoFactory.returnConnect();
        }
    }
    public List<Author> getAll() throws ServiceException {
        List<Author> list;
        DaoFactory daoFactory = new DaoFactory();
        try {
            AuthorDao authorDao = (AuthorDao) daoFactory.getDao(daoFactory.typeDao().getAuthorDao());
            list = authorDao.getAll();
            return list;
        } catch (DaoException e) {
            throw new ServiceException("Cannot getAll", e);
        } finally {
            daoFactory.returnConnect();
        }
    }
}
