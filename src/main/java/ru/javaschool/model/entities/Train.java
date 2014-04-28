package ru.javaschool.model.entities;


import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
public class  Train implements Serializable{

    private static final long serialVersionUID = -6808983024241846152L;

    @Id
    @GeneratedValue
    private long trainId;
    @Column( nullable = false)
    @NotNull
    private int numberOfSeats;
    @NotEmpty
    @Size(min=2, max=20)
    @Column(nullable = false, length = 25)
    private String name;

    public long getTrainId() {
        return trainId;
    }

    public void setTrainId(long trainId) {
        this.trainId = trainId;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Train{" +
                "trainId=" + trainId +
                ", numberOfSeats=" + numberOfSeats +
                ", name='" + name +
                '}';
    }
}
