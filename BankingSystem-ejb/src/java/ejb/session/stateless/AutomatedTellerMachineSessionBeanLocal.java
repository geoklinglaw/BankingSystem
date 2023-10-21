/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package ejb.session.stateless;
import entity.AtmCard;
import entity.DepositAccount;
import java.util.List;
import javax.ejb.Local;
import util.exceptions.CouldNotRetrieveFromDB;
import util.exceptions.FailedToChangePin;

/**
 *
 * @author apple
 */
@Local
public interface AutomatedTellerMachineSessionBeanLocal {
    
    public AtmCard InsertATMCard(String cardNum, String cardPin) throws CouldNotRetrieveFromDB;
    
    public Long getATMId(String cardNum, String cardPin) throws CouldNotRetrieveFromDB;
    
    public void retrieveAndChangePin(String cardNum, String cardPin, String newPin) throws FailedToChangePin, CouldNotRetrieveFromDB;
    
    public String updatePin(String cardNum, String cardPin, String newPin) throws CouldNotRetrieveFromDB;
    
    public List<DepositAccount> getDepositAccountsFromAtmCard(String cardNum, String cardPin) throws CouldNotRetrieveFromDB;

}
