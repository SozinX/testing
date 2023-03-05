package org.sozinx.service;

import jakarta.servlet.http.HttpServletRequest;
import org.sozinx.model.Test;

import java.util.List;

/**
 * Service using in MyTestsServlet and give all function that servlet need.
 * It works with data. Showing all tests tha user created.
 *
 * @author Ostap Petruniak
 * @see Test
 * @see org.sozinx.servlet.servlet.MyTestsServlet
 * @since 1.0
 */
public interface TestsByOwnerService {
    /**
     * Getting number of pages number of pages needed to show all tests of current owner.
     *
     * @param req object HttpServletRequest which gives current page request
     * @return number of pages needed to show all tests
     */
    double getCountOfPagesByOwner(HttpServletRequest req);

    /**
     * Getting 12 tests that could show on current page.
     *
     * @param req object HttpServletRequest which gives current page request
     * @return list of object Test that could show on current page
     */
    List<Test> getTestsByOwner(HttpServletRequest req);

    /**
     * Setting user's attributes for showing correct tests.
     *
     * @param req object HttpServletRequest which gives current page request
     */
    void setUserAttributes(HttpServletRequest req);

}
