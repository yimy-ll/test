package com.example.backend.controllers;

import com.example.backend.domain.dto.ProductDTO;
import com.example.backend.domain.dto.ProductVersionDTO;
import com.example.backend.domain.entities.Product;
import com.example.backend.domain.entities.ProductVersion;
import com.example.backend.domain.services.IProductService;
import com.example.backend.domain.services.IProductVersionService;
import com.example.backend.domain.services.ProductService;
import com.example.backend.domain.services.ProductVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {
    @Autowired
    private IProductService productService;

    @Autowired
    private IProductVersionService productVersionService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> createProduct(@RequestBody Product product) {
        Product newProduct = null;
        Map<String, Object> response = new HashMap<>();
        try {
            newProduct = productService.save(product);
        } catch (DataAccessException e) {
            response.put("message", "Error: Product creation failed");
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("data", ProductDTO.map(newProduct));
        response.put("message", "Success: Product created");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getProducts() {
        return new ResponseEntity<>(productService.getProducts().stream().map(ProductDTO::map), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        return new ResponseEntity<>(ProductDTO.map(productService.getProductById(id)), HttpStatus.OK);
    }

    @PostMapping("/versions")
    public ResponseEntity<Map<String, Object>> createProductVersion(@RequestBody ProductVersionDTO productVersion) {
        ProductVersion newProductVersion = null;
        Map<String, Object> response = new HashMap<>();
        try {
            newProductVersion = productVersionService.createProductVersion(productVersion);
        } catch (DataAccessException e) {
            response.put("message", "Error: Product Version creation failed");
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("data", ProductVersionDTO.map(newProductVersion));
        response.put("message", "Success: product Version created");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}/versions")
    public ResponseEntity<?> getProductVersions(@PathVariable Long id) {
        return new ResponseEntity<>(productVersionService.findByProductId(id).stream().map(ProductVersionDTO::map), HttpStatus.OK);
    }
}
