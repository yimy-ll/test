package com.example.backend.domain.services;

import com.example.backend.domain.dto.ProductVersionDTO;
import com.example.backend.domain.entities.ProductVersion;
import com.example.backend.domain.entities.Ticket;

import java.util.List;

public interface IProductVersionService {

    ProductVersion createProductVersion(ProductVersionDTO productVersion);
    List<ProductVersion> getProductVersions(Long productId);
    ProductVersion save(ProductVersion productVersion);
    ProductVersion getVersionById(Long id);

    List<ProductVersion> findByProductId(Long id);

    //List<Ticket> getTickets(Long productVersionId);
}
