package logic;

import io.DatabaseConnector;
import model.*;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class DatabaseLogic {

//    public void singleInsert(DatabaseConnector db, String tableName, ArrayList<String> columnsNames, ArrayList<String> columnsValues) throws SQLException {
//        if (db.getConnectionState()) {
//            PreparedStatement preparedStatement = db.getConnection().prepareStatement("insert into " + tableName + "(" + columnsNames + ") values (" + columnsValues + ")");
//            preparedStatement.executeUpdate();
//        } else {
//            throw new SQLException("Open database connection first");
//        }
//    }
//
//    public void singleUpdate(DatabaseConnector db, String tableName, ArrayList<String> columnsNames, ArrayList<String> columnsValues, int id) throws SQLException {
//        if (db.getConnectionState()) {
//            String updateString = new String();
//            for (int i = 0; i < columnsNames.size(); i++) {
//                updateString += columnsNames.get(i) + "=" + "'" + columnsValues.get(i) + "'";
//                if (i != (columnsNames.size() - 1)) {
//                    updateString += ", ";
//                }
//            }
//            PreparedStatement preparedStatement = db.getConnection().prepareStatement("update " + tableName + " set " + updateString + " where id=" + id);
//            preparedStatement.executeUpdate();
//        } else {
//            throw new SQLException("Open database connection first");
//        }
//    }
//
//    public void singleDelete(DatabaseConnector db, String tableName, int id) throws SQLException {
//        if (db.getConnectionState()) {
//            PreparedStatement preparedStatement = db.getConnection().prepareStatement("delete from " + tableName + " where id=" + id);
//            preparedStatement.executeUpdate();
//        } else {
//            throw new SQLException("Open database connection first");
//        }
//
//    }
//
//    public ResultSet singleRead(DatabaseConnector db, String tableName, ArrayList<String> columnsNames, int id) throws SQLException {
//        if (db.getConnectionState()) {
//            String columnsString = new String();
//            for (int i = 0; i < columnsNames.size(); i++) {
//                columnsString += columnsNames.get(i);
//                if (i != (columnsNames.size() - 1)) {
//                    columnsString += ", ";
//                }
//            }
//            PreparedStatement preparedStatement = db.getConnection().prepareStatement("select " + columnsString + " from " + tableName + " where id=" + id);
//            return preparedStatement.executeQuery();
//        } else {
//            throw new SQLException("Open database connection first");
//        }
//    }

    private void insertTableElem(DatabaseConnector db, LocalDate date) throws SQLException {
        //first check we have table with this date in sql ;) create if not
        String query = "insert into tables (date) values ('" + Date.valueOf(date) + "')";
        //System.out.println(query);
        PreparedStatement preparedStatement = db.getConnection().prepareStatement(query);
        preparedStatement.executeUpdate();
    }

    private void insertCurrencyElem(DatabaseConnector db, String code, double exchangeRate, LocalDate date) throws SQLException {
        String query = "insert into " + code + " (date, exchangerate) values ('" + Date.valueOf(date) + "', " + exchangeRate + ")";
        //System.out.println(query);
        PreparedStatement preparedStatement = db.getConnection().prepareStatement(query);
        preparedStatement.executeUpdate();
    }

    public void insertCurrencyRow(DatabaseConnector db, CurrencyRow currencyRow) throws SQLException {
        insertTableElem(db, currencyRow.getDate());
        for (int i = 0; i < currencyRow.size(); i++) {
            insertCurrencyElem(db, currencyRow.get(i).getCode(), currencyRow.get(i).getExchangeRate(), currencyRow.getDate());
        }
    }

    public void insertTableCol(DatabaseConnector db, CurrencyCol currencyCol) throws SQLException {
        for (int i = 0; i < currencyCol.size(); i++) {
            insertTableElem(db, currencyCol.get(i).getDate());
        }
    }

    public void insertCurrencyCol(DatabaseConnector db, CurrencyCol currencyCol) throws SQLException {
        for (int i = 0; i < currencyCol.size(); i++) {
            insertCurrencyElem(db, currencyCol.getCode(), currencyCol.get(i).getExchangeRate(), currencyCol.get(i).getDate());
        }
    }


    public void deleteCurrencyRow(DatabaseConnector db, LocalDate date) throws SQLException {
        //here we have foreign key 'data' and need to remove only in 'tables', others will automatically delete "on delete cascade"
        PreparedStatement preparedStatement = db.getConnection().prepareStatement("delete from tables where date='" + Date.valueOf(date) + "'");
        preparedStatement.executeUpdate();
    }

    public void deleteCurrencyRows(DatabaseConnector db, CurrencyDates dates) throws SQLException {
        //here we have foreign key 'data' and need to remove only in 'tables', others will automatically delete "on delete cascade"
        PreparedStatement preparedStatement = db.getConnection().prepareStatement(
                "delete from tables where date>='"
                        + Date.valueOf(LocalDate.of(dates.getDateFrom().getYear(), dates.getDateFrom().getMonth(), dates.getDateFrom().getDay()))
                        + "' and date<='"
                        + Date.valueOf(LocalDate.of(dates.getDateTo().getYear(), dates.getDateTo().getMonth(), dates.getDateTo().getDay())) + "'");
        preparedStatement.executeUpdate();
    }

    public CurrencyCol readCurrencyCol(DatabaseConnector db, String code) throws SQLException {
        CurrencyCol currencyCol = new CurrencyCol();

        PreparedStatement preparedStatement = db.getConnection().prepareStatement("select * from " + code);
        ResultSet result = preparedStatement.executeQuery();
        while (result.next()) {
            String name = result.getString("uniquename");
            LocalDate date = result.getDate("date").toLocalDate();
            double exchangeRate = result.getDouble("exchangerate");
            //System.out.println(date + "\t" + exchangeRate);
            currencyCol.add(new CurrencyColElem(date, exchangeRate));
        }

        return currencyCol;
    }

    public CurrencyCols readCurrencyCols(DatabaseConnector db, CurrencyCodes codes) throws SQLException {
        CurrencyCols cols = new CurrencyCols();
        for (int i = 0; i < codes.getSelectedCodes().size(); i++) {
            cols.add(readCurrencyCol(db, codes.getSelectedCodes().get(i)));
        }

        return cols;
    }

    public CurrencyCol readCurrencyColByDate(DatabaseConnector db, String code, CurrencyDates dates) throws SQLException {
        CurrencyCol currencyCol = new CurrencyCol();
        String query = "select * from " + code + " where date >= '"
                + Date.valueOf(LocalDate.of(dates.getDateFrom().getYear(), dates.getDateFrom().getMonth(), dates.getDateFrom().getDay()))
                + "' and date <= '"
                + Date.valueOf(LocalDate.of(dates.getDateTo().getYear(),dates.getDateTo().getMonth(),dates.getDateTo().getDay())) + "'";
        PreparedStatement preparedStatement = db.getConnection().prepareStatement(query);
        ResultSet result = preparedStatement.executeQuery();

        currencyCol.setCode(code);
        while (result.next()) {
            LocalDate date = result.getDate("date").toLocalDate();
            double exchangeRate = result.getDouble("exchangerate");
            //System.out.println(date + "\t" + exchangeRate);
            currencyCol.add(new CurrencyColElem(date, exchangeRate));
        }
        //System.out.println(currencyCol.getInfo());

        return currencyCol;
    }

    public CurrencyCols readCurrencyColsByDate(DatabaseConnector db, CurrencyCodes codes, CurrencyDates dates) throws SQLException {
        CurrencyCols cols = new CurrencyCols();
        for (int i = 0; i < codes.getSelectedCodes().size(); i++) {
            cols.add(readCurrencyColByDate(db, codes.getSelectedCodes().get(i), dates));
        }

        return cols;
    }

    public void close(DatabaseConnector db) {
        db.closeConnection();
    }


}
