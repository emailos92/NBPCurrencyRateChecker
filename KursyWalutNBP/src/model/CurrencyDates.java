package model;

import java.time.LocalDate;

public class CurrencyDates {

    private SimpleDate dateFrom;
    private SimpleDate dateTo;

    //default last three months
    public CurrencyDates() {
        LocalDate from = LocalDate.now().minusMonths(3);
        LocalDate to = LocalDate.now();

        dateFrom = new SimpleDate(from.getYear(),from.getMonthValue(),from.getDayOfMonth());
        dateTo = new SimpleDate(to.getYear(),to.getMonthValue(),to.getDayOfMonth());
    }

    public SimpleDate getDateFrom(){
        return dateFrom;
    }

    public SimpleDate getDateTo(){
        return dateTo;
    }

    public void setDateFrom(int year, int month, int day){
        dateFrom.setDay(day);
        dateFrom.setMonth(month);
        dateFrom.setYear(year);
    }

    public void setDateFrom(SimpleDate from){
        setDateFrom(from.getYear(),from.getMonth(),from.getDay());
    }

    public void setDateFrom(LocalDate from){
        setDateFrom(from.getYear(),from.getMonthValue(),from.getDayOfMonth());
    }

    public void setDateTo(int year, int month, int day){
        dateTo.setDay(day);
        dateTo.setMonth(month);
        dateTo.setYear(year);
    }

    public void setDateTo(SimpleDate to){
        setDateTo(to.getYear(),to.getMonth(),to.getDay());
    }

//    public void setDateTo(LocalDate to){
//        setDateTo(to.getYear(),to.getMonthValue(),to.getDayOfMonth());
//    }
//
//    public void setLastXMonths(int months) {
//        setDateFrom(LocalDate.now().minusMonths(months));
//        setDateTo(LocalDate.now());
//    }
//
//    public void setLastXWeeks(int weeks) {
//        setDateFrom(LocalDate.now().minusWeeks(weeks));
//        setDateTo(LocalDate.now());
//    }
//
//    public void setSingleDay(LocalDate date) {
//        setDateFrom(date);
//        setDateTo(date);
//    }
//
//    public void setNow() {
//        setDateFrom(LocalDate.now());
//        setDateTo(LocalDate.now());
//    }

    public String toString() {
        return dateFrom + " : " + dateTo;
    }
}
