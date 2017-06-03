package edu.xupt.cs.se.dao;

import edu.xupt.cs.se.idao.Ibill;
import edu.xupt.cs.se.model.Bill;

import java.util.ArrayList;

/**
 * Created by zhoupan on 17-5-31.
 */
public class BillDAO implements Ibill {

    @Override
    public boolean insert(Bill bill) {
        return false;
    }

    @Override
    public boolean delete(int bill_id) {
        return false;
    }

    @Override
    public boolean update(Bill bill) {
        return false;
    }

    @Override
    public Bill getBillByID(int bill_id) {
        return null;
    }

    @Override
    public ArrayList<Bill> getBillByPage(int page) {
        return null;
    }

    @Override
    public ArrayList<Bill> getAllBill() {
        return null;
    }

    @Override
    public ArrayList<Bill> getBillByCustomer(int customer_id) {
        return null;
    }

    @Override
    public ArrayList<Bill> getBillByCustomer(int page, int customer_id) {
        return null;
    }

    @Override
    public ArrayList<Bill> getBillByEmployee(int emp_id) {
        return null;
    }

    @Override
    public ArrayList<Bill> getBillByEmployee(int page, int emp_id) {
        return null;
    }

    @Override
    public ArrayList<Bill> getBillByPaly(int play_id) {
        return null;
    }

    @Override
    public ArrayList<Bill> getBillByPaly(int page, int play_id) {
        return null;
    }
}
