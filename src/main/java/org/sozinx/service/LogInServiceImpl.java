package org.sozinx.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.sozinx.model.Block;
import org.sozinx.model.User;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.sozinx.constant.ErrorConst.*;

public class LogInServiceImpl implements LogInService {
    private final DataBaseService manager;
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

    private boolean userIsBlocked(final HttpServletRequest req) {
        List<Block> blockHistory = manager.getBlockManager().getBlockByUser( //2) getting block by user
                manager.getUserManager().getUserByEmail(req.getParameter("email"))); //1) getting user by email
        AtomicBoolean isBlocked = new AtomicBoolean(false);
        blockHistory.forEach(block -> {
            if (Objects.equals(block.getUnblock(), "") || block.getUnblock() == null) {
                isBlocked.set(true);
            }
        }); //checking every record if it is no unblock date(it means that user is in block right now)
        return isBlocked.get();
    }

    //Sum all validation method in one and get error messages
    public String inputIsCorrect(final HttpServletRequest req) {
        if (!emailIsCorrect(req)) {
            return EMAIL_IS_ABSENT;
        } else if (!passwordIsCorrect(req)) {
            return PASSWORD_IS_NOT_CORRECT;
        } else if (userIsBlocked(req)) {
            return USER_IS_BLOCKED;
        }
        return null;
    }

    //Returns user that we already check for work with his information in session
    public User getCheckingUser() {
        return checkingUser;
    }

    //Creating session with all needed values about user
    @Override
    public void setAttributes(HttpServletRequest req) {
        HttpSession session = req.getSession();
        session.setAttribute("id", getCheckingUser().getId());
        session.setAttribute("name", getCheckingUser().getName());
        session.setAttribute("email", getCheckingUser().getEmail());
        session.setAttribute("role", getCheckingUser().getRole().getRole());
        session.setAttribute("testId", "");
        session.setAttribute("questionNumber", "");
    }
}
