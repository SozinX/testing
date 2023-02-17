package org.sozinx.servlet.filter;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.sozinx.service.CookiesService;
import org.sozinx.service.CookiesServiceImpl;
import org.sozinx.service.TestingService;
import org.sozinx.service.TestingServiceImpl;

import java.io.IOException;
import java.util.Objects;


@WebFilter("/*")
@SuppressWarnings("unused")
public class MainFilter implements Filter {
    private TestingService testingService;
    private CookiesService cookiesService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        testingService = TestingServiceImpl.getInstance();
        cookiesService = CookiesServiceImpl.getInstance();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        final HttpServletResponse resp = (HttpServletResponse) servletResponse;
        cookiesService.filterCheckLanguage(req, resp);
        Object attribute = req.getSession().getAttribute("testId");
        if (attribute != null) {
            if (!Objects.equals(attribute.toString(), "") && !Objects.equals(attribute.toString(), testingService.getTestIdFromUri(req))) {
                resp.sendRedirect("/testing/" + req.getSession().getAttribute("testId") + "?question=" + req.getSession().getAttribute("questionNumber"));
                return;
            }
        }
        filterChain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
