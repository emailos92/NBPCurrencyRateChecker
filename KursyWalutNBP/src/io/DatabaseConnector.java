package io;

import java.sql.*;

public class DatabaseConnector {

    private final static String DB_STRING = "jdbc:postgresql://localhost:5432/ea";
    private final static String DB_USER = "ea";
    private final static String DB_PASS = "ea@";

    private Connection connection;
    private boolean connectionIsOpened;

    public boolean getConnectionState() {
        return connectionIsOpened;
    }

    public Connection getConnection() {
        return connection;
    }

    public void openConnection() {
        if (!connectionIsOpened) {
            try {
                connection = DriverManager.getConnection(DB_STRING, DB_USER, DB_PASS);
                connectionIsOpened = true;
                //System.out.println("Connected to PostgreSQL database!");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void closeConnection() {
        if(connectionIsOpened){
            try {
                connection.close();
                connectionIsOpened = false;
                //System.out.println("Disconnected from PostgreSQL database ;)");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }


}