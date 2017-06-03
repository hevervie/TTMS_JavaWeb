package edu.xupt.cs.se.idao;

import edu.xupt.cs.se.model.Bill;

import java.util.ArrayList;

/**
 * Created by zhoupan on 17-5-30.
 */
public interface Ibill {

    /*
    private int customer_id;
    private int ticket_id;
    private int emp_id;
    private int play_id;
     */
    public boolean insert(Bill bill);

    public boolean delete(int bill_id);

    public boolean update(Bill bill);

    public Bill getBillByID(int bill_id);

    public ArrayList<Bill> getBillByPage(int page);

    public ArrayList<Bill> getAllBill();

    public ArrayList<Bill> getBillByCustomer(int customer_id);

    public ArrayList<Bill> getBillByCustomer(int page, int customer_id);

    public ArrayList<Bill> getBillByEmployee(int emp_id);

    public ArrayList<Bill> getBillByEmployee(int page, int emp_id);

    public ArrayList<Bill> getBillByPaly(int play_id);

    public ArrayList<Bill> getBillByPaly(int page, int play_id);

}
