package logic;

import io.DataReader;
import io.WebsiteReader;
import model.CurrencyList;
import model.CurrencyTable;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class CurrencyCheckerControl {
    // zmienne do kontrolowania programu
    private final static int EXIT = 0;
    private final static int GET_CURRENCY_RATES_FROM_WEBSITE = 1;
    private final static int PRINT_INFO_OF_CURRENCY_RATES = 2;
    private final static int GET_AND_PRINT_NEXT_CURRENCY = 3;
    private final static int DELETE_CURRENCY_RATES = 4;
    private final static int WRITE_CURRENCY_TABLE_TO_DATABASE = 5;
    private final static int DELETE_CURRENCY_TABLE_FROM_DATABASE = 6;
    private final static int READ_CURRENCY_LIST = 7;

    // zmienna do komunikacji z użytkownikiem
    private DataReader dataReader = new DataReader();
    private WebsiteControl websiteControl = new WebsiteControl();
    private DatabaseControl databaseControl = new DatabaseControl();
    private CurrencyTable currencyTable = new CurrencyTable();
    private CurrencyList currencyList = new CurrencyList();
    private CurrencyLogic currencyLogic = new CurrencyLogic();

    /*
     * Główna metoda programu, która pozwala na wybór opcji i interakcję
     */
    public void controlLoop() {
        int option;

        do {
            printOptions();
            option = dataReader.getInt();
            switch (option) {
                case GET_CURRENCY_RATES_FROM_WEBSITE: //pobierz tabele ze strony www NBP
                    try {
                        currencyTable = websiteControl.parseWebsite();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    break;
                case PRINT_INFO_OF_CURRENCY_RATES: //wyswietl aktualną tabele ze strony www z pamieci programu
                    System.out.println(currencyTable.getInfo());
                    break;

                case GET_AND_PRINT_NEXT_CURRENCY:
                    if (!currencyTable.isEmpty()) {
                        System.out.println(currencyTable.getCurrency().getInfo());
                    } else {
                        System.out.println("No more currency in table");
                    }
                    break;
                case DELETE_CURRENCY_RATES:
                    currencyTable.clear();
                    break;

                case WRITE_CURRENCY_TABLE_TO_DATABASE:
                    try {
                        databaseControl.insertCurrencyTable(currencyTable);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    break;
                case DELETE_CURRENCY_TABLE_FROM_DATABASE:
                    try {
                        databaseControl.deleteCurrencyTables(LocalDate.of(2019, 10, 9));
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    break;
                case READ_CURRENCY_LIST:
                    try {
                        currencyList = databaseControl.readCurrencyList("aud_1",LocalDate.of(2019, 10, 9),LocalDate.of(2019, 10, 9));
                        System.out.println(currencyList.getInfo());
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    break;
                case EXIT:
                    exit();
                    break;
                default:
                    System.out.println("Nie ma takiej opcji, wprowadź ponownie: ");
            }
        } while (option != EXIT);
    }

    private void printOptions() {
        System.out.println("Wybierz opcję: ");
        System.out.println(EXIT + " - wyjście z programu");
        System.out.println(GET_CURRENCY_RATES_FROM_WEBSITE + " - pobierz tabele kursow z www");
        System.out.println(PRINT_INFO_OF_CURRENCY_RATES + " - pokaż pobraną tabele kursów");
        System.out.println(GET_AND_PRINT_NEXT_CURRENCY + " - pobierz nastepną walute i usun z tabeli");
        System.out.println(DELETE_CURRENCY_RATES + " - usun pobraną tabele kursów");
        System.out.println(WRITE_CURRENCY_TABLE_TO_DATABASE + " - zapisz aktualne kursy walut do bazy danych");
        System.out.println(DELETE_CURRENCY_TABLE_FROM_DATABASE + " - usun tabele kursów z bazy danych");
        System.out.println(READ_CURRENCY_LIST + " - odczytaj listę kursów z okresu dat");
    }

    private void exit() {
        System.out.println("Koniec programu, papa!");
        websiteControl.close();
        databaseControl.close();
        dataReader.close();
    }

}
