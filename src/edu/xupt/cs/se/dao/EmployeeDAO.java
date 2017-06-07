package edu.xupt.cs.se.dao;

import edu.xupt.cs.se.idao.Iemployee;
import edu.xupt.cs.se.model.Employee;
import edu.xupt.cs.se.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by zhoupan on 17-5-31.
 */
public class EmployeeDAO implements Iemployee {


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
    public boolean insert(Employee employee) {

        boolean rtu = false;
        if (employee == null) {
            return rtu;
        }
        //获取Connection
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "insert into employee(emp_no, theater_id, name, passwd, tel) VALUES (?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1,employee.getEmp_no());
            ps.setInt(2, employee.getTheater_id());
            ps.setString(3, employee.getName());
            ps.setString(4, employee.getPasswd());
            ps.setString(5, employee.getTel());
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
    public boolean delete(int employee_id) {
        boolean rtu = false;
        if (employee_id == 0) {
            return rtu;
        }
        //获取Connection
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "delete from employee where id=?;";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, employee_id);
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
    public boolean update(Employee employee) {
        boolean rtu = false;
        if (employee == null) {
            return rtu;
        }
        //获取Connection
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            //insert into employee(emp_no,theater_id,name,passwd,tel) VALUES (?,?,?,?,?)
            String sql = "update employee set emp_no=?,theater_id=?,name=?,passwd=?,tel=? where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, employee.getEmp_no());
            ps.setInt(2, employee.getTheater_id());
            ps.setString(3, employee.getName());
            ps.setString(4, employee.getPasswd());
            ps.setString(5, employee.getTel());
            ps.setInt(6, employee.getId());
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
    public Employee getEmployeeByID(int employee_id) {
        Employee rtu = null;
        if (employee_id <= 0) {
            return rtu;
        }
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //insert into employee(emp_no,theater_id,name,passwd,tel) VALUES (?,?,?,?,?)
            String sql = "select id,emp_no,theater_id,name,passwd,tel from employee where id =?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, employee_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                rtu = new Employee();
                rtu.setId(rs.getInt("id"));
                rtu.setEmp_no(rs.getString("emp_no"));
                rtu.setTheater_id(rs.getInt("theater_id"));
                rtu.setName(rs.getString("name"));
                rtu.setPasswd(rs.getString("passwd"));
                rtu.setTel(rs.getString("tel"));
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
    public Employee getEmployeeByNumber(String emp_no) {
        Employee rtu = null;
        if (null == emp_no) {
            return rtu;
        }
        if(emp_no.equals("")){
            return rtu;
        }
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //insert into employee(emp_no,theater_id,name,passwd,tel) VALUES (?,?,?,?,?)
            String sql = "select id,emp_no,theater_id,name,passwd,tel from employee where emp_no =?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, emp_no);
            rs = ps.executeQuery();
            while (rs.next()) {
                rtu = new Employee();
                rtu.setId(rs.getInt("id"));
                rtu.setEmp_no(rs.getString("emp_no"));
                rtu.setTheater_id(rs.getInt("theater_id"));
                rtu.setName(rs.getString("name"));
                rtu.setPasswd(rs.getString("passwd"));
                rtu.setTel(rs.getString("tel"));
            }
            return rtu;
        } catch (Exception e) {
            ConnectionManager.close(null, ps, conn);
            e.printStackTrace();
        } finally {
            return rtu;
        }
    }

    @Override
    public ArrayList<Employee> getEmployeeByTheater(int page, int theater_id) {

        if(theater_id <=0){
            return null;
        }

        currentPage = page;
        ArrayList<Employee> list = new ArrayList<Employee>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 获取记录总数
            String sql1 = "select count(id) as AllRecord from employee where theater_id = ?";
            conn = ConnectionManager.getInstance().getConnection();
            ps = conn.prepareStatement(sql1);
            ps.setInt(1,theater_id);
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
            String sql2 = "select * from employee where theater_id = ? limit ?, ?";
            ps = conn.prepareStatement(sql2);
            ps.setInt(1, theater_id);
            ps.setInt(2, PAGE_SIZE * (currentPage - 1));
            ps.setInt(3, PAGE_SIZE);
            rs = ps.executeQuery();
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setId(rs.getInt("id"));
                employee.setEmp_no(rs.getString("emp_no"));
                employee.setTheater_id(rs.getInt("theater_id"));
                employee.setName(rs.getString("name"));
                employee.setPasswd(rs.getString("passwd"));
                employee.setTel(rs.getString("tel"));
                // 将该用户信息插入列表
                list.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(rs, ps, conn);
            return list;
        }
    }

    @Override
    public ArrayList<Employee> getEmployeeByTheater(int theater_id) {
        if(theater_id <=0){
            return null;
        }
        ArrayList<Employee> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = ConnectionManager.getInstance().getConnection();
            String sql = "select * from employee where theater_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, theater_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setId(rs.getInt("id"));
                employee.setEmp_no(rs.getString("emp_no"));
                employee.setTheater_id(rs.getInt("theater_id"));
                employee.setName(rs.getString("name"));
                employee.setPasswd(rs.getString("passwd"));
                employee.setTel(rs.getString("tel"));
                // 将该用户信息插入列表
                list.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(rs, ps, conn);
            return list;
        }
    }

    @Override
    public ArrayList<Employee> getEmployeeByPage(int page) {
        currentPage = page;
        ArrayList<Employee> list = new ArrayList<Employee>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 获取记录总数
            String sql1 = "select count(id) as AllRecord from employee";
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
            String sql2 = "select * from employee limit ?, ?";
            ps = conn.prepareStatement(sql2);
            ps.setInt(1, PAGE_SIZE * (currentPage - 1));
            ps.setInt(2, PAGE_SIZE);
            rs = ps.executeQuery();
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setId(rs.getInt("id"));
                employee.setEmp_no(rs.getString("emp_no"));
                employee.setTheater_id(rs.getInt("theater_id"));
                employee.setName(rs.getString("name"));
                employee.setPasswd(rs.getString("passwd"));
                employee.setTel(rs.getString("tel"));
                // 将该用户信息插入列表
                list.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(rs, ps, conn);
            return list;
        }
    }

    @Override
    public ArrayList<Employee> getAllEmployee() {
        ArrayList<Employee> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = ConnectionManager.getInstance().getConnection();
            String sql = "select * from employee";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setId(rs.getInt("id"));
                employee.setEmp_no(rs.getString("emp_no"));
                employee.setTheater_id(rs.getInt("theater_id"));
                employee.setName(rs.getString("name"));
                employee.setPasswd(rs.getString("passwd"));
                employee.setTel(rs.getString("tel"));
                // 将该用户信息插入列表
                list.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(rs, ps, conn);
            return list;
        }
    }

    public boolean check(String emp_no, String passwd){
        if(null == emp_no || passwd == null){
            return false;
        }
        if(emp_no.equals("") || passwd.equals("")){
            return false;
        }
        Employee employee = getEmployeeByNumber(emp_no);

        if(employee == null){
            return false;
        }
        if(employee.getPasswd().equals(passwd)){
            return true;
        }
        return false;
    }
}

