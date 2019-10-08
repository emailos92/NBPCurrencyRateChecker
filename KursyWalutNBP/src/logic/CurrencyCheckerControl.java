package logic;

import io.DataReader;
import io.WebsiteReader;

import java.io.IOException;

public class CurrencyCheckerControl {
    // zmienne do kontrolowania programu
    private final static int EXIT = 0;
    private final static int GET_CURRENCY_RATES = 1;
    private final static int SHOW_CURRENCY_RATES = 2;
    private final static int WRITE_CURRENCY_RATES = 3;

    // zmienna do komunikacji z użytkownikiem
    private DataReader dataReader = new DataReader();
    private WebsiteReader websiteReader = new WebsiteReader();

    /*
     * Główna metoda programu, która pozwala na wybór opcji i interakcję
     */
    public void controlLoop() {
        int option;

        do {
            printOptions();
            option = dataReader.getInt();
            switch (option) {
                case GET_CURRENCY_RATES:

                    try {
                        websiteReader.getNewCurrencyList();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                    System.out.println("Pobierz tabele ze strony www i zapisz do bazy danych");
                    break;
                case SHOW_CURRENCY_RATES:

                    System.out.println("Wyświetl pobraną tabele kursów");
                    break;
                case WRITE_CURRENCY_RATES:

                    System.out.println("Zapisz tabele kursów do bazy danych");
                    break;
                case EXIT:
                    exit();
                    break;
                default:
                    System.out.println("Nie ma takiej opcji, wprowadź ponownie: ");
            }
        } while(option != EXIT);
    }

    private void printOptions() {
        System.out.println("Wybierz opcję: ");
        System.out.println(EXIT + " - wyjście z programu");
        System.out.println(GET_CURRENCY_RATES + " - zaaktualizuj kursy walut");
        System.out.println(SHOW_CURRENCY_RATES + " - pokaż aktualne kursy walut");
        System.out.println(WRITE_CURRENCY_RATES + " - zapisz aktualne kursy walut do bazy danych");
    }

    private void exit() {
        System.out.println("Koniec programu, papa!");
        // zamykamy strumień wejścia
        dataReader.close();
    }

}
