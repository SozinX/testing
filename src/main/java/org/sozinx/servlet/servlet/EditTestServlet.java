package org.sozinx.servlet.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.sozinx.service.EditTestService;
import org.sozinx.service.EditTestServiceImpl;

import java.io.IOException;

import static org.sozinx.constant.AddressConst.EDIT_TEST_PAGE;

@WebServlet("/edit/*")
@SuppressWarnings("unused")
public class EditTestServlet extends HttpServlet {

    private EditTestService service;

    @Override
    public void init() throws ServletException {
        service = EditTestServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        service.setAttributeTest(req);
        req.getRequestDispatcher(EDIT_TEST_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF8");
        String validationMessage = service.validationMessage(req);
        if (validationMessage != null) {
            req.setAttribute("message", validationMessage);
            doGet(req, resp);
        } else {
            service.editData(req);
            resp.sendRedirect(req.getRequestURI());
        }
    }
}