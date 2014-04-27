package ru.javaschool.model.entities;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
public class Station implements Serializable {

    private static final long serialVersionUID = -8507927853458358682L;

    @Id
    @GeneratedValue
    private long stationId;
    @NotEmpty
    @Size(min=2, max=20)
    @Column(nullable = false, length = 20)
    private String name;

    public long getStationId() {
        return stationId;
    }

    public void setStationId(long stationId) {
        this.stationId = stationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        if (stationId == 0) {
            return "";
        }
        return stationId + ". " + name;
    }
}
