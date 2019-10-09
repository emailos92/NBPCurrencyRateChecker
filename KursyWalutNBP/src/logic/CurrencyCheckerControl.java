package logic;

import io.DataReader;
import io.DatabaseConnector;
import io.WebsiteReader;
import model.CurrencyTable;

import java.io.IOException;

public class CurrencyCheckerControl {
    // zmienne do kontrolowania programu
    private final static int EXIT = 0;
    private final static int GET_CURRENCY_RATES = 1;
    private final static int SHOW_CURRENCY_RATES = 2;
    private final static int DELETE_CURRENCY_RATES = 3;
    private final static int WRITE_CURRENCY_RATES_TO_DATABASE = 4;

    // zmienna do komunikacji z użytkownikiem
    private DataReader dataReader = new DataReader();
    private WebsiteReader websiteReader = new WebsiteReader();
    private WebsiteParser websiteParser = new WebsiteParser();
    private DatabaseConnector databaseConnector = new DatabaseConnector();
    private CurrencyTable currencyTable = new CurrencyTable();

    /*
     * Główna metoda programu, która pozwala na wybór opcji i interakcję
     */
    public void controlLoop() {
        int option;

        do {
            printOptions();
            option = dataReader.getInt();
            switch (option) {
                case GET_CURRENCY_RATES: //pobierz tabele ze strony www NBP
                    try {
                        currencyTable = websiteParser.parseWebsite(websiteReader.getNewCurrencyList());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    break;
                case SHOW_CURRENCY_RATES: //wyswietl aktualną tabele ze strony www z pamieci programu
                    currencyTable.printCurrencies();
                    break;

                case DELETE_CURRENCY_RATES:
                    currencyTable.setCurrenciesNumber(0);
                    break;

                case WRITE_CURRENCY_RATES_TO_DATABASE:

                    if(!databaseConnector.getConnectionIsOpened()) {
                        databaseConnector.makeConnection();
                    }
                    databaseConnector.closeConnection();
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
        System.out.println(GET_CURRENCY_RATES + " - pobierz tabele kursow z www");
        System.out.println(SHOW_CURRENCY_RATES + " - pokaż pobraną tabele kursów");
        System.out.println(DELETE_CURRENCY_RATES + " - usun z pamieci pobraną tabele kursów");
        System.out.println(WRITE_CURRENCY_RATES_TO_DATABASE + " - zapisz aktualne kursy walut do bazy danych");
    }

    private void exit() {
        System.out.println("Koniec programu, papa!");
        // zamykamy strumień wejścia
        databaseConnector.closeConnection();
        dataReader.close();
    }

}
