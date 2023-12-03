package com.example.backend.domain.services;

import com.example.backend.domain.dto.MessageDTO;
import com.example.backend.domain.entities.Ticket;
import com.example.backend.domain.handlers.NotFoundException;
import com.example.backend.domain.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class TicketService implements ITicketService {

    @Autowired
    private TicketRepository ticketRepository;

 //   @Autowired
 //   private ITicketTaskService ticketTaskService;

    @Override
    public Ticket createTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public Collection<Ticket> getTickets() {
        return ticketRepository.findAll();
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
    public void saveTicket(Ticket ticket) {
        ticketRepository.save(ticket);
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

//    @Override
//    public void associateTask(Long ticketId, Long taskId) {
//        ticketTaskService.createTicketTask(ticketId, taskId);
//    }
}
