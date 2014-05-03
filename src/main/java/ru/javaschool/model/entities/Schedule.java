package ru.javaschool.model.entities;


import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Future;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class Schedule implements Serializable {

    private static final long serialVersionUID = -8783126071317237137L;

    @Id
    @GeneratedValue
    private long scheduleId;
    @NotEmpty
    @Future
    @Column
    @Temporal(TemporalType.DATE)
    private Date dateTrip;
    @ManyToOne
    private Train train;
    @ManyToOne(fetch = FetchType.EAGER)
    private Route route;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "schedule")
    private List<Ticket> ticketList;

    public long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Date getDateTrip() {
        return dateTrip;
    }

    public void setDateTrip(Date dateTrip) {
        this.dateTrip = dateTrip;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }
}
