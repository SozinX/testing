package org.sozinx.model;

/**
 * Model of Level from level table.
 *
 * @author Ostap Petruniak
 * @since 1.0
 */
public class Level {
    private int id;
    private String level;

    /**
     * Level constructor.
     *
     * @param id    level's id
     * @param level level's name
     */
    public Level(int id, String level) {
        this.id = id;
        this.level = level;
    }

    /**
     * Getting level's id.
     *
     * @return level's id from table
     */
    public int getId() {
        return id;
    }

    /**
     * Setting level's id.
     *
     * @param id new level's id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getting level's name.
     *
     * @return level's name from table
     */
    public String getLevel() {
        return level;
    }

    /**
     * Setting level's name.
     *
     * @param level new level's name
     */
    public void setLevel(String level) {
        this.level = level;
    }
}
