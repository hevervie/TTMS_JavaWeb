package edu.xupt.cs.se.servlet.manager;

import edu.xupt.cs.se.dao.StudioDAO;
import edu.xupt.cs.se.model.Studio;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.StubNotFoundException;
import java.util.ArrayList;

/**
 * Created by Shinelon on 2017/6/1.
 */
@WebServlet(name = "ManagerStudioServlet", urlPatterns = "/managers/studio/")
public class ManagerStudioServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //需要影厅信息
        StudioDAO studioDAO = new StudioDAO();
        ArrayList<Studio> studios = studioDAO.getAllStudio();
        System.out.println(studios.size());
        request.setAttribute("studios", studios);
        if (null != request.getParameter("type") && request.getParameter("type").equals("delete")) {

            System.out.println(request. getParameter("id"));
            String str_id = request.getParameter("id");
            int id;
            try {
                id = Integer.parseInt(str_id);
            } catch (NumberFormatException e) {
                request.setAttribute("message", "删除失败");
                request.getRequestDispatcher("/manager/studio_manager.jsp").forward(request, response);
                return;
            }
            System.out.println("id = "+id);
            if (studioDAO.delete(id)) {
                //删除成功
                response.sendRedirect("/managers/studio/");
            } else {
                request.setAttribute("message", "删除失败");
                request.getRequestDispatcher("/manager/studio_manager.jsp").forward(request, response);
            }
        }else {
            request.getRequestDispatcher("/manager/studio_manager.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
