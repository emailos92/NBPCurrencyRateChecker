package model;

import java.time.LocalDate;

public class CurrencyDates {

    LocalDate from;
    LocalDate to;

    //default last three months
    public CurrencyDates(){
        this.from = LocalDate.now().minusMonths(3);
        this.to = LocalDate.now();
    }

    public LocalDate getFrom() {
        return from;
    }

    public void setFrom(LocalDate from) {
        this.from = from;
    }

    public LocalDate getTo() {
        return to;
    }

    public void setTo(LocalDate to) {
        this.to = to;
    }

    public void setLastXMonths(int months){
        from = LocalDate.now().minusMonths(months);
        to = LocalDate.now();
    }

    public void setLastXWeeks(int weeks){
        from = LocalDate.now().minusWeeks(weeks);
        to = LocalDate.now();
    }

    public void setSingleDay(LocalDate date){
        from = date;
        to = date;
    }

    public void setNow(){
        from = LocalDate.now();
        to = LocalDate.now();
    }

    public String toString(){
        return from + " : " + to;
    }
}
