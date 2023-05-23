package com.auctix.auctx.controller;

import com.auctix.auctx.model.ProductImage;
import com.auctix.auctx.service.ProductImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductImageController {

    private final ProductImageService productImageService;

    @GetMapping("/findProductImageByProductId/{productId}")
    public ProductImage findProductImageByProductId(@PathVariable Long productId) {
        return productImageService.findByProductId(productId);
    }

    @DeleteMapping("/deleteProductImage/{imageId}")
    public void deleteProductImage(@PathVariable Long imageId) {
        productImageService.delete(imageId);
    }

    @PostMapping(path = "/saveProductImage/{productId}")
    public ProductImage saveProductImage(@RequestParam("image") MultipartFile multipartFile, @PathVariable Long productId) throws IOException {
        ProductImage productImage = new ProductImage();
        productImage.setImage(multipartFile.getBytes());
        productImage.setId(productId);
        return productImageService.save(productImage);
    }
}
