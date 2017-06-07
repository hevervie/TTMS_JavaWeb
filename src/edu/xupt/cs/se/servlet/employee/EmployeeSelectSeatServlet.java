package edu.xupt.cs.se.servlet.employee;


import edu.xupt.cs.se.dao.ScheduleDAO;
import edu.xupt.cs.se.dao.SeatDAO;
import edu.xupt.cs.se.dao.TicketDAO;
import edu.xupt.cs.se.model.Schedule;
import edu.xupt.cs.se.model.Seat;
import edu.xupt.cs.se.model.Ticket;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;

/**
 * Created by Shinelon on 2017/6/6.
 */
@WebServlet(name = "EmployeeSelectSeatServlet", urlPatterns = "/employee/select/")
public class EmployeeSelectSeatServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ScheduleDAO scheduleDAO = new ScheduleDAO();
        Schedule schedule = scheduleDAO.getScheduleByID(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("schedule", schedule);
        request.getRequestDispatcher("/employee/select.jsp").forward(request,response);

        if(null != request.getParameter("type") && request.getParameter("type").equals("ajax")){
            int sch_id = Integer.parseInt(request.getParameter("schedule"));
            int row = Integer.parseInt(request.getParameter("row"));
            int col = Integer.parseInt(request.getParameter("col"));

            TicketDAO ticketDAO = new TicketDAO();
            SeatDAO seatDAO = new SeatDAO();
            Ticket ticket = ticketDAO.getTicketBySeat(seatDAO.getSeatByRowCol(row,col).getId());

        }



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
