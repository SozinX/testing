package org.sozinx.dao;

import org.sozinx.model.User;

public interface UserDAO {
    User getUserById(long id);

    User getUserByEmail(String email);

    boolean addUser(User user);

    boolean deleteUser(User user);

    boolean updateUser(User user, String[] params);
}
