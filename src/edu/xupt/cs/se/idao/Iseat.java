package edu.xupt.cs.se.idao;

import edu.xupt.cs.se.model.Seat;

import java.util.ArrayList;

/**
 * Created by zhoupan on 17-5-30.
 */
public interface Iseat {

    /*
    private int id;
    private int studio_id;
    private int row;
    private int col;
    private int status;
     */
    public boolean insert(Seat seat);

    public boolean delete(int seat_id);

    public boolean update(Seat seat);

    public Seat getSeatByID(int seat_id);

    public Seat getSeatByRowCol(int row, int col);

    public ArrayList<Seat> getSeatByPage(int page);

    public ArrayList<Seat> getSeatByStudio(int page, int studio_id);

    public ArrayList<Seat> getAllSeat();

    public ArrayList<Seat> getSeatByStudio(int studio_id);

}
