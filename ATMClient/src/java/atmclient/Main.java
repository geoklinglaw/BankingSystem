/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package atmclient;


import ejb.session.stateless.AutomatedTellerMachineSessionBeanRemote;
import entity.AtmCard;
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
    private static AutomatedTellerMachineSessionBeanRemote AutomatedTellerMachineSessionBeanRemote;


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String welcomeMsg = "Welcome to Automated Teller Terminal Client. \nAre you an existing customer? \n";
        welcomeMsg += "Y: yes \nN: no";
        System.out.println(welcomeMsg);
        String welcomeAns = sc.next();
        
        String instructions = "";
        if (welcomeAns.toLowerCase().equals("y")) {
            instructions += "1: Insert ATM Card \n";
            instructions += "2: Change Pin Number \n";
            instructions += "3: Enquire Available Balance \n";
            instructions += "4: Request for Replacement of ATM Card \n";
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
                changePin(sc);
                break;

        }
    }
    
    private static void changePin(Scanner sc) {
        Pair<String, String> pair = insertATMCardForPin(sc);
        String cardNum = pair.getKey();
        String cardPin = pair.getValue();
        System.out.print("Enter new card pin: ");
        String newPin = sc.nextLine();
        try {
            AutomatedTellerMachineSessionBeanRemote.changePin(cardNum, cardPin, newPin);
        } catch (FailedToChangePin e) {
            System.out.println(e.getMessage());
        } catch (CouldNotRetrieveFromDB ex) {
        }
        
        System.out.println("Successfully changed pin!");
            
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
            System.out.print("Enter ATM Card Number: ");
            cardNum = sc.nextLine();

            System.out.print("Enter ATM Card Pin: ");
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
            AtmCard card = AutomatedTellerMachineSessionBeanRemote.InsertATMCard(cardNum, cardPin);

            return card != null;
        } catch (CouldNotRetrieveFromDB e) {
            System.err.println(e.getMessage());  
            return false;
        } catch (Exception e) {
            System.out.println("im so done with netbeans...");
            return false;
        }
    }
    


    private static void unverifiedATMCardMsg() {
        System.out.println("Invalid card number or pin. Please try again.");
    }

    
}
