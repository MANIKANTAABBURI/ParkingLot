package org.example.factories;

import org.example.enums.ParkingSpotAssignmentStrategyType;
import org.example.strategies.CheapestSpotAssignmentStrategy;
import org.example.strategies.NearestParkingSpotAssignment;
import org.example.strategies.ParkingSpotAssignmentStrategy;
import org.example.strategies.RandomSpotAssignmentStrategy;

public class ParkingspotAssignmentStrategyFactory {
    public static ParkingSpotAssignmentStrategy getParkingSpotAssignmentStrategy(
            ParkingSpotAssignmentStrategyType strategyType
    ) {
        switch (strategyType) {
            case NEAREST -> new NearestParkingSpotAssignment();
            case RANDOM -> new RandomSpotAssignmentStrategy();
            case CHEAPEST -> new CheapestSpotAssignmentStrategy();
            default -> throw new IllegalArgumentException("Unsupported ParkingSpotAssignmentStrategyType");
        }

        return null;
    }
}

