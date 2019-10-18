package logic;

import model.CurrencyCol;
import model.CurrencyColElem;
import model.CurrencyRow;
import model.CurrencyRowElem;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class CurrencyLogic {

    //MAYBE FOR FUTURE USE ONLY ;)
    public static ArrayList<CurrencyCol> rowsToCols(ArrayList<CurrencyRow> currencyRows) {
        if (currencyRows.size() > 0) { //conajmniej jeden wiersz
            int colNumber = currencyRows.get(0).size();  //to check that all rows have same size

            if (colNumber > 0) { //conajmniej jedna kolumna (waluta)
                ArrayList<CurrencyCol> currencyCols = new ArrayList<>(colNumber);
                CurrencyCol currencyCol = new CurrencyCol();
                CurrencyColElem currencyColElem = new CurrencyColElem();

                for (int i = 0; i < currencyRows.size(); i++) {  //licz po wszystkich wierszach (name, date, CurrencyRowElem)
                    if (currencyRows.get(i).size() == colNumber) {
                        for (int j = 0; j < colNumber; j++) {  //licz po wszystkich kolumnach w wierszu
                            if (i == 0) { //jeżeli pierwszy wiersz dodaj kolumny i ustaw code dla kolejnych kolumn
                                currencyCol.setCode(currencyRows.get(0).get(j).getCode());
                                currencyCols.add(currencyCol);
                            }
                            currencyColElem.setDate(currencyRows.get(i).getDate());
                            currencyColElem.setExchangeRate(currencyRows.get(i).get(j).getExchangeRate());
                            currencyCols.get(j).add(currencyColElem);
                        }
                    } else { //jezeli nty wiersz ma inny rozmiar niz pierwszy
                        throw new NoSuchElementException("WARNING: Rows have different size");
                    }
                }
                return currencyCols;
            } else {
                throw new NoSuchElementException("WARNING: First row is empty");
            }
        } else {
            throw new NoSuchElementException("WARNING: Input rows array is empty");
        }
    }

    public static ArrayList<CurrencyRow> colsToRows(ArrayList<CurrencyCol> currencyCols) { //use when we want show by unique table from website
        if (currencyCols.size() > 0) { //conajmniej jedna kolumna
            int rowNumber = currencyCols.get(0).size();  //to check all columns have same size

            if (rowNumber > 0) { //conajmniej jeden wiersz (tablica)
                ArrayList<CurrencyRow> currencyRows = new ArrayList<>(rowNumber);
                CurrencyRow currencyRow = new CurrencyRow();
                CurrencyRowElem currencyRowElem = new CurrencyRowElem();

                for (int i = 0; i < currencyCols.size(); i++) {  //licz po wszystkich kolumnach (code, CurrencyColElem)
                    if (currencyCols.get(i).size() == rowNumber) {
                        for (int j = 0; j < rowNumber; j++) {  //licz po wszystkich kolumnach w wierszu
                            if (i == 0) { //jeżeli pierwsza kolumna dodaj wiersze i ustaw date dla kolejnych wierszy
                                currencyRow.setDate(currencyCols.get(0).get(j).getDate());
                                currencyRows.add(currencyRow);
                            }
                            currencyRowElem.setCode(currencyCols.get(i).getCode());
                            currencyRowElem.setExchangeRate(currencyCols.get(i).get(j).getExchangeRate());
                            currencyRows.get(j).add(currencyRowElem);
                        }
                    } else { //jezeli nty wiersz ma inny rozmiar niz pierwszy
                        throw new NoSuchElementException("WARNING: Columns have different size");
                    }
                }
                return currencyRows;
            } else {
                throw new NoSuchElementException("WARNING: First column is empty");
            }
        } else {
            throw new NoSuchElementException("WARNING: Input column array is empty");
        }
    }
}
