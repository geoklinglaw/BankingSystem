/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.AtmCard;
import entity.Customer;
import entity.DepositAccount;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author apple
 */
@Stateless
public class TellerTerminalMachineSessionBean implements TellerTerminalMachineSessionBeanRemote, TellerTerminalMachineSessionBeanLocal {

    @EJB(name = "AtmCardSessionBeanLocal")
    private AtmCardSessionBeanLocal atmCardSessionBeanLocal;

    @EJB(name = "DepositAccountSessionBeanLocal")
    private DepositAccountSessionBeanLocal depositAccountSessionBeanLocal;

    @EJB(name = "CustomerEntitySessionBeanLocal")
    private CustomerEntitySessionBeanLocal customerEntitySessionBeanLocal;

    @PersistenceContext(unitName = "BankingSystem-ejbPU")
    private EntityManager em;

    @Override
    public Long issueNewAtmCard(AtmCard card, Customer customer) {
        Customer mergedCustomer = em.merge(customer);
        mergedCustomer.setAtmCard(card);
        List<DepositAccount> depositAccounts = mergedCustomer.getDepositAccount();

        return atmCardSessionBeanLocal.createNewAtmCard(card, depositAccounts);
    }

    @Override
    public void replaceWithNewAtmCard(AtmCard newCard, AtmCard oldCard, Customer customer) {
        oldCard = em.merge(oldCard);
        Customer mergedCustomer = em.merge(customer);
        mergedCustomer.setAtmCard(newCard);
        newCard.setCustomer(mergedCustomer);
        em.persist(newCard);
        em.merge(customer);
        
        em.remove(oldCard);
    }
    
    @Override
    public Long createNewCustomer(Customer newCust) {
        return customerEntitySessionBeanLocal.createNewCustomer(newCust);
    }   

    @Override
    public Long retrieveCustomerByIdentification(String idNum) {
        return customerEntitySessionBeanLocal.retrieveCustomerByIdentification(idNum);
    }  
    
    @Override
    public Customer retrieveCustomerById(Long id) {
        return customerEntitySessionBeanLocal.retrieveCustomerById(id);
    }  
    
    @Override
    public Long OpenNewDepositAccount(DepositAccount depoAcc, Customer customer) {
        Customer mergedCustomer = em.merge(customer);
        List<DepositAccount> list = mergedCustomer.getDepositAccount();
        list.add(depoAcc);
        mergedCustomer.setDepositAccount(list);
        return depositAccountSessionBeanLocal.createNewDepositAccount(depoAcc);
    }   
    

}
