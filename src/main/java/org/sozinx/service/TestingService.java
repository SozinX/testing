package org.sozinx.service;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Service using in TestingServlet and give all function that servlet need.
 * It works with data and make testing system more comfortable. Also setting attributes in request.
 *
 * @author Ostap Petruniak
 * @see org.sozinx.servlet.servlet.TestingServlet
 * @since 1.0
 */
public interface TestingService {
    /**
     * Getting test's id from uri mapping
     *
     * @param req object HttpServletRequest which gives current page request
     * @return string with test's id
     */
    String getTestIdFromUri(final HttpServletRequest req);

    /**
     * Setting attributes in request about current Question.
     *
     * @param req object HttpServletRequest which gives current page request
     */
    void setQuestionAttributes(HttpServletRequest req);

    /**
     * Saving user answer in database.
     *
     * @param req object HttpServletRequest which gives current page request
     */
    void saveAnswer(HttpServletRequest req);

    /**
     * Getting number of pages for pagination.
     *
     * @param req object HttpServletRequest which gives current page request
     * @return number of all pages (number of questions in current case)
     */
    double getCountOfPages(HttpServletRequest req);
}
