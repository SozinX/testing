package org.sozinx.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sozinx.model.Block;
import org.sozinx.model.User;
import org.sozinx.service.ConnectionService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import static org.sozinx.constant.QueryConst.*;


public class BlockDAOImpl implements BlockDAO {
    private static final Logger LOGGER = LogManager.getLogger(String.valueOf(RoleDAOImpl.class));
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
            while (resultSet.next()) {
                block.add(new Block(resultSet.getLong("id"), user,
                        userManager.getUserById(resultSet.getLong("teacher")),
                        resultSet.getString("block"), resultSet.getString("unblock")));
            }
        } catch (SQLException e) {
            LOGGER.info("Query getBlockByUser failed...");
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
            LOGGER.info("Query blockUser failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return false;
    }

    @Override
    public boolean unblockUser(Block block, String unblock) {
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(UNBLOCK_USER);
            statement.setString(1, unblock);
            statement.setLong(2, block.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.info("Query unblockUser failed...");
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
            LOGGER.info("Query deleteBlockByUser failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return false;
    }
}
