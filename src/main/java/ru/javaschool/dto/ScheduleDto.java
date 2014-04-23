package ru.javaschool.dto;


import ru.javaschool.model.entities.Schedule;
import ru.javaschool.model.entities.StationDistance;

import java.text.SimpleDateFormat;
import java.util.List;

public class ScheduleDto {

    private long id;
    private String stationFrom;
    private String stationTo;
    private String trainName;
    private String date;
    private String routeName;
    private String appearTimeFrom;
    private String appearTimeTo;
    private int emptySeats;

    public ScheduleDto() {
    }

    public ScheduleDto(Schedule schedule) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        List<StationDistance> sdList = schedule.getRoute().getStationDistances();   // TODO empty!!!!!!!! why???
        this.id = schedule.getScheduleId();
        if(!sdList.isEmpty()){
        this.stationFrom = sdList.get(0).getStation().getName();
        this.appearTimeFrom = sdf.format(sdList.get(0).getAppearTime());
        this.stationTo = sdList.get(sdList.size() - 1).getStation().getName();
        this.appearTimeTo = sdf.format(sdList.get(sdList.size() - 1).getAppearTime());
        }
        this.trainName = schedule.getTrain().getName();
        this.date = sdf.format(schedule.getDateTrip());
        this.routeName = schedule.getRoute().getTitle();
        this.emptySeats = schedule.getTrain().getNumberOfSeats() - schedule.getTicketList().size();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getAppearTimeFrom() {
        return appearTimeFrom;
    }

    public void setAppearTimeFrom(String appearTimeFrom) {
        this.appearTimeFrom = appearTimeFrom;
    }

    public String getAppearTimeTo() {
        return appearTimeTo;
    }

    public void setAppearTimeTo(String appearTimeTo) {
        this.appearTimeTo = appearTimeTo;
    }

    public int getEmptySeats() {
        return emptySeats;
    }

    public void setEmptySeats(int emptySeats) {
        this.emptySeats = emptySeats;
    }
}
