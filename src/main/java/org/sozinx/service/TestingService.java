package org.sozinx.service;

import jakarta.servlet.http.HttpServletRequest;

public interface TestingService {

    String getTestIdFromUri(final HttpServletRequest req);

    void setQuestionAttributes(HttpServletRequest req);

    void saveAnswer(HttpServletRequest req);

    double getCountOfPages(HttpServletRequest req);
}
