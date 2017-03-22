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
    private DaoFactory daoFactory;
    private GeneralService generalService;

    public GenreService() {}

    public GenreService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
        generalService = new GeneralService(new TypeDao().getGenreDao(), daoFactory);
    }

    public Genre findByGenre(int id) throws ServiceException {
        DaoFactory daoFactory = new DaoFactory();
        Genre genre;
        try {
            GenreDao genreDao = (GenreDao) daoFactory.getDao(daoFactory.typeDao().getGenreDao());
            genre = (Genre) generalService.findById(id);
            return genre;
        } catch (DaoException e) {
            throw new ServiceException("Cannot find by Genre Id", e);
        } finally {
            daoFactory.returnConnect();
        }
    }

    public Genre insert(Genre genre) throws ServiceException {
        DaoFactory daoFactory = new DaoFactory();
        try {
            if (generalService == null) {
                GenreDao genreDao = (GenreDao) daoFactory.getDao(daoFactory.typeDao().getGenreDao());
                genre = genreDao.insert(genre);
            } else {
                genre = (Genre) generalService.insert(genre);
                return genre;
            }
        } catch (DaoException e) {
            e.printStackTrace();
        } finally {
            daoFactory.returnConnect();
        }
        return genre;
    }

    public void update(Genre genre) throws ServiceException {
        generalService.update(genre);
    }

    public void delete(Genre genre) throws ServiceException {
        generalService.delete(genre);
    }

    public List<Genre> getAll() throws ServiceException {
        List<Genre> list;
        DaoFactory daoFactory = new DaoFactory();
        try {
            GenreDao genreDao = (GenreDao) daoFactory.getDao(daoFactory.typeDao().getGenreDao());
            list = genreDao.getAll();
            return list;
        } catch (DaoException e) {
            throw new ServiceException("Cannot getAll", e);
        } finally {
            daoFactory.returnConnect();
        }
    }

    public void findByGenre(Book book) throws ServiceException {
        DaoFactory daoFactory = new DaoFactory();
        try {
            GenreDao genreDao = (GenreDao) daoFactory.getDao(daoFactory.typeDao().getGenreDao());
            book.setGenre(genreDao.findByBook(book));
        } catch (DaoException e) {
            throw new ServiceException("Cannot getBook", e);
        } finally {
            daoFactory.returnConnect();
        }
    }

}
