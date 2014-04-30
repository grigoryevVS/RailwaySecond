package ru.javaschool.model.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Ticket implements Serializable{
    private static final long serialVersionUID = 7332983214543490707L;

    @Id
    @GeneratedValue
    private long ticketId;
    @ManyToOne
    private User user;
    @ManyToOne(optional = false, fetch = FetchType.EAGER )
    private Schedule schedule;

    public long getTicketId() {
        return ticketId;
    }

    public void setTicketId(long ticketId) {
        this.ticketId = ticketId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", passenger=" + user.getFirstName() +
                ", passenger=" + user.getLastName() +
                ", schedule=" + schedule.getDateTrip() +
                '}';
    }
}
