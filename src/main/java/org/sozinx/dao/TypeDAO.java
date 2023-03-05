package org.sozinx.dao;

import org.sozinx.model.Type;

/**
 * Using Type model for create, read, update and delete operations in database's table <b>type</b>.
 *
 * @author Ostap Petruniak
 * @see Type
 * @since 1.0
 */
public interface TypeDAO {
    /**
     * Getting type by his id from table.
     *
     * @param id table's type id
     * @return object Type with table's data
     */
    Type getTypeById(int id);
}
