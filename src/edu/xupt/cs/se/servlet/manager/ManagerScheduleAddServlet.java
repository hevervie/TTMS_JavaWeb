package edu.xupt.cs.se.servlet.manager;

import edu.xupt.cs.se.dao.PlayDAO;
import edu.xupt.cs.se.dao.ScheduleDAO;
import edu.xupt.cs.se.dao.StudioDAO;
import edu.xupt.cs.se.model.Play;
import edu.xupt.cs.se.model.Schedule;
import edu.xupt.cs.se.model.Studio;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by zhoupan on 17-6-1.
 */
@WebServlet(name = "ManagerScheduleAddServlet", urlPatterns = "/managers/schedule/add/")
public class ManagerScheduleAddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        StudioDAO studioDAO = new StudioDAO();
        PlayDAO playDAO = new PlayDAO();
        ArrayList<Studio> studios = studioDAO.getStudioByTheater((int) request.getSession().getAttribute("theaterid"));
        ArrayList<Play> plays = playDAO.getAllPlay();
        request.setAttribute("studios", studios);
        request.setAttribute("plays", plays);
        if (null != request.getParameter("studio")) {
            String str_studioid = request.getParameter("studio");
            String str_playid = request.getParameter("play");
            String time = request.getParameter("time");
            String str_discount = request.getParameter("discount");
            System.out.println("studio"+str_studioid);
            if (null == time || time.length() != 14) {
                request.setAttribute("message", "时间输入错误！");
                request.getRequestDispatcher("/manager/schedule_add.jsp").forward(request, response);
            }
            int studio_id, play_id;
            float discount;

            try {
                studio_id = Integer.parseInt(str_studioid);
                play_id = Integer.parseInt(str_playid);
                discount = Float.parseFloat(str_discount);
            } catch (NumberFormatException e) {
                request.setAttribute("message", "非法输入！");
                request.getRequestDispatcher("/manager/schedule_add.jsp").forward(request, response);
                return;
            }
            Play play = playDAO.getPlayByID(play_id);
            ScheduleDAO scheduleDAO = new ScheduleDAO();
            Schedule schedule = new Schedule();
            schedule.setStudio_id(studio_id);
            schedule.setPlay_id(play_id);
            schedule.setPrice(play.getPrice() * discount);
            schedule.setDiscount(discount);
            schedule.setTime(time);
            schedule.setStatus(0);
            if(scheduleDAO.insert(schedule)){
                response.sendRedirect("/managers/schedule/");
            }else{
                request.setAttribute("message", "插入失败！");
                request.getRequestDispatcher("/manager/schedule_add.jsp").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("/manager/schedule_add.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
