package org.sozinx.service;

import jakarta.servlet.http.HttpServletRequest;


public interface SignUpService {
    String validationMessage(final HttpServletRequest req);

    void insertData(final HttpServletRequest req);
}
