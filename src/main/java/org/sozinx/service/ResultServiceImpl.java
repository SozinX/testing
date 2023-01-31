package org.sozinx.service;

import jakarta.servlet.http.HttpServletRequest;
import org.sozinx.model.*;

import java.time.LocalDate;


public class ResultServiceImpl implements ResultService {
    private final DataBaseService manager;
    @SuppressWarnings("all")
    private static ResultServiceImpl service;

    public ResultServiceImpl() {
        manager = DataBaseServiceImpl.getInstance();
    }

    public static synchronized ResultServiceImpl getInstance() {
        if (service == null) return new ResultServiceImpl();
        return service;
    }

    @Override
    public String getTestIdFromUri(final HttpServletRequest req) {
        String uri = req.getRequestURI();
        return uri.substring(uri.lastIndexOf("/") + 1);
    }

    @Override
    public void countResult(HttpServletRequest req) {
        Test test = manager.getTestManager().getTestById(Long.parseLong(String.valueOf(req.getSession().getAttribute("testId"))));
        User user = manager.getUserManager().getUserById(Long.parseLong(String.valueOf(req.getSession().getAttribute("id"))));
        manager.getTestManager().addPopularity(test);
        int sumOfCorrectAnswers = manager.getLogManager().getSumOfPoints(test, user);
        int countOfAnswers = manager.getAnswerManager().getCountOfAnswers(test);
        int countOfZeros = manager.getLogManager().getCountOfZeros(test, user);
        manager.getResultManager().addResult(new Result(0, LocalDate.now().toString(), 100 * sumOfCorrectAnswers / (countOfAnswers + countOfZeros), user, test));
    }

}
