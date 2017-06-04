package edu.xupt.cs.se.servlet.manager;

import edu.xupt.cs.se.dao.PlayDAO;
import edu.xupt.cs.se.dao.ScheduleDAO;
import edu.xupt.cs.se.dao.StudioDAO;
import edu.xupt.cs.se.model.Manager;
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
@WebServlet(name = "ManagerScheduleServlet", urlPatterns = "/managers/schedule/")
public class ManagerScheduleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StudioDAO studioDAO = new StudioDAO();
        ScheduleDAO scheduleDAO = new ScheduleDAO();
        ArrayList<Studio> studios = studioDAO.getStudioByTheater((int) request.getSession().getAttribute("theaterid"));
        ArrayList<Schedule> schedules = new ArrayList<>();

        for (Studio studio : studios) {
            for(Schedule schedule : scheduleDAO.getScheduleByStudio(studio.getId())){
                schedules.add(schedule);
            }
        }

        if(null != request.getParameter("type") && request.getParameter("type").equals("delete")){
            String str_id = request.getParameter("id");
            int id;
            try {
                id = Integer.parseInt(str_id);
            }catch (NumberFormatException e){
                request.setAttribute("message","删除失败！");
                request.setAttribute("schedules", schedules);
                request.getRequestDispatcher("/manager/schedule_manager.jsp").forward(request, response);
                return;
            }

            if(scheduleDAO.delete(id)){
                response.sendRedirect("/managers/schedule/");
            }else {
                request.setAttribute("message","删除失败！");
                request.setAttribute("schedules", schedules);
                request.getRequestDispatcher("/manager/schedule_manager.jsp").forward(request, response);
            }
        }else {
            request.setAttribute("schedules", schedules);
            request.getRequestDispatcher("/manager/schedule_manager.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
