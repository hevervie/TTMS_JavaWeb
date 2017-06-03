package edu.xupt.cs.se.idao;

import edu.xupt.cs.se.model.Studio;

import java.util.ArrayList;

/**
 * Created by zhoupan on 17-5-30.
 */
public interface Istudio {

    /*
    private int id;
    private int theater_id;
    private String name;
    private int row;
    private int col;
     */
    public boolean insert(Studio studio);

    public boolean delete(int studio_id);

    public boolean update(Studio studio);

    public Studio getStudioByID(int studio_id);

    public ArrayList<Studio> getStudioByTheater(int page, int theater_id);

    public ArrayList<Studio> getStudioByPage(int page);

    public ArrayList<Studio> getStudioByTheater(int theater_id);

    public ArrayList<Studio> getAllStudio();
}
