package edu.xupt.cs.se.dao;

import edu.xupt.cs.se.idao.Ischedule;
import edu.xupt.cs.se.model.Schedule;
import edu.xupt.cs.se.model.Studio;
import edu.xupt.cs.se.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by zhoupan on 17-5-31.
 */
public class ScheduleDAO implements Ischedule {
    @Override
    public boolean insert(Schedule schedule) {
        boolean rtu = false;
        if (schedule == null) {
            return rtu;
        }
        //获取Connection
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        try {

            String sql = "insert into schedule(studio_id, play_id, time, discount, price, status) VALUES (?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, schedule.getStudio_id());
            ps.setInt(2, schedule.getPlay_id());
            ps.setString(3, schedule.getTime());
            ps.setFloat(4, schedule.getDiscount());
            ps.setFloat(5, schedule.getPrice());
            ps.setInt(6, schedule.getStatus());
            ps.executeUpdate();
            rtu = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(null, ps, conn);
            return rtu;
        }
    }

    @Override
    public boolean delete(int schedule_id) {
        boolean rtu = false;
        if (schedule_id <= 0) {
            return rtu;
        }
        //获取Connection
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "delete from schedule where id=?;";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, schedule_id);
            ps.executeUpdate();
            rtu = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(null, ps, conn);
            return rtu;
        }
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
        ArrayList<Schedule> list = new ArrayList<>();
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select * from schedule where studio_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, studio_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Schedule schedule = new Schedule();
                schedule.setId(rs.getInt("id"));
                schedule.setStudio_id(rs.getInt("studio_id"));
                schedule.setPlay_id(rs.getInt("play_id"));
                schedule.setTime(rs.getString("time"));
                schedule.setDiscount(rs.getFloat("discount"));
                schedule.setPrice(rs.getFloat("price"));
                schedule.setStatus(rs.getInt("status"));
                list.add(schedule);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(rs, ps, conn);
            return list;
        }

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
