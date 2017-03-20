package nuris.epam.service;

import nuris.epam.dao.PublisherDao;
import nuris.epam.dao.exception.DaoException;
import nuris.epam.dao.manager.DaoFactory;
import nuris.epam.dao.manager.TypeDao;
import nuris.epam.entity.Book;
import nuris.epam.entity.Publisher;
import nuris.epam.service.exception.ServiceException;

import java.util.List;

/**
 * Created by User on 20.03.2017.
 */
public class PublisherService {

    public Publisher findByPublisher(int id) throws ServiceException {
        Publisher publisher;
        GeneralService generalService = new GeneralService(TypeDao.getInstance().getPublisherDao());
        publisher = (Publisher) generalService.findById(id);
        return publisher;
    }

    public Publisher insert(Publisher publisher) throws ServiceException {
        GeneralService generalService = new GeneralService(TypeDao.getInstance().getPublisherDao());
        publisher = (Publisher) generalService.insert(publisher);
        return publisher;
    }

    public void update(Publisher publisher) throws ServiceException {
        GeneralService generalService = new GeneralService(TypeDao.getInstance().getPublisherDao());
        generalService.update(publisher);
    }

    public void delete(Publisher publisher) throws ServiceException {
        GeneralService generalService = new GeneralService(TypeDao.getInstance().getPublisherDao());
        generalService.delete(publisher);
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

    public void findByPublisher(Book book) throws ServiceException {
        DaoFactory daoFactory = new DaoFactory();
        try {
            PublisherDao publisherDao = (PublisherDao) daoFactory.getDao(daoFactory.typeDao().getPublisherDao());
            book.setPublisher(publisherDao.findByBook(book));
        } catch (DaoException e) {
            throw new ServiceException("Cannot getBook", e);
        } finally {
            daoFactory.returnConnect();
        }
    }
}
