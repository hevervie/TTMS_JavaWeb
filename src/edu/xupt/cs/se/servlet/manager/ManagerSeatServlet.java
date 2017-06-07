package edu.xupt.cs.se.servlet.manager;

import edu.xupt.cs.se.dao.SeatDAO;
import edu.xupt.cs.se.dao.StudioDAO;
import edu.xupt.cs.se.model.Seat;
import edu.xupt.cs.se.model.Studio;

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
@WebServlet(name = "ManagerSeatServlet", urlPatterns = "/managers/seat/")
public class ManagerSeatServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StudioDAO studioDAO = new StudioDAO();

        ArrayList<Studio> studios = studioDAO.getStudioByTheater((int) request.getSession().getAttribute("theaterid"));
        SeatDAO seatDAO = new SeatDAO();
        if (null != request.getParameter("studio_id")) {
            String str_id = request.getParameter("studio_id");
            int studio_id;
            try {
                studio_id = Integer.parseInt(str_id);
            } catch (NumberFormatException e) {
                request.setAttribute("studios", studios);
                request.getRequestDispatcher("/manager/seat_manager.jsp").forward(request, response);
                return;
            }

            ArrayList<Seat> seats = seatDAO.getSeatByStudio(studio_id);
            request.setAttribute("seats", seats);
            request.setAttribute("studios", studios);
            request.getRequestDispatcher("/manager/seat_manager.jsp").forward(request, response);
        } else if (null != request.getParameter("type") && request.getParameter("type").equals("delete")) {
            String str_id = request.getParameter("id");
            int id;
            try {
                id = Integer.parseInt(str_id);
            } catch (NumberFormatException e) {
                request.setAttribute("message", "删除失败");
                request.getRequestDispatcher("/manager/seat_manager.jsp").forward(request, response);
                return;
            }
            if (seatDAO.delete(id)) {
                //删除成功
                response.sendRedirect("/managers/seat/");
            } else {
                request.setAttribute("message", "删除失败");
                request.getRequestDispatcher("/manager/studio_manager.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("studios", studios);
            request.getRequestDispatcher("/manager/seat_manager.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
