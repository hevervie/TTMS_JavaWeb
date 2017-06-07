package edu.xupt.cs.se.dao;

import edu.xupt.cs.se.idao.Itype;
import edu.xupt.cs.se.model.Employee;
import edu.xupt.cs.se.model.Level;
import edu.xupt.cs.se.model.Type;
import edu.xupt.cs.se.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by zhoupan on 17-5-31.
 */
public class TypeDAO implements Itype {
    @Override
    public boolean insert(Type type) {
        return false;
    }

    @Override
    public boolean delete(int type_id) {
        return false;
    }

    @Override
    public boolean update(Type type) {
        return false;
    }

    @Override
    public Type getTypeByID(int type_id) {
        Type rtu = null;
        if (type_id <= 0) {
            return rtu;
        }
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //insert into employee(emp_no,theater_id,name,passwd,tel) VALUES (?,?,?,?,?)
            String sql = "select * from type where id =?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, type_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                rtu = new Type();
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
    public ArrayList<Type> getTypeByPage(int page) {
        return null;
    }

    @Override
    public ArrayList<Type> getAllType() {
        ArrayList<Type> list = new ArrayList<>();
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

            String sql = "select * from type";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Type type = new Type();
                type.setId(rs.getInt("id"));
                type.setType(rs.getString("type"));
                // 将该用户信息插入列表
                list.add(type);
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
