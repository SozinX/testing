package org.sozinx.service;

import jakarta.servlet.http.HttpServletRequest;

public interface AddTestService {
    void insertData(final HttpServletRequest req);
    String validationMessage(final HttpServletRequest req);
}
