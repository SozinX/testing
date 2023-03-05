package org.sozinx.model;

/**
 * Model of Log from log table.
 *
 * @author Ostap Petruniak
 * @see User
 * @see Test
 * @see Question
 * @see Answer
 * @since 1.0
 */
public class Log {
    private long id;
    private User user;
    private Test test;
    private Question question;
    private Answer answer;

    /**
     * Log constructor.
     *
     * @param id       log's id or 0 if it is a new object for table
     * @param user     object User who answered the question
     * @param test     object Test which user is completing
     * @param question object Question which user answered
     * @param answer   object Answer which user chose for question
     */
    public Log(long id, User user, Test test, Question question, Answer answer) {
        this.id = id;
        this.user = user;
        this.test = test;
        this.question = question;
        this.answer = answer;
    }

    /**
     * Getting log's id.
     *
     * @return log's id from table
     */
    public long getId() {
        return id;
    }

    /**
     * Setting log id.
     *
     * @param id new log's id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Getting user who answered the question.
     *
     * @return object User from table
     */
    public User getUser() {
        return user;
    }

    /**
     * Setting user who answered the question.
     *
     * @param user new object User
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Getting test which user is completing.
     *
     * @return object Test from table
     */
    public Test getTest() {
        return test;
    }

    /**
     * Setting test which user is completing.
     *
     * @param test new object Test
     */
    public void setTest(Test test) {
        this.test = test;
    }

    /**
     * Getting question which user answered.
     *
     * @return object Question from table
     */
    public Question getQuestion() {
        return question;
    }

    /**
     * Setting question which user answered.
     *
     * @param question new object Question
     */
    public void setQuestion(Question question) {
        this.question = question;
    }

    /**
     * Getting answer which user chose for question.
     *
     * @return object Answer from table
     */
    public Answer getAnswer() {
        return answer;
    }

    /**
     * Setting answer which user chose for question.
     *
     * @param answer new object Answer
     */
    public void setAnswer(Answer answer) {
        this.answer = answer;
    }
}
