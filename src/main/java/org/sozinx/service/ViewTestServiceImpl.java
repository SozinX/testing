package org.sozinx.service;

import jakarta.servlet.http.HttpServletRequest;
import org.sozinx.model.Result;
import org.sozinx.model.Test;
import org.sozinx.model.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class ViewTestServiceImpl implements ViewTestService {
    private final DataBaseService manager;
    @SuppressWarnings("all")
    private static ViewTestServiceImpl service;

    private ViewTestServiceImpl() {
        manager = DataBaseServiceImpl.getInstance();
    }

    public static synchronized ViewTestServiceImpl getInstance() {
        if (service == null) return new ViewTestServiceImpl();
        return service;
    }

    //Get test id(I've used mapping with different id in uri) from uri
    public String getTestIdFromUri(final HttpServletRequest req) {
        String uri = req.getRequestURI();
        return uri.substring(uri.lastIndexOf("/") + 1);
    }

    public int getTestTime(long testId) {
        return manager.getTestManager().getTestById(testId).getTime();
    }

    //Set current test and result in request scope
    public void setAttributeTest(HttpServletRequest req) {
        Test currentTest = manager.getTestManager().getTestById(Long.parseLong(getTestIdFromUri(req)));
        StringBuilder result = new StringBuilder();
        if (req.getSession().getAttribute("id") == null) {
            result.append("-");
        } else {
            User currentUser = manager.getUserManager().getUserById(Long.parseLong(String.valueOf(req.getSession().getAttribute("id"))));
            Result resultRecord = manager.getResultManager().getResultByUserAndTest(currentUser, currentTest);
            if (resultRecord != null) {
                result.append(resultRecord.getResult());
            }
            if (result.isEmpty()) {
                result.append("-");
            }
        }
        req.setAttribute("currentTest", currentTest);
        req.setAttribute("result", result);
    }

    @Override
    public void setAttributesForTesting(HttpServletRequest req) {
        String testId = getTestIdFromUri(req);
        req.getSession().setAttribute("testId", testId);
        req.getSession().setAttribute("questionNumber", "1");
        req.getSession().setAttribute("time", getTestTime(Long.parseLong(testId)) * 60);
        req.getSession().setAttribute("hour", LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH")));
        req.getSession().setAttribute("minutes", LocalDateTime.now().format(DateTimeFormatter.ofPattern("mm")));
        req.getSession().setAttribute("seconds", LocalDateTime.now().format(DateTimeFormatter.ofPattern("ss")));
    }
}