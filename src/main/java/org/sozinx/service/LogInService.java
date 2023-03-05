package org.sozinx.service;

import jakarta.servlet.http.HttpServletRequest;
import org.sozinx.model.User;

/**
 * Service using in LogInServlet and give all function that servlet need.
 * It works with data and does the validation. Also gives attributes for request.
 *
 * @author Ostap Petruniak
 * @see org.sozinx.servlet.servlet.LogInServlet
 * @see User
 * @since 1.0
 */
public interface LogInService {
    /**
     * Checking inputs for data correctness.
     *
     * @param req object HttpServletRequest which gives current page request
     * @return string with error or null if everything is ok and there are no errors.
     */
    String inputIsCorrect(final HttpServletRequest req);

    /**
     * Getting user that we are checking for email and password correctness.
     *
     * @return object User that we are checking
     */
    User getCheckingUser();

    /**
     * Setting session attributes about user.
     *
     * @param req object HttpServletRequest which gives current page request
     */
    void setAttributes(HttpServletRequest req);
}
