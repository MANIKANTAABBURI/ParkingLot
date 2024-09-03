package org.example.respositories;

import org.example.models.Ticket;

import java.util.Optional;

public class TicketRepository {
    public Optional<Ticket> findTicketByTicketId(long ticketId) {
        return Optional.empty();
    }

    public Ticket saveTicket(Ticket ticket) {
        return null;
    }
}
