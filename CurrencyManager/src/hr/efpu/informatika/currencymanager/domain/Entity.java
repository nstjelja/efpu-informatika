/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.efpu.informatika.currencymanager.domain;

import java.util.UUID;

/**
 * Base entity class for all Domain entities
 * @author nikola
 */
public abstract class Entity {
    protected UUID id;
    /**
     * 
     * @return UUID Unique ID of the Entity in the system
     */
    public UUID getId() {
        return id;
    }
    
    /**
     * Sets the Unique ID of the entity in the system
     * @param value UUID to set
     * @throws ValidationException if value is null
     */
    public void setId(UUID value) throws ValidationException{
        validateValueNotNull(value);
        id = value;
    }
    
  
    
    /**
     * Creates a new entity with a set ID
     */
    public Entity(){
        try{
         setId(UUID.randomUUID());
        }
        catch(ValidationException ex){
            System.err.println(ex.getMessage());
        }
    }
    
        /**
     * Validates that value cannot be null
     * @param value Object validated not to be null
     * @throws ValidationException 
     */
    protected void validateValueNotNull(Object value) throws ValidationException{
        if (value == null) throw new ValidationException("Value must not be null");
    }
    
      /**
     * Validates that value cannot be null
     * @param value Object validated not to be null
     * @param msg String message to throw
     * @throws ValidationException 
     */
    protected void validateValueNotNull(Object value, String msg) throws ValidationException{
        if (value == null) throw new ValidationException(msg);
    }
    
    /**
     * Validates that value cannot be zero
     * @param value to validate not being zero
     * @throws ValidationException 
     */
    protected void validateValueNotZero(double value) throws ValidationException{
          if (value == 0.00) throw new ValidationException("Value cannot be zero");
    }
    
     /**
     * Validates that value cannot be zero
     * @param value to validate not being zero
     * @param msg to send
     * @throws ValidationException 
     */
    protected void validateValueNotZero(double value, String msg) throws ValidationException{
          if (value == 0.00) throw new ValidationException(msg);
    }
   
}
