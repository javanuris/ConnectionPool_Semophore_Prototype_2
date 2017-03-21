package nuris.epam;

import nuris.epam.dao.mysql.MySqlCustomerRole;
import nuris.epam.entity.CustomerRole;
import nuris.epam.service.CustomerRoleService;
import nuris.epam.service.exception.ServiceException;

/**
 * Created by User on 09.03.2017.
 */
public class Main {

    public static void main(String[] args)  {
        MySqlCustomerRole mySqlCustomerRole = new MySqlCustomerRole();
        mySqlCustomerRole.sql();
        CustomerRoleService customerRoleService = new CustomerRoleService();
        try {
            System.out.println(customerRoleService.findRoleByName("admin"));
        } catch (ServiceException e) {
            e.printStackTrace();
        }

    }
}
