package nuris.epam.dao.mysql;

import nuris.epam.dao.AvatarDao;
import nuris.epam.dao.exception.DaoException;
import nuris.epam.entity.Avatar;

/**
 * Created by User on 18.03.2017.
 */
public class MySqlAvatarDao extends AvatarDao {

    public static final String AVATAR = "avatar";
    public static final String PICTURE = "picture";
    public static final String ID_AVATAR = "id_avatar";

    private static final String CUSTOMER = "customer";
    private static final String ID_CUSTOMER = "id_customer";

    private static final String FIND_BY_ID = Sql.create().select().allFrom().var(AVATAR).whereQs(ID_AVATAR).build();
    private static final String INSERT = Sql.create().insert().var(AVATAR).values(ID_AVATAR, 1).build();
    private static final String UPDATE = Sql.create().update().var(AVATAR).set().varQs(PICTURE).whereQs(ID_AVATAR).build();
    private static final String DELETE = Sql.create().delete().var(AVATAR).whereQs(ID_AVATAR).build();
    private static final String FIND_BY_BOOK = Sql.create().select().varS(AVATAR, ID_AVATAR).c().varS(AVATAR, PICTURE).from().var(AVATAR).join(CUSTOMER).varS(CUSTOMER, ID_AVATAR).eq().varS(AVATAR, ID_AVATAR).whereQs(CUSTOMER, ID_CUSTOMER).build();

    @Override
    public Avatar insert(Avatar item) throws DaoException {
        return null;
    }

    @Override
    public Avatar findById(int id) throws DaoException {
        return null;
    }

    @Override
    public void update(Avatar item) throws DaoException {

    }

    @Override
    public void delete(Avatar item) throws DaoException {

    }

}
