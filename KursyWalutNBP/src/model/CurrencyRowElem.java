package model;

public class CurrencyRowElem extends Currency {

    private String code;

    public CurrencyRowElem() {
        super();
    }

    public CurrencyRowElem(String code, double exchangeRate) {
        super(exchangeRate);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String toString(){
        return code + " : " + super.toString();
    }
}
