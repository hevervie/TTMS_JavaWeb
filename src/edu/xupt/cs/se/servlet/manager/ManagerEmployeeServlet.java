package edu.xupt.cs.se.servlet.manager;

import edu.xupt.cs.se.dao.EmployeeDAO;
import edu.xupt.cs.se.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by zhoupan on 17-6-1.
 */
@WebServlet(name = "ManagerEmployeeServlet", urlPatterns = "/managers/employee/")
public class ManagerEmployeeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EmployeeDAO employeeDAO = new EmployeeDAO();
        ArrayList<Employee> employees = employeeDAO.getEmployeeByTheater((int) request.getSession().getAttribute("theaterid"));
        request.setAttribute("employees",employees);
        if(null != request.getParameter("type") && request.getParameter("type").equals("delete")){
            String str_id = request.getParameter("id");
            int id;
            try {
                id = Integer.parseInt(str_id);
            }catch (NumberFormatException e){
                request.getRequestDispatcher("/manager/employee_manager.jsp").forward(request,response);
                return;
            }
            if(employeeDAO.delete(id)){
                response.sendRedirect("/managers/employee/");
                return;
            }else{
                request.getRequestDispatcher("/manager/employee_manager.jsp").forward(request,response);
                return;
            }
        }
        request.getRequestDispatcher("/manager/employee_manager.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
