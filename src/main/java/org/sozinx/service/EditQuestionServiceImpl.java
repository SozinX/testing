package org.sozinx.service;

import jakarta.servlet.http.HttpServletRequest;
import org.sozinx.model.Answer;
import org.sozinx.model.Question;

import java.util.List;
import java.util.Objects;

import static org.sozinx.constant.ErrorConst.*;

public class EditQuestionServiceImpl implements EditQuestionService {
    private final DataBaseService manager;
    @SuppressWarnings("all")
    private static EditQuestionServiceImpl service;

    public EditQuestionServiceImpl() {
        manager = DataBaseServiceImpl.getInstance();
    }

    public static synchronized EditQuestionServiceImpl getInstance() {
        if (service == null) return new EditQuestionServiceImpl();
        return service;
    }

    //Get test id(I've used mapping with different id in uri) from uri
    public String getTestIdFromUri(final HttpServletRequest req) {
        String uri = req.getRequestURI();
        return uri.substring(uri.lastIndexOf("/") + 1);
    }

    public List<Question> getAllQuestions(HttpServletRequest req) {
        return manager.getQuestionManager().getQuestionByTest(manager.getTestManager().getTestById(Long.parseLong(getTestIdFromUri(req))));
    }

    //Return count of pages which equals List of questions size
    public double getCountOfPages(HttpServletRequest req) {
        return getAllQuestions(req).size();
    }

    //Return current question
    public Question getQuestion(HttpServletRequest req) {
        String page = req.getParameter("question");
        if (Objects.equals(page, "") || page == null || page.equals("0")) {
            page = "1";
        }
        return getAllQuestions(req).get(checkOutOfBounds(req, page));
    }

    //Check if user trying to get question which is not present in List, and it is making an error
    private int checkOutOfBounds(HttpServletRequest req, String page) {
        List<Question> questions = getAllQuestions(req);
        if (Integer.parseInt(page) >= questions.size()) {
            return questions.size() - 1;
        } else if (Integer.parseInt(page) < 0) {
            return 0;
        }
        return Integer.parseInt(page) - 1;
    }

    //Return list of answers
    public List<Answer> getAnswers(Question question) {
        return manager.getAnswerManager().getAnswerByQuestion(question);
    }

    //Set request scope attributes for question updating
    public void setQuestionAttributes(HttpServletRequest req) {
        Question currentQuestion = getQuestion(req);
        req.setAttribute("currentTest", currentQuestion.getTest());
        req.setAttribute("currentQuestion", currentQuestion);
        req.setAttribute("currentAnswers", getAnswers(currentQuestion));
        req.setAttribute("pages", getCountOfPages(req));
        String currentPage = req.getParameter("question");
        if (Objects.equals(currentPage, "") || currentPage == null || Integer.parseInt(currentPage) <= 0) {
            currentPage = "1";
        }
        req.setAttribute("currentPage", currentPage);
    }

    //Check is question long present and long enough
    private static boolean questionIsValid(final HttpServletRequest req) {
        final String question = req.getParameter("questionText");
        return question != null && question.length() > 2;
    }

    //Check condition that in question with multiple answers must be two and more correct answers
    private static int multiAnswersForUpdateValid(final HttpServletRequest req) {
        int countOfCorrectAnswers = 0;
        for (int i = 1; i <= 10; i++) {
            String currentCheck = req.getParameter("qtu" + i);
            if (currentCheck != null) {
                countOfCorrectAnswers = countOfCorrectAnswers + Integer.parseInt(currentCheck);
            }
        }
        return countOfCorrectAnswers;
    }

    //Check condition that in question with single answer must be only one correct answer
    private static int singleAnswerForUpdateValid(final HttpServletRequest req) {
        int countOfCorrectAnswers = 0;
        for (int i = 1; i <= 10; i++) {
            String currentCheck = req.getParameter("qtu" + i);
            if (currentCheck != null) {
                countOfCorrectAnswers = countOfCorrectAnswers + Integer.parseInt(currentCheck);
            }
        }
        return countOfCorrectAnswers;
    }

    //Check condition that in question with multiple answers must be two and more correct answers
    private static boolean multiAnswersForInsertValid(final HttpServletRequest req) {
        int countOfCorrectAnswers = 0;
        for (int i = 1; i <= Integer.parseInt(req.getParameter("iterations")); i++) {
            String currentCheck = req.getParameter("qt" + i);
            if (currentCheck != null) {
                countOfCorrectAnswers = countOfCorrectAnswers + Integer.parseInt(currentCheck);
            }
        }
        return countOfCorrectAnswers + multiAnswersForUpdateValid(req) >= 2;
    }

