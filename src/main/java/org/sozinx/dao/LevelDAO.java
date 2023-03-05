package org.sozinx.dao;

import org.sozinx.model.Level;

/**
 * Using Level model for create, read, update and delete operations in database's table <b>level</b>.
 *
 * @author Ostap Petruniak
 * @see Level
 * @since 1.0
 */
public interface LevelDAO {
    /**
     * Getting level by his id from table.
     *
     * @param id level's id from table
     * @return object Level with table's data
     */
    Level getLevelById(int id);
}
