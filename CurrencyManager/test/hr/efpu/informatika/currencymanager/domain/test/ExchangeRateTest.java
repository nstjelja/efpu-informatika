/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.efpu.informatika.currencymanager.domain.test;

import hr.efpu.informatika.currencymanager.domain.*;

import java.util.Currency;
import junit.framework.TestCase;
import org.junit.*;

/**
 *
 * @author nikola
 */
public class ExchangeRateTest extends TestCase {
    
    private ExchangeRate rate = null;
    private CurrencyCode sourceCurrency = null;
    private CurrencyCode targetCurrency = null;
     
    @Before
    public void setUp() throws ValidationException {
        rate = new ExchangeRate();
        
        sourceCurrency = CurrencyCode.YEN;
        
        targetCurrency = CurrencyCode.HRK;
        
        rate.setSourceCurrency(sourceCurrency);
        rate.setTargetCurrency(targetCurrency);
        
        rate.setExchange(5);
        
    }
    
    @After
    public void tearDown() {
        rate = null;
    }
    
    @Test
    public void testFieldsAreNotNull(){
       boolean validationThrown = false;
       
       try{
           rate.setSourceCurrency(null);
       }
       catch(ValidationException ex){
           validationThrown = true;
       }
       
       assertTrue(validationThrown);
       validationThrown = false;
       
       try{
           rate.setTargetCurrency(null);
       }
       catch(ValidationException ex){
           validationThrown = true;
       }
       
       assertTrue(validationThrown);
       validationThrown = false;
    
    }
    
    @Test
    public void testExchangeNotZero(){
         boolean validationThrown = false;
       
       try{
           rate.setExchange(0);
       }
       catch(ValidationException ex){
           validationThrown = true;
       }
       
       assertTrue(validationThrown);
       validationThrown = false;
    }
    
    @Test
    public void testCalculateExchangeFromSource(){
        double result = rate.CalculateExchangeFromSource(1);
        
        assertEquals(5.00, result);
    }
    
    @Test
    public void testCalculateExchangeFromTarget(){
        double result = rate.CalculateExchangeFromTarget(5);
        
        assertEquals(1.00, result);
    }
    
}
