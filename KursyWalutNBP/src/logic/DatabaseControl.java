package logic;

import io.DatabaseConnector;
import model.*;

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

    public void insertCurrencyRows(CurrencyRows rows) throws SQLException {
        databaseConnector.openConnection();
        for (int i = 0; i < rows.size(); i++) {
            databaseLogic.insertCurrencyRow(databaseConnector, rows.get(i));
        }
        databaseConnector.closeConnection();
    }

    public void insertCurrencyCols(CurrencyCols cols) throws SQLException {
        databaseConnector.openConnection();
        for (int i = 0; i < cols.size(); i++) {
            if(i==0){
                databaseLogic.insertTableCol(databaseConnector, cols.get(i));
            }
            databaseLogic.insertCurrencyCol(databaseConnector, cols.get(i));
        }
        databaseConnector.closeConnection();
    }

    public void deleteCurrencyRow(LocalDate date) throws SQLException {
        databaseConnector.openConnection();
        databaseLogic.deleteCurrencyRow(databaseConnector, date);
        databaseConnector.closeConnection();
    }

    public void deleteCurrencyRows(CurrencyDates dates) throws SQLException {
        databaseConnector.openConnection();
        databaseLogic.deleteCurrencyRows(databaseConnector, dates);
        databaseConnector.closeConnection();
    }

    public CurrencyCol readCurrencyColByDate(String code, CurrencyDates dates) throws SQLException {
        CurrencyCol currencyCol = new CurrencyCol();
        databaseConnector.openConnection();
        currencyCol = databaseLogic.readCurrencyColByDate(databaseConnector, code, dates);
        databaseConnector.closeConnection();

        return currencyCol;
    }

    public CurrencyCols readCurrencyCols(CurrencyCodes codes, CurrencyDates dates) throws SQLException {
        databaseConnector.openConnection();
        CurrencyCols cols = databaseLogic.readCurrencyColsByDate(databaseConnector, codes, dates);
        databaseConnector.closeConnection();
        return cols;
    }

    public void close() {
        databaseLogic.close(databaseConnector);
    }

    //maybe later need to implement other functions
}
