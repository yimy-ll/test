package com.example.backend.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_version_id")
    private ProductVersion productVersion;

    private String title;

    private String description;

    private String state;

    private String client;

    @CreationTimestamp
    @Column(name = "creationDate")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updateDate")
    private Date updatedAt;

    private String severity;

    //Comentarios, historial de comentarios

    @ElementCollection
    @CollectionTable(
            name="task",
            joinColumns=@JoinColumn(name="ticket_id")
    )
    @Column(name="task_id")
    private List<Long> listLinkedTasks = new ArrayList<>();

}
