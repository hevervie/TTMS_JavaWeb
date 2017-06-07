package edu.xupt.cs.se.dao;

import edu.xupt.cs.se.idao.Itheater;
import edu.xupt.cs.se.model.Theater;
import edu.xupt.cs.se.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by zhoupan on 17-5-31.
 */
public class TheaterDAO implements Itheater {
    @Override
    public boolean insert(Theater theater) {
        return false;
    }

    @Override
    public boolean delete(int theater_id) {
        return false;
    }

    @Override
    public boolean update(Theater theater) {
        return false;
    }

    @Override
    public Theater getTheaterByID(int theater_id) {
        return null;
    }

    @Override
    public ArrayList<Theater> getTheaterByPage(int page) {
        return null;
    }

    @Override
    public ArrayList<Theater> getAllTheater() {
        ArrayList<Theater> list = new ArrayList<Theater>();
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

            String sql = "select * from theater";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Theater theater = new Theater();
                theater.setId(rs.getInt("id"));
                theater.setAddr(rs.getString("addr"));
                theater.setName(rs.getString("name"));
                theater.setStudio_number(rs.getInt("studio_number"));
                list.add(theater);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(rs, ps, conn);
            return list;
        }
    }


}
