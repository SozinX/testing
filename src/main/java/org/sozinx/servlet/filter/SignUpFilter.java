package org.sozinx.servlet.filter;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * This filter helping allow access only for NOT authorised user in Sign Up page.
 *
 * @author Ostap Petruniak
 * @since 1.0
 */
@WebFilter("/signup")
@SuppressWarnings("unused")
public class SignUpFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        final HttpServletResponse resp = (HttpServletResponse) servletResponse;
        req.setAttribute("origin", req.getRequestURI());
        if (req.getSession().getAttribute("name") != null) {
            resp.sendRedirect("/");
            return;
        }
        filterChain.doFilter(req, resp);
    }

}
