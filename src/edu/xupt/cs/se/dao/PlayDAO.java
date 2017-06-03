package edu.xupt.cs.se.dao;

import edu.xupt.cs.se.idao.Iplay;
import edu.xupt.cs.se.model.Play;
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
public class PlayDAO implements Iplay {
    @Override
    public boolean insert(Play play) {
        return false;
    }

    @Override
    public boolean delete(int play_id) {
        boolean rtu = false;
        if (play_id <= 0) {
            return rtu;
        }
        //获取Connection
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "delete from play where id=?;";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, play_id);
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
    public boolean update(Play play) {
        return false;
    }

    @Override
    public Play getPlayByID(int play_id) {
        return null;
    }

    @Override
    public ArrayList<Play> getPlayByPage(int page) {
        return null;
    }

    @Override
    public ArrayList<Play> getAllPlay() {
        ArrayList<Play> list = new ArrayList<>();
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

            String sql = "select * from play";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Play play = new Play();
                play.setId(rs.getInt("id"));
                play.setName(rs.getString("name"));
                play.setType_id(rs.getInt("type_id"));
                play.setLang_id(rs.getInt("lang_id"));
                play.setLevel_id(rs.getInt("level_id"));
                play.setIntrod(rs.getString("introd"));
                play.setImage_url(rs.getString("image_url"));
                play.setLength(rs.getInt("length"));
                play.setPrice(rs.getFloat("price"));
                play.setScore(rs.getFloat("score"));
                play.setStatus(rs.getInt("status"));
                // 将该用户信息插入列表
                list.add(play);
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
    public ArrayList<Play> getPlayByType(int page, int type_id) {
        return null;
    }

    @Override
    public ArrayList<Play> getPlayByType(int type_id) {
        return null;
    }

    @Override
    public ArrayList<Play> getPlayByLang(int page, int lang_id) {
        return null;
    }

    @Override
    public ArrayList<Play> getPlayByLang(int lang_id) {
        return null;
    }

    @Override
    public ArrayList<Play> getPlayByLevel(int page, int level_id) {
        return null;
    }

    @Override
    public ArrayList<Play> getPlayByLevel(int level_id) {
        return null;
    }

    @Override
    public ArrayList<Play> getPlayByName(int page, String name) {
        return null;
    }

    @Override
    public ArrayList<Play> getPlayByName(String name) {
        return null;
    }
}
