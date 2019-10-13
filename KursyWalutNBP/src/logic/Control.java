package logic;

import gui.JChartTest;
import io.DataReader;
import model.Currencies;
import model.CurrencyCol;
import model.CurrencyColElem;
import model.CurrencyRow;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class Control {
    // zmienne do kontrolowania programu
    private final static int EXIT = 0;
    private final static int GET_FROM_WEBSITE = 1;
    private final static int GET_FROM_FILE = 2;
    private final static int GET_FROM_DATABASE = 3;
    private final static int GET_RANDOM = 4;
    private final static int PUT_TO_FILE = 5;
    private final static int PUT_TO_DATABASE = 6;
    private final static int DELETE_FROM_DATABASE = 7;
    private final static int SELECT_CURRENCY = 8;
    private final static int SELECT_DATE = 9;
    private final static int SHOW = 10;

    // zmienna do komunikacji z użytkownikiem
    private DataReader dataReader = new DataReader();
    private WebsiteControl websiteControl = new WebsiteControl();
    private DatabaseControl databaseControl = new DatabaseControl();

    /*
     * Główna metoda programu, która pozwala na wybór opcji i interakcję
     */
    public void controlLoop() {
        int option;

        //Currencies currencies = new Currencies();

        ArrayList<CurrencyRow> currencyRows = new ArrayList<CurrencyRow>();
        ArrayList<CurrencyCol> currencyCols = new ArrayList<CurrencyCol>();

        ArrayList<String> currenciesCodes = new ArrayList<String>();
        LocalDate date_from = LocalDate.now().minusMonths(3);
        LocalDate date_to = LocalDate.now();

        do {

            printOptions();
            option = dataReader.getInt();
            switch (option) {

                case GET_FROM_WEBSITE:
                    System.out.println("GET_FROM_WEBSITE");
                    currencyRows.clear();
                    currencyCols.clear();

                    try {
                        CurrencyRow row;
                        row = websiteControl.parseWebsite();
                        currencyRows.add(row);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    currencyCols = CurrencyLogic.rowsToCols(currencyRows);
                    System.out.println(currencyRows.get(0).getInfo());
                    break;


                case GET_FROM_FILE:
                    System.out.println("GET_FROM_FILE - not supported yet");
                    currencyRows.clear();
                    currencyCols.clear();
                    break;

                case GET_FROM_DATABASE:
                    System.out.println("READ_CURRENCIES_FROM_DATABASE");
                    currencyRows.clear();
                    currencyCols.clear();

                    System.out.println("Podaj date początkową");
                    date_from = dataReader.getDate();
                    System.out.println("Podaj datę końcową");
                    date_to = dataReader.getDate();

                    System.out.println("Selected codes");
                    for (int i = 0; i < currenciesCodes.size(); i++) {
                        System.out.println(currenciesCodes.get(i));
                    }

                    try {
                        currencyCols = databaseControl.readCurrencyCols(currenciesCodes, date_from, date_to);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                    if (currencyCols.size() > 0) {
                        currencyRows = CurrencyLogic.colsToRows(currencyCols);
                    }

                    break;

                case GET_RANDOM:
                    System.out.println("GET_RANDOM");
                    currencyRows.clear();
                    currencyCols.clear();

                    System.out.println("Data od " + date_from + "do " + date_to);
                    System.out.println("Waluty:");
                    for (int i = 0; i < currenciesCodes.size(); i++) {
                        System.out.println(currenciesCodes.get(i));
                    }

                    currencyCols = createTestArrays(currenciesCodes, date_from, date_to);
                    for (int i = 0; i < currencyCols.size(); i++) {
                        System.out.println(currencyCols.get(i).getInfo());
                    }
                    currencyRows = CurrencyLogic.colsToRows(currencyCols);

                    break;

                case PUT_TO_FILE:
                    System.out.println("PUT_TO_FILE - not supported yet");
                    break;

                case PUT_TO_DATABASE:
                    System.out.println("PUT_TO_DATABASE");
                    try {
                        for (int i = 0; i < currencyRows.size(); i++) {
                            databaseControl.insertCurrencyRow(currencyRows.get(i));
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    break;

                case DELETE_FROM_DATABASE:
                    System.out.println("DELETE_FROM_DATABASE");
                    try {
                        databaseControl.deleteCurrencyRows(date_from, date_to);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                    break;

                case SELECT_CURRENCY:
                    System.out.println("Wybierz kody walut");
                    dataReader.getCodes(currenciesCodes);
                    break;

                case SELECT_DATE:
                    System.out.println("Podaj date początkową");
                    date_from = dataReader.getDate();
                    System.out.println("Podaj datę końcową");
                    date_to = dataReader.getDate();
                    break;

                case SHOW:
                    System.out.println("SHOW");
                    if (currencyCols.size() > 0) {
                        for (int i = 0; i < currencyCols.size(); i++) {
                            System.out.println(currencyCols.get(i).getInfo());
                        }
                        JChartTest.JChartExample(currencyCols);
                    } else {
                        System.out.println("No data to show");
                    }
                    break;

                default:
                    System.out.println("Nie ma takiej opcji, wprowadź ponownie: ");
            }
        } while (option != EXIT);
    }

    public ArrayList<CurrencyCol> createTestArrays(ArrayList<String> codes, LocalDate date_from, LocalDate date_to) {
        Random generator = new Random();
        ArrayList<CurrencyCol> currencyCols = new ArrayList<CurrencyCol>();

        for (int i = 0; i < codes.size(); i++) {
            //create new column
            CurrencyCol currencyCol = new CurrencyCol();
            currencyCol.setCode(codes.get(i));

            for (int yearCnt = date_from.getYear(); yearCnt <= date_to.getYear(); yearCnt++) {  //go over years
                for (int monthCnt = date_from.getMonthValue(); monthCnt <= 12; monthCnt++) { //go over months
                    LocalDate dateMonth = LocalDate.of(yearCnt, monthCnt, 1);
                    int lastDayInMonth = dateMonth.withDayOfMonth(dateMonth.lengthOfMonth()).getDayOfMonth();  //go over days
                    for (int dayCnt = 1; dayCnt <= lastDayInMonth; dayCnt++) { //create 10 columns with currency
                        LocalDate date = LocalDate.of(yearCnt, monthCnt, dayCnt);
                        double exchangeRate = generator.nextDouble() * (3) + i * 3;

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

        System.out.println(EXIT + " - EXIT");
        System.out.println(GET_FROM_WEBSITE + " - GET_FROM_WEBSITE");
        System.out.println(GET_FROM_FILE + " - GET_FROM_FILE");
        System.out.println(GET_FROM_DATABASE + " - GET_FROM_DATABASE");
        System.out.println(GET_RANDOM + " - GET_RANDOM");
        System.out.println(PUT_TO_FILE + " - PUT_TO_FILE");
        System.out.println(PUT_TO_DATABASE + " - PUT_TO_DATABASE");
        System.out.println(DELETE_FROM_DATABASE + " - DELETE_FROM_DATABASE");
        System.out.println(SELECT_CURRENCY + " - SELECT_CURRENCY");
        System.out.println(SELECT_DATE + " - SELECT_DATE");
        System.out.println(SHOW + " - SHOW");

    }

    private void exit() {
        System.out.println("Koniec programu, papa!");
        websiteControl.close();
        databaseControl.close();
        dataReader.close();
    }

}
