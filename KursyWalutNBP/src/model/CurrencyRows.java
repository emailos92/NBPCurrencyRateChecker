package model;

import logic.CurrencyLogic;

import java.util.ArrayList;

public class CurrencyRows {
    ArrayList<CurrencyRow> currencyRows;

    public ArrayList<CurrencyRow> get() {
        return currencyRows;
    }

    public void set(ArrayList<CurrencyRow> currencyRows) {
        this.currencyRows = currencyRows;
    }

    public void add(CurrencyRow currencyRow){
        currencyRows.add(currencyRow);
    }

//    public CurrencyCols toCols(){
//        return CurrencyLogic.rowsToCols(currencyRows);
//    }

    public void clear(){
        currencyRows.clear();
    }
}
