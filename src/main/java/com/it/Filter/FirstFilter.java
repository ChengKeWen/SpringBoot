package com.it.Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "FirstFilter",urlPatterns = "/*")
public class FirstFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("--------------FirstFilter start-------------");
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("--------------FirstFilter end-------------");
    }

    @Override
    public void destroy() {

    }
}
