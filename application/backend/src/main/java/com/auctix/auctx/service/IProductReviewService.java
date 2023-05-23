package com.auctix.auctx.service;

import com.auctix.auctx.model.ProductReview;

import java.time.LocalDateTime;
import java.util.List;

public interface IProductReviewService {
    ProductReview save(ProductReview productReview);

    void deleteById(Long id);

    ProductReview findById(Long id);

    ProductReview update(ProductReview productReview);

    List<ProductReview> findByRating(Integer rating);

    List<ProductReview> findByRatingDesc();

    List<ProductReview> findByRatingAsc();

    List<ProductReview> findByDateAfter(LocalDateTime afterDate);

    List<ProductReview> findByDateBefore(LocalDateTime beforeDate);

    List<ProductReview> findByDateInterval(LocalDateTime beforeDate, LocalDateTime afterDate);

    List<ProductReview> findAllProductReviewsByProductId(Long productId);

    Integer findReviewCountByProductId(Long productId);
}
