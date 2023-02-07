package org.sozinx.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sozinx.model.Question;
import org.sozinx.model.Test;
import org.sozinx.service.ConnectionService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.sozinx.constant.QueryConst.*;

public class QuestionDAOImpl implements QuestionDAO {
    private static final Logger LOGGER = LogManager.getLogger(String.valueOf(UserDAOImpl.class));
    private final TestDAO testManager;
    private final TypeDAO typeManager;

    public QuestionDAOImpl() {
        testManager = new TestDAOImpl();
        typeManager = new TypeDAOImpl();
    }

    @Override
    public Question getQuestionById(long id) {
        Question question = null;
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_QUESTION_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                question = new Question(resultSet.getInt("id"), resultSet.getString("question"),
                        testManager.getTestById(resultSet.getInt("test")), typeManager.getTypeById(resultSet.getInt("type")));
            }
        } catch (SQLException e) {
            LOGGER.info("Query getQuestionById failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return question;
    }

    public Question getQuestionByName(String name, long test, long id) {
        Question question = null;
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_QUESTION_BY_QUESTION);
            statement.setString(1, name);
            statement.setLong(2, test);
            statement.setLong(3, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                question = new Question(resultSet.getInt("id"), resultSet.getString("question"),
                        testManager.getTestById(resultSet.getInt("test")), typeManager.getTypeById(resultSet.getInt("type")));
            }
        } catch (SQLException e) {
            LOGGER.info("Query getQuestionByName failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return question;
    }

    @Override
    public List<Question> getQuestionByTest(Test test) {
        List<Question> question = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_QUESTION_BY_TEST);
            statement.setLong(1, test.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                question.add(new Question(resultSet.getInt("id"), resultSet.getString("question"),
                        test, typeManager.getTypeById(resultSet.getInt("type"))));
            }
        } catch (SQLException e) {
            LOGGER.info("Query getQuestionByTest failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return question;
    }

    //getting last added question for user and test
    @Override
    public Question getLastQuestionByTest(Test test) {
        Question question = null;
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_LAST_QUESTION_BY_TEST);
            statement.setLong(1, test.getId());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                question = new Question(resultSet.getInt("id"), resultSet.getString("question"),
                        test, typeManager.getTypeById(resultSet.getInt("type")));
            }
        } catch (SQLException e) {
            LOGGER.info("Query getLastQuestionByTest failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return question;
    }

    @Override
    public boolean addQuestion(Question question) {
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(ADD_QUESTION);
            statement.setLong(1, question.getTest().getId());
            statement.setString(2, question.getQuestion());
            statement.setInt(3, question.getType().getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.info("Query addQuestion failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return false;
    }

    //changing local object Question for updating
    private void localChangeQuestion(Question question, String[] params) {
        question.setQuestion(params[0]);
        question.setType(typeManager.getTypeById(Integer.parseInt(params[1])));
    }

    @Override
    public boolean updateQuestion(Question question, String[] params) {
        Connection connection = ConnectionService.getConnection();
        localChangeQuestion(question, params);
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(UPDATE_QUESTION);
            statement.setString(1, question.getQuestion());
            statement.setInt(2, question.getType().getId());
            statement.setLong(3, question.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.info("Query updateQuestion failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return false;
    }

    @Override
    public boolean deleteQuestion(Question question) {
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(DELETE_QUESTION);
            statement.setLong(1, question.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.info("Query deleteQuestion failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return false;
    }
}
