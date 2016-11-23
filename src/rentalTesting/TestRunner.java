/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentalTesting;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 *The TestRunner class is the main method for the testing suite. 
 * @author zach
 */
public class TestRunner {
    public static void main(String[] args) {
      Result result = JUnitCore.runClasses(TestJunit.class);
		
      for (Failure failure : result.getFailures()) {
         System.out.println(failure.toString());
      }
		
      if(result.wasSuccessful()){
          System.out.println("Hurrahh all works fine!");
      }
      else{
          System.out.println("Try again...");
      }
    
   }
}
