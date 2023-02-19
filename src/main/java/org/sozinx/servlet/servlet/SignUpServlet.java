package org.sozinx.servlet.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.sozinx.service.SignUpService;
import org.sozinx.service.SignUpServiceImpl;


import static org.sozinx.constant.AddressConst.*;

import java.io.IOException;

@WebServlet("/signup")
@SuppressWarnings("unused")
public class SignUpServlet extends HttpServlet {

    private SignUpService service;

    @Override
    public void init() throws ServletException {
        service = SignUpServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(SIGN_UP_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF8");
        String validationMessage = service.validationMessage(req);
        if (validationMessage != null) {
            req.setAttribute("message", validationMessage);
            doGet(req, resp);
        } else {
            service.insertData(req);
            resp.sendRedirect("/login");
        }
    }

}