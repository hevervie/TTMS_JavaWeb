package edu.xupt.cs.se.servlet.generalmanager;

import com.sun.xml.internal.bind.v2.model.core.ID;
import edu.xupt.cs.se.dao.BillDAO;
import edu.xupt.cs.se.dao.EmployeeDAO;
import edu.xupt.cs.se.dao.PlayDAO;
import edu.xupt.cs.se.dao.TheaterDAO;
import edu.xupt.cs.se.model.Bill;
import edu.xupt.cs.se.model.Employee;
import edu.xupt.cs.se.model.Play;
import edu.xupt.cs.se.model.Theater;
import edu.xupt.cs.se.util.ConnectionManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by MR.D on 2017/6/1.
 */
@WebServlet(name = "generalmanagerStatisticServlet", urlPatterns = "/generalmanager/generalmanagerStatistic")
public class generalmanagerStatisticServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TheaterDAO theaterDAO = new TheaterDAO();
        ArrayList<Theater> theaters = theaterDAO.getAllTheater();
        EmployeeDAO employeeDAO = new EmployeeDAO();
        BillDAO billDAO = new BillDAO();
        ArrayList<Map<String, Object>> list = new ArrayList<>();
        for (Theater theater : theaters) {
            ArrayList<Employee> employees = employeeDAO.getEmployeeByTheater(theater.getId());
            Map<String, Object> map = new HashMap<>();
            int ticket = 0;
            float price = 0;
            for (Employee employee : employees) {
                ArrayList<Bill> bills = billDAO.getBillByEmployee(employee.getId());
                for (Bill bill : bills) {
                    ticket += 1;
                    price += bill.getPrice();
                }
            }
            map.put("theater", theater);
            map.put("ticket", ticket);
            map.put("price", price);
            list.add(map);
        }
        HashSet<String> time = new HashSet<>();
        for (Bill bill : billDAO.getAllBill()) {
            time.add(bill.getSale_time());
        }
        if (null != request.getParameter("theater_id")) {
            int theater_id = Integer.parseInt(request.getParameter("theater_id"));
            ArrayList<Map<String, Object>> data = new ArrayList<>();
            String date = request.getParameter("time");
            PlayDAO playDAO = new PlayDAO();
            ArrayList<Play> plays = playDAO.getAllPlay();

            if (theater_id == 0) {
                for (Play play : plays) {
                    ArrayList<Employee> employees = employeeDAO.getAllEmployee();
                    ArrayList<Bill> bs = billDAO.getBillByPaly(play.getId());
                    Map<String, Object> map = new HashMap<>();
                    int ticket = 0;
                    float price = 0;
                    for (Bill bill : bs) {
                        System.out.println(bill.getId());
                        if (isContain(employees, employeeDAO.getEmployeeByID(bill.getEmp_id()))) {
                            ticket += 1;
                            price += bill.getPrice();
                        }
                    }
                    System.out.println(ticket);
                    System.out.println(price);
                    map.put("play", play);
                    map.put("ticket", ticket);
                    map.put("price", price);
                    data.add(map);
                }
                request.setAttribute("data", data);

            } else {
                for (Play play : plays) {
                    ArrayList<Employee> employees = employeeDAO.getEmployeeByTheater(theater_id);
                    ArrayList<Bill> bs = billDAO.getBillByPaly(play.getId());
                    Map<String, Object> map = new HashMap<>();
                    int ticket = 0;
                    float price = 0;
                    for (Bill bill : bs) {
                        System.out.println(bill.getId());
                        if (isContain(employees, employeeDAO.getEmployeeByID(bill.getEmp_id()))) {
                            ticket += 1;
                            price += bill.getPrice();
                        }
                    }
                    System.out.println(ticket);
                    System.out.println(price);
                    map.put("play", play);
                    map.put("ticket", ticket);
                    map.put("price", price);
                    data.add(map);
                }
                request.setAttribute("data", data);
            }

        }
        request.setAttribute("time", time);
        request.setAttribute("list", list);
        request.getRequestDispatcher("generalmanagerStatistic.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public boolean isContain(ArrayList<Employee> employees, Employee employee) {
        if (employee == null || null == employees) {
            return false;
        }
        for (Employee em : employees) {
            if (em.getId() == employee.getId()) {
                return true;
            }
        }
        return false;
    }
}
