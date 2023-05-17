package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    private final String URL = "jdbc:mysql://localhost:3306/mydb1";
    private final String USER = "root";
    private final String PASSWORD = "root";
    private Connection connection;

    public Util() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
