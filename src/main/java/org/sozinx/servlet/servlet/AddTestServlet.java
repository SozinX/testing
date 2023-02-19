package org.sozinx.servlet.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.sozinx.service.AddTestService;
import org.sozinx.service.AddTestServiceImpl;

import java.io.IOException;

import static org.sozinx.constant.AddressConst.ADD_TEST_PAGE;

@WebServlet("/create")
@SuppressWarnings("unused")
public class AddTestServlet extends HttpServlet {

    private AddTestService service;

    @Override
    public void init() throws ServletException {
        service = AddTestServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        service.setLastAddedTest(req);
        req.getRequestDispatcher(ADD_TEST_PAGE).forward(req, resp);
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
            resp.sendRedirect("/create");
        }
    }

}