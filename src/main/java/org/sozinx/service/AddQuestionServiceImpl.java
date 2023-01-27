package org.sozinx.service;

import jakarta.servlet.http.HttpServletRequest;
import org.sozinx.model.Answer;
import org.sozinx.model.Question;
import org.sozinx.model.Test;

import java.util.Objects;

import static org.sozinx.constant.ErrorConst.*;

public class AddQuestionServiceImpl implements AddQuestionService {
    private final DataBaseService manager;
    @SuppressWarnings("all")
    private static AddQuestionServiceImpl service;

    public AddQuestionServiceImpl() {
        manager = DataBaseServiceImpl.getInstance();
    }

    public static synchronized AddQuestionServiceImpl getInstance() {
        if (service == null) return new AddQuestionServiceImpl();
        return service;
    }

    //Check is question long present and long enough
    private static boolean questionIsValid(final HttpServletRequest req) {
        final String question = req.getParameter("question");
        return question != null && question.length() > 0;
    }

    //Check condition that in question with multiple answers must be two and more correct answers
    private static boolean multiAnswersValid(final HttpServletRequest req) {
        int countOfCorrectAnswers = 0;
        for (int i = 1; i <= Integer.parseInt(req.getParameter("iterations")); i++) {
            String currentCheck = req.getParameter("qt" + i);
            if (currentCheck != null) {
                countOfCorrectAnswers = countOfCorrectAnswers + Integer.parseInt(currentCheck);
            }
        }
        return countOfCorrectAnswers >= 2;
    }

    //Check condition that in question with single answer must be only one correct answer
    private static boolean singleAnswerValid(final HttpServletRequest req) {
        int countOfCorrectAnswers = 0;
        for (int i = 1; i <= Integer.parseInt(req.getParameter("iterations")); i++) {
            String currentCheck = req.getParameter("qt" + i);
            if (currentCheck != null) {
                countOfCorrectAnswers = countOfCorrectAnswers + Integer.parseInt(currentCheck);
            }
        }
        return countOfCorrectAnswers == 1;
    }

    //Check is answer present and long enough
    private static boolean answerIsValid(final HttpServletRequest req) {
        for (int i = 2; i <= Integer.parseInt(req.getParameter("iterations")); i++) {
            String currentCheck = req.getParameter("a" + 1);
            if (currentCheck != null) {
                if (currentCheck.length() < 1) {
                    return false;
                }
            }
        }
        return true;
    }

    //Sum all validation method in one and get error messages
    private String inputIsValid(final HttpServletRequest req) {
        if (!questionIsValid(req)) {
            return QUESTION_ERROR;
        } else if (!multiAnswersValid(req) && Objects.equals(req.getParameter("question-type"), "2")) {
            return MULTIPLY_ERROR;
        } else if (!singleAnswerValid(req) && Objects.equals(req.getParameter("question-type"), "1")) {
            return SINGLE_ERROR;
        } else if (!answerIsValid(req)) {
            return ANSWER_ERROR;
        }
        return null;
    }

    //Get test id(I've used mapping with different id in uri) from uri
    private String getTestIdFromUri(final HttpServletRequest req) {
        String uri = req.getRequestURI();
        return uri.substring(uri.lastIndexOf("/") + 1);
    }

    //Check is question already present in test and print error if it is
    private String isQuestionPresentInTest(final HttpServletRequest req) {
        String question = req.getParameter("question");
        if (manager.getQuestionManager().getQuestionByName(question,
                Long.parseLong(getTestIdFromUri(req)), 0) == null) {
            return null;
        } else {
            return QUESTION_IS_PRESENT;
        }
    }

    //Get validation message
    public String validationMessage(final HttpServletRequest req) {
        String inputIsValid = inputIsValid(req);
        String isQuestionPresentInTest = isQuestionPresentInTest(req);
        if (inputIsValid != null) {
            return inputIsValid;
        } else return isQuestionPresentInTest;
    }

    //Insert question and return already inserted question
    private Question insertAndReturnQuestion(final HttpServletRequest req) {
        Test test = manager.getTestManager().getTestById(Long.parseLong(getTestIdFromUri(req)));
        final String question = req.getParameter("question");
        final String type = req.getParameter("question-type");
        manager.getQuestionManager().addQuestion(new Question(0, question, test, manager.getTypeManager().getTypeById(Integer.parseInt(type))));
        return manager.getQuestionManager().getLastQuestionByTest(test);
    }

    //Insert answers
    private void insertAnswers(final HttpServletRequest req, Question question) {
        for (int i = 1; i <= Integer.parseInt(req.getParameter("iterations")); i++) {
            String currentCheckAnswer = req.getParameter("a" + i);
            String currentCheckCorrect = req.getParameter("qt" + i);
            if (currentCheckAnswer != null && currentCheckCorrect != null) {
                manager.getAnswerManager().addAnswer(new Answer(0, currentCheckAnswer, Integer.parseInt(currentCheckCorrect), question));
            }
        }
    }

    //Use previous methods in one and set test closable changeable(user can open and close his test)
    public void insertData(final HttpServletRequest req) {
        insertAnswers(req, insertAndReturnQuestion(req));
        manager.getTestManager().openTest(manager.getTestManager().getTestById(Long.parseLong(getTestIdFromUri(req))));
    }

    //Get test from uri and set it in request scope
    public void setAttributeTest(HttpServletRequest req) {
        req.setAttribute("currentTest", manager.getTestManager().getTestById(Long.parseLong(getTestIdFromUri(req))));
    }
}
