package org.sozinx.service;

import jakarta.servlet.http.HttpServletRequest;

public interface ResultService {
    String getTestIdFromUri(final HttpServletRequest req);

    void countResult(HttpServletRequest req);
}
