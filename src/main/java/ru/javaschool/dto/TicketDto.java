package ru.javaschool.dto;


import ru.javaschool.model.entities.Schedule;
import ru.javaschool.model.entities.StationDistance;
import ru.javaschool.model.entities.User;

import java.text.SimpleDateFormat;
import java.util.List;

public class TicketDto {

    private String firstName;
    private String lastName;
    private String birthDate;

    private String stationFrom;
    private String stationTo;
    private String trainName;
    private String date;
    private String routeName;
    private String departureTime;
    private String arrivalTime;

    public TicketDto() {
    }

    public TicketDto(User user, Schedule schedule, ScheduleFilterDto filter) {

        List<StationDistance> sdList = schedule.getRoute().getStationDistances();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        this.birthDate = sdf.format(user.getBirthDate());
        this.trainName = schedule.getTrain().getName();
        this.date = sdf.format(schedule.getDateTrip());
        this.routeName = schedule.getRoute().getTitle();
        if (filter == null) {
            this.stationFrom = sdList.get(0).getStation().getName();
            this.stationTo = sdList.get(sdList.size() - 1).getStation().getName();
            this.departureTime = String.valueOf((sdList.get(0).getAppearTime()));
            this.arrivalTime = String.valueOf((sdList.get(sdList.size() - 1).getAppearTime()));
        } else {
            if (!filter.getStationFromName().equals("")) {
                this.stationFrom = filter.getStationFromName();
            } else {
                this.stationFrom = sdList.get(0).getStation().getName();
            }
            if (filter.getStationToName().equals("")) {
                this.stationTo = filter.getStationToName();
            } else {
                this.stationTo = sdList.get(sdList.size() - 1).getStation().getName();
            }
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getStationFrom() {
        return stationFrom;
    }

    public void setStationFrom(String stationFrom) {
        this.stationFrom = stationFrom;
    }

    public String getStationTo() {
        return stationTo;
    }

    public void setStationTo(String stationTo) {
        this.stationTo = stationTo;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}
