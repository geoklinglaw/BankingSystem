/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB31/SingletonEjbClass.java to edit this template
 */
package ejb.session.singleton;

import ejb.session.stateless.AtmCardSessionBeanLocal;
import ejb.session.stateless.CustomerEntitySessionBeanLocal;
import ejb.session.stateless.DepositAccountSessionBeanLocal;
import entity.AtmCard;
import entity.Customer;
import entity.DepositAccount;
import java.math.BigDecimal;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author apple
 */
@Singleton
@LocalBean
@Startup
public class DataInitSessionBean {

    @EJB(name = "AtmCardSessionBeanLocal")
    private AtmCardSessionBeanLocal atmCardSessionBeanLocal;

    @EJB(name = "DepositAccountSessionBeanLocal")
    private DepositAccountSessionBeanLocal depositAccountSessionBeanLocal;

    @EJB(name = "CustomerEntitySessionBeanLocal")
    private CustomerEntitySessionBeanLocal customerEntitySessionBeanLocal;
    

    @PersistenceContext(unitName = "BankingSystem-ejbPU")
    private EntityManager em;
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @PostConstruct
    public void postConstruct() {
        if (em.find(Customer.class, 1l) == null) {
            customerEntitySessionBeanLocal.createNewCustomer(new Customer("Wang", "ChongMing", "T9480283A", "9155312", "RC4", "Level 2", "421415"));
            customerEntitySessionBeanLocal.createNewCustomer(new Customer("Ang", "Sarah", "T9442453K", "9194821", "CAPT", "Level 12", "421416"));
            customerEntitySessionBeanLocal.createNewCustomer(new Customer("Phoebe", "Low", "T845582C", "8347291", "Tembusu", "Level 1", "421417"));
        }
        
        if (em.find(DepositAccount.class, 1l) == null) {
            long custId = customerEntitySessionBeanLocal.createNewCustomer(new Customer("Michelle", "Goh", "T9350212M", "8472921", "RVRC", "Level 4", "421418"));
            Customer customer = customerEntitySessionBeanLocal.retrieveCustomerById(custId);
            
//            long atmId = atmCardSessionBeanLocal.createNewAtmCard(new AtmCard("9362 4018", "Michelle Goh Xiao Li", true, "4444", customer));
//            AtmCard atmCard = atmCardSessionBeanLocal.retrieveAtmCardById(atmId);
            
            
            depositAccountSessionBeanLocal.createNewDepositAccount(new DepositAccount("NoMoneyAccount", new BigDecimal("0.00"), new BigDecimal("0.00"), new BigDecimal("0.00"), true, customer));

        }
    }
        
    
    
}
