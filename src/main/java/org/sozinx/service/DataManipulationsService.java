package org.sozinx.service;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Interface for methods which checking data in patterns and doing inserting, deleting, updating operations.
 *
 * @author Ostap Petruniak
 * @since 1.0
 */
public interface DataManipulationsService {
    /**
     * Checking inputs for data correctness.
     *
     * @param req object HttpServletRequest which gives current page request
     * @return string with error or null if everything is ok and there are no errors.
     */
    String validationMessage(final HttpServletRequest req);

    /**
     * Inserting/editing/deleting data in database.
     *
     * @param req object HttpServletRequest which gives current page request
     */
    void insertData(final HttpServletRequest req);
}
