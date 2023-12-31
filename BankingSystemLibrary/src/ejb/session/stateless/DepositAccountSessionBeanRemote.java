/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionRemote.java to edit this template
 */
package ejb.session.stateless;

import entity.DepositAccount;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author apple
 */
@Remote
public interface DepositAccountSessionBeanRemote {
    
    public Long createNewDepositAccount(DepositAccount newDepoAcc);
    
    public List<DepositAccount> retrieveAllDepositAccounts();
    
}
