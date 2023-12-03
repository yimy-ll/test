package com.example.backend.domain.services;

import com.example.backend.domain.entities.Product;
import com.example.backend.domain.handlers.NotFoundException;
import com.example.backend.domain.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public Collection<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        Optional<Product> productOptional =  productRepository.findById(id);
        if (productOptional.isEmpty()) {
            throw new NotFoundException(String.format("Product with id: %d not found", id));
        }
        return productOptional.get();
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }
}
