/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.efpu.informatika.currencymanager.domain;

import java.util.ArrayList;

/**
 * Main currency entity
 * @author nikola
 */
public class Money extends Entity{
    private CurrencyCode code = CurrencyCode.HRK;
    private ArrayList<ExchangeRate> rates = new ArrayList<ExchangeRate>();
    
    /**
     * 
     * @return CurrencyCode currency code associated with the entity
     */
    public CurrencyCode getCode(){
        return code;
    }
    
    /**
     * Sets the Code for the currency
     * @param value
     * @throws ValidationException if value is null
     */
    public void setCode(CurrencyCode value) throws ValidationException{
        validateValueNotNull(value);
        code = value;
    }
    
     /**
     * @return the rates
     */
    public ArrayList<ExchangeRate> getRates() {
        return rates;
    }

    /**
     * @param rates the rates to set
     */
    public void setRates(ArrayList<ExchangeRate> rates) {
        this.rates = rates;
    }
    
    public void addRate(ExchangeRate rate){
        this.rates.add(rate);
    }
    
    
    public double CalculateExchangeFromSource(CurrencyCode targetCurrency, double sourceAmount) throws ValidationException{
        if (targetCurrency == getCode()) return sourceAmount;
        
        ExchangeRate rate = findRate(targetCurrency);
        
        validateValueNotNull(rate, "Rate must not be null");
        
        
        return rate.CalculateExchangeFromSource(sourceAmount);
    }
    
    public double CalculateExchangeFromTarget(CurrencyCode targetCurrency,double targetAmount) throws ValidationException{
        if (targetCurrency == getCode()) return targetAmount;
        
        ExchangeRate rate = findRate(targetCurrency);
        
        validateValueNotNull(rate, "Rate must not be null");
        
        
        return rate.CalculateExchangeFromTarget(targetAmount);
    }
    
    private ExchangeRate findRate(CurrencyCode targetCurrency){
        ExchangeRate result = null;
        
        for(ExchangeRate rate : getRates()){
            if (rate.getTargetCurrency()!=targetCurrency) continue;
            result = rate;
            break;
            
        }
        
        return result;
    }

   
}
