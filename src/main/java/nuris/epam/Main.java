package nuris.epam;
import nuris.epam.dao.BaseDao;
import nuris.epam.dao.manager.DaoFactory;
import nuris.epam.dao.mysql.Sql;
import nuris.epam.entity.Author;
import nuris.epam.entity.BaseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 09.03.2017.
 */
public class Main {
    public static void main(String[] args) throws Exception {

        DaoFactory daoFactory = DaoFactory.getInstance();
        BaseDao author2 = daoFactory.getDao(daoFactory.typeDao().getAuthorDao());
        Author authorefe= new Author();

        authorefe.setFirstName("Puskin");
        authorefe.setMiddle_name("Lev");
        authorefe.setLastName("Aleksandra");
        author2.insert(authorefe);

        System.out.println(author2.findById(5)+" -----------");

        List<Author> list = author2.getAll();
        for(Author author : list){
            System.out.println(author);
        }
        System.out.println(list.size());
        System.out.println(Sql.create().select().allFrom().var("author").build());
        System.out.println();
    }

}
