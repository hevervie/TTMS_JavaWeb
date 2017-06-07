package edu.xupt.cs.se.dao;

import edu.xupt.cs.se.idao.Imanager;
import edu.xupt.cs.se.model.Employee;
import edu.xupt.cs.se.model.Manager;
import edu.xupt.cs.se.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by zhoupan on 17-5-31.
 */
public class ManagerDAO implements Imanager {


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
    public boolean insert(Manager manager) {
        boolean rtu = false;
        if (manager == null) {
            return rtu;
        }
        //获取Connection
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            //INSERT into manager VALUES (1,'20000001',1,'Box','123456');
            String sql = "insert into manager(emp_no,theater_id,name,passwd) VALUES (?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, manager.getEmp_no());
            ps.setInt(2, manager.getTheater_id());
            ps.setString(3, manager.getName());
            ps.setString(4, manager.getPasswd());
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
    public boolean delete(int manager_id) {
        boolean rtu = false;
        if (manager_id == 0) {
            return rtu;
        }
        //获取Connection
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            //INSERT into manager VALUES (1,'20000001',1,'Box','123456');

            String sql = "delete from manager where id=?;";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, manager_id);
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
    public boolean update(Manager manager) {
        boolean rtu = false;
        if (manager == null) {
            return rtu;
        }
        //获取Connection
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            //INSERT into manager VALUES (1,'20000001',1,'Box','123456');
            String sql = "update manager set emp_no=?,theater_id=?,name=?,passwd=? where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, manager.getEmp_no());
            ps.setInt(2, manager.getTheater_id());
            ps.setString(3, manager.getName());
            ps.setString(4, manager.getPasswd());
            ps.setInt(5, manager.getId());
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
    public Manager getManagerByID(int manager_id) {
        Manager rtu = null;
        if (manager_id <= 0) {
            return rtu;
        }
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //insert into manager(emp_no,theater_id,name,passwd ) VALUES (?,?,?,?,?)
            String sql = "select id,emp_no,theater_id,name,passwd from manager where id =?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, manager_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                rtu = new Manager();
                rtu.setId(rs.getInt("id"));
                rtu.setEmp_no(rs.getString("emp_no"));
                rtu.setTheater_id(rs.getInt("theater_id"));
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
    public ArrayList<Manager> getManagerByPage(int page) {
        currentPage = page;
        ArrayList<Manager> list = new ArrayList<Manager>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 获取记录总数
            String sql1 = "select count(id) as AllRecord from manager";
            conn = ConnectionManager.getInstance().getConnection();
            ps = conn.prepareStatement(sql1);
            rs = ps.executeQuery();
            if (rs.next())
                allCount = rs.getInt("AllRecord");
            rs.close();
            ps.close();
            // 记算总页数
            allPageCount = (allCount + PAGE_SIZE - 1) / PAGE_SIZE;
            // 如果当前页数大于总页数,则赋值为总页数
            if (allPageCount > 0 && currentPage > allPageCount) {
                currentPage = allPageCount;
            }
            // 获取第currentPage页数据
            String sql2 = "select * from manager limit ?, ?";
            ps = conn.prepareStatement(sql2);
            ps.setInt(1, PAGE_SIZE * (currentPage - 1));
            ps.setInt(2, PAGE_SIZE);
            rs = ps.executeQuery();
            while (rs.next()) {
                Manager manager = new Manager();
                manager.setId(rs.getInt("id"));
                manager.setEmp_no(rs.getString("emp_no"));
                manager.setTheater_id(rs.getInt("theater_id"));
                manager.setName(rs.getString("name"));
                manager.setPasswd(rs.getString("passwd"));
                // 将该用户信息插入列表
                list.add(manager);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(rs, ps, conn);
            return list;
        }
    }

    @Override
    public ArrayList<Manager> getAllManager() {
        ArrayList<Manager> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = ConnectionManager.getInstance().getConnection();
            String sql = "select * from manager";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Manager manager = new Manager();
                manager.setId(rs.getInt("id"));
                manager.setEmp_no(rs.getString("emp_no"));
                manager.setTheater_id(rs.getInt("theater_id"));
                manager.setName(rs.getString("name"));
                manager.setPasswd(rs.getString("passwd"));
                list.add(manager);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(rs, ps, conn);
            return list;
        }
    }

    @Override
    public Manager getManagerByNumber(String emp_no) {
        Manager rtu = null;
        if (null == emp_no) {
            return rtu;
        }
        if (emp_no.equals("")) {
            return rtu;
        }
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //insert into Manager(emp_no,theater_id,name,passwd,tel) VALUES (?,?,?,?,?)
            String sql = "select id,emp_no,theater_id,name,passwd from manager where emp_no =?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, emp_no);
            rs = ps.executeQuery();
            while (rs.next()) {
                rtu = new Manager();
                rtu.setId(rs.getInt("id"));
                rtu.setEmp_no(rs.getString("emp_no"));
                rtu.setTheater_id(rs.getInt("theater_id"));
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
    public ArrayList<Manager> getManagerByTheater(int theater_id) {
        if (theater_id <= 0) {
            return null;
        }
        ArrayList<Manager> list = new ArrayList<>();
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //insert into Manager(emp_no,theater_id,name,passwd,tel) VALUES (?,?,?,?,?)
            String sql = "select id,emp_no,theater_id,name,passwd from manager where theater_id =?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, theater_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Manager manager = null;
                manager = new Manager();
                manager.setId(rs.getInt("id"));
                manager.setEmp_no(rs.getString("emp_no"));
                manager.setTheater_id(rs.getInt("theater_id"));
                manager.setName(rs.getString("name"));
                manager.setPasswd(rs.getString("passwd"));
                list.add(manager);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(null, ps, conn);
            return list;
        }
    }

    @Override
    public ArrayList<Manager> getManagerByTheater(int page, int theater_id) {
        return null;
    }

    public boolean check(String emp_no, String passwd) {
        if (null == emp_no || passwd == null) {
            return false;
        }
        if (emp_no.equals("") || passwd.equals("")) {
            return false;
        }
        Manager manager = getManagerByNumber(emp_no);

        if (manager == null) {
            return false;
        }
        if (manager.getPasswd().equals(passwd)) {
            return true;
        }
        return false;
    }
}
