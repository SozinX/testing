package org.sozinx.servlet.filter;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;

/**
 * This filter helping allow access only for authorised user in deactivation page.
 *
 * @author Ostap Petruniak
 * @since 1.0
 */
@WebFilter("/deactivate")
@SuppressWarnings("unused")
public class DeactivateFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        final HttpServletResponse resp = (HttpServletResponse) servletResponse;
        if (req.getSession().getAttribute("id") == null || Objects.equals(String.valueOf(req.getSession().getAttribute("role")), "Confirmed teacher")) {
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
