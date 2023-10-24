/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package atmclient;


import ejb.session.stateless.AutomatedTellerMachineSessionBeanRemote;
import entity.AtmCard;
import entity.DepositAccount;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;
import javax.ejb.EJB;
import util.exceptions.CouldNotRetrieveFromDB;
import util.exceptions.FailedToChangePin;


/**
 *
 * @author apple
 */
public class Main {

    @EJB(name = "AutomatedTellerMachineSessionBeanRemote")
    private static AutomatedTellerMachineSessionBeanRemote automatedTellerMachineSessionBeanRemote;


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String welcomeMsg = "Welcome to Automated Teller Terminal Client. \nAre you an existing customer? \n";
        welcomeMsg += "Y: yes \nN: no \n> ";
        System.out.print(welcomeMsg);
        String welcomeAns = sc.next();
        
        String instructions = "";
        if (welcomeAns.toLowerCase().equals("y")) {
            instructions += "1: Insert ATM Card \n";
            instructions += "2: Change Pin Number \n";
            instructions += "3: Enquire Available Balance \n";
            instructions += "> ";
        } else {
            instructions += "Too bad! Get Teller Terminal to sign you up as a customer first :p";
        }
        
        System.out.print(instructions);
        
        int choice = sc.nextInt();
        sc.nextLine();         
        switch (choice) {
            case 1:
                insertATMCard(sc);
                break;
            case 2:
                updatePin(sc);
                break;
            case 3:
                getDepositAccounts(sc);
                break;

        }
    }
    
    private static void updatePin(Scanner sc) {
        Pair<String, String> pair = insertATMCardForPin(sc);
        String cardNum = pair.getKey();
        String cardPin = pair.getValue();
        System.out.print("Enter new card pin: \n");
        String newPin = sc.nextLine();
        System.out.println("cardpin: " + cardPin + " cardnum: " + cardNum);
        try {
            String updatedPin = automatedTellerMachineSessionBeanRemote.UpdatePin(cardNum, cardPin, newPin);
            System.out.println("Successfully changed pin to " + updatedPin + "!");
//        } catch (FailedToChangePin e) {
//            System.out.println(e.getMessage());
            } catch (CouldNotRetrieveFromDB ex) {
                System.out.println("db issues :(");
        }
//       
            
    }   
        
    private static void insertATMCard(Scanner sc) {
        Boolean verified = false;

        while (!verified) {
            System.out.print("Enter ATM Card Number: ");
            String cardNum = sc.nextLine();

            System.out.print("Enter ATM Card Pin: ");
            String cardPin = sc.nextLine();

            verified = checkATMCard(cardNum, cardPin);

            if (!verified) {
                unverifiedATMCardMsg();
            }
        }

        System.out.println("Successfully verified your card!");
    }

    private static Pair<String, String> insertATMCardForPin(Scanner sc) {
        
        Boolean verified = false;
        AtmCard card = null;
        String cardNum = "";
        String cardPin = "";

        while (!verified) {
            System.out.print("Enter ATM Card Number: \n");
            cardNum = sc.nextLine();

            System.out.print("Enter ATM Card Pin: \n");
            cardPin = sc.nextLine();

            verified = checkATMCard(cardNum, cardPin);

            if (!verified) {
                unverifiedATMCardMsg();
            }
        }
        
        System.out.println("Your current card pin is " + cardPin);
        return new Pair<>(cardNum, cardPin);
    }
    
    
    private static boolean checkATMCard(String cardNum, String cardPin) {
        try {
            AtmCard card = automatedTellerMachineSessionBeanRemote.InsertATMCard(cardNum, cardPin);

            return card != null;
        } catch (CouldNotRetrieveFromDB e) {
            System.err.println(e.getMessage());  
            return false;
        } catch (Exception e) {
            System.out.println("im so done with netbeans...");
            return false;
        }
    }
    
    private static void getDepositAccounts(Scanner sc) {
        try {
            System.out.println("Inquiring deposit accounts now...");
            Pair<String, String> pair = insertATMCardForPin(sc);
            String cardNum = pair.getKey();
            String cardPin = pair.getValue();
            List<DepositAccount> list = automatedTellerMachineSessionBeanRemote.getDepositAccountsFromAtmCard(cardNum, cardPin);
            System.out.println(list.size());
            String listOfBalances = "The following shows your account name and balance: \n";
            int index = 1;
            for (DepositAccount depoAcc: list) {
                listOfBalances += "Account " + index + ": " + depoAcc.getAccountNumber() + "| $" + depoAcc.getAvailableBalance() + "\n";
            }
            System.out.print(listOfBalances);
        } catch (CouldNotRetrieveFromDB e) {
            System.out.println("wrong :(");
        }
        
        
    }
    


    private static void unverifiedATMCardMsg() {
        System.out.println("Invalid card number or pin. Please try again.");
    }
    
    

    
}
