/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionRemote.java to edit this template
 */
package ejb.session.stateless;

import entity.AtmCard;
import entity.Customer;
import entity.DepositAccount;
import javax.ejb.Remote;

/**
 *
 * @author apple
 */
@Remote
public interface TellerTerminalMachineSessionBeanRemote {

    public Long createNewCustomer(Customer newCust);

    public Long OpenNewDepositAccount(DepositAccount depoAcc, Customer customer);

    public Long retrieveCustomerByIdentification(String idNum);
    
    public Customer retrieveCustomerById(Long id);

    public Long issueNewAtmCard(AtmCard card, Customer customer);
    
    public void replaceWithNewAtmCard(AtmCard newCard, AtmCard oldCard, Customer customer);
    
}
