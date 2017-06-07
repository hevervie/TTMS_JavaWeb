package edu.xupt.cs.se.servlet.generalmanager;

import edu.xupt.cs.se.dao.EmployeeDAO;
import edu.xupt.cs.se.dao.ManagerDAO;
import edu.xupt.cs.se.dao.TheaterDAO;
import edu.xupt.cs.se.model.Employee;
import edu.xupt.cs.se.model.Manager;
import edu.xupt.cs.se.model.Theater;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by MR.D on 2017/6/1.
 */
@WebServlet(name = "add_employeeServlet",urlPatterns = "/generalmanager/add_employee")

public class add_employeeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TheaterDAO theaterDAO = new TheaterDAO();
        ArrayList<Theater> theaters = theaterDAO.getAllTheater();
        request.setAttribute("theater", theaters);
        System.out.println("theater:" + request.getParameter("theater"));
        if (null != request.getParameter("theater")) {
            String emp_no = request.getParameter("emp_no");
            String emp_name = request.getParameter("emp_name");
            String emp_passwd = request.getParameter("emp_passwd");
            if (emp_name.length() == 0 || emp_no.length() == 0 || emp_passwd.length() == 0) {
                System.out.println("您的输入不合法");
                request.setAttribute("message", "输入有误");
                request.getRequestDispatcher("/generalmanager/add_employee.jsp").forward(request, response);
            } else {
                Manager manager = new Manager();
                ManagerDAO managerDAO = new ManagerDAO();
                manager.setEmp_no(emp_no);
                manager.setName(emp_name);
                manager.setPasswd(emp_passwd);
                if (managerDAO.insert(manager)) {
                    System.out.println("   " + manager.getName() + " " + manager.getEmp_no() + manager.getPasswd());
                    response.sendRedirect("/generalmanager/employee_manager");
                } else {
                    request.setAttribute("message", "插入失败！");
                    System.out.println("插入失败");
                    request.getRequestDispatcher("/generalmanager/add_employee.jsp").forward(request, response);
                }
            }
        } else {

            request.getRequestDispatcher("/generalmanager/add_employee.jsp").forward(request, response);
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}