package org.sozinx.service;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Service using in AddQuestionServlet and give all function that servlet need.
 * It gives attributes for request.
 *
 * @author Ostap Petruniak
 * @see org.sozinx.servlet.servlet.AddQuestionServlet
 * @since 1.0
 */
public interface AddQuestionService extends DataManipulationsService {
    /**
     * Setting attributes in request about current Test.
     *
     * @param req object HttpServletRequest which gives current page request
     */
    void setAttributeTest(HttpServletRequest req);
}
