package com.example.backend.controllers;

import com.example.backend.domain.entities.Task;
import com.example.backend.domain.services.ITaskService;
import com.example.backend.domain.dto.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TaskController {
    @Autowired
    private ITaskService taskService;

    @GetMapping
    public ResponseEntity<?> getTasks() {
        return new ResponseEntity<>(taskService.getTasks(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody Task task) {
        //return new ResponseEntity<>(taskService.createTask(task), HttpStatus.CREATED);
        Task newTask = null;
        Map<String, Object> response = new HashMap<>();
        try {
            newTask = taskService.save(task);
        } catch (DataAccessException e) {
            response.put("message", "Error: Task creation failed");
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("data", newTask);
        response.put("message", "Success: Task created");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable Long id) {
        return new ResponseEntity<>(taskService.getTaskById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@RequestBody Task task, @PathVariable Long id) {
        try {
            return new ResponseEntity<>(taskService.updateTask(task, id), HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /*@PutMapping("/{idTicket}/link-task/{idTask}")
    public ResponseEntity<?> linkTask(@PathVariable Long idTicket, @PathVariable Long idTask) {
        return new ResponseEntity<>(ticketService.linkTask(idTicket, idTask), HttpStatus.OK);
    }*/

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDTO> deleteTaskById(@PathVariable Long id) {
        return new ResponseEntity<>(taskService.deleteTaskById(id), HttpStatus.OK);
    }
}
