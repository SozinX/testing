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
        return manager.getQuestionManager().getQuestionByTest( // 3) getting questions in this test
                manager.getTestManager().getTestById( //2) getting test by this id
                        Long.parseLong(String.valueOf(req.getSession().getAttribute("testId")))));//1) getting test id from session
    }

    //Return count of pages which equals List of questions size
    @Override
    public double getCountOfPages(HttpServletRequest req) {
        return getAllQuestions(req).size();
    }

    @Override
    public String getTestIdFromUri(final HttpServletRequest req) {
        String uri = req.getRequestURI();
        return uri.substring(uri.lastIndexOf("/") + 1); //getting substring between the last "/" and the end of uri
    }

    //Return current question
    private Question getQuestion(HttpServletRequest req) {
        String page = req.getParameter("question"); //returning uri variable
        if (Objects.equals(page, "") || page == null || page.equals("0")) { //checking is everything is ok
            page = "1"; //if it is not going to the start of pagination
        }
        return getAllQuestions(req).get(checkOutOfBounds(req, page));
    }

    //Check if user trying to get question which is not present in List, and it is making an error
    private int checkOutOfBounds(HttpServletRequest req, String page) {
        List<Question> questions = getAllQuestions(req);
        if (Integer.parseInt(page) >= questions.size()) { //checking if current page is more or equal than questions list
            return questions.size() - 1; //if it is - return last page
        } else if (Integer.parseInt(page) < 0) { //if page is in negative region
            return 0;
        }
        return Integer.parseInt(page) - 1; //if everything is OK - then return page
    }

    //Return list of answers
    private List<Answer> getAnswers(Question question) {
        return manager.getAnswerManager().getAnswerByQuestion(question);
    }

    //Set request scope attributes for question updating
    @Override
    public void setQuestionAttributes(HttpServletRequest req) {
        Question currentQuestion = getQuestion(req);
        req.setAttribute("currentTest", currentQuestion.getTest()); //current question's test
        req.setAttribute("currentQuestion", currentQuestion); //current question
        req.setAttribute("currentAnswers", getAnswers(currentQuestion)); //answers for current test
        req.setAttribute("pages", getCountOfPages(req)); //number of pages
        String currentPage = req.getParameter("question");
        if (Objects.equals(currentPage, "") || currentPage == null || Integer.parseInt(currentPage) <= 0) { //checking is everything is ok
            currentPage = "1"; //if it is not return on the start of pagination
        }
        req.setAttribute("currentPage", currentPage); //current page
    }

    private List<Answer> getAnswers(HttpServletRequest req) {
        String[] answersText = req.getParameterValues("answerCheck"); //returning all answers ids
        List<Answer> answers = new ArrayList<>();
        if (answersText == null) { //if there are no answers (something went wrong)
            return null;
        }
        for (String s : answersText) { //in answers ids
            answers.add(manager.getAnswerManager().getAnswerById(Long.parseLong(s))); //adding new answer in list
        }
        return answers;
    }

    //Saving user's answer
    @Override
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
