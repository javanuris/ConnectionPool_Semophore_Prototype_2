package nuris.epam.service;

import nuris.epam.dao.AvatarDao;
import nuris.epam.dao.exception.DaoException;
import nuris.epam.dao.manager.DaoFactory;
import nuris.epam.dao.manager.TypeDao;
import nuris.epam.entity.Avatar;
import nuris.epam.entity.Customer;
import nuris.epam.service.exception.ServiceException;

/**
 * Created by User on 21.03.2017.
 */
public class AvatarService {

    private GeneralService generalService = new GeneralService(TypeDao.getInstance().getAvatarDao());

    public Avatar findByAuthor(int id) throws ServiceException {
        Avatar avatar;
        avatar = (Avatar) generalService.findById(id);
        return avatar;
    }

    public Avatar insert(Avatar avatar) throws ServiceException {
        avatar = (Avatar) generalService.insert(avatar);
        return avatar;
    }

    public void update(Avatar avatar) throws ServiceException {
        generalService.update(avatar);
    }

    public void delete(Avatar avatar) throws ServiceException {
        generalService.delete(avatar);
    }

    public void findByAvatar(Customer customer) throws ServiceException {
        DaoFactory daoFactory = new DaoFactory();
        try {
            AvatarDao avatarDao = (AvatarDao) daoFactory.getDao(daoFactory.typeDao().getAvatarDao());
            customer.setAvatar(avatarDao.findByCustomer(customer));
        } catch (DaoException e) {
            throw new ServiceException("Cannot find by Avatar", e);
        } finally {
            daoFactory.returnConnect();
        }
    }
}
