package edu.xupt.cs.se.test;

import edu.xupt.cs.se.dao.ManagerDAO;

/**
 * Created by zhoupan on 17-6-3.
 */
public class ManagerDAOTest {
    public static void main(String[] args) {
        ManagerDAO managerDAO = new ManagerDAO();
        System.out.println(managerDAO.getManagerByNumber("20000001").getTheater_id());
    }
}
