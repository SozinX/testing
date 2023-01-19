package org.sozinx.service;

import jakarta.servlet.http.HttpServletRequest;
import org.sozinx.model.User;

import java.time.LocalDate;
import java.util.regex.Pattern;

import static org.sozinx.constant.ErrorConst.*;
import static org.sozinx.constant.RegexConst.*;

public class SignUpServiceImpl implements SignUpService {
    private final DataBaseServiceImpl manager;
    private static SignUpServiceImpl service;

    private SignUpServiceImpl() {
        manager = DataBaseServiceImpl.getInstance();
    }

    public static synchronized SignUpServiceImpl getInstance() {
        if (service == null) return new SignUpServiceImpl();
        return service;
    }

    private static boolean nameIsValid(final HttpServletRequest req) {
        final String name = req.getParameter("name");
        return name != null && name.length() > 2;
    }

    private static boolean emailIsValid(final HttpServletRequest req) {
        final String email = req.getParameter("email");
        return Pattern.compile(EMAIL)
                .matcher(email)
                .matches();
    }

    private static boolean passwordIsValid(final HttpServletRequest req) {
        final String password = req.getParameter("password");
        return Pattern.compile(PASSWORD)
                .matcher(password)
                .matches();
    }

    private String inputIsValid(final HttpServletRequest req) {
        if (!nameIsValid(req)) {
            return NAME_ERROR;
        } else if (!emailIsValid(req)) {
            return EMAIL_ERROR;
        } else if (!passwordIsValid(req)) {
            return PASSWORD_ERROR;
        }
        return null;
    }

    private String isEmailPresentInDataBase(final HttpServletRequest req) {
        String email = req.getParameter("email");
        if (manager.getUserManager().getUserByEmail(email) == null) {
            return null;
        } else {
            return EMAIL_IS_PRESENT;
        }
    }

    public String validationMessage(final HttpServletRequest req) {
        String inputIsValid = inputIsValid(req);
        String isEmailPresentInDataBase = isEmailPresentInDataBase(req);
        if (inputIsValid != null) {
            return inputIsValid;
        } else return isEmailPresentInDataBase;
    }

    public void insertData(final HttpServletRequest req) {
        final String name = req.getParameter("name");
        final String email = req.getParameter("email");
        final int role = Integer.parseInt(req.getParameter("role"));
        final String password = req.getParameter("password");
        manager.getUserManager().addUser(new User(0, name, email, password, LocalDate.now().toString(), manager.getRoleManager().getRoleById(role)));
    }
}