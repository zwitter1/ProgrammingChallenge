/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentalTesting;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import org.junit.*;
import static org.junit.Assert.assertThat;
import rentaltool.DiscountRateException;
import rentaltool.Rental;

/**
 *
 * This file holds all of the junit tests to be run after edits. It makes sure 
 * everything works as expected.
 * 
 * @author zach
 */
public class TestJunit {
    
    
    
    @Test
    public void test1(){
       
        try {
            Calendar inputTime = Calendar.getInstance();
            inputTime.set(Calendar.YEAR,2015);
            inputTime.set(Calendar.MONTH,Calendar.SEPTEMBER);
            inputTime.set(Calendar.DAY_OF_MONTH,3);
            
            Rental testRental = new Rental("LADW",5,0.1,inputTime.getTime());
            
            assertEquals(testRental.getDueDate(),"09/08/15");
            assertEquals(testRental.getDailyCharge(),1.99,0.05);
            assertEquals(testRental.getChargeDays(),5);
            assertEquals(testRental.getBaseCharge(),9.95,0.05);
            assertEquals(testRental.getDiscount(),0.1,0.05);
            assertEquals(testRental.getDiscAmount(),1.00,0.05);
            assertEquals(testRental.getFinalCost(),8.95,0.05);
            
        } catch (DiscountRateException ex) {
            Logger.getLogger(TestJunit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Test
    public void test2(){
        try {
            Calendar inputTime = Calendar.getInstance();
            inputTime.set(Calendar.YEAR,2015);
            inputTime.set(Calendar.MONTH,Calendar.JULY);
            inputTime.set(Calendar.DAY_OF_MONTH,2);
            
            Rental testRental = new Rental("CHNS",5,0.25,inputTime.getTime());
            
            assertEquals(testRental.getDueDate(),"07/07/15");
            assertEquals(testRental.getDailyCharge(),1.49,0.05);
            assertEquals(testRental.getChargeDays(),3);
            assertEquals(testRental.getBaseCharge(),4.47,0.05);
            assertEquals(testRental.getDiscount(),.25,0.05);
            assertEquals(testRental.getDiscAmount(),1.12,0.05);
            assertEquals(testRental.getFinalCost(),3.35,0.05);
            
        } catch (DiscountRateException ex) {
            Logger.getLogger(TestJunit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Test
    public void test3(){
        try {
            Calendar inputTime = Calendar.getInstance();
            inputTime.set(Calendar.YEAR,2015);
            inputTime.set(Calendar.MONTH,Calendar.SEPTEMBER);
            inputTime.set(Calendar.DAY_OF_MONTH,3);
            
            Rental testRental = new Rental("JAKD",6,0.0,inputTime.getTime());
            
            assertEquals(testRental.getDueDate(),"09/09/15");
            assertEquals(testRental.getDailyCharge(),2.99,0.05);
            assertEquals(testRental.getChargeDays(),3);
            assertEquals(testRental.getBaseCharge(),8.97,0.05);
            assertEquals(testRental.getDiscount(),0,0.05);
            assertEquals(testRental.getDiscAmount(),0.00,0.05);
            assertEquals(testRental.getFinalCost(),8.97,0.05);
            
        } catch (DiscountRateException ex) {
            Logger.getLogger(TestJunit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Test
    public void test4(){
        try {
            Calendar inputTime = Calendar.getInstance();
            inputTime.set(Calendar.YEAR,2020);
            inputTime.set(Calendar.MONTH,Calendar.JULY);
            inputTime.set(Calendar.DAY_OF_MONTH,2);
            
            Rental testRental = new Rental("JAKR",4,0.5,inputTime.getTime());
            
            assertEquals(testRental.getDueDate(),"07/06/20");
            assertEquals(testRental.getDailyCharge(),2.99,0.05);
            assertEquals(testRental.getChargeDays(),1);
            assertEquals(testRental.getBaseCharge(),2.99,0.05);
            assertEquals(testRental.getDiscount(),.5,0.05);
            assertEquals(testRental.getDiscAmount(),1.50,0.05);
            assertEquals(testRental.getFinalCost(),1.49,0.05);
            
        } catch (DiscountRateException ex) {
            Logger.getLogger(TestJunit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    /**
     * This is the exception test where we simply want to see if the implemented
     * Exception is properly thrown.
     */
    
    @Test//(expected=DiscountRateException.class)
    public void test5(){
        
        Calendar inputTime = Calendar.getInstance();
        inputTime.set(Calendar.YEAR,2015);
        inputTime.set(Calendar.MONTH,Calendar.SEPTEMBER);
        inputTime.set(Calendar.DAY_OF_MONTH,3);
        try{    
            Rental testRental = new Rental("JAKR",5,1.01,inputTime.getTime());
        }
        catch(DiscountRateException correctError){
            assertThat(correctError.getMessage(),is("The discount rate for this transaction exceeds the limit at 100% or 1.0"));
        }
        
    }
}
