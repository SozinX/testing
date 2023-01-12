package org.sozinx.model;

public class Question {
    private int id;
    private String question;
    private Test test;
    private Type type;

    public Question(int id, String question, Test test, Type type) {
        this.id = id;
        this.question = question;
        this.test = test;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
