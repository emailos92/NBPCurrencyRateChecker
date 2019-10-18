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
        LocalDate loc = LocalDate.of(date.getYear(), date.getMonth(), date.getDay());

        switch (option) {
            case PLUS_DAY:
                loc = LocalDate.of(date.getYear(), date.getMonth(), date.getDay()).plusDays(1);
                break;
            case PLUS_MONTH:
                loc = LocalDate.of(date.getYear(), date.getMonth(), date.getDay()).plusMonths(1);
                break;
            case PLUS_YEAR:
                loc = LocalDate.of(date.getYear(), date.getMonth(), date.getDay()).plusYears(1);
                break;
            case MINUS_DAY:
                loc = LocalDate.of(date.getYear(), date.getMonth(), date.getDay()).minusDays(1);
                break;
            case MINUS_MONTH:
                loc = LocalDate.of(date.getYear(), date.getMonth(), date.getDay()).minusMonths(1);
                break;
            case MINUS_YEAR:
                loc = LocalDate.of(date.getYear(), date.getMonth(), date.getDay()).minusYears(1);
                break;
            default:
                break;
        }

        return new SimpleDate(loc.getYear(), loc.getMonthValue(), loc.getDayOfMonth());
    }

    private void printDates(SimpleDate from, SimpleDate to) {
        System.out.println("\n===================================");
        System.out.println("( " + from.toDateString() + " ) <===> ( " + to.toDateString() + " )");
        System.out.println("===================================");
    }

    public void getDates(CurrencyDates dates) {

        LocalDate start = LocalDate.of(2000, 1, 1);
        LocalDate end = LocalDate.now(); //LocalDate.now()
        SimpleDate startDate = new SimpleDate(start.getYear(), start.getMonthValue(), start.getDayOfMonth());
        SimpleDate endDate = new SimpleDate(end.getYear(), end.getMonthValue(), end.getDayOfMonth());
        SimpleDate tmpFrom = new SimpleDate();
        SimpleDate tmpTo = new SimpleDate();

        int option, option2;
        do {

            printDates(dates.getDateFrom(), dates.getDateTo());
            System.out.print(" Which date do you want to change?\n 0 - EXIT\n 1 - FROM\n 2 - TO\n 3 - BOTH\n");

            option = getInt();
            if (option > 0) {
                do {
                    //save old dates here
                    tmpFrom.set(dates.getDateFrom());
                    tmpTo.set(dates.getDateTo());
                    System.out.println("TMP: FROM " + tmpFrom.toDateString() + " TO " + tmpTo.toDateString());

                    printDates(dates.getDateFrom(), dates.getDateTo());

                    System.out.print("  0 - EXIT\n" + "  1 - ( + ) DAY\n" + " -1 - ( - ) DAY\n" + "  2 - ( + ) MONTH\n" +
                            " -2 - ( - ) MONTH\n" + "  3 - ( + ) YEAR\n" + " -3 - ( - ) YEAR\n");
                    option2 = getInt();

                    if (option == SELECT_FROM) {
                        System.out.println("Changeing date from:");
                        dates.setDateFrom(changeDate(dates.getDateFrom(), option2));
                    } else if (option == SELECT_TO) {
                        System.out.println("Changing date to:");
                        dates.setDateTo(changeDate(dates.getDateTo(), option2));
                    } else if (option == SELECT_BOTH) {
                        System.out.println("Changing both dates:");
                        if (option2 > 0) //+++
                            dates.setDateTo(changeDate(dates.getDateTo(), option2));
                        else if (option2 < 0)// ---
                            dates.setDateFrom(changeDate(dates.getDateFrom(), option2));
                    }

                    System.out.println("CHG: FROM " + dates.getDateFrom().toDateString() + " TO " + dates.getDateTo().toDateString());

                    if (option2 > 0) { // ++++ (DAY, MONTH, YEAR)
                        if (option == SELECT_FROM) { //we can simple plus dateFrom up to dateTo, when dateFrom > dateTo, revert changes
                            if (dates.getDateFrom().isAfter(dates.getDateTo())) {
                                System.out.println("Can't increment date from: (from) <= (to)");
                                dates.setDateFrom(tmpFrom);
                            }
                        } else if (option == SELECT_TO) { //we can simple plus dateTo to dateNow;
                            if (dates.getDateTo().isAfter(endDate)) {
                                System.out.println("Can't increment date to: (to) <= (" + endDate.toDateString() + ")");
                                dates.setDateTo(tmpTo);
                            }
                        } else if (option == SELECT_BOTH) {  //we can simply plus dates to dateNow
                            if (dates.getDateTo().isAfter(endDate)) {
                                System.out.println("Can't increment both dates: (to) <= (" + endDate.toDateString() + ")");
                                dates.setDateTo(tmpTo); //revert changes
                                dates.setDateFrom(tmpTo); //revert changes
                            } else {
                                dates.setDateFrom(dates.getDateTo());
                            }
                        }
                    }
                    else if (option2 < 0) { // ---- (DAY, MONTH, YEAR)
                        if (option == SELECT_FROM) { //we can simple minus dateFrom to 2000.01.01;
                            if (dates.getDateFrom().isBefore(startDate)) {
                                System.out.println("Can't decrement date from: (" + startDate.toDateString() + ") <= (to)");
                                dates.setDateFrom(tmpFrom);
                            }
                        } else if (option == SELECT_TO) { //we can simple minus dateTo to dateFrom
                            if (dates.getDateTo().isBefore(dates.getDateTo())) {
                                System.out.println("Can't decrement date to: (from) <= (to)");
                                dates.setDateTo(tmpTo);
                            }
                        } else if (option == SELECT_BOTH){  //we can simply minus dates to 2000.01.01;
                            if (dates.getDateFrom().isBefore(startDate)) {
                                System.out.println("Can't decrement both dates: (" + startDate.toDateString() + ") <= (to)");
                                dates.setDateFrom(tmpFrom); //revert changes
                                dates.setDateTo(tmpFrom); //revert changes
                            } else {
                                dates.setDateTo(dates.getDateFrom()); //put changes to dateTo too :)
                            }
                        }
                    }

                    System.out.println("LST: FROM " + dates.getDateFrom().toDateString() + " TO " + dates.getDateTo().toDateString());
                } while (option2 != 0);
            }
        } while (option > 0);
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
