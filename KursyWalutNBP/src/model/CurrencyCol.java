package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class CurrencyCol {

    private ArrayList<CurrencyColElem> currencyColElemArray;
    private String code;

    public CurrencyCol() {
        currencyColElemArray = new ArrayList<>();
    }

    public CurrencyCol(String code) {
        currencyColElemArray = new ArrayList<>();
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void add(CurrencyColElem currencyColElem) {
        currencyColElemArray.add(currencyColElem);
    }

    public void clear() {
        currencyColElemArray.clear();
    }

    public CurrencyColElem get(int i) {
        if (i >= 0 && i < currencyColElemArray.size()) {
            return currencyColElemArray.get(i);
        } else {
            throw new NoSuchElementException("CurrencyCol.get(index) wrong index (" + i + ")");
        }
    }

    public CurrencyColElem getNextAndDelete() {
        if (currencyColElemArray.size() > 0) {
            CurrencyColElem currencyColElem = currencyColElemArray.get(0);
            currencyColElemArray.remove(0);
            return currencyColElem;
        } else {
            throw new NoSuchElementException("CurrencyCol are empty!");
        }
    }

    public void remove(int i) {
        if (i >= 0 && i < currencyColElemArray.size()) {
            currencyColElemArray.remove(i);
        } else {
            throw new NoSuchElementException("CurrencyCol.remove(index) wrong index (" + i + ")");
        }
    }

    public int size() {
        return currencyColElemArray.size();
    }

    public void sort() {
        currencyColElemArray.sort(Comparator.comparing(CurrencyColElem::getDate));
    }

    public String toString() {
        String string = new String();
        string += code + "(" + currencyColElemArray.size() + ")\n";
        for (int i = 0; i < currencyColElemArray.size(); i++) {
            string += currencyColElemArray.get(i).toString() + "\n";
        }
        return string;
    }

}
