/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentaltool;
import java.util.Date;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
/**
 * The ToolType enum holds the data that pertains to the individual tool types
 * including the daily charge and days charged.
 * 
 * This enum would be used under the assumption that the cost of tools and
 * times of availability can not be changed by a user. 
 * 
 * @author zach
 */
public enum ToolType {
    LADDER(1.99,"all","Ladder"),
    CHAINSAW(1.49,"week","Chainsaw"),
    JACKHAMMER(2.99,"week,!holidays", "Jackhammer");
    
    private double dailyCharge;
    private String availability;
    private String toolName;
    
    /**
     * This simply instantiates the above enums with appropriate parameters
     * @param double the cost to rent a tool of this type 
     * @param String the availability of a tool of this type
     */
    ToolType(double charge, String avail,String toolName){
        this.dailyCharge = charge;
        this.availability = avail;
        this.toolName = toolName;
    }
    
    /**
     * getter method to get a string for the toolname
     * @return String tool name
     */
    public String getName(){
        return this.toolName;
    }
    
    
    /**
     * Simple getter method fro the cost of the current enum
     * @return double gets the daily cost of the current tool type 
     */
    public double cost(){
        return this.dailyCharge;
    }
    
    /**
     * the chargable function returns the number of days this tool can be
     * charged for
     * @param startD The start date for the availability check
     * @param endD  The end date for tested availability
     * @return boolean weather or not the dates specified are permitted for the 
     * rental
     */
    public int chargable(Date startD, Date endD) throws ExceededRentalDurationException{
        Calendar startCal = Calendar.getInstance();
        startCal.setTime(startD);
        Calendar endCal = Calendar.getInstance();
        endCal.setTime(endD);
        
        int noCharge = 0;
        if(this.availability == "week" || this.availability == "week,!holidays"){
            // need to adjust for Sunday being considered the first day of the week
            // I consider Monday the first day of the week and it also makes my 
            // comparison logic easier so for the sake of the program... it is.
            int startDay = startCal.DAY_OF_WEEK;
            int endDay = endCal.DAY_OF_WEEK;
            if(startDay == 0)
                startDay = 7;
            
            if(endDay == 0)
                endDay = 7;
            
            
            // Now filter out weekends. noCharge represents non chargable days
            
            if( startDay > endDay ){
                if(startDay == 6){
                    noCharge = 1; 
                }
                else if(startDay == 7){
                    noCharge = 0;
                }
                else{
                    noCharge = 2;
                }
            }
            // this technique may not work in France if Sunday is considered 
            // the first day of the week. Not sure if it changes the Enum.
            if(startDay == 6 || startDay == 7){
                noCharge ++;
            }
            if(endDay == 6 || endDay == 7){
                noCharge ++;
            }
            if(this.availability == "week,!holidays"){
                noCharge = noCharge + HolidayChecker.holidayDiscount(startD, endD);
            }
            
        }
        long diff = endD.getTime() - startD.getTime();
        int baseDays = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        if(baseDays > 7){
            throw new ExceededRentalDurationException(Integer.toString(baseDays)
            + " is too long a rental period.");
        }
        return baseDays - noCharge;
        
        
    }
}
