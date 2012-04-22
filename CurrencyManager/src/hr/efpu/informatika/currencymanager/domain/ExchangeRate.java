/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.efpu.informatika.currencymanager.domain;

/**
 *
 * @author nikola
 */
public class ExchangeRate extends Entity{
    private CurrencyCode sourceCurrency = null;
    private CurrencyCode targetCurrency = null;
    private double exchange = 0.00;

    /**
     * @return the sourceCurrency
     */
    public CurrencyCode getSourceCurrency() {
        return sourceCurrency;
    }

    /**
     * @param sourceCurrency the sourceCurrency to set
     */
    public void setSourceCurrency(CurrencyCode sourceCurrency)throws ValidationException {
        validateValueNotNull(sourceCurrency);
        this.sourceCurrency = sourceCurrency;
    }

    /**
     * @return the targetCurrency
     */
    public CurrencyCode getTargetCurrency() {
        return targetCurrency;
    }

    /**
     * @param targetCurrency the targetCurrency to set
     */
    public void setTargetCurrency(CurrencyCode targetCurrency) throws ValidationException {
        validateValueNotNull(targetCurrency);
        this.targetCurrency = targetCurrency;
    }

    /**
     * @return the exchange
     */
    public double getExchange() {
        return exchange;
    }

    /**
     * @param exchange the exchange to set
     */
    public void setExchange(double exchange) throws ValidationException{
        validateValueNotZero(exchange);
        this.exchange = exchange;
    }
    
    /**
     * 
     * @param sourceAmount the amount of the source currency to exchange into the target currency
     * @return 
     */
    public double CalculateExchangeFromSource(double sourceAmount){
        return sourceAmount * getExchange();
    }
    
    public double CalculateExchangeFromTarget(double targetAmount){
        return targetAmount/getExchange();
    }
}
