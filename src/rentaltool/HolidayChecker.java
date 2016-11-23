/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentaltool;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *The HolidayChecker is a class full of statically callable functions that
 * strictly handle the calculation of applicable holiday dates.
 * 
 * @author zach
 */
public class HolidayChecker {
    private ArrayList<Date> holidayList;
    
    /**
     * CalcIndDay calculates if Independence day effects this rental.
     * 
     * @param inputStart the start date that a user is inquiring about for a
     * rental
     * @param inputEnd the end date that a user in inquiring about for a rental
     * @return boolean does july 4th fall within the time frame specified 
     */
    private static boolean calcIndDay(Date inputStart, Date inputEnd){
        // First convert the dates into an object that allows us to analyze them
        Calendar currentS = Calendar.getInstance();
        currentS.setTime( inputStart);
        
        Calendar currentE = Calendar.getInstance();
        currentE.setTime(inputEnd);
        
        // Now determine when july 4th is. 
        Calendar theFourth = Calendar.getInstance();
        theFourth.set(currentS.get(Calendar.YEAR), Calendar.JULY , 4);
        
        if(theFourth.DAY_OF_WEEK == 6){
            theFourth.add(Calendar.DAY_OF_MONTH, -1);
        }
        else if(theFourth.DAY_OF_WEEK == 0){
            theFourth.add(Calendar.DAY_OF_MONTH, 1);
        }
        
        
        if(theFourth.after(currentS) && theFourth.before(currentE)){
            return true;
        }
        else{
            return false;
        }
    }
    
    
    
    /**
     * the calcLaborD function calculates when labor day falls for given years 
     * based on the inputStart parameter. 
     * 
     * @param inputStart The start date to be considered relative to labor day.
     * @param inputEnd The end date to be considered relative to labor day.
     * @return boolean returns true if the date range falls on labor day
     */
    private static boolean calcLaborDay(Date inputStart, Date inputEnd){
        // first step is to find the year in question
        Calendar inStart = Calendar.getInstance();
        inStart.setTime(inputStart);
        int yearInQuestion = inStart.get(Calendar.YEAR);
        
        Calendar inEnd = Calendar.getInstance();
        inEnd.setTime(inputEnd);
        
        // now form the date that would be the first monday of september
        Calendar firstMonday = Calendar.getInstance();
        firstMonday.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        firstMonday.set(Calendar.DAY_OF_WEEK_IN_MONTH, 1);
        firstMonday.set(Calendar.MONTH, Calendar.SEPTEMBER);
        firstMonday.set(Calendar.YEAR, yearInQuestion);
        
        if(firstMonday.after(inStart) && firstMonday.before(inEnd)){
            return true;
        }
        else{
            return false;
        }
        
    }
    
    /**
     * this function merely calculates the number of available holiday discounts
     * 
     * @param sDate the start date of the time frame for a rental
     * @param eDate the end date of the time frame for a rental
     * @return int the number of days to be discounted due to holidays.
     */
    public static int holidayDiscount(Date sDate, Date eDate){
        int daysDiscounted = 0;
        if(calcIndDay(sDate, eDate)){
            daysDiscounted++;
        }
        if(calcLaborDay(sDate, eDate)){
            daysDiscounted++;
        }
        return daysDiscounted;
    }
    
    
    
    
    
    
}
