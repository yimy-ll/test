package com.example.backend.domain.services;

import com.example.backend.domain.entities.Collaborator;
import java.util.Collection;

public interface ICollaboratorService {

    public Collection<Collaborator> getCollaborators();

    public Collaborator getCollaboratorById(Long id);

    public Collaborator getCollaboratorByName(String name);
}
