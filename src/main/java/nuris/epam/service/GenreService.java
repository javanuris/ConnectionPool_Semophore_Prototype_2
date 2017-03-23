package nuris.epam.service;

import nuris.epam.dao.GenreDao;
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
public class GenreService {

    public List<Genre> getAll() throws ServiceException {
        List<Genre> list;
        try {
            try (DaoFactory daoFactory = new DaoFactory()) {
                GenreDao genreDao = (GenreDao) daoFactory.getDao(daoFactory.typeDao().getGenreDao());
                list = genreDao.getAll();
                return list;
            }
        } catch (DaoException e) {
            throw new ServiceException("Cannot update date", e);
        }
    }

    public void findByBook(Book book) throws ServiceException {
        try {
            try (DaoFactory daoFactory = new DaoFactory()) {
                GenreDao genreDao = (GenreDao) daoFactory.getDao(daoFactory.typeDao().getGenreDao());
                book.setGenre(genreDao.findByBook(book));
            }
        } catch (DaoException e) {
            throw new ServiceException("Cannot update date", e);
        }

    }
}
