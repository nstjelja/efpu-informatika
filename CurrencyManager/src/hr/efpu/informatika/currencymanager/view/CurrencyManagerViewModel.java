/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.efpu.informatika.currencymanager.view;

import hr.efpu.informatika.currencymanager.domain.CurrencyCode;
import hr.efpu.informatika.currencymanager.domain.ExchangeRate;
import hr.efpu.informatika.currencymanager.domain.Money;
import hr.efpu.informatika.currencymanager.domain.ValidationException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;

/**
 *
 * @author nikola
 */
public class CurrencyManagerViewModel {
    
    private Money  HRK = null;
    private Money  USD = null;
    private Money  YEN = null;
    private Money  EUR = null;
    
    /**
     * Creates the intial application state
     */
    public CurrencyManagerViewModel(){
        try{
          HRK = new Money();
          HRK.setCode(CurrencyCode.HRK);
          HRK.setRates(buildBasicExchanges(CurrencyCode.HRK));
          
          USD = new Money();
          USD.setCode(CurrencyCode.USD);
          USD.setRates(buildBasicExchanges(CurrencyCode.USD));
          
          YEN = new Money();
          YEN.setCode(CurrencyCode.YEN);
          YEN.setRates(buildBasicExchanges(CurrencyCode.YEN));
          
          EUR = new Money();
          EUR.setCode(CurrencyCode.EUR);
          EUR.setRates(buildBasicExchanges(CurrencyCode.EUR));
        }
        catch(ValidationException ex){
            System.err.println(ex.getMessage());
        }
        
        
    }
    
    /**
     * Sets the amount of a currency
     * @param code code of the currency the amount is set
     * @param amount decimal amount set
     * @return list of new amounts to change for each currency
     * @throws ValidationException 
     */
    public HashMap<CurrencyCode,String> setAmount(CurrencyCode code, String amount) throws ValidationException{
        final double exchange = Double.parseDouble(amount);
        
        HashMap<CurrencyCode,String> result = new HashMap<CurrencyCode,String>();
        
        if (code == CurrencyCode.HRK){
            result.put(CurrencyCode.EUR, Double.toString(EUR.CalculateExchangeFromTarget(CurrencyCode.HRK, exchange)));
        
            result.put(CurrencyCode.YEN, Double.toString(YEN.CalculateExchangeFromTarget(CurrencyCode.HRK, exchange)));
             
            result.put(CurrencyCode.USD, Double.toString(USD.CalculateExchangeFromTarget(CurrencyCode.HRK, exchange)));
        
            return result;
        }
        
        double knAmount = 0.00;
        
        if (code == CurrencyCode.EUR){
           knAmount = EUR.CalculateExchangeFromSource(CurrencyCode.HRK, exchange);
        }
        
        if (code == CurrencyCode.USD) {
           knAmount = USD.CalculateExchangeFromSource(CurrencyCode.HRK, exchange);
        }
        
        if (code == CurrencyCode.YEN) {
           knAmount = USD.CalculateExchangeFromSource(CurrencyCode.HRK, exchange);
        }
        
        result.put(CurrencyCode.HRK, Double.toString(knAmount));
        
        result.put(CurrencyCode.EUR, Double.toString(EUR.CalculateExchangeFromTarget(CurrencyCode.HRK, knAmount)));
        
        result.put(CurrencyCode.YEN, Double.toString(YEN.CalculateExchangeFromTarget(CurrencyCode.HRK, knAmount)));
             
        result.put(CurrencyCode.USD, Double.toString(USD.CalculateExchangeFromTarget(CurrencyCode.HRK, knAmount)));
        
        
        return result;
    }
    
    /**
     * Sets Exchange rate
     * @param sourceCode currency to set the exchange rate towards KN
     * @param amount echange rate
     * @throws ValidationException 
     */
    public void setExchange(CurrencyCode sourceCode, String amount) throws ValidationException{
        ExchangeRate rate = null;
        double exchange = 0.00;
        
        try{
            exchange = Double.parseDouble(amount);
        }
        catch(NumberFormatException ex){
            throw new ValidationException("Value must be a valid number");
        }
        
        if (sourceCode == YEN.getCode()){
           rate = YEN.FindRate(CurrencyCode.HRK);
        }
         
        if (sourceCode == USD.getCode()){
            rate = USD.FindRate(CurrencyCode.HRK);
        }
          
        if (sourceCode == EUR.getCode()){
            rate = EUR.FindRate(CurrencyCode.HRK);
        }
        
        rate.setExchange(exchange);
    }
    
    /**
     * Creates a basic exchange list for a currency code
     * @param source
     * @return list of exchange rates , basic
     * @throws ValidationException 
     */
    private ArrayList<ExchangeRate> buildBasicExchanges(CurrencyCode source) throws ValidationException{
        ArrayList<ExchangeRate> result = new ArrayList<ExchangeRate>();
        
        ArrayList<CurrencyCode> codes = new ArrayList<CurrencyCode>() {{
            add(CurrencyCode.EUR);
            add(CurrencyCode.HRK);
            add(CurrencyCode.USD);
            add(CurrencyCode.YEN);
        }};
        
        for(CurrencyCode code : codes){
            ExchangeRate rate = new ExchangeRate();
            rate.setExchange(1.00);
            rate.setSourceCurrency(source);
            rate.setTargetCurrency(code);
            
            result.add(rate);
        }
        
        return result;
        
    }
    
    
}
