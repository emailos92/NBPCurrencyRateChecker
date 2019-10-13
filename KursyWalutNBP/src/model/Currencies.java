package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Currencies {
    private CurrencyRows rows;
    private CurrencyCols cols;
    private ArrayList<String> codes;
    private LocalDate dateFrom;
    private LocalDate dateTo;

    public CurrencyRows getRows() {
        return rows;
    }

    public void setRows(CurrencyRows rows) {
        this.rows = rows;
    }

    public CurrencyCols getCols() {
        return cols;
    }

    public void setCols(CurrencyCols cols) {
        this.cols = cols;
    }

    public ArrayList<String> getCodes() {
        return codes;
    }

    public void setCodes(ArrayList<String> codes) {
        this.codes = codes;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }
//
//    public void add(CurrencyRow row){
//        rows.get().add(row);
//    }
//
//    public void add(CurrencyRows rows){
//        for(int i=0;i<rows.get().size();i++){
//            rows.get().add(rows.get().get(i));
//        }
//    }
//
//    public void add(CurrencyCol col){
//        cols.get().add(col);
//    }
//
//    public void add(CurrencyCols cols){
//        for(int i=0;i<cols.get().size();i++){
//            rows.get().add(rows.get().get(i));
//        }
//    }
//
//    public void clear(){
//        rows.get().clear();
//        cols.get().clear();
//    }
}
