package edu.xupt.cs.se.servlet.generalmanager;

import edu.xupt.cs.se.dao.BillDAO;
import edu.xupt.cs.se.dao.PlayDAO;
import edu.xupt.cs.se.model.Bill;
import edu.xupt.cs.se.model.Play;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
/**
 * Created by MR.D on 2017/6/1.
 */
@WebServlet(name = "movieStatisticsServlet",urlPatterns ="/generalmanager/movieStatistics")
public class movieStatisticsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PlayDAO playDAO = new PlayDAO();
        ArrayList<Play> plays = playDAO.getAllPlay();
        BillDAO billDAO = new BillDAO();
        ArrayList<Map<String,Object>> list = new ArrayList<>();
        HashSet<String> time = new HashSet<>();
        for(Play play : plays) {
            ArrayList<Bill> bills = billDAO.getBillByPaly(play.getId());
            int ticket;
            ticket = bills.size();
            float price=0;
            for(Bill bill : bills){
                price += bill.getPrice();
            }
            HashMap<String,Object> map = new HashMap<>();
            map.put("ticket",ticket);
            map.put("price", price);
            map.put("movie",play);
            list.add(map);
        }

        for(Bill bill: billDAO.getAllBill()){
            time.add(bill.getSale_time());
        }
        if(null != request.getParameter("play")){
            int play_id = Integer.parseInt(request.getParameter("play"));
            String sale_time = request.getParameter("time");
            if(play_id <= 0 ){
                ArrayList<Bill> bills = billDAO.getAllBill();
                ArrayList<Bill> bs = new ArrayList<>();
                if(!sale_time.equals("all")){
                    for(Bill bill : bills){
                        if(bill.getSale_time().equals(sale_time)){
                            bs.add(bill);
                        }
                    }
                    request.setAttribute("bills",bs);
                }else{
                    request.setAttribute("bills",bills);
                }
            }else{
                ArrayList<Bill> bills = billDAO.getBillByPaly(play_id);
                ArrayList<Bill> bs = new ArrayList<>();
                if(!sale_time.equals("all")){
                    for(Bill bill : bills){
                        if(bill.getSale_time().equals(sale_time)){
                            bs.add(bill);
                        }
                    }
                    request.setAttribute("bills",bs);
                }else{
                    request.setAttribute("bills",bills);
                }
            }
        }
        request.setAttribute("time", time);
        request.setAttribute("list",list);
        request.setAttribute("movie", playDAO.getAllPlay());
        request.getRequestDispatcher("/generalmanager/movieStatistics.jsp").forward(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
