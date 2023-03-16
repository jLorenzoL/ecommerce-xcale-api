package com.xcale.ecommerce.mapper;

import com.xcale.ecommerce.dto.ProductDto;
import com.xcale.ecommerce.external.feign.response.ProductResponse;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ConfigMapper {

    @IterableMapping(qualifiedByName = "extensionMap")
    public abstract List<ProductDto> map(List<ProductResponse> lstProductResponse);

    @Named("extensionMap")
    @Mapping(target="id", source="id")
    @Mapping(target="amount", source="price")
    @Mapping(target="description", source="title")
    ProductDto productEntityToDto(ProductResponse productResponse);

}
