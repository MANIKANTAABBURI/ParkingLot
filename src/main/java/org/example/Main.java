package org.example;

import org.example.controllers.TicketController;
import org.example.dtos.IssueTicketRequestDTO;
import org.example.dtos.IssueTicketResponseDTO;
import org.example.models.VehicleType;
import org.example.respositories.GateRepository;
import org.example.respositories.ParkingLotRepository;
import org.example.respositories.TicketRepository;
import org.example.respositories.VehicleRepository;
import org.example.services.TicketService;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.printf("Hello and welcome!");

        GateRepository gateRepository  = new GateRepository();
        TicketRepository ticketRepository = new TicketRepository();
        VehicleRepository vehicleRepository = new VehicleRepository();
        ParkingLotRepository parkingLotRepository = new ParkingLotRepository();

        TicketService ticketService = new TicketService(
                gateRepository,
                parkingLotRepository,
                vehicleRepository,
                ticketRepository
        );

        TicketController ticketController = new TicketController(ticketService);

        IssueTicketRequestDTO issueTicketRequestDTO = new IssueTicketRequestDTO();
        issueTicketRequestDTO.setGateId(123);
        issueTicketRequestDTO.setOwnerName("Deepak");
        issueTicketRequestDTO.setVehicleNumber("FR43W45");
        issueTicketRequestDTO.setVehicleType(VehicleType.SEDAN);

        IssueTicketResponseDTO issueTicketResponseDTO = ticketController.issueTicket(issueTicketRequestDTO);


    }
}