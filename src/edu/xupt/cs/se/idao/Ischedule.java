package edu.xupt.cs.se.idao;

import edu.xupt.cs.se.model.Schedule;

import java.util.ArrayList;

/**
 * Created by zhoupan on 17-5-30.
 */
public interface Ischedule {
    /*
    private int id;
    private int studio_id;
    private int play_id;
    private String time;
    private float discount;
    private float price;
    private int status;
     */

    public boolean insert(Schedule schedule);

    public boolean delete(int schedule_id);

    public boolean update(Schedule schedule);

    public Schedule getScheduleByID(int schedule_id);

    public ArrayList<Schedule> getScheduleByStudio(int page, int studio_id);

    public ArrayList<Schedule> getScheduleByPlay(int page, int play_id);

    public ArrayList<Schedule> getScheduleByPage(int page);

    public ArrayList<Schedule> getScheduleByStudio(int studio_id);

    public ArrayList<Schedule> getScheduleByPlay(int play_id);

    public ArrayList<Schedule> getAllSchedule();
}
