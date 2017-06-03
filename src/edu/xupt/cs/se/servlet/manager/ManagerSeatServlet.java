package edu.xupt.cs.se.servlet.manager;

import edu.xupt.cs.se.dao.StudioDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Shinelon on 2017/6/1.
 */
@WebServlet(name = "ManagerSeatServlet", urlPatterns = "/managers/seat/")
public class ManagerSeatServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //影厅信息
        StudioDAO studioDAO = new StudioDAO();


        request.getRequestDispatcher("/manager/seat_manager.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
