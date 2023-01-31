package org.sozinx.servlet.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.sozinx.service.LogInService;
import org.sozinx.service.LogInServiceImpl;

import java.io.IOException;

import static org.sozinx.constant.AddressConst.LOG_IN_PAGE;

@WebServlet("/login")
@SuppressWarnings("unused")
public class LogInServlet extends HttpServlet {
    private LogInService service;

    @Override
    public void init() throws ServletException {
        service = LogInServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(LOG_IN_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF8");
        String validationMessage = service.inputIsCorrect(req);
        if (validationMessage != null) {
            req.setAttribute("message", validationMessage);
            doGet(req, resp);
        } else {
            service.setAttributes(req);
            resp.sendRedirect("/");
        }
    }

    @Override
    public void destroy() {

    }
}
