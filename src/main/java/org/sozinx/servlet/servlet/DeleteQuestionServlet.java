package org.sozinx.servlet.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.sozinx.service.EditQuestionService;
import org.sozinx.service.EditQuestionServiceImpl;

import java.io.IOException;

import static org.sozinx.constant.AddressConst.EDIT_QUESTION_PAGE;

@WebServlet("/delete/*")
public class DeleteQuestionServlet extends HttpServlet {

    private EditQuestionService service;
    @Override
    public void init() throws ServletException {
        service = EditQuestionServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        service.deleteQuestionAndAnswers(req);
        resp.sendRedirect("/change/" + service.getTestIdFromUri(req) + "?question=1");
    }

}