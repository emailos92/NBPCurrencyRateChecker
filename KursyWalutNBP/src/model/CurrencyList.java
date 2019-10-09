package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class CurrencyList {
    private String currencyCode; //determine list of specific currency
    private ArrayList<Double> exchangeRates = new ArrayList<Double>();
    private ArrayList<LocalDate> dates = new ArrayList<LocalDate>();

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public void addToCurrencyList(LocalDate date, double exchangeRate) {
        dates.add(date);
        exchangeRates.add(exchangeRate);
    }

    public void clear(){
        dates.clear();
        exchangeRates.clear();
        currencyCode = null;
    }

    public String getInfo() {
        String info = new String();
        info += "CurrencyList (" + currencyCode + ") have " + dates.size() + " elements" + "\n";
        for (int i = 0; i < dates.size(); i++) {
            info += i + ":" + dates.get(i) + " : " + exchangeRates.get(i) + "\n";
        }

        return info;
    }

}
