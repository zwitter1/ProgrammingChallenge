/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentaltool;

/**
 *
 * @author zach
 */
public class ExceededRentalDurationException extends Exception {

    /**
     * Creates a new instance of <code>ExceededRentalDurationException</code>
     * without detail message.
     */
    public ExceededRentalDurationException() {
    }

    /**
     * Constructs an instance of <code>ExceededRentalDurationException</code>
     * with the specified detail message.
     *
     * @param msg the detail message.
     */
    public ExceededRentalDurationException(String msg) {
        super(msg);
    }
}
