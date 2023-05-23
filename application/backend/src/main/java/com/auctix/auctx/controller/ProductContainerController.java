package com.auctix.auctx.controller;

import com.auctix.auctx.converter.ProductConverter;
import com.auctix.auctx.converter.ProductImageConverter;
import com.auctix.auctx.dto.ProductContainerDto;
import com.auctix.auctx.dto.ProductDto;
import com.auctix.auctx.dto.ProductImageDto;
import com.auctix.auctx.model.Product;
import com.auctix.auctx.model.ProductImage;
import com.auctix.auctx.service.ProductImageService;
import com.auctix.auctx.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductContainerController {

    private final ProductService productService;
    private final ProductConverter productConverter;
    private final ProductImageService productImageService;
    private final ProductImageConverter productImageConverter;


    @GetMapping("/findAllProductsContainer")
    public ProductContainerDto findAllProductsContainer() {
        List<Product> products = productService.getAllProducts();
        List<ProductImage> images = new ArrayList<>();
        for (Product product : products) {
            images.add(productImageService.findByProductId(product.getId()));
        }

        List<ProductDto> productDtos = productConverter.convertModelListToDtoList(products);
        List<ProductImageDto> productImages = productImageConverter.convertModelListToDtoList(images);

        return ProductContainerDto.builder()
                .products(productDtos)
                .images(productImages)
                .build();

    }
}
