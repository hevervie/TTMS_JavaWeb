package edu.xupt.cs.se.servlet.manager;

import edu.xupt.cs.se.dao.EmployeeDAO;
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
 * Created by zhoupan on 17-6-1.
 */
@WebServlet(name = "ManagerPasswdServlet", urlPatterns = "/managers/passwd/")
public class ManagerPasswdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(null != request.getParameter("old")){
            String old = request.getParameter("old");
            String newp = request.getParameter("new");
            String repeat = request.getParameter("repeat");
            if(null == old || old.equals("")){
                request.setAttribute("message","密码不能为空！");
                request.getRequestDispatcher("/manager/passwd.jsp").forward(request,response);
            }else if(null == newp || old.equals("")){
                request.setAttribute("message","密码不能为空！");
                request.getRequestDispatcher("/manager/passwd.jsp").forward(request,response);
            }else if(null == repeat || old.equals("")) {
                request.setAttribute("message", "密码不能为空！");
                request.getRequestDispatcher("/manager/passwd.jsp").forward(request, response);
            }else{
                ManagerDAO managerDAO = new ManagerDAO();
                Manager manager = managerDAO.getManagerByID((int) request.getSession().getAttribute("userid"));
               if(newp.equals(repeat)){
                   if(managerDAO.check(manager.getEmp_no(),old)){
                       manager.setPasswd(repeat);
                        managerDAO.update(manager);
                        response.sendRedirect("/logout");
                   }else{
                       request.setAttribute("message", "原始密码错误！");
                       request.getRequestDispatcher("/manager/passwd.jsp").forward(request, response);
                   }
               }else{
                   request.setAttribute("message", "两次输入的密码不正确！");
                   request.getRequestDispatcher("/manager/passwd.jsp").forward(request, response);
               }
            }
        }else {
            request.getRequestDispatcher("/manager/passwd.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
