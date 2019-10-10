package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class CurrencyRow {

    private ArrayList<CurrencyRowElem> currencyRowElems = new ArrayList<CurrencyRowElem>();
    private LocalDate date;
    private String name;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void add(CurrencyRowElem currencyRowElem){
        currencyRowElems.add(currencyRowElem);
    }

    public void clear(){
        currencyRowElems.clear();
    }

    public CurrencyRowElem get(int i) {
        if (currencyRowElems.size() > i) {
            return currencyRowElems.get(i);
        } else {
            throw new NoSuchElementException("CurrencyRow wrong index (" + i + ")");
        }
    }

    public CurrencyRowElem getNextAndDelete() {
        if (currencyRowElems.size() > 0) {
            CurrencyRowElem currencyRowElem = currencyRowElems.get(0);
            currencyRowElems.remove(0);
            return currencyRowElem;
        } else {
            throw new NoSuchElementException("CurrencyRow is empty!");
        }
    }

    public int size(){
        return currencyRowElems.size();
    }

    public String getInfo() {
        String info = new String();
        info += date + "(" + currencyRowElems.size() + ")\n";
        for (int i = 0; i < currencyRowElems.size(); i++) {
            info += currencyRowElems.get(i).getInfo() + "\n";
        }
        return info;
    }

}
