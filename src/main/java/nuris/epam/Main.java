package nuris.epam;
import nuris.epam.dao.BaseDao;
import nuris.epam.dao.manager.DaoFactory;
/**
 * Created by User on 09.03.2017.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        DaoFactory daoFactory = DaoFactory.getInstance();
        BaseDao authorDao =  daoFactory.getDao(daoFactory.getTypeDao().getAuthorDao());

    }

}
