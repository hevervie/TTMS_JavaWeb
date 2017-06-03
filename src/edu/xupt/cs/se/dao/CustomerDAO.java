package edu.xupt.cs.se.dao;

import edu.xupt.cs.se.idao.Icustomer;
import edu.xupt.cs.se.model.Customer;

import java.util.ArrayList;

/**
 * Created by zhoupan on 17-5-31.
 */
public class CustomerDAO implements Icustomer {
    @Override
    public boolean insert(Customer customer) {
        return false;
    }

    @Override
    public boolean delete(int customer_id) {
        return false;
    }

    @Override
    public boolean update(Customer customer) {
        return false;
    }

    @Override
    public Customer getCustomerByID(int customer_id) {
        return null;
    }

    @Override
    public ArrayList<Customer> getCustomerByPage(int page) {
        return null;
    }

    @Override
    public ArrayList<Customer> getAllCustomer() {
        return null;
    }

    @Override
    public ArrayList<Customer> getCustomerByName(int page, String customer_name) {
        return null;
    }

    @Override
    public ArrayList<Customer> getCustomerByName(String customer_name) {
        return null;
    }

    @Override
    public ArrayList<Customer> getCustomerByTel(int page, String tel) {
        return null;
    }

    @Override
    public ArrayList<Customer> getCustomerByTel(String tel) {
        return null;
    }

}
