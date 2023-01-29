package org.sozinx.servlet.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.sozinx.service.BlockUserService;
import org.sozinx.service.BlockUserServiceImpl;

import java.io.IOException;

import static org.sozinx.constant.AddressConst.BLOCKING_SYSTEM;

@WebServlet("/block")
@SuppressWarnings("unused")
public class BlockServlet extends HttpServlet {

    private BlockUserService service;
    @Override
    public void init() throws ServletException {
        service = BlockUserServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(BLOCKING_SYSTEM).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String inputValid = service.inputIsCorrect(req);
        if(inputValid != null){
            req.setAttribute("message", inputValid);
            doGet(req, resp);
        }
        else {
            service.blockUser(req);
            resp.sendRedirect("/block");
        }
    }

}