package org.sozinx.service;

import jakarta.servlet.http.HttpServletRequest;
import org.sozinx.model.Level;

import java.util.regex.Pattern;

import static org.sozinx.constant.ErrorConst.*;
import static org.sozinx.constant.RegexConst.TIME;

public class EditTestServiceImpl implements EditTestService {
    private final DataBaseService manager;
    @SuppressWarnings("all")
    private static EditTestServiceImpl service;

    private EditTestServiceImpl() {
        manager = DataBaseServiceImpl.getInstance();
    }

    public static synchronized EditTestServiceImpl getInstance() {
        if (service == null) return new EditTestServiceImpl();
        return service;
    }

    //Check is name long enough
    private static boolean nameIsValid(final HttpServletRequest req) {
        final String name = req.getParameter("test-name"); //getting test's name from request
        return name != null && name.length() > 2; //checking is the name long enough
    }

    //Check is subject long enough
    private static boolean subjectIsValid(final HttpServletRequest req) {
        final String subject = req.getParameter("test-subject"); //getting test's subject from request
        return subject != null && subject.length() > 2; //checking is the subject long enough
    }

    //Check comparison between time and his regex
    private static boolean timeIsValid(final HttpServletRequest req) {
        final String time = req.getParameter("test-time");  //getting test's time from request
        if (time == null) {
            return false;
        }
        return Pattern.compile(TIME)
                .matcher(time)
                .matches(); // checking this time in pattern(only natural numbers)
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

    //Get test id(I've used mapping with different id in uri) from uri
    private String getTestIdFromUri(final HttpServletRequest req) {
        String uri = req.getRequestURI();
        return uri.substring(uri.lastIndexOf("/") + 1); // getting substring between the last "/" and the end of uri
    }

    //Check is name already used by this user
    private String isNamePresentInDataBase(final HttpServletRequest req) {
        String name = req.getParameter("test-name");
        if (manager.getTestManager().getTestByNameAndOwner(name, //test name
                Long.parseLong(String.valueOf(req.getSession().getAttribute("id"))), //getting owner from session
                Long.parseLong(getTestIdFromUri(req))) == null) {
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

    public void insertData(final HttpServletRequest req) {
        final String name = req.getParameter("test-name");
        final String subject = req.getParameter("test-subject");
        final String time = String.valueOf(req.getParameter("test-time"));
        final String isClose = String.valueOf(req.getParameter("test-close"));
        final Level level = manager.getLevelManager().getLevelById(Integer.parseInt(String.valueOf(req.getParameter("test-level"))));
        manager.getTestManager().updateTest(manager.getTestManager().getTestById(Long.parseLong(getTestIdFromUri(req))), new String[]{name, subject, isClose, time, String.valueOf(level.getId())});
    }

    //Set current test in request scope
    public void setAttributeTest(HttpServletRequest req) {
        req.setAttribute("currentTest", manager.getTestManager().getTestById(Long.parseLong(getTestIdFromUri(req))));
    }
}