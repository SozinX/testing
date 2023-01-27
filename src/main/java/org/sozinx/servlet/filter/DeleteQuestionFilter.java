package org.sozinx.servlet.filter;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.sozinx.model.Test;
import org.sozinx.service.DataBaseService;
import org.sozinx.service.DataBaseServiceImpl;
import org.sozinx.service.EditQuestionService;
import org.sozinx.service.EditQuestionServiceImpl;

import java.io.IOException;
import java.util.Objects;


@WebFilter("/delete/*")
public class DeleteQuestionFilter implements Filter {
    private DataBaseService manager;
    private EditQuestionService service;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        manager = DataBaseServiceImpl.getInstance();
        service = EditQuestionServiceImpl.getInstance();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        final HttpServletResponse resp = (HttpServletResponse) servletResponse;
        Test test = manager.getTestManager().getTestById(Long.parseLong(service.getTestIdFromUri(req)));
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
