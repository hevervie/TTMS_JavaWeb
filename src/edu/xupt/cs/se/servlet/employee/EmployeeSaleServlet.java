package edu.xupt.cs.se.servlet.employee;

import edu.xupt.cs.se.dao.*;
import edu.xupt.cs.se.model.*;
import netscape.javascript.JSException;
import netscape.javascript.JSObject;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Shinelon on 2017/6/5.
 */

@WebServlet(name = "EmployeeSaleServlet", urlPatterns = "/employee/tickets")
public class EmployeeSaleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ScheduleDAO scheduleDAO = new ScheduleDAO();
        StudioDAO studioDAO = new StudioDAO();

        if (null != request.getParameter("type") && request.getParameter("type").equals("getrc")) {
            int id = Integer.parseInt(request.getParameter("id"));
            System.out.println(id);
            Schedule schedule = scheduleDAO.getScheduleByID(id);
            if (null != schedule) {
                Studio studio = studioDAO.getStudioByID(schedule.getStudio_id());
                if(studio == null){
                    return;
                }
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("row", studio.getRow());
                jsonObject.put("col", studio.getCol());
                response.setCharacterEncoding("utf-8");
                response.getWriter().write(jsonObject.toString());
                return;
            }
            return;
        }

        PlayDAO playDAO = new PlayDAO();

        ArrayList<Play> plays = playDAO.getAllPlay();
        Set<String> time = new HashSet<>();
        ArrayList<Schedule> allsch = scheduleDAO.getAllSchedule();
        EmployeeDAO employeeDAO = new EmployeeDAO();
        ArrayList<Studio> studios = studioDAO.getStudioByTheater(employeeDAO.getEmployeeByID((int) request.getSession().getAttribute("userid")).getTheater_id());
        ArrayList<Schedule> mysch = new ArrayList<>();
        for (Schedule schedule : allsch) {
            if (isConstain(studios, studioDAO.getStudioByID(schedule.getStudio_id()))) {
                mysch.add(schedule);
            }
            time.add(schedule.getTime());
        }


        if (null != request.getParameter("play")) {
            int play_id = Integer.parseInt(request.getParameter("play"));
            String date = request.getParameter("time");
            ArrayList<Schedule> schs = new ArrayList<>();
            ArrayList<Schedule> schedules = scheduleDAO.getScheduleByPlay(play_id);
            if (null != date && date.equals("all")) {
                for (Schedule schedule : schedules) {
                    if (isConstain(studios, studioDAO.getStudioByID(schedule.getStudio_id()))) {
                        schs.add(schedule);
                    }
                }
            } else {
                for (Schedule schedule : schedules) {
                    if (isConstain(studios, studioDAO.getStudioByID(schedule.getStudio_id())) && schedule.getTime().equals(date)) {
                        schs.add(schedule);
                    }

                }
            }
            request.setAttribute("schedules", schs);
        }

        if(null != request.getParameter("schedule")){
            int sched_id = Integer.parseInt(request.getParameter("schedule"));
            int row = Integer.parseInt(request.getParameter("row"));
            int col = Integer.parseInt(request.getParameter("col"));
            TicketDAO ticketDAO = new TicketDAO();
            System.out.println("sch:"+sched_id);
            ArrayList<Ticket> tickets = ticketDAO.getTicketBySchedule(sched_id);
            SeatDAO seatDAO = new SeatDAO();
            for(Ticket ticket : tickets){
                Seat seat = seatDAO.getSeatByID(ticket.getSeat_id());
                if(null == seat){
                    System.out.println("null");
                    continue;
                }
                System.out.println("row"+ row + " col" + col );
                System.out.println("row"+ seat.getRow() + " col" + seat.getCol() );
                if(seat.getRow() == row && seat.getCol() == col ){
                    if(ticket.getStatus() == 0){
                        ticket.setStatus(1);
                        if(ticketDAO.update(ticket)){
                            BillDAO billDAO = new BillDAO();
                            Bill bill = new Bill();
                            bill.setCustomer_id(0);
                            bill.setTicket_id(ticket.getId());
                            bill.setEmp_id((int) request.getSession().getAttribute("userid"));
                            bill.setPlay_id(ticket.getPlay_id());
                            bill.setPrice(ticket.getPrice());

                            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
                            bill.setSale_time(df.format(new Date()));

                            billDAO.insert(bill);

                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put("status", "success");
                            jsonObject.put("message", "success");
                            response.setCharacterEncoding("utf-8");
                            response.getWriter().write(jsonObject.toString());
                            return;
                        }else{
                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put("status", "fail");
                            jsonObject.put("message", "订购失败！");
                            response.setCharacterEncoding("utf-8");
                            response.getWriter().write(jsonObject.toString());
                            return;
                        }
                    }else{
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("status", "fail");
                        jsonObject.put("message", "此票已被订购！");
                        response.setCharacterEncoding("utf-8");
                        response.getWriter().write(jsonObject.toString());
                        return;
                    }
                }
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status", "fail");
            jsonObject.put("message", "此票不存在！");
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(jsonObject.toString());
            return;
        }


        request.setAttribute("mysch", mysch);
        request.setAttribute("plays", plays);
        request.setAttribute("time", time);
        request.getRequestDispatcher("/employee/sale_ticket.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    private boolean isConstain(ArrayList<Studio> studios, Studio studio) {
        if (null == studios || null == studio) {
            return false;
        }

        for (Studio stu : studios) {
            if (stu.getId() == studio.getId()) {
                return true;
            }
        }
        return false;
    }
}
