package model;

public class Currency {
    private String name;
    private String code;
    private double exchangeRate;

    public Currency(String name, String code, double avgCourseInPln) {
        this.name = name;
        this.code = code;
        this.exchangeRate = exchangeRate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getAvgCourseInPln() {
        return exchangeRate;
    }

    public void setAvgCourseInPln(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public String getInfo(){
        return name + " " + code + " Kurs w PLN: " + exchangeRate;
    }
}
