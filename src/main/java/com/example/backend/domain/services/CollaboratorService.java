package com.example.backend.domain.services;

import com.example.backend.domain.entities.Collaborator;
import com.example.backend.domain.handlers.NotFoundException;
import com.example.backend.domain.repositories.CollaboratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class CollaboratorService implements ICollaboratorService {
    @Autowired
    CollaboratorRepository collaboratorRepository;

    @Override
    public Collection<Collaborator> getCollaborators() {
        return collaboratorRepository.findAll();
    }

    @Override
    public Collaborator getCollaboratorById(Long id) {
        Optional<Collaborator> collaboratorOptional =  collaboratorRepository.findById(id);
        if (collaboratorOptional.isEmpty()) {
            throw new NotFoundException(String.format("Collaborator with id: %d not found", id));
        }
        return collaboratorOptional.get();
    }
}
