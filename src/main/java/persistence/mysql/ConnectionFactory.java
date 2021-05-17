package persistence.mysql;

import com.mysql.jdbc.Driver;

import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

public class ConnectionFactory {
    public static final String URL = "jdbc:mysql://localhost:3306/tpfinalpoo";
    public static final String USER = "tpfinal";
    public static final String PASS = "poo";

    public static Connection getConnection() {
        try {
            DriverManager.registerDriver(new Driver());
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException ex) {
            throw new RuntimeException("Error connecting to the database", ex);
        }
    }
}
