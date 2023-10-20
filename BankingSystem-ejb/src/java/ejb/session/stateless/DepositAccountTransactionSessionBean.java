/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author apple
 */
@Stateless
public class DepositAccountTransactionSessionBean implements DepositAccountTransactionSessionBeanRemote, DepositAccountTransactionSessionBeanLocal {

    @PersistenceContext(unitName = "BankingSystem-ejbPU")
    private EntityManager em;



    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
