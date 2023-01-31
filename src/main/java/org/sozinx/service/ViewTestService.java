package org.sozinx.service;

import jakarta.servlet.http.HttpServletRequest;

public interface ViewTestService {
    int getTestTime(long testId);

    void setAttributeTest(HttpServletRequest req);

    String getTestIdFromUri(final HttpServletRequest req);

    void setAttributesForTesting(HttpServletRequest req);
}
