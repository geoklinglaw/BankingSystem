/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.Customer;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author apple
 */
@Stateless
public class CustomerEntitySessionBean implements CustomerEntitySessionBeanRemote, CustomerEntitySessionBeanLocal {

    @PersistenceContext(unitName = "BankingSystem-ejbPU")
    private EntityManager em;

    @Override
    public Long createNewCustomer(Customer newCust) {
        em.persist(newCust);
        em.flush();
        
        return newCust.getId();
    }
    
    @Override
    public List<Customer> retrieveAllCustomers() {
        Query query = em.createQuery("SELECT c FROM Customer c");
        return query.getResultList();
    }

    /**
     *
     * @param customerId
     * @return
     */
    @Override
    public Customer retrieveCustomerById(Long customerId) {
        Customer customer = em.find(Customer.class, customerId);
//        customer.getDepositAccount().size();
//        customer.getAtmCard();
        
        return customer;
    }
    
    @Override
    public Long retrieveCustomerByIdentification(String identification) {
        return (Long) em.createQuery("SELECT c.customerId FROM Customer c WHERE c.identificationNumber = :idNum")
                       .setParameter("idNum", identification)
                       .getSingleResult();
    }

}
