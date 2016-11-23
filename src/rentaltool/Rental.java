/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentaltool;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Rental class is used to create an instance of a renting transaction. 
 * @author zach
 */
public class Rental {
    // your instructions showed mm/dd/yy but I think you meant MM/dd/yy
    DateFormat df = new SimpleDateFormat("MM/dd/yy");
    
    // base data generated from input
    private String code;
    private int rentDuration;
    private double discount;
    private Date checkout;
    
    // data generate after instanciation
    private ToolType type;
    private String brand;
    private Date dueDate;
    private double dailyCharge;
    private int chargeDays;
    private double baseCharge;
    private double discountAmount;
    private double finalCost;
    
    
    
    // this is typically the part where i would be using a database but 
    // seeing as this is a java coding demonstration im going to stick with hard
    // coding a hashmap. Id usually instanciate something like this in the
    // Rental Manager class and pass it in via a parameter to only instanciate
    // one instance of the hashmap but since the instructions say to only 
    // require these four parameters i went with this design. the other would 
    // better allow me to track what tools were out as well...
    private HashMap<String,Tool> dataStore;
    
    
    /**
     * The Rental object is the creation of a rental agreement.
     * 
     * @param code String the tool's unique identifier
     * @param rentDuration int the duration for which the tool will be rented
     * @param discount double the rate at which this rental shall be discounted
     * @param checkout  Date the day at which the rental transaction is to take
     * place
     */
    public Rental(String code, int rentDuration, double discount, Date checkout) throws DiscountRateException{
        toolStore();
        
        if(discount >= 1.0){
            throw new DiscountRateException("The discount rate for this" +
                    " transaction exceeds the limit at 100% or 1.0");
        }
        
        this.code = code;
        this.rentDuration = rentDuration;
        this.discount = discount;
        this.checkout = checkout;
        
        // get the tool data
        Tool cTool = dataStore.get(code);
        this.type = cTool.getType();
        this.brand = cTool.getBrand();
        
        //calculate the due date
        Calendar dateCal = Calendar.getInstance();
        dateCal.setTime(checkout);
        dateCal.add(Calendar.DAY_OF_MONTH,rentDuration);
        dueDate = dateCal.getTime();
        
        //get the dailyCharge 
        this.dailyCharge = type.cost();
        try {
            this.chargeDays = type.chargable(checkout, dueDate);
        } catch (ExceededRentalDurationException ex) {
            Logger.getLogger(Rental.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("the date range was too wide");
        }
        this.baseCharge = this.chargeDays * this.dailyCharge;
        this.discountAmount = this.baseCharge * this.discount;
        this.finalCost = this.baseCharge - this.discountAmount;
    }
    
    // just getter methods for the tests.
    public String getDueDate(){
        
        return df.format(this.dueDate);
    }
    
    /**
     * 
     * @return double the double representing what should be charged daily 
     */
    public double getDailyCharge(){
        return this.dailyCharge;
    }
    
    /**
     * 
     * @return int a number representing the number of days the user should be 
     * charged for
     */
    public int getChargeDays(){
        return this.chargeDays;
    }
    
    /**
     * 
     * @return double the base cost of tool rental
     */
    public double getBaseCharge(){
        return this.baseCharge;
    }
    
    /**
     * 
     * @return double the discount rate given to this particular rental
     */
    public double getDiscount(){
        return this.discount;
    }
    
    /**
     * 
     * @return double the value saved by applying the discount 
     */
    public double getDiscAmount(){
        return this.discountAmount;
    }
    
    /**
     * 
     * @return double the final cost of tool rental
     */
    public double getFinalCost(){
        return this.finalCost;
    }
    
    /**
     * 
     * @return String the type of tool being rented
     */
    public String getToolType(){
        return type.getName();
    }
    
    /**
     * 
     * @return String the brand name of the current tool 
     */
    public String getBrandName(){
        return this.brand;
    }
    
    /**
     * 
     * @return int the duration of the rental
     */
    public int getRentalDays(){
        return this.rentDuration;
    }
    
    /**
     * 
     * @return String the date at which the tool was checked out.
     */
    public String getCheckoutDate(){
        return df.format(this.checkout);
    }
    
    
    
    
    /**
     * toolStore merely generates the data store for associating tools with 
     * their ids
     */
    private void toolStore(){
        dataStore = new HashMap<String,Tool>();
        dataStore.put("LADW",new Tool(ToolType.LADDER,"Werner","LADW"));
        dataStore.put("CHNS",new Tool(ToolType.CHAINSAW,"Stihl","CHNS"));
        dataStore.put("JAKR",new Tool(ToolType.JACKHAMMER,"Ridgid","JAKR"));
        dataStore.put("JAKD",new Tool(ToolType.JACKHAMMER,"DeWalt","JAKD"));
    }
}
