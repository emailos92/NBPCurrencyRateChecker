package model;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class CurrencyCol {

    private ArrayList<CurrencyColElem> currencyColElems = new ArrayList<CurrencyColElem>();
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void add(CurrencyColElem currencyColElem){
        currencyColElems.add(currencyColElem);
    }

    public void clear(){
        currencyColElems.clear();
    }

    public CurrencyColElem get(int i) {
        if (currencyColElems.size() > i) {
            return currencyColElems.get(i);
        } else {
            throw new NoSuchElementException("CurrencyCol wrong index (" + i + ")");
        }
    }

    public CurrencyColElem getNextAndDelete() {
        if (currencyColElems.size() > 0) {
            CurrencyColElem currencyColElem = currencyColElems.get(0);
            currencyColElems.remove(0);
            return currencyColElem;
        } else {
            throw new NoSuchElementException("CurrencyCol is empty!");
        }
    }

    public int size() {
        return currencyColElems.size();
    }

    public String getInfo() {
        String info = new String();
        info += code + "(" + currencyColElems.size() + ")\n";
        for (int i = 0; i < currencyColElems.size(); i++) {
            info += currencyColElems.get(i).getInfo() + "\n";
        }
        return info;
    }

}
