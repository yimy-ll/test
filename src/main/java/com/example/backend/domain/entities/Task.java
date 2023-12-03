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
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private Long idCollaborator;

    private String name;

    private String description;

    private String state;

    private Date creationDate;

    //private int severity;

    @ManyToMany(mappedBy = "listLinkedTasks")
    private List<Ticket> listLinkedTickets = new ArrayList<>();

    public List<Ticket> getListLinkedTickets() {
        return listLinkedTickets;
    }
}
