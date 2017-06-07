package edu.xupt.cs.se.servlet;

import edu.xupt.cs.se.dao.EmployeeDAO;
import edu.xupt.cs.se.dao.GeneralmanagerDAO;
import edu.xupt.cs.se.dao.ManagerDAO;
import edu.xupt.cs.se.model.Employee;
import edu.xupt.cs.se.model.Manager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by MR.D on 2017/5/31.
 */
@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        request.getSession().setAttribute("login", null);
        request.getSession().setAttribute("actor", null);
        if (null == username || null == password) {
            request.setAttribute("reason", "用户名或密码不能为空！");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        if (username.length() < 1 || password.length() < 1) {
            request.setAttribute("reason", "用户名或密码不能为空！");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else if (username.charAt(0) == '1') {
            GeneralmanagerDAO login = new GeneralmanagerDAO();
            if (login.check(username, password)) {
                // 获取用户ID
                request.getSession().setAttribute("userid", login.getGeneralmanagerByNumber(username).getId());
                request.getSession().setAttribute("login", "ok");
                request.getSession().setAttribute("actor", "generalmanager");
                response.sendRedirect("/generalmanager/");
            } else {
                request.setAttribute("reason", "用户名或密码不正确！");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } else if (username.charAt(0) == '2') {
            ManagerDAO login = new ManagerDAO();
            if (login.check(username, password)) {
                Manager manager = login.getManagerByNumber(username);
                System.out.println("theaterid = "+manager.getTheater_id());
                request.getSession().setAttribute("userid", manager.getId());
                request.getSession().setAttribute("theaterid", manager.getTheater_id());
                request.getSession().setAttribute("login", "ok");
                request.getSession().setAttribute("actor", "managers");
                response.sendRedirect("/managers/");
            } else {
                request.setAttribute("reason", "用户名或密码不正确！");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }

        } else if (username.charAt(0) == '3') {
            EmployeeDAO login = new EmployeeDAO();
            if (login.check(username, password)) {
                Employee employee = login.getEmployeeByNumber(username);
                request.getSession().setAttribute("userid", employee.getId());
                request.getSession().setAttribute("theaterid", employee.getTheater_id());
                request.getSession().setAttribute("login", "ok");
                request.getSession().setAttribute("actor", "employee");
                response.sendRedirect("/employee/");
            } else {
                request.setAttribute("reason", "用户名或密码不正确！");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("reason", "用户名或密码不正确！");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
