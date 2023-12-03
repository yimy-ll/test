package com.example.backend.domain.services;

import com.example.backend.domain.entities.Product;

import java.util.Collection;

public interface IProductService {

    public Collection<Product> getProducts();

    public Product getProductById(Long id);

    public Product save(Product product);
}
