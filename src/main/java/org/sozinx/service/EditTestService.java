package org.sozinx.service;

import jakarta.servlet.http.HttpServletRequest;

public interface EditTestService {
    void editData(final HttpServletRequest req);

    String validationMessage(final HttpServletRequest req);

    void setAttributeTest(HttpServletRequest req);
}
