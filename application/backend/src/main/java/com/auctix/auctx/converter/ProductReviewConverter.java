package com.auctix.auctx.converter;

import com.auctix.auctx.dto.ProductReviewDto;
import com.auctix.auctx.model.Product;
import com.auctix.auctx.model.ProductReview;
import com.auctix.auctx.model.Users;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductReviewConverter implements IConverter<ProductReview, ProductReviewDto> {
    @Override
    public ProductReview convertDtoToModel(ProductReviewDto productReviewDto) {
        ProductReview productReview = ProductReview.builder()
                .id(productReviewDto.getId())
                .text(productReviewDto.getText())
                .rating(productReviewDto.getRating())
                .postDate(productReviewDto.getPostDate() == null ? LocalDateTime.now() : productReviewDto.getPostDate())
                .build();

        Users reviewer = new Users();
        reviewer.setId(productReviewDto.getReviewerId());
        productReview.setReviewer(reviewer);
        Product product = new Product();
        product.setId(productReviewDto.getProductId());
        productReview.setProduct(product);

        return productReview;
    }

    @Override
    public ProductReviewDto convertModelToDto(ProductReview productReview) {
        if (productReview == null) {
            return new ProductReviewDto();
        }
        return ProductReviewDto.builder()
                .id(productReview.getId())
                .text(productReview.getText())
                .rating(productReview.getRating())
                .postDate(productReview.getPostDate())
                .productId(productReview.getProduct().getId())
                .reviewerId(productReview.getReviewer().getId())
                .build();
    }

    @Override
    public List<ProductReviewDto> convertModelListToDtoList(List<ProductReview> productReviews) {
        return productReviews.stream()
                .map(this::convertModelToDto)
                .collect(Collectors.toList());
    }
}
