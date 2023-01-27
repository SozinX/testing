package org.sozinx.dao;

import org.sozinx.model.User;
import org.sozinx.service.ConnectionService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.sozinx.constant.QueryConst.*;

public class UserDAOImpl implements UserDAO {
    private static final Logger LOGGER = Logger.getLogger(String.valueOf(UserDAOImpl.class));
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
            LOGGER.log(Level.INFO, "Query failed...");
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
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return user;
    }

    @Override
    public void addUser(User user) {
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
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...{0}", e.toString());
        } finally {
            ConnectionService.close(connection);
        }
    }

    @Override
    public void deleteUser(User user) {
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(DELETE_USER);
            statement.setLong(1, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
    }

    //changing local object User for updating
    private void localChangeUser(User user, String[] params) {
        user.setName(params[0]);
        user.setEmail(params[1]);
        user.setRole(manager.getRoleById(Integer.parseInt(params[2])));
        user.setPassword(params[3]);
    }

    @Override
    public void updateUser(User user, String[] params) {
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
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
    }
}
