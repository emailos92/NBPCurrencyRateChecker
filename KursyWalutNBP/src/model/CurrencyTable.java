package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class CurrencyTable {

    ArrayList<Currency> currencies = new ArrayList<Currency>();
    private String uniqueTableName;
    private LocalDate dateOfTable;

    public String getUniqueTableName() {
        return uniqueTableName;
    }

    public void setUniqueTableName(String uniqueTableName) {
        this.uniqueTableName = uniqueTableName;
    }

    public LocalDate getDateOfTable() {
        return dateOfTable;
    }

    public void setDateOfTable(LocalDate dateOfTable) {
        this.dateOfTable = dateOfTable;
    }

    public boolean isEmpty() {
        return currencies.isEmpty();
    }

    public void addCurrency(Currency currency) {
        currencies.add(currency);
    }

    public Currency getCurrency() {
        if (currencies.size() > 0) {
            Currency currency = currencies.get(0);
            currencies.remove(0);
            return currency;
        } else {
            throw new NoSuchElementException("Currencies table is empty!");
        }
    }

    public void clear() {
        currencies.clear();
    }

    public String getInfo() {
        String info = new String();
        info += uniqueTableName + " " + " z dnia " + dateOfTable + "(" + currencies.size() + ")\n";
        for (int i = 0; i < currencies.size(); i++) {
            info += currencies.get(i).getInfo() + "\n";
        }
        return info;
    }

}
