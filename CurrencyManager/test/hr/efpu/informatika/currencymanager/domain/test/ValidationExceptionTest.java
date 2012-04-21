/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.efpu.informatika.currencymanager.domain.test;

import org.junit.*;
import static org.junit.Assert.*;
import hr.efpu.informatika.currencymanager.domain.*;
import junit.framework.TestCase;


/**
 *
 * @author nikola
 */
public class ValidationExceptionTest extends TestCase {
    
   @Test
   public void testExceptionHasMessage(){
       ValidationException catcheException = null;
       String message = "Nikola is the great";
       String catchedMessage = "";
       
       try{
           throw new ValidationException(message);
       } catch (ValidationException ex){
           catchedMessage = ex.getMessage();
       }
       
       assertEquals(message, catchedMessage);
       
   }
}
