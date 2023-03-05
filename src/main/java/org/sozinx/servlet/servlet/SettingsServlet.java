package org.sozinx.servlet.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.sozinx.service.SettingsService;
import org.sozinx.service.SettingsServiceImpl;

import java.io.IOException;

import static org.sozinx.constant.AddressConst.SETTINGS_PAGE;

@WebServlet("/settings")
@SuppressWarnings("unused")
public class SettingsServlet extends HttpServlet {

    private SettingsService service;

    @Override
    public void init() throws ServletException {
        service = SettingsServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(SETTINGS_PAGE).forward(req, resp);
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
            resp.sendRedirect("/settings");
        }
    }

}