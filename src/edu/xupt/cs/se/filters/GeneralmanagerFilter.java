package edu.xupt.cs.se.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by MR.D on 2017/5/31.
 */
@WebFilter(filterName = "GeneralmanagerFilter")
public class GeneralmanagerFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String flag = (String) request.getSession().getAttribute("login");
        String actor = (String) request.getSession().getAttribute("actor");
        if (flag != null && flag.equals("ok")) {
            if (actor != null && actor.equals("generalmanager")) {
                chain.doFilter(request, response);
            }else {
                System.out.println("您无权访问该目录:" + flag);
                request.setAttribute("message", "您无权访问该目录");
                RequestDispatcher rd = request.getRequestDispatcher("/404.html");
                rd.forward(request, response);
            }
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("/login");
            rd.forward(request, response);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
