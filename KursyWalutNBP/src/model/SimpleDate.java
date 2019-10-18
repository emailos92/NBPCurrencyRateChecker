package model;

import java.time.LocalDate;

public class SimpleDate {

    private int day;
    private int month;
    private int year;

    public SimpleDate(){
        year = 2000;
        month = 1;
        day = 1;
    }

    public SimpleDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void set(SimpleDate date){
        day = date.getDay();
        month = date.getMonth();
        year = date.getYear();
    }

    public boolean isAfter(SimpleDate out){
        LocalDate date = LocalDate.of(year,month,day);
        LocalDate outDate = LocalDate.of(out.getYear(),out.getMonth(),out.getDay());

        System.out.println("AFTER = " + date.isAfter(outDate));
        return date.isAfter(outDate);
    }

    public boolean isBefore(SimpleDate out){
        LocalDate date = LocalDate.of(year,month,day);
        LocalDate outDate = LocalDate.of(out.getYear(),out.getMonth(),out.getDay());

        System.out.println("BEFORE = " + date.isBefore(outDate));
        return date.isBefore(outDate);
    }

    public String toString(){
        return year + " " + month + " " + day;
    }

    public String toDateString(){
        LocalDate date = LocalDate.of(year,month,day);

        return String.valueOf(date);
    }
}
