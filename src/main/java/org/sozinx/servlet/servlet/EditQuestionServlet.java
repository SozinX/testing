package org.sozinx.servlet.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.sozinx.service.EditQuestionService;
import org.sozinx.service.EditQuestionServiceImpl;
import org.sozinx.service.EditTestService;
import org.sozinx.service.EditTestServiceImpl;

import java.io.IOException;

import static org.sozinx.constant.AddressConst.EDIT_QUESTION_PAGE;
import static org.sozinx.constant.AddressConst.EDIT_TEST_PAGE;

@WebServlet("/change/*")
public class EditQuestionServlet extends HttpServlet {

    private EditQuestionService service;
    @Override
    public void init() throws ServletException {
        service = EditQuestionServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        service.setQuestionAttributes(req);
        req.getRequestDispatcher(EDIT_QUESTION_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF8");
        String validationMessage = service.validationMessage(req);
        if(validationMessage != null){
            req.setAttribute("message", validationMessage);
            doGet(req, resp);
        }
        else {
            service.insertAndUpdateData(req);
            resp.sendRedirect(req.getRequestURI() + "?question=" + req.getParameter("question"));
        }
    }
}