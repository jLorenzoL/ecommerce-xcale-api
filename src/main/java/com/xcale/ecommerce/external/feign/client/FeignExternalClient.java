package com.xcale.ecommerce.external.feign.client;

import com.xcale.ecommerce.external.feign.config.FeignConfig;
import com.xcale.ecommerce.external.feign.response.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "${app.feign.config.name}", url = "${app.feign.config.url}", configuration = FeignConfig.class)
public interface FeignExternalClient {

    @RequestMapping(method = RequestMethod.GET, value = "/products")
    List<ProductResponse> getProducts();

    @RequestMapping(method = RequestMethod.GET, value = "/products/{id}")
    ProductResponse getProductById(@PathVariable String id);

}
