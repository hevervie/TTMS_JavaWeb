package edu.xupt.cs.se.dao;

import edu.xupt.cs.se.idao.Ischedule;
import edu.xupt.cs.se.model.Schedule;

import java.util.ArrayList;

/**
 * Created by zhoupan on 17-5-31.
 */
public class ScheduleDAO implements Ischedule {
    @Override
    public boolean insert(Schedule schedule) {
        return false;
    }

    @Override
    public boolean delete(int schedule_id) {
        return false;
    }

    @Override
    public boolean update(Schedule schedule) {
        return false;
    }

    @Override
    public Schedule getScheduleByID(int schedule_id) {
        return null;
    }

    @Override
    public ArrayList<Schedule> getScheduleByPage(int page) {
        return null;
    }

    @Override
    public ArrayList<Schedule> getScheduleByStudio(int studio_id) {
        return null;
    }

    @Override
    public ArrayList<Schedule> getScheduleByPlay(int play_id) {
        return null;
    }

    @Override
    public ArrayList<Schedule> getAllSchedule() {
        return null;
    }

    @Override
    public ArrayList<Schedule> getScheduleByStudio(int page, int studio_id) {
        return null;
    }

    @Override
    public ArrayList<Schedule> getScheduleByPlay(int page, int play_id) {
        return null;
    }
}
