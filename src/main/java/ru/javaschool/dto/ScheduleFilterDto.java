package ru.javaschool.dto;


import javax.validation.constraints.NotNull;

public class ScheduleFilterDto {

    @NotNull
    private String date = "";
    @NotNull
    private String stationFromName = "";
    @NotNull
    private String StationToName = "";

    public ScheduleFilterDto() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStationFromName() {
        return stationFromName;
    }

    public void setStationFromName(String stationFromName) {
        this.stationFromName = stationFromName;
    }

    public String getStationToName() {
        return StationToName;
    }

    public void setStationToName(String stationToName) {
        StationToName = stationToName;
    }
}
