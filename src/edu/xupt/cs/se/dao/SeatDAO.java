package edu.xupt.cs.se.dao;

import edu.xupt.cs.se.idao.Iseat;
import edu.xupt.cs.se.model.Seat;
import edu.xupt.cs.se.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by zhoupan on 17-5-31.
 */
public class SeatDAO implements Iseat {
    @Override
    public boolean insert(Seat seat) {
        boolean rtu = false;
        if (seat == null) {
            return rtu;
        }
        //获取Connection
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "insert into seat(studio_id, row, col,status) VALUES (?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, seat.getStudio_id());
            ps.setInt(2, seat.getRow());
            ps.setInt(3, seat.getCol());
            ps.setInt(4, seat.getStatus());
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
    public boolean delete(int seat_id) {
        boolean rtu = false;
        if (seat_id <= 0) {
            return rtu;
        }
        //获取Connection
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "delete from seat where id=?;";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, seat_id);
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
    public boolean update(Seat seat) {
        return false;
    }

    @Override
    public Seat getSeatByID(int seat_id) {
        Seat rtu = null;

        if (seat_id <= 0) {
            return rtu;
        }
        //获取Connection
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "select *  from seat where id = ?;";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, seat_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                rtu = new Seat();
                rtu.setId(rs.getInt("id"));
                rtu.setRow(rs.getInt("row"));
                rtu.setCol(rs.getInt("col"));
                rtu.setStatus(rs.getInt("status"));
                rtu.setStudio_id(rs.getInt("studio_id"));
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
    public ArrayList<Seat> getSeatByPage(int page) {
        return null;
    }

    @Override
    public Seat getSeatByRowCol(int row, int col) {
        Seat rtu = null;

        if (row <= 0 || col <= 0) {
            return rtu;
        }
        //获取Connection
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "select *  from seat where row=? and col = ?;";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, row);
            ps.setInt(2,col);
            rs = ps.executeQuery();
            while (rs.next()) {
                rtu = new Seat();
                rtu.setId(rs.getInt("id"));
                rtu.setRow(rs.getInt("row"));
                rtu.setCol(rs.getInt("col"));
                rtu.setStatus(rs.getInt("status"));
                rtu.setStudio_id(rs.getInt("studio_id"));
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
    public ArrayList<Seat> getSeatByStudio(int page, int studio_id) {
        return null;
    }

    @Override
    public ArrayList<Seat> getAllSeat() {
        return null;
    }

    @Override
    public ArrayList<Seat> getSeatByStudio(int studio_id) {
        if (studio_id <= 0) {
            return null;
        }
        ArrayList<Seat> list = new ArrayList<>();
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select * from seat where studio_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, studio_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Seat seat = new Seat();
                seat.setId(rs.getInt("id"));
                seat.setStudio_id(rs.getInt("studio_id"));
                seat.setRow(rs.getInt("row"));
                seat.setCol(rs.getInt("col"));
                seat.setStatus(rs.getInt("status"));
                // 将该用户信息插入列表
                list.add(seat);
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
