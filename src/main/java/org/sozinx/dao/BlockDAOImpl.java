package org.sozinx.dao;

import org.sozinx.model.Block;
import org.sozinx.model.User;
import org.sozinx.service.ConnectionService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.sozinx.constant.QueryConst.*;


public class BlockDAOImpl implements BlockDAO {
    private static final Logger LOGGER = Logger.getLogger(String.valueOf(RoleDAOImpl.class));
    private final UserDAO userManager;

    public BlockDAOImpl() {
        userManager = new UserDAOImpl();
    }

    @Override
    public List<Block> getBlockByUser(User user) {
        List<Block> block = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_BLOCK_BY_USER);
            statement.setLong(1, user.getId());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                block.add(new Block(resultSet.getLong("id"), user,
                        userManager.getUserById(resultSet.getLong("teacher")),
                        resultSet.getString("block"), resultSet.getString("unblock")));
            }
        } catch (SQLException e) {
            LOGGER.log(java.util.logging.Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return block;
    }

    @Override
    public boolean blockUser(Block block) {
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(BLOCK_USER);
            statement.setLong(1, block.getTeacher().getId());
            statement.setLong(2, block.getStudent().getId());
            statement.setString(3, block.getBlock());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...{0}", e.toString());
        } finally {
            ConnectionService.close(connection);
        }
        return false;
    }

    @Override
    public boolean unblockUser(Block block, String unblock) {
        Connection connection = ConnectionService.getConnection();
        block.setUnblock(unblock);
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(UNBLOCK_USER);
            statement.setString(1, block.getUnblock());
            statement.setLong(3, block.getTeacher().getId());
            statement.setLong(2, block.getStudent().getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return false;
    }

    @Override
    public boolean deleteBlockByUser(User user) {
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(DELETE_BLOCK_BY_USER);
            statement.setLong(1, user.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return false;
    }
}
