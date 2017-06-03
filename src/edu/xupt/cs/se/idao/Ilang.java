package edu.xupt.cs.se.idao;

import edu.xupt.cs.se.model.Lang;

import java.util.ArrayList;

/**
 * Created by zhoupan on 17-5-30.
 */
public interface Ilang {
    public boolean insert(Lang lang);

    public boolean delete(int lang_id);

    public boolean update(Lang lang);

    public Lang getLangByID(int lang_id);

    public ArrayList<Lang> getLangByPage(int page);

    public ArrayList<Lang> getAllLang();
}
