package edu.xupt.cs.se.servlet.manager;

import edu.xupt.cs.se.dao.EmployeeDAO;
import edu.xupt.cs.se.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by zhoupan on 17-6-1.
 */
@WebServlet(name = "ManagerEmployeeAddServlet", urlPatterns = "/managers/employee/add/")
public class ManagerEmployeeAddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (null != request.getParameter("empno")) {
            EmployeeDAO employeeDAO = new EmployeeDAO();
            String empno = request.getParameter("empno");
            String name = request.getParameter("name");
            String passwd = request.getParameter("passwd");
            String tel = request.getParameter("tel");

            System.out.println(name);

            if (null == empno || empno.length() <= 0) {
                request.setAttribute("message", "工号不能为空");
                request.getRequestDispatcher("/manager/employee_add.jsp").forward(request, response);
            } else if (empno.length() != 8 || empno.charAt(0) != '3') {
                request.setAttribute("message", "工号格式输入错误！");
                request.getRequestDispatcher("/manager/employee_add.jsp").forward(request, response);
            } else if (null == name || name.length() <= 0) {
                request.setAttribute("message", "姓名不能为空！");
                request.getRequestDispatcher("/manager/employee_add.jsp").forward(request, response);
            } else if (null == passwd || passwd.length() <= 0) {
                request.setAttribute("message", "密码不能为空！");
                request.getRequestDispatcher("/manager/employee_add.jsp").forward(request, response);
            } else if (null == tel || tel.length() <= 0) {
                request.setAttribute("message", "手机号不能为空！");
                request.getRequestDispatcher("/manager/employee_add.jsp").forward(request, response);
            } else if (null != employeeDAO.getEmployeeByNumber(empno)) {
                request.setAttribute("message", "此工号已重复！");
                request.getRequestDispatcher("/manager/employee_add.jsp").forward(request, response);
            } else {

                System.out.println(name);
                Employee employee = new Employee();
                employee.setName(name);
                employee.setEmp_no(empno);
                employee.setPasswd(passwd);
                employee.setTel(tel);
                employee.setTheater_id((int) request.getSession().getAttribute("theaterid"));

                if (employeeDAO.insert(employee)) {
                    response.sendRedirect("/managers/employee/");
                } else {
                    request.setAttribute("mesage", "插入失败！");
                    request.getRequestDispatcher("/manager/employee_add.jsp").forward(request, response);
                }
            }
        } else {
            request.getRequestDispatcher("/manager/employee_add.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
