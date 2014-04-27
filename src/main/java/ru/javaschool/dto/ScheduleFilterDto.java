package ru.javaschool.dto;


public class ScheduleFilterDto {

    private String date;
    private String stationFromName;
    private String StationToName;

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
