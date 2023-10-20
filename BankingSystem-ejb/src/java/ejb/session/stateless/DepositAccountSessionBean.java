/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.DepositAccount;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author apple
 */
@Stateless
public class DepositAccountSessionBean implements DepositAccountSessionBeanRemote, DepositAccountSessionBeanLocal {

    @PersistenceContext(unitName = "BankingSystem-ejbPU")
    private EntityManager em;

    @Override
    public Long createNewDepositAccount(DepositAccount newDepoAcc) {
        em.persist(newDepoAcc);
        em.flush();
        
        return newDepoAcc.getDepositAccountId();
    }

    @Override
    public List<DepositAccount> retrieveAllDepositAccounts() {
        Query query = em.createQuery("SELECT da FROM DepositAccounts da");
        return query.getResultList();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
