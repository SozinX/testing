package org.sozinx.model;

public class Block {
    private long id;
    private final User teacher;
    private final User student;
    private String block;
    private String unblock;

    public Block(long id, User teacher, User student, String dateOfBlock, String dateOfUnblock) {
        this.id = id;
        this.teacher = teacher;
        this.student = student;
        this.block = dateOfBlock;
        this.unblock = dateOfUnblock;
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

    public User getStudent() {
        return student;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getUnblock() {
        return unblock;
    }

    public void setUnblock(String unblock) {
        this.unblock = unblock;
    }

}
