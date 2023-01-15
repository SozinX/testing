package org.sozinx.dao;

import org.sozinx.model.Block;
import org.sozinx.model.User;

import java.util.List;

public interface BlockDAO {
    List<Block> getBlockByUser(User user);

    boolean blockUser(Block block);

    boolean unblockUser(Block block, String unblock);

    boolean deleteBlockByUser(User user);
}
