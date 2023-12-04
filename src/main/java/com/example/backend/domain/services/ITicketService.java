package com.example.backend.domain.services;

import com.example.backend.domain.dto.MessageDTO;
import com.example.backend.domain.dto.TicketDTO;
import com.example.backend.domain.entities.Client;
import com.example.backend.domain.entities.Ticket;

import java.util.List;

public interface ITicketService {
    List<Ticket> findByProductVersionId(Long productVersionId);

    Ticket createTicket(TicketDTO ticket);

    List<Ticket> getTickets();

    Ticket getTicketById(Long id);

    Ticket save(Ticket ticket);

    Ticket updateTicket(Ticket ticket, Long id);

    MessageDTO deleteTicketById(Long id);

    //public Client getClientByTicketId(Long id);

   // public void associateTask(Long ticketId, Long taskId);
}
