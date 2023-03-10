package org.sozinx.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sozinx.model.User;
import org.sozinx.service.ConnectionService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.sozinx.constant.QueryConst.*;

public class UserDAOImpl implements UserDAO {
    private static final Logger LOGGER = LogManager.getLogger(String.valueOf(UserDAOImpl.class));
    private final RoleDAO manager;

    public UserDAOImpl() {
        manager = new RoleDAOImpl();
    }

    @Override
    public User getUserById(long id) {
        User user = null;
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_USER_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("email"), resultSet.getString("password"),
                        resultSet.getString("registration"), manager.getRoleById(resultSet.getInt("role")));
            }
        } catch (SQLException e) {
            LOGGER.info("Query getUserById failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        User user = null;
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_USER_BY_EMAIL);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("email"), resultSet.getString("password"),
                        resultSet.getString("registration"), manager.getRoleById(resultSet.getInt("role")));
            }
        } catch (SQLException e) {
            LOGGER.info("Query getUserByEmail failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return user;
    }

    @Override
    public boolean addUser(User user) {
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(ADD_USER);
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getRegistration());
            statement.setInt(5, user.getRole().getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.info("Query addUser failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return false;
    }

    @Override
    public boolean deleteUser(User user) {
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(DELETE_USER);
            statement.setLong(1, user.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.info("Query deleteUser failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return false;
    }

    //changing local object User for updating
    private void localChangeUser(User user, String[] params) {
        user.setName(params[0]);
        user.setEmail(params[1]);
        user.setRole(manager.getRoleById(Integer.parseInt(params[2])));
        user.setPassword(params[3]);
    }

    @Override
    public boolean updateUser(User user, String[] params) {
        Connection connection = ConnectionService.getConnection();
        localChangeUser(user, params);
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(UPDATE_USER);
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setInt(4, user.getRole().getId());
            statement.setString(3, user.getPassword());
            statement.setLong(5, user.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.info("Query updateUser failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return false;
    }
}