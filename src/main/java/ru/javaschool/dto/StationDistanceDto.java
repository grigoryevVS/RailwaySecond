package ru.javaschool.dto;


import ru.javaschool.model.entities.StationDistance;

import java.text.SimpleDateFormat;

public class StationDistanceDto {

    private String routeName;
    private String stationName;
    private Long sequenceNumber;
    private String appearenceTime;

    public StationDistanceDto() {
    }

    public StationDistanceDto(StationDistance sd) {
        this.routeName = sd.getRoute().getTitle();
        this.sequenceNumber = sd.getSequenceNumber();
        this.stationName = sd.getStation().getName();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        this.appearenceTime = sdf.format(sd.getAppearTime());
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public Long getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Long sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public String getAppearenceTime() {
        return appearenceTime;
    }

    public void setAppearenceTime(String appearenceTime) {
        this.appearenceTime = appearenceTime;
    }
}
