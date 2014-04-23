package ru.javaschool.model.entities;


import java.io.Serializable;

public class StationDistanceEPK implements Serializable {
    private static final long serialVersionUID = 1312321L;

    private Long routeId;

    private Long sequenceNumber;

    public StationDistanceEPK() {

    }
    public StationDistanceEPK(Route route, Long sequenceNumber) {
        this.routeId = route.getRouteId();
        this.sequenceNumber = sequenceNumber;
    }

    public Long getRoute() {
        return routeId;
    }

    public void setRoute(Route route) {
        this.routeId = route.getRouteId();
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
        if (routeId != null ? !routeId.equals(that.routeId) : that.routeId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = routeId != null ? routeId.hashCode() : 0;
        result = 31 * result + sequenceNumber.intValue();
        return result;
    }
}
