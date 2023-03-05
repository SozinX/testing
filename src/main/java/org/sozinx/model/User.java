package org.sozinx.model;
/**
 * Model of user from user table.
 *
 * @author Ostap Petruniak
 * @see Role
 * @since 1.0
 */
public class User {
    private long id;
    private String name;
    private String email;
    private String password;
    private final String registration;
    private Role role;

    /**
     * User constructor.
     *
     * @param id user's id from table or 0 if it is a new object for table
     * @param name user's name
     * @param email user's email
     * @param password user's password
     * @param registration registration date
     * @param role object Role which the user has
     */
    public User(long id, String name, String email, String password, String registration, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.registration = registration;
        this.role = role;
    }
    /**
     * Getting user's id.
     *
     * @return user's id from table
     */
    public long getId() {
        return id;
    }
    /**
     * Setting user's id.
     *
     * @param id new user's id
     */
    public void setId(long id) {
        this.id = id;
    }
    /**
     * Getting user's name.
     *
     * @return user's name
     */
    public String getName() {
        return name;
    }
    /**
     * Setting user's name.
     *
     * @param name new user's name
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Getting user's email.
     *
     * @return user's email
     */
    public String getEmail() {
        return email;
    }
    /**
     * Setting user's email.
     *
     * @param email new user's email
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Getting user's password.
     *
     * @return user's password
     */
    public String getPassword() {
        return password;
    }
    /**
     * Setting user's password.
     *
     * @param password new user's password
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * Getting user's registration date.
     *
     * @return registration date
     */
    public String getRegistration() {
        return registration;
    }

    /**
     * Getting user's role.
     *
     * @return object Role which the user has
     */
    public Role getRole() {
        return role;
    }
    /**
     * Setting user's role.
     *
     * @param role new object Role which the user has
     */
    public void setRole(Role role) {
        this.role = role;
    }
}
