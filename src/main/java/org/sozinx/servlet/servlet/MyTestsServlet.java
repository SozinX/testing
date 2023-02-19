package org.sozinx.servlet.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.sozinx.service.TestsByOwnerService;
import org.sozinx.service.TestsByOwnerServiceImpl;

import java.io.IOException;

import static org.sozinx.constant.AddressConst.USER_TESTS_PAGE;

@WebServlet("/mytests")
@SuppressWarnings("unused")
public class MyTestsServlet extends HttpServlet {
    private TestsByOwnerService service;

    @Override
    public void init() {
        service = TestsByOwnerServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        service.setUserAttributes(req);
        req.getRequestDispatcher(USER_TESTS_PAGE).forward(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
