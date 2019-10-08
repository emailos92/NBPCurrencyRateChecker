package model;

import java.util.ArrayList;

public class CurrencyList {
    private final static int MAX_CURRENCIES = 1000;
    ArrayList<Currency> currencies = new ArrayList<Currency>(MAX_CURRENCIES);
    private int currenciesNumber;
    private String uniqueTableName;

    public void addCurrency(Currency currency) {
        if(currenciesNumber < MAX_CURRENCIES){
            currencies.add(currency);
            currenciesNumber++;
        } else {
            System.out.println("MAX CURRENCIES (" + MAX_CURRENCIES + ") IN LIST REACHED!!!");
        }
    }





}
