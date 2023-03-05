package org.sozinx.dao;

import org.sozinx.model.Test;
import org.sozinx.model.User;

import java.util.List;
import java.util.Map;

/**
 * Using Role model for create, read, update and delete operations in database's table <b>role</b>.
 *
 * @author Ostap Petruniak
 * @see Test
 * @see User
 * @since 1.0
 */
public interface TestDAO {
    /**
     * Getting test by his id from table.
     *
     * @param id test's id from table
     * @return object Test with table's data
     */
    Test getTestById(long id);

    /**
     * Adding test in table.
     *
     * @param test object Test which we want to add in table
     * @return boolean value has the test been added
     */
    boolean addTest(Test test);

    /**
     * Updating test in table.
     *
     * @param test   object Test which we want to update in table
     * @param params String's array with parameters which we want change. Array order:<br>
     *               1) Test's name;<br>
     *               2) Test's subject;<br>
     *               3) Is test close;<br>
     *               4) Test's time;<br>
     *               5) Test's level.<br>
     * @return boolean value has the test been updated
     */
    boolean updateTest(Test test, String[] params);

    /**
     * Adding test's popularity in table. If user started the test, then this method should be called
     *
     * @param test object Test which popularity we want to increase in table
     * @return boolean value has the popularity been increased
     */
    boolean addPopularity(Test test);

    /**
     * Opening test in table.
     *
     * @param test object Test which we want to open(change value) in table
     * @return boolean value has the test been opened
     */
    boolean openTest(Test test);

    /**
     * Getting last added (by time) test by user.
     *
     * @param owner object User - owner of test
     * @return object Test with table's data
     */
    Test getLastAddedTestByOwner(User owner);

    /**
     * Getting tests for filter on tests page.
     *
     * @param criteriaOfFilter Map with data from filtering form. Could have values(null safety):
     *                         1) <b>name</b> - name of  test;<br>
     *                         2) <b>subject</b> - subject of  test;<br>
     *                         3) <b>level</b> - level of test;<br>
     *                         4) <b>orderColumn</b> - column for ordering in query;<br>
     *                         5) <b>order</b> - 0 or 1(null) for ASC/DESC in query;<br>
     *                         6) <b>page</b> - current page.<br>
     * @return List of Tests with table's data
     */
    List<Test> getFilterResult(Map<String, String> criteriaOfFilter);

    /**
     * Getting number of tests for filter on tests page(without pagination and ordering).
     *
     * @param criteriaOfFilter Map with data from filtering form. Could have values(null safety):
     *                         1) <b>name</b> - name of  test;<br>
     *                         2) <b>subject</b> - subject of  test;<br>
     *                         3) <b>level</b> - level of test;<br>
     *                         4) <b>orderColumn</b> - column for ordering in query;<br>
     *                         5) <b>order</b> - 0 or 1(null) for ASC/DESC in query;<br>
     *                         6) <b>page</b> - current page.<br>
     * @return number of Tests
     */
    double getAllFilterTests(Map<String, String> criteriaOfFilter);

    /**
     * Getting tests for filter on mytests page. Only for confirmed teacher that is already logged in.
     *
     * @param criteriaOfFilter Map with data from filtering form. Could have values(null safety):
     *                         1) <b>name</b> - name of  test;<br>
     *                         2) <b>subject</b> - subject of  test;<br>
     *                         3) <b>level</b> - level of test;<br>
     *                         4) <b>orderColumn</b> - column for ordering in query;<br>
     *                         5) <b>order</b> - 0 or 1(null) for ASC/DESC in query;<br>
     *                         6) <b>page</b> - current page;<br>
     *                         7) <b>owner</b> - test's creator id.<br>
     * @return List of Tests with table's data
     */
    List<Test> getFilterResultForOwner(Map<String, String> criteriaOfFilter);

    /**
     * Getting number of tests for filter on mytests page(without pagination and ordering). Only for confirmed teacher that is already logged in.
     *
     * @param criteriaOfFilter Map with data from filtering form. Could have values(null safety):
     *                         1) <b>name</b> - name of  test;<br>
     *                         2) <b>subject</b> - subject of  test;<br>
     *                         3) <b>level</b> - level of test;<br>
     *                         4) <b>orderColumn</b> - column for ordering in query;<br>
     *                         5) <b>order</b> - 0 or 1(null) for ASC/DESC in query;<br>
     *                         6) <b>page</b> - current page;<br>
     *                         7) <b>owner</b> - test's creator id.<br>
     * @return number of Tests
     */
    double getAllFilterTestsForOwner(Map<String, String> criteriaOfFilter);

    /**
     * Getting test by owner and name.
     *
     * @param name   test's name
     * @param id     owner's id
     * @param testId test's id(for ignore test that we are checking)
     * @return object Test if it is present or null if it is absent
     */
    Test getTestByNameAndOwner(String name, long id, long testId);

    /**
     * Deleting test in table.
     *
     * @param id test's id which we want to delete
     * @return boolean value has the test been deleted
     */
    boolean deleteTestById(long id);
}

