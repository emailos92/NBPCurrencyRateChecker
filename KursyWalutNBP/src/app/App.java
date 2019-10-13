package app;

import logic.Control;

public class App {
    private static final String APP_NAME = "CurrencyRate Checker V0.1";

    public static void main(String[] args) {
        System.out.println(APP_NAME);

        Control control = new Control();
        control.controlLoop();
    }
}
