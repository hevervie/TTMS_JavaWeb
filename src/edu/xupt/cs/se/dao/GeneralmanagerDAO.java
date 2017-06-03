package edu.xupt.cs.se.dao;

import edu.xupt.cs.se.idao.Igeneralmanager;
import edu.xupt.cs.se.model.Employee;
import edu.xupt.cs.se.model.Generalmanager;
import edu.xupt.cs.se.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by zhoupan on 17-5-31.
 */
public class GeneralmanagerDAO implements Igeneralmanager{

    /***
     * 分页相关的方法
     */
    /*每页显示的数据量*/
    private static final int PAGE_SIZE = 10;
    /*数据库中的数据量*/
    private int allCount;
    /*总的分页数*/
    private int allPageCount;
    /*当前页面*/
    private int currentPage;

    public static int getPageSize() {
        return PAGE_SIZE;
    }

    public int getAllCount() {
        return allCount;
    }

    public void setAllCount(int allCount) {
        this.allCount = allCount;
    }

    public int getAllPageCount() {
        return allPageCount;
    }

    public void setAllPageCount(int allPageCount) {
        this.allPageCount = allPageCount;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    @Override
    public boolean insert(Generalmanager generalmanager) {
        boolean rtu = false;
        if (generalmanager == null) {
            return rtu;
        }
        //获取Connection
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            //INSERT into generalmanager VALUES (1,'10000001','Boss','123456');

            String sql = "insert into generalmanager(emp_no,name,passwd) VALUES (?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, generalmanager.getEmp_no());
            ps.setString(2, generalmanager.getName());
            ps.setString(3, generalmanager.getPasswd());
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
    public boolean delete(int genera_id) {
        boolean rtu = false;
        if (genera_id == 0) {
            return rtu;
        }
        //获取Connection
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "delete from generalmanager where id=?;";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, genera_id);
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
    public boolean update(Generalmanager generalmanager) {
        boolean rtu = false;
        if (generalmanager == null) {
            return rtu;
        }
        //获取Connection
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            //insert into generalmanager(emp_no,theater_id,name,passwd,tel) VALUES (?,?,?,?,?)
            String sql = "update generalmanager set emp_no=?,name=?,passwd=? where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, generalmanager.getEmp_no());
            ps.setString(2, generalmanager.getName());
            ps.setString(3, generalmanager.getPasswd());
            ps.setInt(4, generalmanager.getId());
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
    public Generalmanager getGeneralmanagerByID(int genera_id) {
        Generalmanager rtu = null;
        if (genera_id <= 0) {
            return rtu;
        }
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //insert into employee(emp_no,theater_id,name,passwd,tel) VALUES (?,?,?,?,?)
            String sql = "select id,emp_no,name,passwd from generalmanager where id =?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, genera_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                rtu = new Generalmanager();
                rtu.setId(rs.getInt("id"));
                rtu.setEmp_no(rs.getString("emp_no"));
                rtu.setName(rs.getString("name"));
                rtu.setPasswd(rs.getString("passwd"));
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
    public ArrayList<Generalmanager> getGeneralmanagerByPage(int page) {
        return null;
    }

    @Override
    public ArrayList<Generalmanager> getAllGeneralmanager() {
        return null;
    }

    @Override
    public Generalmanager getGeneralmanagerByNumber(String emp_no) {
        Generalmanager rtu = null;

        if(emp_no == null){
            return null;
        }

        if(emp_no.equals("")){
            return null;
        }

        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //insert into employee(emp_no,theater_id,name,passwd,tel) VALUES (?,?,?,?,?)
            String sql = "select id,emp_no,name,passwd from generalmanager where emp_no =?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, emp_no);
            rs = ps.executeQuery();
            while (rs.next()) {
                rtu = new Generalmanager();
                rtu.setId(rs.getInt("id"));
                rtu.setEmp_no(rs.getString("emp_no"));
                rtu.setName(rs.getString("name"));
                rtu.setPasswd(rs.getString("passwd"));
            }
            return rtu;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(null, ps, conn);
            return rtu;
        }
    }
    public boolean check(String emp_no, String passwd){
        if(null == emp_no || passwd == null){
            return false;
        }
        if(emp_no.equals("") || passwd.equals("")){
            return false;
        }
        Generalmanager generalmanager = getGeneralmanagerByNumber(emp_no);

        if(generalmanager == null){
            return false;
        }
        if(generalmanager.getPasswd().equals(passwd)){
            return true;
        }
        return false;
    }
}
