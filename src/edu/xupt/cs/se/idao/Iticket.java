package edu.xupt.cs.se.idao;

import edu.xupt.cs.se.model.Ticket;

import java.util.ArrayList;

/**
 * Created by zhoupan on 17-5-30.
 */
public interface Iticket {

    /*
    private int id;
    private int seat_id;
    private int schedule_id;
    private int play_id;
    private float price;
    private int status;
    private String locktime;
     */

    public boolean insert(Ticket ticket);

    public boolean delete(int ticket_id);

    public boolean update(Ticket ticket);

    public Ticket getTicketByID(int ticket_id);

    public Ticket getTicketBySeat(int seat_id);

    public ArrayList<Ticket> getTicketBySchedule(int page, int schedule_id);

    public ArrayList<Ticket> getTicketByPlay(int page, int play_id);

    public ArrayList<Ticket> getTicketByPage(int page);

    public ArrayList<Ticket> getTicketBySchedule(int schedule_id);

    public ArrayList<Ticket> getTicketByPlay(int play_id);

    public ArrayList<Ticket> getAllTicket();
}
