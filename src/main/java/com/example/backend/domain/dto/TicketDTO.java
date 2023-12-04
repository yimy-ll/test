package com.example.backend.domain.dto;

import com.example.backend.domain.entities.Client;
import com.example.backend.domain.entities.ProductVersion;
import com.example.backend.domain.entities.Ticket;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TicketDTO {

    private Long id;

    private ProductVersion productVersion;

    private String client;

    private String title;

    private String description;

    private String state;

    private Date createdAt;

    private Date updatedAt;

    private String severity;

    private Long productVersionId;

    private List<Long> listLinkedTasks;

    public static TicketDTO map(Ticket ticket) {
        if (ticket == null) {
            return null;
        }
        return TicketDTO.builder().id(ticket.getId()).
                title(ticket.getTitle()).
                description(ticket.getDescription()).
                state(ticket.getState()).
                severity(ticket.getSeverity()).
                createdAt(ticket.getCreatedAt()).
                updatedAt(ticket.getUpdatedAt()).
                client(ticket.getClient()).
                listLinkedTasks(ticket.getListLinkedTasks()).
                productVersionId(ticket.getProductVersion().getId()).build();
    }
}
