package model;

import java.time.LocalDate;

public class CurrencyColElem extends Currency {

    private LocalDate date;

    public CurrencyColElem() {
        super();
    }

    public CurrencyColElem(LocalDate date, double exchangeRate) {
        super(exchangeRate);
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getInfo(){
        return date + " : " + super.getInfo();
    }
}
