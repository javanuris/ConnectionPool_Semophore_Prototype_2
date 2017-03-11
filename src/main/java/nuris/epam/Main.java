package nuris.epam;
import nuris.epam.dao.BaseDao;
import nuris.epam.dao.manager.DaoFactory;
import nuris.epam.dao.manager.TypeDB;

import java.sql.Connection;
import java.util.Collections;
import java.util.List;

/**
 * Created by User on 09.03.2017.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        TypeDB typeDB = TypeDB.getInstance();
        DaoFactory daoFactory = DaoFactory.getInstance();
        BaseDao authorDao = daoFactory.getDao(typeDB.getAuthorDao());
        

    }

}
