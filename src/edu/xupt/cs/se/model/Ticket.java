package edu.xupt.cs.se.model;

/**
 * Created by zhoupan on 17-5-30.
 */
public class Ticket {
    private int id;
    private int seat_id;
    private int schedule_id;
    private int play_id;
    private float price;
    private int status;
    private String locktime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeat_id() {
        return seat_id;
    }

    public void setSeat_id(int seat_id) {
        this.seat_id = seat_id;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getLocktime() {
        return locktime;
    }

    public void setLocktime(String locktime) {
        this.locktime = locktime;
    }
}
