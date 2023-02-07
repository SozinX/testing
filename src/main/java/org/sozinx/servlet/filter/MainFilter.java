package org.sozinx.servlet.filter;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.sozinx.service.TestingService;
import org.sozinx.service.TestingServiceImpl;

import java.io.IOException;
import java.util.Objects;


@WebFilter("/*")
@SuppressWarnings("unused")
public class MainFilter implements Filter {
    private TestingService service;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        service = TestingServiceImpl.getInstance();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        final HttpServletResponse resp = (HttpServletResponse) servletResponse;
        for(int i = 0; i < req.getCookies().length; i++){
            if(Objects.equals(req.getCookies()[i].getName(), "language")){
                String language = req.getCookies()[i].getValue();
                if(language == null){
                    resp.addCookie(new Cookie("language", "ua"));

                }
                req.setAttribute("language", req.getCookies()[i].getValue());
            }
        }
        Object attribute = req.getSession().getAttribute("testId");
        if (attribute != null) {
            if (!Objects.equals(attribute.toString(), "") && !Objects.equals(attribute.toString(), service.getTestIdFromUri(req))) {
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
