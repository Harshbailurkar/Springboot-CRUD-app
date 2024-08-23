package com.harsh.ecommers.Controller;

import com.harsh.ecommers.Service.ProductService;
import com.harsh.ecommers.Model.Product;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void addProduct(@RequestBody Product product) {
        service.addProduct(product);
    }
    @PutMapping("/product/{pid}/update")
    public void updateProduct(@PathVariable int pid, @RequestBody Product prod) {
        service.updateProduct(pid,prod);
    }
    @DeleteMapping("/remove/{pid}")
    public void deleteProduct(@PathVariable int pid) {
        service.deleteProduct(pid);
    }

}
