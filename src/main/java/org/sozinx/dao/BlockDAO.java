package org.sozinx.dao;

import org.sozinx.model.Block;
import org.sozinx.model.User;

import java.util.List;

/**
 * Using Block model for create, read, update and delete operations in database's table <b>block</b>.
 *
 * @author Ostap Petruniak
 * @see Block
 * @see User
 * @since 1.0
 */
public interface BlockDAO {
    /**
     * Getting list of blocks for user.
     *
     * @param user object User which blocks we want to get
     * @return list of blocks for question
     */
    List<Block> getBlockByUser(User user);

    /**
     * Blocking user.
     *
     * @param block object Block which we want insert to add in table
     */
    void blockUser(Block block);

    /**
     * Unblocking user.
     *
     * @param block   object Block which we want insert to add in table
     * @param unblock date of unblocking
     */
    void unblockUser(Block block, String unblock);

    /**
     * Delete all records of user's blocking.
     *
     * @param user object User
     */
    @SuppressWarnings("unused")
    boolean deleteBlockByUser(User user);
}
