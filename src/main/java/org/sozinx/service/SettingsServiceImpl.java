package org.sozinx.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.sozinx.model.User;

import java.util.Objects;
import java.util.regex.Pattern;

import static org.sozinx.constant.ErrorConst.*;
import static org.sozinx.constant.RegexConst.EMAIL;
import static org.sozinx.constant.RegexConst.PASSWORD;

public class SettingsServiceImpl implements SettingsService {
    private final DataBaseServiceImpl manager;
    private static SettingsServiceImpl service;

    private SettingsServiceImpl() {
        manager = DataBaseServiceImpl.getInstance();
    }

    public static synchronized SettingsServiceImpl getInstance() {
        if (service == null) return new SettingsServiceImpl();
        return service;
    }

    private static boolean nameIsValid(final HttpServletRequest req) {
        final String name = req.getParameter("set-name");
        return name != null && name.length() > 2;
    }

    private static boolean emailIsValid(final HttpServletRequest req) {
        final String email = req.getParameter("set-email");
        return Pattern.compile(EMAIL)
                .matcher(email)
                .matches();
    }

    private static boolean passwordsMatch(final HttpServletRequest req) {
        final String password = req.getParameter("new-password");
        if (password != null) {
            return password.equals(req.getParameter("confirm-password"));
        }
        return true;
    }

    private static boolean passwordIsValid(final HttpServletRequest req) {
        final String password = req.getParameter("new-password");
        if (password != null && !password.equals("")) {
            return Pattern.compile(PASSWORD)
                    .matcher(password)
                    .matches();
        }
        return true;
    }

    private String inputIsValid(final HttpServletRequest req) {
        if (!nameIsValid(req)) {
            return NAME_ERROR;
        } else if (!emailIsValid(req)) {
            return EMAIL_ERROR;
        } else if (!passwordIsValid(req)) {
            return PASSWORD_ERROR;
        } else if (!passwordsMatch(req)) {
            return PASSWORDS_DO_NOT_MATCH;
        }
        return null;
    }

    private String isEmailPresentInDataBase(final HttpServletRequest req) {
        String email = req.getParameter("set-email");
        if (manager.getUserManager().getUserByEmail(email) == null || Objects.equals(req.getSession().getAttribute("email").toString(), email)) {
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

    public void editData(final HttpServletRequest req) {
        final String name = req.getParameter("set-name");
        final String email = req.getParameter("set-email");
        final String role = req.getParameter("set-role");
        String password = req.getParameter("new-password");
        User user = manager.getUserManager().getUserById(Long.parseLong(req.getSession().getAttribute("id").toString()));
        if (password == null || password.equals("")) {
            password = user.getPassword();
        }
        HttpSession session = req.getSession();
        session.setAttribute("name", name);
        session.setAttribute("email", email);
        session.setAttribute("role", manager.getRoleManager().getRoleById(Integer.parseInt(role)).getRole());
        manager.getUserManager().updateUser(user, new String[]{name, email, role, password});
    }
}