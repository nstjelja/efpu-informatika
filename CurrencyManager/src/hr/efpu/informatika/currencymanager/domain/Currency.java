/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.efpu.informatika.currencymanager.domain;

/**
 * Main currency entity
 * @author nikola
 */
public class Currency extends Entity{
    protected CurrencyCode code = CurrencyCode.HR;
    
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
    
    public double CalculateExchange(Currency targetCurrency, double sourceAmount){
        return 0.00;
    }
}
