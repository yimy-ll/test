package com.example.backend.domain.dto;

import com.example.backend.domain.entities.Client;
import com.example.backend.domain.entities.Product;
import com.example.backend.domain.entities.ProductVersion;
import com.example.backend.domain.entities.Ticket;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductVersionDTO {

    private Long id;

    private Product product;

    private String name;

    private String description;

    private Date creationDate;

    private List<TicketDTO> tickets;

    private Long productId;


    public static ProductVersionDTO map(ProductVersion productVersion) {
        if (productVersion == null) {
            return null;
        }
        List<Ticket> list = productVersion.getTickets() == null ? new ArrayList<>() : productVersion.getTickets();
        return ProductVersionDTO.builder().id(productVersion.getId()).
                name(productVersion.getName()).
                description(productVersion.getDescription()).
                creationDate(productVersion.getCreationDate()).
                tickets(list.stream().map(TicketDTO::map).collect(Collectors.toList())).
                productId(productVersion.getProduct().getId()).build();

    }
}
