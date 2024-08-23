package com.harsh.ecommers.Service;
import com.harsh.ecommers.Model.Product;
import com.harsh.ecommers.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Service
public class ProductService {

    @Autowired
    ProductRepo repo;

//    List<Product> product= new ArrayList<>( Arrays.asList(
//            new Product(101,"Iphone",50000),
//            new Product(102,"Samsung",30000),
//            new Product(103,"xiomi",20000)
//    ));
    public List<Product> getProducts() {
//        return product;
        return repo.findAll();
    }

    public Product getProductById(int id) {
//        return product.stream()
//                .filter(p-> p.getProdId()==id)
//                .findFirst().orElse(null);
        return repo.findById(id).orElse(null);
    }

    public void addProduct(Product prod) {
//        product.add(prod);
        repo.save(prod);
    }
    public void updateProduct(int id,Product prod) {
//        product.stream().filter(p-> p.getProdId()==id).findFirst().ifPresent(p->{
//            p.setProdName(prod.getProdName());
//            p.setProdPrice(prod.getProdPrice());   });
        repo.findById(id).ifPresent(p -> {
            p.setProdName(prod.getProdName());
           p.setProdPrice(prod.getProdPrice());
           repo.save(p);
        });
    }
    public void deleteProduct(int id) {
//        product.removeIf(p-> p.getProdId()==id);}
        repo.deleteById(id);
}
}


