package edu.xupt.cs.se.servlet.generalmanager;

import edu.xupt.cs.se.dao.ManagerDAO;
import edu.xupt.cs.se.model.Manager;

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
@WebServlet(name = "employee_managerServlet",urlPatterns = "/generalmanager/employee_manager")

public class employee_managerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ManagerDAO managerDAO =new ManagerDAO();
        ArrayList<Manager> managers = managerDAO.getAllManager();
        request.setAttribute("managers", managers);
        if (null !=request.getParameter("type") && request.getParameter("type").equals("delete")){
            System.out.println(request.getParameter("id"));
            String str_id = request.getParameter("id");
            int id;
            try {
                id=Integer.parseInt(str_id);
            }catch (NumberFormatException e){
                request.setAttribute("message", "删除失败");
                request.getRequestDispatcher("/generalmanager/employee_manager.jsp").forward(request, response);
                return;
            }
            if (managerDAO.delete(id)) {
                //删除成功
                response.sendRedirect("/generalmanager/employee_manager");
            } else {
                request.setAttribute("message", "删除失败");
                request.getRequestDispatcher("/generalmanager/employee_manager.jsp").forward(request, response);
            }
        }
        else{request.getRequestDispatcher("/generalmanager/employee_manager.jsp").forward(request,response);}
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
