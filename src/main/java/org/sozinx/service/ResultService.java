package org.sozinx.service;

import jakarta.servlet.http.HttpServletRequest;

public interface ResultService {
    /**
     * Getting test's id from uri mapping
     *
     * @param req object HttpServletRequest which gives current page request
     * @return string with test's id
     */
    String getTestIdFromUri(final HttpServletRequest req);

    /**
     * Calculating result for current user on current test from logs.
     * This method counting incorrect and correct answers, and all count of answers.
     * Then counting result by formula.
     *
     * @param req object HttpServletRequest which gives current page request
     */
    void countResult(HttpServletRequest req);
}
