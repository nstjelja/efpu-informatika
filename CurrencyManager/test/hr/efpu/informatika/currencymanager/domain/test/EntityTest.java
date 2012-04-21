/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.efpu.informatika.currencymanager.domain.test;

import com.sun.org.apache.xpath.internal.operations.Bool;
import hr.efpu.informatika.currencymanager.domain.Entity;
import hr.efpu.informatika.currencymanager.domain.ValidationException;
import junit.framework.TestCase;
import org.junit.*;

/**
 *
 * @author nikola
 */
public class EntityTest extends TestCase {
    
    class TestEntity extends Entity{
        
    }
    
    private TestEntity entity = null;
    
    @Before
    public void setUp() {
        entity = new TestEntity();
    }
    
    @After
    public void tearDown() {
        entity = null;
    }
    
    @Test
    public void testUUIDSet(){
       assertNotNull(entity.getId());
    }
    
    @Test
    public void testIdNotNull(){
        boolean exceptionThrown = false;
        
        try{
         entity.setId(null);
        }
        catch (ValidationException ex){
            exceptionThrown = true;
        }
        
        assertTrue(exceptionThrown);
    }
  
}
