package edu.xupt.cs.se.dao;

import edu.xupt.cs.se.idao.Itheater;
import edu.xupt.cs.se.model.Theater;

import java.util.ArrayList;

/**
 * Created by zhoupan on 17-5-31.
 */
public class TheaterDAO implements Itheater {
    @Override
    public boolean insert(Theater theater) {
        return false;
    }

    @Override
    public boolean delete(int theater_id) {
        return false;
    }

    @Override
    public boolean update(Theater theater) {
        return false;
    }

    @Override
    public Theater getTheaterByID(int theater_id) {
        return null;
    }

    @Override
    public ArrayList<Theater> getTheaterByPage(int page) {
        return null;
    }

    @Override
    public ArrayList<Theater> getAllTheater() {
        return null;
    }


}
