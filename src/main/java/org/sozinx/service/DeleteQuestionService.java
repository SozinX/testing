package org.sozinx.service;

import jakarta.servlet.http.HttpServletRequest;

public interface DeleteQuestionService {
    void deleteQuestionAndAnswers(HttpServletRequest req);

    String getTestIdFromUri(final HttpServletRequest req);
}
