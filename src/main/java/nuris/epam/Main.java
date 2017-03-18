package nuris.epam;
import nuris.epam.dao.CustomerRoleDao;
import nuris.epam.dao.manager.DaoFactory;
import nuris.epam.entity.CustomerRole;
import java.util.List;

/**
 * Created by User on 09.03.2017.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        DaoFactory daoFactory = new DaoFactory();
        CustomerRoleDao customerRoleDao = (CustomerRoleDao) daoFactory.getDao(daoFactory.typeDao().getCustomerRoleDao());



    }
}
