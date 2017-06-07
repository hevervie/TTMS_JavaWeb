package edu.xupt.cs.se.dao;

import edu.xupt.cs.se.idao.Iticket;
import edu.xupt.cs.se.model.Schedule;
import edu.xupt.cs.se.model.Ticket;
import edu.xupt.cs.se.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by zhoupan on 17-5-31.
 */
public class TicketDAO implements Iticket{
    @Override
    public boolean insert(Ticket ticket) {
        boolean rtu = false;
        if (ticket == null) {
            return rtu;
        }
        //获取Connection
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        try {

            String sql = "insert into ticket(seat_id, schedule_id, play_id, price, status, locktime) VALUES (?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,ticket.getSeat_id());
            ps.setInt(2,ticket.getSchedule_id());
            ps.setInt(3,ticket.getPlay_id());
            ps.setFloat(4,ticket.getPrice());
            ps.setInt(5,ticket.getStatus());
            ps.setString(6,ticket.getLocktime());
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
    public boolean delete(int ticket_id) {
        boolean rtu = false;
        if (ticket_id <= 0) {
            return rtu;
        }
        //获取Connection
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "delete from ticket where id=?;";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, ticket_id);
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
    public boolean update(Ticket ticket) {
        boolean rtu = false;
        if (ticket == null) {
            return rtu;
        }
        //获取Connection
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "UPDATE ticket set seat_id= ?, schedule_id=?, play_id=?, price=?, status=?, locktime= ? WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,ticket.getSeat_id());
            ps.setInt(2,ticket.getSchedule_id());
            ps.setInt(3,ticket.getPlay_id());
            ps.setFloat(4,ticket.getPrice());
            ps.setInt(5,ticket.getStatus());
            ps.setString(6,ticket.getLocktime());
            ps.setInt(7,ticket.getId());
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
    public Ticket getTicketByID(int ticket_id) {
        return null;
    }

    @Override
    public ArrayList<Ticket> getTicketByPage(int page) {
        return null;
    }

    @Override
    public ArrayList<Ticket> getTicketBySchedule(int schedule_id) {
        ArrayList<Ticket> list = new ArrayList<>();
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select * from ticket where schedule_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, schedule_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Ticket ticket = new Ticket();
                ticket.setId(rs.getInt("id"));
                ticket.setSchedule_id(rs.getInt("schedule_id"));
                ticket.setSeat_id(rs.getInt("seat_id"));
                ticket.setPlay_id(rs.getInt("play_id"));
                ticket.setPrice(rs.getFloat("price"));
                ticket.setStatus(rs.getInt("status"));
                ticket.setLocktime(rs.getString("locktime"));
                // 将该用户信息插入列表
                list.add(ticket);
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
    public ArrayList<Ticket> getTicketByPlay(int play_id) {
        return null;
    }

    @Override
    public ArrayList<Ticket> getAllTicket() {
        return null;
    }

    @Override
    public Ticket getTicketBySeat(int seat_id) {
        Ticket rtu = null;

        if (seat_id <= 0) {
            return rtu;
        }
        //获取Connection
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "select *  from ticket where seat_id=?;";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, seat_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                rtu = new Ticket();
                rtu.setId(rs.getInt("id"));
                rtu.setSeat_id(rs.getInt("seat_id"));
                rtu.setSchedule_id(rs.getInt("schedule_id"));
                rtu.setPlay_id(rs.getInt("play_id"));
                rtu.setPrice(rs.getFloat("price"));
                rtu.setStatus(rs.getInt("status"));
                rtu.setLocktime(rs.getString("locktime"));
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
    public ArrayList<Ticket> getTicketBySchedule(int page, int schedule_id) {
        return null;
    }

    @Override
    public ArrayList<Ticket> getTicketByPlay(int page, int play_id) {
        return null;
    }
}
