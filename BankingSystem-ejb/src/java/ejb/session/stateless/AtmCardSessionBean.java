/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.AtmCard;
import entity.DepositAccount;
import java.util.ArrayList;
import java.util.List;
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
public class AtmCardSessionBean implements AtmCardSessionBeanRemote, AtmCardSessionBeanLocal {

    @PersistenceContext(unitName = "BankingSystem-ejbPU")
    private EntityManager em;

    @Override
    public Long createNewAtmCard(AtmCard newCard, List<DepositAccount> depoAccList) {
        em.persist(newCard);
        em.flush();
        
        AtmCard updatedCard = em.find(AtmCard.class, newCard.getAtmCardId());
        List<DepositAccount> list = new ArrayList<DepositAccount>();
        
////        for (DepositAccount depoAcc: depoAccList) {
////            DepositAccount depoAccount = em.merge(depoAcc);
////            newCard.getDepositAccount().add(depoAccount);
////            depoAccount.setAtmCard(newCard);
////        }
        for (DepositAccount depoAcc: depoAccList) {
            DepositAccount depoAccount = em.merge(depoAcc);
            list.add(depoAccount);
//            if(updatedCard != null) {
//                updatedCard.setDepositAccount(list);
//            }      
            depoAccount.setAtmCard(newCard);
        }
        System.out.println(list);

        
          
        
        return newCard.getAtmCardId();  

        
        
//        for (DepositAccount depoAcc: depoAccList) {
//            DepositAccount depoAccount = em.merge(depoAcc);
//            newCard.getDepositAccount().add(depoAccount);
//            depoAccount.setAtmCard(newCard);
//        }
//        for (DepositAccount depoAcc: depoAccList) {
//            DepositAccount depoAccount = em.merge(depoAcc);
//            int initialise = newCard.getDepositAccount().size();
//            newCard.getDepositAccount().add(depoAccount);
//            depoAccount.setAtmCard(newCard);
//        }
//        
//        return newCard.getAtmCardId();       
    }
    
    @Override
    public AtmCard retrieveAtmCardById(Long atmCardId) throws CouldNotRetrieveFromDB {
        AtmCard atmCard = em.find(AtmCard.class, atmCardId);
        
        if(atmCard != null) {
            return atmCard;
        }
        else {
            throw new CouldNotRetrieveFromDB("Failed to retrieve ATM card with id: " + atmCardId);
        }
    }
    
    @Override
    public Long retrieveATMCardByNumPin(String cardNum, String cardPin) throws CouldNotRetrieveFromDB {   
        try {
            return (Long) em.createQuery("SELECT a.atmCardId FROM AtmCard a WHERE a.cardNumber = :CardNumber AND a.pin = :Pin")
                            .setParameter("CardNumber", cardNum)
                            .setParameter("Pin", cardPin)
                            .getSingleResult();
        } catch (Exception e) {
            throw new CouldNotRetrieveFromDB("Failed to retrieve ATM card with number: " + cardNum);
        }
    }
    
    @Override
    public String changePin(Long cardId, String newPin) {
//        try {
            AtmCard card = em.find(AtmCard.class, cardId);
            card.setPin(newPin);
            em.flush();
            return card.getPin();
            
//        } catch (Exception e) {
//            throw new FailedToChangePin("An error occurred while changing the pin.");
//        }
        
    }
    
    @Override
    public List<DepositAccount> getDepositAccountId(String cardNum, String cardPin) throws CouldNotRetrieveFromDB  {  
        Long cardId = retrieveATMCardByNumPin(cardNum, cardPin);
        AtmCard card = em.find(AtmCard.class, cardId);
        if (card != null) {
            int initialise = card.getDepositAccount().size();
            return card.getDepositAccount();
        }
        else return new ArrayList<DepositAccount>();
        
        
    }    
}
