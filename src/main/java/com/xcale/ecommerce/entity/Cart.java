package com.xcale.ecommerce.entity;

import lombok.Data;
import java.util.List;

@Data
public class Cart {

    private String id;
    private List<Product> lstProducts;

}
