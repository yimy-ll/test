package com.example.backend.domain.repositories;

import com.example.backend.domain.entities.ProductVersion;
import com.example.backend.domain.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByProductVersionId(Long productVersionId);
}
