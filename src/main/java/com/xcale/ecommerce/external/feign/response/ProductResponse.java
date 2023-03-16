package com.xcale.ecommerce.external.feign.response;

import lombok.Data;

@Data
public class ProductResponse {

    private Long id;
    private Double price;
    private String title;

}
