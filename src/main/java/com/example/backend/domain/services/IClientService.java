package com.example.backend.domain.services;

import com.example.backend.domain.entities.Client;

import java.util.Collection;

public interface IClientService {

    public Collection<Client> getClients();

    public Client getClientByCUIT(Long CUIT);
}
