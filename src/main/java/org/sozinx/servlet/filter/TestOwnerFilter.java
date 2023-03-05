package org.sozinx.servlet.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.sozinx.model.Test;
import org.sozinx.service.DataBaseService;
import org.sozinx.service.DataBaseServiceImpl;

import java.io.IOException;

/**
 * This filter helping allow access only for test's owner.
 *
 * @author Ostap Petruniak
 * @since 1.0
 */
@WebFilter(urlPatterns = {"/add/*", "/delete/*", "/change/*", "/edit/*"})
@SuppressWarnings("unused")
public class TestOwnerFilter implements Filter {
    private DataBaseService service;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        service = DataBaseServiceImpl.getInstance();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        final HttpServletResponse resp = (HttpServletResponse) servletResponse;
        Test test = service.getTestManager().getTestById(Long.parseLong(req.getRequestURI().
                substring(req.getRequestURI().lastIndexOf("/") + 1)));//getting test id from uri -> getting test
        if (!String.valueOf(test.getOwner().getId()).equals(req.getSession().getAttribute("id").toString())) {
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
