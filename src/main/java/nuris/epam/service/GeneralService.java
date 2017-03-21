package nuris.epam.service;

import nuris.epam.dao.BaseDao;
import nuris.epam.dao.exception.DaoException;
import nuris.epam.dao.manager.DaoFactory;
import nuris.epam.entity.BaseEntity;
import nuris.epam.service.exception.ServiceException;

/**
 * Created by User on 20.03.2017.
 */
public class GeneralService<T extends BaseDao<BaseEntity>> {

    private Class<T> daoClass;
    private DaoFactory daoFactory;

    public GeneralService(Class<T> daoClass, DaoFactory daoFactory) {
        this.daoClass = daoClass;
        this.daoFactory = daoFactory;
    }

    public BaseEntity findById(int id) throws ServiceException {
        DaoFactory daoFactory = null;
        try {
            T general = daoFactory.getDao(daoClass);
            BaseEntity baseEntity = general.findById(id);
            return baseEntity;
        } catch (DaoException e) {
            throw new ServiceException("Can not find by id", e);
        } finally {
            daoFactory.returnConnect();
        }
    }

    public BaseEntity insert(BaseEntity baseEntity) throws ServiceException {
        try {
            T general = daoFactory.getDao(daoClass);
            daoFactory.startTransaction();
            baseEntity = general.insert(baseEntity);
            daoFactory.commitTransaction();
            return baseEntity;
        } catch (DaoException e) {
            try {
                daoFactory.rollbackTransacrion();
            } catch (DaoException e1) {
               throw new ServiceException("can't rollback transaction" ,e1);
            }
            throw new ServiceException("Can not insert", e);
        } finally {
            daoFactory.returnConnect();
        }
    }

    public void update(BaseEntity baseEntity) throws ServiceException {
        try {
            T general = daoFactory.getDao(daoClass);
            general.update(baseEntity);
        } catch (DaoException e) {
            throw new ServiceException("Can not update", e);
        } finally {
            daoFactory.returnConnect();
        }
    }

    public void delete(BaseEntity baseEntity) throws ServiceException {
        try {
            T general = daoFactory.getDao(daoClass);
            general.delete(baseEntity);
        } catch (DaoException e) {
            throw new ServiceException("Can not delete", e);
        } finally {
            daoFactory.returnConnect();
        }
    }
}
