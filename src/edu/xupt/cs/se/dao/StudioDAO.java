package edu.xupt.cs.se.dao;

import edu.xupt.cs.se.idao.Istudio;
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
public class StudioDAO implements Istudio {
    @Override
    public boolean insert(Studio studio) {
        boolean rtu = false;
        if (studio == null) {
            return rtu;
        }
        //获取Connection
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        try {

            String sql = "insert into studio(theater_id,name,row,col) VALUES (?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, studio.getTheater_id());
            ps.setString(2, studio.getName());
            ps.setInt(3, studio.getRow());
            ps.setInt(4, studio.getCol());
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
    public boolean delete(int studio_id) {
        boolean rtu = false;
        if (studio_id <= 0) {
            return rtu;
        }
        //获取Connection
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "delete from studio where id=?;";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, studio_id);
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
    public boolean update(Studio studio) {
        return false;
    }

    @Override
    public Studio getStudioByID(int studio_id) {
        Studio rtu = null;

        if (studio_id <= 0) {
            return rtu;
        }
        //获取Connection
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "select *  from studio where id=?;";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, studio_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                rtu = new Studio();
                rtu.setId(rs.getInt("id"));
                rtu.setRow(rs.getInt("row"));
                rtu.setCol(rs.getInt("col"));
                rtu.setName(rs.getString("name"));
                rtu.setTheater_id(rs.getInt("theater_id"));
            }
            return rtu;
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
    public Studio getStudioByName(String studio_name) {
        Studio rtu = null;

        if (null == studio_name) {
            return rtu;
        }
        //获取Connection
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            String sql = "select *  from studio where name=?;";
            ps = conn.prepareStatement(sql);
            ps.setString(1, studio_name);
            rs = ps.executeQuery();
            while (rs.next()) {
                rtu = new Studio();
                rtu.setId(rs.getInt("id"));
                rtu.setRow(rs.getInt("row"));
                rtu.setCol(rs.getInt("col"));
                rtu.setName(rs.getString("name"));
                rtu.setTheater_id(rs.getInt("theater_id"));
            }
            return rtu;
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
    public Studio getStudioByName(String studio_name, int theater_id) {
        Studio rtu = null;

        if (null == studio_name) {
            return rtu;
        }
        //获取Connection
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            String sql = "select *  from studio where name=? and theater_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, studio_name);
            ps.setInt(2, theater_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                rtu = new Studio();
                rtu.setId(rs.getInt("id"));
                rtu.setRow(rs.getInt("row"));
                rtu.setCol(rs.getInt("col"));
                rtu.setName(rs.getString("name"));
                rtu.setTheater_id(rs.getInt("theater_id"));
            }
            return rtu;
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
    public ArrayList<Studio> getStudioByPage(int page) {
        return null;
    }

    @Override
    public ArrayList<Studio> getStudioByTheater(int theater_id) {
        ArrayList<Studio> list = new ArrayList<>();
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select * from studio where theater_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, theater_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Studio studio = new Studio();
                studio.setId(rs.getInt("id"));
                studio.setRow(rs.getInt("row"));
                studio.setCol(rs.getInt("col"));
                studio.setName(rs.getString("name"));
                studio.setTheater_id(rs.getInt("theater_id"));
                // 将该用户信息插入列表
                list.add(studio);
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
    public ArrayList<Studio> getAllStudio() {
        ArrayList<Studio> list = new ArrayList<Studio>();
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

            String sql = "select * from studio";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Studio studio = new Studio();
                studio.setId(rs.getInt("id"));
                studio.setRow(rs.getInt("row"));
                studio.setCol(rs.getInt("col"));
                studio.setName(rs.getString("name"));
                studio.setTheater_id(rs.getInt("theater_id"));
                // 将该用户信息插入列表
                list.add(studio);
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
    public ArrayList<Studio> getStudioByTheater(int page, int theater_id) {
        return null;
    }
}
