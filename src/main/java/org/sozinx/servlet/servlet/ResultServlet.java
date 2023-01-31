package org.sozinx.servlet.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.sozinx.service.ResultService;
import org.sozinx.service.ResultServiceImpl;

import java.io.IOException;


@WebServlet("/result/*")
@SuppressWarnings("unused")
public class ResultServlet extends HttpServlet {

    private ResultService service;

    @Override
    public void init() throws ServletException {
        service = ResultServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        service.countResult(req);
        String test = String.valueOf(req.getSession().getAttribute("testId"));
        req.getSession().setAttribute("testId", "");
        req.getSession().setAttribute("questionNumber", "");
        resp.sendRedirect("/view/" + test);
    }

}