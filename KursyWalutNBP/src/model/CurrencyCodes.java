package model;

import java.util.ArrayList;

public class CurrencyCodes {

    private String[] currenciesCodes;  //cod waluty oraz nazwa tabeli w bazie danych
    private String[] currenciesNames;  //pelna nazwa dla uzytkownika
    private String[] currenciesSupport; //lista supportowanych walut, kilka kodów do jednej waltu itp ...
    private boolean[] selectedCodes;
    private int size;

    public CurrencyCodes(String[] currenciesCodes, String[] currenciesNames, String[] currenciesSupport, String[] defaultSelectedCodes) {
        if ((currenciesCodes.length > 0) &&
                (currenciesCodes.length == currenciesNames.length) &&
                (currenciesCodes.length == currenciesSupport.length)) {
            size = currenciesCodes.length;
            this.currenciesCodes = currenciesCodes;
            this.currenciesNames = currenciesNames;
            this.currenciesSupport = currenciesSupport;
            selectedCodes = new boolean[size];
            System.out.println(defaultSelectedCodes.length);
            for (int i = 0; i < defaultSelectedCodes.length; i++) {
                selectedCodes[getIdFromCode(defaultSelectedCodes[i])] = true;
            }
            //Print default selection
            System.out.print("Default currency selection: ");
            for(int i=0;i <size;i++){
                if(selectedCodes[i]){
                    System.out.print(currenciesCodes[i] + " : ");
                }
            }
            System.out.print("\n");
        } else {
            throw new IllegalArgumentException("Wrong size of constant currency tables (code, name, support)");
        }
    }

    /*
    ====== MAIN OPERATION ======
     */

    public String[] getCurrenciesCodes() {
        return currenciesCodes;
    }

    public String[] getCurrenciesNames() {
        return currenciesNames;
    }

    public String[] getCurrenciesSupport() {
        return currenciesSupport;
    }

    public int size() {
        return size;
    }

    public ArrayList<String> getSelectedCodes() {
        ArrayList<String> codes = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            if (selectedCodes[i]) {
                codes.add(currenciesCodes[i]);
            }
        }
        return codes;
    }


    /*
    ====== SINGLE OPERATIONS ======
     */

    public int getIdFromCode(String code) {
        for (int i = 0; i < size; i++) {
            if (currenciesCodes[i].equals(code)) {
                return i;
            }
        }
        return -1;
    }

    public int getIdFromSupport(String support) {
        for (int i = 0; i < size; i++) {
            if (currenciesSupport[i].equals(support)) {
                return i;
            }
        }
        return -1;
    }

    public String getCode(int code_id) {
        if ((code_id >= 0) && (code_id < size)) {
            return currenciesCodes[code_id];
        }

        return null;
    }

    public String getCode(String support) {
        int code_id = getIdFromSupport(support);
        if ((code_id >= 0) && (code_id < size)) {
            return currenciesCodes[code_id];
        }

        return null;
    }

    public String getName(int code_id) {
        if ((code_id >= 0) && (code_id < size)) {
            return currenciesNames[code_id];
        }

        return null;
    }

    public String getName(String code) {
        int code_id = getIdFromCode(code);
        if ((code_id >= 0) && (code_id < size)) {
            return currenciesNames[code_id];
        }

        return null;
    }

    public boolean checkSupport(String support){
        for(int i=0;i<size;i++){
            if(currenciesSupport[i].equals(support)){
                return true;
            }
        }
        return false;
    }

    /*
    ====== SELECT / DESELECT ======
     */

    public boolean isSelected(String code){
        return selectedCodes[getIdFromCode(code)];
    }

    public void change(int code_id) {
        if ((code_id >= 0) && (code_id < size)) {
            if (selectedCodes[code_id]) {
                selectedCodes[code_id] = false;
            } else {
                selectedCodes[code_id] = true;
            }
        }
    }

    public void change(String code) {
        int code_id = getIdFromCode(code);
        change(code_id);
    }

    public void select(int code_id) {
        if ((code_id >= 0) && (code_id < size)) {
            selectedCodes[code_id] = true;
        }
    }

    public void select(String code) {
        int code_id = getIdFromCode(code);
        select(code_id);
    }

    public void selectAll() {
        for (int i = 0; i < size; i++) {
            selectedCodes[i] = true;
        }
    }

    public void deselect(int code_id) {
        if ((code_id >= 0) && (code_id < size)) {
            selectedCodes[code_id] = false;
        }
    }

    public void deselect(String code) {
        int code_id = getIdFromCode(code);
        deselect(code_id);
    }

    public void deselectAll() {
        for (int i = 0; i < size; i++) {
            selectedCodes[i] = false;
        }
    }

    /*
    ====== DEBUGS ======
     */

    public String toString() {
        String info = new String();
        info += "Selected codes: ";
        for (int i = 0; i < size; i++) {
            if (selectedCodes[i]) {
                info += currenciesCodes[i] + " : ";
            }
        }
        return info;
    }
}
