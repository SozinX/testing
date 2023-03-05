package org.sozinx.service;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Service using in ViewTestServlet and give all function that servlet need.
 * It shows tests to user and manage access to testing system.
 *
 * @author Ostap Petruniak
 * @see org.sozinx.servlet.servlet.ViewTestServlet
 * @since 1.0
 */
public interface ViewTestService {
    /**
     * Getting time for finishing the test.
     *
     * @param testId test which is shown
     * @return duration time
     */
    int getTestTime(long testId);

    /**
     * Setting attributes about this test. Is finished for current user or not.
     *
     * @param req object HttpServletRequest which gives current page request
     */
    void setAttributeTest(HttpServletRequest req);

    /**
     * Getting test's id from uri mapping
     *
     * @param req object HttpServletRequest which gives current page request
     * @return string with test's id
     */
    String getTestIdFromUri(final HttpServletRequest req);

    /**
     * Setting attributes for testing which needed in front end to show correct duration time.
     *
     * @param req object HttpServletRequest which gives current page request
     */
    void setAttributesForTesting(HttpServletRequest req);
}
