package org.sozinx.servlet.filter;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.sozinx.service.EditQuestionService;
import org.sozinx.service.EditQuestionServiceImpl;

import java.io.IOException;


@WebFilter("/change/*")
@SuppressWarnings("unused")
public class EditQuestionFilter implements Filter {
    private EditQuestionService service;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        service = EditQuestionServiceImpl.getInstance();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        final HttpServletResponse resp = (HttpServletResponse) servletResponse;
        if (service.getCountOfPages(req) == 0 || service.getAllQuestions(req).size() == 0) {
            resp.sendRedirect("/add/" + service.getTestIdFromUri(req));
            return;
        }
        filterChain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}