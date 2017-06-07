package edu.xupt.cs.se.idao;


import edu.xupt.cs.se.model.Customer;

import java.util.ArrayList;

/**
 * Created by zhoupan on 17-5-30.
 */
public interface Icustomer {
    /*
    private int id;
    private String name;
    private String tel;
     */

    public boolean insert(Customer customer);

    public boolean delete(int customer_id);

    public boolean update(Customer customer);

    public Customer getCustomerByID(int customer_id);

    public ArrayList<Customer> getCustomerByPage(int page);

    public ArrayList<Customer> getAllCustomer();

    public ArrayList<Customer> getCustomerByName(int page, String customer_name);

    public ArrayList<Customer> getCustomerByName(String customer_name);

    public ArrayList<Customer> getCustomerByTel(int page, String tel);

    public ArrayList<Customer> getCustomerByTel(String tel);
}
