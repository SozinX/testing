package org.sozinx.dao;

import org.sozinx.model.Block;
import org.sozinx.model.User;

import java.util.List;

public interface BlockDAO {
    List<Block> getBlockByUser(User user);

    void blockUser(Block block);

    void unblockUser(Block block, String unblock);

    @SuppressWarnings("unused")
    boolean deleteBlockByUser(User user);
}
