package com.example.backend.domain.dto;

import com.example.backend.domain.entities.Client;
import com.example.backend.domain.entities.Product;
import jakarta.persistence.Entity;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ClientDTO {

    private Long CUIT;

    private String name;

    private int type;

    private int businessName;

    private int phoneNumber;


    public static ClientDTO map(Client client) {
        if (client == null) {
            return null;
        }
        return ClientDTO.builder().CUIT(client.getCUIT()).
                name(client.getName()).
                type(client.getType()).
                businessName(client.getBusinessName()).
                phoneNumber(client.getPhoneNumber()).build();

    }
}
