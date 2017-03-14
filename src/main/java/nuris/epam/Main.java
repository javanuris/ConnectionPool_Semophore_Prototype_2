package nuris.epam;
import nuris.epam.dao.BaseDao;
import nuris.epam.dao.manager.DaoFactory;
import nuris.epam.dao.mysql.Sql;
import nuris.epam.entity.Author;
import nuris.epam.entity.BaseEntity;
import nuris.epam.entity.Genre;
import nuris.epam.entity.Publisher;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 09.03.2017.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        List<Genre> list = new ArrayList<>();
        DaoFactory daoFactory = DaoFactory.getInstance();
        BaseDao genreDao = daoFactory.getDao(daoFactory.typeDao().getGenreDao());



    }

}
