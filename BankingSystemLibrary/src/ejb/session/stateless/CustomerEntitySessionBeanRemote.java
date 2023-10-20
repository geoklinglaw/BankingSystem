/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionRemote.java to edit this template
 */
package ejb.session.stateless;

import entity.Customer;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author apple
 */
@Remote
public interface CustomerEntitySessionBeanRemote {
    
    public Long createNewCustomer(Customer newCust);
    
    public List<Customer> retrieveAllCustomers();

    public Customer retrieveCustomerById(Long customerId);

    public Long retrieveCustomerByIdentification(String identification);
    
}
