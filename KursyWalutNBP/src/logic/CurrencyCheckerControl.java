package logic;

import gui.JChartTest;
import io.DataReader;
import model.CurrencyCol;
import model.CurrencyColElem;
import model.CurrencyRow;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Random;

import static gui.JChartTest.JChartExample;

public class CurrencyCheckerControl {
    // zmienne do kontrolowania programu
    private final static int EXIT = 0;
    private final static int GET_TABLE_FROM_WEBSITE = 1;
    private final static int PUT_TABLE_TO_DATABASE = 2;
    private final static int DELETE_TABLE_FROM_DATABASE = 3;
    private final static int GET_TABLES_FROM_FILE = 4;
    private final static int PUT_TABLES_TO_DATABASE = 5;
    private final static int DELETE_TABLES_FROM_DATABASE = 6;
    private final static int READ_CURRENCIES_FROM_DATABASE = 7;
    private final static int SHOW_CURRENCIES_FROM_DATABASE = 8;
    private final static int GENERATE_TEST_CURRENCIES = 9;
    private final static int TEST = 10;

    // zmienna do komunikacji z użytkownikiem
    private DataReader dataReader = new DataReader();
    private WebsiteControl websiteControl = new WebsiteControl();
    private DatabaseControl databaseControl = new DatabaseControl();

    private ArrayList<CurrencyRow> currencyRows = new ArrayList<CurrencyRow>();
    private ArrayList<CurrencyCol> currencyCols = new ArrayList<CurrencyCol>();

    /*
     * Główna metoda programu, która pozwala na wybór opcji i interakcję
     */
    public void controlLoop() {
        int option;

        do {
            printOptions();
            option = dataReader.getInt();
            switch (option) {
                case GET_TABLE_FROM_WEBSITE:
                    currencyRows.clear();
                    try {
                        CurrencyRow row = new CurrencyRow();
                        row = websiteControl.parseWebsite();
                        currencyRows.add(row);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    System.out.println(currencyRows.get(0).getInfo());
                    break;
                case PUT_TABLE_TO_DATABASE:
                    try {
                        if (currencyRows.size() > 0) {
                            databaseControl.insertCurrencyRow(currencyRows.get(0));
                            currencyRows.clear();
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    break;
                case DELETE_TABLE_FROM_DATABASE:
                    try {
                        System.out.println(LocalDate.now());
                        databaseControl.deleteCurrencyRow(LocalDate.now());
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    break;

                case GET_TABLES_FROM_FILE:
                    System.out.println("GET_TABLES_FROM_FILE");
                    break;
                case PUT_TABLES_TO_DATABASE:
                    System.out.println("PUT_TABLES_TO_DATABASE");
                    break;
                case DELETE_TABLES_FROM_DATABASE:
                    System.out.println("DELETE_TABLES_FROM_DATABASE");
                    break;
                case READ_CURRENCIES_FROM_DATABASE:
                    System.out.println("READ_CURRENCIES_FROM_DATABASE");
                    break;
                case SHOW_CURRENCIES_FROM_DATABASE:
                    System.out.println("SHOW_CURRENCIES_FROM_DATABASE");
                    break;
                case GENERATE_TEST_CURRENCIES:
                    LocalDate date_from = LocalDate.of(2019, 1, 1);
                    LocalDate date_to = LocalDate.now();

                    currencyCols = createTestArrays(10,date_from,date_to);

                    for (int i = 0; i < currencyRows.size(); i++) {
                        System.out.println(currencyCols.get(i).getInfo());
                    }
                    break;
                case TEST:
                    System.out.println("TEST");

                    JChartTest.JChartExample(currencyCols);
                    break;
                default:
                    System.out.println("Nie ma takiej opcji, wprowadź ponownie: ");
            }
        } while (option != EXIT);
    }

    public ArrayList<CurrencyCol> createTestArrays(int colsNum, LocalDate date_from, LocalDate date_to) {
        Random generator = new Random();
        ArrayList<CurrencyCol> currencyCols = new ArrayList<CurrencyCol>();

        for (int i = 0; i < colsNum; i++) {
            //create new column
            CurrencyCol currencyCol = new CurrencyCol();
            currencyCol.setCode("col" + i);

            for (int yearCnt = date_from.getYear(); yearCnt <= date_to.getYear(); yearCnt++) {  //go over years
                for (int monthCnt = date_from.getMonthValue(); monthCnt <= 12; monthCnt++) { //go over months
                    LocalDate dateMonth = LocalDate.of(yearCnt, monthCnt, 1);
                    int lastDayInMonth = dateMonth.withDayOfMonth(dateMonth.lengthOfMonth()).getDayOfMonth();  //go over days
                    for (int dayCnt = 1; dayCnt <= lastDayInMonth; dayCnt++) { //create 10 columns with currency
                        LocalDate date = LocalDate.of(yearCnt, monthCnt, dayCnt);
                        double exchangeRate = generator.nextDouble() * (3) + i*3;

                        CurrencyColElem currencyColElem = new CurrencyColElem(date, exchangeRate);
                        currencyCol.add(currencyColElem);

                        System.out.println(date + " : " + exchangeRate);
                    }
                }
            }

            //add to array lost of columns
            currencyCols.add(currencyCol);
        }
        return currencyCols;
    }

    private void printOptions() {
        System.out.println("Wybierz opcję: ");
        System.out.println(EXIT + ""); //EXIT + " - wyjście z programu");
        System.out.println(GET_TABLE_FROM_WEBSITE + " - odczytanie tabeli z NBP");
        System.out.println(PUT_TABLE_TO_DATABASE + " - zapisanie tabeli do bazy danych");
        System.out.println(DELETE_TABLE_FROM_DATABASE + " - usuwanie tabeli z bazy danych");
        System.out.println(GET_TABLES_FROM_FILE + " - odczytanie tabel z pliku");
        System.out.println(DELETE_TABLES_FROM_DATABASE + " - usuwanie tabel z bazy danych");
        System.out.println(READ_CURRENCIES_FROM_DATABASE + " - odczytanie walut z bazy danych");
        System.out.println(SHOW_CURRENCIES_FROM_DATABASE + " - pokazanie wykresu odzytanych walut");
        System.out.println(GENERATE_TEST_CURRENCIES + " - wygeneruj testowe dane walut");
        System.out.println(TEST + " - TEST FUNCTION");
    }

    private void exit() {
        System.out.println("Koniec programu, papa!");
        websiteControl.close();
        databaseControl.close();
        dataReader.close();
    }

}
