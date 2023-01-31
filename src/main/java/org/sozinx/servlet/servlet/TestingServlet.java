package org.sozinx.servlet.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.sozinx.service.TestingService;
import org.sozinx.service.TestingServiceImpl;

import java.io.IOException;

import static org.sozinx.constant.AddressConst.TESTING_PAGE;

@WebServlet("/testing/*")
@SuppressWarnings("unused")
public class TestingServlet extends HttpServlet {

    private TestingService service;

    @Override
    public void init() throws ServletException {
        service = TestingServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        service.setQuestionAttributes(req);
        req.getRequestDispatcher(TESTING_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        service.saveAnswer(req);
        int currentPage = Integer.parseInt(String.valueOf(req.getParameter("question"))) + 1;
        if (currentPage > service.getCountOfPages(req)) {
            resp.sendRedirect("/result/" + req.getSession().getAttribute("testId"));
        } else {
            req.getSession().setAttribute("questionNumber", currentPage);
            resp.sendRedirect("/testing/" + req.getSession().getAttribute("testId") + "?question=" + currentPage);
        }
    }
}