package com.xcale.ecommerce.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "coll_product")
public class Product {

    private Long id;
    private String description;
    private Double amount;
}
