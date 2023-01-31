package org.sozinx.servlet.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.sozinx.service.ViewTestService;
import org.sozinx.service.ViewTestServiceImpl;

import java.io.IOException;

import static org.sozinx.constant.AddressConst.VIEW_TEST_PAGE;

@WebServlet("/view/*")
@SuppressWarnings("unused")
public class ViewTestServlet extends HttpServlet {

    private ViewTestService service;

    @Override
    public void init() throws ServletException {
        service = ViewTestServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        service.setAttributeTest(req);
        req.getRequestDispatcher(VIEW_TEST_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        service.setAttributesForTesting(req);
        resp.sendRedirect("/testing/" + req.getSession().getAttribute("testId") +
                "?question=" + req.getSession().getAttribute("questionNumber"));
    }
}