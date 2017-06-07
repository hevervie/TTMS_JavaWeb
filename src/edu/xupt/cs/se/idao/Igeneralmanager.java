package edu.xupt.cs.se.idao;

import edu.xupt.cs.se.model.Generalmanager;

import java.util.ArrayList;

/**
 * Created by zhoupan on 17-5-30.
 */
public interface Igeneralmanager {
    /*
    private int id;
    private String emp_no;
    private String name;
    private String passwd;
     */

    public boolean insert(Generalmanager generalmanager);

    public boolean delete(int genera_id);

    public boolean update(Generalmanager generalmanager);

    public Generalmanager getGeneralmanagerByID(int genera_id);

    public Generalmanager getGeneralmanagerByNumber(String emp_no);

    public ArrayList<Generalmanager> getGeneralmanagerByPage(int page);

    public ArrayList<Generalmanager> getAllGeneralmanager();
}
