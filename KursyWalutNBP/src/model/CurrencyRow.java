package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class CurrencyRow {

    private ArrayList<CurrencyRowElem> currencyRowElemArray;
    private LocalDate date;

    public CurrencyRow() {
        currencyRowElemArray = new ArrayList<>();
    }

    public CurrencyRow(LocalDate date, String name) {
        currencyRowElemArray = new ArrayList<>();
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void add(CurrencyRowElem currencyRowElem) {
        currencyRowElemArray.add(currencyRowElem);
    }

    public void clear() {
        currencyRowElemArray.clear();
    }

    public CurrencyRowElem get(int i) {
        if (i >= 0 && i < currencyRowElemArray.size()) {
            return currencyRowElemArray.get(i);
        } else {
            throw new NoSuchElementException("CurrencyRow.get(index) wrong index (" + i + ")");
        }
    }

    public CurrencyRowElem getNextAndDelete() {
        if (currencyRowElemArray.size() > 0) {
            CurrencyRowElem currencyRowElem = currencyRowElemArray.get(0);
            currencyRowElemArray.remove(0);
            return currencyRowElem;
        } else {
            throw new NoSuchElementException("CurrencyRow are empty!");
        }
    }

    public void remove(int i) {
        if (i >= 0 && i < currencyRowElemArray.size()) {
            currencyRowElemArray.remove(i);
        } else {
            throw new NoSuchElementException("CurrencyRow.remove(index) wrong index (" + i + ")");
        }
    }

    public int size() {
        return currencyRowElemArray.size();
    }

    public void sort() {
        currencyRowElemArray.sort(Comparator.comparing(CurrencyRowElem::getCode));
    }

    public String toString() {
        String string = new String();
        string += date + "(" + currencyRowElemArray.size() + ")\n";
        for (int i = 0; i < currencyRowElemArray.size(); i++) {
            string += currencyRowElemArray.get(i).toString() + "\n";
        }
        return string;
    }

}
