package org.sozinx.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sozinx.model.Level;
import org.sozinx.service.ConnectionService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.sozinx.constant.QueryConst.GET_LEVEL_BY_ID;


public class LevelDAOImpl implements LevelDAO {
    private static final Logger LOGGER = LogManager.getLogger(String.valueOf(RoleDAOImpl.class));

    @Override
    public Level getLevelById(int id) {
        Level level = null;
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_LEVEL_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                level = new Level(resultSet.getInt(1), resultSet.getString(2));
            }
        } catch (SQLException e) {
            LOGGER.info("Query getLevelById failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return level;
    }
}
