package org.sozinx.service;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Service using in MainFilter and managing language's cookie.
 *
 * @author Ostap Petruniak
 * @see org.sozinx.servlet.filter.MainFilter
 * @since 1.0
 */
public interface CookiesService {
    /**
     * Checking language in cookies. If cookies is not present - create it and set default value.
     *
     * @param req  object HttpServletRequest which gives current page request
     * @param resp object HttpServletResponse which gives current page response
     */
    void filterCheckLanguage(HttpServletRequest req, HttpServletResponse resp);
}
