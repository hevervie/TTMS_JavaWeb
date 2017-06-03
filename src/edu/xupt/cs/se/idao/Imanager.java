package edu.xupt.cs.se.idao;

import edu.xupt.cs.se.model.Manager;

import java.util.ArrayList;

/**
 * Created by zhoupan on 17-5-30.
 */
public interface Imanager {
    /*
    private int id;
    private String emp_no;
    private int theater_id;
    private String name;
    private String passwd;
     */

    public boolean insert(Manager manager);

    public boolean delete(int manager_id);

    public boolean update(Manager manager);

    public Manager getManagerByID(int manager_id);

    public Manager getManagerByNumber(String emp_no);

    public ArrayList<Manager> getManagerByTheater(int theater_id);

    public ArrayList<Manager> getManagerByTheater(int page, int theater_id);

    public ArrayList<Manager> getManagerByPage(int page);

    public ArrayList<Manager> getAllManager();

}
