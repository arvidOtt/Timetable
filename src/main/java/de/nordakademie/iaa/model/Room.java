package de.nordakademie.iaa.model;

import org.hibernate.annotations.NaturalId;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Entity
public class Room extends HasMinChangeoverTime implements Serializable {

    @NaturalId
    private String building;

    @NaturalId
    private String number;

    @Basic
    private int maxSeats;

    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    public Room() {}

    public Room(int minChangeoverTime, String building, int maxSeats, String number, RoomType roomType) {
        super(minChangeoverTime);
        this.building = building;
        this.maxSeats = maxSeats;
        this.number = number;
        this.roomType = roomType;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getMaxSeats() {
        return maxSeats;
    }

    public void setMaxSeats(int maxSeats) {
        this.maxSeats = maxSeats;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Room room = (Room) o;

        if (!building.equals(room.building)) return false;
        return number.equals(room.number);
    }

    @Override
    public int hashCode() {
        int result = building.hashCode();
        result = 31 * result + number.hashCode();
        return result;
    }
}