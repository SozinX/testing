package org.sozinx.servlet.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.sozinx.service.DeleteQuestionService;
import org.sozinx.service.DeleteQuestionServiceImpl;

import java.io.IOException;


@WebServlet("/delete/*")
@SuppressWarnings("unused")
public class DeleteQuestionServlet extends HttpServlet {

    private DeleteQuestionService service;

    @Override
    public void init() throws ServletException {
        service = DeleteQuestionServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        service.deleteQuestionAndAnswers(req);
        resp.sendRedirect("/change/" + service.getTestIdFromUri(req) + "?question=1");
    }

}