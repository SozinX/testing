package org.sozinx.servlet.filter;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.sozinx.model.Test;
import org.sozinx.service.DataBaseService;
import org.sozinx.service.DataBaseServiceImpl;

import java.io.IOException;
import java.util.Objects;


@WebFilter("/add/*")
public class AddQuestionFilter implements Filter {
    private DataBaseService service;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        service = DataBaseServiceImpl.getInstance();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        final HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String uri = req.getRequestURI();
        String testId = uri.substring(uri.lastIndexOf("/") + 1);
        Test test = service.getTestManager().getTestById(Long.parseLong(testId));
        if (!Objects.equals(String.valueOf(req.getSession().getAttribute("role")), "Confirmed teacher") || !String.valueOf(test.getOwner().getId()).equals(req.getSession().getAttribute("id").toString())) {
            resp.sendRedirect("/");
            return;
        }
        filterChain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
