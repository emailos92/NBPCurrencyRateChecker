package logic;

import io.DatabaseConnector;
import model.CurrencyCol;
import model.CurrencyRow;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class DatabaseControl {
    DatabaseLogic databaseLogic = new DatabaseLogic();
    DatabaseConnector databaseConnector = new DatabaseConnector();

    public void insertCurrencyRow(CurrencyRow currencyRow) throws SQLException {
        databaseConnector.openConnection();
        databaseLogic.insertCurrencyRow(databaseConnector, currencyRow);
        databaseConnector.closeConnection();
    }

    public void insertCurrencyRows(ArrayList<CurrencyRow> currencyRows) throws SQLException {
        databaseConnector.openConnection();
        for (int i = 0; i < currencyRows.size(); i++) {
            databaseLogic.insertCurrencyRow(databaseConnector, currencyRows.get(i));
        }
        databaseConnector.closeConnection();
    }

    public void deleteCurrencyRow(LocalDate date) throws SQLException {
        databaseConnector.openConnection();
        databaseLogic.deleteCurrencyRow(databaseConnector, date);
        databaseConnector.closeConnection();
    }

    public void deleteCurrencyRows(LocalDate date_from, LocalDate date_to) throws SQLException {
        databaseConnector.openConnection();
        databaseLogic.deleteCurrencyRows(databaseConnector, date_from, date_to);
        databaseConnector.closeConnection();
    }

    public CurrencyCol readCurrencyColByDate(String code, LocalDate date_from, LocalDate date_to) throws SQLException {
        CurrencyCol currencyCol = new CurrencyCol();
        databaseConnector.openConnection();
        currencyCol = databaseLogic.readCurrencyColByDate(databaseConnector, code, date_from, date_to);
        databaseConnector.closeConnection();

        return currencyCol;
    }

    public ArrayList<CurrencyCol> readCurrencyCols(ArrayList<String> codes, LocalDate date_from, LocalDate date_to) throws SQLException {
        databaseConnector.openConnection();
        ArrayList<CurrencyCol> arrayListOfCurrencyCol = databaseLogic.readCurrencyColsByDate(databaseConnector, codes, date_from, date_to);
        databaseConnector.closeConnection();
        return arrayListOfCurrencyCol;
    }

    public void close() {
        databaseLogic.close(databaseConnector);
    }

    //maybe later need to implement other functions
}
