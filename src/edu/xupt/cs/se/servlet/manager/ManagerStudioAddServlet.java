package edu.xupt.cs.se.servlet.manager;

import edu.xupt.cs.se.dao.StudioDAO;
import edu.xupt.cs.se.model.Studio;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by zhoupan on 2017/6/1.
 */
@WebServlet(name = "ManagerStudioAddServlet", urlPatterns = "/managers/studio/add/")
public class ManagerStudioAddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(null == request.getParameter("studio_name")){
            request.getRequestDispatcher("/manager/studio_add.jsp").forward(request,response);
            return;
        }
        String name = request.getParameter("studio_name");
        String str_row = request.getParameter("studio_row");
        String str_col = request.getParameter("studio_col");
        int row,col;

        System.out.println("1"+name);
        // 判断name
        if(name.equals("") || name.length() > 40){
            request.setAttribute("message","影厅名称为空或太长！");
            request.getRequestDispatcher("/manager/studio_add.jsp").forward(request,response);
            return;
        }

        //判断row和col
        try {
            row = Integer.parseInt(str_row);
            col = Integer.parseInt(str_col);
        }catch (NumberFormatException n){
            request.setAttribute("message","行数或列数输入错误！");
            request.getRequestDispatcher("/manager/studio_add.jsp").forward(request,response);
            return;
        }

        Studio studio = new Studio();
        StudioDAO studioDAO = new StudioDAO();
        studio.setName(name);
        studio.setRow(row);
        studio.setCol(col);
        studio.setTheater_id(1);
        studioDAO.insert(studio);
        response.sendRedirect("/managers/studio/");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
