/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package ejb.session.stateless;

import entity.DepositAccount;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author apple
 */
@Local
public interface DepositAccountSessionBeanLocal {

    public Long createNewDepositAccount(DepositAccount newDepoAcc);
    
    public List<DepositAccount> retrieveAllDepositAccounts();
    
}
