package org.sozinx.dao;

import org.sozinx.model.Block;
import org.sozinx.model.User;

public interface BlockDAO {
    Block getBlockByUser(User user);
    boolean blockUser(User user);
    boolean unblockUser(User user);
}
