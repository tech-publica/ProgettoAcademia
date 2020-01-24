package net.bitsrl.academia.persistence.repositories.jdbc;

import java.sql.*;

public class ConnectionFactory {
    public static final String URL = "jdbc:mysql://localhost:3306/academia?serverTimezone=UTC";
    public static final String USER = "root";
    public static final String PASSWORD = "mysql";

    public static Connection createConnection() throws SQLException {
        System.out.println("Connessione effettuata!");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
