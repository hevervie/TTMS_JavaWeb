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
@WebServlet(name = "ManagerSeatAddServlet", urlPatterns = "/managers/seat/add/")
public class ManagerSeatAddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        StudioDAO studioDAO = new StudioDAO();
        ArrayList<Studio> studios = studioDAO.getStudioByTheater(1);
        request.setAttribute("studios", studios);
        if (null != request.getParameter("studio")) {
            String str_stusdio = request.getParameter("studio");
            String str_row = request.getParameter("row");
            String str_col = request.getParameter("col");

            System.out.println("studio:"+str_stusdio);
            System.out.println("row:"+str_row);
            System.out.println("col:"+str_col);

            int studio_id, row, col;
            try {
                studio_id = Integer.parseInt(str_stusdio);
                row = Integer.parseInt(str_row);
                col = Integer.parseInt(str_col);
            } catch (NumberFormatException e) {
                request.setAttribute("message", "非法输入");
                request.getRequestDispatcher("/manager/seat_add.jsp").forward(request, response);
                return;
            }
            SeatDAO seatDAO = new SeatDAO();
            ArrayList<Seat> seats = seatDAO.getSeatByStudio(studio_id);
            for (Seat seat : seats) {
                if (seat.getRow() == row && seat.getCol() == col) {
                    request.setAttribute("message", "此座位已存在！");
                    request.getRequestDispatcher("/manager/seat_add.jsp").forward(request, response);
                    return;
                }
            }
            Studio studio = new StudioDAO().getStudioByID(studio_id);

            if(row > studio.getRow() || col > studio.getCol()){
                request.setAttribute("message", "此座位超出影厅范围");
                request.getRequestDispatcher("/manager/seat_add.jsp").forward(request, response);
                return;
            }

            Seat seat = new Seat();
            seat.setStudio_id(studio_id);
            seat.setRow(row);
            seat.setCol(col);
            seatDAO.insert(seat);
            response.sendRedirect("/managers/seat/");

        } else {
            request.getRequestDispatcher("/manager/seat_add.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
