package org.sozinx.dao;

import org.sozinx.model.*;
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

public class LogDAOImpl implements LogDAO {
    private static final Logger LOGGER = Logger.getLogger(String.valueOf(ResultDAOImpl.class));
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
            LOGGER.log(Level.INFO, "Query failed...");
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
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return log;
    }

    @Override
    public boolean addLog(Log log) {
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(ADD_LOG);
            statement.setLong(1, log.getUser().getId());
            statement.setLong(2, log.getTest().getId());
            statement.setLong(3, log.getQuestion().getId());
            statement.setLong(4, log.getAnswer().getId());
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
    public boolean deleteLogByQuestion(Question question) {
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(DELETE_LOG_BY_QUESTION);
            statement.setLong(1, question.getId());
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
    public boolean deleteLogByAnswer(Answer answer) {
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(DELETE_LOG_BY_ANSWER);
            statement.setLong(1, answer.getId());
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
    public boolean deleteLogByUser(User user) {
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(DELETE_LOG_BY_USER);
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
