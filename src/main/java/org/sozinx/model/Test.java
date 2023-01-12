package org.sozinx.model;

public class Test {
    private long id;
    private String name;
    private String subject;
    private  String created;
    private int isClose;
    private int time;
    private User owner;
    private Level level;

    public Test(long id, String name, String subject, String created, int isClose, int time, User owner, Level level) {
        this.id = id;
        this.name = name;
        this.subject = subject;
        this.created = created;
        this.isClose = isClose;
        this.time = time;
        this.owner = owner;
        this.level = level;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public int getIsClose() {
        return isClose;
    }

    public void setIsClose(int isClose) {
        this.isClose = isClose;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
}
