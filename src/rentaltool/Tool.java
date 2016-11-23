/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentaltool;

/**
 * The tool class is build to organize meta data pertaining to each rentable
 * tool.
 * @author zach
 */
public class Tool {
    private ToolType type;
    private String brand;
    private String code;
    
    /**
     * Tool constructor object
     * 
     * @param type ToolType this is the type of tool to be tied to the object
     * @param brand String This is the brand of the given tool
     * @param code String this is the unique id of the given tool
     */
    public Tool(ToolType type,String brand, String code){
        this.type = type;
        this.brand = brand;
        this.code = code;
    }
    
    /**
     * getType is a getter function to retrieve the type of this tool
     * @return ToolType the tooltype object associated with this object
     */
    public ToolType getType(){
        return this.type;
    }
    
    /**
     * A getter method for the brand of the tool
     * @return String tool's brand name
     */
    public String getBrand(){
        return this.brand;
    }
    
    /**
     * A getter method for the tool's unique ID
     * @return String the tool's unique ID
     */
    public String getCode(){
        return this.code;
    }
    
}
