package model;

//Currencies can be display as ArrayList of Columns(one code per column) with ColElem extends Currency(date + ExchangeRate)
//Currencies can be display as ArrayList of Rows (one date per row) with RowElem extends Currency(Date + ExchangeRate)
// -> we can add row(s) or col(s) to currencies class
// -> we can specify overwrite row or col

public class Currencies {

    private CurrencyRows rows;  //by date, next by code
    private CurrencyCols cols;  //by code, next by date

    public Currencies() {
        rows = new CurrencyRows();
        cols = new CurrencyCols();
    }

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

//    private boolean rowsToCol(){
//
//    }
//
//    private boolean colsToRow(){
//
//    }
//
//    public boolean addRowSkip(CurrencyRow row){ //if row exist => skip same row
//
//    }
//
//    public boolean addRowSkipElem(CurrencyRow row){ //if element in row exist => skip same elem in same row
//
//    }
//
//    public boolean addRowOver(CurrencyRow row){ //if row exist => overwrite same row
//
//    }
//
//    public boolean addRowOverElem(CurrencyRow row){ //if elem in row exist => overwrite same elem in same row
//
//    }
//
//    public boolean addColSkip(CurrencyCol col){ //if col exist => skip same col
//
//    }
//
//    public boolean addColSkipElem(CurrencyCol col){ //if element in col exist => skip same elem in same col
//
//    }
//
//    public boolean addColOver(CurrencyCol col){ //if col exist => overwrite same col
//
//    }
//
//    public boolean addColOverElem(CurrencyCol col){ //if elem in col exist => overwrite same elem in same col
//
//    }

    public void clear(){
        rows.clear();
        cols.clear();
    }
}
