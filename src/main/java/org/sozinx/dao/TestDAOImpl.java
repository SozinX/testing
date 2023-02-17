package org.sozinx.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sozinx.model.Test;
import org.sozinx.model.User;
import org.sozinx.service.ConnectionService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.sozinx.constant.QueryConst.*;

public class TestDAOImpl implements TestDAO {
    private static final Logger LOGGER = LogManager.getLogger(String.valueOf(UserDAOImpl.class));
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
            LOGGER.info("Query getTestById failed...");
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
            LOGGER.info("Query addTest failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return false;
    }

    //changing local object Test for updating
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
            LOGGER.info("Query updateTest failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return false;
    }

    //increase popularity by one
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
            LOGGER.info("Query addPopularity failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return false;
    }

    //getting last added test for current user
    public Test getLastAddedTestByOwner(User owner) {
        Test test = null;
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_LAST_ADDED_TEST_BY_OWNER);
            statement.setLong(1, owner.getId());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                test = new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level")));
            }
        } catch (SQLException e) {
            LOGGER.info("Query getLastAddedTestByOwner failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return test;
    }

    //creating query for filtering tests on home page
    private String getQuery(String name, String subject, String level, String orderColumn, String order, String page) {
        if (page == null) {
            page = "1";
        }
        int records = (Integer.parseInt(page) - 1) * 12;
        if (name == null && subject == null && orderColumn == null && level == null && order == null) {
            return "SELECT * FROM test WHERE is_close = 0 ORDER BY name ASC LIMIT " + records + ", 12;";
        } else {
            StringBuilder startString = new StringBuilder("SELECT * FROM test ");
            String orderString = "ORDER BY " + orderColumn + " ASC ";
            if (Objects.equals(order, "0")) {
                orderString = "ORDER BY " + orderColumn + " DESC ";
            }
            if (Objects.equals(name, null) && Objects.equals(subject, null) && Objects.equals(level, "0")) {
                return startString + "WHERE is_close = 0 " + orderString;
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
            return startString + orderString + "LIMIT " + records + ", 12;";
        }
    }

    //getting filter result for query created at the last method with pagination
    @Override
    public List<Test> getFilterResult(String name, String subject, String level, String orderColumn, String order, String page) {
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
            LOGGER.info("Query getFilterResult failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }

    //query for getting all filter results for knowledge about number of pages(Calculating  is services)
    private String getAllFilterRecordsQuery(String name, String subject, String level, String orderColumn, String order) {
        if (name == null && subject == null && orderColumn == null && level == null && order == null) {
            return "SELECT COUNT(*) AS count FROM test WHERE is_close = 0 ORDER BY name ASC;";
        } else {
            StringBuilder startString = new StringBuilder("SELECT COUNT(*) AS count FROM test ");
            String orderString = "ORDER BY " + orderColumn + " ASC ";
            if (Objects.equals(order, "0")) {
                orderString = "ORDER BY " + orderColumn + " DESC ";
            }
            if (Objects.equals(name, null) && Objects.equals(subject, null) && Objects.equals(level, "0")) {
                return startString + "WHERE is_close = 0 " + orderString;
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

    //using created at the previous method query and return number of tests
    @Override
    public double getAllFilterTests(String name, String subject, String level, String orderColumn, String order) {
        int numberOfTests = 0;
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(getAllFilterRecordsQuery(name, subject, level, orderColumn, order));
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                numberOfTests = Integer.parseInt(resultSet.getString("count"));
            }
        } catch (SQLException e) {
            LOGGER.info("Query getAllFilterTests failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return numberOfTests;
    }

    //get query for page My Test where I need to show only current user's tests
    private String getQueryForOwner(String name, String subject, String level, String orderColumn, String order, String owner, String page) {
        if (page == null) {
            page = "1";
        }
        int records = (Integer.parseInt(page) - 1) * 12;
        if (name == null && subject == null && orderColumn == null && level == null && order == null) {
            return "SELECT * FROM test WHERE owner = " + owner + " ORDER BY name ASC LIMIT " + records + ", 12;";
        } else {
            StringBuilder startString = new StringBuilder("SELECT * FROM test ");
            String orderString = "ORDER BY " + orderColumn + " ASC ";
            if (Objects.equals(order, "0")) {
                orderString = "ORDER BY " + orderColumn + " DESC ";
            }
            if (Objects.equals(name, null) && Objects.equals(subject, null) && Objects.equals(level, "0")) {
                return startString + "WHERE owner = " + owner + " " + orderString;
            }
            startString.append("WHERE owner =").append(owner).append(" ");
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
            return startString + orderString + "LIMIT " + records + ", 12;";
        }
    }

    //using query created at the last method and getting tests for current owner
    @Override
    public List<Test> getFilterResultForOwner(String name, String subject, String level, String orderColumn, String order, String owner, String page) {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(getQueryForOwner(name, subject, level, orderColumn, order, owner, page));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level"))));
            }
        } catch (SQLException e) {
            LOGGER.info("Query getFilterResultForOwner failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }

    //query for getting all filter results for knowledge about number of pages(calculating in services)
    private String getAllFilterRecordsQueryForOwner(String name, String subject, String level, String orderColumn, String owner, String order) {
        if (name == null && subject == null && orderColumn == null && level == null && order == null) {
            return "SELECT COUNT(*) AS count FROM test WHERE owner = " + owner + " ORDER BY name ASC;";
        } else {
            StringBuilder startString = new StringBuilder("SELECT COUNT(*) AS count FROM test ");
            String orderString = "ORDER BY " + orderColumn + " ASC ";
            if (Objects.equals(order, "0")) {
                orderString = "ORDER BY " + orderColumn + " DESC ";
            }
            if (Objects.equals(name, null) && Objects.equals(subject, null) && Objects.equals(level, "0")) {
                return startString + "WHERE owner = " + owner + " " + orderString;
            }
            startString.append("WHERE owner =").append(owner).append(" ");
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

    //using created at the previous method query and return number of tests for current owner
    @Override
    public double getAllFilterTestsForOwner(String name, String subject, String level, String orderColumn, String owner, String order) {
        int numbersOfTests = 0;
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(getAllFilterRecordsQueryForOwner(name, subject, level, orderColumn, owner, order));
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                numbersOfTests = Integer.parseInt(resultSet.getString("count"));
            }
        } catch (SQLException e) {
            LOGGER.info("Query getAllFilterTestsForOwner failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return numbersOfTests;
    }

    @Override
    public Test getTestByNameAndOwner(String name, long id, long testId) {
        Test test = null;
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_NAME_AND_OWNER);
            statement.setString(1, name);
            statement.setLong(2, id);
            statement.setLong(3, testId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                test = new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level")));
            }
        } catch (SQLException e) {
            LOGGER.info("Query getTestByNameAndOwner failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return test;
    }

    @Override
    public boolean openTest(Test test) {
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(OPEN_TEST);
            statement.setLong(1, test.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.info("Query openTest failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return false;
    }

    @Override
    public boolean deleteTestById(long id) {
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(DELETE_TEST_BY_ID);
            statement.setLong(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.info("Query deleteTestById failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return false;
    }
}