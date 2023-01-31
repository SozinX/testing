package org.sozinx.service;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConnectionService {
    private static final Logger LOGGER = Logger.getLogger(String.valueOf(ConnectionService.class));

    public static synchronized Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/testing?user=root&password=010203QwErT@");
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Connection failed... {0}", e.toString());
            return null;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(AutoCloseable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                LOGGER.log(Level.INFO, "Something went wrong after closing try...");
            }
        }
    }
}