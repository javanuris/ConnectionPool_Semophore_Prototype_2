package nuris.epam.service;

import nuris.epam.dao.manager.TypeDao;
import nuris.epam.entity.Customer;
import nuris.epam.entity.CustomerRole;
import nuris.epam.service.exception.ServiceException;

/**
 * Created by User on 21.03.2017.
 */
public class CustomerService {
    
    private GeneralService generalService = new GeneralService(TypeDao.getInstance().getCustomerDao());
    private CustomerRoleService customerRoleService = new CustomerRoleService();
    private CityService cityService = new CityService();
    private PersonService personService = new PersonService();
    private String USER_ROLE = "user";
    private AvatarService avatarService = new AvatarService();



    public void registerCusomer(Customer customer) throws ServiceException {
        CustomerRole customerRole = customerRoleService.findRoleByName(USER_ROLE);
        customer.setCustomerRole(customerRole);
        insertCustomer(customer);
    }

    public void insertCustomer(Customer customer) throws ServiceException {
        generalService.insert(customer);
    }

}
