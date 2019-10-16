package logic;

import gui.JChartTest;
import io.DataReader;
import io.ExcelReader;
import model.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
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
    private final static int SELECT_SHOW = 8;
    private final static int SELECT_CURRENCY = 9;
    private final static int SELECT_DATE = 10;
    private final static int SHOW = 11;

    // zmienna do komunikacji z użytkownikiem
    private DataReader dataReader = new DataReader();
    private ExcelReader excelReader = new ExcelReader();
    private WebsiteControl websiteControl = new WebsiteControl();
    private DatabaseControl databaseControl = new DatabaseControl();
    private Currencies currencies = new Currencies();
    private CurrencyDates dates = new CurrencyDates();
    private CurrencyCodes codes = new CurrencyCodes();

    /*
     * Główna metoda programu, która pozwala na wybór opcji i interakcję
     */
    public void controlLoop() {
        int option;

        do {

            printOptions();
            option = dataReader.getInt();
            switch (option) {
                case EXIT:
                    System.out.println("Pa Pa :)");
                    exit();
                    break;
                case GET_FROM_WEBSITE:
                    System.out.println("GET_FROM_WEBSITE");
                    currencies.clear();
                    try {
                        CurrencyRow row;
                        row = websiteControl.parseWebsite();
                        currencies.getRows().add(row);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    break;

                case GET_FROM_FILE:
                    currencies.clear();

                    try {
                        File temp = new File("test"); //File.createTempFile("i-am-a-temp-file", ".tmp");
                        String absolutePath = temp.getAbsolutePath();
                        //System.out.println("File path : " + absolutePath);
                        String filePath = absolutePath.substring(0, absolutePath.lastIndexOf(File.separator));
                        filePath = absolutePath.substring(0, filePath.lastIndexOf(File.separator));
                        filePath += "/Archiwum/test.xls";
                        System.out.println("File path : " + filePath);
                        excelReader.parseExcel(filePath);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                    break;

                case GET_FROM_DATABASE:
                    System.out.println("READ_CURRENCIES_FROM_DATABASE");
                    currencies.clear();

                    System.out.println("Selected codes");
                    System.out.println(codes.toString());

                    try {
                        currencies.setCols(databaseControl.readCurrencyCols(codes, dates));
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                    break;

                case GET_RANDOM:
                    System.out.println("GET_RANDOM");
                    currencies.clear();

                    currencies.setCols(createTestArrays(codes.getSelectedCodes(), dates.getFrom(), dates.getTo()));
                    for (int i = 0; i < currencies.getCols().size(); i++) {
                        System.out.println(currencies.getCols().get(i).toString());
                    }

                    break;

                case PUT_TO_FILE:
                    System.out.println("PUT_TO_FILE - not supported yet");
                    break;

                case PUT_TO_DATABASE:
                    System.out.println("PUT_TO_DATABASE");
                    try {
                        databaseControl.insertCurrencyRows(currencies.getRows());
                        databaseControl.insertCurrencyCols(currencies.getCols());
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    break;

                case DELETE_FROM_DATABASE:
                    System.out.println("DELETE_FROM_DATABASE");
                    try {
                        databaseControl.deleteCurrencyRows(dates);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                    break;

                case SELECT_SHOW:
                    System.out.println(dates.toString());
                    System.out.println(codes.toString());
                    break;

                case SELECT_CURRENCY:
                    System.out.println("Wybierz kody walut");
                    //warning, poprawic dataReader.getCodes
                    dataReader.getCodes(codes.getSelectedCodes());
                    break;

                case SELECT_DATE:
                    System.out.println("Podaj date początkową");
                    dates.setFrom(dataReader.getDate());
                    System.out.println("Podaj datę końcową");
                    dates.setTo(dataReader.getDate());
                    break;

                case SHOW:
                    System.out.println("SHOW");
                    if (currencies.getCols().size() > 0) {
                        for (int i = 0; i < currencies.getCols().size(); i++) {
                            System.out.println(currencies.getCols().get(i).toString());
                        }
                        JChartTest.JChartExample(currencies.getCols().get());
                    } else {
                        System.out.println("No data to show");
                    }
                    break;

                default:
                    System.out.println("Nie ma takiej opcji, wprowadź ponownie: ");
            }
        } while (option != EXIT);
    }

    private CurrencyCols createTestArrays(ArrayList<String> codes, LocalDate date_from, LocalDate date_to) {
        Random generator = new Random();
        CurrencyCols cols = new CurrencyCols();

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
            cols.add(currencyCol);
        }
        return cols;
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
        System.out.println(SELECT_SHOW + " - SELECT_SHOW");
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
