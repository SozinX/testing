package org.sozinx.dao;

import org.sozinx.model.*;


import java.util.List;

/**
 * Using Log model for create, read, update and delete operations in database's table <b>answer</b>.
 *
 * @author Ostap Petruniak
 * @see Answer
 * @see User
 * @see Question
 * @see Test
 * @see Log
 * @since 1.0
 */
public interface LogDAO {
    /**
     * Getting list of logs for user.
     *
     * @param user object User which logs we want to get
     * @return list of logs for user
     */
    @SuppressWarnings("unused")
    List<Log> getLogByUser(User user);

    /**
     * Getting list of logs for test.
     *
     * @param test object Test which logs we want to get
     * @return list of logs for test
     */
    @SuppressWarnings("unused")
    List<Log> getLogByTest(Test test);

    /**
     * Adding log in table.
     *
     * @param log object Log which we want to add in table
     */
    void addLog(Log log);

    /**
     * Deleting logs in table by question.
     *
     * @param question object Question which logs we want delete
     */
    void deleteLogByQuestion(Question question);

    /**
     * Deleting logs in table by answer.
     *
     * @param answer object Answer which logs we want delete
     */
    @SuppressWarnings("unused")
    void deleteLogByAnswer(Answer answer);

    /**
     * Deleting logs in table by user.
     *
     * @param user object User which logs we want delete
     * @return boolean value has the log been deleted
     */
    @SuppressWarnings("unused")
    boolean deleteLogByUser(User user);

    /**
     * Getting sum of correct answers in logs by user and test.
     *
     * @param test object Test which logs we want to get
     * @param user object User which logs we want to get
     * @return sum of correct answers
     */
    int getSumOfPoints(Test test, User user);

    /**
     * Getting count of incorrect answers in logs by user and test.
     *
     * @param test object Test which logs we want to get
     * @param user object User which logs we want to get
     * @return count of incorrect answers
     */
    int getCountOfZeros(Test test, User user);

}
