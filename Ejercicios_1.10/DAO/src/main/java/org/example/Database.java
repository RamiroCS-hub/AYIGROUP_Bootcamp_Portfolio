package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private Connection conn;
    Database(String connection, String user, String pass) throws SQLException {
        this.conn = DriverManager.getConnection(connection, user, pass);
    }

    public Connection getConnection() {
        return conn;
    }

    public void close() throws SQLException {
        conn.close();
    }
}
