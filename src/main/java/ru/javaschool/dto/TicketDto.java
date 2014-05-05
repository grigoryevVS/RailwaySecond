package ru.javaschool.dto;


import ru.javaschool.model.entities.Schedule;
import ru.javaschool.model.entities.StationDistance;
import ru.javaschool.model.entities.User;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Class to represent ticket with string parameters
 * in the view
 */
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
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");

    public TicketDto() {
    }

    public TicketDto(User user, Schedule schedule, String stationFrom, String stationTo) {

        List<StationDistance> sdList = schedule.getRoute().getStationDistances();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.birthDate = sdf.format(user.getBirthDate());
        this.trainName = schedule.getTrain().getName();
        this.date = sdf.format(schedule.getDateTrip());
        this.routeName = schedule.getRoute().getTitle();

        if (!stationFrom.equals("")) {
            this.stationFrom = stationFrom;
            for (StationDistance sd : sdList) {
                if (sd.getStation().getName().equals(stationFrom)) {
                    this.departureTime = sdfTime.format(sd.getAppearTime());
                }
            }
        } else {
            this.stationFrom = sdList.get(0).getStation().getName();
            this.departureTime = sdfTime.format((sdList.get(0).getAppearTime()));
        }
        if (!stationTo.equals("")) {
            this.stationTo = stationTo;
            for (StationDistance sd : sdList) {
                if (sd.getStation().getName().equals(stationTo)) {
                    this.arrivalTime = sdfTime.format(sd.getAppearTime());
                }
            }
        } else {
            this.stationTo = sdList.get(sdList.size() - 1).getStation().getName();
            this.arrivalTime = sdfTime.format((sdList.get(sdList.size() - 1).getAppearTime()));
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
