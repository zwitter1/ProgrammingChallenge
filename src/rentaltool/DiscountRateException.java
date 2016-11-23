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
public class DiscountRateException extends Exception {

    /**
     * Creates a new instance of <code>DiscountRateException</code> without
     * detail message.
     */
    public DiscountRateException() {
    }

    /**
     * Constructs an instance of <code>DiscountRateException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public DiscountRateException(String msg) {
        super(msg);
    }
}
