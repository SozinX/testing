package org.sozinx.service;

import jakarta.servlet.http.HttpServletRequest;

public interface AddQuestionService {
    void insertData(final HttpServletRequest req);

    String validationMessage(final HttpServletRequest req);

    void setAttributeTest(HttpServletRequest req);
}
