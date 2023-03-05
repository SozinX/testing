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
        return uri.substring(uri.lastIndexOf("/") + 1); //getting substring between the last "/" and the end of uri
    }

    public List<Question> getAllQuestions(HttpServletRequest req) {
        return manager.getQuestionManager().getQuestionByTest( //3) then getting questions in this test
                manager.getTestManager().getTestById( //2) then getting test by this id
                        Long.parseLong(getTestIdFromUri(req)))); //1) getting id from uri
    }

    //Return count of pages which equals List of questions size
    public double getCountOfPages(HttpServletRequest req) {
        return getAllQuestions(req).size();
    }

    //Return current question
    public Question getQuestion(HttpServletRequest req) {
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
    public List<Answer> getAnswers(Question question) {
        return manager.getAnswerManager().getAnswerByQuestion(question);
    }

    //Set request scope attributes for question updating
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

    //Check is question long present and long enough
    private static boolean questionIsValid(final HttpServletRequest req) {
        final String question = req.getParameter("questionText"); //getting question from request
        return question != null && question.length() > 2; //checking is the question long enough
    }

    //Check condition that in question with multiple answers must be two and more correct answers
    private static int multiAnswersForUpdateValid(final HttpServletRequest req) {
        int countOfCorrectAnswers = 0;
        for (int i = 1; i <= 10; i++) { //starting the loop for 10 input value (because 10 answers is maximum)
            String currentCheck = req.getParameter("qtu" + i); //getting value in inputs with answer's correctness
            if (currentCheck != null) { //if inputs present
                countOfCorrectAnswers = countOfCorrectAnswers + Integer.parseInt(currentCheck); //then add value of input to sum
                //if correct input value is 1, if not correct - input value is 0
            }
        }
        return countOfCorrectAnswers;
    }

    //Check condition that in question with single answer must be only one correct answer
    private static int singleAnswerForUpdateValid(final HttpServletRequest req) {
        int countOfCorrectAnswers = 0;
        for (int i = 1; i <= 10; i++) { //starting the loop for 10 input value (because 10 answers is maximum)
            String currentCheck = req.getParameter("qtu" + i); //getting value in inputs with answer's correctness
            if (currentCheck != null) { //if inputs present
                countOfCorrectAnswers = countOfCorrectAnswers + Integer.parseInt(currentCheck); //then add value of input to sum
                //if correct input value is 1, if not correct - input value is 0
            }
        }
        return countOfCorrectAnswers;
    }

    //Check condition that in question with multiple answers must be two and more correct answers
    private static boolean multiAnswersForInsertValid(final HttpServletRequest req) {
        int countOfCorrectAnswers = 0;
        for (int i = 1; i <= Integer.parseInt(req.getParameter("iterations")); i++) { //starting the loop for "iterations" input value
            String currentCheck = req.getParameter("qt" + i); //getting value in inputs with answer's correctness
            if (currentCheck != null) { //if inputs present
                countOfCorrectAnswers = countOfCorrectAnswers + Integer.parseInt(currentCheck); //then add value of input to sum
                //if correct input value is 1, if not correct - input value is 0
            }
        }
        return countOfCorrectAnswers + multiAnswersForUpdateValid(req) >= 2;
    }

    //Check condition that in question with single answer must be only one correct answer
    private static boolean singleAnswerForInsertValid(final HttpServletRequest req) {
        int countOfCorrectAnswers = 0;
        for (int i = 1; i <= Integer.parseInt(req.getParameter("iterations")); i++) { //starting the loop for "iterations" input value
            String currentCheck = req.getParameter("qt" + i); //getting value in inputs with answer's correctness
            if (currentCheck != null) {
                countOfCorrectAnswers = countOfCorrectAnswers + Integer.parseInt(currentCheck); //then add value of input to sum
                //if correct input value is 1, if not correct - input value is 0
            }
        }
        return countOfCorrectAnswers + singleAnswerForUpdateValid(req) == 1;
    }

    //Check is answer present and long enough
    private static boolean answerForUpdateIsValid(final HttpServletRequest req) {
        for (int i = 1; i <= 10; i++) { //starting the loop for "iterations" input value
            String currentCheck = req.getParameter("au" + i); //getting value in inputs with answer
            if (currentCheck != null) {
                if (currentCheck.length() < 1) { //checking length
                    return false;
                }
            }
        }
        return true;
    }

    //Check is answer present and long enough
    private static boolean answerForInsertIsValid(final HttpServletRequest req) {
        for (int i = 1; i <= Integer.parseInt(req.getParameter("iterations")); i++) { //starting the loop for "iterations" input value
            String currentCheck = req.getParameter("a" + i); //getting value in inputs with answer
            if (currentCheck != null) {
                if (currentCheck.length() < 1) { //checking length
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
        } else if (!multiAnswersForInsertValid(req) && Objects.equals(req.getParameter("questionType"), "2")) { //for questions with many correct answers
            return MULTIPLY_ERROR;
        } else if (!singleAnswerForInsertValid(req) && Objects.equals(req.getParameter("questionType"), "1")) { //for questions with one correct answer
            return SINGLE_ERROR;
        } else if (!answerForInsertIsValid(req) || !answerForUpdateIsValid(req)) { //checking is answers for update and input are correct
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
        if (inputIsValid != null) { //if there is an error
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

    //Inserting answers in table
    private void insertAnswers(final HttpServletRequest req, Question question) {
        for (int i = 1; i <= Integer.parseInt(req.getParameter("iterations")); i++) { //starting the loop for "iterations" input value
            String currentCheckAnswer = req.getParameter("a" + i);
            String currentCheckCorrect = req.getParameter("qt" + i);
            if (currentCheckAnswer != null && currentCheckCorrect != null) { //if inputs with these names is present
                manager.getAnswerManager().addAnswer(new Answer(0, currentCheckAnswer, Integer.parseInt(currentCheckCorrect), question));
            }
        }
    }

    //Deleting answers from table
    private void deleteAnswers(final HttpServletRequest req) {
        for (int i = 1; i <= 10; i++) { //starting the loop for 10 input value (because 10 answers is maximum)
            String currentCheckId = req.getParameter("delete" + i);
            if (currentCheckId != null) { //if input with this name is present
                Answer answer = manager.getAnswerManager().getAnswerById(Long.parseLong(currentCheckId));
                manager.getLogManager().deleteLogByAnswer(answer); //deleting answer in log
                manager.getAnswerManager().deleteAnswer(answer); //deleting answer in table
            }
        }
    }

    //Updating answers in table
    private void updateAnswers(final HttpServletRequest req) {
        for (int i = 1; i <= 10; i++) { //starting the loop for 10 input value (because 10 answers is maximum)
            String currentCheckAnswer = req.getParameter("au" + i);
            String currentCheckCorrect = req.getParameter("qtu" + i);
            if (currentCheckAnswer != null && currentCheckCorrect != null) { //if inputs with these names is present
                manager.getAnswerManager().updateAnswer(manager.getAnswerManager().getAnswerById(
                                Long.parseLong(req.getParameter("up" + i + "i"))), //inputs naming: up - update, first i - unique number of input, second i - id
                        new String[]{currentCheckAnswer, currentCheckCorrect});
            }
        }
    }

    //Do all Update/Delete/Insert methods. Finalising results
    public void insertData(final HttpServletRequest req) {
        insertAnswers(req, updateAndReturnQuestion(req));
        updateAnswers(req);
        deleteAnswers(req);
    }

}
