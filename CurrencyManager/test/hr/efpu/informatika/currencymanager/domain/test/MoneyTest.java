/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.efpu.informatika.currencymanager.domain.test;

import hr.efpu.informatika.currencymanager.domain.Money;
import hr.efpu.informatika.currencymanager.domain.CurrencyCode;
import hr.efpu.informatika.currencymanager.domain.ExchangeRate;
import hr.efpu.informatika.currencymanager.domain.ValidationException;
import junit.framework.TestCase;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author nikola
 */
public class MoneyTest extends TestCase{
    
    public MoneyTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testSetCodeValid() {
        Money currency = new Money();
        try{
            currency.setCode(CurrencyCode.YEN);
        }
        catch(ValidationException ex){
            fail("Validation exception should not be thrown");
        }
        
        assertEquals(CurrencyCode.YEN, currency.getCode());
        
    }
    
    @Test
    public void testSetCodeNullNotAllowed() {
        Money currency = new Money();
        try{
            currency.setCode(CurrencyCode.YEN);
        }
        catch(ValidationException ex){
            fail("Validation exception should not be thrown");
        }
        
        assertEquals(CurrencyCode.YEN, currency.getCode());
    }
    
    @Test
    public void testCalculateExchangeRate() throws ValidationException{
        Money currency = new Money();
        currency.setCode(CurrencyCode.HRK);
        
        ExchangeRate rateHRK_YEN = new ExchangeRate();
        rateHRK_YEN.setSourceCurrency(CurrencyCode.HRK);
        rateHRK_YEN.setTargetCurrency(CurrencyCode.YEN);
        rateHRK_YEN.setExchange(14.09);
        currency.addRate(rateHRK_YEN);
        
        ExchangeRate rateHRK_EUR = new ExchangeRate();
        rateHRK_EUR.setSourceCurrency(CurrencyCode.HRK);
        rateHRK_EUR.setTargetCurrency(CurrencyCode.EUR);
        rateHRK_EUR.setExchange(0.13);
        currency.addRate(rateHRK_EUR);
        
        double result = currency.CalculateExchangeFromSource(CurrencyCode.YEN, 100);
        assertEquals(1409.00,result,0.00);
        
        result = currency.CalculateExchangeFromTarget(CurrencyCode.YEN, 100);
        
        assertEquals(7.10, result,1.00);
        
        result = currency.CalculateExchangeFromSource(CurrencyCode.EUR, 100);
        assertEquals(13.39, result,1.0);
        
        result = currency.CalculateExchangeFromTarget(CurrencyCode.EUR, 100);
        assertEquals(769.23, result,1.0);
        
         result = currency.CalculateExchangeFromSource(CurrencyCode.HRK, 100);
        assertEquals(100.00, result,1.0);
        
        result = currency.CalculateExchangeFromTarget(CurrencyCode.HRK, 100);
        assertEquals(100.00, result,1.0);
        
        
        
        
    }
   
}
