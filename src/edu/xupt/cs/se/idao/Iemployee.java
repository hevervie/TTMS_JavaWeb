package edu.xupt.cs.se.idao;

import edu.xupt.cs.se.model.Employee;

import java.util.ArrayList;

/**
 * Created by zhoupan on 17-5-30.
 */
public interface Iemployee {

    /*
    private int id;
    private String emp_no;
    private int theater_id;
    private String name;
    private String passwd;
    private String tel;
     */
    public boolean insert(Employee employee);

    public boolean delete(int employee_id);

    public boolean update(Employee employee);

    public Employee getEmployeeByID(int employee_id);

    public Employee getEmployeeByNumber(String emp_no);

    public ArrayList<Employee> getEmployeeByTheater(int page, int theater_id);

    public ArrayList<Employee> getEmployeeByTheater(int theater_id);

    public ArrayList<Employee> getEmployeeByPage(int page);

    public ArrayList<Employee> getAllEmployee();


}
