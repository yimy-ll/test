package com.example.backend.domain.repositories;

import com.example.backend.domain.entities.ProductVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductVersionRepository extends JpaRepository<ProductVersion, Long> {
    List<ProductVersion> findByProductId(Long productId);
}
