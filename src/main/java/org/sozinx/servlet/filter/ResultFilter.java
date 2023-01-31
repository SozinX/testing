package org.sozinx.servlet.filter;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.sozinx.service.ResultService;
import org.sozinx.service.ResultServiceImpl;

import java.io.IOException;
import java.util.Objects;


@WebFilter("/result/*")
@SuppressWarnings("unused")
public class ResultFilter implements Filter {
    private ResultService service;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        service = ResultServiceImpl.getInstance();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        final HttpServletResponse resp = (HttpServletResponse) servletResponse;
        if (!Objects.equals(String.valueOf(req.getSession().getAttribute("testId")), service.getTestIdFromUri(req))) {
            resp.sendRedirect("/tests?page=1");
            return;
        }
        filterChain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
