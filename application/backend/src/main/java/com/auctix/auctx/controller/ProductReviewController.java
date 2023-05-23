package com.auctix.auctx.controller;

import com.auctix.auctx.converter.ProductReviewConverter;
import com.auctix.auctx.dto.ProductReviewDto;
import com.auctix.auctx.model.ProductReview;
import com.auctix.auctx.service.ProductReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductReviewController {
    private final ProductReviewService productReviewService;

    private final ProductReviewConverter productReviewConverter;
    
    @PostMapping("/addProductReview")
    public ProductReviewDto addProductReview(@RequestBody ProductReviewDto productReviewDto) {
        ProductReview productReview = productReviewConverter.convertDtoToModel(productReviewDto);
        ProductReview addedProductReview = productReviewService.save(productReview);
        return productReviewConverter.convertModelToDto(addedProductReview);
    }

    @PutMapping("/updateProductReview")
    public ProductReviewDto updateProductReview(@RequestBody ProductReviewDto productReviewDto) {
        ProductReview productReview = productReviewConverter.convertDtoToModel(productReviewDto);
        ProductReview updatedProductReview = productReviewService.update(productReview);
        return productReviewConverter.convertModelToDto(updatedProductReview);
    }

    @DeleteMapping("/deleteProductReview/{id}")
    public void deleteProductReview(@PathVariable Long id) {
        productReviewService.deleteById(id);
    }

    @GetMapping("/findProductReviewById/{id}")
    public ProductReviewDto findProductReviewById(@PathVariable Long id) {
        ProductReview productReview = productReviewService.findById(id);
        return productReviewConverter.convertModelToDto(productReview);
    }

    @GetMapping("/findProductReviewByRating/{rating}")
    public List<ProductReviewDto> findProductReviewByRating(@PathVariable Integer rating) {
        List<ProductReview> productReviews = productReviewService.findByRating(rating);
        return productReviewConverter.convertModelListToDtoList(productReviews);
    }

    @GetMapping("/findAllProductReviewsByProductId/{productId}")
    public List<ProductReviewDto> findAllProductReviewsByProductId(@PathVariable Long productId) {
        List<ProductReview> productReviews = productReviewService.findAllProductReviewsByProductId(productId);
        return productReviewConverter.convertModelListToDtoList(productReviews);
    }

    @GetMapping("/countProductReviewsByProductId/{productId}")
    public Integer countProductReviewsByProductId(@PathVariable Long productId) {
        return productReviewService.findReviewCountByProductId(productId);
    }

}
