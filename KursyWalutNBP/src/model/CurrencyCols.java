package model;

import logic.CurrencyLogic;

import java.util.ArrayList;

public class CurrencyCols {
    ArrayList<CurrencyCol> currencyCols;

    public ArrayList<CurrencyCol> get() {
        return currencyCols;
    }

    public void set(ArrayList<CurrencyCol> currencyCols) {
        this.currencyCols = currencyCols;
    }

    public void add(CurrencyCol currencyCol){
        currencyCols.add(currencyCol);
    }

//    public CurrencyRows toRows(){
//        return CurrencyLogic.colsToRows(currencyCols);
//    }

    public void clear(){
        currencyCols.clear();
    }


}
