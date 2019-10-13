package io;

import logic.CurrencyLogic;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class DataReader {
    private Scanner sc = new Scanner(System.in);

    public void close() {
        sc.close();
    }

    public int getInt() {
        int number = sc.nextInt();
        sc.nextLine();
        return number;
    }

    public LocalDate getDate() {

        int year, month, day;

        System.out.println("Podaj rok");
        year = getInt();
        System.out.println("Podaj miesiąc");
        month = getInt();
        System.out.println("Podaj dzień");
        day = getInt();

        return LocalDate.of(year, month, day);
    }

    public ArrayList<String> getCodes(ArrayList<String> codes) {

        int option;
        do {
            CurrencyLogic.printCurrenciesOptions(codes);
            option = getInt();
            CurrencyLogic.selectDeselectCodes(codes, option);
        } while (option > 0);

        return codes;
    }
}
