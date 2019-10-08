package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class CurrencyList {
    private final static int MAX_CURRENCIES = 1000;

    ArrayList<Currency> currencies = new ArrayList<Currency> (MAX_CURRENCIES);
    private int currenciesNumber;
    private String uniqueTableName;
    private LocalDate dateOfTable;

    public int getCurrenciesNumber() {
        return currenciesNumber;
    }

    public void setCurrenciesNumber(int currenciesNumber) {
        this.currenciesNumber = currenciesNumber;
    }

    public String getUniqueTableName() {
        return uniqueTableName;
    }

    public void setUniqueTableName(String uniqueTableName) {
        this.uniqueTableName = uniqueTableName;
    }

    public void addCurrency(Currency currency) {
        if(currenciesNumber < MAX_CURRENCIES){
            currencies.add(currency);
            currenciesNumber++;
        } else {
            System.out.println("MAX CURRENCIES (" + MAX_CURRENCIES + ") IN LIST REACHED!!!");
        }
    }





}
