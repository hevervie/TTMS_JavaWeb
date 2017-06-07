package edu.xupt.cs.se.servlet.employee;

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
 * Created by Administrator on 2017/6/2.
 */
@WebServlet(name = "PlayServlet",urlPatterns = "/employee/play")
public class PlayServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PlayDAO playDAO = new PlayDAO();
        ArrayList<Play> plays = playDAO.getAllPlay();
        ScheduleDAO scheduleDAO=new ScheduleDAO();
        StudioDAO studioDAO=new StudioDAO();
        ArrayList<Schedule> schedules = new ArrayList<>();
        ArrayList<Studio> studios = studioDAO.getStudioByTheater((int) request.getSession().getAttribute("userid"));
        for (Studio studio : studios) {
            for(Schedule schedule : scheduleDAO.getScheduleByStudio(studio.getId())){
                schedules.add(schedule);
            }
        }
        if(null != request.getParameter("play")){
            int play_id = Integer.parseInt(request.getParameter("play"));
            String date = request.getParameter("time");
            ArrayList<Schedule> schs = new ArrayList<>();
            ArrayList<Schedule> schedulesed = scheduleDAO.getScheduleByPlay(play_id);
            for(Schedule schedule: schedulesed){
                if(schedule.getTime().equals(date)){
                    schs.add(schedule);
                }
            }

            request.setAttribute("schedules", schs);
        }
        request.setAttribute("schedule",schedules);
        request.setAttribute("plays", plays);
        request.getRequestDispatcher("/employee/Play.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
