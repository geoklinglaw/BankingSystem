/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import javax.ejb.Stateless;

import entity.AtmCard;
import entity.Customer;
import entity.DepositAccount;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import util.exceptions.CouldNotRetrieveFromDB;
import util.exceptions.FailedToChangePin;

/**
 *
 * @author apple
 */
@Stateless
public class AutomatedTellerMachineSessionBean implements AutomatedTellerMachineSessionBeanRemote, AutomatedTellerMachineSessionBeanLocal {

    @EJB(name = "AtmCardSessionBeanLocal")
    private AtmCardSessionBeanLocal atmCardSessionBeanLocal;

    @EJB
    private DepositAccountTransactionSessionBeanLocal depositAccountTransactionSessionBean;

    @EJB(name = "DepositAccountSessionBeanLocal")
    private DepositAccountSessionBeanLocal depositAccountSessionBeanLocal;

    @EJB(name = "CustomerEntitySessionBeanLocal")
    private CustomerEntitySessionBeanLocal customerEntitySessionBeanLocal;



    @PersistenceContext(unitName = "BankingSystem-ejbPU")
    private EntityManager em;
    
    @Override
    public AtmCard InsertATMCard(String cardNum, String cardPin) throws CouldNotRetrieveFromDB {
        Long cardId = atmCardSessionBeanLocal.retrieveATMCardByNumPin(cardNum, cardPin);
        AtmCard card = atmCardSessionBeanLocal.retrieveAtmCardById(cardId);
        return card;
    }
    
    @Override
    public void retrieveAndChangePin(String cardNum, String cardPin, String newPin) throws FailedToChangePin, CouldNotRetrieveFromDB {
        System.out.println("does it even go to retrieveandchangepin");
        Long cardId = atmCardSessionBeanLocal.retrieveATMCardByNumPin(cardNum, cardPin);
        atmCardSessionBeanLocal.changePin(cardId, newPin);
    }

        
    @Override
    public Long getATMId(String cardNum, String cardPin) throws CouldNotRetrieveFromDB{
        Long cardId = atmCardSessionBeanLocal.retrieveATMCardByNumPin(cardNum, cardPin);
        return cardId;
    }
    
    @Override
    public String UpdatePin(String cardNum, String cardPin, String newPin) throws CouldNotRetrieveFromDB {
        Long cardId = atmCardSessionBeanLocal.retrieveATMCardByNumPin(cardNum, cardPin);
        String updatedPin = atmCardSessionBeanLocal.changePin(cardId, newPin);
        return updatedPin;
    }
    
    @Override 
    public List<DepositAccount> getDepositAccountsFromAtmCard(String cardNum, String cardPin) throws CouldNotRetrieveFromDB {
        return atmCardSessionBeanLocal.getDepositAccountId(cardNum, cardPin);
    }
    
}
