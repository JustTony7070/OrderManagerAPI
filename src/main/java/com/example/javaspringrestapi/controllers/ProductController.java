package com.example.javaspringrestapi.controllers;

import com.example.javaspringrestapi.entities.Product;
import com.example.javaspringrestapi.repos.ProductRepo;
import com.example.javaspringrestapi.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;
    private final ProductRepo productRepo;

    public ProductController(ProductService productService, ProductRepo productRepo) {
        this.productService = productService;
        this.productRepo = productRepo;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getProducts(){
        return ResponseEntity.ok(productRepo.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        return ResponseEntity.ok(productRepo.findById(id).get());
    }

    @PostMapping()
    public ResponseEntity<Product> addNewProduct(@RequestBody Product product){
        product.setId(null);
        return new ResponseEntity<>(productRepo.save(product), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> overwriteProduct(@PathVariable Long id, @RequestBody Product product){
        if (!productRepo.existsById(id)){
            return new ResponseEntity<>("ID not found", HttpStatus.NOT_FOUND);
        }

        product.setId(id);

        return ResponseEntity.ok(productRepo.save(product));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Map<String, Object> updates)
            throws NoSuchFieldException, IllegalAccessException {
        Product p = productRepo.findById(id).get();
        updates.remove("Id");
        return ResponseEntity.ok(productService.updateProduct(p, updates));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        if (!productRepo.existsById(id)){
            return new ResponseEntity<>("ID not found", HttpStatus.NOT_FOUND);
        }

        productRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
