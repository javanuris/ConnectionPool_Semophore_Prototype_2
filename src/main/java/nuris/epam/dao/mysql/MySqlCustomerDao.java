package nuris.epam.dao.mysql;

import nuris.epam.dao.CustomerDao;
import nuris.epam.dao.exception.DaoException;
import nuris.epam.entity.Customer;

/**
 * Created by User on 18.03.2017.
 */
public class MySqlCustomerDao extends CustomerDao{

    @Override
    public Customer insert(Customer item) throws DaoException {
        return null;
    }

    @Override
    public Customer findById(int id) throws DaoException {
        return null;
    }

    @Override
    public void update(Customer item) throws DaoException {

    }

    @Override
    public void delete(Customer item) throws DaoException {

    }
}
