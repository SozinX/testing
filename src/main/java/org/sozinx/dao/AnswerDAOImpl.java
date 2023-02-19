package org.sozinx.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sozinx.model.Answer;
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

public class AnswerDAOImpl implements AnswerDAO {
    private static final Logger LOGGER = LogManager.getLogger(String.valueOf(UserDAOImpl.class));
    private final QuestionDAO questionManager;

    public AnswerDAOImpl() {
        questionManager = new QuestionDAOImpl();
    }

    @Override
    public Answer getAnswerById(long id) {
        Answer answer = null;
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_ANSWER_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                answer = new Answer(resultSet.getLong("id"), resultSet.getString("answer"),
                        resultSet.getInt("correctness"), questionManager.getQuestionById(resultSet.getLong("question")));
            }
        } catch (SQLException e) {
            LOGGER.info("Query getAnswerById failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return answer;
    }

    @Override
    public List<Answer> getAnswerByQuestion(Question question) {
        List<Answer> answer = new ArrayList<>();
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_ANSWER_BY_QUESTION);
            statement.setLong(1, question.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                answer.add(new Answer(resultSet.getLong("id"), resultSet.getString("answer"),
                        resultSet.getInt("correctness"), question));
            }
        } catch (SQLException e) {
            LOGGER.info("Query getAnswerByQuestion failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return answer;
    }

    @Override
    public boolean addAnswer(Answer answer) {
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(ADD_ANSWER);
            statement.setLong(1, answer.getQuestion().getId());
            statement.setString(2, answer.getAnswer());
            statement.setInt(3, answer.getCorrectness());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.info("Query addAnswer failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return false;
    }

    //changing local object Answer for updating
    private void localChangeAnswer(Answer answer, String[] params) {
        answer.setAnswer(params[0]);
        answer.setCorrectness(Integer.parseInt(params[1]));
    }

    @Override
    public boolean updateAnswer(Answer answer, String[] params) {
        Connection connection = ConnectionService.getConnection();
        localChangeAnswer(answer, params);
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(UPDATE_ANSWER);
            statement.setString(1, answer.getAnswer());
            statement.setInt(2, answer.getCorrectness());
            statement.setLong(3, answer.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.info("Query updateAnswer failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return false;
    }

    @Override
    public boolean deleteAnswer(Answer answer) {
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(DELETE_ANSWER);
            statement.setLong(1, answer.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.info("Query deleteAnswer failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return false;
    }

    @Override
    public boolean deleteAnswerByQuestion(Question question) {
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(DELETE_ANSWER_BY_QUESTION);
            statement.setLong(1, question.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.info("Query deleteAnswerByQuestion failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return false;
    }

    @Override
    public int getCountOfAnswers(Test test) {
        int count = 0;
        Connection connection = ConnectionService.getConnection();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(GET_COUNT_OF_ANSWERS);
            statement.setLong(1, test.getId());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            LOGGER.info("Query getCountOfAnswers failed...");
        } finally {
            ConnectionService.close(connection);
        }
        return count;
    }
}
