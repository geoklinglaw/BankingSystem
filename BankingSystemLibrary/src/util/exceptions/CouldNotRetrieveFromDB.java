/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package util.exceptions;

/**
 *
 * @author apple
 */
public class CouldNotRetrieveFromDB extends Exception {

    /**
     * Creates a new instance of <code>CouldNotRetrieveFromDB</code> without
     * detail message.
     */
    public CouldNotRetrieveFromDB() {
    }

    /**
     * Constructs an instance of <code>CouldNotRetrieveFromDB</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public CouldNotRetrieveFromDB(String msg) {
        super(msg);
    }
    
    public void printMessage() {
        System.out.print("Failed to retrieve from database");
    }
}
