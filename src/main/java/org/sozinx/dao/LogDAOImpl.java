package org.sozinx.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sozinx.model.*;
import org.sozinx.service.ConnectionService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.sozinx.constant.QueryConst.*;

public class LogDAOImpl implements LogDAO {
    private static final Logger LOGGER = LogManager.getLogger(String.valueOf(ResultDAOImpl.class));
    private final UserDAO userManager;
    private final TestDAO testManager;
    private final AnswerDAO answerManager;
    private final QuestionDAO questionManager;

    public LogDAOImpl() {
        userManager = new UserDAOImpl();
        testManager = new TestDAOImpl();
        questionManager = new QuestionDAOImpl();
        answerManager = new AnswerDAOImpl();
    }

    @Override
    public List<Log> getLogByUser(User user) {
        List<Log> log = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_LOG_BY_USER);
            statement.setLong(1, user.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                log.add(new Log(resultSet.getLong("id"), user, testManager.getTestById(resultSet.getLong("test")),
                        questionManager.getQuestionById(resultSet.getLong("question")),
                        answerManager.getAnswerById(resultSet.getLong("answer"))));
            }
        } catch (SQLException e) {
            LOGGER.info( "Query getLogByUser failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return log;
    }

    @Override
    public List<Log> getLogByTest(Test test) {
        List<Log> log = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_LOG_BY_TEST);
            statement.setLong(1, test.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                log.add(new Log(resultSet.getLong("id"), userManager.getUserById(resultSet.getLong("user")), test,
                        questionManager.getQuestionById(resultSet.getLong("question")),
                        answerManager.getAnswerById(resultSet.getLong("answer"))));
            }
        } catch (SQLException e) {
            LOGGER.info( "Query getLogByTest failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return log;
    }

    @Override
    public void addLog(Log log) {
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(ADD_LOG);
            statement.setLong(1, log.getUser().getId());
            statement.setLong(2, log.getTest().getId());
            statement.setLong(3, log.getQuestion().getId());
            if (log.getAnswer() == null) {
                statement.setNull(4, Types.NULL);
            } else {
                statement.setLong(4, log.getAnswer().getId());
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.info( "Query addLog failed...");
        } finally {
            ConnectionService.close(connection);
        }
    }

    @Override
    public boolean deleteLogByQuestion(Question question) {
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(DELETE_LOG_BY_QUESTION);
            statement.setLong(1, question.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.info( "Query deleteLogByQuestion failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return false;
    }

    @Override
    public boolean deleteLogByAnswer(Answer answer) {
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(DELETE_LOG_BY_ANSWER);
            statement.setLong(1, answer.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.info( "Query deleteLogByAnswer failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return false;
    }

    @Override
    public boolean deleteLogByUser(User user) {
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(DELETE_LOG_BY_USER);
            statement.setLong(1, user.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.info( "Query deleteLogByUser failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return false;
    }

    @Override
    public int getSumOfPoints(Test test, User user) {
        int sum = 0;
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_SUM_OF_POINTS);
            statement.setLong(1, test.getId());
            statement.setLong(2, user.getId());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                sum = resultSet.getInt("sum");
            }
        } catch (SQLException e) {
            LOGGER.info( "Query getSumOfPoints failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return sum;
    }

    @Override
    public int getCountOfZeros(Test test, User user) {
        int count = 0;
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_COUNT_OF_ZEROS);
            statement.setLong(1, test.getId());
            statement.setLong(2, user.getId());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            LOGGER.info( "Query getCountOfZeros failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return count;
    }
}
