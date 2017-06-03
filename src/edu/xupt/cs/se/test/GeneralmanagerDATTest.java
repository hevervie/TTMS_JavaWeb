package edu.xupt.cs.se.test;

import edu.xupt.cs.se.dao.GeneralmanagerDAO;

/**
 * Created by zhoupan on 17-6-3.
 */
public class GeneralmanagerDATTest {
    public static void main(String[] args) {
        GeneralmanagerDAO generalmanagerDAO = new GeneralmanagerDAO();
        //System.out.println(generalmanagerDAO.getGeneralmanagerByID(1).getEmp_no());
        System.out.println(generalmanagerDAO.getGeneralmanagerByNumber("10000001").getEmp_no());
    }

}
