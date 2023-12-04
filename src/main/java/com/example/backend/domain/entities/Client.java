package com.example.backend.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CUIT")
    private Long CUIT;

    private String name;

    private int type;

    private int businessName;

    private int phoneNumber;
}
