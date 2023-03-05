package org.sozinx.service;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Service using in DeleteQuestionServlet and give all function that servlet need.
 * It works with data and deleting questions.
 *
 * @author Ostap Petruniak
 * @see org.sozinx.servlet.servlet.BlockServlet
 * @since 1.0
 */
public interface DeleteQuestionService {
    /**
     * Deleting question in current page.
     *
     * @param req object HttpServletRequest which gives current page request
     */
    void deleteQuestionAndAnswers(HttpServletRequest req);

    /**
     * Getting test's id from uri mapping
     *
     * @param req object HttpServletRequest which gives current page request
     * @return string with test's id
     */
    String getTestIdFromUri(final HttpServletRequest req);
}
