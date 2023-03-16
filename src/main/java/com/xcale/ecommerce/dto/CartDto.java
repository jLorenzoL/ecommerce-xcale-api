package com.xcale.ecommerce.dto;

import lombok.Data;

import java.util.List;

@Data
public class CartDto {

    private String id;
    private List<ProductDto> lstProducts;

}
