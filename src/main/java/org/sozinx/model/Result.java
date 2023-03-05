package org.sozinx.model;

/**
 * Model of Result from result table.
 *
 * @author Ostap Petruniak
 * @see Test
 * @see User
 * @since 1.0
 */
public class Result {
    private long id;
    private final String date;
    private final int result;
    private User user;
    private Test test;

    /**
     * Result constructor.
     *
     * @param id     result's id from table or 0 if it is a new object for table
     * @param date   date of creating result (test finishing)
     * @param result points
     * @param user   object User who finished the test
     * @param test   object Test which user has finished
     */
    public Result(long id, String date, int result, User user, Test test) {
        this.id = id;
        this.date = date;
        this.result = result;
        this.user = user;
        this.test = test;
    }

    /**
     * Getting result's id.
     *
     * @return result's id from table
     */
    public long getId() {
        return id;
    }

    /**
     * Setting result's id.
     *
     * @param id new result's id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Getting date of finish.
     *
     * @return date of creating result (test finishing)
     */
    public String getDate() {
        return date;
    }

    /**
     * Getting final result.
     *
     * @return points
     */
    public int getResult() {
        return result;
    }

    /**
     * Getting user who finished the test
     *
     * @return object User who finished the test
     */
    public User getUser() {
        return user;
    }

    /**
     * Setting user who finished the test
     *
     * @param user object User who finished the test
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Getting test which user has finished.
     *
     * @return object Test which user has finished
     */
    public Test getTest() {
        return test;
    }

    /**
     * Setting test which user has finished.
     *
     * @param test object Test which user has finished
     */
    public void setTest(Test test) {
        this.test = test;
    }
}
