package org.sozinx.service;

import jakarta.servlet.http.HttpServletRequest;
import org.sozinx.model.User;

import java.time.LocalDate;
import java.util.regex.Pattern;

import static org.sozinx.constant.ErrorConst.*;
import static org.sozinx.constant.RegexConst.*;

public class SignUpServiceImpl implements SignUpService {
    private final DataBaseServiceImpl manager;
    @SuppressWarnings("all")
    private static SignUpServiceImpl service;

    private SignUpServiceImpl() {
        manager = DataBaseServiceImpl.getInstance();
    }

    public static synchronized SignUpServiceImpl getInstance() {
        if (service == null) return new SignUpServiceImpl();
        return service;
    }

    //Check is name long enough
    private static boolean nameIsValid(final HttpServletRequest req) {
        final String name = req.getParameter("name");
        return name != null && name.length() > 2;
    }

    //Compare email with his regex
    private static boolean emailIsValid(final HttpServletRequest req) {
        final String email = req.getParameter("email");
        return Pattern.compile(EMAIL)
                .matcher(email)
                .matches();
    }

    //Compare password with his regex
    private static boolean passwordIsValid(final HttpServletRequest req) {
        final String password = req.getParameter("password");
        return Pattern.compile(PASSWORD)
                .matcher(password)
                .matches();
    }

    //Sum all validation method in one and get error messages
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

    //Check is email already present in database and print error if it is
    private String isEmailPresentInDataBase(final HttpServletRequest req) {
        String email = req.getParameter("email");
        if (manager.getUserManager().getUserByEmail(email) == null) {
            return null;
        } else {
            return EMAIL_IS_PRESENT;
        }
    }

    //Get validation message
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