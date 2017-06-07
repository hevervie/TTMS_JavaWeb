package edu.xupt.cs.se.dao;

import edu.xupt.cs.se.idao.Ibill;
import edu.xupt.cs.se.model.Bill;
import edu.xupt.cs.se.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by zhoupan on 17-5-31.
 */
public class BillDAO implements Ibill {

    @Override
    public boolean insert(Bill bill) {
        boolean rtu = false;
        if (bill == null) {
            return rtu;
        }
        //获取Connection
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "insert into bill(customer_id, ticket_id, emp_id, play_id, price, sale_time) VALUES (?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,bill.getCustomer_id());
            ps.setInt(2,bill.getTicket_id());
            ps.setInt(3,bill.getEmp_id());
            ps.setInt(4,bill.getPlay_id());
            ps.setFloat(5,bill.getPrice());
            ps.setString(6,bill.getSale_time());
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
    public boolean delete(int bill_id) {
        return false;
    }

    @Override
    public boolean update(Bill bill) {
        return false;
    }

    @Override
    public Bill getBillByID(int bill_id) {
        return null;
    }

    @Override
    public ArrayList<Bill> getBillByPage(int page) {
        return null;
    }

    @Override
    public ArrayList<Bill> getAllBill() {
        ArrayList<Bill> list = new ArrayList<>();
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

            String sql = "select * from bill";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Bill bill = new Bill();
                bill.setId(rs.getInt("id"));
                bill.setCustomer_id(rs.getInt("customer_id"));
                bill.setTicket_id(rs.getInt("ticket_id"));
                bill.setEmp_id(rs.getInt("emp_id"));
                bill.setPlay_id(rs.getInt("play_id"));
                bill.setPrice(rs.getFloat("price"));
                bill.setSale_time(rs.getString("sale_time"));
                // 将该用户信息插入列表
                list.add(bill);
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
    public ArrayList<Bill> getBillByCustomer(int customer_id) {
        return null;
    }

    @Override
    public ArrayList<Bill> getBillByCustomer(int page, int customer_id) {
        return null;
    }

    @Override
    public ArrayList<Bill> getBillByEmployee(int emp_id) {
        if(emp_id <=0){
            return null;
        }
        ArrayList<Bill> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = ConnectionManager.getInstance().getConnection();
            String sql = "select * from bill where emp_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, emp_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Bill bill = new Bill();
                bill.setId(rs.getInt("id"));
                bill.setCustomer_id(rs.getInt("customer_id"));
                bill.setTicket_id(rs.getInt("ticket_id"));
                bill.setEmp_id(rs.getInt("emp_id"));
                bill.setPlay_id(rs.getInt("play_id"));
                bill.setPrice(rs.getFloat("price"));
                bill.setSale_time(rs.getString("sale_time"));
                // 将该用户信息插入列表
                list.add(bill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(rs, ps, conn);
            return list;
        }
    }

    @Override
    public ArrayList<Bill> getBillByEmployee(int page, int emp_id) {
        return null;
    }

    @Override
    public ArrayList<Bill> getBillByPaly(int play_id) {
        if(play_id<=0){
            return null;
        }
        ArrayList<Bill> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = ConnectionManager.getInstance().getConnection();
            String sql = "select * from bill where play_id= ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, play_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Bill bill = new Bill();
                bill.setId(rs.getInt("id"));
                bill.setCustomer_id(rs.getInt("customer_id"));
                bill.setTicket_id(rs.getInt("ticket_id"));
                bill.setEmp_id(rs.getInt("emp_id"));
                bill.setPlay_id(rs.getInt("play_id"));
                bill.setPrice(rs.getFloat("price"));
                bill.setSale_time(rs.getString("sale_time"));
                // 将该用户信息插入列表
                list.add(bill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(rs, ps, conn);
            return list;
        }
    }

    @Override
    public ArrayList<Bill> getBillByPaly(int page, int play_id) {
        return null;
    }
}
