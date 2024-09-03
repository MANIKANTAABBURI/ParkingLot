package org.example.controllers;

import org.example.dtos.IssueTicketRequestDTO;
import org.example.dtos.IssueTicketResponseDTO;
import org.example.enums.ResponseStatus;
import org.example.models.Ticket;
import org.example.services.TicketService;

public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public IssueTicketResponseDTO issueTicket(IssueTicketRequestDTO issueTicketRequestDTO){
        IssueTicketResponseDTO issueTicketResponseDTO = new IssueTicketResponseDTO();

        try{
            Ticket ticket = ticketService.issueTicket(
                    issueTicketRequestDTO.getGateId(),
                    issueTicketRequestDTO.getVehicleNumber(),
                    issueTicketRequestDTO.getOwnerName(),
                    issueTicketRequestDTO.getVehicleType()
            );

            issueTicketResponseDTO.setTicket(ticket);
            issueTicketResponseDTO.setResponseStatus(ResponseStatus.SUCCESS);
        }catch (Exception e){
            issueTicketResponseDTO.setTicket(null);
            issueTicketResponseDTO.setResponseStatus(ResponseStatus.FAILURE);
        }
        return issueTicketResponseDTO;

    }
}
