package com.example.backend.domain.services;

import com.example.backend.domain.dto.MessageDTO;
import com.example.backend.domain.entities.Task;

import java.util.Collection;

public interface ITaskService {

    public Collection<Task> getTasks();

    public Task createTask(Task task);

    public Task getTaskById(Long id);

    public void saveTask(Task product);

    public MessageDTO deleteTaskById(Long id);

    //public void associateTicket(Long ticketId, Long taskId);
}
