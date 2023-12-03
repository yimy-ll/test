package com.example.backend.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private Long versionId;

    private Long CUITClient;

    private String title;

    private String description;

    private String state;

    private Date creationDate;

    private int severity;

    @ManyToMany
    @JoinTable(
            name = "ticket_task",
            joinColumns = @JoinColumn(name = "ticket_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id"))
    private List<Task> listLinkedTasks = new ArrayList<>();

    public List<Task> getListLinkedTasks() {
        return listLinkedTasks;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
