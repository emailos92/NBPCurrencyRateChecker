package logic;

import model.CurrencyCol;
import model.CurrencyColElem;
import model.CurrencyRow;
import model.CurrencyRowElem;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class CurrencyLogic {

    private final static String[] CURRENCIES_CODES = new String[]{
            "1 THB", "1 USD", "1 AUD", "1 HKD", "1 CAD", "1 NZD", "1 SGD", "1 EUR",
            "100 HUF", "1 CHF", "1 GBP", "1 UAH", "100 JPY", "1 CZK", "1 DKK",
            "100 ISK", "1 NOK", "1 SEK", "1 HRK", "1 RON", "1 BGN", "1 TRY", "1 ILS",
            "100 CLP", "1 PHP", "1 MXN", "1 ZAR", "1 BRL", "1 MYR", "1 RUB", "10000 IDR",
            "100 INR", "100 KRW", "1 CNY", "1 XDR",
    };

    private final static String[] CURRENCIES_NAMES = new String[]{
            "bat (Tajlandia)", "dolar amerykański", "dolar australijski", "dolar Hongkongu", "dolar kanadyjski",
            "dolar nowozelandzki", "dolar singapurski", "euro", "forint", "frank szwajcarski", "funt szterling",
            "hrywna (Ukraina)", "jen (Japonia)", "korona czeska", "korona duńska", "korona islandzka",
            "korona norweska", "korona szwedzka", "kuna (Chorwacja)", "lej rumuński", "lew (Bułgaria)", "lira turecka",
            "nowy izraelski szekel", "peso chilijskie", "peso filipińskie", "peso meksykańskie", "rand (Republika Południowej Afryki)",
            "real (Brazylia)", "ringgit (Malezja)", "rubel rosyjski", "rupia indonezyjska", "rupia indyjska", "won południowokoreański",
            "yuan renminbi (Chiny)", "SDR (MFW)",
    };

    private final static String[] CURRENCIES_TABLES_SQL = new String[]{
            "thb_1", "usd_1", "aud_1", "hkd_1", "cad_1", "nzd_1", "sgd_1", "eur_1",
            "huf_100", "chf_1", "gbp_1", "uah_1", "jpy_100", "czk_1", "dkk_1",
            "isk_100", "nok_1", "sek_1", "hrk_1", "ron_1", "bgn_1", "try_1", "ils_1",
            "clp_100", "php_1", "mxn_1", "zar_1", "brl_1", "myr_1", "rub_1", "idr_10000",
            "inr_100", "krw_100", "cny_1", "xdr_1",
    };

    public static String[] getCurrenciesCodes() {
        return CURRENCIES_CODES;
    }

    public static String[] getCurrenciesNames() {
        return CURRENCIES_NAMES;
    }

    public static int getCurrencyIdFromCode(String code) {
        for (int i = 0; i < CURRENCIES_CODES.length; i++) {
            if (CURRENCIES_CODES[i].equals(code)) {
                return i; //return unique id of currency
            }
        }
        throw new IllegalArgumentException("Wrong currency code ( " + code + " ), we don't support it yet");
    }

    public static int getCurrencyIdFromSqlTableName(String currencySqlTableName) {
        for (int i = 0; i < CURRENCIES_TABLES_SQL.length; i++) {
            if (CURRENCIES_TABLES_SQL[i].equals(currencySqlTableName)) {
                return i; //return unique id of currency
            }
        }
        throw new IllegalArgumentException("Wrong currency table name ( " + currencySqlTableName + " ), we don't support it yet");
    }

    public String getCurrencyName(String code) {
        return CURRENCIES_NAMES[getCurrencyIdFromCode(code)];
    }

    public static String getCurrencySqlTableName(String currencyCode) {
        return CURRENCIES_TABLES_SQL[getCurrencyIdFromCode(currencyCode)];
    }

    public static String getCurrencyCode(String currencySqlTableName) {
        return CURRENCIES_CODES[getCurrencyIdFromSqlTableName(currencySqlTableName)];
    }

    public static void printCurrenciesOptions(ArrayList<String> codes) {
        boolean selected;
        System.out.print("0 - deselect all\n" + (CURRENCIES_CODES.length + 1) + " - select all\n-1 - close\n* - selected\n");
        for (int i = 0; i < CURRENCIES_CODES.length; i++) { // all codes
            selected = false;
            for (int j = 0; j < codes.size(); j++) { // selected codes
                if (CURRENCIES_CODES[i].equals(codes.get(j))) {
                    selected = true;
                }
            }
            if (selected) {
                System.out.println((i + 1) + " - ( * ) " + CURRENCIES_CODES[i]);
            } else {
                System.out.println((i + 1) + " - (   ) " + CURRENCIES_CODES[i]);
            }
        }
    }

    public static void selectDeselectCodes(ArrayList<String> codes, int code_idx) {
        if (code_idx > 0) {
            if (code_idx > CURRENCIES_CODES.length) { //select all
                codes.clear();
                for (int i = 0; i < CURRENCIES_CODES.length; i++) {
                    codes.add(CURRENCIES_CODES[i]);
                }
            } else { //select - deselect
                int code_idx_fixed = code_idx - 1;
                // check have we this currency on list => delete
                for (int i = 0; i < codes.size(); i++) {
                    if (code_idx_fixed == getCurrencyIdFromCode(codes.get(i))) {
                        codes.remove(i);
                        return;
                    }
                } // add code to list
                if (code_idx_fixed < CURRENCIES_CODES.length) {
                    codes.add(CURRENCIES_CODES[code_idx_fixed]);
                } else {
                    System.out.println("Wrong option: " + code_idx);
                }
            }
        } else if (code_idx == 0) {
            codes.clear();
        }
    }

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
