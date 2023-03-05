package org.sozinx.service;

import jakarta.servlet.http.HttpServletRequest;
import org.sozinx.model.Test;

import java.util.List;

/**
 * Service using in TestsServlet and give all function that servlet need.
 * It works with data. Showing all tests tha user created.
 *
 * @author Ostap Petruniak
 * @see org.sozinx.servlet.servlet.TestsServlet
 * @see Test
 * @since 1.0
 */
public interface TestsService {
    /**
     * Getting number of pages number of pages needed to show all tests.
     *
     * @param req object HttpServletRequest which gives current page request
     * @return number of pages needed to show all tests
     */
    double getCountOfPages(HttpServletRequest req);

    /**
     * Getting 12 tests that could show on current page.
     *
     * @param req object HttpServletRequest which gives current page request
     * @return list of object Test that could show on current page
     */
    List<Test> getTests(HttpServletRequest req);

    /**
     * Setting user's attributes for showing correct tests.
     *
     * @param req object HttpServletRequest which gives current page request
     */
    void setUserAttributes(HttpServletRequest req);

}
