package org.sozinx.dao;

import org.sozinx.model.Test;
import org.sozinx.model.User;

import java.util.List;

public interface UserDAO {
    User getUserById(long id);
    User getUserByEmail(String email);
    boolean addUser(User user);
    boolean deleteUser(User user);
    boolean updateUser(User user);
}
