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
                question.add(new Question(0, resultSet.getString("question"),
                        test, typeManager.getTypeById(resultSet.getInt("type"))));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...");
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
            statement.setLong(1, question.getId());
            statement.setString(2, question.getQuestion());
            statement.setInt(3, question.getType().getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Query failed...{0}", e.toString());
        } finally {
            ConnectionService.close(connection);
        }
        return false;
    }

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
            LOGGER.log(Level.INFO, "Query failed...");
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
            LOGGER.log(Level.INFO, "Query failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return false;
    }
}
