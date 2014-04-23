package ru.javaschool.model.entities;


import java.io.Serializable;

public class StationDistanceEPK implements Serializable {
    private static final long serialVersionUID = 1312321L;

    private Long route;

    private Long sequenceNumber;

    public StationDistanceEPK() {

    }
    public StationDistanceEPK(Long routeId, Long sequenceNumber) {
        this.route = routeId;
        this.sequenceNumber = sequenceNumber;
    }

    public Long getRoute() {
        return route;
    }

    public void setRoute(Long route) {
        this.route = route;
    }

    public Long getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Long sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StationDistanceEPK that = (StationDistanceEPK) o;

        if (sequenceNumber != that.sequenceNumber) return false;
        if (route != null ? !route.equals(that.route) : that.route != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = route != null ? route.hashCode() : 0;
        result = 31 * result + sequenceNumber.intValue();
        return result;
    }
}
