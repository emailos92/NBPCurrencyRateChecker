package app;

import logic.CurrencyCheckerControl;

public class CurrencyCheckerApp {
    private static final String APP_NAME = "CurrencyRate Checker V0.1";

    public static void main(String[] args) {
        System.out.println(APP_NAME);

        CurrencyCheckerControl currencyCheckerControl = new CurrencyCheckerControl();
        currencyCheckerControl.controlLoop();
    }
}
