package org.sozinx.service;

import jakarta.servlet.http.HttpServletRequest;
import org.sozinx.model.Answer;
import org.sozinx.model.Question;

import java.util.List;

/**
 * Service using in EditQuestionServlet and give all function that servlet need.
 * It works with data and does the validation. Also gives attributes for request.
 *
 * @author Ostap Petruniak
 * @see org.sozinx.servlet.servlet.EditQuestionServlet
 * @see Question
 * @see Answer
 * @since 1.0
 */
public interface EditQuestionService extends DataManipulationsService {
    /**
     * Getting number of pages for pagination.
     *
     * @param req object HttpServletRequest which gives current page request
     * @return number of all pages (number of questions in current case)
     */
    double getCountOfPages(HttpServletRequest req);

    /**
     * Getting question on current page.
     *
     * @param req object HttpServletRequest which gives current page request
     * @return object Question that could be presenting on current page
     */
    Question getQuestion(HttpServletRequest req);

    /**
     * Getting answers for current question.
     *
     * @param question object Question that is presenting on current page
     * @return list of object Answer for current question
     */
    List<Answer> getAnswers(Question question);

    /**
     * Getting test's id from uri mapping
     *
     * @param req object HttpServletRequest which gives current page request
     * @return string with test's id
     */
    String getTestIdFromUri(final HttpServletRequest req);

    /**
     * Setting attributes in request about question and answers.
     *
     * @param req object HttpServletRequest which gives current page request
     */
    void setQuestionAttributes(HttpServletRequest req);

    /**
     * Getting questions on current test.
     *
     * @param req object HttpServletRequest which gives current page request
     * @return list of object Question that could be presenting on current test
     */
    List<Question> getAllQuestions(HttpServletRequest req);

}
