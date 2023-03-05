package org.sozinx.service;

import jakarta.servlet.http.HttpServletRequest;
import org.sozinx.model.Question;

import java.util.List;
import java.util.Objects;

public class DeleteQuestionServiceImpl implements DeleteQuestionService {
    private final DataBaseService manager;
    @SuppressWarnings("all")
    private static DeleteQuestionServiceImpl service;

    public DeleteQuestionServiceImpl() {
        manager = DataBaseServiceImpl.getInstance();
    }

    public static synchronized DeleteQuestionServiceImpl getInstance() {
        if (service == null) return new DeleteQuestionServiceImpl();
        return service;
    }

    //Get test id(I've used mapping with different id in uri) from uri
    public String getTestIdFromUri(final HttpServletRequest req) {
        String uri = req.getRequestURI();
        return uri.substring(uri.lastIndexOf("/") + 1); // getting substring between the last "/" and the end of uri
    }

    private List<Question> getAllQuestions(HttpServletRequest req) {
        return manager.getQuestionManager().getQuestionByTest( //3) then getting questions in this test
                manager.getTestManager().getTestById( //2) then getting test by this id
                        Long.parseLong(getTestIdFromUri(req)))); //1) getting id from uri
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


    //Remove question with his answers from database
    @Override
    public void deleteQuestionAndAnswers(HttpServletRequest req) {
        Question question = getQuestion(req);
        manager.getLogManager().deleteLogByQuestion(question); //deleting log because relationships will deny deleting
        manager.getAnswerManager().deleteAnswerByQuestion(question);
        manager.getQuestionManager().deleteQuestion(question);
    }
}
