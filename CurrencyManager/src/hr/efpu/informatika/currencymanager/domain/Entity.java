/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.efpu.informatika.currencymanager.domain;

import java.util.UUID;
import sun.security.util.Debug;

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
     * Validates that value cannot be null
     * @param value Object validated not to be null
     * @throws ValidationException 
     */
    protected void validateValueNotNull(Object value) throws ValidationException{
        if (value == null) throw new ValidationException("Value must not be null");
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
   
}
