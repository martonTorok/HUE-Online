package com.anram.hue.utils;

import com.auth0.SessionUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Filter;
import java.util.logging.LogRecord;

public class Auth0Filter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain next) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String accessToken = (String) SessionUtils.get(req, "accessToken");
        String idToken = (String) SessionUtils.get(req, "idToken");
        if (accessToken == null && idToken == null) {
            res.sendRedirect("/login");
            return;
        }
        next.doFilter(request, response);
    }

    public void destroy() {
    }

    @Override
    public boolean isLoggable(LogRecord record) {
        return false;
    }
}
