/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ttmclient;

import ejb.session.stateless.CustomerEntitySessionBeanRemote;
import ejb.session.stateless.DepositAccountSessionBeanRemote;
import ejb.session.stateless.TellerTerminalMachineSessionBeanRemote;
import entity.AtmCard;
import entity.Customer;
import entity.DepositAccount;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;
import javax.ejb.EJB;

/**
 *
 * @author apple
 */
public class Main {

    @EJB(name = "TellerTerminalMachineSessionBeanRemote")
    private static TellerTerminalMachineSessionBeanRemote tellerTerminalMachineSessionBeanRemote;


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String instructions = "Welcome to Teller Terminal Client. \nEnter corresponding number for next steps: \n";
        instructions += "1: Create Customer\n";
        instructions += "2: Open Deposit Account\n";
        instructions += "3: Issue ATM Card\n";
        instructions += "4: Issue Replacement ATM Card\n";
        instructions += "> ";
        
        System.out.print(instructions);
        int choice = sc.nextInt();
        sc.nextLine(); 
        switch (choice) {
            case 1:
                runCreateCustomer();
                break;
            case 2:
                runOpenDepositAccount();
                break;
            case 3:
                runIssueAtmCard();
                break;
            case 4:
                replaceATMCard();
                break;

        }
    }
    
   private static void replaceATMCard() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter New Card Number: ");
        String cardNum = sc.next();
        System.out.print("Enter New Name on Card: ");
        String name = sc.next();
        System.out.print("Enter New Card Pin: ");
        String pinNum = sc.next();
        System.out.print("Enter Your Identification: ");
        String idNum = sc.next();
        
        Long custId = tellerTerminalMachineSessionBeanRemote.retrieveCustomerByIdentification(idNum);
        Customer customer = tellerTerminalMachineSessionBeanRemote.retrieveCustomerById(custId);
        AtmCard oldCard = customer.getAtmCard();
        
        AtmCard newCard = new AtmCard(cardNum, name, true, pinNum, customer);
        tellerTerminalMachineSessionBeanRemote.replaceWithNewAtmCard(newCard, oldCard, customer);
    }
   
   

    private static void runIssueAtmCard() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Card Number: ");
        String cardNum = sc.next();
        System.out.print("Enter Name on Card: ");
        String name = sc.next();
        System.out.print("Enter Card Pin: ");
        String pinNum = sc.next();
        System.out.print("Enter Your Identification: ");
        String idNum = sc.next();
        
        Long custId = tellerTerminalMachineSessionBeanRemote.retrieveCustomerByIdentification(idNum);
        Customer customer = tellerTerminalMachineSessionBeanRemote.retrieveCustomerById(custId);
        
        AtmCard card = new AtmCard(cardNum, name, true, pinNum, customer);
        Long id = tellerTerminalMachineSessionBeanRemote.issueNewAtmCard(card, customer);
        
        if (id != null) {
            String cardInfo = "Successfully created a new card: " + card.getNameOnCard() + " " + card.getCardNumber() + "!";
            System.out.println(cardInfo);
        } else {
            System.out.println("There was an error, please try again later.");
            
        }
        
    }
    
    private static void runOpenDepositAccount() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Account Number in the format A12345: ");
        String accountNum = sc.next();
        System.out.print("Enter Your Deposit Amount: ");
        String depoAmtStr = sc.next();
        BigDecimal depoAmt = new BigDecimal(depoAmtStr);
        System.out.print("Enter Your Identification: ");
        String idNum = sc.next();
        
        Long custId = tellerTerminalMachineSessionBeanRemote.retrieveCustomerByIdentification(idNum);
        Customer customer = tellerTerminalMachineSessionBeanRemote.retrieveCustomerById(custId);
        
        DepositAccount depoAcc = new DepositAccount(accountNum, depoAmt, depoAmt, depoAmt, true, customer);
        Long id = tellerTerminalMachineSessionBeanRemote.OpenNewDepositAccount(depoAcc, customer);
        
        if (id != null) {
            String depoAccInfo = "Successfully created a new deposit account: " + depoAcc.getAccountNumber() + " " + depoAcc.getAvailableBalance() + "!";
            System.out.println(depoAccInfo);
        } else {
            System.out.println("There was an error, please try again later.");
            
        }
    }

    private static void runCreateCustomer() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Customer First Name: ");
        String firstName = sc.nextLine();
        System.out.print("Enter Customer Last Name: ");
        String lastName = sc.nextLine();
        System.out.print("Enter Customer Identification Number: ");
        String identificationNumber = sc.nextLine();
        System.out.print("Enter Customer Contact Number: ");
        String contactNumber = sc.nextLine();
        System.out.print("Enter Customer Address Line 1: ");
        String address1 = sc.nextLine();
        System.out.print("Enter Customer Address Line 2: ");
        String address2 = sc.nextLine();
        System.out.print("Enter Customer Postal Code: ");
        String postalCode = sc.nextLine();     
        
        Customer cust = new Customer(firstName, lastName, identificationNumber, contactNumber, address1, address2, postalCode);
        Long id = tellerTerminalMachineSessionBeanRemote.createNewCustomer(cust);
        
        if (id != null) {
            String customerInfo = "Successfully created a new customer: " + cust.getFirstName() + " " + cust.getLastName() + "!";
            System.out.println(customerInfo);
        } else {
            System.out.println("There was an error, please try again later.");
            
        }
        
    }
    
} 

