package nuris.epam.dao;

import nuris.epam.dao.exception.DaoException;
import nuris.epam.entity.Book;
import nuris.epam.entity.Publisher;

/**
 * Created by User on 14.03.2017.
 */
public abstract class PublisherDao extends BaseDao<Publisher>{

    public abstract Publisher findByBook(Book book) throws DaoException;
}