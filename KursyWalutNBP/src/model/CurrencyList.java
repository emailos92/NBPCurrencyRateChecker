package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class CurrencyList {
    private String currencyCode; //determine list of specific currency
    private ArrayList<Double> exchangeRates = new ArrayList<Double>();
    private ArrayList<LocalDate> date = new ArrayList<LocalDate>();

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public void addCurrency(Currency currency, LocalDate dateOfExchangeRate) {
        if (currency.getCode().equals(currencyCode)) {
            exchangeRates.add(currency.getExchangeRate());
            date.add(dateOfExchangeRate);
        } else {
            throw new IllegalArgumentException("Wrong currency in argument, correct is " + currencyCode);
        }
    }

    public void getInfo() {
        String info = new String();
        info += "CurrencyList with " + date.size() + "elements" + "\n";
        for (int i = 0; i < date.size(); i++) {
            info += date.get(i) + " : " + exchangeRates.get(i) + "\n";
        }
    }

}
