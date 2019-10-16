package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class CurrencyCols {
    ArrayList<CurrencyCol> currencyColArray;

    public CurrencyCols() {
        currencyColArray = new ArrayList<>();
    }

    public ArrayList<CurrencyCol> get() {
        return currencyColArray;
    }

    public void set(ArrayList<CurrencyCol> currencyColArray) {
        this.currencyColArray = currencyColArray;
    }

    public void add(CurrencyCol currencyCol) {
        currencyColArray.add(currencyCol);
    }

    public void clear() {
        currencyColArray.clear();
    }

    public CurrencyCol get(int i) {
        if (i >= 0 && i < currencyColArray.size()) {
            return currencyColArray.get(i);
        } else {
            throw new NoSuchElementException("CurrencyCols.get(index) wrong index (" + i + ")");
        }
    }

    public CurrencyCol getNextAndDelete() {
        if (currencyColArray.size() > 0) {
            CurrencyCol currencyCol = currencyColArray.get(0);
            currencyColArray.remove(0);
            return currencyCol;
        } else {
            throw new NoSuchElementException("CurrencyCol are empty!");
        }
    }

    public void remove(int i) {
        if (i >= 0 && i < currencyColArray.size()) {
            currencyColArray.remove(i);
        } else {
            throw new NoSuchElementException("CurrencyCols.remove(index) wrong index (" + i + ")");
        }
    }

    public int size() {
        return currencyColArray.size();
    }

    public void sort() {
        for (int i = 0; i < currencyColArray.size(); i++) {
            currencyColArray.get(i).sort();
        }
        currencyColArray.sort(Comparator.comparing(CurrencyCol::getCode));
    }

    public String toString() {
        String string = new String();
        for (int i = 0; i < currencyColArray.size(); i++) {
            string += currencyColArray.get(i).getCode() + "\n";
        }
        return string;
    }
}
