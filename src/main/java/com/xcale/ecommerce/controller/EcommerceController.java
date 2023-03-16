package com.xcale.ecommerce.controller;

import com.xcale.ecommerce.entity.Cart;
import com.xcale.ecommerce.excepcion.BussinessExcepcion;
import com.xcale.ecommerce.service.CartService;
import com.xcale.ecommerce.util.JsonManagerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
public class EcommerceController {

    @Autowired
    CartService cartService;

    @PostMapping(value = "/getProducts")
    public Map<String, Object> getProducts () {
        try {
            Map<String, Object> map = cartService.getProducts();
            return JsonManagerResponse.correctProcess().buildResponse(map).getResponse();
        } catch (BussinessExcepcion ex) {
            return JsonManagerResponse.procesoError(ex).getResponse();
        }
    }

    @PostMapping("/generateCart")
    public Map<String, Object> getCustomerList() {
        try {
            Map<String, Object> map = cartService.createCart();
            return JsonManagerResponse.correctProcess().buildResponse(map).getResponse();
        } catch (BussinessExcepcion ex) {
            return JsonManagerResponse.procesoError(ex).getResponse();
        }
    }

    @PostMapping("/addProductsToCart")
    public Map<String, Object> addProductsToCart(@RequestBody Cart cart) {
        try {
            Map<String, Object> map = cartService.addProductsToCart(cart);
            return JsonManagerResponse.correctProcess().buildResponse(map).getResponse();
        } catch (BussinessExcepcion ex) {
            return JsonManagerResponse.procesoError(ex).getResponse();
        }
    }

    @DeleteMapping("/deleteCart")
    public Map<String, Object> deleteCart() {
        try {
            cartService.getCacheAndClear();
            return JsonManagerResponse.correctProcess().getResponse();
        } catch (BussinessExcepcion ex) {
            return JsonManagerResponse.procesoError(ex).getResponse();
        }
    }

}
