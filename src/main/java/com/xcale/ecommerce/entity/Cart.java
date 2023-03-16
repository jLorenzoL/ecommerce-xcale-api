package com.xcale.ecommerce.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document(collection = "coll_cart")
public class Cart {

    private String id;
    private List<Product> lstProducts;

}
