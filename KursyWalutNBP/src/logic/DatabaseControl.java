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

    public void close(){
        databaseLogic.close(databaseConnector);
    }

    //maybe later need to implement other functions
}
