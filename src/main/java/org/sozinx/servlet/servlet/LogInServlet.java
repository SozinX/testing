package org.sozinx.servlet.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.sozinx.service.LogInService;
import org.sozinx.service.LogInServiceImpl;

import java.io.IOException;

import static org.sozinx.constant.AddressConst.LOG_IN_PAGE;

@WebServlet("/login")
public class LogInServlet extends HttpServlet {
    private final LogInService service;

    public LogInServlet() {
        service = LogInServiceImpl.getInstance();
    }

    @Override
    public void init() throws ServletException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(LOG_IN_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF8");
        String validationMessage = service.inputIsCorrect(req);
        if(validationMessage != null){
            req.setAttribute("message", validationMessage);
            doGet(req, resp);
        }
        else {
            HttpSession session = req.getSession();
            session.setAttribute("id", service.getCheckingUser().getId());
            session.setAttribute("name", service.getCheckingUser().getName());
            session.setAttribute("email", service.getCheckingUser().getEmail());
            session.setAttribute("role", service.getCheckingUser().getRole().getRole());
            resp.sendRedirect("/");
        }
    }

    @Override
    public void destroy() {

    }
}
