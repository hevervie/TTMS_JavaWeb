package edu.xupt.cs.se.servlet.manager;

import com.sun.xml.internal.bind.v2.TODO;
import edu.xupt.cs.se.dao.PlayDAO;
import edu.xupt.cs.se.model.Play;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Shinelon on 2017/6/1.
 */
@WebServlet(name = "ManagerPlayServlet", urlPatterns = "/managers/movie/")
public class ManagerPlayServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PlayDAO playDAO = new PlayDAO();
        ArrayList<Play> plays = playDAO.getAllPlay();
        request.setAttribute("plays", plays);
        if (null != request.getParameter("type") && request.getParameter("type").equals("delete")) {

            String str_id = request.getParameter("id");
            int id;
            try {
                id = Integer.parseInt(str_id);
            } catch (NumberFormatException e) {
                request.setAttribute("message", "删除失败");
                request.getRequestDispatcher("/manager/play_manager.jsp").forward(request, response);
                return;
            }

            if (playDAO.delete(id)) {
                //删除成功
                response.sendRedirect("/managers/movie/");
            } else {
                request.setAttribute("message", "删除失败");
                request.getRequestDispatcher("/manager/play_manager.jsp").forward(request, response);
            }
        } else if (null != request.getParameter("type") && request.getParameter("type").equals("status")) {
            //TODO

        } else {
            request.getRequestDispatcher("/manager/play_manager.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
