package edu.xupt.cs.se.dao;

import edu.xupt.cs.se.idao.Iseat;
import edu.xupt.cs.se.model.Seat;

import java.util.ArrayList;

/**
 * Created by zhoupan on 17-5-31.
 */
public class SeatDAO implements Iseat {
    @Override
    public boolean insert(Seat seat) {
        return false;
    }

    @Override
    public boolean delete(int seat_id) {
        return false;
    }

    @Override
    public boolean update(Seat seat) {
        return false;
    }

    @Override
    public Seat getSeatByID(int seat_id) {
        return null;
    }

    @Override
    public ArrayList<Seat> getSeatByPage(int page) {
        return null;
    }

    @Override
    public Seat getSeatByRowCol(int row, int col) {
        return null;
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
        return null;
    }
}
