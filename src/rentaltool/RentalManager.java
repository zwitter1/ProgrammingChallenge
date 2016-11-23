/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentaltool;
import java.util.Date;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The RentalManager class is designed to serve as the primary class for a point of 
 * sale tool that allows for users to checkout tools for customers and
 * automatically generate an appropriate rental agreement for the transaction. 
 * 
 * This application is a proof of concept and as such will be laid out as if its 
 * a part of a restful querying service.
 * 
 * This would be the place where i would respond to user activity. If the 
 * system was restful then this would be listening for queries and governing 
 * the system. I don't intend to build the full system from front to back for 
 * this proof of concept/demo
 * @author zach
 */
public class RentalManager {
    
    private static void test(){
        //Date tDate = new Date();
        //System.out.println(tDate.getDay());
        
        Calendar testCal = Calendar.getInstance();
        testCal.setTime(new Date());
        System.out.println(testCal.get(Calendar.YEAR));
        
    }
    
    
    /**
     * This is just a sample case used to give a sample of what creating 
     * a rental instance would look like. In a practical application this class 
     * and its functions would likely be an interface to handle client side 
     * requests
     * 
     * 
     * @throws DiscountRateException Handles the scenario that the discount rate 
     * is too high.
     */
    private static void sample() throws DiscountRateException{
        
         Calendar inputTime = Calendar.getInstance();
            inputTime.set(Calendar.YEAR,2020);
            inputTime.set(Calendar.MONTH,Calendar.JULY);
            inputTime.set(Calendar.DAY_OF_MONTH,2);
            
            Rental testRental = new Rental("JAKR",4,0.5,inputTime.getTime());
            
        System.out.println("duedate: " + testRental.getDueDate());
        System.out.println("daily Charge: " + testRental.getDailyCharge());
        System.out.println("Days charge: " + testRental.getChargeDays());
        System.out.println("Base charge: " + testRental.getBaseCharge());
        System.out.println("Discount: " + testRental.getDiscount());  
        System.out.println("Discount amount: " + testRental.getDiscAmount());
        System.out.println("Final: " + testRental.getFinalCost());
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            //test();
            sample();
        } catch (DiscountRateException ex) {
            Logger.getLogger(RentalManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
