package ru.javaschool.model.entities;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
public class Route implements Serializable{

    private static final long serialVersionUID = 4245604106003040914L;

    @Id
    @GeneratedValue
    private Long routeId;
    @NotEmpty
    @Size(min=2, max=40)
    @Column
    @NotNull(message = "It can't be empty!")
    private String title;
    @OneToMany( mappedBy="route")
    private List<StationDistance> stationDistances;

    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }

    public List<StationDistance> getStationDistances() {
        return stationDistances;
    }

    public void setStationDistances(List<StationDistance> stationDistances) {
        this.stationDistances = stationDistances;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    @Override
    public String toString() {
        return  routeId + ". " + title;
    }
}
