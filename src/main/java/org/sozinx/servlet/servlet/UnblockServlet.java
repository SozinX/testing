package org.sozinx.servlet.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.sozinx.service.UnblockUserService;
import org.sozinx.service.UnblockUserServiceImpl;

import java.io.IOException;

import static org.sozinx.constant.AddressConst.UNBLOCKING_SYSTEM;

@WebServlet("/unblock")
@SuppressWarnings("unused")
public class UnblockServlet extends HttpServlet {

    private UnblockUserService service;

    @Override
    public void init() throws ServletException {
        service = UnblockUserServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(UNBLOCKING_SYSTEM).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String inputValid = service.validationMessage(req);
        if (inputValid != null) {
            req.setAttribute("message", inputValid);
            doGet(req, resp);
        } else {
            service.insertData(req);
            resp.sendRedirect("/unblock");
        }
    }

}