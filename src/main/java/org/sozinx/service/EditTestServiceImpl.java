package org.sozinx.service;

import jakarta.servlet.http.HttpServletRequest;
import org.sozinx.model.Level;
import org.sozinx.model.Test;
import org.sozinx.model.User;

import java.time.LocalDate;
import java.util.regex.Pattern;

import static org.sozinx.constant.ErrorConst.*;
import static org.sozinx.constant.RegexConst.TIME;

public class EditTestServiceImpl implements EditTestService {
    private final DataBaseServiceImpl manager;
    private static EditTestServiceImpl service;

    private EditTestServiceImpl() {
        manager = DataBaseServiceImpl.getInstance();
    }

    public static synchronized EditTestServiceImpl getInstance() {
        if (service == null) return new EditTestServiceImpl();
        return service;
    }

    private static boolean nameIsValid(final HttpServletRequest req) {
        final String name = req.getParameter("test-name");
        return name != null && name.length() > 2;
    }

    private static boolean subjectIsValid(final HttpServletRequest req) {
        final String subject = req.getParameter("test-subject");
        return subject != null && subject.length() > 2;
    }
    private static boolean timeIsValid(final HttpServletRequest req) {
        final String time = req.getParameter("test-time");
        return Pattern.compile(TIME)
                .matcher(time)
                .matches();
    }
    private String inputIsValid(final HttpServletRequest req) {
        if (!nameIsValid(req)) {
            return TEST_NAME_ERROR;
        } else if (!subjectIsValid(req)) {
            return TEST_SUBJECT_ERROR;
        }else if (!timeIsValid(req)) {
            return TEST_TIME_ERROR;
        }
        return null;
    }

    private String isNamePresentInDataBase(final HttpServletRequest req) {
        String name = req.getParameter("test-name");
        if (manager.getTestManager().getTestByNameAndOwner(name, Long.parseLong(String.valueOf(req.getSession().getAttribute("id")))) == null) {
            return null;
        } else {
            return TEST_NAME_IS_PRESENT;
        }
    }

    public String validationMessage(final HttpServletRequest req) {
        String inputIsValid = inputIsValid(req);
        String isEmailPresentInDataBase = isNamePresentInDataBase(req);
        if (inputIsValid != null) {
            return inputIsValid;
        } else return isEmailPresentInDataBase;
    }

    public void editData(final HttpServletRequest req) {
        String uri = req.getRequestURI();
        String testId = uri.substring(uri.lastIndexOf("/") + 1);
        final String name = req.getParameter("test-name");
        final String subject = req.getParameter("test-subject");
        final String time = String.valueOf(req.getParameter("test-time"));
        final String isClose = String.valueOf(req.getParameter("test-close"));
        final User owner = manager.getUserManager().getUserById(Long.parseLong(String.valueOf(req.getSession().getAttribute("id"))));
        final Level level = manager.getLevelManager().getLevelById(Integer.parseInt(String.valueOf(req.getParameter("test-level"))));
        manager.getTestManager().updateTest(manager.getTestManager().getTestById(Long.parseLong(testId)), new String[] {name, subject, isClose, time, String.valueOf(level.getId())});
    }
    public void setAttributeTest(HttpServletRequest req){
        String uri = req.getRequestURI();
        String testId = uri.substring(uri.lastIndexOf("/") + 1);
        req.setAttribute("currentTest", manager.getTestManager().getTestById(Long.parseLong(testId)));
    }
}