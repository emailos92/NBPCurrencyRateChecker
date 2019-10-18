package logic;

import gui.JChartTest;
import io.DataReader;
import io.ExcelReader;
import io.WebsiteReader;
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
    private final static int SELECT_CURRENCIES = 9;
    private final static int SELECT_DATES = 10;
    private final static int SHOW = 11;

    // zmienne do komunikacji z użytkownikiem
    private DataReader dataReader = new DataReader();
    private ExcelReader excelReader = new ExcelReader();
    private WebsiteReader websiteReader = new WebsiteReader();
    private DatabaseControl databaseControl = new DatabaseControl();

    // lokalne dane aplikacji
    private Currencies currencies = new Currencies();

    private LocalDate dateFrom = LocalDate.now().minusMonths(3);
    private LocalDate dateTo = LocalDate.now();

    // dane widoku / zaznaczenia
    private CurrencyDates selectedDates = new CurrencyDates();
    private CurrencyCodes selectedCodes = new CurrencyCodes(CurrencyStatics.getCurrenciesCodes(),
            CurrencyStatics.getCurrenciesNames(),CurrencyStatics.getCurrenciesSupport(),
            CurrencyStatics.getDefaultSelection());

    /*
    =================================================================
    Główna metoda programu, która pozwala na wybór opcji i interakcję
    =================================================================
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
                        row = websiteReader.readAndParseNBPWebsite(selectedCodes);
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

                    System.out.println(selectedCodes.toString());
                    System.out.println(selectedDates.toString());

                    try {
                        currencies.setCols(databaseControl.readCurrencyCols(selectedCodes, selectedDates));
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                    //print read currencies
                    for(int i=0;i<currencies.getCols().size();i++){
                        System.out.println(currencies.getCols().get(i).toString());
                    }

                    break;

                case GET_RANDOM:
                    System.out.println("GET_RANDOM");
                    currencies.clear();

                    currencies.setCols(createTestArrays(selectedCodes, selectedDates));
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
                    currencies.clear();
                    break;

                case DELETE_FROM_DATABASE:
                    System.out.println("DELETE_FROM_DATABASE");
                    try {
                        databaseControl.deleteCurrencyRows(selectedDates);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                    break;

                case SELECT_SHOW:
                    System.out.println(selectedDates.toString());
                    System.out.println(selectedCodes.toString());
                    break;

                case SELECT_CURRENCIES:
                    dataReader.getCodes(selectedCodes);
                    break;

                case SELECT_DATES:
                    dataReader.getDates(selectedDates);
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

    private CurrencyCols createTestArrays(CurrencyCodes codes, CurrencyDates dates) {
        Random generator = new Random();
        CurrencyCols cols = new CurrencyCols();
        ArrayList<String> selectedCodes = codes.getSelectedCodes();
        LocalDate date_from = LocalDate.of(dates.getDateFrom().getYear(),dates.getDateFrom().getMonth(),dates.getDateFrom().getDay());
        LocalDate date_to = LocalDate.of(dates.getDateTo().getYear(),dates.getDateTo().getMonth(),dates.getDateTo().getDay());

        for (int i = 0; i < selectedCodes.size(); i++) {
            //create new column
            CurrencyCol currencyCol = new CurrencyCol();
            currencyCol.setCode(selectedCodes.get(i));

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
        System.out.println(SELECT_CURRENCIES + " - SELECT_CURRENCY");
        System.out.println(SELECT_DATES + " - SELECT_DATE");
        System.out.println(SHOW + " - SHOW");

    }

    private void exit() {
        System.out.println("Koniec programu, papa!");
        databaseControl.close();
        dataReader.close();
    }

}
