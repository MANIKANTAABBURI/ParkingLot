package org.example.services;

import org.example.exceptions.GateNotFoundException;
import org.example.exceptions.ParkingLotNotFoundException;
import org.example.exceptions.VehicleNotFoundException;
import org.example.factories.ParkingspotAssignmentStrategyFactory;
import org.example.models.*;
import org.example.enums.*;
import org.example.respositories.GateRepository;
import org.example.respositories.ParkingLotRepository;
import org.example.respositories.TicketRepository;
import org.example.respositories.VehicleRepository;
import org.example.strategies.ParkingSpotAssignmentStrategy;

import java.util.Date;
import java.util.Optional;

public class TicketService {
    private GateRepository gateRepository;
    private ParkingLotRepository parkingLotRepository;
    private VehicleRepository vehicleRepository;
    private TicketRepository ticketRepository;

    public TicketService(
            GateRepository gateRepository,
            ParkingLotRepository parkingLotRepository,
            VehicleRepository vehicleRepository,
            TicketRepository ticketRepository) {
        this.gateRepository = gateRepository;
        this.parkingLotRepository = parkingLotRepository;
        this.vehicleRepository = vehicleRepository;
        this.ticketRepository = ticketRepository;
    }

    public Ticket issueTicket(long gateId,
                              String vehicleNumber,
                              String vehicleOwnerName,
                              VehicleType vehicleType)
            throws GateNotFoundException,
            ParkingLotNotFoundException,
            VehicleNotFoundException {

        /*
        01. Get the gate object from gate id
        02. Check if the vehicle with the given number exits else register the vehicle
        03. Assign the parking spot
        */

        Optional<Gate> optionalGate = gateRepository.findGateById(gateId);

        if(optionalGate.isEmpty()){
            // throw an exception
            throw new GateNotFoundException("Gate with gate id " + gateId + " does not exist");
        }

        Gate gate = optionalGate.get();

        Ticket ticket = new Ticket();
        ticket.setGate(gate);
        ticket.setEntryTime(new Date());

        // Get the Vehicle object
        Optional<Vehicle> vehicleOptional = vehicleRepository.findVehicleByVehicleNumber(vehicleNumber);
        if(vehicleOptional.isEmpty()){
            throw new VehicleNotFoundException("Vehicle with number " + vehicleNumber + " does not exist");
        }


        Optional<ParkingLot> parkingLotOptional = parkingLotRepository.findParkingLotByGateId(gateId);

        Vehicle savedVehicle = null;

        if(parkingLotOptional.isEmpty()){
            Vehicle vehicle = new Vehicle();
            vehicle.setVehicleNumber(vehicleNumber);
            vehicle.setVehicleType(vehicleType);
            vehicle.setOwnerName(vehicleOwnerName);

            vehicleRepository.save(vehicle);

            savedVehicle = vehicle;
        }else{
            savedVehicle = vehicleOptional.get();
        }

        ParkingLot parkingLot = parkingLotOptional.get();

        ParkingSpotAssignmentStrategyType strategyType =
                parkingLot.getParkingSpotAssignmentStrategyType();

        ParkingSpotAssignmentStrategy parkingSpotAssignmentStrategy =
                ParkingspotAssignmentStrategyFactory.getParkingSpotAssignmentStrategy(strategyType);

        ParkingSpot parkingSpot = parkingSpotAssignmentStrategy.assignParkingSpot(gate, savedVehicle);

        ticket.setParkingSpot(parkingSpot);

        ticketRepository.saveTicket(ticket);

        return ticket;
    }
}
