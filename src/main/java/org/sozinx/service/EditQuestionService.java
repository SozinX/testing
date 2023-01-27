package org.sozinx.service;

import jakarta.servlet.http.HttpServletRequest;
import org.sozinx.model.Answer;
import org.sozinx.model.Question;
import org.sozinx.model.Test;

import java.util.List;

public interface EditQuestionService {
    double getCountOfPages(HttpServletRequest req);

    Question getQuestion(HttpServletRequest req);

    List<Answer> getAnswers(Question question);

    String getTestIdFromUri(final HttpServletRequest req);

    void setQuestionAttributes(HttpServletRequest req);

    void insertAndUpdateData(final HttpServletRequest req);

    String validationMessage(final HttpServletRequest req);

    List<Question> getAllQuestions(HttpServletRequest req);
    void deleteQuestionAndAnswers(HttpServletRequest req);

}
