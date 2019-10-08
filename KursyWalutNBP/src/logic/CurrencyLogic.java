package logic;

public class CurrencyLogic {

    private static final String[] CURRENCIES_CODES = new String[]{
            "1 THB", "1 USD", "1 AUD", "1 HKD", "1 CAD", "1 NZD", "1 SGD", "1 EUR",
            "100 HUF", "1 CHF", "1 GBP", "1 UAH", "100 JPY", "1 CZK", "1 DKK",
            "100 ISK", "1 NOK", "1 SEK", "1 HRK", "1 RON", "1 BGN", "1 TRY", "1 ILS",
            "100 CLP", "1 PHP", "1 MXN", "1 ZAR", "1 BRL", "1 MYR", "1 RUB", "10000 IDR",
            "100 INR", "100 KRW", "1 CNY", "1 XDR"
    };

    private static final String[] CURRENCIES_NAMES = new String[]{
            "bat (Tajlandia)", "dolar amerykański", "dolar australijski", "dolar Hongkongu", "dolar kanadyjski",
            "dolar nowozelandzki", "dolar singapurski", "euro", "forint", "frank szwajcarski", "funt szterling",
            "hrywna (Ukraina)", "jen (Japonia)", "korona czeska", "korona duńska", "korona islandzka",
            "korona norweska", "korona szwedzka", "kuna (Chorwacja)", "lej rumuński", "lew (Bułgaria)", "lira turecka",
            "nowy izraelski szekel", "peso chilijskie", "peso filipińskie", "peso meksykańskie", "rand (Republika Południowej Afryki)",
            "real (Brazylia)", "ringgit (Malezja)", "rubel rosyjski", "rupia indonezyjska", "rupia indyjska", "won południowokoreański",
            "yuan renminbi (Chiny)", "SDR (MFW)"
    };

    public int getCurrencyUniqueId(String currencyCode) {
        for (int i = 0; i < CURRENCIES_CODES.length; i++) {
            if (CURRENCIES_CODES[i].equals(currencyCode)) {
                return i; //return unique id of currency
            }
        }
        return 0;
    }

    public String getCurrencyName(int uniqueId) {
        return CURRENCIES_NAMES[uniqueId];
    }
}
