package com.example.backend.domain.services;

import com.example.backend.domain.dto.ClientDTO;
import com.example.backend.domain.dto.ProductVersionDTO;
import com.example.backend.domain.entities.ProductVersion;
import com.example.backend.domain.entities.Ticket;
import com.example.backend.domain.services.ITicketService;
import com.example.backend.domain.handlers.NotFoundException;
import com.example.backend.domain.repositories.ProductVersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductVersionService implements IProductVersionService {
    @Autowired
    ProductVersionRepository productVersionRepository;

    @Autowired
    IProductService productService;

   // @Autowired
    //ITicketService ticketService;

    @Override
    public ProductVersion createProductVersion(ProductVersionDTO productVersion) {
        return productVersionRepository.save(ProductVersion.builder().
                product(productService.getProductById(productVersion.getProductId())).
                name(productVersion.getName()).
                description(productVersion.getDescription()).
                creationDate(productVersion.getCreationDate()).build());
    }
    @Override
    public ProductVersion save(ProductVersion productVersion) {
        return productVersionRepository.save(productVersion);
    }
    @Override
    public List<ProductVersion> getProductVersions(Long productId) {
        return productVersionRepository.findByProductId(productId);
    }

    @Override
    public List<ProductVersion> findByProductId(Long id) {
        return productVersionRepository.findByProductId(id);
    }

    @Override
    public ProductVersion getVersionById(Long id) {
        Optional<ProductVersion> versionOptional =  productVersionRepository.findById(id);
        if (versionOptional.isEmpty()) {
            throw new NotFoundException(String.format("Product version with id: %d not found", id));
        }
        return versionOptional.get();
    }

    //@Override
   // public List<Ticket> getTickets(Long productVersionId) {
 //       return ticketService.getTickets(productVersionId);
  //  }
}
