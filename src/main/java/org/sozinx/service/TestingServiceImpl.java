package org.sozinx.service;

import jakarta.servlet.http.HttpServletRequest;
import org.sozinx.model.*;

import java.util.*;


public class TestingServiceImpl implements TestingService {
    private final DataBaseService manager;
    @SuppressWarnings("all")
    private static TestingServiceImpl service;

    public TestingServiceImpl() {
        manager = DataBaseServiceImpl.getInstance();
    }

    public static synchronized TestingServiceImpl getInstance() {
        if (service == null) return new TestingServiceImpl();
        return service;
    }

    private List<Question> getAllQuestions(HttpServletRequest req) {
        return manager.getQuestionManager().getQuestionByTest(manager.getTestManager().getTestById(Long.parseLong(String.valueOf(req.getSession().getAttribute("testId")))));
    }

    //Return count of pages which equals List of questions size
    @Override
    public double getCountOfPages(HttpServletRequest req) {
        return getAllQuestions(req).size();
    }

    @Override
    public String getTestIdFromUri(final HttpServletRequest req) {
        String uri = req.getRequestURI();
        return uri.substring(uri.lastIndexOf("/") + 1);
    }

    //Return current question
    private Question getQuestion(HttpServletRequest req) {
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
    private List<Answer> getAnswers(Question question) {
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

    private List<Answer> getAnswers(HttpServletRequest req) {
        String[] answersText = req.getParameterValues("answerCheck");
        List<Answer> answers = new ArrayList<>();
        if (answersText == null) {
            return null;
        }
        for (String s : answersText) {
            answers.add(manager.getAnswerManager().getAnswerById(Long.parseLong(s)));
        }
        return answers;
    }

    public void saveAnswer(HttpServletRequest req) {
        List<Answer> answers = getAnswers(req);
        Test test = manager.getTestManager().getTestById(Long.parseLong(String.valueOf(req.getSession().getAttribute("testId"))));
        Question question = getQuestion(req);
        User user = manager.getUserManager().getUserById(Long.parseLong(String.valueOf(req.getSession().getAttribute("id"))));
        if (answers == null) {
            manager.getLogManager().addLog(new Log(0, user, test, question, null));
        } else {
            answers.forEach(answer -> manager.getLogManager().addLog(new Log(0, user, test, question, answer)));
        }
    }

}
