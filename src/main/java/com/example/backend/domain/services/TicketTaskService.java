package com.example.backend.domain.services;/*
package com.psa.backend.domain.services;

import com.psa.backend.domain.entities.TicketTask;
import com.psa.backend.domain.repositories.TicketTaskRepository;
import com.psa.backend.domain.handlers.NotFoundException;
import com.psa.backend.domain.dto.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class TicketTaskService implements ITicketTaskService {
    @Autowired
    private TicketTaskRepository ticketTaskRepository;

    @Override
    public void createTicketTask(Long ticketId, Long taskId) {
        TicketTask ticketTask = new TicketTask(ticketId, taskId);
        ticketTaskRepository.save(ticketTask);
    }

    @Override
    public Collection<TicketTask> getByTicketId(Long ticketId) {
        return ticketTaskRepository.findByTicketId(ticketId);
    }

    @Override
    public Collection<TicketTask> getByTaskId(Long taskId) {
        return ticketTaskRepository.findByTaskId(taskId);
    }

    @Override
    public MessageDTO deleteByTicketId(Long ticketId) {
        try {
            ticketTaskRepository.deleteByTicketId(ticketId);
            return new MessageDTO(String.format("Delete TicketTask with id: %d success", ticketId));
        } catch (Exception e) {
            throw new NotFoundException("TicketTask not found");
        }
    }

    @Override
    public MessageDTO deleteByTaskId(Long taskId) {
        try {
            ticketTaskRepository.deleteByTaskId(taskId);
            return new MessageDTO(String.format("Delete TicketTask with id: %d success", taskId));
        } catch (Exception e) {
            throw new NotFoundException("TicketTask not found");
        }
    }
}

 */
