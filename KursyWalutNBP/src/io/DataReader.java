package io;

import model.Currency;
import model.CurrencyCodes;
import model.CurrencyDates;
import model.SimpleDate;

import java.time.LocalDate;
import java.util.Scanner;

public class DataReader {

    private static final int SELECT_FROM = 1;
    private static final int SELECT_TO = 2;
    private static final int SELECT_BOTH = 3;

    private static final int PLUS_DAY = 1;
    private static final int PLUS_MONTH = 2;
    private static final int PLUS_YEAR = 3;
    private static final int MINUS_DAY = -1;
    private static final int MINUS_MONTH = -2;
    private static final int MINUS_YEAR = -3;

    private Scanner sc = new Scanner(System.in);

    public void close() {
        sc.close();
    }

    public int getInt() {
        int number = sc.nextInt();
        sc.nextLine();
        return number;
    }

    /*
    ============================================
    SELECT DATES TO VIEW / PARSE / OTHER ACTIONS
    ============================================
    */

    private SimpleDate changeDate(SimpleDate date, int option) {
        LocalDate loc = LocalDate.of(date.getYear(),date.getMonth(),date.getDay());

        switch (option) {
            case PLUS_DAY:
                loc = LocalDate.of(date.getYear(),date.getMonth(),date.getDay()).plusDays(1);
                break;
            case PLUS_MONTH:
                loc = LocalDate.of(date.getYear(),date.getMonth(),date.getDay()).plusMonths(1);
                break;
            case PLUS_YEAR:
                loc = LocalDate.of(date.getYear(),date.getMonth(),date.getDay()).plusYears(1);
                break;
            case MINUS_DAY:
                loc = LocalDate.of(date.getYear(),date.getMonth(),date.getDay()).minusDays(1);
                break;
            case MINUS_MONTH:
                loc = LocalDate.of(date.getYear(),date.getMonth(),date.getDay()).minusMonths(1);
                break;
            case MINUS_YEAR:
                loc = LocalDate.of(date.getYear(),date.getMonth(),date.getDay()).minusYears(1);
                break;
            default:
                break;
        }

        System.out.println(date.toString());

        return new SimpleDate(loc.getYear(),loc.getMonthValue(),loc.getDayOfMonth());
    }

    public void getDates(CurrencyDates dates) {

        LocalDate start = LocalDate.of(2000,1,1);
        LocalDate end = LocalDate.now();
        SimpleDate startDate = new SimpleDate(start.getYear(),start.getMonthValue(),start.getDayOfMonth());
        SimpleDate endDate = new SimpleDate(end.getYear(),end.getMonthValue(),end.getDayOfMonth());

        SimpleDate tmpFrom = new SimpleDate();
        SimpleDate tmpTo = new SimpleDate();

        int option, option2;
        do {
            System.out.print("0 - EXIT\n1 - SELECT FROM\n2 - SELECT TO\n3 - SELECT BOTH\n");
            System.out.println(dates.toString());
            option = getInt();

            if (option > 0) {
                do {
                    System.out.print("0 - EXIT\n-1 / 1 - DAY\n-2 / 2 - MONTH\n-3 / 3 - YEAR\n");
                    System.out.println(dates.toString());

                    //save dates here
                    tmpFrom = dates.getDateFrom();
                    tmpTo = dates.getDateTo();

                    System.out.println("TMP FROM: " + tmpFrom.toString() + " || TMP_TO: " + tmpTo.toString());

                    option2 = getInt();

                    if (option == SELECT_FROM) {
                        dates.setDateFrom(changeDate(dates.getDateFrom(), option2));
                    } else if (option == SELECT_TO) {
                        dates.setDateTo(changeDate(dates.getDateTo(), option2));
                    } else if (option == SELECT_BOTH) {
                        if (option2 > 0) //+++
                            dates.setDateFrom(changeDate(dates.getDateFrom(), option2));
                        else if (option2 < 0)// ---
                            dates.setDateTo(changeDate(dates.getDateTo(), option2));
                    }

                    if (option2 > 0) { // ++++ (DAY, MONTH, YEAR)
                        if (option == SELECT_FROM) { //we can simple plus dateFrom up to dateTo, when dateFrom > dateTo, revert changes
                            if (dates.getDateFrom().isAfter(dates.getDateTo())) {
                                dates.setDateFrom(tmpFrom);
                                System.out.println("SELECT_FROM " + option2 + " revert " + dates.getDateFrom().toString());
                            }
                        } else if (option == SELECT_TO) { //we can simple plus dateTo to dateNow;
                            if (dates.getDateTo().isAfter(endDate)) {
                                dates.setDateTo(tmpTo);
                                System.out.println("SELECT_TO " + option2 + " revert " + dates.getDateTo().toString());
                            }
                        } else {  //we can simply plus dates to dateNow
                            if (dates.getDateTo().isAfter(endDate)) {
                                dates.setDateTo(tmpTo); //revert changes
                                System.out.println("SELECT_BOTH " + option2 + " revert " + dates.getDateTo().toString());
                            } else {
                                dates.setDateFrom(LocalDate.of(dates.getDateTo().getYear(), dates.getDateTo().getMonth(), dates.getDateTo().getDay())); //put changes to dateTo too :)
                                System.out.println("SELECT_BOTH " + option2 + " put to date from too " + dates.getDateTo().toString());
                            }
                        }
                    }
//                    else if (option2 < 0) { // ---- (DAY, MONTH, YEAR)
//                        if (option == SELECT_FROM) { //we can simple minus dateFrom to 2000.01.01;
//                            if (dates.getDateFrom().isBefore(startDate)) {
//                                dates.setDateFrom(tmpFrom);
//                            }
//                        } else if (option == SELECT_TO) { //we can simple minus dateTo to dateFrom
//                            if (dates.getDateTo().isBefore(dates.getDateTo())) {
//                                dates.setDateTo(tmpTo);
//                            }
//                        } else {  //we can simply minus dates to 2000.01.01;
//                            if (dates.getDateFrom().isBefore(startDate)) {
//                                dates.setDateFrom(tmpFrom); //revert changes
//                            } else {
//                                dates.setDateTo(dates.getDateFrom().getYear(), dates.getDateFrom().getMonth(), dates.getDateFrom().getDay()); //put changes to dateTo too :)
//                            }
//                        }
//                    }
                } while (option2 != 0);
            }
        } while (option != 0);
    }


    /*
    ============================================
    SELECT CODES TO VIEW / PARSE / OTHER ACTIONS
    ============================================
     */

    public void getCodes(CurrencyCodes currencyCodes) {
        int code_idx;
        System.out.println("Wybierz kody walut:");
        do {
            System.out.print("0 - deselect all\n" + (currencyCodes.size() + 1) + " - select all\n-1 - close\n* - selected\n");
            for (int i = 0; i < currencyCodes.size(); i++) { // all codes
                if (currencyCodes.isSelected(currencyCodes.getCurrenciesCodes()[i])) {
                    System.out.println((i + 1) + " - ( * ) " + currencyCodes.getCurrenciesCodes()[i]);
                } else {
                    System.out.println((i + 1) + " - (   ) " + currencyCodes.getCurrenciesCodes()[i]);
                }
            }
            code_idx = getInt();
            if (code_idx > 0) {
                if (code_idx > currencyCodes.size()) { //select all
                    currencyCodes.selectAll();
                } else { //select - deselect
                    int code_idx_fixed = code_idx - 1;
                    currencyCodes.change(code_idx_fixed);
                }
            } else if (code_idx == 0) {
                currencyCodes.deselectAll();
            }
        } while (code_idx >= 0);
    }
}
