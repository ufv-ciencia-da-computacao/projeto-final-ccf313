package persistence.mysql;

import com.mysql.jdbc.Driver;

import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

public class ConnectionFactory {
    public static final String URL = "jdbc:mysql://ec2-3-15-169-104.us-east-2.compute.amazonaws.com:3306/tpfinalpoo";
    public static final String USER = "poo";
    public static final String PASS = "tpfinal";

    public static Connection getConnection() {
        try {
            DriverManager.registerDriver(new Driver());
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException ex) {
            throw new RuntimeException("Error connecting to the database", ex);
        }
    }
}
