package edu.xupt.cs.se.idao;

import edu.xupt.cs.se.model.Type;

import java.util.ArrayList;

/**
 * Created by zhoupan on 17-5-30.
 */
public interface Itype {

    /*
    private int id;
    private String type;
     */

    public boolean insert(Type type);

    public boolean delete(int type_id);

    public boolean update(Type type);

    public Type getTypeByID(int type_id);

    public ArrayList<Type> getTypeByPage(int page);

    public ArrayList<Type> getAllType();
}
