package com.auctix.auctx.repository;

import com.auctix.auctx.model.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductReviewRepository extends JpaRepository<ProductReview, Long> {
    List<ProductReview> findAllByRating(Integer rating);

    List<ProductReview> findAllByOrderByRatingDesc();

    List<ProductReview> findAllByOrderByRatingAsc();

    List<ProductReview> findAllByPostDateAfter(LocalDateTime afterDate);

    List<ProductReview> findAllByPostDateBefore(LocalDateTime beforeDate);

    List<ProductReview> findAllByPostDateAfterAndPostDateBefore(LocalDateTime afterDate, LocalDateTime beforeDate);

    List<ProductReview> findAllByProductIdOrderByIdDesc(Long productId);

    Integer countAllByProductId(Long productId);
}
