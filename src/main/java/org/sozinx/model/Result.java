package org.sozinx.model;

public class Result {
    private long id;
    private String date;
    private int result;
    private User user;
    private Test test;

    public Result(long id, String date, int result, User user, Test test) {
        this.id = id;
        this.date = date;
        this.result = result;
        this.user = user;
        this.test = test;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }
}
