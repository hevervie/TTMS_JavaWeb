package edu.xupt.cs.se.test;

import edu.xupt.cs.se.dao.StudioDAO;

/**
 * Created by zhoupan on 17-6-3.
 */
public class StudioDATTest {
    public static void main(String[] args) {
        System.out.println(new StudioDAO().delete(5));
    }
}
