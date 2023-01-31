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
        return uri.substring(uri.lastIndexOf("/") + 1);
    }

    private List<Question> getAllQuestions(HttpServletRequest req) {
        return manager.getQuestionManager().getQuestionByTest(manager.getTestManager().getTestById(Long.parseLong(getTestIdFromUri(req))));
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


    //Remove question with his answers from database
    @Override
    public void deleteQuestionAndAnswers(HttpServletRequest req) {
        Question question = getQuestion(req);
        manager.getLogManager().deleteLogByQuestion(question);
        manager.getAnswerManager().deleteAnswerByQuestion(question);
        manager.getQuestionManager().deleteQuestion(question);
    }
}
