package org.example.onlineBankingSystem.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;

public class DBConfig {
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try (InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties")) {
                Properties props = new Properties();
                props.load(input);

                String url = props.getProperty("db.url");
                String username = props.getProperty("db.username");
                String password = props.getProperty("db.password");

                connection = DriverManager.getConnection(url, username, password);
                connection.setAutoCommit(false);

                System.out.println("Database connection established successfully!");
            } catch (Exception e) {
                throw new SQLException("Unable to connect to the database", e);
            }
        }
        return connection;
    }
}
