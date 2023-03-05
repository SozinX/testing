package org.sozinx.dao;

import org.sozinx.model.Question;
import org.sozinx.model.Test;

import java.util.List;

/**
 * Using Question model for create, read, update and delete operations in database's table <b>answer</b>.
 *
 * @author Ostap Petruniak
 * @see Question
 * @see Test
 * @since 1.0
 */
public interface QuestionDAO {
    /**
     * Getting question by his id from table.
     *
     * @param id question's id from table
     * @return object Question with table's data
     */
    Question getQuestionById(long id);

    /**
     * Getting list of questions for test.
     *
     * @param test object Test which questions we want to get
     * @return list of questions for test
     */
    List<Question> getQuestionByTest(Test test);

    /**
     * Adding question in table.
     *
     * @param question object Question which we want to add in table
     * @return boolean value is answer was added
     */
    boolean addQuestion(Question question);

    /**
     * Updating question in table.
     *
     * @param question object Question which we want to update in table
     * @param params   String's array with parameters which we want change. Array order:<br>
     *                 1) Question's text;<br>
     *                 2) Question's type. <br>
     * @return boolean value has the question been updated
     */
    boolean updateQuestion(Question question, String[] params);

    /**
     * Deleting question in table.
     *
     * @param question object Question which we want delete
     * @return boolean value has the question been deleted
     */
    boolean deleteQuestion(Question question);

    /**
     * Getting question for question's text. This method need for warning about duplicate the same questions in one test.
     *
     * @param name       question's text that we are getting
     * @param testId     id of test in which is question
     * @param questionId id of question that we are checking(if question did not change we do not need warning)
     * @return object Question that we are looking for(null if absent)
     */
    Question getQuestionByName(String name, long testId, long questionId);

    /**
     * Getting last added by time question in test.
     *
     * @param test object Test which last question we want to get
     * @return last added by time in table question
     */
    Question getLastQuestionByTest(Test test);

}
