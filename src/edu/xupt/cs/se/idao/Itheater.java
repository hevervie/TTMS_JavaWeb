package edu.xupt.cs.se.idao;

import edu.xupt.cs.se.model.Theater;

import java.util.ArrayList;

/**
 * Created by zhoupan on 17-5-30.
 */
public interface Itheater {

    /*
    private  int id;
    private String name;
    private int studio_number;
    private String addr;
     */

    public boolean insert(Theater theater);

    public boolean delete(int theater_id);

    public boolean update(Theater theater);

    public Theater getTheaterByID(int theater_id);

    public ArrayList<Theater> getTheaterByPage(int page);

    public ArrayList<Theater> getAllTheater();

}
