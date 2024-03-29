package model;

/*
       REMEMBER!!!  ALL TABLES HAVE TO HAVE SAME LENGTH  !!!
 */

import java.time.LocalDate;

public class CurrencyStatics {

    private final static String[] CURRENCIES_CODES = new String[]{
            "thb_1", "usd_1", "aud_1", "hkd_1", "cad_1", "nzd_1", "sgd_1", "eur_1",
            "huf_100", "chf_1", "gbp_1", "uah_1", "jpy_100", "czk_1", "dkk_1", "isk_100",
            "nok_1", "sek_1", "hrk_1", "ron_1", "bgn_1", "try_1", "ils_1", "clp_100",
            "php_1", "mxn_1", "zar_1", "brl_1", "myr_1", "rub_1", "idr_10000", "inr_100",
            "krw_100", "cny_1", "xdr_1",
    };

    private final static String[] CURRENCIES_NAMES = new String[]{
            "bat (Tajlandia)", "dolar amerykański", "dolar australijski", "dolar Hongkongu",
            "dolar kanadyjski", "dolar nowozelandzki", "dolar singapurski", "euro",
            "forint", "frank szwajcarski", "funt szterling", "hrywna (Ukraina)",
            "jen (Japonia)", "korona czeska", "korona duńska", "korona islandzka",
            "korona norweska", "korona szwedzka", "kuna (Chorwacja)", "lej rumuński",
            "lew (Bułgaria)", "lira turecka", "nowy izraelski szekel", "peso chilijskie",
            "peso filipińskie", "peso meksykańskie", "rand (Republika Południowej Afryki)", "real (Brazylia)",
            "ringgit (Malezja)", "rubel rosyjski", "rupia indonezyjska", "rupia indyjska",
            "won południowokoreański", "yuan renminbi (Chiny)", "SDR (MFW)",
    };

    private final static String[] CURRENCIES_SUPPORT = new String[]{
            "1 THB", "1 USD", "1 AUD", "1 HKD", "1 CAD", "1 NZD", "1 SGD", "1 EUR",
            "100 HUF", "1 CHF", "1 GBP", "1 UAH", "100 JPY", "1 CZK", "1 DKK", "100 ISK",
            "1 NOK", "1 SEK", "1 HRK", "1 RON", "1 BGN", "1 TRY", "1 ILS", "100 CLP",
            "1 PHP", "1 MXN", "1 ZAR", "1 BRL", "1 MYR", "1 RUB", "10000 IDR", "100 INR",
            "100 KRW", "1 CNY", "1 XDR",
    };

    private final static String[] CURRENCIES_DEFAULT = new String[]{"usd_1", "eur_1", "gbp_1"};

    public static String[] getDefaultSelection() {
        return CURRENCIES_DEFAULT;
    }

    public static String[] getCurrenciesCodes() {
        return CURRENCIES_CODES;
    }

    public static String[] getCurrenciesNames() {
        return CURRENCIES_NAMES;
    }

    public static String[] getCurrenciesSupport() {
        return CURRENCIES_SUPPORT;
    }
}
