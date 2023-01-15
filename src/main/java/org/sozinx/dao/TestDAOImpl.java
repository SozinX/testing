package org.sozinx.dao;

import org.sozinx.model.Test;
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

    @Override
    public List<Test> getTestByNameASC() {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_NAME_ASC);
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

    @Override
    public List<Test> getTestByNameDESC() {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_NAME_DESC);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level"))));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }

    @Override
    public List<Test> getTestBySubjectASC() {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_SUBJECT_ASC);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level"))));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }

    @Override
    public List<Test> getTestBySubjectDESC() {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_SUBJECT_DESC);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level"))));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }

    @Override
    public List<Test> getTestByPopularityASC() {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_POPULARITY_ASC);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level"))));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }

    @Override
    public List<Test> getTestByPopularityDESC() {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_POPULARITY_DESC);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level"))));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }

    @Override
    public List<Test> getTestByDateASC() {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_DATE_ASC);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level"))));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }

    @Override
    public List<Test> getTestByDateDESC() {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_DATE_DESC);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level"))));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }

    @Override
    public List<Test> getTestByLevelASC() {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_LEVEL_ASC);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level"))));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }

    @Override
    public List<Test> getTestByLevelDESC() {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_LEVEL_DESC);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level"))));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }

    @Override
    public List<Test> getTestByElectNameOrderByNameASC(String name) {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_ELECT_NAME_ORDER_BY_NAME_ASC);
            statement.setString(1, "%" + name + "%");
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

    @Override
    public List<Test> getTestByElectNameOrderByNameDESC(String name) {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_ELECT_NAME_ORDER_BY_NAME_DESC);
            statement.setString(1, "%" + name + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level"))));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }

    @Override
    public List<Test> getTestByElectNameOrderBySubjectASC(String name) {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_ELECT_NAME_ORDER_BY_SUBJECT_ASC);
            statement.setString(1, "%" + name + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level"))));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }

    @Override
    public List<Test> getTestByElectNameOrderBySubjectDESC(String name) {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_ELECT_NAME_ORDER_BY_SUBJECT_DESC);
            statement.setString(1, "%" + name + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level"))));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }

    @Override
    public List<Test> getTestByElectNameOrderByPopularityASC(String name) {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_ELECT_NAME_ORDER_BY_POPULARITY_ASC);
            statement.setString(1, "%" + name + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level"))));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }

    @Override
    public List<Test> getTestByElectNameOrderByPopularityDESC(String name) {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_ELECT_NAME_ORDER_BY_POPULARITY_DESC);
            statement.setString(1, "%" + name + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level"))));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }

    @Override
    public List<Test> getTestByElectNameOrderByDateASC(String name) {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_ELECT_NAME_ORDER_BY_DATE_ASC);
            statement.setString(1, "%" + name + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level"))));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }

    @Override
    public List<Test> getTestByElectNameOrderByDateDESC(String name) {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_ELECT_NAME_ORDER_BY_DATE_DESC);
            statement.setString(1, "%" + name + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level"))));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }

    @Override
    public List<Test> getTestByElectNameOrderByLevelASC(String name) {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_ELECT_NAME_ORDER_BY_LEVEL_ASC);
            statement.setString(1, "%" + name + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level"))));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }

    @Override
    public List<Test> getTestByElectNameOrderByLevelDESC(String name) {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_ELECT_NAME_ORDER_BY_LEVEL_DESC);
            statement.setString(1, "%" + name + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level"))));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }

    @Override
    public List<Test> getTestByElectSubjectOrderByNameASC(String subject) {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_ELECT_SUBJECT_ORDER_BY_NAME_ASC);
            statement.setString(1, "%" + subject + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level"))));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }

    @Override
    public List<Test> getTestByElectSubjectOrderByNameDESC(String subject) {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_ELECT_SUBJECT_ORDER_BY_NAME_DESC);
            statement.setString(1, "%" + subject + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level"))));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }

    @Override
    public List<Test> getTestByElectSubjectOrderBySubjectASC(String subject) {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_ELECT_SUBJECT_ORDER_BY_SUBJECT_ASC);
            statement.setString(1, "%" + subject + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level"))));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }

    @Override
    public List<Test> getTestByElectSubjectOrderBySubjectDESC(String subject) {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_ELECT_SUBJECT_ORDER_BY_SUBJECT_DESC);
            statement.setString(1, "%" + subject + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level"))));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }

    @Override
    public List<Test> getTestByElectSubjectOrderByPopularityASC(String subject) {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_ELECT_SUBJECT_ORDER_BY_POPULARITY_ASC);
            statement.setString(1, "%" + subject + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level"))));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }

    @Override
    public List<Test> getTestByElectSubjectOrderByPopularityDESC(String subject) {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_ELECT_SUBJECT_ORDER_BY_POPULARITY_DESC);
            statement.setString(1, "%" + subject + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level"))));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }

    @Override
    public List<Test> getTestByElectSubjectOrderByDateASC(String subject) {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_ELECT_SUBJECT_ORDER_BY_DATE_ASC);
            statement.setString(1, "%" + subject + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level"))));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }

    @Override
    public List<Test> getTestByElectSubjectOrderByDateDESC(String subject) {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_ELECT_SUBJECT_ORDER_BY_DATE_DESC);
            statement.setString(1, "%" + subject + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level"))));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }

    @Override
    public List<Test> getTestByElectSubjectOrderByLevelASC(String subject) {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_ELECT_SUBJECT_ORDER_BY_LEVEL_ASC);
            statement.setString(1, "%" + subject + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level"))));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }

    @Override
    public List<Test> getTestByElectSubjectOrderByLevelDESC(String subject) {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_ELECT_SUBJECT_ORDER_BY_LEVEL_DESC);
            statement.setString(1, "%" + subject + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level"))));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }

    @Override
    public List<Test> getTestByElectLevelOrderByNameASC(int level) {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_ELECT_LEVEL_ORDER_BY_NAME_ASC);
            statement.setInt(1, level);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level"))));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }

    @Override
    public List<Test> getTestByElectLevelOrderByNameDESC(int level) {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_ELECT_LEVEL_ORDER_BY_NAME_DESC);
            statement.setInt(1, level);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level"))));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }

    @Override
    public List<Test> getTestByElectLevelOrderBySubjectASC(int level) {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_ELECT_LEVEL_ORDER_BY_SUBJECT_ASC);
            statement.setInt(1, level);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level"))));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }

    @Override
    public List<Test> getTestByElectLevelOrderBySubjectDESC(int level) {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_ELECT_LEVEL_ORDER_BY_SUBJECT_DESC);
            statement.setInt(1, level);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level"))));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }

    @Override
    public List<Test> getTestByElectLevelOrderByPopularityASC(int level) {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_ELECT_LEVEL_ORDER_BY_POPULARITY_ASC);
            statement.setInt(1, level);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level"))));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }

    @Override
    public List<Test> getTestByElectLevelOrderByPopularityDESC(int level) {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_ELECT_LEVEL_ORDER_BY_POPULARITY_DESC);
            statement.setInt(1, level);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level"))));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }

    @Override
    public List<Test> getTestByElectLevelOrderByDateASC(int level) {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_ELECT_LEVEL_ORDER_BY_DATE_ASC);
            statement.setInt(1, level);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level"))));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }

    @Override
    public List<Test> getTestByElectLevelOrderByDateDESC(int level) {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_ELECT_LEVEL_ORDER_BY_DATE_DESC);
            statement.setInt(1, level);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level"))));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }

    @Override
    public List<Test> getTestByElectNameAndSubjectOrderByNameASC(String name, String subject) {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_ELECT_NAME_AND_SUBJECT_ORDER_BY_NAME_ASC);
            statement.setString(1, "%" + name + "%");
            statement.setString(2, "%" + subject + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level"))));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }

    @Override
    public List<Test> getTestByElectNameAndSubjectOrderByNameDESC(String name, String subject) {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_ELECT_NAME_AND_SUBJECT_ORDER_BY_NAME_DESC);
            statement.setString(1, "%" + name + "%");
            statement.setString(2, "%" + subject + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level"))));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }

    @Override
    public List<Test> getTestByElectNameAndSubjectOrderBySubjectASC(String name, String subject) {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_ELECT_NAME_AND_SUBJECT_ORDER_BY_SUBJECT_ASC);
            statement.setString(1, "%" + name + "%");
            statement.setString(2, "%" + subject + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level"))));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }

    @Override
    public List<Test> getTestByElectNameAndSubjectOrderBySubjectDESC(String name, String subject) {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_ELECT_NAME_AND_SUBJECT_ORDER_BY_SUBJECT_DESC);
            statement.setString(1, "%" + name + "%");
            statement.setString(2, "%" + subject + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level"))));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }

    @Override
    public List<Test> getTestByElectNameAndSubjectOrderByPopularityASC(String name, String subject) {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_ELECT_NAME_AND_SUBJECT_ORDER_BY_POPULARITY_ASC);
            statement.setString(1, "%" + name + "%");
            statement.setString(2, "%" + subject + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level"))));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }

    @Override
    public List<Test> getTestByElectNameAndSubjectOrderByPopularityDESC(String name, String subject) {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_ELECT_NAME_AND_SUBJECT_ORDER_BY_POPULARITY_DESC);
            statement.setString(1, "%" + name + "%");
            statement.setString(2, "%" + subject + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level"))));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }

    @Override
    public List<Test> getTestByElectNameAndSubjectOrderByDateASC(String name, String subject) {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_ELECT_NAME_AND_SUBJECT_ORDER_BY_DATE_ASC);
            statement.setString(1, "%" + name + "%");
            statement.setString(2, "%" + subject + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level"))));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }

    @Override
    public List<Test> getTestByElectNameAndSubjectOrderByDateDESC(String name, String subject) {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_ELECT_NAME_AND_SUBJECT_ORDER_BY_DATE_DESC);
            statement.setString(1, "%" + name + "%");
            statement.setString(2, "%" + subject + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level"))));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }

    @Override
    public List<Test> getTestByElectNameAndSubjectOrderByLevelASC(String name, String subject) {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_ELECT_NAME_AND_SUBJECT_ORDER_BY_LEVEL_ASC);
            statement.setString(1, "%" + name + "%");
            statement.setString(2, "%" + subject + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level"))));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }

    @Override
    public List<Test> getTestByElectNameAndSubjectOrderByLevelDESC(String name, String subject) {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_ELECT_NAME_AND_SUBJECT_ORDER_BY_LEVEL_DESC);
            statement.setString(1, "%" + name + "%");
            statement.setString(2, "%" + subject + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level"))));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }

    @Override
    public List<Test> getTestByElectNameAndLevelOrderByNameASC(String name, int level) {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_ELECT_NAME_AND_LEVEL_ORDER_BY_NAME_ASC);
            statement.setString(1, "%" + name + "%");
            statement.setInt(2, level);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level"))));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }

    @Override
    public List<Test> getTestByElectNameAndLevelOrderByNameDESC(String name, int level) {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_ELECT_NAME_AND_LEVEL_ORDER_BY_NAME_DESC);
            statement.setString(1, "%" + name + "%");
            statement.setInt(2, level);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level"))));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }

    @Override
    public List<Test> getTestByElectNameAndLevelOrderBySubjectASC(String name, int level) {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_ELECT_NAME_AND_LEVEL_ORDER_BY_SUBJECT_ASC);
            statement.setString(1, "%" + name + "%");
            statement.setInt(2, level);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level"))));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }

    @Override
    public List<Test> getTestByElectNameAndLevelOrderBySubjectDESC(String name, int level) {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_ELECT_NAME_AND_LEVEL_ORDER_BY_SUBJECT_DESC);
            statement.setString(1, "%" + name + "%");
            statement.setInt(2, level);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level"))));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }

    @Override
    public List<Test> getTestByElectNameAndLevelOrderByPopularityASC(String name, int level) {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_ELECT_NAME_AND_LEVEL_ORDER_BY_POPULARITY_ASC);
            statement.setString(1, "%" + name + "%");
            statement.setInt(2, level);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level"))));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }

    @Override
    public List<Test> getTestByElectNameAndLevelOrderByPopularityDESC(String name, int level) {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_ELECT_NAME_AND_LEVEL_ORDER_BY_POPULARITY_DESC);
            statement.setString(1, "%" + name + "%");
            statement.setInt(2, level);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level"))));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }

    @Override
    public List<Test> getTestByElectNameAndLevelOrderByDateASC(String name, int level) {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_ELECT_NAME_AND_LEVEL_ORDER_BY_DATE_ASC);
            statement.setString(1, "%" + name + "%");
            statement.setInt(2, level);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level"))));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }

    @Override
    public List<Test> getTestByElectNameAndLevelOrderByDateDESC(String name, int level) {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_ELECT_NAME_AND_LEVEL_ORDER_BY_DATE_DESC);
            statement.setString(1, "%" + name + "%");
            statement.setInt(2, level);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level"))));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }

    @Override
    public List<Test> getTestByElectSubjectAndLevelOrderByNameASC(String subject, int level) {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_ELECT_SUBJECT_AND_LEVEL_ORDER_BY_NAME_ASC);
            statement.setString(1, "%" + subject + "%");
            statement.setInt(2, level);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level"))));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }

    @Override
    public List<Test> getTestByElectSubjectAndLevelOrderByNameDESC(String subject, int level) {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_ELECT_SUBJECT_AND_LEVEL_ORDER_BY_NAME_DESC);
            statement.setString(1, "%" + subject + "%");
            statement.setInt(2, level);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level"))));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }

    @Override
    public List<Test> getTestByElectSubjectAndLevelOrderBySubjectASC(String subject, int level) {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_ELECT_SUBJECT_AND_LEVEL_ORDER_BY_SUBJECT_ASC);
            statement.setString(1, "%" + subject + "%");
            statement.setInt(2, level);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level"))));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }

    @Override
    public List<Test> getTestByElectSubjectAndLevelOrderBySubjectDESC(String subject, int level) {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_ELECT_SUBJECT_AND_LEVEL_ORDER_BY_SUBJECT_DESC);
            statement.setString(1, "%" + subject + "%");
            statement.setInt(2, level);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level"))));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }

    @Override
    public List<Test> getTestByElectSubjectAndLevelOrderByPopularityASC(String subject, int level) {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_ELECT_SUBJECT_AND_LEVEL_ORDER_BY_POPULARITY_ASC);
            statement.setString(1, "%" + subject + "%");
            statement.setInt(2, level);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level"))));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }

    @Override
    public List<Test> getTestByElectSubjectAndLevelOrderByPopularityDESC(String subject, int level) {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_ELECT_SUBJECT_AND_LEVEL_ORDER_BY_POPULARITY_DESC);
            statement.setString(1, "%" + subject + "%");
            statement.setInt(2, level);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level"))));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }

    @Override
    public List<Test> getTestByElectSubjectAndLevelOrderByDateASC(String subject, int level) {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_ELECT_SUBJECT_AND_LEVEL_ORDER_BY_DATE_ASC);
            statement.setString(1, "%" + subject + "%");
            statement.setInt(2, level);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level"))));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }

    @Override
    public List<Test> getTestByElectSubjectAndLevelOrderByDateDESC(String subject, int level) {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_ELECT_SUBJECT_AND_LEVEL_ORDER_BY_DATE_DESC);
            statement.setString(1, "%" + subject + "%");
            statement.setInt(2, level);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level"))));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }

    @Override
    public List<Test> getTestByElectNameAndSubjectAndLevelOrderByNameASC(String name, String subject, int level) {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_ELECT_NAME_AND_SUBJECT_AND_LEVEL_ORDER_BY_NAME_ASC);
            statement.setString(1, "%" + name + "%");
            statement.setString(2, "%" + subject + "%");
            statement.setInt(3, level);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level"))));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }

    @Override
    public List<Test> getTestByElectNameAndSubjectAndLevelOrderByNameDESC(String name, String subject, int level) {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_ELECT_NAME_AND_SUBJECT_AND_LEVEL_ORDER_BY_NAME_DESC);
            statement.setString(1, "%" + name + "%");
            statement.setString(2, "%" + subject + "%");
            statement.setInt(3, level);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level"))));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }

    @Override
    public List<Test> getTestByElectNameAndSubjectAndLevelOrderBySubjectASC(String name, String subject, int level) {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_ELECT_NAME_AND_SUBJECT_AND_LEVEL_ORDER_BY_SUBJECT_ASC);
            statement.setString(1, "%" + name + "%");
            statement.setString(2, "%" + subject + "%");
            statement.setInt(3, level);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level"))));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }

    @Override
    public List<Test> getTestByElectNameAndSubjectAndLevelOrderBySubjectDESC(String name, String subject, int level) {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_ELECT_NAME_AND_SUBJECT_AND_LEVEL_ORDER_BY_SUBJECT_DESC);
            statement.setString(1, "%" + name + "%");
            statement.setString(2, "%" + subject + "%");
            statement.setInt(3, level);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level"))));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }

    @Override
    public List<Test> getTestByElectNameAndSubjectAndLevelOrderByPopularityASC(String name, String subject, int level) {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_ELECT_NAME_AND_SUBJECT_AND_LEVEL_ORDER_BY_POPULARITY_ASC);
            statement.setString(1, "%" + name + "%");
            statement.setString(2, "%" + subject + "%");
            statement.setInt(3, level);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level"))));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }

    @Override
    public List<Test> getTestByElectNameAndSubjectAndLevelOrderByPopularityDESC(String name, String subject, int level) {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_ELECT_NAME_AND_SUBJECT_AND_LEVEL_ORDER_BY_POPULARITY_DESC);
            statement.setString(1, "%" + name + "%");
            statement.setString(2, "%" + subject + "%");
            statement.setInt(3, level);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level"))));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }

    @Override
    public List<Test> getTestByElectNameAndSubjectAndLevelOrderByDateASC(String name, String subject, int level) {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_ELECT_NAME_AND_SUBJECT_AND_LEVEL_ORDER_BY_DATE_ASC);
            statement.setString(1, "%" + name + "%");
            statement.setString(2, "%" + subject + "%");
            statement.setInt(3, level);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level"))));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }

    @Override
    public List<Test> getTestByElectNameAndSubjectAndLevelOrderByDateDESC(String name, String subject, int level) {
        List<Test> tests = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_TEST_BY_ELECT_NAME_AND_SUBJECT_AND_LEVEL_ORDER_BY_DATE_DESC);
            statement.setString(1, "%" + name + "%");
            statement.setString(2, "%" + subject + "%");
            statement.setInt(3, level);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("subject"), resultSet.getString("created"),
                        resultSet.getInt("is_close"), resultSet.getInt("time"),
                        resultSet.getInt("finished"), userManager.getUserById(resultSet.getInt("owner")),
                        levelManager.getLevelById(resultSet.getInt("level"))));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return tests;
    }
}
