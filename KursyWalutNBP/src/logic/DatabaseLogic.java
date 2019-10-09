package logic;

import io.DatabaseConnector;
import model.Currency;
import model.CurrencyList;
import model.CurrencyTable;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class DatabaseLogic {

    CurrencyLogic currencyLogic = new CurrencyLogic();

    public void singleInsert(DatabaseConnector db, String tableName, ArrayList<String> columnsNames, ArrayList<String> columnsValues) throws SQLException {
        if (db.getConnectionState()) {
            PreparedStatement preparedStatement = db.getConnection().prepareStatement("insert into " + tableName + "(" + columnsNames + ") values (" + columnsValues + ")");
            preparedStatement.executeUpdate();
        } else {
            throw new SQLException("Open database connection first");
        }
    }

    public void singleUpdate(DatabaseConnector db, String tableName, ArrayList<String> columnsNames, ArrayList<String> columnsValues, int id) throws SQLException {
        if (db.getConnectionState()) {
            String updateString = new String();
            for (int i = 0; i < columnsNames.size(); i++) {
                updateString += columnsNames.get(i) + "=" + "'" + columnsValues.get(i) + "'";
                if (i != (columnsNames.size() - 1)) {
                    updateString += ", ";
                }
            }
            PreparedStatement preparedStatement = db.getConnection().prepareStatement("update " + tableName + " set " + updateString + " where id=" + id);
            preparedStatement.executeUpdate();
        } else {
            throw new SQLException("Open database connection first");
        }
    }

    public void singleDelete(DatabaseConnector db, String tableName, int id) throws SQLException {
        if (db.getConnectionState()) {
            PreparedStatement preparedStatement = db.getConnection().prepareStatement("delete from " + tableName + " where id=" + id);
            preparedStatement.executeUpdate();
        } else {
            throw new SQLException("Open database connection first");
        }

    }

    public ResultSet singleRead(DatabaseConnector db, String tableName, ArrayList<String> columnsNames, int id) throws SQLException {
        if (db.getConnectionState()) {
            String columnsString = new String();
            for (int i = 0; i < columnsNames.size(); i++) {
                columnsString += columnsNames.get(i);
                if (i != (columnsNames.size() - 1)) {
                    columnsString += ", ";
                }
            }
            PreparedStatement preparedStatement = db.getConnection().prepareStatement("select " + columnsString + " from " + tableName + " where id=" + id);
            return preparedStatement.executeQuery();
        } else {
            throw new SQLException("Open database connection first");
        }
    }

    private void insertCurrency(DatabaseConnector db, Currency currency, LocalDate date) throws SQLException {
        String tableName = currencyLogic.getCurrencySqlTableName(currency.getCode());
        String query = new String();
        query = "insert into " + tableName + " (date, exchangerate) values ('" + Date.valueOf(date) + "', " + currency.getExchangeRate() + ")";
        System.out.println(query);
        PreparedStatement preparedStatement = db.getConnection().prepareStatement(query);
        preparedStatement.executeUpdate();
    }

    private void insertTable(DatabaseConnector db, String uniqueName, LocalDate date) throws SQLException {
        String query = new String();
        query = "insert into tables (date, uniquename) values ('" + Date.valueOf(date) + "', '" + uniqueName + "')";
        System.out.println(query);
        PreparedStatement preparedStatement = db.getConnection().prepareStatement(query);
        preparedStatement.executeUpdate();
    }

    public void insertCurrencyTable(DatabaseConnector db, CurrencyTable currency_table) throws SQLException {
        insertTable(db, currency_table.getUniqueTableName(), currency_table.getDateOfTable());
        while (currency_table.isEmpty() == false) {
            insertCurrency(db, currency_table.getCurrency(), currency_table.getDateOfTable());
        }
    }

    public void deleteCurrencyTable(DatabaseConnector db, LocalDate date) throws SQLException {
        //here we have foreign key 'data' and need to remove only in 'tables', others will automatically delete "on delete cascade"
        PreparedStatement preparedStatement = db.getConnection().prepareStatement("delete from tables where date='" + Date.valueOf(date) + "'");
        preparedStatement.executeUpdate();
    }



//
//    public CurrencyList readCurrency() {
//
//    }
//
//    public ArrayList<Currency> readCurrencies() {
//
//    }

//    public CurrencyList readCurrencies(LocalDate data_from, LocalDate data_to, String currency_code){
//
//    }
//
//    public ArrayList<CurrencyList> readCurrencies(LocalDate data_from, LocalDate data_to, ArrayList<String> currencies_codes){
//
//    }

    public void close(DatabaseConnector db) {
        db.closeConnection();
    }

}
