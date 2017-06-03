package edu.xupt.cs.se.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by MR.D on 2017/5/31.
 */

@WebFilter(filterName = "CharsetAllEncodingFilter", initParams = {@WebInitParam(name = "encoding", value = "UTF-8")})
public class CharsetAllEncodingFilter implements Filter {

    private String encoding = null;

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        if (httpRequest.getMethod().equalsIgnoreCase("POST")) {
            req.setCharacterEncoding(encoding);
        }
        System.out.println("test");
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        encoding = config.getInitParameter("encoding");
    }

}
