package com.harsh.ecommers.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
 // Product Modeling
@Data
@AllArgsConstructor
public class Product {
    private int prodId;
    private String prodName;
    private int prodPrice;
}
