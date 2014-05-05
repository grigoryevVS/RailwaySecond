package ru.javaschool.dto;


import ru.javaschool.model.entities.Route;
import ru.javaschool.model.entities.Schedule;
import ru.javaschool.model.entities.StationDistance;
import ru.javaschool.services.ScheduleService;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Class to represent schedule in the view with string parameters.
 */
public class ScheduleDto {

    private long id;
    private String stationFrom;
    private String stationTo;
    private String trainName;
    private String date;
    private String routeName;
    private String departureTime;
    private String arrivalTime;
    private int emptySeats;
    SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");

    private ScheduleService scheduleService;

    public ScheduleDto() {
    }

    public ScheduleDto(Schedule schedule) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Route route = schedule.getRoute();
        List<StationDistance> sdList = schedule.getRoute().getStationDistances();
        this.id = schedule.getScheduleId();
        if (!sdList.isEmpty()) {
            this.stationFrom = sdList.get(0).getStation().getName();
            this.departureTime = sdfTime.format((sdList.get(0).getAppearTime()));
            this.stationTo = sdList.get(sdList.size() - 1).getStation().getName();
            this.arrivalTime = sdfTime.format((sdList.get(sdList.size() - 1).getAppearTime()));
        }
        this.trainName = schedule.getTrain().getName();
        this.date = sdf.format(schedule.getDateTrip());
        this.routeName = schedule.getRoute().getTitle();
        this.emptySeats = schedule.getTrain().getNumberOfSeats() - schedule.getTicketList().size();
    }

    public ScheduleDto(Schedule schedule, ScheduleFilterDto filter) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<StationDistance> sdList = schedule.getRoute().getStationDistances();
        boolean fromEmpty = false;
        boolean toEmpty = false;
        this.id = schedule.getScheduleId();
        if (!sdList.isEmpty()) {
            StationDistance sdFrom = new StationDistance();
            StationDistance sdTo = new StationDistance();
            if (filter.getStationFromName().equals("")) {
                sdFrom = sdList.get(0);
                fromEmpty = true;
            }
            if (filter.getStationToName().equals("")) {
                sdTo = sdList.get(sdList.size() - 1);
                toEmpty = true;
            }
            for (StationDistance sd : sdList) {
                if (!fromEmpty && sd.getStation().getName().equals(filter.getStationFromName())) {
                    sdFrom = sd;
                }
                if (!toEmpty && sd.getStation().getName().equals(filter.getStationToName())) {
                    sdTo = sd;
                }
            }
            this.stationFrom = sdFrom.getStation().getName();
            this.departureTime = sdfTime.format((sdFrom.getAppearTime()));
            this.stationTo = sdTo.getStation().getName();
            this.arrivalTime = sdfTime.format(sdTo.getAppearTime());
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

    public int getEmptySeats() {
        return emptySeats;
    }

    public void setEmptySeats(int emptySeats) {
        this.emptySeats = emptySeats;
    }
}
