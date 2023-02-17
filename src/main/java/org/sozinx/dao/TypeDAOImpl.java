package org.sozinx.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sozinx.model.Type;
import org.sozinx.service.ConnectionService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.sozinx.constant.QueryConst.GET_TYPE_BY_ID;

public class TypeDAOImpl implements TypeDAO {
    private static final Logger LOGGER = LogManager.getLogger(String.valueOf(RoleDAOImpl.class));

    @Override
    public Type getTypeById(int id) {
        Type type = null;
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TYPE_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                type = new Type(resultSet.getInt(1), resultSet.getString(2));
            }
        } catch (SQLException e) {
            LOGGER.info("Query getTypeById failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return type;
    }
}
