package com.example.backend.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class ProductVersion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private String name;

    private String description;

    @CreationTimestamp
    private Date creationDate;

    @OneToMany(mappedBy = "productVersion", cascade = CascadeType.ALL)
    private List<Ticket> tickets = new ArrayList<>();
}
