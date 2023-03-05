package org.sozinx.dao;

import org.sozinx.model.User;

/**
 * Using User model for create, read, update and delete operations in database's table <b>user</b>.
 *
 * @author Ostap Petruniak
 * @see User
 * @since 1.0
 */
public interface UserDAO {
    /**
     * Getting user by his id from table.
     *
     * @param id user's id from table
     * @return object User with table's data
     */
    User getUserById(long id);

    /**
     * Getting user by his email from table.
     *
     * @param email user's email from table
     * @return object User with table's data
     */
    User getUserByEmail(String email);

    /**
     * Adding user in table.
     *
     * @param user object User which we want to add in table
     * @return boolean value has the user been added
     */
    boolean addUser(User user);

    /**
     * Deleting answers in table.
     *
     * @param user object User which we want delete
     * @return boolean value has the user been deleted
     */
    boolean deleteUser(User user);

    /**
     * Updating user in table.
     *
     * @param user   object User which we want to update in table
     * @param params String's array with parameters which we want change. Array order:<br>
     *               1) User's name;<br>
     *               2) User's email; <br>
     *               3) User's role; <br>
     *               4) User's password. <br>
     * @return boolean value has the user been updated
     */
    boolean updateUser(User user, String[] params);
}
