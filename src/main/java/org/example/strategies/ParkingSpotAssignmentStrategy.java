package org.example.strategies;

import org.example.models.Gate;
import org.example.models.ParkingSpot;
import org.example.models.Vehicle;
import org.example.models.VehicleType;

public interface ParkingSpotAssignmentStrategy {
    ParkingSpot assignParkingSpot(Gate gate, Vehicle vehicle);
}
