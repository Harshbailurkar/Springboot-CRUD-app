package com.harsh.ecommers.Service;
import com.harsh.ecommers.Model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Service
public class ProductService {

    List<Product> product= new ArrayList<>( Arrays.asList(
            new Product(101,"Iphone",50000),
            new Product(102,"Samsung",30000),
            new Product(103,"xiomi",20000)
    ));
    public List<Product> getProducts() {
        return product;
    }

    public Product getProductById(int id) {
        return product.stream()
                .filter(p-> p.getProdId()==id)
                .findFirst().orElse(null);
    }

    public void addProduct(Product prod) {
        product.add(prod);
    }
    public void updateProduct(int id,Product prod) {
        product.stream().filter(p-> p.getProdId()==id).findFirst().ifPresent(p->{
            p.setProdName(prod.getProdName());
            p.setProdPrice(prod.getProdPrice());
        });

    }
    public void deleteProduct(int id) {
        product.removeIf(p-> p.getProdId()==id);
    }
}

