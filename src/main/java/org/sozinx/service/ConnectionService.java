package org.sozinx.service;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Connection service is the service that give the connection pool for DAO classes. In this method was used Hikari for connection pool.
 *
 * @author Ostap Petruniak
 * @since 1.0
 */
public class ConnectionService {
    private static final Logger LOGGER = LogManager.getLogger(String.valueOf(ConnectionService.class));
    private static final HikariConfig config = new HikariConfig();
    private static final HikariDataSource dataSource;

    static {
        config.setJdbcUrl("jdbc:mysql://localhost:3306/testing");//link to database
        config.setUsername("root");//username which having database
        config.setPassword("010203QwErT@");//user's password in sql
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");//connection driver
        config.setMaximumPoolSize(20);//maximum of pool size
        config.addDataSourceProperty("cachePrepStmts", "true");//cache prepared statements given true
        config.addDataSourceProperty("prepStmtCacheSize", "250");//maximum cache size
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");//maximum sql limit
        dataSource = new HikariDataSource(config);
        LOGGER.info("Config successfully created...");
    }

    private ConnectionService() {
    }

    /**
     * Getting connection.
     *
     * @return object Connection which using in DAO
     */
    public static synchronized Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            LOGGER.info("Connection failed... ");
            return null;
        }
    }

    /**
     * Closing connection.
     *
     * @param closeable object AutClosable that will close the connection
     */
    public static void close(AutoCloseable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                LOGGER.info("Something went wrong after closing try...");
            }
        }
    }
}