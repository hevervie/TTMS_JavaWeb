package edu.xupt.cs.se.servlet.generalmanager;


import edu.xupt.cs.se.dao.GeneralmanagerDAO;
import edu.xupt.cs.se.model.Generalmanager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by MR.D on 2017/6/1.
 */
@WebServlet(name = "change_passwdServlet",urlPatterns = "/generalmanager/change_passwd")

public class change_passwdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(null != request.getParameter("old")){
            String old = request.getParameter("old");
            String newp = request.getParameter("new");
            String repeat = request.getParameter("repeat");
            if(null == old || old.equals("")){
                request.setAttribute("message","密码不能为空！");
                request.getRequestDispatcher("/generalmanager/change_passwd.jsp").forward(request,response);
            }else if(null == newp || old.equals("")){
                request.setAttribute("generalmanager","密码不能为空！");
                request.getRequestDispatcher("/generalmanager/change_passwd.jsp").forward(request,response);
            }else if(null == repeat || old.equals("")) {
                request.setAttribute("generalmanager", "密码不能为空！");
                request.getRequestDispatcher("/generalmanager/change_passwd.jsp").forward(request, response);
            }else{
                GeneralmanagerDAO generalmanagerDAO = new GeneralmanagerDAO();
                Generalmanager generalmanager = generalmanagerDAO.getGeneralmanagerByID((int) request.getSession().getAttribute("userid"));
                if(newp.equals(repeat)){
                    if(generalmanagerDAO.check(generalmanager.getEmp_no(),old)){
                        generalmanager.setPasswd(repeat);
                        generalmanagerDAO.update(generalmanager);
                        response.sendRedirect("/index.jsp");
                    }else{
                        request.setAttribute("message", "原始密码错误！");
                        request.getRequestDispatcher("/generalmanager/change_passwd.jsp").forward(request, response);
                    }
                }else{
                    request.setAttribute("message", "两次输入的密码不正确！");
                    request.getRequestDispatcher("/generalmanager/change_passwd.jsp").forward(request, response);
                }
            }
        }else {
            request.getRequestDispatcher("/generalmanager/change_passwd.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
