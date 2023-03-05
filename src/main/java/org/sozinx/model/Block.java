package org.sozinx.model;

/**
 * Model of Block from block table.
 *
 * @author Ostap Petruniak
 * @see User
 * @since 1.0
 */
public class Block {
    private long id;
    private final User teacher;
    private final User student;
    private String block;
    private String unblock;

    /**
     * Block constructor.
     *
     * @param id            block's id or 0 if it is a new object for table
     * @param teacher       teacher's id (the one who blocks)
     * @param student       student's id (the one who is blocked)
     * @param dateOfBlock   date of block
     * @param dateOfUnblock date of unblock(null safety)
     */
    public Block(long id, User teacher, User student, String dateOfBlock, String dateOfUnblock) {
        this.id = id;
        this.teacher = teacher;
        this.student = student;
        this.block = dateOfBlock;
        this.unblock = dateOfUnblock;
    }

    /**
     * Getting block's id.
     *
     * @return block's id from table
     */
    public long getId() {
        return id;
    }

    /**
     * Setting block's id.
     *
     * @param id new block's id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Getting the blocking user.
     *
     * @return object User who blocked another user
     */
    public User getTeacher() {
        return teacher;
    }

    /**
     * Getting user who was blocked.
     *
     * @return object User who was blocked
     */
    public User getStudent() {
        return student;
    }

    /**
     * Getting date of block.
     *
     * @return blocking date
     */
    public String getBlock() {
        return block;
    }

    /**
     * Setting block's date.
     *
     * @param block new block's date
     */
    public void setBlock(String block) {
        this.block = block;
    }

    /**
     * Getting date of unblock.
     *
     * @return unblocking date
     */
    public String getUnblock() {
        return unblock;
    }

    /**
     * Setting date of unblock.
     *
     * @param unblock new date of unblock
     */
    public void setUnblock(String unblock) {
        this.unblock = unblock;
    }

}
