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
    private final DataBaseService manager;
    @SuppressWarnings("all")
    private static SettingsServiceImpl service;

    private SettingsServiceImpl() {
        manager = DataBaseServiceImpl.getInstance();
    }

    public static synchronized SettingsServiceImpl getInstance() {
        if (service == null) return new SettingsServiceImpl();
        return service;
    }

    //Check is name long enough
    private static boolean nameIsValid(final HttpServletRequest req) {
        final String name = req.getParameter("set-name");
        return name != null && name.length() > 2;
    }

    //Compare email with his regex
    private static boolean emailIsValid(final HttpServletRequest req) {
        final String email = req.getParameter("set-email");
        if (email == null) {
            return false;
        }
        return Pattern.compile(EMAIL)
                .matcher(email)
                .matches();
    }

    //Compare two passwords. They must be equal
    private static boolean passwordsMatch(final HttpServletRequest req) {
        final String password = req.getParameter("new-password");
        if (password != null) {
            return password.equals(req.getParameter("confirm-password"));
        }
        return true;
    }

    //Compare password with his regex
    private static boolean passwordIsValid(final HttpServletRequest req) {
        final String password = req.getParameter("new-password");
        if (password == null) {
            return false;
        }
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
        } else if (!passwordsMatch(req)) {
            return PASSWORDS_DO_NOT_MATCH;
        }
        return null;
    }

    //Check is email already present in database and print error if it is
    private String isEmailPresentInDataBase(final HttpServletRequest req) {
        String email = req.getParameter("set-email");
        if (manager.getUserManager().getUserByEmail(email) == null || Objects.equals(req.getSession().getAttribute("email").toString(), email)) {
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

    //Get values from page and make an array of Strings
    private String[] getParameters(final HttpServletRequest req) {
        final String name = req.getParameter("set-name");
        final String email = req.getParameter("set-email");
        final String role = req.getParameter("set-role");
        String password = req.getParameter("new-password");
        return new String[]{name, email, role, password};
    }

    public void editData(final HttpServletRequest req) {
        String[] parameters = getParameters(req);
        User user = manager.getUserManager().getUserById(Long.parseLong(req.getSession().getAttribute("id").toString()));
        if (parameters[3] == null || parameters[3].equals("")) {
            parameters[3] = user.getPassword();
        }
        manager.getUserManager().updateUser(user, parameters);
        setUserAttributes(req, parameters);
    }

    //Set into session scope new values after updating
    private void setUserAttributes(final HttpServletRequest req, String[] parameters) {
        HttpSession session = req.getSession();
        session.setAttribute("name", parameters[0]);
        session.setAttribute("email", parameters[1]);
        session.setAttribute("role", manager.getRoleManager().getRoleById(Integer.parseInt(parameters[2])).getRole());
    }
}