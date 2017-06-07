package edu.xupt.cs.se.servlet.employee;

import edu.xupt.cs.se.dao.EmployeeDAO;
import edu.xupt.cs.se.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017/6/2.
 */
@WebServlet(name = "ChangePwdServlet",urlPatterns = "/employee/changePwd")
public class ChangePwdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("oldPassword")==null){
            request.getRequestDispatcher("/employee/changePwd.jsp").forward(request,response);
            return;
        }
        String emp_no=(String) request.getSession().getAttribute("username");
        System.out.println("-------------"+(String) request.getSession().getAttribute("username"));
        EmployeeDAO employeeDAO=new EmployeeDAO();
        employeeDAO.getEmployeeByNumber(emp_no).getPasswd();
        System.out.println( employeeDAO.getEmployeeByNumber(emp_no).getPasswd());
        String oldPwd=request.getParameter("oldPassword");
        String newPassword1=request.getParameter("newPassword1");
        String newPassword2=request.getParameter("newPassword2");
        System.out.println(newPassword1+newPassword2);
        System.out.println(oldPwd);

        if (oldPwd.length()<1 || newPassword1.length()<1 || newPassword2.length()<1){
            request.setAttribute("reason","不能为空!");
            request.getRequestDispatcher("/employee/changePwd.jsp").forward(request,response);
        } else if (!employeeDAO.getEmployeeByNumber(emp_no).getPasswd().equals(oldPwd)){
            request.setAttribute("reason","原始密码错误！");
            request.getRequestDispatcher("/employee/changePwd.jsp").forward(request,response);
        } else if (!newPassword1.equals(newPassword2)){
            request.setAttribute("reason","新密码与确认密码不同！");
            request.getRequestDispatcher("/employee/changePwd.jsp").forward(request,response);
        }else{
            Employee employee=new Employee();
            employee.setPasswd(newPassword1);
            employee.setId(employeeDAO.getEmployeeByNumber(emp_no).getId());
            employee.setEmp_no(employeeDAO.getEmployeeByNumber(emp_no).getEmp_no());
            employee.setTheater_id(employeeDAO.getEmployeeByNumber(emp_no).getTheater_id());
            employee.setName(employeeDAO.getEmployeeByNumber(emp_no).getName());
            employee.setTel(employeeDAO.getEmployeeByNumber(emp_no).getTel());
            System.out.println(employee.getId()+"jjjjjjjjjj"+employee.getPasswd());
            employeeDAO.update(employee);
            request.setAttribute("reason","修改成功！");
            request.getRequestDispatcher("/employee/changePwd.jsp").forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
