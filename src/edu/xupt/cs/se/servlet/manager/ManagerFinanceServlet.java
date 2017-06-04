package edu.xupt.cs.se.servlet.manager;

import edu.xupt.cs.se.dao.BillDAO;
import edu.xupt.cs.se.dao.EmployeeDAO;
import edu.xupt.cs.se.model.Bill;
import edu.xupt.cs.se.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by zhoupan on 17-6-1.
 */
@WebServlet(name = "ManagerFinanceServlet", urlPatterns = "/managers/finance/")
public class ManagerFinanceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        BillDAO billDAO = new BillDAO();
        ArrayList<Employee> employees = employeeDAO.getEmployeeByTheater((int) request.getSession().getAttribute("theaterid"));
        ArrayList<Map<String, Object>> list = new ArrayList<>();
        Set<String> time = new HashSet<>();
        ArrayList<Bill> allbill = billDAO.getAllBill();
        for(Bill bill: allbill){
            time.add(bill.getSale_time());
        }

        for (Employee employee : employees) {
            ArrayList<Bill> bills = billDAO.getBillByEmployee(employee.getId());
            float sum = 0;
            for (Bill bill : bills) {
                sum += bill.getPrice();
            }
            Map<String, Object> map = new HashMap<>();
            map.put("employee", employee);
            map.put("price", sum);
            list.add(map);
        }

        if(null != request.getParameter("employee")){
            String str_empid = request.getParameter("employee");
            String sale_time = request.getParameter("time");
            int empid = Integer.parseInt(str_empid);
            Employee employee = employeeDAO.getEmployeeByID(empid);
            if(null != sale_time && sale_time.equals("all")){
                ArrayList<Bill> bills = billDAO.getBillByEmployee(employee.getId());
                request.setAttribute("bills", bills);
            }else {
                ArrayList<Bill> bills = billDAO.getBillByEmployee(employee.getId());
                ArrayList<Bill> bs = new ArrayList<>();
                for (Bill b : bills) {
                    if (null != b.getSale_time() && b.getSale_time().equals(sale_time)) {
                        bs.add(b);
                    }
                }
                request.setAttribute("bills", bs);
            }
        }
        request.setAttribute("time", time);
        request.setAttribute("list", list);
        request.getRequestDispatcher("/manager/finance.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
