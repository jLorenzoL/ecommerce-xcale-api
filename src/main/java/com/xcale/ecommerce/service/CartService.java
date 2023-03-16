package com.xcale.ecommerce.service;

import com.xcale.ecommerce.entity.Cart;
import com.xcale.ecommerce.excepcion.BussinessExcepcion;
import com.xcale.ecommerce.external.feign.client.FeignExternalClient;
import com.xcale.ecommerce.mapper.ConfigMapper;
import com.xcale.ecommerce.util.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartService {

    private final FeignExternalClient feignExternalClient;

    @Autowired
    CacheManager cacheManager;

    @Autowired
    ConfigMapper customerMapper;

    @Cacheable(cacheNames  = "cartId")
    public String obtenerIdCart(){
        String codigo = "";
        if (Objects.requireNonNull(cacheManager.getCache("cartId")).get("cartId") == null) {
            Objects.requireNonNull(cacheManager.getCache("cartId")).put("cartId", Utils.generateOrder());
        }
        codigo =  Objects.requireNonNull(Objects.requireNonNull(cacheManager.getCache("cartId")).get("cartId")).get().toString();
        return codigo;
    }

    public Map<String, Object> createCart(){
        Map<String, Object> map = new HashMap<>();
        map.put("cartId", obtenerIdCart());
        return map;
    }

    public Map<String, Object> getProducts(){
        Map<String, Object> map = new HashMap<>();
        map.put("lstProducts", customerMapper.map(feignExternalClient.getProducts()));
        return map;
    }

    public Map<String, Object> addProductsToCart(Cart cart){
        Map<String, Object> map = new HashMap<>();

        if (Objects.requireNonNull(cacheManager.getCache("cartId")).get("cartId") == null) {
            throw new BussinessExcepcion("Cart id is not generated, execute service /generateCart");
        }

        cart.getLstProducts().forEach(x->{
            if(Objects.isNull(feignExternalClient.getProductById(String.valueOf(x.getId())))){
                throw new BussinessExcepcion("The product ".concat(x.getDescription()).concat(" with id ").concat(String.valueOf(x.getId())).concat(" is not found."));
            }
        });

        map.put("cartId", obtenerIdCart());
        map.put("lstProducts", cart.getLstProducts());
        return map;

    }

    public void getCacheAndClear() {

        String cacheName = "cartId";
        final Cache cache = cacheManager.getCache(cacheName);
        if (cache == null) {
            throw new BussinessExcepcion("invalid cache name: " + cacheName);
        }
        cache.clear();
    }

}
