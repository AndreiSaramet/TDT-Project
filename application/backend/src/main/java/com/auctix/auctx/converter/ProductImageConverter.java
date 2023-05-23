package com.auctix.auctx.converter;

import com.auctix.auctx.dto.ProductImageDto;
import com.auctix.auctx.model.ProductImage;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@Component
public class ProductImageConverter implements IConverter<ProductImage, ProductImageDto> {

    @Override
    public ProductImage convertDtoToModel(ProductImageDto productImageDto) {
        ProductImage productImage = new ProductImage();
        productImage.setId(productImageDto.getId());
        productImage.setImage(productImageDto.getImage());
        return productImage;
    }

    @Override
    public ProductImageDto convertModelToDto(ProductImage productImage) {
        if (productImage == null) {
            return new ProductImageDto();
        }
        ProductImageDto productImageDto = new ProductImageDto();
        productImageDto.setId(productImage.getId());
        productImageDto.setImage(productImage.getImage());
        return productImageDto;
    }

    @Override
    public List<ProductImageDto> convertModelListToDtoList(List<ProductImage> productImages) {
        return productImages.stream()
                .map(this::convertModelToDto)
                .collect(java.util.stream.Collectors.toList());
    }
}
