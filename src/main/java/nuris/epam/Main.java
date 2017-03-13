package nuris.epam;
import nuris.epam.dao.BaseDao;
import nuris.epam.dao.manager.DaoFactory;
import nuris.epam.entity.Author;
import nuris.epam.entity.BaseEntity;

/**
 * Created by User on 09.03.2017.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        DaoFactory daoFactory = DaoFactory.getInstance();
        BaseDao<BaseEntity> authorDao =  daoFactory.getDao(daoFactory.typeDao().getAuthorDao());
        Author author1 = new Author();
        author1.setFirstName("Nuris");
        author1.setLastName("Nuris");
        author1.setMiddle_name("Nuris");
        author1.setId(1);
        Author author =  (Author) authorDao.findById(2);

        System.out.println(author.getId()+"/"+author.getLastName()+"/"+author.getFirstName()+"/"+author.getMiddle_name());
    }

}
