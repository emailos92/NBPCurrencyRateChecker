package model;

import java.util.ArrayList;

public class ViewCodes {

    private final static String[] CURRENCIES_CODES = new String[]{
            "1 THB", "1 USD", "1 AUD", "1 HKD", "1 CAD", "1 NZD", "1 SGD", "1 EUR",
            "100 HUF", "1 CHF", "1 GBP", "1 UAH", "100 JPY", "1 CZK", "1 DKK", "100 ISK",
            "1 NOK", "1 SEK", "1 HRK", "1 RON", "1 BGN", "1 TRY", "1 ILS", "100 CLP",
            "1 PHP", "1 MXN", "1 ZAR", "1 BRL", "1 MYR", "1 RUB", "10000 IDR", "100 INR",
            "100 KRW", "1 CNY", "1 XDR",
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

    private final static String[] CURRENCIES_DB_TABLES = new String[]{
            "thb_1", "usd_1", "aud_1", "hkd_1", "cad_1", "nzd_1", "sgd_1", "eur_1",
            "huf_100", "chf_1", "gbp_1", "uah_1", "jpy_100", "czk_1", "dkk_1", "isk_100",
            "nok_1", "sek_1", "hrk_1", "ron_1", "bgn_1", "try_1", "ils_1", "clp_100",
            "php_1", "mxn_1", "zar_1", "brl_1", "myr_1", "rub_1", "idr_10000", "inr_100",
            "krw_100", "cny_1", "xdr_1",
    };

    private boolean[] currencyCodes = new boolean[CURRENCIES_CODES.length];


    public ArrayList<String> getSelectedCodes() {
        ArrayList<String> codes = new ArrayList<>();

        for(int i=0;i<CURRENCIES_CODES.length;i++){
            if(currencyCodes[i]){
                codes.add(CURRENCIES_CODES[i]);
            }
        }
        return codes;
    }

    public int getId(String code){
        for(int i=0;i<CURRENCIES_CODES.length;i++){
            if(CURRENCIES_CODES[i].equals(code)){
                return i;
            }
        }
        return -1;
    }

    public String getCode(int code_id){
        if((code_id >= 0) && (code_id < CURRENCIES_CODES.length)) {
            return CURRENCIES_CODES[code_id];
        }

        return null;
    }

    public String getName(int code_id){
        if((code_id >= 0) && (code_id < CURRENCIES_CODES.length)) {
            return CURRENCIES_NAMES[code_id];
        }

        return null;
    }

    public String getSqlTable(int code_id){
        if((code_id >= 0) && (code_id < CURRENCIES_CODES.length)) {
            return CURRENCIES_DB_TABLES[code_id];
        }

        return null;
    }

    public void selectAll() {
        for (int i = 0; i < currencyCodes.length; i++) {
            currencyCodes[i]=true;
        }
    }

    public void select(int code_id) {
        if((code_id >= 0) && (code_id < CURRENCIES_CODES.length)) {
            currencyCodes[code_id] = true;
        }
    }

    public void select(String code) {
        int code_id = getId(code);
        select(code_id);
    }

    public void deselect(int code_id) {
        if((code_id >= 0) && (code_id < CURRENCIES_CODES.length)) {
            currencyCodes[code_id] = false;
        }
    }

    public void deselect(String code) {
        int code_id = getId(code);
        deselect(code_id);
    }

    public void change(int code_id){
        if((code_id >= 0) && (code_id < CURRENCIES_CODES.length)) {
            if(currencyCodes[code_id]){
                currencyCodes[code_id] = false;
            } else {
                currencyCodes[code_id] = true;
            }
        }
    }

    public void change(String code){
        int code_id = getId(code);
        change(code_id);
    }

    public void clear() {
        for (int i = 0; i < currencyCodes.length; i++) {
            currencyCodes[i]=false;
        }
    }

    public String getInfo(){
        String info = new String();
        for(int i =0;i<CURRENCIES_CODES.length;i++){
            if(currencyCodes[i]){
                info += CURRENCIES_CODES[i] + " : ";
            }
        }
        return info;
    }
}
