package org.example.models;

import java.util.List;

public class ParkingFloor extends BaseModel {
    private List<ParkingSpot> parkingSpots;
    private int floorNumber;
    private ParkingFloorStatus parkingStatus;

    public List<ParkingSpot> getParkingSpots() {
        return parkingSpots;
    }

    public void setParkingSpots(List<ParkingSpot> parkingSpots) {
        this.parkingSpots = parkingSpots;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public ParkingFloorStatus getParkingStatus() {
        return parkingStatus;
    }

    public void setParkingStatus(ParkingFloorStatus parkingStatus) {
        this.parkingStatus = parkingStatus;
    }
}
