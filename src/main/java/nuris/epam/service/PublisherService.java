package nuris.epam.service;

import nuris.epam.dao.PublisherDao;
import nuris.epam.dao.exception.DaoException;
import nuris.epam.dao.manager.DaoFactory;
import nuris.epam.entity.Book;
import nuris.epam.entity.Publisher;
import nuris.epam.service.exception.ServiceException;

import java.util.List;

/**
 * Created by User on 20.03.2017.
 */
public class PublisherService {

    public Publisher findByPublisher(int id) throws ServiceException {
        DaoFactory daoFactory = new DaoFactory();
        Publisher publisher;
        try {
            PublisherDao publisherDao = (PublisherDao) daoFactory.getDao(daoFactory.typeDao().getPublisherDao());
            publisher = publisherDao.findById(id);
            return publisher;
        } catch (DaoException e) {
            throw new ServiceException("Cannot findByAuthor", e);
        } finally {
            daoFactory.returnConnect();
        }
    }

    public Publisher insert(Publisher publisher) throws ServiceException {
        DaoFactory daoFactory = new DaoFactory();
        try {
            PublisherDao publisherDao = (PublisherDao) daoFactory.getDao(daoFactory.typeDao().getPublisherDao());
            publisher = publisherDao.insert(publisher);
            return publisher;
        } catch (DaoException e) {
            throw new ServiceException("Cannot insert", e);
        } finally {
            daoFactory.returnConnect();
        }
    }
    public void update(Publisher publisher) throws ServiceException {
        DaoFactory daoFactory = new DaoFactory();
        try {
            PublisherDao publisherDao = (PublisherDao) daoFactory.getDao(daoFactory.typeDao().getPublisherDao());
            publisherDao.update(publisher);
        } catch (DaoException e) {
            throw new ServiceException("Cannot update", e);
        } finally {
            daoFactory.returnConnect();
        }
    }
    public void delete(Publisher publisher) throws ServiceException {
        DaoFactory daoFactory = new DaoFactory();
        try {
            PublisherDao publisherDao = (PublisherDao) daoFactory.getDao(daoFactory.typeDao().getPublisherDao());
            publisherDao.delete(publisher);
        } catch (DaoException e) {
            throw new ServiceException("Cannot delete", e);
        } finally {
            daoFactory.returnConnect();
        }
    }
    public List<Publisher> getAll() throws ServiceException {
        List<Publisher> list;
        DaoFactory daoFactory = new DaoFactory();
        try {
            PublisherDao publisherDao = (PublisherDao) daoFactory.getDao(daoFactory.typeDao().getPublisherDao());
            list = publisherDao.getAll();
            return list;
        } catch (DaoException e) {
            throw new ServiceException("Cannot getAll", e);
        } finally {
            daoFactory.returnConnect();
        }
    }

    public void findByPublisher(Book book)throws ServiceException {
        DaoFactory daoFactory = new DaoFactory();
        try {
            PublisherDao publisherDao = (PublisherDao) daoFactory.getDao(daoFactory.typeDao().getPublisherDao());
            book.setPublisher(publisherDao.findByBook(book));
        } catch (DaoException e) {
            throw new ServiceException("Cannot getBook", e);}
    }
}
