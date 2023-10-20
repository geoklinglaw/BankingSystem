/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.AtmCard;
import entity.Customer;
import entity.DepositAccount;
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
        customer.setAtmCard(card);
        return atmCardSessionBeanLocal.createNewAtmCard(card);
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
    public Long OpenNewDepositAccount(DepositAccount depoAcc) {
        return depositAccountSessionBeanLocal.createNewDepositAccount(depoAcc);
    }   
    

}
