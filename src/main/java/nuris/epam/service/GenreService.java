package nuris.epam.service;

import nuris.epam.dao.GenreDao;
import nuris.epam.dao.exception.DaoException;
import nuris.epam.dao.manager.DaoFactory;
import nuris.epam.entity.Genre;
import nuris.epam.service.exception.ServiceException;

import java.util.List;

/**
 * Created by User on 20.03.2017.
 */
public class GenreService {

    public Genre findByGenre(int id) throws ServiceException {
        DaoFactory daoFactory = new DaoFactory();
        Genre genre;
        try {
            GenreDao genreDao = (GenreDao) daoFactory.getDao(daoFactory.typeDao().getGenreDao());
            genre = genreDao.findById(id);
            return genre;

        } catch (DaoException e) {
            throw new ServiceException("Cannot findByGenre", e);
        } finally {
            daoFactory.returnConnect();
        }
    }

    public Genre insert(Genre genre) throws ServiceException {
        DaoFactory daoFactory = new DaoFactory();

        try {
            GenreDao genreDao = (GenreDao) daoFactory.getDao(daoFactory.typeDao().getGenreDao());
            genre = genreDao.insert(genre);
            return genre;
        } catch (DaoException e) {
            throw new ServiceException("Cannot insert", e);
        } finally {
            daoFactory.returnConnect();
        }
    }

    public void update(Genre genre) throws ServiceException {
        DaoFactory daoFactory = new DaoFactory();
        try {
            GenreDao genreDao = (GenreDao) daoFactory.getDao(daoFactory.typeDao().getGenreDao());
            genreDao.update(genre);
        } catch (DaoException e) {
            throw new ServiceException("Cannot update", e);
        } finally {
            daoFactory.returnConnect();
        }

    }

    public void delete(Genre genre) throws ServiceException {
        DaoFactory daoFactory = new DaoFactory();
        try {
            GenreDao genreDao = (GenreDao) daoFactory.getDao(daoFactory.typeDao().getGenreDao());
            genreDao.delete(genre);
        } catch (DaoException e) {
            throw new ServiceException("Cannot delete", e);
        } finally {
            daoFactory.returnConnect();
        }

    }

    public List<Genre> getAll() throws ServiceException {
        List<Genre> list;
        DaoFactory daoFactory = new DaoFactory();
        try {
            GenreDao genreDao = (GenreDao) daoFactory.getDao(daoFactory.typeDao().getGenreDao());
            list = genreDao.getAll();
            return list;
        } catch (DaoException e) {
            throw new ServiceException("Cannot delete", e);
        } finally {
            daoFactory.returnConnect();
        }
    }


}
