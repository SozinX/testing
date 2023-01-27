package org.sozinx.dao;

import org.sozinx.model.Question;
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

public class QuestionDAOImpl implements QuestionDAO {
    private static final Logger LOGGER = Logger.getLogger(String.valueOf(UserDAOImpl.class));
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
            LOGGER.log(Level.INFO, "Query failed...");
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
            LOGGER.log(Level.INFO, "Query failed...{0}", e.toString());
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
            LOGGER.log(Level.INFO, "Query failed...");
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
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return question;
    }

    @Override
    public void addQuestion(Question question) {
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(ADD_QUESTION);
            statement.setLong(1, question.getTest().getId());
            statement.setString(2, question.getQuestion());
            statement.setInt(3, question.getType().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...{0}", e.toString());
        } finally {
            ConnectionService.close(connection);
        }
    }

    //changing local object Question for updating
    private void localChangeQuestion(Question question, String[] params) {
        question.setQuestion(params[0]);
        question.setType(typeManager.getTypeById(Integer.parseInt(params[1])));
    }

    @Override
    public void updateQuestion(Question question, String[] params) {
        Connection connection = ConnectionService.getConnection();
        localChangeQuestion(question, params);
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(UPDATE_QUESTION);
            statement.setString(1, question.getQuestion());
            statement.setInt(2, question.getType().getId());
            statement.setLong(3, question.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
    }

    @Override
    public void deleteQuestion(Question question) {
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(DELETE_QUESTION);
            statement.setLong(1, question.getId());
            System.out.println(question.getId() + " done");
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...{0}", e.toString());
        } finally {
            ConnectionService.close(connection);
        }
    }
}
