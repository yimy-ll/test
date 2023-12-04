package com.example.backend.domain.services;

import com.example.backend.domain.dto.TicketDTO;
import com.example.backend.domain.entities.ProductVersion;
import com.example.backend.domain.entities.Ticket;
import com.example.backend.domain.entities.Client;
import com.example.backend.domain.repositories.TicketRepository;

import com.example.backend.domain.handlers.NotFoundException;
import com.example.backend.domain.dto.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService implements ITicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private ProductVersionService productVersionService;

 //   @Autowired
 //   private ITicketTaskService ticketTaskService;

   // @Override
   // public Ticket createTicket(Ticket ticket) {
   //     return ticketRepository.save(ticket);
   // }

    @Override
    public Ticket createTicket(TicketDTO ticket) {
        return ticketRepository.save(Ticket.builder().
                productVersion(productVersionService.getVersionById(ticket.getProductVersionId())).
                title(ticket.getTitle()).
                description(ticket.getDescription()).
                state(ticket.getState()).
                severity(ticket.getSeverity()).
                createdAt(ticket.getCreatedAt()).
                listLinkedTasks(ticket.getListLinkedTasks()).
                client(ticket.getClient()).build());
    }
    @Override
    public List<Ticket> findByProductVersionId(Long productVersionId) {
        return ticketRepository.findByProductVersionId(productVersionId);
    }

    @Override
    public Ticket getTicketById(Long id) {
        Optional<Ticket> ticketOptional =  ticketRepository.findById(id);
        if (ticketOptional.isEmpty()) {
            throw new NotFoundException(String.format("Ticket with id: %d not found", id));
        }
        return ticketOptional.get();
    }

    @Override
    public Ticket save(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket updateTicket(Ticket newTicket, Long id) {
        if (ticketRepository.findById(id).isEmpty()) {
            throw new NotFoundException(String.format("Ticket with id: %d not found", id));
        }
        Ticket ticket = ticketRepository.findById(id).get();
        ticket.setTitle(newTicket.getTitle());
        ticket.setDescription(newTicket.getDescription());
        ticket.setState(newTicket.getState());
        ticket.setSeverity(newTicket.getSeverity());
        ticket.setListLinkedTasks(newTicket.getListLinkedTasks());
        return ticketRepository.save(ticket);
    }

    @Override
    public MessageDTO deleteTicketById(Long id) {
        try {
            ticketRepository.deleteById(id);
            return new MessageDTO(String.format("Delete ticket with id: %d success", id));
        } catch (Exception e) {
            throw new NotFoundException("Ticket not found");
        }
    }

    @Override
    public List<Ticket> getTickets(){
        return ticketRepository.findAll();
    }

/*
    @Override
    public Client getClientByTicketId(Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Ticket with id: %d not found", id)));

        return ticket.getClient();
    }

 */

//    @Override
//    public void associateTask(Long ticketId, Long taskId) {
//        ticketTaskService.createTicketTask(ticketId, taskId);
//    }
}
