package edu.xupt.cs.se.idao;

import edu.xupt.cs.se.model.Play;

import java.util.ArrayList;

/**
 * Created by zhoupan on 17-5-30.
 */
public interface Iplay {

    /*
    private int id;
    private String name;
    private int type_id;
    private int lang_id;
    private int level_id;
    private float score;
    private String introd;
    private String image_url;
    private int length;
    private float price;
    private int status;
     */

    public boolean insert(Play play);

    public boolean delete(int play_id);

    public boolean update(Play play);

    public Play getPlayByID(int play_id);

    public ArrayList<Play> getPlayByType(int page, int type_id);

    public ArrayList<Play> getPlayByType(int type_id);

    public ArrayList<Play> getPlayByLang(int page, int lang_id);

    public ArrayList<Play> getPlayByLang(int lang_id);

    public ArrayList<Play> getPlayByLevel(int page, int level_id);

    public ArrayList<Play> getPlayByLevel(int level_id);

    public ArrayList<Play> getPlayByName(int page, String name);

    public ArrayList<Play> getPlayByName(String name);

    public ArrayList<Play> getPlayByPage(int page);

    public ArrayList<Play> getAllPlay();

}
