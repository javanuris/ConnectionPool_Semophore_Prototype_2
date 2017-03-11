package nuris.epam.dao.mysql;
import nuris.epam.dao.AuthorDao;
import nuris.epam.entity.BaseEntity;

import java.util.List;

public class MySqlAuthorDao extends AuthorDao {

    @Override
    public int insert(BaseEntity e) {
        return 0;
    }

    @Override
    public BaseEntity findById(int id) {
        return null;
    }

    @Override
    public void update(BaseEntity item) {

    }

    @Override
    public List getAll() {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void delete(BaseEntity item) {

    }
}
