package org.sozinx.model;

/**
 * Model of Test from test table.
 *
 * @author Ostap Petruniak
 * @see Level
 * @see User
 * @since 1.0
 */
public class Test {
    private long id;
    private String name;
    private String subject;
    private final String created;
    private int isClose;
    private int time;
    private final long finished;

    private final User owner;
    private Level level;

    /**
     * Test constructor.
     *
     * @param id       test's id from table or 0 if it is a new object for table
     * @param name     test's name
     * @param subject  test's subject
     * @param created  date when test was created
     * @param isClose  is the test closed(1 - closed, 0 - not closed, 2 - for just created test, and it can't be open or closed)
     * @param time     duration time
     * @param finished how many times the test was finished(0 for just created test)
     * @param owner    test's creator
     * @param level    difficulty of the test
     */
    public Test(long id, String name, String subject, String created, int isClose, int time, long finished, User owner, Level level) {
        this.id = id;
        this.name = name;
        this.subject = subject;
        this.created = created;
        this.isClose = isClose;
        this.time = time;
        this.finished = finished;
        this.owner = owner;
        this.level = level;
    }

    /**
     * Getting test's id.
     *
     * @return test's id from table
     */
    public long getId() {
        return id;
    }

    /**
     * Setting test's id.
     *
     * @param id new test's id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Getting test's name.
     *
     * @return test's name
     */
    public String getName() {
        return name;
    }

    /**
     * Setting test's name.
     *
     * @param name new test's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getting test's subject.
     *
     * @return test's subject.
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Setting test's subject.
     *
     * @param subject new test's subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Getting date when test was created.
     *
     * @return date when test was created
     */
    public String getCreated() {
        return created;
    }

    /**
     * Getting is the test closed.
     *
     * @return test's closing info
     */
    public int getIsClose() {
        return isClose;
    }

    /**
     * Setting is the test closed.
     *
     * @param isClose new test's closing info
     */
    public void setIsClose(int isClose) {
        this.isClose = isClose;
    }

    /**
     * Getting test's duration time.
     *
     * @return duration time
     */
    public int getTime() {
        return time;
    }

    /**
     * Setting test's duration time.
     *
     * @param time new duration time
     */
    public void setTime(int time) {
        this.time = time;
    }

    /**
     * Getting how many times the test was finished.
     *
     * @return numbers times of finish
     */
    @SuppressWarnings("unused")
    public long getFinished() {
        return finished;
    }

    /**
     * Getting test's creator.
     *
     * @return object User who created the test
     */
    public User getOwner() {
        return owner;
    }

    /**
     * Getting difficulty of the test.
     *
     * @return object Level difficulty of the test
     */
    public Level getLevel() {
        return level;
    }

    /**
     * Setting difficulty of the test.
     *
     * @param level object Level new difficulty of the test
     */
    public void setLevel(Level level) {
        this.level = level;
    }

    /**
     * Getting string presentation of object.
     *
     * @return string presentation of Test
     */
    @Override
    public String toString() {
        return "Test{" +
                ", name='" + name + '\'' +
                ", subject='" + subject + '\'' +
                ", level=" + level.getLevel() +
                '}';
    }
}
