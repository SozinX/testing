package org.sozinx.servlet.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.sozinx.service.DataBaseService;
import org.sozinx.service.DataBaseServiceImpl;

import java.io.IOException;

@WebServlet("/deactivate")
@SuppressWarnings("unused")
public class DeactivateServlet extends HttpServlet {
    private DataBaseService service;

    @Override
    public void init() throws ServletException {
        service = DataBaseServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        service.getUserManager().deleteUser(service.getUserManager().getUserById(Integer.parseInt(req.getSession().getAttribute("id").toString())));
        resp.sendRedirect("/logout");
    }
}
