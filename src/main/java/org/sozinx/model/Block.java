package org.sozinx.model;

public class Block {
    private long id;
    private User teacher;
    private User student;
    private String dateOfBlock;
    private  String dateOfUnblock;

    public Block(long id, User teacher, User student, String dateOfBlock, String dateOfUnblock) {
        this.id = id;
        this.teacher = teacher;
        this.student = student;
        this.dateOfBlock = dateOfBlock;
        this.dateOfUnblock = dateOfUnblock;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public String getDateOfBlock() {
        return dateOfBlock;
    }

    public void setDateOfBlock(String dateOfBlock) {
        this.dateOfBlock = dateOfBlock;
    }

    public String getDateOfUnblock() {
        return dateOfUnblock;
    }

    public void setDateOfUnblock(String dateOfUnblock) {
        this.dateOfUnblock = dateOfUnblock;
    }
}
