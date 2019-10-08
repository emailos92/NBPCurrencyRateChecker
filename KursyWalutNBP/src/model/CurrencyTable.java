package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class CurrencyTable {
    private final static int MAX_CURRENCIES = 1000;

    ArrayList<Currency> currencies = new ArrayList<Currency>(MAX_CURRENCIES);
    private int currenciesNumber;
    private String uniqueTableName;
    private LocalDate dateOfTable;

    public ArrayList<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(ArrayList<Currency> currencies) {
        this.currencies = currencies;
    }

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

    public LocalDate getDateOfTable() {
        return dateOfTable;
    }

    public void setDateOfTable(LocalDate dateOfTable) {
        this.dateOfTable = dateOfTable;
    }

    public void addCurrency(Currency currency) {
        if (currenciesNumber < MAX_CURRENCIES) {
            currencies.add(currency);
            currenciesNumber++;
        } else {
            System.out.println("MAX CURRENCIES (" + MAX_CURRENCIES + ") IN LIST REACHED!!!");
        }
    }

    public void printCurrencies() {
        System.out.println(uniqueTableName + " " + " z dnia " + dateOfTable);
        for (int i = 0; i < currenciesNumber; i++) {
            System.out.println(currencies.get(i).getInfo());
        }
    }


}