    //Check condition that in question with single answer must be only one correct answer
    private static boolean singleAnswerForInsertValid(final HttpServletRequest req) {
        int countOfCorrectAnswers = 0;
        for (int i = 1; i <= Integer.parseInt(req.getParameter("iterations")); i++) {
            String currentCheck = req.getParameter("qt" + i);
            if (currentCheck != null) {
                countOfCorrectAnswers = countOfCorrectAnswers + Integer.parseInt(currentCheck);
            }
        }
        return countOfCorrectAnswers + singleAnswerForUpdateValid(req) == 1;
    }

    //Check is answer present and long enough
    private static boolean answerForUpdateIsValid(final HttpServletRequest req) {
        for (int i = 1; i <= 10; i++) {
            String currentCheck = req.getParameter("au" + i);
            if (currentCheck != null) {
                if (currentCheck.length() < 1) {
                    return false;
                }
            }
        }
        return true;
    }

    //Check is answer present and long enough
    private static boolean answerForInsertIsValid(final HttpServletRequest req) {
        for (int i = 1; i <= Integer.parseInt(req.getParameter("iterations")); i++) {
            String currentCheck = req.getParameter("a" + i);
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
        } else if (!multiAnswersForInsertValid(req) && Objects.equals(req.getParameter("questionType"), "2")) {
            return MULTIPLY_ERROR;
        } else if (!singleAnswerForInsertValid(req) && Objects.equals(req.getParameter("questionType"), "1")) {
            return SINGLE_ERROR;
        } else if (!answerForInsertIsValid(req) || !answerForUpdateIsValid(req)) {
            return ANSWER_ERROR;
        }
        return null;
    }

    //Check is question already present in test and print error if it is
    private String isQuestionPresentInTest(final HttpServletRequest req) {
        String question = req.getParameter("questionText");
        if (manager.getQuestionManager().getQuestionByName(question,
                Long.parseLong(getTestIdFromUri(req)),
                Long.parseLong(req.getParameter("questionId"))) == null) {
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

    //Update question and return already changed question
    private Question updateAndReturnQuestion(final HttpServletRequest req) {
        final long id = Long.parseLong(req.getParameter("questionId"));
        final String question = req.getParameter("questionText");
        final String type = req.getParameter("questionType");
        manager.getQuestionManager().updateQuestion(
                manager.getQuestionManager().getQuestionById(id),
                new String[]{question, type});
        return manager.getQuestionManager().getQuestionById(id);
    }

    private void insertAnswers(final HttpServletRequest req, Question question) {
        for (int i = 1; i <= Integer.parseInt(req.getParameter("iterations")); i++) {
            String currentCheckAnswer = req.getParameter("a" + i);
            String currentCheckCorrect = req.getParameter("qt" + i);
            if (currentCheckAnswer != null && currentCheckCorrect != null) {
                manager.getAnswerManager().addAnswer(new Answer(0, currentCheckAnswer, Integer.parseInt(currentCheckCorrect), question));
            }
        }
    }

    private void deleteAnswers(final HttpServletRequest req) {
        for (int i = 1; i <= 10; i++) {
            String currentCheckId = req.getParameter("delete" + i);
            if (currentCheckId != null) {
                manager.getAnswerManager().deleteAnswer(manager.getAnswerManager().getAnswerById(Long.parseLong(currentCheckId)));
            }
        }
    }

    private void updateAnswers(final HttpServletRequest req) {
        for (int i = 1; i <= 10; i++) {
            String currentCheckAnswer = req.getParameter("au" + i);
            String currentCheckCorrect = req.getParameter("qtu" + i);
            if (currentCheckAnswer != null && currentCheckCorrect != null) {
                manager.getAnswerManager().updateAnswer(manager.getAnswerManager().getAnswerById(Long.parseLong(req.getParameter("up" + i + "i"))),
                        new String[]{currentCheckAnswer, currentCheckCorrect});
            }
        }
    }

    //Do all Update/Delete/Insert methods. Finalising results
    public void insertAndUpdateData(final HttpServletRequest req) {
        insertAnswers(req, updateAndReturnQuestion(req));
        updateAnswers(req);
        deleteAnswers(req);
    }

    //Remove question with his answers from database
    public void deleteQuestionAndAnswers(HttpServletRequest req) {
        String page = req.getParameter("question");
        Question question = getQuestion(req);
        if (Objects.equals(page, "") || page == null || page.equals("0")) {
            manager.getAnswerManager().deleteAnswerByQuestion(question);
            manager.getQuestionManager().deleteQuestion(question);
        }
    }
}
