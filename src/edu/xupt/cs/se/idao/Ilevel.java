package edu.xupt.cs.se.idao;

import edu.xupt.cs.se.model.Level;

import java.util.ArrayList;

/**
 * Created by zhoupan on 17-5-30.
 */
public interface Ilevel {

    public boolean insert(Level level);

    public boolean delete(int level_id);

    public boolean update(Level lang);

    public Level getLevelByID(int level_id);

    public ArrayList<Level> getLevelByPage(int page);

    public ArrayList<Level> getAllLevel();

}
