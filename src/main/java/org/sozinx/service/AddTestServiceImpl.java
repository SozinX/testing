package org.sozinx.service;

import jakarta.servlet.http.HttpServletRequest;
import org.sozinx.model.Level;
import org.sozinx.model.Test;
import org.sozinx.model.User;

import java.time.LocalDate;
import java.util.regex.Pattern;

import static org.sozinx.constant.ErrorConst.*;
import static org.sozinx.constant.RegexConst.*;

public class AddTestServiceImpl implements AddTestService {
    private final DataBaseServiceImpl manager;
    @SuppressWarnings("all")
    private static AddTestServiceImpl service;

    private AddTestServiceImpl() {
        manager = DataBaseServiceImpl.getInstance();
    }

    public static synchronized AddTestServiceImpl getInstance() {
        if (service == null) return new AddTestServiceImpl();
        return service;
    }

    //Check is name long enough
    private static boolean nameIsValid(final HttpServletRequest req) {
        final String name = req.getParameter("test-name");
        return name != null && name.length() > 2;
    }

    //Check is subject long enough
    private static boolean subjectIsValid(final HttpServletRequest req) {
        final String subject = req.getParameter("test-subject");
        return subject != null && subject.length() > 2;
    }

    //Check comparison between time and his regex
    private static boolean timeIsValid(final HttpServletRequest req) {
        final String time = req.getParameter("test-time");
        return Pattern.compile(TIME)
                .matcher(time)
                .matches();
    }

    //Sum all methods of validation before and return error or null
    private String inputIsValid(final HttpServletRequest req) {
        if (!nameIsValid(req)) {
            return TEST_NAME_ERROR;
        } else if (!subjectIsValid(req)) {
            return TEST_SUBJECT_ERROR;
        } else if (!timeIsValid(req)) {
            return TEST_TIME_ERROR;
        }
        return null;
    }

    //Check is name already used by this user
    private String isNamePresentInDataBase(final HttpServletRequest req) {
        String uri = req.getRequestURI();
        long testId = Long.parseLong(uri.substring(uri.lastIndexOf("/") + 1));
        String name = req.getParameter("test-name");
        if (manager.getTestManager().getTestByNameAndOwner(name, Long.parseLong(String.valueOf(req.getSession().getAttribute("id"))), testId) == null) {
            return null;
        } else {
            return TEST_NAME_IS_PRESENT;
        }
    }

    //Get validation message
    public String validationMessage(final HttpServletRequest req) {
        String inputIsValid = inputIsValid(req);
        String isEmailPresentInDataBase = isNamePresentInDataBase(req);
        if (inputIsValid != null) {
            return inputIsValid;
        } else return isEmailPresentInDataBase;
    }

    //Insert data into database
    public void insertData(final HttpServletRequest req) {
        final String name = req.getParameter("test-name");
        final String subject = req.getParameter("test-subject");
        final int time = Integer.parseInt(String.valueOf(req.getParameter("test-time")));
        final User owner = manager.getUserManager().getUserById(Long.parseLong(String.valueOf(req.getSession().getAttribute("id"))));
        final Level level = manager.getLevelManager().getLevelById(Integer.parseInt(String.valueOf(req.getParameter("test-level"))));

        manager.getTestManager().addTest(new Test(0, name, subject, LocalDate.now().toString(), 2, time, 0, owner, level));
    }

    //Set in request scope last added by this user test
    public void setLastAddedTest(HttpServletRequest req) {
        req.setAttribute("lastAddedTest", manager.getTestManager().getLastAddedTestByOwner(
                manager.getUserManager().getUserById(Long.parseLong(String.valueOf(req.getSession().getAttribute("id"))))));
    }
}