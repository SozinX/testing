package org.sozinx.servlet.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.sozinx.service.DataBaseService;
import org.sozinx.service.DataBaseServiceImpl;

import java.io.IOException;
import java.util.Objects;

@WebServlet("/lang")
public class ChangeLanguageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        for (int i = 0; i < req.getCookies().length; i++) {
            if (Objects.equals(req.getCookies()[i].getName(), "language")) {
                if (Objects.equals(req.getCookies()[i].getValue(), "ua")) {
                    resp.addCookie(new Cookie("language", "en"));
                } else {
                    resp.addCookie(new Cookie("language", "ua"));
                }
            }
        }
        resp.sendRedirect("/");
    }
}
