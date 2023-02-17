package org.sozinx.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sozinx.model.Role;
import org.sozinx.service.ConnectionService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.sozinx.constant.QueryConst.GET_ROLE_BY_ID;

public class RoleDAOImpl implements RoleDAO {
    private static final Logger LOGGER = LogManager.getLogger(String.valueOf(RoleDAOImpl.class));

    @Override
    public Role getRoleById(int id) {
        Role role = null;
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_ROLE_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                role = new Role(resultSet.getInt(1), resultSet.getString(2));
            }
        } catch (SQLException e) {
            LOGGER.info("Query getRoleById failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return role;
    }
}
