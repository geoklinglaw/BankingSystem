/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionRemote.java to edit this template
 */
package ejb.session.stateless;

import entity.AtmCard;
import entity.DepositAccount;
import java.util.List;
import javax.ejb.Remote;
import util.exceptions.CouldNotRetrieveFromDB;
import util.exceptions.FailedToChangePin;

/**
 *
 * @author apple
 */
@Remote
public interface AtmCardSessionBeanRemote {
    
    public Long createNewAtmCard(AtmCard newCard, List<DepositAccount> depoAccList);

    public AtmCard retrieveAtmCardById(Long atmCardId) throws CouldNotRetrieveFromDB;
    
    public Long retrieveATMCardByNumPin(String cardNum, String cardPin) throws CouldNotRetrieveFromDB;
    
    public void changePin(Long cardId, String newPin) throws FailedToChangePin;
    
    public List<DepositAccount> getDepositAccountId(String cardNum, String cardPin) throws CouldNotRetrieveFromDB;
    
}
