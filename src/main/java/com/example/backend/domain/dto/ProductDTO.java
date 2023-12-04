package com.example.backend.domain.dto;

import com.example.backend.domain.entities.Product;

import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductDTO {

    private Long id;

    private String name;

    private String description;

    private Date createdAt;

    private List<ProductVersionDTO> productVersions;

    public static ProductDTO map(Product product) {
        if (product == null) {
            return null;
        }
        return ProductDTO.builder().id(product.getId()).
                name(product.getName()).
                description(product.getDescription()).
                createdAt(product.getCreatedAt()).
                productVersions(product.getProductVersions().stream().map(ProductVersionDTO::map).collect(Collectors.toList())).build();

    }
}
