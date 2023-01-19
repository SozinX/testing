package org.sozinx.service;

import jakarta.servlet.http.HttpServletRequest;
import org.sozinx.model.User;

import java.util.Objects;

import static org.sozinx.constant.ErrorConst.*;

public class LogInServiceImpl implements LogInService {
    private final DataBaseServiceImpl manager;
    private static LogInService service;
    private User checkingUser;

    private LogInServiceImpl() {
        manager = DataBaseServiceImpl.getInstance();
    }

    public static synchronized LogInService getInstance() {
        if (service == null) return new LogInServiceImpl();
        return service;
    }

    private boolean emailIsCorrect(final HttpServletRequest req) {
        final String email = req.getParameter("email");
        checkingUser = manager.getUserManager().getUserByEmail(email);
        return checkingUser != null;
    }

    private boolean passwordIsCorrect(final HttpServletRequest req) {
        final String password = req.getParameter("password");
        return Objects.equals(password, checkingUser.getPassword());
    }

    public String inputIsCorrect(final HttpServletRequest req) {
        if (!emailIsCorrect(req)) {
            return EMAIL_IS_ABSENT;
        } else if (!passwordIsCorrect(req)) {
            return PASSWORD_IS_NOT_CORRECT;
        }
        return null;
    }

    public User getCheckingUser() {
        return checkingUser;
    }
}
