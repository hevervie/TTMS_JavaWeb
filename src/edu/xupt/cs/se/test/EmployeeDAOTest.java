package edu.xupt.cs.se.test;

import edu.xupt.cs.se.dao.EmployeeDAO;

/**
 * Created by zhoupan on 17-6-1.
 */
public class EmployeeDAOTest {
    public static void main(String[] args) {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        //System.out.println(employeeDAO.check("30000001","123456"));
        System.out.println(employeeDAO.getEmployeeByID(2).getEmp_no());
    }
}
