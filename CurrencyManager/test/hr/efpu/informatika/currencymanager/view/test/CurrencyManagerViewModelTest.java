/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.efpu.informatika.currencymanager.view.test;

import junit.framework.TestCase;
import org.junit.*;
import hr.efpu.informatika.currencymanager.view.*;
import hr.efpu.informatika.currencymanager.domain.*;
import java.util.HashMap;

/**
 *
 * @author nikola
 */
public class CurrencyManagerViewModelTest extends TestCase {
    
   
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testBasiSetExchangeAndSetAmountForKn() throws ValidationException{
        CurrencyManagerViewModel viewModel = new CurrencyManagerViewModel();
        viewModel.setExchange(CurrencyCode.EUR, "10.00");
        viewModel.setExchange(CurrencyCode.USD, "5.00");
        viewModel.setExchange(CurrencyCode.YEN,"20.00");
        
        HashMap<CurrencyCode,String> result = viewModel.setAmount(CurrencyCode.HRK, "100.00");
        
        assertEquals("10.0",result.get(CurrencyCode.EUR));
        assertEquals("20.0", result.get(CurrencyCode.USD));
        assertEquals("5.0", result.get(CurrencyCode.YEN));
    }
   
}
