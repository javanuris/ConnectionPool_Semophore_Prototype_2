package nuris.epam.dao.mysql;

import nuris.epam.dao.CustomerRoleDao;
import nuris.epam.dao.exception.DaoException;
import nuris.epam.entity.Customer;
import nuris.epam.entity.CustomerRole;
import nuris.epam.entity.Genre;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by User on 18.03.2017.
 */
public class MySqlCustomerRole  extends CustomerRoleDao{
    public static final String ROLE = "role";
    public static final String NAME = "name";
    public static final String ID_ROLE = "id_role";

    private static final String CUSTOMER = "customer";
    public static final String ID_CUSTOMER = "id_book";

    public static final String FIND_BY_CUSTOMER = Sql.create().select().varS(ROLE, ID_ROLE).c().varS(ROLE, NAME).from().var(ROLE).join(CUSTOMER).varS(CUSTOMER, ID_ROLE).eq().varS(ROLE, ID_ROLE).whereQs(CUSTOMER, ID_CUSTOMER).build();

    public void sql(){
        System.out.println(FIND_BY_CUSTOMER);
    }

    @Override
    public CustomerRole findByCustomer(Customer customer) throws DaoException {
        CustomerRole customerRole = null;
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(FIND_BY_CUSTOMER)) {
                statement.setInt(1, customer.getId());
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        customerRole = itemRole(customerRole, resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Can not insert by Book from " + this.getClass().getSimpleName(), e);
        }
        return customerRole;
    }

    private CustomerRole itemRole(CustomerRole customerRole, ResultSet resultSet) throws SQLException {
        customerRole = new CustomerRole();
        customerRole.setId(resultSet.getInt(1));
        customerRole.setName(resultSet.getString(2));
        return customerRole;
    }

    @Override
    public CustomerRole insert(CustomerRole item) throws DaoException {
        return null;
    }

    @Override
    public CustomerRole findById(int id) throws DaoException {
        return null;
    }

    @Override
    public void update(CustomerRole item) throws DaoException {

    }

    @Override
    public List<CustomerRole> getAll() throws DaoException {
        return null;
    }

    @Override
    public void delete(CustomerRole item) throws DaoException {

    }

}
