/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package ejb.session.stateless;

import entity.Customer;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author apple
 */
@Local
public interface CustomerEntitySessionBeanLocal {

    public Long createNewCustomer(Customer newCust);

    public List<Customer> retrieveAllCustomers();
    
    public Customer retrieveCustomerById(Long customerId);
    
    public Long retrieveCustomerByIdentification(String identification);
    
}
