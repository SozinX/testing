package org.sozinx.dao;

import org.sozinx.model.Answer;
import org.sozinx.model.Question;
import org.sozinx.model.Test;

import java.util.List;

/**
 * Using Answer model for create, read, update and delete operations in database's table <b>answer</b>.
 *
 * @author Ostap Petruniak
 * @see Answer
 * @see Question
 * @see Test
 * @since 1.0
 */
public interface AnswerDAO {
    /**
     * Getting answer by his id from table.
     *
     * @param id answer's id from table
     * @return object Answer with table's data
     */
    Answer getAnswerById(long id);

    /**
     * Getting list of answers for question.
     *
     * @param question object Question which answers we want to get
     * @return list of answers for question
     */
    List<Answer> getAnswerByQuestion(Question question);

    /**
     * Adding answer in table.
     *
     * @param answer object Answer which we want to add in table
     * @return boolean value has the answer been added
     */
    boolean addAnswer(Answer answer);

    /**
     * Updating answer in table.
     *
     * @param answer object Answer which we want to update in table
     * @param params String's array with parameters which we want change. Array order:<br>
     *               1) Answer's text;<br>
     *               2) Is answer correct. <br>
     * @return boolean value has the answer been updated
     */
    boolean updateAnswer(Answer answer, String[] params);

    /**
     * Deleting answer in table.
     *
     * @param answer object Answer which we want to delete in table
     * @return boolean value has the answer been deleted
     */
    boolean deleteAnswer(Answer answer);

    /**
     * Deleting answers in table.
     *
     * @param question object Question which answers we want delete
     * @return boolean value has the answer been deleted
     */
    boolean deleteAnswerByQuestion(Question question);

    /**
     * Getting number of answers in test.
     *
     * @param test object Test which answers we want to get
     * @return number of all answers in test
     */
    int getCountOfAnswers(Test test);
}
