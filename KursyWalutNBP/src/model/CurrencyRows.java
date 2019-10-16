package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;


public class CurrencyRows {
    ArrayList<CurrencyRow> currencyRowArray;

    public CurrencyRows() {
        currencyRowArray = new ArrayList<>();
    }

    public void add(CurrencyRow currencyRow) {
        currencyRowArray.add(currencyRow);
    }

    public void clear() {
        currencyRowArray.clear();
    }

    public CurrencyRow get(int i) {
        if (i >= 0 && i < currencyRowArray.size()) {
            return currencyRowArray.get(i);
        } else {
            throw new NoSuchElementException("CurrencyRows.get(index) wrong index (" + i + ")");
        }
    }

    public CurrencyRow getNextAndDelete() {
        if (currencyRowArray.size() > 0) {
            CurrencyRow currencyRow = currencyRowArray.get(0);
            currencyRowArray.remove(0);
            return currencyRow;
        } else {
            throw new NoSuchElementException("CurrencyRows are empty!");
        }
    }

    public void remove(int i) {
        if (i >= 0 && i < currencyRowArray.size()) {
            currencyRowArray.remove(i);
        } else {
            throw new NoSuchElementException("CurrencyRows.remove(index) wrong index (" + i + ")");
        }
    }

    public int size() {
        return currencyRowArray.size();
    }

    public void sort() {
        for (int i = 0; i < currencyRowArray.size(); i++) {
            currencyRowArray.get(i).sort();
        }
        currencyRowArray.sort(Comparator.comparing(CurrencyRow::getDate));
    }

    public String toString() {
        String string = new String();
        for (int i = 0; i < currencyRowArray.size(); i++) {
            string += currencyRowArray.get(i).getDate() + "\n";
        }
        return string;
    }
}
