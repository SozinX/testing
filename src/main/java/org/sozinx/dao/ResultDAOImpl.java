package org.sozinx.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sozinx.model.Result;
import org.sozinx.model.Test;
import org.sozinx.model.User;
import org.sozinx.service.ConnectionService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import static org.sozinx.constant.QueryConst.*;

public class ResultDAOImpl implements ResultDAO {
    private static final Logger LOGGER = LogManager.getLogger(String.valueOf(ResultDAOImpl.class));
    private final UserDAO userManager;
    private final TestDAO testManager;

    public ResultDAOImpl() {
        userManager = new UserDAOImpl();
        testManager = new TestDAOImpl();
    }

    @Override
    public List<Result> getResultByUser(User user) {
        List<Result> result = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_RESULT_BY_USER);
            statement.setLong(1, user.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.add(new Result(resultSet.getInt("id"), resultSet.getString("date"), resultSet.getInt("result"),
                        user, testManager.getTestById(resultSet.getLong("test"))));
            }
        } catch (SQLException e) {
            LOGGER.info("Query getResultByUser failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return result;
    }

    @Override
    public List<Result> getResultByTest(Test test) {
        List<Result> result = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_RESULT_BY_TEST);
            statement.setLong(1, test.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.add(new Result(resultSet.getInt("id"), resultSet.getString("date"), resultSet.getInt("result"),
                        userManager.getUserById(resultSet.getInt("user")), test));
            }
        } catch (SQLException e) {
            LOGGER.info("Query getResultByTest failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return result;
    }

    @Override
    public Result getResultByUserAndTest(User user, Test test) {
        Result result = null;
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_RESULT_BY_USER_AND_TEST);
            statement.setLong(1, user.getId());
            statement.setLong(2, test.getId());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = new Result(resultSet.getInt("id"), resultSet.getString("date"),
                        resultSet.getInt("result"), user, test);
            }
        } catch (SQLException e) {
            LOGGER.info("Query getResultByUserAndTest failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return result;
    }

    @Override
    public boolean addResult(Result result) {
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(ADD_RESULT);
            statement.setLong(1, result.getUser().getId());
            statement.setLong(2, result.getTest().getId());
            statement.setString(3, result.getDate());
            statement.setInt(4, result.getResult());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.info("Query addResult failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return false;
    }

    @Override
    public boolean deleteResult(Result result) {
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(DELETE_RESULT);
            statement.setLong(1, result.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.info("Query deleteResult failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return false;
    }

    @Override
    public boolean updateResult(Result result, int points) {
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(UPDATE_RESULT);
            statement.setInt(1, points);
            statement.setLong(2, result.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.info("Query updateResult failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return false;
    }

    @Override
    public boolean deleteResultByUser(User user) {
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(DELETE_RESULT_BY_USER);
            statement.setLong(1, user.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.info("Query deleteResultByUser failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return false;
    }
}
