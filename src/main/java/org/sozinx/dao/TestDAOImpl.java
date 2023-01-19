package org.sozinx.dao;

import org.sozinx.model.Test;
import org.sozinx.service.ConnectionService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.sozinx.constant.QueryConst.*;

public class TestDAOImpl implements TestDAO {
    private static final Logger LOGGER = Logger.getLogger(String.valueOf(UserDAOImpl.class));
    private final UserDAO userManager;
    private final LevelDAO levelManager;

    public TestDAOImpl() {
        userManager = new UserDAOImpl();
        levelManager = new LevelDAOImpl();
    }

    @Override
    public Test getTestById(long id) {
        Test test = null;
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                test = new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level")));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return test;
    }

    @Override
    public boolean addTest(Test test) {
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(ADD_TEST);
            statement.setLong(1, test.getOwner().getId());
            statement.setInt(2, test.getLevel().getId());
            statement.setString(3, test.getName());
            statement.setString(4, test.getSubject());
            statement.setString(5, test.getCreated());
            statement.setInt(6, test.getIsClose());
            statement.setInt(7, test.getTime());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...{0}", e.toString());
        } finally {
            ConnectionService.close(connection);
        }
        return false;
    }

    private void localChangeTest(Test test, String[] params) {
        test.setName(params[0]);
        test.setSubject(params[1]);
        test.setIsClose(Integer.parseInt(params[2]));
        test.setTime(Integer.parseInt(params[3]));
        test.setLevel(levelManager.getLevelById(Integer.parseInt(params[4])));
    }

    @Override
    public boolean updateTest(Test test, String[] params) {
        Connection connection = ConnectionService.getConnection();
        localChangeTest(test, params);
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(UPDATE_TEST);
            statement.setString(1, test.getName());
            statement.setString(2, test.getSubject());
            statement.setInt(3, test.getIsClose());
            statement.setInt(4, test.getTime());
            statement.setInt(5, test.getLevel().getId());
            statement.setLong(6, test.getId());
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
    public boolean addPopularity(Test test) {
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(ADD_POPULARITY);
            statement.setLong(1, test.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...{0}", e.toString());
        } finally {
            ConnectionService.close(connection);
        }
        return false;
    }

    private String getQuery(String name, String subject, String level, String orderColumn, String order, String page) {
        if(page == null){
            page = "1";
        }
        int records = (Integer.parseInt(page) - 1) * 12;
        if(name == null && subject == null && orderColumn == null && level == null && order == null){
            return "SELECT * FROM test WHERE is_close = 0 ORDER BY name ASC LIMIT " + records + ", 12;";
        }
        else {
            StringBuilder startString = new StringBuilder("SELECT * FROM test ");
            String orderString = "ORDER BY " + orderColumn + " ASC ";
            if (Objects.equals(order, "0")) {
                orderString = "ORDER BY " + orderColumn + " DESC ";
            }
            if (Objects.equals(name, null) && Objects.equals(subject, null) && Objects.equals(level, "0")) {
                return startString + "WHERE is_close = 0 "+ orderString;
            }
            startString.append("WHERE is_close = 0 ");
            if (!Objects.equals(name, null)) {
                startString.append("AND name LIKE '%").append(name).append("%' ");
            }
            if (!Objects.equals(subject, null) && startString.length() > 30) {
                startString.append("AND subject LIKE '%").append(subject).append("%' ");
            } else if (!Objects.equals(subject, "") && startString.length() < 30) {
                startString.append("subject LIKE '%").append(subject).append("%' ");
            }
            if (!Objects.equals(level, "0") && !Objects.equals(level, "") && startString.length() > 30) {
                startString.append("AND level = ").append(level).append(" ");
            } else if (!Objects.equals(level, "0") && startString.length() < 30) {
                startString.append("level = ").append(level).append(" ");
            }
            System.out.println(startString + orderString + "LIMIT " + records + ", 12;");
            return startString + orderString + "LIMIT " + records + ", 12;";
        }
    }

    @Override
    public List<Test> getFilerResult(String name, String subject, String level, String orderColumn, String order, String page) {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(getQuery(name, subject, level, orderColumn, order, page));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level"))));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...{0}", e.toString());
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }

    private String getAllFilterRecordsQuery(String name, String subject, String level, String orderColumn, String order) {
        if(name == null && subject == null && orderColumn == null && level == null && order == null){
            return "SELECT COUNT(*) AS count FROM test WHERE is_close = 0 ORDER BY name ASC;";
        }
        else {
            StringBuilder startString = new StringBuilder("SELECT COUNT(*) AS count FROM test ");
            String orderString = "ORDER BY " + orderColumn + " ASC ";
            if (Objects.equals(order, "0")) {
                orderString = "ORDER BY " + orderColumn + " DESC ";
            }
            if (Objects.equals(name, null) && Objects.equals(subject, null) && Objects.equals(level, "0")) {
                return startString +"WHERE is_close = 0 " + orderString;
            }
            startString.append("WHERE is_close = 0 ");
            if (!Objects.equals(name, null)) {
                startString.append("AND name LIKE '%").append(name).append("%' ");
            }
            if (!Objects.equals(subject, null) && startString.length() > 30) {
                startString.append("AND subject LIKE '%").append(subject).append("%' ");
            } else if (!Objects.equals(subject, "") && startString.length() < 30) {
                startString.append("subject LIKE '%").append(subject).append("%' ");
            }
            if (!Objects.equals(level, "0") && !Objects.equals(level, "") && startString.length() > 30) {
                startString.append("AND level = ").append(level).append(" ");
            } else if (!Objects.equals(level, "0") && startString.length() < 30) {
                startString.append("level = ").append(level).append(" ");
            }
            return startString + orderString;
        }
    }

    @Override
    public double getAllFilterTests(String name, String subject, String level, String orderColumn, String order) {
        int numbersOfTests = 0;
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(getAllFilterRecordsQuery(name, subject, level, orderColumn, order));
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                numbersOfTests = Integer.parseInt(resultSet.getString("count"));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...{0}", e.toString());
        } finally {
            ConnectionService.close(connection);
        }
        return numbersOfTests;
    }
}