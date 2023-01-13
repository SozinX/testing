package org.sozinx.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.sozinx.constant.AddressConst.SETTINGS_FILE;

public class ConnectionService {
    private static final Logger LOGGER = Logger.getLogger(String.valueOf(ConnectionService.class));
    private static final Properties properties = new Properties();

    static {
        try {
            properties.load(Files.newInputStream(Path.of(SETTINGS_FILE)));
        }catch (IOException e){
            LOGGER.log(Level.INFO, "No such file for connection...");
        }
    }
    public static synchronized Connection getConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(properties.getProperty("connection.url"));
        }catch (SQLException e){
            LOGGER.log(Level.INFO, "Connection failed... {0}", e.toString());
            return null;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static void close(AutoCloseable closeable){
        if(closeable != null){
            try {
                closeable.close();
            }catch (Exception e){
                LOGGER.log(Level.INFO, "Something went wrong after closing try...");
            }
        }
    }
}