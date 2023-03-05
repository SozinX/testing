package org.sozinx.service;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Service using in AddTestServlet and give all function that servlet need.
 * It gives attributes for request.
 *
 * @author Ostap Petruniak
 * @see org.sozinx.servlet.servlet.AddTestServlet
 * @since 1.0
 */
public interface AddTestService extends DataManipulationsService {
    /**
     * Setting attributes in request about last added Test.
     *
     * @param req object HttpServletRequest which gives current page request
     */
    void setLastAddedTest(HttpServletRequest req);
}
