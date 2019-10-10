package model;

public class Currency {

    private double exchangeRate;

    public Currency() {
    }

    public Currency(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public String getInfo(){
        return String.valueOf(exchangeRate);
    }
}
