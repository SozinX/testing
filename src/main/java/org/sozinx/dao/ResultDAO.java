package org.sozinx.dao;

import org.sozinx.model.Result;
import org.sozinx.model.Test;
import org.sozinx.model.User;

import java.util.List;

/**
 * Using Result model for create, read, update and delete operations in database's table <b>result</b>.
 *
 * @author Ostap Petruniak
 * @see Test
 * @see Result
 * @see User
 * @since 1.0
 */
public interface ResultDAO {
    /**
     * Getting list of results for user.
     *
     * @param user object User which results we want to get
     * @return list of blocks for user
     */
    @SuppressWarnings("unused")
    List<Result> getResultByUser(User user);

    /**
     * Getting list of results for test.
     *
     * @param test object Test which results we want to get
     * @return list of results for test
     */
    @SuppressWarnings("unused")
    List<Result> getResultByTest(Test test);

    /**
     * Getting list of results for user and test.
     *
     * @param user object User which results we want to get
     * @param test object Test which results we want to get
     * @return result for test and user
     */
    Result getResultByUserAndTest(User user, Test test);

    /**
     * Adding result in table.
     *
     * @param result object Result which we want to add in table
     */
    void addResult(Result result);

    /**
     * Deleting result in table.
     *
     * @param result object Result which we want delete
     * @return boolean value has the result been deleted
     */
    @SuppressWarnings("unused")
    boolean deleteResult(Result result);

    /**
     * Updating result in table.
     *
     * @param result object Result which we want to update in table
     * @param points that user get in test
     * @return boolean value has the result been updated
     */
    @SuppressWarnings("unused")
    boolean updateResult(Result result, int points);

    /**
     * Deleting results in table by user.
     *
     * @param user object User which results we want delete
     * @return boolean value has results been deleted
     */
    @SuppressWarnings("unused")
    boolean deleteResultByUser(User user);
}
