package com.harsh.ecommers.Controller;

import com.harsh.ecommers.Service.ProductService;
import com.harsh.ecommers.Model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductService service;

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return service.getProducts();
    }

    @GetMapping("/product/{pid}")
    public Product getProduct(@PathVariable int pid) {
        return service.getProductById(pid);
    }

    @PostMapping("/product/add")
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        service.addProduct(product);
        return ResponseEntity.ok("Product added successfully");
    }

    @PutMapping("/product/{pid}/update")
    public ResponseEntity<String> updateProduct(@PathVariable int pid, @RequestBody Product prod) {
        service.updateProduct(pid, prod);
        return ResponseEntity.ok("Product updated successfully");
    }
    @DeleteMapping("/remove/{pid}")
    public ResponseEntity<String> deleteProduct(@PathVariable int pid) {
        service.deleteProduct(pid);
        return ResponseEntity.ok("Product removed successfully");
    }

}
