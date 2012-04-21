/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.efpu.informatika.currencymanager.domain;

/**
 * Exception raised by domain objects when values do not pass validation
 * @author nikola
 */
public class ValidationException extends Exception{
    
    protected String message;
    
    /**
     * 
     * @return String validation message
     */
    public String getMessage(){
        return message;
    }
    
    /**
     * Creates a new exception and sets its message
     * @param message String message to show describing the domain validation violation
     */
    public ValidationException(String message)
    {
        super();
        this.message = message;
    }
}
