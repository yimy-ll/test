package com.example.backend.controllers;

import com.example.backend.domain.dto.ProductVersionDTO;
import com.example.backend.domain.dto.TicketDTO;
import com.example.backend.domain.entities.Product;
import com.example.backend.domain.entities.ProductVersion;
import com.example.backend.domain.entities.Ticket;
import com.example.backend.domain.services.IProductService;
import com.example.backend.domain.services.IProductVersionService;
import com.example.backend.domain.services.ITicketService;
import com.example.backend.domain.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/products/versions")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductVersionController {
    @Autowired
    private IProductVersionService productVersionService;

    @Autowired
    private ITicketService ticketService;

    /*@GetMapping
    public ResponseEntity<?> getProductVersions(@PathVariable Long productId) {
        return new ResponseEntity<>(productVersionService.getProductVersions(productId), HttpStatus.OK);
    }*/

    @GetMapping("/{id}")
    public ResponseEntity<?> getVersionById(@PathVariable Long id) {
        return new ResponseEntity<>(ProductVersionDTO.map(productVersionService.getVersionById(id)), HttpStatus.OK);
    }

    @GetMapping("/{id}/tickets")
    public ResponseEntity<?> getTickets(@PathVariable Long id) {
        return new ResponseEntity<>((ticketService.findByProductVersionId(id).stream().map(TicketDTO::map)), HttpStatus.OK);
    }

    @PostMapping("/tickets")
    public ResponseEntity<?> createTicket(@RequestBody TicketDTO ticket) {

        Ticket newTicket = null;
        Map<String, Object> response = new HashMap<>();
        try {
            newTicket = ticketService.createTicket(ticket);
        } catch (DataAccessException e) {
            response.put("message", "Error: Ticket creation failed");
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("data", TicketDTO.map(newTicket));
        response.put("message", "Success: Ticket created");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /*@PostMapping("/product/save")
    public ResponseEntity<Map<String, Object>> createProduct(@RequestBody Product product) {
        Product newProduct = null;
        Map<String, Object> response = new HashMap<>();
        try {
            newProduct = productService.save(product);
            System.out.println("newProduct: " + product);
            System.out.println("newProduct name: " + product.getName());
            System.out.println("newProduct: " + product.getId());

        } catch (DataAccessException e) {
            response.put("message", "Error: product creation failed");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("data", newProduct);
        response.put("message", "Success: product created");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }*/
}
