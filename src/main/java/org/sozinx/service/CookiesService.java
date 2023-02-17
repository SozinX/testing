package org.sozinx.service;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface CookiesService {
    void filterCheckLanguage(HttpServletRequest req, HttpServletResponse resp);
}
