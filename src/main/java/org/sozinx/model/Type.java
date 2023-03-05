package org.sozinx.model;

/**
 * Model of Type from type table.
 *
 * @author Ostap Petruniak
 * @since 1.0
 */
public class Type {
    private int id;
    private String type;

    /**
     * Type constructor.
     *
     * @param id   type's id
     * @param type type's name
     */
    public Type(int id, String type) {
        this.id = id;
        this.type = type;
    }

    /**
     * Getting type's id.
     *
     * @return type's id from table
     */
    public int getId() {
        return id;
    }

    /**
     * Setting type's id.
     *
     * @param id new type's id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getting type's name.
     *
     * @return type's name from table
     */
    public String getType() {
        return type;
    }

    /**
     * Setting type's name.
     *
     * @param type new type's name
     */
    public void setType(String type) {
        this.type = type;
    }
}
