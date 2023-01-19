package org.sozinx.servlet.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.sozinx.service.TestsService;
import org.sozinx.service.TestsServiceImpl;

import java.io.IOException;

import static org.sozinx.constant.AddressConst.HOME_PAGE;
@WebServlet("/tests")
public class TestsServlet extends HttpServlet {
    private TestsService service;
    @Override
    public void init(){
        service = TestsServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        service.setUserAttributes(req);
        req.getRequestDispatcher(HOME_PAGE).forward(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
