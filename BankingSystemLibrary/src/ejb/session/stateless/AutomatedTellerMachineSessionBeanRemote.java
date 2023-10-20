/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionRemote.java to edit this template
 */
package ejb.session.stateless;
import entity.AtmCard;
import javax.ejb.Remote;
import util.exceptions.CouldNotRetrieveFromDB;
import util.exceptions.FailedToChangePin;

/**
 *
 * @author apple
 */
@Remote
public interface AutomatedTellerMachineSessionBeanRemote {
    
    public AtmCard InsertATMCard(String cardNum, String cardPin) throws CouldNotRetrieveFromDB;
    
    public Long getATMId(String cardNum, String cardPin) throws CouldNotRetrieveFromDB;
    
    public void changePin(String cardNum, String cardPin, String newPin) throws FailedToChangePin, CouldNotRetrieveFromDB;
}
