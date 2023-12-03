package com.example.backend.domain.services;

import com.example.backend.domain.entities.ProductVersion;

import java.util.Collection;

public interface IProductVersionService {
    Collection<ProductVersion> getProductVersions(Long productId);

    ProductVersion getVersionById(Long id);
}
