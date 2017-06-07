package edu.xupt.cs.se.servlet.employee;

import edu.xupt.cs.se.dao.BillDAO;
import edu.xupt.cs.se.dao.CustomerDAO;
import edu.xupt.cs.se.dao.TicketDAO;
import edu.xupt.cs.se.model.Bill;
import edu.xupt.cs.se.model.Customer;
import edu.xupt.cs.se.model.Ticket;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/6/2.
 */
@WebServlet(name = "RefundsServlet",urlPatterns = "/employee/refunds")
public class RefundsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //request.getRequestDispatcher("/employee/Refunds.jsp").forward(request,response);
        TicketDAO ticketDAO=new TicketDAO();
        String tel=request.getParameter("tel");
        //int bill_id=Integer.parseInt(request.getParameter("bill"));
        String ticket_id=request.getParameter("ticketid");

        CustomerDAO customerDAO=new CustomerDAO();
        ArrayList<Customer> customers=customerDAO.getCustomerByTel(tel);
        ArrayList<Ticket> tickets=ticketDAO.getAllTicket();
        BillDAO billDAO=new BillDAO();
        ArrayList<Bill> bills=billDAO.getAllBill();
        if (null!=request.getParameter("ticketid")) {
            Ticket ticket=new Ticket();
            for (Bill bill:bills){
                if (bill.getTicket_id()==Integer.parseInt(ticket_id) && customerDAO.getCustomerByID(bill.getCustomer_id()).getTel().equals(tel)){
                   // System.out.println(bill.getCustomer_id());
                   // System.out.println(customerDAO.getCustomerByID(bill.getCustomer_id()).getTel());
                    ticket = ticketDAO.getTicketByID(Integer.parseInt(ticket_id));
                    request.setAttribute("ticket", ticket);
                    request.setAttribute("bills",bills);
                    request.setAttribute("tickets",tickets);
                    request.getRequestDispatcher("/employee/Refunds.jsp").forward(request,response);

                }
            }
        }else if (null!=request.getParameter("del") && request.getParameter("del").equals("delete")){

        }else {
            request.setAttribute("bills", bills);
            request.setAttribute("tickets", tickets);
            request.getRequestDispatcher("/employee/Refunds.jsp").forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}