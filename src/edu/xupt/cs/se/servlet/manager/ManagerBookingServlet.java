package edu.xupt.cs.se.servlet.manager;

import edu.xupt.cs.se.dao.BillDAO;
import edu.xupt.cs.se.dao.EmployeeDAO;
import edu.xupt.cs.se.dao.PlayDAO;
import edu.xupt.cs.se.model.Bill;
import edu.xupt.cs.se.model.Employee;
import edu.xupt.cs.se.model.Play;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.soap.SAAJMetaFactory;
import java.io.IOException;
import java.util.*;

/**
 * Created by zhoupan on 17-6-1.
 */
@WebServlet(name = "ManagerBookingServlet", urlPatterns = "/managers/booking/")
public class ManagerBookingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PlayDAO playDAO = new PlayDAO();
        BillDAO billDAO = new BillDAO();
        EmployeeDAO employeeDAO = new EmployeeDAO();
        ArrayList<Employee> employees = employeeDAO.getEmployeeByTheater((int) request.getSession().getAttribute("theaterid"));
        ArrayList<Map<String, Object>> list = new ArrayList<>();
        Set<String> time = new HashSet<>();
        ArrayList<Bill> allbill = billDAO.getAllBill();
        for (Bill bill : allbill) {
            time.add(bill.getSale_time());
        }
        ArrayList<Play> plays = playDAO.getAllPlay();
        for (Play play : plays) {
            Map<String, Object> map = new HashMap<>();
            map.put("play", play);
            int ticket = 0;
            float price = 0;
            ArrayList<Bill> bills = billDAO.getBillByPaly(play.getId());
            for (Bill bill : bills) {
                Employee employee = employeeDAO.getEmployeeByID(bill.getEmp_id());
                if (isConstain(employees, employee)) {
                    ticket += 1;
                    price += bill.getPrice();
                }
            }
            map.put("ticket", ticket);
            map.put("price", price);
            list.add(map);
        }

        if(null != request.getParameter("play")){
            String str_play = request.getParameter("play");
            String sale_time = request.getParameter("time");
            System.out.println("time:"+sale_time);
            int play_id = Integer.parseInt(str_play);
            if(play_id == 0 ){
                ArrayList<Bill> bills = billDAO.getAllBill();
                ArrayList<Bill> bs = new ArrayList<>();
                if(null != sale_time && sale_time.equals("all")) {
                    for (Bill bill : bills) {
                        Employee employee = employeeDAO.getEmployeeByID(bill.getEmp_id());
                        if (isConstain(employees, employee)) {
                            bs.add(bill);
                        }
                    }
                }else{
                    for (Bill bill : bills) {
                        Employee employee = employeeDAO.getEmployeeByID(bill.getEmp_id());
                        if (isConstain(employees, employee)  && bill.getSale_time().equals(sale_time)) {
                            bs.add(bill);
                        }
                    }
                }
                request.setAttribute("bills", bs);
            }else {
                ArrayList<Bill> bills = billDAO.getBillByPaly(play_id);
                ArrayList<Bill> bs = new ArrayList<>();
                if(null != sale_time && sale_time.equals("all")) {
                    for (Bill bill : bills) {
                        Employee employee = employeeDAO.getEmployeeByID(bill.getEmp_id());
                        if (isConstain(employees, employee)) {
                            bs.add(bill);
                        }
                    }
                }else{
                    for (Bill bill : bills) {
                        Employee employee = employeeDAO.getEmployeeByID(bill.getEmp_id());
                        if (isConstain(employees, employee)  && bill.getSale_time().equals(sale_time)) {
                            bs.add(bill);
                        }
                    }
                }
                request.setAttribute("bills", bs);
            }
        }
        request.setAttribute("plays", plays);
        request.setAttribute("list", list);
        request.setAttribute("time", time);
        request.getRequestDispatcher("/manager/booking.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    private boolean isConstain(ArrayList<Employee> list, Employee obj) {
        for (Employee employee : list) {
            if (employee.getEmp_no().equals(obj.getEmp_no())) {
                return true;
            }
        }
        return false;
    }
}
