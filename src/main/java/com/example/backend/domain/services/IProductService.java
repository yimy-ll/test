package com.example.backend.domain.services;

import com.example.backend.domain.dto.ProductVersionDTO;
import com.example.backend.domain.entities.Product;
import com.example.backend.domain.entities.ProductVersion;

import java.util.Collection;
import java.util.List;

public interface IProductService {

    public Collection<Product> getProducts();

    public Product getProductById(Long id);

    public ProductVersion createProductVersion(ProductVersionDTO productVersion);

    public Product save(Product product);

    public List<ProductVersion> getProductVersions(Long productId);
}
