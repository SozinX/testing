package org.sozinx.servlet.filter;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.sozinx.service.*;

import java.io.IOException;
import java.util.Objects;


@WebFilter("/testing/*")
@SuppressWarnings("unused")
public class TestingFilter implements Filter {

    private TestingService service;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        service = TestingServiceImpl.getInstance();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        final HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String testId = service.getTestIdFromUri(req);
        if (!Objects.equals(testId, String.valueOf(req.getSession().getAttribute("testId")))) {
            resp.sendRedirect("/");
            return;
        }
        int currentNumber = Integer.parseInt(String.valueOf(req.getSession().getAttribute("questionNumber")));
        if (Integer.parseInt(req.getParameter("question")) != currentNumber) {
            resp.sendRedirect("/testing/" + testId + "?question=" + currentNumber);
        }
        filterChain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}