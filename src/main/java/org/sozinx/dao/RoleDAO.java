package org.sozinx.dao;

import org.sozinx.model.Role;

/**
 * Using Role model for create, read, update and delete operations in database's table <b>role</b>.
 *
 * @author Ostap Petruniak
 * @see Role
 * @since 1.0
 */
public interface RoleDAO {
    /**
     * Getting role by his id from table.
     *
     * @param id role's id from table
     * @return object Role with table's data
     */
    Role getRoleById(int id);

}
