/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author apple
 */
@Entity
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    
    @OneToMany(mappedBy="customer")
    private List<DepositAccount> depositAccount;
    
    @OneToOne(mappedBy="customer")
    private AtmCard atmCard;
    
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String identificationNumber;
    @Column(nullable = false)
    private String contactNumber;
    @Column(nullable = false)
    private String addressLine1;
    @Column(nullable = false)
    private String addressLine2;
    @Column(nullable = false)
    private String postalCode;
    

    public Customer() {
    }

    public Customer(String firstName, String lastName, String identificationNumber, String contactNumber, String addressLine1, String addressLine2, String postalCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.identificationNumber = identificationNumber;
        this.contactNumber = contactNumber;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.postalCode = postalCode;
        this.depositAccount = new ArrayList<DepositAccount>();
    }
    
    
    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the identificationNumber
     */
    public String getIdentificationNumber() {
        return identificationNumber;
    }

    /**
     * @param identificationNumber the identificationNumber to set
     */
    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    /**
     * @return the contactNumber
     */
    public String getContactNumber() {
        return contactNumber;
    }

    /**
     * @param contactNumber the contactNumber to set
     */
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    /**
     * @return the addressLine1
     */
    public String getAddressLine1() {
        return addressLine1;
    }

    /**
     * @param addressLine1 the addressLine1 to set
     */
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    /**
     * @return the addressLine2
     */
    public String getAddressLine2() {
        return addressLine2;
    }

    /**
     * @param addressLine2 the addressLine2 to set
     */
    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    /**
     * @return the postalCode
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * @param postalCode the postalCode to set
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }


    public Long getId() {
        return customerId;
    }

    public void setId(Long id) {
        this.customerId = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (customerId != null ? customerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) object;
        if ((this.customerId == null && other.customerId != null) || (this.customerId != null && !this.customerId.equals(other.customerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Customer[ id=" + customerId + " ]";
    }

    /**
     * @return the depositAccount
     */
    public List<DepositAccount> getDepositAccount() {
        return depositAccount;
    }

    /**
     * @param depositAccount the depositAccount to set
     */
    public void setDepositAccount(List<DepositAccount> depositAccount) {
        this.depositAccount = depositAccount;
    }

    /**
     * @return the atmCard
     */
    public AtmCard getAtmCard() {
        return atmCard;
    }

    /**
     * @param atmCard the atmCard to set
     */
    public void setAtmCard(AtmCard atmCard) {
        this.atmCard = atmCard;
    }
    
}
