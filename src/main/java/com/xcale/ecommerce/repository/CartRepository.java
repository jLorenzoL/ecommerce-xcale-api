package com.xcale.ecommerce.repository;

import com.xcale.ecommerce.entity.Cart;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class CartRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    public void saveCartGenerated(String cartId){
        Cart collCart = new Cart();
        collCart.setId(cartId);
        mongoTemplate.save(collCart);
    }
}
