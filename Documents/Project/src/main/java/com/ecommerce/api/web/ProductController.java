package com.ecommerce.api.web;

import com.ecommerce.api.domain.Product;
import com.ecommerce.api.repository.ProductRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/api/v1/products") @RequiredArgsConstructor
public class ProductController {
    private final ProductRepository products;
    @GetMapping @Cacheable("products") public List<Product> all() { return products.findAll(); }
    @GetMapping("/{id}") @Cacheable(value = "product", key = "#id")
    public Product one(@PathVariable Long id) { return products.findById(id).orElseThrow(); }
    @PostMapping @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Product> create(@Valid @RequestBody Product product) {
        product.setId(null); return ResponseEntity.status(HttpStatus.CREATED).body(products.save(product));
    }
}
