package edu.xupt.cs.se.dao;

import edu.xupt.cs.se.idao.Iticket;
import edu.xupt.cs.se.model.Ticket;

import java.util.ArrayList;

/**
 * Created by zhoupan on 17-5-31.
 */
public class TicketDAO implements Iticket{
    @Override
    public boolean insert(Ticket ticket) {
        return false;
    }

    @Override
    public boolean delete(int ticket_id) {
        return false;
    }

    @Override
    public boolean update(Ticket ticket) {
        return false;
    }

    @Override
    public Ticket getTicketByID(int ticket_id) {
        return null;
    }

    @Override
    public ArrayList<Ticket> getTicketByPage(int page) {
        return null;
    }

    @Override
    public ArrayList<Ticket> getTicketBySchedule(int schedule_id) {
        return null;
    }

    @Override
    public ArrayList<Ticket> getTicketByPlay(int play_id) {
        return null;
    }

    @Override
    public ArrayList<Ticket> getAllTicket() {
        return null;
    }

    @Override
    public Ticket getTicketBySeat(int seat_id) {
        return null;
    }

    @Override
    public ArrayList<Ticket> getTicketBySchedule(int page, int schedule_id) {
        return null;
    }

    @Override
    public ArrayList<Ticket> getTicketByPlay(int page, int play_id) {
        return null;
    }
}
