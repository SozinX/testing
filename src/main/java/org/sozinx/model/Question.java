package org.sozinx.model;

/**
 * Model of Question from question table.
 *
 * @author Ostap Petruniak
 * @see Test
 * @see Type
 * @since 1.0
 */
public class Question {
    private int id;
    private String question;
    private Test test;
    private Type type;

    /**
     * Question constructor.
     *
     * @param id       question's id or 0 if it is a new object for table
     * @param question question's text
     * @param test     object Test which contains the question
     * @param type     object Type which characterizes the question
     */
    public Question(int id, String question, Test test, Type type) {
        this.id = id;
        this.question = question;
        this.test = test;
        this.type = type;
    }

    /**
     * Getting question's id.
     *
     * @return question's id from table
     */
    public int getId() {
        return id;
    }

    /**
     * Setting question's id.
     *
     * @param id new question's id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getting question's text.
     *
     * @return question's text
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Setting question's text.
     *
     * @param question new question's text
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * Getting test.
     *
     * @return object Test which this question present
     */
    public Test getTest() {
        return test;
    }

    /**
     * Setting test.
     *
     * @param test new object Test which this question present
     */
    public void setTest(Test test) {
        this.test = test;
    }

    /**
     * Getting type.
     *
     * @return object Type which characterizes the question
     */
    public Type getType() {
        return type;
    }

    /**
     * Setting type.
     *
     * @param type new object Type which characterizes the question
     */
    public void setType(Type type) {
        this.type = type;
    }
}
