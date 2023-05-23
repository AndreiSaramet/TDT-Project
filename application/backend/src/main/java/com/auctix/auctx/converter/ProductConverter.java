package com.auctix.auctx.converter;

import com.auctix.auctx.dto.ProductDto;
import com.auctix.auctx.model.Product;
import com.auctix.auctx.model.Users;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductConverter implements IConverter<Product, ProductDto> {
    @Override
    public Product convertDtoToModel(ProductDto productDto) {
        var product = Product.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .category(productDto.getCategory())
                .build();
        var user = new Users();
        user.setId(productDto.getUserId());
        product.setUser(user);
        return product;
    }

    @Override
    public ProductDto convertModelToDto(Product product) {
        if (product == null) {
            return new ProductDto();
        }
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .category(product.getCategory())
                .userId(product.getUser().getId())
                .build();
    }

    @Override
    public List<ProductDto> convertModelListToDtoList(List<Product> products) {
        return products.stream()
                .map(this::convertModelToDto)
                .collect(java.util.stream.Collectors.toList());
    }
}
