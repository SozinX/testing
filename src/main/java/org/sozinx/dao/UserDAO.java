package org.sozinx.dao;

import org.sozinx.model.User;

public interface UserDAO {
    User getUserById(long id);

    User getUserByEmail(String email);

    void addUser(User user);

    void deleteUser(User user);

    void updateUser(User user, String[] params);
}
