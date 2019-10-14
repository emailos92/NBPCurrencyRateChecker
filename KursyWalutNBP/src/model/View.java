package model;

public class View {
    private CurrencyRows rows;  //by date
    private CurrencyCols cols;  //by name
    private ViewSelect select; //select currency by name and date

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

    public ViewSelect getSelect() {
        return select;
    }

    public void setSelect(ViewSelect select) {
        this.select = select;
    }
}
