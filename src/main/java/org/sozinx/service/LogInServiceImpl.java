package org.sozinx.service;

import jakarta.servlet.http.HttpServletRequest;
import org.sozinx.model.User;

import java.util.Objects;

import static org.sozinx.constant.ErrorConst.*;

public class LogInServiceImpl implements LogInService {
    private final DataBaseServiceImpl manager;
    @SuppressWarnings("all")
    private static LogInService service;
    private User checkingUser;

    private LogInServiceImpl() {
        manager = DataBaseServiceImpl.getInstance();
    }

    public static synchronized LogInService getInstance() {
        if (service == null) return new LogInServiceImpl();
        return service;
    }

    //Get user by this email and if this email is not present in database return false
    private boolean emailIsCorrect(final HttpServletRequest req) {
        final String email = req.getParameter("email");
        checkingUser = manager.getUserManager().getUserByEmail(email);
        return checkingUser != null;
    }

    //If email is present comparison password from form with actual user password
    private boolean passwordIsCorrect(final HttpServletRequest req) {
        final String password = req.getParameter("password");
        return Objects.equals(password, checkingUser.getPassword());
    }

    //Sum all validation method in one and get error messages
    public String inputIsCorrect(final HttpServletRequest req) {
        if (!emailIsCorrect(req)) {
            return EMAIL_IS_ABSENT;
        } else if (!passwordIsCorrect(req)) {
            return PASSWORD_IS_NOT_CORRECT;
        }
        return null;
    }

    //Returns user that we already check for work with his information in session
    public User getCheckingUser() {
        return checkingUser;
    }
}
