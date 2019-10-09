package logic;

import io.DatabaseConnector;
import model.Currency;
import model.CurrencyList;
import model.CurrencyTable;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class DatabaseControl {
    DatabaseLogic databaseLogic = new DatabaseLogic();
    DatabaseConnector databaseConnector = new DatabaseConnector();

    public void insertCurrencyTable(CurrencyTable currencyTable) throws SQLException {
        databaseConnector.openConnection();
        databaseLogic.insertCurrencyTable(databaseConnector,currencyTable);
        databaseConnector.openConnection();
    }

    public void deleteCurrencyTables(LocalDate date) throws SQLException {
        databaseConnector.openConnection();
        databaseLogic.deleteCurrencyTable(databaseConnector,date);
        databaseConnector.openConnection();
    }

    public CurrencyList readCurrencyList(String tableName, LocalDate date_from, LocalDate date_to) throws SQLException {
        CurrencyList currencyList = new CurrencyList();
        databaseConnector.openConnection();
        currencyList = databaseLogic.readCurrencyList(databaseConnector,tableName,date_from,date_to);
        databaseConnector.closeConnection();

        return currencyList;
    }

    public ArrayList<CurrencyList> readCurrenciesLists(ArrayList<String> currenciesTableNames, LocalDate date_from, LocalDate date_to) throws SQLException {
        ArrayList<CurrencyList> arrayListOfCurrencyList;
        databaseConnector.openConnection();
        arrayListOfCurrencyList = databaseLogic.readArrayListOfCurrencyList(databaseConnector,currenciesTableNames,date_from,date_to);
        databaseConnector.openConnection();
        return arrayListOfCurrencyList;
    }

    public void close(){
        databaseLogic.close(databaseConnector);
    }

    //maybe later need to implement other functions
}
