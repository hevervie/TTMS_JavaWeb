package edu.xupt.cs.se.dao;

import edu.xupt.cs.se.idao.Ilevel;
import edu.xupt.cs.se.model.Lang;
import edu.xupt.cs.se.model.Level;
import edu.xupt.cs.se.model.Level;
import edu.xupt.cs.se.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by zhoupan on 17-5-31.
 */
public class LevelDAO implements Ilevel {
    @Override
    public boolean insert(Level level) {
        return false;
    }

    @Override
    public boolean delete(int level_id) {
        return false;
    }

    @Override
    public boolean update(Level lang) {
        return false;
    }

    @Override
    public Level getLevelByID(int level_id) {
        Level rtu = null;
        if (level_id <= 0) {
            return rtu;
        }
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //insert into employee(emp_no,theater_id,name,passwd,tel) VALUES (?,?,?,?,?)
            String sql = "select * from level where id =?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, level_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                rtu = new Level();
                rtu.setId(rs.getInt("id"));
                rtu.setType(rs.getString("type"));
            }
            return rtu;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(null, ps, conn);
            return rtu;
        }
    }

    @Override
    public ArrayList<Level> getLevelByPage(int page) {
        return null;
    }

    @Override
    public ArrayList<Level> getAllLevel() {
        ArrayList<Level> list = new ArrayList<>();
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

            String sql = "select * from level";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Level level = new Level();
                level.setId(rs.getInt("id"));
                level.setType(rs.getString("type"));
                // 将该用户信息插入列表
                list.add(level);
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
