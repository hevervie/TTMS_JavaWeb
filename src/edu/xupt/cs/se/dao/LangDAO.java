package edu.xupt.cs.se.dao;

import edu.xupt.cs.se.idao.Ilang;
import edu.xupt.cs.se.model.Lang;
import edu.xupt.cs.se.model.Lang;
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
public class LangDAO implements Ilang {
    @Override
    public boolean insert(Lang lang) {
        return false;
    }

    @Override
    public boolean delete(int lang_id) {
        return false;
    }

    @Override
    public boolean update(Lang lang) {
        return false;
    }

    @Override
    public Lang getLangByID(int lang_id) {
        Lang rtu = null;
        if (lang_id <= 0) {
            return rtu;
        }
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //insert into employee(emp_no,theater_id,name,passwd,tel) VALUES (?,?,?,?,?)
            String sql = "select * from lang where id =?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, lang_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                rtu = new Lang();
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
    public ArrayList<Lang> getLangByPage(int page) {
        return null;
    }

    @Override
    public ArrayList<Lang> getAllLang() {
        ArrayList<Lang> list = new ArrayList<>();
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

            String sql = "select * from lang";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Lang Lang = new Lang();
                Lang.setId(rs.getInt("id"));
                Lang.setType(rs.getString("type"));
                // 将该用户信息插入列表
                list.add(Lang);
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