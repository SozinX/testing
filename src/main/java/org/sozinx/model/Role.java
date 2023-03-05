package org.sozinx.model;

/**
 * Model of Question from question table.
 *
 * @author Ostap Petruniak
 * @since 1.0
 */
public class Role {
    private int id;
    private String role;

    /**
     * Role constructor.
     *
     * @param id role's id from table
     * @param role role's name
     */
    public Role(int id, String role) {
        this.id = id;
        this.role = role;
    }

    /**
     * Getting role's id.
     *
     * @return role's id from table
     */
    public int getId() {
        return id;
    }
    /**
     * Setting role's id.
     *
     * @param id new role's id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getting role's name.
     *
     * @return role's name from table
     */
    public String getRole() {
        return role;
    }

    /**
     * Setting role's name.
     *
     * @param role new role's name
     */
    public void setRole(String role) {
        this.role = role;
    }
}
