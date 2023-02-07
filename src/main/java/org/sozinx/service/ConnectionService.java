package org.sozinx.service;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConnectionService {
    private static final Logger LOGGER = Logger.getLogger(String.valueOf(ConnectionService.class));

    private static final HikariConfig config = new HikariConfig();
    private static final HikariDataSource dataSource;

    static {
        config.setJdbcUrl("jdbc:mysql://localhost:3306/testing");
        config.setUsername("root");
        config.setPassword("010203QwErT@");
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setMaximumPoolSize(20);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        dataSource = new HikariDataSource(config);
    }

    private ConnectionService() {
    }

    public static synchronized Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Connection failed... {0}", e.toString());
            return null;
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